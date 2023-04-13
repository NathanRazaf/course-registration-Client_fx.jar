package client_fx;

import server.models.Course;
import server.models.RegistrationForm;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 * La classe Model est la partie modèle du MVC. Model contient les fonctions nécessaires pour implémenter les
 * commandes "charger" et "inscrire" de l'application.
 */
public class Model {
    /**
     * La constante LOAD_COMMAND représente la commande "charger".
     */
    public final static String LOAD_COMMAND = "CHARGER";
    /**
     * La constante REGISTER_COMMAND représente la commande "inscrire".
     */
    public final static String REGISTER_COMMAND = "INSCRIRE";

    /**
     * La fonction getCourses envoie une requête au serveur pour obtenir la liste de cours d'une session choisie et la
     * retourne.
     * Le paramètre semester est un String qui représente la session choisie.
     * Un ArrayList&lt;Course&gt; est retournée et contient les cours de la session choisie.
     * La fonction gère les exceptions lorsqu'il y a une erreur lors de la lecture du stream, et lorsque la classe
     * Course n'existe pas.
     *
     * @param semester session choisie
     * @return liste de cours ArrayList&lt;Course&gt qui contient les cours pour la session choisie
     * @throws IOException si le stream ne peut pas être lu
     * @throws ClassNotFoundException si la classe Course n'existe pas
     */
    public ArrayList<Course> getCourses(String semester) throws IOException, ClassNotFoundException {
        Socket socket = new Socket("127.0.0.1", 1337);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(LOAD_COMMAND+" "+semester);

        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        return (ArrayList<Course>) ois.readObject();
    }

    /**
     * La fonction sendRegistration envoie une requête au serveur pour pouvoir ajouter une inscription au fichier
     * inscription.txt et affiche un message de succès ou d'échec.
     * Les paramètres prenom, nom, email et matricule sont des String qui représente respectivement le prénom, nom,
     * e-mail et matricule de l'utilisateur, et le paramètre course est de type Course et représente le cours choisi
     * par l'utilisateur.
     * La fonction retourne un message de succès ou d'échec.
     * La fonction gère les exceptions lorsqu'il y aune erreur lors de la lecture du stream, lorsque la classe
     * RegistrationForm ou Course n'existe pas.
     *
     * @param prenom prénom de l'utilisateur
     * @param nom nom de l'utilisateur
     * @param email e-mail de l'utilisateur
     * @param matricule matricule de l'utilisateur
     * @param course cours auquel l'utilisateur veut s'inscrire
     * @return message de succès ou d'échec de l'inscription
     * @throws IOException si le stream ne peut pas être lu
     * @throws ClassNotFoundException si la classe RegistrationForm ou Course n'existe pas
     */
    public String sendRegistration(String prenom, String nom, String email, String matricule, Course course) throws
            IOException, ClassNotFoundException {
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
