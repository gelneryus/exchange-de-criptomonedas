package Mindhub.RaspCash.servicios.implementacionesServicios;

import Mindhub.RaspCash.models.ProductoUsuario;
import Mindhub.RaspCash.respositories.ProductoUsuarioRepositorio;
import Mindhub.RaspCash.servicios.ServicioProductoUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImplementacionServicioProductoUsuario implements ServicioProductoUsuario {

    @Autowired
    ProductoUsuarioRepositorio productoUsuarioRepositorio;

    @Override
    public ProductoUsuario encontrarProductoUsuarioPorId(long id) {
        return productoUsuarioRepositorio.getById(id);
    }

    @Override
    public void guardarProductoUsuario(ProductoUsuario productoUsuario) {
        productoUsuarioRepositorio.save(productoUsuario);
    }

    @Override
    public void eliminarProductoUsuario(ProductoUsuario productoUsuario) {
        productoUsuarioRepositorio.delete(productoUsuario);
    }


}
