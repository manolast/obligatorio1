package solli;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Dominio {

    public static class Bar {

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

        public void invertirColor() {
            boolean color = this.getColor();
            this.setColor(!color);
        }

        @Override
        public String toString() {
            char symb = this.getSymbol();
            Boolean col = this.getColor();
            String ret = "";
            if (col) {
                ret = "\u001B[34m" + symb + "\u001B[0m";
            } else {
                ret = "\u001B[31m" + symb + "\u001B[0m";
            }
            return ret;
        }

    }

    public static class Juego {

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
            this.movimientosInicial = new ArrayList<int[]>();
            desSolucionar(level);

        }

        public Juego(Boolean txt) {
            Bar[][] matriz;
            try {
                Scanner input = new Scanner(new File(".\\test\\datos.txt"));
                int rows = input.nextInt();
                int columns = input.nextInt();
                input.nextLine();
                matriz = new Bar[rows][columns];
                for (int i = 0; i < matriz.length; i++) {
                    String linea = input.nextLine();
                    String[] elementos = linea.split(" ");
                    for (int j = 0; j < columns; j++) {
                        String elemento = elementos[j];
                        char barra = elemento.charAt(0);
                        char color = elemento.charAt(1);
                        Boolean boolColor;
                        if (color == 'R') {
                            boolColor = false;
                        } else {
                            boolColor = true;
                        }
                        matriz[i][j] = new Bar(barra, boolColor);
                    }
                }
                int nivel = input.nextInt();
                this.movimientosInicial = new ArrayList<int[]>();
                for (int i = 0; i < nivel; i++) {
                    int movimientoFila = input.nextInt();
                    int movimientoColumna = input.nextInt();
                    int[] movimiento = {movimientoFila, movimientoColumna};
                    this.movimientosInicial.add(movimiento);
                }
                this.level = nivel;
                this.tablero = matriz;
            } catch (FileNotFoundException e) {
                matriz = new Bar[0][0];
            }
        }

        public Juego() {
            this.level = 3;
            this.tablero = generarTableroPredet();

        }

        public void agregarMovimiento(int x, int y) {
            int[] coordenadas = {x, y};
            movimientos.add(coordenadas);
        }

        private boolean esCoordenadaValida(int x, int y) {
            return x >= 0 && x < tablero.length && y >= 0 && y < tablero[0].length;
        }

        public String hacerCambiosTablero(int x, int y) {
            String ret = "";
            if (!esCoordenadaValida(x, y)) {
                ret = "Coordenada no valida, ingrese una valida";
            } else {
                char barra = tablero[x][y].getSymbol();
                switch (barra) {
                    case '\\' -> {
                        ret = "es una c \\";
                        hacerMovimientoBackslash(x, y);
                    }
                    case '/' -> {
                        ret = "es una /";
                        hacerMovimientoFrontslash(x, y);
                    }
                    case '|' -> {
                        ret = "es una |";
                        hacerMovimientoRecta(x, y);
                    }
                    case '-' -> {
                        ret = "es una -";
                        hacerMovimientoGuion(x, y);
                    }
                    default ->
                        ret = "que carajo paso";
                }

            }
            return ret + "\n" + this.toString();
        }

        public void hacerMovimientoBackslash(int x, int y) {
            //primero voy diagonal para arriba, luego diagonal para abajo
            boolean valida = true;
            int col = y;
            //for para ir en diagonal hacia arriba
            for (int i = x; i >= 0 && valida; i--) {
                this.tablero[i][col].invertirColor();
                col--;
                valida = this.esCoordenadaValida(i - 1, col);
            }
            col = y + 1;
            valida = this.esCoordenadaValida(x + 1, col);
            //for para ir en diagonal hacia abajo
            for (int i = x + 1; i < this.tablero.length && valida; i++) {
                this.tablero[i][col].invertirColor();
                col++;
                valida = this.esCoordenadaValida(i + 1, col);
            }
        }

        public void hacerMovimientoFrontslash(int x, int y) {
            //primero voy diagonal para arriba, luego diagonal para abajo
            boolean valida = true;
            int col = y;
            //for para ir en diagonal hacia arriba
            for (int i = x; i >= 0 && valida; i--) {
                this.tablero[i][col].invertirColor();
                col++;
                valida = this.esCoordenadaValida(i - 1, col);
            }
            col = y - 1;
            valida = this.esCoordenadaValida(x + 1, col);
            //for para ir en diagonal hacia abajo
            for (int i = x + 1; i < this.tablero.length && valida; i++) {
                this.tablero[i][col].invertirColor();
                col--;
                valida = this.esCoordenadaValida(i + 1, col);
            }
        }

        public void hacerMovimientoRecta(int x, int y) {
            //primero voy para arriba, luego abajo
            boolean valida = true;
            int col = y;
            //for para ir para arriba
            for (int i = x; i >= 0 && valida; i--) {
                this.tablero[i][col].invertirColor();
                valida = this.esCoordenadaValida(i - 1, col);
            }
            valida = this.esCoordenadaValida(x + 1, col);
            //for para ir hacia abajo
            for (int i = x + 1; i < this.tablero.length && valida; i++) {
                this.tablero[i][col].invertirColor();
                valida = this.esCoordenadaValida(i + 1, col);
            }
        }

        public void hacerMovimientoGuion(int x, int y) {
            //primero voy para izquierda, luego derecha
            boolean valida = true;
            int fila = x;
            //for para ir a la izquierda
            for (int i = y; i >= 0 && valida; i--) {
                this.tablero[fila][i].invertirColor();
                valida = this.esCoordenadaValida(fila, i - 1);
            }
            valida = this.esCoordenadaValida(fila, y + 1);
            //for para ir a la derecha
            for (int i = y + 1; i < this.tablero[0].length && valida; i++) {
                this.tablero[fila][i].invertirColor();
                valida = this.esCoordenadaValida(fila, i + 1);
            }
        }

        private Bar[][] generarTableroRandom(int filas, int columnas, int nivel, boolean color) {
            Bar[][] matriz = new Bar[filas][columnas];
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    char barraRand = generarBarraRandom();
                    Bar bar = new Bar(barraRand, color);
                    matriz[i][j] = bar;
                }
            }
            return matriz;
        }

        private char generarBarraRandom() {
            char ret = 'd';
            Random random = new Random();
            int opcion = random.nextInt(4) + 1;
            switch (opcion) {
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

        private void desSolucionar(int nivel) {
            //funciona perfecto, falta asegurarse de que no haga y deshaga
            Random random = new Random();
            int filas = this.tablero.length;
            int columnas = this.tablero[0].length;
            for (int i = 0; i < nivel; i++) {
                int opcionFilas = random.nextInt(filas - 1);
                int opcionColumna = random.nextInt(columnas - 1);
                hacerCambiosTablero(opcionFilas, opcionColumna);
                int[] movimiento = {opcionFilas, opcionColumna};
                this.movimientosInicial.add(movimiento);
            }
        }

        private Bar[][] generarTableroPredet() {
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

            return matriz;
        }

        public boolean estaSolucionada() {
            boolean color = this.tablero[0][0].getColor();
            boolean soluc = true;
            for (int i = 0; i < this.tablero.length && soluc; i++) {
                for (int j = 0; j < this.tablero[0].length && soluc; j++) {
                    soluc = color == this.tablero[i][j].getColor();
                }
            }
            return soluc;
        }

        @Override
        public String toString() {
            String ret = "";
            int numColumnas = this.tablero[0].length;
            String linea1 = " ";
            for (int i = 1; i <= numColumnas; i++) {
                linea1 += "   " + i;
            }
            ret += linea1 + "\n";
            for (int i = 0; i < (this.tablero.length * 2) + 1; i++) {
                if (i % 2 == 0) {
                    String lineas = "  " + "+---".repeat(numColumnas) + "+";
                    ret += lineas;
                } else {
                    String numeroFila = "" + (((i - 1) / 2) + 1) + " ";
                    ret += numeroFila;
                    for (int j = 0; j < this.tablero[0].length; j++) {
                        String barString = this.tablero[(i - 1) / 2][j].toString();
                        ret += "| " + barString + " ";
                    }
                    ret += "|";
                }

                ret += '\n';
            }
            return ret;
        }
    }
}
