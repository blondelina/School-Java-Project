package repository;

import model.Patient;

import java.util.*;

public class PatientRepository implements Repository {

    protected ArrayList<Patient> dentistPatients;

    public PatientRepository() {
        dentistPatients=new ArrayList<Patient>();
    }

    @Override
    public void addPatient(Patient p)
    {
        for(int i=0;i<dentistPatients.size();i++) {
            if (p.getId() == dentistPatients.get(i).getId())
                throw new RuntimeException("Id already exists in the repository...\n");

        }
        dentistPatients.add(p);
    }

    @Override
    public void addAppointment(int id,String ap_date,String ap_time,String reason)
    {
        int index=-1;
        for(int i=0;i<dentistPatients.size();i++) {
            if (id == dentistPatients.get(i).getId()) index=i;
            if(Objects.equals(dentistPatients.get(i).getAppointment_time(), ap_time) && Objects.equals(dentistPatients.get(i).getAppointment_date(), ap_date)) {
                throw new RuntimeException("Appointment time and date taken...");
            }


            }
        if(index>-1) {
            dentistPatients.get(index).setAppointment_date(ap_date);
            dentistPatients.get(index).setAppointment_time(ap_time);
            dentistPatients.get(index).setAppointment_reason(reason);
        }
        else throw new RuntimeException("Patient not found...");

    }

    @Override
    public void cancelAppointment(String ap_date,String ap_time)
    {
        boolean found=false;
        for(int i=0;i<dentistPatients.size();i++)
            if(Objects.equals(dentistPatients.get(i).getAppointment_time(), ap_time) && Objects.equals(dentistPatients.get(i).getAppointment_date(), ap_date)) {
                dentistPatients.get(i).setAppointment_time("");
                dentistPatients.get(i).setAppointment_date("");
                dentistPatients.get(i).setAppointment_reason("");
                found=true;
            }
        if(!found)
            throw new RuntimeException("Appointment doesn't exist...");
    }

    @Override
    public void deletePatient(int id) {
        int index=-1;
        for(int i=0;i<dentistPatients.size();i++)
            if (id == dentistPatients.get(i).getId()) index=i;
        if(index==-1)
            throw new RuntimeException("Id not found...");
        else
            dentistPatients.remove(index);

    }

    @Override
    public String toString()
    {
        String n="";
        for(int i=0;i<dentistPatients.size();i++)
            n=n+dentistPatients.get(i);
        return n;
    }

    @Override
    public String Swapped(String st)
    {
        String[] d=st.split("/");
        List l=Arrays.asList(d);
        if(l.size()>1) {
            Collections.swap(l, 0, 2);
            String returned_string;
            returned_string = l.get(0).toString() + "/" + l.get(1).toString() + "/" + l.get(2).toString();
            return returned_string;
        }
        else
            return st;
    }

    @Override
    public ArrayList<Patient> getRepo()
    {
        return dentistPatients;
    }






}
