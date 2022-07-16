package doctor;

import java.util.Random;

public class BadTreatment implements Treatment{

    @Override
    public int getTreatmentValue() {
        Random random = new Random();
        return random.nextInt(10) - random.nextInt(10);
    }
}
