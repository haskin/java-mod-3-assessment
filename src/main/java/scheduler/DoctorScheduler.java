package scheduler;

import java.util.List;
import java.util.Optional;

import doctor.Doctor;

public interface DoctorScheduler {
    Optional<List<Doctor>> schedule(List<Doctor> doctors);
}
