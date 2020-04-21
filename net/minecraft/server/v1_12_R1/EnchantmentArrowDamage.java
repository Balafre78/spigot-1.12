/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ public class EnchantmentArrowDamage
/*    */   extends Enchantment
/*    */ {
/*    */   public EnchantmentArrowDamage(Enchantment.Rarity paramRarity, EnumItemSlot... paramVarArgs) {
/*  7 */     super(paramRarity, EnchantmentSlotType.BOW, paramVarArgs);
/*  8 */     c("arrowDamage");
/*    */   }
/*    */ 
/*    */   
/*    */   public int a(int paramInt) {
/* 13 */     return 1 + (paramInt - 1) * 10;
/*    */   }
/*    */ 
/*    */   
/*    */   public int b(int paramInt) {
/* 18 */     return a(paramInt) + 15;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaxLevel() {
/* 23 */     return 5;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EnchantmentArrowDamage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */