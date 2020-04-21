/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ public class EnchantmentWaterWorker
/*    */   extends Enchantment
/*    */ {
/*    */   public EnchantmentWaterWorker(Enchantment.Rarity paramRarity, EnumItemSlot... paramVarArgs) {
/*  7 */     super(paramRarity, EnchantmentSlotType.ARMOR_HEAD, paramVarArgs);
/*  8 */     c("waterWorker");
/*    */   }
/*    */ 
/*    */   
/*    */   public int a(int paramInt) {
/* 13 */     return 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public int b(int paramInt) {
/* 18 */     return a(paramInt) + 40;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaxLevel() {
/* 23 */     return 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EnchantmentWaterWorker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */