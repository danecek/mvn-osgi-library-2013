/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.proxy;

import java.awt.GridLayout;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.swing.JLabel;
import org.lib.connection.ConnectionService;
import org.lib.controller.dialogs.AbstractLibraryDialog;
import org.lib.protocol.LibraryCommand;
import org.lib.utils.LibraryException;
import org.lib.utils.ValidatedTF;
import org.lib.view.MainFrame;

/**
 *
 * @author danecek
 */
public final class ConnectionDialog extends AbstractLibraryDialog {

    ValidatedTF host;
    ValidatedTF port;

    public ConnectionDialog() {
        super("Connection Dialog");
        getContent().setLayout(new GridLayout(2, 2));
        getContent().add(new JLabel("Host: "));
        getContent().add(host = new ValidatedTF(this, "localhost"));
        getContent().add(new JLabel("Port: "));
        getContent().add(port = new ValidatedTF(this, Integer.toString(LibraryCommand.PORT)));
        validateDialog();
        pack();
        setVisible(true);
    }

    @Override
    public void okAction() {
        try {
            ConnectionService.getDefault().connect(InetAddress.getByName(host.getText()), Integer.parseInt(port.getText()));
             MainFrame.getInstance().notifyActions();
        } catch (UnknownHostException | LibraryException ex) {
            MainFrame.getInstance().showError(ex);
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
            error("invalid port ");
            return false;
        }

        clearError();
        return true;
    }
}
