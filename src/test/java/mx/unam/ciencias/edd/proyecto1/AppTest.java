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
    // @Test
    // public void testBanderasR(){
    //   Banderas antonio;
    //   try{
    //     antonio = new Banderas(new String[] {""});
    //     assertFalse(antonio.getReversa());
    //     antonio = new Banderas(new String[] {"-r"});
    //     assertTrue(antonio.getReversa());
    //     antonio = new Banderas(new String[] {"a", "b", "c", "d"});
    //     assertFalse(antonio.getReversa());
    //     antonio = new Banderas(new String[] {"a", "b", "c", "-r", "d"});
    //     assertTrue(antonio.getReversa());
    //
    //     String[] args = {"arch","arch","arch","arch","arch","arch","arch","arch",
    //     "arch","arch","arch","arch","arch","arch","arch","arch","arch","arch",
    //     "arch","arch","arch","arch","arch","arch","arch","arch","arch","arch",
    //     "arch","arch"};
    //     int m=29;
    //     for (Integer alfa=0; alfa<30; alfa++) {
    //       antonio = new Banderas(args);
    //       assertFalse(antonio.getReversa());
    //       args[m%alfa]="-r";
    //       antonio = new Banderas(args);
    //       assertTrue(antonio.getReversa());
    //       args[m%alfa]="arch";
    //     }
    //   }
    //   catch (Exception e) {}
    // }
    //
    // @Test
    // public void testBanderasO(){
    //   Banderas antonio;
    //   try {
    //     antonio = new Banderas(new String[] {"-o"});
    //     assertTrue(false);//No debe llegar aqui
    //   } catch(Exception e) {}
    //   try {
    //     antonio = new Banderas(new String[] {"-o", "arch"});
    //     assertTrue(antonio.getOverwrite());
    //     if(antonio.getOverwrite())
    //       assertTrue(antonio.getFileToWrite().equals("arch"));
    //   } catch(Exception e) {}
    //   try {
    //     antonio = new Banderas(new String[] {"a", "b", "c,","-o"});
    //     assertTrue(false);//No debe llegar aqui
    //   } catch(Exception e) {}
    //   try {
    //     antonio = new Banderas(new String[] {"a", "b", "c,", "-o", "arch", "a", "b", "c,"});
    //     assertTrue(antonio.getOverwrite());
    //     if(antonio.getOverwrite())
    //       assertTrue(antonio.getFileToWrite().equals("arch"));
    //   } catch(Exception e) {}
    // }
    //
    // @Test
    // public void testBanderasIterador(){
    //     Banderas antonio;
    //     Iterator i;
    //     try {
    //       antonio = new Banderas(new String[] {});
    //       i = antonio.getFileIterator();
    //       assertFalse(i.hasNext());
    //
    //       antonio = new Banderas(new String[] {"arch1", "arch1", "arch1"});
    //       i = antonio.getFileIterator();
    //       assertTrue(i.hasNext());
    //       assertTrue(i.next().equals("arch1"));
    //       assertFalse(i.hasNext());
    //
    //       antonio = new Banderas(new String[] {"arch1", "arch2", "arch1", "arch2"});
    //       i = antonio.getFileIterator();
    //       assertTrue(i.hasNext());
    //       assertTrue(i.next().equals("arch1"));
    //       assertTrue(i.hasNext());
    //       assertTrue(i.next().equals("arch2"));
    //       assertFalse(i.hasNext());
    //
    //       String[] args = {"1","2","3","4","5","6","7","8","9","10","11","12"};
    //       antonio = new Banderas(args);
    //       i = antonio.getFileIterator();
    //       for (String s: args) {
    //         assertTrue(i.hasNext());
    //         assertTrue(i.next().equals(s));
    //       }
    //
    //       String[] args2 = {"1","2","3","4","5","6","7","8","9","-r","11","12"};
    //       antonio = new Banderas(args);
    //       i = antonio.getFileIterator();
    //       for (String s: args2) {
    //         if(!s.equals("-r")){
    //           assertTrue(i.hasNext());
    //           assertTrue(i.next().equals(s));
    //         }
    //         else
    //           i.next();
    //       }
    //
    //     } catch(Exception e) {}
    // }

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
