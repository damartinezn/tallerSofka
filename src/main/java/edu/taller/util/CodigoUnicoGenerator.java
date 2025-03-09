package edu.taller.util;

import java.util.UUID;

public class CodigoUnicoGenerator {
    public static String generarCodigoDesdeUUID() {
        // Genera un UUID y elimina los caracteres no numéricos
        String uuidNumerico = UUID.randomUUID().toString().replaceAll("[^0-9]", "");

        // Asegura que tenga al menos 10 dígitos
        while (uuidNumerico.length() < 10) {
            uuidNumerico += UUID.randomUUID().toString().replaceAll("[^0-9]", "");
        }

        // Toma los primeros 10 dígitos
        return uuidNumerico.substring(0, 10);
    }
}
