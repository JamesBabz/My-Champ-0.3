package mychamp.gui.model;

import java.io.IOException;
import java.util.ArrayList;
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

/**
 *
 * @author Thomas Meyer Hansen, Simon Juhl Birkedal, Stephan Fuhlendorff & Jacob
 * Enemark
 */
public class ChampModel
{
    private final ArrayList<Team> teams;
    private final ObservableList<String> teamNames;
    private final ObservableList<String> test;
    private final TeamDAO teamDAO;
    private Team editTeam;
    private Group group;
    private int[] firstMatch;
    private int[] secondMatch;

    private static ChampModel instance;

    /**
     * A singleton pattern to retrieve model data.
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
        this.teamNames = FXCollections.observableArrayList();
        this.test = FXCollections.observableArrayList();
        teams = new ArrayList<>();
        teamDAO = new TeamDAO();

    }

    /**
     * Generates and opens a new view.
     * @param pane The pane to get the window owner of. (Typically the current pane)
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
    }

    /**
     * Creates a new team
     *
     * @param name - Name of the team
     */
    public void addTeam(String name)
    {
        Team team = new Team(name);
        teams.add(team);
        setTeamNames();
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
    private void setTeamNames()
    {
        teamNames.clear();
        for (Team team : teams)
        {
            teamNames.add(team.getName());
        }
    }

    /**
     * Removes a team from the list by a given index.
     * @param teamIndex The index of the team.
     */
    public void removeTeam(int teamIndex)
    {
        teams.remove(teamIndex);
        setTeamNames();
    }

    /**
     * Edits a team by it's given index.
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
     * @return Returns a team representing the most recent edited team.
     */
    public Team getEditTeam()
    {
        return editTeam;
    }

    /**
     * Edits the name of the current editable team.
     * @param name The new name of the team.
     */
    public void editTeam(String name)
    {
        int index = teams.indexOf(editTeam);
        teams.set(index, new Team(name));
        editTeam = null;
        setTeamNames();
    }

    /**
     * 
     * @param home1Id
     * @param away1Id
     * @param home2Id
     * @param away2Id 
     */
    public void setRoundTeams(int home1Id, int away1Id, int home2Id, int away2Id)
    {
        firstMatch = new int[]
        {
            home1Id, away1Id
        };
        secondMatch = new int[]
        {
            home2Id, away2Id
        };
    }

    /**
     * 
     * @return 
     */
    public int[] getFirstMatch()
    {
        return firstMatch;
    }

    /**
     * 
     * @return 
     */
    public int[] getSecondMatch()
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

    /**
     * 
     * @return 
     */
    public ObservableList<String> getTest()
    {
        return test;
    }

}
