/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ public class EnchantmentDepthStrider
/*    */   extends Enchantment
/*    */ {
/*    */   public EnchantmentDepthStrider(Enchantment.Rarity paramRarity, EnumItemSlot... paramVarArgs) {
/*  7 */     super(paramRarity, EnchantmentSlotType.ARMOR_FEET, paramVarArgs);
/*  8 */     c("waterWalker");
/*    */   }
/*    */ 
/*    */   
/*    */   public int a(int paramInt) {
/* 13 */     return paramInt * 10;
/*    */   }
/*    */ 
/*    */   
/*    */   public int b(int paramInt) {
/* 18 */     return a(paramInt) + 15;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaxLevel() {
/* 23 */     return 3;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(Enchantment paramEnchantment) {
/* 28 */     return (super.a(paramEnchantment) && paramEnchantment != Enchantments.j);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EnchantmentDepthStrider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */