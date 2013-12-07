/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.view.impl;

import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import org.lib.model.Reader;
import org.lib.utils.Messages;

/**
 *
 * @author danecek
 */
public class ReaderPanel extends JPanel {

    private JTable tbl;
    private ReaderModel rm;

    public ReaderPanel() {
        setBorder(BorderFactory.createTitledBorder(Messages.Readers.cm()));
        setLayout(new BorderLayout());
        add(new JScrollPane(tbl = new JTable(rm = new ReaderModel())));
        tbl.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    Reader getSelectedReader() {
        int sr = tbl.getSelectedRow();
        if (sr == -1) {
            return null;
        }
        return rm.getReader(sr);
    }
}