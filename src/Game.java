// Game.java

public class Game {
    public static void main (String[] args) {
        Field field = new Field( 6, 6 );
        field.prepare();
        field.putKoma( 2, 2, "B" );
        field.feature();
    }
}



// 修正時刻: Thu Jul 16 21:49:02 2020
