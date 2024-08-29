package battleship;

public class Board{
    private char[][] field;
    private Ship aircraft = new Ship(Type.AIRCRAFT_CARRIER);
    private Ship battleship = new Ship(Type.BATTLESHIP);
    private Ship submarine = new Ship(Type.SUBMARINE);
    private Ship cruiser = new Ship(Type.CRUISER);
    private Ship destroyer = new Ship(Type.DESTROYER);


    public Board(){
        field = new char[10][10];
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                field[i][j] = '~';
            }
        }
    }

    public void print() {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (int i = 0; i < field.length; i++) {
            System.out.print((char) ('A' + i) + " ");
            for (int j = 0; j < field[i].length; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void print(boolean isHidden) {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (int i = 0; i < field.length; i++) {
            System.out.print((char) ('A' + i) + " ");
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j] == 'O' && isHidden) {
                    System.out.print("~ ");
                } else {
                    System.out.print(field[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public boolean checkTypeLength(String[] coordinates, Type type) {
        int length = length(coordinates);
        switch (type) {
            case AIRCRAFT_CARRIER:
                if (length != 5) {
                    System.out.println("Error! Wrong length of the Aircraft Carrier! Try again:");
                    return false;
                }
                break;
            case BATTLESHIP:
                if (length != 4) {
                    System.out.println("Error! Wrong length of the Battleship! Try again:");
                    return false;
                }
                break;
            case SUBMARINE:
                if (length != 3) {
                    System.out.println("Error! Wrong length of the Submarine! Try again:");
                    return false;
                }
                break;
            case CRUISER:
                if (length != 3) {
                    System.out.println("Error! Wrong length of the Cruiser! Try again:");
                    return false;
                }
                break;
            case DESTROYER:
                if (length != 2) {
                    System.out.println("Error! Wrong length of the Destroyer! Try again:");
                    return false;
                }
                break;
        }
        return true;
    }

    public boolean placeShip(String[] coordinates, Type type) {
        if (!checkCoordinates(coordinates)){
            return false;
        }
        if (!checkAdjacent(coordinates)) {
            System.out.println("Error! You placed it too close to another one.");
            return false;
        }

        char letter1 = coordinates[0].charAt(0);
        char letter2 = coordinates[1].charAt(0);
        int number1 = Integer.parseInt(coordinates[0].replaceAll("[^0-9]", ""));
        int number2 = Integer.parseInt(coordinates[1].replaceAll("[^0-9]", ""));
        final String errorMessage = "Error! You placed it too close to another one.";


        if (letter1 == letter2) {
            for (int i = 0; i < Math.abs(number1 - number2) + 1; i++) {
                if (number1 < number2) {
                    if (field[letter1 - 'A'][number1 - 1 + i] == 'O') {
                        System.out.println(errorMessage);
                        return false;
                    } else {
                        field[letter1 - 'A'][number1 - 1 + i] = 'O';
                        if (type == Type.AIRCRAFT_CARRIER) {
                            aircraft.setCoordinate(letter1 + String.valueOf(number1 + i), i);
                        } else if (type == Type.BATTLESHIP) {
                            battleship.setCoordinate(letter1 + String.valueOf(number1 + i), i);
                        } else if (type == Type.SUBMARINE) {
                            submarine.setCoordinate(letter1 + String.valueOf(number1 + i), i);
                        } else if (type == Type.CRUISER) {
                            cruiser.setCoordinate(letter1 + String.valueOf(number1 + i), i);
                        } else if (type == Type.DESTROYER) {
                            destroyer.setCoordinate(letter1 + String.valueOf(number1 + i), i);
                        }
                    }
                } else {
                    if (field[letter1 - 'A'][number1 - 1 - i] == 'O') {
                        System.out.println(errorMessage);
                        return false;
                    } else {
                        field[letter1 - 'A'][number1 - 1 - i] = 'O';
                        if (type == Type.AIRCRAFT_CARRIER) {
                            aircraft.setCoordinate(letter1 + String.valueOf(number1 - i), i);
                        } else if (type == Type.BATTLESHIP) {
                            battleship.setCoordinate(letter1 + String.valueOf(number1 - i), i);
                        } else if (type == Type.SUBMARINE) {
                            submarine.setCoordinate(letter1 + String.valueOf(number1 - i), i);
                        } else if (type == Type.CRUISER) {
                            cruiser.setCoordinate(letter1 + String.valueOf(number1 - i), i);
                        } else if (type == Type.DESTROYER) {
                            destroyer.setCoordinate(letter1 + String.valueOf(number1 - i), i);
                        }
                    }
                }
            }
        } else if (number1 == number2) {
            for (int i = 0; i < Math.abs(letter1 - letter2) + 1; i++) {
                if (letter1 < letter2) {
                    if (field[letter1 - 'A' + i][number1 - 1] == 'O') {
                        System.out.println(errorMessage);
                        return false;
                    } else {
                        field[letter1 - 'A' + i][number1 - 1] = 'O';
                        if (type == Type.AIRCRAFT_CARRIER) {
                            aircraft.setCoordinate((char) (letter1 + i) + String.valueOf(number1), i);
                        } else if (type == Type.BATTLESHIP) {
                            battleship.setCoordinate((char) (letter1 + i) + String.valueOf(number1), i);
                        } else if (type == Type.SUBMARINE) {
                            submarine.setCoordinate((char) (letter1 + i) + String.valueOf(number1), i);
                        } else if (type == Type.CRUISER) {
                            cruiser.setCoordinate((char) (letter1 + i) + String.valueOf(number1), i);
                        } else if (type == Type.DESTROYER) {
                            destroyer.setCoordinate((char) (letter1 + i) + String.valueOf(number1), i);
                        }
                    }
                } else {
                    if (field[letter1 - 'A' - i][number1 - 1] == 'O') {
                        System.out.println(errorMessage);
                        return false;
                    } else {
                        field[letter1 - 'A' - i][number1 - 1] = 'O';
                        if (type == Type.AIRCRAFT_CARRIER) {
                            aircraft.setCoordinate((char) (letter1 - i) + String.valueOf(number1), i);
                        } else if (type == Type.BATTLESHIP) {
                            battleship.setCoordinate((char) (letter1 - i) + String.valueOf(number1), i);
                        } else if (type == Type.SUBMARINE) {
                            submarine.setCoordinate((char) (letter1 - i) + String.valueOf(number1), i);
                        } else if (type == Type.CRUISER) {
                            cruiser.setCoordinate((char) (letter1 - i) + String.valueOf(number1), i);
                        } else if (type == Type.DESTROYER) {
                            destroyer.setCoordinate((char) (letter1 - i) + String.valueOf(number1), i);
                        }
                    }
                }
            }
        }
        return true;
    }

    public boolean checkCoordinates(String[] coordinates) {
        char letter1 = coordinates[0].charAt(0);
        char letter2 = coordinates[1].charAt(0);
        int number1 = Integer.parseInt(coordinates[0].replaceAll("[^0-9]", ""));
        int number2 = Integer.parseInt(coordinates[1].replaceAll("[^0-9]", ""));

        boolean isInvalidLetter = letter1 < 'A' || letter1 > 'J' || letter2 < 'A' || letter2 > 'J';
        boolean isInvalidNumber = (number1 < 1 || number1 > 10 ) || (number2 < 1 || number2 > 10);
        if (isInvalidLetter || isInvalidNumber) {
            System.out.println("Error!");
            return false;
        }
        return true;
    }

    public int length(String[] coordinates) {
        int length = 0;
        int number1 = Integer.parseInt(coordinates[0].replaceAll("[^0-9]", ""));
        int number2 = Integer.parseInt(coordinates[1].replaceAll("[^0-9]", ""));

        if(coordinates[0].charAt(0) == coordinates[1].charAt(0)) {
            length = Math.abs(number1 - number2) + 1;
        } else if(number1 == number2) {
            length = Math.abs(coordinates[0].charAt(0) - coordinates[1].charAt(0)) + 1;
        } else {
            System.out.println("Error!");
        }
        return length;
    }

    public boolean checkShotCoordinate(String coordinate) {
        if (coordinate.length() <2 || coordinate.length() > 3) {
            return false;
        }
        char letter = coordinate.charAt(0);
        int number = Integer.parseInt(coordinate.replaceAll("[^0-9]", ""));
        if (letter < 'A' || letter > 'J' || number < 1 || number > 10) {
            return false;
        }
        return true;
    }

    public boolean checkHit(String coordinate) {
        char letter = coordinate.charAt(0);
        int number = Integer.parseInt(coordinate.replaceAll("[^0-9]", ""));
        if (field[letter - 'A'][number - 1] == 'O' || field[letter - 'A'][number - 1] == 'X') {
            field[letter - 'A'][number - 1] = 'X';
            setHitToShip(coordinate);
            return true;
        } else {
            field[letter - 'A'][number - 1] = 'M';
            return false;
        }
    }

    public void setHitToShip(String coordinate) {
        Type[] types = Type.values();
        for (Type type : types) {
            for (int i = 0; i < type.getLength(); i++) {
                if (type == Type.AIRCRAFT_CARRIER) {
                    if (aircraft.getCoordinates()[i].equals(coordinate)) {
                        aircraft.setHit(coordinate);
                        if (checkAllShipsSunk()) {
                            System.out.println("You sank the last ship. You won. Congratulations!");
                        } else if (aircraft.getIsSunk()) {
                            System.out.println("You sank a ship!");
                        } else {
                            System.out.println("You hit a ship!");
                        }
                        return;
                    }
                } else if (type == Type.BATTLESHIP) {
                    if (battleship.getCoordinates()[i].equals(coordinate)) {
                        battleship.setHit(coordinate);
                        if (checkAllShipsSunk()) {
                            System.out.println("You sank the last ship. You won. Congratulations!");
                        } else if (battleship.getIsSunk()) {
                            System.out.println("You sank a ship!");
                        } else {
                            System.out.println("You hit a ship!");
                        }
                        return;
                    }
                } else if (type == Type.SUBMARINE) {
                    if (submarine.getCoordinates()[i].equals(coordinate)) {
                        submarine.setHit(coordinate);
                        if (checkAllShipsSunk()) {
                            System.out.println("You sank the last ship. You won. Congratulations!");
                        } else if (submarine.getIsSunk()) {
                            System.out.println("You sank a ship!");
                        } else {
                            System.out.println("You hit a ship!");
                        }
                        return;
                    }
                } else if (type == Type.CRUISER) {
                    if (cruiser.getCoordinates()[i].equals(coordinate)) {
                        cruiser.setHit(coordinate);
                        if (checkAllShipsSunk()) {
                            System.out.println("You sank the last ship. You won. Congratulations!");
                        } else if (cruiser.getIsSunk()) {
                            System.out.println("You sank a ship!");
                        } else {
                            System.out.println("You hit a ship!");
                        }
                        return;
                    }
                } else if (type == Type.DESTROYER) {
                    if (destroyer.getCoordinates()[i].equals(coordinate)) {
                        destroyer.setHit(coordinate);
                        if (checkAllShipsSunk()) {
                            System.out.println("You sank the last ship. You won. Congratulations!");
                        } else if (destroyer.getIsSunk()) {
                            System.out.println("You sank a ship!");
                        } else {
                            System.out.println("You hit a ship!");
                        }
                        return;
                    }
                }
            }
        }
    }

    public boolean checkAllShipsSunk() {
        if (aircraft.getIsSunk() && battleship.getIsSunk() && submarine.getIsSunk() && cruiser.getIsSunk() && destroyer.getIsSunk()) {
            return true;
        }
        return false;
    }



    public boolean checkAdjacent(String[] coordinates) {
        char letter1 = coordinates[0].charAt(0);
        char letter2 = coordinates[1].charAt(0);
        int number1 = Integer.parseInt(coordinates[0].replaceAll("[^0-9]", ""));
        int number2 = Integer.parseInt(coordinates[1].replaceAll("[^0-9]", ""));

        if (letter1 == letter2) {
            for (int i = 0; i < Math.abs(number1 - number2) + 1; i++) {
                if (number1 < number2) {
                    if (number1 + i + 1 < 11 && field[letter1 - 'A'][number1 - 1 + i + 1] == 'O') {
                        return false;
                    }
                    if (number1 + i - 1 > 0 && field[letter1 - 'A'][number1 - 1 + i - 1] == 'O') {
                        return false;
                    }
                    if (letter1 - 'A' + 1 < 10 && field[letter1 - 'A' + 1][number1 - 1 + i] == 'O') {
                        return false;
                    }
                    if (letter1 - 'A' - 1 > -1 && field[letter1 - 'A' - 1][number1 - 1 + i] == 'O') {
                        return false;
                    }
                } else {
                    if (number1 - i + 1 < 11 && field[letter1 - 'A'][number1 - 1 - i + 1] == 'O') {
                        return false;
                    }
                    if (number1 - i - 1 > 0 && field[letter1 - 'A'][number1 - 1 - i - 1] == 'O') {
                        return false;
                    }
                    if (letter1 - 'A' + 1 < 10 && field[letter1 - 'A' + 1][number1 - 1 - i] == 'O') {
                        return false;
                    }
                    if (letter1 - 'A' - 1 > -1 && field[letter1 - 'A' - 1][number1 - 1 - i] == 'O') {
                        return false;
                    }
                }
            }
        } else if (number1 == number2) {
            for (int i = 0; i < Math.abs(letter1 - letter2) + 1; i++) {
                if (letter1 < letter2) {
                    if (letter1 - 'A' + i + 1 < 10 && field[letter1 - 'A' + i + 1][number1 - 1] == 'O') {
                        return false;
                    }
                    if (letter1 - 'A' + i - 1 > -1 && field[letter1 - 'A' + i - 1][number1 - 1] == 'O') {
                        return false;
                    }
                    if (number1 + 1 < 11 && field[letter1 - 'A' + i][number1] == 'O') {
                        return false;
                    }
                    if (number1 - 1 > 0 && field[letter1 - 'A' + i][number1 - 2] == 'O') {
                        return false;
                    }
                } else {
                    if (letter1 - 'A' - i + 1 < 10 && field[letter1 - 'A' - i + 1][number1 - 1] == 'O') {
                        return false;
                    }
                    if (letter1 - 'A' - i - 1 > -1 && field[letter1 - 'A' - i - 1][number1 - 1] == 'O') {
                        return false;
                    }
                    if (number1 + 1 < 11 && field[letter1 - 'A' - i][number1] == 'O') {
                        return false;
                    }
                    if (number1 - 1 > 0 && field[letter1 - 'A' - i][number1 - 2] == 'O') {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
