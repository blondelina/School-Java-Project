package controller;

import model.Patient;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public interface ControllerInterface {
    public void addPatient(Patient p);
    void addAppointment(int id, LocalDate ap_date, String ap_time, String reason);
    public void cancelAppointment(LocalDate ap_date, String ap_time);
    public void deletePatient(int id);
    public ArrayList<Patient> getAll();

}
