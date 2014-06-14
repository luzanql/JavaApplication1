/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package JuegoCards;

import javax.swing.*;

/**
 *
 * @author Andrea de Luz
 */

public class Boton extends JButton {

    ImageIcon cartaTapada;
    boolean mostrandoImagen;
    ImageIcon imagenCarta;

    public Boton(){

    }

public Boton (ImageIcon imagenCarta){

    this.imagenCarta = imagenCarta;
    cartaTapada = new ImageIcon(getClass().getResource("/Recursos/0.png"));
    this.setIcon(cartaTapada);
    mostrandoImagen = false;
    }

public void mostrarImagen (boolean mostrandoImagen){
    this.mostrandoImagen = mostrandoImagen;
    if(mostrandoImagen){

    this.setIcon(imagenCarta);
    } else {
        this.setIcon(cartaTapada);
    }
}

public void deshabilitarBoton (){

   this.setEnabled(false);
 }

public boolean isMostrandoImagen (){
    
    return mostrandoImagen;
}

public ImageIcon getImagen(){
    return imagenCarta;
}

}
