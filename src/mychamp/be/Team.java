package mychamp.be;

import java.io.Serializable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
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
    private final BooleanProperty isDeleted;

    private final IntegerProperty semiScore;
    private final IntegerProperty finalScore;
    private final IntegerProperty quarterScore;

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
    public Team(int id, String name, int played, int wins, int draws, int losses,
            int goalFor, int goalAgainst, int point, int quarterScore, int semiScore, int finalScore)
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
        this.isDeleted = new SimpleBooleanProperty();
        this.name.set(name);
        this.played.set(played);
        this.wins.set(wins);
        this.draws.set(draws);
        this.losses.set(losses);
        this.goalFor.set(goalFor);
        this.goalAgainst.set(goalAgainst);
        this.point.set(point);
        this.isDeleted.set(false);

        this.quarterScore = new SimpleIntegerProperty();
        this.semiScore = new SimpleIntegerProperty();
        this.finalScore = new SimpleIntegerProperty();
        this.quarterScore.set(quarterScore);
        this.semiScore.set(semiScore);
        this.finalScore.set(finalScore);

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
    public void setGoalAgainst(int goalAgainst)
    {
        this.goalAgainst.set(goalAgainst);
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
    public void setGoalFor(int goalFor)
    {
        this.goalFor.set(goalFor);
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
    public void setLosses(int losses)
    {
        this.losses.set(losses);
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
    public void setDraws(int draws)
    {
        this.draws.set(draws);
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
    public void setWins(int wins)
    {
        this.wins.set(wins);
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
    public void setPlayed(int played)
    {
        this.played.set(played);
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
    public void setName(String name)
    {
        this.name.set(name);
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
        if (isDeleted.get())
        {
            point.set(0);
        }
        else
        {
            point.set(wins.get() * 3 + draws.get());
        }
    }

    public IntegerProperty pointProperty()
    {
        return point;
    }

    public BooleanProperty getIsDeleted()
    {
        return isDeleted;
    }

    public void setIsDeleted(boolean deleted)
    {
        this.isDeleted.set(deleted);
    }

    public int getQuarterScore()
    {
        return quarterScore.get();
    }

    public void setQuarterScore(int value)
    {
        quarterScore.set(value);
    }

    public IntegerProperty quarterScoreProperty()
    {
        return quarterScore;
    }

    public int getFinalScore()
    {
        return finalScore.get();
    }

    public void setFinalScore(int value)
    {
        finalScore.set(value);
    }

    public IntegerProperty finalScoreProperty()
    {
        return finalScore;
    }

    public int getSemiScore()
    {
        return semiScore.get();
    }

    public void setSemiScore(int value)
    {
        semiScore.set(value);
    }

    public IntegerProperty semiScoreProperty()
    {
        return semiScore;
    }

}
