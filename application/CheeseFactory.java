package application;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Filename: Main.java Project: Milk's Friend Authors: ateam88
 * Defines a factory that gets its supplies from multiple farms each year
 * 
 * @author ateam88
 *
 */
public class CheeseFactory {

	/**
	 * The inner class of CheeseFactory, an instance of this class stores the milk
	 * data of a cheese factory in total in a certain year
	 * 
	 * @author ateam88
	 *
	 */
	private class yearNodes {
		private int year;
		private int[][] timeMap;
		private int totalWeightForYear;

		/**
		 * Create a new yearNodes instance with the given year
		 * 
		 * @param yr - the year this instance represents
		 */
		private yearNodes(int yr) {
			year = yr;
			timeMap = new int[12][31];
			totalWeightForYear = 0;
		}
	}

	private HashMap<String, Farm> milkDataFromFarms;
	private ArrayList<yearNodes> yearCollection;
	private int numFarms;

	/**
	 * create a new instance of CheeseFactory
	 */
	public CheeseFactory() {
		this.milkDataFromFarms = new HashMap<String, Farm>();
		this.yearCollection = new ArrayList<yearNodes>();
		numFarms = 0;
	}

	/**
	 * Add milk data from a single line of data provided by data input
	 * 
	 * @param data - the String containing that milk weight data
	 */
	public void insertSingleData(String data) {
		// retrieve information from data String
		String[] dataSplit = data.split(",", 0);
		String date = dataSplit[0];
		String id = dataSplit[1].split(" ")[1];
		int weight = Integer.parseInt(dataSplit[2]);
		String[] dateSplit = date.split("-", 0);
		int iYear = Integer.parseInt(dateSplit[0]);
		int iMonth = Integer.parseInt(dateSplit[1]);
		int iDate = Integer.parseInt(dateSplit[2]);
		// insert data into corresponding farm
		if (this.milkDataFromFarms.get(id) == null) {
			this.milkDataFromFarms.put(id, new Farm(id));
			numFarms++;
			this.milkDataFromFarms.get(id).insertMilkForDate(date, weight);
		} else {
			this.milkDataFromFarms.get(id).insertMilkForDate(date, weight);
		}
		// insert data into corresponding year in the yearCollection
		for (yearNodes y : yearCollection) {
			if (y.year == iYear) {
				y.timeMap[iMonth - 1][iDate - 1] += weight;
				y.totalWeightForYear += weight;
			}
		}
		// if the year is not yet created in the yearCollection, create a new year
		// node and insert data into that node
		yearNodes yn = new yearNodes(iYear);
		yearCollection.add(yn);
		yn.timeMap[iMonth - 1][iDate - 1] += weight;
		yn.totalWeightForYear += weight;
	}

	/**
	 * Add milk weight data from the given input ArrayList of Strings line by line
	 * 
	 * @param farms -the ArrayList of Strings containing the milk weight data
	 */
	public void insertData(ArrayList<String> farms) {
		for (String farm : farms) {
			this.insertSingleData(farm);
		}
	}

	/**
	 * Edit milk data given by a single line of data provided in data input
	 * 
	 * @param data -the String containing milk weight data
	 * @return true if the data is successfully edited, false otherwise
	 */
	public boolean editSingleData(String data) {
		// retrieve information from data String
		String[] dataSplit = data.split(",", 0);
		String date = dataSplit[0];
		String id = dataSplit[1].split(" ")[1];
		int weight = Integer.parseInt(dataSplit[2]);
		String[] dateSplit = date.split("-", 0);
		int iYear = Integer.parseInt(dateSplit[0]);
		int iMonth = Integer.parseInt(dateSplit[1]);
		int iDate = Integer.parseInt(dateSplit[2]);
		int oldWeight;
		// edit data of corresponding farm
		if (this.milkDataFromFarms.get(id) == null) {
			return false;
		} else {
			this.milkDataFromFarms.get(id).editMilkForDate(date, weight);
			// edit the information in the yearCollection
			for (yearNodes y : yearCollection) {
				if (y.year == iYear) {
					oldWeight = y.timeMap[iMonth - 1][iDate - 1];
					y.timeMap[iMonth - 1][iDate - 1] = weight;
					y.totalWeightForYear -= oldWeight;
					y.totalWeightForYear += weight;
				}
			}
			yearNodes yn = new yearNodes(iYear);
			yn.timeMap[iMonth - 1][iDate - 1] = weight;
			yn.totalWeightForYear = weight;
			yearCollection.add(yn);
			return true;
		}
	}

	/**
	 * Remove milk data in a date given by a single line of data provided in data
	 * input
	 * 
	 * @param data -the String containing milk weight data
	 * @return true if the data is successfully removed, false otherwise
	 */
	public boolean removeSingleData(String data) {
		// retrieve information from data String
		String[] dataSplit = data.split(",", 0);
		String date = dataSplit[0];
		String id = dataSplit[1].split(" ")[1];
		String[] dateSplit = date.split("-", 0);
		int iYear = Integer.parseInt(dateSplit[0]);
		int iMonth = Integer.parseInt(dateSplit[1]);
		int iDate = Integer.parseInt(dateSplit[2]);
		int removedMilk;
		// remove the data in the corresponding farm
		if (this.milkDataFromFarms.get(id) == null) {
			return false;
		} else {
			this.milkDataFromFarms.get(id).removeMilkForDate(date);
			// remove the data in the corresponding yearCollection
			for (yearNodes y : yearCollection) {
				if (y.year == iYear) {
					removedMilk = y.timeMap[iMonth - 1][iDate - 1];
					y.timeMap[iMonth - 1][iDate - 1] = 0;
					y.totalWeightForYear -= removedMilk;
				}
			}
			return true;
		}
	}

	/**
	 * get the hashMap containing farms in this cheese factory
	 * 
	 * @return
	 */
	public HashMap<String, Farm> getHashMap() {
		return milkDataFromFarms;
	}

	/**
	 * Get the total milk weight of all farms in a given day
	 * 
	 * @param day   - the day whose data is to be retrieved
	 * @param month - the month this day is in
	 * @param year  - the year this day is in
	 * @return the total milk weight in this day
	 */
	public int getDailyTotal(int day, int month, int year) {
		for (yearNodes yr : yearCollection) {
			if (yr.year == year) {
				return yr.timeMap[month - 1][day - 1];
			}
		}
		return 0; // not found;
	}

	/**
	 * Get the total milk weight of all farms in a given month
	 * 
	 * @param month - the month whose data is to be retrieved
	 * @param year  - the year this month is in
	 * @return the total milk weight in this month
	 */
	public int getMonthlyTotal(int month, int year) {
		for (yearNodes yr : yearCollection) {
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
	 * get the total milk weight of all farms in a given month
	 * 
	 * @param year -the year whose data is to be retrieved
	 * @return -the total milk weight in this year
	 */
	public int getYearlyTotal(int year) {
		for (yearNodes yr : yearCollection) {
			if (yr.year == year) {
				return yr.totalWeightForYear;
			}
		}
		return 0; // not found;
	}

	/**
	 * Get the number of farms in this cheese factory
	 * 
	 * @return the number of farms
	 */
	public int getNumFarms() {
		return numFarms;
	}

}