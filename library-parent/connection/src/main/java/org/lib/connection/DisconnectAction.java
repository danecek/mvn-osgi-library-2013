/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.connection;

import java.awt.event.ActionEvent;
import org.lib.controller.actions.AbstractLibraryAction;

/**
 *
 * @author danecek
 */
public class DisconnectAction extends AbstractLibraryAction {

    public DisconnectAction() {
        super("Disconnect", "Connection");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        ConnectionService.getDefault().disconnect();
    }

    @Override
    public void setEnabled() {
        setEnabled(ConnectionService.getDefault().isConnected());
    }
}
