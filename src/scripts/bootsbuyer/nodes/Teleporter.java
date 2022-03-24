package scripts.bootsbuyer.nodes;

import org.tribot.api.General;
import org.tribot.api2007.Equipment;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSItem;
import scripts.bootsbuyer.api.Node;
import scripts.bootsbuyer.api.Constants;

public class Teleporter extends Node{
	public void execute() {
		RSItem neckCheck = Equipment.SLOTS.AMULET.getItem();
		if(neckCheck!=null) {
			neckCheck.click("Burthorpe");
			General.sleep(3000);
		}
	}
	public boolean validate() {
		return Constants.BANK_AREA.contains(Player.getPosition()) && Inventory.getCount(Constants.Coins)>=336 && Equipment.SLOTS.RING.getItem()!=null && Equipment.SLOTS.AMULET.getItem()!=null;

	}
}
