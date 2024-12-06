package com.logindemo;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class UserService {
    public static void changeScence(ActionEvent event, String fxmlFile, String title, String username, String note) {
        Parent root = null;
        if (username != null && note != null) {
            try {
                FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
                root = loader.load();

                LoggedInController loggedInController = loader.getController();
                loggedInController.setUserInformation(username, note);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                root = FXMLLoader.load(DBUtils.class.getResource(fxmlFile));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public static void logInUser(ActionEvent event, String username, String password) {


        UserDaoImpl userDao = new UserDaoImpl();

        User user = userDao.getUserByUsername(username);

        if (user == null) {
            System.out.println("User not found in the database!");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Provided credentials are in correct!");
            alert.show();
        } else {
            String retrievePassword = user.getPassword();
            String retrieveNote = user.getEmail();
            if (retrievePassword != null && retrievePassword.equals(password)) {
                changeScence(event, "logged-In.fxml", "Welcome", username, retrieveNote);
            } else {
                System.out.println("password did not match");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided credentials are in correct!");
                alert.show();
            }
        }

            /*connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pham_database", "root", "");
            preparedStatement = connection.prepareStatement("SELECT password, note FROM users WHERE username = ?");
            preparedStatement.setString(1,username);
            resultSet = preparedStatement.executeQuery();

            if(!resultSet.isBeforeFirst()) {
                System.out.println("User not found in the database!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided credentials are in correct!");
                alert.show();
            } else {
                while (resultSet.next()) {
                    String retrievePassword = resultSet.getString("password");
                    String retrieveNote = resultSet.getString("note");
                    if(retrievePassword.equals(password)){
                        changeScence(event,"logged-In.fxml","Welcome", username, retrieveNote);
                    } else {
                        System.out.println("password did not match");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Provided credentials are in correct!");
                        alert.show();
                    }
                }

            }*/

    }
}
