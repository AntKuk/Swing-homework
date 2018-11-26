package com.netcracker.ui.swing.listener;

import com.netcracker.ui.swing.main.BookCatalog;
import com.netcracker.ui.swing.window.ChangeBookWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ChangeBookListener implements ActionListener {
    private JTable table;
    private BookCatalog bCatalog;

    public ChangeBookListener(JTable table, BookCatalog bCatalog) {
        this.table = table;
        this.bCatalog = bCatalog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int index = table.getSelectedRow();
        new ChangeBookWindow(bCatalog, index);
    }
}
