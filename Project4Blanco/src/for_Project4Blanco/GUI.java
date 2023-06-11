package for_Project4Blanco;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

public class GUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JLabel instructionsL;
    private JTextField textEnterTF;
    private JButton enterB;
    private JTextPane pane;
    private Container myCP;
    private Spellchecker sp;

    public GUI() {
        super("A Super Stupendous Spellchecker");
        setSize(455, 200);
        setLocation(100, 100);
        myCP = getContentPane();
        myCP.setLayout(new FlowLayout());

        sp = new Spellchecker();

        instructionsL = new JLabel("Paste your text below and click the Enter button:");
        instructionsL.setFont(new Font("Arial", Font.PLAIN, 20));
        myCP.add(instructionsL);

        textEnterTF = new JTextField(20);
        textEnterTF.setFont(new Font("Arial", Font.PLAIN, 20));
        textEnterTF.setText("");
        myCP.add(textEnterTF);

        enterB = new JButton("Enter");
        enterB.setFont(new Font("Arial", Font.PLAIN, 20));
        enterB.addActionListener(new EnterBHandler());
        myCP.add(enterB);

        pane = new JTextPane();
        pane.setFont(new Font("Arial", Font.PLAIN, 15));
        myCP.add(pane);

        setVisible(true);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public void append(Color c, String s) {
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);
        int len = pane.getDocument().getLength();
        pane.setCaretPosition(len);
        pane.setCharacterAttributes(aset, false);
        pane.replaceSelection(s);
    }

    public class EnterBHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String text = textEnterTF.getText();
            String[] words = text.split("\s");
            
            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                boolean isSpelledCorrectly = sp.isWordCorrect(word);
                Color color;
                if (isSpelledCorrectly) {
                    color = Color.BLACK;
                } else {
                    color = Color.RED;
                }
                append(color, word + " ");
                
                if ((i + 1) % 10 == 0) {
                    append(Color.BLACK, "\n");
                }
            }
        }
    }
    public static void main(String[] args) {
        GUI myGui = new GUI();
    }
}