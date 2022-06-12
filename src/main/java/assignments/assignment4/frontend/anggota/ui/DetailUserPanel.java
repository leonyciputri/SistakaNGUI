package assignments.assignment4.frontend.anggota.ui;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.backend.pengguna.Anggota;
import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// TODO: Implementasikan hal-hal yang diperlukan
public class DetailUserPanel extends SistakaPanel {
    private JPanel kosongan = new JPanel();
    private JPanel editorPanePanel = new JPanel();
    private JPanel panelUntuk3 = new JPanel();
    private JLabel Label = new JLabel("Lihat Detail Anggota");
    private JEditorPane infoEditorPane = new JEditorPane();
    private JScrollPane scrollPane = new JScrollPane(infoEditorPane, 
        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    private JButton kembaliButton = new JButton("Kembali");

    public DetailUserPanel(HomeGUI main) {
        super(main);
        // TODO: Implementasikan hal-hal yang diperlukan
        // TODO: Implementasikan hal-hal yang diperlukan
        setLayout(new FlowLayout(FlowLayout.CENTER));;

        //panel kosongan untuk membantu elemen menjadi di tengah
        kosongan.setPreferredSize(new Dimension(500,100));
        add(kosongan);

        //panel untuk label utama dan editorPane berisi detail
        panelUntuk3.setLayout(new BorderLayout(10,5));
        panelUntuk3.setPreferredSize(new Dimension(450,100));
        add(panelUntuk3);

        infoEditorPane.setMaximumSize(new Dimension(290,200));
        scrollPane.setPreferredSize(new Dimension(300,200));
        infoEditorPane.setEditable(false);
        infoEditorPane.setContentType("text/html");       
        
        //label
        Label.setHorizontalAlignment(SwingConstants.CENTER);
        Label.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        panelUntuk3.add(Label, BorderLayout.NORTH);

        //panel untuk tombol
        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelButtons.setPreferredSize(new Dimension(450,50));
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
        editorPanePanel.remove(scrollPane);
        panelUntuk3.remove(scrollPane);
        panelUntuk3.setPreferredSize(new Dimension(450, 300));
        infoEditorPane.setText(((Anggota)SistakaNG.getPenggunaLoggedIn()).detail());
        editorPanePanel.add(scrollPane);
        panelUntuk3.add(scrollPane, BorderLayout.CENTER);
    }
}
