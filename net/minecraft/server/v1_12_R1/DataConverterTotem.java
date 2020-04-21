/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DataConverterTotem
/*    */   implements IDataConverter
/*    */ {
/*    */   public int a() {
/*  9 */     return 820;
/*    */   }
/*    */ 
/*    */   
/*    */   public NBTTagCompound a(NBTTagCompound paramNBTTagCompound) {
/* 14 */     if ("minecraft:totem".equals(paramNBTTagCompound.getString("id"))) {
/* 15 */       paramNBTTagCompound.setString("id", "minecraft:totem_of_undying");
/*    */     }
/* 17 */     return paramNBTTagCompound;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DataConverterTotem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */