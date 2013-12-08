/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.view.impl;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.lib.business.LibraryFacadeService;
import org.lib.model.Reader;
import org.lib.utils.LibraryException;
import static org.lib.utils.Messages.*;
import org.lib.view.MainFrame;

/**
 *
 * @author danecek
 */
public final class ReaderModel
        extends AbstractTableModel implements Refreshable {

    private List<Reader> readers = new ArrayList<>();

    public ReaderModel() {
        MainFrame.addRefreshable(this);
    }

    Reader getReader(int row) {
        return readers.get(row);
    }

    @Override
    public void refresh() throws LibraryException {
        Collection<Reader> rs = LibraryFacadeService.getDefault().getReaders();
        readers = new ArrayList<>(rs);
        Collections.sort(readers, new Comparator<Reader>() {
            @Override
            public int compare(Reader t, Reader t1) {
                return Collator.getInstance().compare(t.getName(), t1.getName());
            }
        });
        fireTableDataChanged();
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
                return Id.cm();
            case 1:
                return Name.cm();
            case 2:
                return Address.cm();
            default:
                assert false;
                return null;
        }
    }
}
