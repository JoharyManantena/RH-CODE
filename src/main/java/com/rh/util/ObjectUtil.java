package com.rh.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ObjectUtil {

public LocalDate convertToLocalDate(String dateStr) {
    // Assurez-vous que la chaîne est au format 'yyyy-MM-dd' comme dans l'input HTML
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    return LocalDate.parse(dateStr, formatter);
}

}
