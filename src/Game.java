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
        field.feature();
        Human human = new Human();
        human.selectMove( field );
    }
}



// 修正時刻: Sun Jul 19 06:25:48 2020

