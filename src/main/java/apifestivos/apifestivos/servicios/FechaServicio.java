package apifestivos.apifestivos.servicios;

import java.util.Calendar;
import java.util.Date;

public class FechaServicio {

    @SuppressWarnings("deprecation")
    public static Date getSemanaSanta(int año) {
        int a = año % 19;
        int b = año % 4;
        int c = año % 7;
        int d = (19 * a + 24) % 30;
        int dias = d + (2 * b + 4 * c + 6 * d + 5) % 7;
        int dia = 15 + dias;
        int mes = 3;
        return new Date(año - 1900, mes - 1, dia);
    }

    public static Date incrementarDias(Date fecha, int dias) {
        Calendar Cld = Calendar.getInstance();
        Cld.setTime(fecha);
        Cld.add(Calendar.DATE, dias);
        return Cld.getTime();
    }

    public static Date siguienteLunes(Date fecha) {
        Calendar Cld = Calendar.getInstance();
        Cld.setTime(fecha);
        int diaSemana = Cld.get(Calendar.DAY_OF_WEEK);
        if (diaSemana != Calendar.MONDAY) {
            if (diaSemana > Calendar.MONDAY) {
                fecha = incrementarDias(fecha, 9 - diaSemana);
            } else {
                fecha = incrementarDias(fecha, 1);
            }
        }
        return fecha;
    }

}
