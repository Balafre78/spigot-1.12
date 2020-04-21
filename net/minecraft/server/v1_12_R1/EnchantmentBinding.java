/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ public class EnchantmentBinding
/*    */   extends Enchantment
/*    */ {
/*    */   public EnchantmentBinding(Enchantment.Rarity paramRarity, EnumItemSlot... paramVarArgs) {
/*  7 */     super(paramRarity, EnchantmentSlotType.WEARABLE, paramVarArgs);
/*  8 */     c("binding_curse");
/*    */   }
/*    */ 
/*    */   
/*    */   public int a(int paramInt) {
/* 13 */     return 25;
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
/*    */   public boolean isTreasure() {
/* 28 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCursed() {
/* 33 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EnchantmentBinding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */