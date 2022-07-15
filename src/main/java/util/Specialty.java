package util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public enum Specialty {

    DERMATOLOGY("D"),
    PEDIATRICS("P"),
    RADIOLOGY("R");

    // private static Map<String, Specialty> abbreviationToSpecialty = new
    // HashMap<>();

    Specialty(String abbreviation) {
        this.abbreviation = abbreviation;
        Conversion.abbreviationToSpecialty.put(abbreviation, this);
    }

    private static final class Conversion {
        protected static final Map<String, Specialty> abbreviationToSpecialty = new HashMap<>();
    }

    private final String abbreviation;

    public String getAbbreviation() {
        return abbreviation;
    }

    public static Specialty getSpecialtyFromAbbreviation(String abbreviation) throws Exception {
        abbreviation = abbreviation.trim().toUpperCase();
        if (!Conversion.abbreviationToSpecialty.containsKey(abbreviation))
            throw new Exception();
        return Conversion.abbreviationToSpecialty.get(abbreviation);
    }

    public static Specialty getRandomSpecialty() {
        Random random = new Random();
        Specialty[] specialties = Specialty.values();
        return specialties[random.nextInt(specialties.length)];
    }

    public static void printSpecialties() {
        System.out.println("\n ---- Specialities Available ----");
        Arrays.stream(Specialty.values()).forEach(specialty -> System.out
                .println(String.format("%-4sFor %s enter %s", "", specialty, specialty.getAbbreviation())));
        System.out.println(" --------------------------------");
    }
}
