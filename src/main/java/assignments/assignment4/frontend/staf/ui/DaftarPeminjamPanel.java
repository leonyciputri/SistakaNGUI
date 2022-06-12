package assignments.assignment4.frontend.staf.ui;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.backend.buku.Buku;
import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// TODO: Implementasikan hal-hal yang diperlukan
public class DaftarPeminjamPanel extends SistakaPanel {
    private JPanel kosongan = new JPanel();
    private JPanel editorPanePanel = new JPanel();
    private JPanel panelUntuk3 = new JPanel();
    private JLabel Label = new JLabel("Lihat Daftar Peminjaman");
    private JLabel perintahLabel = new JLabel("Pilih buku");
    private JComboBox<String> bukuComboBox = new JComboBox<>();
    private JEditorPane infoEditorPane = new JEditorPane();
    private JScrollPane scrollPane = new JScrollPane(infoEditorPane, 
        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    private JButton lihatButton = new JButton("Lihat");
    private JButton kembaliButton = new JButton("Kembali");

    public DaftarPeminjamPanel(HomeGUI main) {
        super(main);
        // TODO: Implementasikan hal-hal yang diperlukan
        setLayout(new FlowLayout(FlowLayout.CENTER));;

        //panel kosongan untuk membantu elemen menjadi di tengah
        kosongan.setPreferredSize(new Dimension(500,200));
        add(kosongan);

        //panel untuk label utama, label "pilih buku", bukuComboBox
        panelUntuk3.setLayout(new BorderLayout(10,5));
        panelUntuk3.setPreferredSize(new Dimension(450,100));
        add(panelUntuk3);

        //label
        Label.setHorizontalAlignment(SwingConstants.CENTER);
        Label.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        panelUntuk3.add(Label, BorderLayout.NORTH);

        //label "pilih buku"
        perintahLabel.setHorizontalAlignment(SwingConstants.CENTER);
        perintahLabel.setFont(new Font("Times New Roman", Font.PLAIN,12));
        panelUntuk3.add(perintahLabel, BorderLayout.CENTER);

        //panel untuk informasi anggota
        editorPanePanel.setLayout(new FlowLayout (FlowLayout.CENTER));
        add(editorPanePanel);

        //panel untuk tombol
        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelButtons.setPreferredSize(new Dimension(450,50));
        panelButtons.add(lihatButton);
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

        //combobox anggota setting
        bukuComboBox.removeAllItems();
        for(int i=0;i<SistakaNG.getDaftarBuku().size();i++){
            bukuComboBox.addItem(SistakaNG.getDaftarBuku().get(i).toString());
        }
        panelUntuk3.add(bukuComboBox, BorderLayout.SOUTH);

        editorPanePanel.remove(scrollPane);

        lihatButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String dataBuku = bukuComboBox.getSelectedItem().toString();
                Buku book = new Buku();
                    for(int i=0;i<SistakaNG.getDaftarBuku().size();i++){
                        if(SistakaNG.getDaftarBuku().get(i).toString().equals(dataBuku)){
                            book = SistakaNG.getDaftarBuku().get(i);
                            break;
                        }
                    }

                if(dataBuku.equals("")||book==null||bukuComboBox.getSelectedItem()==null){
                    JOptionPane.showMessageDialog(panelUntuk3, "Silahkan memilih buku!",
                "Warning", JOptionPane.WARNING_MESSAGE);
                }else{
                    infoEditorPane.setMaximumSize(new Dimension(290,200));
                    scrollPane.setPreferredSize(new Dimension(300,200));
                    infoEditorPane.setEditable(false);
                    infoEditorPane.setContentType("text/html");
                    infoEditorPane.setText(SistakaNG.daftarPeminjam(book));
                    editorPanePanel.add(scrollPane);

                    kosongan.setPreferredSize(new Dimension(500,100));
                }
            }
        });
    }
}
