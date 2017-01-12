package mychamp.dal;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import mychamp.be.Match;

/**
 * Class which reads and writes matches from and to a file.
 *
 * @author Stephan Fuhlendorff, Jacob Enemark, Thomas Hansen, Simon Birkedal
 */
public class MatchDAO
{
    /**
     * Writes an object to a file, in this case the object must be an arraylist
     * of Matches.
     *
     * @param songs The matches to be saved.
     * @param fileName The name of the file to save the matches in.
     * @throws IOException
     */
    public void writeObjectData(ArrayList<Match> matches, String fileName) throws IOException
    {
        try (FileOutputStream fos = new FileOutputStream(fileName))
        {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            oos.writeObject(matches);
        }
    }

    /**
     * Reads an object from a file, the object must be an arraylist of matches.
     *
     * @param fileName The fileName to read the information from.
     * @return Returns a new array containing all the stored data.
     * @throws FileNotFoundException
     */
    public ArrayList<Match> readObjectData(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException
    {
        ArrayList<Match> matchList = new ArrayList<Match>();

        try (FileInputStream fis = new FileInputStream(fileName))
        {
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bis);
            matchList = (ArrayList<Match>) ois.readObject();
        }

        return matchList;
    }
}
