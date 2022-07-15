package scheduler;

import java.util.List;
import java.util.Optional;

import model.Doctor;

public interface DoctorScheduler {
    Optional<List<Doctor>> schedule(List<Doctor> doctors);
}
