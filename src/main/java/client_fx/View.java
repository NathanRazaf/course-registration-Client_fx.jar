package client_fx;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import server.models.Course;

public class View extends HBox {

    private final TableView<Course> coursesTable;

    private final TableColumn<Course, String> code;

    private final TableColumn<Course, String> cours;
    private final ComboBox<String> semesterComboBox;
    private final TextField firstNameTextField;
    private final TextField nameTextField;
    private final TextField emailTextField;
    private final TextField matriculeTextField;
    private final Button sendButton;
    private final Button loadButton;

    public View() {
        // Set up left side of the window
        Label coursesTitleLabel = new Label("Liste des cours");
        coursesTitleLabel.setStyle("-fx-font-size: 20;");
        coursesTable = new TableView<>();

        code = new TableColumn<>("Code");

        cours = new TableColumn<>("Cours");

        coursesTable.getColumns().addAll(code, cours);
        coursesTable.setPrefWidth(300);
        coursesTable.setPrefHeight(200);
        coursesTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        semesterComboBox = new ComboBox<>();
        semesterComboBox.getItems().addAll("Automne", "Ete", "Hiver");
        semesterComboBox.getSelectionModel().selectFirst();

        loadButton = new Button("Charger");
        VBox leftVBox = new VBox(10, coursesTitleLabel, coursesTable, semesterComboBox, loadButton);
        leftVBox.setAlignment(Pos.TOP_CENTER);
        leftVBox.setPadding(new Insets(20));

        // Set up right side of the window
        Label registrationTitleLabel = new Label("Formulaire d'inscription");
        registrationTitleLabel.setStyle("-fx-font-size: 20;");
        Label firstNameLabel = new Label("Prénom");
        firstNameTextField = new TextField();
        Label nameLabel = new Label("Nom");
        nameTextField = new TextField();
        Label emailLabel = new Label("Email");
        emailTextField = new TextField();
        Label matriculeLabel = new Label("Matricule");
        matriculeTextField = new TextField();
        sendButton = new Button("Envoyer");

        // Create GridPane for labels and text fields
        GridPane formGrid = new GridPane();
        formGrid.setAlignment(Pos.TOP_CENTER);
        formGrid.setVgap(10);
        formGrid.setHgap(10);
        formGrid.setPadding(new Insets(20));
        formGrid.add(firstNameLabel, 0, 0);
        formGrid.add(firstNameTextField, 1, 0);
        formGrid.add(nameLabel, 0, 1);
        formGrid.add(nameTextField, 1, 1);
        formGrid.add(emailLabel, 0, 2);
        formGrid.add(emailTextField, 1, 2);
        formGrid.add(matriculeLabel, 0, 3);
        formGrid.add(matriculeTextField, 1, 3);

        VBox rightVBox = new VBox(10, registrationTitleLabel, formGrid, sendButton); // Include GridPane for labels and text fields
        rightVBox.setAlignment(Pos.TOP_CENTER);
        rightVBox.setPadding(new Insets(20));

        // Set up main layout
        this.getChildren().addAll(leftVBox, rightVBox);
        this.setSpacing(20);
        this.setAlignment(Pos.TOP_CENTER);

    }

    public TableView<Course> getCoursesTable() {
        return coursesTable;
    }

    public TableColumn<Course, String> getCode() {
        return code;
    }

    public TableColumn<Course, String> getCours() {
        return cours;
    }

    public ComboBox<String> getSemesterComboBox() {
        return semesterComboBox;
    }

    public TextField getFirstNameTextField() {
        return firstNameTextField;
    }

    public TextField getNameTextField() {
        return nameTextField;
    }

    public TextField getEmailTextField() {
        return emailTextField;
    }

    public TextField getMatriculeTextField() {
        return matriculeTextField;
    }

    public Button getLoadButton() {
        return loadButton;
    }

    public Button getSendButton() {
        return sendButton;
    }
}


