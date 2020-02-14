package com.latam.desarrollo.be.desarrolloBE.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.GregorianCalendar;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FormatDate {

	public long calculateBirthDay(String birthDate) {
        log.info("entre al metodo");
        try {
            LocalDate today = LocalDate.now();
            LocalDate birthDay = LocalDate.parse(birthDate);
            LocalDate nextBDay = birthDay.withYear(today.getYear());
//          if(nextBDay.isBefore(today) || nextBDay.isEqual(today)) {
//              nextBDay = nextBDay.plusYears(1);
//          }
            Period p = Period.between(today, nextBDay);
            long totalDias = ChronoUnit.DAYS.between(today, nextBDay);

            if (totalDias == 0) {
                log.info("Su cumple es hoy, felicidades");
                return totalDias;
            } else {
                log.info("Restan " + p.getMonths() + " meses, y " + p.getDays() + " dias para su proximo cumple."
                        + totalDias + " dias en total");
                return totalDias;
            }

        } catch (DateTimeParseException exc) {
            log.error("error, no es una fecha valida ", birthDate);
        }
        return 0;

    }

    public int calcularEdad(String birthDate) {
    
        LocalDate fechaNac = LocalDate.parse(birthDate);
        LocalDate ahora = LocalDate.now();
        Period periodo = Period.between(fechaNac, ahora);
        return periodo.getYears();
    }

    public String cambiarFormatoFecha(String ds1) {
        String ds2 ="";
        try {
            
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
            ds2 = sdf2.format(sdf1.parse(ds1));
            System.out.println(ds2); //will be 30/06/2007
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return ds2;
    }
    
    public String cambiarFormatoFechaRequest(String ds1) {
        String ds2 ="";
        try {
            
            SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
            ds2 = sdf2.format(sdf1.parse(ds1));
            System.out.println(ds2); //will be 30/06/2007
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return ds2;
    }
    public int splitAnio(String date) {
        String year = "";
        String parts [] = date.split("-");
        for(String anio : parts) {
            year = parts[0];
        }
        int anio = Integer.parseInt(year);
        return anio;
    }
    public boolean validarAnioBisiesto(int anio) {
        boolean validar = false;
        GregorianCalendar calendar = new GregorianCalendar();

        if (calendar.isLeapYear(anio)) {
            System.out.println("es bisiesto");
        validar = true;
        } else {
            System.out.println("no es bisiesto");
            validar = false;
        }
        return validar;
    }
}
