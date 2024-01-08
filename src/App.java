import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        try {
            // Connect to the library_db database, via the jdbc:mysql: channel on localhost
            // Use username "otheruser", password "swordfish".
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ebookstore?useSSL=false",
                    "otheruser",
                    "swordfish");
            // Create a direct line to the database for running our queries
            Statement statement = connection.createStatement();

            Scanner scanner = new Scanner(System.in);
            int choice;

            // Continues to prompt the user to select an option until they quit the application.
            do {
                int lastId = getLastIdFromTable(statement);
                int firstID = getFirstIdFromTable(statement);
                printMenu();

                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        addBook(statement, lastId, scanner);
                        break;

                    case 2:
                        updateBook(statement, lastId, firstID, scanner);
                        break;

                    case 3:
                        deleteBook(statement, lastId, firstID, scanner);
                        break;

                    case 4:
                        searchBook(statement, scanner);
                        break;

                    case 5:
                        printAllFromTable(statement);
                        break;

                    case 0:
                        System.out.println("Exiting...");
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } while (choice != 0);

            statement.close();
            connection.close();
            scanner.close();

        } catch (SQLException e) {
            System.out.println("Database connection error:");

        }
    }

    /*
     * Prints the main menu prompt for the user
     */
    public static void printMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Enter book");
        System.out.println("2. Update book");
        System.out.println("3. Delete book");
        System.out.println("4. Search books");
        System.out.println("5. View inventory");
        System.out.println("0. Exit");

        System.out.print("\nEnter the number of your choice: ");
    }

    /**
     * Adds a new book to the table in the database.
     * 
     * @param statement on an existing connection
     * @param lastId    the last ID existing in the table
     * @param scanner   the scanner object used for the user input
     * @throws SQLException
     */
    public static void addBook(Statement statement, int lastId, Scanner scanner) throws SQLException {
        int rowsAffected;
        int newId = lastId + 1;

        System.out.print("\nEnter book title: ");
        String title = scanner.nextLine();

        System.out.print("\nEnter author name: ");
        String author = scanner.nextLine();

        int newQuantity = 0;
        boolean validInputAdd = false;

        while (!validInputAdd) {
            try {
                System.out.print("\nEnter quantity: ");
                newQuantity = scanner.nextInt();
                scanner.nextLine();
                validInputAdd = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer for quantity.");
                scanner.nextLine();
            }
        }

        // Inserts the new book into the db
        rowsAffected = statement.executeUpdate(
                "INSERT INTO books VALUES (" + newId + ", '" + title + "', '" + author + "', "
                        + newQuantity + ")");

        System.out.println("Query complete, " + rowsAffected + " rows added.");
    }

    /**
     * Updates an existing book within the table in the database by using the book
     * ID.
     * 
     * @param statement on an existing connection
     * @param lastId    the last ID existing in the table
     * @param firstID   the first ID existing in the table
     * @param scanner   the scanner object used for the user input
     * @throws SQLException
     */
    public static void updateBook(Statement statement, int lastId, int firstID, Scanner scanner) throws SQLException {
        int rowsAffected;

        System.out.print("\nCurrent library: \n");
        printAllFromTable(statement);

        System.out.print("\nWhat book would you like to update? Enter the book ID: ");
        int toUpdate = -1;
        boolean validInputToUpdate = false;

        while (!validInputToUpdate) {
            try {
                toUpdate = scanner.nextInt();
                scanner.nextLine();

                // Checks if its a positive value and within a range of table id's
                if (toUpdate > 0 && toUpdate >= firstID && toUpdate <= lastId) {
                    validInputToUpdate = true;
                } else {
                    System.out.println("Invalid book ID. Please enter a valid ID.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer for book ID.");
                scanner.nextLine();
            }
        }

        System.out.print("\nEnter book title: ");
        String updateTitle = scanner.nextLine();

        System.out.print("\nEnter author name: ");
        String updateAuthor = scanner.nextLine();

        int updateQuantity = 0;
        boolean validInputUpdate = false;

        while (!validInputUpdate) {
            try {
                System.out.print("\nEnter quantity: ");
                updateQuantity = scanner.nextInt();
                scanner.nextLine();
                validInputUpdate = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer for quantity.");
                scanner.nextLine();
            }
        }

        // updates the existing book in the db
        rowsAffected = statement.executeUpdate(
                "UPDATE books " + "SET Title = '" + updateTitle + "', Author = '" + updateAuthor
                        + "', Qty = " + updateQuantity + " " + "WHERE ID = " + toUpdate);

        System.out.println("Query complete, " + rowsAffected + " row updated.");
    }

    /**
     * Deletes a book from the table in the database by using the book ID.
     * 
     * @param statement on an existing connection
     * @param lastId    the last ID existing in the table
     * @param firstID   the first ID existing in the table
     * @param scanner   the scanner object used for the user input
     * @throws SQLException
     */
    public static void deleteBook(Statement statement, int lastId, int firstID, Scanner scanner) throws SQLException {
        int rowsAffected;
        int toDelete = -1;
        boolean validInputDelete = false;

        printAllFromTable(statement);

        System.out.print("\nWhat book would you like to delete? Enter the book ID: ");

        while (!validInputDelete) {
            try {
                toDelete = scanner.nextInt();
                scanner.nextLine();

                // Checks if its a positive value and within a range of table id's
                if (toDelete > 0 && toDelete >= firstID && toDelete <= lastId) {
                    validInputDelete = true;
                } else {
                    System.out.println("Invalid book ID. Please enter a valid ID.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer for book ID.");
                scanner.nextLine();
            }
        }

        // Delete the book in the db
        rowsAffected = statement.executeUpdate(
                "DELETE FROM books WHERE id = " + toDelete + "");

        System.out.println("Query complete, " + rowsAffected + " row removed.");
    }

    /*
     * Prints the search menu prompt for the user
     */
    public static void printSearchMenu() {
        System.out.println("\nHow would you like to search?:");
        System.out.println("1. Title");
        System.out.println("2. Author");
        System.out.println("3. ID");

        System.out.print("\nEnter the number of your choice: ");
    }

    /**
     * Search menu with options that the user can choose, namely: search by title,
     * author and ID.
     * Executes the chosen search method.
     * 
     * @param statement on an existing connection
     * @param scanner   the scanner object used for the user input
     * @throws SQLException
     */
    public static void searchBook(Statement statement, Scanner scanner) throws SQLException {
        int searchChoice;

        printSearchMenu();
        searchChoice = scanner.nextInt();
        scanner.nextLine();

        switch (searchChoice) {
            case 1:
                searchByTitle(statement, scanner);
                break;

            case 2:
                searchByAuthor(statement, scanner);
                break;

            case 3:
                searchByID(statement, scanner);
                break;

            default:
                System.out.println("Invalid choice. Please try again.");
        }

    }

    /**
     * Searches the records from the books table using the book's title
     * 
     * @param statement on an existing connection
     * @param scanner   the scanner object used for the user input
     * @throws SQLException
     */
    public static void searchByTitle(Statement statement, Scanner scanner) throws SQLException {
        ResultSet results;

        System.out.print("\nEnter book Title to search: ");
        String searchTitle = scanner.nextLine();

        // Execute query to search for books by title
        results = statement.executeQuery(
                "SELECT id, Author, Qty FROM books WHERE Title = '" + searchTitle + "'");

        boolean titleFound = false;

        // While loop to iterate through the results and print it
        while (results.next()) {
            int id = results.getInt("id");
            String foundAuthor = results.getString("Author");
            int qty = results.getInt("Qty");

            System.out.println("\nBook ID: " + id);
            System.out.println("Title: " + searchTitle);
            System.out.println("Author: " + foundAuthor);
            System.out.println("Quantity: " + qty);

            titleFound = true;
        }

        if (!titleFound) {
            System.out.println("\nNo books found with the title: " + searchTitle);
        }

    }

    /**
     * Searches the records from the books table using the Author's name
     * 
     * @param statement on an existing connection
     * @param scanner   the scanner object used for the user input
     * @throws SQLException
     */
    public static void searchByAuthor(Statement statement, Scanner scanner) throws SQLException {
        ResultSet results;

        System.out.print("\nEnter book Author to search: ");
        String searchAuthor = scanner.nextLine();

        // Execute query to search for books by author
        results = statement.executeQuery(
                "SELECT id, Title, Qty FROM books WHERE Author = '" + searchAuthor + "'");

        boolean authorFound = false;

        // While loop to iterate through the results and print it
        while (results.next()) {
            int id = results.getInt("id");
            String foundTitle = results.getString("Title");
            int qty = results.getInt("Qty");

            System.out.println("\nBook ID: " + id);
            System.out.println("Title: " + foundTitle);
            System.out.println("Author: " + searchAuthor);
            System.out.println("Quantity: " + qty);

            authorFound = true;
        }

        if (!authorFound) {
            System.out.println("\nNo books found from the author: " + searchAuthor);
        }

    }

    /**
     * Searches the records from the books table using the book's ID
     * 
     * @param statement on an existing connection
     * @param scanner   the scanner object used for the user input
     * @throws SQLException
     */
    public static void searchByID(Statement statement, Scanner scanner) throws SQLException {
        ResultSet results;
        int searchID;

        System.out.print("\nEnter book ID to search: ");
        searchID = scanner.nextInt();
        scanner.nextLine();

        // Execute query to search for books by id
        results = statement.executeQuery(
                "SELECT Author, Title, Qty FROM books WHERE id = '" + searchID + "'");

        boolean idFound = false;

        // While loop to iterate through the results and print it
        while (results.next()) {
            String foundTitle = results.getString("Title");
            String foundAuthor = results.getString("Author");
            int qty = results.getInt("Qty");

            System.out.println("\nBook ID: " + searchID);
            System.out.println("Title: " + foundTitle);
            System.out.println("Author: " + foundAuthor);
            System.out.println("Quantity: " + qty);

            idFound = true;
        }

        if (!idFound) {
            System.out.println("\nNo books found with the ID: " + searchID);
        }

    }

    /**
     * Prints all the records from the books table within the database
     * 
     * @param statement on an existing connection
     * @return Prints all the records from the books table
     * @throws SQLException
     */
    public static void printAllFromTable(Statement statement) throws SQLException {

        ResultSet results = statement.executeQuery("SELECT id, Title, Author, Qty FROM books");
        System.out.println("\nCurrent library:");
        while (results.next()) {
            System.out.println(
                    results.getInt("id") + ", "
                            + results.getString("Title") + ", "
                            + results.getString("Author") + ", "
                            + results.getInt("Qty"));
        }
    }

    /**
     * Retrieves the last ID from the books table in the database.
     *
     * @param statement on an existing connection
     * @return the last ID found in the books table
     * @throws SQLException
     */
    public static int getLastIdFromTable(Statement statement) throws SQLException {
        int lastId = 0;
        ResultSet resultSet = statement.executeQuery("SELECT MAX(id) as max_id FROM books");
        if (resultSet.next()) {
            lastId = resultSet.getInt("max_id");
        }
        return lastId;
    }

    /**
     * Retrieves the first ID from the books table in the database.
     * 
     * @param statement on an existing connection
     * @return the first ID found in the books table
     * @throws SQLException
     */
    public static int getFirstIdFromTable(Statement statement) throws SQLException {
        int firstId = 0;
        ResultSet resultSet = statement.executeQuery("SELECT MIN(id) as min_id FROM books");
        if (resultSet.next()) {
            firstId = resultSet.getInt("min_id");
        }
        return firstId;
    }

}
