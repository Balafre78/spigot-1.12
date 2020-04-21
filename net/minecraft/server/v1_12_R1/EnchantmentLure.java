/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ public class EnchantmentLure
/*    */   extends Enchantment
/*    */ {
/*    */   protected EnchantmentLure(Enchantment.Rarity paramRarity, EnchantmentSlotType paramEnchantmentSlotType, EnumItemSlot... paramVarArgs) {
/*  7 */     super(paramRarity, paramEnchantmentSlotType, paramVarArgs);
/*    */     
/*  9 */     c("fishingSpeed");
/*    */   }
/*    */ 
/*    */   
/*    */   public int a(int paramInt) {
/* 14 */     return 15 + (paramInt - 1) * 9;
/*    */   }
/*    */ 
/*    */   
/*    */   public int b(int paramInt) {
/* 19 */     return super.a(paramInt) + 50;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaxLevel() {
/* 24 */     return 3;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EnchantmentLure.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */