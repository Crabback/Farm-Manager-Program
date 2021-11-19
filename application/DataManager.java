package application;



import java.util.ArrayList;
import java.util.Set;

/**
 * Filename: Main.java Project: Milk's Friend Authors: ateam88
 * Defines operations on data manipulations and forming the required data for
 * the visualizer.
 * 
 * @author ateam88
 *
 */
public class DataManager {

	private CheeseFactory cFac;
	final int ryear[] = { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	final int nryear[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	/**
	 * Initialize an instance of DataManager
	 */
	public DataManager() {
		cFac = new CheeseFactory();
	}

	/**
     * Initialize an instance of DataManager with given cheese factory
     * @param cf - input cheese factory
     */
	public DataManager(CheeseFactory cf) {
		cFac = cf;
	}

	/**
	 * Calculate the number of days in a given month
	 * 
	 * @param month - the given month
	 * @param year  - the year that this month is in
	 * @return the number of days in this month
	 */
	private int calDays(int month, int year) {
		if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
			return ryear[month - 1];
		else
			return nryear[month - 1];
	}

	/**
	 * Get the average milk weight of a day offered in a given month
	 * 
	 * @param month -the given month
	 * @param year  -the year that this month is in
	 * @return -the average milk weight
	 */
	public int getMonthlyAverage(int month, int year) {
		return cFac.getMonthlyTotal(month, year) / calDays(month, year);
	}

	/**
	 * Return the minimum milk weight in one day during the given month
	 * 
	 * @param month - the given month
	 * @param year  - the year this month is in
	 * @return the minimum milk weight
	 */
	public int getMonthlyMin(int month, int year) {
		int min = cFac.getDailyTotal(1, month, year);
		int dayTotal;
		int days = calDays(month, year);
		for (int i = 1; i <= days; i++) {
			dayTotal = cFac.getDailyTotal(i, month, year);
			if (dayTotal < min) {
				min = dayTotal;
			}
		}
		return min;
	}

	/**
	 * Return the day with the minimum milk weight during the given month
	 * 
	 * @param month - the given month
	 * @param year  - the year this month is in
	 * @return the day with the minimum milk in given month and year
	 */
	public int getMonthlyMin_Day(int month, int year) {
		int min = cFac.getDailyTotal(1, month, year);
		int dayTotal;
		int days = calDays(month, year);
		int minDay = 1;
		for (int i = 1; i <= days; i++) {
			dayTotal = cFac.getDailyTotal(i, month, year);
			if (dayTotal < min) {
				min = dayTotal;
				minDay = i;
			}
		}
		return minDay;
	}

	/**
	 * Return the maximum milk weight in one day during the given month
	 * 
	 * @param month -the given month
	 * @param year  -the year this month is in
	 * @return the maximum milk weight
	 */
	public int getMonthlyMax(int month, int year) {
		int max = cFac.getDailyTotal(1, month, year);
		int dayTotal;
		int days = calDays(month, year);
		for (int i = 1; i <= days; i++) {
			dayTotal = cFac.getDailyTotal(i, month, year);
			if (dayTotal > max) {
				max = dayTotal;
			}
		}
		return max;
	}

	/**
	 * Return the day with the maximum milk weight during the given month
	 * 
	 * @param month - the given month
	 * @param year  - the year this month is in
	 * @return the day with the maximum milk weight in the given month 
	 */
	public int getMonthlyMax_Day(int month, int year) {
		int max = cFac.getDailyTotal(1, month, year);
		int dayTotal;
		int days = calDays(month, year);
		int maxDay = 1;
		for (int i = 1; i <= days; i++) {
			dayTotal = cFac.getDailyTotal(i, month, year);
			if (dayTotal > max) {
				max = dayTotal;
				maxDay = i;
			}
		}
		return maxDay;
	}

	/**
	 * Get the average milk weight of a month offered in a given year
	 * 
	 * @param year - the given year
	 * @return the average milk weight
	 */
	public int getYearlyAverage(int year) {
		return cFac.getYearlyTotal(year) / 12;
	}

	/**
	 * Return the minimum milk weight provided in a month during the given year
	 * 
	 * @param year - the given year
	 * @return the milk weight in that month
	 */
	public int getYearlyMin(int year) {
		int min = cFac.getMonthlyTotal(1, year);
		int monthTotal;
		for (int i = 1; i <= 12; i++) {
			monthTotal = cFac.getMonthlyTotal(i, year);
			if (monthTotal < min) {
				min = monthTotal;
			}
		}
		return min;
	}

	/**
	 * Return the month with the minimum milk weight provided in given year
	 * 
	 * @param year -the given year
	 * @return the month with the minimum milk weight provided in given year
	 */
	public int getYearlyMin_Month(int year) {
		int min = cFac.getMonthlyTotal(1, year);
		int monthTotal;
		int minMonth = 1;
		for (int i = 1; i <= 12; i++) {
			monthTotal = cFac.getMonthlyTotal(i, year);
			if (monthTotal < min) {
				min = monthTotal;
				minMonth = i;
			}
		}
		return minMonth;
	}

	/**
	 * Return the maximum milk weight provided in a month during the given year
	 * 
	 * @param year -the given year
	 * @return the maximum milk weight provided in a month during the given year
	 */
	public int getYearlyMax(int year) {
		int max = cFac.getMonthlyTotal(1, year);
		int monthTotal;
		for (int i = 1; i <= 12; i++) {
			monthTotal = cFac.getMonthlyTotal(i, year);
			if (monthTotal > max) {
				max = monthTotal;
			}
		}
		return max;
	}

	/**
	 * Return the month with the maximum milk weight provided in given year
	 * 
	 * @param year - the given year
	 * @return the month with the maximum milk weight provided in given year
	 */
	public int getYearlyMax_Month(int year) {
		int max = cFac.getMonthlyTotal(1, year);
		int monthTotal;
		int maxMonth = 1;
		for (int i = 1; i <= 12; i++) {
			monthTotal = cFac.getMonthlyTotal(i, year);
			if (monthTotal > max) {
				max = monthTotal;
				maxMonth = i;
			}
		}
		return maxMonth;
	}

	/**
	 * Get the average milk weight provided by a given farm in one day of a given
	 * month
	 * 
	 * @param id    - the ID of the farm
	 * @param month - the given month
	 * @param year  - the year that this month is in
	 * @return the average milk weight
	 */
	public int getMonthlyAverageForFarm(String id, int month, int year) {
		try {
			return cFac.getHashMap().get(id).getMonthlyTotalForFarm(month, year) / calDays(month, year);
		} catch (Exception e) {
			System.out.println("Farm not found!");
		}
		return 0;
	}

	/**
	 * Get the minimum milk weight provided by a given farm in one day of the given
	 * month
	 * 
	 * @param id    - the ID of the farm
	 * @param month - the given month
	 * @param year  - the year that this month is in
	 * @return the minimum milk weight in a day of that month
	 */
	public int getMonthlyMinForFarm(String id, int month, int year) {
		try {
			int min = cFac.getHashMap().get(id).getDailyTotalForFarm(1, month, year);
			int dayTotalForFarm;
			int days = calDays(month, year);
			for (int i = 1; i <= days; i++) {
				dayTotalForFarm = cFac.getHashMap().get(id).getDailyTotalForFarm(i, month, year);
				if (dayTotalForFarm < min) {
					min = dayTotalForFarm;
				}
			}
			return min;
		} catch (Exception e) {
			System.out.println("Farm not found!");
		}
		return 0;
	}

	/**
	 * Get the day with the minimum milk weight provided by a given farm in the given month
	 * 
	 * @param id    - the ID of the farm
	 * @param month - the given month
	 * @param year  - the year that this month is in
	 * @return - the day with the minimum milk weight provided by a given farm in the given month
	 */
	public int getMonthlyMinForFarm_Day(String id, int month, int year) {
		try {
			int min = cFac.getHashMap().get(id).getDailyTotalForFarm(1, month, year);
			int dayTotalForFarm;
			int days = calDays(month, year);
			int minDay = 1;
			for (int i = 1; i <= days; i++) {
				dayTotalForFarm = cFac.getHashMap().get(id).getDailyTotalForFarm(i, month, year);
				if (dayTotalForFarm < min) {
					min = dayTotalForFarm;
					minDay = i;
				}
			}
			return minDay;
		} catch (Exception e) {
			System.out.println("Farm not found!");
		}
		return 0;
	}

	/**
	 * Get the maximum milk weight provided by a given farm in one day of the given
	 * month
	 * 
	 * @param id    - the ID of the farm
	 * @param month - the given month
	 * @param year  - the year that this month is in
	 * @return the maximum milk weight in a day of that month
	 */
	public int getMonthlyMaxForFarm(String id, int month, int year) {
		try {
			int max = cFac.getHashMap().get(id).getDailyTotalForFarm(1, month, year);
			int dayTotalForFarm;
			int days = calDays(month, year);
			for (int i = 1; i <= days; i++) {
				dayTotalForFarm = cFac.getHashMap().get(id).getDailyTotalForFarm(i, month, year);
				if (dayTotalForFarm > max) {
					max = dayTotalForFarm;
				}
			}
			return max;
		} catch (Exception e) {
			System.out.println("Farm not found!");
		}
		return 0;
	}

	/**
	 * Get the day with the maximum milk weight provided by a given farm in the given month
	 * 
	 * @param id    - the ID of the farm
	 * @param month - the given month
	 * @param year  - the year that this month is in
	 * @return the day with the maximum milk weight provided by a given farm in the given month
	 */
	public int getMonthlyMaxForFarm_Day(String id, int month, int year) {
		try {
			int max = cFac.getHashMap().get(id).getDailyTotalForFarm(1, month, year);
			int dayTotalForFarm;
			int days = calDays(month, year);
			int maxDay = 1;
			for (int i = 1; i <= days; i++) {
				dayTotalForFarm = cFac.getHashMap().get(id).getDailyTotalForFarm(i, month, year);
				if (dayTotalForFarm > max) {
					max = dayTotalForFarm;
					maxDay = i;
				}
			}
			return maxDay;
		} catch (Exception e) {
			System.out.println("Farm not found!");
		}
		return 0;
	}

	/**
	 * Get the average milk weight of a month provided by a given farm in a given
	 * year
	 * 
	 * @param id   - the ID of the farm
	 * @param year - the given year
	 * @return the average milk weight
	 */
	public int getYearlyAverageForFarm(String id, int year) {
		try {
			return cFac.getHashMap().get(id).getYearlyTotalForFarm(year) / 12;
		} catch (Exception e) {
			System.out.println("Farm not found!");
		}
		return 0;
	}

	/**
	 * Return the minimum milk weight provided by a farm in a month during the given
	 * year
	 * 
	 * @param id   - the ID of the farm
	 * @param year - the given year
	 * @return the milk weight of that month
	 */
	public int getYearlyMinForFarm(String id, int year) {
		try {
			int min = cFac.getHashMap().get(id).getMonthlyTotalForFarm(1, year);
			int monthTotal;
			for (int i = 1; i <= 12; i++) {
				monthTotal = cFac.getHashMap().get(id).getMonthlyTotalForFarm(i, year);
				if (monthTotal < min) {
					min = monthTotal;
				}
			}
			return min;
		} catch (Exception e) {
			System.out.println("Farm not found!");
		}
		return 0;
	}

	/**
	 * Return the minimum milk weight provided by a farm in a month during the given
	 * year
	 * 
	 * @param id   - the ID of the farm
	 * @param year - the given year
	 * @return the month with the minimum milk weight provided by a farm during the given year
	 */
	public int getYearlyMinForFarm_Month(String id, int year) {
		try {
			int min = cFac.getHashMap().get(id).getMonthlyTotalForFarm(1, year);
			int monthTotal;
			int minMonth = 1;
			for (int i = 1; i <= 12; i++) {
				monthTotal = cFac.getHashMap().get(id).getMonthlyTotalForFarm(i, year);
				if (monthTotal < min) {
					min = monthTotal;
					minMonth = i;
				}
			}
			return minMonth;
		} catch (Exception e) {
			System.out.println("Farm not found!");
		}
		return 0;
	}

	/**
	 * Return the maximum milk weight provided by a farm in a month during the given
	 * year
	 * 
	 * @param id   - the ID of the farm
	 * @param year - the given year
	 * @return the milk weight of that month
	 */
	public int getYearlyMaxForFarm(String id, int year) {
		try {
			int max = cFac.getHashMap().get(id).getMonthlyTotalForFarm(1, year);
			int monthTotal;
			for (int i = 1; i <= 12; i++) {
				monthTotal = cFac.getHashMap().get(id).getMonthlyTotalForFarm(i, year);
				if (monthTotal > max) {
					max = monthTotal;
				}
			}
			return max;
		} catch (Exception e) {
			System.out.println("Farm not found!");
		}
		return 0;
	}

	/**
     * Return the maximum milk weight provided by a farm in a month during the given
     * year
     * 
     * @param id   - the ID of the farm
     * @param year - the given year
     * @return the month with the maximum milk weight provided by a farm during the given year
     */
	public int getYearlyMaxForFarm_Month(String id, int year) {
		try {
			int max = cFac.getHashMap().get(id).getMonthlyTotalForFarm(1, year);
			int monthTotal;
			int maxMonth = 1;
			for (int i = 1; i <= 12; i++) {
				monthTotal = cFac.getHashMap().get(id).getMonthlyTotalForFarm(i, year);
				if (monthTotal > max) {
					max = monthTotal;
					maxMonth = i;
				}
			}
			return maxMonth;
		} catch (Exception e) {
			System.out.println("Farm not found!");
		}
		return 0;
	}

	/**
	 * Get the daily average milk weight between two given date
	 * 
	 * @param year   -the given year that two dates are in
	 * @param month1 -the month that the beginning date is in
	 * @param day1   -the beginning date
	 * @param month2 -the month that the ending date is in
	 * @param day2   -the ending date
	 * @return -the average milk weight
	 */
	public int getAverageInDateRange(int year, int month1, int day1, int month2, int day2) {
		int totalDays = 0;
		int totalWeight = 0;
		// calculate the total number of days
		for (int i = month1; i < month2; i++) {
			totalDays = totalDays + calDays(i, year);
		}
		totalDays = totalDays - day1 + day2;
		// first calculate the weights without month1 and month2
		for (int i = month1 + 1; i < month2; i++) {
			for (int j = 1; j <= calDays(i, year); j++)
				totalWeight = totalWeight + cFac.getDailyTotal(i, j, year);
		}
		// calculate the weight for month1 days
		for (int i = day1; i <= calDays(month1, year); i++) {
			totalWeight = totalWeight + cFac.getDailyTotal(i, month1, year);
		}
		// calculate the weight for month2 days
		for (int i = 1; i <= day2; i++) {
			totalWeight = totalWeight + cFac.getDailyTotal(i, month2, year);
		}
		return totalWeight / totalDays;

	}

	/**
	 * Get the minimum weight of milk provided in a day between two given dates
	 * 
	 * @param year   - the year that the two given dates are in
	 * @param month1 -the month that the beginning date is in
	 * @param day1   -the beginning date
	 * @param month2 -the month that the ending date is in
	 * @param day2   -the ending date
	 * @return -the milk weight of that day
	 */
	public int getMinInDateRange(int year, int month1, int day1, int month2, int day2) {
		int minWeight = cFac.getDailyTotal(day1, month1, year);
		// first calculate the min weights without month1 and month2
		for (int i = month1 + 1; i < month2; i++) {
			for (int j = 1; j <= calDays(i, year); j++)
				if (minWeight > cFac.getDailyTotal(j, i, year)) {
					minWeight = cFac.getDailyTotal(j, i, year);
				}
		}
		// calculate the min weight for month1 days
		for (int i = day1; i <= calDays(month1, year); i++) {
			if (minWeight > cFac.getDailyTotal(i, month1, year))
				minWeight = cFac.getDailyTotal(i, month1, year);

		}
		// calculate the min weight for month2 days
		for (int i = 1; i <= day2; i++) {
			if (minWeight > cFac.getDailyTotal(i, month2, year))
				minWeight = cFac.getDailyTotal(i, month2, year);
		}
		return minWeight;
	}

	/**
	 * Get the maximum weight of milk provided in a day between two given dates
	 * 
	 * @param year   - the year that the two given dates are in
	 * @param month1 -the month that the beginning date is in
	 * @param day1   -the beginning date
	 * @param month2 -the month that the ending date is in
	 * @param day2   -the ending date
	 * @return -the milk weight of that day
	 */
	public int getMaxInDateRange(int year, int month1, int day1, int month2, int day2) {
		int maxWeight = cFac.getDailyTotal(day1, month1, year);
		// calculate the total number of days

		// first calculate the weights without month1 and month2
		for (int i = month1 + 1; i < month2; i++) {
			for (int j = 1; j <= calDays(i, year); j++)
				if (maxWeight < cFac.getDailyTotal(j, i, year)) {
					maxWeight = cFac.getDailyTotal(j, i, year);
				}
		}
		// calculate the weight for month1 days
		for (int i = day1; i <= calDays(month1, year); i++) {
			if (maxWeight < cFac.getDailyTotal(i, month1, year))
				maxWeight = cFac.getDailyTotal(i, month1, year);

		}
		// calculate the weight for month2 days
		for (int i = 1; i <= day2; i++) {
			if (maxWeight < cFac.getDailyTotal(i, month2, year))
				maxWeight = cFac.getDailyTotal(i, month2, year);
		}
		return maxWeight;
	}

	/**
     * Get the day with the maximum weight of milk provided by all farms within the given
     * date range
     * @param year   - the year that the two given dates are in
     * @param month1 -the month that the beginning date is in
     * @param day1   -the beginning date
     * @param month2 -the month that the ending date is in
     * @param day2   -the ending date
     * @return the day with the maximum weight
     */
	public String getMaxInDateRange_Day(int year, int month1, int day1, int month2, int day2) {
		int maxWeight = getMaxInDateRange(year, month1, day1, month2, day2);
		// first calculate the weights without month1 and month2
		for (int i = month1 + 1; i < month2; i++) {
			for (int j = 1; j <= calDays(i, year); j++)
				if (maxWeight == cFac.getDailyTotal(j, i, year)) {
					return year + "-" + i + "-" + j;
				}
		}
		// calculate the weight for month1 days
		for (int i = day1; i <= calDays(month1, year); i++) {
			if (maxWeight == cFac.getDailyTotal(i, month1, year)) {
				return year + "-" + month1 + "-" + i;
			}

		}
		// calculate the weight for month2 days
		for (int i = 1; i <= day2; i++) {
			if (maxWeight == cFac.getDailyTotal(i, month2, year)) {
				return year + "-" + month2 + "-" + i;
			}
		}

		return null;
	}

	/**
     * Get the day with the minimum  weight of milk provided by all farms within the given
     * date range
     * @param year   - the year that the two given dates are in
     * @param month1 -the month that the beginning date is in
     * @param day1   -the beginning date
     * @param month2 -the month that the ending date is in
     * @param day2   -the ending date
     * @return the day with the minimum weight
     */
	public String getMinInDateRange_Day(int year, int month1, int day1, int month2, int day2) {
		int minWeight = getMinInDateRange(year, month1, day1, month2, day2);
		// first calculate the weights without month1 and month2
		for (int i = month1 + 1; i < month2; i++) {
			for (int j = 1; j <= calDays(i, year); j++)
				if (minWeight == cFac.getDailyTotal(j, i, year)) {
					return year + "-" + i + "-" + j;
				}
		}
		// calculate the weight for month1 days
		for (int i = day1; i <= calDays(month1, year); i++) {
			if (minWeight == cFac.getDailyTotal(i, month1, year)) {
				return year + "-" + month1 + "-" + i;
			}

		}
		// calculate the weight for month2 days
		for (int i = 1; i <= day2; i++) {
			if (minWeight == cFac.getDailyTotal(i, month2, year)) {
				return year + "-" + month2 + "-" + i;
			}
		}
		return null;
	}

	/**
	 * Get the name of all farms stored in the factory
	 * @return
	 */
	public ArrayList<String> getFarmNames(){
		ArrayList<String> list = new ArrayList<String>();
		Set<String> keySet = cFac.getHashMap().keySet();
		for(String key: keySet) {
			list.add(key);
		}
		return list;
	}
	
	/**
	 * Get total milk weight for a given year
	 * @param year - year for data to be obtained
	 * @return total milk weight 
	 */
	public int get(int year) {
		return cFac.getYearlyTotal(year);
	}

	/**
	 * Get total milk weight for a given farm and a given year 
	 * @param id - id of farm for data to be obtained 
	 * @param year - year for data to be obtained 
	 * @return total milk weight
	 */
	public int get(String id, int year) {
		try {
			return cFac.getHashMap().get(id).getYearlyTotalForFarm(year);
		} catch (Exception e) {
			System.out.println("Farm not found!");
		}
		return 0;
	}

	/**
     * Get total milk weight for a given month and year
     * @param month - month for data to be obtained
     * @param year - year for data to be obtained
     * @return total milk weight 
     */
	public int get(int month, int year) {
		return cFac.getMonthlyTotal(month, year);
	}

	/**
     * Get total milk weight for a given farm and a given month, year 
     * @param id - id of farm for data to be obtained 
     * @param month - month for data to be obtained
     * @param year - year for data to be obtained 
     * @return total milk weight
     */
	public int get(String id, int month, int year) {
		try {
			return cFac.getHashMap().get(id).getMonthlyTotalForFarm(month, year);
		} catch (Exception e) {
			System.out.println("Farm not found!");
		}
		return 0;
	}

	/**
     * Get total milk weight for a given day, month, year 
     * @param day - day for data to be obtained 
     * @param month - month for data to be obtained
     * @param year - year for data to be obtained 
     * @return total milk weight
     */
	public int get(int day, int month, int year) {
		return cFac.getDailyTotal(day, month, year);
	}

	/**
     * Get total milk weight for a given farm and a given month, year 
     * @param id - id of farm for data to be obtained 
     * @param day - day for data to be obtained 
     * @param month - month for data to be obtained
     * @param year - year for data to be obtained 
     * @return total milk weight
     */
	public int get(String id, int day, int month, int year) {
		try {
			return cFac.getHashMap().get(id).getDailyTotalForFarm(day, month, year);
		} catch (Exception e) {
			System.out.println("Farm not found!");
		}
		return 0;
	}
	
	/**
	 * Get total milk weight supplied by a given farm within a given date range 
	 * @param id - id of farm for data to be obtained
	 * @param year - year for data to be obtained
	 * @param month1 - month of start of date range
	 * @param day1 - day of start of date range
	 * @param month2 - month of end of date range
	 * @param day2 - day of end of date range
	 * @return total milk weight
	 */
	public int getFarmWeightForDateRange(String id, int year, int month1, int day1, int month2, int day2) {
		int totalWeight = 0;		
		// first calculate the weights without month1 and month2
		if(month1-month2 > 1) {
			for (int i = month1 + 1; i < month2; i++) {
				for (int j = 1; j <= calDays(i, year); j++) {
					totalWeight = totalWeight + cFac.getHashMap().get(id).getDailyTotalForFarm(j, i, year);
					//System.out.println(cFac.getHashMap().get(id).getDailyTotalForFarm(j, i, year));
				}
			}
			// calculate the weight for month1 days
			for (int i = day1; i <= calDays(month1, year); i++) {
				totalWeight = totalWeight + cFac.getHashMap().get(id).getDailyTotalForFarm(i, month1, year);
			}
			// calculate the weight for month2 days
			for (int i = 1; i <= day2; i++) {
				totalWeight = totalWeight + cFac.getHashMap().get(id).getDailyTotalForFarm(i, month2, year);
			}
		}else if(month1 == month2){
			for (int i = day1; i <= day2; i++) {
				totalWeight = totalWeight + cFac.getHashMap().get(id).getDailyTotalForFarm(i, month1, year);
			}
		}else if(month1 - month2 == 1) {
			// calculate the weight for month1 days
						for (int i = day1; i <= calDays(month1, year); i++) {
							totalWeight = totalWeight + cFac.getHashMap().get(id).getDailyTotalForFarm(i, month1, year);
						}
			for (int i = 1; i <= day2; i++) {
				totalWeight = totalWeight + cFac.getHashMap().get(id).getDailyTotalForFarm(i, month2, year);
			}
		}
		return totalWeight;
	}
	
	/**
	 * Get total number of farms that supplies to this cheese factory
	 * @return number of farms
	 */
	public int getFarmNums() {
		return cFac.getNumFarms();
	}

}