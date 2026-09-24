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

    private SessionHistory history;

    private JTextField hourlyWageField;
    private JTextField rentField;
    private JTextField amenitiesField;
    private JTextField groceriesField;

    public CostsTrackerForm(CardLayout cardLayout, JPanel container, SessionHistory history)
    {
        this.history = history;

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

        JButton historyButton = new JButton("View History");
        historyButton.addActionListener(e -> cardLayout.show(container, "historyScreen"));
        add(historyButton);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> cardLayout.show(container, "pickerScreen"));
        add(backButton);
    }

    /**
     * Validates the input, builds a CostsTracker,
     * shows the result, and adds it to the session history.
     */
    public void onSubmit()
    {
        JTextField[] fields = {hourlyWageField, rentField, amenitiesField, groceriesField};

        // clear any highlights from the last submit
        for (JTextField field : fields) {
            field.putClientProperty("JComponent.outline", null);
        }

        // empty fields are checked before parsing
        boolean hasEmpty = false;
        for (JTextField field : fields) {
            if (field.getText().trim().isEmpty()) {
                field.putClientProperty("JComponent.outline", "error");
                hasEmpty = true;
            }
        }
        if (hasEmpty) {
            JOptionPane.showMessageDialog(this, "Please fill in every field!  :)");
            return;
        }

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

        double[] values = {hourlyWage, rent, amenities, groceries};
        boolean hasNegative = false;
        for (int i = 0; i < values.length; i++) {
            if (values[i] < 0) {
                fields[i].putClientProperty("JComponent.outline", "error");
                hasNegative = true;
            }
        }
        if (hasNegative) {
            JOptionPane.showMessageDialog(this, "Enter a number that is 0.0 or greater!  :)");
            return;
        }

        CostsTracker tracker = new CostsTracker(rent, amenities, groceries, hourlyWage);

        double hours;
        try {
            hours = tracker.getHours();
        } catch (IllegalArgumentException e) {
            hourlyWageField.putClientProperty("JComponent.outline", "error");
            JOptionPane.showMessageDialog(this, "Enter an hourly wage greater than $0");
            return;
        }

        JOptionPane.showMessageDialog(this, "You need to work " + String.format("%.2f", hours)
            + " hours to cover your expenses.");

        history.addEntry(tracker);
    }

}
