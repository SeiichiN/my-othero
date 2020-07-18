// Human.java

import util.GetUserInput;
import util.GetConf;

public class Human {
    Map<String, String> mapList = new GetConf("game.conf").load();
    final int COL = Integer.parseInt( mapList.get("col") );
    final int ROW = Integer.parseInt( mapList.get("row") );
    Field field = new Field( COL, ROW );
    field.prepare();

    
}


// 修正時刻: Sat Jul 18 21:29:02 2020
