/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package JuegoCards;

import javax.swing.JApplet;

/**
 *
 * @author Andrea de Luz
 */
public class cardsApplet extends JApplet {

    /**
     * Initialization method that will be called after the applet is loaded
     * into the browser.
     */
    public void init() {
        Cards aplicacion = new Cards ();
      aplicacion.IconosAleatorios();
      aplicacion.setSize(600,600);
      
    }

    // TODO overwrite start(), stop() and destroy() methods

}
