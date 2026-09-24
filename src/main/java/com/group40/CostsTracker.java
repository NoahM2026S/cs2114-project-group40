package com.group40;

/**
 * The costs tracking tracker class.
 * This class is used to track the hourly wage inputted by the user.
 * It subsequently calculates how many hours the user needs
 * to work to cover the total expenses inputted by the user
 * and calculated by the tracker.
 * 
 * @author Hasini Pottipati
 * @version September 23, 2026
 */
public class CostsTracker extends Tracker {

    // fields

    private double hourlyWage;

    // constructor

    /**
     * Constructor for the CostsTracker class.
     * 
     * @param rent       The rent cost inputted by the user.
     * @param amenities  The amenities cost inputted by the user.
     * @param groceries  The groceries cost inputted by the user.
     * @param hourlyWage The hourly wage inputted by the user.
     */
    public CostsTracker(double rent, double amenities, double groceries, double hourlyWage)
    {
        super("Costs Tracker", "How many hours do you need to work to cover your expenses?", rent, amenities, groceries);
        this.hourlyWage = hourlyWage;
    }

    // methods

    /**
     * Calculates the number of hours the user needs to work.
     * This method divides the total expenses by the
     * hourly wage inputted by the user.
     * 
     * @return The hours needed to cover the expenses.
     */
    public double getHoursNeeded()
    {
        if (hourlyWage <= 0)
        {
        throw new IllegalArgumentException("Hourly wage must be greater than 0.");
        }
        return getExpenses() / hourlyWage;
    }

    /**    (non-Javadoc)
     * Provides the result, and overrides
     * the getResult() method in the Tracker class.
     * @return The result of the tracker.
     */
    @Override
    public double getResult()
    {
        return getHoursNeeded();
    }

    @Override
    public String getSummary()
    {
        return String.format("Costs Tracker: You need to work %.1f hours to cover your expenses.", getHoursNeeded());
    }
}
