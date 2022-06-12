package assignments.assignment4.frontend.anggota.ui;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.backend.buku.Buku;
import assignments.assignment4.backend.pengguna.Dosen;
import assignments.assignment4.backend.pengguna.Mahasiswa;
import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PeminjamanPanel extends SistakaPanel {
    private JPanel kosongan = new JPanel();
    private JPanel panelUntuk3 = new JPanel();
    private JLabel Label = new JLabel("Pinjam Buku");
    private JLabel bukuLabel = new JLabel("Buku");
    private JComboBox<String> bukuPilihan = new JComboBox<>();
    private JLabel tanggalPeminjamanLabel = new JLabel("Tanggal Peminjaman (DD/MM/YYYY)");
    private JTextField inputTanggalPeminjaman = new JTextField();
    private JButton pinjamButton = new JButton("Pinjam");
    private JButton kembaliButton = new JButton("Kembali");

    public PeminjamanPanel(HomeGUI main) {
        super(main);
        // TODO: Implementasikan hal-hal yang diperlukan
        setLayout(new FlowLayout(FlowLayout.CENTER));

        //panel kosongan untuk membantu elemen menjadi di tengah
        kosongan.setPreferredSize(new Dimension(500,225));
        add(kosongan);

        //panel untuk label utama, label buku, combobox buku
        panelUntuk3.setLayout(new BorderLayout(10,5));
        panelUntuk3.setPreferredSize(new Dimension(450,80));
        add(panelUntuk3);

        //label
        Label.setHorizontalAlignment(SwingConstants.CENTER);
        Label.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        panelUntuk3.add(Label, BorderLayout.NORTH);

        //label buku
        bukuLabel.setHorizontalAlignment(SwingConstants.CENTER);
        bukuLabel.setFont(new Font("Times New Roman", Font.PLAIN,12));
        panelUntuk3.add(bukuLabel, BorderLayout.CENTER);

        //panel untuk tanggal peminjaman (textfield dan label)
        JPanel tanggalPeminjamanPanel = new JPanel();
        tanggalPeminjamanPanel.setLayout(new BorderLayout(10,5));
        tanggalPeminjamanPanel.setPreferredSize(new Dimension(450,40));
        add(tanggalPeminjamanPanel);

        //label tanggal peminjaman
        tanggalPeminjamanLabel.setHorizontalAlignment(SwingConstants.CENTER);
        tanggalPeminjamanLabel.setFont(new Font("Times New Roman", Font.PLAIN,12));
        tanggalPeminjamanPanel.add(tanggalPeminjamanLabel, BorderLayout.NORTH);

        //label input peminjaman
        inputTanggalPeminjaman.setHorizontalAlignment(SwingConstants.CENTER);
        tanggalPeminjamanPanel.add(inputTanggalPeminjaman, BorderLayout.CENTER);

        //panel untuk tombol
        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelButtons.setPreferredSize(new Dimension(450,50));
        panelButtons.add(pinjamButton);
        panelButtons.add(kembaliButton);
        add(panelButtons);

        kembaliButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                main.setPanel("anggota");
            }
        });
    }

    @Override
    public void refresh() {
        // TODO: Implementasikan hal-hal yang diperlukan
        // Jika tidak diperlukan, Anda dapat mengabaikannya (kosongkan method ini)

        //buku combo box
        bukuPilihan.removeAllItems();
        for(int i=0;i<SistakaNG.getDaftarBuku().size();i++){
            bukuPilihan.addItem(SistakaNG.getDaftarBuku().get(i).toString());
        }
        panelUntuk3.add(bukuPilihan, BorderLayout.SOUTH);

        pinjamButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                //mengambil dan cek data buku
                String dataBuku;
                if(bukuPilihan.getSelectedItem()==null){dataBuku = "";}
                else{dataBuku = bukuPilihan.getSelectedItem().toString();}
                Buku buku = new Buku(); //hanya untuk inisialisasi, karena nanti dipastikan ada
                for(int i=0;i<SistakaNG.getDaftarBuku().size();i++){
                    if(dataBuku.equals(SistakaNG.getDaftarBuku().get(i).toString())){
                        buku = SistakaNG.getDaftarBuku().get(i);
                        break;
                    }
                }

                //mengambil dan cek data tanggal peminjaman
                String tanggalPeminjaman = inputTanggalPeminjaman.getText().toString();
                String[] arrOfTanggalLahir = tanggalPeminjaman.split("/");
                boolean formatTanggalBenar = true;
                if(tanggalPeminjaman.equals("")||inputTanggalPeminjaman.getText()==null){
                    formatTanggalBenar = false;
                    JOptionPane.showMessageDialog(panelUntuk3, "Silahkan masukkan tanggal lahir dalam format DD/MM/YYYY",
                "Warning", JOptionPane.WARNING_MESSAGE);
                }else{
                    // Cek apakah ada 3 input (untuk dd, mm, dan yyyy)
                    if (arrOfTanggalLahir.length != 3) {
                        formatTanggalBenar = false;
                        JOptionPane.showMessageDialog(panelUntuk3, "Tanggal yang dimasukkan harus dalam format DD/MM/YYYY!",
                    "Warning", JOptionPane.WARNING_MESSAGE);
                    }else{
                    // Cek apakah semuanya numerik
                        for (String s : arrOfTanggalLahir) {
                            if (!isNumeric(s)) {
                                formatTanggalBenar = false;
                                JOptionPane.showMessageDialog(panelUntuk3, "Tanggal yang dimasukkan harus dalam format DD/MM/YYYY!",
                            "Warning", JOptionPane.WARNING_MESSAGE);
                            }
                        }
                    }
                }
                
                String message;
                if(formatTanggalBenar){
                    if(SistakaNG.getPenggunaLoggedIn() instanceof Dosen){
                        message = ((Dosen) SistakaNG.getPenggunaLoggedIn()).pinjam(buku, tanggalPeminjaman);
                    }else{
                        message = ((Mahasiswa) SistakaNG.getPenggunaLoggedIn()).pinjam(buku, tanggalPeminjaman);
                    }
                
                    if(bukuPilihan.getSelectedItem()==null){
                        JOptionPane.showMessageDialog(panelUntuk3, "Silahkan memilih buku!",
                    "Warning", JOptionPane.WARNING_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(panelUntuk3, message, "Info", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                refresh();
            }
        });
    }
}
