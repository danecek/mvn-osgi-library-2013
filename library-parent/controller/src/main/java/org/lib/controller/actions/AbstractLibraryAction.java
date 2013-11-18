/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.controller.actions;

import java.util.ArrayList;
import java.util.Collection;
import javax.swing.AbstractAction;
import org.lib.view.MainFrame;
import org.lib.view.TestEnable;

/**
 *
 * @author danecek
 */
public abstract class AbstractLibraryAction extends AbstractAction implements TestEnable{

    public AbstractLibraryAction(String string) {
        super(string);
        MainFrame.addTestEnable(this);
    }

}
