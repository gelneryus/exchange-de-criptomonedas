package Mindhub.RaspCash.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class PrestamoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private double monto;
    private Integer cuotas;

    @ManyToOne(fetch = FetchType.EAGER)// Declaro la relacion Muchos a uno, quiere decir que un tipo de prestamo puede lo puede tener mas de un cliente
    @JoinColumn(name = "usuario_id")//Le agrego una Columna a la base de datos de ClientLoan
    private  Usuario duenioPrestamo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "prestamo_id")
    private Prestamo prestamo;

    public PrestamoUsuario() {
    }

    public PrestamoUsuario(double monto,Integer cuotas, Usuario usuario,Prestamo prestamo){
        this.monto=monto;
        this.cuotas=cuotas;
        this.duenioPrestamo=usuario;
        this.prestamo=prestamo;
    }

    public long getId() {
        return id;
    }

    public double getMonto() {
        return monto;
    }

    public String getNombre(){
       return this.prestamo.getNombre();
    }

    public Integer getCuotas() {
        return cuotas;
    }

    public Usuario getDuenioPrestamo() {
        return duenioPrestamo;
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }

}

