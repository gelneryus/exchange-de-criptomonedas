package Mindhub.RaspCash.utilidades;

import org.springframework.context.annotation.Bean;

import java.nio.charset.Charset;
import java.util.Random;

public class Utilidades {
    //Clase con utilidades necesarias para diferentes procesos, por ejemplo creacion aleatoria de direccion de billetara

    public Utilidades() {
    }

    @Bean
    public String obtenerDireccionBilletera(){

            //Declaro 26 como el tamaño para crear la direccion aleatoria
            int i=26;

            // bind the length
            byte[] bytearray;
            bytearray = new byte[256];

            String theAlphaNumericS;

            new Random().nextBytes(bytearray);

           String mystring  = new String(bytearray, Charset.forName("UTF-8"));

            StringBuffer thebuffer = new StringBuffer();

            //remove all spacial char
            theAlphaNumericS = mystring.replaceAll("[^A-Z0-9]", "");

            //random selection
            for (int m = 0; m < theAlphaNumericS.length(); m++) {

                if (Character.isLetter(theAlphaNumericS.charAt(m))
                        && (i > 0)
                        || Character.isDigit(theAlphaNumericS.charAt(m))
                        && (i > 0)) {

                    thebuffer.append(theAlphaNumericS.charAt(m));
                    i--;
                }
            }

            // the resulting string
            return thebuffer.toString();
        }
    }

