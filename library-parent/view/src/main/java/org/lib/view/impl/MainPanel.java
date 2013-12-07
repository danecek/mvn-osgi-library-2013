/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.view.impl;

import java.awt.BorderLayout;
import java.util.Collection;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import org.lib.model.Book;

/**
 *
 * @author danecek
 */
public class MainPanel extends JPanel {

    BookPanel bookPanel;
    public MainPanel() {

        setLayout(new BorderLayout());

//  pro vice nez 2 entity:  new JTabbedPane()

        JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                new ReaderPanel(), bookPanel = new BookPanel());
        sp.setDividerLocation(400);
        add(sp);

    }

    public Collection<Book> getSelectedBooks() {
        return bookPanel.getSelectedBooks();
    }
}
