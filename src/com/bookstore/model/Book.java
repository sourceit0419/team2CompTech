package com.bookstore.model;

import com.bookstore.Main;

import java.lang.String;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Book {
    private int book_id; //unique number of the book
    @Signal
    private String book_title; //name of the book
    @Signal
    private String book_author; //book_author of the book
    @Signal
    private double book_price; //book_price of the book
    @Signal
    private BookGenre book_genre; //book_genre of the book

    public Book(int id, String title, String author, double price, BookGenre genre) {
        this.book_id = id;
        this.book_title = title;
        this.book_author = author;
        this.book_price = price;
        this.book_genre = genre;
    }

    public Book() {
    }
    @GetterBook
    public int getBook_id() {
        return book_id;
    }
    @SetterBook
    public void setBook_id(int id) {
        this.book_id = id;
    }
    @GetterBook
    public String getBook_title() {
        return book_title;
    }
    @SetterBook
    public void setBook_title(String book_title) {
        this.book_title = book_title;
    }
    @GetterBook
    public String getBook_author() {
        return book_author;
    }
    @SetterBook
    public void setBook_author(String book_author) {
        this.book_author = book_author;
    }
    @GetterBook
    public double getBook_price() {
        return book_price;
    }
    @SetterBook
    public void setBook_price(double book_price) {
        this.book_price = book_price;
    }
    @GetterBook
    public BookGenre getBook_genre() {
        return book_genre;
    }
    @SetterBook
    public void setBook_genre(BookGenre book_genre) {
        this.book_genre = book_genre;
    }


    @Override
    public String toString() {
        return  "Book: " + book_title + "; " +
                "Author='" + book_author + "; " +
                "Price=" + book_price + "; " +
                "Genre=" + book_genre +  ";\n"+ ' ';
    }


    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface GetterBook {

    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface SetterBook {

    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface Signal {



    }

    public  void fillBookInfo() {
        Object[] typeDefinitor = new Object[1];
        Book book = new Book();
        Class clss = book.getClass();
        Method[] methods = clss.getDeclaredMethods();
        Field[] fields = clss.getDeclaredFields();
        Scanner scanner = new Scanner(System.in);
        final String ENTER_VALUE = Main.myBundle.getString("enter_value");
        int pass = 0;
        int fail = 0;
        for (Field field : fields) {
            if (field.isAnnotationPresent(Book.Signal.class)) {
                try {
                    String[] temp = field.getName().split("set");
                    System.out.println(ENTER_VALUE + Main.myBundle.getString(temp[1]) + ":");
                    String a = scanner.nextLine();
                    field.setAccessible(true);
                    field.set(clss, Main.changeType(a, field.getType()));

                    pass++;
                } catch (Exception e) {
                    fail++;
                }
            }
        }



    }
}
