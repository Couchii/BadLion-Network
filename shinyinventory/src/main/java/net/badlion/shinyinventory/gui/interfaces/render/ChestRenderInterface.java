package net.badlion.shinyinventory.gui.interfaces.render;

import net.badlion.shinyinventory.gui.Interface;
import net.badlion.shinyinventory.gui.buttons.Button;
import net.badlion.shinyinventory.gui.interfaces.ChestInterface;
import net.badlion.shinyinventory.utils.ItemCreator;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ShinyDialga45 on 9/23/2015.
 */
public class ChestRenderInterface extends ChestInterface {

    private Coordinate origin;

    public ChestRenderInterface(Player player, List<Button> buttons, int size, String title, Interface parent) {
        super(player, buttons, size, title, parent);
        this.origin = new Coordinate(0, 0);
    }

    public int getRenderSlot(Coordinate index) {
        double currentX = getOrigin().getX();
        double currentY = getOrigin().getY();
        for (int i = 0; i < getSize(); i++) {
            Coordinate coordinate = new Coordinate(currentX, currentY);
            //Bukkit.broadcastMessage(index.getX() + " " + coordinate.getX() + " " + getOrigin().getX());
            //Bukkit.broadcastMessage(index.getY() + " " + coordinate.getY() + " " + getOrigin().getY());
            if (index.getX() == coordinate.getX() && index.getY() == coordinate.getY()) {
                return i;
            }
            if (((i + 1) % 9) == 0) {
                currentX = getOrigin().getX();
                currentY--;
            } else {
                currentX++;
            }
        }
        return -1;
    }

    public Coordinate getOrigin() {
        return this.origin != null ? this.origin : new Coordinate(0, 0);
    }

    public void setOrigin(Coordinate origin) {
        this.origin = origin;
    }

    @Override
    public void updateButtons() {
        List<Button> buttons = new ArrayList<>();
        List<Coordinate> coordinates = new ArrayList<>();
        for (final Coordinate coordinate : coordinates) {
            int renderSlot = getRenderSlot(coordinate);
            if (renderSlot >= 0) {
                Button button = new Button(new ItemCreator(Material.DIRT).setName(coordinate.getX() + " " + coordinate.getY()), renderSlot) {
                    @Override
                    public void function(Player player) {
                        Bukkit.broadcastMessage(coordinate.getX() + " " + coordinate.getY());
                    }
                };
                buttons.add(button);
            }
        }
        Button up = new Button(new ItemCreator(Material.ARROW)
                .setName(ChatColor.GREEN + "Move Up"), 7) {
            @Override
            public void function(Player player) {
                setOrigin(new Coordinate(getOrigin().getX(), getOrigin().getY() + 1));
                updateButtons();
            }
        };
        buttons.add(up);
        Button down = new Button(new ItemCreator(Material.ARROW)
                .setName(ChatColor.GREEN + "Move Down"), 25) {
            @Override
            public void function(Player player) {
                setOrigin(new Coordinate(getOrigin().getX(), getOrigin().getY() - 1));
                updateButtons();
            }
        };
        buttons.add(down);
        Button left = new Button(new ItemCreator(Material.ARROW)
                .setName(ChatColor.GREEN + "Move Left"), 15) {
            @Override
            public void function(Player player) {
                setOrigin(new Coordinate(getOrigin().getX() - 1, getOrigin().getY()));
                updateButtons();
            }
        };
        buttons.add(left);
        Button right = new Button(new ItemCreator(Material.ARROW)
                .setName(ChatColor.GREEN + "Move Right"), 17) {
            @Override
            public void function(Player player) {
                setOrigin(new Coordinate(getOrigin().getX() + 1, getOrigin().getY()));
                updateButtons();
            }
        };
        buttons.add(right);
        Button compass = new Button(new ItemCreator(Material.COMPASS)
                .setName(ChatColor.GREEN + "Use these to move!"), 16);
        buttons.add(compass);
        setButtons(buttons);
        updateInventory();
    }

}
