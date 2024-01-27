
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class onClick extends MouseAdapter{
    private UserInterface ui;
    int i;
    int j;
    public onClick(UserInterface ui, int i, int j) {
        this.ui = ui;
        this.i = i;
        this.j = j;
    }
    GameLogic logic = GameLogic.getLogicInstance();
    @Override
    public void mouseClicked(MouseEvent e) {
        // Get the source of the event, which is the JPanel that was clicked
        JPanel clickedPanel = (JPanel) e.getSource();
        JLabel ticTac = new JLabel();
        ticTac.setFont(new Font("Serif", Font.BOLD, 30));
        
        if(UserInterface.playerTurn == 0 ){
            ticTac.setText("✓");
            clickedPanel.add(ticTac);
            
            logic.setBlockValue(i-1, j-1, 0);
            logic.checkWin(i-1, j-1, 0);
            ui.updateTurn();
        }else{
            ticTac.setText("X");
            clickedPanel.add(ticTac);
            logic.setBlockValue(i-1, j-1, 1);
            logic.checkWin(i-1, j-1, 1);
            ui.updateTurn();
            
            
        }
        
        
        clickedPanel.revalidate();
        clickedPanel.repaint();
    }
}
