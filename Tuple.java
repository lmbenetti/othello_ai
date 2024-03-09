public class Tuple implements Comparable<Tuple> {

    private Integer utility;
    private Position position;

    public Tuple(Integer utility, Position position) {
        this.utility = utility;
        this.position = position;
    }

    public void updateUtility(GameState s){
        int[] utilArr = s.countTokens();
        int utilDiff = utilArr[0]-utilArr[1];
        utility = utilDiff;
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
