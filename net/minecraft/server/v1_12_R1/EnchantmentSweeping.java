/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ public class EnchantmentSweeping
/*    */   extends Enchantment
/*    */ {
/*    */   public EnchantmentSweeping(Enchantment.Rarity paramRarity, EnumItemSlot... paramVarArgs) {
/*  7 */     super(paramRarity, EnchantmentSlotType.WEAPON, paramVarArgs);
/*    */   }
/*    */ 
/*    */   
/*    */   public int a(int paramInt) {
/* 12 */     return 5 + (paramInt - 1) * 9;
/*    */   }
/*    */ 
/*    */   
/*    */   public int b(int paramInt) {
/* 17 */     return a(paramInt) + 15;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaxLevel() {
/* 22 */     return 3;
/*    */   }
/*    */   
/*    */   public static float e(int paramInt) {
/* 26 */     return 1.0F - 1.0F / (paramInt + 1);
/*    */   }
/*    */ 
/*    */   
/*    */   public String a() {
/* 31 */     return "enchantment.sweeping";
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EnchantmentSweeping.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */