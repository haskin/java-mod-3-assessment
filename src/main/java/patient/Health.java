package patient;

import java.util.stream.IntStream;

public interface Health {
    void updateHealthIndex(int healthAddend);

    Integer getHealthIndex();

    // ========= 90% health
    public static String getHealthBar(int health) {
        StringBuilder equalsBar = new StringBuilder();
        IntStream.range(0, health / 10).mapToObj(i -> "=").forEach(equalsBar::append);
        return String.format("%s %s%% health", equalsBar.toString(), health);

    }
}
