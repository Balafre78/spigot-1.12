/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DataConverterHorse
/*    */   implements IDataConverter
/*    */ {
/*    */   public int a() {
/*  9 */     return 703;
/*    */   }
/*    */ 
/*    */   
/*    */   public NBTTagCompound a(NBTTagCompound paramNBTTagCompound) {
/* 14 */     if ("EntityHorse".equals(paramNBTTagCompound.getString("id"))) {
/* 15 */       int i = paramNBTTagCompound.getInt("Type");
/* 16 */       switch (i) {
/*    */         
/*    */         default:
/* 19 */           paramNBTTagCompound.setString("id", "Horse");
/*    */           break;
/*    */         case 1:
/* 22 */           paramNBTTagCompound.setString("id", "Donkey");
/*    */           break;
/*    */         case 2:
/* 25 */           paramNBTTagCompound.setString("id", "Mule");
/*    */           break;
/*    */         case 3:
/* 28 */           paramNBTTagCompound.setString("id", "ZombieHorse");
/*    */           break;
/*    */         case 4:
/* 31 */           paramNBTTagCompound.setString("id", "SkeletonHorse");
/*    */           break;
/*    */       } 
/* 34 */       paramNBTTagCompound.remove("Type");
/*    */     } 
/* 36 */     return paramNBTTagCompound;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DataConverterHorse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */