package lab5_;

import java.io.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Vector;

public class test2 {
    public static void main(String[]args){
        Vector<Student> students=new Vector<Student>();

        try{
            BufferedReader bf=new BufferedReader(new FileReader("grades.txt"));
            String line=bf.readLine();

            while(line!=null){
                String[] str=line.split(" ");
                students.add(new Student(str[0],str[1],Integer.parseInt(str[2])));

                line=bf.readLine();
            }
            bf.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try{
            BufferedWriter bw=new BufferedWriter(new FileWriter("scores.txt"));
            bw.newLine();
            bw.write(String.format("Average - %d" , (students.lastElement().getSumofGrade())/students.size()));
            bw.newLine();
            bw.write(String.format("Maximum - %d",  Collections.max(students).getGrades()));
            bw.newLine();
            bw.write(String.format("Minimum - %d",  Collections.min(students).getGrades()));
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
