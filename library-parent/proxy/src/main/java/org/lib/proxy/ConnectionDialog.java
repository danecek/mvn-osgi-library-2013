/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.proxy;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.swing.JLabel;
import org.lib.connection.ConnectionService;
import org.lib.controller.dialogs.AbstractLibraryDialog;
import org.lib.protocol.LibraryCommand;
import org.lib.utils.GBCBuilder;
import org.lib.utils.LibraryException;
import org.lib.utils.Messages;
import org.lib.utils.ValidatedTF;
import org.lib.view.MainFrame;

/**
 *
 * @author danecek
 */
public final class ConnectionDialog extends AbstractLibraryDialog {

    private ValidatedTF host;
    private ValidatedTF port;

    public ConnectionDialog() {
        super(Messages.Connection_Dialog.cm());
        getContent().setLayout(new GridBagLayout());
        getContent().add(new JLabel(Messages.Host.cm() + ": "),
                new GBCBuilder().build());
        getContent().add(host = new ValidatedTF(this, "localhost"),
                new GBCBuilder().setXRel().build());
        getContent().add(new JLabel(Messages.Port.cm() + ": "),
                new GBCBuilder().setY(1).build());
        getContent().add(port = new ValidatedTF(this, Integer.toString(LibraryCommand.PORT)),
                new GBCBuilder().setY(1).setX(GridBagConstraints.RELATIVE).build());
        validateDialog();
        pack();
        setVisible(true);
    }

    @Override
    public void okAction() {
        try {
            ConnectionService.getDefault().connect(InetAddress.getByName(host.getText()), Integer.parseInt(port.getText()));
            MainFrame.getInstance().refresh();
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
            error(Messages.Invalid_port.cm());
            return false;
        }
        clearError();
        return true;
    }
}
