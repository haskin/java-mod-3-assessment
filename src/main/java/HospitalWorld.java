import java.util.Optional;
import java.util.Scanner;

import hospital.HospitalService;
import model.Ailment;
import doctor.Doctor;
import model.DoctorPatient;
import hospital.Hospital;
import patient.Patient;
import scheduler.DoctorScheduler;
import scheduler.RoundRobin;
import util.FileIOUtil;
import model.Specialty;
import util.UserInput;

/*
 * Notes (Changes I made from Lab Requirements)
 * - Ailments will contain whether a full cure is possible. I am omitting number of treatments variable because it is useless.
 * - The error message for numbers does not work. When it is output, it stops the program. I have commented it out.
 * TODO:
 * x Each patient has a health index
 * x Start each patient with a health index based on their ailment
 * x Allow the user to select a patient to go through a round of treatment
 * x Call out places where user input correction is needed
 *       x User selects a patient index that's outside the range of existing patients
 * x Update the patient's health index when they go through a round of treatment
 * x Dismiss a patient from the hospital once their health index reaches 100
 * x Allow the user to stop the virtual world and restore it to its last state when they start the virtual world again
 * x Allow the user to admit new patients at the hospital
 */
public class HospitalWorld {
//    private static final int PATIENT_SIZE = 1;
//    private static final int DOCTOR_SIZE = 5;

    private static final DoctorScheduler SCHEDULER = new RoundRobin();
    private static final int PATIENT_SIZE = 2;
    private static final int DOCTOR_SIZE = 2;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {

                // Create Hospital
                System.out.println("\n---- Hospital Creation System ----");
                Optional<Hospital> optionalHospital = Optional.empty();
//                Hospital hospital = null;
                if (FileIOUtil.hospitalExists()) {
                    System.out.print("Previous hospital found. Restore previous hospital? (Y/n): ");
                    if (!scanner.nextLine().equalsIgnoreCase("n")) {
                        optionalHospital = FileIOUtil.getHospital();
                    }
                }

                Hospital hospital = optionalHospital.orElseGet(() -> UserInput.getUserHospital(scanner));
//                FileIOUtil.saveHospital(hospital);

                while (HospitalService.getDoctorAmount(hospital) < DOCTOR_SIZE) {
                    System.out.println(String.format("%n ---- Adding Doctor(s) ---- (Doctors Left: %s)",
                            DOCTOR_SIZE - HospitalService.getDoctorAmount(hospital)));
                    String doctorName = UserInput.getName(scanner, "doctor");
                    Specialty specialty = UserInput.getUserSpecialty(scanner);
                    hospital.addDoctor(new Doctor(specialty, doctorName));
                }

                while (HospitalService.getPatientAmount(hospital) < PATIENT_SIZE) {
                    System.out.println(String.format("%n ---- Adding Patient(s) ---- (Patients Left: %s)",
                            PATIENT_SIZE - HospitalService.getPatientAmount(hospital)));
                    String patientName = UserInput.getName(scanner, "patient");
                    Ailment ailment = UserInput.getUserAilment(scanner);
                    // Specialty specialty = UserInput.getUserSpecialty(scanner);
                    hospital.addPatient(
                            new Patient(patientName, ailment), ailment.getSpecialty(), SCHEDULER);
                }

                boolean isMenu = true;
                while (isMenu) {
                    System.out.println("\n---- Patient Menu ----");
                    switch(UserInput.getUserPatientOption(scanner)) {
                        case 0: // New patient
                            System.out.println("\n---- Adding New Patient ----");
                            String patientName = UserInput.getName(scanner, "patient");
                            Ailment ailment = UserInput.getUserAilment(scanner);
                            hospital.addPatient(
                                    new Patient(patientName, ailment), ailment.getSpecialty(), SCHEDULER);
                            break;
                        case 1: // treat user
                            System.out.println("\n---- Treating Patient ----");
                            Patient patient = UserInput.getUserPatient(scanner, hospital);
                            DoctorPatient doctorPatientMapping = null;
                            for (DoctorPatient doctorPatient : hospital.getDoctorPatients()) {
                                if (doctorPatient.getPatientId().equals(patient.getPatientId())) {
                                    doctorPatientMapping = doctorPatient;
                                    break;
                                }
                            }

                            if (doctorPatientMapping == null || doctorPatientMapping.getDoctorId() == null) {
                                System.out.println("This patient has no doctor. Please choose another patient.");
                                continue;
                            }

                            Doctor doctor = HospitalService.getDoctorById(hospital, doctorPatientMapping.getDoctorId());

                            if (doctor == null) {
                                System.out.println("This patient has no doctor. Please choose another patient.");
                                continue;
                            }

                            patient.printHealth();

                            if (!patient.getAilment().isCurable()) {
                                System.out.println(patient.getAilment().name() + " is incurable.");
                                System.out.println(doctor.getName() + "provided treatment but it had no effect.");
                            } else {
                                int treatment = doctor.getTreatment().getTreatmentValue();
                                System.out.println(String.format("Dr. %s treated %s with %s health points", doctor.getName(),
                                        patient.getName(), treatment));
                                patient.updateHealthIndex(treatment);
                            }
                            patient.printHealth();

                            if (patient.getHealth().getHealthIndex() <= 0 || patient.getHealth().getHealthIndex() >= 100) {
                                hospital.removePatient(patient);
                            }
                            break;
                        case 2:
                            isMenu = false;
                            break;
                        default:
                            break;
                    }
                }

                System.out.println(String.format("%n---- Hospital %s World ----",
                        hospital.getName()));
                hospital.getDoctorPatients().stream()
                        .forEach(doctorPatient -> System.out
                                .println(String.format("Patient: %s, Doctor: %s", HospitalService.getPatientById(hospital, doctorPatient.getPatientId()),
                                        HospitalService.getDoctorById(hospital, doctorPatient.getDoctorId()))));

                System.out.print("\nWould you like to save this Hospital World? (Y/n): ");
                if (!scanner.nextLine().equalsIgnoreCase("n")) {
                    FileIOUtil.saveHospital(hospital);
                }

                System.out.print("\nWould you like to create another Hospital World? (Y/n): ");
                if (scanner.nextLine().equalsIgnoreCase("n"))
                    return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
