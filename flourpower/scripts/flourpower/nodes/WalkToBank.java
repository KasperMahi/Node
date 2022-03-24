package scripts.flourpower.nodes;



import org.tribot.api2007.Inventory;

import org.tribot.api2007.Player;
import org.tribot.api2007.WebWalking;
import org.tribot.api2007.types.RSTile;

import scripts.flourpower.api.Constants;
import scripts.flourpower.api.Node;

public class WalkToBank extends Node {

	@Override
	public void execute() {

		if(Player.getPosition()!=Constants.Chest){
			WebWalking.walkTo(Constants.Chest);
		}
	}
	@Override
	public boolean validate() { 
		return Inventory.isFull() && Player.getPosition() != new RSTile(2661, 3162);
	}
}
