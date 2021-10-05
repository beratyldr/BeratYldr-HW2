package com.kodluyoruz;

import java.util.*;

public class Main {
    /**
     * This is the main class for the application, interacting
     * with the customer, adding to the cart different products to
     * be purchased and finally calculating the total amount to be paid
     */

    /**
     * Allows a cashier to enter the details for a product
     * to be purchased by a customer
     * @param cart the shopping cart of a given customer
     */
    public static void askCustomer(Cart cart){
        String ans="";
    // Code to read from console the product name, seller,
    // price, number of products, discount and
    // if Buy2Take3 applies.
    // Then create a product of the correct type
    // and add it to the shopping cart
        do {
            System.out.println("Enter 1 to buy a product");
            System.out.println("Enter 0 to chechout and proceed with the payment");
            Scanner scanner = new Scanner(System.in);
            ans = scanner.nextLine();
            if (ans.equals("1")) {
                System.out.print("Product name: ");
                String pName = scanner.nextLine();

                System.out.print("Seller: ");
                String seller = scanner.nextLine();

                System.out.print("Price: ");
                double price = scanner.nextDouble();

                System.out.print("How many: ");
                int count = scanner.nextInt();

                System.out.print("Discount (enter 0 if no discount applies): ");
                int discount = scanner.nextInt();


                System.out.print("Does Buy2Take3 apply? Y/N: ");
                String buy2take3 =scanner.next();

                if(discount!=0 && buy2take3.equals("Y")){
                    throw new IllegalArgumentException("You cannot benefit from two promotions.");
                }

                if (discount != 0) {                                                //If there is a discount, add it to the cart as a discounted product
                    Product product = new Product(seller, pName, price);
                    DiscountedProduct dP = new DiscountedProduct(product, discount);
                    if (count == 1) {
                        cart.addProduct(dP);
                    }
                    else{
                        cart.addProduct(dP,count);
                    }
                }
                if (buy2take3.equals("Y")) {                                //If there is a buy2take3, add it to the cart as a buy2take3 product
                    Product product = new Product(seller, pName, price);
                    Buy2Take3Product btp = new Buy2Take3Product(product);
                    if (count == 1) {
                        cart.addProduct(btp);
                    } else {
                        cart.addProduct(btp, count);
                    }
                }

            }
        }while (!ans.equals("0"));


        for (Product item: cart.getProducts()) {
            if(item instanceof DiscountedProduct){
                System.out.println(item.toString()+" "+item.getPrice(cart)+"TL."+" Sold by "+item.getSeller());//check which product it is and print
            }else{
                System.out.println(item.getName()+" "+item.getPrice(cart)+" TL."+"Sold by "+item.getSeller());
            }

        }
        System.out.println("In total you have to pay "+cart.totalPrice()+" TL");

    }

    // Main method to interact with a customer
    public static void main(String[] args) {
        System.out.println("Welcome to kodluyoruz shop");
        System.out.println("What’s your name?");

        Scanner scanner = new Scanner(System.in);

        String customer = scanner.nextLine();
        System.out.println("Hi " + customer + ". Please choose one of the following options:");
        System.out.println("");

        Cart cart = new Cart();

        askCustomer(cart);
        //Implement the user interface here
        // Ask the user to choose 0 (buy product) or
        // 1 (checkout), depending on what they want to do.
        // Use the method askCustomer
    }
}
