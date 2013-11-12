/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.view.impl;

import org.lib.utils.LibraryException;

/**
 *
 * @author danecek
 */
public interface Refreshable {

    void refresh() throws LibraryException;
    
}
