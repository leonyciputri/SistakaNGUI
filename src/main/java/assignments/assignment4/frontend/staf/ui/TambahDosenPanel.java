package assignments.assignment4.frontend.staf.ui;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.backend.pengguna.Dosen;
import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// TODO: Implementasikan hal-hal yang diperlukan
public class TambahDosenPanel extends SistakaPanel {
    private JPanel kosongan = new JPanel();
    private JPanel panelUntuk3 = new JPanel();
    private JLabel Label = new JLabel("Tambah Dosen");
    private JLabel namaLabel = new JLabel("Nama");
    private JTextField inputNama = new JTextField();
    private JButton tambahButton = new JButton("Tambah");
    private JButton kembaliButton = new JButton("Kembali");

    public TambahDosenPanel(HomeGUI main) {
        super(main);
        // TODO: Implementasikan hal-hal yang diperlukan
        setLayout(new FlowLayout(FlowLayout.CENTER));;

        //panel kosongan untuk membantu elemen menjadi di tengah
        kosongan.setPreferredSize(new Dimension(500,225));
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
                String nama = inputNama.getText();

                boolean sudahAda = false;
                for(int i=0; i<SistakaNG.getDaftarAnggota().size();i++){
                    if(SistakaNG.getDaftarAnggota().get(i).getNama().equalsIgnoreCase(nama)) {
                        sudahAda = true;
                        break;
                    }
                }

                if(nama.equals("")||inputNama.getText()==null){
                    JOptionPane.showMessageDialog(panelUntuk3, "Tidak dapat menambahkan dosen, silahkan periksa kembali input Anda",
                "Warning", JOptionPane.WARNING_MESSAGE);
                }else if(sudahAda){
                    JOptionPane.showMessageDialog(panelUntuk3, "Anggota sudah pernah ditambahkan",
                        "Warning", JOptionPane.WARNING_MESSAGE);
                }else{
                    Dosen dosen = SistakaNG.addDosen(nama);
                    JOptionPane.showMessageDialog(panelUntuk3, String.format("Berhasil menambahkan mahasiswa dengan ID %s", dosen.getId()), 
                        "Success!", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }
}
