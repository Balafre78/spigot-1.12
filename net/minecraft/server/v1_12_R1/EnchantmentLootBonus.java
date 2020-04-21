/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ public class EnchantmentLootBonus
/*    */   extends Enchantment
/*    */ {
/*    */   protected EnchantmentLootBonus(Enchantment.Rarity paramRarity, EnchantmentSlotType paramEnchantmentSlotType, EnumItemSlot... paramVarArgs) {
/*  7 */     super(paramRarity, paramEnchantmentSlotType, paramVarArgs);
/*    */     
/*  9 */     if (paramEnchantmentSlotType == EnchantmentSlotType.DIGGER) {
/* 10 */       c("lootBonusDigger");
/* 11 */     } else if (paramEnchantmentSlotType == EnchantmentSlotType.FISHING_ROD) {
/* 12 */       c("lootBonusFishing");
/*    */     } else {
/* 14 */       c("lootBonus");
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public int a(int paramInt) {
/* 20 */     return 15 + (paramInt - 1) * 9;
/*    */   }
/*    */ 
/*    */   
/*    */   public int b(int paramInt) {
/* 25 */     return super.a(paramInt) + 50;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaxLevel() {
/* 30 */     return 3;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(Enchantment paramEnchantment) {
/* 35 */     return (super.a(paramEnchantment) && paramEnchantment != Enchantments.SILK_TOUCH);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EnchantmentLootBonus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */