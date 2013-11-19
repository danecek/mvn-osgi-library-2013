/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.proxy.impl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lib.protocol.LibraryCommand;
import org.lib.utils.LibraryException;

/**
 *
 * @author danecek
 */
public class Connection {
    
    
    public void connect() {
        
    }
    
    public void disconnect() {
        
    }
    

    private Connection() {
    }

    public static Connection getInstance() {
        return ConnectionHolder.INSTANCE;
    }

    private static class ConnectionHolder {

        private static final Connection INSTANCE = new Connection();
    }
    
    ObjectOutputStream oos;
    ObjectInputStream ois;

    Object send(LibraryCommand libraryCommand) throws LibraryException {
        try {
            oos.writeObject(libraryCommand);
            oos.flush();
            Object response = ois.readObject();
            if (response instanceof LibraryException) {
                throw (LibraryException) response;
            }
            return response;
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.INFO, null, ex);
            throw new LibraryException(ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }



    }
}
