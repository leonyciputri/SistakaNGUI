package assignments.assignment4.frontend.anggota.ui;

import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;
import assignments.assignment4.backend.SistakaNG;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// TODO: Implementasikan hal-hal yang diperlukan
public class AnggotaHomePanel extends SistakaPanel {
    private JLabel welcome = new JLabel();
    private JButton peminjamanButton = new JButton("Peminjaman");
    private JButton pengembalianButton = new JButton("Pengembalian");
    private JButton pembayaranDendaButton = new JButton("Pembayaran Denda");
    private JButton detailAnggotaButton = new JButton("Detail Anggota");
    private JButton logOutButton = new JButton("Logout");

    public AnggotaHomePanel(HomeGUI main) {
        super(main);
        // TODO: Implementasikan hal-hal yang diperlukan
        setLayout(new GridLayout(10,1,10,10));

        //label kosong agar grid terpasang di tengah
        add(new JLabel());
        add(new JLabel());

        //label welcome
        welcome.setHorizontalAlignment(SwingConstants.CENTER);
        welcome.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        add(welcome);

        //peminjaman button
        add(peminjamanButton);
        peminjamanButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                main.setPanel("peminjaman");
            }
        });

        //pengembalian button
        add(pengembalianButton);
        pengembalianButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                main.setPanel("pengembalian");
            }
        });

        //pembayaran denda button
        add(pembayaranDendaButton);
        pembayaranDendaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                main.setPanel("pembayaran");
            }
        });

        //detail anggota button
        add(detailAnggotaButton);
        detailAnggotaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                main.setPanel("detailUser");
            }
        });

        //log out button
        add(logOutButton);
        logOutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                main.setPanel("welcome");
            }
        });
    }

    @Override
    public void refresh() {
        // TODO: Implementasikan hal-hal yang diperlukan
        // Jika tidak diperlukan, Anda dapat mengabaikannya (kosongkan method ini)
        String nama;
        if(SistakaNG.getPenggunaLoggedIn()!=null){
            nama = SistakaNG.getPenggunaLoggedIn().getNama();
        }else{
            nama="";
        }
        welcome.setText(String.format("Selamat Datang Kembali %s!", nama));
    }

}
