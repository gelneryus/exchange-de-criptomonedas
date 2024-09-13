package Mindhub.RaspCash.servicios.implementacionesServicios;

import Mindhub.RaspCash.models.Billetera;
import Mindhub.RaspCash.models.Carrito;
import Mindhub.RaspCash.respositories.BilleteraRepositorio;
import Mindhub.RaspCash.respositories.CarritoRepositorio;
import Mindhub.RaspCash.servicios.EmailSenderService;
import Mindhub.RaspCash.servicios.ServicioUsuario;
import Mindhub.RaspCash.utilidades.Utilidades;
import Mindhub.excepciones.ConflictException;
import Mindhub.RaspCash.dtos.UsuarioDTO;
import Mindhub.RaspCash.models.Usuario;
import Mindhub.RaspCash.respositories.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImplementacionServicioUsuario implements ServicioUsuario {

	@Autowired
	private PasswordEncoder passwordEncoder;


	Utilidades utilidades =new Utilidades();
	   
    @Autowired
    UsuarioRepositorio usuarioRepositorio;

	@Autowired
	BilleteraRepositorio billeteraRepositorio;

	@Autowired
	CarritoRepositorio carritoRepositorio;

	@Autowired
	EmailSenderService emailSenderService;

    @Override
    public List<UsuarioDTO> obtenerUsuarios() {
        return usuarioRepositorio.findAll().stream().map(UsuarioDTO::new).collect(Collectors.toList());
    }

	@Override
	public ResponseEntity<Object> registro(String nombre, String apellido, String apodo, String correo, String password) throws MessagingException {

		 if (correo.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || password.isEmpty() || apodo.isEmpty()) {
	            return new ResponseEntity<>("Complete los datos solicitados", HttpStatus.FORBIDDEN);
	        }
	        if (usuarioRepositorio.findByEmail(correo) != null) {
	            return new ResponseEntity<>("Correo ya registrado", HttpStatus.FORBIDDEN);
	        }

	        if (password.length() < 8){
	            return new ResponseEntity<>("La contraseña no puede tener menos de 8 caracteres", HttpStatus.FORBIDDEN);
	        }

	        if (!correo.contains("@") && !correo.contains(".")) {
	            return new ResponseEntity<>("Ingrese una dirección de correo valida", HttpStatus.FORBIDDEN);
	        }

	        for(int i = 0; i < nombre.length(); i++){
	            char c = nombre.charAt(i);
	            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == ' ')) {
	                return new ResponseEntity<>("El nombre no puede tener números, ni caracteres especiales", HttpStatus.FORBIDDEN);
	            }
	        }
	        for(int i = 0; i < apellido.length(); i++){
	            char c = apellido.charAt(i);
	            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == ' ')) {
	                return new ResponseEntity<>("El apellido no puede tener números, ni caracteres especiales", HttpStatus.FORBIDDEN);
	            }
	        }


			Usuario usuario=new Usuario(correo, passwordEncoder.encode(password), nombre, apellido, apodo);
			Carrito carrito= new Carrito();
		    carritoRepositorio.save(carrito);
			usuario.setCarrito(carrito);
			//usuario.setBilletera(billetera);
			//billetera.setUsuario(usuario);
			usuarioRepositorio.save(usuario);

			Billetera billetera=new Billetera(utilidades.obtenerDireccionBilletera(),0.2,20000);
			usuario.setBilletera(billetera);

			billeteraRepositorio.save(billetera);

			if(billetera == null || carrito == null)
				return new ResponseEntity<>("Error al implementar servicios al usuario", HttpStatus.FORBIDDEN);


			return new ResponseEntity<>("Registro de usuario realizado con éxito", HttpStatus.CREATED);
	}
    
    public UsuarioDTO obtenerUsuarioPorId(long id){
    	
    	Usuario usuario = usuarioRepositorio.buscarPorId(id);
    	
    	if(usuario == null)
    		throw new ConflictException("No se encontro el usuario.");
    	
    	return new UsuarioDTO(usuario);
    }

	@Override
	public UsuarioDTO findByEmail(String email) {
		return new UsuarioDTO(usuarioRepositorio.findByEmail(email));
	}

	@Override
	public Usuario encontrarUsuarioPorEmail(String email) {
		return usuarioRepositorio.findByEmail(email);
	}


}
