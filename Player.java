package battleship;

public enum Player {
    PLAYER1("Player 1"),
    PLAYER2("Player 2");

    private final String name;

    Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Player nextPlayer() {
        return this == PLAYER1 ? PLAYER2 : PLAYER1;
    }

    public void printPlayerBoard(Board board1, Board board2) {
        if (this == PLAYER1) {
            board1.print(true);
            System.out.println("---------------------");
            board2.print();
        } else {
            board2.print(true);
            System.out.println("---------------------");
            board1.print();
        }
    }
}
