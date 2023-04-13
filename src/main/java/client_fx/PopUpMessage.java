package client_fx;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * La classe PopUpMessage représente la fenêtre qui affichera l'erreur ou la confirmation dans l'inscription
 * effectuée par l'utilisateur.
 */
public class PopUpMessage {
    /**
     * La méthode PopUpMessage est le constructeur qui instancie la nouvelle fenêtre pour afficher le message,
     * qui sera soit un message de succès, soit un message d'erreur, dépendamment du booléen success.
     * @param success le booléen qui indique si le message sera un succès ou une erreur
     * @param message le message à afficher sur la nouvelle fenêtre
     */
    public PopUpMessage(Boolean success, Label message) {
        Label label = new Label(success ? "Success!" : "Error");
        label.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        Label displayFace = new Label(success ? ":)" : ":(");
        displayFace.setStyle("-fx-font-size: 48px; -fx-background-color: " +
                (success ? "#90EE90;" : "#FFB6C1;") + "-fx-text-fill: #FFFFFF;");
        HBox topHBox = new HBox(150, label, displayFace);
        topHBox.setAlignment(Pos.CENTER);

        HBox bottomHBox = new HBox(message);
        bottomHBox.setAlignment(Pos.CENTER_LEFT);

        VBox root = new VBox(20, topHBox, bottomHBox);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #DDDDDD; -fx-padding: 20px;");
        VBox.setMargin(topHBox, new javafx.geometry.Insets(20, 0, 20, 0));

        Scene scene = new Scene(root, 400, 300);
        Stage popupStage = new Stage();
        popupStage.setScene(scene);
        popupStage.show();
    }
}
