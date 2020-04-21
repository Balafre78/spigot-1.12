/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnchantmentDurability
/*    */   extends Enchantment
/*    */ {
/*    */   protected EnchantmentDurability(Enchantment.Rarity paramRarity, EnumItemSlot... paramVarArgs) {
/* 11 */     super(paramRarity, EnchantmentSlotType.BREAKABLE, paramVarArgs);
/*    */     
/* 13 */     c("durability");
/*    */   }
/*    */ 
/*    */   
/*    */   public int a(int paramInt) {
/* 18 */     return 5 + (paramInt - 1) * 8;
/*    */   }
/*    */ 
/*    */   
/*    */   public int b(int paramInt) {
/* 23 */     return super.a(paramInt) + 50;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaxLevel() {
/* 28 */     return 3;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canEnchant(ItemStack paramItemStack) {
/* 33 */     if (paramItemStack.f()) {
/* 34 */       return true;
/*    */     }
/* 36 */     return super.canEnchant(paramItemStack);
/*    */   }
/*    */   
/*    */   public static boolean a(ItemStack paramItemStack, int paramInt, Random paramRandom) {
/* 40 */     if (paramItemStack.getItem() instanceof ItemArmor && paramRandom.nextFloat() < 0.6F) {
/* 41 */       return false;
/*    */     }
/* 43 */     return (paramRandom.nextInt(paramInt + 1) > 0);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EnchantmentDurability.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */