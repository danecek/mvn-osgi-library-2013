/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.view.impl;

import java.awt.BorderLayout;
import java.util.Collection;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import org.lib.model.Book;

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
        tbl.getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    }

    Collection<Book> getSelectedBooks() {
        int[] sr = tbl.getSelectedRows();
        return bm.getBooks(sr);

    }
}
