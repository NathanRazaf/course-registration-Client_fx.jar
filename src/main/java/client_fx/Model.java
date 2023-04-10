package client_fx;

import server.models.Course;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Model {
    public final static String FALL_SEMESTER = "Automne";
    public final static String WINTER_SEMESTER = "Hiver";
    public final static String SUMMER_SEMESTER = "Ete";
    public final static String LOAD_COMMAND = "CHARGER";
    public final static String REGISTER_COMMAND = "INSCRIRE";

    public ArrayList<Course> getCourses(String semester) throws IOException, ClassNotFoundException {
        Socket socket = new Socket("127.0.0.1", 1337);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(LOAD_COMMAND+" "+semester);

        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        return (ArrayList<Course>) ois.readObject();
    }


}
