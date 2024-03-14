public class Tuple implements Comparable<Tuple> {

    private Integer utility;
    private Position position;

    public Tuple(Integer utility, Position position) {
        this.utility = utility;
        this.position = position;
    }

    public void updateUtility(GameState s, Boolean firstPlayer){
        int[] utilArr = s.countTokens();
        int utilDiff = 0;
        int[][] board = s.getBoard();
        if (firstPlayer) {
            utilDiff = utilArr[0]-utilArr[1];
        } else utilDiff = utilArr[1]-utilArr[0];
        utility = utilDiff+checkCorners(board, firstPlayer);
    }
    private int checkCorners (int[][] board, Boolean firstPlayer){
        int player = 2;
        int utToReturn = 0;
        if (firstPlayer){
            player = 1;
        }
        if (board[0][0] == player){
            utToReturn+=3;;
        }
        if (board[0][board.length-1] == player){
            utToReturn+=3;;
        }
        if (board[board.length-1][0] == player){
            utToReturn+=3;;
        }
        if (board[board.length-1][board.length-1] == player){
            utToReturn+=3;;
        }

        return utToReturn;
    }

    public int getUtility() {
        return utility;
    }

    public Position getPosition() {
        return position;
    }

    public void setUtility(int utility) {
        this.utility = utility;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public int compareTo(Tuple o) {
        return this.utility.compareTo(o.utility);
    }
    
}
