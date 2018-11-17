package lab5_;

import java.io.*;
import java.util.Vector;
import java.lang.String;



public class test1 {
    public static void main(String[] args){
        Vector<Student> students = new Vector<Student>();

        try {
            BufferedReader br = new BufferedReader(new FileReader("grades.txt"));
            String line = br.readLine();

            while(line != null){
                String[] str = line.split(" ");
                students.add(new Student(str[0], str[1], Integer.parseInt(str[2])));

                line = br.readLine();
            }
            br.close();

        } catch (FileNotFoundException fe){
            fe.printStackTrace();
        } catch (IOException ioe){
            ioe.printStackTrace();
        }

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("grades.txt"));
            int index = 0;
            for(Student s : students){
                bw.write(String.format("%d. %s", ++index, s.toString()));
                bw.newLine();
            }
            bw.close();

        } catch (IOException ioe){
            ioe.printStackTrace();
        }

    }
}
