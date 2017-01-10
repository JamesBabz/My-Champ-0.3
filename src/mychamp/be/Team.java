package mychamp.be;

import java.io.Serializable;

/**
 *
 * @author Thomas Meyer Hansen, Simon Juhl Birkedal, Stephan Fuhlendorff & Jacob
 * Enemark
 */
public class Team implements Serializable
{

    int id;
    String name;
    int played;
    int wins;
    int draws;
    int losses;
    int goalFor;
    int goalAgainst;
    int point;

    /**
     * The default constructor for generating a new team.
     *
     * @param name A team name, this name must be unique.
     */
    public Team(String name)
    {
        this.name = name;
        this.played = 0;
        this.wins = 0;
        this.draws = 0;
        this.losses = 0;
        this.goalFor = 0;
        this.goalAgainst = 0;
        this.point = 0;

    }

    /**
     * Gets the team's Id represented by an integer.
     *
     * @return Returns the id.
     */
    public int getId()
    {
        return id;
    }

    /**
     * Sets the team's Id.
     *
     * Note: The ID should not change once set.
     *
     * @param id The new ID for this team.
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * Gets the name of this team.
     *
     * @return Returns the team's name.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Sets the name of this team.
     *
     * @param name The name of the team represented by a String.
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Gets the amount of rounds played.
     *
     * @return Returns the amount of rounds played represented by an integer
     * value.
     */
    public int getPlayed()
    {
        return played;
    }

    /**
     * Sets the amount of rounds played.
     *
     * @param played An integer representing the amount of rounds played.
     */
    public void setPlayed(int played)
    {
        this.played = played;
    }

    /**
     * Gets the amount of wins this team has.
     *
     * @return Returns the amount of wins this team has, represented by an
     * integer.
     */
    public int getWins()
    {
        return wins;
    }

    /**
     * Sets the amount of wins this team has.
     *
     * @param wins The amount of wins, represented by an integer value.
     */
    public void setWins(int wins)
    {
        this.wins = wins;
    }

    /**
     * Gets the amount of draws this team has.
     *
     * @return Returns the amount of draws this team has, represented by an
     * integer.
     */
    public int getDraws()
    {
        return draws;
    }

    /**
     * Sets the amount of draws this team has.
     *
     * @param draws The amount of draws, represented by an integer value.
     */
    public void setDraws(int draws)
    {
        this.draws = draws;
    }

    /**
     * Gets the amount of losses rounds this team has.
     *
     * @return Returns the amount of losses rounds this team has, represented by
     * an integer.
     */
    public int getLosses()
    {
        return losses;
    }

    /**
     * Sets the amount of losses this team has.
     *
     * @param losses The amount of losses.
     */
    public void setLosses(int losses)
    {
        this.losses = losses;
    }

    /**
     *
     * @return
     */
    public int getGoalFor()
    {
        return goalFor;
    }

    /**
     *
     * @param goalFor
     */
    public void setGoalFor(int goalFor)
    {
        this.goalFor = goalFor;
    }

    /**
     *
     * @return
     */
    public int getGoalAgainst()
    {
        return goalAgainst;
    }

    /**
     *
     * @param goalAgainst
     */
    public void setGoalAgainst(int goalAgainst)
    {
        this.goalAgainst = goalAgainst;
    }

    /**
     * Gets the amount of points this team has.
     *
     * @return Returns the amount of points on this team, represented by an
     * integer.
     */
    public int getPoint()
    {
        return point;
    }

    /**
     * Sets the points this team has.
     */
    public void setPoint()
    {
        this.point = getWins() * 3 + getDraws();
    }

}