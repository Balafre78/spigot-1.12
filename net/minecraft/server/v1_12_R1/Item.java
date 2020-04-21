/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.collect.HashMultimap;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.Multimap;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
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
/*     */ public class Item
/*     */ {
/*  63 */   public static final RegistryMaterials<MinecraftKey, Item> REGISTRY = new RegistryMaterials<>();
/*  64 */   private static final Map<Block, Item> a = Maps.newHashMap();
/*  65 */   private static final IDynamicTexture b = new IDynamicTexture()
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */   
/*  71 */   private static final IDynamicTexture c = new IDynamicTexture()
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */   
/*  77 */   private static final IDynamicTexture d = new IDynamicTexture()
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */   
/*  83 */   private static final IDynamicTexture e = new IDynamicTexture()
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */ 
/*     */   
/*  90 */   private final IRegistry<MinecraftKey, IDynamicTexture> f = new RegistrySimple<>();
/*     */   
/*  92 */   protected static final UUID h = UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5CF");
/*  93 */   protected static final UUID i = UUID.fromString("FA233E1C-4180-4865-B01B-BCCE9785ACA3");
/*     */ 
/*     */   
/*     */   private CreativeModeTab n;
/*     */ 
/*     */   
/*     */   public static int getId(Item paramItem) {
/* 100 */     return (paramItem == null) ? 0 : REGISTRY.a(paramItem);
/*     */   }
/*     */   
/*     */   public static Item getById(int paramInt) {
/* 104 */     return REGISTRY.getId(paramInt);
/*     */   }
/*     */   
/*     */   public static Item getItemOf(Block paramBlock) {
/* 108 */     Item item = a.get(paramBlock);
/* 109 */     return (item == null) ? Items.a : item;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static Item b(String paramString) {
/* 114 */     Item item = REGISTRY.get(new MinecraftKey(paramString));
/* 115 */     if (item == null) {
/*     */       try {
/* 117 */         return getById(Integer.parseInt(paramString));
/* 118 */       } catch (NumberFormatException numberFormatException) {}
/*     */     }
/*     */     
/* 121 */     return item;
/*     */   }
/*     */   
/*     */   public final void a(MinecraftKey paramMinecraftKey, IDynamicTexture paramIDynamicTexture) {
/* 125 */     this.f.a(paramMinecraftKey, paramIDynamicTexture);
/*     */   }
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
/*     */   public boolean a(NBTTagCompound paramNBTTagCompound) {
/* 138 */     return false;
/*     */   }
/*     */   
/*     */   public enum EnumToolMaterial {
/* 142 */     WOOD(0, 59, 2.0F, 0.0F, 15),
/* 143 */     STONE(1, 131, 4.0F, 1.0F, 5),
/* 144 */     IRON(2, 250, 6.0F, 2.0F, 14),
/* 145 */     DIAMOND(3, 1561, 8.0F, 3.0F, 10),
/* 146 */     GOLD(0, 32, 12.0F, 0.0F, 22);
/*     */     
/*     */     private final int f;
/*     */     private final int g;
/*     */     private final float h;
/*     */     private final float i;
/*     */     private final int j;
/*     */     
/*     */     EnumToolMaterial(int param1Int1, int param1Int2, float param1Float1, float param1Float2, int param1Int3) {
/* 155 */       this.f = param1Int1;
/* 156 */       this.g = param1Int2;
/* 157 */       this.h = param1Float1;
/* 158 */       this.i = param1Float2;
/* 159 */       this.j = param1Int3;
/*     */     }
/*     */     
/*     */     public int a() {
/* 163 */       return this.g;
/*     */     }
/*     */     
/*     */     public float b() {
/* 167 */       return this.h;
/*     */     }
/*     */     
/*     */     public float c() {
/* 171 */       return this.i;
/*     */     }
/*     */     
/*     */     public int d() {
/* 175 */       return this.f;
/*     */     }
/*     */     
/*     */     public int e() {
/* 179 */       return this.j;
/*     */     }
/*     */     
/*     */     public Item f() {
/* 183 */       if (this == WOOD)
/* 184 */         return Item.getItemOf(Blocks.PLANKS); 
/* 185 */       if (this == STONE)
/* 186 */         return Item.getItemOf(Blocks.COBBLESTONE); 
/* 187 */       if (this == GOLD)
/* 188 */         return Items.GOLD_INGOT; 
/* 189 */       if (this == IRON)
/* 190 */         return Items.IRON_INGOT; 
/* 191 */       if (this == DIAMOND) {
/* 192 */         return Items.DIAMOND;
/*     */       }
/* 194 */       return null;
/*     */     }
/*     */   }
/*     */   
/* 198 */   protected static Random j = new Random();
/*     */ 
/*     */   
/* 201 */   protected int maxStackSize = 64;
/*     */   
/*     */   private int durability;
/*     */   
/*     */   protected boolean l;
/*     */   
/*     */   protected boolean m;
/*     */   
/*     */   private Item craftingResult;
/*     */   private String name;
/*     */   
/*     */   public Item() {
/* 213 */     a(new MinecraftKey("lefthanded"), d);
/* 214 */     a(new MinecraftKey("cooldown"), e);
/*     */   }
/*     */   
/*     */   public Item d(int paramInt) {
/* 218 */     this.maxStackSize = paramInt;
/* 219 */     return this;
/*     */   }
/*     */   
/*     */   public EnumInteractionResult a(EntityHuman paramEntityHuman, World paramWorld, BlockPosition paramBlockPosition, EnumHand paramEnumHand, EnumDirection paramEnumDirection, float paramFloat1, float paramFloat2, float paramFloat3) {
/* 223 */     return EnumInteractionResult.PASS;
/*     */   }
/*     */   
/*     */   public float getDestroySpeed(ItemStack paramItemStack, IBlockData paramIBlockData) {
/* 227 */     return 1.0F;
/*     */   }
/*     */   
/*     */   public InteractionResultWrapper<ItemStack> a(World paramWorld, EntityHuman paramEntityHuman, EnumHand paramEnumHand) {
/* 231 */     return new InteractionResultWrapper<>(EnumInteractionResult.PASS, paramEntityHuman.b(paramEnumHand));
/*     */   }
/*     */   
/*     */   public ItemStack a(ItemStack paramItemStack, World paramWorld, EntityLiving paramEntityLiving) {
/* 235 */     return paramItemStack;
/*     */   }
/*     */   
/*     */   public int getMaxStackSize() {
/* 239 */     return this.maxStackSize;
/*     */   }
/*     */   
/*     */   public int filterData(int paramInt) {
/* 243 */     return 0;
/*     */   }
/*     */   
/*     */   public boolean k() {
/* 247 */     return this.m;
/*     */   }
/*     */   
/*     */   protected Item a(boolean paramBoolean) {
/* 251 */     this.m = paramBoolean;
/* 252 */     return this;
/*     */   }
/*     */   
/*     */   public int getMaxDurability() {
/* 256 */     return this.durability;
/*     */   }
/*     */   
/*     */   protected Item setMaxDurability(int paramInt) {
/* 260 */     this.durability = paramInt;
/* 261 */     if (paramInt > 0) {
/* 262 */       a(new MinecraftKey("damaged"), b);
/* 263 */       a(new MinecraftKey("damage"), c);
/*     */     } 
/* 265 */     return this;
/*     */   }
/*     */   
/*     */   public boolean usesDurability() {
/* 269 */     return (this.durability > 0 && (!this.m || this.maxStackSize == 1));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean a(ItemStack paramItemStack, EntityLiving paramEntityLiving1, EntityLiving paramEntityLiving2) {
/* 276 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean a(ItemStack paramItemStack, World paramWorld, IBlockData paramIBlockData, BlockPosition paramBlockPosition, EntityLiving paramEntityLiving) {
/* 283 */     return false;
/*     */   }
/*     */   
/*     */   public boolean canDestroySpecialBlock(IBlockData paramIBlockData) {
/* 287 */     return false;
/*     */   }
/*     */   
/*     */   public boolean a(ItemStack paramItemStack, EntityHuman paramEntityHuman, EntityLiving paramEntityLiving, EnumHand paramEnumHand) {
/* 291 */     return false;
/*     */   }
/*     */   
/*     */   public Item n() {
/* 295 */     this.l = true;
/* 296 */     return this;
/*     */   }
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
/*     */   public Item c(String paramString) {
/* 309 */     this.name = paramString;
/* 310 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String j(ItemStack paramItemStack) {
/* 318 */     return LocaleI18n.get(a(paramItemStack));
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/* 323 */     return "item." + this.name;
/*     */   }
/*     */   
/*     */   public String a(ItemStack paramItemStack) {
/* 327 */     return "item." + this.name;
/*     */   }
/*     */   
/*     */   public Item b(Item paramItem) {
/* 331 */     this.craftingResult = paramItem;
/* 332 */     return this;
/*     */   }
/*     */   
/*     */   public boolean p() {
/* 336 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Item q() {
/* 342 */     return this.craftingResult;
/*     */   }
/*     */   
/*     */   public boolean r() {
/* 346 */     return (this.craftingResult != null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(ItemStack paramItemStack, World paramWorld, Entity paramEntity, int paramInt, boolean paramBoolean) {}
/*     */ 
/*     */   
/*     */   public void b(ItemStack paramItemStack, World paramWorld, EntityHuman paramEntityHuman) {}
/*     */   
/*     */   public boolean f() {
/* 356 */     return false;
/*     */   }
/*     */   
/*     */   public EnumAnimation f(ItemStack paramItemStack) {
/* 360 */     return EnumAnimation.NONE;
/*     */   }
/*     */   
/*     */   public int e(ItemStack paramItemStack) {
/* 364 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(ItemStack paramItemStack, World paramWorld, EntityLiving paramEntityLiving, int paramInt) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public String b(ItemStack paramItemStack) {
/* 374 */     return LocaleI18n.get(j(paramItemStack) + ".name").trim();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumItemRarity g(ItemStack paramItemStack) {
/* 382 */     if (paramItemStack.hasEnchantments()) {
/* 383 */       return EnumItemRarity.RARE;
/*     */     }
/* 385 */     return EnumItemRarity.COMMON;
/*     */   }
/*     */   
/*     */   public boolean g_(ItemStack paramItemStack) {
/* 389 */     return (getMaxStackSize() == 1 && usesDurability());
/*     */   }
/*     */ 
/*     */   
/*     */   protected MovingObjectPosition a(World paramWorld, EntityHuman paramEntityHuman, boolean paramBoolean) {
/* 394 */     float f1 = paramEntityHuman.pitch;
/* 395 */     float f2 = paramEntityHuman.yaw;
/*     */     
/* 397 */     double d1 = paramEntityHuman.locX;
/* 398 */     double d2 = paramEntityHuman.locY + paramEntityHuman.getHeadHeight();
/* 399 */     double d3 = paramEntityHuman.locZ;
/*     */     
/* 401 */     Vec3D vec3D1 = new Vec3D(d1, d2, d3);
/*     */     
/* 403 */     float f3 = MathHelper.cos(-f2 * 0.017453292F - 3.1415927F);
/* 404 */     float f4 = MathHelper.sin(-f2 * 0.017453292F - 3.1415927F);
/* 405 */     float f5 = -MathHelper.cos(-f1 * 0.017453292F);
/* 406 */     float f6 = MathHelper.sin(-f1 * 0.017453292F);
/*     */     
/* 408 */     float f7 = f4 * f5;
/* 409 */     float f8 = f6;
/* 410 */     float f9 = f3 * f5;
/*     */     
/* 412 */     double d4 = 5.0D;
/* 413 */     Vec3D vec3D2 = vec3D1.add(f7 * 5.0D, f8 * 5.0D, f9 * 5.0D);
/*     */     
/* 415 */     return paramWorld.rayTrace(vec3D1, vec3D2, paramBoolean, !paramBoolean, false);
/*     */   }
/*     */   
/*     */   public int c() {
/* 419 */     return 0;
/*     */   }
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Item b(CreativeModeTab paramCreativeModeTab) {
/* 439 */     this.n = paramCreativeModeTab;
/* 440 */     return this;
/*     */   }
/*     */   
/*     */   public boolean s() {
/* 444 */     return false;
/*     */   }
/*     */   
/*     */   public boolean a(ItemStack paramItemStack1, ItemStack paramItemStack2) {
/* 448 */     return false;
/*     */   }
/*     */   
/*     */   public Multimap<String, AttributeModifier> a(EnumItemSlot paramEnumItemSlot) {
/* 452 */     return (Multimap<String, AttributeModifier>)HashMultimap.create();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void t() {
/* 458 */     a(Blocks.AIR, new ItemAir(Blocks.AIR));
/* 459 */     a(Blocks.STONE, (new ItemMultiTexture(Blocks.STONE, Blocks.STONE, new ItemMultiTexture.a()
/*     */           {
/*     */             public String a(ItemStack param1ItemStack) {
/* 462 */               return BlockStone.EnumStoneVariant.a(param1ItemStack.getData()).d();
/*     */             }
/* 464 */           })).c("stone"));
/* 465 */     a(Blocks.GRASS, new ItemWithAuxData(Blocks.GRASS, false));
/* 466 */     a(Blocks.DIRT, (new ItemMultiTexture(Blocks.DIRT, Blocks.DIRT, new ItemMultiTexture.a()
/*     */           {
/*     */             public String a(ItemStack param1ItemStack) {
/* 469 */               return BlockDirt.EnumDirtVariant.a(param1ItemStack.getData()).c();
/*     */             }
/* 471 */           })).c("dirt"));
/* 472 */     b(Blocks.COBBLESTONE);
/* 473 */     a(Blocks.PLANKS, (new ItemMultiTexture(Blocks.PLANKS, Blocks.PLANKS, new ItemMultiTexture.a()
/*     */           {
/*     */             public String a(ItemStack param1ItemStack) {
/* 476 */               return BlockWood.EnumLogVariant.a(param1ItemStack.getData()).d();
/*     */             }
/* 478 */           })).c("wood"));
/* 479 */     a(Blocks.SAPLING, (new ItemMultiTexture(Blocks.SAPLING, Blocks.SAPLING, new ItemMultiTexture.a()
/*     */           {
/*     */             public String a(ItemStack param1ItemStack) {
/* 482 */               return BlockWood.EnumLogVariant.a(param1ItemStack.getData()).d();
/*     */             }
/* 484 */           })).c("sapling"));
/* 485 */     b(Blocks.BEDROCK);
/* 486 */     a(Blocks.SAND, (new ItemMultiTexture(Blocks.SAND, Blocks.SAND, new ItemMultiTexture.a()
/*     */           {
/*     */             public String a(ItemStack param1ItemStack) {
/* 489 */               return BlockSand.EnumSandVariant.a(param1ItemStack.getData()).e();
/*     */             }
/* 491 */           })).c("sand"));
/* 492 */     b(Blocks.GRAVEL);
/* 493 */     b(Blocks.GOLD_ORE);
/* 494 */     b(Blocks.IRON_ORE);
/* 495 */     b(Blocks.COAL_ORE);
/* 496 */     a(Blocks.LOG, (new ItemMultiTexture(Blocks.LOG, Blocks.LOG, new ItemMultiTexture.a()
/*     */           {
/*     */             public String a(ItemStack param1ItemStack) {
/* 499 */               return BlockWood.EnumLogVariant.a(param1ItemStack.getData()).d();
/*     */             }
/* 501 */           })).c("log"));
/* 502 */     a(Blocks.LOG2, (new ItemMultiTexture(Blocks.LOG2, Blocks.LOG2, new ItemMultiTexture.a()
/*     */           {
/*     */             public String a(ItemStack param1ItemStack) {
/* 505 */               return BlockWood.EnumLogVariant.a(param1ItemStack.getData() + 4).d();
/*     */             }
/* 507 */           })).c("log"));
/* 508 */     a(Blocks.LEAVES, (new ItemLeaves(Blocks.LEAVES)).c("leaves"));
/* 509 */     a(Blocks.LEAVES2, (new ItemLeaves(Blocks.LEAVES2)).c("leaves"));
/* 510 */     a(Blocks.SPONGE, (new ItemMultiTexture(Blocks.SPONGE, Blocks.SPONGE, new ItemMultiTexture.a()
/*     */           {
/*     */             public String a(ItemStack param1ItemStack) {
/* 513 */               return ((param1ItemStack.getData() & 0x1) == 1) ? "wet" : "dry";
/*     */             }
/* 515 */           })).c("sponge"));
/* 516 */     b(Blocks.GLASS);
/* 517 */     b(Blocks.LAPIS_ORE);
/* 518 */     b(Blocks.LAPIS_BLOCK);
/* 519 */     b(Blocks.DISPENSER);
/* 520 */     a(Blocks.SANDSTONE, (new ItemMultiTexture(Blocks.SANDSTONE, Blocks.SANDSTONE, new ItemMultiTexture.a()
/*     */           {
/*     */             public String a(ItemStack param1ItemStack) {
/* 523 */               return BlockSandStone.EnumSandstoneVariant.a(param1ItemStack.getData()).c();
/*     */             }
/* 525 */           })).c("sandStone"));
/* 526 */     b(Blocks.NOTEBLOCK);
/* 527 */     b(Blocks.GOLDEN_RAIL);
/* 528 */     b(Blocks.DETECTOR_RAIL);
/* 529 */     a(Blocks.STICKY_PISTON, new ItemPiston(Blocks.STICKY_PISTON));
/* 530 */     b(Blocks.WEB);
/* 531 */     a(Blocks.TALLGRASS, (new ItemWithAuxData(Blocks.TALLGRASS, true)).a(new String[] { "shrub", "grass", "fern" }));
/* 532 */     b(Blocks.DEADBUSH);
/* 533 */     a(Blocks.PISTON, new ItemPiston(Blocks.PISTON));
/* 534 */     a(Blocks.WOOL, (new ItemCloth(Blocks.WOOL)).c("cloth"));
/* 535 */     a(Blocks.YELLOW_FLOWER, (new ItemMultiTexture(Blocks.YELLOW_FLOWER, Blocks.YELLOW_FLOWER, new ItemMultiTexture.a()
/*     */           {
/*     */             public String a(ItemStack param1ItemStack) {
/* 538 */               return BlockFlowers.EnumFlowerVarient.a(BlockFlowers.EnumFlowerType.YELLOW, param1ItemStack.getData()).d();
/*     */             }
/* 540 */           })).c("flower"));
/* 541 */     a(Blocks.RED_FLOWER, (new ItemMultiTexture(Blocks.RED_FLOWER, Blocks.RED_FLOWER, new ItemMultiTexture.a()
/*     */           {
/*     */             public String a(ItemStack param1ItemStack) {
/* 544 */               return BlockFlowers.EnumFlowerVarient.a(BlockFlowers.EnumFlowerType.RED, param1ItemStack.getData()).d();
/*     */             }
/* 546 */           })).c("rose"));
/* 547 */     b(Blocks.BROWN_MUSHROOM);
/* 548 */     b(Blocks.RED_MUSHROOM);
/* 549 */     b(Blocks.GOLD_BLOCK);
/* 550 */     b(Blocks.IRON_BLOCK);
/* 551 */     a(Blocks.STONE_SLAB, (new ItemStep(Blocks.STONE_SLAB, Blocks.STONE_SLAB, Blocks.DOUBLE_STONE_SLAB)).c("stoneSlab"));
/* 552 */     b(Blocks.BRICK_BLOCK);
/* 553 */     b(Blocks.TNT);
/* 554 */     b(Blocks.BOOKSHELF);
/* 555 */     b(Blocks.MOSSY_COBBLESTONE);
/* 556 */     b(Blocks.OBSIDIAN);
/* 557 */     b(Blocks.TORCH);
/* 558 */     b(Blocks.END_ROD);
/* 559 */     b(Blocks.CHORUS_PLANT);
/* 560 */     b(Blocks.CHORUS_FLOWER);
/* 561 */     b(Blocks.PURPUR_BLOCK);
/* 562 */     b(Blocks.PURPUR_PILLAR);
/* 563 */     b(Blocks.PURPUR_STAIRS);
/* 564 */     a(Blocks.PURPUR_SLAB, (new ItemStep(Blocks.PURPUR_SLAB, Blocks.PURPUR_SLAB, Blocks.PURPUR_DOUBLE_SLAB)).c("purpurSlab"));
/* 565 */     b(Blocks.MOB_SPAWNER);
/* 566 */     b(Blocks.OAK_STAIRS);
/* 567 */     b(Blocks.CHEST);
/* 568 */     b(Blocks.DIAMOND_ORE);
/* 569 */     b(Blocks.DIAMOND_BLOCK);
/* 570 */     b(Blocks.CRAFTING_TABLE);
/* 571 */     b(Blocks.FARMLAND);
/* 572 */     b(Blocks.FURNACE);
/* 573 */     b(Blocks.LADDER);
/* 574 */     b(Blocks.RAIL);
/* 575 */     b(Blocks.STONE_STAIRS);
/* 576 */     b(Blocks.LEVER);
/* 577 */     b(Blocks.STONE_PRESSURE_PLATE);
/* 578 */     b(Blocks.WOODEN_PRESSURE_PLATE);
/* 579 */     b(Blocks.REDSTONE_ORE);
/* 580 */     b(Blocks.REDSTONE_TORCH);
/* 581 */     b(Blocks.STONE_BUTTON);
/* 582 */     a(Blocks.SNOW_LAYER, new ItemSnow(Blocks.SNOW_LAYER));
/* 583 */     b(Blocks.ICE);
/* 584 */     b(Blocks.SNOW);
/* 585 */     b(Blocks.CACTUS);
/* 586 */     b(Blocks.CLAY);
/* 587 */     b(Blocks.JUKEBOX);
/* 588 */     b(Blocks.FENCE);
/* 589 */     b(Blocks.SPRUCE_FENCE);
/* 590 */     b(Blocks.BIRCH_FENCE);
/* 591 */     b(Blocks.JUNGLE_FENCE);
/* 592 */     b(Blocks.DARK_OAK_FENCE);
/* 593 */     b(Blocks.ACACIA_FENCE);
/* 594 */     b(Blocks.PUMPKIN);
/* 595 */     b(Blocks.NETHERRACK);
/* 596 */     b(Blocks.SOUL_SAND);
/* 597 */     b(Blocks.GLOWSTONE);
/* 598 */     b(Blocks.LIT_PUMPKIN);
/* 599 */     b(Blocks.TRAPDOOR);
/* 600 */     a(Blocks.MONSTER_EGG, (new ItemMultiTexture(Blocks.MONSTER_EGG, Blocks.MONSTER_EGG, new ItemMultiTexture.a()
/*     */           {
/*     */             public String a(ItemStack param1ItemStack) {
/* 603 */               return BlockMonsterEggs.EnumMonsterEggVarient.a(param1ItemStack.getData()).c();
/*     */             }
/* 605 */           })).c("monsterStoneEgg"));
/* 606 */     a(Blocks.STONEBRICK, (new ItemMultiTexture(Blocks.STONEBRICK, Blocks.STONEBRICK, new ItemMultiTexture.a()
/*     */           {
/*     */             public String a(ItemStack param1ItemStack) {
/* 609 */               return BlockSmoothBrick.EnumStonebrickType.a(param1ItemStack.getData()).c();
/*     */             }
/* 611 */           })).c("stonebricksmooth"));
/* 612 */     b(Blocks.BROWN_MUSHROOM_BLOCK);
/* 613 */     b(Blocks.RED_MUSHROOM_BLOCK);
/* 614 */     b(Blocks.IRON_BARS);
/* 615 */     b(Blocks.GLASS_PANE);
/* 616 */     b(Blocks.MELON_BLOCK);
/* 617 */     a(Blocks.VINE, new ItemWithAuxData(Blocks.VINE, false));
/* 618 */     b(Blocks.FENCE_GATE);
/* 619 */     b(Blocks.SPRUCE_FENCE_GATE);
/* 620 */     b(Blocks.BIRCH_FENCE_GATE);
/* 621 */     b(Blocks.JUNGLE_FENCE_GATE);
/* 622 */     b(Blocks.DARK_OAK_FENCE_GATE);
/* 623 */     b(Blocks.ACACIA_FENCE_GATE);
/* 624 */     b(Blocks.BRICK_STAIRS);
/* 625 */     b(Blocks.STONE_BRICK_STAIRS);
/* 626 */     b(Blocks.MYCELIUM);
/* 627 */     a(Blocks.WATERLILY, new ItemWaterLily(Blocks.WATERLILY));
/* 628 */     b(Blocks.NETHER_BRICK);
/* 629 */     b(Blocks.NETHER_BRICK_FENCE);
/* 630 */     b(Blocks.NETHER_BRICK_STAIRS);
/* 631 */     b(Blocks.ENCHANTING_TABLE);
/* 632 */     b(Blocks.END_PORTAL_FRAME);
/* 633 */     b(Blocks.END_STONE);
/* 634 */     b(Blocks.END_BRICKS);
/* 635 */     b(Blocks.DRAGON_EGG);
/* 636 */     b(Blocks.REDSTONE_LAMP);
/* 637 */     a(Blocks.WOODEN_SLAB, (new ItemStep(Blocks.WOODEN_SLAB, Blocks.WOODEN_SLAB, Blocks.DOUBLE_WOODEN_SLAB)).c("woodSlab"));
/* 638 */     b(Blocks.SANDSTONE_STAIRS);
/* 639 */     b(Blocks.EMERALD_ORE);
/* 640 */     b(Blocks.ENDER_CHEST);
/* 641 */     b(Blocks.TRIPWIRE_HOOK);
/* 642 */     b(Blocks.EMERALD_BLOCK);
/* 643 */     b(Blocks.SPRUCE_STAIRS);
/* 644 */     b(Blocks.BIRCH_STAIRS);
/* 645 */     b(Blocks.JUNGLE_STAIRS);
/* 646 */     b(Blocks.COMMAND_BLOCK);
/* 647 */     b(Blocks.BEACON);
/* 648 */     a(Blocks.COBBLESTONE_WALL, (new ItemMultiTexture(Blocks.COBBLESTONE_WALL, Blocks.COBBLESTONE_WALL, new ItemMultiTexture.a()
/*     */           {
/*     */             public String a(ItemStack param1ItemStack) {
/* 651 */               return BlockCobbleWall.EnumCobbleVariant.a(param1ItemStack.getData()).c();
/*     */             }
/* 653 */           })).c("cobbleWall"));
/* 654 */     b(Blocks.WOODEN_BUTTON);
/* 655 */     a(Blocks.ANVIL, (new ItemAnvil(Blocks.ANVIL)).c("anvil"));
/* 656 */     b(Blocks.TRAPPED_CHEST);
/* 657 */     b(Blocks.LIGHT_WEIGHTED_PRESSURE_PLATE);
/* 658 */     b(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE);
/* 659 */     b(Blocks.DAYLIGHT_DETECTOR);
/* 660 */     b(Blocks.REDSTONE_BLOCK);
/* 661 */     b(Blocks.QUARTZ_ORE);
/* 662 */     b(Blocks.HOPPER);
/* 663 */     a(Blocks.QUARTZ_BLOCK, (new ItemMultiTexture(Blocks.QUARTZ_BLOCK, Blocks.QUARTZ_BLOCK, new String[] { "default", "chiseled", "lines" })).c("quartzBlock"));
/* 664 */     b(Blocks.QUARTZ_STAIRS);
/* 665 */     b(Blocks.ACTIVATOR_RAIL);
/* 666 */     b(Blocks.DROPPER);
/* 667 */     a(Blocks.STAINED_HARDENED_CLAY, (new ItemCloth(Blocks.STAINED_HARDENED_CLAY)).c("clayHardenedStained"));
/* 668 */     b(Blocks.BARRIER);
/* 669 */     b(Blocks.IRON_TRAPDOOR);
/* 670 */     b(Blocks.HAY_BLOCK);
/* 671 */     a(Blocks.CARPET, (new ItemCloth(Blocks.CARPET)).c("woolCarpet"));
/* 672 */     b(Blocks.HARDENED_CLAY);
/* 673 */     b(Blocks.COAL_BLOCK);
/* 674 */     b(Blocks.PACKED_ICE);
/* 675 */     b(Blocks.ACACIA_STAIRS);
/* 676 */     b(Blocks.DARK_OAK_STAIRS);
/* 677 */     b(Blocks.SLIME);
/* 678 */     b(Blocks.GRASS_PATH);
/* 679 */     a(Blocks.DOUBLE_PLANT, (new ItemMultiTexture(Blocks.DOUBLE_PLANT, Blocks.DOUBLE_PLANT, new ItemMultiTexture.a()
/*     */           {
/*     */             public String a(ItemStack param1ItemStack) {
/* 682 */               return BlockTallPlant.EnumTallFlowerVariants.a(param1ItemStack.getData()).c();
/*     */             }
/* 684 */           })).c("doublePlant"));
/* 685 */     a(Blocks.STAINED_GLASS, (new ItemCloth(Blocks.STAINED_GLASS)).c("stainedGlass"));
/* 686 */     a(Blocks.STAINED_GLASS_PANE, (new ItemCloth(Blocks.STAINED_GLASS_PANE)).c("stainedGlassPane"));
/* 687 */     a(Blocks.PRISMARINE, (new ItemMultiTexture(Blocks.PRISMARINE, Blocks.PRISMARINE, new ItemMultiTexture.a()
/*     */           {
/*     */             public String a(ItemStack param1ItemStack) {
/* 690 */               return BlockPrismarine.EnumPrismarineVariant.a(param1ItemStack.getData()).c();
/*     */             }
/* 692 */           })).c("prismarine"));
/* 693 */     b(Blocks.SEA_LANTERN);
/* 694 */     a(Blocks.RED_SANDSTONE, (new ItemMultiTexture(Blocks.RED_SANDSTONE, Blocks.RED_SANDSTONE, new ItemMultiTexture.a()
/*     */           {
/*     */             public String a(ItemStack param1ItemStack) {
/* 697 */               return BlockRedSandstone.EnumRedSandstoneVariant.a(param1ItemStack.getData()).c();
/*     */             }
/* 699 */           })).c("redSandStone"));
/* 700 */     b(Blocks.RED_SANDSTONE_STAIRS);
/* 701 */     a(Blocks.STONE_SLAB2, (new ItemStep(Blocks.STONE_SLAB2, Blocks.STONE_SLAB2, Blocks.DOUBLE_STONE_SLAB2)).c("stoneSlab2"));
/* 702 */     b(Blocks.dc);
/* 703 */     b(Blocks.dd);
/* 704 */     b(Blocks.df);
/* 705 */     b(Blocks.dg);
/* 706 */     b(Blocks.dh);
/* 707 */     b(Blocks.di);
/* 708 */     b(Blocks.dj);
/* 709 */     b(Blocks.dk);
/* 710 */     a(Blocks.WHITE_SHULKER_BOX, new ItemShulkerBox(Blocks.WHITE_SHULKER_BOX));
/* 711 */     a(Blocks.dm, new ItemShulkerBox(Blocks.dm));
/* 712 */     a(Blocks.dn, new ItemShulkerBox(Blocks.dn));
/* 713 */     a(Blocks.LIGHT_BLUE_SHULKER_BOX, new ItemShulkerBox(Blocks.LIGHT_BLUE_SHULKER_BOX));
/* 714 */     a(Blocks.dp, new ItemShulkerBox(Blocks.dp));
/* 715 */     a(Blocks.dq, new ItemShulkerBox(Blocks.dq));
/* 716 */     a(Blocks.dr, new ItemShulkerBox(Blocks.dr));
/* 717 */     a(Blocks.ds, new ItemShulkerBox(Blocks.ds));
/* 718 */     a(Blocks.dt, new ItemShulkerBox(Blocks.dt));
/* 719 */     a(Blocks.du, new ItemShulkerBox(Blocks.du));
/* 720 */     a(Blocks.dv, new ItemShulkerBox(Blocks.dv));
/* 721 */     a(Blocks.dw, new ItemShulkerBox(Blocks.dw));
/* 722 */     a(Blocks.dx, new ItemShulkerBox(Blocks.dx));
/* 723 */     a(Blocks.dy, new ItemShulkerBox(Blocks.dy));
/* 724 */     a(Blocks.dz, new ItemShulkerBox(Blocks.dz));
/* 725 */     a(Blocks.dA, new ItemShulkerBox(Blocks.dA));
/* 726 */     b(Blocks.dB);
/* 727 */     b(Blocks.dC);
/* 728 */     b(Blocks.dD);
/* 729 */     b(Blocks.dE);
/* 730 */     b(Blocks.dF);
/* 731 */     b(Blocks.dG);
/* 732 */     b(Blocks.dH);
/* 733 */     b(Blocks.dI);
/* 734 */     b(Blocks.dJ);
/* 735 */     b(Blocks.dK);
/* 736 */     b(Blocks.dL);
/* 737 */     b(Blocks.dM);
/* 738 */     b(Blocks.dN);
/* 739 */     b(Blocks.dO);
/* 740 */     b(Blocks.dP);
/* 741 */     b(Blocks.dQ);
/* 742 */     a(Blocks.dR, (new ItemCloth(Blocks.dR)).c("concrete"));
/* 743 */     a(Blocks.dS, (new ItemCloth(Blocks.dS)).c("concrete_powder"));
/*     */     
/* 745 */     b(Blocks.STRUCTURE_BLOCK);
/*     */ 
/*     */     
/* 748 */     a(256, "iron_shovel", (new ItemSpade(EnumToolMaterial.IRON)).c("shovelIron"));
/* 749 */     a(257, "iron_pickaxe", (new ItemPickaxe(EnumToolMaterial.IRON)).c("pickaxeIron"));
/* 750 */     a(258, "iron_axe", (new ItemAxe(EnumToolMaterial.IRON)).c("hatchetIron"));
/* 751 */     a(259, "flint_and_steel", (new ItemFlintAndSteel()).c("flintAndSteel"));
/* 752 */     a(260, "apple", (new ItemFood(4, 0.3F, false)).c("apple"));
/* 753 */     a(261, "bow", (new ItemBow()).c("bow"));
/* 754 */     a(262, "arrow", (new ItemArrow()).c("arrow"));
/* 755 */     a(263, "coal", (new ItemCoal()).c("coal"));
/* 756 */     a(264, "diamond", (new Item()).c("diamond").b(CreativeModeTab.l));
/* 757 */     a(265, "iron_ingot", (new Item()).c("ingotIron").b(CreativeModeTab.l));
/* 758 */     a(266, "gold_ingot", (new Item()).c("ingotGold").b(CreativeModeTab.l));
/* 759 */     a(267, "iron_sword", (new ItemSword(EnumToolMaterial.IRON)).c("swordIron"));
/* 760 */     a(268, "wooden_sword", (new ItemSword(EnumToolMaterial.WOOD)).c("swordWood"));
/* 761 */     a(269, "wooden_shovel", (new ItemSpade(EnumToolMaterial.WOOD)).c("shovelWood"));
/* 762 */     a(270, "wooden_pickaxe", (new ItemPickaxe(EnumToolMaterial.WOOD)).c("pickaxeWood"));
/* 763 */     a(271, "wooden_axe", (new ItemAxe(EnumToolMaterial.WOOD)).c("hatchetWood"));
/* 764 */     a(272, "stone_sword", (new ItemSword(EnumToolMaterial.STONE)).c("swordStone"));
/* 765 */     a(273, "stone_shovel", (new ItemSpade(EnumToolMaterial.STONE)).c("shovelStone"));
/* 766 */     a(274, "stone_pickaxe", (new ItemPickaxe(EnumToolMaterial.STONE)).c("pickaxeStone"));
/* 767 */     a(275, "stone_axe", (new ItemAxe(EnumToolMaterial.STONE)).c("hatchetStone"));
/* 768 */     a(276, "diamond_sword", (new ItemSword(EnumToolMaterial.DIAMOND)).c("swordDiamond"));
/* 769 */     a(277, "diamond_shovel", (new ItemSpade(EnumToolMaterial.DIAMOND)).c("shovelDiamond"));
/* 770 */     a(278, "diamond_pickaxe", (new ItemPickaxe(EnumToolMaterial.DIAMOND)).c("pickaxeDiamond"));
/* 771 */     a(279, "diamond_axe", (new ItemAxe(EnumToolMaterial.DIAMOND)).c("hatchetDiamond"));
/* 772 */     a(280, "stick", (new Item()).n().c("stick").b(CreativeModeTab.l));
/* 773 */     a(281, "bowl", (new Item()).c("bowl").b(CreativeModeTab.l));
/* 774 */     a(282, "mushroom_stew", (new ItemSoup(6)).c("mushroomStew"));
/* 775 */     a(283, "golden_sword", (new ItemSword(EnumToolMaterial.GOLD)).c("swordGold"));
/* 776 */     a(284, "golden_shovel", (new ItemSpade(EnumToolMaterial.GOLD)).c("shovelGold"));
/* 777 */     a(285, "golden_pickaxe", (new ItemPickaxe(EnumToolMaterial.GOLD)).c("pickaxeGold"));
/* 778 */     a(286, "golden_axe", (new ItemAxe(EnumToolMaterial.GOLD)).c("hatchetGold"));
/* 779 */     a(287, "string", (new ItemReed(Blocks.TRIPWIRE)).c("string").b(CreativeModeTab.l));
/* 780 */     a(288, "feather", (new Item()).c("feather").b(CreativeModeTab.l));
/* 781 */     a(289, "gunpowder", (new Item()).c("sulphur").b(CreativeModeTab.l));
/* 782 */     a(290, "wooden_hoe", (new ItemHoe(EnumToolMaterial.WOOD)).c("hoeWood"));
/* 783 */     a(291, "stone_hoe", (new ItemHoe(EnumToolMaterial.STONE)).c("hoeStone"));
/* 784 */     a(292, "iron_hoe", (new ItemHoe(EnumToolMaterial.IRON)).c("hoeIron"));
/* 785 */     a(293, "diamond_hoe", (new ItemHoe(EnumToolMaterial.DIAMOND)).c("hoeDiamond"));
/* 786 */     a(294, "golden_hoe", (new ItemHoe(EnumToolMaterial.GOLD)).c("hoeGold"));
/* 787 */     a(295, "wheat_seeds", (new ItemSeeds(Blocks.WHEAT, Blocks.FARMLAND)).c("seeds"));
/* 788 */     a(296, "wheat", (new Item()).c("wheat").b(CreativeModeTab.l));
/* 789 */     a(297, "bread", (new ItemFood(5, 0.6F, false)).c("bread"));
/* 790 */     a(298, "leather_helmet", (new ItemArmor(ItemArmor.EnumArmorMaterial.LEATHER, 0, EnumItemSlot.HEAD)).c("helmetCloth"));
/* 791 */     a(299, "leather_chestplate", (new ItemArmor(ItemArmor.EnumArmorMaterial.LEATHER, 0, EnumItemSlot.CHEST)).c("chestplateCloth"));
/* 792 */     a(300, "leather_leggings", (new ItemArmor(ItemArmor.EnumArmorMaterial.LEATHER, 0, EnumItemSlot.LEGS)).c("leggingsCloth"));
/* 793 */     a(301, "leather_boots", (new ItemArmor(ItemArmor.EnumArmorMaterial.LEATHER, 0, EnumItemSlot.FEET)).c("bootsCloth"));
/* 794 */     a(302, "chainmail_helmet", (new ItemArmor(ItemArmor.EnumArmorMaterial.CHAIN, 1, EnumItemSlot.HEAD)).c("helmetChain"));
/* 795 */     a(303, "chainmail_chestplate", (new ItemArmor(ItemArmor.EnumArmorMaterial.CHAIN, 1, EnumItemSlot.CHEST)).c("chestplateChain"));
/* 796 */     a(304, "chainmail_leggings", (new ItemArmor(ItemArmor.EnumArmorMaterial.CHAIN, 1, EnumItemSlot.LEGS)).c("leggingsChain"));
/* 797 */     a(305, "chainmail_boots", (new ItemArmor(ItemArmor.EnumArmorMaterial.CHAIN, 1, EnumItemSlot.FEET)).c("bootsChain"));
/* 798 */     a(306, "iron_helmet", (new ItemArmor(ItemArmor.EnumArmorMaterial.IRON, 2, EnumItemSlot.HEAD)).c("helmetIron"));
/* 799 */     a(307, "iron_chestplate", (new ItemArmor(ItemArmor.EnumArmorMaterial.IRON, 2, EnumItemSlot.CHEST)).c("chestplateIron"));
/* 800 */     a(308, "iron_leggings", (new ItemArmor(ItemArmor.EnumArmorMaterial.IRON, 2, EnumItemSlot.LEGS)).c("leggingsIron"));
/* 801 */     a(309, "iron_boots", (new ItemArmor(ItemArmor.EnumArmorMaterial.IRON, 2, EnumItemSlot.FEET)).c("bootsIron"));
/* 802 */     a(310, "diamond_helmet", (new ItemArmor(ItemArmor.EnumArmorMaterial.DIAMOND, 3, EnumItemSlot.HEAD)).c("helmetDiamond"));
/* 803 */     a(311, "diamond_chestplate", (new ItemArmor(ItemArmor.EnumArmorMaterial.DIAMOND, 3, EnumItemSlot.CHEST)).c("chestplateDiamond"));
/* 804 */     a(312, "diamond_leggings", (new ItemArmor(ItemArmor.EnumArmorMaterial.DIAMOND, 3, EnumItemSlot.LEGS)).c("leggingsDiamond"));
/* 805 */     a(313, "diamond_boots", (new ItemArmor(ItemArmor.EnumArmorMaterial.DIAMOND, 3, EnumItemSlot.FEET)).c("bootsDiamond"));
/* 806 */     a(314, "golden_helmet", (new ItemArmor(ItemArmor.EnumArmorMaterial.GOLD, 4, EnumItemSlot.HEAD)).c("helmetGold"));
/* 807 */     a(315, "golden_chestplate", (new ItemArmor(ItemArmor.EnumArmorMaterial.GOLD, 4, EnumItemSlot.CHEST)).c("chestplateGold"));
/* 808 */     a(316, "golden_leggings", (new ItemArmor(ItemArmor.EnumArmorMaterial.GOLD, 4, EnumItemSlot.LEGS)).c("leggingsGold"));
/* 809 */     a(317, "golden_boots", (new ItemArmor(ItemArmor.EnumArmorMaterial.GOLD, 4, EnumItemSlot.FEET)).c("bootsGold"));
/* 810 */     a(318, "flint", (new Item()).c("flint").b(CreativeModeTab.l));
/* 811 */     a(319, "porkchop", (new ItemFood(3, 0.3F, true)).c("porkchopRaw"));
/* 812 */     a(320, "cooked_porkchop", (new ItemFood(8, 0.8F, true)).c("porkchopCooked"));
/* 813 */     a(321, "painting", (new ItemHanging((Class)EntityPainting.class)).c("painting"));
/* 814 */     a(322, "golden_apple", (new ItemGoldenApple(4, 1.2F, false)).h().c("appleGold"));
/* 815 */     a(323, "sign", (new ItemSign()).c("sign"));
/* 816 */     a(324, "wooden_door", (new ItemDoor(Blocks.WOODEN_DOOR)).c("doorOak"));
/* 817 */     Item item1 = (new ItemBucket(Blocks.AIR)).c("bucket").d(16);
/* 818 */     a(325, "bucket", item1);
/* 819 */     a(326, "water_bucket", (new ItemBucket(Blocks.FLOWING_WATER)).c("bucketWater").b(item1));
/* 820 */     a(327, "lava_bucket", (new ItemBucket(Blocks.FLOWING_LAVA)).c("bucketLava").b(item1));
/* 821 */     a(328, "minecart", (new ItemMinecart(EntityMinecartAbstract.EnumMinecartType.RIDEABLE)).c("minecart"));
/* 822 */     a(329, "saddle", (new ItemSaddle()).c("saddle"));
/* 823 */     a(330, "iron_door", (new ItemDoor(Blocks.IRON_DOOR)).c("doorIron"));
/* 824 */     a(331, "redstone", (new ItemRedstone()).c("redstone"));
/* 825 */     a(332, "snowball", (new ItemSnowball()).c("snowball"));
/* 826 */     a(333, "boat", new ItemBoat(EntityBoat.EnumBoatType.OAK));
/* 827 */     a(334, "leather", (new Item()).c("leather").b(CreativeModeTab.l));
/* 828 */     a(335, "milk_bucket", (new ItemMilkBucket()).c("milk").b(item1));
/* 829 */     a(336, "brick", (new Item()).c("brick").b(CreativeModeTab.l));
/* 830 */     a(337, "clay_ball", (new Item()).c("clay").b(CreativeModeTab.l));
/* 831 */     a(338, "reeds", (new ItemReed(Blocks.REEDS)).c("reeds").b(CreativeModeTab.l));
/* 832 */     a(339, "paper", (new Item()).c("paper").b(CreativeModeTab.f));
/* 833 */     a(340, "book", (new ItemBook()).c("book").b(CreativeModeTab.f));
/* 834 */     a(341, "slime_ball", (new Item()).c("slimeball").b(CreativeModeTab.f));
/* 835 */     a(342, "chest_minecart", (new ItemMinecart(EntityMinecartAbstract.EnumMinecartType.CHEST)).c("minecartChest"));
/* 836 */     a(343, "furnace_minecart", (new ItemMinecart(EntityMinecartAbstract.EnumMinecartType.FURNACE)).c("minecartFurnace"));
/* 837 */     a(344, "egg", (new ItemEgg()).c("egg"));
/* 838 */     a(345, "compass", (new ItemCompass()).c("compass").b(CreativeModeTab.i));
/* 839 */     a(346, "fishing_rod", (new ItemFishingRod()).c("fishingRod"));
/* 840 */     a(347, "clock", (new ItemClock()).c("clock").b(CreativeModeTab.i));
/* 841 */     a(348, "glowstone_dust", (new Item()).c("yellowDust").b(CreativeModeTab.l));
/* 842 */     a(349, "fish", (new ItemFish(false)).c("fish").a(true));
/* 843 */     a(350, "cooked_fish", (new ItemFish(true)).c("fish").a(true));
/* 844 */     a(351, "dye", (new ItemDye()).c("dyePowder"));
/* 845 */     a(352, "bone", (new Item()).c("bone").n().b(CreativeModeTab.f));
/* 846 */     a(353, "sugar", (new Item()).c("sugar").b(CreativeModeTab.l));
/* 847 */     a(354, "cake", (new ItemReed(Blocks.CAKE)).d(1).c("cake").b(CreativeModeTab.h));
/* 848 */     a(355, "bed", (new ItemBed()).d(1).c("bed"));
/* 849 */     a(356, "repeater", (new ItemReed(Blocks.UNPOWERED_REPEATER)).c("diode").b(CreativeModeTab.d));
/* 850 */     a(357, "cookie", (new ItemFood(2, 0.1F, false)).c("cookie"));
/* 851 */     a(358, "filled_map", (new ItemWorldMap()).c("map"));
/* 852 */     a(359, "shears", (new ItemShears()).c("shears"));
/* 853 */     a(360, "melon", (new ItemFood(2, 0.3F, false)).c("melon"));
/* 854 */     a(361, "pumpkin_seeds", (new ItemSeeds(Blocks.PUMPKIN_STEM, Blocks.FARMLAND)).c("seeds_pumpkin"));
/* 855 */     a(362, "melon_seeds", (new ItemSeeds(Blocks.MELON_STEM, Blocks.FARMLAND)).c("seeds_melon"));
/* 856 */     a(363, "beef", (new ItemFood(3, 0.3F, true)).c("beefRaw"));
/* 857 */     a(364, "cooked_beef", (new ItemFood(8, 0.8F, true)).c("beefCooked"));
/* 858 */     a(365, "chicken", (new ItemFood(2, 0.3F, true)).a(new MobEffect(MobEffects.HUNGER, 600, 0), 0.3F).c("chickenRaw"));
/* 859 */     a(366, "cooked_chicken", (new ItemFood(6, 0.6F, true)).c("chickenCooked"));
/* 860 */     a(367, "rotten_flesh", (new ItemFood(4, 0.1F, true)).a(new MobEffect(MobEffects.HUNGER, 600, 0), 0.8F).c("rottenFlesh"));
/* 861 */     a(368, "ender_pearl", (new ItemEnderPearl()).c("enderPearl"));
/* 862 */     a(369, "blaze_rod", (new Item()).c("blazeRod").b(CreativeModeTab.l).n());
/* 863 */     a(370, "ghast_tear", (new Item()).c("ghastTear").b(CreativeModeTab.k));
/* 864 */     a(371, "gold_nugget", (new Item()).c("goldNugget").b(CreativeModeTab.l));
/* 865 */     a(372, "nether_wart", (new ItemSeeds(Blocks.NETHER_WART, Blocks.SOUL_SAND)).c("netherStalkSeeds"));
/* 866 */     a(373, "potion", (new ItemPotion()).c("potion"));
/* 867 */     Item item2 = (new ItemGlassBottle()).c("glassBottle");
/* 868 */     a(374, "glass_bottle", item2);
/* 869 */     a(375, "spider_eye", (new ItemFood(2, 0.8F, false)).a(new MobEffect(MobEffects.POISON, 100, 0), 1.0F).c("spiderEye"));
/* 870 */     a(376, "fermented_spider_eye", (new Item()).c("fermentedSpiderEye").b(CreativeModeTab.k));
/* 871 */     a(377, "blaze_powder", (new Item()).c("blazePowder").b(CreativeModeTab.k));
/* 872 */     a(378, "magma_cream", (new Item()).c("magmaCream").b(CreativeModeTab.k));
/* 873 */     a(379, "brewing_stand", (new ItemReed(Blocks.BREWING_STAND)).c("brewingStand").b(CreativeModeTab.k));
/* 874 */     a(380, "cauldron", (new ItemReed(Blocks.cauldron)).c("cauldron").b(CreativeModeTab.k));
/* 875 */     a(381, "ender_eye", (new ItemEnderEye()).c("eyeOfEnder"));
/* 876 */     a(382, "speckled_melon", (new Item()).c("speckledMelon").b(CreativeModeTab.k));
/* 877 */     a(383, "spawn_egg", (new ItemMonsterEgg()).c("monsterPlacer"));
/* 878 */     a(384, "experience_bottle", (new ItemExpBottle()).c("expBottle"));
/* 879 */     a(385, "fire_charge", (new ItemFireball()).c("fireball"));
/* 880 */     a(386, "writable_book", (new ItemBookAndQuill()).c("writingBook").b(CreativeModeTab.f));
/* 881 */     a(387, "written_book", (new ItemWrittenBook()).c("writtenBook").d(16));
/* 882 */     a(388, "emerald", (new Item()).c("emerald").b(CreativeModeTab.l));
/* 883 */     a(389, "item_frame", (new ItemHanging((Class)EntityItemFrame.class)).c("frame"));
/* 884 */     a(390, "flower_pot", (new ItemReed(Blocks.FLOWER_POT)).c("flowerPot").b(CreativeModeTab.c));
/* 885 */     a(391, "carrot", (new ItemSeedFood(3, 0.6F, Blocks.CARROTS, Blocks.FARMLAND)).c("carrots"));
/* 886 */     a(392, "potato", (new ItemSeedFood(1, 0.3F, Blocks.POTATOES, Blocks.FARMLAND)).c("potato"));
/* 887 */     a(393, "baked_potato", (new ItemFood(5, 0.6F, false)).c("potatoBaked"));
/* 888 */     a(394, "poisonous_potato", (new ItemFood(2, 0.3F, false)).a(new MobEffect(MobEffects.POISON, 100, 0), 0.6F).c("potatoPoisonous"));
/* 889 */     a(395, "map", (new ItemMapEmpty()).c("emptyMap"));
/* 890 */     a(396, "golden_carrot", (new ItemFood(6, 1.2F, false)).c("carrotGolden").b(CreativeModeTab.k));
/* 891 */     a(397, "skull", (new ItemSkull()).c("skull"));
/* 892 */     a(398, "carrot_on_a_stick", (new ItemCarrotStick()).c("carrotOnAStick"));
/* 893 */     a(399, "nether_star", (new ItemNetherStar()).c("netherStar").b(CreativeModeTab.l));
/* 894 */     a(400, "pumpkin_pie", (new ItemFood(8, 0.3F, false)).c("pumpkinPie").b(CreativeModeTab.h));
/* 895 */     a(401, "fireworks", (new ItemFireworks()).c("fireworks"));
/* 896 */     a(402, "firework_charge", (new ItemFireworksCharge()).c("fireworksCharge").b(CreativeModeTab.f));
/* 897 */     a(403, "enchanted_book", (new ItemEnchantedBook()).d(1).c("enchantedBook"));
/* 898 */     a(404, "comparator", (new ItemReed(Blocks.UNPOWERED_COMPARATOR)).c("comparator").b(CreativeModeTab.d));
/* 899 */     a(405, "netherbrick", (new Item()).c("netherbrick").b(CreativeModeTab.l));
/* 900 */     a(406, "quartz", (new Item()).c("netherquartz").b(CreativeModeTab.l));
/* 901 */     a(407, "tnt_minecart", (new ItemMinecart(EntityMinecartAbstract.EnumMinecartType.TNT)).c("minecartTnt"));
/* 902 */     a(408, "hopper_minecart", (new ItemMinecart(EntityMinecartAbstract.EnumMinecartType.HOPPER)).c("minecartHopper"));
/* 903 */     a(409, "prismarine_shard", (new Item()).c("prismarineShard").b(CreativeModeTab.l));
/* 904 */     a(410, "prismarine_crystals", (new Item()).c("prismarineCrystals").b(CreativeModeTab.l));
/* 905 */     a(411, "rabbit", (new ItemFood(3, 0.3F, true)).c("rabbitRaw"));
/* 906 */     a(412, "cooked_rabbit", (new ItemFood(5, 0.6F, true)).c("rabbitCooked"));
/* 907 */     a(413, "rabbit_stew", (new ItemSoup(10)).c("rabbitStew"));
/* 908 */     a(414, "rabbit_foot", (new Item()).c("rabbitFoot").b(CreativeModeTab.k));
/* 909 */     a(415, "rabbit_hide", (new Item()).c("rabbitHide").b(CreativeModeTab.l));
/* 910 */     a(416, "armor_stand", (new ItemArmorStand()).c("armorStand").d(16));
/* 911 */     a(417, "iron_horse_armor", (new Item()).c("horsearmormetal").d(1).b(CreativeModeTab.f));
/* 912 */     a(418, "golden_horse_armor", (new Item()).c("horsearmorgold").d(1).b(CreativeModeTab.f));
/* 913 */     a(419, "diamond_horse_armor", (new Item()).c("horsearmordiamond").d(1).b(CreativeModeTab.f));
/* 914 */     a(420, "lead", (new ItemLeash()).c("leash"));
/* 915 */     a(421, "name_tag", (new ItemNameTag()).c("nameTag"));
/* 916 */     a(422, "command_block_minecart", (new ItemMinecart(EntityMinecartAbstract.EnumMinecartType.COMMAND_BLOCK)).c("minecartCommandBlock").b((CreativeModeTab)null));
/* 917 */     a(423, "mutton", (new ItemFood(2, 0.3F, true)).c("muttonRaw"));
/* 918 */     a(424, "cooked_mutton", (new ItemFood(6, 0.8F, true)).c("muttonCooked"));
/* 919 */     a(425, "banner", (new ItemBanner()).c("banner"));
/* 920 */     a(426, "end_crystal", new ItemEndCrystal());
/* 921 */     a(427, "spruce_door", (new ItemDoor(Blocks.SPRUCE_DOOR)).c("doorSpruce"));
/* 922 */     a(428, "birch_door", (new ItemDoor(Blocks.BIRCH_DOOR)).c("doorBirch"));
/* 923 */     a(429, "jungle_door", (new ItemDoor(Blocks.JUNGLE_DOOR)).c("doorJungle"));
/* 924 */     a(430, "acacia_door", (new ItemDoor(Blocks.ACACIA_DOOR)).c("doorAcacia"));
/* 925 */     a(431, "dark_oak_door", (new ItemDoor(Blocks.DARK_OAK_DOOR)).c("doorDarkOak"));
/* 926 */     a(432, "chorus_fruit", (new ItemChorusFruit(4, 0.3F)).h().c("chorusFruit").b(CreativeModeTab.l));
/* 927 */     a(433, "chorus_fruit_popped", (new Item()).c("chorusFruitPopped").b(CreativeModeTab.l));
/* 928 */     a(434, "beetroot", (new ItemFood(1, 0.6F, false)).c("beetroot"));
/* 929 */     a(435, "beetroot_seeds", (new ItemSeeds(Blocks.BEETROOT, Blocks.FARMLAND)).c("beetroot_seeds"));
/* 930 */     a(436, "beetroot_soup", (new ItemSoup(6)).c("beetroot_soup"));
/* 931 */     a(437, "dragon_breath", (new Item()).b(CreativeModeTab.k).c("dragon_breath").b(item2));
/* 932 */     a(438, "splash_potion", (new ItemSplashPotion()).c("splash_potion"));
/* 933 */     a(439, "spectral_arrow", (new ItemSpectralArrow()).c("spectral_arrow"));
/* 934 */     a(440, "tipped_arrow", (new ItemTippedArrow()).c("tipped_arrow"));
/* 935 */     a(441, "lingering_potion", (new ItemLingeringPotion()).c("lingering_potion"));
/* 936 */     a(442, "shield", (new ItemShield()).c("shield"));
/* 937 */     a(443, "elytra", (new ItemElytra()).c("elytra"));
/* 938 */     a(444, "spruce_boat", new ItemBoat(EntityBoat.EnumBoatType.SPRUCE));
/* 939 */     a(445, "birch_boat", new ItemBoat(EntityBoat.EnumBoatType.BIRCH));
/* 940 */     a(446, "jungle_boat", new ItemBoat(EntityBoat.EnumBoatType.JUNGLE));
/* 941 */     a(447, "acacia_boat", new ItemBoat(EntityBoat.EnumBoatType.ACACIA));
/* 942 */     a(448, "dark_oak_boat", new ItemBoat(EntityBoat.EnumBoatType.DARK_OAK));
/* 943 */     a(449, "totem_of_undying", (new Item()).c("totem").d(1).b(CreativeModeTab.j));
/* 944 */     a(450, "shulker_shell", (new Item()).c("shulkerShell").b(CreativeModeTab.l));
/*     */     
/* 946 */     a(452, "iron_nugget", (new Item()).c("ironNugget").b(CreativeModeTab.l));
/* 947 */     a(453, "knowledge_book", (new ItemKnowledgeBook()).c("knowledgeBook"));
/*     */ 
/*     */     
/* 950 */     a(2256, "record_13", (new ItemRecord("13", SoundEffects.gb)).c("record"));
/* 951 */     a(2257, "record_cat", (new ItemRecord("cat", SoundEffects.gd)).c("record"));
/* 952 */     a(2258, "record_blocks", (new ItemRecord("blocks", SoundEffects.gc)).c("record"));
/* 953 */     a(2259, "record_chirp", (new ItemRecord("chirp", SoundEffects.ge)).c("record"));
/* 954 */     a(2260, "record_far", (new ItemRecord("far", SoundEffects.gf)).c("record"));
/* 955 */     a(2261, "record_mall", (new ItemRecord("mall", SoundEffects.gg)).c("record"));
/* 956 */     a(2262, "record_mellohi", (new ItemRecord("mellohi", SoundEffects.gh)).c("record"));
/* 957 */     a(2263, "record_stal", (new ItemRecord("stal", SoundEffects.gi)).c("record"));
/* 958 */     a(2264, "record_strad", (new ItemRecord("strad", SoundEffects.gj)).c("record"));
/* 959 */     a(2265, "record_ward", (new ItemRecord("ward", SoundEffects.gl)).c("record"));
/* 960 */     a(2266, "record_11", (new ItemRecord("11", SoundEffects.ga)).c("record"));
/* 961 */     a(2267, "record_wait", (new ItemRecord("wait", SoundEffects.gk)).c("record"));
/*     */   }
/*     */   
/*     */   private static void b(Block paramBlock) {
/* 965 */     a(paramBlock, new ItemBlock(paramBlock));
/*     */   }
/*     */   
/*     */   protected static void a(Block paramBlock, Item paramItem) {
/* 969 */     a(Block.getId(paramBlock), Block.REGISTRY.b(paramBlock), paramItem);
/* 970 */     a.put(paramBlock, paramItem);
/*     */   }
/*     */   
/*     */   private static void a(int paramInt, String paramString, Item paramItem) {
/* 974 */     a(paramInt, new MinecraftKey(paramString), paramItem);
/*     */   }
/*     */   
/*     */   private static void a(int paramInt, MinecraftKey paramMinecraftKey, Item paramItem) {
/* 978 */     REGISTRY.a(paramInt, paramMinecraftKey, paramItem);
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\Item.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */