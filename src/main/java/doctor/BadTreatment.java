package doctor;

import java.util.Random;

public class BadTreatment implements Treatment{

    int treatmentValue = 10;
    @Override
    public int getTreatmentValue() {
        Random random = new Random();
        return random.nextInt(treatmentValue) - random.nextInt(treatmentValue);
    }
}
