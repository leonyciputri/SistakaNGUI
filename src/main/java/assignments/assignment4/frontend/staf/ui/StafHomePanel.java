package assignments.assignment4.frontend.staf.ui;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// TODO: Implementasikan hal-hal yang diperlukan
public class StafHomePanel extends SistakaPanel {
    private JLabel welcome = new JLabel();
    private JButton tambahMahasiswaButton = new JButton("Tambah Mahasiswa");
    private JButton tambahDosenButton = new JButton("Tambah Dosen");
    private JButton tambahKategoriButton = new JButton("Tambah Kategori");
    private JButton tambahBukuButton = new JButton("Tambah Buku");
    private JButton hapusBukuButton = new JButton("Hapus Buku");
    private JButton peringkatPertamaButton = new JButton("3 Peringkat Pertama");
    private JButton detailAnggotaButton = new JButton("Detail Anggota");
    private JButton daftarPeminjamBukuButton = new JButton("Daftar Peminjam Buku");
    private JButton logOutButton = new JButton("Logout");

    public StafHomePanel(HomeGUI main) {
        super(main);
        // TODO: Implementasikan hal-hal yang diperlukan
        setLayout(new GridLayout(10,1,10,10));

        //label welcome
        welcome.setHorizontalAlignment(SwingConstants.CENTER);
        welcome.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        add(welcome);

        //tambah mahasiswa button
        add(tambahMahasiswaButton);
        tambahMahasiswaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                main.setPanel("tambahMhs");
            }
        });

        //tambah dosen button
        add(tambahDosenButton);
        tambahDosenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                main.setPanel("tambahDosen");
            }
        });

        //tambah kategori button
        add(tambahKategoriButton);
        tambahKategoriButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                main.setPanel("tambahKategori");
            }
        });

        //tambah buku button
        add(tambahBukuButton);
        tambahBukuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                main.setPanel("tambahBuku");
            }
        });

        //hapus buku button
        add(hapusBukuButton);
        hapusBukuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                main.setPanel("hapusBuku");
            }
        });

        //3 peringkat pertama button
        add(peringkatPertamaButton);
        peringkatPertamaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                main.setPanel("peringkat");
            }
        });

        //detail anggota button
        add(detailAnggotaButton);
        detailAnggotaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                main.setPanel("detailAnggota");
            }
        });

        //daftar peminjam buku button
        add(daftarPeminjamBukuButton);
        daftarPeminjamBukuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                main.setPanel("daftarPeminjam");
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
