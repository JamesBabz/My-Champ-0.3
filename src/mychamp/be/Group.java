package mychamp.be;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;

/**
 *
 * @author Thomas Meyer Hansen, Simon Juhl Birkedal, Stephan Fuhlendorff & Jacob
 * Enemark
 */
public class Group {

    String name;
    int teamsInGroup;
    int[] homeTeams1;
    int[] awayTeams1;
    int[] homeTeams2;
    int[] awayTeams2;
    int currentRound;
    private ObservableList<Team> teams;
    private final BooleanProperty isDone;

    /**
     * The default constructor for creating a group.
     *
     * @param name The group's name.
     * @param teams The amount of teams the group contains.
     */
    public Group(String name, int teams)
    {
        this.isDone = new SimpleBooleanProperty();
        this.currentRound = 1;
        this.name = name;
        this.teamsInGroup = teams;
        groupPlay(teamsInGroup);
    }

    /**
     * An alternative constructor that takes an observablelist as a teams
     * argument.
     *
     * @param name The group's name.
     * @param teams The list of teams the group contains.
     */
    public Group(String name, ObservableList<Team> teams)
    {
        this.isDone = new SimpleBooleanProperty();
        this.currentRound = 1;
        this.name = name;
//        this.teamsInGroup = teams;
        this.teams = teams;
        this.teamsInGroup = teams.size();
//        groupPlay(teamsInGroup);
    }

    /**
     *
     * @param teams
     */
    public void groupPlay(int teams)
    {
        switch (teams)
        {
            case 2:
                
                break;
            case 3:
                groupOf3();
                break;
            case 4:
                groupOf4();
                break;
            default:
                System.out.println("ERROR");
                break;
        }
    }

    /**
     *
     */
    private void groupOf3()
    {
        homeTeams1 = new int[]
        {
            1, 2, 1, 2, 3, 3
        };
        awayTeams1 = new int[]
        {
            2, 3, 3, 1, 1, 2
        };
    }

    /**
     *
     */
    private void groupOf4()
    {
        homeTeams1 = new int[]
        {
            1, 4, 1, 1, 2, 4
        };
        awayTeams1 = new int[]
        {
            2, 1, 3, 4, 1, 2
        };
        homeTeams2 = new int[]
        {
            3, 2, 2, 3, 4, 3
        };
        awayTeams2 = new int[]
        {
            4, 3, 4, 2, 3, 1
        };
    }

    /**
     * Gets the teams currently in this group.
     *
     * @return Returns the teams currently in a group, represented by an
     * integer.
     */
    public int getTeamsInGroup()
    {
        return teamsInGroup;
    }

    /**
     * Gets the first home team.
     *
     * @return Returns the first home teams represented by an array of integers.
     */
    public int[] getHomeTeams1()
    {
        return homeTeams1;
    }

    /**
     * Gets the first away team.
     *
     * @return Returns the first away teams represented by an array of integers.
     */
    public int[] getAwayTeams1()
    {
        return awayTeams1;
    }

    /**
     * Gets the second home team.
     *
     * @return Returns the second home teams represented by an array of
     * integers.
     */
    public int[] getHomeTeams2()
    {
        return homeTeams2;
    }

    /**
     * Gets the second away team.
     *
     * @return Returns the second away teams represented by an array of
     * integers.
     */
    public int[] getAwayTeams2()
    {
        return awayTeams2;
    }

    /**
     * Gets the current round being played.
     *
     * @return Returns an integer representing the currently playing round.
     */
    public int getCurrentRound()
    {
        return currentRound;
    }

    /**
     * Sets the current round.
     *
     * @param currentRound The round to be played.
     */
    public void setCurrentRound(int currentRound)
    {
        this.currentRound = currentRound;
        if(currentRound > 6)
        {
            setDone(true);
        }
    }

    /**
     * Gets the teams in this group.
     *
     * @return Returns the teams in this group.
     */
    public ObservableList<Team> getTeams()
    {
        return teams;
    }

//    public boolean isDone()
//    {
//        return isDone;
//    }
//
//    public void setDone(boolean done)
//    {
//        this.isDone = done;
//    }
    public boolean isDone()
    {
        return isDone.get();
    }

    public void setDone(boolean value)
    {
        isDone.set(value);
    }

    public BooleanProperty isDoneProperty()
    {
        return isDone;
    }

}
