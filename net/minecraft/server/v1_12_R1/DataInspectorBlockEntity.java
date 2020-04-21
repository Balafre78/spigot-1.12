/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.Map;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class DataInspectorBlockEntity
/*     */   implements DataInspector {
/*  11 */   private static final Logger a = LogManager.getLogger();
/*  12 */   private static final Map<String, String> b = Maps.newHashMap();
/*  13 */   private static final Map<String, String> c = Maps.newHashMap();
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   private static String a(int i, String s) {
/*  19 */     return (i < 515) ? b.get((new MinecraftKey(s)).toString()) : c.get((new MinecraftKey(s)).toString());
/*     */   }
/*     */   
/*     */   public NBTTagCompound a(DataConverter dataconverter, NBTTagCompound nbttagcompound, int i) {
/*  23 */     if (!nbttagcompound.hasKeyOfType("tag", 10)) {
/*  24 */       return nbttagcompound;
/*     */     }
/*  26 */     NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("tag");
/*     */     
/*  28 */     if (nbttagcompound1.hasKeyOfType("BlockEntityTag", 10)) {
/*  29 */       boolean flag; NBTTagCompound nbttagcompound2 = nbttagcompound1.getCompound("BlockEntityTag");
/*  30 */       String s = nbttagcompound.getString("id");
/*  31 */       String s1 = a(i, s);
/*     */ 
/*     */       
/*  34 */       if (s1 == null) {
/*     */ 
/*     */         
/*  37 */         flag = false;
/*     */       } else {
/*  39 */         flag = !nbttagcompound2.hasKey("id");
/*  40 */         nbttagcompound2.setString("id", s1);
/*     */       } 
/*     */       
/*  43 */       dataconverter.a(DataConverterTypes.BLOCK_ENTITY, nbttagcompound2, i);
/*  44 */       if (flag) {
/*  45 */         nbttagcompound2.remove("id");
/*     */       }
/*     */     } 
/*     */     
/*  49 */     return nbttagcompound;
/*     */   }
/*     */ 
/*     */   
/*     */   static {
/*  54 */     Map<String, String> map = b;
/*     */     
/*  56 */     map.put("minecraft:furnace", "Furnace");
/*  57 */     map.put("minecraft:lit_furnace", "Furnace");
/*  58 */     map.put("minecraft:chest", "Chest");
/*  59 */     map.put("minecraft:trapped_chest", "Chest");
/*  60 */     map.put("minecraft:ender_chest", "EnderChest");
/*  61 */     map.put("minecraft:jukebox", "RecordPlayer");
/*  62 */     map.put("minecraft:dispenser", "Trap");
/*  63 */     map.put("minecraft:dropper", "Dropper");
/*  64 */     map.put("minecraft:sign", "Sign");
/*  65 */     map.put("minecraft:mob_spawner", "MobSpawner");
/*  66 */     map.put("minecraft:noteblock", "Music");
/*  67 */     map.put("minecraft:brewing_stand", "Cauldron");
/*  68 */     map.put("minecraft:enhanting_table", "EnchantTable");
/*  69 */     map.put("minecraft:command_block", "CommandBlock");
/*  70 */     map.put("minecraft:beacon", "Beacon");
/*  71 */     map.put("minecraft:skull", "Skull");
/*  72 */     map.put("minecraft:daylight_detector", "DLDetector");
/*  73 */     map.put("minecraft:hopper", "Hopper");
/*  74 */     map.put("minecraft:banner", "Banner");
/*  75 */     map.put("minecraft:flower_pot", "FlowerPot");
/*  76 */     map.put("minecraft:repeating_command_block", "CommandBlock");
/*  77 */     map.put("minecraft:chain_command_block", "CommandBlock");
/*  78 */     map.put("minecraft:standing_sign", "Sign");
/*  79 */     map.put("minecraft:wall_sign", "Sign");
/*  80 */     map.put("minecraft:piston_head", "Piston");
/*  81 */     map.put("minecraft:daylight_detector_inverted", "DLDetector");
/*  82 */     map.put("minecraft:unpowered_comparator", "Comparator");
/*  83 */     map.put("minecraft:powered_comparator", "Comparator");
/*  84 */     map.put("minecraft:wall_banner", "Banner");
/*  85 */     map.put("minecraft:standing_banner", "Banner");
/*  86 */     map.put("minecraft:structure_block", "Structure");
/*  87 */     map.put("minecraft:end_portal", "Airportal");
/*  88 */     map.put("minecraft:end_gateway", "EndGateway");
/*  89 */     map.put("minecraft:shield", "Shield");
/*  90 */     map = c;
/*  91 */     map.put("minecraft:furnace", "minecraft:furnace");
/*  92 */     map.put("minecraft:lit_furnace", "minecraft:furnace");
/*  93 */     map.put("minecraft:chest", "minecraft:chest");
/*  94 */     map.put("minecraft:trapped_chest", "minecraft:chest");
/*  95 */     map.put("minecraft:ender_chest", "minecraft:enderchest");
/*  96 */     map.put("minecraft:jukebox", "minecraft:jukebox");
/*  97 */     map.put("minecraft:dispenser", "minecraft:dispenser");
/*  98 */     map.put("minecraft:dropper", "minecraft:dropper");
/*  99 */     map.put("minecraft:sign", "minecraft:sign");
/* 100 */     map.put("minecraft:mob_spawner", "minecraft:mob_spawner");
/* 101 */     map.put("minecraft:noteblock", "minecraft:noteblock");
/* 102 */     map.put("minecraft:brewing_stand", "minecraft:brewing_stand");
/* 103 */     map.put("minecraft:enhanting_table", "minecraft:enchanting_table");
/* 104 */     map.put("minecraft:command_block", "minecraft:command_block");
/* 105 */     map.put("minecraft:beacon", "minecraft:beacon");
/* 106 */     map.put("minecraft:skull", "minecraft:skull");
/* 107 */     map.put("minecraft:daylight_detector", "minecraft:daylight_detector");
/* 108 */     map.put("minecraft:hopper", "minecraft:hopper");
/* 109 */     map.put("minecraft:banner", "minecraft:banner");
/* 110 */     map.put("minecraft:flower_pot", "minecraft:flower_pot");
/* 111 */     map.put("minecraft:repeating_command_block", "minecraft:command_block");
/* 112 */     map.put("minecraft:chain_command_block", "minecraft:command_block");
/* 113 */     map.put("minecraft:shulker_box", "minecraft:shulker_box");
/* 114 */     map.put("minecraft:white_shulker_box", "minecraft:shulker_box");
/* 115 */     map.put("minecraft:orange_shulker_box", "minecraft:shulker_box");
/* 116 */     map.put("minecraft:magenta_shulker_box", "minecraft:shulker_box");
/* 117 */     map.put("minecraft:light_blue_shulker_box", "minecraft:shulker_box");
/* 118 */     map.put("minecraft:yellow_shulker_box", "minecraft:shulker_box");
/* 119 */     map.put("minecraft:lime_shulker_box", "minecraft:shulker_box");
/* 120 */     map.put("minecraft:pink_shulker_box", "minecraft:shulker_box");
/* 121 */     map.put("minecraft:gray_shulker_box", "minecraft:shulker_box");
/* 122 */     map.put("minecraft:silver_shulker_box", "minecraft:shulker_box");
/* 123 */     map.put("minecraft:cyan_shulker_box", "minecraft:shulker_box");
/* 124 */     map.put("minecraft:purple_shulker_box", "minecraft:shulker_box");
/* 125 */     map.put("minecraft:blue_shulker_box", "minecraft:shulker_box");
/* 126 */     map.put("minecraft:brown_shulker_box", "minecraft:shulker_box");
/* 127 */     map.put("minecraft:green_shulker_box", "minecraft:shulker_box");
/* 128 */     map.put("minecraft:red_shulker_box", "minecraft:shulker_box");
/* 129 */     map.put("minecraft:black_shulker_box", "minecraft:shulker_box");
/* 130 */     map.put("minecraft:bed", "minecraft:bed");
/* 131 */     map.put("minecraft:standing_sign", "minecraft:sign");
/* 132 */     map.put("minecraft:wall_sign", "minecraft:sign");
/* 133 */     map.put("minecraft:piston_head", "minecraft:piston");
/* 134 */     map.put("minecraft:daylight_detector_inverted", "minecraft:daylight_detector");
/* 135 */     map.put("minecraft:unpowered_comparator", "minecraft:comparator");
/* 136 */     map.put("minecraft:powered_comparator", "minecraft:comparator");
/* 137 */     map.put("minecraft:wall_banner", "minecraft:banner");
/* 138 */     map.put("minecraft:standing_banner", "minecraft:banner");
/* 139 */     map.put("minecraft:structure_block", "minecraft:structure_block");
/* 140 */     map.put("minecraft:end_portal", "minecraft:end_portal");
/* 141 */     map.put("minecraft:end_gateway", "minecraft:end_gateway");
/* 142 */     map.put("minecraft:shield", "minecraft:shield");
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DataInspectorBlockEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */