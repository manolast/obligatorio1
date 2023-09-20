/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package dominio;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author manue
 */
public class Dominio {
    
    public class Bar {
        private char symbol;
        //si es azul, color = True, si rojo = False
        private Boolean color;

        public Bar(char symbol, Boolean color) {
            this.symbol = symbol;
            this.color = color;
        }

        public char getSymbol() {
            return symbol;
        }

        public Boolean getColor() {
            return color;
        }
    }
    
public class Juego {
    private Bar[][] tablero;
    private int filas;
    private int columnas
    private int level;
    private int totalMoves;
    //dependiendo de como se comienza el juego, si la fuente es al azar, txt o predet
    private String fuente;

    public Juego(int rows, int columns, int level) {
        this.level = level;
        this.totalMoves = 0;
        initializeBoard();
    }
    
    public Juego(){
        this.filas = 5;
        this.columnas = 6;
        this.level = 3;
        this.totalMoves = 0;
        this.tablero = generarTableroPredet();
        
    }
    
    private Bar[][] generarTableroPredet(){
        Bar[][] matriz = new Bar[5][6];
        
        matriz[0][0] = new Bar('\\', true);
        matriz[0][1] = new Bar('/', false);
        matriz[0][2] = new Bar('|', false);
        matriz[0][3] = new Bar('\\', true); 
        matriz[0][4] = new Bar('\\', false);
        matriz[0][5] = new Bar('/', false);
        
        matriz[1][0] = new Bar('-', false);
        matriz[1][1] = new Bar('\\', true);
        matriz[1][2] = new Bar('\\', false);
        matriz[1][3] = new Bar('\\', true); 
        matriz[1][4] = new Bar('|', false);
        matriz[1][5] = new Bar('-', false);
        
        matriz[2][0] = new Bar('-', false);
        matriz[2][1] = new Bar('\\', true);
        matriz[2][2] = new Bar('\\', false);
        matriz[2][3] = new Bar('\\', true); 
        matriz[2][4] = new Bar('|', false);
        matriz[2][5] = new Bar('-', false);
        
    }
    private void initializeBoard() {
        board = new Bar[rows][columns];
        Random random = new Random();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
//                board[i][j] = random.nextBoolean();
            }
        }
    }
}


//class Tablero {
//    private Bar[][] matriz;
//    private int filas;
//    private int columnas;
//
//    public Tablero(int filas, int columnas, Bar[][] tablero) {
//        this.filas = filas;
//        this.columnas = columnas;
//        this.matriz = new Bar[filas][columnas];
//    }
//
//
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) {
//        // TODO code application logic here
//    }
//    
//}
