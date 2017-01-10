package mychamp.be;

import java.io.Serializable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Thomas Meyer Hansen, Simon Juhl Birkedal, Stephan Fuhlendorff & Jacob
 * Enemark
 */
public class Team implements Serializable {
    private final IntegerProperty id;
    private final StringProperty name;
    private final IntegerProperty played;
    private final IntegerProperty wins;
    private final IntegerProperty draws;
    private final IntegerProperty losses;
    private final IntegerProperty goalFor;
    private final IntegerProperty goalAgainst;
    private final IntegerProperty point;


    /**
     * The default constructor for generating a new team.
     *
     * @param id
     * @param name A team name, this name must be unique.
     * @param played
     * @param wins
     * @param draws
     * @param losses
     * @param goalAgainst
     * @param goalFor
     * @param point
     */
    public Team(int id, String name, int played, int wins, int draws, int losses, int goalFor, int goalAgainst, int point)
    {
        this.point = new SimpleIntegerProperty();
        this.goalAgainst = new SimpleIntegerProperty();
        this.goalFor = new SimpleIntegerProperty();
        this.losses = new SimpleIntegerProperty();
        this.draws = new SimpleIntegerProperty();
        this.wins = new SimpleIntegerProperty();
        this.played = new SimpleIntegerProperty();
        this.name = new SimpleStringProperty();
        this.id = new SimpleIntegerProperty();
        this.name.set(name);
        this.played.set(played);
        this.wins.set(wins);
        this.draws.set(draws);
        this.losses.set(losses);
        this.goalFor.set(goalFor);
        this.goalAgainst.set(goalAgainst);
        this.point.set(point);

    }


    /**
     * Sets the team's Id.
     *
     * Note: The ID should not change once set.
     *
     * @param id The new ID for this team.
     */
//    public void setId(int id)
//    {
//        this.id = id;
//    }

        /**
     * Gets the team's Id represented by an integer.
     *
     * @return Returns the id.
     */
    public int getId()
    {
        return id.get();
    }

    public void setId(int value)
    {
        id.set(value);
    }
    
        /**
     *
     * @return
     */
    public int getGoalAgainst()
    {
        return goalAgainst.get();
    }

        /**
     *
     * @param goalAgainst
     */
    public void setGoalAgainst(int value)
    {
        goalAgainst.set(value);
    }

    public IntegerProperty goalAgainstProperty()
    {
        return goalAgainst;
    }

    public int getGoalFor()
    {
        return goalFor.get();
    }

        /**
     *
     * @param goalFor
     */
    public void setGoalFor(int value)
    {
        goalFor.set(value);
    }

    public IntegerProperty goalForProperty()
    {
        return goalFor;
    }

    
    /**
     * Gets the amount of losses rounds this team has.
     *
     * @return Returns the amount of losses rounds this team has, represented by
     * an integer.
     */
    public int getLosses()
    {
        return losses.get();
    }

        /**
     * Sets the amount of losses this team has.
     *
     * @param losses The amount of losses.
     */
    public void setLosses(int value)
    {
        losses.set(value);
    }

    public IntegerProperty lossesProperty()
    {
        return losses;
    }

    /**
     * Gets the amount of draws this team has.
     *
     * @return Returns the amount of draws this team has, represented by an
     * integer.
     */
    public int getDraws()
    {
        return draws.get();
    }

    
    /**
     * Sets the amount of draws this team has.
     *
     * @param draws The amount of draws, represented by an integer value.
     */
    public void setDraws(int value)
    {
        draws.set(value);
    }

    public IntegerProperty drawsProperty()
    {
        return draws;
    }

    /**
     * Gets the amount of wins this team has.
     *
     * @return Returns the amount of wins this team has, represented by an
     * integer.
     */
    public int getWins()
    {
        return wins.get();
    }

    /**
     * Sets the amount of wins this team has.
     *
     * @param wins The amount of wins, represented by an integer value.
     */
    public void setWins(int value)
    {
        wins.set(value);
    }

    public IntegerProperty winsProperty()
    {
        return wins;
    }

    /**
     * Gets the amount of rounds played.
     *
     * @return Returns the amount of rounds played represented by an integer
     * value.
     */
    public int getPlayed()
    {
        return played.get();
    }

    /**
     * Sets the amount of rounds played.
     *
     * @param played An integer representing the amount of rounds played.
     */
    public void setPlayed(int value)
    {
        played.set(value);
    }

    public IntegerProperty playedProperty()
    {
        return played;
    }

    /**
     * Gets the name of this team.
     *
     * @return Returns the team's name.
     */
    public String getName()
    {
        return name.get();
    }

    /**
     * Sets the name of this team.
     *
     * @param name The name of the team represented by a String.
     */
    public void setName(String value)
    {
        name.set(value);
    }

    public StringProperty nameProperty()
    {
        return name;
    }

    /**
     * Gets the amount of points this team has.
     *
     * @return Returns the amount of points on this team, represented by an
     * integer.
     */
    public int getPoint()
    {
        return point.get();
    }

    /**
     * Sets the points this team has.
     */
    public void setPoint()
    {
        point.set(wins.get()*3+draws.get());
    }

    public IntegerProperty pointProperty()
    {
        return point;
    }

}
