/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DataConverterDropChances
/*    */   implements IDataConverter
/*    */ {
/*    */   public int a() {
/* 11 */     return 113;
/*    */   }
/*    */ 
/*    */   
/*    */   public NBTTagCompound a(NBTTagCompound paramNBTTagCompound) {
/* 16 */     if (paramNBTTagCompound.hasKeyOfType("HandDropChances", 9)) {
/* 17 */       NBTTagList nBTTagList = paramNBTTagCompound.getList("HandDropChances", 5);
/* 18 */       if (nBTTagList.size() == 2 && nBTTagList.g(0) == 0.0F && nBTTagList.g(1) == 0.0F) {
/* 19 */         paramNBTTagCompound.remove("HandDropChances");
/*    */       }
/*    */     } 
/* 22 */     if (paramNBTTagCompound.hasKeyOfType("ArmorDropChances", 9)) {
/* 23 */       NBTTagList nBTTagList = paramNBTTagCompound.getList("ArmorDropChances", 5);
/* 24 */       if (nBTTagList.size() == 4 && nBTTagList.g(0) == 0.0F && nBTTagList.g(1) == 0.0F && nBTTagList.g(2) == 0.0F && nBTTagList.g(3) == 0.0F) {
/* 25 */         paramNBTTagCompound.remove("ArmorDropChances");
/*    */       }
/*    */     } 
/* 28 */     return paramNBTTagCompound;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DataConverterDropChances.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */