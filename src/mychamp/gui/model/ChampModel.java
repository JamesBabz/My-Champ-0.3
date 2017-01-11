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


/**
 *
 * @author Thomas Meyer Hansen, Simon Juhl Birkedal, Stephan Fuhlendorff & Jacob
 * Enemark
 */
public class ChampModel
{
    private final ArrayList<Team> teams;
    private final ArrayList<Team> teamsToQuarter;
    private final ObservableList<String> teamNames;
    private final ObservableList<String> test;
    private final ArrayList<Match> matches;
    private final TeamDAO teamDAO;
    private Team editTeam;
    private Group group;
    private int[] firstMatch;
    private int[] secondMatch;
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
        teamDAO = new TeamDAO("TeamData");
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


    public boolean getResumed()
    {
        return isResumed;
    }
    
    public void setResumed(boolean resume)
    {
        isResumed = resume;
    }
    
    public void addMatch(Team ht, Team at, int hs, int as)
    {
        Match match = new Match(ht, at, hs, as);
        matches.add(match);
    }

    public ArrayList<Team> getTeamsToQuarter()
    {
        return teamsToQuarter;
    }
    

    

    public void setQuarterFinalTeams(Team team)
    {
        teamsToQuarter.add(team);
        
    }
 

}
