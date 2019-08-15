package com.bookstore;

import com.bookstore.model.*;

import java.io.UnsupportedEncodingException;
import java.lang.String;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Type;
import java.util.*;



public class Main {

    public static ArrayList<Book> booksMain = new ArrayList<>();
    public static ArrayList<Customer> customersMain = new ArrayList<>();
    public static ArrayList<Order> ordersMain = new ArrayList<>();
    public static TreeMap<Date, String> finishedOredrs = new TreeMap();

    public static ResourceBundle myBundle = new ResourceBundle() {
        @Override
        protected Object handleGetObject(String key) {
            return null;
        }

        @Override
        public Enumeration<String> getKeys() {
            return null;
        }
    };


//    public static LinkedHashMap<Integer, Order> ordersMain = new LinkedHashMap<>();

    public static void main(String[] args) throws InterruptedException, NoSuchFieldException, NoSuchMethodException, UnsupportedEncodingException {
        initData();


        Book book = new Book();
        Profit profit = new Profit();

        //Реализация консольного интерфейса:

        createNewOrder();


        //Ввод и вывод информации о товаре:

        book.fillBookInfo();


    }



    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.CLASS)
    public @interface UserFriendly{
        String book_id = "Book_id"; //unique number of the book
        String book_title = "Book_title"; //name of the book
        String book_author = "Book_author"; //author of the book price;
        String book_price = "Book_price"; // price of the book
        String book_genre = "Book_genre"; //genre of the book

        String customer_id = "customer_id";
        String customer_name = "customer_name";
        String customer_age = "customer_age";
        String order_id = "order_id";
        String order_status = "order_status";
        String ordered_books = "ordered_books";
        String basket = "basket";
        String profit_amount = "profit_amount";
        String profit_totalSum = "profit_totalSum";
        String card_number = "card_number";
        String card_expiration = "card_expiration";
        String card_type = "card_type";
        String card_cvv = "card_cvv";

    }




    public static String typeof(int expr) {
        return "String";
    }

    public static String typeof(double expr) {
        return "Double";
    }
    public static String typeof(String expr) {
        return "String";
    }



    public static Object changeType(String stringVar, Type typeName){
        if(typeName.equals(String.class)){
            return stringVar;
        }else if(typeName.equals(Integer.class)){
            Integer q = 0;

            try {
                Integer i1 = new Integer(stringVar);
                q=i1;
            } catch (NumberFormatException e) {
                System.err.println("Неверный формат строки!");
            }
            return q;
        }else if (typeName.equals(Double.class)){
            Double p = 0.0;

            try {
                Double d2 = Double.valueOf(stringVar);
                p=d2;
            } catch (NumberFormatException e) {
                System.err.println("Неверный формат строки!");
            }

            return p;
        }
//        return stringVar;
        return null;
    }





    public static void createNewOrder() throws UnsupportedEncodingException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите язык: 1 - Русский, 2 - Английский;\nChoose language: 1 - Russian, 2 - English;");
        int determinant = scanner.nextInt();
        if(determinant == 1){
            Locale locale = new Locale("ru", "RU");
            myBundle = ResourceBundle.getBundle("resources_ru", locale);
        }else{
            Locale locale = new Locale("en", "US");
            myBundle = ResourceBundle.getBundle("resources", locale);

        }


