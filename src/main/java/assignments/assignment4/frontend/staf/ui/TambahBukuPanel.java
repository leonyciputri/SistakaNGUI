package assignments.assignment4.frontend.staf.ui;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.backend.buku.Buku;
import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// TODO: Implementasikan hal-hal yang diperlukan
public class TambahBukuPanel extends SistakaPanel {
    private JPanel kosongan = new JPanel();
    private JPanel kategoriPanel = new JPanel();
    private JPanel panelUntuk3 = new JPanel();
    private JLabel Label = new JLabel("Tambah Buku");
    private JLabel judulLabel = new JLabel("Judul");
    private JTextField inputJudul = new JTextField();
    private JLabel penulisLabel = new JLabel("Penulis");
    private JTextField inputPenulis = new JTextField();
    private JLabel penerbitLabel = new JLabel("Penerbit");
    private JTextField inputPenerbit = new JTextField();
    private JLabel kategoriLabel = new JLabel("Kategori");
    private JComboBox<String> kategoriComboBox = new JComboBox<>();
    private JLabel stokLabel = new JLabel("Stok");
    private JTextField inputStok = new JTextField();
    private JButton tambahButton = new JButton("Tambah");
    private JButton kembaliButton = new JButton("Kembali");

    public TambahBukuPanel(HomeGUI main) {
        super(main);
        // TODO: Implementasikan hal-hal yang diperlukan
        setLayout(new FlowLayout(FlowLayout.CENTER));

        //panel kosongan untuk membantu elemen menjadi di tengah
        kosongan.setPreferredSize(new Dimension(500,150));
        add(kosongan);

        //panel untuk label utama, label judu;, input judul
        panelUntuk3.setLayout(new BorderLayout(10,5));
        panelUntuk3.setPreferredSize(new Dimension(450,80));
        add(panelUntuk3);

        //welcome label
        Label.setHorizontalAlignment(SwingConstants.CENTER);
        Label.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        panelUntuk3.add(Label, BorderLayout.NORTH);

        //label judul
        judulLabel.setHorizontalAlignment(SwingConstants.CENTER);
        judulLabel.setFont(new Font("Times New Roman", Font.PLAIN,12));
        panelUntuk3.add(judulLabel, BorderLayout.CENTER);

        //input judul textfield
        inputJudul.setPreferredSize(new Dimension(450,20));
        inputJudul.setHorizontalAlignment(SwingConstants.CENTER);
        panelUntuk3.add(inputJudul, BorderLayout.SOUTH);

        //panel untuk penulis(textfield dan label)
        JPanel penulisPanel = new JPanel();
        penulisPanel.setLayout(new BorderLayout(10,5));
        penulisPanel.setPreferredSize(new Dimension(450,40));
        add(penulisPanel);

        //label penulis
        penulisLabel.setHorizontalAlignment(SwingConstants.CENTER);
        penulisLabel.setFont(new Font("Times New Roman", Font.PLAIN,12));
        penulisPanel.add(penulisLabel, BorderLayout.NORTH);

        //label input penulis
        inputPenulis.setHorizontalAlignment(SwingConstants.CENTER);
        penulisPanel.add(inputPenulis, BorderLayout.CENTER);

        //panel untuk penerbit(textfield dan label)
        JPanel penerbitPanel = new JPanel();
        penerbitPanel.setLayout(new BorderLayout(10,5));
        penerbitPanel.setPreferredSize(new Dimension(450,40));
        add(penerbitPanel);

        //label penerbit
        penerbitLabel.setHorizontalAlignment(SwingConstants.CENTER);
        penerbitLabel.setFont(new Font("Times New Roman", Font.PLAIN,12));
        penerbitPanel.add(penerbitLabel, BorderLayout.NORTH);

        //label input penerbit
        inputPenerbit.setHorizontalAlignment(SwingConstants.CENTER);
        penerbitPanel.add(inputPenerbit, BorderLayout.CENTER);

        //panel untuk Kategori(label dan textfield)
        kategoriPanel.setLayout(new BorderLayout(10,5));
        kategoriPanel.setPreferredSize(new Dimension(100,50));
        add(kategoriPanel);

        //label kategori
        kategoriLabel.setHorizontalAlignment(SwingConstants.CENTER);
        kategoriLabel.setFont(new Font("Times New Roman", Font.PLAIN,12));
        kategoriPanel.add(kategoriLabel, BorderLayout.NORTH);

        //panel untuk stok (text field dan label)
        JPanel stokPanel = new JPanel();
        stokPanel.setLayout(new BorderLayout(10,5));
        stokPanel.setPreferredSize(new Dimension(450,40));
        add(stokPanel);

        //label stok
        stokLabel.setHorizontalAlignment(SwingConstants.CENTER);
        stokLabel.setFont(new Font("Times New Roman", Font.PLAIN,12));
        stokPanel.add(stokLabel, BorderLayout.NORTH);

        //textfield stok
        inputStok.setHorizontalAlignment(SwingConstants.CENTER);
        stokPanel.add(inputStok, BorderLayout.CENTER);

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
        //kategori combo box
        kategoriComboBox.removeAllItems();
        for(int i=0;i<SistakaNG.getDaftarKategori().size();i++){
            kategoriComboBox.addItem(SistakaNG.getDaftarKategori().get(i).getNama());
        }
        kategoriPanel.add(kategoriComboBox, BorderLayout.SOUTH);

        tambahButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String judul = inputJudul.getText().toString();
                String penulis = inputPenulis.getText().toString();
                String penerbit= inputPenerbit.getText().toString();
                String kategori;
                if(kategoriComboBox.getSelectedItem()==null){kategori = "";}
                else{ kategori = kategoriComboBox.getSelectedItem().toString();}
                String stok = inputStok.getText().toString();

                if(judul.equals("")||inputJudul.getText()==null||
                penulis.equals("")||inputPenulis.getText()==null||
                penerbit.equals("")||inputPenerbit.getText()==null||
                stok.equals("")||inputStok.getText()==null||
                isNumeric(stok)==false){
                    JOptionPane.showMessageDialog(panelUntuk3, "Tidak dapat menambahkan buku, silahkan periksa kembali input Anda",
                "Warning", JOptionPane.WARNING_MESSAGE);
                }else if(Integer.parseInt(stok)==0){
                    JOptionPane.showMessageDialog(panelUntuk3, "Stok harus lebih dari 0!",
                "Warning", JOptionPane.WARNING_MESSAGE);
                }else if(SistakaNG.findBuku(judul, penulis)!=null){
                    JOptionPane.showMessageDialog(panelUntuk3, String.format("Buku %s oleh %s sudah pernah ditambahkan", judul, penulis),
                "Warning", JOptionPane.WARNING_MESSAGE);
                }else{
                    Buku buku = SistakaNG.addBuku(judul, penulis, penerbit, kategori, Integer.parseInt(stok));
                    JOptionPane.showMessageDialog(panelUntuk3, String.format("Buku %s oleh %s berhasil ditambahkan!", 
                        buku.getJudul(), buku.getPenulis()),"Success!", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }
}
