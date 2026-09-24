package com.group40;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import com.formdev.flatlaf.FlatLightLaf;

public class TrackerApp extends JFrame
{

    // fields
    private CardLayout cardLayout;
    private JPanel container;

    // constructor

    public TrackerApp()
    {
        setTitle("Shepherd");
        setSize(600, 400);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        container = new JPanel(cardLayout);

        PickerScreen pickerScreen = new PickerScreen(cardLayout, container);
        container.add(pickerScreen, "pickerScreen");

        WageTrackerForm wageForm = new WageTrackerForm();
        container.add(wageForm, "wageForm");
        
        CostsTrackerForm costsForm = new CostsTrackerForm();
        container.add(costsForm, "costsForm");
        
        add(container);
    }

    public static void main(String[] args)
    {

        try
        {
            Font quicksandBold = Font.createFont(Font.TRUETYPE_FONT,
                TrackerApp.class.getResourceAsStream("/Quicksand-Bold.ttf"));
                    GraphicsEnvironment.getLocalGraphicsEnvironment()
                        .registerFont(quicksandBold);
        }
        catch (FontFormatException | IOException e) 
        {
            e.printStackTrace();
        }

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
