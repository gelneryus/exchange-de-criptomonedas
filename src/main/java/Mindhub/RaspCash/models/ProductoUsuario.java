package Mindhub.RaspCash.models;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class ProductoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private EstadoProducto estadoProducto;

    @ManyToOne
    @JoinColumn(name="producto_id")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "usuario_dueño")
    private Usuario usuarioDuenio;

    @ManyToOne
    @JoinColumn(name="carrito_alojado")
    private Carrito carrito_id;

    public ProductoUsuario() {
    }

    public ProductoUsuario(Producto producto,Carrito carrito,EstadoProducto estadoProducto) {
        this.producto=producto;
        this.carrito_id=carrito;
        this.estadoProducto=estadoProducto;
    }

    //Inicialmente se encuentra Disponible, cambia el estado cuando se selecciona para estar en el carrito, y luego cuando lo compra el usuario, o si sale del carrito vuelve a estar disponible
    private void cambiarEstadoDeProducto(EstadoProducto nuevoEstadoProducto){
        this.estadoProducto=nuevoEstadoProducto;
    }

    public Producto getProducto() {
        return producto;
    }

    public String getNombre(){
        return this.producto.getNombre();
    }
    public EstadoProducto getEstadoProducto(){
        return this.estadoProducto;
    }

    public TipoProducto getTipoProducto(){
        return this.producto.getTipo();
    }

    public String getImagen(){
        return this.producto.getImagen();
    }

    public String getDescripcion(){
        return this.producto.getDescripcion();
    }

    public double getPrecio(){
        return this.producto.getValor();
    }

    public long getId(){
        return this.id;
    }

    public int getStock(){
        return this.producto.getStock();
    }

    public void setUsuarioDuenio(Usuario usuarioDuenio) {
        this.usuarioDuenio = usuarioDuenio;
    }

    public void setEstadoProducto(EstadoProducto estadoProducto) {
        this.estadoProducto = estadoProducto;
    }

    public void setCarrito(Carrito carrito){
        this.carrito_id=carrito;
    }
}
