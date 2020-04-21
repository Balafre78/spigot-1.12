/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ public class EnchantmentArrowKnockback
/*    */   extends Enchantment
/*    */ {
/*    */   public EnchantmentArrowKnockback(Enchantment.Rarity paramRarity, EnumItemSlot... paramVarArgs) {
/*  7 */     super(paramRarity, EnchantmentSlotType.BOW, paramVarArgs);
/*  8 */     c("arrowKnockback");
/*    */   }
/*    */ 
/*    */   
/*    */   public int a(int paramInt) {
/* 13 */     return 12 + (paramInt - 1) * 20;
/*    */   }
/*    */ 
/*    */   
/*    */   public int b(int paramInt) {
/* 18 */     return a(paramInt) + 25;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaxLevel() {
/* 23 */     return 2;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EnchantmentArrowKnockback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */