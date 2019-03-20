package mx.unam.ciencias.edd.proyecto1;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;
import java.io.FileWriter;
import java.io.FileReader;
import mx.unam.ciencias.edd.Lista;

public class IOUtilsTest{
  @Test
  public void testIO(){
    String arch="testIOUtils.test";
    Lista<String> l = new Lista<>();
    for (int i=0; i<50; i++)
      l.agrega(((Double) Math.random()).toString());

    try{
      IOUtils.cargaABuffer(new FileWriter(arch), l);
    }catch(Exception e){
      assertTrue(false);
    }

    Lista<String> l2 = new Lista<>();
    try {
      IOUtils.cargaAColeccion(new FileReader(arch), l2);
    } catch(Exception e) {
      assertTrue(false);
    }

    assertTrue(l.equals(l2));

  }
}
