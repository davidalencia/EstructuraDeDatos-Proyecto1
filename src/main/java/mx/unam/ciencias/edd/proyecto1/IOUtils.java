package mx.unam.ciencias.edd.proyecto1;

import mx.unam.ciencias.edd.Coleccion;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Reader;
import java.io.Writer;
import java.io.IOException;
import java.util.Iterator;


public class IOUtils {

  public static void cargaAColeccion(Reader in, Coleccion c) throws IOException{
    BufferedReader br = new BufferedReader(in);
    String l;
    while((l=br.readLine())!=null)
      c.agrega(l);
    br.close();
  }

  public static void cargaABuffer(Writer in, Coleccion c) throws IOException{
    BufferedWriter bw = new BufferedWriter(in);
    Iterator i = c.iterator();
    while(i.hasNext())
      bw.write((String) i.next() + "\n");
    bw.close();
  }


}
