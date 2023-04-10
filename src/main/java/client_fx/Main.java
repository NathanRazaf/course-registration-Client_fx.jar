package client_fx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Model model = new Model();
        View mainLayout = new View();
        Controller controller = new Controller(model, mainLayout);
        Scene scene = new Scene(mainLayout, 800, 400);
        stage.setTitle("Inscription UdeM");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
