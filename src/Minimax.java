import com.sun.source.doctree.SummaryTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class Minimax {

    Board board;
    final int WIN = 100;
    final int TIE = 0;
    final int LOST = -100;
    final int DEPTH = 10;


    public Minimax(Board board){
        this.board = board;
    }

    public int minimax(boolean maxPlayer, int depth, Board board) throws InterruptedException {
        int sum = evaluate();

        if(sum == WIN || sum == LOST || board.getPlayableTiles().size()==0){
            return sum;
        }
        ArrayList<String> possibilities = board.getPlayableTiles();
        if(maxPlayer){
            int maxValue = Integer.MIN_VALUE;
//            System.out.println("Max player");
            for(String possibility: possibilities){

                String[] tilesCoord = possibility.split(",");
                int row = Integer.parseInt(tilesCoord[0]);
                int col = Integer.parseInt(tilesCoord[1]);

                board.setTiles(Notations.O,row,col);
                maxValue = Math.max(maxValue, minimax(false, depth-1,board));
                board.setTiles(Notations.BLANK,row,col);
            }
            return maxValue;
        }else{
            int minValue = Integer.MAX_VALUE;
            for(String possibility: possibilities){
//                System.out.println("Min player");
                String[] tilesCoord = possibility.split(",");
                int row = Integer.parseInt(tilesCoord[0]);
                int col = Integer.parseInt(tilesCoord[1]);

                board.setTiles(Notations.X,row,col);
                minValue = Math.min(minValue, minimax(true, depth-1,board));
                board.setTiles(Notations.BLANK,row,col);
            }
            return minValue;
        }
    }

    public void playAI() throws InterruptedException {
        int maxValue = Integer.MIN_VALUE;
        int[] coordBestMove = new int[]{0,0};
        ArrayList<String> possibilities = this.board.getPlayableTiles();

        for(String possibility: possibilities){
            String[] tilesCoord = possibility.split(",");
            int row = Integer.parseInt(tilesCoord[0]);
            int col = Integer.parseInt(tilesCoord[1]);
            board.setTiles(Notations.O,row,col);
            int minMaxValue = minimax(false, DEPTH, this.board);
//            System.out.println("AT POS row: " + row + " col: "+ col + " the minmaxvalue is " + minMaxValue + " ,the max value is " + maxValue);
            board.setTiles(Notations.BLANK,row,col);
            if(minMaxValue > maxValue){
                maxValue = minMaxValue;
                //We make the move
               coordBestMove[0] = row;
               coordBestMove[1] = col;
            }

        }

        System.out.println("Finally, the best move to play is: "+ Arrays.toString(coordBestMove));
        //We make the move
        board.setTiles(Notations.O,coordBestMove[0],coordBestMove[1]);
    }

    public int evaluate() throws InterruptedException {

        //We check the horizontal first
        for(int rows =0;rows<board.board.length; rows++){
            if(board.getTile(rows,0).equals(board.getTile(rows,1)) && board.getTile(rows,1).equals(board.getTile(rows,2)) && !board.getTile(rows,0).equals(Notations.BLANK)){

                return board.getTile(rows,0).equals(Notations.X) ? LOST : WIN;
            }
        }

        //We check the horizontal first
        for(int col =0;col<board.board[0].length; col++){
            if(board.getTile(0,col).equals(board.getTile(1,col)) && board.getTile(1,col).equals(board.getTile(2,col)) && !board.getTile(0,col).equals(Notations.BLANK)){
//                int sumsum = board.getTile(0,col).equals(Notations.X) ? LOST : WIN;
//                System.out.println("The sum: " + sumsum);
//                board.printBoard();
//                TimeUnit.SECONDS.sleep(5);

                return board.getTile(0,col).equals(Notations.X) ? LOST : WIN;
            }
        }

        //We check diagonal
       if(board.getTile(0,0).equals(board.getTile(1,1)) && board.getTile(1,1).equals(board.getTile(2,2))  && !board.getTile(0,0).equals(Notations.BLANK)){
//           int sumsum = board.getTile(0,0).equals(Notations.X) ? LOST : WIN;
//           System.out.println("The sum: " + sumsum);
//           board.printBoard();
//           TimeUnit.SECONDS.sleep(5);
           return board.getTile(0,0).equals(Notations.X) ? LOST : WIN;
       }

        if(board.getTile(0,2).equals(board.getTile(1,1)) && board.getTile(1,1).equals(board.getTile(2,0))  && !board.getTile(2,0).equals(Notations.BLANK)){
//            int sumsum = board.getTile(2,0).equals(Notations.X) ? LOST : WIN;
//            System.out.println("The sum: " + sumsum);
//            board.printBoard();
//            TimeUnit.SECONDS.sleep(5);
            return board.getTile(2,0).equals(Notations.X) ? LOST : WIN;
        }

        //TIE
        return TIE;
    }



}
