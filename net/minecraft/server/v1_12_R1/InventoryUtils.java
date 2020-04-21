/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class InventoryUtils
/*    */ {
/* 12 */   private static final Random a = new Random();
/*    */   
/*    */   public static void dropInventory(World paramWorld, BlockPosition paramBlockPosition, IInventory paramIInventory) {
/* 15 */     dropInventory(paramWorld, paramBlockPosition.getX(), paramBlockPosition.getY(), paramBlockPosition.getZ(), paramIInventory);
/*    */   }
/*    */   
/*    */   public static void dropEntity(World paramWorld, Entity paramEntity, IInventory paramIInventory) {
/* 19 */     dropInventory(paramWorld, paramEntity.locX, paramEntity.locY, paramEntity.locZ, paramIInventory);
/*    */   }
/*    */   
/*    */   private static void dropInventory(World paramWorld, double paramDouble1, double paramDouble2, double paramDouble3, IInventory paramIInventory) {
/* 23 */     for (byte b = 0; b < paramIInventory.getSize(); b++) {
/* 24 */       ItemStack itemStack = paramIInventory.getItem(b);
/* 25 */       if (!itemStack.isEmpty())
/*    */       {
/*    */ 
/*    */         
/* 29 */         dropItem(paramWorld, paramDouble1, paramDouble2, paramDouble3, itemStack); } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public static void dropItem(World paramWorld, double paramDouble1, double paramDouble2, double paramDouble3, ItemStack paramItemStack) {
/* 34 */     float f1 = a.nextFloat() * 0.8F + 0.1F;
/* 35 */     float f2 = a.nextFloat() * 0.8F + 0.1F;
/* 36 */     float f3 = a.nextFloat() * 0.8F + 0.1F;
/*    */     
/* 38 */     while (!paramItemStack.isEmpty()) {
/* 39 */       EntityItem entityItem = new EntityItem(paramWorld, paramDouble1 + f1, paramDouble2 + f2, paramDouble3 + f3, paramItemStack.cloneAndSubtract(a.nextInt(21) + 10));
/*    */       
/* 41 */       float f = 0.05F;
/* 42 */       entityItem.motX = a.nextGaussian() * 0.05000000074505806D;
/* 43 */       entityItem.motY = a.nextGaussian() * 0.05000000074505806D + 0.20000000298023224D;
/* 44 */       entityItem.motZ = a.nextGaussian() * 0.05000000074505806D;
/*    */       
/* 46 */       paramWorld.addEntity(entityItem);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\InventoryUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */