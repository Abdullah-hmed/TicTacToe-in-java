
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class onClick extends MouseAdapter{
    private final UserInterface ui;
    int i;
    int j;
    GameLogic logic = GameLogic.getLogicInstance();
    boolean labelAdded = false;
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
        labelAdded = logic.labelAddedCheck(i-1,j-1);
        if(!labelAdded){
            if(UserInterface.playerTurn == 0){//Player 0 logic
                ticTac.setText("✓");
                clickedPanel.add(ticTac);
                ui.mainPanel.revalidate();
                ui.mainPanel.repaint();
                logic.setBlockValue(i-1, j-1, 0);
                //labelAdded = logic.labelAddedCheck(i-1,j-1);
                if(logic.checkWin(i-1, j-1, 0)){
                    JOptionPane.showMessageDialog(null, "Player ✓ Wins!!!");
                    restart();
                }
                if(logic.checkDraw()){
                    JOptionPane.showMessageDialog(null, "Draw Occurred");
                    restart();
                }
                ui.updateTurn();
            } else{ //Player 1 logic
                ticTac.setText("X");
                clickedPanel.add(ticTac);
                ui.mainPanel.revalidate();
                ui.mainPanel.repaint();
                logic.setBlockValue(i-1, j-1, 1);
                //labelAdded = logic.labelAddedCheck(i-1,j-1);
                if(logic.checkWin(i-1, j-1, 1)){
                    JOptionPane.showMessageDialog(null, "Player X Wins!!!");
                    restart();
                }
                if(logic.checkDraw()){
                    JOptionPane.showMessageDialog(null, "Draw Occurred");
                    restart();
                }
                ui.updateTurn();
            }
        } else{
            System.out.println("Already Clicked!");
        }
        
        
        clickedPanel.revalidate();
        clickedPanel.repaint();
    }
    
    
    
    public void restart(){
        logic.restartGame();
        ui.emptyPanels();
    }
}
