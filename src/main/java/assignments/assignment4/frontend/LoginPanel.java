package assignments.assignment4.frontend;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.backend.pengguna.Pengguna;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// TODO: Implementasikan hal-hal yang diperlukan
public class LoginPanel extends SistakaPanel {
    private JLabel label = new JLabel("Masukkan ID Anda untuk login ke sistem");
    private JTextField input = new JTextField();
    private JButton loginButton = new JButton("Login");

    public LoginPanel(HomeGUI main){
        super(main);
        // TODO: Implementasikan hal-hal yang diperlukan
        setLayout(new GridBagLayout());

        JPanel panel = new JPanel();
        add(new JLabel());
        add(new JLabel());
        add(new JLabel());
        add(new JLabel());
        add(panel);  //cell paling tengah adalah cell ke-5 sehingga sbelumnya ditambah component kosong
        panel.setLayout(new BorderLayout(10,5));

        //label
        label.setPreferredSize(new Dimension(450,20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label, BorderLayout.NORTH);

        //input textfield
        input.setPreferredSize(new Dimension(450,20));
        input.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(input, BorderLayout.CENTER);

        //loginButton
        JPanel panel2 = new JPanel();
        panel2.add(loginButton);
        panel.add(panel2, BorderLayout.SOUTH);
        loginButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String ID = input.getText();
                Pengguna user = SistakaNG.handleLogin(ID);
                SistakaNG.setPenggunaLoggedIn(user);
                
                if(ID.equals("")){
                    JOptionPane.showMessageDialog(panel, "Harap masukkan ID Anda pada kotak di atas!",
                "Warning", JOptionPane.WARNING_MESSAGE);
                }else if(user==null){
                    JOptionPane.showMessageDialog(panel, String.format("Pengguna dengan ID %s tidak ditemukan", ID),
                "Warning", JOptionPane.WARNING_MESSAGE);
                }else{
                    if(user.getTipe().equals("staf")){
                        main.setPanel("staf");
                    }else{
                        main.setPanel("anggota");
                    }
                }
            }
        });
    }

    @Override
    public void refresh() {
        // ignored
    }
}
