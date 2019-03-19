package mx.unam.ciencias.edd.proyecto1;

import java.util.Iterator;
import mx.unam.ciencias.edd.Lista;

public class Banderas {

  public static class Bandera{
    private String bandera;
    private boolean valor;
    private String arg;
    private boolean usaArg;

    public Bandera(String bandera){
      this.bandera = bandera;
      arg = "";
    }
    public Bandera(String bandera, boolean usaArg){
      this.bandera = bandera;
      this.usaArg = usaArg;
      arg = "";
    }
    public String getArg(){
      return arg;
    }
    public boolean getValor(){
      return valor;
    }

  }

  Lista<String> args;
  Lista<Bandera> banderas;

  public Banderas(Bandera... banderas){
    args = new Lista<String>();
    this.banderas = new Lista<Bandera>();
    for(Bandera bandera: banderas)
      this.banderas.agrega(bandera);
  }

  public void analizar(String[] args){
    for (int alfa=0; alfa<args.length; alfa++){
      boolean agregar = true;
      for(Bandera bandera: banderas)
        if(args[alfa].equals("-"+bandera.bandera)){
          agregar = false;
          bandera.valor = true;
          if(bandera.usaArg)
            if((args.length-alfa)>1)
              bandera.arg = args[++alfa];
            else
              throw new IllegalArgumentException("Argumentos insuficientes");
        }
      if(agregar)
        this.args.agrega(args[alfa]);
    }
  }

  public Bandera getBandera(String b){
    for(Bandera bandera: banderas)
      if(bandera.bandera.equals(b))
        return bandera;
    return null;
  }

  public Iterator argsIterator(){
    return args.iterator();
  }
}
