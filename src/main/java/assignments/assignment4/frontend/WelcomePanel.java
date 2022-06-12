package assignments.assignment4.frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WelcomePanel extends SistakaPanel {
    private JLabel welcome = new JLabel("Welcome to SistakaNG!");
    private JButton loginButton = new JButton("Login");
    private JButton exitButton = new JButton("Exit");

    public WelcomePanel(HomeGUI homeGUI) {
        super(homeGUI);
        // TODO: Implementasikan hal-hal yang diperlukan
        setLayout(new FlowLayout(FlowLayout.CENTER));

        //panel untuk text
        JPanel panel = new JPanel();
        add(panel);
        panel.setLayout(new BorderLayout(10,5));
        panel.setPreferredSize(new Dimension(500,450));

        welcome.setHorizontalAlignment(SwingConstants.CENTER);
        welcome.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        panel.add(welcome);

        //panel untuk exit dan login
        JPanel panelButton = new JPanel();
        add(panelButton);
        panelButton.setLayout(new BorderLayout(10,20));

        loginButton.setPreferredSize(new Dimension(350,50));
        exitButton.setPreferredSize(new Dimension(350,50));
        panelButton.add(loginButton, BorderLayout.NORTH);
        panelButton.add(exitButton, BorderLayout.CENTER);

        loginButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                homeGUI.setPanel("login"); //masuk halaman login
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.exit(0); //GUI tertutup
            }
        });
    }

    @Override
    public void refresh() {
        // ignored
    }
}
