import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
* Program Name:	DBUtils.java
* Purpose:  Methods used for database utilities including table modeling, as well as adding and updating elements.
* Student Name: Kang Yang . Phuc Hanh Nguyen
*
*/

public class DBUtils {
	// Class-wide connection information
	static String url = "jdbc:mysql://localhost:3306/info3136_books?allowPublicKeyRetrieval=true&useSSL=false";
	static String user = "root";
	static String password = "password";
	
	static Connection myConn = null;
	static Statement myStmt = null;
	static PreparedStatement myPrepStmt = null;
	static ResultSet rs = null;
	
	
	/**
	* Method Name:	getList
	* Purpose:		Returns a vector of strings to be used in a JComboBox - specifically the author,
	* 				subject, and borrower JComboBoxes
	* Accepts:	    @String column - a string containing the name of the column or table to query
	* Returns:	    Vector<String> - a vector containing author, subject, or borrower strings
	*/
	public synchronized static Vector<String> getList (String column)
	{
		try
		{
			// Establish connection
			myConn = DriverManager.getConnection(url, user, password);

			myStmt = myConn.createStatement();
			
			// Querying for author names
			if (column == "author")
			{
				// Grab author column values
				rs = myStmt.executeQuery("Select AuthorId, First_Name, Last_Name From  info3136_books.author");	
				
				ResultSetMetaData metaData = rs.getMetaData();

				int numberOfColumns = metaData.getColumnCount();

				// instantiate a vector of strings to hold columns names
				Vector<String> authorNames = new Vector<String>();

				// get rows of data and store in vector
				while (rs.next()) {
					String newRow = "";

					for (int i = 1; i <= numberOfColumns; ++i) {
						// store column data in the vector
						// if it's the ID column, add a period
						if (i == 1)
							newRow += rs.getString(i) + ". ";
						// if it's not the last column, add a space
						else if (i != numberOfColumns)
							newRow += rs.getString(i) + " ";
						else
							newRow += rs.getString(i);
					}

					authorNames.addElement(newRow);
				}

				return authorNames;
			}
			
			// Querying for subject names
			else if (column == "subject")
			{
				// Grab unique subject names (no repeats) using distinct SQL keyword
				rs = myStmt.executeQuery("Select Distinct subject From  info3136_books.book");

				ResultSetMetaData metaData = rs.getMetaData();

				int numberOfColumns = metaData.getColumnCount();

				// instantiate a vector of strings to hold columns names
				Vector<String> subjectNames = new Vector<String>();

				// get rows of data and store in vector
				while (rs.next()) {
					String newRow = "";

					for (int i = 1; i <= numberOfColumns; ++i) {
						// store column data in the vector
						newRow += rs.getString(i);
					}

					subjectNames.addElement(newRow);
				}

				return subjectNames;
			}
			
			// Querying for borrower names
			else if (column == "borrower")
			{
				// Grab borrower column values
				rs = myStmt.executeQuery("Select Borrower_Id, First_Name, Last_Name From  info3136_books.borrower");

				ResultSetMetaData metaData = rs.getMetaData();

				int numberOfColumns = metaData.getColumnCount();

				// instantiate a vector of strings to hold columns names
				Vector<String> borrowerNames = new Vector<String>();

				// get rows of data and store in vector
				while (rs.next()) {
					String newRow = "";

					for (int i = 1; i <= numberOfColumns; ++i) {
						// store column data in the vector
						// if it's the ID column, add a period
						if (i == 1)
							newRow += rs.getString(i) + ". ";
						// if it's not the last column, add a space
						else if (i != numberOfColumns)
							newRow += rs.getString(i) + " ";
						else
							newRow += rs.getString(i);
					}

					borrowerNames.addElement(newRow);
				}

				return borrowerNames;
			}

		}
		catch (SQLException e)
		{

			e.printStackTrace();
		}

		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if (myStmt != null)
			try {
				myStmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if (myConn != null)
			try {
				myConn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		return null; // if we went outside the try block return null
	}
	
	/**
	* Method Name:	borrowersToTableModel
	* Purpose:		Create a TableModel of borrowers to be used to create a JTable in the Borrowers tab	
	* Accepts:	    none
	* Returns:	    TableModel containing borrower data values
	*/
	public static synchronized TableModel borrowersToTableModel() 
	{
		try 
		{
			// Establish connection
			myConn = DriverManager.getConnection(url, user, password);

			myStmt = myConn.createStatement();
			
			// Select all borrowers
			rs = myStmt.executeQuery("Select * from info3136_books.borrower");
			
			ResultSetMetaData metaData = rs.getMetaData();

			int numberOfColumns = metaData.getColumnCount();

			// instantiate a vector of strings to hold columns names
			Vector<String> columnNames = new Vector<String>();

			
			// get column names and store in the vector
			for (int column = 1; column <= numberOfColumns; ++column)
			{
				columnNames.addElement(metaData.getColumnName(column));
			}

			// create a DefaultTableModel
			DefaultTableModel model = new DefaultTableModel(columnNames, 0)
			{
				// make only the ID column non-editable
				@Override
				public boolean isCellEditable(int row, int column)
				{
					return column == 0 ? false : true;
				}
			};

			// get rows of data and store in vector
			while (rs.next()) {
				Vector<String> newRow = new Vector<String>();

				for (int i = 1; i <= numberOfColumns; ++i) {
					// store column data in the vector
					newRow.add(rs.getString(i));
				}

				model.addRow(newRow);
			}

			return model;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if (myStmt != null)
			try {
				myStmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if (myConn != null)
			try {
				myConn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		return null; // if we went outside the try block return null
	}
	
	/**
	* Method Name:	updateBorrower
	* Purpose:		Update a borrower's database values based on changes made to the JTable in the Borrowers' tab	
	* Accepts:	    @String first - a string containing the borrower's first name
	* Accepts:	    @String last - a string containing the borrower's last name
	* Accepts:	    @String email - a string containing the borrower's email
	* Accepts:	    @int BorrowerID - the ID of the borrower we are updating
	* Returns:	    void
	*/
	public static synchronized void updateBorrower(String first, String last, String email, int BorrowerID)
	{
		try 
		{
			// Establish connection
			myConn = DriverManager.getConnection(url, user, password);

			// We check this in the listener as well, but just to be sure
			// Note that existing values will mean any update click will query an update (potential area of improvement)
			if (!(first.isEmpty() || last.isEmpty() || email.isEmpty())) {
				// Update Borrower Query
				myPrepStmt = myConn.prepareStatement(
						"UPDATE info3136_books.borrower SET First_Name = ?,	Last_Name = ?, Borrower_Email = ? WHERE Borrower_ID = ?");

				myPrepStmt.setString(1, first);
				myPrepStmt.setString(2, last);
				myPrepStmt.setString(3, email);
				myPrepStmt.setInt(4, BorrowerID);
				
				myPrepStmt.executeUpdate();
			} 
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if (myPrepStmt != null)
			try {
				myPrepStmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if (myConn != null)
			try {
				myConn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	/**
	* Method Name:	addNewBorrower
	* Purpose:		Add a new borrower to the database	
	* Accepts:	    @String first - a string containing the borrower's first name
	* Accepts:	    @String last - a string containing the borrower's last name
	* Accepts:	    @String email - a string containing the borrower's email
	* Returns:	    void
	*/
	public static synchronized void addNewBorrower(String first, String last, String email)
	{
		try 
		{
			// Establish connection
			myConn = DriverManager.getConnection(url, user, password);

			if (!(first.isEmpty() || last.isEmpty() || email.isEmpty())) {
				// Insert Borrower Query
				myPrepStmt = myConn.prepareStatement(
						"INSERT INTO info3136_books.borrower (First_Name, Last_Name, Borrower_email) " + "VALUES (?, ?, ?)");

				myPrepStmt.setString(1, first);
				myPrepStmt.setString(2, last);
				myPrepStmt.setString(3, email);

				myPrepStmt.executeUpdate();
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if (myPrepStmt != null)
			try {
				myPrepStmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if (myConn != null)
			try {
				myConn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	/**
	* Method Name:	checkOutBook
	* Purpose:		Check out a book by selecting a book in the Books JTable and clicking Check-Out.
	* 				A new book_loan entry is added and, if successful, the book is updated to be
	* 				unavailable.	
	* Accepts:	    @param borrowerID - ID of the borrower that is checking out the book
	* Accepts:	    @param loanPeriod - 1, 2 or 3 weeks loan period
	* Accepts:	    @param comment - a comment about the loan. This is considered to be optional.
	* Accepts:	    @param bookID - ID of the book that we are checking out, selected in the JTable
	* Returns:	    void
	*/
	public static synchronized void checkOutBook(int borrowerID, int loanPeriod, String comment, int bookID) 
	{
		try 
		{
			// Establish connection
			myConn = DriverManager.getConnection(url, user, password);
			
			// Insert Book Loan Query
			myPrepStmt = myConn.prepareStatement(
			"INSERT INTO info3136_books.book_loan (Book_BookID, Borrower_Borrower_ID, Comment, Date_out, Date_due) " +
			"VALUES (?, ?, ?, curdate(), DATE_ADD(curdate(), INTERVAL ? WEEK))");

			myPrepStmt.setInt(1, bookID);
			myPrepStmt.setInt(2, borrowerID);
			myPrepStmt.setString(3, comment);
			myPrepStmt.setInt(4, loanPeriod);
			
			// If insert was successful, update book's availability
			if (myPrepStmt.executeUpdate() != 0); {
				myPrepStmt = myConn.prepareStatement("UPDATE info3136_books.book SET Available = 0 WHERE BookID = ?");
				myPrepStmt.setInt(1, bookID);
				myPrepStmt.executeUpdate();
			}			
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if (myPrepStmt != null)
			try {
				myPrepStmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if (myConn != null)
			try {
				myConn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	/**
	* Method Name:	bookParametersToTableModel
	* Purpose:		Returns a TableModel containing a list of books in the database.
	* 				Parameters filter out books by title, author, ISBN, edition, subject and availability (WHERE clause).
	* 				If no parameters are entered, returns all books in the database.
	* Accepts:	    @String title - a string containing the book's title
	* Accepts:	    @String author - a string containing the author's full name
	* Accepts:	    @String ISBN - a string containing a 13 digit integer for the International Standard Book Number
	* Accepts:	    @String edition - a string containing a 3 digit integer that represents the book's edition
	* Accepts:	    @String subject - string containing the subject of the book
	* Accepts:	    @boolean available - boolean for whether a book is available or not.
	* 				Note that bools in MySQL are aliases for 1 or 0: https://dev.mysql.com/doc/refman/8.0/en/numeric-type-overview.html
	* Returns:	    TableModel - a table model containing title, author, ISBN, edition, subject and availability.
	*/
	public static synchronized TableModel bookParametersToTableModel(String title, String author,
																	String ISBN, String edition,
																	String subject, boolean available)
	{
		// a boolean to be used by filterCreator - if true, adds a " WHERE " clause. If false, adds an " AND ".
		boolean isFirstWhere = true;
		
		String titleInput = "";
		String ISBNInput = "";
		String editionInput = "";
		String subjectInput = "";
		String authorInput = "";
		String availableInput = "";
		
		try
		{
			// Establish connection
			myConn = DriverManager.getConnection(url, user, password);

			myStmt = myConn.createStatement();

			// Query to search all books
			String querySearchString = "Select BookId, Title, concat(First_Name, ' ', Last_Name) as 'Author', ISBN, Edition_Number, Subject, Available From info3136_books.book b "
					+ "INNER JOIN info3136_books.book_author ba on b.BookId = ba.Book_BookID "
					+ "INNER JOIN info3136_books.author a on ba.Author_AuthorId = a.AuthorId ";

			// If no entered parameters, return all books
			if (title.isEmpty() && author.isEmpty() && ISBN.isEmpty() 
					&& edition.isEmpty() && subject.isEmpty()
					&& !available)
			{
				rs = myStmt.executeQuery(querySearchString + "ORDER BY 1");
			}
			
			// Filter parameters to add WHERE clause
			else 
			{
				if (!title.isEmpty()) {
					titleInput = filterCreator(isFirstWhere, "Title", title);
					isFirstWhere = false;
				}
				if (!ISBN.isEmpty()) {
					ISBNInput = filterCreator(isFirstWhere, "ISBN", ISBN);
					isFirstWhere = false;
				}
				if (!edition.isEmpty()) {
					editionInput = filterCreator(isFirstWhere, "Edition_Number", edition);
					isFirstWhere = false;
				}
				if (!subject.isEmpty()) {
					subjectInput = filterCreator(isFirstWhere, "Subject", subject);
					isFirstWhere = false;
				}
				if (!author.isEmpty()) {
					authorInput = filterCreator(isFirstWhere, "AuthorId", author);
					isFirstWhere = false;
				}
				if (available) {
					if (isFirstWhere) {
						availableInput += " WHERE ";
					} else {
						availableInput += " AND ";
					}

					availableInput += "Available = TRUE ";
				}
				
				/*
				 * if (!available) { if (isFirstWhere) { availableInput += " WHERE "; } else {
				 * availableInput += " AND "; }
				 * 
				 * availableInput += "Available = FALSE "; }
				 */
				 
				// Execute new SQL statement with added WHERE clauses
				rs = myStmt.executeQuery(querySearchString + titleInput + ISBNInput + editionInput + subjectInput
						+ authorInput + availableInput + "ORDER BY 1");
			}
			
			ResultSetMetaData metaData = rs.getMetaData();

			int numberOfColumns = metaData.getColumnCount();

			// instantiate a vector of strings to hold columns names
			Vector<String> columnNames = new Vector<String>();

			
			// get column names and store in the vector
			for (int column = 1; column <= numberOfColumns; ++column) {
				columnNames.addElement(metaData.getColumnName(column));
			}

			// create a DefaultTableModel
			DefaultTableModel model = new DefaultTableModel(columnNames, 0)
			{
				// make the table non-editable
				@Override
				public boolean isCellEditable(int row, int column)
				{
					return false;
				}
			};

			// get rows of data and store in vector
			while (rs.next()) {
				Vector<String> newRow = new Vector<String>();

				for (int i = 1; i <= numberOfColumns; ++i) {
					// store column data in the vector
					// if available column
					if (i == 7)
					{
						if (rs.getString(i).equals("1"))
							newRow.add("Yes");
						else
							newRow.add("No");
					}
					else
						newRow.add(rs.getString(i));
				}

				model.addRow(newRow);
			}

			return model;

		} catch (SQLException e)
		{

			e.printStackTrace();
		}

		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if (myStmt != null)
			try {
				myStmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if (myConn != null)
			try {
				myConn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		return null; // if we went outside the try block return null
	}
	
	/**
	* Method Name:	checkInBook
	* Purpose:		Checks-in book, updating book_loan return date.
	* 				If the loan update is successful, update the availability of the book.
	* Accepts:	    @int bookId - the ID of the book we are checking in
	* Returns:	    void
	*/
	public static synchronized void checkInBook (int bookId)
	{
		try
		{
			// Establish connection
			myConn = DriverManager.getConnection(url, user, password);
			
			// Update Book Loan Query
			myPrepStmt = myConn.prepareStatement("UPDATE info3136_books.book_loan SET Date_returned = curdate() WHERE Book_BookID = ?");
			
			myPrepStmt.setInt(1, bookId);
			
			// If the update is successful
			if (myPrepStmt.executeUpdate() != 0)
			{
				// Update Book Availability Query
				myPrepStmt = myConn.prepareStatement("UPDATE info3136_books.book SET Available = 1 WHERE BookID = ?");
				myPrepStmt.setInt(1, bookId);
				myPrepStmt.executeUpdate();
			}

		} catch (SQLException e)
		{

			e.printStackTrace();
		}

		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if (myPrepStmt != null)
			try {
				myPrepStmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if (myConn != null)
			try {
				myConn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	/**
	* Method Name:	addNewBook
	* Purpose:		Adds a new book to the database written by one or more authors based on selected table rows.
	* 				INSERTS are made to the book and book_author tables.
	* Accepts:	    @String title - a string containing the book's title
	* Accepts:	    @String ISBN - a string containing a 13 digit integer for the International Standard Book Number
	* Accepts:	    @String edition - a string containing a 3 digit integer that represents the book's edition
	* Accepts:	    @String subject - string containing the subject of the book
	* Accepts:	    @int[] authorIDArray - an array containing AuthorIDs
	* Returns:	    void
	*/
	public static synchronized void addNewBook (String title, String ISBN, String edition, String subject, int[] authorIDArray)
	{
		try 
		{
			// Establish connection
			myConn = DriverManager.getConnection(url, user, password);

			if (!(title.isEmpty() || ISBN.isEmpty() || edition.isEmpty() || subject.isEmpty() || authorIDArray.length == 0))
			{
				// Add Book Query
				myPrepStmt = myConn.prepareStatement("INSERT INTO info3136_books.book (Title, ISBN, Edition_Number, Subject, Available) " + 
						"VALUES (?, ?, ?, ?, true)");
				
				myPrepStmt.setString(1, title);
				myPrepStmt.setString(2, ISBN);
				myPrepStmt.setString(3, edition);
				myPrepStmt.setString(4, subject);
				
				myPrepStmt.executeUpdate();
				
				// Add Book_Author entry or entries
				// Grab ID of book using last_insert_id(), which is stored at the first column
				myStmt = myConn.createStatement();
				rs = myStmt.executeQuery("Select last_insert_id()");
				rs.next();
				int bookID = rs.getInt(1);
				
				// Add Book_Author Query
				myPrepStmt = myConn.prepareStatement("INSERT INTO info3136_books.book_author (Book_BookID, Author_AuthorID) " + 
						"VALUES (?, ?)");
				
				// For each author, add a new Book_Author entry
				for (int i = 0; i < authorIDArray.length; ++i)
				{
					myPrepStmt.setInt(1, bookID);
					myPrepStmt.setInt(2, authorIDArray[i]);
					myPrepStmt.executeUpdate();
				}
				
			}			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if (myStmt != null)
			try {
				myStmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if (myConn != null)
			try {
				myConn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	/**
	* Method Name:	addNewAuthor
	* Purpose:		Adds a new author to the database.	
	* Accepts:	    @String first - author's first name
	* Accepts:	    @String last - author's last name
	* Returns:	    void
	*/
	public static synchronized void addNewAuthor(String first, String last)
	{
		try 
		{
			// Establish connection
			myConn = DriverManager.getConnection(url, user, password);

			if (!(first.isEmpty() || last.isEmpty()))
			{
				// Insert New Author query
				myPrepStmt = myConn.prepareStatement(
						"INSERT INTO info3136_books.author (First_Name, Last_Name) " + "VALUES (?, ?)");

				myPrepStmt.setString(1, first);
				myPrepStmt.setString(2, last);
				myPrepStmt.executeUpdate();
			} 
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if (myStmt != null)
			try {
				myStmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if (myConn != null)
			try {
				myConn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	/**
	* Method Name:	authorsToTableModel
	* Purpose:		Creates a TableModel to be used to construct a JTable with author names	
	* Accepts:	    none
	* Returns:	    TableModel containing author names
	*/
	public static synchronized TableModel authorsToTableModel()
	{
		try
		{
			// Establish Connection
			myConn = DriverManager.getConnection(url, user, password);

			myStmt = myConn.createStatement();

			// Select Authors Query
			String loanedBooksString = "Select * From info3136_books.author";
			
			rs = myStmt.executeQuery(loanedBooksString);
			
			ResultSetMetaData metaData = rs.getMetaData();

			int numberOfColumns = metaData.getColumnCount();

			// instantiate a vector of strings to hold columns names
			Vector<String> columnNames = new Vector<String>();

			
			// get column names and store in the vector
			for (int column = 1; column <= numberOfColumns; ++column) {
				columnNames.addElement(metaData.getColumnName(column));
			}

			// create a DefaultTableModel
			DefaultTableModel model = new DefaultTableModel(columnNames, 0)
			{
				// make the table non-editable
				@Override
				public boolean isCellEditable(int row, int column)
				{
					return false;
				}
			};

			// get rows of data and store in vector
			while (rs.next()) {
				Vector<String> newRow = new Vector<String>();

				for (int i = 1; i <= numberOfColumns; ++i) {
					// store column data in the vector
					newRow.add(rs.getString(i));
				}

				model.addRow(newRow);
			}

			return model;

		} catch (SQLException e)
		{

			e.printStackTrace();
		}

		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if (myStmt != null)
			try {
				myStmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if (myConn != null)
			try {
				myConn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		return null; // if we went outside the try block return null
	}
	
	/**
	* Method Name:	loansToTableModel
	* Purpose:		Creates a TableModel to be used to construct a JTable with book loans
	* Accepts:	    @boolean isOverdue - if true, only contains overdue books
	* Returns:	    TableModel containing book loans
	*/
	public static synchronized TableModel loansToTableModel(boolean isOverdue)
	{
		try
		{
			// Establish Connection
			myConn = DriverManager.getConnection(url, user, password);

			myStmt = myConn.createStatement();

			// Select Loaned Books Query
			String loanedBooksString = "Select info3136_books.book_loan.Book_BookID, info3136_books.book.Title , concat(info3136_books.borrower.First_Name, ' ', info3136_books.borrower.Last_Name) As 'Full Name of Borrower', " +
										" info3136_books.book_loan.Date_due "
										+ "From  info3136_books.book INNER JOIN info3136_books.book_loan  ON info3136_books.book.BookID = info3136_books.book_loan.Book_BookID " + 
										"INNER JOIN info3136_books.borrower  ON info3136_books.borrower.Borrower_ID = info3136_books.book_loan.Borrower_Borrower_ID " + 
										"WHERE Date_returned IS NULL";
			
			// Select only overdue books
			if (isOverdue)
				loanedBooksString += " AND info3136_books.book_loan.Date_due < curdate()";
			
			rs = myStmt.executeQuery(loanedBooksString);
			
			ResultSetMetaData metaData = rs.getMetaData();

			int numberOfColumns = metaData.getColumnCount();

			// instantiate a vector of strings to hold columns names
			Vector<String> columnNames = new Vector<String>();

			
			// get column names and store in the vector
			for (int column = 1; column <= numberOfColumns; ++column) {
				columnNames.addElement(metaData.getColumnName(column));
			}

			// create a DefaultTableModel
			DefaultTableModel model = new DefaultTableModel(columnNames, 0)
			{
				// make the table non-editable
				@Override
				public boolean isCellEditable(int row, int column)
				{
					return false;
				}
			};

			// get rows of data and store in vector
			while (rs.next()) {
				Vector<String> newRow = new Vector<String>();

				for (int i = 1; i <= numberOfColumns; ++i) {
					// store column data in the vector
					newRow.add(rs.getString(i));
				}

				model.addRow(newRow);
			}

			return model;

		} catch (SQLException e)
		{

			e.printStackTrace();
		}

		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if (myStmt != null)
			try {
				myStmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if (myConn != null)
			try {
				myConn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		return null; // if we went outside the try block return null
	}
	
	/**
	 * Name: filterCreator
	 * Purpose: Returns a string with properly formatted SQL 'where' statement (the 'filter')
	 * Accepts: Bool determining if first 'where' clause
	 * 			String containing column name String containing value to filter by Returns:
	 * 			String containing SQL filter statement
	 */
	public static String filterCreator(boolean isFirstWhere, String columnName, String inputText) {
		String returnString = "";

		// Check if this is the first where clause
		if (isFirstWhere) {
			returnString += " WHERE ";
		} else {
			returnString += " AND ";
		}
		
		// Use "LIKE" to return items that contain that text. Use "WHERE" to contain only that exact text.
		returnString += columnName + " LIKE '%" + inputText + "%'";

		return returnString;
	}	
}
