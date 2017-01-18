package mychamp.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mychamp.be.Group;
import mychamp.be.Match;
import mychamp.be.Team;
import mychamp.gui.model.ChampModel;

/**
 * FXML Controller class
 *
 * @author Thomas Meyer Hansen, Simon Juhl Birkedal, Stephan Fuhlendorff & Jacob
 * Enemark
 */
public class NextRoundViewController implements Initializable {

    Match firstMatch;
    Match secondMatch;

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

        processAllowedTextInputs();

    }

    private void processAllowedTextInputs()
    {
        for (Node node : anchorPane.getChildren())
        {
            if (node instanceof TextField)
            {
                ((TextField) node).textProperty().addListener((listener, oldVal, newVal)
                        -> 
                        {
                            if (!Pattern.matches("[0-9]+", newVal))
                            {
                                if (oldVal != null && !newVal.isEmpty())
                                {
                                    ((TextField) node).setText(oldVal);
                                }
                                else
                                {
                                    ((TextField) node).setText("");
                                }

                            }
                });
            }
        }
    }

    @FXML
    private void handleSave()
    {

        int homeScore = 0;
        int awayScore = 0;
        boolean error = false;

        if (txtHome2.isDisabled())
        {
            if ("".equals(txtHome1.getText()) || "".equals(txtAway1.getText()))
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("Please fill in the required fields");
                alert.showAndWait();
                error = true;
            }
        }
        else if ("".equals(txtHome1.getText()) || "".equals(txtAway1.getText()) || "".equals(txtHome2.getText()) || "".equals(txtAway2.getText()))
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Please fill in the required fields");
            alert.showAndWait();
            error = true;
        }
        if (!error)
        {
            if (!"".equals(txtHome1.getText()) && !"".equals(txtAway1.getText()))
            {
                homeScore = Integer.parseInt(txtHome1.getText());
                awayScore = Integer.parseInt(txtAway1.getText());
                setMatch(firstMatch, homeScore, awayScore);
                checkIfFinals();
            }

            if (!"".equals(txtHome2.getText()) && !"".equals(txtAway2.getText()))
            {

                homeScore = Integer.parseInt(txtHome2.getText());
                awayScore = Integer.parseInt(txtAway2.getText());
                setMatch(secondMatch, homeScore, awayScore);
            }

            closeWindow();
        }
    }

    private void checkIfFinals()
    {
        Team firstFinalist = firstMatch.getHomeTeam();
        Team secondFinalist = firstMatch.getAwayTeam();

        int scoreHome = Integer.parseInt(txtHome1.getText());
        int scoreAway = Integer.parseInt(txtAway1.getText());

        switch (group.getCurrentRound())
        {
            case 10:
                firstFinalist.setQuarterScore(scoreHome);
                secondFinalist.setQuarterScore(scoreAway);
                if (firstFinalist.getQuarterScore() > secondFinalist.getQuarterScore())
                {
                    model.getSemiFinalTeams().add(firstFinalist);
                }
                else if (firstFinalist.getQuarterScore() < secondFinalist.getQuarterScore())
                {
                    model.getSemiFinalTeams().add(secondFinalist);
                }
                break;
            case 11:
                firstFinalist.setSemiScore(scoreHome);
                secondFinalist.setSemiScore(scoreAway);
                if (firstFinalist.getSemiScore() > secondFinalist.getSemiScore())
                {
                    model.getFinalTeams().add(firstFinalist);
                }
                else if (firstFinalist.getSemiScore() < secondFinalist.getSemiScore())
                {
                    model.getFinalTeams().add(secondFinalist);
                }
                break;
            case 12:
                firstFinalist.setFinalScore(scoreHome);
                secondFinalist.setFinalScore(scoreAway);
                if (firstFinalist.getFinalScore() > secondFinalist.getFinalScore())
                {
                    model.getFinalTeams().add(firstFinalist);
                }
                else if (firstFinalist.getFinalScore() < secondFinalist.getFinalScore())
                {
                    model.getFinalTeams().add(secondFinalist);
                }
                break;
            default:
                break;
        }

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
        switch (group.getCurrentRound())
        {
            case 10:
                lblRoundNumb.setText("Quarter-Final");
                break;
            case 11:
                lblRoundNumb.setText("Semi-Final");
                break;
            case 12:
                lblRoundNumb.setText("Final");
                break;
            default:
                lblRoundNumb.setText("Round: " + group.getCurrentRound());
                break;
        }
    }

    /**
     * Sets the label names from a model.
     *
     * @param model The model to connect with.
     */
    private void setLabelName(ChampModel model)
    {
        firstMatch = model.getFirstMatch();
        secondMatch = model.getSecondMatch();
        Team firstHomeTeam = firstMatch.getHomeTeam();
        Team firstAwayTeam = firstMatch.getAwayTeam();
        Team secondHomeTeam;
        Team secondAwayTeam;
        if (secondMatch != null)
        {
            secondHomeTeam = secondMatch.getHomeTeam();
            secondAwayTeam = secondMatch.getAwayTeam();
        }
        else
        {
            secondHomeTeam = null;
            secondAwayTeam = null;
        }

        lblHome1.setText(firstHomeTeam.getName());
        lblAway1.setText(firstAwayTeam.getName());
        if (secondMatch != null)
        {
            lblHome2.setText(secondHomeTeam.getName());
            lblAway2.setText(secondAwayTeam.getName());
        }
        else
        {
            lblHome2.setDisable(true);
            lblAway2.setDisable(true);
            txtHome2.setDisable(true);
            txtAway2.setDisable(true);
        }
        if (firstHomeTeam.getIsDeleted().get() || firstAwayTeam.getIsDeleted().get())
        {

            lblHome1.setDisable(true);
            lblAway1.setDisable(true);
            txtHome1.setDisable(true);
            txtAway1.setDisable(true);
        }
        if (firstHomeTeam.getIsDeleted().get())
        {
            txtHome1.setText("0");
            txtAway1.setText("3");
        }
        else if (firstAwayTeam.getIsDeleted().get())
        {
            txtHome1.setText("3");
            txtAway1.setText("0");
        }

        if (secondMatch != null)
        {
            if (secondHomeTeam.getIsDeleted().get() || secondAwayTeam.getIsDeleted().get())
            {
                lblHome2.setDisable(true);
                lblAway2.setDisable(true);
                txtHome2.setDisable(true);
                txtAway2.setDisable(true);
            }
            if (secondHomeTeam.getIsDeleted().get())
            {
                txtHome2.setText("0");
                txtAway2.setText("3");
            }
            else if (secondAwayTeam.getIsDeleted().get())
            {
                txtHome2.setText("3");
                txtAway2.setText("0");
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
    private void setMatch(Match match, int homeScore, int awayScore)
    {
        match.setHomeScore(homeScore);
        match.setAwayScore(awayScore);

        Team homeTeam = match.getHomeTeam();
        Team awayTeam = match.getAwayTeam();

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
            if (x / 2 == group.getCurrentRound())
            {
                group.setCurrentRound(group.getCurrentRound() + 1);
            }
        }

        if (group.getTeamsInGroup() != 2)
        {
            if (group.getCurrentRound() == group.getHomeTeams1().length + 1)
            {
                group.setDone(true);
            }
        }
    }

    @FXML
    private void macros(KeyEvent key)
    {
        if (KeyCode.ENTER == key.getCode())
        {
            handleSave();
        }
    }
}
