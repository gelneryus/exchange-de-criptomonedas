package Mindhub.RaspCash.dtos;

public class AplicacionPrestamoUsuarioDTO {

    long id;
    String monto;
    int cuotas;
    String billeteraDestino;
    String nombrePrestamo;

    public AplicacionPrestamoUsuarioDTO(String monto, int cuotas, String billeteraDestino, String nombrePrestamo) {

        this.monto = monto;
        this.cuotas = cuotas;
        this.billeteraDestino = billeteraDestino;
        this.nombrePrestamo = nombrePrestamo;
    }

    public long getId() {
        return id;
    }

    public String getMonto() {
        return monto;
    }

    public int getCuotas() {
        return cuotas;
    }

    public String obtenerBilleteraDestino() {
        return billeteraDestino;
    }

    public String getNombrePrestamo() {
        return nombrePrestamo;
    }

}
