package mychamp.dal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import mychamp.be.Team;

/**
 * This class allows for the teams data to be written as a string in to a .txt
 * document. Once a .txt file is generated, data can be retrieved from it.
 *
 * @author Thomas Meyer Hansen, Simon Juhl Birkedal, Stephan Fuhlendorff & Jacob
 * Enemark
 */
public class TeamDAO
{
    protected final String fileName;

    /**
     * Default constructor for this class.
     *
     * @param fileName The filename of which to store and load data from. NOTE:
     * You must exclude the extension.
     */
    public TeamDAO(String fileName)
    {
        this.fileName = fileName + ".txt";
    }

    /**
     * Appends the team data to the file.
     *
     * @param team The team.
     * @throws IOException
     */
    public void addTeam(Team team) throws IOException
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true)))
        {
            bw.append(team.getId()
                    + "," + team.getName()
                    + "," + team.getPlayed()
                    + "," + team.getWins()
                    + "," + team.getDraws()
                    + "," + team.getLosses()
                    + "," + team.getGoalFor()
                    + "," + team.getGoalAgainst()
                    + "," + team.getPoint());
            bw.newLine();
        }
    }

    /**
     * Retrieves the team data stored in the .txt file generated by this class.
     *
     * @return @throws IOException
     */
    public List<Team> getTeamData() throws IOException
    {
        List<Team> teamList = new ArrayList();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName)))
        {
            Scanner scanner = new Scanner(br);
            while (scanner.hasNext())
            {
                String line = scanner.nextLine();
                String[] fields = line.split(",");
                teamList.add(
                        new Team(
                                Integer.parseInt(fields[0].trim()),
                                fields[1].trim(),
                                Integer.parseInt(fields[2].trim()),
                                Integer.parseInt(fields[3].trim()),
                                Integer.parseInt(fields[4].trim()),
                                Integer.parseInt(fields[5].trim()),
                                Integer.parseInt(fields[6].trim()),
                                Integer.parseInt(fields[7].trim()),
                                Integer.parseInt(fields[8].trim())
                        ));
            }
        }
        return teamList;
    }

    /**
     * Saves all the teams's data into a file with a .txt extension.
     *
     * @param teams The list of teams to be saved.
     * @throws IOException
     */
    public void saveTeamData(List<Team> teams) throws IOException
    {
        RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
        raf.setLength(0);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true)))
        {
            for (Team team : teams)
            {

                bw.append(team.getId()
                        + "," + team.getName()
                        + "," + team.getPlayed()
                        + "," + team.getWins()
                        + "," + team.getDraws()
                        + "," + team.getLosses()
                        + "," + team.getGoalFor()
                        + "," + team.getGoalAgainst()
                        + "," + team.getPoint());
                bw.newLine();
            }
        }
    }

}
