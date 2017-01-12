/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychamp.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import mychamp.gui.model.ChampModel;

/**
 * FXML Controller class
 *
 * @author Jacob Enemark
 */
public class FinalsViewController implements Initializable {

    @FXML
    private TableView<?> quarterFinalA;
    @FXML
    private TableColumn<?, ?> QuarterTeamA;
    @FXML
    private TableColumn<?, ?> QuarterScoreA;
    @FXML
    private TableView<?> quarterFinalB;
    @FXML
    private TableColumn<?, ?> QuarterTeamB;
    @FXML
    private TableColumn<?, ?> QuarterScoreB;
    @FXML
    private TableView<?> SemiFinalA;
    @FXML
    private TableColumn<?, ?> SemiTeamA;
    @FXML
    private TableColumn<?, ?> SemiScoreA;
    @FXML
    private TableView<?> FinalA;
    @FXML
    private TableColumn<?, ?> FinalTeamA;
    @FXML
    private TableColumn<?, ?> FinalScoreA;
    @FXML
    private TableView<?> quarterFinalC;
    @FXML
    private TableColumn<?, ?> QuarterTeamC;
    @FXML
    private TableColumn<?, ?> QuarterScoreC;
    @FXML
    private TableView<?> SemiFinalB;
    @FXML
    private TableColumn<?, ?> SemiTeamB;
    @FXML
    private TableColumn<?, ?> SemiScoreB;
    @FXML
    private TableView<?> quarterFinalD;
    @FXML
    private TableColumn<?, ?> QuarterTeamD;
    @FXML
    private TableColumn<?, ?> QuarterScoreD;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button btnQuarter;
    @FXML
    private Button btnSemi;
    @FXML
    private Button btnFinal;

    ChampModel model;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
//        model.getTeamsToQuarter();
    }

    private void createQuarterFinals()
    {

    }

}
