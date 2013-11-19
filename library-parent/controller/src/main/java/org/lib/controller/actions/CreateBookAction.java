/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.controller.actions;

import java.awt.event.ActionEvent;
import org.lib.controller.dialogs.CreateBookDialog;

/**
 *
 * @author danecek
 */
public class CreateBookAction extends AbstractLibraryAction {
    
    private CreateBookAction() {
        super("Create Book"); // todo
    }
    
    public static CreateBookAction getInstance() {
        return CreateBookActionHolder.INSTANCE;
    }
    
    private static class CreateBookActionHolder {
        private static final CreateBookAction INSTANCE = 
                new CreateBookAction();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        new CreateBookDialog();
    }

    
}
