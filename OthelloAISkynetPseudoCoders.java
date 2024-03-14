import java.util.ArrayList;

public class OthelloAISkynetPseudoCoders implements IOthelloAI {

    private Boolean firstPlayer;
    private int depp;

    public OthelloAISkynetPseudoCoders() {
        this.firstPlayer = false;
    }

    public Position decideMove(GameState s) {
        System.out.println("decision made");
        int count = s.countTokens()[0] + s.countTokens()[1];
        depp = count - 4 + 12;
        if (count == 4) {
            firstPlayer = true;
        }

        return maxValue(s, Integer.MIN_VALUE, Integer.MAX_VALUE).getPosition();
    }

    
    public Tuple maxValue(GameState s, int alpha, int beta) {
        Tuple tupleUtilMove = new Tuple(Integer.MIN_VALUE, new Position(-10, -10));

        if (s.isFinished() || s.countTokens()[0] + s.countTokens()[1] >= depp) {
            tupleUtilMove.updateUtility(s, firstPlayer);
            return tupleUtilMove;
        } else {
            ArrayList<Position> moves = s.legalMoves();
            if (moves.isEmpty()){
                GameState tempBoard = new GameState(s.getBoard(), s.getPlayerInTurn());
                tempBoard.changePlayer();
                Tuple tuple2 = maxValue(tempBoard, beta, alpha);
                if (tuple2.compareTo(tupleUtilMove) > 0) {
                    tupleUtilMove = tuple2;
                    alpha = Math.max(alpha, tupleUtilMove.getUtility());
                }
                if (tupleUtilMove.getUtility() >= beta) {
                    return tupleUtilMove;
                }
            }
            else {
                for (Position action : moves) {
                    GameState tempBoard = new GameState(s.getBoard(), s.getPlayerInTurn());
                    tempBoard.insertToken(action);
                    Tuple tuple2 = minValue(tempBoard, alpha, beta);
                    tuple2.setPosition(action);

                    if (tuple2.compareTo(tupleUtilMove) > 0) {
                        tupleUtilMove = tuple2;
                        alpha = Math.max(alpha, tupleUtilMove.getUtility());
                    }

                    if (tupleUtilMove.getUtility() >= beta) {
                        return tupleUtilMove;
                    }

                }
            }
        }
        return tupleUtilMove;
    }

    public Tuple minValue(GameState s, int alpha, int beta) {
        Tuple tupleUtilMove = new Tuple(Integer.MAX_VALUE, new Position(-15, -15));

        if (s.isFinished() || s.countTokens()[0] + s.countTokens()[1] >= depp) {
            tupleUtilMove.updateUtility(s, firstPlayer);
            return tupleUtilMove;
        } else {
            ArrayList<Position> moves = s.legalMoves();
            if (moves.isEmpty()){
                GameState tempBoard = new GameState(s.getBoard(), s.getPlayerInTurn());
                tempBoard.changePlayer();
                Tuple tuple2 = minValue(tempBoard, alpha, beta);
                if (tuple2.compareTo(tupleUtilMove) < 0) {
                    tupleUtilMove = tuple2;
                    beta = Math.min(beta, tupleUtilMove.getUtility());
                }
                if (tupleUtilMove.getUtility() <= alpha) {
                    return tupleUtilMove;
                }
            }
            else {
                
                for (Position action : moves) {
                    GameState tempBoard = new GameState(s.getBoard(), s.getPlayerInTurn());
                    tempBoard.insertToken(action);
                    Tuple tuple2 = maxValue(tempBoard, alpha, beta);
                    tuple2.setPosition(action);

                    if (tuple2.compareTo(tupleUtilMove) < 0) {
                        tupleUtilMove = tuple2;
                        beta = Math.min(beta, tupleUtilMove.getUtility());
                    }

                    if (tupleUtilMove.getUtility() <= alpha) {
                        return tupleUtilMove;
                    }
                }
            }
        }
        return tupleUtilMove;
    }

}
