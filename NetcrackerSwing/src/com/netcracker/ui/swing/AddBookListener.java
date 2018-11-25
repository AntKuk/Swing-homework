package com.netcracker.ui.swing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddBookListener implements ActionListener {
    //BookCatalog bCatalog;
    AddEntry addEntry;
    //private List<Book> bookList;
/*
    public AddBookListener(BookCatalog bCatalog) {
        this.bCatalog = bCatalog;
        //this.bookList = bookList;
    }
*/

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
