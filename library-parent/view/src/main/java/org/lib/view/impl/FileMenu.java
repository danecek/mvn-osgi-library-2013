/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.view.impl;

import javax.swing.JMenu;
import org.lib.controller.actions.CreateBookAction;
import org.lib.controller.actions.ExitAction;
import static org.lib.utils.Messages.*;

/**
 *
 * @author danecek
 */
public class FileMenu extends JMenu {
    
    public FileMenu() {
        super(File.cm());
        add(ExitAction.getInstance());
        add(CreateBookAction.getInstance());
    }
}
