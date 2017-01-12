/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychamp.gui.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import mychamp.be.Match;
import mychamp.gui.model.ChampModel;

/**
 * FXML Controller class
 *
 * @author James
 */
public class MatchScheduleViewController implements Initializable {

    ChampModel model;

    @FXML
    private AnchorPane pane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        model = ChampModel.getInstance();
        setLabels();
    }

    private void setLabels()
    {
        ArrayList<Match> matches = new ArrayList<>();
        matches.addAll(model.getMatchesA());
        matches.addAll(model.getMatchesB());
        matches.addAll(model.getMatchesC());
        matches.addAll(model.getMatchesD());
        int y = 0;
        double x = pane.getPrefWidth() / 2 - 100;
        int size = 0;
        for (Match match : matches)
        {
            if (size == 0)
            {
                Label header = new Label("Group A:");
                pane.getChildren().add(header);
                header.setFont(new Font("Arial", 20));
                header.setLayoutY(y);
                header.setLayoutX(x);
                y = y + 24;
            }
            else if (size == model.getMatchesA().size())
            {
                y = y + 5;
                Label header = new Label("Group B:");
                pane.getChildren().add(header);
                header.setFont(new Font("Arial", 20));
                header.setLayoutY(y);
                header.setLayoutX(x);
                y = y + 24;
            }
            else if (size == model.getMatchesA().size() + model.getMatchesB().size())
            {
                Label header = new Label("Group C:");
                pane.getChildren().add(header);
                header.setFont(new Font("Arial", 20));
                header.setLayoutY(y);
                header.setLayoutX(x);
                y = y + 24;
            }
            else if (size == model.getMatchesA().size() + model.getMatchesB().size() + model.getMatchesC().size())
            {
                Label header = new Label("Group D:");
                pane.getChildren().add(header);
                header.setFont(new Font("Arial", 20));
                header.setLayoutY(y);
                header.setLayoutX(x);
                y = y + 24;
            }
            Label label = new Label("(" + match.getHomeScore() + ")  " + match.getHomeTeam().getName() + " - " + match.getAwayTeam().getName() + "  (" + match.getHomeScore() + ")");
            pane.getChildren().add(label);
            label.setLayoutY(y);
            label.setLayoutX(x);
            y = y + 15;
            size++;
        }
    }

}
