package mx.unam.ciencias.edd.proyecto1;

import java.util.Iterator;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileReader;
import mx.unam.ciencias.edd.Lista;

public class App {
    public static void main(String[] args){
      Banderas antonio = new Banderas(args);

      //cambiar por ARN
      Lista<String> texto = new Lista<>();

      Iterator i = antonio.getFileIterator();
      while(i.hasNext())
        try{
          BufferedReader br = new BufferedReader(
                                new FileReader((String )i.next()));
          String l;
          while((l=br.readLine())!=null)
            texto.agrega(l);
        }catch (Exception e) {
          System.out.println("Algo ha fallado.\nPor favor asegurese de que todos los archivos existan.");
        }

      texto = Lista.mergeSort(texto);
      for (String l: texto) {
        System.out.println(l);
      }

      try {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String l;
        while((l=br.readLine())!=null)
          System.out.println(l);
      } catch(Exception e) {

      }
    }
}
