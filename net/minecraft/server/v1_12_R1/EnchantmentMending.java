/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ public class EnchantmentMending
/*    */   extends Enchantment
/*    */ {
/*    */   public EnchantmentMending(Enchantment.Rarity paramRarity, EnumItemSlot... paramVarArgs) {
/*  7 */     super(paramRarity, EnchantmentSlotType.BREAKABLE, paramVarArgs);
/*  8 */     c("mending");
/*    */   }
/*    */ 
/*    */   
/*    */   public int a(int paramInt) {
/* 13 */     return paramInt * 25;
/*    */   }
/*    */ 
/*    */   
/*    */   public int b(int paramInt) {
/* 18 */     return a(paramInt) + 50;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isTreasure() {
/* 23 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaxLevel() {
/* 28 */     return 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EnchantmentMending.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */