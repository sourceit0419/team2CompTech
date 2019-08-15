package com.bookstore.model;

import com.bookstore.Main;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import static com.bookstore.Main.booksMain;
import static com.bookstore.Main.finishedOredrs;
//import static com.bookstore.model.Customer.customerId;

public class Order {
    private enum OrderStatus{}; //the status of order
    private int id; //unique number of order
    public ArrayList<Integer> books = new ArrayList<>();//booksMain that were sold to customer

    private HashMap<Long, Integer> basket; //amount of books that were sold to customer




//    Customer customer = new Customer();

    public static int fakeOrderId = 1;
//    public static int fakeCustomerId = 1;

    public Order(int[] books) {
        for(int bookId : books){
        this.books.add(bookId);
        }
        this.id = fakeOrderId++;
//        Date date = new Date();
//        SimpleDateFormat currentTime = new SimpleDateFormat(date.toString());
//        finishedOredrs.put(currentTime, getBook());

    }


    public Order() {
        Customer customer = new Customer();
        this.id = fakeOrderId++;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList getBooks() {
        return books;
    }

    public void setBooks(ArrayList books) {
        this.books = books;
    }

    public HashMap<Long, Integer> getBasket() {
        return basket;
    }

    public void setBasket(HashMap<Long, Integer> basket) {
        this.basket = basket;
    }


    public boolean addGoodToTheBasket(int bookId){
        for(Book book : booksMain) {
            if (book.getBook_id() == bookId) {
//                long[] temp = new long[MAX_SIZE];
                this.books.add(bookId);

//                long m = 1;
//                basket.put(m, 1);
                return true;
            }
        }
        return false;
    }

    public String getBook(){
        String title1 = "\"";
        for (int i = 0; i<this.books.size(); i++){
            title1 += Main.getBookById(books.get(i)).getBook_title()+ "\", ";
        }
        return title1;
    }

    @Override
    public String toString() {
        return "Order №" + this.id + '\n'
                + "Ordered: " + getBook() + '\n';
    }


    //HW 8
    public void finishOrder(String nameOfCustomer, int ageOfCustomer, String numberOfCard,
                                   String expirationDate, String typeOfCard, String cvv, Order order){
        CreditCard creditCard =  new CreditCard();
        Customer customer = new Customer();

        creditCard.setCvv(cvv);
        creditCard.setExpiration(expirationDate);
        creditCard.setNumber(numberOfCard);
        creditCard.setType(typeOfCard);
        customer.setName(nameOfCustomer);
        customer.setAge(ageOfCustomer);
//        Object[] finishedOrder = new Object[]{order, customer, creditCard};


///
//        /
//            /
//                /
//                    /
//                        /
//                    /
//                /
//            /
//        /
///


//        Scanner scan = new Scanner(System.in);
//        String dateOfOrder = scan.nextLine();
        Date currentTime = new Date();
        finishedOredrs.put(currentTime, getBook());
    }
}
