package scripts.bootsbuyer.nodes;

import java.awt.event.KeyEvent;
import java.util.*;
import java.io.PrintStream;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.input.Keyboard;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.NPCChat;
import org.tribot.api2007.NPCs;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSNPC;

import scripts.bootsbuyer.api.Constants;
import scripts.bootsbuyer.ClimbingBootsBuyer;
import scripts.bootsbuyer.api.Node;
public class BootsBuyer extends Node{
	public String name;
	public int counter;
	public void execute() {
		//if(isTalking()==false) {
		RSNPC[] tenzing = NPCs.findNearest(4094);

		System.out.println(name);
		if(name==null) {
			tenzing[0].click("Talk-to");
			General.sleep(1000);
			name = NPCChat.getName();
			System.out.println(NPCChat.getName() + " " + name);
		}
		else { 
			while(!Inventory.isFull() && name!=null && counter <100 && Constants.TENZING_AREA.contains(Player.getPosition())) {
				Keyboard.holdKey(KeyEvent.CHAR_UNDEFINED, KeyEvent.VK_SPACE, () -> NPCChat.getOptions() == null && NPCChat.getMessage()==null);
				General.sleep(150,200);
				if(NPCChat.getOptions() != null)  {
					General.sleep(50);
					Keyboard.typeKeys('1');
					General.sleep(200,300);
				}
				counter++;
			} name = null; counter = 0;
		}

		//}
	}
	//public static boolean isTalking()
	//{
	//	if (NPCChat.getName() == null && NPCChat.getClickContinueInterface() == null && NPCChat.getSelectOptionInterface() == null)
	//	return false;		

	//	return true;	}
	public boolean validate() {
		return Inventory.getCount(Constants.Coins)>11 && !Inventory.isFull() && Constants.TENZING_AREA.contains(Player.getPosition());
	}

}