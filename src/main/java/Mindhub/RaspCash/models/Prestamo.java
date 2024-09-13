package Mindhub.RaspCash.models;

import org.hibernate.annotations.GenericGenerator;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id ;

    private String nombre;
    private double interes;
    private double monto;
    private double garantia;

    @ElementCollection
    @Column(name="cuotas")
    private List<Integer> cuotas = new ArrayList<>();

    @OneToMany(mappedBy = "duenioPrestamo", fetch = FetchType.EAGER)
    Set<PrestamoUsuario> prestamosUsuario = new HashSet<>();

    public Prestamo() {
    }

    public Prestamo(String nombre, double garantia, double interes, double monto, List<Integer> cuotas) {
        this.nombre = nombre;
        this.interes = interes;
        this.monto = monto;
        this.garantia=garantia;
        this.cuotas=cuotas;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public double getInteres() {
        return interes;
    }

    public double getMonto() {
        return monto;
    }

    public double getGarantia() {
        return garantia;
    }

    public List<Integer> getCuotas() {
        return cuotas;
    }

    public void agregarPrestamoUsuario(PrestamoUsuario prestamoUsuario){
        this.prestamosUsuario.add(prestamoUsuario);


    }
}
