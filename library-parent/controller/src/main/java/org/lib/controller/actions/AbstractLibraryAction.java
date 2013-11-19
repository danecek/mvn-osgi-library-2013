/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.controller.actions;

import javax.swing.AbstractAction;
import org.lib.view.MainFrame;
import org.lib.view.SetEnable;

/**
 *
 * @author danecek
 */
public abstract class AbstractLibraryAction extends AbstractAction implements 
        SetEnable{

    public AbstractLibraryAction(String string) {
        super(string);
        MainFrame.addTestEnable(this);
    }

    @Override
    public void setEnable() {
    }
    
    

}
