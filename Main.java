package battleship;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Board board1 = new Board();
        Board board2 = new Board();
        Player currentPlayer = Player.PLAYER1;

        System.out.println("Player 1, place your ships on the game field");
        placeShips(scanner, board1);
        nextPlayer(scanner);

        currentPlayer = currentPlayer.nextPlayer();

        System.out.println("Player 2, place your ships on the game field");
        placeShips(scanner, board2);
        nextPlayer(scanner);

        while (!board1.checkAllShipsSunk() || !board2.checkAllShipsSunk()) {
            currentPlayer.printPlayerBoard(board1, board2);
            currentPlayer = currentPlayer.nextPlayer();

            System.out.println(currentPlayer.getName() + ", it's your turn:");
            String shot = scanner.nextLine().toUpperCase();

            if (currentPlayer == Player.PLAYER1) {
                while (!board2.checkShotCoordinate(shot)) {
                    System.out.println("Error! You entered the wrong coordinates! Try again:");
                    shot = scanner.nextLine().toUpperCase();
                }
                if (board2.checkHit(shot)){
                    System.out.print(" ");
                } else {
                    System.out.println("You missed!");
                }
                nextPlayer(scanner);
            } else {
                while (!board1.checkShotCoordinate(shot)) {
                    System.out.println("Error! You entered the wrong coordinates! Try again:");
                    shot = scanner.nextLine().toUpperCase();
                }
                if (board1.checkHit(shot)) {
                    System.out.print(" ");
                } else {
                    System.out.println("You missed!");
                }
                nextPlayer(scanner);
            }
        }
        scanner.close();
    }



    private static void nextPlayer(Scanner scanner) {
        System.out.println("Press Enter and pass the move to another player");
        scanner.nextLine();
        System.out.println();
    }

    private static void placeShips(Scanner scanner, Board board) {
        board.print();
        System.out.println();
        inputCoordinates(board, scanner, Type.AIRCRAFT_CARRIER);
        inputCoordinates(board, scanner, Type.BATTLESHIP);
        inputCoordinates(board, scanner, Type.SUBMARINE);
        inputCoordinates(board, scanner, Type.CRUISER);
        inputCoordinates(board, scanner, Type.DESTROYER);
    }

    public static void inputCoordinates(Board board, Scanner scanner, Type type) {
        boolean isValidCoordinates;
        System.out.println("Enter the coordinates of the " + type.getName() + " (" + type.getLength() + " cells):");
        String[] coordinates = scanner.nextLine().toUpperCase().split(" ");
        while (coordinates.length != 2) {
            System.out.println("Error! Wrong coordinates! Try again:");
            coordinates = scanner.nextLine().toUpperCase().split(" ");
        }

        isValidCoordinates = board.checkTypeLength(coordinates, type);

        if (isValidCoordinates) {
            if (board.placeShip(coordinates, type)) {
                board.print();
                System.out.println();
            } else {
                inputCoordinates(board, scanner, type);
            }
        } else {
            inputCoordinates(board, scanner, type);
        }
    }
}

