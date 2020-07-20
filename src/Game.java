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

        Human human = new Human();
        Bot bot = new Bot();
        Action action = new Action();
        String next;
        
        Field field = new Field( COL, ROW );

        field.prepare();

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
        System.out.println("次の手番は B です。");
        next = "B";

        while (true) {
            action = human.selectMove( field );
            if (action.getX() == -1 && action.getY() == -1) {
                ;
            } else {
                field.putKoma( action );
                field.afterAction( action );
            }
            field.feature();
            next = (action.getPlayer().equals("B")) ? "W" : "B";
            System.out.println("次の手番は " + next + " です。");

            // Bot
            action = bot.selectMove( field, next );
            if (action.getX() == 0 && action.getY() == -1) {
                ;
            } else {
                System.out.println("action.getPlayer()" + action.getPlayer());
                field.putKoma( action );
                field.afterAction( action );
            }
            field.feature();
            next = (action.getPlayer().equals("B")) ? "W" : "B";
            System.out.println("次の手番は " + next + " です。");
        }
    }
}



// 修正時刻: Mon Jul 20 15:13:18 2020
