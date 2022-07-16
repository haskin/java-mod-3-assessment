package doctor;

import java.util.Random;

public class TreatmentFactory {
    public static Treatment getRandomTreatment() {
        Treatment[] treatments = {new StableTreatment(), new GoodTreatment(), new BadTreatment()};
        Random random = new Random();
        return treatments[random.nextInt(treatments.length)];
    }
}
