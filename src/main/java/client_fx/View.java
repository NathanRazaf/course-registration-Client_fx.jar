package client_fx;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import server.models.Course;

/**
 * La classe View représente la partie graphique de l'application.
 */
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

    /**
     * La méthode View est le constructeur qui instancie la partie graphique de l'application lorsqu'on l'appelle.
     */
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
        HBox buttons = new HBox(10, semesterComboBox, loadButton);
        buttons.setAlignment(Pos.TOP_CENTER);
        buttons.setSpacing(100);
        buttons.setPadding(new Insets(10));

        VBox leftVBox = new VBox(10, coursesTitleLabel, coursesTable, buttons);
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
        // Set background color
        this.setStyle("-fx-background-color: #EFEBCE;");

        // Add separator between left VBox and right VBox
        Separator separator = new Separator(Orientation.VERTICAL);
        this.getChildren().addAll(leftVBox, separator, rightVBox);
        this.setSpacing(20);
        this.setAlignment(Pos.TOP_CENTER);

        // Set tool tips for text fields
        Tooltip firstNameTip = new Tooltip("""
                Un prénom doit :
                - commencer par une majuscule
                - avoir aucun espace au début et à la fin
                - être composé uniquement de lettres""");
        Tooltip nameTip = new Tooltip("""
                Un nom doit :
                - commencer par une majuscule
                - avoir aucun espace au début et à la fin
                - être composé uniquement de lettres""");
        Tooltip emailTip = new Tooltip("""
                Un e-mail doit :
                - avoir aucun espace
                - avoir aucun caractère spécial, tel que # ! % $ ‘ & + * – / = ? ^ _`. { | } ~""");
        Tooltip matriculeTip = new Tooltip("Un matricule doit être composé de 8 chiffres");
        firstNameTextField.setTooltip(firstNameTip);
        nameTextField.setTooltip(nameTip);
        emailTextField.setTooltip(emailTip);
        matriculeTextField.setTooltip(matriculeTip);

    }

    /**
     * La méthode getCoursesTable retourne le tableau coursesTable contenant les cours disponibles.
     * @return TableView&lt;Course&gt; coursesTable
     */
    public TableView<Course> getCoursesTable() {
        return coursesTable;
    }

    /**
     * La méthode getCode retourne la colonne code qui contient les codes des cours dans le tableau coursesTable.
     * @return TableColumn&lt;Course, String&GT; code
     */
    public TableColumn<Course, String> getCode() {
        return code;
    }

    /**
     * La méthode getCours retourne la colonne cours qui contient les noms des cours dans le tableau coursesTable.
     * @return TableColumn&lt;Course, String&gt; cours
     */
    public TableColumn<Course, String> getCours() {
        return cours;
    }

    /**
     * La méthode getSemesterComboBox retourne la boîte de sélection semesterComboBox qui contient les différents choix
     * disponibles pour la session à charger.
     * @return ComboBox&lt;String&gt; semesterComboBox
     */
    public ComboBox<String> getSemesterComboBox() {
        return semesterComboBox;
    }

    /**
     * La méthode getFirstNameTextField retourne le champ de texte firstNameTextField qui va contenir le prénom
     * entré par l'utilisateur.
     * @return TextField firstNameTextField
     */
    public TextField getFirstNameTextField() {
        return firstNameTextField;
    }

    /**
     * La méthode getNameTextField retourne le champ de texte nameTextField qui va contenir le nom
     * entré par l'utilisateur.
     * @return TextField nameTextField
     */
    public TextField getNameTextField() {
        return nameTextField;
    }

    /**
     * La méthode getEmailTextField retourne le champ de texte emailTextField qui contiendra l'email
     * entré par l'utilisateur.
     * @return TextField emailTextField
     */
    public TextField getEmailTextField() {
        return emailTextField;
    }

    /**
     * La méthode getMatriculeTextField retourne le champ de texte matriculeTextField qui contiendra le matricule
     * entré par l'utilisateur.
     * @return TextField matriculeTextField
     */
    public TextField getMatriculeTextField() {
        return matriculeTextField;
    }

    /**
     * La méthode getLoadButton retourne le bouton loadButton sur lequel l'utilisateur clique pour
     * charger le tableau de cours.
     * @return Button loadButton
     */
    public Button getLoadButton() {
        return loadButton;
    }

    /**
     * La méthode getSendButton retourne le bouton sendButton sur lequel l'utilisateur clique pour
     * envoyer son formulaire d'inscription au serveur.
     * @return Button sendButton
     */
    public Button getSendButton() {
        return sendButton;
    }

}


