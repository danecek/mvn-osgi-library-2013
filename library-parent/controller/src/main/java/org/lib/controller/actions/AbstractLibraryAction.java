/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.controller.actions;

import java.util.ArrayList;
import java.util.Collection;
import javax.swing.AbstractAction;

/**
 *
 * @author danecek
 */
public abstract class AbstractLibraryAction extends AbstractAction {

    static Collection<AbstractLibraryAction> actions = new ArrayList<>();

    public AbstractLibraryAction(String string) {
        super(string);
        actions.add(this);
    }

    public abstract boolean shouldEnabled();

    public static void notifyActions() {
        for (AbstractLibraryAction la : actions) {
            la.setEnabled(la.shouldEnabled());
        }
    }
}
