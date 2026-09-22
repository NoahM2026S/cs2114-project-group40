package com.group40;

import javax.swing.*;
import java.awt.*;

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