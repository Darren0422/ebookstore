# ebookstore
## Description 

This is a Java, SQL and MySQL application that can be used by a bookstore clerk to interact with their book inventory management system. 

This application allows the user to enter a new book into the database, update a book's information, delete a book from the database and search the database to find a specific book. It makes use of a intuitive text-based user interface. 

## Importance 

This repository is important as it shows my understanding and comprehension of Java, SQL, MySQL and databases fundamentals. It has a menu-driven interface for the user to interact with the program. User input handling is taken into consideration, as well as dealing with potential input errors. Their is database interactions which involves involves connecting to a MySQL database, creating a statement, and executing SQL queries. The application makes complies SQL queries dynamically based on user input. 

## Installation

To install this project locally, open the repository in your web browser: [ebookstore](https://github.com/Darren0422/ebookstore). On the repository page, click on the green "Code" button located near the top-right of the page. From the dropdown menu, select "Download ZIP". This will start the download of the repository as a ZIP file to your computer. Save it on your local storage device. 

<img width="1728" alt="Screenshot 2024-01-08 at 11 45 47" src="https://github.com/Darren0422/ebookstore/assets/134398985/9a304146-fd97-4eb4-b441-dcdee9e56b41">

##  Usage

You may open this file with a code editor of your choice, such as, VSCode and gain access to the written lines of code.
Run the program in your editor of choice.

<img width="1728" alt="Screenshot 2024-01-08 at 11 46 13" src="https://github.com/Darren0422/ebookstore/assets/134398985/184800db-a471-490e-9dd9-16e8ed80d513">

## ebookstore Management System
### MySQL Database 

a MySQL Database called "ebookstore" was created. A table for "books" was created with various fields shown below. Example datasets were added.

<img width="1183" alt="Screenshot 2024-01-08 at 12 02 09" src="https://github.com/Darren0422/ebookstore/assets/134398985/2db9b03d-363e-4bc9-b1a6-9534c0580ecc">

### Classes 
#### App (main)

The ATM class is the main class of the ATM application. It creates the new user instance so a login can occur and holds the functionailty for the user interactions.

### Features  
#### Main Menu

The user is prompted to enter their choice for the option they would like to make use of. This prompt will repeat until a valid option is selected or the user quits.

<img width="1365" alt="Screenshot 2024-01-08 at 12 06 09" src="https://github.com/Darren0422/ebookstore/assets/134398985/f76537a0-7720-425d-87cb-22761d382b62">

#### Add Book

This adds a new record to the "books" table in the "ebookstore" database. The user is prompted to enter the book title, author and quantity.

<img width="1365" alt="Screenshot 2024-01-08 at 12 07 14" src="https://github.com/Darren0422/ebookstore/assets/134398985/580f92ed-c8a3-438c-880b-78382d3c6386">

#### Update Book

This updates an existing record within the "books" table in the "ebookstore" database. The user is shown the current library of books and is then prompted to enter the book ID, title, author and quantity.

<img width="1365" alt="Screenshot 2024-01-08 at 12 08 25" src="https://github.com/Darren0422/ebookstore/assets/134398985/16c8eca2-acab-45ee-8d58-e7613fa7cba2">

#### Delete Book

This deletes an existing record from the "books" table in the "ebookstore" database. The user is prompted to enter the book ID for it to be removed.

<img width="1365" alt="Screenshot 2024-01-08 at 12 09 07" src="https://github.com/Darren0422/ebookstore/assets/134398985/d29c438f-fc98-4538-a74d-4672ada6a441">

#### Search Book

The user is prompted to enter their choice for the method to search the database, namely: searching by the book's Title, Author or ID.

<img width="1365" alt="Screenshot 2024-01-08 at 12 09 37" src="https://github.com/Darren0422/ebookstore/assets/134398985/5b3c00b6-4cf6-402a-9d2b-f40a112bd7e0">

##### Search by Title

This searches for an existing record within the "books" table in the "ebookstore" database using the book's title. The user is prompted to enter the book's title they wish to search for. 

##### Search by Author

This searches for an existing record within the "books" table in the "ebookstore" database using the book's author. The user is prompted to enter the book's author they wish to search for.  

##### Search by ID

This searches for an existing record within the "books" table in the "ebookstore" database using the book's ID. The user is prompted to enter the book's ID they wish to search for. 

#### View Inventory

This displays all the existing records within the "books" table in the "ebookstore" database. 

<img width="1365" alt="Screenshot 2024-01-08 at 12 10 23" src="https://github.com/Darren0422/ebookstore/assets/134398985/18c34bae-f431-4568-9b51-dcead33e9cca">

#### Quit

The user quits the application and the book inventory management system is closed.

<img width="1365" alt="Screenshot 2024-01-08 at 12 11 08" src="https://github.com/Darren0422/ebookstore/assets/134398985/e090c571-7791-4fb8-9cae-bd6dc0ad96ac">

## Credits
[Darren Chen](https://github.com/Darren0422)
