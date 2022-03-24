package scripts.bootsbuyer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api2007.Login;
import org.tribot.api2007.NPCChat;
import org.tribot.script.Script;
import org.tribot.script.ScriptManifest;
import org.tribot.script.interfaces.Painting;

import scripts.bootsbuyer.api.Node;
import scripts.bootsbuyer.api.Vars;
import scripts.bootsbuyer.nodes.Walker;
import scripts.bootsbuyer.nodes.BankBoots;
import scripts.bootsbuyer.nodes.BootsBuyer;
import scripts.bootsbuyer.nodes.DoorOpener;
import scripts.bootsbuyer.nodes.Talker;
import scripts.bootsbuyer.nodes.Teleporter;

@ScriptManifest (authors = {"Mahizzta"}, category = "Misc", name = "ClimbingBootsBuyer")
public class ClimbingBootsBuyer extends Script implements Painting {
	private final List<Node> nodes = new ArrayList<>();
	 
	@Override
	public void run() {
		if(Login.getLoginState() != Login.STATE.INGAME) {
			Login.login();
			General.sleep(5000);
		}
		Collections.addAll(nodes, new Teleporter(), new BankBoots(), new Walker(), new DoorOpener(), new BootsBuyer());
		loop(20, 40);
	}

	private void loop(int min, int max) {
		while (true) {
			for (final Node node : nodes) {
				if (node.validate()) {
					node.execute();
					sleep(General.random(min, max));	//time in between executing nodes
				}
			}
		}
	}


	private static final long startTime = System.currentTimeMillis();
	Font font = new Font("Verdana", Font.BOLD, 14);
	public long perHour(int value, long totalT) { return (long) (value * 3600000D / totalT); }
	@Override
	public void onPaint(Graphics g) {

		long timeRan = System.currentTimeMillis() - startTime;
		g.setFont(font);

		g.setColor(new Color(255,255,255));
		g.drawString("Runtime: " + Timing.msToString(timeRan), 340, 300);
		g.drawString("Total Boots: " + Vars.totalBought, 340, 320);
		g.drawString("Boots per hour: " + perHour(Vars.totalBought, timeRan), 340, 340);
	}
}