package Mindhub.RaspCash.models;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public @Data class CriptomonedaUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="billetera_id")
    private Billetera billeteraOwner;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="criptomoneda_id")
    private CriptoMoneda criptomoneda;

    private double cantidad;

    public CriptomonedaUsuario() {
    }

    public CriptomonedaUsuario(CriptoMoneda criptomoneda, double cantidad) {
        this.criptomoneda = criptomoneda;
        this.cantidad = cantidad;
    }

    // Suma una cantidad de cripto a la cantidad actual, sirve para cuando recibe una transferencia por ejemplo, o cuando se le acredita un prestamo
    public void sumarMonto(double nuevaCantidad){
        this.cantidad+=nuevaCantidad;
    }

    // Resta una cantidad de cripto a la cantidad actual, sirve para cuando se envia una transferencia por ejemplo
    public void restarMonto(double nuevaCantidad){

        if (nuevaCantidad<this.cantidad){
            this.cantidad -=nuevaCantidad;//Se realiza la operacion si el monto a restar es inferior al monto actual
        }

    }
}
