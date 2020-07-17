// Game.java

public class Game {
    public static void main (String[] args) {
        Field field = new Field( 6, 6 );
        field.prepare();
        field.putKoma( 2, 2, "B" );
        field.putKoma( 2, 3, "W" );
        field.putKoma( 3, 2, "W" );
        field.putKoma( 3, 3, "B" );
        if (field.canMove( 4, 1, "B" )) {
            System.out.println("OK");
        } else {
            System.out.println("NG");
        }
        field.feature();
    }
}



// 修正時刻: Fri Jul 17 16:33:39 2020
