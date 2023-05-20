import java.awt.*;
import javax.swing.*;
import com.intellij.uiDesigner.core.*;
import net.miginfocom.swing.*;
/*
 * Created by JFormDesigner on Sat May 20 01:48:22 TRT 2023
 */



/**
 * @author hazim
 */
public class OpenResearch extends JPanel {
    public OpenResearch() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Hazim Alper Ata
        frame1 = new JFrame();
        label1 = new JLabel();
        label2 = new JLabel();
        textField1 = new JTextField();

        //======== frame1 ========
        {
            frame1.setTitle("OpenResearch");
            var frame1ContentPane = frame1.getContentPane();
            frame1ContentPane.setLayout(new MigLayout(
                "insets 30,hidemode 3,gap 0 0",
                // columns
                "[grow 1,fill]",
                // rows
                "[grow 1,fill]" +
                "[grow 1,fill]" +
                "[]" +
                "[fill]"));

            //---- label1 ----
            label1.setText("Welcome to Open Research");
            label1.setFont(label1.getFont().deriveFont(label1.getFont().getStyle() | Font.BOLD, 26f));
            label1.setForeground(new Color(0x00cccc));
            label1.setHorizontalAlignment(SwingConstants.CENTER);
            frame1ContentPane.add(label1, "cell 0 0,align center top,grow 0 0");

            //---- label2 ----
            label2.setText("Username");
            label2.setHorizontalAlignment(SwingConstants.CENTER);
            frame1ContentPane.add(label2, "cell 0 1 0 2,align center center,grow 0 0");
            frame1ContentPane.add(textField1, "cell 0 1 0 2");
            frame1.pack();
            frame1.setLocationRelativeTo(frame1.getOwner());
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Hazim Alper Ata
    private JFrame frame1;
    private JLabel label1;
    private JLabel label2;
    private JTextField textField1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
