package com.group40;

import javax.swing.*;
import java.awt.*;

/**
 * This is the picker screen class. It'll display the two
 * buttons for the user to choose which tracker they want to use.
 * It will also display the icon, title, and a message for the user.
 * 
 * @author Amelia Vasquez Rosario
 * @version September 23, 2026
 */

public class PickerScreen extends JPanel 
{

    public PickerScreen(CardLayout cardLayout, JPanel container)
    {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(24, 40, 32, 40));
        setBackground(new Color(0xF7F5F0));

        // icon
        ImageIcon rawIcon = new ImageIcon(getClass().getResource("/icon.png"));
        Image scaledImage = rawIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        JLabel iconLabel = new JLabel(new ImageIcon(scaledImage));
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // title
        JLabel titleLabel = new JLabel("Tracker App");
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 28f));
        titleLabel.setForeground(new Color(0x2B2233));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // subtitle
        JLabel subtitleLabel = new JLabel("Pick a tracker to get started");
        subtitleLabel.setFont(subtitleLabel.getFont().deriveFont(13f));
        subtitleLabel.setForeground(new Color(0x7A7365));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // buttons
        JButton wageButton = createTrackerButton("Wage Tracker", new Color(0xF3C6BE), new Color(0x6B2E28));
        wageButton.addActionListener(e -> cardLayout.show(container, "wageForm"));

        JButton costsButton = createTrackerButton("Costs Tracker", new Color(0xC3D9F0), new Color(0x24406B));
        costsButton.addActionListener(e -> cardLayout.show(container, "costsForm"));

        JPanel buttonRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 22, 0));
        buttonRow.setOpaque(false);
        buttonRow.add(wageButton);
        buttonRow.add(costsButton);
        buttonRow.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(iconLabel);
        add(Box.createVerticalStrut(14));
        add(titleLabel);
        add(Box.createVerticalStrut(4));
        add(subtitleLabel);
        add(Box.createVerticalStrut(34));
        add(buttonRow);
    }

    private JButton createTrackerButton(String text, Color background, Color textColor)
    {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(210, 74));
        button.setBackground(background);
        button.setForeground(textColor);
        button.setFont(button.getFont().deriveFont(Font.BOLD, 15f));
        button.putClientProperty("JButton.arc", 50); // FlatLaf-specific: rounds this button's corners
        button.setFocusPainted(false);
        return button;
    }

}