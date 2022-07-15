package patient;

import ailment.Ailment;

public class Patient {
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

    public int getHealthIndex() {
        return health.getHealthIndex();
    }

}
