package ru.cofob.ItemControl;

import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;


public class ItemControl extends JavaPlugin {
    @Override
    public void onEnable() {
        Bukkit.getScheduler().runTaskTimer(this, this::scanAllPlayers, 400L, 400L);
    }

    private void scanAllPlayers() {
        for (Player player : this.getServer().getOnlinePlayers()) {
            for (ItemStack item : player.getInventory().getContents()) {
                if (item != null) {
                    for (Enchantment enchantment : item.getEnchantments().keySet()) {
                        int max_level = enchantment.getMaxLevel();
                        if (item.getEnchantmentLevel(enchantment) > max_level) {
                            item.removeEnchantment(enchantment);
                            if (enchantment.canEnchantItem(item)) {
                                item.addEnchantment(enchantment, max_level);
                            }
                        }
                    }
                }
            }
        }
    }
}
