/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychamp.be;

/**
 *
 * @author James
 */
public class Match
{

    Team homeTeam;
    Team awayTeam;
    int homeScore;
    int awayScore;

    public Match(int round, Team homeTeam, Team awayTeam)
    {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    public Team getHomeTeam()
    {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam)
    {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam()
    {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam)
    {
        this.awayTeam = awayTeam;
    }

    public int getHomeScore()
    {
        return homeScore;
    }

    public void setHomeScore(int homeScore)
    {
        this.homeScore = homeScore;
    }

    public int getAwayScre()
    {
        return awayScore;
    }

    public void setAwayScore(int awayScore)
    {
        this.awayScore = awayScore;
    }

}
