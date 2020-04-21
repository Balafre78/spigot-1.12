/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DataConverterArmorStand
/*    */   implements IDataConverter
/*    */ {
/*    */   public int a() {
/*  9 */     return 147;
/*    */   }
/*    */ 
/*    */   
/*    */   public NBTTagCompound a(NBTTagCompound paramNBTTagCompound) {
/* 14 */     if ("ArmorStand".equals(paramNBTTagCompound.getString("id")) && 
/* 15 */       paramNBTTagCompound.getBoolean("Silent") && !paramNBTTagCompound.getBoolean("Marker")) {
/* 16 */       paramNBTTagCompound.remove("Silent");
/*    */     }
/*    */     
/* 19 */     return paramNBTTagCompound;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DataConverterArmorStand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */