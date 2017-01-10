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
 * Read & Write binary data to a file.
 *
 * @author Thomas Meyer Hansen, Simon Juhl Birkedal, Stephan Fuhlendorff & Jacob
 * Enemark
 */
public class TeamDAO
{
    protected final String fileName;
    
    public TeamDAO(String fileName)
    {
       this.fileName = fileName+".txtdat";
    }
    
    public void addTeam(Team team) throws IOException
    {
        try (BufferedWriter bw
                = new BufferedWriter(
                        new FileWriter(fileName, true)))
        {
            bw.append(team.getId() + "," + team.getName() +"," + team.getPlayed() 
                    + "," + team.getWins() + "," + team.getDraws() + "," + team.getLosses() 
                    + "," + team.getGoalFor() + "," + team.getGoalAgainst() + "," + team.getPoint());
            bw.newLine();
        }
    }


    public List<Team> getAll() throws IOException
    {
        List<Team> teamList = new ArrayList();

        try (BufferedReader br
                = new BufferedReader(
                        new FileReader(fileName)))
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

    public void saveAll(List<Team> teams) throws IOException
    {
    RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
        raf.setLength(0);
        try (BufferedWriter bw
                = new BufferedWriter(
                        new FileWriter(fileName, true)))
        {
            for (Team team : teams)
            {

                bw.append(team.getId() + "," + team.getName() +"," + team.getPlayed() 
                    + "," + team.getWins() + "," + team.getDraws() + "," + team.getLosses() 
                    + "," + team.getGoalFor() + "," + team.getGoalAgainst() + "," + team.getPoint());
                bw.newLine();
            }
        }
    }
 
}
