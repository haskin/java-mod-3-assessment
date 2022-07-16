package doctor;

import java.util.Random;

public class GoodTreatment implements Treatment{

    @Override
    public int getTreatmentValue() {
        Random random = new Random();
        return 10 + random.nextInt(10);
    }
}
