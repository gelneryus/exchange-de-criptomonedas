package Mindhub.RaspCash.models;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public @Data class CriptoMoneda {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private NombreCriptomoneda nombre;
    private SimboloCriptomoneda simboloCriptomoneda;
    private double cotizacion;
    private String logo;

    @OneToMany(mappedBy = "criptomoneda",fetch = FetchType.EAGER)
    Set<CriptomonedaUsuario> criptomonedasUsuario= new HashSet<>();

    public CriptoMoneda() {
    }

    public CriptoMoneda(NombreCriptomoneda nombre,SimboloCriptomoneda simboloCriptomoneda, double cotizacion) {
        this.nombre = nombre;
        this.simboloCriptomoneda=simboloCriptomoneda;
        this.cotizacion = cotizacion;
    }

    public CriptoMoneda(NombreCriptomoneda nombre, SimboloCriptomoneda simboloCriptomoneda, double cotizacion, String logo){
        this.nombre=nombre;
        this.simboloCriptomoneda=simboloCriptomoneda;
        this.cotizacion=cotizacion;
        this.logo=logo;
    }

    public long getId() {
        return id;
    }

    public NombreCriptomoneda getNombre() {
        return nombre;
    }

    public double getCotizacion() {
        return cotizacion;
    }

    public String getLogo() {
        return logo;
    }

    public SimboloCriptomoneda getSimboloCriptomoneda() {
        return simboloCriptomoneda;
    }
}
