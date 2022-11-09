import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {

    Board board = new Board();
    Minimax minimax;
    Random rand = new Random();


    public static void main(String[] args) throws InterruptedException {
        Main main = new Main();
        main.minimax = new Minimax(main.board);
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        int round=9;
        while(round>=0){
            if(main.board.getPlayableTiles().size()==0 || main.board.isFinished() ){
                break;
            }
            main.board.printBoard();

            System.out.println("Enter a tiles (Ex: 1,1 for the middle one)");
            String cord = myObj.nextLine();  // Read user input
            String[] tilesCoord = cord.split(",");
            main.board.setTiles(Notations.X,Integer.parseInt(tilesCoord[0]),Integer.parseInt(tilesCoord[1]));
            main.minimax.playAI();
            round--;
        }

        main.board.printBoard();
        System.out.print("Terminated," );
        if(main.minimax.evaluate() == 100 ){
            System.out.print("O is the winner");
        }else if(main.minimax.evaluate() == -100 ){
            System.out.print("X is the winner");
        }else{
            System.out.print("It's a tie");
        }

    }

    public void generateRandomMove(){
        ArrayList<String> possibilities = board.getPlayableTiles();
        if(possibilities.size() > 0 ){
            String[] possibility = possibilities.get(rand.nextInt(Math.max(0,possibilities.size()-2))).split(",");
            board.setTiles(Notations.X,Integer.parseInt(possibility[0]),Integer.parseInt(possibility[1]));
        }

    }

}
