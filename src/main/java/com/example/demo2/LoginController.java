package com.example.demo2;

import com.example.demo2.dao.DBconnection;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.dialogs.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import com.example.demo2.models.usersModel;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {
    public MFXPasswordField passwordid;
    public MFXTextField usernameid;
    @FXML
    private BorderPane screen1;

    @FXML
    private Label welcomeText;


    private void showErreur(String message ){
        MFXGenericDialog username_is_invalid = MFXDialogs.error().setContentText(message)
                .setOnClose(mouseEvent -> {
                    Node source = (Node) mouseEvent.getSource();
                    Stage stage = (Stage) source.getScene().getWindow();
                    stage.close();

                })
                .get();
        MFXStageDialog mfxStageDialog = MFXStageDialogBuilder.build(new MFXStageDialog(username_is_invalid)).get();
        mfxStageDialog.show();
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void onLogin(ActionEvent actionEvent) throws SQLException {
// teste de connection



        if(!usernameid.getText().matches("[a-zA-Z0-9]{4,}")){
            showErreur("Username invalid");
            return;

        }
        if(passwordid.getText().isEmpty()){
            showErreur("Password is empty");
        }

        Boolean connected = DBconnection.checkLogin(usernameid.getText(), passwordid.getText());



        if(connected){
            MFXGenericDialog username_is_invalid = MFXDialogs.info().setContentText("Connected Successfully")
                    .setOnClose(mouseEvent -> {
                        Node source = (Node) mouseEvent.getSource();
                        Stage stage = (Stage) source.getScene().getWindow();
                        stage.close();

                    })
                    .get();
            MFXStageDialog mfxStageDialog = MFXStageDialogBuilder.build(new MFXStageDialog(username_is_invalid)).get();
            mfxStageDialog.show();

            Stage window = (Stage) Window.getWindows().get(0);
            window.close();
        }



    }

    public void signUp(ActionEvent actionEvent) {
        usersModel usersmodel= new usersModel();

        usersmodel.setUsernameid(usernameid.getText());
        usersmodel.setPasswordid(passwordid.getText());


        //System.out.println(usersmodel.getUsernameid() + " " + usersmodel.getPasswordid());

        DBconnection.signUp(usersmodel);

    }

    public void goSign(MouseEvent mouseEvent) throws IOException {

        AnchorPane pane = FXMLLoader.load(getClass().getResource("system.fxml"));
        ((Stage)usernameid.getScene().getWindow()).setScene(new Scene(pane));

    }
}