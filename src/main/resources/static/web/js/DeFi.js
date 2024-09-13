let app = new Vue({
    el: "#appDefi",
    data: {
        billetera: "",
        btc: "0",
        pesos: "0",
        type: "BTC_A_PESOS",
        montoEnPesos: "",
        montoEnBTC: "",
        cotizacionBTCPesos: "4000000",
        prestamos:[],
        prestamoElegido:"",
        cuotasASolicitar:0
    },
    created() {
        this.cargarUsuario();
        this.cargarPrestamos();
    },
    methods: {
        cargarUsuario() {
            axios.get('/api/usuarios/current')
                .then(response => {
                    console.log(response.data)
                    this.billetera = response.data.billetera;
                    this.montoEnBTC = this.billetera.montoBTC;
                    this.montoEnPesos = this.billetera.montoPesos;
                })
                .catch(
                    error => {
                        Swal.fire("Inicie sesión");
                        window.location.href = "/index.html"
                    }
                );          
        },
        realizarSwapPesosABTC(swapBTC) {

            axios.post('/api/transaccion/swap', `direccionBilletera=${this.billetera.direccion}&montoEnPesos=${this.pesos}&montoEnBTC=${swapBTC}&tipoDeSwap=${this.type}`)
                .then(response => Swal.fire(response.data))
                .catch(error => Swal.fire(error.response.data));
        },
        realizarSwapBTCAPesos(swapPesos) {

            axios.post('/api/transaccion/swap', `direccionBilletera=${this.billetera.direccion}&montoEnPesos=${swapPesos}&montoEnBTC=${this.btc}&tipoDeSwap=${this.type}`)
                .then(response => Swal.fire(response.data))
                .catch(error => Swal.fire(error.response.data));
        },
        solicitarPrestamo() {
            let prestamoASolicitar=this.prestamos.filter(prestamo =>prestamo.nombre==this.prestamoElegido);
            
            Swal.fire({
                title: 'Estas seguro/a?',
                text: "No se podrá volver atrás!",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3EBD02',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Si, realizar transacción!'
            }).then((result) => {
                if (result.isConfirmed) {           
                                
                    axios.post('/api/prestamos', {monto:`${prestamoASolicitar[0].monto}`,cuotas:`${this.cuotasASolicitar}`,billeteraDestino:`${this.billetera.direccion}`,nombrePrestamo:`${this.prestamoElegido}` } )
                        .then(response => {
                            Swal.fire({
                                icon: 'success',
                                text: 'Prestamo solicitado exitosamente!',
                                showConfirmButton: true
                            });
                            this.reiniciarValores();
                            this.cargarUsuario();
                            this.cargarPrestamos();

                            
                        })
                        .catch(error => {
                            Swal.fire({
                                icon: 'error',
                                text: error.response.data,
                                showConfirmButton: true
                            });
                            this.reiniciarValores();

                            
                        })

                }
            })

        },
        cargarPrestamos(){
            axios.get('/api/prestamos')
            .then(response=>{
                this.prestamos=response.data;
                console.log(this.prestamos);
                console.log(response.data);
            })
            .catch(
                console.log("error")
                )
        },
        reiniciarValores(){
            this.btc= "0";
            this.pesos= "0";
            this.montoEnPesos= "";
            this.montoEnBTC= "";
            this.prestamoElegido="";
            this.cuotasASolicitar=0;
        },
        currency:function(number){
            return new Intl.NumberFormat().format(number);
        }

        
    },
    computed: {
        obtenerCuotas: function () {
            console.log(this.prestamoElegido);
            let prestamo = "";
            if (this.prestamoElegido != "") {
                prestamo = this.prestamos.filter(prestamo => prestamo.nombre == this.prestamoElegido);
                return prestamo[0].cuotas; 
            }  
        },
        obtenerMontoPrestamo: function(){
            if (this.prestamoElegido != "") {
                prestamo = this.prestamos.filter(prestamo => prestamo.nombre == this.prestamoElegido);
                return prestamo[0].monto; 
            }  
        },
        obtenerInteresPrestamo:function () {
            if (this.prestamoElegido != "") {
                prestamo = this.prestamos.filter(prestamo => prestamo.nombre == this.prestamoElegido);
                return prestamo[0].interes*100; 
            }  
        },
        obtenerGarantiaPrestamo:function(){
            if (this.prestamoElegido != "") {
                prestamo = this.prestamos.filter(prestamo => prestamo.nombre == this.prestamoElegido);
                return prestamo[0].garantia; 
            }  
        }

    }

})