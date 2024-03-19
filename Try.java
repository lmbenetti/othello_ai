public class Try {
    public static void main(String[] args) {
        Position pos1 = new Position(1, 3);
        Tuple tup1 = new Tuple(10, pos1);
        Tuple tup2 = new Tuple(11, pos1);

        System.out.println(tup1.compareTo(tup2));
    }
    
}
