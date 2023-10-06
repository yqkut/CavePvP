package org.cavepvp.suge.kit.menu;

import cc.fyre.proton.menu.Button;
import cc.fyre.proton.menu.Menu;
import cc.fyre.proton.util.TimeUtils;
import lombok.AllArgsConstructor;
import net.frozenorb.foxtrot.util.CC;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.cavepvp.suge.Suge;
import org.cavepvp.suge.kit.data.Category;
import org.cavepvp.suge.kit.data.Kit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class KitCategoryMenu extends Menu {
    private Category category;

    @Override
    public String getTitle(Player player) {
        return category.getFlatName() + " Kits";
    }

    @Override
    public boolean isPlaceholder() {
        return true;
    }

    @Override
    public void onClose(Player player) {
        Suge.getInstance().getServer().getScheduler().runTaskLater(Suge.getInstance(), () -> new KitMainMenu().openMenu(player), 1);
    }

    @Override
    public int size(Player player) {
        if (category.equals(Category.NORMAL)) {
            return 45;
        }

        return 27;
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        final Map<Integer, Button> toReturn = new HashMap<>();
        
        for (Kit kit : category.findKits()) {

            toReturn.put(kit.getSlot()-1, new Button() {
                @Override
                public String getName(Player player) {
                    return CC.translate(kit.getDisplayName());
                }

                @Override
                public List<String> getDescription(Player player) {
                    return getLore(player, kit);
                }

                @Override
                public Material getMaterial(Player player) {
                    return kit.getMaterial();
                }

                @Override
                public byte getDamageValue(Player player) {
                    return (byte) kit.getDamage();
                }

                @Override
                public void clicked(Player player, int slot, ClickType clickType) {

                    if (clickType.name().contains("RIGHT")) {
                        player.closeInventory();
                        new KitPreviewMenu(kit, getMenu()).openMenu(player);
                        return;
                    }

                    if (!player.hasPermission("crazyenchantments.gkitz." + kit.getName().toLowerCase())) {
                        player.sendMessage(ChatColor.RED + "This kit is locked, purchase this kit at https://store.cavepvp.org");
                        return;
                    }

                    kit.equip(player);
                }
            });

        }

        return toReturn;
    }

    public Menu getMenu() {
        return this;
    }

    @Override
    public boolean isAutoUpdate() {
        return true;
    }

    public List<String> getLore(Player player, Kit kit) {
        final List<String> lore = new ArrayList<>();

        final int seconds = (int) (Suge.getInstance().getKitHandler().getRemaining(player, kit)/1000);

        for (String line : kit.getLore()) {
            lore.add(CC.translate(line).replace("{remaining}", seconds == 0 ? "Now" : TimeUtils.formatIntoHHMMSS(seconds)));
        }

        return lore;
    }
}
