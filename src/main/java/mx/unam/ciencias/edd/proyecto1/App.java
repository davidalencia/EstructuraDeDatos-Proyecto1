package mx.unam.ciencias.edd.proyecto1;

import java.util.Iterator;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import mx.unam.ciencias.edd.Lista;
import mx.unam.ciencias.edd.Coleccion;

public class App {
    public static void main(String[] args){
      Banderas antonio;
      try{
        antonio = new Banderas(args);
      }catch (Exception e) {
        System.out.println(e.getMessage());
        return;
      }
      //cambiar por ARN
      Lista<String> texto = new Lista<>();

      //leer archivos
      Iterator i = antonio.getFileIterator();
      while(i.hasNext())
        try{
          BufferedReader br = new BufferedReader(
                                new FileReader((String )i.next()));
          cargaBuffer(br, texto);
          br.close();
        }catch (Exception e) {
          System.out.println("Algo ha fallado."+
          "\nPor favor asegurese de que todos los archivos existan.");
        }

      //leer entrada estandar
      try {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        if(br.ready())
          cargaBuffer(br, texto);
        br.close();
      } catch(Exception e) {}

      //imprimir
      texto = texto.mergeSort((a, b)->{
        a=a.trim().toLowerCase().replace("¿", "").replaceAll("á", "a")
      	.replaceAll("é", "e").replaceAll("í", "i").replaceAll("ó", "o")
	.replaceAll("ú", "u").replaceAll("ñ","n").replaceAll(" ", "");
        b=b.trim().toLowerCase().replace("¿", "").replaceAll("á", "a")
          .replaceAll("é", "e").replaceAll("í", "i").replaceAll("ó", "o")
	.replaceAll("ú", "u").replaceAll("ñ","n").replaceAll(" ", "");
        return a.compareTo(b);
      });

      if(antonio.getOverwrite())
        try{
          BufferedWriter bw = new BufferedWriter(
                                    new FileWriter(antonio.getFileToWrite()));
          for (String l: texto)
            bw.write(l+"\n");
          bw.close();
        }
        catch (Exception e) {
          String m = "Algo ha fallado al guardar "+antonio.getFileToWrite();
          System.out.println(m);
        }
      else
        for (String l: texto)
          System.out.println(l);
    }

    private static void
    cargaBuffer(BufferedReader br, Coleccion c) throws Exception{
      String l;
      while((l=br.readLine())!=null)
        c.agrega(l);
    }
}
