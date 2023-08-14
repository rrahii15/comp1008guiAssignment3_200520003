package com.example.assign3java;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class HelloController {

    @FXML
    private ColorPicker studentColorPicker;

    @FXML
    private TextField studentNameTextField;

    @FXML
    private Pane seatPane1, seatPane2, seatPane3, seatPane4, seatPane5, 
                 seatPane6, seatPane7, seatPane8, seatPane9;

    @FXML
    private Label statusLabel;

    @FXML
    private Text studentNameText1, studentNameText2, studentNameText3, 
                 studentNameText4, studentNameText5, studentNameText6, 
                 studentNameText7, studentNameText8, studentNameText9;

    private Pane[] colorPanes;
    private Text[] studentNameTexts;
    private HashSet<Student> registeredStudents = new HashSet<>();
    private Random random = new Random();

    private class Student {
        String name;
        String color;

        Student(String name, String color) {
            this.name = name;
            this.color = color;
        }
    }

    @FXML
    public void initialize() {
        colorPanes = new Pane[]{seatPane1, seatPane2, seatPane3, seatPane4, 
                                seatPane5, seatPane6, seatPane7, seatPane8, seatPane9};
        studentNameTexts = new Text[]{studentNameText1, studentNameText2, studentNameText3, 
                                      studentNameText4, studentNameText5, studentNameText6, 
                                      studentNameText7, studentNameText8, studentNameText9};
    }

    @FXML
    void handleAddStudent(ActionEvent event) {
        String enteredName = studentNameTextField.getText().trim().toLowerCase();
        Color chosenColor = studentColorPicker.getValue();

        if (enteredName.contains(" ")) {
            displayErrorMessage("SPACES ARE NOT ALLOWED BETWEEN THE NAMES");
            return;
        }

        if (enteredName.length() < 2) {
            displayErrorMessage("ENTERED NAME IS TOO SHORT.");
            return;
        }

        if (chosenColor.equals(Color.WHITE)) {
            displayErrorMessage("PLEASE SELECT ANOTHER COLOR AS WHITE IS ALREADY TAKEN.");
            return;
        }

        for (Student s : registeredStudents) {
            if (s.name.equals(enteredName)) {
                displayErrorMessage("STUDENT NAME ALREADY EXISTS.");
                return;
            }

            if (s.color.equals(chosenColor.toString())) {
                displayErrorMessage("THIS COLOUR IS ALREADY TAKEN.");
                return;
            }
        }

        int emptySpace = -1;
        List<Integer> availableSpaces = new ArrayList<>();

        for (int i = 0; i < studentNameTexts.length; i++) {
            if (studentNameTexts[i].getText().isEmpty()) {
                availableSpaces.add(i);
            }
        }

        if (!availableSpaces.isEmpty()) {
            emptySpace = availableSpaces.get(random.nextInt(availableSpaces.size()));
        }

        if (emptySpace != -1) {
            colorPanes[emptySpace].setStyle("-fx-background-color: #" + chosenColor.toString().substring(2));
            studentNameTexts[emptySpace].setText(enteredName);
            studentNameTexts[emptySpace].setVisible(true);

            registeredStudents.add(new Student(enteredName, chosenColor.toString()));
            displaySuccessMessage("Added successfully!");
        } else {
            displayErrorMessage("Seats are full.");
        }
    }

    private void displayErrorMessage(String message) {
        statusLabel.setText(message);
        statusLabel.setStyle("-fx-text-fill: red;");
    }

    private void displaySuccessMessage(String message) {
        statusLabel.setText(message);
        statusLabel.setStyle("-fx-text-fill: green;");
    }
}
