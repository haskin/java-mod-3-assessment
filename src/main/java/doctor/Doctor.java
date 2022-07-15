package doctor;

import util.Specialty;

public class Doctor {

    private Specialty specialty;
    private String name;
    private Treatment treatment;

    public Doctor(Specialty specialty, String name) {
        this.specialty = specialty;
        this.name = name;
        this.treatment = new StableTreatment();
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

    public int getTreatment() {
        return treatment.getTreatmentValue();
    }
}
