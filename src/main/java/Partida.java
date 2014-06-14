package JuegoCards;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Andrea de Luz
 */
public class Partida {

    private String nombreJugador;
    private Calendar horaJuego;
    private String nivelJugador;
    private String clasificacion;
    private int puntajeAlcanzado;
     Calendar ahora;

public Partida(){
       ahora = Calendar.getInstance();
}
    public String getName() {
        return nombreJugador;
    }


    public String getDate() {
        String fecha="";
      
        int hora = ahora.get(Calendar.HOUR_OF_DAY);
        int minuto = ahora.get(Calendar.MINUTE);
        int mes = ahora.get(Calendar.MONTH) + 1;
        int dia = ahora.get(Calendar.DAY_OF_MONTH);
        int an = ahora.get(Calendar.YEAR);

           // iniciar el mensaje horario mostrando las horas
        fecha+=("Jugó a las ");
        fecha+=(" ");
        fecha+=( (hora > 12) ? (hora - 12) : hora );
        fecha+=(" horas ");

        //  mostrando los minutos

        if (minuto != 0) {
           fecha+=(minuto + " ");
           fecha+=( (minuto != 1) ? "minutos " :
                "minuto ");

        }
              if (hora < 12) {
            fecha+=("de la mañana, ");
        } else if (hora < 17) {
            fecha+=("de la tarde, ");
        } else {
            fecha+=("de la noche, ");
        }

       // mostrar el día
        fecha+=( "El día  " + dia + " de ");

        // mostrar el nombre del mes
        switch (mes) {
            case 1:
                fecha+=("Enero");
                break;
            case 2:
                fecha+=("Febrero");
                break;
            case 3:
                fecha+=("Marzo");
                break;
            case 4:
                fecha+=("Abril");
                break;
            case 5:
                fecha+=("Mayo");
                break;
            case 6:
                fecha+=("Junio");
                break;
            case 7:
                fecha+=("Julio");
                break;
            case 8:
                fecha+=("Agosto");
                break;
            case 9:
                fecha+=("Septiembre");
                break;
            case 10:
                fecha+=("Octubre");
                break;
            case 11:
                fecha+=("Noviembre");
                break;
            case 12:
                fecha+=("Diciembre");
        }

        // mostrar año
       fecha+=(  " de " + an + ".");



        return fecha;
    }

    public String getNivel() {
        return nivelJugador;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public int getPuntaje() {
        return puntajeAlcanzado;
    }

    public void setName(String name) {
        this.nombreJugador = name;
    }


    public void setDate(Calendar date) {
        this.horaJuego = date;
    }

    public void setNivel(String nivel) {
        this.nivelJugador =nivel;
    }

    public void setClasificacion(String clasi) {
        this.clasificacion = clasi;
    }

    public void setPuntaje(int puntaje) {
        this.puntajeAlcanzado = puntaje;
    }
}
