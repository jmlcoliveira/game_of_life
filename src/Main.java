import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner in = new Scanner(System.in);

        int l = in.nextInt();
        int c = in.nextInt();
        int p = in.nextInt();
        in.nextLine();

        GameOfLife game = new GameOfLife(l, c);
        readGame(in, l, c, game);


        int count = 0;
        while(count < p){
            System.out.printf("Transformation: %d\n", count+1);
            game.envolve(/*p*/);
            writeGame(game, l, c);
            System.out.println("-----------------");
            count++;
            TimeUnit.SECONDS.sleep(1);
        }
    }

    private static void writeGame(GameOfLife game, int nLinhas, int nColunas) {
        boolean[][] finalGame = game.getState();
        for (int l = 0; l < nLinhas; l++) {
            for (int c = 0; c < nColunas; c++) {
                if (finalGame[l][c])
                    System.out.print("#");
                else
                    System.out.print(" ");
            }
            System.out.print("\n");
        }
    }

    private static void readGame(Scanner in, int nLinhas, int nColunas, GameOfLife game) {
        for (int l = 0; l < nLinhas; l++) {
            String valor = in.nextLine();
            for (int c = 0; c < nColunas; c++) {
                if (valor.charAt(c) == '#')
                    game.setValueGame(l, c, true);
            }
        }
    }
}
