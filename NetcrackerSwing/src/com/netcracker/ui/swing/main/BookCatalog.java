package com.netcracker.ui.swing.main;

import javax.swing.table.AbstractTableModel;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.netcracker.ui.swing.main.Author;
import com.netcracker.ui.swing.main.Book;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class BookCatalog extends AbstractTableModel {
    private List<Book> bookList = new ArrayList<>();
    private String filePath = "newBooks.json";

    public BookCatalog() {
        this.bookList.add(new Book("", new Author("", "", ""), 0));

    }

    public void addNewBook(Book book) {
        bookList.add(book);
        saveFile();
        fireTableDataChanged();
    }

    public void deleteBook(int index) {
        bookList.remove(index);
        saveFile();
        fireTableDataChanged();
    }

    public void readFile(String filePath) {
        bookList.clear();
        this.filePath = filePath;
        JSONParser parser = new JSONParser();
        try(FileReader in = new FileReader(filePath))
        {
            Object obj = parser.parse(in);
            JSONArray books = (JSONArray)obj;
            Iterator bookIterator = books.iterator();
            while(bookIterator.hasNext()) {
                JSONObject book = (JSONObject)bookIterator.next();
                String title = book.get("title").toString();
                int year = Integer.parseInt(book.get("year").toString());
                int qty = Integer.parseInt(book.get("qty").toString());
                String authorName = book.get("author").toString();
                String country = book.get("country").toString();
                String gender = book.get("gender").toString();

                Author author = new Author(authorName, country, gender);
                Book current = new Book(title, author, year);
                current.setQty(qty);
                this.bookList.add(current);
            }
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        catch (ParseException e) {
            e.printStackTrace();
        }

        fireTableDataChanged();

    }

    public void saveFile() {
        JSONArray mainList = new JSONArray();
        for(Book book : bookList) {
            JSONObject bookObject = new JSONObject();
            bookObject.put("title", book.getTitle());
            bookObject.put("author", book.getAuthor().getName());
            bookObject.put("country", book.getAuthor().getCountry());
            bookObject.put("gender", book.getAuthor().getGender());
            bookObject.put("year", book.getYear());
            bookObject.put("qty", book.getQty());
            mainList.add(bookObject);
        }

        try (FileWriter file = new FileWriter(this.filePath)) {
            file.write(mainList.toJSONString());
            file.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        fireTableDataChanged();
    }
    @Override
    public int getColumnCount() {
        return bookList.get(0).getFieldsCount() + bookList.get(0).getAuthor().getFieldsCount() - 1;
    }

    @Override
    public int getRowCount() {
        return bookList.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Book currentBook = bookList.get(rowIndex);
        switch (columnIndex){
            case 0:
                return currentBook.getTitle();
            case 1:
                return currentBook.getAuthor().getName();
            case 2:
                return currentBook.getAuthor().getCountry();
            case 3:
                return currentBook.getAuthor().getGender();
            case 4:
                return currentBook.getYear();
            case 5:
                return currentBook.getQty();
        }
        return null;
    }

    public String getColumnName(int column) {
        switch (column){
            case 0:
                return "Book name";
            case 1:
                return "Author";
            case 2:
                return "Country";
            case 3:
                return "Gender";
            case 4:
                return "Year";
            case 5:
                return "Quantity";
        }
        return "";
    }

    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex){
            case 0:
                return String.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
            case 4:
                return Integer.class;
            case 5:
                return Integer.class;
        }
        return Object.class;
    }

    public List<Book> getBookList() {
        return this.bookList;
    }

}
