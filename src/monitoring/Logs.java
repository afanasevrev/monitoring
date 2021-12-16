/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monitoring;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author rewoly
 */
public class Logs {
    
    File log = new File("logs.txt");
    Date date = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("E dd.MM.yyyy HH:mm:ss");
    
    
    public void setWriter(String serverName, String ipAddress) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(log, true))) {
            String lineSeparator = System.getProperty("line.separator");
            writer.write(dateFormat.format(date) + " сервер "+ serverName + "(" + ipAddress + ")" + " не отвечает" + lineSeparator);
            writer.flush();
        }
    }
}
