package com.netcracker.ui.swing.window;

import com.netcracker.ui.swing.main.Author;
import com.netcracker.ui.swing.main.Book;
import com.netcracker.ui.swing.main.BookCatalog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeBookWindow extends JFrame {

    private BookCatalog bCatalog;

    private JTextField tfTitle;
    private JTextField tfAuthor;
    private JTextField tfCountry;
    private JComboBox<String> jcbGender;
    private JTextField tfYear;
    private JTextField tfQty;

    private float textSize = 45.0f;

    private JLabel lbTitle;
    private JLabel lbAuthor;
    private JLabel lbCountry;
    private JLabel lbGender;
    private JLabel lbYear;
    private JLabel lbQty;

    private JButton btnAdd;

    private JTextArea jta;



    public ChangeBookWindow(BookCatalog bCatalog, int index) {
        super("Add Book");

        this.bCatalog = bCatalog;

        setSize(900, 700);

        setLayout(new GridLayout(7,1));


        lbTitle = new JLabel("Book name");
        add(lbTitle);

        tfTitle = new JTextField(bCatalog.getBookList().get(index).getTitle());
        add(tfTitle);

        lbAuthor = new JLabel("Author name");
        add(lbAuthor);

        tfAuthor = new JTextField(bCatalog.getBookList().get(index).getAuthor().getName());
        add(tfAuthor);

        lbCountry = new JLabel("Author's country");
        add(lbCountry);

        tfCountry = new JTextField(bCatalog.getBookList().get(index).getAuthor().getCountry());
        add(tfCountry);

        lbGender = new JLabel("Gender");
        add(lbGender);

        jcbGender = new JComboBox<>();
        jcbGender.addItem("male");
        jcbGender.addItem("female");
        add(jcbGender);

        lbYear = new JLabel("Year");
        add(lbYear);

        tfYear = new JTextField(Integer.toString(bCatalog.getBookList().get(index).getYear()));
        add(tfYear);

        lbQty = new JLabel("Quantity");
        add(lbQty);

        tfQty = new JTextField(Integer.toString(bCatalog.getBookList().get(index).getQty()));
        add(tfQty);

        btnAdd = new JButton("Change book!");
        add(btnAdd);

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isFilled()) {
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

        jta = new JTextArea("");
        add(jta);

        setTextSize();

        setVisible(true);
    }

    private boolean isFilled() {
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

        jta.setText(str.toString());
        return result;
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
    private void setDefaultFont() {
        this.tfTitle.setBackground(Color.WHITE);
        this.tfAuthor.setBackground(Color.WHITE);
        this.tfCountry.setBackground(Color.WHITE);
        this.tfYear.setBackground(Color.WHITE);
        this.tfQty.setBackground(Color.WHITE);
    }

    private void setTextSize() {
        lbTitle.setFont(lbTitle.getFont().deriveFont(this.textSize));
        tfTitle.setFont(tfTitle.getFont().deriveFont(this.textSize));

        lbAuthor.setFont(lbAuthor.getFont().deriveFont(this.textSize));
        tfAuthor.setFont(tfAuthor.getFont().deriveFont(this.textSize));

        lbCountry.setFont(lbCountry.getFont().deriveFont(this.textSize));
        tfCountry.setFont(tfCountry.getFont().deriveFont(this.textSize));

        lbGender.setFont(lbGender.getFont().deriveFont(this.textSize));
        jcbGender.setFont(jcbGender.getFont().deriveFont(this.textSize));

        lbYear.setFont(lbYear.getFont().deriveFont(this.textSize));
        tfYear.setFont(tfYear.getFont().deriveFont(this.textSize));

        lbQty.setFont(lbQty.getFont().deriveFont(this.textSize));
        tfQty.setFont(tfQty.getFont().deriveFont(this.textSize));

        btnAdd.setFont(btnAdd.getFont().deriveFont(this.textSize));

        jta.setFont(btnAdd.getFont().deriveFont(15.0f));
    }
}
