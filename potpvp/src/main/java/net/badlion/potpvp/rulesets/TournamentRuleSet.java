package net.badlion.potpvp.rulesets;

import net.badlion.potpvp.managers.ArenaManager;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class TournamentRuleSet extends KitRuleSet {

    public TournamentRuleSet(int id, String name) {
        super(id, name, ArenaManager.ArenaType.PEARL, true, false);

	    // Create default armor kit
	    this.defaultArmorKit[3] = new ItemStack(Material.IRON_HELMET);
	    this.defaultArmorKit[2] = new ItemStack(Material.IRON_CHESTPLATE);
	    this.defaultArmorKit[1] = new ItemStack(Material.IRON_LEGGINGS);
	    this.defaultArmorKit[0] = new ItemStack(Material.IRON_BOOTS);

	    // Create default inventory kit
	    this.defaultInventoryKit[0] = new ItemStack(Material.IRON_SWORD);
    }

}