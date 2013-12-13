/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.view.impl;

import org.lib.view.BookModel;
import java.awt.BorderLayout;
import java.util.Collection;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.lib.model.Book;
import org.lib.utils.Messages;
import org.lib.view.MainFrame;

/**
 *
 * @author danecek
 */
public class BookPanel extends JPanel {

    private JTable tbl;
    private BookModel bm;

    public BookPanel() {
        setBorder(BorderFactory.createTitledBorder(Messages.Books.cm()));
        setLayout(new BorderLayout());
        add(new JScrollPane(tbl = new JTable(bm = new BookModel())));
        MainFrame.addRefreshable(bm);
        ListSelectionModel sm = tbl.getSelectionModel();
        sm.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        sm.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                MainFrame.getInstance().notifyActions();
            }
        });

    }

    public Collection<Book> getSelectedBooks() {
        int[] sr = tbl.getSelectedRows();
        return bm.getBooks(sr);
    }
}
