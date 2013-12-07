/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.proxy;

import java.awt.event.ActionEvent;
import org.lib.connection.ConnectionService;
import org.lib.controller.actions.AbstractLibraryAction;
import org.lib.utils.Messages;

/**
 *
 * @author danecek
 */
public class ConnectAction extends AbstractLibraryAction {

    public ConnectAction() {
        super(Messages.Connect.cm(), Messages.Connection.cm());
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        new ConnectionDialog();
    }

    @Override
    public void setEnabled() {
      setEnabled(!ConnectionService.getDefault().isConnected());
    }   
    
}
