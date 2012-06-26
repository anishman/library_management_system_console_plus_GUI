package bookManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Library {

	static ArrayList<Book> library = new ArrayList<Book>();
	static int inputInteger;

	public static void main(String args[]) throws IOException {
		
		initialize();
		printAllBooks(library);
		startLibraryEngine();
	}

	private static void displayMenu() {
		System.out.println("Welcome to the library" + "\n"
				+ "What would you like to do?" + "\n" + "1: View all books"
				+ "\n" + "2: Search a book using Name/Code" + "\n"
				+ "3: Add a book" + "\n" + "4: Delete a book" + "\n"
				+ "5: Reserve a book" + "\n" + "6: Return a book" + "\n"
				+ "7: Exit the system");

	}

	private static void startLibraryEngine() throws IOException {
		// TODO Auto-generated method stub

		do {
			displayMenu();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					System.in));
			try {
				String input = reader.readLine();
				inputInteger = Integer.parseInt(input);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			switch (inputInteger) {
			case 1:
				printAllBooks(library);
				if (wouldLikeToContinue() == true)
					continue;
				else
					seeYouAgain();
			case 2:
				searchAndPrint();
				if (wouldLikeToContinue() == true)
					continue;
				else
					seeYouAgain();
			case 3:
				addBook();
				if (wouldLikeToContinue() == true)
					continue;
				else
					seeYouAgain();
			case 4:
				deleteBook();
				if (wouldLikeToContinue() == true)
					continue;
				else
					seeYouAgain();
			case 5:
				reserveBook();
				if (wouldLikeToContinue() == true)
					continue;
				else
					seeYouAgain();
			case 6:
				returnBook();
				if (wouldLikeToContinue() == true)
					continue;
				else
					seeYouAgain();
			case 7:
				seeYouAgain();
			}

		} while (true);

	}

	private static void seeYouAgain() {
		// TODO Auto-generated method stub
		System.out.println("Doumo Arigatou!!! See you again!!!");
		System.exit(0);
	}

	private static boolean wouldLikeToContinue() throws IOException {
		// TODO Auto-generated method stub
		System.out
				.println("thank you for the transaction! Would you like to Continue?"
						+ "\n"
						+ "Press 1 to continue, any other number to exit");
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		String input = reader.readLine();
		int inputVal = Integer.parseInt(input);
		switch (inputVal) {
		case 1:
			return true;

		}

		return false;
	}

	static void initialize() {
		Book physics = new Book("conceptual physics", "P01");
		library.add(physics);
		Book chemistry = new Book("ramailo chemistry", "C01");
		library.add(chemistry);
		Book biology = new Book("learning biology", "B01");
		library.add(biology);
		Book physics2 = new Book("university physics", "P02");
		library.add(physics2);
		Book chemistry2 = new Book("vidhyalaya chemistry", "C02");
		library.add(chemistry2);
		Book biology2 = new Book("daigaku biology", "B02");
		library.add(biology2);

	}

	static void addBook() throws IOException {

		System.out.println("Please the Name of the book");
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		String name = reader.readLine();
		System.out.println("Please the Code Number");
		BufferedReader reader1 = new BufferedReader(new InputStreamReader(
				System.in));
		String codeNumber = reader1.readLine();

		Book a = new Book(name, codeNumber);
		library.add(a);
	}

	static void reserveBook() throws IOException {
		System.out.println("Please the Index Number");
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		String indexDisplayed = reader.readLine();
		int index = Integer.parseInt(indexDisplayed);

		for (Book i : library) {
			if (i.getIndex() == index) {
				i.reserve();
				break;
			}
		}
	}

	static void returnBook() throws IOException {
		System.out.println("Please the Index Number");
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		String indexDisplayed = reader.readLine();
		int index = Integer.parseInt(indexDisplayed);

		for (Book i : library) {
			if (i.getIndex() == index) {
				i.returnThisBook();
				break;
			}
		}
	}

	static void searchAndPrint() throws IOException {
		System.out.println("Please enter the Name or Code Number");
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		String query = reader.readLine();
		boolean found = false;
		ArrayList<Book> searchResultArray = new ArrayList<Book>();
		for (Book i : library) {
			// System.out.println(i.name);
			if (i.getName().contains(query)
					|| i.getCodeNumber().contains(query)) {
				searchResultArray.add(i);
				found = true;
			}

		}
		if (found = true) {
			printAllBooks(searchResultArray);
		}
		if (found == false) {
			System.out.println("Sorry, no matches found!");
		}
	}

	static void deleteBook() throws IOException {
		System.out.println("Please the Index Number");
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		String indexDisplayed = reader.readLine();
		int index = Integer.parseInt(indexDisplayed);

		for (Book i : library) {

			if (i.getIndex() == index) {

				library.remove(i);
				break;
			}

		}
	}

	static void printAllBooks(ArrayList<Book> lib) {

		System.out.printf("Index" + "\t" + "Name" + "\t" + "\t" + "\t" + "Code"
				+ "\t" + "Availability" + "\t" + "Reserved Until" + "\t"
				+ "Reserved By" + "\n");
		for (Book i : lib) {
			System.out.printf("%s \t %s \t %s \t %s \t \t %s \t %s",
					i.getIndex(), i.getName(), i.getCodeNumber(),
					i.isAvailable(), i.getFullReturnDate(), i.getReservedBy());
			System.out.println();
		}

	}

}
