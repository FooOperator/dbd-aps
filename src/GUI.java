import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.DefaultListModel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class GUI {
    JFrame frame;
    JPanel leftPanel;
    JPanel rightPanel;
    Dimension screenSize;
    String windowTitle;
    Color backgroundColor;
    Color accentColor;
    ImageIcon icon;

    public GUI() {
        frame = new JFrame();
        icon = new ImageIcon("src/assets/logo.png");
        windowTitle = "DBD - AV1";
        backgroundColor = new Color(0xF4F2F3);
        accentColor = new Color(0xF93943);
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        
        frame.setTitle(windowTitle);
        frame.setIconImage(icon.getImage());
        frame.setSize((int) screenSize.getWidth()/2, (int) screenSize.getHeight()/2);
        frame.getContentPane().setBackground(accentColor);
        frame.setResizable(false);
        
        initLeftPanel();
        initRightPanel();
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    void initLeftPanel(){
        List<String> fields = new ArrayList<String>();
        Collections.addAll(fields, "Nome", "Idade", "Email", "CPF", "Curso", "Tipo de usuário");
        JLabel label;
        JTextField tx = new JTextField();
        
        leftPanel = new JPanel();
        leftPanel.setBackground(accentColor);
        leftPanel.setPreferredSize(new Dimension(frame.getWidth()/2, frame.getHeight()/2));
        for (String field : fields){
            label = new JLabel(field);
            leftPanel.add(label);
            tx = new JTextField();
            tx.setColumns(20);
            leftPanel.add(tx);
        }
    
        frame.add(leftPanel, BorderLayout.WEST);
    }
    void initRightPanel() {
        List<String> content = new ArrayList<String>();
        Collections.addAll(content, "A", "B", "C", "D", "E", "F");

        DefaultListModel<String> lm = new DefaultListModel<>();
        lm.addAll(content);

        JLabel queryLabel = new JLabel("Entradas");
        JList queryList = new JList(lm);
        queryList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        
        rightPanel = new JPanel();
        rightPanel.setBackground(backgroundColor);
        queryList.setMaximumSize(new Dimension(rightPanel.getWidth(), rightPanel.getHeight()));
        rightPanel.add(queryLabel);
        rightPanel.add(queryList);

        rightPanel.setPreferredSize(new Dimension(frame.getWidth()/2, frame.getHeight()/2));
        frame.add(rightPanel, BorderLayout.EAST);
    }

    public static void main(String args[]){
        new GUI();
    }
}