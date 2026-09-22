package com.group40;

import javax.swing.*;
import java.awt.*;

public class WageTrackerForm extends JPanel {

    private JTextField monthlyWageField;
    private JTextField rentField;
    private JTextField amenitiesField;
    private JTextField groceriesField;

    public WageTrackerForm()
    {
        setLayout(new GridLayout(0, 2));

        add(new JLabel("Monthly Wage:"));
        monthlyWageField = new JTextField();
        add(monthlyWageField);

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
        double wage, rent, amenities, groceries;

        try {
            wage = Double.parseDouble(monthlyWageField.getText().trim());
            rent = Double.parseDouble(rentField.getText().trim());
            amenities = Double.parseDouble(amenitiesField.getText().trim());
            groceries = Double.parseDouble(groceriesField.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number." +
            "\n Make sure to use a decimal point for your input! :)");
            return;
        }

        if (wage < 0 || rent < 0 || amenities < 0 || groceries < 0) {
            JOptionPane.showMessageDialog(this, "Enter a number that is 0.0 or greater!  :)");
            return;
        }

        WageTracker tracker = new WageTracker(rent, amenities, groceries, wage);

        // TODO: SessionHistory.addEntry(tracker); once Hasini's class exists
    }

}