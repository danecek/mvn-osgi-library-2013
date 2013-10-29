/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.view.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import org.lib.business.LibraryFacade;
import org.lib.model.Reader;
import org.lib.utils.LibraryException;

/**
 *
 * @author danecek
 */
public final class ReaderModel extends AbstractTableModel {

    List<Reader> readers = new ArrayList<>();

    public ReaderModel() {
        try {
            refresh();
        } catch (LibraryException ex) {
            Logger.getLogger(ReaderModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    Reader getReader(int row) {
        return readers.get(row);
    }

    public void refresh() throws LibraryException {
        Collection<Reader> rs = LibraryFacade.getDefault().getReaders();
        readers = new ArrayList<>(rs);
        Collections.sort(readers, new Comparator<Reader>() {
            @Override
            public int compare(Reader t, Reader t1) {
                return t.getName().compareTo(t1.getName()); // Lepe Collator 
            }
        });
    }

    @Override
    public int getRowCount() {
        return readers.size();

    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Reader r = getReader(row);
        switch (col) {
            case 0:
                return r.getId();
            case 1:
                return r.getName();
            case 2:
                return r.getAddress();
            default:
                assert false;
                return null;
        }
    }

    @Override
    public String getColumnName(int col) {
        switch (col) {
            case 0:
                return "Id";
            case 1:
                return "Name";
            case 2:
                return "Address";
            default:
                assert false;
                return null;
        }
    }
}
