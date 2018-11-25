package com.netcracker.ui.swing;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


public class Main extends JFrame implements AddEntry {
    private BookCatalog bCatalog;


    public Main() {
        super("Book Catalog");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 500);

        bCatalog = new BookCatalog();

        //Creating Menu Bar
        JMenuBar jmb = new JMenuBar();

        //Creating File menu
        JMenu jmFile = new JMenu("File");
        JMenuItem jmiOpen = new JMenuItem("Open");
        jmFile.add(jmiOpen);
        jmb.add(jmFile);
        add(jmb, BorderLayout.NORTH);

        jmiOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("JSON", "json"));
                fileChooser.showDialog(null, "Open JSON file");
                File selectedFile = fileChooser.getSelectedFile();
                String pathFile = selectedFile.getAbsolutePath();
                bCatalog.readFile(pathFile);
            }
        });


        //Creating Table
        JTable table = new JTable(bCatalog);
        JScrollPane jsp = new JScrollPane(table);
        add(jsp, BorderLayout.CENTER);

        //Creating the right-side panel
        JPanel eastGrid = new JPanel(new GridLayout(5,1, 5, 5));

        //Setting the size of the right panel
        eastGrid.setPreferredSize(new Dimension(200,100));
        eastGrid.setMinimumSize(new Dimension(100,100));
        eastGrid.setMaximumSize(new Dimension(500,100));

        JTextArea jta = new JTextArea("Hello there. It's a book catalog!");
        JButton jbtnAdd = new JButton("Add book");
        jbtnAdd.addActionListener(new AddBookListener(this));
        eastGrid.add(jta);
        eastGrid.add(jbtnAdd);

        //Change-button
        JButton jbtnChange = new JButton("Change book");
        jbtnChange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = table.getSelectedRow();
                new ChangeBookWindow(bCatalog, index);
            }
        });
        eastGrid.add(jbtnChange);

        //Delete-button
        JButton jbtnDelete = new JButton("Delete book");
        eastGrid.add(jbtnDelete);

        jbtnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = table.getSelectedRow();
                if(index != -1) {
                    bCatalog.deleteBook(index);
                }
            }
        });

        add(eastGrid, BorderLayout.EAST);

        setVisible(true);

        JOptionPane.showMessageDialog(this, "Please, open JSON file");
    }

    //Method that executes when we add new book. It is called from AddBookWindow
    public void add(Book book) {
        bCatalog.addNewBook(book);
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
            }
        });
    }
}
