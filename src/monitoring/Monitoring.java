/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monitoring;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.Timer;

/**
 *
 * @author rewoly
 */
public final class Monitoring extends JFrame {
   
    ListReader text = new ListReader();
    getPing ping = new getPing();
    Timer timer = new Timer(true);
    
    public Monitoring() {
        initcomponents();
    }
    
    public void initcomponents(){
        try {
            text.getRead();
        } catch (IOException ex) {
            Logger.getLogger(Monitoring.class.getName()).log(Level.SEVERE, null, ex);
        }
        frame = new JFrame();
        frame.setVisible(true);
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Мониторинг удаленных серверов");
        Font bigFont = new Font("TimesRoman", Font.BOLD, 30);
        panel = new JPanel();
        
        for (String re : text.list.keySet()) {
            button = new JButton(re);
            button.setBackground(Color.yellow);
            button.setFont(bigFont);
            button.addActionListener(this::jbuttonActionPerformed);
            panel.add(button);
            getTimerPing timerPing = new getTimerPing(button, text.list.get(re), re);
            timer.scheduleAtFixedRate(timerPing, 1000, 10 * 6000);
        }
        frame.add(panel, BorderLayout.CENTER);
    }
    
    public void jbuttonActionPerformed(java.awt.event.ActionEvent evt) {                                         
       Object ob = evt.getSource();
       String st = evt.getActionCommand();
       JButton buttonLocal = (JButton) ob;
        try {
            if (ping.simplePing(text.list.get(st)))
                buttonLocal.setBackground(Color.green);
            else buttonLocal.setBackground(Color.red);
        } catch (IOException ex) {
            Logger.getLogger(Monitoring.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private javax.swing.JFrame frame;
    private javax.swing.JPanel panel;
    private javax.swing.JButton button;
    
    public static void main (String[] args) throws IOException {
        Monitoring monitoring = new Monitoring();
    } 
}
