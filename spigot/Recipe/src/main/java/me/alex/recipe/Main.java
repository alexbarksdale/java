package me.alex.recipe;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        Bukkit.addRecipe(getRecipe());
        Bukkit.addRecipe(getPickaxeRecipe());
    }

    @Override
    public void onDisable() {

    }

    public ShapedRecipe getRecipe(){
        NamespacedKey key = new NamespacedKey(this, "custom_star");
        ItemStack item = new ItemStack(Material.NETHER_STAR);

        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(" T ", "TET", " T ");
        recipe.setIngredient('T', Material.GHAST_TEAR);
        recipe.setIngredient('E', Material.EMERALD_BLOCK);
        return recipe;
    }

    public ShapedRecipe getPickaxeRecipe() {
        NamespacedKey key = new NamespacedKey(this, "pickaxe_key");
        ItemStack item = new ItemStack(Material.DIAMOND_PICKAXE);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("Emerald Pickaxe");
        //               Enchantment                  lvl | If you're going out of bounds of the enchant lvl
        meta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 10, true);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);

        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("EEE", " S ", " S ");
        recipe.setIngredient('E', Material.EMERALD_BLOCK);
        recipe.setIngredient('S', Material.STICK);

        return recipe;
    }
}
