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
        SetEnable {

    private String menuName;

    public AbstractLibraryAction(String actionName, String menuName) {
        super(actionName);
        this.menuName = menuName;
    }

    @Override
    public void setEnable() {
    }

    /**
     * @return the menuName
     */
    public String getMenuName() {
        return menuName;
    }
}
