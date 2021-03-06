/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.xmlserver.impl;

import java.net.Socket;
import org.lib.business.LibraryFacadeInterface;
import org.lib.server.CreateClientTaskService;

/**
 *
 * @author danecek
 */
public class CreateXMLClientTask extends CreateClientTaskService {

    @Override
    public Runnable createClientTask(Socket socket, LibraryFacadeInterface facade) {
        return new XMLClientTask(socket, facade);

    }
    
}
