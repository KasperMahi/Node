package scripts.bootsbuyer.nodes;

import org.tribot.api.General;
import org.tribot.api2007.Equipment;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Player;
import org.tribot.api2007.Walking;
import org.tribot.api2007.WebWalking;
import org.tribot.api2007.ext.Filters;

import scripts.bootsbuyer.api.Constants;
import scripts.bootsbuyer.api.Node;

public class Walker extends Node {

	@Override
	public void execute() {
		Inventory.open();
		while(Player.getPosition().getX()>2840) {
			Walking.blindWalkTo(Constants.MID_AREA.getRandomTile());
		}
		General.sleep(200,500);
		Walking.blindWalkTo(Constants.MNTN_AREA.getRandomTile());
	//	if(Constants.MID_AREA.contains(Player.getPosition())) {
		//	Walking.blindWalkTo(Constants.Midway);
	//		General.sleep(300,500);
	//		Walking.blindWalkTo(Constants.Port);
	//		General.sleep(300,500);
	//	}
	}

	@Override
	public boolean validate() {
		return !Constants.BANK_AREA.contains(Player.getPosition()) && Inventory.getCount(Constants.Coins)>=336 && Equipment.isEquipped(Constants.Rings) && !Constants.MNTN_AREA.contains(Player.getPosition()) && !Constants.TENZING_AREA.contains(Player.getPosition()) ;
	}
}
//&& Constants.START_AREA.contains(Player.getPosition()) | Constants.MID_AREA.contains(Player.getPosition()) | Player.getPosition().distanceTo(Constants.MID_AREA.getRandomTile())<100