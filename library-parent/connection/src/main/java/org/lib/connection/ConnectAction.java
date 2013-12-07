/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.connection;

import java.awt.event.ActionEvent;
import org.lib.connection.impl.ConnectionDialog;
import org.lib.controller.actions.AbstractLibraryAction;

/**
 *
 * @author danecek
 */
public class ConnectAction extends AbstractLibraryAction {

    public ConnectAction() {
        super("Connect", "Connection");
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
