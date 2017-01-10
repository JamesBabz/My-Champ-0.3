package mychamp.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mychamp.be.Team;
import mychamp.bll.TeamManager;
import mychamp.gui.model.ChampModel;

/**
 * FXML Controller class
 *
 * @author Thomas Meyer Hansen, Simon Juhl Birkedal, Stephan Fuhlendorff & Jacob
 * Enemark
 */
public class TeamManagerController implements Initializable {

    private final static int MIN_TEAMS = 12;
    private final static int MAX_TEAMS = 16;
    private final ChampModel model;
    private final TeamManager teamManager;
    private int selectedTeamIndex;

    @FXML
    private ListView listTeams;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnRemove;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnStart;
    @FXML
    private AnchorPane anchorPane;

    /**
     * The default constructor for this controller class.
     */
    public TeamManagerController()
    {
        model = ChampModel.getInstance();
        observableListListener(model.getTeamNames());
        teamManager = new TeamManager();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

        btnEdit.setDisable(true);
        btnRemove.setDisable(true);
        btnStart.setDisable(true);
        listTeams.setItems(model.getTeamNames());
        try
        {
            loadData();
        }
        catch (IOException | ClassNotFoundException ex)
        {
            System.out.println(ex);
        }
        model.getTeamNames();
        updateValidInteractions();
    }

    private void loadData() throws IOException, ClassNotFoundException
    {
        for (Team team : teamManager.loadTeamData())
        {
            model.loadTeam(team);
        }
      model.setTeamNames();
        
    }

    @FXML
    private void handleAddTeam()
    {
        try
        {
            model.openNewView(anchorPane, "TeamNameView", "New team");
        }
        catch (IOException ex)
        {
            Logger.getLogger(TeamManagerController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void handleEditTeam()
    {
        model.setEditTeam(selectedTeamIndex);
        try
        {
            model.openNewView(anchorPane, "TeamNameView", "Edit team");
        }
        catch (IOException ex)
        {
            Logger.getLogger(TeamManagerController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void handleRemoveTeam()
    {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Remove team");
        alert.setHeaderText("Do you want to remove this team?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
        {
            model.removeTeam(selectedTeamIndex);
        }
        else
        {
            // ... user chose CANCEL or closed the dialog
        }

    }

    @FXML
    private void macros(KeyEvent key) throws IOException
    {
        if (key.isControlDown())
        {
            if (KeyCode.N == key.getCode() && !btnAdd.isDisable())
            {
                handleAddTeam();
            }

            if (KeyCode.E == key.getCode() && !btnEdit.isDisable())
            {
                handleEditTeam();
            }
        }
        if (KeyCode.DELETE == key.getCode() && !btnRemove.isDisable())
        {
            handleRemoveTeam();
        }
    }

    @FXML
    private void handleStart()
    {
        try
        {
            teamManager.saveTeamData();
        }
        catch (IOException ex)
        {
            Logger.getLogger(TeamManagerController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Stage primaryStage = (Stage) listTeams.getScene().getWindow();
        primaryStage.close();

        try
        {
            model.openNewView(anchorPane, "GroupView", "");
        }
        catch (IOException ex)
        {
            Logger.getLogger(TeamManagerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Listens to changes in a given observable list.
     *
     * @param list The list to listen for changes in.
     */
    private void observableListListener(ObservableList list)
    {
        list.addListener(new ListChangeListener() {
            @Override
            public void onChanged(ListChangeListener.Change change)
            {
                int amount = list.size();
                if (amount >= MIN_TEAMS)
                {
                    btnStart.setDisable(false);
                }
                if (amount == MAX_TEAMS)
                {
                    btnAdd.setDisable(true);
                }
                else if (amount < MAX_TEAMS)
                {
                    btnAdd.setDisable(false);
                }
            }
        });
    }

    /**
     * Updates which controls can be interacted with.
     */
    private void updateValidInteractions()
    {
        listTeams.getSelectionModel().selectedItemProperty().addListener((selected, oldValue, newValue)
                -> 
                {
                    if (selected.getValue() == null)
                    {
                        btnEdit.setDisable(true);
                        btnRemove.setDisable(true);
                    }
                    else
                    {
                        selectedTeamIndex = listTeams.getSelectionModel().getSelectedIndex();
                        btnEdit.setDisable(false);
                        btnRemove.setDisable(false);
                    }
        });
    }
    
    @FXML
    private void handleResume()
    {
        
    }
}
