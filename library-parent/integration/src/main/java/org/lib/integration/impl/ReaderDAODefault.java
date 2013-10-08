/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.integration.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.lib.integration.ReaderDAO;
import org.lib.model.Address;
import org.lib.model.Reader;
import org.lib.model.ReaderId;
import org.lib.utils.LibraryException;

/**
 *
 * @author danecek
 */
public class ReaderDAODefault implements ReaderDAO {

    static int idcount;
    Map<ReaderId, Reader> readers = new ConcurrentHashMap<ReaderId, Reader>();

    public void create(String name, Address address) throws LibraryException {
        ReaderId ri = new ReaderId(++idcount);
        readers.put(ri, new Reader(ri, name, address));
    }

    public void delete(ReaderId id) throws LibraryException {
        readers.remove(id);
    }

    public void update(Reader r) throws LibraryException {
        readers.put(r.getId(), r);
    }

    public Reader find(ReaderId id) throws LibraryException {
        return readers.get(id);
    }

    public Collection<Reader> getAll() throws LibraryException {
        return new ArrayList<Reader>(readers.values());
    }
}
