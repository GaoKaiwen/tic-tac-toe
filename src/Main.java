import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        char[][] board = {{' ', ' ', ' '},
                          {' ', ' ', ' '},
                          {' ', ' ', ' '}};

        printBoard(board);

        Scanner scan = new Scanner(System.in);

        boolean played;
        int player = 1;

        while(!gameFinished(board)) {
            played = playerTurn(board, scan, player);
            while(!played) {
                played = playerTurn(board, scan, player);
            }
            printBoard(board);
            if(player == 1)
                player = 2;
            else player = 1;
        }

    }

    private static boolean gameFinished(char[][] board) {
        if((board[0][0] == 'X' && board[0][1] == 'X' && board[0][2] == 'X') ||
           (board[1][0] == 'X' && board[1][1] == 'X' && board[1][2] == 'X') ||
           (board[2][0] == 'X' && board[2][1] == 'X' && board[2][2] == 'X') ||

           (board[0][0] == 'X' && board[1][0] == 'X' && board[2][0] == 'X') ||
           (board[0][1] == 'X' && board[1][1] == 'X' && board[2][1] == 'X') ||
           (board[0][2] == 'X' && board[1][2] == 'X' && board[2][2] == 'X') ||

           (board[0][0] == 'X' && board[1][1] == 'X' && board[2][2] == 'X') ||
           (board[0][2] == 'X' && board[1][1] == 'X' && board[2][0] == 'X')) {
            System.out.println("Player 1 won!");
            return true;
        }

        if((board[0][0] == 'O' && board[0][1] == 'O' && board[0][2] == 'O') ||
           (board[1][0] == 'O' && board[1][1] == 'O' && board[1][2] == 'O') ||
           (board[2][0] == 'O' && board[2][1] == 'O' && board[2][2] == 'O') ||

           (board[0][0] == 'O' && board[1][0] == 'O' && board[2][0] == 'O') ||
           (board[0][1] == 'O' && board[1][1] == 'O' && board[2][1] == 'O') ||
           (board[0][2] == 'O' && board[1][2] == 'O' && board[2][2] == 'O') ||

           (board[0][0] == 'O' && board[1][1] == 'O' && board[2][2] == 'O') ||
           (board[0][2] == 'O' && board[1][1] == 'O' && board[2][0] == 'O')) {
            System.out.println("Player 2 won!");
            return true;
        }

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
               if(board[i][j] == ' ')
                   return false;
            }
        }

        System.out.println("It's a tie!");
        return true;
    }

    private static boolean playerTurn(char[][] board, Scanner scan, int player) {
        char playerSign = player == 1 ? 'X' : 'O';
        System.out.println("Player " + player + ", type the number of the position of your choice (1-9): ");

        int position = scan.nextInt();

        switch (position) {
            case 1:
                if(board[0][0] == ' ') {
                    board[0][0] = playerSign;
                    return true;
                }
                break;
            case 2:
                if(board[0][1] == ' ') {
                    board[0][1] = playerSign;
                    return true;
                }
                break;
            case 3:
                if(board[0][2] == ' ') {
                    board[0][2] = playerSign;
                    return true;
                }
                break;
            case 4:
                if(board[1][0] == ' ') {
                    board[1][0] = playerSign;
                    return true;
                }
                break;
            case 5:
                if(board[1][1] == ' ') {
                    board[1][1] = playerSign;
                    return true;
                }
                break;
            case 6:
                if(board[1][2] == ' ') {
                    board[1][2] = playerSign;
                    return true;
                }
                break;
            case 7:
                if(board[2][0] == ' ') {
                    board[2][0] = playerSign;
                    return true;
                }
                break;
            case 8:
                if(board[2][1] == ' ') {
                    board[2][1] = playerSign;
                    return true;
                }
                break;
            case 9:
                if(board[2][2] == ' ') {
                    board[2][2] = playerSign;
                    return true;
                }
                break;
            default:
                System.out.println("Type a valid position!");
                return false;
        }
        System.out.println("This position is already been chosen!");
        return false;
    }

    private static void printBoard(char[][] board) {
        System.out.println(board[0][0] + "|" + board[0][1] + "|" + board[0][2]);
        System.out.println("-+-+-+");
        System.out.println(board[1][0] + "|" + board[1][1] + "|" + board[1][2]);
        System.out.println("-+-+-+");
        System.out.println(board[2][0] + "|" + board[2][1] + "|" + board[2][2]);
    }
}