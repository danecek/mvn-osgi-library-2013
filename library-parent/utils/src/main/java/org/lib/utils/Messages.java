/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.utils;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author danecek
 */
public enum Messages {

    Id,
    Name,
    Address,
    Title,
    Exit,
    File,
    Main_Frame,
    No_conection,
    Create_Book,
    Book,
    Delete_Books,
    No_selected_book,
    Connection_Dialog,
    Invalid_port,
    Books,
    Readers,
    Cancel,
    No_connection,
    Error,
    Exit_application,
    Empty_title,
    Refresh,
    View,
    OK,
    Connect,
    Connect_Dialog,
    Connection,
    Disconnect,
    Host,
    Port;

    public String cm(Object... args) {
        ResourceBundle bnd = ResourceBundle.getBundle("org.lib.utils.messages");
        try {
            String tmpl = bnd.getString(name());
            return MessageFormat.format(tmpl, args);
        } catch (MissingResourceException | IllegalArgumentException ex) {
            Logger.getLogger(getClass().getName()).log(Level.WARNING, "Missing resource: " + name());
            return name().replace('_', ' ');
        }
    }
}