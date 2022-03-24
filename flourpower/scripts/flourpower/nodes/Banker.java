package scripts.flourpower.nodes;


import org.tribot.api.General;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Inventory;

import org.tribot.api2007.Player;

import scripts.flourpower.api.Constants;
import scripts.flourpower.api.Node;
import scripts.flourpower.api.Vars;

public class Banker extends Node {

	@Override
	public void execute() {
		if(!Banking.isBankScreenOpen()) {
		Banking.openBank();
		General.sleep(250);
		}
		if(Inventory.getCount(995)<=1000) {
			Banking.withdraw(10000, 995);
			General.sleep(250);
		}
		if(Inventory.isFull()) {
			Vars.totalBought = Vars.totalBought + Inventory.getCount(1933);
			Banking.depositAllExcept(995);
			General.sleep(250);
			Banking.close();
		}
		
	}

	@Override
	public boolean validate() {
		return Inventory.isFull();
	}
}
