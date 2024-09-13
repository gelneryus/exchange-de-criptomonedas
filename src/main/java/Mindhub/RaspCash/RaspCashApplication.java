package Mindhub.RaspCash;

import Mindhub.RaspCash.models.*;
import Mindhub.RaspCash.respositories.*;
import Mindhub.RaspCash.utilidades.Utilidades;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class RaspCashApplication {

	@Autowired
	private PasswordEncoder passwordEncoder;


	public static void main(String[] args) {
		SpringApplication.run(RaspCashApplication.class, args);
	}


	@EventListener(ApplicationReadyEvent.class)
	public void triggerMail(){                //PARA QUE SEA MAS DINAMICO LO IDEAL SERIA HACER UNA VARIABLE PARA QUE SEA UN EMAIL DIFERENTE
												// O QUE ENGLOBE ESTE SERVICIO ENTONCES QUEDA CONECTADO
												//hablando del receptor del email

 		/*	senderService.senSimpleEmailTo("gelneryus20@gmail.com",
					"Para el registro, usted ha sido registrado con éxito y el mail del usuario",
					"RASPCASH");*/
	}

	@Bean
	public CommandLineRunner initData(UsuarioRepositorio usuarioRepositorio, BilleteraRepositorio billeteraRepositorio,
									  CarritoRepositorio carritoRepositorio, CriptoMonedaRepositorio criptoMonedaRepositorio,
									  PrestamoRespositorio prestamoRespositorio, PrestamoUsuarioRespositorio prestamoUsuarioRespositorio,
									  ProductoRepositorio productoRepositorio, TransaccionRepositorio transaccionRepositorio){

	return (args) -> {

			Utilidades utilidades=new Utilidades();

			String contrasenia1=passwordEncoder.encode("123456");
			String contraseniaAdmin=passwordEncoder.encode("admin");
			Usuario usuarioAdmin = new Usuario("admin",contraseniaAdmin,"admin","admin","admin");
			Usuario usuario1= new Usuario("gelneryus20@gmail.com",contrasenia1,"Joel","vallejos","J");

			usuarioRepositorio.save(usuario1);

			usuarioRepositorio.save(usuarioAdmin);


			Billetera billetera1=new Billetera(utilidades.obtenerDireccionBilletera(), 3.5,1000000);
			Billetera billetera2=new Billetera(utilidades.obtenerDireccionBilletera(), 0.5,1500000);
			Billetera billetera3=new Billetera(utilidades.obtenerDireccionBilletera(), 0,0);
			Billetera billetera4=new Billetera(utilidades.obtenerDireccionBilletera(), 3,2000000);

			usuario1.setBilletera(billetera1);

			usuarioAdmin.setBilletera(billetera3);

			billetera1.setUsuario(usuario1);

			billetera3.setUsuario(usuarioAdmin);


			billeteraRepositorio.save(billetera1);
			billeteraRepositorio.save(billetera2);
			billeteraRepositorio.save(billetera3);
			billeteraRepositorio.save(billetera4);

			System.out.println(billetera1.getUsuario().getNombre());
			System.out.println(billetera2.getUsuario().getNombre());
			System.out.println(billetera3.getUsuario().getNombre());

			Carrito carrito1= new Carrito();
			carritoRepositorio.save(carrito1);
		    usuario1.setCarrito(carrito1);

			Carrito carrito2= new Carrito();
			carritoRepositorio.save(carrito2);


			Carrito carrito3= new Carrito();
			carritoRepositorio.save(carrito3);

			Carrito carritoAdmin= new Carrito();
			carritoRepositorio.save(carritoAdmin);
			usuarioAdmin.setCarrito(carritoAdmin);

			usuarioRepositorio.save(usuario1);

			usuarioRepositorio.save(usuarioAdmin);


		//Creacion de algunas criptomonedas de prueba

			/*CriptoMoneda criptoBTC= new CriptoMoneda(NombreCriptomoneda.BITCOIN,SimboloCriptomoneda.BTC,4255459);
			CriptoMoneda criptoETH= new CriptoMoneda(NombreCriptomoneda.ETHEREUM,SimboloCriptomoneda.ETH,282029);
			CriptoMoneda criptoUSDT = new CriptoMoneda(NombreCriptomoneda.THETER,SimboloCriptomoneda.USDT,108);
			CriptoMoneda criptoBNB= new CriptoMoneda(NombreCriptomoneda.BNB,SimboloCriptomoneda.BNB,41046);
			CriptoMoneda criptoUSDC= new CriptoMoneda(NombreCriptomoneda.USD_COIN,SimboloCriptomoneda.USDC, 108);
			CriptoMoneda criptoXRP = new CriptoMoneda(NombreCriptomoneda.RIPPLE,SimboloCriptomoneda.XRP,86);
			CriptoMoneda criptoTerra=new CriptoMoneda(NombreCriptomoneda.LUNA,SimboloCriptomoneda.TERRA,9715);
			CriptoMoneda criptoADA= new CriptoMoneda(NombreCriptomoneda.CARDANO,SimboloCriptomoneda.ADA,86);
			CriptoMoneda criptoSOL = new CriptoMoneda(NombreCriptomoneda.SOLANA,SimboloCriptomoneda.SOL,8924);
			CriptoMoneda criptoAVAX = new CriptoMoneda(NombreCriptomoneda.AVALANCHE,SimboloCriptomoneda.AVAX,7830);
			criptoMonedaRepositorio.save(criptoBTC);
			criptoMonedaRepositorio.save(criptoETH);
			criptoMonedaRepositorio.save(criptoBNB);
			criptoMonedaRepositorio.save(criptoUSDT);
			criptoMonedaRepositorio.save(criptoUSDC);
			criptoMonedaRepositorio.save(criptoXRP);
			criptoMonedaRepositorio.save(criptoTerra);
			criptoMonedaRepositorio.save(criptoADA);
			criptoMonedaRepositorio.save(criptoSOL);
			criptoMonedaRepositorio.save(criptoAVAX);
			*/

		//Creacion de dos prestamos de prueba

		List<Integer> cuotasTradicional= new ArrayList<>();
		List<Integer> cuotasPersonal= new ArrayList<>();

		cuotasTradicional.add(6);
		cuotasTradicional.add(12);
		cuotasTradicional.add(18);

		cuotasPersonal.add(6);
		cuotasPersonal.add(12);

		Prestamo prestamoTradicional = new Prestamo("Tradicional Bitcoin",0.11,0.3,1,cuotasTradicional);
		Prestamo prestamoTrader=new Prestamo("Trader Bitcoin",0.22,0.6,1.5,cuotasPersonal);

		prestamoRespositorio.save(prestamoTradicional);
		prestamoRespositorio.save(prestamoTrader);

		/*
		PrestamoUsuario prestamoUsuario1=new ClientLoan(400000,60, client1,loanHipotecario);
		ClientLoan clientLoan2=new ClientLoan(50000,12,client1,loanAutomotriz);
		*/

		/*PrestamoUsuario prestamoUsuario1 = new PrestamoUsuario(prestamoTradicional.getMonto(),6,usuario1,prestamoTradicional);
		usuario1.agregarPrestamo(prestamoUsuario1);
		prestamoTradicional.agregarPrestamoUsuario(prestamoUsuario1);

		prestamoUsuarioRespositorio.save(prestamoUsuario1);*/
		/*
		https://i.ibb.co/S6Qd689/funda-notebook.jpg
https://i.ibb.co/NmrcyQ1/libreta2.jpg
https://i.ibb.co/RSv6DSn/mochila-2.jpg
https://i.ibb.co/HnPHnk7/otra-taza1.jpg
https://i.ibb.co/CsXSYK1/remera.jpg
https://i.ibb.co/WG4Gwwf/taza1.jpg
		*/
		    //public Producto(String nombre, int stock,String imagen,double valor,String descripcion,TipoProducto tipo){

			Producto productoFundaNotebook = new Producto("Funda Notebook",100,"https://i.ibb.co/S6Qd689/funda-notebook.jpg",5000,"Funda para notebook ideal para llevar tu notebook a todos lados, practica y comoda",TipoProducto.MERCHANDISING);
			Producto productoLibreta=new Producto("Libreta NFT",1500,"https://i.ibb.co/NmrcyQ1/libreta2.jpg",1500,"Libreta práctica y canchera, para anotar tus ideas mas frescas",TipoProducto.MERCHANDISING);
			Producto productoMochila=new Producto("Mochila NFT",5000,"https://i.ibb.co/RSv6DSn/mochila-2.jpg",10500,"Mochila NFT, diseño unico y exclusivo, amplia para ir con tus cosas a todos lados",TipoProducto.MERCHANDISING);
			Producto productoTaza2=new Producto("Taza NFT - 2",12000,"https://i.ibb.co/HnPHnk7/otra-taza1.jpg",2350,"Taza termica e irrompible con diseño unico y exclusivo",TipoProducto.MERCHANDISING);
			Producto productoRemera=new Producto("Remera NFT",1000,"https://i.ibb.co/CsXSYK1/remera.jpg",4999,"Destacá entre tus Criptoamigos con esta remera unica en su especie",TipoProducto.MERCHANDISING);
			Producto productoTaza1=new Producto("Taza NFT - 1",1200,"https://i.ibb.co/WG4Gwwf/taza1.jpg",2350,"Taza termica e irrompible con diseño unico y exclusivo", TipoProducto.MERCHANDISING);

			productoRepositorio.save(productoFundaNotebook);
			productoRepositorio.save(productoLibreta);
			productoRepositorio.save(productoMochila);
			productoRepositorio.save(productoTaza2);
			productoRepositorio.save(productoRemera);
			productoRepositorio.save(productoTaza1);

			/*
	    		https://i.ibb.co/RgTWhrZ/1434-a-HR0c-HM6-Ly9z-My5jb2lud-GVs-ZWdy-YXBo-Lm-Nvb-S91c-Gxv-YWRz-Lz-Iw-Mj-Et-MTAv-MDI1-OWY3-N2-Et-O.webp
				https://i.ibb.co/RSrMnd0/2021051910175571146.jpg
				https://i.ibb.co/hVsBXGp/giphy.webp
				https://i.ibb.co/TKxCRFk/https-hypebeast-com-image-2021-03-First-NFT-Art-Gallery-Space-Opens-in-New-York-b.jpg
				https://i.ibb.co/6R6Hm5r/itdo-nft-que-son-como-funcionan.jpg
				https://i.ibb.co/Vtc89xh/IYBJLIS4-FJKF5-PEBL5-LG7-WDMP4.jpg
				https://i.ibb.co/F4BXjrz/mutant-975x1024-1-1.jpg
				https://i.ibb.co/tDdjb3L/NFT-jpeg-900x510.jpg
				https://i.ibb.co/4WHyBDK/nft-2.jpg

				https://i.ibb.co/37ypZx5/nft-free-jpg-optimal.jpg

				https://i.ibb.co/Wxf2KF7/nfts.jpg

				https://i.ibb.co/Wf0dnfM/primer-nft-bitcat.jpg

				https://i.ibb.co/QQpKjJX/Qu-es-un-token-no-fungible-o-NFT.jpg

				https://i.ibb.co/2hFTcJf/sothebys-bored-apes-2.gif

				https://i.ibb.co/983NqGb/TTO3-PANSYFEUXORCCYTE3-XIJMQ.webp
				https://i.ibb.co/Dw32r9H/tweet-being-solds-millions-2021-ky-UG-656x492-Corriere-Web-Sezioni.jpg*/

				Producto nft1=new Producto("Cointelegraph1",1,"https://i.ibb.co/RgTWhrZ/1434-a-HR0c-HM6-Ly9z-My5jb2lud-GVs-ZWdy-YXBo-Lm-Nvb-S91c-Gxv-YWRz-Lz-Iw-Mj-Et-MTAv-MDI1-OWY3-N2-Et-O.webp",400000," ",TipoProducto.NFT);
				Producto nft2=new Producto("Niña Incendio",1,"https://i.ibb.co/RSrMnd0/2021051910175571146.jpg",100000," ",TipoProducto.NFT);
				Producto nft3=new Producto("Gato volador",1,"https://i.ibb.co/hVsBXGp/giphy.webp",250000," ",TipoProducto.NFT);
				Producto nft4=new Producto("Space New York",1,"https://i.ibb.co/TKxCRFk/https-hypebeast-com-image-2021-03-First-NFT-Art-Gallery-Space-Opens-in-New-York-b.jpg",150000," ",TipoProducto.NFT);
				Producto nft5=new Producto("Hot Rabbit",1,"https://i.ibb.co/6R6Hm5r/itdo-nft-que-son-como-funcionan.jpg",50000," ",TipoProducto.NFT);
				Producto nft6=new Producto("Quick Fix Woman",1,"https://i.ibb.co/Vtc89xh/IYBJLIS4-FJKF5-PEBL5-LG7-WDMP4.jpg",250000," ",TipoProducto.NFT);
				Producto nft7=new Producto("Monkey Crystal Eyes",1,"https://i.ibb.co/F4BXjrz/mutant-975x1024-1-1.jpg",120000," ",TipoProducto.NFT);
				Producto nft8=new Producto("Future moon",1,"https://i.ibb.co/tDdjb3L/NFT-jpeg-900x510.jpg",250050," ",TipoProducto.NFT);
				Producto nft9=new Producto("NFT Good Dollar Sir",1,"https://i.ibb.co/4WHyBDK/nft-2.jpg",150000," ", TipoProducto.NFT);
				Producto nft10=new Producto("Art New Men",1,"https://i.ibb.co/37ypZx5/nft-free-jpg-optimal.jpg",150000," ", TipoProducto.NFT);
				Producto nft11=new Producto("Fashion 2050 Girl",1,"https://i.ibb.co/Wxf2KF7/nfts.jpg",150000," ", TipoProducto.NFT);
				Producto nft12=new Producto("Indie Bit Cat",1,"https://i.ibb.co/Wf0dnfM/primer-nft-bitcat.jpg",150000," ", TipoProducto.NFT);
				Producto nft13=new Producto("Crystal Space Man",1,"https://i.ibb.co/QQpKjJX/Qu-es-un-token-no-fungible-o-NFT.jpg",150000," ", TipoProducto.NFT);
				Producto nft14=new Producto("Bored Apes",1,"https://i.ibb.co/2hFTcJf/sothebys-bored-apes-2.gif",1500000," ", TipoProducto.NFT);
				Producto nft15=new Producto("Dreamscape girl",1,"https://i.ibb.co/983NqGb/TTO3-PANSYFEUXORCCYTE3-XIJMQ.webp",1050000," ", TipoProducto.NFT);
				Producto nft16=new Producto("Jack Twitter",1,"https://i.ibb.co/Dw32r9H/tweet-being-solds-millions-2021-ky-UG-656x492-Corriere-Web-Sezioni.jpg*/",1050000," ", TipoProducto.NFT);

				productoRepositorio.save(nft1);
				productoRepositorio.save(nft2);
				productoRepositorio.save(nft3);
				productoRepositorio.save(nft4);
				productoRepositorio.save(nft5);
				productoRepositorio.save(nft6);
				productoRepositorio.save(nft7);
				productoRepositorio.save(nft8);
				productoRepositorio.save(nft9);
				productoRepositorio.save(nft10);
				productoRepositorio.save(nft11);
				productoRepositorio.save(nft12);
				productoRepositorio.save(nft13);
				productoRepositorio.save(nft14);
				productoRepositorio.save(nft15);
				productoRepositorio.save(nft16);



	};
	}

}
