package scripts.bootsbuyer.nodes;

import org.tribot.api.General;
import org.tribot.api2007.Camera;
import org.tribot.api2007.Equipment;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Objects;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSObject;
import scripts.bootsbuyer.api.Node;
import scripts.bootsbuyer.api.Constants;

public class DoorOpener extends Node {
	public void execute() {
		RSObject[] gate = Objects.findNearest(10, 3726);
		RSObject[] gateOpen = Objects.findNearest(10, 3728);
		RSObject[] door = Objects.findNearest(5, 3745);
		if (gate.length > 0) { // check if there are actually doors within the 40 tile radius.
			RSObject target = gate[0]; //let's find the first door out of our nearest doors as our target
			if (target != null) { //we don't trust trilez and/or we don't know if the object still exists so let's make sure findNearest doesn't return null values.
				if (!target.isOnScreen()) { //the target isn't on screen so let's turn to it.
					Camera.setCameraAngle(0); // set the camera angle
					Camera.turnToTile(target.getPosition()); //turn so you can see the door
				} else {
					if (target.click("Open")) { //click open door, and make sure we clicked
						Camera.setCameraAngle(100); //set the camera back up
						General.sleep(50); //let's sleep for the heck of it since the OP wanted to.
					}
				}
			}
		}
		if (gateOpen.length > 0 && door.length > 0) { // check if there are actually doors within the 40 tile radius.
			RSObject target = door[0]; //let's find the first door out of our nearest doors as our target
			if (target != null) { //we don't trust trilez and/or we don't know if the object still exists so let's make sure findNearest doesn't return null values.
				if (!target.isOnScreen()) { //the target isn't on screen so let's turn to it.
					Camera.setCameraAngle(0); // set the camera angle
					Camera.turnToTile(target.getPosition()); //turn so you can see the door
				} else {
					if (target.click("Open")) { //click open door, and make sure we clicked
						Camera.setCameraAngle(100); //set the camera back up
						General.sleep(1000); //let's sleep for the heck of it since the OP wanted to.
					}
				}
			}
		}
	}
	public boolean validate() {
		return Inventory.getCount(Constants.Coins)>11 && Constants.MNTN_AREA.contains(Player.getPosition()) && !Constants.TENZING_AREA.contains(Player.getPosition());
	}
}
