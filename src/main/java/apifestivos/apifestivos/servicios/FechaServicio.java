package apifestivos.apifestivos.servicios;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

    public static List<Date> getFestivosTipo3y4(int año) {
        List<Date> festivos = new ArrayList<>();
        Date domingoPascua = getSemanaSanta(año);

        // Festivos tipo 3
        festivos.add(incrementarDias(domingoPascua, -3)); // Jueves Santo
        festivos.add(incrementarDias(domingoPascua, -2)); // Viernes Santo
        festivos.add(incrementarDias(domingoPascua, 40)); // Ascensión del Señor
        festivos.add(incrementarDias(domingoPascua, 61)); // Corpus Christi
        festivos.add(incrementarDias(domingoPascua, 68)); // Sagrado Corazón de Jesús

        // Festivos tipo 4
        festivos.add(siguienteLunes(incrementarDias(domingoPascua, 40))); // Ascensión del Señor (trasladado)
        festivos.add(siguienteLunes(incrementarDias(domingoPascua, 61))); // Corpus Christi (trasladado)
        festivos.add(siguienteLunes(incrementarDias(domingoPascua, 68))); // Sagrado Corazón de Jesús (trasladado)

        return festivos;
    }

    public static String esFestivo(Date fecha, int año) {
        if (fecha == null) {
            return "Fecha no válida";
        }

        List<Date> festivos = getFestivosTipo3y4(año);
        for (Date festivo : festivos) {
            if (mismaFecha(fecha, festivo)) {
                return "Es festivo";
            }
        }
        return "No es festivo";
    }

    public static boolean mismaFecha(Date fecha1, Date fecha2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(fecha1);
        cal2.setTime(fecha2);

        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
                && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)
                && cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);
    }
}
