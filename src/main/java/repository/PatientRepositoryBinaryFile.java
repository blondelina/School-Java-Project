package repository;

import model.Patient;
import repository.PatientRepository;

import java.io.*;
import java.util.ArrayList;

public class PatientRepositoryBinaryFile extends PatientRepository {
    private String filename;

    public PatientRepositoryBinaryFile(String filename)
    {
        this.filename=filename;
    }

    public void readFromFile()
    {
        try(ObjectInputStream in=new ObjectInputStream(new FileInputStream(this.filename)))
        {
            dentistPatients = (ArrayList<Patient>) in.readObject();
            System.out.println("------Read from binary file------\n");
        }
        catch (Exception f)
        {
            System.out.println(f);
        }

    }

    public void writeInFile()
    {
        try(ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream(this.filename)))
        {
            out.writeObject(dentistPatients);
            System.out.println("------Wrote in binary file------\n");
        }
        catch (IOException f)
        {
            System.out.println(f);
        }
    }

    @Override
    public void addPatient(Patient p)
    {
        try {
            super.addPatient(p);
            writeInFile();
        }
        catch(RuntimeException e)
        {
            System.out.println(e);
        }
    }

    @Override
    public void addAppointment(int id,String ap_date,String ap_time,String reason)
    {
        try {
            super.addAppointment(id,ap_date,ap_time,reason);
            writeInFile();
        }
        catch(RuntimeException e)
        {
            System.out.println(e);
        }
    }

    @Override
    public void cancelAppointment(String ap_date,String ap_time)
    {
        try {
            super.cancelAppointment(ap_date,ap_time);
            writeInFile();
        }
        catch(RuntimeException e)
        {
            System.out.println(e);
        }
    }





}
