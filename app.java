import javax.swing.*;

public class app
{
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                RockPaperScissor rockPaperScissorGUI=new RockPaperScissor();

                rockPaperScissorGUI.setVisible(true);
            }
        });
    }
}
