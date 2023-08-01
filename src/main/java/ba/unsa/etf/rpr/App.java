package ba.unsa.etf.rpr;

import java.io.PrintWriter;
import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

import ba.unsa.etf.rpr.business.OrderItemsManager;
import ba.unsa.etf.rpr.business.UsersManager;
import ba.unsa.etf.rpr.business.ProductsManager;
import ba.unsa.etf.rpr.business.OrdersManager;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Users;
import ba.unsa.etf.rpr.domain.Products;
import ba.unsa.etf.rpr.domain.Orders;
import ba.unsa.etf.rpr.exceptions.UsersException;
import ba.unsa.etf.rpr.exceptions.ProductsException;
import ba.unsa.etf.rpr.exceptions.OrdersException;
import org.apache.commons.cli.*;
import org.mockito.internal.matchers.Or;

public class App 
{
    private static final Option getUsers = new Option("getU","get-users",false,"Printing all users from database");
    private static final Option getProducts = new Option("getP","get-products",false,"Printing all products from database");
    private static final Option getOrders = new Option("getO","get-orders",false,"Printing all orders from database");
    private static final Option getOrderItems = new Option("getOI","get-order-items",false,"Printing all order items from database");
    private static final Option addUsers = new Option("addU","add-users",false,"Adding a user to the database");
    private static final Option addProducts = new Option("addP","add-products",false,"Adding a product to the database");
    private static final Option updateUsers = new Option("updateU","update-users",false,"Updating a user from the database");
    private static final Option updateProducts = new Option("updateP","update-products",false,"Updating a product from the database");
    private static final Option deleteUsers = new Option("delU","delete-users",true,"Deleting user...");
    private static final Option deleteProducts = new Option("delP","delete-products",true,"Deleting product...");
    private static final Option deleteOrders = new Option("delO","delete-orders",true,"Deleting order...");

