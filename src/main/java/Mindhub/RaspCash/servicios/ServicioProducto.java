package Mindhub.RaspCash.servicios;

import Mindhub.RaspCash.dtos.ProductoDTO;
import Mindhub.RaspCash.models.Producto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ServicioProducto {

    public List<ProductoDTO> obtenerTodosLosProductosDTO();
    public List<ProductoDTO> obtenerMerchandisingDTO();
    public List<ProductoDTO> obtenerNFTsDTO();
    public Producto obtenerProductoPorId(long id);

}
