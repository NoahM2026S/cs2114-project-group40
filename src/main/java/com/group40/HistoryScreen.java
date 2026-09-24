package com.group40;

import javax.swing.*;
import java.awt.*;

/**
 * The history screen class that will
 * visually display the session history list.
 * It reads from SessionHistory and forwards
 * delete and edit-and-recalculate requests back to it.
 * 
 * @author Zachias Noble
 * @version September 23, 2026
 */

public class HistoryScreen extends JPanel {

    private SessionHistory history;
    private DefaultListModel<String> listModel;
    private JList<String> historyList;

    public HistoryScreen(CardLayout cardLayout, JPanel container, SessionHistory history)
    {
        this.history = history;

        setLayout(new BorderLayout());

        add(new JLabel("Session History"), BorderLayout.NORTH);

        listModel = new DefaultListModel<>();
        historyList = new JList<>(listModel);
        add(new JScrollPane(historyList), BorderLayout.CENTER);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> onDelete(historyList.getSelectedIndex()));

        JButton editButton = new JButton("Edit & Recalculate");
        editButton.addActionListener(e -> onEditRecalculate(historyList.getSelectedIndex()));

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> cardLayout.show(container, "pickerScreen"));

        JPanel buttonRow = new JPanel();
        buttonRow.add(deleteButton);
        buttonRow.add(editButton);
        buttonRow.add(backButton);
        add(buttonRow, BorderLayout.SOUTH);

        // reload the list every time this screen is shown
        addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentShown(java.awt.event.ComponentEvent e) {
                refresh();
            }
        });
    }

    /**
     * Re-reads the session history and redraws the list.
     */
    public void refresh()
    {
        listModel.clear();

        for (Tracker tracker : history.getEntries()) {
            listModel.addElement(tracker.getSummary());
        }
    }

    /**
     * Forwards a delete request to SessionHistory.
     * 
     * @param index The index of the entry to delete.
     */
    public void onDelete(int index)
    {
        try {
            history.deleteEntry(index);
        } catch (IndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(this, "That entry no longer exists");
        }
        refresh();
    }

    /**
     * Asks the user for new inputs, builds an updated tracker
     * of the same type, and forwards it to SessionHistory.
     * 
     * @param index The index of the entry to edit.
     */
    public void onEditRecalculate(int index)
    {
        Tracker old;
        try {
            old = history.getEntries().get(index);
        } catch (IndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(this, "That entry no longer exists");
            refresh();
            return;
        }

        boolean isWage = old instanceof WageTracker;

        JTextField wageField = new JTextField();
        JTextField rentField = new JTextField();
        JTextField amenitiesField = new JTextField();
        JTextField groceriesField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.add(new JLabel(isWage ? "Monthly Wage:" : "Hourly Wage:"));
        panel.add(wageField);
        panel.add(new JLabel("Rent:"));
        panel.add(rentField);
        panel.add(new JLabel("Amenities:"));
        panel.add(amenitiesField);
        panel.add(new JLabel("Groceries:"));
        panel.add(groceriesField);

        int choice = JOptionPane.showConfirmDialog(this, panel, "Edit & Recalculate",
            JOptionPane.OK_CANCEL_OPTION);
        if (choice != JOptionPane.OK_OPTION) {
            return;
        }

        JTextField[] fields = {wageField, rentField, amenitiesField, groceriesField};
        for (JTextField field : fields) {
            if (field.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in every field!  :)");
                return;
            }
        }

        double wage, rent, amenities, groceries;

        try {
            wage = Double.parseDouble(wageField.getText().trim());
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

        Tracker updated;
        if (isWage) {
            updated = new WageTracker(rent, amenities, groceries, wage);
        } else {
            CostsTracker costs = new CostsTracker(rent, amenities, groceries, wage);
            try {
                costs.getHours();
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(this, "Enter an hourly wage greater than $0");
                return;
            }
            updated = costs;
        }

        try {
            history.recalculate(index, updated);
        } catch (IndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(this, "That entry no longer exists");
        }
        refresh();
    }

}
