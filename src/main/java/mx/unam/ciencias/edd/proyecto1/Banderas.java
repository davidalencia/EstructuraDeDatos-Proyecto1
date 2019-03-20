package mx.unam.ciencias.edd.proyecto1;

import java.util.Iterator;
import mx.unam.ciencias.edd.Lista;

public class Banderas {

  public static class Bandera{
    private String bandera;
    private boolean valor;
    private String arg;
    private boolean usaArg;

    /**
     * Instancia una bandera que no recibira argumentos
     * @param bandera cadena con la se analizara el texto, i.e. la bandera
     */
    public Bandera(String bandera){
      this.bandera = bandera;
      arg = "";
    }
    /**
     * Instancia una bandera que puede o no recibir argumentos
     * @param bandera cadena con la se analizara el texto, i.e. la bandera
     * @param usaArg booleano que dice si debe recibir algun argumentos
     */
    public Bandera(String bandera, boolean usaArg){
      this.bandera = bandera;
      this.usaArg = usaArg;
      arg = "";
    }

    /**
     * regresa el argumento si es que existe
     * @return arg
     */
    public String getArg(){
      return arg;
    }

    /**
     * despues de llamar analiza regresa si la bandera fue llamada desde los
     * argumentos de terminal, si jamas es llamado analiza entonces regresa fasle
     * @return valor
     */
    public boolean getValor(){
      return valor;
    }

  }

  Lista<String> args;
  Lista<Bandera> banderas;

  /**
   * Instancia todas la variables necesarias para analizar los argumentos.
   * @param banderas puede ser vacio, pero en caso de recibir alguna bandera la
   * agrega a la lista de banderas para mas tarde ser utilizada.
   */
  public Banderas(Bandera... banderas){
    args = new Lista<String>();
    this.banderas = new Lista<Bandera>();
    for(Bandera bandera: banderas)
      this.banderas.agrega(bandera);
  }

  /**
   * Convierte los argumentos pasados por terminal a una lista de argumentos y
   * una lista de banderas con y sin argumentos.
   * @param args los argumentos pasados por terminal.
   */
  public void analiza(String[] args){
    this.args = new Lista<String>();
    for(Bandera bandera: banderas)
      bandera.valor = false;
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
      if(agregar && this.args.indiceDe(args[alfa])==-1)
        this.args.agrega(args[alfa]);
    }
  }

  /**
   * Regresa la bandera de la lista de banderas igual a la cadena pasada.
   * @param b el valor de la cadena a buscar.
   * @return la bandera con el valor de la cadena.
   */
  public Bandera getBandera(String b){
    for(Bandera bandera: banderas)
      if(bandera.bandera.equals(b))
        return bandera;
    return null;
  }

  /**
   * Regresa un iterador de la lista de argumentos que fueron pasados sin contar
   * banderas ni argumentos de las banderas.
   * @return un iterador de la lista de argumentos.
   */
  public Iterator argsIterator(){
    return args.iterator();
  }
}
