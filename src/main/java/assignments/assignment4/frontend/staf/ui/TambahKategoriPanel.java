package assignments.assignment4.frontend.staf.ui;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.backend.buku.Kategori;
import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// TODO: Implementasikan hal-hal yang diperlukan
public class TambahKategoriPanel extends SistakaPanel {
    private JPanel kosongan = new JPanel();
    private JPanel panelUntuk3 = new JPanel();
    private JLabel Label = new JLabel("Tambah Kategori");
    private JLabel namaLabel = new JLabel("Nama");
    private JTextField inputNama = new JTextField();
    private JLabel poinLabel = new JLabel("Poin");
    private JTextField inputPoin = new JTextField();
    private JButton tambahButton = new JButton("Tambah");
    private JButton kembaliButton = new JButton("Kembali");

    public TambahKategoriPanel(HomeGUI main) {
        super(main);
        // TODO: Implementasikan hal-hal yang diperlukan
        setLayout(new FlowLayout(FlowLayout.CENTER));;

        //panel kosongan untuk membantu elemen menjadi di tengah
        kosongan.setPreferredSize(new Dimension(500,200));
        add(kosongan);

        //panel untuk label utama, label nama kategori, input nama kategori
        panelUntuk3.setLayout(new BorderLayout(10,5));
        panelUntuk3.setPreferredSize(new Dimension(450,80));
        add(panelUntuk3);

        //label
        Label.setHorizontalAlignment(SwingConstants.CENTER);
        Label.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        panelUntuk3.add(Label, BorderLayout.NORTH);

        //label nama
        namaLabel.setHorizontalAlignment(SwingConstants.CENTER);
        namaLabel.setFont(new Font("Times New Roman", Font.PLAIN,12));
        panelUntuk3.add(namaLabel, BorderLayout.CENTER);

        //input nama textfield
        inputNama.setPreferredSize(new Dimension(450,20));
        inputNama.setHorizontalAlignment(SwingConstants.CENTER);
        panelUntuk3.add(inputNama, BorderLayout.SOUTH);

        //panel untuk poin (textfield dan label)
        JPanel poinPanel = new JPanel();
        poinPanel.setLayout(new BorderLayout(10,5));
        poinPanel.setPreferredSize(new Dimension(450,40));
        add(poinPanel);

        //label poin
        poinLabel.setHorizontalAlignment(SwingConstants.CENTER);
        poinLabel.setFont(new Font("Times New Roman", Font.PLAIN,12));
        poinPanel.add(poinLabel, BorderLayout.NORTH);

        //label input poin
        inputPoin.setHorizontalAlignment(SwingConstants.CENTER);
        poinPanel.add(inputPoin, BorderLayout.CENTER);

        //panel untuk tombol
        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelButtons.setPreferredSize(new Dimension(450,50));
        panelButtons.add(tambahButton);
        panelButtons.add(kembaliButton);
        add(panelButtons);

        kembaliButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                main.setPanel("staf");
            }
        });
    }

    @Override
    public void refresh() {
        // TODO: Implementasikan hal-hal yang diperlukan
        // Jika tidak diperlukan, Anda dapat mengabaikannya (kosongkan method ini)
        tambahButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String nama = inputNama.getText().toString();
                String poin = inputPoin.getText().toString();

                if(nama.equals("")||isNumeric(poin)==false||
                    inputNama.getText()==null||inputPoin.getText()==null){
                    JOptionPane.showMessageDialog(panelUntuk3, "Tidak dapat menambahkan kategori, silahkan periksa kembali input Anda",
                "Warning", JOptionPane.WARNING_MESSAGE);
                }else if(SistakaNG.findKategori(nama)==null){
                    Kategori kategori = SistakaNG.addKategori(nama, Integer.parseInt(poin));
                    JOptionPane.showMessageDialog(panelUntuk3, String.format("Kategori %s dengan poin %d berhasil ditambahkan", 
                        kategori.getNama(),kategori.getPoin()), "Success!", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(panelUntuk3, String.format("Kategori %s sudah pernah ditambahkan", nama),
                "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }
}
