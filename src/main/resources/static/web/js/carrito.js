var appCarrito = new Vue({
    el: '#appCarrito',
    data: {
        carrito: [],
        productosEnCarrito: [],
        total: 0
    },
    created() {
        this.cargarDatos();
    },

    methods: {
        cargarDatos() {
            axios.get('/api/carrito/current')
                .then(response => {

                    this.carrito = response.data;
                    this.productosEnCarrito = this.carrito.productos;
                    console.log(response.data);
                    this.total = this.carrito.montoTotal;

                })
                .catch(error => console.log(error.response.data))
                .finally(function() {
                    const preload = document.querySelector(".preload");
                    preload.style.visibility = "hidden";
                });
        },
        eliminarProductoDelCarrito(idProducto) {
            axios.post('/api/carrito/sacar_producto', "idProducto=" + idProducto).then(response => {
                Swal.fire({
                    text: 'Se ha borrado el producto con exito',
                    icon: 'success',
                    confirmButtonText: 'Ok'});
                this.cargarDatos();
            })},
            currency:function(number){
                return new Intl.NumberFormat().format(number);
            },
    
        
        realizarCompra() {
            axios.post('/api/carrito/realizarCompra').then(response => {
                this.cargarDatos();
                Swal.fire( response.data
            )}).catch(error => Swal.fire(error.response.data));
    }}})