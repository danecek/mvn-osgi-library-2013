/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.controller;

import javax.swing.JMenu;
import org.lib.controller.actions.CreateBookAction;
import org.lib.controller.actions.DeleteBookAction;

/**
 *
 * @author danecek
 */
public class BookMenu extends JMenu{

    public BookMenu() {
        super("Book");  // todo
        add(CreateBookAction.getInstance());
        add(DeleteBookAction.getInstance());
    }
    
    
}
