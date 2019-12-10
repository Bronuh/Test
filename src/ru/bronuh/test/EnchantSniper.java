package ru.bronuh.test;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.inventory.ItemStack;

public class EnchantSniper extends EnchantmentWrapper{
	
		public EnchantSniper(String id) {
			super(id);
		}
		 
		@Override
		public boolean canEnchantItem(ItemStack item) {
			return true;
		}
		 
		@Override
		public boolean conflictsWith(Enchantment other) {
			return false;
		}
		 
		@Override
		public EnchantmentTarget getItemTarget() {
			return null;
		}
		 
		@Override
		public int getMaxLevel() {
			return 10;
		}
		 
		@Override
		public String getName() {
			return"Sniper";
		}
		 
		@Override
		public int getStartLevel() {
			return 1;
		}
		 
}
