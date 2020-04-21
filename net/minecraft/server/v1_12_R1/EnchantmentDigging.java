/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnchantmentDigging
/*    */   extends Enchantment
/*    */ {
/*    */   protected EnchantmentDigging(Enchantment.Rarity paramRarity, EnumItemSlot... paramVarArgs) {
/*  9 */     super(paramRarity, EnchantmentSlotType.DIGGER, paramVarArgs);
/*    */     
/* 11 */     c("digging");
/*    */   }
/*    */ 
/*    */   
/*    */   public int a(int paramInt) {
/* 16 */     return 1 + 10 * (paramInt - 1);
/*    */   }
/*    */ 
/*    */   
/*    */   public int b(int paramInt) {
/* 21 */     return super.a(paramInt) + 50;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaxLevel() {
/* 26 */     return 5;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canEnchant(ItemStack paramItemStack) {
/* 31 */     if (paramItemStack.getItem() == Items.SHEARS) {
/* 32 */       return true;
/*    */     }
/* 34 */     return super.canEnchant(paramItemStack);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EnchantmentDigging.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */