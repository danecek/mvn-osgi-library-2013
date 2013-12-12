/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.proxy.impl;

import java.awt.GridLayout;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lib.connection.ConnectionService;
import org.lib.controller.dialogs.AbstractLibraryDialog;
import org.lib.utils.LibraryException;
import org.lib.utils.Messages;
import org.lib.utils.ValidatedTF;

/**
 *
 * @author danecek
 */
public class ConnectDialog extends AbstractLibraryDialog {

    ValidatedTF host;
    ValidatedTF port;

    public ConnectDialog() {
        super(Messages.Connect_Dialog.cm());
        getContent().setLayout(new GridLayout(0, 2));
        getContent().add(host = new ValidatedTF(this, "localhost"));
        getContent().add(port =
                new ValidatedTF(this, String.valueOf(ConnectionService.DEFAULT_PORT)));
        pack();
        setVisible(true);
    }

    @Override
    public void okAction() throws LibraryException {
        try {
            ConnectionService.getDefault().connect(InetAddress.getByName(host.getText()),
                    Integer.parseInt(port.getText()));
        } catch (UnknownHostException ex) {
            Logger.getLogger(ConnectDialog.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public boolean validateDialog() {
        try {
            InetAddress.getByName(host.getText());
        } catch (UnknownHostException ex) {
            error(ex.toString());
            return false;
        }
        try {
            Integer.parseInt(port.getText());
        } catch (NumberFormatException ex) {
            error(ex.toString());
            return false;

        }
        clearError();
        return true;
    }
}
