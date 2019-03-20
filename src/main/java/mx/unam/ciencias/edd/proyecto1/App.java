package mx.unam.ciencias.edd.proyecto1;

import java.util.Iterator;
import java.util.Comparator;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Normalizer;
import mx.unam.ciencias.edd.Lista;

public class App {

    private static class ComparaCadenas implements Comparator<String>{
      public int compare(String a, String b){
        return tratamiendo(a).compareTo(tratamiendo(b));
      }
      private static String tratamiendo(String a){
        return Normalizer.normalize(a, Normalizer.Form.NFKD)
          .replaceAll("[^\\p{ASCII}]", "").trim()
          .toLowerCase().replaceAll(" ", "");
      }
    }

    public static void main(String[] args){
      //analisis de cadenas
      Banderas.Bandera o = new Banderas.Bandera("o", true);
      Banderas.Bandera r = new Banderas.Bandera("r");
      Banderas antonio = new Banderas(o, r);
      try{
        antonio.analiza(args);
      }catch (Exception e) {
        System.out.println(e.getMessage());
        return;
      }

      Lista<String> texto = new Lista<>();

      //leer archivos
      Iterator i = antonio.argsIterator();
      while(i.hasNext())
        try{
          IOUtils.cargaAColeccion(new FileReader((String) i.next()), texto);
        }catch (IOException e) {
          System.out.println("Algo ha fallado."+
          "\nPor favor asegurese de que todos los archivos existan.");
          return;
        }

      //leer entrada estandar
      try {
        IOUtils.cargaAColeccion(new InputStreamReader(System.in), texto);
      } catch(IOException e) {}

      //ordenar
      texto = texto.mergeSort(new ComparaCadenas());

      //reversa
      if(r.getValor())
        texto = texto.reversa();

      //salida
      if(o.getValor())
        try{
          IOUtils.cargaABuffer(new FileWriter(o.getArg()), texto);
        }
        catch (IOException e) {
          System.out.println("Algo ha fallado al guardar: "+o.getArg());
        }
      else
        for (String l: texto)
          System.out.println(l);
    }
}
