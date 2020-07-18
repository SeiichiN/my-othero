// Game.java

import java.util.ArrayList;
import java.util.Map;

import util.GetUserInput;
import util.GetConf;

public class Game {
    public static void main (String[] args) {
        Map<String, String> mapList = new GetConf("game.conf").load();
        final int COL = Integer.parseInt( mapList.get("col") );
        final int ROW = Integer.parseInt( mapList.get("row") );
        Field field = new Field( COL, ROW );
        field.prepare();
        field.putKoma( 2, 2, "B" );
        field.putKoma( 2, 3, "W" );
        field.putKoma( 3, 2, "W" );
        field.putKoma( 3, 3, "B" );
        field.putKoma( 3, 1, "B" );
        ArrayList<Direction> moveList = new ArrayList <> ();
        String strX = GetUserInput.get("x> ");
        String strY = GetUserInput.get("y> ");
        String userState = GetUserInput.get("B / W> ").toUpperCase();
        int userX = Integer.parseInt( strX );
        int userY = Integer.parseInt( strY );
        moveList = field.move( userX, userY, userState );
        moveList.forEach( ele -> {
                System.out.println(ele.getX() + ":" + ele.getY());
            });
        field.feature();
    }
}



// 修正時刻: Sat Jul 18 21:16:55 2020
