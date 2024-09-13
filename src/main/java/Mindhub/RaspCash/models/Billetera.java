package Mindhub.RaspCash.models;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;

@Getter
@Entity
public class Billetera {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private String direccion;
    private double montoBTC;
    private double montoPesos;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "billetera", fetch = FetchType.EAGER)
    Set<Transaccion> transacciones=new HashSet<>();

    public Usuario getUsuario() {
        return usuario;
    }

    public Billetera() {
    }

    public Billetera(String direccion, double montoBTC, double montoPesos) {
        this.direccion=direccion;
        this.montoBTC=montoBTC;
        this.montoPesos=montoPesos;
    }

    public Billetera(String direccion, double montoBTC, double montoPesos, Usuario usuario) {
        this.direccion=direccion;
        this.montoBTC=montoBTC;
        this.montoPesos=montoPesos;
        this.usuario=usuario;
        usuario.setBilletera(this);
    }


    public void agregarTransaccion(Transaccion transaccion){
        //Agregar toda la logica de los montos, de si es credito o debito, y de que tipo de Cripto es la transaccion
        // Si la transaccion es de CREDITO, corroboro de que tipo de moneda es la transaccion y sumo el monto
        TipoDeTransaccion tipoDeTransaccion=transaccion.getTipo();
        TipoDeMoneda tipoDeMoneda=transaccion.getTipoDeMoneda();
        if (tipoDeTransaccion.equals(TipoDeTransaccion.CREDITO)){
            if (tipoDeMoneda.equals(TipoDeMoneda.PESOS)){
                this.montoPesos+=transaccion.getAmount();
            }
            if (tipoDeMoneda.equals(TipoDeMoneda.BITCOIN)){
                this.montoBTC+=transaccion.getAmount();
            }
        }

        // Si la transaccion es de DEBITO, corroboro de que tipo es la transaccion, luego chequeo si el monto es suficiente

        if (tipoDeTransaccion.equals(TipoDeTransaccion.DEBITO)){
            if (tipoDeMoneda.equals(TipoDeMoneda.PESOS)){
                if (transaccion.getAmount()<=this.montoPesos){
                    this.montoPesos=this.montoPesos-transaccion.getAmount();
                }
                else
                {
                    System.out.println("MONTO INSUFICIENTE");
                }
            }
            if (tipoDeMoneda.equals(TipoDeMoneda.BITCOIN)){
                if(transaccion.getAmount()<=this.montoBTC){
                    this.montoBTC=this.montoBTC- transaccion.getAmount();
                }
            }
        }

        this.transacciones.add(transaccion);
    }

    public long getId() {
        return id;
    }

    public String getDireccion() {
        return direccion;
    }


    public Set<Transaccion> getTransacciones() {
        return transacciones;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public double getMontoBTC() {
        return montoBTC;
    }

    public double getMontoPesos() {
        return montoPesos;
    }
}
