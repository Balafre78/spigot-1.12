/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ public class EnchantmentSilkTouch
/*    */   extends Enchantment
/*    */ {
/*    */   protected EnchantmentSilkTouch(Enchantment.Rarity paramRarity, EnumItemSlot... paramVarArgs) {
/*  7 */     super(paramRarity, EnchantmentSlotType.DIGGER, paramVarArgs);
/*    */     
/*  9 */     c("untouching");
/*    */   }
/*    */ 
/*    */   
/*    */   public int a(int paramInt) {
/* 14 */     return 15;
/*    */   }
/*    */ 
/*    */   
/*    */   public int b(int paramInt) {
/* 19 */     return super.a(paramInt) + 50;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaxLevel() {
/* 24 */     return 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(Enchantment paramEnchantment) {
/* 29 */     return (super.a(paramEnchantment) && paramEnchantment != Enchantments.LOOT_BONUS_BLOCKS);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EnchantmentSilkTouch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */