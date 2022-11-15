package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventPlayer extends JFrame implements ActionListener {

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 1000;

    private JButton addButton;
    private JButton saveButton;
    private JButton loadButton;

    private JPanel pinkPanel;
    private JPanel greenPanel;
    private JPanel displayPanel;

    private JLabel label;

    public EventPlayer() {
        super("Basketball Simulator");


        createTitle();
        // JPanel = a gui component that functions as a container to hold other components
        pinkPanel = new JPanel();
        pinkPanel.setBackground(Color.PINK);
        pinkPanel.setBounds(0,0,WIDTH,80);

        greenPanel = new JPanel(new GridLayout(1,3));
        greenPanel.setBackground(Color.LIGHT_GRAY);
        greenPanel.setBounds(0, 80, WIDTH, 40);

        displayPanel = new JPanel();
        displayPanel.setBackground(Color.WHITE);
        displayPanel.setBounds(20, 80 + 40, WIDTH - 40, HEIGHT - 320);

        addButton = new JButton("Add Player");
        addButton.addActionListener(this);

        saveButton = new JButton("Save Team");
        saveButton.addActionListener(this);

        loadButton = new JButton("Load Team");
        loadButton.addActionListener(this);

        add(greenPanel);
        add(pinkPanel);
        add(displayPanel);

        greenPanel.add(addButton);
        greenPanel.add(saveButton);
        greenPanel.add(loadButton);

        pinkPanel.add(label);
        initializePage();









    }

    private void initializePage() {
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

    }

    private void createTitle() {
        label = new JLabel();
        label.setText("BasketBall Team Simulator");
        label.setFont(new Font("Times New Roman", Font.BOLD,20));
        ImageIcon icon = new ImageIcon("./data/BasketBall.png");
        Image image = icon.getImage();
        Image scaledImage = image.getScaledInstance(60,60, Image.SCALE_SMOOTH);
        icon = new ImageIcon(scaledImage);
        label.setIcon(icon);
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


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            System.out.println("HI");
            // new AddPlayerWindow();
        }
    }
}
