package com.netcracker.ui.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeBookWindow extends JFrame {

    BookCatalog bCatalog;
    JTextField tfTitle;
    JTextField tfAuthor;
    JTextField tfCountry;
    JComboBox<String> jcbGender;
    JTextField tfYear;
    JTextField tfQty;



    public ChangeBookWindow(BookCatalog bCatalog, int index) {
        super("Add Book");

        this.bCatalog = bCatalog;

        setSize(500, 500);

        setLayout(new GridLayout(7,1));


        JLabel lbTitle = new JLabel("Book name");
        add(lbTitle);

        tfTitle = new JTextField(bCatalog.getBookList().get(index).getTitle());
        add(tfTitle);

        JLabel lbAuthor = new JLabel("Author name");
        add(lbAuthor);

        tfAuthor = new JTextField(bCatalog.getBookList().get(index).getAuthor().getName());
        add(tfAuthor);

        JLabel lbCountry = new JLabel("Author's country");
        add(lbCountry);

        tfCountry = new JTextField(bCatalog.getBookList().get(index).getAuthor().getCountry());
        add(tfCountry);

        JLabel lbGender = new JLabel("Gender");
        add(lbGender);

        jcbGender = new JComboBox<>();
        jcbGender.addItem("male");
        jcbGender.addItem("female");
        add(jcbGender);

        JLabel lbYear = new JLabel("Year");
        add(lbYear);

        tfYear = new JTextField(Integer.toString(bCatalog.getBookList().get(index).getYear()));
        add(tfYear);

        JLabel lbQty = new JLabel("Quantity");
        add(lbQty);

        tfQty = new JTextField(Integer.toString(bCatalog.getBookList().get(index).getQty()));
        add(tfQty);

        JButton btnAdd = new JButton("Add book!");
        add(btnAdd);

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isFilled()) {
                    Book book = createBook();
                    bCatalog.getBookList().set(index, book);
                    dispose();
                }
                else {
                    JOptionPane.showMessageDialog(ChangeBookWindow.this, "Please, fill all fields correctly");
                }
            }
        });


        setVisible(true);
    }

    private boolean isFilled() {
        if(tfTitle.getText().equals("")) {
            return false;
        }
        if(tfAuthor.getText().equals("")) {
            return false;
        }
        if(tfCountry.getText().equals("")) {
            return false;
        }
        if(tfYear.getText().equals("")) {
            return false;
        }
        if(tfYear.getText().equals("")) {
            return tfYear.getText().matches("[0-9]+");
        }
        if(tfQty.getText().equals("")) {
            return false;
        }
        if(tfQty.getText().equals("")) {
            return tfYear.getText().matches("[0-9]+");
        }
        return true;
    }

    private Book createBook() {
        Book result;
        String title = tfTitle.getText();
        String author = tfAuthor.getText();
        String country = tfCountry.getText();
        String gender = (String)jcbGender.getSelectedItem();
        Integer year;
        Integer qty;
        try {
            year = Integer.parseInt(tfYear.getText());
            qty = Integer.parseInt(tfQty.getText());
        }
        catch (NumberFormatException e) {
            e.printStackTrace();
            year = 0;
            qty = -1;
        }

        result = new Book(title, new Author(author, country, gender), year);

        if(qty > 1) {
            result.setQty(qty);
        }
        return result;
    }
}