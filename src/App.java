import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;


class App {
    static String windowTitle;
    static JFrame frame;
    static Dimension screenSize;

    static JPanel listsPanel;
    static JPanel fieldsPanel;
    static JPanel buttonsPanel;

    public static void main(String[] args) {
        windowTitle = "AV1 - DBD";
        frame = new JFrame(windowTitle);
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        initUI();
        initFields();
        initLists();
        initButtons();
    }
    public static void initUI() {
        fieldsPanel = new JPanel();
        listsPanel = new JPanel();
        buttonsPanel = new JPanel();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(0, 0, screenSize.width/2, screenSize.height/2);

        frame.getContentPane().add(BorderLayout.LINE_START, fieldsPanel);
        frame.getContentPane().add(BorderLayout.LINE_END, listsPanel);
        frame.getContentPane().add(BorderLayout.PAGE_END, buttonsPanel);
        frame.setVisible(true);

    }
    public static void initFields() {
        List<String> fields = new ArrayList<String>(); 
        List<JLabel> jLabels = new ArrayList<JLabel>();
        List<JTextField> jText = new ArrayList<JTextField>();

        Collections.addAll(fields, "Nome", "Idade", "Email", "CPF");
        
        fields.forEach((String field) -> {
            jLabels.add(new JLabel(field));
            jText.add(new JTextField());
        });

        for (int i = 0; i < fields.size(); i++) {
            JLabel label = jLabels.get(i); 
            JTextField tx = jText.get(i);

            fieldsPanel.add(label);
            tx.setColumns(10);
            fieldsPanel.add(tx);
        }

    }
    public static void initLists() {
        String[] data1 = {"a", "b", "c"};
        String[] data2 = {"d", "e", "f"};

        JList<String> alunosList = new JList<String>(data1);
        JList<String> professoresList = new JList<String>(data2);

        listsPanel.add(alunosList, professoresList);

    }
    public static void initButtons() {
        JButton postButton = new JButton("POST");
        buttonsPanel.add(postButton);
    }

}
