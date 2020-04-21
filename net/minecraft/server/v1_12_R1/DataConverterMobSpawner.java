/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DataConverterMobSpawner
/*    */   implements IDataConverter
/*    */ {
/*    */   public int a() {
/* 11 */     return 107;
/*    */   }
/*    */ 
/*    */   
/*    */   public NBTTagCompound a(NBTTagCompound paramNBTTagCompound) {
/* 16 */     if (!"MobSpawner".equals(paramNBTTagCompound.getString("id"))) {
/* 17 */       return paramNBTTagCompound;
/*    */     }
/*    */     
/* 20 */     if (paramNBTTagCompound.hasKeyOfType("EntityId", 8)) {
/* 21 */       String str = paramNBTTagCompound.getString("EntityId");
/*    */       
/* 23 */       NBTTagCompound nBTTagCompound = paramNBTTagCompound.getCompound("SpawnData");
/* 24 */       nBTTagCompound.setString("id", str.isEmpty() ? "Pig" : str);
/* 25 */       paramNBTTagCompound.set("SpawnData", nBTTagCompound);
/*    */       
/* 27 */       paramNBTTagCompound.remove("EntityId");
/*    */     } 
/*    */     
/* 30 */     if (paramNBTTagCompound.hasKeyOfType("SpawnPotentials", 9)) {
/* 31 */       NBTTagList nBTTagList = paramNBTTagCompound.getList("SpawnPotentials", 10);
/* 32 */       for (byte b = 0; b < nBTTagList.size(); b++) {
/* 33 */         NBTTagCompound nBTTagCompound = nBTTagList.get(b);
/*    */         
/* 35 */         if (nBTTagCompound.hasKeyOfType("Type", 8)) {
/* 36 */           NBTTagCompound nBTTagCompound1 = nBTTagCompound.getCompound("Properties");
/* 37 */           nBTTagCompound1.setString("id", nBTTagCompound.getString("Type"));
/* 38 */           nBTTagCompound.set("Entity", nBTTagCompound1);
/*    */           
/* 40 */           nBTTagCompound.remove("Type");
/* 41 */           nBTTagCompound.remove("Properties");
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 46 */     return paramNBTTagCompound;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DataConverterMobSpawner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */