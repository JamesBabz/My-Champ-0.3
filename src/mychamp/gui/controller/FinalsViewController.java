/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychamp.gui.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
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
        quarterATeams.add(quarterTeams.get(3));

        quarterBTeams.add(quarterTeams.get(2));
        quarterBTeams.add(quarterTeams.get(1));

        quarterCTeams.add(quarterTeams.get(4));
        quarterCTeams.add(quarterTeams.get(7));

        quarterDTeams.add(quarterTeams.get(6));
        quarterDTeams.add(quarterTeams.get(5));

        QuarterTeamA.setCellValueFactory(new PropertyValueFactory<>("name"));
        QuarterTeamB.setCellValueFactory(new PropertyValueFactory<>("name"));
        QuarterTeamC.setCellValueFactory(new PropertyValueFactory<>("name"));
        QuarterTeamD.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

    private void populateTables()
    {
        quarterFinalA.setItems(quarterATeams);
        quarterFinalB.setItems(quarterBTeams);
        quarterFinalC.setItems(quarterCTeams);
        quarterFinalD.setItems(quarterDTeams);
    }

}
