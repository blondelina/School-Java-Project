package model;

import model.Appointment;

import java.io.Serializable;


public class Patient implements Serializable{
    private String firstName;
    private String lastName;
    private int id;
    Appointment appointment= new Appointment();
    private String phone_number;


    public Patient(String firstName, String lastName, int id,String ph)
    {
        if(firstName.isBlank() || lastName.isBlank() || Integer.toString(id).isBlank())
            throw new RuntimeException("Empty fields...");


        this.firstName=firstName;
        this.lastName=lastName;
        this.id=id;
        this.phone_number=ph;
        this.setAppointment_date("");
        this.setAppointment_time("");
        this.setAppointment_reason("");

    }

    public Patient(String firstName, String lastName, int id, String ph, String ap_date, String ap_time,String reason)
    {

        if(firstName.isBlank() || lastName.isBlank() || Integer.toString(id).isBlank())
            throw new RuntimeException("Empty fields...");
        this.firstName=firstName;
        this.lastName=lastName;
        this.id=id;
        this.appointment.setAppointment_time(ap_time);
        this.appointment.setAppointment_date(ap_date);
        this.phone_number=ph;
        this.appointment.setAppointmentReason(reason);

    }



    public String getAppointment_time() {
        return appointment.getAppointment_time();
    }

    public void setAppointment_time(String appointment_time) {
        this.appointment.setAppointment_time(appointment_time);
    }

    public String getAppointment_date() {
        return appointment.getAppointment_date();
    }

    public void setAppointment_date(String appointment_date) {
        this.appointment.setAppointment_date(appointment_date);
    }

    public String getAppointment_reason() {
        return appointment.getAppointmentReason();
    }

    public void setAppointment_reason(String appointment_reason) {
        this.appointment.setAppointmentReason(appointment_reason);
    }

    public void setFirstName(String firstName)
    {
        this.firstName=firstName;
    }
    public void setLastName(String lastName)
    {
        this.lastName=lastName;
    }
    public void setId(int id)
    {
        this.id=id;
    }
    public String getFirstName()
    {
        return this.firstName;
    }
    public String getLastName()
    {
        return this.lastName;
    }
    public int getId()
    {
        return this.id;
    }
    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }


    @Override
    public String toString()
    {
        String s;
        if(this.appointment.getAppointment_date()=="" && this.appointment.getAppointment_time()=="")
            s=this.getFirstName()+" "+this.getLastName()+" of id "+this.getId()+"\n";
        else
            s=this.getFirstName()+" "+this.getLastName()+" of id "+this.getId()+" appointment on "+
                    this.getAppointment_date()+" "+this.getAppointment_time()+" and problem: "+this.getAppointment_reason()+"\n";
        return s;
    }



}
