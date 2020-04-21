/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EnchantmentManager
/*     */ {
/*     */   public static int getEnchantmentLevel(Enchantment paramEnchantment, ItemStack paramItemStack) {
/*  27 */     if (paramItemStack.isEmpty()) {
/*  28 */       return 0;
/*     */     }
/*     */     
/*  31 */     NBTTagList nBTTagList = paramItemStack.getEnchantments();
/*  32 */     for (byte b = 0; b < nBTTagList.size(); b++) {
/*  33 */       NBTTagCompound nBTTagCompound = nBTTagList.get(b);
/*     */       
/*  35 */       Enchantment enchantment = Enchantment.c(nBTTagCompound.getShort("id"));
/*  36 */       short s = nBTTagCompound.getShort("lvl");
/*     */       
/*  38 */       if (enchantment == paramEnchantment) {
/*  39 */         return s;
/*     */       }
/*     */     } 
/*  42 */     return 0;
/*     */   }
/*     */   
/*     */   public static Map<Enchantment, Integer> a(ItemStack paramItemStack) {
/*  46 */     LinkedHashMap<Enchantment, Integer> linkedHashMap = Maps.newLinkedHashMap();
/*  47 */     NBTTagList nBTTagList = (paramItemStack.getItem() == Items.ENCHANTED_BOOK) ? ItemEnchantedBook.h(paramItemStack) : paramItemStack.getEnchantments();
/*     */     
/*  49 */     for (byte b = 0; b < nBTTagList.size(); b++) {
/*  50 */       NBTTagCompound nBTTagCompound = nBTTagList.get(b);
/*     */       
/*  52 */       Enchantment enchantment = Enchantment.c(nBTTagCompound.getShort("id"));
/*  53 */       short s = nBTTagCompound.getShort("lvl");
/*     */       
/*  55 */       linkedHashMap.put(enchantment, Integer.valueOf(s));
/*     */     } 
/*     */     
/*  58 */     return linkedHashMap;
/*     */   }
/*     */   
/*     */   public static void a(Map<Enchantment, Integer> paramMap, ItemStack paramItemStack) {
/*  62 */     NBTTagList nBTTagList = new NBTTagList();
/*     */     
/*  64 */     for (Map.Entry<Enchantment, Integer> entry : paramMap.entrySet()) {
/*  65 */       Enchantment enchantment = (Enchantment)entry.getKey();
/*  66 */       if (enchantment == null) {
/*     */         continue;
/*     */       }
/*  69 */       int i = ((Integer)entry.getValue()).intValue();
/*     */       
/*  71 */       NBTTagCompound nBTTagCompound = new NBTTagCompound();
/*  72 */       nBTTagCompound.setShort("id", (short)Enchantment.getId(enchantment));
/*  73 */       nBTTagCompound.setShort("lvl", (short)i);
/*     */       
/*  75 */       nBTTagList.add(nBTTagCompound);
/*     */       
/*  77 */       if (paramItemStack.getItem() == Items.ENCHANTED_BOOK) {
/*  78 */         ItemEnchantedBook.a(paramItemStack, new WeightedRandomEnchant(enchantment, i));
/*     */       }
/*     */     } 
/*     */     
/*  82 */     if (nBTTagList.isEmpty()) {
/*  83 */       if (paramItemStack.hasTag()) {
/*  84 */         paramItemStack.getTag().remove("ench");
/*     */       }
/*  86 */     } else if (paramItemStack.getItem() != Items.ENCHANTED_BOOK) {
/*  87 */       paramItemStack.a("ench", nBTTagList);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void a(EnchantmentModifier paramEnchantmentModifier, ItemStack paramItemStack) {
/*  96 */     if (paramItemStack.isEmpty()) {
/*     */       return;
/*     */     }
/*     */     
/* 100 */     NBTTagList nBTTagList = paramItemStack.getEnchantments();
/* 101 */     for (byte b = 0; b < nBTTagList.size(); b++) {
/* 102 */       short s1 = nBTTagList.get(b).getShort("id");
/* 103 */       short s2 = nBTTagList.get(b).getShort("lvl");
/*     */       
/* 105 */       if (Enchantment.c(s1) != null) {
/* 106 */         paramEnchantmentModifier.a(Enchantment.c(s1), s2);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void a(EnchantmentModifier paramEnchantmentModifier, Iterable<ItemStack> paramIterable) {
/* 112 */     for (ItemStack itemStack : paramIterable)
/* 113 */       a(paramEnchantmentModifier, itemStack); 
/*     */   }
/*     */   
/*     */   static interface EnchantmentModifier {
/*     */     void a(Enchantment param1Enchantment, int param1Int);
/*     */   }
/*     */   
/*     */   static final class EnchantmentModifierProtection implements EnchantmentModifier { public int a;
/*     */     
/*     */     public void a(Enchantment param1Enchantment, int param1Int) {
/* 123 */       this.a += param1Enchantment.a(param1Int, this.b);
/*     */     }
/*     */     public DamageSource b;
/*     */     private EnchantmentModifierProtection() {} }
/* 127 */   private static final EnchantmentModifierProtection a = new EnchantmentModifierProtection();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int a(Iterable<ItemStack> paramIterable, DamageSource paramDamageSource) {
/* 137 */     a.a = 0;
/* 138 */     a.b = paramDamageSource;
/*     */     
/* 140 */     a(a, paramIterable);
/*     */ 
/*     */ 
/*     */     
/* 144 */     return a.a;
/*     */   }
/*     */   
/*     */   static final class EnchantmentModifierDamage implements EnchantmentModifier { public float a;
/*     */     public EnumMonsterType b;
/*     */     
/*     */     private EnchantmentModifierDamage() {}
/*     */     
/*     */     public void a(Enchantment param1Enchantment, int param1Int) {
/* 153 */       this.a += param1Enchantment.a(param1Int, this.b);
/*     */     } }
/*     */ 
/*     */   
/* 157 */   private static final EnchantmentModifierDamage b = new EnchantmentModifierDamage();
/*     */   
/*     */   public static float a(ItemStack paramItemStack, EnumMonsterType paramEnumMonsterType) {
/* 160 */     b.a = 0.0F;
/* 161 */     b.b = paramEnumMonsterType;
/*     */     
/* 163 */     a(b, paramItemStack);
/*     */     
/* 165 */     return b.a;
/*     */   }
/*     */   
/*     */   public static float a(EntityLiving paramEntityLiving) {
/* 169 */     int i = a(Enchantments.r, paramEntityLiving);
/* 170 */     if (i > 0) {
/* 171 */       return EnchantmentSweeping.e(i);
/*     */     }
/* 173 */     return 0.0F;
/*     */   }
/*     */   
/*     */   static final class EnchantmentModifierThorns implements EnchantmentModifier { public EntityLiving a;
/*     */     public Entity b;
/*     */     
/*     */     private EnchantmentModifierThorns() {}
/*     */     
/*     */     public void a(Enchantment param1Enchantment, int param1Int) {
/* 182 */       param1Enchantment.b(this.a, this.b, param1Int);
/*     */     } }
/*     */ 
/*     */   
/* 186 */   private static final EnchantmentModifierThorns c = new EnchantmentModifierThorns();
/*     */   
/*     */   public static void a(EntityLiving paramEntityLiving, Entity paramEntity) {
/* 189 */     c.b = paramEntity;
/* 190 */     c.a = paramEntityLiving;
/* 191 */     if (paramEntityLiving != null) {
/* 192 */       a(c, paramEntityLiving.aQ());
/*     */     }
/* 194 */     if (paramEntity instanceof EntityHuman)
/* 195 */       a(c, paramEntityLiving.getItemInMainHand()); 
/*     */   }
/*     */   
/*     */   static final class EnchantmentModifierArthropods implements EnchantmentModifier {
/*     */     public EntityLiving a;
/*     */     public Entity b;
/*     */     
/*     */     private EnchantmentModifierArthropods() {}
/*     */     
/*     */     public void a(Enchantment param1Enchantment, int param1Int) {
/* 205 */       param1Enchantment.a(this.a, this.b, param1Int);
/*     */     }
/*     */   }
/*     */   
/* 209 */   private static final EnchantmentModifierArthropods d = new EnchantmentModifierArthropods();
/*     */   
/*     */   public static void b(EntityLiving paramEntityLiving, Entity paramEntity) {
/* 212 */     d.a = paramEntityLiving;
/* 213 */     d.b = paramEntity;
/* 214 */     if (paramEntityLiving != null) {
/* 215 */       a(d, paramEntityLiving.aQ());
/*     */     }
/* 217 */     if (paramEntityLiving instanceof EntityHuman) {
/* 218 */       a(d, paramEntityLiving.getItemInMainHand());
/*     */     }
/*     */   }
/*     */   
/*     */   public static int a(Enchantment paramEnchantment, EntityLiving paramEntityLiving) {
/* 223 */     List<ItemStack> list = paramEnchantment.a(paramEntityLiving);
/* 224 */     if (list == null) {
/* 225 */       return 0;
/*     */     }
/* 227 */     int i = 0;
/* 228 */     for (ItemStack itemStack : list) {
/* 229 */       int j = getEnchantmentLevel(paramEnchantment, itemStack);
/* 230 */       if (j > i) {
/* 231 */         i = j;
/*     */       }
/*     */     } 
/* 234 */     return i;
/*     */   }
/*     */   
/*     */   public static int b(EntityLiving paramEntityLiving) {
/* 238 */     return a(Enchantments.KNOCKBACK, paramEntityLiving);
/*     */   }
/*     */   
/*     */   public static int getFireAspectEnchantmentLevel(EntityLiving paramEntityLiving) {
/* 242 */     return a(Enchantments.FIRE_ASPECT, paramEntityLiving);
/*     */   }
/*     */   
/*     */   public static int getOxygenEnchantmentLevel(EntityLiving paramEntityLiving) {
/* 246 */     return a(Enchantments.OXYGEN, paramEntityLiving);
/*     */   }
/*     */   
/*     */   public static int e(EntityLiving paramEntityLiving) {
/* 250 */     return a(Enchantments.DEPTH_STRIDER, paramEntityLiving);
/*     */   }
/*     */   
/*     */   public static int getDigSpeedEnchantmentLevel(EntityLiving paramEntityLiving) {
/* 254 */     return a(Enchantments.DIG_SPEED, paramEntityLiving);
/*     */   }
/*     */   
/*     */   public static int b(ItemStack paramItemStack) {
/* 258 */     return getEnchantmentLevel(Enchantments.LUCK, paramItemStack);
/*     */   }
/*     */   
/*     */   public static int c(ItemStack paramItemStack) {
/* 262 */     return getEnchantmentLevel(Enchantments.LURE, paramItemStack);
/*     */   }
/*     */   
/*     */   public static int g(EntityLiving paramEntityLiving) {
/* 266 */     return a(Enchantments.LOOT_BONUS_MOBS, paramEntityLiving);
/*     */   }
/*     */   
/*     */   public static boolean h(EntityLiving paramEntityLiving) {
/* 270 */     return (a(Enchantments.WATER_WORKER, paramEntityLiving) > 0);
/*     */   }
/*     */   
/*     */   public static boolean i(EntityLiving paramEntityLiving) {
/* 274 */     return (a(Enchantments.j, paramEntityLiving) > 0);
/*     */   }
/*     */   
/*     */   public static boolean d(ItemStack paramItemStack) {
/* 278 */     return (getEnchantmentLevel(Enchantments.k, paramItemStack) > 0);
/*     */   }
/*     */   
/*     */   public static boolean shouldNotDrop(ItemStack paramItemStack) {
/* 282 */     return (getEnchantmentLevel(Enchantments.D, paramItemStack) > 0);
/*     */   }
/*     */   
/*     */   public static ItemStack b(Enchantment paramEnchantment, EntityLiving paramEntityLiving) {
/* 286 */     List<ItemStack> list = paramEnchantment.a(paramEntityLiving);
/* 287 */     if (list.isEmpty()) {
/* 288 */       return ItemStack.a;
/*     */     }
/* 290 */     ArrayList<ItemStack> arrayList = Lists.newArrayList();
/* 291 */     for (ItemStack itemStack : list) {
/* 292 */       if (!itemStack.isEmpty() && getEnchantmentLevel(paramEnchantment, itemStack) > 0) {
/* 293 */         arrayList.add(itemStack);
/*     */       }
/*     */     } 
/*     */     
/* 297 */     return arrayList.isEmpty() ? ItemStack.a : arrayList.get(paramEntityLiving.getRandom().nextInt(arrayList.size()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int a(Random paramRandom, int paramInt1, int paramInt2, ItemStack paramItemStack) {
/* 308 */     Item item = paramItemStack.getItem();
/* 309 */     int i = item.c();
/*     */     
/* 311 */     if (i <= 0)
/*     */     {
/* 313 */       return 0;
/*     */     }
/*     */     
/* 316 */     if (paramInt2 > 15) {
/* 317 */       paramInt2 = 15;
/*     */     }
/* 319 */     int j = paramRandom.nextInt(8) + 1 + (paramInt2 >> 1) + paramRandom.nextInt(paramInt2 + 1);
/* 320 */     if (paramInt1 == 0) {
/* 321 */       return Math.max(j / 3, 1);
/*     */     }
/* 323 */     if (paramInt1 == 1) {
/* 324 */       return j * 2 / 3 + 1;
/*     */     }
/* 326 */     return Math.max(j, paramInt2 * 2);
/*     */   }
/*     */   
/*     */   public static ItemStack a(Random paramRandom, ItemStack paramItemStack, int paramInt, boolean paramBoolean) {
/* 330 */     List<WeightedRandomEnchant> list = b(paramRandom, paramItemStack, paramInt, paramBoolean);
/*     */     
/* 332 */     boolean bool = (paramItemStack.getItem() == Items.BOOK) ? true : false;
/* 333 */     if (bool) {
/* 334 */       paramItemStack = new ItemStack(Items.ENCHANTED_BOOK);
/*     */     }
/*     */     
/* 337 */     for (WeightedRandomEnchant weightedRandomEnchant : list) {
/* 338 */       if (bool) {
/* 339 */         ItemEnchantedBook.a(paramItemStack, weightedRandomEnchant); continue;
/*     */       } 
/* 341 */       paramItemStack.addEnchantment(weightedRandomEnchant.enchantment, weightedRandomEnchant.level);
/*     */     } 
/*     */ 
/*     */     
/* 345 */     return paramItemStack;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static List<WeightedRandomEnchant> b(Random paramRandom, ItemStack paramItemStack, int paramInt, boolean paramBoolean) {
/* 356 */     ArrayList<WeightedRandomEnchant> arrayList = Lists.newArrayList();
/*     */ 
/*     */     
/* 359 */     Item item = paramItemStack.getItem();
/* 360 */     int i = item.c();
/*     */     
/* 362 */     if (i <= 0) {
/* 363 */       return arrayList;
/*     */     }
/*     */     
/* 366 */     paramInt += 1 + paramRandom.nextInt(i / 4 + 1) + paramRandom.nextInt(i / 4 + 1);
/*     */ 
/*     */     
/* 369 */     float f = (paramRandom.nextFloat() + paramRandom.nextFloat() - 1.0F) * 0.15F;
/* 370 */     paramInt = MathHelper.clamp(Math.round(paramInt + paramInt * f), 1, 2147483647);
/*     */     
/* 372 */     List<WeightedRandomEnchant> list = a(paramInt, paramItemStack, paramBoolean);
/* 373 */     if (!list.isEmpty()) {
/* 374 */       arrayList.add(WeightedRandom.a(paramRandom, list));
/*     */       
/* 376 */       while (paramRandom.nextInt(50) <= paramInt) {
/* 377 */         a(list, SystemUtils.<WeightedRandomEnchant>a(arrayList));
/*     */         
/* 379 */         if (list.isEmpty()) {
/*     */           break;
/*     */         }
/*     */         
/* 383 */         arrayList.add(WeightedRandom.a(paramRandom, list));
/* 384 */         paramInt /= 2;
/*     */       } 
/*     */     } 
/* 387 */     return arrayList;
/*     */   }
/*     */   
/*     */   public static void a(List<WeightedRandomEnchant> paramList, WeightedRandomEnchant paramWeightedRandomEnchant) {
/* 391 */     Iterator<WeightedRandomEnchant> iterator = paramList.iterator();
/* 392 */     while (iterator.hasNext()) {
/* 393 */       if (!paramWeightedRandomEnchant.enchantment.c(((WeightedRandomEnchant)iterator.next()).enchantment)) {
/* 394 */         iterator.remove();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public static List<WeightedRandomEnchant> a(int paramInt, ItemStack paramItemStack, boolean paramBoolean) {
/* 400 */     ArrayList<WeightedRandomEnchant> arrayList = Lists.newArrayList();
/*     */     
/* 402 */     Item item = paramItemStack.getItem();
/* 403 */     boolean bool = (paramItemStack.getItem() == Items.BOOK) ? true : false;
/* 404 */     for (Enchantment enchantment : Enchantment.enchantments) {
/* 405 */       if (enchantment.isTreasure() && !paramBoolean) {
/*     */         continue;
/*     */       }
/*     */       
/* 409 */       if (!enchantment.itemTarget.canEnchant(item) && !bool) {
/*     */         continue;
/*     */       }
/*     */       
/* 413 */       for (int i = enchantment.getMaxLevel(); i > enchantment.getStartLevel() - 1; i--) {
/* 414 */         if (paramInt >= enchantment.a(i) && paramInt <= enchantment.b(i)) {
/* 415 */           arrayList.add(new WeightedRandomEnchant(enchantment, i));
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/* 421 */     return arrayList;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EnchantmentManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */