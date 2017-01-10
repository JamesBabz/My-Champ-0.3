package mychamp.bll;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import mychamp.be.Team;
import mychamp.dal.TeamDAO;
import mychamp.gui.model.ChampModel;

/**
 *
 * @author Thomas Meyer Hansen, Simon Juhl Birkedal, Stephan Fuhlendorff & Jacob
 * Enemark
 */
public class TeamManager
{
    public TeamDAO teamDAO;
    public ChampModel model;

    /**
     * The default constructor for this class.
     */
    public TeamManager()
    {
        teamDAO = new TeamDAO();
        model = ChampModel.getInstance();
    }

    /**
     * Loads the data currently stored in TeamData.dat
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ClassNotFoundException 
     */
    public void loadTeamData() throws IOException, FileNotFoundException, ClassNotFoundException
    {
        model.getTeams().clear();
        for (Team team : teamDAO.readObjectData("TeamData.dat"))
        {
            model.addTeam(team.getName());
        }
    }

    /**
     * Saves the current team data into TeamData.dat
     * @throws IOException 
     */
    public void saveTeamData() throws IOException
    {
        ArrayList<Team> teamsToSave = new ArrayList<>();
        for (Team team : model.getTeams())
        {
            teamsToSave.add(team);
        }
        teamDAO.writeObjectData(teamsToSave, "TeamData.dat");
    }

}