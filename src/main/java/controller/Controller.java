package controller;
import model.Patient;
import repository.PatientRepositoryFile;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.Properties;

public class Controller implements ControllerInterface {

    private PatientRepositoryFile repo;

    public Controller(){

            Properties properties=new Properties();
            try {
                properties.load(new FileInputStream("files.properties"));
                String textfilename = properties.getProperty("TextFile");
                if (textfilename != null) {
                    repo = new PatientRepositoryFile(textfilename);
                    repo.readFromFile();
                }

            }
            catch (IOException ignored){}

    }

    @Override
    public void addPatient(Patient p) {
        repo.addPatient(p);
    }

    @Override
    public void addAppointment(int id, String ap_date, String ap_time, String reason) {
        repo.addAppointment(id,ap_date,ap_time,reason);
    }

    @Override
    public void cancelAppointment(String ap_date, String ap_time) {
        repo.cancelAppointment(ap_date,ap_time);
    }

    @Override
    public void deletePatient(int id)
    {
        repo.deletePatient(id);
    }

    @Override
    public ArrayList<Patient> getAll() {
        return repo.getRepo();
    }

    public List<Patient> AppointmentsOnDateReportStream(String date)
    {
        List<Patient> p= repo.getRepo().stream().filter(x-> Objects.equals(x.getAppointment_date(), date)).collect(Collectors.toList());

        if(p.size()==0)
            throw new RuntimeException("No appointments on this date...");
        else
            return p;
    }


    public String PhoneNumberOfPatientStream(String firstname, String lastname)
    {
        List<Patient> p = repo.getRepo().stream().filter(x -> Objects.equals(x.getLastName(), lastname) && Objects.equals(x.getFirstName(), firstname)).collect(Collectors.toList());
        if (p.size() == 0)
            throw new RuntimeException("No patient found...");
        else
            return p.get(0).getPhone_number();

    }

    public String EarliestAppointmentStream()
    {
        List l= repo.getRepo().stream().map(x-> repo.Swapped(x.getAppointment_date()) +" "+ x.getAppointment_time()).sorted().collect(Collectors.toList());
        int i=0;
        while(l.get(i).toString().trim().length()==0)
            i++;
        if(i==repo.getRepo().size())
            throw new RuntimeException("No appointments found...");
        else
            return repo.Swapped(l.get(i).toString().split("\s")[0])+" "+l.get(i).toString().split("\s")[1]+"\n";

    }


    public List<Patient> ImportantMedicalProblemsStream()
    {
        List<Patient> l=repo.getRepo().stream().filter(x-> !Objects.equals(x.getAppointment_reason(), "checkup") && !Objects.equals(x.getAppointment_date(), "")).collect(Collectors.toList());
        if(l.size()==0)
            throw new RuntimeException("No important medical problems...");
        else
            return l;
    }


    public List<Patient> CheckupsOrderedStream()
    {
        List<Patient> a=repo.getRepo().stream().filter(x-> Objects.equals(x.getAppointment_reason(), "checkup")).collect(Collectors.toList());
        List l= a.stream().map(x-> repo.Swapped(x.getAppointment_date()) +" "+ x.getAppointment_time()).sorted().collect(Collectors.toList());

        if(l.size()==0)
            throw new RuntimeException("No checkups...");
        else return a;

    }
}
