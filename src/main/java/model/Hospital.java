package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import scheduler.DoctorScheduler;
import util.Specialty;

public class Hospital {
    private List<Patient> patients = new ArrayList<>();
    private List<Doctor> doctors = new ArrayList<>();
    private List<DoctorPatient> doctorPatients = new ArrayList<>();
    private Map<Specialty, List<Doctor>> departments = new HashMap<>();
    private DoctorScheduler scheduler;

    public Hospital(String name, DoctorScheduler scheduler) {
        this.name = name;
        this.scheduler = scheduler;
        for (Specialty specialty : Specialty.values()) {
            departments.put(specialty, new LinkedList<>());
        }
    }

    private String name;

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public List<DoctorPatient> getDoctorPatients() {
        return doctorPatients;
    }

    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
        departments.get(doctor.getSpecialty()).add(doctor);
    }

    public void addPatient(Patient patient, Specialty specialty) {
        patients.add(patient);
        try {
            doctorPatients.add(new DoctorPatient(departments.get(specialty).get(0), patient));
        } catch (IndexOutOfBoundsException | NullPointerException e) { // No doctors in the department
            doctorPatients.add(new DoctorPatient(null, patient));
        }
        List<Doctor> department = departments.get(specialty);
        departments.put(specialty, scheduler.schedule(department).orElse(null));
    }

    public int getDoctorsSize() {
        return doctors.size();
    }

    public int getPatientsSize() {
        return patients.size();
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    /**
     * Gets a random doctor that was assigned to the specialty
     * 
     * @param specialty
     * @return
     */
    public Optional<Doctor> getDoctorForSpecialty(Specialty specialty) {
        Doctor[] specialtyDoctors = doctors.stream()
                .filter(doctor -> doctor.getSpecialty() == specialty)
                .toArray(Doctor[]::new);
        if (specialtyDoctors.length == 0)
            return Optional.empty();
        Random random = new Random();
        return Optional.of(specialtyDoctors[random.nextInt(specialtyDoctors.length)]);
    }
}
