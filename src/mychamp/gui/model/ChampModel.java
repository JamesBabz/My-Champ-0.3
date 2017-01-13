package mychamp.gui.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mychamp.be.Group;
import mychamp.be.Team;
import mychamp.dal.TeamDAO;
import mychamp.be.Match;
import mychamp.dal.RoundDAO;

/**
 *
 * @author Thomas Meyer Hansen, Simon Juhl Birkedal, Stephan Fuhlendorff & Jacob
 * Enemark
 */
public class ChampModel {

    private final ArrayList<Team> teams;
    private ArrayList<Group> groups;
    private final ArrayList<Team> teamsToQuarter;
    private final ObservableList<String> teamNames;
    private final ObservableList<String> test;
    private final ArrayList<Match> matches;
    private final ArrayList<Match> matchesA;
    private final ArrayList<Match> matchesB;
    private final ArrayList<Match> matchesC;
    private final ArrayList<Match> matchesD;
    private final TeamDAO teamDAO;
    private final RoundDAO roundDAO;
    private Team editTeam;
    private Group group;
    private Match firstMatch;
    private Match secondMatch;
    private boolean isResumed;

    private static ChampModel instance;

    /**
     * A singleton pattern to retrieve model data.
     *
     * @return Returns the initialized model.
     */
    public static ChampModel getInstance()
    {
        if (instance == null)
        {
            instance = new ChampModel();
        }
        return instance;
    }

    /**
     * A private constructor used for initializing the models data.
     */
    private ChampModel()
    {

        this.isResumed = false;
        this.teamNames = FXCollections.observableArrayList();
        this.test = FXCollections.observableArrayList();
        teams = new ArrayList<>();
        matches = new ArrayList<>();
        groups = new ArrayList<>();
        matchesA = new ArrayList<>();
        matchesB = new ArrayList<>();
        matchesC = new ArrayList<>();
        matchesD = new ArrayList<>();
        teamDAO = new TeamDAO("TeamData");
        roundDAO = new RoundDAO("RoundData");
        teamsToQuarter = new ArrayList<>();

    }

    /**
     * Generates and opens a new view.
     *
     * @param pane The pane to get the window owner of. (Typically the current
     * pane)
     * @param viewName
     * @param title
     * @throws IOException
     */
    public void openNewView(Pane pane, String viewName, String title) throws IOException
    {
        Stage primaryStage = (Stage) pane.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mychamp/gui/view/" + viewName + ".fxml"));
        Parent root = loader.load();

        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));

        newStage.initModality(Modality.WINDOW_MODAL);
        newStage.initOwner(primaryStage);
        newStage.setTitle(title);

        newStage.show();
        newStage.setOnCloseRequest(value -> 
        {
            try
            {
                teamDAO.saveTeamData(teams);
                roundDAO.saveRoundData(groups);
            }
            catch (IOException ex)
            {
                Logger.getLogger(ChampModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    /**
     * Creates a new team
     *
     * @param name - Name of the team
     */
    public void addTeam(String name)
    {
        Team team = new Team(1, name, 0, 0, 0, 0, 0, 0, 0);
        teams.add(team);
        setTeamNames();
    }

    public void loadTeam(Team team)
    {
        teams.add(team);
    }

    /**
     * Returns ArrayList of all the teams
     *
     * @return - Team object
     */
    public ArrayList<Team> getTeams()
    {
        return teams;
    }

    /**
     * Returns ObservableList of all team names
     *
     * @return - Team names
     */
    public ObservableList<String> getTeamNames()
    {
        return teamNames;
    }

    /**
     * Sets the team names
     */
    public void setTeamNames()
    {
        teamNames.clear();
        for (Team team : teams)
        {
            teamNames.add(team.getName());
        }
    }

    /**
     * Removes a team from the list by a given index.
     *
     * @param teamIndex The index of the team.
     */
    public void removeTeam(int teamIndex)
    {
        teams.remove(teamIndex);
        setTeamNames();
    }

    /**
     * Edits a team by it's given index.
     *
     * @param teamIndex The index of the team to be edited.
     */
    public void setEditTeam(int teamIndex)
    {
        if (teamIndex >= 0)
        {
            editTeam = teams.get(teamIndex);
        }
        else
        {
            editTeam = null;
        }
    }

    /**
     * Gets the recently edited team.
     *
     * @return Returns a team representing the most recent edited team.
     */
    public Team getEditTeam()
    {
        return editTeam;
    }

    /**
     * Edits the name of the current editable team.
     *
     * @param name The new name of the team.
     */
    public void editTeam(String name)
    {
        int index = teams.indexOf(editTeam);
        teams.set(index, new Team(1, name, 0, 0, 0, 0, 0, 0, 0));
        editTeam = null;
        setTeamNames();
    }

    public void setRoundMatches(Match fMatch, Match sMatch)
    {
        this.firstMatch = fMatch;
        this.secondMatch = sMatch;
    }

    public Match getFirstMatch()
    {
        return firstMatch;
    }

    public Match getSecondMatch()
    {
        return secondMatch;
    }

    /**
     *
     * @param group
     */
    public void setGroup(Group group)
    {
        this.group = group;
    }

    /**
     *
     * @return
     */
    public Group getGroup()
    {
        return group;
    }
    
    public ArrayList<Group> getGroups()
    {
        return this.groups;
    }
    
    public void setGroups(ArrayList<Group> groups)
    {
        this.groups = groups;
    }

    public boolean getResumed()
    {
        return isResumed;
    }

    public void setResumed(boolean resume)
    {
        isResumed = resume;
    }

    public ArrayList<Team> getTeamsToQuarter()
    {
        return teamsToQuarter;
    }

    public void setQuarterFinalTeams(Team team)
    {
        teamsToQuarter.add(team);

    }

    public ArrayList<Match> getMatchesA()
    {
        return matchesA;
    }

    public ArrayList<Match> getMatchesB()
    {
        return matchesB;
    }

    public ArrayList<Match> getMatchesC()
    {
        return matchesC;
    }

    public ArrayList<Match> getMatchesD()
    {
        return matchesD;
    }

    public void setMatchesA(ArrayList matches)
    {
        this.matchesA.addAll(matches);
    }

    public void setMatchesB(ArrayList matches)
    {
        this.matchesB.addAll(matches);
    }

    public void setMatchesC(ArrayList matches)
    {
        this.matchesC.addAll(matches);
    }

    public void setMatchesD(ArrayList matches)
    {
        this.matchesD.addAll(matches);
    }

}
