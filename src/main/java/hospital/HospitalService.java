package hospital;

import doctor.Doctor;
import patient.Patient;

public class HospitalService {
    public static int getDoctorAmount(final Hospital hospital) {
        return hospital.getDoctors().size();
    }

    public static int getPatientAmount(final Hospital hospital) {
        return hospital.getPatients().size();
    }

    public static Doctor getDoctorById(Hospital hospital, String doctorId) {
        for(Doctor doctor : hospital.getDoctors()) {
            if(doctor.getDoctorId().equals(doctorId))
                return doctor;
        }
        return null;
    }

    public static Patient getPatientById(Hospital hospital, String patientId) {
        for(Patient patient: hospital.getPatients()) {
            if(patient.getPatientId().equals(patientId))
                return patient;
        }
        return null;
    }
}
