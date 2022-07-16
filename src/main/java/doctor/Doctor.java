package doctor;

import model.Specialty;

import java.util.Objects;
import java.util.UUID;

public class Doctor {
    private String doctorId;
    private Specialty specialty;
    private String name;
    private Treatment treatment;

    public Doctor() {
//        treatment = new StableTreatment();
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public Doctor(Specialty specialty, String name) {
        this.specialty = specialty;
        this.name = name;
        this.treatment = new StableTreatment();
        this.doctorId = UUID.randomUUID().toString();
    }

    public Doctor(Specialty specialty, String name, Treatment treatment) {
        this.specialty = specialty;
        this.name = name;
        this.treatment = treatment;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("{ %s, Specialty: %s }", name, specialty);
    }

    public Treatment getTreatment() {
        return treatment;
    }

    public void setTreatment(Treatment treatment) {
        this.treatment = treatment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return doctorId == doctor.doctorId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(doctorId);
    }
}
