package com.netcracker.ui.swing.window;

import com.netcracker.ui.swing.main.Book;
import com.netcracker.ui.swing.main.BookCatalog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeBookWindow extends AddWindow {
    public ChangeBookWindow(BookCatalog bCatalog, int index) {
        super();
        super.setTitle("Change book");
        super.bCatalog = bCatalog;

        tfTitle.setText(bCatalog.getBookList().get(index).getTitle());
        tfAuthor.setText(bCatalog.getBookList().get(index).getAuthor().getName());
        tfCountry.setText(bCatalog.getBookList().get(index).getAuthor().getCountry());
        tfYear.setText(Integer.toString(bCatalog.getBookList().get(index).getYear()));
        tfQty.setText(Integer.toString(bCatalog.getBookList().get(index).getQty()));
        btnAdd.setText("Change book!");

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ChangeBookWindow.super.isFilled()) {
                    Book book = createBook();
                    bCatalog.getBookList().set(index, book);
                    bCatalog.saveFile();
                    dispose();
                }
                else {
                    JOptionPane.showMessageDialog(ChangeBookWindow.this, "Please, fill all fields correctly");
                }
            }
        });

        setVisible(true);
    }

}
