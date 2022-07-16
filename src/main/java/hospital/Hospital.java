package hospital;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import doctor.Doctor;
import model.DoctorPatient;
import model.Specialty;
import patient.Patient;
import scheduler.DoctorScheduler;

public class Hospital {
    private String name;
    private List<Patient> patients = new ArrayList<>();
    private List<Doctor> doctors = new ArrayList<>();
    private List<DoctorPatient> doctorPatients = new ArrayList<>();
    private Map<Specialty, List<Doctor>> departments = new HashMap<>();


    public Hospital() {

    }

    public Hospital(String name) {
        this.name = name;
        for (Specialty specialty : Specialty.values()) {
            departments.put(specialty, new LinkedList<>()); // LinkedList because of scheduler
        }
    }


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

    public void addPatient(Patient patient, Specialty specialty, DoctorScheduler scheduler) {
        patients.add(patient);
        try {
            doctorPatients.add(new DoctorPatient(departments.get(specialty).get(0).getDoctorId(), patient.getPatientId()));
        } catch (IndexOutOfBoundsException | NullPointerException e) { // No doctors in the department
            doctorPatients.add(new DoctorPatient(null, patient.getPatientId()));
        }
        List<Doctor> department = departments.get(specialty);
        departments.put(specialty, scheduler.schedule(department).orElse(null));
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

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public void setDoctorPatients(List<DoctorPatient> doctorPatients) {
        this.doctorPatients = doctorPatients;
    }

    public Map<Specialty, List<Doctor>> getDepartments() {
        return departments;
    }

    public void setDepartments(Map<Specialty, List<Doctor>> departments) {
        this.departments = departments;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void removePatient(Patient patient) {
        patients.remove(patient);
        doctorPatients.stream().filter(doctorPatient -> doctorPatient.getPatientId() == patient.getPatientId()).findFirst()
                .ifPresent(doctorPatientMapping -> doctorPatients.remove(doctorPatientMapping));
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
