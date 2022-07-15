package patient;

public class MutableHealth implements Health {
    private Integer healthIndex;

    public MutableHealth(int healthIndex) {
        this.healthIndex = healthIndex;
    }

    @Override
    public void updateHealthIndex(int healthAddend) {
        healthIndex += healthAddend;
    }

    @Override
    public Integer getHealthIndex() {
        return healthIndex;
    }
}
