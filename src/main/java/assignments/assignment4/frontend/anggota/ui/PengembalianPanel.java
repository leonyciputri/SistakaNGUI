package assignments.assignment4.frontend.anggota.ui;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.backend.buku.Buku;
import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;
import assignments.assignment4.backend.pengguna.Anggota;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// TODO: Implementasikan hal-hal yang diperlukan
public class PengembalianPanel extends SistakaPanel {
    private JPanel kosongan = new JPanel();
    private JPanel panelUntuk3 = new JPanel();
    private JLabel Label = new JLabel("Pengembalian Buku");
    private JLabel bukuLabel = new JLabel("Buku");
    private JComboBox<String> bukuPilihan = new JComboBox<>();
    private JLabel tanggalPengembalianLabel = new JLabel("Tanggal Pengembalian (DD/MM/YYYY)");
    private JTextField inputTanggalPengembalian = new JTextField();
    private JButton kembalikanButton = new JButton("Kembalikan");
    private JButton kembaliButton = new JButton("Kembali");

    public PengembalianPanel(HomeGUI main) {
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

        //panel untuk tanggal Pengembalian (textfield dan label)
        JPanel tanggalPengembalianPanel = new JPanel();
        tanggalPengembalianPanel.setLayout(new BorderLayout(10,5));
        tanggalPengembalianPanel.setPreferredSize(new Dimension(450,40));
        add(tanggalPengembalianPanel);

        //label tanggal pengembalian
        tanggalPengembalianLabel.setHorizontalAlignment(SwingConstants.CENTER);
        tanggalPengembalianLabel.setFont(new Font("Times New Roman", Font.PLAIN,12));
        tanggalPengembalianPanel.add(tanggalPengembalianLabel, BorderLayout.NORTH);

        //label input tanggal pengembalian
        inputTanggalPengembalian.setHorizontalAlignment(SwingConstants.CENTER);
        tanggalPengembalianPanel.add(inputTanggalPengembalian, BorderLayout.CENTER);

        //panel untuk tombol
        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelButtons.setPreferredSize(new Dimension(450,50));
        panelButtons.add(kembalikanButton);
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

        kembalikanButton.addActionListener(new ActionListener() {
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

                //mengambil dan cek data tanggal pengembalian
                String tanggalPengembalian = inputTanggalPengembalian.getText().toString();
                String[] arrOfTanggalLahir = tanggalPengembalian.split("/");
                boolean formatTanggalBenar = true;
                if(tanggalPengembalian.equals("")||inputTanggalPengembalian.getText()==null){
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
                    message = ((Anggota) SistakaNG.getPenggunaLoggedIn()).kembali(buku, tanggalPengembalian);
                
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
