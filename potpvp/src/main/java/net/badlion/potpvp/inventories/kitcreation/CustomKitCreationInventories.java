package net.badlion.potpvp.inventories.kitcreation;

import net.badlion.gberry.utils.BukkitUtil;
import net.badlion.potpvp.Group;
import net.badlion.potpvp.GroupStateMachine;
import net.badlion.potpvp.PotPvP;
import net.badlion.potpvp.helpers.KitCreationHelper;
import net.badlion.potpvp.helpers.KitHelper;
import net.badlion.smellyinventory.SmellyInventory;
import net.badlion.potpvp.managers.PotPvPPlayerManager;
import net.badlion.potpvp.rulesets.CustomRuleSet;
import net.badlion.potpvp.rulesets.KitRuleSet;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

public class CustomKitCreationInventories {

	private static SmellyInventory selectCustomKitSaveInventory;
	private static SmellyInventory selectCustomKitLoadInventory;

	private static SmellyInventory selectEventKitSaveInventory;
	private static SmellyInventory selectEventKitLoadInventory;

	public static void initialize() {
		// Create select custom kit save inventory
		SmellyInventory selectCustomKitSaveInventory = new SmellyInventory(new CustomKitCreationSaveScreenHandler(),
				27, ChatColor.AQUA + ChatColor.BOLD.toString() + "Select Custom Kit to Save");
		for (int i = 1; i < 21; i++) {
			selectCustomKitSaveInventory.getMainInventory().addItem(KitHelper.createCustomKitInventoryItem(i));
		}
		CustomKitCreationInventories.selectCustomKitSaveInventory = selectCustomKitSaveInventory;

		// Create select custom kit load inventory
		SmellyInventory selectCustomKitLoadInventory = new SmellyInventory(new CustomKitCreationLoadScreenHandler(),
				27, ChatColor.AQUA + ChatColor.BOLD.toString() + "Select Custom Kit to Load");
		for (int i = 1; i < 21; i++) {
			selectCustomKitLoadInventory.getMainInventory().addItem(KitHelper.createCustomKitInventoryItem(i));
		}
		CustomKitCreationInventories.selectCustomKitLoadInventory = selectCustomKitLoadInventory;

		// Create select event kit save inventory
		SmellyInventory selectEventKitSaveInventory = new SmellyInventory(new EventKitCreationSaveScreenHandler(),
				27, ChatColor.AQUA + ChatColor.BOLD.toString() + "Select Event Kit to Save");
		for (int i = 1; i < 21; i++) {
			selectEventKitSaveInventory.getMainInventory().addItem(KitHelper.createEventKitInventoryItem(i));
		}
		CustomKitCreationInventories.selectEventKitSaveInventory = selectEventKitSaveInventory;

		// Create select event kit load inventory
		SmellyInventory selectEventKitLoadInventory = new SmellyInventory(new EventKitCreationLoadScreenHandler(),
				27, ChatColor.AQUA + ChatColor.BOLD.toString() + "Select Event Kit to Load");
		for (int i = 1; i < 21; i++) {
			selectEventKitLoadInventory.getMainInventory().addItem(KitHelper.createEventKitInventoryItem(i));
		}
		CustomKitCreationInventories.selectEventKitLoadInventory = selectEventKitLoadInventory;
	}

	public static void openCustomKitSaveInventory(Player player, KitRuleSet kitRuleSet) {
		if (kitRuleSet.getClass() == CustomRuleSet.class) {
			PotPvPPlayerManager.addDebug(player, "Open custom kit save inventory");

			BukkitUtil.openInventory(player, CustomKitCreationInventories.selectCustomKitSaveInventory.getMainInventory());
		} else {
			PotPvPPlayerManager.addDebug(player, "Open event kit save inventory");

			BukkitUtil.openInventory(player, CustomKitCreationInventories.selectEventKitSaveInventory.getMainInventory());
		}
	}

	public static void openCustomKitLoadInventory(Player player, KitRuleSet kitRuleSet) {
		if (kitRuleSet.getClass() == CustomRuleSet.class) {
			PotPvPPlayerManager.addDebug(player, "Open custom kit load inventory");

			BukkitUtil.openInventory(player, CustomKitCreationInventories.selectCustomKitLoadInventory.getMainInventory());
		} else {
			PotPvPPlayerManager.addDebug(player, "Open event kit save inventory");

			BukkitUtil.openInventory(player, CustomKitCreationInventories.selectEventKitLoadInventory.getMainInventory());
		}
	}

