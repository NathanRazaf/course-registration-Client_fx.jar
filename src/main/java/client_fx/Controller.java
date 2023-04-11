package client_fx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
            String firstName = Controller.this.view.getFirstNameTextField().getText();
            String name = Controller.this.view.getNameTextField().getText();
            String email = Controller.this.view.getEmailTextField().getText();
            String matricule = Controller.this.view.getMatriculeTextField().getText();
            Course course = Controller.this.view.getCoursesTable().getSelectionModel().getSelectedItem();
            try {
                Controller.this.model.sendRegistration(firstName, name, email, matricule, course);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }
}
