import java.util.ArrayList;

public class BasicAI implements IOthelloAI {

    private Boolean firstPlayer;
    private int depp;

     /**
	 * Constructor of the OthelloAISkynetPseudoCoders.
     * It takes no parameters and sets that the AI is not the first player by default.
	 */
    public BasicAI() {
        this.firstPlayer = false;
    }

    /**
	 * Implementation of the IOthelloAI interface method decideMove. It decides the best movement applying MiniMax.   
     * @param s a GameState object that represents a board at a certain point in the game.
     * @return a Position object, the next movement according to the GameState
	 */
    public Position decideMove(GameState s) {
        int count = s.countTokens()[0] + s.countTokens()[1]; //The amount of takens on the board
        depp = count - 4 + 12; //Set the depth of the MiniMax search 12 movements ahead.
        if (count == 4) { //If the board has only 4 tokes, the AI is player one.
            firstPlayer = true;
        }
        return maxValue(s, Integer.MIN_VALUE, Integer.MAX_VALUE).getPosition();
    }

    /**
	 * Method aplying the Max part of a MinMax algorithm with AlphaBeta pruning.
     * @param s a GameState object that represents a board at a certain point in the game.
     * @param alpha an integer with the value of alpha. Is Integer.MIN_VALUE when called for the first time.
     * @param beta an integer with the value of beta. Is Integer.MAX_VALUE when called for the first time.
     * @return a Position object, the next movement according to the GameState
	 */
    private Tuple2 maxValue(GameState s, int alpha, int beta) {
        Tuple2 tupleUtilMove = new Tuple2(Integer.MIN_VALUE, new Position(-10, -10)); //Empty tupple created.
        if (s.isFinished() || s.countTokens()[0] + s.countTokens()[1] >= depp) { //End of the three or the game. Checks the depth of the serach.
            tupleUtilMove.updateUtility(s, firstPlayer);
            return tupleUtilMove;
        } else {
            ArrayList<Position> moves = s.legalMoves();
            if (moves.isEmpty()) { //If the game is not finished but there are no more movements to perform by the AI
                GameState tempBoard = new GameState(s.getBoard(), s.getPlayerInTurn());
                tempBoard.changePlayer();
                Tuple2 tuple2 = maxValue(tempBoard, beta, alpha);
                if (tuple2.compareTo(tupleUtilMove) > 0) {
                    tupleUtilMove = tuple2;
                    alpha = Math.max(alpha, tupleUtilMove.getUtility());
                }
                if (tupleUtilMove.getUtility() >= beta) { // AlphaBeta pruning.
                    return tupleUtilMove;
                }
            } else {
                for (Position action : moves) { //If there are movements.
                    GameState tempBoard = new GameState(s.getBoard(), s.getPlayerInTurn());
                    tempBoard.insertToken(action);
                    Tuple2 tuple2 = minValue(tempBoard, alpha, beta);
                    tuple2.setPosition(action);

                    if (tuple2.compareTo(tupleUtilMove) > 0) {
                        tupleUtilMove = tuple2;
                        alpha = Math.max(alpha, tupleUtilMove.getUtility());
                    }

                    if (tupleUtilMove.getUtility() >= beta) { // AlphaBeta pruning.
                        return tupleUtilMove;
                    }
                }
            }
        }
        return tupleUtilMove;
    }

    /**
	 * Method aplying the Min part of a MinMax algorithm with AlphaBeta pruning.
     * @param s a GameState object that represents a board at a certain point in the game.
     * @param alpha an integer with the value of alpha. Is Integer.MAX_VALUE when called for the first time.
     * @param beta an integer with the value of beta. Is Integer.MIN_VALUE when called for the first time.
     * @return a Position object, the next movement according to the GameState
	 */
    private Tuple2 minValue(GameState s, int alpha, int beta) {
        Tuple2 tupleUtilMove = new Tuple2(Integer.MAX_VALUE, new Position(-15, -15)); //Empty tupple created.
        if (s.isFinished() || s.countTokens()[0] + s.countTokens()[1] >= depp) { //End of the three or the game. Checks the depth of the search.
            tupleUtilMove.updateUtility(s, firstPlayer);
            return tupleUtilMove;
        } else {
            ArrayList<Position> moves = s.legalMoves();
            if (moves.isEmpty()) { //If the game is not finished but there are no more movements to perform by the AI
                GameState tempBoard = new GameState(s.getBoard(), s.getPlayerInTurn());
                tempBoard.changePlayer();
                Tuple2 tuple2 = minValue(tempBoard, alpha, beta);
                if (tuple2.compareTo(tupleUtilMove) < 0) {
                    tupleUtilMove = tuple2;
                    beta = Math.min(beta, tupleUtilMove.getUtility());
                }
                if (tupleUtilMove.getUtility() <= alpha) { // AlphaBeta pruning.
                    return tupleUtilMove;
                }
            } else {

                for (Position action : moves) { //If there are movements.
                    GameState tempBoard = new GameState(s.getBoard(), s.getPlayerInTurn());
                    tempBoard.insertToken(action);
                    Tuple2 tuple2 = maxValue(tempBoard, alpha, beta);
                    tuple2.setPosition(action);

                    if (tuple2.compareTo(tupleUtilMove) < 0) {
                        tupleUtilMove = tuple2;
                        beta = Math.min(beta, tupleUtilMove.getUtility());
                    }
                    if (tupleUtilMove.getUtility() <= alpha) { // AlphaBeta pruning.
                        return tupleUtilMove;
                    }
                }
            }
        }
        return tupleUtilMove;
    }
}
