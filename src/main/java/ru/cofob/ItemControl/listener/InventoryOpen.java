package ru.cofob.ItemControl.listener;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.ItemStack;
import ru.cofob.ItemControl.ItemControl;

import java.util.logging.Logger;

import static org.bukkit.Bukkit.getServer;

public class InventoryOpen {
    private final ItemControl plugin;

    public InventoryOpen(ItemControl plugin) {
        this.plugin = plugin;
    }

    public void register() {
        getServer().getPluginManager().registerEvents(new EventsListener(this.plugin), this.plugin);
    }

    private static class EventsListener implements Listener {
        private final Logger log;

        public EventsListener(ItemControl plugin) {
            this.log = plugin.getLogger();
//            log.info("§a[ItemControl] §6debug§r: registered InventoryOpenEvent.");
        }

        @EventHandler
        public void onInventoryOpenEvent(InventoryOpenEvent  e) {
            HumanEntity player = e.getPlayer();
            for(ItemStack item : player.getInventory().getContents())
            {
                if(item != null)
                {
                    for(Enchantment enchantment : item.getEnchantments().keySet()) {
//                        log.info(item.toString());
                        int level = item.getEnchantmentLevel(enchantment);
                        int max_level = enchantment.getMaxLevel();
                        boolean is_converted = false;
                        boolean breaking = false;
                        if(level > max_level) {
                            item.removeEnchantment(enchantment);
                            if(enchantment.canEnchantItem(item)){
                                item.addEnchantment(enchantment, max_level);
                                breaking = true;
                            }
                            is_converted = true;
                        }
//                        log.info("§a[ItemControl] §6debug§r: name: "+ enchantment.getKey() + ", current level: " + level + ", max level: " + max_level + ", is converted? " + is_converted + ", breaking? " + breaking);
                    }
                }
            }
        }
    }
}
