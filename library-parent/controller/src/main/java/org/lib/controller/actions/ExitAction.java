/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.controller.actions;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import org.lib.view.MainFrame;

/**
 *
 * @author danecek
 */
public class ExitAction extends AbstractLibraryAction {
    

    public ExitAction() {
        super("Exit", "File"); // todo
        this.putValue(AbstractAction.SHORT_DESCRIPTION, "Ukonceni");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        MainFrame.getInstance().exit();
    }
    

}
