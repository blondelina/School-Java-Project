package controller;
import model.Patient;
import repository.PatientRepositoryFile;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Comparator;


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
    public void addAppointment(int id, LocalDate ap_date, String ap_time, String reason) {
        repo.addAppointment(id,ap_date,ap_time,reason);
    }

    @Override
    public void cancelAppointment(LocalDate ap_date, String ap_time) {
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

    public List<Patient> AppointmentsOnDateReportStream(LocalDate date)
    {
        List<Patient> appointments_on_date= repo.getRepo().stream().filter(x-> Objects.equals(x.getAppointment_date(), date)).collect(Collectors.toList());
        if(appointments_on_date.size()==0)
            throw new RuntimeException("No appointments on this date...");
        else
            return appointments_on_date;
    }

    public List<Patient> ImportantMedicalProblemsStream()
    {
        List<Patient> important_medical_problems=repo.getRepo().stream().filter(x-> !Objects.equals(x.getAppointment_reason(), "checkup") && !Objects.equals(x.getAppointment_date(), null)).collect(Collectors.toList());
        if(important_medical_problems.size()==0)
            throw new RuntimeException("No important medical problems...");
        else
            return important_medical_problems;
    }


    public List<Patient> CheckupsOrderedStream()
    {
        List<Patient> checkups=repo.getRepo().stream().filter(x-> Objects.equals(x.getAppointment_reason(), "checkup")).collect(Collectors.toList());
        List<Patient> ordered_checkups=checkups.stream().sorted(Comparator.comparing(Patient::getAppointment_date).thenComparing(Patient::getAppointment_time)).collect(Collectors.toList());
        if(checkups.size()==0)
            throw new RuntimeException("No checkups...");
        else return ordered_checkups;

    }
}
