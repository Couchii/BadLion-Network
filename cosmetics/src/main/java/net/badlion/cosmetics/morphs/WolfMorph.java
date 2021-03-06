package net.badlion.cosmetics.morphs;

import net.badlion.common.libraries.EnumCommon;
import net.badlion.cosmetics.utils.MorphUtil;
import net.badlion.gberry.utils.ItemStackUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

public class WolfMorph extends Morph {

    public WolfMorph() {
        super("dog_morph", ItemRarity.SUPER_COMMON, ItemStackUtil.createItem(Material.MONSTER_EGG, 1, (byte) 95, ChatColor.GREEN + "Dog Morph", ChatColor.GRAY + "Left click to bark."));
        this.morphType = MorphUtil.MorphType.WOLF;
    }

    @Override
    protected void handleLeftClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        player.playSound(player.getLocation(), EnumCommon.getEnumValueOf(Sound.class, "WOLF_BARK", "ENTITY_WOLF_HOWL"), 1.0f, 1.0f);
    }

    @Override
    public void setMorph(Player player) {
        new MorphUtil(MorphUtil.MorphType.WOLF, player).sendServerSetMorph();
    }
}
