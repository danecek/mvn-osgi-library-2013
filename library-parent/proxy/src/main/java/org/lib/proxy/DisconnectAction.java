/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.proxy;

import java.awt.event.ActionEvent;
import org.lib.connection.ConnectionService;
import org.lib.controller.actions.AbstractLibraryAction;
import org.lib.view.MainFrame;

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
        MainFrame.getInstance().notifyActions();
    }

    @Override
    public void setEnabled() {
        setEnabled(ConnectionService.getDefault().isConnected());
    }
}
