import java.util.Scanner;

import doctor.Doctor;
import model.Hospital;
import patient.Patient;
import util.Specialty;
import util.UserInput;

/*
 * Notes (Changes I made from Lab Requirements)
 * - Ailments will contain whether a full cure is possible. I am omitting number of treatments variable, because it is useless.
 * 
 * TODO:
 * x Each patient has a health index 
 * - Start each patient with a health index based on their ailment
 * - Allow the user to select a patient to go through a round of treatment
 * - Call out places where user input correction is needed
 *       - User selects a patient index that's outside the range of existing patients
 * - Update the patient's health index when they go through a round of treatment
 */
public class HospitalWorld {
    private static final int PATIENT_SIZE = 5;
    private static final int DOCTOR_SIZE = 3;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {

                // Create Hospital
                System.out.println("\n---- Hospital Creation System ----");

                Hospital hospital = UserInput.getUserHospital(scanner);

                while (hospital.getDoctorsSize() < DOCTOR_SIZE) {
                    System.out.println(String.format("%n ---- Adding Doctor(s) ---- (Doctors Left: %s)",
                            DOCTOR_SIZE - hospital.getDoctorsSize()));
                    String doctorName = UserInput.getName(scanner, "doctor");
                    Specialty specialty = UserInput.getUserSpecialty(scanner);
                    hospital.addDoctor(new Doctor(specialty, doctorName));
                }

                while (hospital.getPatientsSize() < PATIENT_SIZE) {
                    System.out.println(String.format("%n ---- Adding Patient(s) ---- (Patients Left: %s)",
                            PATIENT_SIZE - hospital.getPatientsSize()));
                    String patientName = UserInput.getName(scanner, "patient");
                    Specialty specialty = UserInput.getUserSpecialty(scanner);
                    hospital.addPatient(
                            new Patient(patientName), specialty);
                }

                System.out.println(String.format("%n---- Hospital %s World ----",
                        hospital.getName()));
                hospital.getDoctorPatients().stream()
                        .forEach(doctorPatient -> System.out
                                .println(String.format("Patient: %s, Doctor: %s", doctorPatient.getPatient(),
                                        doctorPatient.getDoctor())));
                // hospital.getPatients().stream()
                // .forEach(patient -> System.out.println(
                // String.format("Patient: %s, Doctor: %s", patient.getName(),
                // patient.getDoctor())));

                System.out.print("\nWould you like to create another Hospital World? (Y/n): ");
                if (scanner.nextLine().equalsIgnoreCase("n"))
                    return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
