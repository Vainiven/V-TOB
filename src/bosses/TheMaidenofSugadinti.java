package bosses;

import java.util.ArrayList;
import java.util.List;

import Properties.PrayerGroups;
import bot.TOB.Loadouts;
import simple.api.actions.SimpleObjectActions;
import simple.api.coords.WorldArea;
import simple.api.coords.WorldPoint;

public class TheMaidenofSugadinti extends TobBoss {

	public TheMaidenofSugadinti() {
		super(new int[] { 8360 }, 12869);
	}

	@Override
	public boolean move() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getToNextRoom() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<PrayerGroups> getPrayers() {
		ArrayList<PrayerGroups> prayers = new ArrayList<>();
		prayers.add(PrayerGroups.MELEE_PRAYER);
		prayers.add(PrayerGroups.PROTECT_FROM_MELEE);
		return prayers;
	}

	@Override
	public Loadouts getLoadout() {
		return Loadouts.MELEE;
	}

	@Override
	public boolean goToBoss() {
		WorldPoint[] path = { new WorldPoint(3217, 4452, 0), new WorldPoint(3213, 4449, 0),
				new WorldPoint(3208, 4446, 0), new WorldPoint(3202, 4446, 0), new WorldPoint(3197, 4446, 0) };

		if (players.getRegionID() == 12869) {
			ctx.pathing.walkPath(path);
		} else if (players.getRegionID() == 12613 && (players.myPosition() != new WorldPoint(3186, 4446, 0))) {
			pathing.stepTo(3186, 4446, 0);
		} else if (!ctx.objects.populate().filter("Barrier").isEmpty()) {
			ctx.objects.nearest().next().interact(SimpleObjectActions.FIRST);
		} else if (!ctx.widgets.populate().filter(2459).isEmpty()) {
			players.startTobBoss();
		} else if (players.getRegionID() == 12613 && (players.myPosition() != new WorldPoint(3174, 4446, 0))) {
			pathing.stepTo(3174, 4446, 0);
		}
		return ctx.pathing.inArea(new WorldArea(new WorldPoint(3175, 4457, 0), new WorldPoint(3160, 4435, 0)));
	}

}
