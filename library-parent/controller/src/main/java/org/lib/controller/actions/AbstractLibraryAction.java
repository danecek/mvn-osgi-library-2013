/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.controller.actions;

import javax.swing.AbstractAction;
import org.lib.utils.LibraryAction;

/**
 *
 * @author danecek
 */
public abstract class AbstractLibraryAction extends AbstractAction implements
        LibraryAction {

    private String menuName;

    public AbstractLibraryAction(String actionName, String menuName) {
        super(actionName);
        this.menuName = menuName;
    }

    @Override
    public void setEnabled() {
    }

    /**
     * @return the menuName
     */
    @Override
    public String getMenuName() {
        return menuName;
    }
}
