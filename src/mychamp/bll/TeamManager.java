package mychamp.bll;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import mychamp.be.Team;
import mychamp.dal.TeamDAO;
import mychamp.gui.model.ChampModel;

/**
 *
 * @author Thomas Meyer Hansen, Simon Juhl Birkedal, Stephan Fuhlendorff & Jacob
 * Enemark
 */
public class TeamManager {

    public TeamDAO teamDAO;
    public ChampModel model;

    /**
     * The default constructor for this class.
     */
    public TeamManager()
    {
        teamDAO = new TeamDAO("TeamData");
        model = ChampModel.getInstance();
    }

    /**
     * Loads the data currently stored in TeamData.dat
     */
    public List<Team> loadTeamData() throws IOException
    {
      return teamDAO.getAll();
      
    }

    /**
     * Saves the current team data into TeamData.dat
     *
     * @throws IOException
     */
    public void saveTeamData() throws IOException
    {
        ArrayList<Team> teamsToSave = new ArrayList<>();
        for (Team team : model.getTeams())
        {
            teamsToSave.add(team);
        }
        teamDAO.saveAll(teamsToSave);
    }

}
