package ui;

import model.Team;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class EventPlayer extends JFrame implements ActionListener, KeyListener {

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 1000;

    private JButton addButton1;
    private JButton addButton2;

    private JButton saveButton;
    private JButton loadButton;

    private JPanel pinkPanel;
    private JPanel grayPanel;
    private JPanel teamOneCol;
    private JPanel teamTwoCol;
    private JPanel teamOnePanel;
    private JPanel teamTwoPanel;

    private JLabel title;

    private Team team1;
    private Team team2;

    private static final String JSON_STORE_ONE = "./data/team1.json";
    private static final String JSON_STORE_TWO = "./data/team2.json";
    private JsonWriter jsonWriterOne;
    private JsonReader jsonReaderOne;
    private JsonWriter jsonWriterTwo;
    private JsonReader jsonReaderTwo;

    public EventPlayer() {
        super("Basketball Simulator");
        //TODO: add team name manually
        jsonWriterOne = new JsonWriter(JSON_STORE_ONE);
        jsonWriterTwo = new JsonWriter(JSON_STORE_TWO);
        jsonReaderOne = new JsonReader(JSON_STORE_ONE);
        jsonReaderTwo = new JsonReader(JSON_STORE_TWO);
        team1 = new Team("team 1");
        team2 = new Team("team 2");
        createTitle();
        createButtons();
        createPanel();
        addComponents();
        initializeFrame();
    }

    private void addComponents() {
        add(grayPanel);
        add(pinkPanel);
        add(teamOneCol);
        add(teamTwoCol);
        add(teamOnePanel);
        add(teamTwoPanel);
        grayPanel.add(addButton1);
        grayPanel.add(addButton2);
        grayPanel.add(saveButton);
        grayPanel.add(loadButton);
        pinkPanel.add(title);
    }

    private void createButtons() {
        addButton1 = new JButton("Add Player To T1 (OR Press '1')");
        addButton1.addActionListener(this);

        addButton2 = new JButton("Add Player To T2 (OR Press '2')");
        addButton2.addActionListener(this);

        saveButton = new JButton("Save Teams");
        saveButton.addActionListener(this);

        loadButton = new JButton("Load Teams");
        loadButton.addActionListener(this);
    }

    private void createPanel() {
        // JPanel = a gui component that functions as a container to hold other components
        pinkPanel = new JPanel();
        pinkPanel.setBackground(Color.PINK);
        pinkPanel.setBounds(0,0,WIDTH,80);
        grayPanel = new JPanel(new GridLayout(1,4));
        grayPanel.setBackground(Color.LIGHT_GRAY);
        grayPanel.setBounds(0, 80, WIDTH, 40);
        displayTeamOneCol();
        displayTeamTwoCol();

        teamOnePanel = new JPanel(new GridLayout(0,1));
        teamOnePanel.setBackground(Color.LIGHT_GRAY);
        teamOnePanel.setBounds(20, 80 + 40 + 20 + 30, WIDTH - 40, 300);

        teamTwoPanel = new JPanel(new GridLayout(0,1));
        teamTwoPanel.setBackground(Color.LIGHT_GRAY);
        teamTwoPanel.setBounds(20, 80 + 40 + 20 + 300 + 20 + 30, WIDTH - 40, 300);
    }

    private void displayTeamOneCol() {
        teamOneCol = new JPanel(new GridLayout(1,3));
        teamOneCol.setBackground(Color.PINK);
        teamOneCol.setBounds(20, 80 + 40 + 20, WIDTH - 40, 30);
        JLabel num = new JLabel("Player #");
        num.setHorizontalAlignment(JLabel.CENTER);
        JLabel name = new JLabel("Player Name");
        name.setHorizontalAlignment(JLabel.CENTER);
        JLabel rating = new JLabel("Player Rating");
        rating.setHorizontalAlignment(JLabel.CENTER);
        teamOneCol.add(num);
        teamOneCol.add(name);
        teamOneCol.add(rating);
    }

    private void displayTeamTwoCol() {
        teamTwoCol = new JPanel(new GridLayout(1,2));
        teamTwoCol.setBackground(Color.PINK);
        teamTwoCol.setBounds(20, 80 + 40 + 20 + 300 + 20, WIDTH - 40, 30);
        JLabel num2 = new JLabel("Player #");
        num2.setHorizontalAlignment(JLabel.CENTER);
        JLabel name2 = new JLabel("Player Name");
        name2.setHorizontalAlignment(JLabel.CENTER);
        JLabel rating2 = new JLabel("Player Rating");
        rating2.setHorizontalAlignment(JLabel.CENTER);
        teamTwoCol.add(num2);
        teamTwoCol.add(name2);
        teamTwoCol.add(rating2);
    }

    public void initializeFrame() {
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.LIGHT_GRAY);
        setSize(WIDTH,HEIGHT);
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(20,20,20,20));
        setVisible(true);
        setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 5,
                Toolkit.getDefaultToolkit().getScreenSize().height / 9);
        setResizable(false);
        setLocationRelativeTo(null);
        addKeyListener(this);
        setFocusable(true);

    }

    private void createTitle() {
        title = new JLabel();
        title.setText("BasketBall Team Simulator");
        title.setFont(new Font("Times New Roman", Font.BOLD,20));
        ImageIcon icon = new ImageIcon("./data/BasketBall.png");
        Image image = icon.getImage();
        Image scaledImage = image.getScaledInstance(60,60, Image.SCALE_SMOOTH);
        icon = new ImageIcon(scaledImage);
        title.setIcon(icon);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton1) {
            new AddPlayerWindow(team1, teamOnePanel, this);
        } else if (e.getSource() == addButton2) {
            new AddPlayerWindow(team2, teamTwoPanel,this);
        } else if (e.getSource() == saveButton) {
            saveTeam();
        } else if (e.getSource() == loadButton) {
            loadTeam();
        }
    }

    private void loadTeam() {
        try {
            team1 = jsonReaderOne.read();
            System.out.println("Loaded " + team1.getTeamName() + " from " + JSON_STORE_ONE);
            team2 = jsonReaderTwo.read();
            System.out.println("Loaded " + team2.getTeamName() + " from " + JSON_STORE_TWO);
            updateTeamDisplay(team1, teamOnePanel);
            updateTeamDisplay(team2, teamTwoPanel);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_ONE + "and" + JSON_STORE_TWO);
        }
    }

    private void updateTeamDisplay(Team t, JPanel p) {
        p.removeAll();
        for (int i = 0; i < t.getPlayers().size(); i++) {
            JLabel num = new JLabel(Integer.toString(i + 1));
            num.setHorizontalAlignment(0);
            JLabel name = new JLabel(t.getPlayers().get(i).getPlayerName());
            name.setHorizontalAlignment(0);
            JLabel rating = new JLabel(Integer.toString(t.getPlayers().get(i).getPlayerRating()));
            rating.setHorizontalAlignment(0);
            JPanel row = new JPanel(new GridLayout(1,3));
            row.setBackground(Color.LIGHT_GRAY);
            row.add(num);
            row.add(name);
            row.add(rating);
            p.add(row);
        }
        revalidate();
        repaint();



    }

    private void saveTeam() {
        try {
            jsonWriterOne.open();
            jsonWriterOne.write(team1);
            jsonWriterOne.close();
            System.out.println("Saved " + team1.getTeamName() + " to " + JSON_STORE_ONE);
            jsonWriterTwo.open();
            jsonWriterTwo.write(team2);
            jsonWriterTwo.close();
            System.out.println("Saved " + team2.getTeamName() + " to " + JSON_STORE_TWO);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_ONE);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_1) {
            new AddPlayerWindow(team1, teamOnePanel, this);
        }
        if (e.getKeyCode() == KeyEvent.VK_2) {
            new AddPlayerWindow(team2, teamTwoPanel,this);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public static void main(String[] args) {
        new EventPlayer();
//        try {
//
//            new EventAPP();
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to run application: file not found");
//        }
    }
}
