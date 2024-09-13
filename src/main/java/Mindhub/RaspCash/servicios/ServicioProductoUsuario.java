package Mindhub.RaspCash.servicios;

import Mindhub.RaspCash.models.ProductoUsuario;
import org.springframework.stereotype.Service;

@Service
public interface ServicioProductoUsuario {

    public ProductoUsuario encontrarProductoUsuarioPorId(long id);
    public void guardarProductoUsuario(ProductoUsuario productoUsuario);
    public void eliminarProductoUsuario(ProductoUsuario productoUsuario);
}
