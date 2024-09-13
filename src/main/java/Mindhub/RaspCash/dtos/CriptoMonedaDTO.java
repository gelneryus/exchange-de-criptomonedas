package Mindhub.RaspCash.dtos;

import Mindhub.RaspCash.models.CriptoMoneda;
import Mindhub.RaspCash.models.NombreCriptomoneda;
import Mindhub.RaspCash.models.SimboloCriptomoneda;
import lombok.Data;

public @Data class CriptoMonedaDTO {

    private long id;
    private String logo;
    private NombreCriptomoneda nombre;
    private SimboloCriptomoneda simboloCriptomoneda;
    private double cotizacion;

    public CriptoMonedaDTO(CriptoMoneda criptoMoneda) {
        this.id=criptoMoneda.getId();
        this.nombre= criptoMoneda.getNombre();
        this.simboloCriptomoneda=criptoMoneda.getSimboloCriptomoneda();
        this.logo= criptoMoneda.getLogo();
        this.cotizacion= criptoMoneda.getCotizacion();

    }

    public SimboloCriptomoneda getSimboloCriptomoneda() {
        return simboloCriptomoneda;
    }

    public long getId() {
        return id;
    }

    public String getLogo() {
        return logo;
    }

    public NombreCriptomoneda getNombre() {
        return nombre;
    }

    public double getCotizacion() {
        return cotizacion;
    }
}
