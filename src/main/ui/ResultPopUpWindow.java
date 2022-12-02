package ui;

import model.Game;
import model.Team;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResultPopUpWindow extends JFrame implements ActionListener {
    private JLabel result;
    private JButton exit;
    private Game game;

    // TODO: Require Modifies Effects
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

    private void createComponents() {
        result = new JLabel(game.playMatch());
        exit = new JButton("ok");
        this.add(result);
        this.add(exit);
        exit.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("ok")) {
            dispose();
        }
    }
}
