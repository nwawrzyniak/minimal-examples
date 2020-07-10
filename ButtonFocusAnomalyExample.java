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

/**
 * Solved. Not an anomaly.
 *
 * TL;DR: You can't request focus on an element in a modal Dialog.
 * If modal is set to false, behaviour is as expected.
 */
public class ButtonFocusAnomalyExample extends JFrame {
    public ButtonFocusAnomalyExample() {
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
        new DialogMinimal(this, false); // Runs the Dialog and behaviour is normal
        //new DialogMinimal(this, true); // Runs the Dialog but focus isn't placed on button
    }

    public static void main(String[] args) {
        new ButtonFocusAnomalyExample();
    }

    static class DialogMinimal extends JDialog {
        private final JTextField output = new JTextField();

        public DialogMinimal(final JFrame owner, final boolean modal) {
            super(owner, modal);
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            int frameWidth = 292;
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
            bYes.setBounds(0, 0, 120, 33);
            bYes.setText("Yes (Space)");
            bYes.addActionListener(this::bYes_ActionPerformed);
            JPanel buttonPanel = new JPanel(null, true);
            buttonPanel.add(bYes);
            JButton bNo = new JButton();
            bNo.setBounds(128, 0, 140, 33);
            bNo.setText("No (Enter/Return)");
            getRootPane().setDefaultButton(bNo);
            bNo.addActionListener(this::bNo_ActionPerformed);
            buttonPanel.add(bNo);
            buttonPanel.setBounds(8, 8, 400, 92);
            buttonPanel.setOpaque(false);
            cp.add(buttonPanel);
            output.setBounds(8, 50, 270, 32);
            cp.add(output);
            setResizable(false);
            setVisible(true);
            bNo.requestFocusInWindow();
        }

        public void bYes_ActionPerformed(final ActionEvent evt) {
            output.setText("Yes"); // Fires on every space bar press
        }

        public void bNo_ActionPerformed(final ActionEvent evt) {
            output.setText("No"); // Fires on every return/enter press
        }
    }
}
