package Mindhub.RaspCash.dtos;

import Mindhub.RaspCash.models.Prestamo;

import java.util.List;

public class PrestamoDTO {

    private long id ;
    private String nombre;
    private double interes;
    private double monto;
    private double garantia;
    private List<Integer> cuotas;

    public PrestamoDTO(Prestamo prestamo) {
        this.nombre= prestamo.getNombre();
        this.id= prestamo.getId();
        this.interes= prestamo.getInteres();
        this.monto=prestamo.getMonto();
        this.garantia= prestamo.getGarantia();
        this.cuotas=prestamo.getCuotas();
    }

    public long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
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
}
