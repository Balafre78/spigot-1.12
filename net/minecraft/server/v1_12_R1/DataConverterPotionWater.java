/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DataConverterPotionWater
/*    */   implements IDataConverter
/*    */ {
/*    */   public int a() {
/* 10 */     return 806;
/*    */   }
/*    */ 
/*    */   
/*    */   public NBTTagCompound a(NBTTagCompound paramNBTTagCompound) {
/* 15 */     String str = paramNBTTagCompound.getString("id");
/* 16 */     if ("minecraft:potion".equals(str) || "minecraft:splash_potion"
/* 17 */       .equals(str) || "minecraft:lingering_potion"
/* 18 */       .equals(str) || "minecraft:tipped_arrow"
/* 19 */       .equals(str)) {
/*    */       
/* 21 */       NBTTagCompound nBTTagCompound = paramNBTTagCompound.getCompound("tag");
/* 22 */       if (!nBTTagCompound.hasKeyOfType("Potion", 8)) {
/* 23 */         nBTTagCompound.setString("Potion", "minecraft:water");
/*    */       }
/* 25 */       if (!paramNBTTagCompound.hasKeyOfType("tag", 10)) {
/* 26 */         paramNBTTagCompound.set("tag", nBTTagCompound);
/*    */       }
/*    */     } 
/*    */     
/* 30 */     return paramNBTTagCompound;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DataConverterPotionWater.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */