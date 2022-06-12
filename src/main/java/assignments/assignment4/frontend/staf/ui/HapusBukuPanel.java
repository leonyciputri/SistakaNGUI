package assignments.assignment4.frontend.staf.ui;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.backend.buku.Buku;
import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.*;

// TODO: Implementasikan hal-hal yang diperlukan
public class HapusBukuPanel extends SistakaPanel {
    private JPanel kosongan = new JPanel();
    private JPanel panelUntuk3 = new JPanel();
    private JLabel Label = new JLabel("Hapus Buku");
    private JLabel bukuLabel = new JLabel("Buku");
    private JComboBox<String> bukuPilihan = new JComboBox<>();
    private JButton hapusButton = new JButton("Hapus");
    private JButton kembaliButton = new JButton("Kembali");

    public HapusBukuPanel(HomeGUI main) {
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

        //panel untuk tombol
        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelButtons.setPreferredSize(new Dimension(450,50));
        panelButtons.add(hapusButton);
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

        //buku combo box
        bukuPilihan.removeAllItems();
        for(int i=0;i<SistakaNG.getDaftarBuku().size();i++){
            bukuPilihan.addItem(SistakaNG.getDaftarBuku().get(i).toString());
        }
        panelUntuk3.add(bukuPilihan, BorderLayout.SOUTH);

        hapusButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
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
                String message = SistakaNG.deleteBuku(buku);
                
                if(bukuPilihan.getSelectedItem()==null){
                    JOptionPane.showMessageDialog(panelUntuk3, "Silahkan memilih buku!",
                "Warning", JOptionPane.WARNING_MESSAGE);
                }else if(message.equals(String.format("Buku %s oleh %s tidak dapat dihapus karena sedang dipinjam%n",
                    buku.getJudul(), buku.getPenulis()))){
                    JOptionPane.showMessageDialog(panelUntuk3, message, "Info", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    //refresh comboBox
                    ArrayList<Buku> daftarBukuNew = SistakaNG.getDaftarBuku();
                    daftarBukuNew.remove(buku);
                    SistakaNG.setDaftarBuku(daftarBukuNew);

                    //refresh comboBox yang baru
                    bukuPilihan.removeAllItems();
                    for(int i=0;i<SistakaNG.getDaftarBuku().size();i++){
                        bukuPilihan.addItem(SistakaNG.getDaftarBuku().get(i).toString());
                    }

                    JOptionPane.showMessageDialog(panelUntuk3, message, "Success!", JOptionPane.INFORMATION_MESSAGE);
                }
                refresh();
            }
        });
    }
}
