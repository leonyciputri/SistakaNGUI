package assignments.assignment4.frontend.anggota.ui;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;
import assignments.assignment4.backend.pengguna.Anggota;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// TODO: Implementasikan hal-hal yang diperlukan
public class PembayaranPanel extends SistakaPanel {
    private JPanel kosongan = new JPanel();
    private JPanel panelUntuk3 = new JPanel();
    private JLabel Label = new JLabel("Pembayaran Denda");
    private JLabel uangLabel = new JLabel("Jumlah Denda");
    private JTextField inputUang = new JTextField();
    private JButton bayarButton = new JButton("Bayar");
    private JButton kembaliButton = new JButton("Kembali");

    public PembayaranPanel(HomeGUI main) {
        super(main);
        // TODO: Implementasikan hal-hal yang diperlukan
        setLayout(new FlowLayout(FlowLayout.CENTER));;

        //panel kosongan untuk membantu elemen menjadi di tengah
        kosongan.setPreferredSize(new Dimension(500,170));
        add(kosongan);

        //panel untuk label utama, label nama, input nama
        panelUntuk3.setLayout(new BorderLayout(10,5));
        panelUntuk3.setPreferredSize(new Dimension(450,80));
        add(panelUntuk3);

        //label
        Label.setHorizontalAlignment(SwingConstants.CENTER);
        Label.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        panelUntuk3.add(Label, BorderLayout.NORTH);

        //label uang
        uangLabel.setHorizontalAlignment(SwingConstants.CENTER);
        uangLabel.setFont(new Font("Times New Roman", Font.PLAIN,12));
        panelUntuk3.add(uangLabel, BorderLayout.CENTER);

        //input uang textfield
        inputUang.setPreferredSize(new Dimension(450,20));
        inputUang.setHorizontalAlignment(SwingConstants.CENTER);
        panelUntuk3.add(inputUang, BorderLayout.SOUTH);

        //panel untuk tombol
        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelButtons.setPreferredSize(new Dimension(450,50));
        panelButtons.add(bayarButton);
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
        bayarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String uang = inputUang.getText().toString();

                if(inputUang.getText()==null||inputUang.getText().toString().equals("")){
                    JOptionPane.showMessageDialog(panelUntuk3, "Silahkan masukkan jumlah denda yang ingin dibayar",
                "Warning", JOptionPane.WARNING_MESSAGE);
                }else{
                    if(!isNumeric(uang)){
                        JOptionPane.showMessageDialog(panelUntuk3, "Jumlah bayar harus berupa angka!",
                "Warning", JOptionPane.WARNING_MESSAGE);
                    }else{
                        String message = ((Anggota) SistakaNG.getPenggunaLoggedIn()).bayarDenda((long) Integer.parseInt(uang));
                        JOptionPane.showMessageDialog(panelUntuk3, message, "Info", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
    }
}
