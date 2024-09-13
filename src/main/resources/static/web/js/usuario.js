var app = new Vue({
    el: '#appUsuario',
    data: {
        correo: "",        
        nombre: "",
        apellido: "",
        apodo: "",
        inicioSesion: true,
        registro: false,
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
                    this.correo=response.data.email;
                    this.nombre=response.data.nombre;
                    this.apellido=response.data.apellido;
                    this.apodo=response.data.apodo;
                    this.productosAdquiridos=response.data.productosAdquiridos;
                    
                })
                .catch(error => {
                    console.log("Inicie sesión!")

                }).finally(function () {
                    const preload = document.querySelector(".preload");
                    preload.style.visibility = "hidden";
                });
        },
    }
});