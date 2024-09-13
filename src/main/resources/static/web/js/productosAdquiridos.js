var app = new Vue({
    el: '#appProductosAdquiridos',
    data: {
        productosAdquiridos:[]
    },
    created() {
        this.cargarDatos();
    },

    methods: {
        cargarDatos() {
            axios.get('/api/usuarios/current')
                .then(response => {
                    console.log(response.data);
                    this.productosAdquiridos=response.data.productosAdquiridos;
                    
                })
                .catch(error => {
                    console.log("Inicie sesión!")

                }).finally(function () {
                    const preload = document.querySelector(".preload");
                    preload.style.visibility = "hidden";
                });
        },
        currency:function(number){
            return new Intl.NumberFormat().format(number);
        }

    }
});