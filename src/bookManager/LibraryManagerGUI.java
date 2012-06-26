package bookManager;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class LibraryManagerGUI extends JFrame implements ActionListener {

	JPanel panel1;
	JPanel panel2;
	JLabel label1;
	JButton viewAllBooks;
	JButton searchBook;
	JButton addABook;
	JButton deleteABook;
	JButton reserveABook;
	JButton returnABook;
	JButton exitTheSystem;
	JTextArea textArea;
	Library libraryGUI;

	LibraryManagerGUI() {
		super("TOKYO INSTITUTE OF TECHNOLOGY");
		setSize(1200, 600);

		panel1 = new JPanel();
		panel2 = new JPanel();
		label1 = new JLabel(
				"Database Management System of Maekawa Laboratory ",
				JLabel.CENTER);
		// label1.setSize(1200, 120);
		label1.setFont(new Font("Serif", Font.BOLD, 40));

		viewAllBooks = new JButton("View All Books / Refresh");
		searchBook = new JButton("Search by Name/Code");
		addABook = new JButton("Add A Book");
		deleteABook = new JButton("Delete A Book");
		reserveABook = new JButton("Reserve A Book");
		returnABook = new JButton("Return A Book");
		exitTheSystem = new JButton("Exit The System");

		libraryGUI = new Library();
		libraryGUI.initialize();   // add the books to the library

		viewAllBooks.addActionListener(this);
		searchBook.addActionListener(this);
		addABook.addActionListener(this);
		deleteABook.addActionListener(this);
		reserveABook.addActionListener(this);
		returnABook.addActionListener(this);
		exitTheSystem.addActionListener(this);

		textArea = new JTextArea();
		textArea.append("Welcome to Maekawa Library");
		panel2.setLayout(new GridLayout(7, 1));
		panel2.add(viewAllBooks);
		panel2.add(searchBook);
		panel2.add(addABook);
		panel2.add(deleteABook);
		panel2.add(reserveABook);
		panel2.add(returnABook);
		panel2.add(exitTheSystem);

		panel1.setLayout(new BorderLayout());
		panel1.add(panel2, BorderLayout.WEST);
		panel1.add(label1, BorderLayout.NORTH);
		panel1.add(textArea, BorderLayout.CENTER);

		setContentPane(panel1);
		setVisible(true);

	}

	void printAllBooksGUI() {
		textArea.setText("");
		String output = "";
		output += "Index" + "\t" + "Name" + "\t" + "\t" + "\t" + "Code" + "\t"
				+ "Availability" + "\t" + "Reserved Until" + "\t"
				+ "Reserved By" + "\n";
		for (Book i : Library.library) {
			// System.out.println(i.name);
			output += "" + i.getIndex() + "\t" + i.getName() + "\t" + "\t"
					+ i.getCodeNumber() + "\t" + i.isAvailable() + "\t"
					+ i.getFullReturnDate() + "\t" + i.getReservedBy() + "\n";

		}
		// return output;
		textArea.append(output);
	}
	
	void searchAndPrint() {
		final JFrame newFrame = new JFrame("Search");
		newFrame.setSize(300, 150);

		JPanel p = new JPanel(new GridLayout(3, 1));
		JLabel labelName = new JLabel("Please enter the Name or Code Number");
		p.add(labelName);
		final JTextField textFieldName = new JTextField(20);
		labelName.setLabelFor(textFieldName);
		p.add(textFieldName);

		JButton buttonAddBookOK = new JButton("OK");
		buttonAddBookOK.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				boolean found = false;
				if (textFieldName.getText().equals("")) {
					fillInRequiredFields();
				} else {
					ArrayList<Book> searchResultArray = new ArrayList<Book>();
					// TODO Auto-generated method stub
					for (Book i : Library.library) {
						if (i.getName().contains(textFieldName.getText().toString()) ||
								i.getCodeNumber().contains(textFieldName.getText().toString())) {
							searchResultArray.add(i);
							found = true;
							//break;
						}
					}
					
					if (found == true){
						textArea.setText("");
						String output = "";
						output += "Index" + "\t" + "Name" + "\t" + "\t" + "\t" + "Code" + "\t"
								+ "Availability" + "\t" + "Reserved Until" + "\t"
								+ "Reserved By" + "\n";
						for (Book i : searchResultArray) {
							// System.out.println(i.name);
							output += "" + i.getIndex() + "\t" + i.getName() + "\t" + "\t"
									+ i.getCodeNumber() + "\t" + i.isAvailable() + "\t"
									+ i.getFullReturnDate() + "\t" + i.getReservedBy() + "\n";

						}
						textArea.append(output);
					}
					if (found == false) {
						couldNotFindTheBook();
					}
				}
				newFrame.dispose();
			}

		});
		p.add(buttonAddBookOK);
		newFrame.setContentPane(p);
		newFrame.setVisible(true);

	}

	void addBook() {
		final JFrame newFrame = new JFrame("Add A book");
		newFrame.setSize(300, 250);

		JPanel p = new JPanel(new GridLayout(5, 1));
		JLabel labelName = new JLabel("Please Enter Full Title of Book");
		p.add(labelName);
		final JTextField textFieldName = new JTextField(25);
		labelName.setLabelFor(textFieldName);
		p.add(textFieldName);

		JLabel labelCode = new JLabel("Please enter Code Number of the Book");
		p.add(labelCode);
		final JTextField textFieldCode = new JTextField(10);
		labelCode.setLabelFor(textFieldCode);
		p.add(textFieldCode);
		JButton buttonAddBookOK = new JButton("OK");
		p.add(buttonAddBookOK);

		buttonAddBookOK.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				if (textFieldName.getText().equals("")
						|| textFieldCode.getText().equals("")) {
					fillInRequiredFields();

				}
				// TODO Auto-generated method stub
				else {

					Book a = new Book(textFieldName.getText(), textFieldCode

					.getText());
					Library.library.add(a);

				}
				newFrame.dispose();
			}

		});

		newFrame.setContentPane(p);
		newFrame.setVisible(true);

	}

	void deleteBook() {
		final JFrame newFrame = new JFrame("Delete A book");
		newFrame.setSize(300, 150);

		JPanel p = new JPanel(new GridLayout(3, 1));
		JLabel labelName = new JLabel("Please write the index Number");
		p.add(labelName);
		final JTextField textFieldName = new JTextField(20);
		labelName.setLabelFor(textFieldName);
		p.add(textFieldName);

		JButton buttonAddBookOK = new JButton("OK");
		buttonAddBookOK.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				boolean found = false;
				if (textFieldName.getText().equals("")) {
					fillInRequiredFields();
				} else {

					// TODO Auto-generated method stub
					for (Book i : Library.library) {
						if (i.getIndex() == Integer.parseInt(textFieldName
								.getText())) {
							Library.library.remove(i);
							found = true;
							break;
						}
					}
					if (found == false) {
						couldNotFindTheBook();
					}
				}
				newFrame.dispose();
			}

		});
		p.add(buttonAddBookOK);
		newFrame.setContentPane(p);
		newFrame.setVisible(true);

	}

	void reserveBook() {
		final JFrame newFrame = new JFrame("Reserve A book");
		newFrame.setSize(300, 250);

		JPanel p = new JPanel(new GridLayout(5, 1));
		JLabel labelName = new JLabel("Please write the index Number");
		p.add(labelName);
		final JTextField textFieldName = new JTextField(20);
		labelName.setLabelFor(textFieldName);
		p.add(textFieldName);

		JLabel labelName2 = new JLabel("Please write your full name");
		p.add(labelName2);
		final JTextField textFieldName2 = new JTextField(20);
		labelName.setLabelFor(textFieldName2);
		p.add(textFieldName2);

		JButton buttonAddBookOK = new JButton("OK");
		buttonAddBookOK.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				boolean found = false;
				if (textFieldName.getText().equals("")
						|| textFieldName2.getText().equals("")) {
					fillInRequiredFields();
				} else {

					// TODO Auto-generated method stub
					for (Book i : Library.library) {

						if (i.getIndex() == Integer.parseInt(textFieldName
								.getText())) {
							if (i.isAvailable() == true) {

								i.reserveGUI(textFieldName2.getText());
								found = true;
								break;
							} else if (i.isAvailable() == false) {
								bookAlreadyReserved();
								found = true;
								break;
							}
						}

					}

					if (found == false) {
						couldNotFindTheBook();
					}
				}
				newFrame.dispose();
			}

		});
		p.add(buttonAddBookOK);
		newFrame.setContentPane(p);
		newFrame.setVisible(true);

	}

	void returnBook() {
		final JFrame newFrame = new JFrame("Return A book");
		newFrame.setSize(300, 150);

		JPanel p = new JPanel(new GridLayout(3, 1));
		JLabel labelName = new JLabel("Please write the index Number");
		p.add(labelName);
		final JTextField textFieldName = new JTextField(20);
		labelName.setLabelFor(textFieldName);
		p.add(textFieldName);

		JButton buttonAddBookOK = new JButton("OK");
		buttonAddBookOK.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				boolean found = false;
				if (textFieldName.getText().equals("")) {
					fillInRequiredFields();
				} else {
					// TODO Auto-generated method stub
					for (Book i : Library.library) {
						if (i.getIndex() == Integer.parseInt(textFieldName
								.getText())) {
							i.returnThisBook();
							found = true;
							break;
						}
					}
					if (found == false) {
						couldNotFindTheBook();
					}
				}
				newFrame.dispose();
			}

		});
		p.add(buttonAddBookOK);
		newFrame.setContentPane(p);
		newFrame.setVisible(true);

	}

	void fillInRequiredFields() {
		final JFrame newFrame = new JFrame("Fill in Required Fields");
		newFrame.setSize(300, 100);

		JPanel p = new JPanel(new GridLayout(2, 1));
		JLabel labelName = new JLabel("Sorry, Some fields are missing",
				JLabel.CENTER);
		p.add(labelName);

		JButton buttonAddBookOK = new JButton("OK");
		buttonAddBookOK.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				newFrame.dispose();
			}

		});
		p.add(buttonAddBookOK);
		newFrame.setContentPane(p);
		newFrame.setVisible(true);

	}

	void bookAlreadyReserved() {
		final JFrame newFrame = new JFrame("Book Already Reserved");
		newFrame.setSize(300, 100);

		JPanel p = new JPanel(new GridLayout(2, 1));
		JLabel labelName = new JLabel("Sorry, the book is already reserved",
				JLabel.CENTER);
		p.add(labelName);

		JButton buttonAddBookOK = new JButton("OK");
		buttonAddBookOK.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				newFrame.dispose();
			}

		});
		p.add(buttonAddBookOK);
		newFrame.setContentPane(p);
		newFrame.setVisible(true);

	}

	void couldNotFindTheBook() {
		final JFrame newFrame = new JFrame("The Book Could Not Be Found");
		newFrame.setSize(300, 100);

		JPanel p = new JPanel(new GridLayout(2, 1));
		JLabel labelName = new JLabel("Sorry, The book could not be found",
				JLabel.CENTER);
		p.add(labelName);

		JButton buttonAddBookOK = new JButton("OK");
		buttonAddBookOK.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				newFrame.dispose();
			}

		});
		p.add(buttonAddBookOK);
		newFrame.setContentPane(p);
		newFrame.setVisible(true);

	}

	public void actionPerformed(ActionEvent e) {

		// TODO Auto-generated method stub
		if (e.getSource() == viewAllBooks) {
			printAllBooksGUI();
		}
		if (e.getSource() == addABook) {
			addBook();
		}
		if (e.getSource() == searchBook) {
			searchAndPrint();
		}
		if (e.getSource() == deleteABook) {
			deleteBook();
		}
		if (e.getSource() == reserveABook) {
			reserveBook();
		}
		if (e.getSource() == returnABook) {
			returnBook();
		}
		if (e.getSource() == exitTheSystem) {
			System.exit(0);
		}

	}

	public static void main(String args[]) {

		LibraryManagerGUI libraryManagerGUI = new LibraryManagerGUI();

	}

}
