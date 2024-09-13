package Mindhub.RaspCash.dtos;

import Mindhub.RaspCash.models.Billetera;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class BilleteraDTO {

    private long id;
    private String direccion;
    //private UsuarioDTO usuarioDTO;
    private double montoPesos;
    private double montoBTC;
    private Set<TransaccionDTO> transacciones=new HashSet<>();

    public BilleteraDTO(Billetera billetera) {
        this.id= billetera.getId();
        this.direccion= billetera.getDireccion();
       // this.usuarioDTO=new UsuarioDTO(billetera.getUsuario());
        this.montoPesos=billetera.getMontoPesos();
        this.montoBTC= billetera.getMontoBTC();
        this.transacciones=billetera.getTransacciones().stream().map(TransaccionDTO::new).collect(Collectors.toSet());
    }

    public long getId() {
        return id;
    }

    public String getDireccion() {
        return direccion;
    }
    /*@JsonIgnore
    public UsuarioDTO getUsuarioDTO() {
        return usuarioDTO;
    }*/

    public double getMontoPesos() {
        return montoPesos;
    }

    public double getMontoBTC() {
        return montoBTC;
    }

    public Set<TransaccionDTO> getTransacciones() {
        return transacciones;
    }
}
