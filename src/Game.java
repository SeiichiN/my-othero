// Game.java

import java.util.ArrayList;

public class Game {
    public static void main (String[] args) {
        Field field = new Field( 6, 6 );
        field.prepare();
        field.putKoma( 2, 2, "B" );
        field.putKoma( 2, 3, "W" );
        field.putKoma( 3, 2, "W" );
        field.putKoma( 3, 3, "B" );
        ArrayList<Direction> moveList = new ArrayList <> ();
        moveList = field.move(3, 1, "B" );
        moveList.forEach( ele -> {
                System.out.println(ele.getX() + ":" + ele.getY());
            });
        field.feature();
    }
}



// 修正時刻: Sat Jul 18 08:27:49 2020
