package apifestivos.apifestivos.presentacion;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import apifestivos.apifestivos.core.interfaces.servicios.IFestivosServicio;
import apifestivos.apifestivos.dominio.entidades.Festivo;

@RestController
@RequestMapping("/api/festivos")
@CrossOrigin(origins = "http://localhost:4200/")
public class festivoController {

    private static final Logger logger = LoggerFactory.getLogger(festivoController.class);

    private final IFestivosServicio servicio;

    public festivoController(IFestivosServicio servicio) {
        this.servicio = servicio;
    }

    @GetMapping("/listar/{anio}")
    public List<Festivo> listar(@PathVariable int anio) {
        logger.info("Recibida solicitud para listar festivos del año: {}", anio);
        return servicio.listar(anio);
    }

    @GetMapping("/verificar/{anio}/{mes}/{dia}")
    public String verificar(@PathVariable int anio, @PathVariable int mes, @PathVariable int dia) {
        logger.info("Recibida solicitud para verificar si es festivo: Año {}, Mes {}, Día {}", anio, mes, dia);
        return servicio.verificar(anio, mes, dia);
    }
}
