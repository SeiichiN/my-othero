// Human.java

import java.util.ArrayList;

import util.GetUserInput;

public class Human {
    public Human () {}
    
    public Action selectMove (Field field) {
		Action action;
        ArrayList<Direction> DirectionList = new ArrayList <> ();
        String strX = GetUserInput.get("x> ");
        String strY = GetUserInput.get("y> ");
        String userState = GetUserInput.get("B / W> ").toUpperCase();
        int userX = Integer.parseInt( strX );
        int userY = Integer.parseInt( strY );
		action = new Action( userX, userY, userState );
        DirectionList = field.move( action );
        DirectionList.forEach( ele -> {
                System.out.println(ele.getX() + ":" + ele.getY() + ":" + ele.getPoint());
            });
        action = new Action( userX, userY, userState );
        return action;
    }
}

// 修正時刻: Sun Jul 19 07:14:21 2020

