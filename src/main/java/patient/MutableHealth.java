package patient;

public class MutableHealth implements Health {
    private Integer healthIndex;

    public MutableHealth() {
        healthIndex = 100;
    }
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

    @Override
    public void setHealthIndex(int healthIndex) {
        this.healthIndex = healthIndex;
    }
}
