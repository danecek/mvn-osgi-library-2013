/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.connection;

import java.awt.event.ActionEvent;
import org.lib.controller.actions.AbstractLibraryAction;
import org.lib.view.MainFrame;

/**
 *
 * @author danecek
 */
public class RefreshAction extends AbstractLibraryAction {

    public RefreshAction() {
        super("Refresh", "Connection");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        MainFrame.getInstance().refresh();
    }
}
