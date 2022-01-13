package view;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Appointment;
import model.Patient;
import java.net.URL;
import java.util.ResourceBundle;

public class UI implements Initializable {

    Controller controller;
    private ObservableList<Patient> list ;
    private ObservableList<Patient> list1 ;

    @FXML private ComboBox hour;
    @FXML private ComboBox min;
    @FXML private TextField Id;
    @FXML private DatePicker d;
    @FXML private TextArea r;

    @FXML private ComboBox hour1;
    @FXML private ComboBox min1;
    @FXML private DatePicker d1;

    @FXML private TextField lname;
    @FXML private TextField fname;
    @FXML private TextField phnumber;
    @FXML private TextField ID;

    @FXML
    private TableView<Patient> PatientTable;

    @FXML
    private TableColumn<Patient,Integer> id;

    @FXML
    private TableColumn<Patient,String> lastName;

    @FXML
    private TableColumn<Patient,String> firstName;

    @FXML
    private TableColumn<Patient,String> phone_number;

    @FXML
    private TableColumn<Appointment,String> date;

    @FXML
    private TableColumn<Appointment,String> time;

    @FXML
    private TableColumn<Appointment,String> reason;

    @FXML
    private TextField id_delete;

    @FXML
    private Label errorLabel1;

    @FXML
    private Label errorLabel2;

    @FXML
    private Label errorLabel3;

    @FXML
    private Label errorLabel4;

    @FXML
    private Label errorLabel5;

    @FXML
    private Label errorLabel6;

    @FXML
    private Label errorLabel7;

    @FXML
    private DatePicker report_date;

    @FXML
    private TableView<Patient> PatientTable1;

    @FXML
    private TableColumn<Patient,Integer> id1;

    @FXML
    private TableColumn<Patient,String> lastName1;

    @FXML
    private TableColumn<Patient,String> firstName1;

    @FXML
    private TableColumn<Patient,String> phone_number1;

    @FXML
    private TableColumn<Appointment,String> date1;

    @FXML
    private TableColumn<Appointment,String> time1;

    @FXML
    private TableColumn<Appointment,String> reason1;



    public UI()  {
        controller = new Controller();
        list = FXCollections.observableArrayList();
        list1 = FXCollections.observableArrayList();
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        min.getItems().setAll(
                "00",
                "01","02","03","04","05","06","07","08","09","10",
                "11","12","13","14","15","16","17","18","19","20",
                "21","22","23","24","25","26","27","28","29","30",
                "31","32","33","34","35","36","37","38","39","40",
                "41","42","43","44","45","46","47","48","49","50",
                "51","52","53","54","55","56","57","58","59"
        );

        hour.getItems().setAll(
                "00",
                "01","02","03","04","05","06","07","08","09","10",
                "11","12","13","14","15","16","17","18","19","20",
                "21","22","23"
        );

        min1.getItems().setAll(
                "00",
                "01","02","03","04","05","06","07","08","09","10",
                "11","12","13","14","15","16","17","18","19","20",
                "21","22","23","24","25","26","27","28","29","30",
                "31","32","33","34","35","36","37","38","39","40",
                "41","42","43","44","45","46","47","48","49","50",
                "51","52","53","54","55","56","57","58","59"
        );

        hour1.getItems().setAll(
                "00",
                "01","02","03","04","05","06","07","08","09","10",
                "11","12","13","14","15","16","17","18","19","20",
                "21","22","23"
        );

        this.id.setCellValueFactory(new PropertyValueFactory<Patient, Integer>("id"));
        this.lastName.setCellValueFactory(new PropertyValueFactory<Patient, String>("lastName"));
        this.firstName.setCellValueFactory(new PropertyValueFactory<Patient, String>("firstName"));
        this.phone_number.setCellValueFactory(new PropertyValueFactory<Patient, String>("phone_number"));
        this.date.setCellValueFactory(new PropertyValueFactory<Appointment, String>("appointment_date"));
        this.time.setCellValueFactory(new PropertyValueFactory<Appointment, String>("appointment_time"));
        this.reason.setCellValueFactory(new PropertyValueFactory<Appointment, String>("appointment_reason"));

        this.id1.setCellValueFactory(new PropertyValueFactory<Patient, Integer>("id"));
        this.lastName1.setCellValueFactory(new PropertyValueFactory<Patient, String>("lastName"));
        this.firstName1.setCellValueFactory(new PropertyValueFactory<Patient, String>("firstName"));
        this.phone_number1.setCellValueFactory(new PropertyValueFactory<Patient, String>("phone_number"));
        this.date1.setCellValueFactory(new PropertyValueFactory<Appointment, String>("appointment_date"));
        this.time1.setCellValueFactory(new PropertyValueFactory<Appointment, String>("appointment_time"));
        this.reason1.setCellValueFactory(new PropertyValueFactory<Appointment, String>("appointment_reason"));

        for (Patient elem : controller.getAll())
            list.add(elem);

        PatientTable.setItems(list);
    }

