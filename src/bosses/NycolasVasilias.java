package bosses;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import Properties.PrayerGroups;
import bot.TOB;
import bot.TOB.Loadouts;
import simple.api.actions.SimpleObjectActions;
import simple.api.coords.WorldArea;
import simple.api.coords.WorldPoint;

public class NycolasVasilias extends TobBoss {
	
	
//	8355 melee
//	8356 mage
//	8357 ranged
	
	TOB tob = new TOB();

	WorldArea bossRegion = new WorldArea(new WorldPoint(3289, 4255, 0), new WorldPoint(3302, 4242, 0));

	public NycolasVasilias() {
		super(new int[] { 8356, 8357, 8355 }, 13122);
	}

	@Override
	public boolean move() {
		return false;
	}

	@Override
	public boolean getToNextRoom() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean goToBoss() {
		WorldPoint[] path = { new WorldPoint(3295, 4276, 0), new WorldPoint(3295, 4269, 0),
				new WorldPoint(3295, 4261, 0) };

		if (ctx.objects.populate().filter(32755).isEmpty() && !ctx.pathing.inArea(bossRegion)) {
			ctx.pathing.walkPath(path);
		} else if (!ctx.objects.populate().filter(32755).isEmpty() && !ctx.pathing.inArea(bossRegion)) {
			tob.handleBarrier();
		}
		return ctx.pathing.inArea(bossRegion);
	}

	@Override
	public List<PrayerGroups> getPrayers() {
		ArrayList<PrayerGroups> prayers = new ArrayList<>();
		prayers.add(PrayerGroups.MELEE_PRAYER);
		return prayers;
		//TODO ADD PRAYER SWAPS
	}

	@Override
	public Loadouts getLoadout() {
		// TODO Auto-generated method stub
		return null;
	}

}
