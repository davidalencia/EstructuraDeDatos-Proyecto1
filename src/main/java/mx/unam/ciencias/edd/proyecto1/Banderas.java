package mx.unam.ciencias.edd.proyecto1;

import java.util.Iterator;
import mx.unam.ciencias.edd.Lista;

public class Banderas {
    private boolean reversa;
    private boolean overwrite;
    private String fileToWrite;
    private Lista<String> archivos;

    public Banderas(String[] args) throws Exception{
      archivos = new Lista<String>();
      for (int alfa=0; alfa<args.length; alfa++){
        if("-r".equals(args[alfa]))
          reversa = true;
        else if("-o".equals(args[alfa])){
          overwrite = true;
          if((args.length-alfa)>1)
            fileToWrite = args[++alfa];
	  else
	      throw new Exception("Argumentos insuficientes");
        }else
          archivos.agrega(args[alfa]);
      }
    }

    public boolean getReversa(){
      return reversa;
    }
    public boolean getOverwrite(){
      return overwrite;
    }
    public String getFileToWrite(){
      return fileToWrite;
    }

    public Iterator getFileIterator(){
      return archivos.iterator();
    }
}
