package apifestivos.apifestivos.dominio.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tipo")

public class TipoFestivo {

    @Id
    @ManyToOne
    @JoinColumn(name = "idtipo", referencedColumnName = "id", nullable = false)
    private Festivo tipoFestivos;

    @Column(name = "tipo", length = 100, unique = true)
    private String tipo;

    public TipoFestivo() {
    }

    public TipoFestivo(Festivo tipoFestivos, String tipo) {
        this.tipoFestivos = tipoFestivos;
        this.tipo = tipo;
    }

    public Festivo getTipoFestivos() {
        return tipoFestivos;
    }

    public void setTipoFestivos(Festivo tipoFestivos) {
        this.tipoFestivos = tipoFestivos;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
