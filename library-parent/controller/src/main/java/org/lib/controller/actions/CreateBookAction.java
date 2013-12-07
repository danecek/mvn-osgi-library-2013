/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.controller.actions;

import java.awt.event.ActionEvent;
import org.lib.business.LibraryFacade;
import org.lib.controller.dialogs.CreateBookDialog;
import org.lib.utils.Messages;

/**
 *
 * @author danecek
 */
public class CreateBookAction extends AbstractLibraryAction {

    public CreateBookAction() {
        super(Messages.Create_Book.cm(), Messages.Book.cm());
    }

    @Override
    public void setEnabled() {
        setEnabled(LibraryFacade.getDefault().isAvailable());
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        new CreateBookDialog();
    }
}
