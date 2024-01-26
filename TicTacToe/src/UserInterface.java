import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class UserInterface extends JFrame {
    public static int playerTurn;
    Random random = new Random();
    JPanel r1c1 = new JPanel();
    JPanel r1c2 = new JPanel();
    JPanel r1c3 = new JPanel();
    JPanel r2c1 = new JPanel();
    JPanel r2c2 = new JPanel();
    JPanel r2c3 = new JPanel();
    JPanel r3c1 = new JPanel();
    JPanel r3c2 = new JPanel();
    JPanel r3c3 = new JPanel();
    
    JPanel mainPanel = new JPanel();
    JPanel gamePanel = new JPanel();
    JPanel turnPanel = new JPanel();
    JLabel turn = new JLabel();
    
    GameLogic logic = new GameLogic();
    
    public void LoadUI(){
        setUIItems();
        playerTurn = random.nextInt(2);
        System.out.println(playerTurn);
        addPanels();
    }
    
    private void setUIItems(){
        setSize(500,500);
        turn.setText("Turn:"+playerTurn);
        System.out.println("Turn:"+playerTurn);
        turn.setFont(new Font("Serif",Font.BOLD, 20));
        mainPanel.add(turn);
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

    private void addPanels() {
//        r1c1.setBackground(Color.red);
//        r1c2.setBackground(Color.blue);
//        r1c3.setBackground(Color.green);
//        r2c1.setBackground(Color.red);
//        r2c2.setBackground(Color.blue);
//        r2c3.setBackground(Color.green);
//        r3c1.setBackground(Color.red);
//        r3c2.setBackground(Color.blue);
//        r3c3.setBackground(Color.green);

        r1c1.addMouseListener(new onClick());
        r1c2.addMouseListener(new onClick());
        r1c3.addMouseListener(new onClick());
        r2c1.addMouseListener(new onClick());
        r2c2.addMouseListener(new onClick());
        r2c3.addMouseListener(new onClick());
        r3c1.addMouseListener(new onClick());
        r3c2.addMouseListener(new onClick());
        r3c3.addMouseListener(new onClick());
        gamePanel.add(r1c1);
        gamePanel.add(r1c2);
        gamePanel.add(r1c3);
        gamePanel.add(r2c1);
        gamePanel.add(r2c2);
        gamePanel.add(r2c3);
        gamePanel.add(r3c1);
        gamePanel.add(r3c2);
        gamePanel.add(r3c3);
    }

    public static void setTurn(String newTurn) {
        System.out.println(newTurn);
    }

    public void updateTurn(){
        playerTurn = logic.getNextTurn(playerTurn);
        System.out.println("Turn: "+playerTurn);
        turn.setText("Turn: "+playerTurn);
    }
    
    
}
