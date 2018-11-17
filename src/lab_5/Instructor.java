package lab_5;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Vector;

public class Instructor implements Serializable {
    private String firstName;
    private String lastName;
    private String department;
    private String email;

    public Instructor(String firstName_, String lastName_, String department_, String email_){
        firstName = firstName_;
        lastName = lastName_;
        department = department_;
        email = email_;
    }

    public void setFirstName(String firstName_){
        firstName = firstName_;
    }

    public String getFirstName(){
        return firstName;
    }

    public void setLastName(String lastName_){
        lastName = lastName_;
    }

    public String getLastName(){
        return lastName;
    }

    public void setDepartment(String department_){
        department = department_;
    }

    public String getDepartment(){
        return department;
    }

    public void setEmail(String email_){
        email = email_;
    }

    public String getEmail(){
        return email;
    }

    public void keep(Vector<Instructor> v){
        try {
            FileOutputStream f = new FileOutputStream("instructors.out");
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(v);
            o.close();
            f.close();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @Override
    public String toString(){
        return firstName + " " + lastName;
    }


    @Override
    public boolean equals(Object obj){
        if(obj != null && this.getClass() == obj.getClass()){
            Instructor temp = (Instructor) obj;
            return temp.firstName.equals(firstName) && temp.lastName.equals(lastName);
        }
        return false;
    }
}