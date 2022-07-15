package doctor;

/**
 * Stable treatment which will add 10 to the health index
 * of the patient.
 */
public class StableTreatment implements Treatment {
    int treatmentValue = 10;

    @Override
    public int getTreatmentValue() {
        return treatmentValue;
    }

}
