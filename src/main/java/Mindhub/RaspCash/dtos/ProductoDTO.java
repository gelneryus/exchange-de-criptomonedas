package Mindhub.RaspCash.dtos;

import Mindhub.RaspCash.models.Producto;
import Mindhub.RaspCash.models.TipoProducto;
import lombok.Getter;

@Getter
public class ProductoDTO {

    private long id;
    private int stock;
    private String imagen;
    private double valor;
    private String descripcion;
    private String nombre;
    private TipoProducto tipo;

    public ProductoDTO(Producto producto) {
        this.id= producto.getId();
        this.stock= producto.getStock();
        this.imagen= producto.getImagen();
        this.valor= producto.getValor();
        this.descripcion= producto.getDescripcion();
        this.nombre= producto.getNombre();
        this.tipo=producto.getTipo();
    }

    public long getId() {
        return id;
    }

    public int getStock() {
        return stock;
    }

    public String getImagen() {
        return imagen;
    }

    public double getValor() {
        return valor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public TipoProducto getTipo() {
        return tipo;
    }
}
