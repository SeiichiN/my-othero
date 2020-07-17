// Game.java

public class Game {
    public static void main (String[] args) {
        Field field = new Field( 6, 6 );
        field.prepare();
        field.putKoma( 2, 2, "B" );
        field.putKoma( 2, 3, "W" );
        field.putKoma( 3, 2, "W" );
        field.putKoma( 3, 3, "B" );
        field.putKoma( 3, 1, "W" );
        if (field.move(3, 0, "B" )) {
            System.out.println("OK");
        } else {
            System.out.println("NG");
        }
        field.feature();
    }
}



// 修正時刻: Fri Jul 17 21:11:43 2020
