var appBilletera = new Vue({
    el: "#appBilletera",
    data: {
        billetera: {},
        tipoDeMoneda: "",
        montoEnPesos: "",
        montoEnBTC: "",
        billeteraEmisora: "",
        billeteraReceptora: "",
        descripcion: ""
    },
    created() {
        this.obtenerBilletera()
    },
    methods: {
        obtenerBilletera() {
            axios.get('/api/usuarios/current')
                .then(response => {
                    console.log(response.data);
                    this.billetera = response.data.billetera;
                    console.log(this.billetera);
                })
        },
        transaccion() {
            axios.post('/api/usuarios/current', "tipoDeMoneda=" + this.tipoDeMoneda + "&monto=" + this.monto + "&direccionBilleteraEmisora=" + this.billeteraEmisora + "&direccionBilleteraReceptora" + this.billeteraReceptora + "&descripcion" + this.descripcion)
            Swal.fire({
                text: 'Se ha borrado el producto con exito',
                icon: 'success',
                confirmButtonText: 'Ok',
            }).then(response => {
                this.obtenerBilletera();
            })
        },
        currency:function(number){
            return new Intl.NumberFormat().format(number);
        }
        
        
    }
})