/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.integration;


import java.util.Collection;
import org.lib.model.Address;
import org.lib.model.Reader;
import org.lib.model.ReaderId;
import org.lib.utils.LibraryException;

/**
 *
 * @author danecek
 */
public interface ReaderDAO {

    void create(String name, Address address) throws LibraryException;

    void delete(ReaderId id) throws LibraryException;

    void update(Reader r) throws LibraryException;

    Reader find(ReaderId id) throws LibraryException;

    Collection<Reader> getAll() throws LibraryException;
}
