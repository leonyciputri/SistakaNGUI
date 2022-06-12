package assignments.assignment4.frontend.staf.ui;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;

import javax.swing.*;
import java.awt.*;

import java.awt.event.*;

// TODO: Implementasikan hal-hal yang diperlukan
public class PeringkatPanel extends SistakaPanel {
    private JPanel kosongan = new JPanel();
    private JPanel panelUntuk3 = new JPanel();
    private JLabel Label = new JLabel("Peringkat");
    private JLabel isian = new JLabel();
    private JButton kembaliButton = new JButton("Kembali");
    
    public PeringkatPanel(HomeGUI main) {
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

        //panel untuk tombol
        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelButtons.setPreferredSize(new Dimension(450,50));
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
        //isian
        panelUntuk3.remove(isian);
        isian.setText(SistakaNG.handleRankingAnggota());
        if(SistakaNG.getDaftarAnggota().size()==0){
            isian.setHorizontalAlignment(SwingConstants.CENTER);
        }else if(SistakaNG.getDaftarAnggota().size()==1){
            kosongan.setPreferredSize(new Dimension(500,155));
            panelUntuk3.setPreferredSize(new Dimension(175,150));
            isian.setHorizontalAlignment(SwingConstants.LEFT);
        }else if(SistakaNG.getDaftarAnggota().size()==2){
            kosongan.setPreferredSize(new Dimension(500,120));
            panelUntuk3.setPreferredSize(new Dimension(175,240));
            isian.setHorizontalAlignment(SwingConstants.LEFT);
        }else{
            kosongan.setPreferredSize(new Dimension(500,65));
            panelUntuk3.setPreferredSize(new Dimension(175,340));
            isian.setHorizontalAlignment(SwingConstants.LEFT);
        }
        isian.setFont(new Font("Times New Roman", Font.PLAIN,12));
        panelUntuk3.add(isian, BorderLayout.CENTER);
    }
}
