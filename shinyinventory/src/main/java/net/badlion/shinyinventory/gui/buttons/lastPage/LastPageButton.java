package net.badlion.shinyinventory.gui.buttons.lastPage;

import net.badlion.shinyinventory.gui.Interface;
import net.badlion.shinyinventory.gui.InterfaceManager;
import net.badlion.shinyinventory.gui.buttons.Button;
import net.badlion.shinyinventory.gui.interfaces.ChestInterface;
import net.badlion.shinyinventory.gui.interfaces.SinglePageInterface;
import net.badlion.shinyinventory.utils.Constants;
import net.badlion.shinyinventory.utils.ItemCreator;
import org.bukkit.Material;
import org.bukkit.entity.Player;

/**
 * Created by ShinyDialga45 on 4/10/2015.
 */
public class LastPageButton extends Button {

    private SinglePageInterface page;

    public LastPageButton(int slot) {
        super(null, slot);
    }

    public LastPageButton(SinglePageInterface gui, int slot) {
        super(null, slot);
        this.page = gui;
    }

    public SinglePageInterface getPage() {
        return this.page;
    }

    public Interface getLastPage(ChestInterface gui) {
        if (this.page == null) {
            return gui.getParent();
        }
        try {
            int page = this.page.page;
            Interface previousInterface = getPage().getParent() != null ? getPage().getParent() : gui;
            try {
                Interface gui1 = /*page != 0 ? new SinglePageInterface(this.page.rawButtons, this.page.getSize(), this.page.rawTitle, this.page.getParent(), this.page.page - 1) :*/ previousInterface;
                if (gui1 instanceof SinglePageInterface) {
                   // ((SinglePageInterface)gui1).updateButtons();
                }
                return gui1;
            } catch (Exception e) {
                return previousInterface;
            }
        } catch (Exception e) {
            return gui;
        }
    }

    @Override
    public ItemCreator getIcon() {
        return new ItemCreator(Material.BARRIER)
                .setName(Constants.PREFIX + "Previous");
    }

    @Override
    public void function(Player player) {
        Interface currentInterface = InterfaceManager.getInterface(player.getOpenInventory());
        //Interface lastPage = getLastPage((ChestInterface)currentInterface);
        //player.openInventory(lastPage.getInventory());
        if (currentInterface instanceof SinglePageInterface) {
            ((SinglePageInterface)currentInterface).openLastPage(player);
        } else {
            player.openInventory(((ChestInterface)currentInterface).getParent().getInventory());
        }
    }

}
