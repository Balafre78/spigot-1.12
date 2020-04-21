/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ public class EnchantmentThorns
/*    */   extends Enchantment {
/*    */   public EnchantmentThorns(Enchantment.Rarity enchantment_rarity, EnumItemSlot... aenumitemslot) {
/*  8 */     super(enchantment_rarity, EnchantmentSlotType.ARMOR_CHEST, aenumitemslot);
/*  9 */     c("thorns");
/*    */   }
/*    */   
/*    */   public int a(int i) {
/* 13 */     return 10 + 20 * (i - 1);
/*    */   }
/*    */   
/*    */   public int b(int i) {
/* 17 */     return super.a(i) + 50;
/*    */   }
/*    */   
/*    */   public int getMaxLevel() {
/* 21 */     return 3;
/*    */   }
/*    */   
/*    */   public boolean canEnchant(ItemStack itemstack) {
/* 25 */     return (itemstack.getItem() instanceof ItemArmor) ? true : super.canEnchant(itemstack);
/*    */   }
/*    */   
/*    */   public void b(EntityLiving entityliving, Entity entity, int i) {
/* 29 */     Random random = entityliving.getRandom();
/* 30 */     ItemStack itemstack = EnchantmentManager.b(Enchantments.THORNS, entityliving);
/*    */     
/* 32 */     if (entity != null && a(i, random)) {
/* 33 */       if (entity != null) {
/* 34 */         entity.damageEntity(DamageSource.a(entityliving), b(i, random));
/*    */       }
/*    */       
/* 37 */       if (!itemstack.isEmpty()) {
/* 38 */         itemstack.damage(3, entityliving);
/*    */       }
/* 40 */     } else if (!itemstack.isEmpty()) {
/* 41 */       itemstack.damage(1, entityliving);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static boolean a(int i, Random random) {
/* 47 */     return (i <= 0) ? false : ((random.nextFloat() < 0.15F * i));
/*    */   }
/*    */   
/*    */   public static int b(int i, Random random) {
/* 51 */     return (i > 10) ? (i - 10) : (1 + random.nextInt(4));
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EnchantmentThorns.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */