package Mindhub.RaspCash.models;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
@Entity
public class Transaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private double amount;
    private String descripcion;
    private LocalDateTime date;
    private TipoDeMoneda tipoDeMoneda;
    private TipoDeTransaccion tipo;

    // Declaro la relacion Muchos a uno, quiere decir que una cuenta puede de una a muchas transacciones
    @ManyToOne(fetch = FetchType.EAGER)
    //Le agrego una fila a la base de datos de transacciones, que se va a llamar accounnt_id, el cual es un identificador unico para la cuenta
    @JoinColumn(name = "id_dueño_billetera")
    private Billetera billetera;

    public Transaccion() {
    }


    public Transaccion(TipoDeTransaccion tipo,double amount, String descripcion,LocalDateTime date,TipoDeMoneda tipoDeMoneda, Billetera billetera ){
        this.tipo=tipo;
        this.amount=amount;
        this.descripcion=descripcion;
        this.date=date;
        this.billetera=billetera;
        this.tipoDeMoneda=tipoDeMoneda;
    }


    public long getId() {
        return id;
    }

    public TipoDeTransaccion getTipo() {
        return tipo;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Billetera getBilletera() {
        return billetera;
    }

    public void setTipo(TipoDeTransaccion tipo) {
        this.tipo = tipo;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setBilletera(Billetera billetera) {
        this.billetera = billetera;
    }

    public TipoDeMoneda getTipoDeMoneda() {
        return tipoDeMoneda;
    }
}

