/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ public class EnchantmentFire
/*    */   extends Enchantment
/*    */ {
/*    */   protected EnchantmentFire(Enchantment.Rarity paramRarity, EnumItemSlot... paramVarArgs) {
/*  7 */     super(paramRarity, EnchantmentSlotType.WEAPON, paramVarArgs);
/*    */     
/*  9 */     c("fire");
/*    */   }
/*    */ 
/*    */   
/*    */   public int a(int paramInt) {
/* 14 */     return 10 + 20 * (paramInt - 1);
/*    */   }
/*    */ 
/*    */   
/*    */   public int b(int paramInt) {
/* 19 */     return super.a(paramInt) + 50;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaxLevel() {
/* 24 */     return 2;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EnchantmentFire.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */