package bosses;

import java.awt.event.KeyEvent;
import java.util.List;

import Properties.PrayerGroups;
import bot.TOB.Loadouts;
import simple.api.actions.SimpleObjectActions;
import simple.api.coords.WorldArea;
import simple.api.coords.WorldPoint;

public class PestilentBloat extends TobBoss {
	
	WorldArea bossRegion = new WorldArea(new WorldPoint(3288, 4455, 0), new WorldPoint(3303, 4440, 0));

	WorldArea northRegion = new WorldArea(new WorldPoint(3292, 4451, 0), new WorldPoint(3299, 4455, 0));
	WorldArea eastRegion = new WorldArea(new WorldPoint(3298, 4444, 0), new WorldPoint(3303, 4451, 0));
	WorldArea westRegion = new WorldArea(new WorldPoint(3288, 4444, 0), new WorldPoint(3292, 4451, 0));
	WorldArea southRegion = new WorldArea(new WorldPoint(3292, 4444, 0), new WorldPoint(3299, 4440, 0));

	WorldPoint northTile = new WorldPoint(3292, 4450, 0);
	WorldPoint eastTile = new WorldPoint(3298, 4451, 0);
	WorldPoint southTile = new WorldPoint(3299, 4445, 0);
	WorldPoint westTile = new WorldPoint(3293, 4444, 0);

	public PestilentBloat(int[] ids, int[] region) {
		super(new int[] { 8359 }, 13125);
	}

	@Override
	public boolean move() {
		if (ctx.npcs.populate().filter(getBoss()).next().getAnimation() != 8082) {
			WorldPoint bossLocation = ctx.npcs.populate().filter(getIds()).next().getLocation();
			if (bossLocation.within(northRegion)) {
				ctx.pathing.step(northTile);
			} else if (bossLocation.within(eastRegion)) {
				ctx.pathing.step(eastTile);
			} else if (bossLocation.within(westRegion)) {
				ctx.pathing.step(westTile);
			} else if (bossLocation.within(southRegion)) {
				ctx.pathing.step(southTile);
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean getToNextRoom() {
		WorldPoint secondBarrier = new WorldPoint(3288, 4447, 0);
//		
//		WorldPoint[] path = { new WorldPoint(3190, 4446, 0), new WorldPoint(3191, 4440, 0),
//				new WorldPoint(3191, 4434, 0), new WorldPoint(3185, 4431, 0), new WorldPoint(3177, 4431, 0),
//				new WorldPoint(3176, 4424, 0) };
//
//		if (ctx.pathing.inArea(bossRegion) && !ctx.objects.populate().filter(32755).isEmpty()) {
//			ctx.objects.nearest().next().interact(SimpleObjectActions.FIRST);
//			ctx.onCondition(() -> ctx.dialogue.dialogueOpen());
//			ctx.keyboard.clickKey(KeyEvent.VK_1);
//		} else if (!ctx.objects.populate().filter("Formidable passage").isEmpty()) {
//			ctx.objects.nearest().next().interact(SimpleObjectActions.FIRST);
//		} else if (!ctx.pathing.inArea(bossRegion)) {
//			ctx.pathing.walkPath(path);
//		}
//		return false;
//	}
//		
//		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean goToBoss() {
		if (ctx.objects.populate().filter(32755).isEmpty() && !ctx.pathing.inArea(bossRegion)) {
			ctx.pathing.step(new WorldPoint(3305, 4448, 0));
		} else if (!ctx.objects.populate().filter(32755).isEmpty() && !ctx.pathing.inArea(bossRegion)) {
			ctx.objects.nearest().next().interact(SimpleObjectActions.FIRST);
			ctx.onCondition(() -> ctx.dialogue.dialogueOpen());
			if (ctx.npcs.populate().filter(8359).next().getLocation().within(southRegion)) {
				ctx.keyboard.clickKey(KeyEvent.VK_1);
			}
		}
		return ctx.pathing.inArea(bossRegion);
	}

	@Override
	public List<PrayerGroups> getPrayers() {
		return null;
	}

	@Override
	public Loadouts getLoadout() {
		return Loadouts.MELEE;
	}

}
