/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.view.impl;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import org.lib.model.Reader;

/**
 *
 * @author danecek
 */
public class ReaderPanel extends JPanel {

    JTable tbl;
    ReaderModel rm; 
    public ReaderPanel() {
        setLayout(new BorderLayout());
        add(new JScrollPane(tbl = new JTable(rm = new ReaderModel())));
        tbl.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    
    Reader getSelectedReader() {
        int sr = tbl.getSelectedRow();
        if (sr == -1) return null;
        return rm.getReader(sr);
        
    }
}
