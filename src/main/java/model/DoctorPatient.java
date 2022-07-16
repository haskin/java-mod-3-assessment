package model;

import doctor.Doctor;
import patient.Patient;


public class DoctorPatient {
    private String doctorId;
    private String patientId;

    public DoctorPatient() {

    }

    public DoctorPatient(String doctorId, String patientId) {
        this.doctorId = doctorId;
        this.patientId = patientId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }
}
