package com.group40;

import javax.swing.*;
import java.awt.*;

/**
 * The Picker screen class that will
 * determine the buttons that will click
 * into the tracker forms.
 * 
 * @author Amelia Vasquez Rosario
 * @version September 22, 2026
 */

public class PickerScreen extends JPanel {

    public PickerScreen(CardLayout cardLayout, JPanel container)
    {
        setLayout(new GridLayout(2, 1));

        JButton wageButton = new JButton("Wage Tracker");
        wageButton.addActionListener(e -> cardLayout.show(container, "wageForm"));

        JButton costsButton = new JButton("Costs Tracker");
        costsButton.addActionListener(e -> cardLayout.show(container, "costsForm"));

        add(wageButton);
        add(costsButton);
    }

}