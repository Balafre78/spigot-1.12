/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DataConverterShulker
/*    */   implements IDataConverter
/*    */ {
/*    */   public int a() {
/* 10 */     return 808;
/*    */   }
/*    */ 
/*    */   
/*    */   public NBTTagCompound a(NBTTagCompound paramNBTTagCompound) {
/* 15 */     if ("minecraft:shulker".equals(paramNBTTagCompound.getString("id")) && 
/* 16 */       !paramNBTTagCompound.hasKeyOfType("Color", 99)) {
/* 17 */       paramNBTTagCompound.setByte("Color", (byte)10);
/*    */     }
/*    */     
/* 20 */     return paramNBTTagCompound;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DataConverterShulker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */