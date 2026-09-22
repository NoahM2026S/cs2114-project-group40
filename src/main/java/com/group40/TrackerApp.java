package com.group40;

import javax.swing.*;
import java.awt.*;
import com.formdev.flatlaf.FlatLightLaf;

/**
 * The Tracker app class that will
 * visually display the code and buttons
 * from the picker screen class.
 * 
 * @author Amelia Vasquez Rosario
 * @version September 22, 2026
 */

public class TrackerApp extends JFrame
{

    // fields

    private CardLayout cardLayout;
    private JPanel container;

    // constructor

    public TrackerApp()
    {
        setTitle("TODO");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Image icon = new ImageIcon(getClass().getResource("/icon.png")).getImage();
        setIconImage(icon);
        cardLayout = new CardLayout();
        container = new JPanel(cardLayout);

        // to do: create pickerscreen and add to container.
        PickerScreen pickerScreen = new PickerScreen(cardLayout, container);
        container.add(pickerScreen, "pickerScreen");

        add(container);
    }

    /**
     * The main method that runs the program.
     * @param args The command line arguments.
     */
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
