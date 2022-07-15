package patient;

public class ImmutableHealth implements Health {

    @Override
    public Integer getHealthIndex() {
        return 50;
    }

    @Override
    public void updateHealthIndex(int healthAddend) {

    }

}
