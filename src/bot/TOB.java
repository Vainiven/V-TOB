package bot;

import java.util.HashMap;

import Bot.VScript;
import ClientContext.Players;
import GUI.GUI;
import GUI.GUISettingsProvider;
import Properties.EquipmentLoadout;
import Properties.InventoryChoice;
import Properties.InventoryLoadout;
import Properties.ItemGroups;
import Properties.Loadout;
import Properties.PrayerGroups;
import Properties.PrayerLoadout;
import bosses.TobBoss;
import paint.PaintProvider;
import simple.api.actions.SimpleNpcActions;
import simple.api.script.Category;
import simple.api.script.ScriptManifest;
import simple.api.wrappers.SimpleNpc;

@ScriptManifest(author = "Vainiven & FVZ", category = Category.MONEYMAKING, description = "Dikke lul drie bier script", discord = "Vainven#6986", name = "V-TOB", servers = {
		"Xeros" }, version = "0.1")

public class TOB extends VScript implements GUISettingsProvider {

	public enum Loadouts {
		MELEE, RANGED, MAGIC
	}

	private final PrayerLoadout prayerLoadout = new PrayerLoadout(PrayerGroups.MELEE_PRAYER, PrayerGroups.RANGED_PRAYER,
			PrayerGroups.MAGIC_PRAYER);
	private final InventoryLoadout inventoryLoadout = new InventoryLoadout(
			new InventoryChoice(ItemGroups.MELEE_POTION, 1, 0), new InventoryChoice(ItemGroups.RANGED_POTION, 1, 0),
			new InventoryChoice(ItemGroups.MAGIC_POTION, 1, 0), new InventoryChoice(ItemGroups.FOOD, 19, 4),
			new InventoryChoice(ItemGroups.PRAYER_POTION, 5, 0), new InventoryChoice(ItemGroups.PET, 1, 0),
			new InventoryChoice(ItemGroups.SPEC_WEAPONS, 1, 0));
	private final HashMap<Loadouts, EquipmentLoadout> loadouts = new HashMap<>();
	private final GUI gui;
	private TobBoss[] bosses = {};
	private TobBoss currentBoss = getBoss();

	public TOB() {
		super(null);
		EquipmentLoadout meleeLoadout = new EquipmentLoadout(ItemGroups.getEquipment());
		EquipmentLoadout rangedLoadout = new EquipmentLoadout(ItemGroups.getEquipment());
		EquipmentLoadout magicLoadout = new EquipmentLoadout(ItemGroups.getEquipment());
		gui = new GUI(this, new GUISettingsProvider() {

			@Override
			public Loadout<?, ?, ?>[] getLoadouts() {
				return new Loadout<?, ?, ?>[] { meleeLoadout };
			}

			@Override
			public String getName() {
				return "Melee Loadout";
			}

		}, new GUISettingsProvider() {

			@Override
			public Loadout<?, ?, ?>[] getLoadouts() {
				return new Loadout<?, ?, ?>[] { magicLoadout };
			}

			@Override
			public String getName() {
				return "Magic Loadout";
			}

		}, new GUISettingsProvider() {

			@Override
			public Loadout<?, ?, ?>[] getLoadouts() {
				return new Loadout<?, ?, ?>[] { rangedLoadout };
			}

			@Override
			public String getName() {
				return "Ranged Loadout";
			}
		});
		loadouts.put(Loadouts.MELEE, meleeLoadout);
		loadouts.put(Loadouts.MAGIC, magicLoadout);
		loadouts.put(Loadouts.RANGED, rangedLoadout);
		bank.forceBank();
	}

	@Override
	public Loadout<?, ?, ?>[] getLoadouts() {
		return new Loadout<?, ?, ?>[] { prayerLoadout, inventoryLoadout };
	}

	@Override
	protected PaintProvider[] getPaintProviders() {
		return new PaintProvider[] {};
	}

	@Override
	public void onProcess() {
		if (!gui.isVisible()) {
			if (currentBoss == null) {
//				if (players.isAtTOB()) {
				if (!players.useHealingBox() && !bank.bank(inventoryLoadout, loadouts.get(Loadouts.MELEE), false)
						&& !bank.withdraw(loadouts.get(Loadouts.RANGED))
						&& !bank.withdraw(loadouts.get(Loadouts.MAGIC))) {
					if (ctx.bank.bankOpen()) {
						ctx.bank.closeBank();
					} else if (players.inParty("ToB Party (")) {
						if (players.getPartyLeaderName()) {
							players.handleToBPartyDialogue();
						}
					} else {
						players.createParty();
					}
				}
//				} else {
//					ctx.teleporter.teleportStringPath("Minigames", "Theatre of Blood");
			} else {
				// Room fight
				if (!ctx.npcs.populate().filter(currentBoss.getIds()).isEmpty()) {
					prayers.usePrayers(currentBoss.getPrayers(), prayerLoadout, false);
					inventory.dropEmptyVials();
					inventory.usePotions();
					inventory.eat();
					if (!currentBoss.move() && !ctx.npcs.peekNext().equals(ctx.players.getLocal().getInteracting())) {
						ctx.npcs.next().interact(SimpleNpcActions.ATTACK);
					}
				} else {
					currentBoss.getToNextRoom();
					currentBoss = getBoss();
				}
			}
		}
	}

	private TobBoss getBoss() {
		for (TobBoss boss : bosses) {
			if (players.inRegion(boss.getRegion())) {
				return boss;
			}
		}
		return null;
	}
}