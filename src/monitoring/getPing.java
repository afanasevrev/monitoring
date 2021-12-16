/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monitoring;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author rewoly
 */
public class getPing {
    
    public boolean simplePing(String ipAddress) throws IOException {
        if (ipAddress == null) return false;
        else {
        try {
            InetAddress inet = InetAddress.getByName(ipAddress);   
            return (inet.isReachable(1000)) ; 
        } catch (UnknownHostException ex) {
            System.out.println("Exception:" + ex.getMessage());
        }
        return false; 
    }
    }
}
