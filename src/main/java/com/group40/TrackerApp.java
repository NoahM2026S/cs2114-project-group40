package com.group40;

import javax.swing.*;
import java.awt.*;
import com.formdev.flatlaf.FlatLightLaf;

public class TrackerApp extends JFrame
{

    // fields
    private CardLayout cardLayout;
    private JPanel container;

    // constructor

    public TrackerApp()
    {
        setTitle("Tracker App");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // --- persistent header: icon + title, always visible ---
        ImageIcon rawIcon = new ImageIcon(getClass().getResource("/icon.png"));
        Image scaledImage = rawIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        JLabel headerIcon = new JLabel(new ImageIcon(scaledImage));

        JLabel headerTitle = new JLabel("Tracker App");
        headerTitle.setFont(headerTitle.getFont().deriveFont(Font.BOLD, 18f));
        headerTitle.setForeground(new Color(0x2B2233));

        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 12));
        headerPanel.setBackground(new Color(0xF7F5F0));
        headerPanel.add(headerIcon);
        headerPanel.add(headerTitle);

        add(headerPanel, BorderLayout.NORTH);
        // ----------------------------------------------------------

        cardLayout = new CardLayout();
        container = new JPanel(cardLayout);

        PickerScreen pickerScreen = new PickerScreen(cardLayout, container);
        container.add(pickerScreen, "pickerScreen");

        add(container);
    }

    public static void main(String[] args)
    {
        try
        {
            UIManager.setLookAndFeel(new FlatLightLaf());
        }
        catch (UnsupportedLookAndFeelException e)
        {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            TrackerApp app = new TrackerApp();
            app.setVisible(true);
        });
    }

}