package util;

import java.util.Random;

public class RandomName {
    /**
     * Returns a random name
     * 
     * @return
     */
    public static String getRandomName() {
        String[] fakeNames = { "Alfredo", "Lexi", "Kelsie", "Dakota", "Myron", "Ximena", "Luka", "Koda", "Clara",
                "Harper", "Oskar", "Gianluca", "Kylie", "Carsyn", "Aubrey", "Gillian", "Denisse", "Lyla", "Romina",
                "Audrey", "Gilberto", "Sailor", "Jad", "Aliyana", "Harlee", "Latrell", "Adan", "Rhys", "Arabella",
                "Anakin", "Izabelle", "Ronin", "Olivia", "Tripp",
                "Evan", "Jailyn", "Kalen", "Kensley", "Fabiola", "Bryson", "Savanna", "Tatiana", "Kya", "Sailor",
                "Niko", "Kaden", "Kenlee", "Willis", "Shepherd", "Haddie" };
        Random random = new Random();
        return fakeNames[random.nextInt(fakeNames.length)];
    }
}