    @FXML
    public void updateTable()
    {
        PatientTable.refresh();
    }


    @FXML
    public void AddPatientButton()
    {
        try {
            Patient p = new Patient(fname.getText(), lname.getText(), Integer.parseInt(ID.getText()), phnumber.getText());

            controller.addPatient(p);
            list.add(p);
            PatientTable.setItems(list);
            errorLabel1.setText("");
            fname.clear();
            lname.clear();
            ID.clear();
            phnumber.clear();
            updateTable();

        }
        catch (NumberFormatException e)
        {
            errorLabel1.setText("Invalid Id...");
        }
        catch (RuntimeException e)
        {
            errorLabel1.setText(String.valueOf(e.getMessage()));
        }


    }

    @FXML
    public void AddAppointment()
    {

        try {
                String[] l = this.d.getValue().toString().split("-");
                System.out.println(ID.getText());
                controller.addAppointment(Integer.parseInt(this.Id.getText()), l[2] + "/" + l[1] + "/" + l[0], this.hour.getValue().toString() + ":" + this.min.getValue().toString(), this.r.getText());
                errorLabel2.setText("");
                Id.clear();
                r.clear();
                hour.valueProperty().set(null);
                min.valueProperty().set(null);
                d.valueProperty().set(null);
                updateTable();
        }
        catch(NullPointerException e)
        {
            errorLabel2.setText("Empty date and time...");
        }
        catch (NumberFormatException e)
        {
            errorLabel2.setText("Invalid Id...");
        }
        catch (RuntimeException e)
        {
            errorLabel2.setText(String.valueOf(e.getMessage()));
        }
    }

    @FXML
    public void CancelAppointment()
    {
        try {
            String[] l = this.d1.getValue().toString().split("-");
            controller.cancelAppointment( l[2] + "/" + l[1] + "/" + l[0], this.hour1.getValue().toString() + ":" + this.min1.getValue().toString() );
            errorLabel3.setText("");
            hour1.valueProperty().set(null);
            min1.valueProperty().set(null);
            d1.valueProperty().set(null);
            updateTable();
        }
        catch(NullPointerException e)
        {
            errorLabel3.setText("Empty date and time...");
        }
        catch (RuntimeException e)
        {
            errorLabel3.setText(String.valueOf(e.getMessage()));
        }
    }

    @FXML
    public void DeletePatient()
    {
        try {
            controller.deletePatient(Integer.parseInt(id_delete.getText()));
            errorLabel4.setText("");
            for (int i=0;i<list.size();i++)
                if(list.get(i).getId()==Integer.parseInt(id_delete.getText()))
                    list.remove(i);
            id_delete.clear();
            updateTable();
        }
        catch (NumberFormatException e)
        {
            errorLabel4.setText("Invalid Id...");
        }
        catch (RuntimeException e)
        {
            errorLabel4.setText(String.valueOf(e.getMessage()));
        }
    }


    @FXML
    public void CheckupsOrderedReportButton()
    {
        if(!list1.isEmpty())
            list1.clear();
        try{
            this.list1.addAll(controller.CheckupsOrderedStream());
            errorLabel5.setText("");
        }
        catch (RuntimeException e)
        {
            errorLabel5.setText(String.valueOf(e.getMessage()));
        }
        PatientTable1.setItems(list1);

    }

    @FXML
    public void ImportantMedicalProblemsReportButton()
    {
        if(!list1.isEmpty())
            list1.clear();
        try {
            this.list1.addAll(controller.ImportantMedicalProblemsStream());
            errorLabel6.setText("");
        }
        catch (RuntimeException e)
        {
            errorLabel6.setText(String.valueOf(e.getMessage()));

        }
        PatientTable1.setItems(list1);

    }

    @FXML
    public void AppointmentsOnDateReportButton()
    {
        if(!list1.isEmpty())
            list1.clear();

        try {
            String[] l = this.report_date.getValue().toString().split("-");
            this.list1.addAll(controller.AppointmentsOnDateReportStream(l[2] + "/" + l[1] + "/" + l[0]));
            errorLabel7.setText("");
        }
        catch(NullPointerException e)
        {
            errorLabel7.setText("Empty date and time...");
        }
        catch (RuntimeException e)
        {
            errorLabel7.setText(String.valueOf(e.getMessage()));
        }

        PatientTable1.setItems(list1);
    }


}
