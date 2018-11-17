package lab_5;

import java.awt.*;
import java.io.Serializable;
import java.util.Vector;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

public class Course implements Serializable {
    private String courseTitle;
    private Instructor instructor;
    private Textbook textbook;

    public Course(String courseTitle_, Instructor instructor_, Textbook textbook_){
        courseTitle=courseTitle_;
        instructor=new Instructor(instructor_.getFirstName(),instructor_.getLastName(), instructor_.getDepartment(), instructor_.getEmail());
        textbook= new Textbook(textbook_.getISBN(),textbook_.getAuthor(),textbook_.getTitle());
    }
    public void setCourseTitle(String courseTitle_){
        courseTitle = courseTitle_;
    }

    public String getCourseTitle(){

        return courseTitle;
    }

    public void keep(Vector<Course> v){
        try {
            FileOutputStream fs = new FileOutputStream("courses.out");
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(v);
            os.close();
            fs.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @Override
    public String toString(){
        return courseTitle;
    }

    @Override
    public boolean equals(Object obj){
        if(obj != null && this.getClass() == obj.getClass()){
            Course temp = (Course) obj;
            return temp.courseTitle.equals(courseTitle) && temp.instructor.equals(instructor) && temp.textbook.equals(textbook);
        }
        return false;
    }
}
