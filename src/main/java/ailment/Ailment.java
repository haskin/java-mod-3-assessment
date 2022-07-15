package ailment;

import util.Specialty;

public enum Ailment {
    MOLE_REMOVAL(Specialty.DERMATOLOGY, 90, true),
    CANCER(Specialty.RADIOLOGY, 10, true),
    COLD(Specialty.PEDIATRICS, 60, true),
    DIABITIES(Specialty.DERMATOLOGY, false);

    private Specialty specialty;
    private Integer ailmentIndex;
    private boolean curable;

    private Ailment(Specialty specialty, Integer ailmentIndex, boolean curable) {
        this.specialty = specialty;
        this.ailmentIndex = ailmentIndex;
        this.curable = curable;
    }

    private Ailment(Specialty specialty, boolean curable) {
        this.specialty = specialty;
        this.curable = curable;
    }

    boolean isCurable() {
        return curable;
    }

    public Specialty getSpecialty() {
        return specialty;
    }
}
