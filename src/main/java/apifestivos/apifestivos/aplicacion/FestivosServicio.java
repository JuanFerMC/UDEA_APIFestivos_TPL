package apifestivos.apifestivos.aplicacion;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.stereotype.Service;

import apifestivos.apifestivos.core.interfaces.repositorios.IRepositoryFestivo;
import apifestivos.apifestivos.core.interfaces.servicios.IFestivosServicio;
import apifestivos.apifestivos.dominio.entidades.Festivo;
import apifestivos.apifestivos.servicios.FechaServicio;

@Service
public class FestivosServicio implements IFestivosServicio {

    @SuppressWarnings("FieldMayBeFinal")
    private IRepositoryFestivo repositorio;

    public FestivosServicio(IRepositoryFestivo repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public List<Festivo> listar(int año) {
        return repositorio.findAll();
    }

    @Override
    public String verificar(int año, int mes, int dia) {
        Calendar cal = Calendar.getInstance();
        cal.setLenient(false);
        try {
            cal.set(año, mes - 1, dia);
            cal.getTime();
        } catch (IllegalArgumentException e) {
            return "Fecha no válida";
        }
        List<Date> festivos = calcularFestivos(año);

        Calendar fechaSolicitada = Calendar.getInstance();
        fechaSolicitada.set(año, mes - 1, dia);

        for (Date festivo : festivos) {
            Calendar calFestivo = Calendar.getInstance();
            calFestivo.setTime(festivo);
            if (calFestivo.get(Calendar.YEAR) == fechaSolicitada.get(Calendar.YEAR)
                    && calFestivo.get(Calendar.MONTH) == fechaSolicitada.get(Calendar.MONTH)
                    && calFestivo.get(Calendar.DAY_OF_MONTH) == fechaSolicitada.get(Calendar.DAY_OF_MONTH)) {
                return "Es festivo";
            }
        }

        return "No es festivo";
    }

    private List<Date> calcularFestivos(int año) {
        List<Date> festivos = new ArrayList<>();

        Date domingoDePascua = FechaServicio.getSemanaSanta(año);

        List<Festivo> festivosDb = repositorio.findAll();
        for (Festivo festivo : festivosDb) {
            Date fechaFestivo;
            switch (festivo.getIdtipo()) {
                case 1 ->
                    fechaFestivo = new GregorianCalendar(año, festivo.getMes() - 1, festivo.getdia()).getTime();
                case 2 ->
                    fechaFestivo = FechaServicio.siguienteLunes(
                            new GregorianCalendar(año, festivo.getMes() - 1, festivo.getdia()).getTime()
                    );
                case 3 ->
                    fechaFestivo = FechaServicio.incrementarDias(domingoDePascua, festivo.getDiaspascua());
                case 4 ->
                    fechaFestivo = FechaServicio.siguienteLunes(
                            FechaServicio.incrementarDias(domingoDePascua, festivo.getDiaspascua())
                    );
                default -> {
                    continue;
                }
            }
            festivos.add(fechaFestivo);
        }
        return festivos;
    }
}