    public static Options addOptions() {
        Options options = new Options();
        options.addOption(getUsers);
        options.addOption(getProducts);
        options.addOption(getOrders);
        options.addOption(getOrderItems);
        options.addOption(addUsers);
        options.addOption(addProducts);
        options.addOption(updateUsers);
        options.addOption(updateProducts);
        options.addOption(deleteUsers);
        options.addOption(deleteProducts);
        options.addOption(deleteOrders);
        return options;
    }
    public static void printFormattedOptions(Options options) {
        HelpFormatter helpFormatter = new HelpFormatter();
        PrintWriter printWriter = new PrintWriter(System.out);
        helpFormatter.printUsage(printWriter, 150, "java -jar rpr-projekat-cli-jar-with-dependencies.jar 'something else if needed' ");
        helpFormatter.printOptions(printWriter, 150, options, 2, 7);
        printWriter.close();
    }
    public static void main( String[] args ) throws Exception {
        Options options = addOptions();
        CommandLineParser commandLineParser = new DefaultParser();
        CommandLine commandLine = commandLineParser.parse(options, args);
        if(commandLine.hasOption(getUsers.getOpt()) || commandLine.hasOption(getUsers.getLongOpt())){
            UsersManager usersManager = new UsersManager();
            usersManager.getAll().forEach(entry-> {System.out.println(entry.getId() + " " + entry.getFirstName() + " " + entry.getLastName());});
        }
        else if(commandLine.hasOption(getProducts.getOpt()) || commandLine.hasOption(getProducts.getLongOpt())){
            ProductsManager productsManager = new ProductsManager();
            productsManager.getAll().forEach(System.out::println);
        }
        else if(commandLine.hasOption(getOrders.getOpt()) || commandLine.hasOption(getOrders.getLongOpt())){
            OrdersManager ordersManager = new OrdersManager();
           ordersManager.getAll().forEach(System.out::println);
        }
        else if (commandLine.hasOption(getOrderItems.getOpt()) || commandLine.hasOption(getOrderItems.getLongOpt())){
            OrderItemsManager orderItemsManager = new OrderItemsManager();
            orderItemsManager.getAll().forEach(System.out::println);
        }
        else if (commandLine.hasOption(deleteUsers.getOpt()) || commandLine.hasOption(deleteUsers.getLongOpt())){
            UsersManager usersManager = new UsersManager();
            try {
                String s = commandLine.getOptionValue(deleteUsers.getOpt());
                if (Integer.parseInt(s) == 1) System.out.println("Cannot delete the admin!");
                else {
                    usersManager.delete(Integer.parseInt(s));
                    System.out.println("User " + s + " deleted!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Formatting error!");
            } catch (UsersException e) {
                System.out.println("Database error!");
            }
        }
        else if (commandLine.hasOption(deleteProducts.getOpt()) || commandLine.hasOption(deleteProducts.getLongOpt())){
            ProductsManager productsManager = new ProductsManager();
            try {
                String s = commandLine.getOptionValue(deleteProducts.getOpt());
                productsManager.delete(Integer.parseInt(s));
                System.out.println("Product " + s + " deleted!");
            } catch (NumberFormatException e) {
                System.out.println("Formatting error!");
            } catch (ProductsException e) {
                System.out.println("Database error!");
            }
        }
        else if (commandLine.hasOption(deleteOrders.getOpt()) || commandLine.hasOption(deleteOrders.getLongOpt())){
            OrdersManager ordersManager = new OrdersManager();
            try {
                String s = commandLine.getOptionValue(deleteOrders.getOpt());
                ordersManager.delete(Integer.parseInt(s));
                System.out.println("Order " + s + " deleted!");
            } catch (NumberFormatException e) {
                System.out.println("Formatting error!");
            } catch (OrdersException e) {
                System.out.println("Database error!");
            }
        }
        else if(commandLine.hasOption(updateUsers.getOpt()) || commandLine.hasOption(updateUsers.getLongOpt())) {
            Scanner s = new Scanner(System.in);
            Users u = null;
            System.out.println("User to update (id): ");
            try {
                u = DaoFactory.usersDao().getById(s.nextInt()); s.nextLine();
                System.out.print("First name: " + u.getFirstName() + "\nNew first name: ");
                u.setFirstName(s.nextLine());
                System.out.print("Last name: " + u.getLastName() + "\nNew last name: ");
                u.setLastName(s.nextLine());
                System.out.print("Email: " + u.getEmail() + "\nNew email: ");
                u.setEmail(s.nextLine());
                System.out.print("City: " + u.getCity() + "\nNew city: ");
                u.setCity(s.nextLine());
                System.out.print("Address: " + u.getAddress() + "\nNew address: ");
                u.setAddress(s.nextLine());
                System.out.print("Mobile number: " + u.getMobileNumber() + "\nNew mobile number: ");
                u.setMobileNumber(s.nextLine());
                System.out.print("Username: " + u.getUsername() + "\nNew username: ");
                u.setUsername(s.nextLine());
                System.out.print("Password: " + u.getPassword() + "\nNew password: ");
                u.setPassword(s.nextLine());
                UsersManager usersManager = new UsersManager();
                try {
                    usersManager.update(u);
                } catch (UsersException e) {
                    System.out.println(e.getMessage());
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        else if(commandLine.hasOption(updateProducts.getOpt()) || commandLine.hasOption(updateProducts.getLongOpt())) {
            Scanner s = new Scanner(System.in);
            Products p = null;
            System.out.println("Product to update (id): ");
            try {
                p = DaoFactory.productsDao().getById(s.nextInt()); s.nextLine();
                System.out.print("Name: " + p.getName() + "\n");
                System.out.print("Price: " + p.getPrice() + "\nNew price: ");
                p.setPrice(Double.valueOf(s.nextLine()));
                System.out.print("Quantity: " + p.getQuantity() + "\nNew quantity: ");
                p.setQuantity(Integer.parseInt(s.nextLine()));
                ProductsManager productsManager = new ProductsManager();
                try {
                    productsManager.update(p);
                } catch (ProductsException e) {
                    System.out.println(e.getMessage());
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        else if(commandLine.hasOption(addUsers.getOpt()) || commandLine.hasOption(addUsers.getLongOpt())){
            Scanner s = new Scanner(System.in);
            Users u = new Users();
            System.out.print("First name: ");
            u.setFirstName(s.nextLine());
            System.out.print("Last name: ");
            u.setLastName(s.nextLine());
            System.out.print("Email: ");
            u.setEmail(s.nextLine());
            System.out.print("City: ");
            u.setCity(s.nextLine());
            System.out.print("Address: ");
            u.setAddress(s.nextLine());
            System.out.print("Mobile number: ");
            u.setMobileNumber(s.nextLine());
            System.out.print("Username: ");
            u.setUsername(s.nextLine());
            System.out.print("Password: ");
            u.setPassword(s.nextLine());
            UsersManager usersManager = new UsersManager();
            try {
                System.out.println("Adding new user...");
                usersManager.add(u);
                System.out.println("User added!");
            } catch (UsersException e) {
                System.out.println(e.getMessage());
            }
        }
        else if (commandLine.hasOption(addProducts.getOpt()) || commandLine.hasOption(addProducts.getLongOpt())){
            Scanner s = new Scanner(System.in);
            Products p = new Products();
            System.out.print("Name: ");
            p.setName(s.nextLine());
            System.out.print("Price: ");
            p.setPrice(Double.valueOf(s.nextLine()));
            System.out.print("Quantity: ");
            p.setQuantity(Integer.parseInt(s.nextLine()));
            ProductsManager productsManager = new ProductsManager();
            try {
                System.out.println("Adding new product...");
                productsManager.add(p);
                System.out.println("Product added!");
            } catch (ProductsException e) {
                System.out.println(e.getMessage());
            }
        }
        else {
            printFormattedOptions(options);
        }
    }
}
