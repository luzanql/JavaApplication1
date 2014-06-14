/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JuegoCards;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.ImageIcon;
import javax.swing.*;



/**
 *
 * @author Andrea de Luz
 */
public class Tablero extends JPanel implements ActionListener {

    Vector vectorImagenes;

    int nivel,desaciertosPermitidos, aciertosGanar;
    
    Tablero(int nivel, Vector vectorLogos) {
          
        vectorImagenes = vectorLogos;
        this.nivel = nivel;

        int num = 0;
        switch (nivel) {

            case 1:
                num = 3;
                break;

            case 2:
                num = 4;
                break;

            case 3:
                num = 5;
                break;
        }
      
        this.setLayout(new GridLayout(num, num));
        Vector todasImagenes = volverAleatorio();
       
        Boton[] verBotones = new Boton[todasImagenes.size()];
        for (int i = 0; i < todasImagenes.size(); i++) {
            Boton botonAux = new Boton((ImageIcon) todasImagenes.elementAt(i));
            botonAux.addActionListener(this);
            verBotones[i] = botonAux;
            botonAux.setFocusPainted(false);
            botonAux.setBorderPainted(false);
            botonAux.setContentAreaFilled(false);
            this.add(verBotones[i]);
        }
        this.setOpaque(false);
        this.setSize(200, 200);
    }

    public Vector crearVectorOrdenado() {

        /* Metodo para multiplicar las cartas, nos da el vector con todas las cartas de juego ordenadas */

        Vector vPartidaOrdenada = new Vector(vectorImagenes.size() * 2, 1);
        if (nivel == 1) {
            for (int i = 0; i < vectorImagenes.size(); i++) {
                vPartidaOrdenada.addElement(vectorImagenes.elementAt(i));
                if (i != vectorImagenes.size() - 1) {
                    vPartidaOrdenada.addElement(vectorImagenes.elementAt(i));
                }
            }
            desaciertosPermitidos =2;
            aciertosGanar =4;
        } else if (nivel == 2) {
            for (int i = 0; i < vectorImagenes.size(); i++) {
                vPartidaOrdenada.addElement(vectorImagenes.elementAt(i));
                if (i != vectorImagenes.size()) {
                    vPartidaOrdenada.addElement(vectorImagenes.elementAt(i));
                }
            }
            desaciertosPermitidos =4;
            aciertosGanar =8;
        } else if (nivel == 3) {
            for (int i = 0; i < vectorImagenes.size(); i++) {
                vPartidaOrdenada.addElement(vectorImagenes.elementAt(i));
                if (i != vectorImagenes.size() - 1) {
                    vPartidaOrdenada.addElement(vectorImagenes.elementAt(i));
                }
            }desaciertosPermitidos =5;
            aciertosGanar = 13;
        }
        return vPartidaOrdenada;
    }
    // metodo pone en modo aleatorio las imagenes ordenadas que esten en el vector
public boolean esAleatorio(int yaSalieron[], int aleatorio) {

        for (int i = 0; i < yaSalieron.length; i++) {
            if (yaSalieron[i] == aleatorio) {
                return true;
            }
        }

        return false;
    }
    public Vector volverAleatorio() {

        int num = crearVectorOrdenado().size();

        Vector vectoOrdenado = crearVectorOrdenado();
        Vector vectorAleatorio = new Vector();

                        Random numAle = new Random();
                        int aleatorio = numAle.nextInt(num)+1;
                        int enteros[] = new int[num];
                        for (int x = 0; x < num; x++) {

                            while (esAleatorio(enteros, aleatorio)) {
                                aleatorio = numAle.nextInt(num)+1;
                            }
                            enteros[x] = aleatorio;
                            vectorAleatorio.add(vectoOrdenado.elementAt(aleatorio-1));
                            }

                      
        return vectorAleatorio;
    }

    int cont, aciertos, desaciertos, puntaje;
    String clasificacion = "Perdio!!";
    Boton cartaAcertadaA = new Boton();
    Boton cartaAcertadaB = new Boton();

    public void actionPerformed(ActionEvent ae) {


if (desaciertos<=desaciertosPermitidos){

        if (!((Boton) ae.getSource()).mostrandoImagen) {
              cont++;

            if (cont == 1) {
                ((Boton) ae.getSource()).mostrarImagen(true);

            cartaAcertadaA = ((Boton) ae.getSource());
        } else if (cont == 2) {
            ((Boton) ae.getSource()).mostrarImagen(true);

            if (((Boton) ae.getSource()).getImagen() == cartaAcertadaA.getImagen()) {
                aciertos += 1;
                if (aciertos == aciertosGanar) {
                    puntaje = aciertos - desaciertos;
                    if (puntaje < 0) {
                        clasificacion = "Dummy";
                    } else if (puntaje > 0 && puntaje <= 6) {
                        clasificacion = "Basic";
                    } else if (puntaje > 6) {
                        clasificacion = "Master";
                    }
                 
            JOptionPane.showMessageDialog(null,"FELICIDADES HAS GANADO!!\n"
                    + "Aciertos --> "+aciertos+"\n"
                    + "Desaciertos -> "+desaciertos+"\n"
                    + "Puntaje --> "+puntaje+"\n"
                    + "clasificacion -->"+ clasificacion);

                }
                ((Boton) ae.getSource()).deshabilitarBoton();
                cartaAcertadaA.deshabilitarBoton();
                cont = 0;
            } else {
                cartaAcertadaB = ((Boton) ae.getSource());
            }
        } else if (cont == 3) {
            cont = 0;
            desaciertos += 1;
            cartaAcertadaB.mostrarImagen(false);
            cartaAcertadaA.mostrarImagen(false);
        }


    }
       

        }
 else {
    puntaje = aciertos - desaciertos;
    JOptionPane.showMessageDialog(null,"Ha alcanzado el numero de desaciertos permitidos\n"
            + "en este nivel ("+desaciertos+")\n"
            + " xXx HA PERDIDO LA PARTIDA xXx ");
 
 }
    }

      public String getNivel() {
          String level="";
          if (nivel==1){
              level="principiante";
          }

          else if (nivel==2){
              level="Intermedio";
          }

          else if (nivel==3){
              level="Avanzado";
          }
        return level;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public int getPuntaje() {
        return puntaje;
    }

   
    }

