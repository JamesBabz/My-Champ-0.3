package mychamp.dal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import mychamp.be.Group;

/**
 *
 * @author Simon Birkedal
 */
public class RoundDAO
{
    private final String fileName;
    
    public RoundDAO(String fileName)
    {
        this.fileName = fileName + ".txt";
    }
    
    public void saveRoundData(List<Group> groups) throws FileNotFoundException, IOException
    {
        RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
        raf.setLength(0);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true)))
        {
            for (Group group : groups)
            {

                bw.append("" + group.getCurrentRound());
                bw.newLine();
            }
        }
    }
    
    public List<Integer> loadRoundData() throws FileNotFoundException, IOException
    {
        List<Integer> roundList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName)))
        {
            Scanner scanner = new Scanner(br);
            while (scanner.hasNext())
            {
                String line = scanner.nextLine();
                roundList.add(
                    Integer.parseInt(line)
                );
            }
        }
        return roundList;
    }
}