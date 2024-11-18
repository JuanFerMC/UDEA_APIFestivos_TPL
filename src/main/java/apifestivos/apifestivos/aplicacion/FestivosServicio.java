package apifestivos.apifestivos.aplicacion;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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
    public List<Festivo> listar() {
        return repositorio.findAll();
    }

    @SuppressWarnings("deprecation")
    @Override
    public String verificar(int año, int mes, int dia) {
        try {
            Date fecha = new Date(año, mes, dia);

            /*Dado que la fecha ingresada no sea valida*/
            if (dia < 1 || dia > 31 || mes < 1 || mes > 12) {
                return "Fecha no valida";
            }
            /*Encontrar festivos fijos */
            Optional<Festivo> festivoFijo = repositorio.findByDiaAndMes(dia, mes);
            if (festivoFijo.isPresent()) {
                return "Es festivo";
            }
            List<Date> festivos = FechaServicio.getFestivosTipo3y4(año);
            for (Date festivo : festivos) {
                if (FechaServicio.mismaFecha(fecha, festivo)) {
                    return "Es festivo";
                }
            }
            return "No es festivo";

        } catch (IllegalArgumentException e) {
            return "No es festivo";

        }
    }
}
