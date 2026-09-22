package com.group40;

/**
 * The wage tracking tracker class.
 * This class is used to track the wage inputted by the user.
 * It subsequently compared their wage to the total expenses
 * inputted by the user and calculated by the tracker.
 * 
 * @author Amelia Vasquez Rosario
 * @version September 22, 2026
 */
public class WageTracker extends Tracker {
    
    // fields

    private double monthlyWage;

    // constructor

    /**
     * Constructor for the WageTracker class.
     * 
     * @param rent        The rent cost inputted by the user.
     * @param amenities   The amenities cost inputted by the user.
     * @param groceries   The groceries cost inputted by the user.
     * @param monthlyWage The monthly wage inputted by the user.
     */
    public WageTracker(double rent, double amenities, double groceries, double monthlyWage)
    {
        super("Wage Tracker", "Is your wage enough to cover your expenses?", rent, amenities, groceries);
        this.monthlyWage = monthlyWage;
    }

    // methods

    /**
     * Calculates the result of the tracker.
     * This method calculates the difference between
     * the total expenses and the wage inputted by
     * the user. It returns a positive number if
     * the wage is greater than the expenses and a
     * negative number if the expenses are greater
     * than the wage.
     * 
     * @return The result of the tracker.
     */
    public double getRemaining()
    {
        return monthlyWage - getExpenses();
    }

    /**    (non-Javadoc)
     * Provides the result, and overrides
     * the getResult() method in the Tracker class.
     * @return The result of the tracker.
     */
    @Override
    public double getResult()
    {
        return getRemaining();
    }

}
