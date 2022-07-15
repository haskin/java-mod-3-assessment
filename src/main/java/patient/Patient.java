package patient;

public class Patient {
    private String name;
    private int id;
    private Health health;

    public Patient(String name) {
        this.name = name;
        this.id = this.hashCode();
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
