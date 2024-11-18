package apifestivos.apifestivos.core.interfaces.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apifestivos.apifestivos.dominio.entidades.Festivo;

@Repository
public interface ITipoFestivoRepositorio extends JpaRepository<Festivo, Integer> {

}