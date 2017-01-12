/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychamp.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import mychamp.be.Group;
import mychamp.be.Match;
import mychamp.be.Team;
import mychamp.bll.PropertyValue;
import mychamp.gui.model.ChampModel;

/**
 * FXML Controller class
 *
 * @author Jacob Enemark
 */
public class FinalsViewController implements Initializable
{

    private ArrayList<Team> quarterTeams;

    private ObservableList<Team> quarterATeams;
    private ObservableList<Team> quarterBTeams;
    private ObservableList<Team> quarterCTeams;
    private ObservableList<Team> quarterDTeams;

    ChampModel model;

    @FXML
    private TableView<Team> quarterFinalA;
    @FXML
    private TableColumn<Team, String> QuarterTeamA;
    @FXML
    private TableColumn<Team, Integer> QuarterScoreA;
    @FXML
    private TableView<Team> quarterFinalB;
    @FXML
    private TableColumn<Team, String> QuarterTeamB;
    @FXML
    private TableColumn<Team, Integer> QuarterScoreB;
    @FXML
    private TableView<Team> SemiFinalA;
    @FXML
    private TableColumn<Team, String> SemiTeamA;
    @FXML
    private TableColumn<Team, Integer> SemiScoreA;
    @FXML
    private TableView<Team> FinalA;
    @FXML
    private TableColumn<Team, String> FinalTeamA;
    @FXML
    private TableColumn<Team, Integer> FinalScoreA;
    @FXML
    private TableView<Team> quarterFinalC;
    @FXML
    private TableColumn<Team, String> QuarterTeamC;
    @FXML
    private TableColumn<Team, Integer> QuarterScoreC;
    @FXML
    private TableView<Team> SemiFinalB;
    @FXML
    private TableColumn<Team, String> SemiTeamB;
    @FXML
    private TableColumn<Team, Integer> SemiScoreB;
    @FXML
    private TableView<Team> quarterFinalD;
    @FXML
    private TableColumn<Team, String> QuarterTeamD;
    @FXML
    private TableColumn<Team, Integer> QuarterScoreD;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button btnQuarter;
    @FXML
    private Button btnSemi;
    @FXML
    private Button btnFinal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

        model = ChampModel.getInstance();
         
        initArrays();
        
        
        populateTables();
        createQuarterFinals();
    }

    private void initArrays()
    {
        quarterTeams = new ArrayList<>();
        quarterTeams = model.getTeamsToQuarter();
        
        quarterATeams = FXCollections.observableArrayList();
        quarterBTeams = FXCollections.observableArrayList();
        quarterCTeams = FXCollections.observableArrayList();
        quarterDTeams = FXCollections.observableArrayList();
    }

    private void createQuarterFinals()
    {
        quarterATeams.add(quarterTeams.get(0));
        quarterATeams.get(0).setGoalFor(0);
        quarterATeams.add(quarterTeams.get(3));
        quarterATeams.get(1).setGoalFor(0);

        quarterBTeams.add(quarterTeams.get(2));
        quarterBTeams.get(0).setGoalFor(0);
        quarterBTeams.add(quarterTeams.get(1));
        quarterBTeams.get(1).setGoalFor(0);

        quarterCTeams.add(quarterTeams.get(4));
        quarterCTeams.get(0).setGoalFor(0);
        quarterCTeams.add(quarterTeams.get(7));
        quarterCTeams.get(1).setGoalFor(0);

        quarterDTeams.add(quarterTeams.get(6));
        quarterDTeams.get(0).setGoalFor(0);
        quarterDTeams.add(quarterTeams.get(5));
        quarterDTeams.get(1).setGoalFor(0);

        QuarterTeamA.setCellValueFactory(new PropertyValueFactory<>("name"));
        QuarterScoreA.setCellValueFactory(new PropertyValueFactory<>("goalFor"));
        QuarterTeamB.setCellValueFactory(new PropertyValueFactory<>("name"));
        QuarterScoreB.setCellValueFactory(new PropertyValueFactory<>("goalFor"));
        QuarterTeamC.setCellValueFactory(new PropertyValueFactory<>("name"));
        QuarterScoreC.setCellValueFactory(new PropertyValueFactory<>("goalFor"));
        QuarterTeamD.setCellValueFactory(new PropertyValueFactory<>("name"));
        QuarterScoreD.setCellValueFactory(new PropertyValueFactory<>("goalFor"));
    }
    

    private void populateTables()
    {
        quarterFinalA.setItems(quarterATeams);
        quarterFinalB.setItems(quarterBTeams);
        quarterFinalC.setItems(quarterCTeams);
        quarterFinalD.setItems(quarterDTeams);
    }

    @FXML
    private void firstQFinal() throws IOException
    {
        Match match = new Match(quarterATeams.get(0),quarterATeams.get(1));
        Group group = new Group("1st QFinals",2);
        group.setCurrentRound(10);
        model.setGroup(group);
        model.setRoundMatches(match, null);
        model.openNewView(anchorPane, "NextRoundView", "QFinals");
        
    }

}
