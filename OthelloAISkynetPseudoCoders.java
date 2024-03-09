import java.util.ArrayList;


public class OthelloAISkynetPseudoCoders implements IOthelloAI {

    public Position decideMove(GameState s) {
        return maxValue(s).getPosition();
    }

    public Tuple maxValue(GameState s) {
        Tuple tupleUtilMove = new Tuple(Integer.MIN_VALUE, new Position(-1, -1));

        if (s.isFinished()) {
            tupleUtilMove.updateUtility(s);
            return tupleUtilMove;
        } else {
            ArrayList<Position> moves = s.legalMoves();

            for (Position action : moves) {
                GameState tempBoard = new GameState(s.getBoard(), s.getPlayerInTurn());
                tempBoard.insertToken(action);
                Tuple tuple2 = minValue(tempBoard);
                tuple2.setPosition(action);

                if (tuple2.compareTo(tupleUtilMove) > 0) {
                    tupleUtilMove = tuple2;
                }

            }
        }
        return tupleUtilMove;
    }

    public Tuple minValue(GameState s) {
        Tuple tupleUtilMove = new Tuple(Integer.MAX_VALUE, new Position(-1, -1));

        if (s.isFinished()) {
            tupleUtilMove.updateUtility(s);
            return tupleUtilMove;
        } else {
            ArrayList<Position> moves = s.legalMoves();

            for (Position action : moves) {
                GameState tempBoard = new GameState(s.getBoard(), s.getPlayerInTurn());
                tempBoard.insertToken(action);
                Tuple tuple2 = maxValue(tempBoard);
                tuple2.setPosition(action);

                if (tuple2.compareTo(tupleUtilMove) < 0) {
                    tupleUtilMove = tuple2;
                }

            }
        }
        return tupleUtilMove;
    }

}
