import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

/**
* Program Name:	BookLoanController.java
* Purpose:  Class will act as the controller and the view for the book loans application.
* Coder:	 Kang Yang . Phuc Hanh Nguyen
*  
*/

public class BookLoanController extends JFrame implements ActionListener
{
	
	// ************************ Book Tab Components ************************
	// User Input (textfield, checkbox, combobox)
	private JTextField titleTextBook = new JTextField(20);
	private JTextField ISBNTextBook = new JTextField(20);
	private JTextField editionTextBook = new JTextField(20);
	private JTextField commentTextBook = new JTextField(20);
	private JCheckBox availableCheckBoxBook = new JCheckBox();	
	private JComboBox<String> authorDropDownBook;
	private JComboBox<String> subjectDropDownBook;
	private JComboBox<String> borrowerDropDownBook;
	String[] weeks = new String[] {"1 week", "2 weeks", "3 weeks", "4 weeks"};
	private JComboBox<String> loanPeriodDropDownBook = new JComboBox<String>(weeks);
	
	// Buttons
	private JButton searchBtnBooks = new JButton("Search");
	private JButton clearBtnBooks = new JButton("Clear");
	private JButton checkoutBtnBooks = new JButton("Check-Out");
	
	// Table
	private JTable tableBooks;
	
	// ScrollPane
	private JScrollPane scrollPaneBooks;	
	
	// Panels
	private JPanel backgroundPanelBooks = new JPanel(new BorderLayout());
	private JPanel tablePanelBooks = new JPanel(new BorderLayout());
	private JPanel searchPanelBooks = new JPanel(new GridLayout(3, 4, 10, 10));
	private JPanel btnPanelBooks = new JPanel(new FlowLayout());
	private JPanel srcAndbtnPanelBooks = new JPanel(new BorderLayout());
	private JPanel addPanelBooks = new JPanel(new FlowLayout());
	private JPanel footerPanelBooks = new JPanel(new BorderLayout());
	private JPanel footerLb = new JPanel(new GridLayout(2,1));
	
	// Main background panel
	private JPanel backgroundPanel = new JPanel(new BorderLayout());
	
	// ************************ Borrower Tab Components ************************
	// Panels
	private JPanel backgroundPanelBorrowers = new JPanel(new BorderLayout());	
	private JPanel tablePanelBorrowers = new JPanel(new BorderLayout());	
	private JPanel btnPanelBorrowers = new JPanel(new FlowLayout());
	private JPanel addPanelBorrowers = new JPanel(new FlowLayout());
	private JPanel boPanelBorrower = new JPanel(new BorderLayout()); 
	
	// User Input (textfield)
	private JTextField firstNameTextBorrowers = new JTextField(20);
	private JTextField lastNameTextBorrowers = new JTextField(20);
	private JTextField emailTextBorrowers = new JTextField(20);
	
	// Buttons
	//private JButton searchBtnBorrowers = new JButton("Search");
	private JButton updateBtnBorrowers = new JButton("Update");
	private JButton clearBtnBorrowers = new JButton("Clear");
	private JButton addBtnBorrowers = new JButton("Add");
	
	// Table
	private JTable tableBorrowers;

	// ScrollPane
	private JScrollPane scrollPaneBorrowers;
	
	// ************************ Book on Loan Tab Components ************************
	// Panels
	private JPanel backgroundPanelLoans = new JPanel(new BorderLayout());
	private JPanel tablePanelLoans = new JPanel(new BorderLayout());
	private JPanel btnPanelLoans = new JPanel(new FlowLayout());
	
	// Buttons
	private JButton searchBtnLoans = new JButton("Search");
	private JButton checkinBtnLoans = new JButton("Return to Library");
	
	// User Input (checkbox)
	private JCheckBox overdueCheckBoxLoans = new JCheckBox();
	
	// Table
	private JTable tableLoans;
	
	// ScrollPane
	private JScrollPane scrollPaneLoans;
	
