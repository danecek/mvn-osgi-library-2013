/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.view.impl;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.lib.model.Book;
import org.lib.view.MainFrame;

/**
 *
 * @author danecek
 */
public class BookPanel extends JPanel {

    JTable tbl;
    BookModel bm;

    public BookPanel() {
        setLayout(new BorderLayout());
        add(new JScrollPane(tbl = new JTable(bm = new BookModel())));
        ListSelectionModel sm = tbl.getSelectionModel();
        sm.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        sm.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                MainFrame.getInstance().actionsNotif();
            }
        });

    }

    Collection<Book> getSelectedBooks() {
        int[] sr = tbl.getSelectedRows();
        return bm.getBooks(sr);

    }

    public Book getSelectedBook() {
        int row = tbl.getSelectedRow();
        if (row == -1) {
            return null;
        }
        return new ArrayList<Book>(bm.getBooks(new int[]{row})).get(0);
    }
}
