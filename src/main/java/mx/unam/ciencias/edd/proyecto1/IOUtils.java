package mx.unam.ciencias.edd.proyecto1;

import java.util.Iterator;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Reader;
import java.io.Writer;
import java.io.IOException;
import mx.unam.ciencias.edd.Coleccion;


public class IOUtils {

  public static void cargaAColeccion(Reader in, Coleccion c) throws IOException{
    BufferedReader br = new BufferedReader(in);
    String l;
    if(br.ready())
      while((l=br.readLine())!=null)
        c.agrega(l);
    br.close();
  }

  public static void cargaABuffer(Writer out, Coleccion c) throws IOException{
    BufferedWriter bw = new BufferedWriter(out);
    Iterator i = c.iterator();
    while(i.hasNext())
      bw.write((String) i.next().toString() + "\n");
    bw.close();
  }


}
