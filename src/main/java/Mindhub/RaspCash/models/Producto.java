package Mindhub.RaspCash.models;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

import javax.persistence.*;

@Entity
public @Data class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private int stock;
    private String imagen;
    private double valor;
    private String descripcion;
    private String nombre;
    private  TipoProducto tipo;

  /*  @OneToMany(mappedBy = "producto", fetch = FetchType.EAGER)
    private ProductoUsuario productoUsuario;

    @OneToMany(mappedBy = "producto",fetch = FetchType.EAGER)
    private List<Opinion> opiniones = new ArrayList<>();
*/
    public Producto() {
    }

    public Producto(String nombre, int stock,String imagen,double valor,String descripcion,TipoProducto tipo){
        this.nombre=nombre;
        this.stock=stock;
        this.imagen=imagen;
        this.valor=valor;
        this.descripcion=descripcion;
        this.tipo=tipo;
    }

    public void reducirStock(int cantidad){
        //Reduzco la cantidad del stock del producto
        if (stock>cantidad){
            this.stock -=cantidad;
        }

    }
    public void agregarAlStock(int cantidad){
        //Aumento la cantidad del stock del producto
        this.stock+=cantidad;
    }

    public void disminuirStock(int cantidad){
        this.stock-=cantidad;
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

    /*public ProductoUsuario getProductoUsuario() {
        return productoUsuario;
    }

    public List<Opinion> getOpiniones() {
        return opiniones;
    }*/
}
