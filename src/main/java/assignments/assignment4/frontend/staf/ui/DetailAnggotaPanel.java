package assignments.assignment4.frontend.staf.ui;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.backend.pengguna.Anggota;
import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// TODO: Implementasikan hal-hal yang diperlukan
public class DetailAnggotaPanel extends SistakaPanel {
    private JPanel kosongan = new JPanel();
    private JPanel editorPanePanel = new JPanel();
    private JPanel panelUntuk3 = new JPanel();
    private JLabel Label = new JLabel("Lihat Detail Anggota");
    private JLabel perintahLabel = new JLabel("Pilih ID Anggota");
    private JComboBox<String> anggotaComboBox = new JComboBox<>();
    private JEditorPane infoEditorPane = new JEditorPane();
    private JScrollPane scrollPane = new JScrollPane(infoEditorPane, 
        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    private JButton lihatButton = new JButton("Lihat");
    private JButton kembaliButton = new JButton("Kembali");

    public DetailAnggotaPanel(HomeGUI main) {
        super(main);
        // TODO: Implementasikan hal-hal yang diperlukan
        setLayout(new FlowLayout(FlowLayout.CENTER));;

        //panel kosongan untuk membantu elemen menjadi di tengah
        kosongan.setPreferredSize(new Dimension(500,200));
        add(kosongan);

        //panel untuk label utama, label "pilih id anggota", anggotaComboBox
        panelUntuk3.setLayout(new BorderLayout(10,5));
        panelUntuk3.setPreferredSize(new Dimension(450,100));
        add(panelUntuk3);

        //label
        Label.setHorizontalAlignment(SwingConstants.CENTER);
        Label.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        panelUntuk3.add(Label, BorderLayout.NORTH);

        //label "pilih id anggota"
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
        anggotaComboBox.removeAllItems();
        for(int i=0;i<SistakaNG.getDaftarAnggota().size();i++){
            anggotaComboBox.addItem(SistakaNG.getDaftarAnggota().get(i).getId());
        }
        panelUntuk3.add(anggotaComboBox, BorderLayout.SOUTH);

        editorPanePanel.remove(scrollPane);

        lihatButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String id = anggotaComboBox.getSelectedItem().toString();
                Anggota anggota = SistakaNG.findAnggota(id);

                if(id==null||id.equals("")||anggota==null||anggotaComboBox.getSelectedItem()==null){
                    JOptionPane.showMessageDialog(panelUntuk3, "Silahkan memilih ID anggota!",
                "Warning", JOptionPane.WARNING_MESSAGE);
                }else{
                    infoEditorPane.setMaximumSize(new Dimension(290,200));
                    scrollPane.setPreferredSize(new Dimension(300,200));
                    infoEditorPane.setEditable(false);
                    infoEditorPane.setContentType("text/html");
                    infoEditorPane.setText(anggota.detail());
                    editorPanePanel.add(scrollPane);

                    kosongan.setPreferredSize(new Dimension(500,100));
                }
            }
        });
    }
}
