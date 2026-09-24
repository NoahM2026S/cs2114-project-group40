package com.group40;

import javax.swing.*;
import java.awt.*;

public class HistoryScreen extends JPanel {
    public HistoryScreen() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(24, 40, 32, 40));
        setBackground(new Color(0xF7F5F0));

        // icon
        ImageIcon rawIcon = new ImageIcon(getClass().getResource("/icon.png"));
        Image scaledImage = rawIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        JLabel iconLabel = new JLabel(new ImageIcon(scaledImage));
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // title
        JLabel titleLabel = new JLabel("History");
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 28f));
        titleLabel.setForeground(new Color(0x2B2233));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // subtitle
        JLabel subtitleLabel = new JLabel("Your previous tracker results");
        subtitleLabel.setFont(subtitleLabel.getFont().deriveFont(13f));
        subtitleLabel.setForeground(new Color(0x7A7365));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(iconLabel);
        add(Box.createVerticalStrut(14));
        add(titleLabel);
        add(Box.createVerticalStrut(4));
        add(subtitleLabel);
    }
}
