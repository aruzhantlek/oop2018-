package lab_5;

import java.io.*;
import java.util.*;

public class Driver {
    public static void main(String[] args){
        try {
            Scanner scan = new Scanner(System.in);
            Vector<Textbook> textbooks = new Vector<>();
            Vector<Instructor> instructors = new Vector<>();
            Vector<Course> courses = new Vector<>();
            Date date;

            menu : while(true){
                System.out.println("Welcome! In order to continue using our system you have to choose");
                System.out.println("either <user mode> or <admin mode>. Otherwise, we kindly ask you");
                System.out.println("to quit our website by writing down <q>.");
                System.out.println("----------------------------HELPER-----------------------------");
                System.out.println("Log in as Admin - write down <a>");
                System.out.println("Log in as User - write down <u>");

                String input = scan.next();

                switch(input){

                    case "a":
                        adminreg : while(true){
                            System.out.println("You've chosen \"Log in as Admin\". Please write down your admin name and password. In order to return back write <back>.");
                            String username = scan.next();

                            switch(username){
                                case "back":
                                    continue menu;
                                default:
                                    BufferedReader br = new BufferedReader(new FileReader("admin.txt"));
                                    BufferedWriter bw = new BufferedWriter(new FileWriter("admin.txt", true));

                                    String password = scan.next();
                                    int hashedPassword = Objects.hash(password);

                                    String adminName = br.readLine();
                                    String adminPass = br.readLine();

                                    br.close();

                                    if(username.equals(adminName) && hashedPassword == Integer.parseInt(adminPass)){
                                        trueadmin : while(true){
                                            System.out.println("[1] - Add course\n[2] - Add instructor\n[3] - Add textbook\n[4] - Go back");
                                            int input_adm = scan.nextInt();

                                            String courseTitle = null;
                                            Course temp_course = null;
                                            Instructor temp_instructor = null;
                                            Textbook temp_textbook = null;
                                            date = new Date();
                                            bw.write(String.format("%s admin logged in to a system", date));
                                            bw.newLine();

                                            switch(input_adm){
                                                case 1:
                                                    System.out.println("Write down course's title, instructor and textbook: ");
                                                    System.out.print("Course title: ");
                                                    courseTitle = scan.next();

                                                    System.out.println("Now you need to add instructor.\n[1] - Choose one from existing.\n[2] - Create a new instructor.");
                                                    int choice = scan.nextInt();
                                                    switch(choice){
                                                        case 1:
                                                            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("instructors.out"));
                                                            Iterator it;
                                                            int eXchoice, index = 0;
                                                            Vector<Instructor> temp_instructors = (Vector<Instructor>) ois.readObject();

                                                            it = temp_instructors.iterator();
                                                            while(it.hasNext()){
                                                                System.out.println(String.format("%d) %s", ++index, it.next()));
                                                            }
                                                            ois.close();

                                                            System.out.print("Enter index of existing instructor: ");
                                                            eXchoice = scan.nextInt();

                                                            temp_instructor = new Instructor(temp_instructors.get(eXchoice - 1).getFirstName(), temp_instructors.get(eXchoice - 1).getLastName(), temp_instructors.get(eXchoice - 1).getDepartment(), temp_instructors.get(eXchoice - 1).getEmail());

                                                            date = new Date();
                                                            bw.write(String.format("%s admin added new instructor \"%s\"", date, temp_instructor));
                                                            bw.newLine();

                                                            temp_instructor.keep(instructors);
                                                            System.out.println(String.format("\"%s\" instructor is chosen.", temp_instructor));
                                                            break;
                                                        case 2:
                                                            System.out.println("Write down instructor's first name, last name, department and email: ");
                                                            String firstName = scan.next();
                                                            String lastName = scan.next();
                                                            String department = scan.next();
                                                            String email = scan.next();

                                                            temp_instructor = new Instructor(firstName, lastName, department, email);
                                                            instructors.add(temp_instructor);

                                                            date = new Date();
                                                            bw.write(String.format("%s admin added new instructor \"%s\"", date, temp_instructor));
                                                            bw.newLine();

                                                            temp_instructor.keep(instructors);
                                                            break;
                                                        default:
                                                            System.out.println("Wrong input. You've returned back to trueadmin.");
                                                            continue trueadmin;
                                                    }

                                                    System.out.println("Now you need to add textbook.\n[1] - Choose one from existing.\n[2] - Create a new textbook.");
                                                    choice = scan.nextInt();
                                                    switch(choice){
                                                        case 1:
                                                            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("textbooks.out"));
                                                            Iterator it;
                                                            int eXchoice, index = 0;
                                                            Vector<Textbook> temp_textbooks = (Vector<Textbook>) ois.readObject();

                                                            it = temp_textbooks.iterator();
                                                            while(it.hasNext()){
                                                                System.out.println(String.format("%d) %s", ++index, it.next()));
                                                            }
                                                            ois.close();

                                                            System.out.print("Enter index of existing textbook: ");
                                                            eXchoice = scan.nextInt();

                                                            temp_textbook = new Textbook(temp_textbooks.get(eXchoice - 1).getTitle(), temp_textbooks.get(eXchoice - 1).getAuthor(), temp_textbooks.get(eXchoice - 1).getISBN());

                                                            date = new Date();
                                                            bw.write(String.format("%s admin added new nextbook \"%s\"", date, temp_textbook));
                                                            bw.newLine();

                                                            temp_textbook.save(textbooks);
                                                            System.out.println(String.format("\"%s\" textbook is chosen.", temp_textbook));
                                                            break;
                                                        case 2:
                                                            System.out.println("Write down textbook's isbn, author and title: ");
                                                            String ISBN = scan.next();
                                                            String author = scan.next();
                                                            String title = scan.next();

                                                            temp_textbook = new Textbook(ISBN, author, title);
                                                            textbooks.add(temp_textbook);

                                                            date = new Date();
                                                            bw.write(String.format("%s admin added new textbook \"%s\"", date, temp_textbook));
                                                            bw.newLine();

                                                            temp_textbook.save(textbooks);

                                                            break;
                                                        default:
                                                            System.out.println("Wrong input. You've returned back to trueadmin.");
                                                            continue trueadmin;
                                                    }

                                                    temp_course = new Course(courseTitle, temp_instructor, temp_textbook);
                                                    courses.add(temp_course);

                                                    bw.write(String.format("%s admin added new course \"%s\"", date, temp_course));
                                                    bw.newLine();

                                                    temp_course.keep(courses);
                                                    break;
                                                case 2:
                                                    System.out.println("Write down instructor's first name, last name, department and email: ");
                                                    String firstName = scan.next();
                                                    String lastName = scan.next();
                                                    String department = scan.next();
                                                    String email = scan.next();

                                                    temp_instructor = new Instructor(firstName, lastName, department, email);
                                                    instructors.add(temp_instructor);

                                                    date = new Date();
                                                    bw.write(String.format("%s admin added new instructor \"%s\"", date, temp_instructor));
                                                    bw.newLine();

                                                    temp_instructor.keep(instructors);
                                                    break;
                                                case 3:
                                                    System.out.println("Write down textbook's isbn, author and title: ");
                                                    String ISBN = scan.next();
                                                    String author = scan.next();
                                                    String title = scan.next();

                                                    temp_textbook = new Textbook(ISBN, author, title);
                                                    textbooks.add(temp_textbook);

                                                    date = new Date();
                                                    bw.write(String.format("%s admin added new textbook \"%s\"", date, temp_textbook));
                                                    bw.newLine();

                                                    temp_textbook.save(textbooks);
                                                    break;
                                                case 4:
                                                    bw.close();
                                                    continue adminreg;
                                                default:
                                                    System.out.println("Wrong input, try again.");
                                                    continue trueadmin;
                                            }
                                        }
                                    }
                                    else {
                                        System.out.println("Wrong username or password. Try again!");
                                        continue adminreg;
                                    }
                            }
                        }
                    case "u":
                        user : while(true){
                            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("courses.out"));;
                            Iterator it;
                            System.out.println("You've chosen \"Log in as User\".\nHere you can watch: \n[1] - courses \n[2] - instructors \n[3] - textbooks\nIn order to return back write <back>.");
                            String input_user = scan.next();

                            switch(input_user){
                                case "1":
                                    ois = new ObjectInputStream(new FileInputStream("courses.out"));
                                    Vector<Course> temp_courses = (Vector<Course>) ois.readObject();

                                    it = temp_courses.iterator();
                                    while(it.hasNext()){
                                        System.out.println(it.next());
                                    }
                                    break;
                                case "2":
                                    ois = new ObjectInputStream(new FileInputStream("instructors.out"));
                                    Vector<Instructor> temp_instructors = (Vector<Instructor>) ois.readObject();

                                    it = temp_instructors.iterator();
                                    while(it.hasNext()){
                                        System.out.println(it.next());
                                    }
                                    break;
                                case "3":
                                    ois = new ObjectInputStream(new FileInputStream("textbooks.out"));
                                    Vector<Textbook> temp_textbooks = (Vector<Textbook>) ois.readObject();

                                    it = temp_textbooks.iterator();
                                    while(it.hasNext()){
                                        System.out.println(it.next());
                                    }
                                    break;
                                case "back":
                                    ois.close();
                                    continue menu;
                            }
                        }
                    case "q":
                        System.exit(200);
                }

            }
        } catch (IOException ioe){
            ioe.printStackTrace();
        } catch (ClassNotFoundException cnf){
            cnf.printStackTrace();
        } catch (NullPointerException npe){
            npe.printStackTrace();
        }
    }
}