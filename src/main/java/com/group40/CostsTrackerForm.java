package com.group40;

import javax.swing.*;
import java.awt.*;

/**
 * The Costs Tracker form class that will
 * visually display the costs tracking interface.
 * 
 * @author Hasini Pottipati
 * @version September 23, 2026
 */

public class CostsTrackerForm extends JPanel {

    private JTextField hourlyWageField;
    private JTextField rentField;
    private JTextField amenitiesField;
    private JTextField groceriesField;

    public CostsTrackerForm()
    {
        setLayout(new GridLayout(0, 2));

        add(new JLabel("Hourly Wage:"));
        hourlyWageField = new JTextField();
        add(hourlyWageField);

        add(new JLabel("Rent:"));
        rentField = new JTextField();
        add(rentField);

        add(new JLabel("Amenities:"));
        amenitiesField = new JTextField();
        add(amenitiesField);

        add(new JLabel("Groceries:"));
        groceriesField = new JTextField();
        add(groceriesField);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> onSubmit());
        add(submitButton);
    }

    private void onSubmit()
    {
        double hourlyWage, rent, amenities, groceries;

        try {
            hourlyWage = Double.parseDouble(hourlyWageField.getText().trim());
            rent = Double.parseDouble(rentField.getText().trim());
            amenities = Double.parseDouble(amenitiesField.getText().trim());
            groceries = Double.parseDouble(groceriesField.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number." +
            "\n Make sure to use a decimal point for your input! :)");
            return;
        }

        if (hourlyWage < 0 || rent < 0 || amenities < 0 || groceries < 0) {
            JOptionPane.showMessageDialog(this, "Enter a number that is 0.0 or greater!  :)");
            return;
        }

        CostsTracker tracker = new CostsTracker(rent, amenities, groceries, hourlyWage);

        SessionHistory.addEntry(tracker);
        JOptionPane.showMessageDialog(this, tracker.getSummary());
    }

}
