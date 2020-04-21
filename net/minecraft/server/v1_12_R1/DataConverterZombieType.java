/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DataConverterZombieType
/*    */   implements IDataConverter
/*    */ {
/*    */   public int a() {
/*  9 */     return 702;
/*    */   }
/*    */ 
/*    */   
/*    */   public NBTTagCompound a(NBTTagCompound paramNBTTagCompound) {
/* 14 */     if ("Zombie".equals(paramNBTTagCompound.getString("id"))) {
/* 15 */       int i = paramNBTTagCompound.getInt("ZombieType");
/* 16 */       switch (i) {
/*    */ 
/*    */ 
/*    */         
/*    */         case 1:
/*    */         case 2:
/*    */         case 3:
/*    */         case 4:
/*    */         case 5:
/* 25 */           paramNBTTagCompound.setString("id", "ZombieVillager");
/* 26 */           paramNBTTagCompound.setInt("Profession", i - 1);
/*    */           break;
/*    */         case 6:
/* 29 */           paramNBTTagCompound.setString("id", "Husk");
/*    */           break;
/*    */       } 
/* 32 */       paramNBTTagCompound.remove("ZombieType");
/*    */     } 
/* 34 */     return paramNBTTagCompound;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DataConverterZombieType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */