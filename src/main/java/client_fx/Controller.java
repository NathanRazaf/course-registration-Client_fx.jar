package client_fx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import server.models.Course;

import java.io.IOException;
import java.util.ArrayList;

public class Controller {
    private Model model;
    private View view;

    public Controller(Model m, View v) {
        this.model=m;
        this.view=v;

        this.view.getLoadButton().setOnAction(actionEvent -> {
            String semester = Controller.this.view.getSemesterComboBox().getValue();
            ArrayList<Course> courseList;

            try {
                courseList = Controller.this.model.getCourses(semester);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            ObservableList<Course> courses = FXCollections.observableArrayList(courseList);
            Controller.this.view.getCoursesTable().setItems(courses);

            Controller.this.view.getCode().setCellValueFactory(new PropertyValueFactory<>("code"));
            Controller.this.view.getCours().setCellValueFactory(new PropertyValueFactory<>("name"));
        });

        this.view.getSendButton().setOnAction(actionEvent -> {
            View thisView = Controller.this.view;
            boolean isAllCorrect = true;
            Label label = new Label();
            String message = "Un ou plusieurs champs ne sont pas conformes :";

            // Validate first name
            String firstName = thisView.getFirstNameTextField().getText();
            if (firstName.isBlank()) {
                thisView.getFirstNameTextField().setStyle("-fx-border-color: red;");
                isAllCorrect = false;
                message += "\n- Un prénom est requis!";
            } else if (firstName.matches(".*[0-9!@#$%&*()_+=|<>?{}/\\\\~].*") ||
                    firstName.startsWith(" ") || firstName.endsWith(" ") ||
                    !Character.isUpperCase(firstName.charAt(0))) {
                thisView.getFirstNameTextField().setStyle("-fx-border-color: red;");
                isAllCorrect = false;
                message += "\n- Le prénom n'est pas conforme!";
            } else {
                thisView.getFirstNameTextField().setStyle("-fx-border-color: none;");
            }

            // Validate last name
            String name = thisView.getNameTextField().getText();
            if (name.isBlank()) {
                thisView.getNameTextField().setStyle("-fx-border-color: red;");
                isAllCorrect = false;
                message += "\n- Un nom est requis!";
            } else if (name.matches(".*[0-9!@#$%&*()_+=|<>?{}/\\\\~].*") || name.startsWith(" ")
                    || name.endsWith(" ") || !Character.isUpperCase(name.charAt(0))) {
                thisView.getNameTextField().setStyle("-fx-border-color: red;");
                isAllCorrect = false;
                message += "\n- Le nom n'est pas conforme!";
            } else {
                thisView.getNameTextField().setStyle("-fx-border-color: none;");
            }

            // Validate email
            String email = thisView.getEmailTextField().getText();
            if (email.isBlank()) {
                thisView.getEmailTextField().setStyle("-fx-border-color: red;");
                isAllCorrect = false;
                message += "\n- Un e-mail est requis!";
            } else if (email.contains(" ") || !email.contains("@") || email.indexOf("@") == 0 ||
                    email.lastIndexOf(".") == email.indexOf("@")+1 ||
                    email.lastIndexOf(".") < email.indexOf("@") || email.lastIndexOf(".") == email.length() - 1
                    || email.matches(".*[!#$%&*()+=|<>?{}/\\\\~].*")) {
                thisView.getEmailTextField().setStyle("-fx-border-color: red;");
                isAllCorrect = false;
                message += "\n- L'e-mail n'est pas conforme!";
            } else {
                thisView.getEmailTextField().setStyle("-fx-border-color: none;");
            }

            // Validate student ID
            String matricule = thisView.getMatriculeTextField().getText();
            if (matricule.isBlank()) {
                thisView.getMatriculeTextField().setStyle("-fx-border-color: red;");
                isAllCorrect = false;
                message += "\n- Un matricule est requis!";
            } else if (matricule.matches(".*[a-zA-z!@#$%&*()_+=|<>?{}/\\\\~-].*") || matricule.contains(" ") ||
                    (!matricule.matches(".*[a-zA-z!@#$%&*()_+=|<>?{}/\\\\~-].*") && matricule.length() != 8)) {
                thisView.getMatriculeTextField().setStyle("-fx-border-color: red;");
                isAllCorrect = false;
                message += "\n- Le matricule n'est pas conforme!";
            } else {
                thisView.getMatriculeTextField().setStyle("-fx-border-color: none;");
            }

            // Validate course
            Course course = null;
            if (thisView.getCoursesTable().getSelectionModel().isEmpty()) {
                thisView.getCoursesTable().setStyle("-fx-border-color: red;");
                isAllCorrect = false;
                message += "\n- Veuillez sélectionner un cours!";
            } else {
                thisView.getCoursesTable().setStyle("-fx-border-color: none;");
                course = thisView.getCoursesTable().getSelectionModel().getSelectedItem();
            }

            // If all the fields are valid...
            if (isAllCorrect) {
                try {
                    // send registration form
                    String serverMessage = Controller.this.model.sendRegistration(firstName, name, email, matricule, course);

                    // if the registration is successful...
                    if (serverMessage.startsWith("Félicitation")) {
                        // reset text fields and course list
                        thisView.getFirstNameTextField().clear();
                        thisView.getFirstNameTextField().setStyle("-fx-border-color: none;");
                        thisView.getNameTextField().clear();
                        thisView.getNameTextField().setStyle("-fx-border-color: none;");
                        thisView.getEmailTextField().clear();
                        thisView.getEmailTextField().setStyle("-fx-border-color: none;");
                        thisView.getMatriculeTextField().clear();
                        thisView.getMatriculeTextField().setStyle("-fx-border-color: none;");
                        thisView.getCoursesTable().getSelectionModel().clearSelection();
                        thisView.getCoursesTable().setStyle("-fx-border-color: none;");

                        // display a pop-up message of success
                        label.setText(serverMessage);
                        PopUpMessage popUpMessage = new PopUpMessage(true, label);

                    // if the registration fails...
                    } else {
                        // display a pop-up message of error
                        label.setText(serverMessage);
                        PopUpMessage popUpMessage = new PopUpMessage(false, label);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }

            // if there are one or more fields are invalid, display a pop-message of error
            } else {
                label.setText(message);
                PopUpMessage popUpMessage = new PopUpMessage(false, label);
            }
        });
    }
}
