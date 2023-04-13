package client_fx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * La classe Main représente le lanceur principal de l'application.
 */
public class Main extends Application {
    @Override
    public void start(Stage stage) {
        Model model = new Model();
        View mainLayout = new View();
        Controller controller = new Controller(model, mainLayout);
        Scene scene = new Scene(mainLayout, 800, 400);
        stage.setTitle("Inscription UdeM");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * La méthode launch dans le main() sert à lancer l'application graphique, avec pour paramètre args.
     * @param args est le paramètre du main
     */
    public static void main(String[] args) {
        launch(args);
    }
}
