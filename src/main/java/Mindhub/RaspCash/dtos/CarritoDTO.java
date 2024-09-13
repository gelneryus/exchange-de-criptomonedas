package Mindhub.RaspCash.dtos;

import Mindhub.RaspCash.models.Carrito;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CarritoDTO {

    private long id;
    private double montoTotal;
    private List<ProductoUsuarioDTO> productos=new ArrayList<>();

    public CarritoDTO(Carrito carrito) {
        montoTotal= carrito.getTotal();
        id= carrito.getId();
        productos=carrito.getProductosEnCarrito().stream().map(ProductoUsuarioDTO::new).collect(Collectors.toList());
    }

    public long getId() {
        return id;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public List<ProductoUsuarioDTO> getProductos() {
        return productos;
    }
}
