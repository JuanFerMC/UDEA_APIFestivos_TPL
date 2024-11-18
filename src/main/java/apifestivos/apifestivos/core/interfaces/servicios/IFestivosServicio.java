package apifestivos.apifestivos.core.interfaces.servicios;

import java.util.List;

import apifestivos.apifestivos.dominio.entidades.Festivo;

public interface IFestivosServicio {

    public List<Festivo> listar();

    public String verificar(int año, int mes, int dia);
}
