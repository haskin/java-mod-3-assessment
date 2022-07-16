package patient;

import model.Ailment;

import java.util.Objects;
import java.util.UUID;

public class Patient {
    private String patientId;
    private String name;
    private Ailment ailment;
    private Health health;


    public Patient() {

    }

    public Patient(String name) {
        this.name = name;
    }

    public Patient(String name, Ailment ailment) {
        this.name = name;
        this.ailment = ailment;
        if (ailment.isCurable())
            this.health = new MutableHealth(ailment.getAilmentIndex());
        else
            this.health = new ImmutableHealth();

        this.patientId = UUID.randomUUID().toString();
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public Ailment getAilment() {
        return ailment;
    }

    public void printHealth() {
        System.out.println(
                String.format("%s has a health of %s", name, Health.getHealthBar(health.getHealthIndex())));
    }

    public void updateHealthIndex(int healthAddend) {
        health.updateHealthIndex(healthAddend);
    }

    public Health getHealth() {
        return health;
    }

    public void setHealth(Health health) {
            this.health = health;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return patientId == patient.patientId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(patientId);
    }
}
