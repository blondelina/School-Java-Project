package controller;

import model.Patient;

import java.util.ArrayList;

public interface ControllerInterface {
    public void addPatient(Patient p);
    public void addAppointment(int id,String ap_date,String ap_time,String reason);
    public void cancelAppointment(String ap_date,String ap_time);
    public void deletePatient(int id);
    public ArrayList<Patient> getAll();

}
