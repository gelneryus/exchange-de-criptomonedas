package Mindhub.RaspCash.dtos;

import Mindhub.RaspCash.models.Carrito;
import Mindhub.RaspCash.models.Usuario;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class UsuarioDTO {
    private long id;
    private String nombre;
    private String apellido;
    private String apodo;
    private String email;
    private BilleteraDTO billetera;
    private List<PrestamoUsuarioDTO> prestamos=new ArrayList<>();
    private Set<ProductoUsuarioDTO> productosAdquiridos=new HashSet<>();
    private CarritoDTO carrito;


    public UsuarioDTO(Usuario usuario) {
        this.id=usuario.getId();
        this.nombre = usuario.getNombre();
        this.apellido= usuario.getApellido();
        this.apodo= usuario.getApodo();
        this.email= usuario.getEmail();
        this.billetera=new BilleteraDTO(usuario.getBilletera());
        this.prestamos=usuario.getPrestamos().stream().map(PrestamoUsuarioDTO::new).collect(Collectors.toList());
        this.productosAdquiridos=usuario.getProductosComprados().stream().map(ProductoUsuarioDTO::new).collect(Collectors.toSet());
        this.carrito = new CarritoDTO(usuario.getCarrito());
    }

    public long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getApodo() {
        return apodo;
    }

    public String getEmail() {
        return email;
    }

    public BilleteraDTO getBilletera() {
        return billetera;
    }

    public List<PrestamoUsuarioDTO> getPrestamos() {
        return prestamos;
    }

    public Set<ProductoUsuarioDTO> getProductosAdquiridos() {
        return productosAdquiridos;
    }

    public CarritoDTO getCarrito() {return carrito;}
}
