/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.integration.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lib.integration.ReaderDAO;
import org.lib.model.Address;
import org.lib.model.Reader;
import org.lib.model.ReaderId;
import org.lib.utils.LibraryException;

/**
 *
 * @author danecek
 */
public class ReaderDAOImpl implements ReaderDAO {

    private static int keyCount;
    Map<ReaderId, Reader> readers = new ConcurrentHashMap<ReaderId, Reader>();

    public ReaderDAOImpl() {
        try {
            create("Novak", new Address("Praha"));
            create("Svoboda", new Address("Praha"));
        } catch (LibraryException ex) {
            Logger.getLogger(ReaderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Reader create(String name, Address address) throws LibraryException {
        Reader r = new Reader(new ReaderId(++keyCount), name, address);
        readers.put(r.getId(), r);
        return r;
    }

    public void delete(ReaderId id) throws LibraryException {
        readers.remove(id);
    }

    public void update(Reader reader) throws LibraryException {
        readers.put(reader.getId(), reader);
    }

    public Reader find(ReaderId id) throws LibraryException {
        return readers.get(id);
    }

    public Collection<Reader> getAll() throws LibraryException {
        return new ArrayList<Reader>(readers.values());
    }
}
