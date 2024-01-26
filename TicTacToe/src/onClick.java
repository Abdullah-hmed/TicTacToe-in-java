
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class onClick extends MouseAdapter{
    UserInterface ui = new UserInterface();
    @Override
    public void mouseClicked(MouseEvent e) {
        // Get the source of the event, which is the JPanel that was clicked
        JPanel clickedPanel = (JPanel) e.getSource();
        JLabel ticTac = new JLabel();
        ticTac.setFont(new Font("Serif", Font.BOLD, 30));
        if(UserInterface.playerTurn == 0){
            ticTac.setText("✓");
            clickedPanel.add(ticTac);
            ui.updateTurn();
        }else{
            ticTac.setText("X");
            clickedPanel.add(ticTac);
            ui.updateTurn();
        }
        

        // Repaint the panel to make sure the new component is visible
        clickedPanel.revalidate();
        clickedPanel.repaint();
    }
}
