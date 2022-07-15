package scheduler;

import java.util.List;
import java.util.Optional;

import model.Doctor;

public class RoundRobin implements DoctorScheduler {

    /**
     * Moves the first doctor to the back of the list
     */
    @Override
    public Optional<List<Doctor>> schedule(List<Doctor> doctors) {
        try {
            Doctor doctor = doctors.remove(0);
            doctors.add(doctor);
            return Optional.of(doctors);
        } catch (Exception e) { // empty list of doctors
            return Optional.empty();
        }
    }
}
