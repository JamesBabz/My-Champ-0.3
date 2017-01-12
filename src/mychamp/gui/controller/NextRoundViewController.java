package mychamp.gui.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mychamp.be.Group;
import mychamp.be.Team;
import mychamp.gui.model.ChampModel;

/**
 * FXML Controller class
 *
 * @author Thomas Meyer Hansen, Simon Juhl Birkedal, Stephan Fuhlendorff & Jacob
 * Enemark
 */
public class NextRoundViewController implements Initializable {

    Team homeTeam1;
    Team awayTeam1;
    Team homeTeam2;
    Team awayTeam2;

    ChampModel model;
    Group group;

    @FXML
    private Label lblRoundNumb;
    @FXML
    private Label lblHome1;
    @FXML
    private Label lblHome2;
    @FXML
    private Label lblAway1;
    @FXML
    private Label lblAway2;
    @FXML
    private TextField txtHome1;
    @FXML
    private TextField txtHome2;
    @FXML
    private TextField txtAway1;
    @FXML
    private TextField txtAway2;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button btnCancel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        model = ChampModel.getInstance();
        group = model.getGroup();
        setLabelName(model);
        setLabelRound();
    }

    @FXML
    private void handleSave()
    {
        int homeScore = 0;
        int awayScore = 0;
        if (!"".equals(txtHome1.getText()) && !"".equals(txtAway1.getText()))
        {
            homeScore = Integer.parseInt(txtHome1.getText());
            awayScore = Integer.parseInt(txtAway1.getText());
            setMatch(homeTeam1, awayTeam1, homeScore, awayScore);
        }
        if (!"".equals(txtHome2.getText()) && !"".equals(txtAway2.getText()))
        {
            homeScore = Integer.parseInt(txtHome2.getText());
            awayScore = Integer.parseInt(txtAway2.getText());
            setMatch(homeTeam2, awayTeam2, homeScore, awayScore);
        }
        closeWindow();
    }

    @FXML
    private void handleCancel()
    {
        closeWindow();
    }

    /**
     * Sets the data of lblRoundNumb.
     */
    private void setLabelRound()
    {
        lblRoundNumb.setText(group.getCurrentRound() + "");
    }

    /**
     * Sets the label names from a model.
     *
     * @param model The model to connect with.
     */
    private void setLabelName(ChampModel model)
    {
        ArrayList<Team> teams = model.getTeams();
        for (Team team : teams)
        {
            if (team.getId() == model.getFirstMatch()[0])
            {
                homeTeam1 = team;
                lblHome1.setText(team.getName());
            }
            else if (team.getId() == model.getFirstMatch()[1])
            {
                awayTeam1 = team;
                lblAway1.setText(team.getName());
            }

            if (model.getSecondMatch()[0] != 0)
            {
                if (team.getId() == model.getSecondMatch()[0])
                {
                    homeTeam2 = team;
                    lblHome2.setText(team.getName());
                }
                else if (team.getId() == model.getSecondMatch()[1])
                {
                    awayTeam2 = team;
                    lblAway2.setText(team.getName());

                }

            }
            else
            {
                lblHome2.setDisable(true);
                lblAway2.setDisable(true);
                txtHome2.setDisable(true);
                txtAway2.setDisable(true);
            }
        }
    }

    /**
     * Closes the currently active window without saving the data stored.
     */
    private void closeWindow()
    {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    /**
     * Sets a new match between two teams.
     *
     * @param homeTeam The home team.
     * @param awayTeam The away team.
     * @param homeScore The score of the home team.
     * @param awayScore The score of the away team.
     */
    private void setMatch(Team homeTeam, Team awayTeam, int homeScore, int awayScore)
    {
        model.addMatch(homeTeam, awayTeam, homeScore, awayScore);
        homeTeam.setPlayed(homeTeam.getPlayed() + 1);
        awayTeam.setPlayed(awayTeam.getPlayed() + 1);

        if (homeScore > awayScore)
        {
            homeTeam.setWins(homeTeam.getWins() + 1);
            awayTeam.setLosses(awayTeam.getLosses() + 1);
        }
        else if (homeScore < awayScore)
        {
            awayTeam.setWins(awayTeam.getWins() + 1);
            homeTeam.setLosses(homeTeam.getLosses() + 1);
        }
        else
        {
            homeTeam.setDraws(homeTeam.getDraws() + 1);
            awayTeam.setDraws(awayTeam.getDraws() + 1);
        }

        homeTeam.setGoalFor(homeTeam.getGoalFor() + homeScore);
        awayTeam.setGoalFor(awayTeam.getGoalFor() + awayScore);

        homeTeam.setGoalAgainst(homeTeam.getGoalAgainst() + awayScore);
        awayTeam.setGoalAgainst(awayTeam.getGoalAgainst() + homeScore);

        homeTeam.setPoint();
        awayTeam.setPoint();

        ObservableList<Team> teamsInGroup = group.getTeams();
        int x = 0;
        if (group.getTeamsInGroup() == 4)
        {
            for (Team team : teamsInGroup)
            {
                if (team.getPlayed() == group.getCurrentRound())
                {
                    x++;
                }
            }
            if (x == teamsInGroup.size())
            {
                group.setCurrentRound(group.getCurrentRound() + 1);
            }
        }
        else if (group.getTeamsInGroup() == 3)
        {

            for (Team team : teamsInGroup)
            {
                x = x + team.getPlayed();
            }
            if(x/2 == group.getCurrentRound())
            {
                group.setCurrentRound(group.getCurrentRound()+1);
            }
        }
        if(group.getCurrentRound() == group.getHomeTeams1().length+1)
        {
            System.out.println("DONE");
            group.setDone(true);
        }

    }
    
    @FXML
    private void macros(KeyEvent key)
    {
        if(KeyCode.ENTER == key.getCode())
        {
            handleSave();
        }
    }
}
