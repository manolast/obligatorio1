/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package solli;

import java.util.*;
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
        public void setSymbol(char newSymbol) {
            this.symbol = newSymbol;
        }
        public void setColor(Boolean newColor) {
            this.color = newColor;
        }

        public Boolean getColor() {
            return color;
        }
    }
    public class Juego {
        private Bar[][] tablero;
        private int level;
        private ArrayList<int[]> movimientosInicial;
        private ArrayList<int[]> movimientos;


        //constructor con 3 params genera aleatorio por nivel
        public Juego(int rows, int columns, int level) {
            this.level = level;
            Random random = new Random();
            boolean randomBoolean = random.nextBoolean();
            //devuelve un tablero todo de un mismo color, con barras random
            this.tablero = generarTableroRandom(rows, columns, level, randomBoolean);
            //se "dessoluciona" el tablero y se devuelve la solucion
            desSolucionar(level);

        }

        public Juego(){
            this.level = 3;
//            this.tablero = generarTableroPredet();

        }
        public void agregarMovimiento(int x, int y) {
            int[] coordenadas = {x, y};
            movimientos.add(coordenadas);
        }
        private Bar[][] generarTableroRandom(int filas, int columnas, int nivel, boolean color){
            Bar[][] matriz = new Bar[5][6];
            for(int i = 0; i< filas; i++){
                for(int j = 0; j< columnas; j++){
                    char barraRand = generarBarraRandom();
                    matriz[i][j].setSymbol(barraRand);
                    matriz[i][j].setColor(color);
                }
            }
            return matriz;
        }
        private char generarBarraRandom(){
            char ret = 'd';
            Random random = new Random();
            int opcion = random.nextInt(4) + 1;
            switch(opcion){
                case 1:
                    ret = '\\';
                    break;
                case 2:
                    ret = '/';
                    break;
                case 3:
                    ret = '|';
                    break;
                case 4:
                    ret = '-';
                    break;
                default:
                    ret = '-';
                    break;                
            }
            return ret;
        }
        private void desSolucionar(int nivel){
            Random random = new Random();
            int filas = this.tablero.length;
            int columnas = this.tablero[0].length;
            for(int i = 0; i< nivel; i++){
                int opcionFilas = random.nextInt(filas - 1);
                int opcionColumna = random.nextInt(columnas - 1);
                //falta modificar el tablero acorde
                int[] movimiento = {opcionFilas, opcionColumna};
                this.movimientosInicial.add(movimiento);
            }
        }
       private Bar generarTableroPredet(){
            Bar[][] matriz = new Bar[5][6];

            matriz[0][0] = new Bar('|', true);
            matriz[0][1] = new Bar('|', true);
            matriz[0][2] = new Bar('-', false);
            matriz[0][3] = new Bar('/', true); 
            matriz[0][4] = new Bar('|', false);
            matriz[0][5] = new Bar('-', false);

            matriz[1][0] = new Bar('-', false);
            matriz[1][1] = new Bar('/', true);
            matriz[1][2] = new Bar('/', true);
            matriz[1][3] = new Bar('|', true); 
            matriz[1][4] = new Bar('-', false);
            matriz[1][5] = new Bar('-', false);

            matriz[2][0] = new Bar('-', false);
            matriz[2][1] = new Bar('-', false);
            matriz[2][2] = new Bar('|', true);
            matriz[2][3] = new Bar('-', false); 
            matriz[2][4] = new Bar('/', false);
            matriz[2][5] = new Bar('-', false);
            
            matriz[3][0] = new Bar('\\', false);
            matriz[3][1] = new Bar('-', false);
            matriz[3][2] = new Bar('|', false);
            matriz[3][3] = new Bar('\\', false); 
            matriz[3][4] = new Bar('|', true);
            matriz[3][5] = new Bar('|', false);
            
            matriz[4][0] = new Bar('\\', false);
            matriz[4][1] = new Bar('/', false);
            matriz[4][2] = new Bar('/', false);
            matriz[4][3] = new Bar('|', true); 
            matriz[4][4] = new Bar('/', true);
            matriz[4][5] = new Bar('\\', true);
            
            
        }      
    }
    
}
