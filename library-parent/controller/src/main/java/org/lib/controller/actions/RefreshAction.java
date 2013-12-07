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
public class RefreshAction extends AbstractLibraryAction {
    

    public RefreshAction() {
        super("Refresh", "File"); // todo
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        MainFrame.getInstance().refresh();
    }
    

}
