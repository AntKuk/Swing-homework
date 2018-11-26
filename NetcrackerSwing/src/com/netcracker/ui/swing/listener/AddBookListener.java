package com.netcracker.ui.swing.listener;

import com.netcracker.ui.swing.window.AddBookWindow;
import com.netcracker.ui.swing.main.AddEntry;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddBookListener implements ActionListener {
    private AddEntry addEntry;


    public AddBookListener(AddEntry addEntry) {
        this.addEntry = addEntry;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AddBookWindow(addEntry);
            }
        });
    }
}
