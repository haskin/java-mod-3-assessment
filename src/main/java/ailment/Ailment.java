package ailment;

import util.Specialty;

public enum Ailment {
    MOLE_REMOVAL(Specialty.DERMATOLOGY, true),
    CANCER(Specialty.RADIOLOGY, true),
    COLD(Specialty.PEDIATRICS, true),
    DIABITIES(Specialty.DERMATOLOGY, false);

    private Specialty specialty;
    private boolean curable;

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
