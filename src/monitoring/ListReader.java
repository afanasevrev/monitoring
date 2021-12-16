/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monitoring;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author rewoly
 */
public class ListReader {
    /**
     *
     */
    public HashMap<String, String> list = new HashMap();
    
    public void getRead() throws FileNotFoundException, IOException {
        String[] splitLine;
        File file = new File("list.txt");
        FileReader filereader = new FileReader(file);
        BufferedReader reader = new BufferedReader(filereader);
        String line;
        while((line = reader.readLine()) != null) {
            String serverName = "";
            splitLine = line.split("\\s+");
            if (splitLine.length > 2) {
            for(int i = 1; i < splitLine.length; i++) serverName = serverName + splitLine[i] + " ";
            list.put(serverName, splitLine[0]);
            } else list.put(splitLine[1], splitLine[0]);
        }
    }
}
