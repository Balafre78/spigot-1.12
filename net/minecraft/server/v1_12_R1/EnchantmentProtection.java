/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ public class EnchantmentProtection
/*     */   extends Enchantment
/*     */ {
/*     */   public final DamageType a;
/*     */   
/*     */   public enum DamageType
/*     */   {
/*  10 */     ALL("all", 1, 11, 20),
/*  11 */     FIRE("fire", 10, 8, 12),
/*  12 */     FALL("fall", 5, 6, 10),
/*  13 */     EXPLOSION("explosion", 5, 8, 12),
/*  14 */     PROJECTILE("projectile", 3, 6, 15);
/*     */     
/*     */     private final String f;
/*     */     private final int g;
/*     */     private final int h;
/*     */     private final int i;
/*     */     
/*     */     DamageType(String param1String1, int param1Int1, int param1Int2, int param1Int3) {
/*  22 */       this.f = param1String1;
/*  23 */       this.g = param1Int1;
/*  24 */       this.h = param1Int2;
/*  25 */       this.i = param1Int3;
/*     */     }
/*     */     
/*     */     public String a() {
/*  29 */       return this.f;
/*     */     }
/*     */     
/*     */     public int b() {
/*  33 */       return this.g;
/*     */     }
/*     */     
/*     */     public int c() {
/*  37 */       return this.h;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnchantmentProtection(Enchantment.Rarity paramRarity, DamageType paramDamageType, EnumItemSlot... paramVarArgs) {
/*  48 */     super(paramRarity, EnchantmentSlotType.ARMOR, paramVarArgs);
/*  49 */     this.a = paramDamageType;
/*     */     
/*  51 */     if (paramDamageType == DamageType.FALL) {
/*  52 */       this.itemTarget = EnchantmentSlotType.ARMOR_FEET;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public int a(int paramInt) {
/*  58 */     return this.a.b() + (paramInt - 1) * this.a.c();
/*     */   }
/*     */ 
/*     */   
/*     */   public int b(int paramInt) {
/*  63 */     return a(paramInt) + this.a.c();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxLevel() {
/*  68 */     return 4;
/*     */   }
/*     */ 
/*     */   
/*     */   public int a(int paramInt, DamageSource paramDamageSource) {
/*  73 */     if (paramDamageSource.ignoresInvulnerability()) {
/*  74 */       return 0;
/*     */     }
/*     */     
/*  77 */     if (this.a == DamageType.ALL) {
/*  78 */       return paramInt;
/*     */     }
/*  80 */     if (this.a == DamageType.FIRE && paramDamageSource.o()) {
/*  81 */       return paramInt * 2;
/*     */     }
/*  83 */     if (this.a == DamageType.FALL && paramDamageSource == DamageSource.FALL) {
/*  84 */       return paramInt * 3;
/*     */     }
/*  86 */     if (this.a == DamageType.EXPLOSION && paramDamageSource.isExplosion()) {
/*  87 */       return paramInt * 2;
/*     */     }
/*  89 */     if (this.a == DamageType.PROJECTILE && paramDamageSource.a()) {
/*  90 */       return paramInt * 2;
/*     */     }
/*  92 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public String a() {
/*  97 */     return "enchantment.protect." + this.a.a();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(Enchantment paramEnchantment) {
/* 102 */     if (paramEnchantment instanceof EnchantmentProtection) {
/* 103 */       EnchantmentProtection enchantmentProtection = (EnchantmentProtection)paramEnchantment;
/*     */       
/* 105 */       if (this.a == enchantmentProtection.a) {
/* 106 */         return false;
/*     */       }
/* 108 */       if (this.a == DamageType.FALL || enchantmentProtection.a == DamageType.FALL) {
/* 109 */         return true;
/*     */       }
/* 111 */       return false;
/*     */     } 
/* 113 */     return super.a(paramEnchantment);
/*     */   }
/*     */   
/*     */   public static int a(EntityLiving paramEntityLiving, int paramInt) {
/* 117 */     int i = EnchantmentManager.a(Enchantments.PROTECTION_FIRE, paramEntityLiving);
/*     */     
/* 119 */     if (i > 0) {
/* 120 */       paramInt -= MathHelper.d(paramInt * i * 0.15F);
/*     */     }
/*     */     
/* 123 */     return paramInt;
/*     */   }
/*     */   
/*     */   public static double a(EntityLiving paramEntityLiving, double paramDouble) {
/* 127 */     int i = EnchantmentManager.a(Enchantments.PROTECTION_EXPLOSIONS, paramEntityLiving);
/*     */     
/* 129 */     if (i > 0) {
/* 130 */       paramDouble -= MathHelper.floor(paramDouble * (i * 0.15F));
/*     */     }
/*     */     
/* 133 */     return paramDouble;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EnchantmentProtection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */