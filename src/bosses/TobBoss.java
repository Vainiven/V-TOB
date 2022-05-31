package bosses;

import Properties.PrayerGroups;
import bot.TOB.Loadouts;

public abstract class TobBoss {

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

	public abstract PrayerGroups[] getPrayers();

	public abstract Loadouts getLoadout();

}
