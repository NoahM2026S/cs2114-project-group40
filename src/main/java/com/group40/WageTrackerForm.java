package com.group40;

import javax.swing.*;
import java.awt.*;

/**
 * The Wage Tracker form class that will
 * visually display the wage tracking interface.
 * 
 * @author Amelia Vasquez Rosario
 * @version September 23, 2026
 */

public class WageTrackerForm extends JPanel
{

    private JTextField monthlyWageField;
    private JTextField rentField;
    private JTextField amenitiesField;
    private JTextField groceriesField;

    private CardLayout cardLayout;
    private JPanel container;

    public WageTrackerForm(CardLayout cardLayout, JPanel container)
    {
        this.cardLayout = cardLayout;
        this.container = container;

        setLayout(new BorderLayout());
        setBackground(new Color(0xFBF1EC));
        setBorder(BorderFactory.createEmptyBorder(16, 30, 20, 30));

        // top bar: back button
        JButton backButton = createStyledButton("< Back", new Color(0xF6DED0), new Color(0x4A2E3B), null);
        backButton.addActionListener(e -> cardLayout.show(container, "pickerScreen"));

        JPanel topBar = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        topBar.setOpaque(false);
        topBar.add(backButton);
        add(topBar, BorderLayout.NORTH);

        // center: title + input fields
        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Wage Tracker");
        titleLabel.setFont(new Font("Quicksand", Font.BOLD, 22));
        titleLabel.setForeground(new Color(0x4A2E3B));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(titleLabel);
        centerPanel.add(Box.createVerticalStrut(18));

        JPanel fieldsPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        fieldsPanel.setOpaque(false);
        fieldsPanel.setMaximumSize(new Dimension(420, 140));

        fieldsPanel.add(styledLabel("Monthly Wage:"));
        monthlyWageField = styledTextField();
        fieldsPanel.add(monthlyWageField);

        fieldsPanel.add(styledLabel("Rent:"));
        rentField = styledTextField();
        fieldsPanel.add(rentField);

        fieldsPanel.add(styledLabel("Amenities:"));
        amenitiesField = styledTextField();
        fieldsPanel.add(amenitiesField);

        fieldsPanel.add(styledLabel("Groceries:"));
        groceriesField = styledTextField();
        fieldsPanel.add(groceriesField);

        centerPanel.add(fieldsPanel);
        add(centerPanel, BorderLayout.CENTER);

        // bottom bar: submit + view history
        JButton submitButton = createStyledButton("Submit", new Color(0xF3C6BE), new Color(0x6B2E28), "/darkredpaw.png");
        submitButton.addActionListener(e -> onSubmit());

        JButton clearButton = createStyledButton("Clear", new Color(0xF6DED0), new Color(0x4A2E3B), null);
        clearButton.addActionListener(e -> clearFields());

        JButton historyButton = createStyledButton("View History", new Color(0xC3D9F0), new Color(0x24406B), "/darkbluepaw.png");
        historyButton.addActionListener(e -> cardLayout.show(container, "historyScreen"));

        JPanel bottomBar = new JPanel(new FlowLayout(FlowLayout.CENTER, 16, 0));
        bottomBar.setOpaque(false);
        bottomBar.add(submitButton);
        bottomBar.add(clearButton);
        bottomBar.add(historyButton);
        add(bottomBar, BorderLayout.SOUTH);
    }

    private JLabel styledLabel(String text)
    {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Quicksand", Font.BOLD, 13));
        label.setForeground(new Color(0x4A2E3B));
        return label;
    }

    private JTextField styledTextField()
    {
        JTextField field = new JTextField();
        field.setFont(new Font("Quicksand", Font.PLAIN, 13));
        field.putClientProperty("FlatLaf.style", "arc: 12");
        return field;
    }

    private JButton createStyledButton(String text, Color background, Color textColor, String pawFile)
    {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(150, 46));
        button.setBackground(background);
        button.setForeground(textColor);
        button.setFont(new Font("Quicksand", Font.BOLD, 14));
        button.putClientProperty("FlatLaf.style", "arc: 999");
        button.setFocusPainted(false);

        if (pawFile != null)
        {
            ImageIcon rawPaw = new ImageIcon(getClass().getResource(pawFile));
            Image scaledPaw = rawPaw.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
            button.setIcon(new ImageIcon(scaledPaw));
            button.setIconTextGap(6);
        }

        return button;
    }

    private void onSubmit()
    {
        double wage, rent, amenities, groceries;

        try
        {
            wage = Double.parseDouble(monthlyWageField.getText().trim());
            rent = Double.parseDouble(rentField.getText().trim());
            amenities = Double.parseDouble(amenitiesField.getText().trim());
            groceries = Double.parseDouble(groceriesField.getText().trim());
        }
        catch (NumberFormatException e)
        {
            JOptionPane.showMessageDialog(this, "Please enter a valid number." +
            "\n Make sure to use a decimal point for your input! :)");
            return;
        }

        if (wage < 0 || rent < 0 || amenities < 0 || groceries < 0)
        {
            JOptionPane.showMessageDialog(this, "Enter a number that is 0.0 or greater!  :)");
            return;
        }
        WageTracker tracker = new WageTracker(rent, amenities, groceries, wage);

        SessionHistory.addEntry(tracker);
        JOptionPane.showMessageDialog(this, tracker.getSummary());
    }

    private void clearFields()
    {
        monthlyWageField.setText("");
        rentField.setText("");
        amenitiesField.setText("");
        groceriesField.setText("");
    }

}