/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychamp.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;
import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
public class FinalsViewController implements Initializable {

    private ArrayList<Team> quarterTeams;
    private ArrayList<Team> semiTeams;
    private ArrayList<Team> finalTeams;

    private ObservableList<Team> quarterATeams;
    private ObservableList<Team> quarterBTeams;
    private ObservableList<Team> quarterCTeams;
    private ObservableList<Team> quarterDTeams;

    private ObservableList<Team> semiATeams;
    private ObservableList<Team> semiBTeams;

    private ObservableList<Team> finalATeams;

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
    private Button btn1Result;
    @FXML
    private Button btn2Result;
    @FXML
    private Button btn3Result;
    @FXML
    private Button btn4Result;
    @FXML
    private Button btn1ResultSemi;
    @FXML
    private Button btn1ResultSemi2;
    @FXML
    private Button btn1ResultFinal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

        SemiFinalA.setDisable(true);
        SemiFinalB.setDisable(true);
        btn1ResultSemi.setDisable(true);
        btn1ResultSemi2.setDisable(true);
        FinalA.setDisable(true);
        btn1ResultFinal.setDisable(true);

        model = ChampModel.getInstance();

        initArrays();

        populateTables();

        createQuarterFinals();

    }

    private void initArrays()
    {
        quarterTeams = new ArrayList<>();
        quarterTeams = model.getTeamsToQuarter();

        semiTeams = new ArrayList<>();
        semiTeams = model.getSemiFinalTeams();

        finalTeams = new ArrayList<>();
        finalTeams = model.getFinalTeams();

        quarterATeams = FXCollections.observableArrayList();
        quarterBTeams = FXCollections.observableArrayList();
        quarterCTeams = FXCollections.observableArrayList();
        quarterDTeams = FXCollections.observableArrayList();

        semiATeams = FXCollections.observableArrayList();
        semiBTeams = FXCollections.observableArrayList();

        finalATeams = FXCollections.observableArrayList();

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
        QuarterScoreA.setCellValueFactory(new PropertyValueFactory<>("quarterScore"));
        QuarterTeamB.setCellValueFactory(new PropertyValueFactory<>("name"));
        QuarterScoreB.setCellValueFactory(new PropertyValueFactory<>("quarterScore"));
        QuarterTeamC.setCellValueFactory(new PropertyValueFactory<>("name"));
        QuarterScoreC.setCellValueFactory(new PropertyValueFactory<>("quarterScore"));
        QuarterTeamD.setCellValueFactory(new PropertyValueFactory<>("name"));
        QuarterScoreD.setCellValueFactory(new PropertyValueFactory<>("quarterScore"));
    }

    private void populateTables()
    {
        populateQuarterTables();
        populateSemiTables();
        populateFinalTables();
    }

    private void populateQuarterTables()
    {

        quarterFinalA.setItems(quarterATeams);
        quarterFinalB.setItems(quarterBTeams);
        quarterFinalC.setItems(quarterCTeams);
        quarterFinalD.setItems(quarterDTeams);

    }

    private void populateSemiTables()
    {

        SemiFinalA.setItems(semiATeams);
        SemiFinalB.setItems(semiBTeams);
    }

    private void populateFinalTables()
    {
        FinalA.setItems(finalATeams);
    }

    private void resultQuarterFinal(int qFinal) throws IOException
    {
        ObservableList<Team> qTeams;
        switch (qFinal)
        {
            case 1:

                qTeams = quarterATeams;

                break;

            case 2:
                qTeams = quarterBTeams;

                break;

            case 3:
                qTeams = quarterCTeams;

                break;

            case 4:
                qTeams = quarterDTeams;

                break;

            default:
                qTeams = null;

        }
        Match match = new Match(qTeams.get(0), qTeams.get(1));
        Group group = new Group("", 2);
        group.setCurrentRound(10);
        model.setGroup(group);
        model.setRoundMatches(match, null);
        model.openNewView(anchorPane, "NextRoundView", "");

    }

    @FXML
    private void firstQFinal(ActionEvent event) throws IOException
    {

        resultQuarterFinal(1);

    }

    @FXML
    private void secondQFinal(ActionEvent event) throws IOException
    {
        resultQuarterFinal(2);

    }

    @FXML
    private void thirdQFinal(ActionEvent event) throws IOException
    {
        resultQuarterFinal(3);
    }

    @FXML
    private void fourthQFinal(ActionEvent event) throws IOException
    {
        resultQuarterFinal(4);

    }

    @FXML
    private void createSemiFinals()
    {
        SemiTeamA.setCellValueFactory(new PropertyValueFactory<>("name"));
        SemiScoreA.setCellValueFactory(new PropertyValueFactory<>("semiScore"));
        SemiTeamB.setCellValueFactory(new PropertyValueFactory<>("name"));
        SemiScoreB.setCellValueFactory(new PropertyValueFactory<>("semiScore"));
        if (semiTeams.size() == 4)
        {
            semiATeams.add(semiTeams.get(0));
            semiATeams.add(semiTeams.get(1));

            semiBTeams.add(semiTeams.get(2));
            semiBTeams.add(semiTeams.get(3));

            disableAndAbleBtnSemi();
        }

    }

    private void resultSemiFinal(int sFinal) throws IOException
    {
        ObservableList<Team> sTeams;
        switch (sFinal)
        {
            case 1:

                sTeams = semiATeams;

                break;

            case 2:
                sTeams = semiBTeams;

                break;

            default:
                sTeams = null;

        }
        Match match = new Match(sTeams.get(0), sTeams.get(1));
        Group group = new Group("", 2);
        group.setCurrentRound(11);
        model.setGroup(group);
        model.setRoundMatches(match, null);
        model.openNewView(anchorPane, "NextRoundView", "");

    }

    @FXML
    private void firstSFinal() throws IOException
    {
        resultSemiFinal(1);

    }

    @FXML
    private void secondSFinal() throws IOException
    {
        resultSemiFinal(2);

    }

    private void disableAndAbleBtnSemi()
    {
        SemiFinalA.setDisable(false);
        SemiFinalB.setDisable(false);
        btn1ResultSemi.setDisable(false);
        btn1ResultSemi2.setDisable(false);
        btn1Result.setDisable(true);
        btn2Result.setDisable(true);
        btn3Result.setDisable(true);
        btn4Result.setDisable(true);

    }

    @FXML
    private void createFinals()
    {
        if (finalTeams.size() == 2)
        {
            finalATeams.add(finalTeams.get(0));
            finalATeams.add(finalTeams.get(1));
            disableAndAbleBtnFinal();
        }

        FinalTeamA.setCellValueFactory(new PropertyValueFactory<>("name"));
        FinalScoreA.setCellValueFactory(new PropertyValueFactory<>("finalScore"));

    }

    private void resultFinal(int fFinal) throws IOException
    {
        ObservableList<Team> fTeams;
        switch (fFinal)
        {
            case 1:

                fTeams = finalATeams;

                break;

            default:
                fTeams = null;

        }
        Match match = new Match(fTeams.get(0), fTeams.get(1));
        Group group = new Group("", 2);
        group.setCurrentRound(12);
        model.setGroup(group);
        model.setRoundMatches(match, null);
        model.openNewView(anchorPane, "NextRoundView", "");

    }

    @FXML
    private void firstFinal() throws IOException
    {
        resultFinal(1);

    }

    private void disableAndAbleBtnFinal()
    {
        FinalA.setDisable(false);
        btn1ResultFinal.setDisable(false);
    }
}
