package apifestivos.apifestivos.dominio.entidades;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "festivo")
public class Festivo {

    @SuppressWarnings("deprecation")
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "diasG")
    @GenericGenerator(name = "diasG", strategy = "increment")

    private Long id;

    @Column(name = "nombre", length = 100, unique = true)
    private String nombre;

    @Column(name = "dia")
    private int dia;

    @Column(name = "mes")
    private int mes;

    @Column(name = "diaspascua")
    private int diaspascua;

    @Column(name = "idtipo")
    private int idtipo;

    public Festivo() {
    }

    public Festivo(String nombre, int dia, Long id, int idtipo, int mes, int diaspascua) {
        this.nombre = nombre;
        this.dia = dia;
        this.id = id;
        this.idtipo = idtipo;
        this.mes = mes;
        this.diaspascua = diaspascua;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getnombre() {
        return nombre;
    }

    public void setnombre(String nombre) {
        this.nombre = nombre;
    }

    public int getdia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getDiaspascua() {
        return diaspascua;
    }

    public void setDiaspascua(int diaspascua) {
        this.diaspascua = diaspascua;
    }

    public int getIdtipo() {
        return idtipo;
    }

    public void setIdtipo(int idtipo) {
        this.idtipo = idtipo;
    }

}
