
import javax.swing.JLabel;

public class GameLogic {
    
    public int getNextTurn(int turn){
        if(turn == 1){
            return 0;
        }else{
            return 1;
        }
    }
    
}
