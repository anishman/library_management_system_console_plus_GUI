package bookManager;

import java.util.Calendar;

public class DateManager {

	int[] numberOfDaysInMonth = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30,
			31 };
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	Calendar cal = Calendar.getInstance();


	String reserveFor2Weeks() {

		int currentDate = cal.get(Calendar.DATE);
		int currentMonth = cal.get(Calendar.MONTH) + 1;
		int currentYear = cal.get(Calendar.YEAR);
		int returnDate = currentDate + 14;  //reservation of two weeks, i.e., 14 days
		int returnMonth = currentMonth, returnYear = currentYear;
		if (returnDate > numberOfDaysInMonth[currentMonth - 1]) {
			if (currentMonth == 12) {
				returnYear += 1;
				returnMonth = 1;
				returnDate = returnDate - numberOfDaysInMonth[currentMonth - 1];
			} else {
				returnMonth += 1;
				returnDate = returnDate - numberOfDaysInMonth[currentMonth - 1];
			}

		}
		String fullReturnDate = returnYear + "-" + returnMonth + "-"
				+ returnDate;
		return fullReturnDate;
	}
}
