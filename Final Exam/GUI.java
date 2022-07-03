package q8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI {

    private static JTextField n1;
    private static JTextField n2;
    private static JTextField sumText;
    private static JButton button;

    static ActionListener AL = l -> {
        sumText.setText(String.valueOf(Integer.parseInt(n1.getText())+ Integer.parseInt(n2.getText())));
    };

    public static void main (String[] args) {
        JFrame frame = new JFrame("Q: 8");
        JPanel panel = new JPanel();

        n1 = new JTextField();
        n2 = new JTextField();
        sumText = new JTextField();
        panel.setLayout(new GridLayout(4, 2));

        panel.add(new JLabel("Number 1: "), BorderLayout.WEST);
        panel.add(n1);
        panel.add(new JLabel("Number 2: "),BorderLayout.WEST);
        panel.add(n2);
        panel.add(new JLabel("Sum: "), BorderLayout.WEST);
        panel.add(sumText);

        button = new JButton("Add");
        panel.add(button, BorderLayout.WEST);
        button.addActionListener(AL);

        frame.getContentPane().add(panel);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(300, 200);
    }

}
