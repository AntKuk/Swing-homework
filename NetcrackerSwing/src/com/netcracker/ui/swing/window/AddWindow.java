package com.netcracker.ui.swing.window;

import com.netcracker.ui.swing.main.AddEntry;
import com.netcracker.ui.swing.main.Author;
import com.netcracker.ui.swing.main.Book;
import com.netcracker.ui.swing.main.BookCatalog;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddWindow extends JFrame {
    BookCatalog bCatalog;
    private String warning;

    JTextField tfTitle;
    JTextField tfAuthor;
    JTextField tfCountry;
    private JComboBox<String> jcbGender;
    JTextField tfYear;
    JTextField tfQty;

    private JLabel lbTitle;
    private JLabel lbAuthor;
    private JLabel lbCountry;
    private JLabel lbGender;
    private JLabel lbYear;
    private JLabel lbQty;

    JButton btnAdd;


    public AddWindow() {
        super("Add Book");
        setLayout(null);
        setSize(257,256);

        JPanel inputControls = new JPanel(new BorderLayout(5,5));

        JPanel inputLabels = new JPanel(new GridLayout(0,1,5,5));
        JPanel inputFields = new JPanel(new GridLayout(0,1,5,5));
        inputControls.add(inputLabels, BorderLayout.WEST);
        inputControls.add(inputFields, BorderLayout.CENTER);


        lbTitle = new JLabel("Book name");
        inputLabels.add(lbTitle);

        tfTitle = new JTextField(10);
        inputFields.add(tfTitle);

        lbAuthor = new JLabel("Author name");
        inputLabels.add(lbAuthor);

        tfAuthor = new JTextField(10);
        inputFields.add(tfAuthor);

        lbCountry = new JLabel("Author's country");
        inputLabels.add(lbCountry);

        tfCountry = new JTextField(10);
        inputFields.add(tfCountry);

        lbGender = new JLabel("Gender");
        inputLabels.add(lbGender);

        jcbGender = new JComboBox<>();
        jcbGender.addItem("male");
        jcbGender.addItem("female");
        inputFields.add(jcbGender);

        lbYear = new JLabel("Year");
        inputLabels.add(lbYear);

        tfYear = new JTextField(10);
        inputFields.add(tfYear);

        lbQty = new JLabel("Quantity");
        inputLabels.add(lbQty);

        tfQty = new JTextField(10);
        inputFields.add(tfQty);

        JPanel jpnlButton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnAdd = new JButton("Add book!");
        jpnlButton.add(btnAdd);

        JPanel gui = new JPanel(new BorderLayout(10,10));
        gui.setBorder(new TitledBorder("Book information"));
        gui.setBounds(10,10,237,206);
        gui.add(inputControls, BorderLayout.CENTER);
        gui.add(jpnlButton, BorderLayout.SOUTH);

        add(gui);

        setLocationByPlatform(true);
        setVisible(true);

    }


    public AddWindow(AddEntry addEntry) {
        this();

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isFilled()) {
                    addEntry.add(createBook());
                    dispose();
                }
                else {
                    JOptionPane.showMessageDialog(AddWindow.this, "Please, fill all fields correctly!\n" + warning);
                }
            }
        });

        setVisible(true);
    }

    boolean isFilled() {
        boolean result = true;
        StringBuilder str = new StringBuilder();

        setDefaultFont();

        if(tfTitle.getText().equals("")) {
            this.tfTitle.setBackground(Color.RED);
            str.append("Title example: Sons and Lovers\n");
            result = false;
        }
        if(tfAuthor.getText().equals("")) {
            this.tfAuthor.setBackground(Color.RED);
            str.append("Author example: D. H. Lawrence\n");
            result = false;
        }
        if(tfCountry.getText().equals("")) {
            this.tfCountry.setBackground(Color.RED);
            str.append("Country example: United Kingdom\n");
            result = false;
        }

        if(tfYear.getText().equals("") || !tfYear.getText().matches("[0-9]+")) {
            this.tfYear.setBackground(Color.RED);
            str.append("Year example: 1913\n");
            result = tfYear.getText().matches("[0-9]+");
        }

        if(tfQty.getText().equals("") || !tfQty.getText().matches("[0-9]+")) {
            this.tfQty.setBackground(Color.RED);
            str.append("QTY example: 3\n");
            result = tfQty.getText().matches("[0-9]+");
        }

        warning = str.toString();
        return result;
    }



    Book createBook() {
        Book result;
        String title = tfTitle.getText();
        String author = tfAuthor.getText();
        String country = tfCountry.getText();
        String gender = (String)jcbGender.getSelectedItem();
        int year;
        int qty;
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

    private void setDefaultFont() {
        this.tfTitle.setBackground(Color.WHITE);
        this.tfAuthor.setBackground(Color.WHITE);
        this.tfCountry.setBackground(Color.WHITE);
        this.tfYear.setBackground(Color.WHITE);
        this.tfQty.setBackground(Color.WHITE);
    }

}
