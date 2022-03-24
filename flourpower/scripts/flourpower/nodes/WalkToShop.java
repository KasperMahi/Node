package scripts.flourpower.nodes;


import org.tribot.api.General;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Inventory;

import org.tribot.api2007.Player;
import org.tribot.api2007.Walking;
import org.tribot.api2007.WebWalking;

import scripts.flourpower.api.Constants;
import scripts.flourpower.api.Node;
import scripts.flourpower.api.Vars;

public class WalkToShop extends Node {	
	@Override
	public void execute() {
		Walking.blindWalkTo(Constants.SHOP_AREA2.getRandomTile());
		General.sleep(1000);
	}

	@Override
	public boolean validate() {
		return !Inventory.isFull() && !Constants.SHOP_AREA.contains(Player.getPosition());
	}
}
