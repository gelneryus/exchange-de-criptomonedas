package Mindhub.RaspCash.controllers;

import Mindhub.RaspCash.dtos.ProductoDTO;
import Mindhub.RaspCash.servicios.ServicioProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductosController {

    @Autowired
    ServicioProducto servicioProducto;

    @GetMapping("/productos")
    public List<ProductoDTO> obtenerTodosLosProductosDTO(){
        return servicioProducto.obtenerTodosLosProductosDTO();
    }

    @GetMapping("/productos/nfts")
    public List<ProductoDTO> obtenerTodosLosNFTs(){
        return servicioProducto.obtenerNFTsDTO();
    }

    @GetMapping("/productos/merchandising")
    public List<ProductoDTO> obtenerTodosLosProductosMerchandising(){
        return servicioProducto.obtenerMerchandisingDTO();
    }
}
