/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.view;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import org.lib.view.impl.BookPanel;
import org.lib.view.impl.ReaderPanel;

/**
 *
 * @author danecek
 */
public class MainPanel extends JPanel {

    public MainPanel() {

        setLayout(new BorderLayout());

//  pro vice nez 2 entity:  new JTabbedPane()

        JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                new ReaderPanel(), new BookPanel());
        add(sp);

    }
}
