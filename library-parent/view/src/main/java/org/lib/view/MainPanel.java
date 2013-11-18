/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.view;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import org.lib.model.Book;
import org.lib.view.impl.BookPanel;
import org.lib.view.impl.ReaderPanel;

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

    Book getSelectedBook() {
        return bookPanel.getSelectedBook();
    }
}
