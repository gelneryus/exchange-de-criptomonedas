package Mindhub.RaspCash.controllers;

import Mindhub.RaspCash.models.*;
import Mindhub.RaspCash.servicios.ServicioBilletera;
import Mindhub.RaspCash.servicios.ServicioTransaccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
public class TransaccionController {

    @Autowired
    ServicioTransaccion servicioTransaccion;

    @Autowired
    ServicioBilletera servicioBilletera;

    @PostMapping("/transaccion")
    public ResponseEntity<Object> realizarTransaccion(@RequestParam String tipoDeMoneda, @RequestParam String monto, @RequestParam String direccionBilleteraEmisora,@RequestParam String direccionBilleteraReceptora,@RequestParam String descripcion){
        //Las transacciones son de billetera a billetera

        TipoDeMoneda tipoDeMonedatransaccion=TipoDeMoneda.valueOf(tipoDeMoneda);
        double montoTransaccion = Double.parseDouble(monto);

        if(montoTransaccion<=0){
            return new ResponseEntity<>("El monto de la transferencia no puede ser 0 o negativo", HttpStatus.FORBIDDEN);
        }

        if (direccionBilleteraEmisora.equals("")){
            return new ResponseEntity<>("La Direccion de billetera emisora del pago se encuentra vacía", HttpStatus.FORBIDDEN);
        }

        if(direccionBilleteraReceptora.equals("")){
            return new ResponseEntity<>("La Direccion de billetera receptora del pago se encuentra vacía", HttpStatus.FORBIDDEN);
        }

        //Si pasa las condiciones iniciales para la transferencia, rescatamos las billeteras de la Base de datos.
        Billetera billeteraEmisora = servicioBilletera.encontrarPorDireccion(direccionBilleteraEmisora);
        Billetera billeteraReceptora= servicioBilletera.encontrarPorDireccion(direccionBilleteraReceptora);

        if (billeteraEmisora==null){
            return new ResponseEntity<>("No se encuentra la billetera emisora", HttpStatus.FORBIDDEN);
        }

        if (billeteraReceptora==null){
            return new ResponseEntity<>("No se encuentra la billetera receptora", HttpStatus.FORBIDDEN);
        }


        //Chequeo si el monto en pesos o el monto en BTC de la cuenta emisora es suficiente para realizar la transferencia
        if (tipoDeMonedatransaccion.equals(TipoDeMoneda.PESOS)){
            if (billeteraEmisora.getMontoPesos()<0){
                return new ResponseEntity<>("El monto en pesos de la cuenta emisora es insuficiente para realizar la transacciòn", HttpStatus.FORBIDDEN);
            }
        }
        if (tipoDeMonedatransaccion.equals(TipoDeMoneda.BITCOIN)){
            if (billeteraEmisora.getMontoBTC()<0){
                return new ResponseEntity<>("El monto en pesos de la cuenta emisora es insuficiente para realizar la transacciòn", HttpStatus.FORBIDDEN);
            }
        }

        Transaccion transaccionDebito=new Transaccion(TipoDeTransaccion.DEBITO,montoTransaccion,descripcion, LocalDateTime.now(),tipoDeMonedatransaccion,billeteraEmisora);
        Transaccion transaccionCredito=new Transaccion(TipoDeTransaccion.CREDITO,montoTransaccion,descripcion,LocalDateTime.now(),tipoDeMonedatransaccion,billeteraReceptora);

        billeteraEmisora.agregarTransaccion(transaccionDebito);
        billeteraReceptora.agregarTransaccion(transaccionCredito);

        servicioTransaccion.guardarTransaccion(transaccionDebito);
        servicioTransaccion.guardarTransaccion(transaccionCredito);

        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @Transactional
    @PostMapping("/transaccion/swap")
    public ResponseEntity<Object> realizarSwap(@RequestParam String direccionBilletera,
                                               @RequestParam String montoEnPesos,
                                               @RequestParam String montoEnBTC,
                                               @RequestParam String tipoDeSwap){

        System.out.println(tipoDeSwap);
        System.out.println(montoEnBTC);
        System.out.println(montoEnPesos);
        System.out.println(direccionBilletera);



        if(tipoDeSwap.equals("")){
            return new ResponseEntity<>("Por favor seleccione un tipo de SWAP para realizar la operacion", HttpStatus.FORBIDDEN);
        }

        if (direccionBilletera.equals("")){
            return new ResponseEntity<>("La Direccion de billetera para realizar el swap se encuentra vacía", HttpStatus.FORBIDDEN);
        }

        if(montoEnPesos.equals("")||montoEnBTC.equals("")){
            return new ResponseEntity<>("Faltan algún monto para poder realizar la operación", HttpStatus.FORBIDDEN);
        }

        TipoDeSwap tipoDeSwapTransaccion = TipoDeSwap.valueOf(tipoDeSwap);
        double montoEnBTCTransaccion = Double.parseDouble(montoEnBTC);
        double montoEnPesosTransaccion = Double.parseDouble(montoEnPesos);
        //double cotizacionTransaccion = Double.parseDouble(cotizacion);

        if (montoEnBTCTransaccion==0||montoEnPesosTransaccion==0){
            return new ResponseEntity<>("No puede Swapear con monto 0, por favor seleccione un monto válido", HttpStatus.FORBIDDEN);
        }

        Billetera billeteraSwap = servicioBilletera.encontrarPorDireccion(direccionBilletera);

        if(billeteraSwap==null) {
            return new ResponseEntity<>("No es posible encontrar la billetera para realizar el Swap", HttpStatus.FORBIDDEN);
        }

        //Puede optar por dos caminos dependiendo el tipo de SWAP, de Bitcoin a Pesos, o de Pesos a Bitcoin
        if(tipoDeSwapTransaccion.equals(TipoDeSwap.BTC_A_PESOS)){
            if (billeteraSwap.getMontoBTC()<montoEnBTCTransaccion){
                return new ResponseEntity<>("El monto en BTC de la billetera es insuficiente para realizar la transaccion", HttpStatus.FORBIDDEN);
            }
            Transaccion transaccionDebito = new Transaccion(TipoDeTransaccion.DEBITO,montoEnBTCTransaccion,"Swap`Bitcoin a pesos",LocalDateTime.now(),TipoDeMoneda.BITCOIN,billeteraSwap);
            Transaccion transaccionCredito = new Transaccion(TipoDeTransaccion.CREDITO,montoEnPesosTransaccion,"Swap`Bitcoin a pesos",LocalDateTime.now(),TipoDeMoneda.PESOS,billeteraSwap);

            billeteraSwap.agregarTransaccion(transaccionDebito);
            billeteraSwap.agregarTransaccion(transaccionCredito);

            servicioTransaccion.guardarTransaccion(transaccionDebito);
            servicioTransaccion.guardarTransaccion(transaccionCredito);

        }

        if (tipoDeSwapTransaccion.equals(TipoDeSwap.PESOS_A_BTC)){
            if (billeteraSwap.getMontoBTC()<montoEnBTCTransaccion){
                return new ResponseEntity<>("El monto en Pesos de la billetera es insuficiente para realizar la transaccion", HttpStatus.FORBIDDEN);
            }
            Transaccion transaccionDebito = new Transaccion(TipoDeTransaccion.DEBITO,montoEnPesosTransaccion,"Swap Pesos a Bitcoin",LocalDateTime.now(),TipoDeMoneda.PESOS,billeteraSwap);
            Transaccion transaccionCredito = new Transaccion(TipoDeTransaccion.CREDITO,montoEnBTCTransaccion,"Swap pesos a Bitcoin",LocalDateTime.now(),TipoDeMoneda.BITCOIN,billeteraSwap);

            billeteraSwap.agregarTransaccion(transaccionDebito);
            billeteraSwap.agregarTransaccion(transaccionCredito);

            servicioTransaccion.guardarTransaccion(transaccionDebito);
            servicioTransaccion.guardarTransaccion(transaccionCredito);

        }


        return new ResponseEntity<>("Felicitaciones, el SWAP fue realizado exitosamente",HttpStatus.CREATED);
    }

}
