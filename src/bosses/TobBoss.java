package bosses;

import java.awt.event.KeyEvent;
import java.util.List;

import ClientContext.Pathing;
import ClientContext.Players;
import Properties.PrayerGroups;
import bot.TOB.Loadouts;
import simple.api.ClientContext;
import simple.api.actions.SimpleObjectActions;
import simple.api.wrappers.SimpleNpc;

public abstract class TobBoss {

	private boolean dead;
	Pathing pathing = new Pathing();
	Players players = new Players();
	ClientContext ctx = ClientContext.instance();
	private SimpleNpc currentBoss;

	public TobBoss(int[] ids, int... region) {
		this.ids = ids;
		this.region = region;
	}

	private final int[] ids;

	public int[] getIds() {
		return ids;
	}

	private final int[] region;

	public int[] getRegions() {
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

	public SimpleNpc getBoss() {
		if (currentBoss == null) {
			currentBoss = ctx.npcs.populate().filter(getIds()).filterHasAction("Attack").nearest().next();
		} else if (currentBoss.isDead() || !ctx.npcs.populate().contains(currentBoss)) {
			currentBoss = ctx.npcs.populate().filter(getIds()).filterHasAction("Attack").nearest().next();
			if (currentBoss == null) {
				dead = true;
			}
		}
		return currentBoss;
	}
	
	public boolean isDead() {
		return dead;
	}
	
	public void handleBarrier() {
		ctx.objects.nearest().next().interact(SimpleObjectActions.FIRST);
		ctx.onCondition(() -> ctx.dialogue.dialogueOpen());
		ctx.keyboard.clickKey(KeyEvent.VK_1);
	}
	

}
