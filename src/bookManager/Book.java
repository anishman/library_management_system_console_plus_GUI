package bookManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/** The class of Book
*/
public class Book {

	static int PresentIndex = 1; //counter for the total of books created until now

	private boolean available;
	private String name;
	private String codeNumber;
	private int index;
	private String fullReturnDate = "";
	private String reservedBy = "";

	Book(String name, String codeNumber) {
		this.name = name;
		this.codeNumber = codeNumber;
		this.index = PresentIndex;
		PresentIndex += 1;
		this.available = true;
	}

	public void setReservedBy(String reserver) {
		this.reservedBy = reserver;
	}

	public boolean isAvailable() {
		if (this.available == true)
			return true;
		else
			return false;
	}

	int getIndex() {
		return index;
	}

	public String getName() {
		return name;
	}

	public String getCodeNumber() {
		return codeNumber;
	}

	public String getFullReturnDate() {
		return fullReturnDate;
	}

	public String getReservedBy() {
		return reservedBy;
	}

	void reserve() {
		System.out.println("Please enter your Name");
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		try {
			String nameOfReserver = reader.readLine();
			this.reservedBy = nameOfReserver;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.available = false;
		DateManager d = new DateManager();
		this.fullReturnDate = d.reserveFor2Weeks();

	}

	void reserveGUI(String nameOfReserver) {

		this.reservedBy = nameOfReserver;
		this.available = false;
		DateManager d = new DateManager();
		this.fullReturnDate = d.reserveFor2Weeks();

	}

	void returnThisBook() {
		this.available = true;
		this.fullReturnDate = " ";
		this.reservedBy = "";
	}

	void printInfo() {
		System.out.printf("%s  %s  %s  %s %s", this.index, this.name,
				this.codeNumber, this.available, this.fullReturnDate);
	}

	

}
