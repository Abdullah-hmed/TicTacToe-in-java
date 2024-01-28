public class GameLogic {
    static GameLogic INSTANCE = new GameLogic();
    private GameLogic() { //disabling object instantiation
    }
    public static GameLogic getLogicInstance(){
        return INSTANCE;
    }
    
    
    final int ROW = 3;
    final int COLUMN = 3;
    int[][] blockValues = new int[ROW][COLUMN];
    
    int recentX;
    int recentY;
    
    public int getNextTurn(int turn){
        if(turn == 1){
            return 0;
        }else{
            return 1;
        }
    }
    
    public void setInitialValues(){
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COLUMN; j++) {
                blockValues[i][j] = -1;
            }
        }
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COLUMN; j++) {
                System.out.print(blockValues[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    public void setBlockValue(int x, int y, int turn){
        System.out.println(x+","+y);
        
        blockValues[x][y] = turn;
        
        // Print the array for verification
        System.out.println("--------------------------------------------");
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COLUMN; j++) {
                System.out.print(blockValues[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("--------------------------------------------");
        
    }
    
    public boolean checkWin(int x, int y, int value){
        boolean[] winCheck = {false,false,false};
        winCheck[0] = checkHorizontal(x, value);
        winCheck[1] = checkVertical(y, value);
        winCheck[2] = checkDiagonal(x, y, value);
        for(int i = 0; i < 3; i++){
            if(winCheck[i] == true){
                System.out.println("Winner!!!!!!!!!");
                return true;
            }
        }
        return false;
    }
    
    
    public boolean checkDraw(){
        for (int i = 0; i < blockValues.length; i++) {
            for (int j = 0; j < blockValues[i].length; j++) {
                if (blockValues[i][j] == -1) {
                    return false; // Found a value equal to -1
                }
            }
        }
        return true;
    }

    private boolean checkHorizontal(int x, int value) {
        int sameVal = 0;
        for(int j = 0; j < 3 ; j++ ){
            if(blockValues[x][j] == value){
                sameVal++;
                System.out.println("Horizontal Check: "+sameVal);
            }
        }
        if(sameVal == 3){
            System.out.println("Win!");
            return true;
        }
        return false;
    }

    private boolean checkVertical(int y, int value) {
        int sameVal = 0;
        for(int i = 0; i < 3 ; i++ ){
            if(blockValues[i][y] == value){
                sameVal++;
                System.out.println("Check Vertical: "+sameVal);
            }
        }
        if(sameVal == 3){
            System.out.println("Win!");
            return true;
        }
        return false;
    }

    private boolean checkDiagonal(int x, int y, int value) {
        int sameValLD = 0;
        int sameValRD = 0;
        
            for(int i=0 ; i<3 ; i++){ //For Left diagonal
                if(blockValues[i][i] == value){
                    sameValLD++;
                    System.out.println("Check LD: "+sameValLD+" bV:"+x+", "+y);
                }
                if(sameValLD == 3){
                    System.out.println("Win! LD");
                    return true;
                }
            }
            for(int i=0, j=2 ; i<3 && j > -1 ; i++, j--){ //For Right diagonal
                if(blockValues[i][j] == value){
                    sameValRD++;
                    System.out.println("Check RD: "+sameValRD+" bV:"+x+", "+y);
                }
                if(sameValRD == 3){
                    System.out.println("Win! RD");
                    return true;
                }
            }
        return false;
    }
    
    public void restartGame(){
        setInitialValues();
    }
    
    public boolean labelAddedCheck(int i, int j){
        if(blockValues[i][j] == -1){
            return false;
        }else{
            return true;
        }
    }
    
    public int[] NextEmpty(){
        int[] emptyVal = new int[2];
        for(int i=0 ; i< ROW ; i++){
            for(int j=0 ; j< COLUMN ; j++){
                if(blockValues[i][j] == -1){
                    emptyVal[0] = i;
                    emptyVal[1] = j;
                    return emptyVal;
                }
                else{
                    checkDraw();
                }
            }
        }
        return null;
    }
    

    
    public int[] findBestMove() {
        int[] bestMove = {-1, -1};
        int bestScore = Integer.MIN_VALUE;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (blockValues[i][j] == -1) {
                    blockValues[i][j] = 1; // Assume it's the maximizing player's move
                    int score = minimax(blockValues, 0, false);
                    blockValues[i][j] = -1;

                    if (score > bestScore) {
                        bestScore = score;
                        bestMove[0] = i;
                        bestMove[1] = j;
                    }
                }
            }
        }

        return bestMove;
}


    public static int minimax(int[][] board, int depth, boolean isMaximizing) {
        int result = evaluate(board);

        if (result != 0) {
            return result;
        }

        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == -1) {
                        board[i][j] = 1;
                        bestScore = Math.max(bestScore, minimax(board, depth + 1, !isMaximizing));
                        board[i][j] = -1;
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == -1) {
                        board[i][j] = 0;
                        bestScore = Math.min(bestScore, minimax(board, depth + 1, !isMaximizing));
                        board[i][j] = -1;
                    }
                }
            }
            return bestScore;
        }
    }


    public static int evaluate(int[][] board) {
        // Check for a win
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != -1) {
                return (board[i][0] == 1) ? 1 : -1;
            }
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != -1) {
                return (board[0][i] == 1) ? 1 : -1;
            }
        }

        // Check for a diagonal win
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != -1) {
            return (board[0][0] == 1) ? 1 : -1;
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != -1) {
            return (board[0][2] == 1) ? 1 : -1;
        }

        // Check for a draw
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == -1) {
                    return 0; // The game is still ongoing
                }
            }
        }

        return 0; // It's a draw
    }

}
