package mychamp.dal;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mychamp.be.Team;

/**
 * Read & Write binary data to a file.
 *
 * @author Thomas Meyer Hansen, Simon Juhl Birkedal, Stephan Fuhlendorff & Jacob
 * Enemark
 */
public class TeamDAO
{
    /**
     * Writes the given teams data as binary into the given fileName.
     * This fileName should contain the .dat extension.
     * @param teams The arraylist of teams to be saved as binary data.
     * @param fileName The fileName in which to save the data.
     * @throws IOException 
     */
    public void writeObjectData(ArrayList<Team> teams, String fileName)
    {
        try (FileOutputStream fos = new FileOutputStream(fileName))
        {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(teams);
        }
        catch (IOException ex)
        {
            Logger.getLogger(TeamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Reads the binary data from a file.
     * Note: The binary data being read must be an ArrayList of teams.
     * @param fileName The file name to read the data from.
     * Note: This file name must contain the .dat extension. e.g. Teams.dat.
     * @return Returns an array list of teams collected from the binary data file.
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public ArrayList<Team> readObjectData(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException
    {
        ArrayList<Team> teamList;

        try (FileInputStream fis = new FileInputStream(fileName))
        {
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bis);
            teamList = (ArrayList<Team>) ois.readObject();
        }

        return teamList;
    }
}