	// ************************ Add Book Tab Components ************************
	// Panels
	private JPanel backgroundPanelAddBook = new JPanel(new BorderLayout());
	private JPanel addPanelAddBook = new JPanel(new GridLayout(2, 4, 10, 10));
	private JPanel addPanelAddBookAddbtn = new JPanel(new FlowLayout());
	private JPanel addTotal = new JPanel(new BorderLayout());
	private JPanel tablePanelAddBook = new JPanel(new BorderLayout());
	private JPanel addAuthorPanelAddBook = new JPanel(new FlowLayout());
	private JPanel auPanel = new JPanel(new BorderLayout()); 
	
	
	// Buttons
	private JButton addBtnAddBook = new JButton("Add Book");
	private JButton clearBtnAddBook = new JButton("Clear");
	private JButton addAuthorBtnAddBook = new JButton("Add Author");
	
	// User Input (textfield)
	private JTextField titleTextAddBook = new JTextField(20);
	private JTextField ISBNTextAddBook = new JTextField(20);
	private JTextField editionTextAddBook = new JTextField(20);
	private JTextField subjectTextAddBook = new JTextField(20);
	private JTextField firstNameTextAddBook = new JTextField(20);
	private JTextField lastNameTextAddBook = new JTextField(20);
	
	// Table
	private JTable tableAuthorAddBook;
	
	// ScrollPane
	private JScrollPane scrollPaneAddBook;	
	
