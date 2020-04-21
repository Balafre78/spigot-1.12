/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnchantmentWeaponDamage
/*    */   extends Enchantment
/*    */ {
/* 18 */   private static final String[] e = new String[] { "all", "undead", "arthropods" };
/*    */ 
/*    */ 
/*    */   
/* 22 */   private static final int[] f = new int[] { 1, 5, 5 };
/*    */ 
/*    */ 
/*    */   
/* 26 */   private static final int[] g = new int[] { 11, 8, 8 };
/*    */ 
/*    */ 
/*    */   
/* 30 */   private static final int[] h = new int[] { 20, 20, 20 };
/*    */ 
/*    */   
/*    */   public final int a;
/*    */ 
/*    */   
/*    */   public EnchantmentWeaponDamage(Enchantment.Rarity paramRarity, int paramInt, EnumItemSlot... paramVarArgs) {
/* 37 */     super(paramRarity, EnchantmentSlotType.WEAPON, paramVarArgs);
/* 38 */     this.a = paramInt;
/*    */   }
/*    */ 
/*    */   
/*    */   public int a(int paramInt) {
/* 43 */     return f[this.a] + (paramInt - 1) * g[this.a];
/*    */   }
/*    */ 
/*    */   
/*    */   public int b(int paramInt) {
/* 48 */     return a(paramInt) + h[this.a];
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaxLevel() {
/* 53 */     return 5;
/*    */   }
/*    */ 
/*    */   
/*    */   public float a(int paramInt, EnumMonsterType paramEnumMonsterType) {
/* 58 */     if (this.a == 0) {
/* 59 */       return 1.0F + Math.max(0, paramInt - 1) * 0.5F;
/*    */     }
/* 61 */     if (this.a == 1 && paramEnumMonsterType == EnumMonsterType.UNDEAD) {
/* 62 */       return paramInt * 2.5F;
/*    */     }
/* 64 */     if (this.a == 2 && paramEnumMonsterType == EnumMonsterType.ARTHROPOD) {
/* 65 */       return paramInt * 2.5F;
/*    */     }
/* 67 */     return 0.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public String a() {
/* 72 */     return "enchantment.damage." + e[this.a];
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(Enchantment paramEnchantment) {
/* 77 */     return !(paramEnchantment instanceof EnchantmentWeaponDamage);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canEnchant(ItemStack paramItemStack) {
/* 82 */     if (paramItemStack.getItem() instanceof ItemAxe) {
/* 83 */       return true;
/*    */     }
/* 85 */     return super.canEnchant(paramItemStack);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(EntityLiving paramEntityLiving, Entity paramEntity, int paramInt) {
/* 90 */     if (paramEntity instanceof EntityLiving) {
/* 91 */       EntityLiving entityLiving = (EntityLiving)paramEntity;
/*    */       
/* 93 */       if (this.a == 2 && entityLiving.getMonsterType() == EnumMonsterType.ARTHROPOD) {
/* 94 */         int i = 20 + paramEntityLiving.getRandom().nextInt(10 * paramInt);
/* 95 */         entityLiving.addEffect(new MobEffect(MobEffects.SLOWER_MOVEMENT, i, 3));
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EnchantmentWeaponDamage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */