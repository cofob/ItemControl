package ru.cofob.ItemControl;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import ru.cofob.ItemControl.listener.InventoryOpen;

import java.util.logging.Logger;


public class ItemControl extends JavaPlugin {
    public String version;
    private final Logger log;

    public ItemControl() {
        this.log = this.getLogger();
    }

    @Override
    public void onEnable() {
//        defining variables
        version = "v0.0.2";
        log.info("§a[ItemControl] §rLoaded §6"+version+"§r!");
        new InventoryOpen(this).register();
    }

    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("itemcontrol")) {
            s.sendMessage("§a[ItemControl] §rversion: §6"+version+"§r!");
            return true;
        }
        return false;
    }
}
