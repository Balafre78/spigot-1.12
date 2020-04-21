/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ public class EnchantmentInfiniteArrows
/*    */   extends Enchantment
/*    */ {
/*    */   public EnchantmentInfiniteArrows(Enchantment.Rarity paramRarity, EnumItemSlot... paramVarArgs) {
/*  7 */     super(paramRarity, EnchantmentSlotType.BOW, paramVarArgs);
/*  8 */     c("arrowInfinite");
/*    */   }
/*    */ 
/*    */   
/*    */   public int a(int paramInt) {
/* 13 */     return 20;
/*    */   }
/*    */ 
/*    */   
/*    */   public int b(int paramInt) {
/* 18 */     return 50;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaxLevel() {
/* 23 */     return 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(Enchantment paramEnchantment) {
/* 28 */     if (paramEnchantment instanceof EnchantmentMending) {
/* 29 */       return false;
/*    */     }
/* 31 */     return super.a(paramEnchantment);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EnchantmentInfiniteArrows.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */