var app = new Vue({
    el: '#appLogin',
    data: {
        correo: "",
        password: "",
        nombre: "",
        apellido: "",
        apodo: "",
        inicioSesion: true,
        registro: false
    },
    created() {
       // this.cargarDatos();
    },

    methods: {
        cargarDatos() {
            axios.get('/api/usuarios/current')
              //  .then(response => window.location.href = "/web/nft.html")
                .catch(error => {
                    console.log("Inicie sesión!")

                }).finally(function () {
                    const preload = document.querySelector(".preload");
                    preload.style.visibility = "hidden";
                });
        },
        iniciarSesion() {
            axios.post('/api/login', "email=" + this.correo + "&password=" + this.password, {
                headers: {
                    'content-type': 'application/x-www-form-urlencoded'
                }
            })
                .then(response => {
                    window.location.href = "/web/nft.html";
                }).catch(error => {
                    Swal.fire({
                        text: 'Usuario o contraseña incorrectos!',
                        icon: 'danger',
                        confirmButtonText: 'Ok',
                    })
                })
        },
        registrarse() {
            axios.post('/api/usuarios', "correo=" + this.correo + "&password=" + this.password + "&nombre=" + this.nombre + "&apellido=" + this.apellido + "&apodo=" + this.apodo, {
                headers: {
                    'content-type': 'application/x-www-form-urlencoded'
                }
            }).then(response => {
                Swal.fire(response.data); 
                this.iniciarSesion();   
            }).catch(error => {
                 Swal.fire(error.response.data);
                });
        },
        cerrarSesion(){
                axios.post(`/api/logout`)
                    .then(response => window.alert('GRACIAS POR LA VISITA!! :D'))
                return (window.location.href = "/index.html")  
//            axios.post('/api/logout')
            /* .then(response => {
                Swal.fire(response.data); 
                console.log('signed out!!!');
                window.location.href = "./index.html";                
            }).catch(error => {
                 Swal.fire(error.response.data);
                }); */
        }
    }

})