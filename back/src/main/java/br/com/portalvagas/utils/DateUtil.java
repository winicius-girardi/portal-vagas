package br.com.portalvagas.utils;

import java.time.LocalDate;

public class DateUtil{

    public static LocalDate createDateNow() {
        return LocalDate.now();
    }

    public static LocalDate createExpirationDate(LocalDate data) {
        return data.plusDays(30);
    }

}


