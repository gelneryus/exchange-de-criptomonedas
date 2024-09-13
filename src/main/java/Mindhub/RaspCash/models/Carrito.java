package Mindhub.RaspCash.models;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;

import javax.persistence.*;
import java.util.*;

@Getter
@Entity
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @OneToMany(mappedBy = "carrito_id", fetch = FetchType.EAGER)
    private Set<ProductoUsuario> productosEnCarrito;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private double total;

    public Carrito() {
        total=0;
    }

    public void agregarProductoAlCarrito(ProductoUsuario productoUsuario){
        this.productosEnCarrito.add(productoUsuario);
        this.total+=productoUsuario.getPrecio();
    }

    public long getId() {
        return id;
    }

    public Set<ProductoUsuario> getProductosEnCarrito() {
        return productosEnCarrito;
    }

    public double getTotal() {
        return total;
    }

    public Usuario getUsuario(){
        return usuario;
    }

    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }

    public void vaciarCarrito(){
        this.total=0;
        this.productosEnCarrito=new HashSet<>();
    }
    //public void setUsuario(Usuario usuario){        this.usuario=usuario;    }

    public void disminuirTotal(double valor){
        this.total-=valor;
    }
}
