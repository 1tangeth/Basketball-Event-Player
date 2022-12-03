package ui;

import model.Game;
import model.Team;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Add result pop up window, and display match results
public class ResultPopUpWindow extends JFrame implements ActionListener {
    private JLabel result;
    private JButton exit;
    private Game game;

    // MODIFIES: this
    // EFFECTS: set the JPanel, Jframe and the Match
    public ResultPopUpWindow(Game game) {
        super("Game Result");
        this.game = game;
        createComponents();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(600, 120));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new FlowLayout());
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // MODIFIES: this
    // EFFECTS: create buttons, and labels
    private void createComponents() {
        result = new JLabel(game.playMatch());
        exit = new JButton("ok");
        this.add(result);
        this.add(exit);
        exit.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: dispose JFrame if user press the "ok" button
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("ok")) {
            dispose();
        }
    }
}