	//constructor
	public BookLoanController()
	{
		super("JDBC Library Application - Kang Yang & Phuc Hanh Nguyen");
		
		// boilerplate code
		this.setLayout(new FlowLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1000, 720);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		//************************ Book Tab Components ************************
		// Search Panel
		searchPanelBooks.add(new JLabel("Title:", JLabel.LEFT));
		searchPanelBooks.add(titleTextBook);
		searchPanelBooks.add(new JLabel("Author:", JLabel.LEFT));
		authorDropDownBook = new JComboBox<String>(DBUtils.getList("author"));
		authorDropDownBook.setSelectedIndex(-1);
		searchPanelBooks.add(authorDropDownBook);
		searchPanelBooks.add(new JLabel("ISBN:", JLabel.LEFT));
		searchPanelBooks.add(ISBNTextBook);
		searchPanelBooks.add(new JLabel("Subject:", JLabel.LEFT));
		subjectDropDownBook = new JComboBox<String>(DBUtils.getList("subject"));
		subjectDropDownBook.setSelectedIndex(-1);
		searchPanelBooks.add(subjectDropDownBook);
		searchPanelBooks.add(new JLabel("Edition:", JLabel.LEFT));
		searchPanelBooks.add(editionTextBook);		
		searchPanelBooks.add(new JLabel("Available:", JLabel.LEFT));
		searchPanelBooks.add(availableCheckBoxBook);
		
		// Button Panel
		btnPanelBooks.add(new JLabel("LIST OF BOOK: "));
		btnPanelBooks.add(searchBtnBooks);
		btnPanelBooks.add(clearBtnBooks);
		
		// Add Loan Panel
		addPanelBooks.add(new JLabel("Borrower:"));
		borrowerDropDownBook = new JComboBox<String>(DBUtils.getList("borrower"));
		borrowerDropDownBook.setSelectedIndex(-1);
		addPanelBooks.add(borrowerDropDownBook);
		addPanelBooks.add(new JLabel("Loan Period:"));
		addPanelBooks.add(loanPeriodDropDownBook);
		addPanelBooks.add(new JLabel("Comment:"));
		addPanelBooks.add(commentTextBook);
		addPanelBooks.add(checkoutBtnBooks);
		
		// Footer Panel
		JLabel footerLb1 = new JLabel("Borrow a Book",JLabel.CENTER);
		footerLb1.setFont(new Font("Courier New", Font.BOLD, 20));
		JLabel footerLb2 = new JLabel("Select a book from the list above and click Check-Out to borrow.", JLabel.CENTER);		
		footerLb.add(footerLb1);
		footerLb.add(footerLb2);
		footerPanelBooks.add(footerLb, BorderLayout.NORTH);
		footerPanelBooks.add(addPanelBooks, BorderLayout.CENTER);
		
		// Table Panel	
		String blank = "";
		tableBooks = new JTable(DBUtils.bookParametersToTableModel(
				blank, blank, blank, blank, blank, false));
		scrollPaneBooks = new JScrollPane(tableBooks);
		tablePanelBooks.add(scrollPaneBooks);
		
		// Search and button Panel		
		srcAndbtnPanelBooks.add(searchPanelBooks,BorderLayout.CENTER);
		srcAndbtnPanelBooks.add(btnPanelBooks,BorderLayout.SOUTH);
		// Background Panel	
		backgroundPanelBooks.add(srcAndbtnPanelBooks, BorderLayout.NORTH);
		backgroundPanelBooks.add(tablePanelBooks, BorderLayout.CENTER);
		backgroundPanelBooks.add(footerPanelBooks, BorderLayout.SOUTH);
		
		
		//************************ Borrower Tab Components ************************
		// Button Panel
		btnPanelBorrowers.add(new JLabel("Double click a cell to change its contents, press ENTER, then click Update.", JLabel.LEFT));
		//btnPanelBorrowers.add(searchBtnBorrowers);
		btnPanelBorrowers.add(updateBtnBorrowers);
		
		// Table Panel		
		tableBorrowers = new JTable(DBUtils.borrowersToTableModel());
		scrollPaneBorrowers = new JScrollPane(tableBorrowers);
		tablePanelBorrowers.add(scrollPaneBorrowers);
		
		// Add Borrower Panel
		addPanelBorrowers.add(new JLabel("Last Name:", JLabel.LEFT));
		addPanelBorrowers.add(lastNameTextBorrowers);
		addPanelBorrowers.add(new JLabel("First Name:", JLabel.LEFT));
		addPanelBorrowers.add(firstNameTextBorrowers);
		addPanelBorrowers.add(new JLabel("Email:", JLabel.LEFT));
		addPanelBorrowers.add(emailTextBorrowers);		
		addPanelBorrowers.add(addBtnBorrowers);		
		
		JLabel bofooterLb1 = new JLabel("Add new Borrower",JLabel.CENTER);
		bofooterLb1.setFont(new Font("Courier New", Font.BOLD, 20));		
		boPanelBorrower.add(bofooterLb1, BorderLayout.NORTH);
		boPanelBorrower.add(addPanelBorrowers, BorderLayout.CENTER);
		
		// Background Panel
		backgroundPanelBorrowers.add(btnPanelBorrowers, BorderLayout.NORTH);
		backgroundPanelBorrowers.add(tablePanelBorrowers, BorderLayout.CENTER);
		backgroundPanelBorrowers.add(boPanelBorrower, BorderLayout.SOUTH);
		
		//************************ Book Loan Tab Components ************************
		// Button Panel				
		btnPanelLoans.add(new JLabel("List of books on loans. Select a book and click 'Return to Library' to return it.     ", JLabel.LEFT));		
		btnPanelLoans.add(searchBtnLoans);
		btnPanelLoans.add(checkinBtnLoans);
		btnPanelLoans.add(new JLabel("      Overdue:", JLabel.RIGHT));
		btnPanelLoans.add(overdueCheckBoxLoans);
		
		// Table Panel
		tableLoans = new JTable(DBUtils.loansToTableModel(false));
		scrollPaneLoans = new JScrollPane(tableLoans);
		tablePanelLoans.add(scrollPaneLoans);
		
		// Background Panel
		backgroundPanelLoans.add(btnPanelLoans, BorderLayout.NORTH);
		backgroundPanelLoans.add(tablePanelLoans, BorderLayout.CENTER);
		
		//************************ Add Book/Author Tab Components ************************
		// Add Book Panel
		addPanelAddBook.add(new JLabel("Title:", JLabel.LEFT));
		addPanelAddBook.add(titleTextAddBook);
		addPanelAddBook.add(new JLabel("ISBN:", JLabel.LEFT));
		addPanelAddBook.add(ISBNTextAddBook);
		addPanelAddBook.add(new JLabel("Edition:", JLabel.LEFT));
		addPanelAddBook.add(editionTextAddBook);
		addPanelAddBook.add(new JLabel("Subject:", JLabel.LEFT));
		addPanelAddBook.add(subjectTextAddBook);
		
		addPanelAddBookAddbtn.add(new JLabel("Select one or more authors to add a book."));
		addPanelAddBookAddbtn.add(addBtnAddBook);
		addPanelAddBookAddbtn.add(clearBtnAddBook);	
		addTotal.add(addPanelAddBook, BorderLayout.CENTER);
		addTotal.add(addPanelAddBookAddbtn,BorderLayout.SOUTH);
		
		
		// Add Author Panel
		addAuthorPanelAddBook.add(new JLabel("First Name:", JLabel.LEFT));
		addAuthorPanelAddBook.add(firstNameTextAddBook);
		addAuthorPanelAddBook.add(new JLabel("Last Name:", JLabel.LEFT));
		addAuthorPanelAddBook.add(lastNameTextAddBook);
		addAuthorPanelAddBook.add(addAuthorBtnAddBook);
		
		JLabel aufooterLb1 = new JLabel("Add new Author",JLabel.CENTER);
		aufooterLb1.setFont(new Font("Courier New", Font.BOLD, 20));		
		auPanel.add(aufooterLb1, BorderLayout.NORTH);
		auPanel.add(addAuthorPanelAddBook, BorderLayout.CENTER);
		
		// Table Panel
		tableAuthorAddBook = new JTable(DBUtils.authorsToTableModel());
		scrollPaneAddBook = new JScrollPane(tableAuthorAddBook);
		tablePanelAddBook.add(scrollPaneAddBook);
		
		// Background Panel
		backgroundPanelAddBook.add(addTotal, BorderLayout.NORTH);
		backgroundPanelAddBook.add(tablePanelAddBook, BorderLayout.CENTER);
		backgroundPanelAddBook.add(auPanel, BorderLayout.SOUTH);
		
		// Create tabs
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Books", backgroundPanelBooks);		
		tabbedPane.addTab("Borrowers", backgroundPanelBorrowers);	
		tabbedPane.addTab("Books on loan", backgroundPanelLoans);
		tabbedPane.addTab("Add New Book or New Author", backgroundPanelAddBook);
		backgroundPanel.add(tabbedPane, BorderLayout.NORTH);
		
		// Add Background Panel to JFrame
		this.add(backgroundPanel);		
		
		// register the listeners
		searchBtnBooks.addActionListener(this);
		clearBtnBooks.addActionListener(this);
		checkoutBtnBooks.addActionListener(this);

		//searchBtnBorrowers.addActionListener(this);
		addBtnBorrowers.addActionListener(this);
		updateBtnBorrowers.addActionListener(this);
		clearBtnBorrowers.addActionListener(this);
		
		searchBtnLoans.addActionListener(this);
		checkinBtnLoans.addActionListener(this);
		
		addBtnAddBook.addActionListener(this);
		addAuthorBtnAddBook.addActionListener(this);
		clearBtnAddBook.addActionListener(this);
		
		//last line
		this.setVisible(true);	
	}	
	
