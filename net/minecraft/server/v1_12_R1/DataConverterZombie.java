/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DataConverterZombie
/*    */   implements IDataConverter
/*    */ {
/* 11 */   private static final Random a = new Random();
/*    */ 
/*    */   
/*    */   public int a() {
/* 15 */     return 502;
/*    */   }
/*    */ 
/*    */   
/*    */   public NBTTagCompound a(NBTTagCompound paramNBTTagCompound) {
/* 20 */     if ("Zombie".equals(paramNBTTagCompound.getString("id")) && 
/* 21 */       paramNBTTagCompound.getBoolean("IsVillager")) {
/* 22 */       if (!paramNBTTagCompound.hasKeyOfType("ZombieType", 99)) {
/* 23 */         int i = -1;
/* 24 */         if (paramNBTTagCompound.hasKeyOfType("VillagerProfession", 99)) {
/*    */           try {
/* 26 */             i = a(paramNBTTagCompound.getInt("VillagerProfession"));
/* 27 */           } catch (RuntimeException runtimeException) {}
/*    */         }
/*    */         
/* 30 */         if (i == -1) {
/* 31 */           i = a(a.nextInt(6));
/*    */         }
/*    */         
/* 34 */         paramNBTTagCompound.setInt("ZombieType", i);
/*    */       } 
/*    */       
/* 37 */       paramNBTTagCompound.remove("IsVillager");
/*    */     } 
/*    */     
/* 40 */     return paramNBTTagCompound;
/*    */   }
/*    */   
/*    */   private int a(int paramInt) {
/* 44 */     if (paramInt < 0 || paramInt >= 6) {
/* 45 */       return -1;
/*    */     }
/* 47 */     return paramInt;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DataConverterZombie.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */