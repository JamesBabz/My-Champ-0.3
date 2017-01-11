/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychamp.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mychamp.be.Group;
import mychamp.be.Team;
import mychamp.bll.PropertyValue;
import mychamp.gui.model.ChampModel;

/**
 * FXML Controller class
 *
 * @author Thomas Meyer Hansen, Simon Juhl Birkedal, Stephan Fuhlendorff & Jacob
 * Enemark
 */
public class GroupViewController implements Initializable {

    ChampModel model;

//    private final static String[] cellValues = new String[]{"name",""};
    private ArrayList<Team> teams;
    private ArrayList<Group> groups;
    private ObservableList<Team> groupATeams;
    private ObservableList<Team> groupBTeams;
    private ObservableList<Team> groupCTeams;
    private ObservableList<Team> groupDTeams;

    private Group groupA;
    private Group groupB;
    private Group groupC;
    private Group groupD;

    @FXML
    private TableView<Team> tableA;
    @FXML
    private TableView<Team> tableB;
    @FXML
    private TableView<Team> tableC;
    @FXML
    private TableView<Team> tableD;

    @FXML
    private TableColumn<Team, String> colTeamA;
    @FXML
    private TableColumn<Team, Integer> colPlayedA;
    @FXML
    private TableColumn<Team, Integer> colWonA;
    @FXML
    private TableColumn<Team, Integer> colDrawnA;
    @FXML
    private TableColumn<Team, Integer> colLostA;
    @FXML
    private TableColumn<Team, Integer> colGFA;
    @FXML
    private TableColumn<Team, Integer> colGAA;
    @FXML
    private TableColumn<Team, Integer> colPointsA;

    @FXML
    private TableColumn<Team, String> colTeamB;
    @FXML
    private TableColumn<Team, Integer> colPlayedB;
    @FXML
    private TableColumn<Team, Integer> colWonB;
    @FXML
    private TableColumn<Team, Integer> colDrawnB;
    @FXML
    private TableColumn<Team, Integer> colLostB;
    @FXML
    private TableColumn<Team, Integer> colGFB;
    @FXML
    private TableColumn<Team, Integer> colGAB;
    @FXML
    private TableColumn<Team, Integer> colPointsB;

    @FXML
    private TableColumn<Team, String> colTeamC;
    @FXML
    private TableColumn<Team, Integer> colPlayedC;
    @FXML
    private TableColumn<Team, Integer> colWonC;
    @FXML
    private TableColumn<Team, Integer> colDrawnC;
    @FXML
    private TableColumn<Team, Integer> colLostC;
    @FXML
    private TableColumn<Team, Integer> colGFC;
    @FXML
    private TableColumn<Team, Integer> colGAC;
    @FXML
    private TableColumn<Team, Integer> colPointsC;

    @FXML
    private TableColumn<Team, String> colTeamD;
    @FXML
    private TableColumn<Team, Integer> colPlayedD;
    @FXML
    private TableColumn<Team, Integer> colWonD;
    @FXML
    private TableColumn<Team, Integer> colDrawnD;
    @FXML
    private TableColumn<Team, Integer> colLostD;
    @FXML
    private TableColumn<Team, Integer> colGFD;
    @FXML
    private TableColumn<Team, Integer> colGAD;
    @FXML
    private TableColumn<Team, Integer> colPointsD;

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button btnNxtRndA;
    @FXML
    private Button btnNxtRndB;
    @FXML
    private Button btnNxtRndC;
    @FXML
    private Button btnNxtRndD;
    @FXML
    private Button btnGoToFinals;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

        setCellValues();

        model = ChampModel.getInstance();
        teams = model.getTeams();
        groups = new ArrayList<>();
        groupInit();
        setTeamIds();
        sortingListener();
        populateTables();
        listeners();

        Comparator<Team> comparator = Comparator.comparingInt(Team::getPoint);
        comparator = comparator.reversed();
        FXCollections.sort(groupATeams, comparator);
        FXCollections.sort(groupBTeams, comparator);
        FXCollections.sort(groupCTeams, comparator);
        FXCollections.sort(groupDTeams, comparator);
    }

    private void listeners()
    {
        groupIsDoneListener();
        sortingListener();
    }

    @FXML
    private void openNextRoundViewA() throws IOException
    {
        model.setGroup(groupA);
        setMatchRound("A");
        openNextRound("group A");
    }

    @FXML
    private void openNextRoundViewB() throws IOException
    {
        model.setGroup(groupB);
        setMatchRound("B");
        openNextRound("group B");
    }

    @FXML
    private void openNextRoundViewC() throws IOException
    {
        model.setGroup(groupC);
        setMatchRound("C");
        openNextRound("group C");
    }

    @FXML
    private void openNextRoundViewD() throws IOException
    {
        model.setGroup(groupD);
        setMatchRound("D");
        openNextRound("group D");
    }

    /**
     * Populates the four tables with the required data to represent a team.
     */
    private void populateTables()
    {
        tableA.setItems(groupATeams);
        tableB.setItems(groupBTeams);
        tableC.setItems(groupCTeams);
        tableD.setItems(groupDTeams);
    }

    /**
     * Sets the cell value of each cell in each group.
     */
    private void setCellValues()
    {

        setGroupCellValues(tableA);
        setGroupCellValues(tableB);
        setGroupCellValues(tableC);
        setGroupCellValues(tableD);

    }

    /**
     * Initializes the groups, generating new collections that can be acted
     * upon.
     */
    private void groupInit()
    {
        groupATeams = FXCollections.observableArrayList();
        groupBTeams = FXCollections.observableArrayList();
        groupCTeams = FXCollections.observableArrayList();
        groupDTeams = FXCollections.observableArrayList();
        addTeamsToGroups(teams);

        groupA = new Group("A", groupATeams);
        groupB = new Group("B", groupBTeams);
        groupC = new Group("C", groupCTeams);
        groupD = new Group("D", groupDTeams);
        
        groups.add(groupA);
        groups.add(groupB);
        groups.add(groupC);
        groups.add(groupD);
    }

    /**
     * Sets a unique ID identifier for each team available.
     */
    private void setTeamIds()
    {
        for (Team team : teams)
        {
            team.setId(teams.indexOf(team) + 1);
        }
    }

    /**
     * Takes an arraylist of teams and places them inside their respective
     * group.
     *
     * @param teams The list of teams to be placed inside each group.
     */
    private void addTeamsToGroups(ArrayList<Team> teams)
    {
        if (!model.getResumed())

        {
            Collections.shuffle(teams);
        }

        int currentGroup = 0;
        for (Team team : teams)
        {
            switch (currentGroup)
            {
                case 0:
                    groupATeams.add(team);
                    break;
                case 1:
                    groupBTeams.add(team);
                    break;
                case 2:
                    groupCTeams.add(team);
                    break;
                case 3:
                    groupDTeams.add(team);
                    break;
                default:
                    System.out.println("NOPE");
            }
            if (currentGroup == 3)
            {
                currentGroup = 0;
            }
            else
            {
                currentGroup++;
            }
        }
    }

    /**
     * Loads the next round view
     *
     * @param title The title of the group.
     */
    private void openNextRound(String title)
    {
        try
        {
            model.openNewView(anchorPane, "NextRoundView", title);
        }
        catch (IOException ex)
        {
            Logger.getLogger(TeamManagerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Sets the match round - ???
     *
     * @param groupName The name of the group to set the match round for.
     */
    private void setMatchRound(String groupName)
    {
        Group group;
        ObservableList<Team> groupTeams;
        int currRound;
        switch (groupName)
        {
            case "A":
                group = groupA;
                groupTeams = groupATeams;
                break;
            case "B":
                group = groupB;
                groupTeams = groupBTeams;
                break;
            case "C":
                group = groupC;
                groupTeams = groupCTeams;
                break;
            case "D":
                group = groupD;
                groupTeams = groupDTeams;
                break;
            default:
                group = null;
                groupTeams = null;
                break;
        }
        currRound = group.getCurrentRound() - 1;
        Team home1;
        int home1Id;
        Team away1;
        int away1Id;
        Team home2;
        int home2Id = 0;
        Team away2;
        int away2Id = 0;

        home1 = groupTeams.get(group.getHomeTeams1()[currRound] - 1);
        away1 = groupTeams.get(group.getAwayTeams1()[currRound] - 1);
        home1Id = home1.getId();
        away1Id = away1.getId();

        if (group.getAwayTeams2() != null)
        {
            home2 = groupTeams.get(group.getHomeTeams2()[currRound] - 1);
            away2 = groupTeams.get(group.getAwayTeams2()[currRound] - 1);
            home2Id = home2.getId();
            away2Id = away2.getId();
        }
        model.setRoundTeams(home1Id, away1Id, home2Id, away2Id);
    }

    /**
     * ??
     *
     * @param table
     */
    private void setGroupCellValues(TableView<Team> table)
    {
        ObservableList<TableColumn<Team, ?>> tableList = table.getColumns();

        int x = 0;
        for (TableColumn clmn : tableList)
        {
            clmn.setCellValueFactory(new PropertyValueFactory<>(PropertyValue.values()[x].toString()));
            if (x == 0)
            {

                clmn.setCellFactory(TextFieldTableCell.forTableColumn());
            }
            x++;
        }
    }
    
    @FXML
    private void handleEditCommit(CellEditEvent<Team, String> event)
    {
        ((Team) event.getTableView().getItems().get(
                event.getTablePosition().getRow())).setName(event.getNewValue());
    }

private void groupIsDoneListener()
    {
        for (Group group : groups)
        {
            group.isDoneProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue)
                    -> 
                    {
                        Button btn;
                        if (group.equals(groupA))
                        {
                            btn = btnNxtRndA;
                        }
                        else if (group.equals(groupB))
                        {
                            btn = btnNxtRndB;
                        }
                        else if (group.equals(groupC))
                        {
                            btn = btnNxtRndC;
                        }
                        else if (group.equals(groupD))
                        {
                            btn = btnNxtRndD;
                        }else
                        {
                            btn = null;
                        }
                        btn.setDisable(true);
                        if (btnNxtRndA.isDisabled() && btnNxtRndB.isDisabled() && btnNxtRndC.isDisabled() && btnNxtRndD.isDisabled())
                        {
                                btnGoToFinals.setDisable(false);
                        }
            });
        }
    }

    private void sortingListener()
    {
        sortGroup("A");
        sortGroup("B");
        sortGroup("C");
        sortGroup("D");
    }


    private void sortGroup(String groupTeamsString)
    {
        ObservableList<Team> groupTeams;
        switch (groupTeamsString)
        {
            case "A":
                groupTeams = groupATeams;

                break;
            case "B":
                groupTeams = groupBTeams;

                break;
            case "C":
                groupTeams = groupCTeams;

                break;
            case "D":
                groupTeams = groupDTeams;

                break;
            default:
                groupTeams = null;
        }
        for (Team team : groupTeams)
        {
            team.pointProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
                {
                    Comparator<Team> comparator = Comparator.comparingInt(Team::getPoint);
                    comparator = comparator.reversed();
                    FXCollections.sort(groupTeams, comparator);
                }
            });
        }
    }

    public void getQuarterFinalTeams()
    {
        
        model.setQuarterFinalTeams(tableA.getItems().get(0));
        model.setQuarterFinalTeams(tableA.getItems().get(1));
        
        model.setQuarterFinalTeams(tableB.getItems().get(0));
        model.setQuarterFinalTeams(tableB.getItems().get(1));
        
        model.setQuarterFinalTeams(tableC.getItems().get(0));
        model.setQuarterFinalTeams(tableC.getItems().get(1));
        
        model.setQuarterFinalTeams(tableD.getItems().get(0));
        model.setQuarterFinalTeams(tableD.getItems().get(1));
        
        
    }

    @FXML
    private void goToFinals(ActionEvent event)
    {
           Stage primaryStage = (Stage) anchorPane.getScene().getWindow();
        primaryStage.close();

        try
        {
            model.openNewView(anchorPane, "FinalsView", "");
        } catch (IOException ex)
        {
            Logger.getLogger(TeamManagerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
