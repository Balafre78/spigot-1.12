/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DataConverterShulkerBoxBlock
/*    */   implements IDataConverter
/*    */ {
/*    */   public int a() {
/*  9 */     return 813;
/*    */   }
/*    */ 
/*    */   
/*    */   public NBTTagCompound a(NBTTagCompound paramNBTTagCompound) {
/* 14 */     if ("minecraft:shulker".equals(paramNBTTagCompound.getString("id"))) {
/* 15 */       paramNBTTagCompound.remove("Color");
/*    */     }
/* 17 */     return paramNBTTagCompound;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DataConverterShulkerBoxBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */