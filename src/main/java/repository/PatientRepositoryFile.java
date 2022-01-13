package repository;

import javafx.fxml.FXML;
import model.Patient;
import repository.PatientRepository;

import java.io.*;


public class PatientRepositoryFile extends PatientRepository {
    private String filename;

    public PatientRepositoryFile(String filename)
    {
        this.filename=filename;

    }

    public void readFromFile()
    {
        try(BufferedReader reader = new BufferedReader(new FileReader(this.filename))) {

            String line="";
            while((line=reader.readLine())!=null)
            {
                int id;
                String[] el=line.split(",");
                if(el.length!=7 && el.length!=4){
                    System.out.println(el);
                    throw new RuntimeException("Not a valid nr of attributes\n");

                }

                else {
                    id = Integer.parseInt(el[2]);
                    Patient p;

                    if(el.length==4) {
                        p = new Patient(el[0], el[1], id,el[3] );

                        super.addPatient(p);
                    }
                    if(el.length==7) {
                        p = new Patient(el[0], el[1], id, el[3], el[4],el[5],el[6]);
                        super.addPatient(p);
                    }

                }
            }
            System.out.println("------Read from file------\n");
        } catch (IOException | NumberFormatException f)
        {
            System.out.println(f);
        }

    }

    public void writeInFile()
    {
        try(PrintWriter pr=new PrintWriter(this.filename))
        {

            for(int i=0;i<dentistPatients.size();i++) {
                pr.write(dentistPatients.get(i).getFirstName());
                pr.write(",");
                pr.write(dentistPatients.get(i).getLastName());
                pr.write(",");
                pr.write(Integer.toString(dentistPatients.get(i).getId()));
                pr.write(",");
                pr.write(dentistPatients.get(i).getPhone_number());
                if(dentistPatients.get(i).getAppointment_date()!="") {
                    pr.write(",");
                    pr.write(dentistPatients.get(i).getAppointment_date());
                    pr.write(",");
                    pr.write(dentistPatients.get(i).getAppointment_time());
                    pr.write(",");
                    pr.write(dentistPatients.get(i).getAppointment_reason());
                }
                pr.write("\n");
            }
            System.out.println("------Wrote in file------\n");
        }
        catch(FileNotFoundException f)
        {
            System.out.println(f);
        }


    }

    @Override
    public void addPatient(Patient p)
    {
        super.addPatient(p);
        writeInFile();
    }

    @Override
    public void addAppointment(int id,String ap_date,String ap_time,String reason)
    {
        super.addAppointment(id,ap_date,ap_time,reason);
        writeInFile();
    }

    @Override
    public void cancelAppointment(String ap_date,String ap_time)
    {
        super.cancelAppointment(ap_date,ap_time);
        writeInFile();
    }

    @Override
    public void deletePatient(int id) {
        super.deletePatient(id);
        writeInFile();
    }
}
