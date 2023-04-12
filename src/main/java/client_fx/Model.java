package client_fx;

import server.models.Course;
import server.models.RegistrationForm;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Model {
    public final static String LOAD_COMMAND = "CHARGER";
    public final static String REGISTER_COMMAND = "INSCRIRE";

    public ArrayList<Course> getCourses(String semester) throws IOException, ClassNotFoundException {
        Socket socket = new Socket("127.0.0.1", 1337);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(LOAD_COMMAND+" "+semester);

        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        return (ArrayList<Course>) ois.readObject();
    }

    public String sendRegistration(String prenom, String nom, String email, String matricule, Course course) throws IOException, ClassNotFoundException {
        Socket socket = new Socket("127.0.0.1", 1337);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(REGISTER_COMMAND);
        oos.writeObject(new RegistrationForm(prenom, nom, email, matricule, course));

        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        String message = (String) ois.readObject();
        socket.close();

        return message;
    }
}
