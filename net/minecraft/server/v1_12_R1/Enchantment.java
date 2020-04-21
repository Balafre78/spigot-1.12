/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.enchantments.CraftEnchantment;
/*     */ 
/*     */ public abstract class Enchantment {
/*  10 */   public static final RegistryMaterials<MinecraftKey, Enchantment> enchantments = new RegistryMaterials<>();
/*     */   private final EnumItemSlot[] a;
/*     */   private final Rarity e;
/*     */   @Nullable
/*     */   public EnchantmentSlotType itemTarget;
/*     */   protected String d;
/*     */   
/*     */   @Nullable
/*     */   public static Enchantment c(int i) {
/*  19 */     return enchantments.getId(i);
/*     */   }
/*     */   
/*     */   public static int getId(Enchantment enchantment) {
/*  23 */     return enchantments.a(enchantment);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static Enchantment b(String s) {
/*  28 */     return enchantments.get(new MinecraftKey(s));
/*     */   }
/*     */   
/*     */   protected Enchantment(Rarity enchantment_rarity, EnchantmentSlotType enchantmentslottype, EnumItemSlot[] aenumitemslot) {
/*  32 */     this.e = enchantment_rarity;
/*  33 */     this.itemTarget = enchantmentslottype;
/*  34 */     this.a = aenumitemslot;
/*     */   }
/*     */   
/*     */   public List<ItemStack> a(EntityLiving entityliving) {
/*  38 */     ArrayList<ItemStack> arraylist = Lists.newArrayList();
/*  39 */     EnumItemSlot[] aenumitemslot = this.a;
/*  40 */     int i = aenumitemslot.length;
/*     */     
/*  42 */     for (int j = 0; j < i; j++) {
/*  43 */       EnumItemSlot enumitemslot = aenumitemslot[j];
/*  44 */       ItemStack itemstack = entityliving.getEquipment(enumitemslot);
/*     */       
/*  46 */       if (!itemstack.isEmpty()) {
/*  47 */         arraylist.add(itemstack);
/*     */       }
/*     */     } 
/*     */     
/*  51 */     return arraylist;
/*     */   }
/*     */   
/*     */   public Rarity e() {
/*  55 */     return this.e;
/*     */   }
/*     */   
/*     */   public int getStartLevel() {
/*  59 */     return 1;
/*     */   }
/*     */   
/*     */   public int getMaxLevel() {
/*  63 */     return 1;
/*     */   }
/*     */   
/*     */   public int a(int i) {
/*  67 */     return 1 + i * 10;
/*     */   }
/*     */   
/*     */   public int b(int i) {
/*  71 */     return a(i) + 5;
/*     */   }
/*     */   
/*     */   public int a(int i, DamageSource damagesource) {
/*  75 */     return 0;
/*     */   }
/*     */   
/*     */   public float a(int i, EnumMonsterType enummonstertype) {
/*  79 */     return 0.0F;
/*     */   }
/*     */   
/*     */   public final boolean c(Enchantment enchantment) {
/*  83 */     return (a(enchantment) && enchantment.a(this));
/*     */   }
/*     */   
/*     */   protected boolean a(Enchantment enchantment) {
/*  87 */     return (this != enchantment);
/*     */   }
/*     */   
/*     */   public Enchantment c(String s) {
/*  91 */     this.d = s;
/*  92 */     return this;
/*     */   }
/*     */   
/*     */   public String a() {
/*  96 */     return "enchantment." + this.d;
/*     */   }
/*     */   
/*     */   public String d(int i) {
/* 100 */     String s = LocaleI18n.get(a());
/*     */     
/* 102 */     if (isCursed()) {
/* 103 */       s = EnumChatFormat.RED + s;
/*     */     }
/*     */     
/* 106 */     return (i == 1 && getMaxLevel() == 1) ? s : (String.valueOf(s) + " " + LocaleI18n.get("enchantment.level." + i));
/*     */   }
/*     */   
/*     */   public boolean canEnchant(ItemStack itemstack) {
/* 110 */     return this.itemTarget.canEnchant(itemstack.getItem());
/*     */   }
/*     */   
/*     */   public void a(EntityLiving entityliving, Entity entity, int i) {}
/*     */   
/*     */   public void b(EntityLiving entityliving, Entity entity, int i) {}
/*     */   
/*     */   public boolean isTreasure() {
/* 118 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isCursed() {
/* 122 */     return false;
/*     */   }
/*     */   
/*     */   public static void g() {
/* 126 */     EnumItemSlot[] aenumitemslot = { EnumItemSlot.HEAD, EnumItemSlot.CHEST, EnumItemSlot.LEGS, EnumItemSlot.FEET };
/*     */     
/* 128 */     enchantments.a(0, new MinecraftKey("protection"), new EnchantmentProtection(Rarity.COMMON, EnchantmentProtection.DamageType.ALL, aenumitemslot));
/* 129 */     enchantments.a(1, new MinecraftKey("fire_protection"), new EnchantmentProtection(Rarity.UNCOMMON, EnchantmentProtection.DamageType.FIRE, aenumitemslot));
/* 130 */     enchantments.a(2, new MinecraftKey("feather_falling"), new EnchantmentProtection(Rarity.UNCOMMON, EnchantmentProtection.DamageType.FALL, aenumitemslot));
/* 131 */     enchantments.a(3, new MinecraftKey("blast_protection"), new EnchantmentProtection(Rarity.RARE, EnchantmentProtection.DamageType.EXPLOSION, aenumitemslot));
/* 132 */     enchantments.a(4, new MinecraftKey("projectile_protection"), new EnchantmentProtection(Rarity.UNCOMMON, EnchantmentProtection.DamageType.PROJECTILE, aenumitemslot));
/* 133 */     enchantments.a(5, new MinecraftKey("respiration"), new EnchantmentOxygen(Rarity.RARE, aenumitemslot));
/* 134 */     enchantments.a(6, new MinecraftKey("aqua_affinity"), new EnchantmentWaterWorker(Rarity.RARE, aenumitemslot));
/* 135 */     enchantments.a(7, new MinecraftKey("thorns"), new EnchantmentThorns(Rarity.VERY_RARE, aenumitemslot));
/* 136 */     enchantments.a(8, new MinecraftKey("depth_strider"), new EnchantmentDepthStrider(Rarity.RARE, aenumitemslot));
/* 137 */     enchantments.a(9, new MinecraftKey("frost_walker"), new EnchantmentFrostWalker(Rarity.RARE, new EnumItemSlot[] { EnumItemSlot.FEET }));
/* 138 */     enchantments.a(10, new MinecraftKey("binding_curse"), new EnchantmentBinding(Rarity.VERY_RARE, aenumitemslot));
/* 139 */     enchantments.a(16, new MinecraftKey("sharpness"), new EnchantmentWeaponDamage(Rarity.COMMON, 0, new EnumItemSlot[] { EnumItemSlot.MAINHAND }));
/* 140 */     enchantments.a(17, new MinecraftKey("smite"), new EnchantmentWeaponDamage(Rarity.UNCOMMON, 1, new EnumItemSlot[] { EnumItemSlot.MAINHAND }));
/* 141 */     enchantments.a(18, new MinecraftKey("bane_of_arthropods"), new EnchantmentWeaponDamage(Rarity.UNCOMMON, 2, new EnumItemSlot[] { EnumItemSlot.MAINHAND }));
/* 142 */     enchantments.a(19, new MinecraftKey("knockback"), new EnchantmentKnockback(Rarity.UNCOMMON, new EnumItemSlot[] { EnumItemSlot.MAINHAND }));
/* 143 */     enchantments.a(20, new MinecraftKey("fire_aspect"), new EnchantmentFire(Rarity.RARE, new EnumItemSlot[] { EnumItemSlot.MAINHAND }));
/* 144 */     enchantments.a(21, new MinecraftKey("looting"), new EnchantmentLootBonus(Rarity.RARE, EnchantmentSlotType.WEAPON, new EnumItemSlot[] { EnumItemSlot.MAINHAND }));
/* 145 */     enchantments.a(22, new MinecraftKey("sweeping"), new EnchantmentSweeping(Rarity.RARE, new EnumItemSlot[] { EnumItemSlot.MAINHAND }));
/* 146 */     enchantments.a(32, new MinecraftKey("efficiency"), new EnchantmentDigging(Rarity.COMMON, new EnumItemSlot[] { EnumItemSlot.MAINHAND }));
/* 147 */     enchantments.a(33, new MinecraftKey("silk_touch"), new EnchantmentSilkTouch(Rarity.VERY_RARE, new EnumItemSlot[] { EnumItemSlot.MAINHAND }));
/* 148 */     enchantments.a(34, new MinecraftKey("unbreaking"), new EnchantmentDurability(Rarity.UNCOMMON, new EnumItemSlot[] { EnumItemSlot.MAINHAND }));
/* 149 */     enchantments.a(35, new MinecraftKey("fortune"), new EnchantmentLootBonus(Rarity.RARE, EnchantmentSlotType.DIGGER, new EnumItemSlot[] { EnumItemSlot.MAINHAND }));
/* 150 */     enchantments.a(48, new MinecraftKey("power"), new EnchantmentArrowDamage(Rarity.COMMON, new EnumItemSlot[] { EnumItemSlot.MAINHAND }));
/* 151 */     enchantments.a(49, new MinecraftKey("punch"), new EnchantmentArrowKnockback(Rarity.RARE, new EnumItemSlot[] { EnumItemSlot.MAINHAND }));
/* 152 */     enchantments.a(50, new MinecraftKey("flame"), new EnchantmentFlameArrows(Rarity.RARE, new EnumItemSlot[] { EnumItemSlot.MAINHAND }));
/* 153 */     enchantments.a(51, new MinecraftKey("infinity"), new EnchantmentInfiniteArrows(Rarity.VERY_RARE, new EnumItemSlot[] { EnumItemSlot.MAINHAND }));
/* 154 */     enchantments.a(61, new MinecraftKey("luck_of_the_sea"), new EnchantmentLootBonus(Rarity.RARE, EnchantmentSlotType.FISHING_ROD, new EnumItemSlot[] { EnumItemSlot.MAINHAND }));
/* 155 */     enchantments.a(62, new MinecraftKey("lure"), new EnchantmentLure(Rarity.RARE, EnchantmentSlotType.FISHING_ROD, new EnumItemSlot[] { EnumItemSlot.MAINHAND }));
/* 156 */     enchantments.a(70, new MinecraftKey("mending"), new EnchantmentMending(Rarity.RARE, EnumItemSlot.values()));
/* 157 */     enchantments.a(71, new MinecraftKey("vanishing_curse"), new EnchantmentVanishing(Rarity.VERY_RARE, EnumItemSlot.values()));
/*     */     
/* 159 */     for (Object enchantment : enchantments) {
/* 160 */       org.bukkit.enchantments.Enchantment.registerEnchantment((org.bukkit.enchantments.Enchantment)new CraftEnchantment((Enchantment)enchantment));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public enum Rarity
/*     */   {
/* 167 */     COMMON(10), UNCOMMON(5), RARE(2), VERY_RARE(1);
/*     */     
/*     */     private final int e;
/*     */     
/*     */     Rarity(int i) {
/* 172 */       this.e = i;
/*     */     }
/*     */     
/*     */     public int a() {
/* 176 */       return this.e;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\Enchantment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */