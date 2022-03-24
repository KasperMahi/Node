package scripts.bootsbuyer.nodes;

import org.tribot.api.General;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Equipment;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Objects;
import org.tribot.api2007.Player;
import org.tribot.api2007.WebWalking;
import org.tribot.api2007.ext.Filters;
import org.tribot.api2007.types.RSItem;
import org.tribot.api2007.types.RSObject;

import scripts.bootsbuyer.api.Constants;
import scripts.bootsbuyer.api.Node;
import scripts.bootsbuyer.api.Vars;

public class BankBoots extends Node {
	@Override
	public void execute() {
		
		if(!Constants.BANK_AREA.contains(Player.getPosition())){
			Equipment.SLOTS.RING.getItem().click("Castle Wars");
			General.sleep(3000);
			Inventory.open();
		} else {
			RSItem[] ring = Equipment.find(Filters.Items.idEquals(Constants.Rings));
			RSItem[] amulet = Equipment.find(Filters.Items.idEquals(Constants.Amulets));
			WebWalking.walkToBank();
			Banking.openBank();
			Vars.totalBought = Vars.totalBought + Inventory.getCount(3105);
			Banking.depositAllExcept(Constants.Coins);
			if(Inventory.getCount(Constants.Coins)<336) {
				Banking.withdraw(336-Inventory.getCount(Constants.Coins), Constants.Coins);
			}
			if(ring.length == 0) {
				Banking.withdraw(1,"Ring of dueling(8)");
				General.sleep(1000);
			}
			if(amulet.length == 0) {
				Banking.withdraw(1,"Games necklace(8)");
				General.sleep(1000);
			}
			Banking.close();
			RSItem[] ringInv = Inventory.find("Ring of dueling(8)");
			RSItem[] amuletInv = Inventory.find("Games necklace(8)");
			if(ringInv.length>0) {
				ringInv[0].click("Wear");
				General.sleep(500);
			}
			if(amuletInv.length>0) {
				amuletInv[0].click("Wear");
				General.sleep(500);
			}
		}
	}

	@Override
	public boolean validate() {
		return Inventory.isFull() || Inventory.getAllList().size()==0;
	}

}