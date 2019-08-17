package com.autodidax.demoncycle.init;

import java.util.ArrayList;
import java.util.List;

import com.autodidax.demoncycle.items.ItemBase;
import com.autodidax.demoncycle.items.ItemSeed;
import com.autodidax.demoncycle.items.armor.ArmorBase;
import com.autodidax.demoncycle.items.food.FoodBase;
import com.autodidax.demoncycle.items.food.FoodEffectBase;
import com.autodidax.demoncycle.items.tools.Axe;
import com.autodidax.demoncycle.items.tools.Gouge;
import com.autodidax.demoncycle.items.tools.Hoe;
import com.autodidax.demoncycle.items.tools.Pickaxe;
import com.autodidax.demoncycle.items.tools.Spade;
import com.autodidax.demoncycle.items.tools.Sword;
import com.autodidax.demoncycle.util.Reference;

import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;

public class ModItems {
	public static final List<Item> ITEMS = new ArrayList<Item>();

	// Material
	public static final ToolMaterial MATERIAL_COPPER = EnumHelper.addToolMaterial("material_copper", 2, 250, 5.0F, 1.5F,
			14);
	public static final ArmorMaterial ARMOR_MATERIAL_FLAX_WOVEN = EnumHelper.addArmorMaterial(
			"armor_material_flax_woven", Reference.MOD_ID + ":flax_woven", 7, new int[] { 1, 3, 4, 2 }, // Boots, Leggings,Chestplate, Helmet
			10, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0f);

	// Seeds
	public static final ItemSeed SEED_BLACKSTEM = new ItemSeed("seed_blackstem", ModBlocks.CROP_BLACKSTEM);
	public static final ItemSeed SEED_FLAX = new ItemSeed("seed_flax", ModBlocks.CROP_FLAX);

	// Plants
	public static final Item FLAX = new ItemBase("flax");
	public static final Item FLAX_FIBRE = new ItemBase("flax_fibre");
	public static final Item FLAX_WOVEN = new ItemBase("flax_woven");

	// Food
	public static final Item BLACKSTEM_SOUP = new FoodBase("blackstem_soup", 1, 0.5f, false);
	public static final Item BLACKSTEM = new FoodEffectBase("blackstem", 1, 0.f, false, new PotionEffect(MobEffects.NAUSEA, (10*20), 1, false, false));
	
	// Ingots
	public static final Item INGOT_COPPER = new ItemBase("ingot_copper");

	// Tools
	public static final Gouge GOUGE_WOOD = new Gouge(ToolMaterial.WOOD, "gouge_wood");
	public static final Gouge GOUGE_STONE = new Gouge(ToolMaterial.STONE, "gouge_stone");

	public static final ItemSword SWORD_COPPER = new Sword("sword_copper", MATERIAL_COPPER);
	public static final ItemSpade SHOVEL_COPPER = new Spade("shovel_copper", MATERIAL_COPPER);
	public static final ItemPickaxe PICKAXE_COPPER = new Pickaxe("pickaxe_copper", MATERIAL_COPPER);
	public static final ItemHoe HOE_COPPER = new Hoe("hoe_copper", MATERIAL_COPPER);
	public static final ItemAxe AXE_COPPER = new Axe("axe_copper", MATERIAL_COPPER, 6f, -3.2f);

	// Armor
	public static final Item HELMET_FLAX_WOVEN = new ArmorBase("helmet_flax_woven", ARMOR_MATERIAL_FLAX_WOVEN, 1,
			EntityEquipmentSlot.HEAD);
	public static final Item CHESTPLATE_FLAX_WOVEN = new ArmorBase("chestplate_flax_woven", ARMOR_MATERIAL_FLAX_WOVEN,
			1, EntityEquipmentSlot.CHEST);
	public static final Item LEGGINGS_FLAX_WOVEN = new ArmorBase("leggings_flax_woven", ARMOR_MATERIAL_FLAX_WOVEN, 2,
			EntityEquipmentSlot.LEGS);
	public static final Item BOOTS_FLAX_WOVEN = new ArmorBase("boots_flax_woven", ARMOR_MATERIAL_FLAX_WOVEN, 1,
			EntityEquipmentSlot.FEET);

	public static void Init() {
		OreDictionary.registerOre("ingotCopper", INGOT_COPPER);
		OreDictionary.registerOre("flax", FLAX);
		OreDictionary.registerOre("flax_fibre", FLAX_FIBRE);
		OreDictionary.registerOre("flax_woven", FLAX_WOVEN);
		OreDictionary.registerOre("blackstem", BLACKSTEM);
	}
}
