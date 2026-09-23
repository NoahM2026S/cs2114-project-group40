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