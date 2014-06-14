/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package JuegoCards;

import java.util.Arrays;
import java.util.Vector;

/**
 *
 * @author Usuario
 */
public class ColeccionPartidas {

      public Vector partidas = new Vector();

      public Partida ElementoPartida(int indice) {
        return (Partida) partidas.elementAt(indice);
    }

    public void AgregarPartida(Partida p){
        
        
        partidas.add(p);
        Vector vectoraux = new Vector();
        int aux[] = new int[partidas.size()];
        for (int i=0; i<partidas.size(); i++){
        aux[i]= ElementoPartida(i).getPuntaje();
        System.out.println("puntaje "+aux[i]);
        }
        Arrays.sort(aux);
        for (int j=0; j<aux.length; j++){
            if(((Partida)partidas.elementAt(j)).getPuntaje() == aux[j]){
                vectoraux.add(0,((Partida)partidas.elementAt(j)) );
            }
        }
        partidas = vectoraux;
        }
    

     public int Tamanio(){
       return partidas.size();
    }

     public Vector getTodo(){
         return partidas;
     }


      public String MostrarPartidas(){

          String salida="";

          for (int i=0; i<partidas.size(); i++){
          Partida p = new Partida();
          p = (Partida)partidas.elementAt(i);
          salida += (p.getName() +"\t" + p.getDate() + "\t" + p.getNivel() +"\t"
                  + p.getClasificacion()+"\t"+ p.getPuntaje()+"\n");

          }

          return salida;
      }
}
