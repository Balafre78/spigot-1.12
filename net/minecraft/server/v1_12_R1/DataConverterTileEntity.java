/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ public class DataConverterTileEntity
/*    */   implements IDataConverter
/*    */ {
/* 10 */   private static final Map<String, String> a = Maps.newHashMap();
/*    */   
/*    */   static {
/* 13 */     a.put("Airportal", "minecraft:end_portal");
/* 14 */     a.put("Banner", "minecraft:banner");
/* 15 */     a.put("Beacon", "minecraft:beacon");
/* 16 */     a.put("Cauldron", "minecraft:brewing_stand");
/* 17 */     a.put("Chest", "minecraft:chest");
/* 18 */     a.put("Comparator", "minecraft:comparator");
/* 19 */     a.put("Control", "minecraft:command_block");
/* 20 */     a.put("DLDetector", "minecraft:daylight_detector");
/* 21 */     a.put("Dropper", "minecraft:dropper");
/* 22 */     a.put("EnchantTable", "minecraft:enchanting_table");
/* 23 */     a.put("EndGateway", "minecraft:end_gateway");
/* 24 */     a.put("EnderChest", "minecraft:ender_chest");
/* 25 */     a.put("FlowerPot", "minecraft:flower_pot");
/* 26 */     a.put("Furnace", "minecraft:furnace");
/* 27 */     a.put("Hopper", "minecraft:hopper");
/* 28 */     a.put("MobSpawner", "minecraft:mob_spawner");
/* 29 */     a.put("Music", "minecraft:noteblock");
/* 30 */     a.put("Piston", "minecraft:piston");
/* 31 */     a.put("RecordPlayer", "minecraft:jukebox");
/* 32 */     a.put("Sign", "minecraft:sign");
/* 33 */     a.put("Skull", "minecraft:skull");
/* 34 */     a.put("Structure", "minecraft:structure_block");
/* 35 */     a.put("Trap", "minecraft:dispenser");
/*    */   }
/*    */ 
/*    */   
/*    */   public int a() {
/* 40 */     return 704;
/*    */   }
/*    */ 
/*    */   
/*    */   public NBTTagCompound a(NBTTagCompound paramNBTTagCompound) {
/* 45 */     String str = a.get(paramNBTTagCompound.getString("id"));
/* 46 */     if (str != null) {
/* 47 */       paramNBTTagCompound.setString("id", str);
/*    */     }
/*    */     
/* 50 */     return paramNBTTagCompound;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DataConverterTileEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */