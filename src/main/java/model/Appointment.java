package model;
import java.io.Serializable;
import java.time.LocalDate;


public class Appointment implements Serializable {

    private LocalDate appointment_date;
    private String appointment_time;
    private String appointment_reason;

    public Appointment() {

    }


    public LocalDate getAppointment_date() {
        return appointment_date;
    }

    public String getAppointment_time() {
        return appointment_time;
    }

    public void setAppointment_date(LocalDate appointment_date) {
        this.appointment_date = appointment_date;
    }

    public void setAppointment_time(String appointment_time) {
        this.appointment_time = appointment_time;
    }

    public String getAppointmentReason() {
        return appointment_reason;
    }

    public void setAppointmentReason(String appointmentReason) {
        this.appointment_reason = appointmentReason;
    }


}
