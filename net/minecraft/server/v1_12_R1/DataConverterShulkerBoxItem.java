/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DataConverterShulkerBoxItem
/*    */   implements IDataConverter
/*    */ {
/*  8 */   public static final String[] a = new String[] { "minecraft:white_shulker_box", "minecraft:orange_shulker_box", "minecraft:magenta_shulker_box", "minecraft:light_blue_shulker_box", "minecraft:yellow_shulker_box", "minecraft:lime_shulker_box", "minecraft:pink_shulker_box", "minecraft:gray_shulker_box", "minecraft:silver_shulker_box", "minecraft:cyan_shulker_box", "minecraft:purple_shulker_box", "minecraft:blue_shulker_box", "minecraft:brown_shulker_box", "minecraft:green_shulker_box", "minecraft:red_shulker_box", "minecraft:black_shulker_box" };
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int a() {
/* 29 */     return 813;
/*    */   }
/*    */ 
/*    */   
/*    */   public NBTTagCompound a(NBTTagCompound paramNBTTagCompound) {
/* 34 */     if ("minecraft:shulker_box".equals(paramNBTTagCompound.getString("id")) && 
/* 35 */       paramNBTTagCompound.hasKeyOfType("tag", 10)) {
/* 36 */       NBTTagCompound nBTTagCompound = paramNBTTagCompound.getCompound("tag");
/* 37 */       if (nBTTagCompound.hasKeyOfType("BlockEntityTag", 10)) {
/* 38 */         NBTTagCompound nBTTagCompound1 = nBTTagCompound.getCompound("BlockEntityTag");
/* 39 */         if (nBTTagCompound1.getList("Items", 10).isEmpty()) {
/* 40 */           nBTTagCompound1.remove("Items");
/*    */         }
/*    */         
/* 43 */         int i = nBTTagCompound1.getInt("Color");
/* 44 */         nBTTagCompound1.remove("Color");
/* 45 */         if (nBTTagCompound1.isEmpty()) {
/* 46 */           nBTTagCompound.remove("BlockEntityTag");
/*    */         }
/* 48 */         if (nBTTagCompound.isEmpty()) {
/* 49 */           paramNBTTagCompound.remove("tag");
/*    */         }
/* 51 */         paramNBTTagCompound.setString("id", a[i % 16]);
/*    */       } 
/*    */     } 
/*    */     
/* 55 */     return paramNBTTagCompound;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DataConverterShulkerBoxItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */