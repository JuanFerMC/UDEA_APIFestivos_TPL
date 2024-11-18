package apifestivos.apifestivos.core.interfaces.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apifestivos.apifestivos.dominio.entidades.Festivo;

@Repository
public interface IRepositoryFestivo extends JpaRepository<Festivo, Long> {

    Optional<Festivo> findByDiaAndMes(int dia, int mes);
}
