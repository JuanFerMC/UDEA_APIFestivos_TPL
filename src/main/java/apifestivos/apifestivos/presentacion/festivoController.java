package apifestivos.apifestivos.presentacion;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import apifestivos.apifestivos.core.interfaces.servicios.IFestivosServicio;
import apifestivos.apifestivos.dominio.entidades.Festivo;

@RestController
@RequestMapping("/api/festivos")
public class festivoController {

    @SuppressWarnings("FieldMayBeFinal")
    private IFestivosServicio servicio;

    public festivoController(IFestivosServicio servicio) {
        this.servicio = servicio;
    }

    @GetMapping("/listar")
    public List<Festivo> listar() {
        return servicio.listar();
    }

    @GetMapping("/verificar/{año}/{mes}/{dia}")
    public String verificarFestivo(
            @PathVariable int año,
            @PathVariable int mes,
            @PathVariable int dia) {
        return servicio.verificar(año, mes, dia);
    }
}