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
    Main_Frame;

    public String cm(Object ... args) {
        ResourceBundle bnd = ResourceBundle.getBundle("org.lib.utils.messages");
        try {
            String tmpl = bnd.getString(name());
            return MessageFormat.format(tmpl, args);
        } catch (MissingResourceException | IllegalArgumentException ex) {
            Logger.getLogger(getClass().getName()).log(Level.WARNING, "", ex);
            return name().replace('_', ' ');
        }
    }
}
