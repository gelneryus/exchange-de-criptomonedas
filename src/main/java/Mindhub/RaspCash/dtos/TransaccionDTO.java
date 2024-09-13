package Mindhub.RaspCash.dtos;

import Mindhub.RaspCash.models.TipoDeMoneda;
import Mindhub.RaspCash.models.TipoDeTransaccion;
import Mindhub.RaspCash.models.Transaccion;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TransaccionDTO {

    private long id;
    private double amount;
    private String descripcion;
    private LocalDateTime date;
    private TipoDeMoneda tipoDeMoneda;
    private TipoDeTransaccion tipo;

    public TransaccionDTO(Transaccion transaccion) {
        this.id=transaccion.getId();
        this.amount=transaccion.getAmount();
        this.tipoDeMoneda=transaccion.getTipoDeMoneda();
        this.tipo=transaccion.getTipo();
        this.date=transaccion.getDate();
        this.descripcion=transaccion.getDescripcion();
    }

    public long getId() {
        return id;
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

    public TipoDeMoneda getTipoDeMoneda() {
        return tipoDeMoneda;
    }

    public TipoDeTransaccion getTipo() {
        return tipo;
    }
}
