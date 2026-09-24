package com.group40;

import javax.swing.*;
import java.awt.*;

/**
 * The history screen class that will
 * visually display the session history list.
 * Each entry shows the summary of a submitted tracker.
 * 
 * @author Hasini Pottipati
 * @version September 23, 2026
 */

public class HistoryScreen extends JPanel
{

    private DefaultListModel<String> listModel;

    public HistoryScreen(CardLayout cardLayout, JPanel container)
    {
        setLayout(new BorderLayout());
        setBackground(new Color(0xFBF1EC));
        setBorder(BorderFactory.createEmptyBorder(16, 30, 20, 30));

        // top bar: back button
        JButton backButton = createStyledButton("< Back", new Color(0xF6DED0), new Color(0x4A2E3B), null);
        backButton.addActionListener(e -> cardLayout.show(container, "pickerScreen"));

        JButton clearHistoryButton = createStyledButton("Clear History", new Color(0xF3C6BE), new Color(0x6B2E28), null);
        clearHistoryButton.addActionListener(e -> {
            SessionHistory.clearAll();
            refresh();
        });

        JPanel topBar = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        topBar.setOpaque(false);
        topBar.add(backButton);
        topBar.add(clearHistoryButton);
        add(topBar, BorderLayout.NORTH);

        // center: title + list
        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new BorderLayout(0, 12));

        JLabel titleLabel = new JLabel("Session History");
        titleLabel.setFont(new Font("Quicksand", Font.BOLD, 22));
        titleLabel.setForeground(new Color(0x4A2E3B));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        centerPanel.add(titleLabel, BorderLayout.NORTH);

        listModel = new DefaultListModel<>();
        JList<String> historyList = new JList<>(listModel);
        historyList.setFont(new Font("Quicksand", Font.PLAIN, 14));
        historyList.setForeground(new Color(0x4A2E3B));
        historyList.setBackground(new Color(0xFFFDFB));
        historyList.setFixedCellHeight(34);

        JScrollPane scrollPane = new JScrollPane(historyList);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(0xE8D9CE), 1, true));
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);

        addComponentListener(new java.awt.event.ComponentAdapter()
        {
            @Override
            public void componentShown(java.awt.event.ComponentEvent e)
            {
                refresh();
            }
        });
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

    /**
     * Reloads the list with the current session history.
     */
    public void refresh()
    {
        listModel.clear();

        for (Tracker tracker : SessionHistory.getEntries())
        {
            listModel.addElement(tracker.getSummary());
        }
    }

}