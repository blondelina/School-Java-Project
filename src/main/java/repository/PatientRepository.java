package repository;
import model.Patient;
import java.time.LocalDate;
import java.util.*;


public class PatientRepository implements Repository {

    protected ArrayList<Patient> dentistPatients;

    public PatientRepository() {
        dentistPatients=new ArrayList<Patient>();
    }

    @Override
    public void addPatient(Patient p)
    {
        for (Patient dentistPatient : dentistPatients) {
            if (p.getId() == dentistPatient.getId())
                throw new RuntimeException("Id already exists in the repository...\n");

        }
        dentistPatients.add(p);
    }

    @Override
    public void addAppointment(int id, LocalDate ap_date, String ap_time, String reason)
    {
        int found=-1;
        for(int i=0;i<dentistPatients.size();i++) {
            if (id == dentistPatients.get(i).getId()) found=i;
            if(Objects.equals(dentistPatients.get(i).getAppointment_time(), ap_time) && Objects.equals(dentistPatients.get(i).getAppointment_date(), ap_date)) {
                throw new RuntimeException("Appointment time and date taken...");
            }


            }
        if(found>-1) {
            dentistPatients.get(found).setAppointment_date(ap_date);
            System.out.println(ap_date);
            dentistPatients.get(found).setAppointment_time(ap_time);
            dentistPatients.get(found).setAppointment_reason(reason);
        }
        else throw new RuntimeException("Patient not found...");

    }

    @Override
    public void cancelAppointment(LocalDate ap_date,String ap_time)
    {
        boolean found=false;
        for (Patient dentistPatient : dentistPatients)
            if (Objects.equals(dentistPatient.getAppointment_time(), ap_time) && Objects.equals(dentistPatient.getAppointment_date(), ap_date)) {
                dentistPatient.setAppointment_time(null);
                dentistPatient.setAppointment_date(null);
                dentistPatient.setAppointment_reason("");
                found = true;
            }
        if(!found)
            throw new RuntimeException("Appointment doesn't exist...");
    }


    @Override
    public void deletePatient(int id) {
        int found=-1;
        for(int i=0;i<dentistPatients.size();i++)
            if (id == dentistPatients.get(i).getId()) found=i;
        if(found==-1)
            throw new RuntimeException("Id not found...");
        else
            dentistPatients.remove(found);

    }


    @Override
    public ArrayList<Patient> getRepo()
    {
        return dentistPatients;
    }


}
