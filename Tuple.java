/**
 * A Tuple Class that assists the OthelloAISkynetPseudoCoders Class. It carries two values: a move to perform, called 'position', and the utility of that move.
 * @author DFEJ, HOHO, LIMB, LKAZ, LUCH, @itu.dk
 * @version 19.3.2024
 */
public class Tuple implements Comparable<Tuple> {

    private Integer utility;
    private Position position;
    
    /**
	 * Constructor of the Tuple Class.
     * @param utility an Integer represententing how valuable a movement is.
     * @param position a Position object representing a move in the Othello game.
	 */
    public Tuple(Integer utility, Position position) {
        this.utility = utility;
        this.position = position;
    }
    /**
	 * Updates the utility of the Tuple on a certain GameState for Player1 or Player2.
     * @param s a GameState object that represnts a board at a certain point in the game.
     * @param firstPlayer a boolean that is true if the AI is player one.
	 */
    public void updateUtility(GameState s, boolean firstPlayer){
        int[] utilArr = s.countTokens();
        int utilDiff = 0;
        int[][] board = s.getBoard();
        if (firstPlayer) {
            utilDiff = utilArr[0]-utilArr[1];
        } else utilDiff = utilArr[1]-utilArr[0];
        utility = utilDiff+checkCorners(board, firstPlayer);
    }
    /**
	 * Checks if the corners are taken by the player or by the oponent and returns the move utility. It rewards the AI when it has taken the corner but punishes it if done by the opponent.
     * @param board A two dimensional array that represents the board.
     * @param firstPlayer a boolean that is true if the AI is player one.
     * @return The utility acording to the corner status.
	 */
    private int checkCorners (int[][] board, boolean firstPlayer){
        int player = 2;
        int oponent = 1;
        int utToReturn = 0;
        if (firstPlayer){ //If the AI is player one, its tokens are 1 and opponent's 2.
            player = 1;
            oponent = 2;
        }
        if (board[0][0] == player){
            utToReturn+=3;;
        }
        if (board[0][0] == oponent){
            utToReturn-=3;;
        }
        if (board[0][board.length-1] == player){
            utToReturn+=3;;
        }
        if (board[0][board.length-1] == oponent){
            utToReturn-=3;;
        }
        if (board[board.length-1][0] == player){
            utToReturn+=3;;
        }
        if (board[board.length-1][0] == oponent){
            utToReturn-=3;;
        }
        if (board[board.length-1][board.length-1] == player){
            utToReturn+=3;;
        }
        if (board[board.length-1][board.length-1] == oponent){
            utToReturn-=3;;
        }

        return utToReturn;
    }
    /**
	 * Returns the utility of a Tuple.
     * @return The utility of the Tuple.
	 */
    public int getUtility() {
        return utility;
    }
     /**
	 * Returns the Position Object of a Tuple.
     * @return The Position of the Tuple.
	 */
    public Position getPosition() {
        return position;
    }

     /**
	 * Sets the utility of a Tuple with the int given.
     * @param utility an int representing the utility.
	 */
    public void setUtility(int utility) {
        this.utility = utility;
    }

     /**
	 * Sets the Position of a Tuple with the Position given.
     * @param position a Position object representing a move.
	 */
    public void setPosition(Position position) {
        this.position = position;
    }

     /**
	 * Checks if a Tuple is bigger than other by comparing their utilities.
     * @param o a Tuple object.
     * @return 1 if the object calling the function is bigger, 0 if they are equal or -1 if it is smaller.
	 */
    @Override
    public int compareTo(Tuple o) {
        return this.utility.compareTo(o.utility);
    }
    
}
