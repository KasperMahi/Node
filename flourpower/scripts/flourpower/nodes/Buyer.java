package scripts.flourpower.nodes;

import org.tribot.api.Clicking;
import org.tribot.api.General;
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.NPCs;
import org.tribot.api2007.Player;
import org.tribot.api2007.WebWalking;
import org.tribot.api2007.WorldHopper;
import org.tribot.api2007.types.RSInterface;
import org.tribot.api2007.types.RSNPC;

import scripts.flourpower.api.Constants;
import scripts.flourpower.api.Node;
import scripts.flourpower.api.Vars;

public class Buyer extends Node {

	@Override
	public void execute() {
		RSNPC[] shopkeeper = NPCs.findNearest(2888);
		if(shopkeeper[0]!=null && !isOpen()) {
			shopkeeper[0].click("Trade");
			General.sleep(2000);
		}
		if(isOpen()) {
			Vars.flour = Interfaces.get(300,16,12);
			Vars.shopAmount = Vars.flour.getComponentStack();
			if(Vars.shopAmount == 0) {
				int world = WorldHopper.getRandomWorld(true);
				krydser();
				General.sleep(500);
				WorldHopper.changeWorld(world);
				General.sleep(5000);
			} 
			if(Vars.shopAmount>0) {
				buy(Vars.flour, 0,"Buy 50");
				General.sleep(200,700);
			}
		}
	}
	public static  boolean krydser() {
		RSInterface kryds = Interfaces.get(300,1,11);
		if (Interfaces.isInterfaceSubstantiated(kryds)) {
			return Clicking.click(kryds);
		}
		return false;
	}
	public static boolean isOpen(){return Interfaces.get(300, 16) != null;}
	public static boolean buy(RSInterface shopItem, int minStock, String ... amountToBuy) {

		String[] actions;
		if (Interfaces.isInterfaceSubstantiated(shopItem) && (actions = shopItem.getActions()) != null && actions.length > 0 && shopItem.getComponentStack() > minStock) {
			General.sleep(500, 1200);
			return Clicking.click(amountToBuy, shopItem);
		}
		return false;
	}
	@Override
	public boolean validate() {
		return !Inventory.isFull() && Constants.SHOP_AREA.contains(Player.getPosition()) && Inventory.getCount(995)>100;
	}
}
