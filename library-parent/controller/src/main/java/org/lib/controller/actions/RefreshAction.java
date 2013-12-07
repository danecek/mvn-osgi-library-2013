/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.controller.actions;

import java.awt.event.ActionEvent;
import org.lib.business.LibraryFacade;
import org.lib.utils.Messages;
import org.lib.view.MainFrame;

/**
 *
 * @author danecek
 */
public class RefreshAction extends AbstractLibraryAction {

    public RefreshAction() {
        super(Messages.Refresh.cm(), Messages.View.cm());
    }

    @Override
    public void setEnabled() {
        setEnabled(LibraryFacade.getDefault().isAvailable());
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        MainFrame.getInstance().refresh();
    }
}
