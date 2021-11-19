package application;

import java.util.ArrayList;

/**
 * Filename: Main.java Project: Milk's Friend Authors: ateam88
 * Defines a farm and its constituent milk supplies
 * 
 * @author ateam88
 *
 */
public class Farm {

	private String farmID;
	private ArrayList<YearNode> yearCollection;

	/**
	 * The inner class of Farm class, an instance of this class stores the data of a
	 * particular farm in a given year
	 * 
	 * @author ateam88
	 *
	 */
	private class YearNode {
		private int year;
		private int[][] timeMap;
		private int totalWeightForYear;

		/**
		 * Create a new instance of YearNode
		 * 
		 * @param yr
		 */
		private YearNode(int yr) {
			year = yr;
			timeMap = new int[12][31];
		}
	}

	/**
	 * Create a new instance of Farm with given ID
	 * 
	 * @param id -the ID of the new farm to be created
	 */
	public Farm(String id) {
		this.farmID = id;
		yearCollection = new ArrayList<YearNode>();
	}

	/**
	 * return the ID of this farm
	 * 
	 * @return -the ID of this farm
	 */
	public String getID() {
		return farmID;
	}

	/**
	 * Insert milk weight data at a particular date
	 * 
	 * @param input  - the String containing the information of the date to be added
	 * @param weight - the weight of the milk to be added
	 * @return true if the data is successfully added
	 */
	public boolean insertMilkForDate(String input, int weight) {
		// retrieve the date from the input parameter
		String[] splitedDate = input.split("\\-");
		int iYear = Integer.parseInt(splitedDate[0]);
		int iMonth = Integer.parseInt(splitedDate[1]);
		int iDate = Integer.parseInt(splitedDate[2]);

		for (YearNode y : yearCollection) {
			if (y.year == iYear) {
				y.timeMap[iMonth - 1][iDate - 1] += weight;
				y.totalWeightForYear += weight;
			}
		}
		// not found year, create a new year node;
		YearNode yn = new YearNode(iYear);
		yn.timeMap[iMonth - 1][iDate - 1] += weight;
		yn.totalWeightForYear += weight;
		yearCollection.add(yn);
		return true;
	}

	/**
	 * Edit the milk weight data at a particular date
	 * 
	 * @param input  - the String containing the information of the date to be added
	 * @param weight - the weight of the milk to be added
	 * @return true if the data is successfully added
	 */
	public boolean editMilkForDate(String input, int weight) {
		// retrieve the date from the input parameter
		String[] splitedDate = input.split("\\-");
		int iYear = Integer.parseInt(splitedDate[0]);
		int iMonth = Integer.parseInt(splitedDate[1]);
		int iDate = Integer.parseInt(splitedDate[2]);
		int oldWeight;

		for (YearNode y : yearCollection) {
			if (y.year == iYear) {
				oldWeight = y.timeMap[iMonth - 1][iDate - 1];
				y.timeMap[iMonth - 1][iDate - 1] = weight;
				y.totalWeightForYear -= oldWeight;
				y.totalWeightForYear += weight;
			}
		}
		// not found year, create a new year node;
		YearNode yn = new YearNode(iYear);
		yn.timeMap[iMonth - 1][iDate - 1] = weight;
		yn.totalWeightForYear = weight;
		yearCollection.add(yn);
		return true;

	}

	/**
	 * Remove the milk weight data at a particular date
	 * 
	 * @param input - the String containing the information of the date to be added
	 * @return the weight of the milk removed
	 */
	public int removeMilkForDate(String input) {
		// retrieve the date from the input parameter
		String[] splitedDate = input.split("\\-");
		int iYear = Integer.parseInt(splitedDate[0]);
		int iMonth = Integer.parseInt(splitedDate[1]);
		int iDate = Integer.parseInt(splitedDate[2]);
		int removedMilk;

		for (YearNode y : yearCollection) {
			if (y.year == iYear) {
				removedMilk = y.timeMap[iMonth - 1][iDate - 1];
				y.timeMap[iMonth - 1][iDate - 1] = 0;
				y.totalWeightForYear -= removedMilk;
				return removedMilk;
			}
		}
		// not found year, do nothing;
		return 0;
	}

	/**
	 * Clear out the milk data for this farm
	 * 
	 * @return the total weight of milk of this farm removed
	 */
	public int clearData() {
		int total = 0;
		for (int i = 0; i < this.yearCollection.size(); i++) {
			total += yearCollection.get(i).totalWeightForYear;
		}
		yearCollection.clear();
		return total;
	}

	/**
	 * Get the yearNode for the given year
	 * 
	 * @param year -the year to be searched for
	 * @return the 2D array containing the milk data of this year, returns null if
	 *         the year is not found in the yearCollection
	 */
	public int[][] getYear(int year) {
		for (YearNode yr : yearCollection) {
			if (yr.year == year) {
				return yr.timeMap;
			}
		}
		return null;
	}

	/**
	 * Gets the milk weight at a particular date
	 * 
	 * @param day   -the date to search for 
	 * @param month -the month this date is in
	 * @param year  -the year this date is in
	 * @return the milk weight at this day
	 */
	public int getDailyTotalForFarm(int day, int month, int year) {
		for (YearNode yr : yearCollection) {
			if (yr.year == year) {
				return yr.timeMap[month - 1][day - 1];
			}
		}
		return 0; // not found;
	}

	/**
	 * get the milk weight in the given month
	 * 
	 * @param month - the month to search
	 * @param year  - the year this month is in
	 * @return the milk weight for this month
	 */
	public int getMonthlyTotalForFarm(int month, int year) {
		for (YearNode yr : yearCollection) {
			if (yr.year == year) {
				int monthlyTotal = 0;
				for (int i = 0; i < 31; i++) {
					monthlyTotal += yr.timeMap[month - 1][i];
				}
				return monthlyTotal;
			}
		}
		return 0; // not found;
	}

	/**
	 * Get the milk weight in the given year
	 * 
	 * @param year - the year to search for
	 * @return the milk weight
	 */
	public int getYearlyTotalForFarm(int year) {
		for (YearNode yr : yearCollection) {
			if (yr.year == year) {
				return yr.totalWeightForYear;
			}
		}
		return 0; // not found;
	}

	public int yearSize() {
		return yearCollection.size();
	}

}
