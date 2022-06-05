package bosses;

import java.util.List;

import ClientContext.Pathing;
import ClientContext.Players;
import Properties.PrayerGroups;
import bot.TOB.Loadouts;
import simple.api.ClientContext;

public abstract class TobBoss {
	
	Pathing pathing = new Pathing();
	Players players = new Players();
	ClientContext ctx = ClientContext.instance();

	public TobBoss(int[] ids, int region) {
		this.ids = ids;
		this.region = region;
	}

	private final int[] ids;

	public int[] getIds() {
		return ids;
	}

	private final int region;

	public int getRegion() {
		return region;
	}

	/**
	 * 
	 * @return True when needing to move or moving
	 */
	public abstract boolean move();

	/**
	 * 
	 * @return True if getting to next room. False if the next room has been reached
	 */
	public abstract boolean getToNextRoom();
	/**
	 * 
	 * @return True if getting to next boss. False if the next boss has been reached
	 */
	public abstract boolean goToBoss();

	public abstract List<PrayerGroups> getPrayers();

	public abstract Loadouts getLoadout();

}
