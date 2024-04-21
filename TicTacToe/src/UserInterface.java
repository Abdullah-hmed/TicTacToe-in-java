import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.Random;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

class UserInterface extends JFrame {
    public static int playerTurn;
    public static boolean pvpGameType = true;
    public static boolean isRestarting = false;
    public GameLogic logic = GameLogic.getLogicInstance();
    Random random = new Random();
    JPanel[][] gridPanels;
    JPanel mainPanel = new JPanel();
    JPanel gamePanel = new JPanel();
    JPanel turnPanel = new JPanel();
    JLabel turn = new JLabel();
    JRadioButton pvp = new JRadioButton("2 Player");
    JRadioButton ai = new JRadioButton("Play Against Computer");
    ButtonGroup group = new ButtonGroup();
    

    public void LoadUI(){
        playerTurn = random.nextInt(2);
        if(!pvpGameType){
            playerTurn = 1;
        }
        setUIItems();
        System.out.println(playerTurn);
        addPanels();
        logic.setInitialValues();
    }
    
    private void setUIItems(){
        setSize(500,500);
        if(playerTurn == 0){
            turn.setText("Turn: ✓");
        }else{
            turn.setText("Turn: X");
        }
        System.out.println("Turn:"+playerTurn);
        turn.setFont(new Font("Serif",Font.BOLD, 20));
        mainPanel.add(turn);
        setRadioButtons();
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(gamePanel);
        add(mainPanel);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        gamePanel.setLayout(new GridLayout(3,3,2,2));
        gamePanel.setBackground(Color.BLACK);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void setRadioButtons() {
        mainPanel.add(pvp);
        mainPanel.add(ai);
        group.add(pvp);
        group.add(ai);
        pvp.setSelected(true);
        radioListeners();
    }

    private void radioListeners() {
        pvp.addActionListener((ActionEvent e) -> {
            if(pvp.isSelected()){
                pvpGameType = true;
                restart();
            }
        });
        ai.addActionListener((ActionEvent e) -> {
            if(ai.isSelected()){
                pvpGameType = false;
                restart();
            }
        });
    }

    private void addPanels() {
        gridPanels = new JPanel[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gridPanels[i][j] = new JPanel();
                gridPanels[i][j].addMouseListener(new onClick(this, i, j));
                gamePanel.add(gridPanels[i][j]);
            }
        }

    }

    public static void setTurn(String newTurn) {
        System.out.println(newTurn);
    }

     public void updateTurn(){
        playerTurn = logic.getNextTurn(playerTurn);
        System.out.println("Turn: "+playerTurn);
        if(playerTurn == 0){
            turn.setText("Turn: ✓");
        }else{
            turn.setText("Turn: X");
        }
        mainPanel.revalidate();
        mainPanel.repaint();
        if(playerTurn == 1 && !pvpGameType){
            computerMove();
        }
    }

    public void emptyPanels(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gridPanels[i][j].removeAll();
            }
        }
    }
    
    public void computerMove(){
        System.out.println("\n"+playerTurn+"\n");
        int i = logic.findBestMove()[0];
        int j = logic.findBestMove()[1];
        JLabel ticTac = new JLabel();
        playerTurn(ticTac, gridPanels[i][j], "X", 1, i, j);
    }
    
    public void playerTurn(JLabel ticTac, JPanel clickedPanel, String playerSign, int playerValue, int i, int j){
        ticTac.setText(playerSign);
        ticTac.setFont(new Font("Serif", Font.BOLD, 70));
        clickedPanel.add(ticTac);
        mainPanel.revalidate();
        mainPanel.repaint();
        logic.setBlockValue(i, j, playerValue);
        if(logic.checkWin(i, j, playerValue)){
            JOptionPane.showMessageDialog(null, "Player "+playerSign+" Wins!!!");
            restart();
            if(!pvpGameType){
                return;
            }
        }
        if(logic.checkDraw()){
            JOptionPane.showMessageDialog(null, "Draw Occurred");
            restart();
            if(!pvpGameType){
                return;
            }
        }
        updateTurn();
    }
    
    public void restart(){
        logic.restartGame();
        emptyPanels();
        mainPanel.revalidate();
        mainPanel.repaint();
        
        if(!pvpGameType){
            playerTurn = 1;
            computerMove();
        }
    }
}