	private static class CustomKitCreationSaveScreenHandler implements SmellyInventory.SmellyInventoryHandler {

		@Override
		public void handleInventoryClickEvent(SmellyInventory.FakeHolder fakeHolder, Player player, InventoryClickEvent event, ItemStack item, int slot) {
			if (event.getClick().equals(ClickType.MIDDLE)) { // Preview kit
				KitHelper.openKitPreviewInventory(CustomKitCreationInventories.selectCustomKitLoadInventory, event.getView().getTopInventory(),
						player, item);
			} else {
				KitCreationHelper.KitCreator kitCreator = GroupStateMachine.kitCreationState.getKitCreator(player);
				KitHelper.saveKit(player, kitCreator.getKitRuleSet(), KitHelper.getCustomKitNumberFromItem(item));

				BukkitUtil.closeInventory(player);
			}
		}

		@Override
		public void handleInventoryCloseEvent(SmellyInventory.FakeHolder fakeHolder, Player player, InventoryCloseEvent event) {

		}

	}

	private static class CustomKitCreationLoadScreenHandler implements SmellyInventory.SmellyInventoryHandler {

		@Override
		public void handleInventoryClickEvent(SmellyInventory.FakeHolder fakeHolder, Player player, InventoryClickEvent event, ItemStack item, int slot) {
			if (event.getClick().equals(ClickType.MIDDLE)) { // Preview kit
				KitHelper.openKitPreviewInventory(CustomKitCreationInventories.selectCustomKitLoadInventory, event.getView().getTopInventory(),
						player, item);
			} else {
				KitCreationHelper.KitCreator kitCreator = GroupStateMachine.kitCreationState.getKitCreator(player);
				KitHelper.loadKit(player, kitCreator.getKitRuleSet(), KitHelper.getCustomKitNumberFromItem(item));

				BukkitUtil.closeInventory(player);
			}
		}

		@Override
		public void handleInventoryCloseEvent(SmellyInventory.FakeHolder fakeHolder, Player player, InventoryCloseEvent event) {

		}

	}

	private static class EventKitCreationSaveScreenHandler implements SmellyInventory.SmellyInventoryHandler {

		@Override
		public void handleInventoryClickEvent(SmellyInventory.FakeHolder fakeHolder, Player player, InventoryClickEvent event, ItemStack item, int slot) {
			if (event.getClick().equals(ClickType.MIDDLE)) { // Preview kit
				KitHelper.openKitPreviewInventory(CustomKitCreationInventories.selectCustomKitLoadInventory, event.getView().getTopInventory(),
						player, item);
			} else {
				KitCreationHelper.KitCreator kitCreator = GroupStateMachine.kitCreationState.getKitCreator(player);
				KitHelper.saveKit(player, kitCreator.getKitRuleSet(), KitHelper.getCustomKitNumberFromItem(item));

				BukkitUtil.closeInventory(player);
			}
		}

		@Override
		public void handleInventoryCloseEvent(SmellyInventory.FakeHolder fakeHolder, Player player, InventoryCloseEvent event) {

		}

	}

	private static class EventKitCreationLoadScreenHandler implements SmellyInventory.SmellyInventoryHandler {

		@Override
		public void handleInventoryClickEvent(SmellyInventory.FakeHolder fakeHolder, Player player, InventoryClickEvent event, ItemStack item, int slot) {
			if (event.getClick().equals(ClickType.MIDDLE)) { // Preview kit
				KitHelper.openKitPreviewInventory(CustomKitCreationInventories.selectCustomKitLoadInventory, event.getView().getTopInventory(),
						player, item);
			} else {
				KitCreationHelper.KitCreator kitCreator = GroupStateMachine.kitCreationState.getKitCreator(player);
				KitHelper.loadKit(player, kitCreator.getKitRuleSet(), KitHelper.getCustomKitNumberFromItem(item));

				BukkitUtil.closeInventory(player);
			}
		}

		@Override
		public void handleInventoryCloseEvent(SmellyInventory.FakeHolder fakeHolder, Player player, InventoryCloseEvent event) {

		}

	}

}
