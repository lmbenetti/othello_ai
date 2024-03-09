import java.util.ArrayList;

public class OthelloAISkynetPseudoCoders implements IOthelloAI {

    private Boolean firstPlayer;
    private int depp;

    public OthelloAISkynetPseudoCoders() {
        this.firstPlayer = false;
    }

    public Position decideMove(GameState s) {
        depp = 1000000;
        int count = s.countTokens()[0] + s.countTokens()[1];
        if (count == 4) {
            firstPlayer = true;
        }

        return maxValue(s, Integer.MIN_VALUE, Integer.MAX_VALUE).getPosition();
    }

    public Tuple maxValue(GameState s, int alpha, int beta) {
        Tuple tupleUtilMove = new Tuple(Integer.MIN_VALUE, new Position(-1, -1));

        if (s.isFinished() || depp <= 0) {
            tupleUtilMove.updateUtility(s, firstPlayer);
            return tupleUtilMove;
        } else {
            ArrayList<Position> moves = s.legalMoves();

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
                    depp--;
                    return tupleUtilMove;
                }

            }
        }
        depp--;
        return tupleUtilMove;
    }

    public Tuple minValue(GameState s, int alpha, int beta) {
        Tuple tupleUtilMove = new Tuple(Integer.MAX_VALUE, new Position(-1, -1));

        if (s.isFinished() || depp <= 0) {
            tupleUtilMove.updateUtility(s, firstPlayer);
            return tupleUtilMove;
        } else {
            ArrayList<Position> moves = s.legalMoves();

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
                    depp--;
                    return tupleUtilMove;
                }

            }
        }
        depp--;
        return tupleUtilMove;
    }

}
