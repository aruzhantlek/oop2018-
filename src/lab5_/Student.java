package lab5_;

public class Student implements Comparable{
    private String firstname;
    private String lastname;
    private int grades;
    private static int bestofGrade;
    private static int sumofGrade;


    public Student(){
        firstname="null";
        lastname="null";
        grades=0;
        bestofGrade=0;
        sumofGrade=0;
    }
    public Student(String firstname_,String lastname_,int grades_){
        firstname=firstname_;
        lastname=lastname_;
        grades=grades_;
        sumofGrade+=grades_;
        if(grades_>bestofGrade){
            bestofGrade=grades_;
        }
    }

    public void setFirstname(String firstname_){
        firstname=firstname_;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setLastname(String lastname_) {
        lastname = lastname_;
    }
    public String getLastname(){
        return lastname;
    }
    public void setGrades(int grades_){
        grades=grades_;
    }

    public int getGrades() {
        return grades;
    }

    public static int getSumofGrade() {
        return sumofGrade;
    }

    public static int getBestofGrade() {
        return bestofGrade;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj!=null && this.getClass()==obj.getClass()){
            Student studentObj= (Student)obj;
            return(this.firstname.equals(studentObj.firstname) && this.lastname.equals(studentObj.lastname) && this.grades==studentObj.grades);
        }
        return false;
    }

    @Override
    public int compareTo(Object o) {
        Student studentObj=(Student)o;
        if(grades>studentObj.grades) return 1;
        else if (grades<studentObj.grades) return -1;
        return 0;
    }

    @Override
    public String toString() {
        Grades current=null;
        if(bestofGrade-10<=grades){
            current=Grades.A;
        }
        else if(bestofGrade-20<=grades){
            current=Grades.B;
        }
        else if(bestofGrade-30<=grades){
            current=Grades.C;
        }
        else if(bestofGrade-40<=grades){
            current=Grades.D;
        }
        else if(bestofGrade-50<=grades){
            current=Grades.F;
        }

        return String.format("%s %s - \"%s\"", firstname, lastname, current);
    }
}
