package util;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.stream.IntStream;

import model.Ailment;
import hospital.Hospital;
import patient.Patient;
import scheduler.RoundRobin;
import model.Specialty;

public class UserInput {

    /**
     * Gets a number from the user.
     * 
     * @param scanner
     * @param prompt
     * @param minRange      (inclusive)
     * @param maxRange      (exclusive)
     * @param defaultNumber
     * @return
     */
    private static Integer getUserNumber(Scanner scanner, String prompt, Integer minRange, Integer maxRange,
            Integer defaultNumber) {
        while (true) {
            try {
                System.out.print(prompt);
                Integer answer = Integer.valueOf(scanner.nextLine().trim());
                if (minRange <= answer && answer < maxRange) {
                    return answer;
                }
            } catch (Exception e) {
                // Program stops if I have the below error message ????
//                System.out.println("ERROR: Invalid number was given.");
                if (defaultNumber != null)
                    return defaultNumber;
            }
        }
    }

    public static Integer getUserPatientOption(Scanner scanner) {
        StringBuilder prompt = new StringBuilder();
        prompt.append(("Please choose an option from the following indices: \n"));
        String[] choices = {"Add a new patient", "Treat a patient", "Exit the patient menu"};
        String choiceFormat = "%s. %s %n";
        IntStream.range(0,choices.length).mapToObj(i -> String.format(choiceFormat, i, choices[i])).forEach(prompt::append);
        return getUserNumber(scanner, prompt.toString(), 0, choices.length, null);
    }


    /**
     * Gets an ailment from the user
     * 
     * @param scanner
     * @return
     */
    public static Ailment getUserAilment(Scanner scanner) {
        StringBuilder stringBuilder = new StringBuilder();
        Ailment[] ailments = Ailment.values();
        IntStream.range(0, ailments.length)
                .forEach(i -> stringBuilder.append("\t" + i + ". " + ailments[i].name() + "\n"));
        String ailmentChoices = stringBuilder.toString();
        String prompt = "Please choose an ailment using an index from below: \n" + ailmentChoices;
        return ailments[getUserNumber(scanner, prompt, 0, ailments.length, null)];
    }

    /**
     * Gets an ailment from the user
     * 
     * @param scanner
     * @return
     */
    public static Patient getUserPatient(Scanner scanner, Hospital hospital) {
        StringBuilder patientChoices = new StringBuilder();
        String patientString = "\t%s. %s (Ailment: %s)%n";
        List<Patient> patients = hospital.getPatients();
        IntStream.range(0, patients.size())
                .forEach(index -> patientChoices.append(String.format(patientString, index,
                        patients.get(index).getName(), patients.get(index).getAilment().name())));
        String prompt = "Please choose an patient using an index from below: \n" + patientChoices.toString();
        return patients.get(getUserNumber(scanner, prompt, 0, patients.size(), null));
    }

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

        return new Hospital(hospitalName);
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
