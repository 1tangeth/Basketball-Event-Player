package ui;

import model.Player;
import model.Team;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class AddPlayerWindow extends JFrame implements  ActionListener {
    private JLabel label;
    private JLabel labelRating;

    private JTextField field;
    private JButton addBtn;
    private JButton randomBtn;

    private Team team;
    private int rating;

    private JPanel displayPanel;
    private JFrame frame;

    //FIX LENGTH
    public AddPlayerWindow(Team team, JPanel displayPanel, JFrame frame) {
        super("Add Player");
        this.displayPanel = displayPanel;
        this.team = team;
        this.frame = frame;

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(600, 120));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new FlowLayout());

        createComponents();
        addComponents();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    private void createComponents() {
        addBtn = new JButton("Add");
        addBtn.setActionCommand("AddPlayer");
        addBtn.addActionListener(this);

        randomBtn = new JButton("Random Rating");
        randomBtn.setActionCommand("RandomizeRating");
        randomBtn.addActionListener(this);

        label = new JLabel("Player Name:");
        labelRating = new JLabel("");

        field = new JTextField(5);
    }

    private void addComponents() {
        add(label);
        add(field);
        add(randomBtn);
        add(labelRating);
        add(addBtn);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("AddPlayer")) {
            this.team.addPlayer(new Player(field.getText(), this.rating));
            displayPanel.removeAll();
            displayPlayers();
            frame.revalidate();
            frame.repaint();
            // TODO: POP UP WINDOW, MAKE USER PRESS RANDOM RATING BEFORE PROCEEDING.
            dispose();

        } else if (e.getActionCommand().equals("RandomizeRating")) {
            Random random = new Random();
            int randomRating = random.nextInt(100) + 1;
            this.rating = randomRating;
            labelRating.setText(Integer.toString(this.rating));
        }
    }

    private void displayPlayers() {
        for (int i = 0; i < this.team.getPlayers().size(); i++) {
            JLabel num = new JLabel(Integer.toString(i + 1));
            num.setHorizontalAlignment(0);
            JLabel name = new JLabel(this.team.getPlayers().get(i).getPlayerName());
            name.setHorizontalAlignment(0);
            JLabel rating = new JLabel(Integer.toString(this.team.getPlayers().get(i).getPlayerRating()));
            rating.setHorizontalAlignment(0);
            JPanel row = new JPanel(new GridLayout(1,3));
            row.setBackground(Color.LIGHT_GRAY);
            row.add(num);
            row.add(name);
            row.add(rating);
            displayPanel.add(row);
        }
    }

}
