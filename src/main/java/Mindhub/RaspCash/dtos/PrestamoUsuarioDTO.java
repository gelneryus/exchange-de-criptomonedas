package Mindhub.RaspCash.dtos;

import Mindhub.RaspCash.models.PrestamoUsuario;

public class PrestamoUsuarioDTO {

    private long idUsuario, idPrestamo;
    private String nombre;
    private double monto;
    private Integer cuotas;

    public PrestamoUsuarioDTO(PrestamoUsuario prestamoUsuario) {
        this.idUsuario= prestamoUsuario.getDuenioPrestamo().getId();
        this.idPrestamo= prestamoUsuario.getPrestamo().getId();
        this.nombre=prestamoUsuario.getNombre();
        this.monto=prestamoUsuario.getMonto();
        this.cuotas=prestamoUsuario.getCuotas();
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public long getIdPrestamo() {
        return idPrestamo;
    }

    public String getNombre() {
        return nombre;
    }

    public double getMonto() {
        return monto;
    }

    public Integer getCuotas() {
        return cuotas;
    }
}