//        final String INTRO_TEXT = new String(myBundle.getString("intro_text").getBytes("ISO-8859-1"), "UTF-8");
        final String INTRO_TEXT = myBundle.getString("intro_text");
        final String HELP_TEXT2 = myBundle.getString("help_text2");
        final String HELP_TEXT4 = myBundle.getString("help_text4");
        final String CUSTOMER_ORDER_NAME = myBundle.getString("customer_order_name");
        final String CUSTOMER_ORDER_AGE = myBundle.getString("customer_order_age");
        final String CUSTOMER_CARD_NUMBER = myBundle.getString("customer_card_number");
        final String CARD_LENGTH_EXCEPTION = myBundle.getString("card_length_exception");
        final String CUSTOMER_CARD_EXPIRATION_DATE = myBundle.getString("customer_card_expiration_date");
        final String CHOOSE_THE_CARD_TYPE = myBundle.getString("choose_the_card_type");
        final String CUSTOMER_CARD_CVV = myBundle.getString("customer_card_cvv");
        final String CUSTOMER_CVV_EXCEPTION = myBundle.getString("customer_cvv_exception");
        final String DATE_FROM = myBundle.getString("date_from");
        final String DATE_TO = myBundle.getString("date_to");





        Customer customer = new Customer();
        Order order = new Order();
        while (true) {
            System.out.println(INTRO_TEXT);
            int command = scanner.nextInt();


            if(command == 1){
                System.out.println(getFullListOfBooks());

            }

            if(command == 2){
                System.out.println(HELP_TEXT2);
                int goodId = scanner.nextInt();
                System.out.println(order.addGoodToTheBasket(goodId));
            }

            if(command == 3){
//                System.out.println("3; Укажите номер заказа:");
//                long orderUniqNum = scanner.nextInt();
                System.out.println(order.toString());
            }

            if(command == 4){
                System.out.println(HELP_TEXT4 + getTotalPriceOfOrder(order));
                System.out.println(CUSTOMER_ORDER_NAME);
                String name = scanner.nextLine();
                scanner.nextLine();
                System.out.println(CUSTOMER_ORDER_AGE);
                int age = scanner.nextInt();
                System.out.println(CUSTOMER_CARD_NUMBER);
                scanner.nextLine();
                String cardNumber = scanner.nextLine();
                while (cardNumber.length()!=16){
                    System.out.println(CARD_LENGTH_EXCEPTION + cardNumber.length());
                    cardNumber = scanner.nextLine();
                }
                System.out.println(CUSTOMER_CARD_EXPIRATION_DATE);
                String experationDate = scanner.nextLine();
                System.out.println(CHOOSE_THE_CARD_TYPE);
                String typeOfCard = scanner.nextLine();
                System.out.println(CUSTOMER_CARD_CVV);
                String cvvOfCard = scanner.nextLine();
                if(cvvOfCard.length()!=3){
                    System.out.println(CUSTOMER_CVV_EXCEPTION);
                    cvvOfCard = scanner.nextLine();
                }
//                order.setBasket().
//                System.out.println(getFullListOfBooks());
                order.finishOrder(name, age, cardNumber, experationDate, typeOfCard, cvvOfCard, order);
                ordersMain.add(order);

            }

            if(command == 5){
                viewLastFivePurchases();
            }

            if(command == 6){
                break;
            }

//            /
////                /
////                    /
////                        /
////                            /
////                        /
////                    /
////                /
////            /

            if(command == 7 ){
                Scanner scanner1 = new Scanner(System.in);
                System.out.println(DATE_FROM);
                String a = scanner1.nextLine();
                Date date1 = new Date(a);
                System.out.println(DATE_TO);
                String b = scanner1.nextLine();
                Date date2 = new Date(b);
                ordersDuringPeriod(date1, date2);
//                System.out.println(date2);
            }

        }
    }


    //HW8
    public static void ordersDuringPeriod(Date dateFrom, Date dateTo ){

        for(Date date : finishedOredrs.keySet())
            if(dateFrom.before(date) && dateTo.after(date)){
                System.out.println(finishedOredrs.get(date));
            }

    }

    //HW 8
    public static void viewLastFivePurchases(){
        ArrayList fiveLastOrders = new ArrayList();

            if((ordersMain.size()-5)<0){
                for(int j = 0; j<ordersMain.size(); j++){
                    fiveLastOrders.add(ordersMain.get(j));
                    System.out.println(ordersMain.get(j));
                }
            }else {
                for(int q = ordersMain.size()-5; q< ordersMain.size(); q++) {
                    fiveLastOrders.add(ordersMain.get(q));
                    System.out.println(ordersMain.get(q));
                }
            }


    }


// HW 8
    /**
     * getting the full list of accessible booksMain
     * @return list of booksMain converted into string format
     */
    public static String getFullListOfBooks() {
        String temp = " ";
        for(Book book : booksMain){
            temp +=" " + book.toString();
        }
        return temp;

    }



    /**
     * Calculates the total sum of all solved booksMain for whole time
     * @return total price of all solved booksMain for whole time
     */
    public static double getAllIncome(){
        double totalPrice = 0;
        for (int i = 1; i < ordersMain.size(); i++){
            totalPrice += getTotalPriceOfOrder(ordersMain.get(i));
        }
        return totalPrice;
    }

    /**
     * Calculate total price of one particular order
     * @param order order by which calculates total price
     * @return total price by this particular order
     */
    public static double getTotalPriceOfOrder(Order order){
        double total = 0;

        for (Integer bookId : order.books){
            Book book = getBookById(bookId);
            if (book != null)
                total += book.getBook_price();

        }
        return total;
    }

    /**
     * Count total amount of sold booksMain
     * @return the amount of sold booksMain
     */
    public static int getAmountOfSoldBooks(){
        int count = 0;
        for(int i = 1; i < ordersMain.size(); i++){
            count += ordersMain.get(i).getBooks().size();
        }
        return count;
    }

    /**
     * Search for book in the list of booksMain by its unique number
     * @param id unique number of the particular book
     * @return book which we searched for
     */
    public static Book getBookById(int id) {
        Book current = null;

        for (Book book : booksMain){
            if (book.getBook_id() == id){
                current = book;
                break;
            }
        }
        return current;
    }

    public static void initData() throws InterruptedException{

        //покупатели
//        customersMain.add(new Customer(1, "Martin", 21));
//        customersMain.add(new Customer(2, "Kevin", 20));
//        customersMain.add(new Customer(3, "Ivan", 25));

        //книги
        booksMain.add(new Book(1, "Book1", "Author1", 1600, BookGenre.Art));
        booksMain.add(new Book(2, "Book2", "Author2", 1500, BookGenre.Programming));
        booksMain.add(new Book(3, "Book3", "Author3", 1400, BookGenre.Art));
        booksMain.add(new Book(4, "Book4", "Author4", 1200, BookGenre.Art));
        booksMain.add(new Book(5, "Book5", "Author5", 1300, BookGenre.Psychology));
        booksMain.add(new Book(6, "Book6", "Author6", 1900, BookGenre.Programming));
        booksMain.add(new Book(7, "Book7", "Author7", 1800, BookGenre.Programming));
        booksMain.add(new Book(8, "Book8", "Author8", 1000, BookGenre.Psychology));
        booksMain.add(new Book(9, "Book9", "Author9", 600, BookGenre.Art));


        ordersMain.add( new Order( new int[]{8,3,5}));

        ordersMain.add( new Order( new int[]{1}));

        ordersMain.add( new Order( new int[]{2,4}));

        ordersMain.add( new Order( new int[]{7,9}));

        ordersMain.add( new Order( new int[]{6}));

        ordersMain.add( new Order( new int[]{1,2,3}));

    }
}

