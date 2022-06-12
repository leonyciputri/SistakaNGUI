package assignments.assignment4.frontend.staf.ui;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.backend.pengguna.Mahasiswa;
import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// TODO: Implementasikan hal-hal yang diperlukan
public class TambahMahasiswaPanel extends SistakaPanel {
    private JPanel kosongan = new JPanel();
    private JPanel panelUntuk3 = new JPanel();
    private JLabel Label = new JLabel("Tambah Mahasiswa");
    private JLabel namaLabel = new JLabel("Nama");
    private JTextField inputNama = new JTextField();
    private JLabel tanggalLahirLabel = new JLabel("Tanggal Lahir (DD/MM/YYYY)");
    private JTextField inputTanggalLahir = new JTextField();
    private JLabel programStudiLabel = new JLabel("Program Studi");
    private JComboBox<String> programStudiComboBox = new JComboBox<String>(new String[]{"SSI", "SIK", "MTI", "MIK", "DIK"});
    private JLabel angkatanLabel = new JLabel("Angkatan");
    private JTextField inputAngkatan = new JTextField();
    private JButton tambahButton = new JButton("Tambah");
    private JButton kembaliButton = new JButton("Kembali");

    public TambahMahasiswaPanel(HomeGUI main) {
        super(main);
        // TODO: Implementasikan hal-hal yang diperlukan
        setLayout(new FlowLayout(FlowLayout.CENTER));

        //panel kosongan untuk membantu elemen menjadi di tengah
        kosongan.setPreferredSize(new Dimension(500,150));
        add(kosongan);

        //panel untuk label utama, label nama, input nama
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

        //panel untuk tanggal lahir (textfield dan label)
        JPanel tanggalLahirPanel = new JPanel();
        tanggalLahirPanel.setLayout(new BorderLayout(10,5));
        tanggalLahirPanel.setPreferredSize(new Dimension(450,40));
        add(tanggalLahirPanel);

        //label tanggal lahir
        tanggalLahirLabel.setHorizontalAlignment(SwingConstants.CENTER);
        tanggalLahirLabel.setFont(new Font("Times New Roman", Font.PLAIN,12));
        tanggalLahirPanel.add(tanggalLahirLabel, BorderLayout.NORTH);

        //input tanggal lahir
        inputTanggalLahir.setHorizontalAlignment(SwingConstants.CENTER);
        tanggalLahirPanel.add(inputTanggalLahir, BorderLayout.CENTER);

        //panel untuk program studi (label dan textfield)
        JPanel programStudiPanel = new JPanel();
        programStudiPanel.setLayout(new BorderLayout(10,5));
        programStudiPanel.setPreferredSize(new Dimension(100,50));
        add(programStudiPanel);

        //label program studi
        programStudiLabel.setHorizontalAlignment(SwingConstants.CENTER);
        programStudiLabel.setFont(new Font("Times New Roman", Font.PLAIN,12));
        programStudiPanel.add(programStudiLabel, BorderLayout.NORTH);

        //program studi combo box
        programStudiPanel.add(programStudiComboBox, BorderLayout.SOUTH);

        //panel untuk angkatan (text field dan label)
        JPanel angkatanPanel = new JPanel();
        angkatanPanel.setLayout(new BorderLayout(10,5));
        angkatanPanel.setPreferredSize(new Dimension(450,40));
        add(angkatanPanel);

        //label angkatan
        angkatanLabel.setHorizontalAlignment(SwingConstants.CENTER);
        angkatanLabel.setFont(new Font("Times New Roman", Font.PLAIN,12));
        angkatanPanel.add(angkatanLabel, BorderLayout.NORTH);

        //textfield angkatan
        inputAngkatan.setHorizontalAlignment(SwingConstants.CENTER);
        angkatanPanel.add(inputAngkatan, BorderLayout.CENTER);

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
                String tanggalLahir = inputTanggalLahir.getText().toString();
                String programStudi;
                //untuk menghindari logic error, jadi pake condition
                if(programStudiComboBox.getSelectedItem()==null){programStudi="";}
                else{programStudi = programStudiComboBox.getSelectedItem().toString();}
                String angkatan = inputAngkatan.getText().toString();

                Mahasiswa mahasiswa = SistakaNG.addMahasiswa(nama, tanggalLahir, programStudi, angkatan);
                if(mahasiswa==null||nama.equals("")||programStudiComboBox.getSelectedItem()==null){
                    JOptionPane.showMessageDialog(panelUntuk3, "Tidak dapat menambahkan mahasiswa, silahkan periksa kembali input Anda",
                "Warning", JOptionPane.WARNING_MESSAGE);
                }else{
                    if(SistakaNG.findAnggota(mahasiswa.getId())==null){
                        JOptionPane.showMessageDialog(panelUntuk3, "Anggota sudah pernah ditambahkan",
                    "Warning", JOptionPane.WARNING_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(panelUntuk3, String.format("Berhasil menambahkan mahasiswa dengan ID %s", 
                            mahasiswa.getId()),"Success!", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
    }
}
