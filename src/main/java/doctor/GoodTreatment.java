package doctor;

import java.util.Random;

public class GoodTreatment implements Treatment{
    int treatmentValue = 10;
    @Override
    public int getTreatmentValue() {
        Random random = new Random();
        return treatmentValue + random.nextInt(treatmentValue);
    }
}
