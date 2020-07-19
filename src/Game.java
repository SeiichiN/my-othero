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

        Action action = new Action();
        action.setX(2);
        action.setY(2);
        action.setPlayer("B");
        field.putKoma( action );
		action.setX(2);
		action.setY(3);
		action.setPlayer("W");
        field.putKoma( action );
		action.setX(3);
		action.setY(2);
		action.setPlayer("W");
        field.putKoma( action );
		action.setX(3);
		action.setY(3);
		action.setPlayer("B");
        field.putKoma( action );
        field.feature();
        Human human = new Human();

        while (true) {
            action = human.selectMove( field );
            field.putKoma( action );
			field.afterAction( action );
            field.feature();
            String next = (action.getPlayer().equals("B")) ? "W" : "B";
            System.out.println("次の手番は " + next + " です。");
        }
    }
}



// 修正時刻: Sun Jul 19 18:08:34 2020
