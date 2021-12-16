/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monitoring;

import java.awt.Color;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;

/**
 *
 * @author rewoly
 */

//Класс для пинг запроса
public class getTimerPing extends TimerTask {
    
    JButton button;     // Здесь кнопка используется для окрашивания
    String ipAddress;   // IP - адрес сервера, которому будем посылать пинг запрос
    String serverName;  // Имя сервера, которму будем посылать пинг запрос
    Logs log;           // Создаем объект для логов
    SoftReference<Logs> logRef = new SoftReference<>(log); // Отмечаем объект класса Logs как мягкую ссылку
    
    
    public getTimerPing(JButton button, String ipAddress, String serverName) {
        this.button = button;
        this.ipAddress = ipAddress;
        this.serverName = serverName;
    }
    
    @Override
    public void run() {
        try {
            getTimePing();
        } catch (IOException ex) {
            Logger.getLogger(getTimerPing.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getTimePing() throws IOException {
        if (this.ipAddress == null) this.button.setBackground(Color.red);
        else {
        try {
            InetAddress inet = InetAddress.getByName(this.ipAddress);   
            if (inet.isReachable(1000)) this.button.setBackground(Color.green);
            else { 
                log = new Logs();
                log.setWriter(this.serverName, this.ipAddress);
                log = null;
                log = logRef.get();
                logRef.clear();
                this.button.setBackground(Color.red); 
            }
        } catch (UnknownHostException ex) {
            System.out.println("Exception:" + ex.getMessage());
        }
    }
    }
}
