package Clases;

import net.time4j.PlainDate;
import net.time4j.format.expert.ChronoFormatter;

import java.text.ParseException;
import java.util.Locale;
import java.util.Scanner;

public class ParseFecha {

    /**
     * Este método lee mediante una salida por pantalla una fecha y la devuelve en formato PlainDate.
     * @param fechaDeQue String, debería ser "ingreso" o "egreso"
     * @return Fecha en formato PlainDate
     */
    public static PlainDate pedirFecha(String fechaDeQue) {
        boolean fechaCorrecta;
        PlainDate plainDateParseada=null;
        do{
            try{
                System.out.println("Ingrese la fecha de "+fechaDeQue+" en formato AñoMesDia");
                Scanner sca = new Scanner(System.in);
                String fechaa = sca.next();
                ChronoFormatter<PlainDate> formateador = ChronoFormatter //construye el objeto que interpretará el String
                        .setUp(PlainDate.class, Locale.GERMAN)
                        .addFixedInteger(PlainDate.YEAR, 4)
                        .addFixedNumerical(PlainDate.MONTH_OF_YEAR,2)
                        .addFixedInteger(PlainDate.DAY_OF_MONTH,2)
                        .build();
                plainDateParseada = formateador.parse(fechaa);
                fechaCorrecta = true; // Si llega acá no saltó la excepcion y por ende puede salir del failCheck
            }catch (ParseException e){
                System.out.println("La fecha ingresada es incorrecta, por favor verifique si el formato\ny orden utilizados fueron los correctos y vuelva a ingresarla.\n\nA modo de ejemplo para 2018/04/27 la fecha ingresada debería ser 20180427 \n");
                fechaCorrecta = false;
            }
        }while (fechaCorrecta == false);
        return plainDateParseada;
    }


}
