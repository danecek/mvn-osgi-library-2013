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

    Library;

    public String eval(Object... args) {
        ResourceBundle b = ResourceBundle.getBundle("org.lib.utils.messages");
        try {
            return MessageFormat.format(b.getString(name()), args);
        } catch (MissingResourceException | IllegalArgumentException ex) {
            Logger.getLogger(getClass().getName()).log(Level.WARNING, ex.toString());
            return name().replace('_', ' ');
        }
    }
}
