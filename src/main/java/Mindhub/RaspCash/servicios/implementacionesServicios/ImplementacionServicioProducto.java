package Mindhub.RaspCash.servicios.implementacionesServicios;

import Mindhub.RaspCash.dtos.ProductoDTO;
import Mindhub.RaspCash.models.Producto;
import Mindhub.RaspCash.models.TipoProducto;
import Mindhub.RaspCash.respositories.ProductoRepositorio;
import Mindhub.RaspCash.servicios.ServicioProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImplementacionServicioProducto implements ServicioProducto {

    @Autowired
    ProductoRepositorio productoRepositorio;

    @Override
    public List<ProductoDTO> obtenerTodosLosProductosDTO() {
        return productoRepositorio.findAll().stream().map(ProductoDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<ProductoDTO> obtenerMerchandisingDTO() {
        List<Producto> productos=productoRepositorio.findAll();
        List<Producto> ProductoMerchandising=productos.stream().filter(producto -> producto.getTipo().equals(TipoProducto.MERCHANDISING)).collect(Collectors.toList());
        return ProductoMerchandising.stream().map(ProductoDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<ProductoDTO> obtenerNFTsDTO() {
        List<Producto> productos=productoRepositorio.findAll();
        List<Producto> nfts=productos.stream().filter(producto -> producto.getTipo().equals(TipoProducto.NFT)).collect(Collectors.toList());
        return nfts.stream().map(ProductoDTO::new).collect(Collectors.toList());
    }

    @Override
    public Producto obtenerProductoPorId(long id) {
        return productoRepositorio.getById(id);
    }


}
