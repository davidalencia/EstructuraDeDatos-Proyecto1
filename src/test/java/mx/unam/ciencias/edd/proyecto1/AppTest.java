package mx.unam.ciencias.edd.proyecto1;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;
import java.util.Iterator;
import java.io.FileWriter;
import java.io.FileReader;
import mx.unam.ciencias.edd.proyecto1.Banderas;
import mx.unam.ciencias.edd.Lista;



/**
 * Unit test for simple App.
 */
public class AppTest{
    @Test
    public void testBanderasR(){
      Banderas.Bandera r = new Banderas.Bandera("r");
      Banderas antonio = new Banderas(r);
      try{
        antonio.analiza(new String[] {""});
        assertFalse(r.getValor());
        antonio.analiza(new String[] {"-r"});
        assertTrue(r.getValor());
        antonio.analiza(new String[] {"a", "b", "c", "d"});
        assertFalse(r.getValor());
        antonio.analiza(new String[] {"a", "b", "c", "-r", "d"});
        assertTrue(r.getValor());

        String[] args = {"arch","arch","arch","arch","arch","arch","arch","arch",
        "arch","arch","arch","arch","arch","arch","arch","arch","arch","arch",
        "arch","arch","arch","arch","arch","arch","arch","arch","arch","arch",
        "arch","arch"};
        int m=29;
        for (Integer alfa=0; alfa<30; alfa++) {
          antonio.analiza(args);
          assertFalse(r.getValor());
          args[m%alfa]="-r";
          antonio.analiza(args);
          assertTrue(r.getValor());
          args[m%alfa]="arch";
        }
      }
      catch (Exception e) {}
    }

    @Test
    public void testBanderasO(){
      Banderas.Bandera o = new Banderas.Bandera("o", true);
      Banderas antonio = new Banderas(o);
      try {
        antonio.analiza(new String[] {"-o"});
        assertTrue(false);//No debe llegar aqui
      } catch(Exception e) {}
      try {
        antonio.analiza(new String[] {"-o", "arch"});
        assertTrue(o.getValor());
        if(o.getValor())
          assertTrue(o.getArg().equals("arch"));
      } catch(Exception e) {}
      try {
        antonio.analiza(new String[] {"a", "b", "c,","-o"});
        assertTrue(false);//No debe llegar aqui
      } catch(Exception e) {}
      try {
        antonio.analiza(new String[] {"a", "b", "c,", "-o", "arch", "a", "b", "c,"});
        assertTrue(o.getValor());
        if(o.getValor())
          assertTrue(o.getArg().equals("arch"));
      } catch(Exception e) {}
    }

    @Test
    public void testBanderasIterador(){
        Banderas antonio = new Banderas();
        Iterator i;
        try {
          antonio.analiza(new String[] {});
          i = antonio.argsIterator();
          assertFalse(i.hasNext());

          antonio.analiza(new String[] {"arch1", "arch1", "arch1"});
          i = antonio.argsIterator();
          assertTrue(i.hasNext());
          assertTrue(i.next().equals("arch1"));
          assertFalse(i.hasNext());

          antonio.analiza(new String[] {"arch1", "arch2", "arch1", "arch2"});
          i = antonio.argsIterator();
          assertTrue(i.hasNext());
          assertTrue(i.next().equals("arch1"));
          assertTrue(i.hasNext());
          assertTrue(i.next().equals("arch2"));
          assertFalse(i.hasNext());

          String[] args = {"1","2","3","4","5","6","7","8","9","10","11","12"};
          antonio.analiza(args);
          i = antonio.argsIterator();
          for (String s: args) {
            assertTrue(i.hasNext());
            assertTrue(i.next().equals(s));
          }

          String[] args2 = {"1","2","3","4","5","6","7","8","9","-r","11","12"};
          antonio.analiza(args);
          i = antonio.argsIterator();
          for (String s: args2) {
            if(!s.equals("-r")){
              assertTrue(i.hasNext());
              assertTrue(i.next().equals(s));
            }
            else
              i.next();
          }

        } catch(Exception e) {}
    }

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
