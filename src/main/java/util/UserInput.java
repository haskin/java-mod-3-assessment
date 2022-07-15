package util;

import java.util.Scanner;
import java.util.UUID;

import model.Hospital;
import scheduler.RoundRobin;

public class UserInput {

    public static Hospital getUserHospital(Scanner scanner) {
        System.out.print("\nPlease enter hospital name (Default is random name): ");
        String hospitalName = "";
        try {
            hospitalName = scanner.nextLine();
            if (hospitalName.isBlank())
                throw new Exception();

        } catch (Exception e) {
            hospitalName = String.valueOf(UUID.randomUUID().toString().toCharArray(), 0, 4);
            System.out.println(
                    String.format("ERROR: This hospital name is invalid. The name \"%s\" will be used", hospitalName));

        }

        return new Hospital(hospitalName, new RoundRobin());
    }

    public static Specialty getUserSpecialty(Scanner scanner) {
        try {
            while (true) {
                System.out.print(
                        "\nPlease enter a Specialty abbreviation or I for specialty abbreviation information (Default chosen at random): ");
                String specialtyAbbreviation = scanner.nextLine();
                if (specialtyAbbreviation.equalsIgnoreCase("I")) {
                    Specialty.printSpecialties();
                    continue;
                }
                return Specialty.getSpecialtyFromAbbreviation(specialtyAbbreviation);
            }

        } catch (Exception e) {
            Specialty specialty = Specialty.getRandomSpecialty();
            System.out.println(String.format(
                    "ERROR: This specialty abbreviation was invalid. The specialty \"%s\" will be used.", specialty));
            return specialty;
        }
    }

    /**
     * 
     * @param scanner
     * @param category Either doctor or patient
     * @return
     */
    public static String getName(Scanner scanner, String category) {
        System.out.print(String.format("%nPlease enter %s name (Default is random name): ", category));
        String name = "";
        try {
            name = scanner.nextLine();
            if (name.isBlank())
                throw new Exception();

        } catch (Exception e) {
            name = RandomName.getRandomName();
            System.out.println(String.format("ERROR: This name is invalid. The name \"%s\" will be used", name));

        }
        return name;
    }
}
