package repository;
import model.Patient;
import java.io.*;
import java.time.LocalDate;


public class PatientRepositoryFile extends PatientRepository {
    private final String filename;

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
                        p = new Patient(el[0], el[1], id, el[3], LocalDate.parse(el[4]),el[5],el[6]);
                        super.addPatient(p);
                    }

                }
            }

        } catch (IOException | NumberFormatException f)
        {
            throw new RuntimeException(f);
        }

    }

    public void writeInFile()
    {
        try(PrintWriter pr=new PrintWriter(this.filename))
        {

            for (Patient dentistPatient : dentistPatients) {
                pr.write(dentistPatient.getFirstName());
                pr.write(",");
                pr.write(dentistPatient.getLastName());
                pr.write(",");
                pr.write(Integer.toString(dentistPatient.getId()));
                pr.write(",");
                pr.write(dentistPatient.getPhone_number());
                if (dentistPatient.getAppointment_date() != null) {
                    pr.write(",");
                    pr.write(dentistPatient.getAppointment_date().toString());
                    pr.write(",");
                    pr.write(dentistPatient.getAppointment_time().toString());
                    pr.write(",");
                    pr.write(dentistPatient.getAppointment_reason());
                }
                pr.write("\n");
            }

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
    public void addAppointment(int id,LocalDate ap_date,String ap_time,String reason)
    {
        super.addAppointment(id,ap_date,ap_time,reason);
        writeInFile();
    }

    @Override
    public void cancelAppointment(LocalDate ap_date, String ap_time)
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
