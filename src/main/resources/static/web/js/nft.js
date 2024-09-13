var app = new Vue({
    el: '#appNFTs',
    data: {
        nfts: [],
        obrasDestacadas: []
    },
    created() {
        this.cargarDatos();
    },

    methods: {
        cargarDatos() {
            axios.get('/api/productos/nfts')
                .then(response => {
                    console.log(response.data);
                    this.nfts = response.data;
                    this.obrasDestacadas = this.nfts.slice(10, 15);
                    console.log(this.obrasDestacadas);
                })
                .finally(function () {
                    const preload = document.querySelector(".preload");
                    preload.style.visibility = "hidden";
                });
        },
        agregarProductoAlCarrito(idProducto) {
            axios.post('/api/carrito/producto', `idProducto=${idProducto}`).then(response => {
                app.cargarDatos();
                Swal.fire({
                    text: response.data,
                    icon: 'success',
                    confirmButtonText: 'Ok',
                });
            }).catch(error => {
                Swal.fire({
                    text: error.response.data,
                    icon: 'success',
                    confirmButtonText: 'Ok',
                });
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