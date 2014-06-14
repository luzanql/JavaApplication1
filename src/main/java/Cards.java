
/*
TALLER EVALUABLE #1 de PROGRAMACION INTERACTIVA
Fecha creación: Marzo 23 de 2011
Fecha última modificación: Marzo 23 de 2011
Versión: 0.1
Autores: Luz Andrea Quiceno y Diana Valencia
Codigos: 0924163 - 0924165
Email: luz.quiceno@correounivalle.edu.co y diana.valencia@univalle.edu.co
ESCUELA DE INGENIERÍA DE SISTEMAS Y COMPUTACIÓN - Universidad del Valle

 */
package JuegoCards;

import javax.swing.*;

/**
 *
 * @author luzanql
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;
import java.util.Vector;

public class Cards extends JFrame implements ActionListener {

    private Vector  picPrincipiantes;
    private Vector  picIntermedio;
    private  Vector  picAvanzado;
    Container contenedor;
    JButton nuevaPartida, puntaje, niveles, manual, aCercaDe, abandonar, salir;
    JLabel menu, estado, fondo, fondo2;
    JPanel panelControles, panelEntorno,panel, paneLiconos;
    JComboBox combolevel, comboJugador;
    JButton verBotones [];
    Tablero tab;
    JTextField TFnombre;
    ColeccionPartidas cp;

    public Cards() {
        super("Cards");
          
        nuevaPartida = new JButton("Nueva Partida");
        nuevaPartida.addActionListener(this);
        puntaje = new JButton("Puntajes");
        puntaje.addActionListener(this);
        niveles = new JButton("Niveles de Juego");
        niveles.addActionListener(this);
        manual = new JButton("Manual Basico de Manejo");
        manual.addActionListener(this);
        aCercaDe = new JButton("A cerca de");
        aCercaDe.addActionListener(this);
        abandonar = new JButton("Abandonar Partida");
        abandonar.addActionListener(this);
        abandonar.setEnabled(false);
        salir = new JButton("Salir");
        salir.addActionListener(this);
     
        panelEntorno = new JPanel();
        panelControles = new JPanel (new FlowLayout());
        panel = new JPanel(new GridLayout(7,1));
        panel.add(nuevaPartida);
        panel.add(puntaje);
        panel.add(niveles);
        panel.add(manual);
        panel.add(aCercaDe);
        panel.add(abandonar);
        panel.add(salir);
        panelControles.add(panel);

        
        Image imagen=  new ImageIcon(getClass().getResource("/Recursos/Poker.jpg")).getImage();
        JPanelConFondo panelFondo = new JPanelConFondo(imagen);
        panelFondo.setLayout(new BorderLayout());
        this.add(panelFondo);
        panelControles.setOpaque(false);
        panelEntorno.setOpaque(false);
        panelFondo.add(panelEntorno, BorderLayout.WEST);
        panelFondo.add(panelControles, BorderLayout.EAST);
        panelControles.setBorder(BorderFactory.createLineBorder(Color.red));
        panelEntorno.setBorder(BorderFactory.createLineBorder(Color.green));
        cp = new ColeccionPartidas();
        
         setVisible(true);
    }

    // metodo para saber si ya ha salido un  aleatorio
    public boolean esAleatorio (int yaSalieron[], int aleatorio) {

        for (int i=0; i<yaSalieron.length; i++)
        {
               if (yaSalieron[i] ==aleatorio  )
                   return true;
        }

        return false;
    }


      public Vector ObtenerAleatorios(int numImagenes){

                        Vector vectorImagenes =  new Vector(numImagenes,1);
                        ImageIcon imagen[]=new ImageIcon[numImagenes];
                        Random numAle = new Random();
                        int aleatorio = numAle.nextInt(52)+1;
                        int enteros[] = new int[numImagenes];
                        for (int x = 0; x < numImagenes; x++) {

                            while (esAleatorio(enteros, aleatorio)) {
                                aleatorio = numAle.nextInt(numImagenes)+1;
                            }
                            enteros[x] = aleatorio;
                            imagen[x] = new ImageIcon(getClass().getResource("/Recursos/Cartas-"+aleatorio+".png"));
                            vectorImagenes.add(x, imagen[x]);

                        }
                        return vectorImagenes;
    }

    public void IconosAleatorios() {

        int nPrinci = 4, nMedio = 8, nAvanzado = 12;

        picPrincipiantes = new Vector(nPrinci + 1); // Mas uno porque despues agregaremos la imagen comodin
        picIntermedio = new Vector(nMedio);
        picAvanzado = new Vector(nAvanzado + 1);

        picPrincipiantes = ObtenerAleatorios(nPrinci);
        picPrincipiantes.addElement(new ImageIcon(getClass().getResource("/Recursos/Com.png")));
        picIntermedio = ObtenerAleatorios(nMedio);
        picAvanzado = ObtenerAleatorios(nAvanzado);
        picAvanzado.addElement(new ImageIcon(getClass().getResource("/Recursos/Com.png")));

    }

    public void AsignarCartas(){

        
        Vector vectorImagenes = new Vector();
        int index = combolevel.getSelectedIndex() + 1;
        String tituloVentana = "";

        
        switch (index) {

            case 1:
                tituloVentana = "Cards! - Nivel Principiantes";
                paneLiconos = new JPanel(new GridLayout(1, 5,6,3));
                vectorImagenes = picPrincipiantes;
                break;

            case 2:
                tituloVentana = "Cards! - Nivel Intermedio";
                paneLiconos = new JPanel(new GridLayout(2, 4,6,3));
                vectorImagenes = picIntermedio;
                break;

            case 3:
                tituloVentana = "Cards! - Nivel Avanzado";
                paneLiconos = new JPanel(new GridLayout(4, 4,6,3));
                vectorImagenes = picAvanzado;
                break;
        }

        
         paneLiconos.setBackground(new Color(46, 139, 87));
        JLabel[] Limage = new JLabel[vectorImagenes.size()];
        System.out.print(vectorImagenes.size());
        for (int i=0; i<vectorImagenes.size(); i++){
        Limage[i]= new JLabel((ImageIcon)vectorImagenes.get(i));
        paneLiconos.add(Limage[i]);
        }
       

        JFrame windows = new JFrame(tituloVentana);
        windows.getContentPane().setBackground(new Color(46, 139, 87));
        windows.setLayout(new FlowLayout());
        windows.add(paneLiconos);
        windows.pack(); // tamanio automatico de la ventana
        windows.setVisible(true);

    }
    public void jugador() {

       final JFrame ventanaJugador = new JFrame("Crear Jugador");

       JPanel panelJugador = new JPanel();

       JLabel nivel1,nombre;
       
       JButton Bjugar, Bcancelar;

       String[] levelStrings = {"Principiante", "Intermedio", "Avanzado"};
       comboJugador = new JComboBox(levelStrings);
       comboJugador.setSelectedIndex(0);

       nombre = new JLabel("Nombre");
       nombre.setForeground(Color.WHITE);
       nivel1 = new JLabel("Nivel");
       nivel1.setForeground(Color.WHITE);

       TFnombre= new JTextField(8);
       Bjugar = new JButton("Jugar");
       Bcancelar = new JButton("Cancelar");

       panelJugador.setLayout(new GridLayout(3, 2));
        panelJugador.add(nombre);
        panelJugador.add(TFnombre);
        panelJugador.add(nivel1);
        panelJugador.add(comboJugador);
        panelJugador.add(Bjugar);
        Bjugar.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent evento) {

                        
                        if (1 == comboJugador.getSelectedIndex() + 1) {
                            tab = new Tablero(1, picPrincipiantes);

                        } else if (comboJugador.getSelectedIndex() + 1 == 2) {
                            tab = new Tablero(2, picIntermedio);

                        } else if (comboJugador.getSelectedIndex() + 1 == 3) {
                            tab = new Tablero(3, picAvanzado);
                        }
                        ventanaJugador.setVisible(false);
                        panelEntorno.add(tab, BorderLayout.CENTER);
                        pack();
nuevaPartida.setEnabled(false);
abandonar.setEnabled(true);

                    }

                }
        );
        panelJugador.add(Bcancelar);

        Bcancelar.addActionListener(
                new ActionListener() {  // clase interna an�nima

                    public void actionPerformed(ActionEvent evento) {
                        ventanaJugador.setVisible(false);
                    }
                });

       panelJugador.setBackground(new Color(46, 139, 87));

       ventanaJugador.getContentPane().setBackground(new Color(46, 139, 87));
       ventanaJugador.setLayout(new FlowLayout());
       ventanaJugador.add(panelJugador);
       panelJugador.setBorder(BorderFactory.createTitledBorder("Crear Jugador"));
        ventanaJugador.setVisible(true);
        ventanaJugador.pack();

    }

    public void verPuntajes(){

        String puestos="";
        JTextArea areaTexto = new JTextArea();

        for (int i = 0; i < cp.Tamanio(); i++) {
            puestos = puestos + (i+1)+" \t"
                    +cp.ElementoPartida(i).getPuntaje()+" \t"
                    +cp.ElementoPartida(i).getName()+" \t"
                    +cp.ElementoPartida(i).getNivel()+" \t"
                    +cp.ElementoPartida(i).getClasificacion()+" \t"
                    +cp.ElementoPartida(i).getDate()+ "\n";
        }
        areaTexto.setText(puestos);

        JOptionPane.showMessageDialog(null,areaTexto);

    }

    public void verNiveles() {

        final JFrame ventanaNivel = new JFrame("Ver Niveles");

        JPanel p = new JPanel();

        JLabel nivel;
        JButton Bver, bSalir;

        String[] levelStrings = {"Principiante - 4 Cartas", "Intermedio - 8 Cartas", "Avanzado - 12 Cartas"};
        combolevel = new JComboBox(levelStrings);
        combolevel.setSelectedIndex(0);

        nivel = new JLabel("Nivel a observar");
        nivel.setForeground(Color.WHITE);
        Bver = new JButton("Ver");
        p.setBorder(BorderFactory.createTitledBorder("Niveles"));

        Bver.addActionListener(
                new ActionListener() {  // clase interna an�nima

                    public void actionPerformed(ActionEvent evento) {

                        AsignarCartas();
                    }
                });

        bSalir = new JButton("Salir");
        bSalir.addActionListener(
                new ActionListener() {  // clase interna an�nima

                    public void actionPerformed(ActionEvent evento) {
                        ventanaNivel.setVisible(false);
                    }
                });

        p.setLayout(new GridLayout(2, 2));
        p.add(nivel);
        p.add(Bver);
        p.add(combolevel);
        p.add(bSalir);
        p.setBackground(new Color(46, 139, 87));

        ventanaNivel.getContentPane().setBackground(new Color(46, 139, 87));
        ventanaNivel.setLayout(new FlowLayout());
        ventanaNivel.add(p);
        ventanaNivel.setVisible(true);
        ventanaNivel.pack();
    }
 
    

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == niveles) {
            verNiveles();
        }

        if (e.getSource() == manual) {

            String texto = "", nombreVe = "";

            texto = "* Para iniciar una nueva partida debo:"
                    + "\n\tCerrar la partida Actual --> Click en Iniciar Partida:\n"
                    + "\n* Para ver Puntajes debo:"
                    + "\n\tClick en Puntajes --> Se despliegan los 10 mayores puntajes.\n"
                    + "\n* Para Jugar Debo:"
                    + "\n\tIniciar Una nueva Partida."
                    + "\n\tSeleccionar una Ficha Inicial y otra Final."
                    + "\n\tBuscar Los pares Coincidentes de las fichas.\n"
                    + "\n* Para Terminar la Partida debo."
                    + "\n\tClick en Abandonar Partida.\n"
                    + "\n\nPUNTUACION Y NIVELES:\n\nEn cada acierto, se suma 1 punto al jugador,"
                    + "y en cada desacierto, se resta 1 punto al jugador.\n "
                    + "Pueden existir puntajes negativos. Para puntajes "
                    + "menores o iguales a 0, la clasificación de \n"
                    + "jugador es Dummy; para puntajes etnre 0 y 6, "
                    + "la clasificación del jugador es Basic; \n"
                    + "para puntajes mayores a 6, la clasificicacion del \n"
                    + "jugador es Master. El juego dispondrá de 3 niveles:\n\n"
                    +"* Principiante: dispone de 4 Cartas con su pareja "
                    + "y 1 comodín, para un tablero de 9 (3x3) fichas \n"
                    + "en total. Por un total de 3 desaciertos, \n"
                    + "automaticamente se pierde la partida. El comodin "
                    + "no tiene pareja y no da puntos.\n\n"
                    +"* Intermedio: Dispone de 8 cartas con su "
                    + "respectiva pareja, para un tablero de 16 (4x4) \n"
                    + "fichas en total.  Por un total de 5 desaciertos, "
                    + "automáticamente se pierte la partida.\n\n"
                    +"* Avanzado: dispone de 12 cartas con su pareja"
                    +"y un comodín, para un tabler de 25 (5x5) fichas "
                    +"\nen total. Por un total de 6 desaciertos, \n"
                    +"automáticamente se pierde la partida. El"
                    +"comodin no tiene pareja, y no da puntos.";

            nombreVe = "Manual";


            JFrame ventanaAyuda;
            JTextArea textoAyuda;
            JScrollPane scrollManual;

            textoAyuda = new JTextArea();
            textoAyuda.setEditable(false);
            textoAyuda.setText(texto);


            scrollManual = new JScrollPane(textoAyuda);
            scrollManual.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

            scrollManual.setAutoscrolls(true);

            ventanaAyuda = new JFrame(nombreVe);
            ventanaAyuda.add(scrollManual);
            ventanaAyuda.setSize(550,300);
            

            ventanaAyuda.setVisible(true);


        }

        if (e.getSource() == aCercaDe) {

            Icon icono =  new ImageIcon(getClass().getResource("/Recursos/0.png"));

            JOptionPane.showMessageDialog(this, "Cards!\nVersion 1.0\nDesarrollado por: "
                    + "Diana Marcela - Andrea Lozada\nProgramacion Interactiva 2011A\nMarzo de 2011.",
                    "A Cerca De", JOptionPane.PLAIN_MESSAGE, icono);
        }


        if (e.getSource() == salir) {
            System.exit(0);
        }

        if (e.getSource() == nuevaPartida ){
           
            jugador();
            
        }
        if (e.getSource()==abandonar){
            Partida p = new Partida();
            p.setName(TFnombre.getText());
            p.setNivel(tab.getNivel());
            p.setPuntaje(tab.getPuntaje());
            p.setClasificacion(tab.getClasificacion());
            cp.AgregarPartida(p);
            panelEntorno.remove(tab);
            pack();
            nuevaPartida.setEnabled(true);
            
        } if(e.getSource()==puntaje){
            verPuntajes();
        }

    }


 public static void main(String a[])
 {
      Cards aplicacion = new Cards ();
      aplicacion.IconosAleatorios();
      aplicacion.setSize(600,600);
      aplicacion.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

    
 }
    }


