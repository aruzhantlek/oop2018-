package lab_5;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Vector;

public class Textbook implements Serializable {
    private String ISBN;
    private String author;
    private String title;

    public Textbook(String ISBN_, String author_, String title_){
        ISBN = ISBN_;
        author = author_;
        title = title_;
    }

    public void setISBN(String ISBN_){
        ISBN = ISBN_;
    }

    public String getISBN(){
        return ISBN;
    }

    public void setAuthor(String author_){
        author = author_;
    }

    public String getAuthor(){
        return author;
    }

    public void setTitle(String title_){
        title = title_;
    }

    public String getTitle(){
        return title;
    }

    public void save(Vector<Textbook> v){
        try {
            FileOutputStream f = new FileOutputStream("textbooks.out");
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
        return title;
    }

    @Override
    public boolean equals(Object obj){
        if(obj != null && this.getClass() == obj.getClass()){
            Textbook temp = (Textbook) obj;
            return (temp.ISBN.equals(ISBN) && temp.title.equals(title));
        }
        return false;
    }



}