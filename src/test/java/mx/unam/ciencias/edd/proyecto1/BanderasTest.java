package mx.unam.ciencias.edd.proyecto1;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;
import java.util.Iterator;
import mx.unam.ciencias.edd.Lista;


public class BanderasTest{
  @Test
  public void testBanderasSinArgumento(){
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
  public void testBanderasConArgumento(){
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
}
