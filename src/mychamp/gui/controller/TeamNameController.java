package mychamp.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mychamp.gui.model.ChampModel;

/**
 * FXML Controller class
 *
 * @author Thomas Meyer Hansen, Simon Juhl Birkedal, Stephan Fuhlendorff & Jacob
 * Enemark
 */
public class TeamNameController implements Initializable {

    private ChampModel model;
    private boolean isEdit;

    @FXML
    private Button btnCancel;
    @FXML
    private TextField txtName;
    @FXML
    private AnchorPane anchorPane;

    /**
     * The default constructor for the Team Name Controller.
     */
    public TeamNameController()
    {
        this.isEdit = false;
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        model = ChampModel.getInstance();
        if (model.getEditTeam() != null)
        {
            isEdit = true;
            txtName.setText(model.getEditTeam().getName());
        }
    }

    @FXML
    private void handleSave()
    {
        String name = txtName.getText();
        if (name.equals(""))
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Team name cannot be empty");
            alert.showAndWait();
        }
        else
        {
            if (isEdit)
            {
                model.editTeam(name);
            }
            else
            {
                model.addTeam(name);
            }
            closeWindow();
        }
    }

    @FXML
    private void handleCancel()
    {
        closeWindow();
    }

    /**
     * Closes the stage currently active.
     */
    private void closeWindow()
    {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

}
