
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class onClick extends MouseAdapter{
    private final UserInterface ui;
    int i;
    int j;
    GameLogic logic = GameLogic.getLogicInstance();
    boolean labelAdded;
    public onClick(UserInterface ui, int i, int j) {
        this.ui = ui;
        this.i = i;
        this.j = j;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        // Get the source of the event, which is the JPanel that was clicked
        JPanel clickedPanel = (JPanel) e.getSource();
        JLabel ticTac = new JLabel();
        ticTac.setFont(new Font("Serif", Font.BOLD, 70));
        labelAdded = logic.labelAddedCheck(i,j);
        if(!labelAdded){
            if(UserInterface.playerTurn == 0){//Player 0 logic
                ui.playerTurn(ticTac, clickedPanel, "✓", 0, i, j);
            } else{ //Player 1 logic
                ui.playerTurn(ticTac, clickedPanel, "X", 1, i, j);
            }
        } else{
            System.out.println("Already Clicked!");
        }
        
        
        clickedPanel.revalidate();
        clickedPanel.repaint();
    }
    
    
    
    
    
    
}
