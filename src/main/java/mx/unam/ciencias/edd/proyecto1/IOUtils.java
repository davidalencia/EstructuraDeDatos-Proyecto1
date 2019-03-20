package mx.unam.ciencias.edd.proyecto1;

import java.util.Iterator;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Reader;
import java.io.Writer;
import java.io.IOException;
import mx.unam.ciencias.edd.Coleccion;


public class IOUtils {

  /**
   * Toma lo que es ingresado en el in y lo agrega a la coleccion.
   * @param in la entrada desde la que se leera.
   * @param c la coleccion a la que se le agregaran los registros.
   */
  public static void cargaAColeccion(Reader in, Coleccion c) throws IOException{
    BufferedReader br = new BufferedReader(in);
    String l;
    if(br.ready())
      while((l=br.readLine())!=null)
        c.agrega(l);
    br.close();
  }

  /**
   * Le la coleccion y lo escribe en el out.
   * @param out la salida en la que se escribira.
   * @param c la coleccion desde la que se leera.
   */
  public static void cargaABuffer(Writer out, Coleccion c) throws IOException{
    BufferedWriter bw = new BufferedWriter(out);
    Iterator i = c.iterator();
    while(i.hasNext())
      bw.write((String) i.next().toString() + "\n");
    bw.close();
  }


}
