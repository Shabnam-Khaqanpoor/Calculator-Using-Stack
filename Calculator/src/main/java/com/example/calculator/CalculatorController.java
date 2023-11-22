package com.example.calculator;

import com.example.calculator.mathematicalSection.CalculateByStack;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class CalculatorController {

    @FXML
    private Label screen;

    @FXML
    void ACClicked(MouseEvent event) {
        screen.setText("");
    }

    @FXML
    void close(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    void delClicked(MouseEvent event) {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < screen.getText().length() - 1; i++) {
            text.append(screen.getText().charAt(i));
        }
        screen.setText(String.valueOf(text));
    }


    @FXML
    void divClicked(MouseEvent event) {
        screen.setText(screen.getText() + "/");
    }


    @FXML
    void eClicked(MouseEvent event) {
        screen.setText(screen.getText() + "e");
    }

    @FXML
    void eightClicked(MouseEvent event) {
        screen.setText(screen.getText() + "8");
    }


    @FXML
    void equalClicked(MouseEvent event) {
        try {
            CalculateByStack calculateByStack = new CalculateByStack(screen.getText());
            double result = calculateByStack.calculate(calculateByStack.stack);
            if (result % 1 == 0) screen.setText(String.valueOf((long) result));     //remove 0 after point
            else screen.setText(String.valueOf(result));
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Try again!");
            alert.show();
        }
    }


    @FXML
    void factClicked(MouseEvent event) {
        screen.setText(screen.getText() + "!");
    }

    @FXML
    void fiveClicked(MouseEvent event) {
        screen.setText(screen.getText() + "5");
    }


    @FXML
    void fourClicked(MouseEvent event) {
        screen.setText(screen.getText() + "4");
    }


    @FXML
    void mulClicked(MouseEvent event) {
        screen.setText(screen.getText() + " x ");
    }


    @FXML
    void nineClicked(MouseEvent event) {
        screen.setText(screen.getText() + "9");
    }


    @FXML
    void oneClicked(MouseEvent event) {
        screen.setText(screen.getText() + "1");
    }


    @FXML
    void piClicked(MouseEvent event) {
        screen.setText(screen.getText() + "pi");
    }

    @FXML
    void plusClicked(MouseEvent event) {
        screen.setText(screen.getText() + "+");
    }


    @FXML
    void pointClicked(MouseEvent event) {
        screen.setText(screen.getText() + ".");
    }


    @FXML
    void powClicked(MouseEvent event) {
        screen.setText(screen.getText() + "^");
    }

    @FXML
    void sevenClicked(MouseEvent event) {
        screen.setText(screen.getText() + "7");
    }


    @FXML
    void sixClicked(MouseEvent event) {
        screen.setText(screen.getText() + "6");
    }


    @FXML
    void subClicked(MouseEvent event) {
        screen.setText(screen.getText() + "-");
    }


    @FXML
    void threeClicked(MouseEvent event) {
        screen.setText(screen.getText() + "3");
    }


    @FXML
    void twoClicked(MouseEvent event) {
        screen.setText(screen.getText() + "2");
    }


    @FXML
    void zeroClicked(MouseEvent event) {
        screen.setText(screen.getText() + "0");
    }

    @FXML
    void closePClicked(MouseEvent event) {
        screen.setText(screen.getText() + ")");
    }

    @FXML
    void openPClicked(MouseEvent event) {
        screen.setText(screen.getText() + "(");
    }


}
