package mx.unam.ciencias.edd.proyecto1;

import java.util.Iterator;
import java.util.Comparator;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import mx.unam.ciencias.edd.Lista;

public class App {

    private static class ComparaCadenas implements Comparator<String>{
      public int compare(String a, String b){
        return tratamiendo(a).compareTo(tratamiendo(b));
      }
      private static String tratamiendo(String a){
        return a.trim().toLowerCase().replaceAll("¿", "").replaceAll("á", "a")
          .replaceAll("é", "e").replaceAll("í", "i").replaceAll("ó", "o")
          .replaceAll("ú", "u").replaceAll("ñ","n").replaceAll(" ", "");
      }
    }

    public static void main(String[] args){
      Banderas antonio;
      try{
        antonio = new Banderas(args);
      }catch (Exception e) {
        System.out.println(e.getMessage());
        return;
      }

      Lista<String> texto = new Lista<>();

      //leer archivos
      Iterator i = antonio.getFileIterator();
      while(i.hasNext())
        try{
          IOUtils.cargaAColeccion(new FileReader((String) i.next()), texto);
        }catch (IOException e) {
          System.out.println("Algo ha fallado."+
          "\nPor favor asegurese de que todos los archivos existan.");
        }

      //leer entrada estandar
      try {
        if(texto.esVacia())
          IOUtils.cargaAColeccion(new InputStreamReader(System.in), texto);
      } catch(Exception e) {}

      //ordenar
      texto = texto.mergeSort(new ComparaCadenas());

      //salida
      if(antonio.getOverwrite())
        try{
          IOUtils.cargaABuffer(new FileWriter(antonio.getFileToWrite()), texto);
        }
        catch (IOException e) {
          String m = "Algo ha fallado al guardar: "+antonio.getFileToWrite();
          System.out.println(m);
        }
      else
        for (String l: texto)
          System.out.println(l);
    }
}
