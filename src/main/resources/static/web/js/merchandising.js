app = new Vue({
    el: '#appMerchandising',
    data: {
        productos: []
    },
    created() {
        this.cargarDatos();
    },

    methods: {
        cargarDatos() {
            axios.get('/api/productos/merchandising')
                .then(response => {
                    this.productos = response.data;
                    console.log(this.productos);
                })
                .finally(function() {
                    const preload = document.querySelector(".preload");
                    preload.style.visibility = "hidden";
                });
        },
        agregarProductoAlCarrito(idProducto) {
            axios.post('/api/carrito/producto', `idProducto=${idProducto}`)
            .then(response => {
                Swal.fire({
                    text: response.data,
                    icon: 'success',
                    confirmButtonText: 'Ok',
                });
                this.cargarDatos();
            }).catch(error => {
                Swal.fire({
                    text: error.response.data,
                    icon: 'danger',
                    confirmButtonText: 'Ok',
                })
                if (error.response.data == "No hay un usuario logueado") {
                    window.location.href = "login.html"
                }
            })
        },
        currency:function(number){
            return new Intl.NumberFormat().format(number);
        }

    }
})