	@Override
	public void actionPerformed(ActionEvent ev)
	{
		//************************ Book Tab Handlers ************************
		// Search Button
		if (ev.getSource() == searchBtnBooks)
		{
			// Grab text field values
			String titleString = titleTextBook.getText();
			String ISBNString = ISBNTextBook.getText();
			String editionString = editionTextBook.getText();
			String authorString = "";
			String subjectString = "";

			// if a subject is selected, grab the subject name
			if (subjectDropDownBook.getSelectedIndex() > -1)
				subjectString = subjectDropDownBook.getSelectedItem().toString();
			
			// if an author is selected, grab the authorID
			if (authorDropDownBook.getSelectedIndex() > -1)
			{
				authorString = authorDropDownBook.getSelectedItem().toString();
				authorString = authorString.substring(0, authorString.indexOf("."));
			}
				
			// grab only available books
			boolean available = availableCheckBoxBook.isSelected();
				
			// Create a table model based on inputs
			TableModel model = DBUtils.bookParametersToTableModel(titleString, authorString.toString(), ISBNString, editionString, subjectString, available);

			// Refresh the table panel
			tablePanelBooks.removeAll();			
			tableBooks = new JTable(model);
			scrollPaneBooks = new JScrollPane(tableBooks);
			tablePanelBooks.add(scrollPaneBooks);
			revalidate();
			repaint();
		}
		
		// Clear Button
		else if (ev.getSource() == clearBtnBooks)
		{
			titleTextBook.setText("");
			ISBNTextBook.setText("");
			subjectDropDownBook.setSelectedIndex(-1);
			authorDropDownBook.setSelectedIndex(-1);
			editionTextBook.setText("");
			availableCheckBoxBook.setSelected(false);
			tablePanelBooks.removeAll();
			repaint();
		}
		
		// Checkout Button
		else if (ev.getSource() == checkoutBtnBooks)
		{
			// If a book is selected and a borrower is selected
			if (tableBooks.getSelectedRowCount() > 0 && borrowerDropDownBook.getSelectedIndex() > -1)
			{
				// If a book is available
				if (tableBooks.getValueAt(tableBooks.getSelectedRow(), 6).toString().equals("Yes"))
				{
					// grab the borrower ID
					String borrowerString = borrowerDropDownBook.getSelectedItem().toString();
					int borrowerID = Integer.parseInt(borrowerString.substring(0, borrowerString.indexOf(".")));
					
					// grab the # of weeks (the int) - default is 1 week
					String loanPeriodString = loanPeriodDropDownBook.getSelectedItem().toString();
					int loanPeriod = Integer.parseInt(loanPeriodString.substring(0, loanPeriodString.indexOf(" ")));
					
					// grab the comment - we consider this to be optional
					String commentString = commentTextBook.getText();
					
					// grab the bookID
					int bookID = Integer.parseInt(tableBooks.getValueAt(tableBooks.getSelectedRow(), 0).toString());
					String bookTitle = tableBooks.getValueAt(tableBooks.getSelectedRow(), 1).toString();

					// Check out the book
					DBUtils.checkOutBook(borrowerID, loanPeriod, commentString, bookID);

					// Refresh the books tablePanel with null parameters
					tablePanelBooks.removeAll();
					String blank = "";
					tableBooks = new JTable(
							DBUtils.bookParametersToTableModel(blank, blank, blank, blank, blank, false));
					scrollPaneBooks = new JScrollPane(tableBooks);
					tablePanelBooks.add(scrollPaneBooks);
					
					// Refresh the loans tablePanel
					tablePanelLoans.removeAll();
					overdueCheckBoxLoans.setSelected(false);
					tableLoans = new JTable(DBUtils.loansToTableModel(false));
					scrollPaneLoans = new JScrollPane(tableLoans);
					tablePanelLoans.add(scrollPaneLoans);
					
					revalidate();
					repaint();
					java.time.LocalDate.now().plusWeeks(loanPeriod);
					JOptionPane.showMessageDialog(this, bookTitle + " successfully checked-out!\nDate due for check-in: " + java.time.LocalDate.now().plusWeeks(loanPeriod), "Book Checked-Out", JOptionPane.WARNING_MESSAGE);
				}
				else // book is not available
				{
					JOptionPane.showMessageDialog(this, "This book is not available. Please check the loans tab to see its scheduled return date.", "Book Not Available", JOptionPane.WARNING_MESSAGE);
				}

			}
			else // borrower or book were not selected
			{
				JOptionPane.showMessageDialog(this,	"Please select a book from the list and fill all fields.", "Missing Book Fields Warning", JOptionPane.WARNING_MESSAGE);
			}
		}
		
		//************************ Borrower Tab Handlers ************************
		// Search Button
		/*
		 * else if (ev.getSource() == searchBtnBorrowers) { // Refresh the tablePanel,
		 * adding model from borrowersToTableModel tablePanelBorrowers.removeAll();
		 * tableBorrowers = new JTable(DBUtils.borrowersToTableModel());
		 * scrollPaneBorrowers = new JScrollPane(tableBorrowers);
		 * tablePanelBorrowers.add(scrollPaneBorrowers); revalidate(); repaint(); }
		 */
		
		// Add Button
		else if (ev.getSource() == addBtnBorrowers)
		{
			// grab text field values
			String firstNameString = firstNameTextBorrowers.getText();
			String lastNameString = lastNameTextBorrowers.getText();
			String emailString = emailTextBorrowers.getText();
			
			// if there are values in all of the fields
			if (!(firstNameString.isEmpty() || lastNameString.isEmpty() || emailString.isEmpty()))
			{
				// Add a new borrower
				DBUtils.addNewBorrower(firstNameString, lastNameString, emailString);

				// Refresh the borrowers tablePanel
				tablePanelBorrowers.removeAll();
				tableBorrowers = new JTable(DBUtils.borrowersToTableModel());
				scrollPaneBorrowers = new JScrollPane(tableBorrowers);
				tablePanelBorrowers.add(scrollPaneBorrowers);
				
				// Refresh the borrower ComboBox in the books tab
				addPanelBooks.removeAll();
				addPanelBooks.add(new JLabel("Borrower:"));
				borrowerDropDownBook = new JComboBox<String>(DBUtils.getList("borrower"));
				borrowerDropDownBook.setSelectedIndex(-1);
				addPanelBooks.add(borrowerDropDownBook);
				addPanelBooks.add(new JLabel("Loan Period:"));
				addPanelBooks.add(loanPeriodDropDownBook);
				addPanelBooks.add(new JLabel("Comment:"));
				addPanelBooks.add(commentTextBook);
				addPanelBooks.add(checkoutBtnBooks);	
				
				firstNameTextBorrowers.setText("");
				lastNameTextBorrowers.setText("");
				emailTextBorrowers.setText("");
				revalidate();
				repaint();
				
				JOptionPane.showMessageDialog(this, "Borrower " + firstNameString + " " + lastNameString + " successfully added!", "Added Borrower", JOptionPane.INFORMATION_MESSAGE);
			}
			else // fields weren't filled out
			{
				JOptionPane.showMessageDialog(this, "Please fill out all fields!", "Missing Borrower Fields Warning", JOptionPane.WARNING_MESSAGE);
			}			
		}
		
		// Update Button
		else if (ev.getSource() == updateBtnBorrowers)
		{
			// If a borrower was selected
			if (tableBorrowers.getSelectedRowCount() > 0)
			{
				// grab name and email strings, and borrowerID
				String lastNameString = tableBorrowers.getValueAt(tableBorrowers.getSelectedRow(), 1).toString();
				String firstNameString = tableBorrowers.getValueAt(tableBorrowers.getSelectedRow(), 2).toString();
				String emailString = tableBorrowers.getValueAt(tableBorrowers.getSelectedRow(), 3).toString();
				int borrowerID = Integer.parseInt(tableBorrowers.getValueAt(tableBorrowers.getSelectedRow(), 0).toString());
					
				// Update Borrower
				DBUtils.updateBorrower(firstNameString, lastNameString, emailString, borrowerID);
	
				// Refresh the borrowers tablePanel
				tablePanelBorrowers.removeAll();
				tableBorrowers = new JTable(DBUtils.borrowersToTableModel());
				scrollPaneBorrowers = new JScrollPane(tableBorrowers);
				tablePanelBorrowers.add(scrollPaneBorrowers);
				
				// Refresh the borrower ComboBox in the books tab
				addPanelBooks.removeAll();
				addPanelBooks.add(new JLabel("Borrower:"));
				borrowerDropDownBook = new JComboBox<String>(DBUtils.getList("borrower"));
				borrowerDropDownBook.setSelectedIndex(-1);
				addPanelBooks.add(borrowerDropDownBook);
				addPanelBooks.add(new JLabel("Loan Period:"));
				addPanelBooks.add(loanPeriodDropDownBook);
				addPanelBooks.add(new JLabel("Comment:"));
				addPanelBooks.add(commentTextBook);
				addPanelBooks.add(checkoutBtnBooks);	
				
				revalidate();
				repaint();
				
				JOptionPane.showMessageDialog(this, "Borrower " + firstNameString + " " + lastNameString + " successfully updated!", "Borrower Updated", JOptionPane.INFORMATION_MESSAGE);
			}
			else // borrower wasn't selected
			{
				JOptionPane.showMessageDialog(this, "Please select a borrower!", "Select a Borrower Warning", JOptionPane.WARNING_MESSAGE);
			}
		}
		
		
		//************************ Loan Tab Handlers ************************
		// Search Button
		else if (ev.getSource() == searchBtnLoans)
		{
			// are we looking for overdue books?
			boolean isOverdue = overdueCheckBoxLoans.isSelected();
			
			// Refresh tablePanel based on overdue parameter
			tablePanelLoans.removeAll();
			tableLoans = new JTable(DBUtils.loansToTableModel(isOverdue));
			scrollPaneLoans = new JScrollPane(tableLoans);
			tablePanelLoans.add(scrollPaneLoans);
			
			revalidate();
			repaint();
			
		}
		
		// Check-in Button
		else if (ev.getSource() == checkinBtnLoans)
		{
			// If a book is selected
			if (tableLoans.getSelectedRowCount() > 0)
			{
				// Grab the book ID
				int bookID = Integer.parseInt(tableLoans.getValueAt(tableLoans.getSelectedRow(), 0).toString());
				String bookTitle = tableLoans.getValueAt(tableLoans.getSelectedRow(), 1).toString();
				
				// Check-in the book
				DBUtils.checkInBook(bookID);
				
				// Refresh the books tablePanel with default parameters
				tablePanelBooks.removeAll();
				String blank = "";
				tableBooks = new JTable(
						DBUtils.bookParametersToTableModel(blank, blank, blank, blank, blank, false));
				scrollPaneBooks = new JScrollPane(tableBooks);
				tablePanelBooks.add(scrollPaneBooks);
				
				// Refresh the loans tablePanel with default parameters
				tablePanelLoans.removeAll();
				overdueCheckBoxLoans.setSelected(false);
				tableLoans = new JTable(DBUtils.loansToTableModel(false));
				scrollPaneLoans = new JScrollPane(tableLoans);
				tablePanelLoans.add(scrollPaneLoans);
				
				revalidate();
				repaint();
				
				
				JOptionPane.showMessageDialog(this, bookTitle + " successfully checked-in!", "Book Checked-In", JOptionPane.INFORMATION_MESSAGE);
			}
			else // book wasn't selected
			{
				JOptionPane.showMessageDialog(this, "Please select a book to check-in!", "Select a Book Warning", JOptionPane.WARNING_MESSAGE);
			}
		}
		
		//************************ Add Book or Author Tab Handlers ************************
		
		// Add Book Button
		else if (ev.getSource() == addBtnAddBook)
		{
			// Grab input strings
			String titleString = titleTextAddBook.getText();
			String ISBNString = ISBNTextAddBook.getText();
			String editionString = editionTextAddBook.getText();
			String subjectString = subjectTextAddBook.getText();
			
			// If at least one author is selected
			if (tableAuthorAddBook.getSelectedRowCount() > 0) 
			{
				// Grab selected author table indices
				int[] selectedRowIndices = tableAuthorAddBook.getSelectedRows();
				
				// if strings aren't null
				if (!(titleString.isEmpty() || ISBNString.isEmpty() || editionString.isEmpty() || subjectString.isEmpty()))
				{
					// Check if ISBN has 13 digits
					String patternISBN = "\\d{13}";
					if (ISBNString.matches(patternISBN))
					{
						// Check if edition number has 1-3 digits
						String patternEdition = "\\d{1,3}";
						if (editionString.matches(patternEdition))
						{
							
							// Create an authorID array using table values from the selected indices
							int[] authorIDArray = new int[selectedRowIndices.length];
							for (int i = 0; i < authorIDArray.length; ++i)
							{
								authorIDArray[i] = Integer.parseInt(tableAuthorAddBook.getValueAt(selectedRowIndices[i], 0).toString()); 
							}
							
							// Add the new book (adds to book and book_author table)
							DBUtils.addNewBook(titleString, ISBNString, editionString, subjectString, authorIDArray);
							
							// Refresh subject combo box in book tab
							searchPanelBooks.removeAll();
							searchPanelBooks.add(new JLabel("Title:", JLabel.LEFT));
							searchPanelBooks.add(titleTextBook);
							searchPanelBooks.add(new JLabel("Author:", JLabel.LEFT));
							authorDropDownBook = new JComboBox<String>(DBUtils.getList("author"));
							authorDropDownBook.setSelectedIndex(-1);
							searchPanelBooks.add(authorDropDownBook);
							searchPanelBooks.add(new JLabel("ISBN:", JLabel.LEFT));
							searchPanelBooks.add(ISBNTextBook);
							searchPanelBooks.add(new JLabel("Subject:", JLabel.LEFT));
							subjectDropDownBook = new JComboBox<String>(DBUtils.getList("subject"));
							subjectDropDownBook.setSelectedIndex(-1);
							searchPanelBooks.add(subjectDropDownBook);
							searchPanelBooks.add(new JLabel("Edition:", JLabel.LEFT));
							searchPanelBooks.add(editionTextBook);		
							searchPanelBooks.add(new JLabel("Available:", JLabel.LEFT));
							searchPanelBooks.add(availableCheckBoxBook);							
							
							// Refresh book tablePanel with default search parameters
							tablePanelBooks.removeAll();
							String blank = "";
							tableBooks = new JTable(
									DBUtils.bookParametersToTableModel(blank, blank, blank, blank, blank, false));;
							scrollPaneBooks = new JScrollPane(tableBooks);
							tablePanelBooks.add(scrollPaneBooks);
							
							revalidate();
							repaint();
							
							JOptionPane.showMessageDialog(this, titleString + " book successfully added!", "Book Added", JOptionPane.INFORMATION_MESSAGE);
						}
						else // improper edition number, focus on edition text field
						{
							JOptionPane.showMessageDialog(this, "Edition numbers have a max of 3 digits.", "Edition Number Warning", JOptionPane.WARNING_MESSAGE);
							editionTextAddBook.requestFocus();
						}						
					}
					else // improper ISBN number, focus on ISBN text field
					{
						JOptionPane.showMessageDialog(this, "Please enter a 13 digit ISBN number.", "ISBN Warning", JOptionPane.WARNING_MESSAGE);
						ISBNTextAddBook.requestFocus();
					}
				}
				else // fields missing
				{
					JOptionPane.showMessageDialog(this, "Please fill out all fields!", "Missing Book Fields Warning", JOptionPane.WARNING_MESSAGE);
				}
			}
			else // no authors were selected
			{
				JOptionPane.showMessageDialog(this, "Please select one or more authors in the table!", "Select an Author Warning", JOptionPane.WARNING_MESSAGE);
			}			
		}
		
		// Add Author Button
		else if (ev.getSource() == addAuthorBtnAddBook)
		{
			// grab name strings
			String firstNameString = firstNameTextAddBook.getText();
			String lastNameString = lastNameTextAddBook.getText();
			
			// if strings aren't null
			if (!(firstNameString.isEmpty() || lastNameString.isEmpty()))
			{
				// add new author
				DBUtils.addNewAuthor(firstNameString, lastNameString);
				
				// refresh author combo box in book tab
				searchPanelBooks.removeAll();
				searchPanelBooks.add(new JLabel("Title:", JLabel.LEFT));
				searchPanelBooks.add(titleTextBook);
				searchPanelBooks.add(new JLabel("Author:", JLabel.LEFT));
				authorDropDownBook = new JComboBox<String>(DBUtils.getList("author"));
				authorDropDownBook.setSelectedIndex(-1);
				searchPanelBooks.add(authorDropDownBook);
				searchPanelBooks.add(new JLabel("ISBN:", JLabel.LEFT));
				searchPanelBooks.add(ISBNTextBook);
				searchPanelBooks.add(new JLabel("Subject:", JLabel.LEFT));
				subjectDropDownBook = new JComboBox<String>(DBUtils.getList("subject"));
				subjectDropDownBook.setSelectedIndex(-1);
				searchPanelBooks.add(subjectDropDownBook);
				searchPanelBooks.add(new JLabel("Edition:", JLabel.LEFT));
				searchPanelBooks.add(editionTextBook);		
				searchPanelBooks.add(new JLabel("Available:", JLabel.LEFT));
				searchPanelBooks.add(availableCheckBoxBook);				
				
				// refresh author tablePanel in add book/author tab
				tablePanelAddBook.removeAll();
				tableAuthorAddBook = new JTable(DBUtils.authorsToTableModel());
				scrollPaneAddBook = new JScrollPane(tableAuthorAddBook);
				tablePanelAddBook.add(scrollPaneAddBook);
				
				firstNameTextAddBook.setText("");
				lastNameTextAddBook.setText("");
				revalidate();
				repaint();
				
				JOptionPane.showMessageDialog(this, "Author " + firstNameString + " " + lastNameString + " successfully added!", "Author Added", JOptionPane.INFORMATION_MESSAGE);
				
			}
			else // fields missing
			{
				JOptionPane.showMessageDialog(this, "Please fill out all fields!", "Missing Author Fields Warning", JOptionPane.WARNING_MESSAGE);
			}
		}
		
		// Clear Button		
		else if (ev.getSource() == clearBtnAddBook)
		{
			titleTextAddBook.setText("");
			ISBNTextAddBook.setText("");
			editionTextAddBook.setText("");
			subjectTextAddBook.setText("");
		}
		
	}
	
	//************************ The Main ************************
	public static void main(String [] args)
	{
		new BookLoanController();
	}
}
