package Mindhub.RaspCash.controllers;

import Mindhub.RaspCash.dtos.CarritoDTO;
import Mindhub.RaspCash.models.*;
import Mindhub.RaspCash.respositories.CarritoRepositorio;
import Mindhub.RaspCash.respositories.UsuarioRepositorio;
import Mindhub.RaspCash.servicios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class CarritoController {

    @Autowired
    ServicioCarrito servicioCarrito;

    @Autowired
    ServicioUsuario servicioUsuario;

    @Autowired
    ServicioProducto servicioProducto;

    @Autowired
    ServicioProductoUsuario servicioProductoUsuario;

    @Autowired
    ServicioTransaccion servicioTransaccion;

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    @Autowired
    CarritoRepositorio carritoRepositorio;

    @GetMapping("/carritos")
    public List<CarritoDTO> obtenerCarritos() {
        return servicioCarrito.obtenerTodosLosCarritos();
    }

    @GetMapping("/carrito/{id}")
    public Carrito obtenerCarritoPorId(@PathVariable long id) {
        return servicioCarrito.obtenerCarritoPorId(id);
    }

    @GetMapping("/carrito/current")
    public CarritoDTO obtenerCurrentCarrito(Authentication authentication) {
        return servicioCarrito.obtenerCurrentCarrito(authentication);
    }

    @Transactional
    @PostMapping("/carrito/producto")
    public ResponseEntity<Object> agregarProductoAlcarrito(Authentication authentication,
            @RequestParam long idProducto) {

        if (authentication == null) {
            return new ResponseEntity<>("No hay un usuario logueado", HttpStatus.FORBIDDEN);
        }

        Usuario usuario = usuarioRepositorio.findByEmail(authentication.getName());

        Carrito carrito;

        if (Objects.nonNull(usuario.getCarrito())){
            carrito = usuario.getCarrito();
        } else {
            carrito = carritoRepositorio.save(new Carrito());
            usuario.setCarrito(carrito);
            carrito.setUsuario(usuario);
        }

        Producto producto = servicioProducto.obtenerProductoPorId(idProducto);

        if (Objects.isNull(producto)) {
            return new ResponseEntity<>("No es posible encontrar el producto seleccionado", HttpStatus.FORBIDDEN);
        }

        if (producto.getStock() == 0) {
            return new ResponseEntity<>("No hay stock disponible para el producto que intenta añadir al carrito",
                    HttpStatus.FORBIDDEN);
        }

        ProductoUsuario productoUsuarioAlCarrito = new ProductoUsuario(producto, carrito, EstadoProducto.EN_CARRITO);

        servicioProductoUsuario.guardarProductoUsuario(productoUsuarioAlCarrito);

        producto.disminuirStock(1);

        carrito.agregarProductoAlCarrito(productoUsuarioAlCarrito);

        servicioCarrito.guardarCarrito(carrito);

        return new ResponseEntity<>("Se agregó el producto al carrito", HttpStatus.CREATED);
    }

    @Transactional
    @PostMapping("/carrito/realizarCompra")
    public ResponseEntity<Object> realizarCompra(Authentication authentication) {

        Usuario usuario = servicioUsuario.encontrarUsuarioPorEmail(authentication.getName());
        Billetera billetera = usuario.getBilletera();
        //Carrito carrito = servicioCarrito.obtenerCarritoPorId(idCarrito);
        Carrito carrito = usuario.getCarrito();

        if (billetera.getMontoPesos() < carrito.getTotal()) {
            return new ResponseEntity<>("No cuenta con fondos en pesos requeridos para esta compra",
                    HttpStatus.FORBIDDEN);
        }

        if (carrito.getProductosEnCarrito().size() == 0) {
            return new ResponseEntity<>("El carrito se encuentra vacío", HttpStatus.FORBIDDEN);
        }

        if (carrito.getTotal() == 0) {
            return new ResponseEntity<>("El carrito se encuentra vacío", HttpStatus.FORBIDDEN);
        }

        String descripcionCompra = "Compra Carrito Id=" + carrito.getId() + " ,Por el monto de $" + carrito.getTotal();

        // Asigno todos los productos del carrito al usuario
        carrito.getProductosEnCarrito().forEach(productoUsuario -> {
            usuario.agregarProductoComprado(productoUsuario);
            productoUsuario.setUsuarioDuenio(usuario);
            productoUsuario.setEstadoProducto(EstadoProducto.VENDIDO);
            productoUsuario.setCarrito(carrito);
        });

        // Genero la transacción para cobrarle al usuario y le asigno la transaccion a
        // la billetera
        Transaccion transaccion = new Transaccion(TipoDeTransaccion.DEBITO, carrito.getTotal(), descripcionCompra,
                LocalDateTime.now(), TipoDeMoneda.PESOS, billetera);
        billetera.agregarTransaccion(transaccion);

        servicioTransaccion.guardarTransaccion(transaccion);

       // carrito.vaciarCarrito();
       // servicioCarrito.guardarCarrito(carrito);
        Carrito carrito1=new Carrito();
        usuario.setCarrito(carrito1);

        //servicioCarrito.borrarCarrito(carrito);

        servicioCarrito.guardarCarrito(carrito1);


        return new ResponseEntity<>("La compra fue realizada con éxito", HttpStatus.CREATED);
    }

    @PostMapping("/carrito/vaciarCarrito")
    public ResponseEntity<Object> vaciarCarrito(long idCarrito) {
        Carrito carrito = servicioCarrito.obtenerCarritoPorId(idCarrito);
        carrito.vaciarCarrito();
        return new ResponseEntity<>("El carrito se encuentra vacío nuevamente", HttpStatus.CREATED);
    }

    @PostMapping("/carrito/sacar_producto")
    public ResponseEntity<Object> sacarProductoDelCarrito(Authentication authentication, @RequestParam long idProducto){

        Usuario usuario = servicioUsuario.encontrarUsuarioPorEmail(authentication.getName());
        Carrito carrito = usuario.getCarrito();

        ProductoUsuario productoUsuarioSacarCarrito = servicioProductoUsuario.encontrarProductoUsuarioPorId(idProducto);
     //   productoUsuarioSacarCarrito.setEstadoProducto(EstadoProducto.DISPONIBLE);
        Producto producto=servicioProducto.obtenerProductoPorId(productoUsuarioSacarCarrito.getProducto().getId());
        producto.agregarAlStock(1);

        carrito.getProductosEnCarrito().remove(productoUsuarioSacarCarrito);
        carrito.disminuirTotal(productoUsuarioSacarCarrito.getPrecio());

        servicioProductoUsuario.eliminarProductoUsuario(productoUsuarioSacarCarrito);
        productoUsuarioSacarCarrito=null;
        System.gc();

        servicioCarrito.guardarCarrito(carrito);
        return new ResponseEntity<>("El elemento se ha borrado del carrito", HttpStatus.CREATED);
    }
}
