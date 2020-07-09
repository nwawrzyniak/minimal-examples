import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class AnyFrame extends JFrame {
    public AnyFrame() {
        super();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        int frameWidth = 300;
        int frameHeight = 300;
        setSize(frameWidth, frameHeight);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (d.width - getSize().width) / 2;
        int y = (d.height - getSize().height) / 2;
        setLocation(x, y);
        setTitle("Any Frame");
        setResizable(false);
        Container cp = getContentPane();
        cp.setLayout(null);
        setVisible(true);

        // The only important line in this class
        new DialogMinimal(this, true);
    }

    public static void main(String[] args) {
        new AnyFrame();
    }

    static class DialogMinimal extends JDialog {
        private final JTextField output = new JTextField();

        public DialogMinimal(final JFrame owner, final boolean modal) {
            super(owner, modal);
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            int frameWidth = 252;
            int frameHeight = 126;
            setSize(frameWidth, frameHeight);
            Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
            int x = (d.width - getSize().width) / 2;
            int y = (d.height - getSize().height) / 2;
            setLocation(x, y);
            setTitle("Minimal Button Focus Example");
            Container cp = getContentPane();
            cp.setLayout(null);
            JButton bYes = new JButton();
            bYes.setBounds(0, 0, 100, 33);
            bYes.setText("Yes (Space)");
            bYes.setMargin(new Insets(2, 2, 2, 2));
            bYes.addActionListener(this::bYes_ActionPerformed);
            JPanel buttonPanel = new JPanel(null, true);
            buttonPanel.add(bYes);
            JButton bNo = new JButton();
            bNo.setBounds(108, 0, 120, 33);
            bNo.setText("No (Enter/Return)");
            bNo.setMargin(new Insets(2, 2, 2, 2));
            getRootPane().setDefaultButton(bNo);
            bNo.requestFocus();
            bNo.addActionListener(this::bNo_ActionPerformed);
            buttonPanel.add(bNo);
            buttonPanel.setBounds(8, 8, 400, 92);
            buttonPanel.setOpaque(false);
            cp.add(buttonPanel);
            output.setBounds(8, 50, 220, 32);
            cp.add(output);
            setResizable(false);
            setVisible(true);
        }

        public void bYes_ActionPerformed(final ActionEvent evt) {
            output.setText("Yes");
        }

        public void bNo_ActionPerformed(final ActionEvent evt) {
            output.setText("No");
        }
    }
}
