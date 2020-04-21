/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DataConverterBedItem
/*    */   implements IDataConverter
/*    */ {
/*    */   public int a() {
/* 11 */     return 1125;
/*    */   }
/*    */ 
/*    */   
/*    */   public NBTTagCompound a(NBTTagCompound paramNBTTagCompound) {
/* 16 */     if ("minecraft:bed".equals(paramNBTTagCompound.getString("id")) && paramNBTTagCompound.getShort("Damage") == 0) {
/* 17 */       paramNBTTagCompound.setShort("Damage", (short)EnumColor.RED.getColorIndex());
/*    */     }
/* 19 */     return paramNBTTagCompound;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DataConverterBedItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */