package com.group40;

/**
 * Abstract head class for the tracker classes.
 * This class is used to track the costs of the project.
 * It also contains the inherited methods for the
 * extending classes.
 * 
 * @author Amelia Vasquez Rosario
 * @version September 22, 2026
 */

public abstract class Tracker {
    
    // fields

    protected String title;
    protected String description;
    protected double rent;
    protected double amenities;
    protected double groceries;

    // constructor

    /**
     * Constructor for the Tracker class.
     * 
     * @param title     The title for the tracker.
     * @param description The description for the tracker.
     * @param rent      The rent cost inputted by the user.
     * @param amenities The amenities cost inputted by the user.
     * @param groceries The groceries cost inputted by the user.
     */
    public Tracker(String title, String description, double rent, double amenities, double groceries)
    {
        this.title = title;
        this.description = description;
        this.rent = rent;
        this.amenities = amenities;
        this.groceries = groceries;
    }

    // methods

    /**
     * Calculates the total expenses for the tracker.
     * @return The total expenses.
     */
    public double getExpenses()
    {
        return rent + amenities + groceries;
    }

    /**
     * Provides a stub for the result each extending tracker class will implement.
    */
   public abstract double getResult();

   /**
    * Provides the summary of the tracker's result.
    * It's also used in the session history list.
    * @return a string with the summary.
    */
   public String getSummary()
   {
       return title + ": $" + String.format("%.2f", getResult());
   }

   /**
    * Provides the title of the tracker.
    * @return the title of the tracker.
    */
   public String getTitle()
   {
       return title;
   }

   /**
    * Provides the description of the tracker.
    * @return the description of the tracker.
    */
   public String getDescription()
   {
       return description;
   }

}
