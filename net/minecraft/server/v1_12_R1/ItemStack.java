/*     */ package net.minecraft.server.v1_12_R1;
/*     */ import com.google.common.collect.Multimap;
/*     */ import java.text.DecimalFormat;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.TreeType;
/*     */ import org.bukkit.block.BlockState;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.block.CraftBlockState;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.util.CraftMagicNumbers;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.block.BlockPlaceEvent;
/*     */ import org.bukkit.event.player.PlayerItemDamageEvent;
/*     */ import org.bukkit.event.world.StructureGrowEvent;
/*     */ 
/*     */ public final class ItemStack {
/*  24 */   public static final ItemStack a = new ItemStack(null);
/*  25 */   public static final DecimalFormat b = new DecimalFormat("#.##");
/*     */   private int count;
/*     */   private int d;
/*     */   private Item item;
/*     */   private NBTTagCompound tag;
/*     */   private boolean g;
/*     */   private int damage;
/*     */   private EntityItemFrame i;
/*     */   private Block j;
/*     */   private boolean k;
/*     */   private Block l;
/*     */   private boolean m;
/*     */   
/*     */   public ItemStack(Block block) {
/*  39 */     this(block, 1);
/*     */   }
/*     */   
/*     */   public ItemStack(Block block, int i) {
/*  43 */     this(block, i, 0);
/*     */   }
/*     */   
/*     */   public ItemStack(Block block, int i, int j) {
/*  47 */     this(Item.getItemOf(block), i, j);
/*     */   }
/*     */   
/*     */   public ItemStack(Item item) {
/*  51 */     this(item, 1);
/*     */   }
/*     */   
/*     */   public ItemStack(Item item, int i) {
/*  55 */     this(item, i, 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack(Item item, int i, int j) {
/*  60 */     this(item, i, j, true);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack(Item item, int i, int j, boolean convert) {
/*  65 */     this.item = item;
/*  66 */     this.damage = j;
/*  67 */     this.count = i;
/*     */     
/*  69 */     if (MinecraftServer.getServer() != null) {
/*  70 */       setData(j);
/*     */     }
/*  72 */     if (convert) {
/*  73 */       convertStack();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  80 */     F();
/*     */   }
/*     */ 
/*     */   
/*     */   public void convertStack() {
/*  85 */     if (MinecraftServer.getServer() != null) {
/*     */ 
/*     */       
/*  88 */       if (this.item == Items.BED) {
/*     */         return;
/*     */       }
/*     */       
/*  92 */       NBTTagCompound savedStack = new NBTTagCompound();
/*  93 */       save(savedStack);
/*  94 */       (MinecraftServer.getServer()).dataConverterManager.a(DataConverterTypes.ITEM_INSTANCE, savedStack);
/*  95 */       load(savedStack);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void F() {
/* 100 */     if (this.g && this == a) throw new AssertionError("TRAP"); 
/* 101 */     this.g = isEmpty();
/*     */   }
/*     */ 
/*     */   
/*     */   public void load(NBTTagCompound nbttagcompound) {
/* 106 */     this.item = Item.b(nbttagcompound.getString("id"));
/* 107 */     this.count = nbttagcompound.getByte("Count");
/*     */ 
/*     */     
/* 110 */     setData(nbttagcompound.getShort("Damage"));
/*     */ 
/*     */     
/* 113 */     if (nbttagcompound.hasKeyOfType("tag", 10)) {
/*     */       
/* 115 */       this.tag = (NBTTagCompound)nbttagcompound.getCompound("tag").clone();
/* 116 */       if (this.item != null) {
/* 117 */         this.item.a(this.tag);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack(NBTTagCompound nbttagcompound) {
/* 124 */     load(nbttagcompound);
/*     */     
/* 126 */     F();
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 130 */     return (this == a) ? true : ((this.item != null && this.item != Item.getItemOf(Blocks.AIR)) ? ((this.count <= 0) ? true : (!(this.damage >= -32768 && this.damage <= 65535))) : true);
/*     */   }
/*     */   
/*     */   public static void a(DataConverterManager dataconvertermanager) {
/* 134 */     dataconvertermanager.a(DataConverterTypes.ITEM_INSTANCE, new DataInspectorBlockEntity());
/* 135 */     dataconvertermanager.a(DataConverterTypes.ITEM_INSTANCE, new DataInspectorEntity());
/*     */   }
/*     */   
/*     */   public ItemStack cloneAndSubtract(int i) {
/* 139 */     int j = Math.min(i, this.count);
/* 140 */     ItemStack itemstack = cloneItemStack();
/*     */     
/* 142 */     itemstack.setCount(j);
/* 143 */     subtract(j);
/* 144 */     return itemstack;
/*     */   }
/*     */   
/*     */   public Item getItem() {
/* 148 */     return this.g ? Item.getItemOf(Blocks.AIR) : this.item;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumInteractionResult placeItem(EntityHuman entityhuman, World world, BlockPosition blockposition, EnumHand enumhand, EnumDirection enumdirection, float f, float f1, float f2) {
/* 153 */     int oldData = getData();
/* 154 */     int oldCount = getCount();
/*     */     
/* 156 */     if (!(getItem() instanceof ItemBucket)) {
/* 157 */       world.captureBlockStates = true;
/*     */       
/* 159 */       if (getItem() instanceof ItemDye && getData() == 15) {
/* 160 */         Block block = world.getType(blockposition).getBlock();
/* 161 */         if (block == Blocks.SAPLING || block instanceof BlockMushroom) {
/* 162 */           world.captureTreeGeneration = true;
/*     */         }
/*     */       } 
/*     */     } 
/* 166 */     EnumInteractionResult enuminteractionresult = getItem().a(entityhuman, world, blockposition, enumhand, enumdirection, f, f1, f2);
/* 167 */     int newData = getData();
/* 168 */     int newCount = getCount();
/* 169 */     setCount(oldCount);
/* 170 */     setData(oldData);
/* 171 */     world.captureBlockStates = false;
/* 172 */     if (enuminteractionresult == EnumInteractionResult.SUCCESS && world.captureTreeGeneration && world.capturedBlockStates.size() > 0) {
/* 173 */       world.captureTreeGeneration = false;
/* 174 */       Location location = new Location((World)world.getWorld(), blockposition.getX(), blockposition.getY(), blockposition.getZ());
/* 175 */       TreeType treeType = BlockSapling.treeType;
/* 176 */       BlockSapling.treeType = null;
/* 177 */       List<BlockState> blocks = (List<BlockState>)world.capturedBlockStates.clone();
/* 178 */       world.capturedBlockStates.clear();
/* 179 */       StructureGrowEvent event = null;
/* 180 */       if (treeType != null) {
/* 181 */         boolean isBonemeal = (getItem() == Items.DYE && oldData == 15);
/* 182 */         event = new StructureGrowEvent(location, treeType, isBonemeal, (Player)entityhuman.getBukkitEntity(), blocks);
/* 183 */         Bukkit.getPluginManager().callEvent((Event)event);
/*     */       } 
/* 185 */       if (event == null || !event.isCancelled()) {
/*     */         
/* 187 */         if (getCount() == oldCount && getData() == oldData) {
/* 188 */           setData(newData);
/* 189 */           setCount(newCount);
/*     */         } 
/* 191 */         for (BlockState blockstate : blocks) {
/* 192 */           blockstate.update(true);
/*     */         }
/*     */       } 
/*     */       
/* 196 */       return enuminteractionresult;
/*     */     } 
/* 198 */     world.captureTreeGeneration = false;
/*     */     
/* 200 */     if (enuminteractionresult == EnumInteractionResult.SUCCESS) {
/* 201 */       BlockPlaceEvent placeEvent = null;
/* 202 */       List<BlockState> blocks = (List<BlockState>)world.capturedBlockStates.clone();
/* 203 */       world.capturedBlockStates.clear();
/* 204 */       if (blocks.size() > 1) {
/* 205 */         BlockMultiPlaceEvent blockMultiPlaceEvent = CraftEventFactory.callBlockMultiPlaceEvent(world, entityhuman, enumhand, blocks, blockposition.getX(), blockposition.getY(), blockposition.getZ());
/* 206 */       } else if (blocks.size() == 1) {
/* 207 */         placeEvent = CraftEventFactory.callBlockPlaceEvent(world, entityhuman, enumhand, blocks.get(0), blockposition.getX(), blockposition.getY(), blockposition.getZ());
/*     */       } 
/*     */       
/* 210 */       if (placeEvent != null && (placeEvent.isCancelled() || !placeEvent.canBuild())) {
/* 211 */         enuminteractionresult = EnumInteractionResult.FAIL;
/*     */         
/* 213 */         placeEvent.getPlayer().updateInventory();
/*     */         
/* 215 */         for (BlockState blockstate : blocks) {
/* 216 */           blockstate.update(true, false);
/*     */         }
/*     */       } else {
/*     */         
/* 220 */         if (getCount() == oldCount && getData() == oldData) {
/* 221 */           setData(newData);
/* 222 */           setCount(newCount);
/*     */         } 
/*     */         
/* 225 */         for (Map.Entry<BlockPosition, TileEntity> e : world.capturedTileEntities.entrySet()) {
/* 226 */           world.setTileEntity(e.getKey(), e.getValue());
/*     */         }
/*     */         
/* 229 */         for (BlockState blockstate : blocks) {
/* 230 */           int x = blockstate.getX();
/* 231 */           int y = blockstate.getY();
/* 232 */           int z = blockstate.getZ();
/* 233 */           int updateFlag = ((CraftBlockState)blockstate).getFlag();
/* 234 */           Material mat = blockstate.getType();
/* 235 */           Block oldBlock = CraftMagicNumbers.getBlock(mat);
/* 236 */           BlockPosition newblockposition = new BlockPosition(x, y, z);
/* 237 */           IBlockData block = world.getType(newblockposition);
/*     */           
/* 239 */           if (!(block.getBlock() instanceof BlockTileEntity)) {
/* 240 */             block.getBlock().onPlace(world, newblockposition, block);
/*     */           }
/*     */           
/* 243 */           world.notifyAndUpdatePhysics(newblockposition, null, oldBlock.getBlockData(), block, updateFlag);
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 248 */         if (this.item instanceof ItemRecord) {
/* 249 */           ((BlockJukeBox)Blocks.JUKEBOX).a(world, blockposition, world.getType(blockposition), this);
/* 250 */           world.a((EntityHuman)null, 1010, blockposition, Item.getId(this.item));
/* 251 */           subtract(1);
/* 252 */           entityhuman.b(StatisticList.Z);
/*     */         } 
/*     */         
/* 255 */         if (this.item == Items.SKULL) {
/* 256 */           BlockPosition bp = blockposition;
/* 257 */           if (!world.getType(blockposition).getBlock().a(world, blockposition)) {
/* 258 */             if (!world.getType(blockposition).getMaterial().isBuildable()) {
/* 259 */               bp = null;
/*     */             } else {
/* 261 */               bp = bp.shift(enumdirection);
/*     */             } 
/*     */           }
/* 264 */           if (bp != null) {
/* 265 */             TileEntity te = world.getTileEntity(bp);
/* 266 */             if (te instanceof TileEntitySkull) {
/* 267 */               Blocks.SKULL.a(world, bp, (TileEntitySkull)te);
/*     */             }
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 273 */         if (this.item instanceof ItemBlock) {
/* 274 */           SoundEffectType soundeffecttype = ((ItemBlock)this.item).getBlock().getStepSound();
/* 275 */           world.a(entityhuman, blockposition, soundeffecttype.e(), SoundCategory.BLOCKS, (soundeffecttype.a() + 1.0F) / 2.0F, soundeffecttype.b() * 0.8F);
/*     */         } 
/*     */         
/* 278 */         entityhuman.b(StatisticList.b(this.item));
/*     */       } 
/*     */     } 
/* 281 */     world.capturedTileEntities.clear();
/* 282 */     world.capturedBlockStates.clear();
/*     */ 
/*     */     
/* 285 */     return enuminteractionresult;
/*     */   }
/*     */   
/*     */   public float a(IBlockData iblockdata) {
/* 289 */     return getItem().getDestroySpeed(this, iblockdata);
/*     */   }
/*     */   
/*     */   public InteractionResultWrapper<ItemStack> a(World world, EntityHuman entityhuman, EnumHand enumhand) {
/* 293 */     return getItem().a(world, entityhuman, enumhand);
/*     */   }
/*     */   
/*     */   public ItemStack a(World world, EntityLiving entityliving) {
/* 297 */     return getItem().a(this, world, entityliving);
/*     */   }
/*     */   
/*     */   public NBTTagCompound save(NBTTagCompound nbttagcompound) {
/* 301 */     MinecraftKey minecraftkey = Item.REGISTRY.b(this.item);
/*     */     
/* 303 */     nbttagcompound.setString("id", (minecraftkey == null) ? "minecraft:air" : minecraftkey.toString());
/* 304 */     nbttagcompound.setByte("Count", (byte)this.count);
/* 305 */     nbttagcompound.setShort("Damage", (short)this.damage);
/* 306 */     if (this.tag != null) {
/* 307 */       nbttagcompound.set("tag", this.tag.clone());
/*     */     }
/*     */     
/* 310 */     return nbttagcompound;
/*     */   }
/*     */   
/*     */   public int getMaxStackSize() {
/* 314 */     return getItem().getMaxStackSize();
/*     */   }
/*     */   
/*     */   public boolean isStackable() {
/* 318 */     return (getMaxStackSize() > 1 && (!f() || !h()));
/*     */   }
/*     */   
/*     */   public boolean f() {
/* 322 */     return this.g ? false : ((this.item.getMaxDurability() <= 0) ? false : (!(hasTag() && getTag().getBoolean("Unbreakable"))));
/*     */   }
/*     */   
/*     */   public boolean usesData() {
/* 326 */     return getItem().k();
/*     */   }
/*     */   
/*     */   public boolean h() {
/* 330 */     return (f() && this.damage > 0);
/*     */   }
/*     */   
/*     */   public int i() {
/* 334 */     return this.damage;
/*     */   }
/*     */   
/*     */   public int getData() {
/* 338 */     return this.damage;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setData(int i) {
/* 344 */     if (i == 32767) {
/* 345 */       this.damage = i;
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 350 */     if (CraftMagicNumbers.getBlock(CraftMagicNumbers.getId(getItem())) != Blocks.AIR)
/*     */     {
/* 352 */       if (!usesData() && !getItem().usesDurability()) {
/* 353 */         i = 0;
/*     */       }
/*     */     }
/*     */ 
/*     */     
/* 358 */     if (CraftMagicNumbers.getBlock(CraftMagicNumbers.getId(getItem())) == Blocks.DOUBLE_PLANT && (i > 5 || i < 0)) {
/* 359 */       i = 0;
/*     */     }
/*     */     
/* 362 */     this.damage = i;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int k() {
/* 369 */     return getItem().getMaxDurability();
/*     */   }
/*     */   
/*     */   public boolean isDamaged(int i, Random random, @Nullable EntityPlayer entityplayer) {
/* 373 */     if (!f()) {
/* 374 */       return false;
/*     */     }
/* 376 */     if (i > 0) {
/* 377 */       int j = EnchantmentManager.getEnchantmentLevel(Enchantments.DURABILITY, this);
/* 378 */       int k = 0;
/*     */       
/* 380 */       for (int l = 0; j > 0 && l < i; l++) {
/* 381 */         if (EnchantmentDurability.a(this, j, random)) {
/* 382 */           k++;
/*     */         }
/*     */       } 
/*     */       
/* 386 */       i -= k;
/*     */       
/* 388 */       if (entityplayer != null) {
/* 389 */         CraftItemStack item = CraftItemStack.asCraftMirror(this);
/* 390 */         PlayerItemDamageEvent event = new PlayerItemDamageEvent((Player)entityplayer.getBukkitEntity(), (org.bukkit.inventory.ItemStack)item, i);
/* 391 */         Bukkit.getServer().getPluginManager().callEvent((Event)event);
/* 392 */         if (i != event.getDamage() || event.isCancelled()) {
/* 393 */           event.getPlayer().updateInventory();
/*     */         }
/* 395 */         if (event.isCancelled()) return false; 
/* 396 */         i = event.getDamage();
/*     */       } 
/*     */       
/* 399 */       if (i <= 0) {
/* 400 */         return false;
/*     */       }
/*     */     } 
/*     */     
/* 404 */     if (entityplayer != null && i != 0) {
/* 405 */       CriterionTriggers.s.a(entityplayer, this, this.damage + i);
/*     */     }
/*     */     
/* 408 */     this.damage += i;
/* 409 */     return (this.damage > k());
/*     */   }
/*     */ 
/*     */   
/*     */   public void damage(int i, EntityLiving entityliving) {
/* 414 */     if ((!(entityliving instanceof EntityHuman) || !((EntityHuman)entityliving).abilities.canInstantlyBuild) && 
/* 415 */       f() && 
/* 416 */       isDamaged(i, entityliving.getRandom(), (entityliving instanceof EntityPlayer) ? (EntityPlayer)entityliving : null)) {
/* 417 */       entityliving.b(this);
/*     */       
/* 419 */       if (this.count == 1 && entityliving instanceof EntityHuman) {
/* 420 */         CraftEventFactory.callPlayerItemBreakEvent((EntityHuman)entityliving, this);
/*     */       }
/*     */       
/* 423 */       subtract(1);
/* 424 */       if (entityliving instanceof EntityHuman) {
/* 425 */         EntityHuman entityhuman = (EntityHuman)entityliving;
/*     */         
/* 427 */         entityhuman.b(StatisticList.c(this.item));
/*     */       } 
/*     */       
/* 430 */       this.damage = 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(EntityLiving entityliving, EntityHuman entityhuman) {
/* 438 */     boolean flag = this.item.a(this, entityliving, entityhuman);
/*     */     
/* 440 */     if (flag) {
/* 441 */       entityhuman.b(StatisticList.b(this.item));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(World world, IBlockData iblockdata, BlockPosition blockposition, EntityHuman entityhuman) {
/* 447 */     boolean flag = getItem().a(this, world, iblockdata, blockposition, entityhuman);
/*     */     
/* 449 */     if (flag) {
/* 450 */       entityhuman.b(StatisticList.b(this.item));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b(IBlockData iblockdata) {
/* 456 */     return getItem().canDestroySpecialBlock(iblockdata);
/*     */   }
/*     */   
/*     */   public boolean a(EntityHuman entityhuman, EntityLiving entityliving, EnumHand enumhand) {
/* 460 */     return getItem().a(this, entityhuman, entityliving, enumhand);
/*     */   }
/*     */   
/*     */   public ItemStack cloneItemStack() {
/* 464 */     ItemStack itemstack = new ItemStack(this.item, this.count, this.damage, false);
/*     */     
/* 466 */     itemstack.d(D());
/* 467 */     if (this.tag != null) {
/* 468 */       itemstack.tag = this.tag.g();
/*     */     }
/*     */     
/* 471 */     return itemstack;
/*     */   }
/*     */   
/*     */   public static boolean equals(ItemStack itemstack, ItemStack itemstack1) {
/* 475 */     return (itemstack.isEmpty() && itemstack1.isEmpty()) ? true : ((!itemstack.isEmpty() && !itemstack1.isEmpty()) ? ((itemstack.tag == null && itemstack1.tag != null) ? false : (!(itemstack.tag != null && !itemstack.tag.equals(itemstack1.tag)))) : false);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean fastMatches(ItemStack itemstack, ItemStack itemstack1) {
/* 480 */     if (itemstack.isEmpty() && itemstack1.isEmpty()) {
/* 481 */       return true;
/*     */     }
/* 483 */     if (!itemstack.isEmpty() && !itemstack1.isEmpty()) {
/* 484 */       return (itemstack.count == itemstack1.count && itemstack.item == itemstack1.item && itemstack.damage == itemstack1.damage);
/*     */     }
/* 486 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean matches(ItemStack itemstack, ItemStack itemstack1) {
/* 491 */     return (itemstack.isEmpty() && itemstack1.isEmpty()) ? true : ((!itemstack.isEmpty() && !itemstack1.isEmpty()) ? itemstack.d(itemstack1) : false);
/*     */   }
/*     */   
/*     */   private boolean d(ItemStack itemstack) {
/* 495 */     return (this.count != itemstack.count) ? false : ((getItem() != itemstack.getItem()) ? false : ((this.damage != itemstack.damage) ? false : ((this.tag == null && itemstack.tag != null) ? false : (!(this.tag != null && !this.tag.equals(itemstack.tag))))));
/*     */   }
/*     */   
/*     */   public static boolean c(ItemStack itemstack, ItemStack itemstack1) {
/* 499 */     return (itemstack == itemstack1) ? true : ((!itemstack.isEmpty() && !itemstack1.isEmpty()) ? itemstack.doMaterialsMatch(itemstack1) : false);
/*     */   }
/*     */   
/*     */   public static boolean d(ItemStack itemstack, ItemStack itemstack1) {
/* 503 */     return (itemstack == itemstack1) ? true : ((!itemstack.isEmpty() && !itemstack1.isEmpty()) ? itemstack.b(itemstack1) : false);
/*     */   }
/*     */   
/*     */   public boolean doMaterialsMatch(ItemStack itemstack) {
/* 507 */     return (!itemstack.isEmpty() && this.item == itemstack.item && this.damage == itemstack.damage);
/*     */   }
/*     */   
/*     */   public boolean b(ItemStack itemstack) {
/* 511 */     return !f() ? doMaterialsMatch(itemstack) : ((!itemstack.isEmpty() && this.item == itemstack.item));
/*     */   }
/*     */   
/*     */   public String a() {
/* 515 */     return getItem().a(this);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 519 */     return String.valueOf(this.count) + "x" + getItem().getName() + "@" + this.damage;
/*     */   }
/*     */   
/*     */   public void a(World world, Entity entity, int i, boolean flag) {
/* 523 */     if (this.d > 0) {
/* 524 */       this.d--;
/*     */     }
/*     */     
/* 527 */     if (this.item != null) {
/* 528 */       this.item.a(this, world, entity, i, flag);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(World world, EntityHuman entityhuman, int i) {
/* 534 */     entityhuman.a(StatisticList.a(this.item), i);
/* 535 */     getItem().b(this, world, entityhuman);
/*     */   }
/*     */   
/*     */   public int m() {
/* 539 */     return getItem().e(this);
/*     */   }
/*     */   
/*     */   public EnumAnimation n() {
/* 543 */     return getItem().f(this);
/*     */   }
/*     */   
/*     */   public void a(World world, EntityLiving entityliving, int i) {
/* 547 */     getItem().a(this, world, entityliving, i);
/*     */   }
/*     */   
/*     */   public boolean hasTag() {
/* 551 */     return (!this.g && this.tag != null);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public NBTTagCompound getTag() {
/* 556 */     return this.tag;
/*     */   }
/*     */   
/*     */   public NBTTagCompound c(String s) {
/* 560 */     if (this.tag != null && this.tag.hasKeyOfType(s, 10)) {
/* 561 */       return this.tag.getCompound(s);
/*     */     }
/* 563 */     NBTTagCompound nbttagcompound = new NBTTagCompound();
/*     */     
/* 565 */     a(s, nbttagcompound);
/* 566 */     return nbttagcompound;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public NBTTagCompound d(String s) {
/* 572 */     return (this.tag != null && this.tag.hasKeyOfType(s, 10)) ? this.tag.getCompound(s) : null;
/*     */   }
/*     */   
/*     */   public void e(String s) {
/* 576 */     if (this.tag != null && this.tag.hasKeyOfType(s, 10)) {
/* 577 */       this.tag.remove(s);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagList getEnchantments() {
/* 583 */     return (this.tag != null) ? this.tag.getList("ench", 10) : new NBTTagList();
/*     */   }
/*     */   
/*     */   public void setTag(@Nullable NBTTagCompound nbttagcompound) {
/* 587 */     this.tag = nbttagcompound;
/*     */   }
/*     */   
/*     */   public String getName() {
/* 591 */     NBTTagCompound nbttagcompound = d("display");
/*     */     
/* 593 */     if (nbttagcompound != null) {
/* 594 */       if (nbttagcompound.hasKeyOfType("Name", 8)) {
/* 595 */         return nbttagcompound.getString("Name");
/*     */       }
/*     */       
/* 598 */       if (nbttagcompound.hasKeyOfType("LocName", 8)) {
/* 599 */         return LocaleI18n.get(nbttagcompound.getString("LocName"));
/*     */       }
/*     */     } 
/*     */     
/* 603 */     return getItem().b(this);
/*     */   }
/*     */   
/*     */   public ItemStack f(String s) {
/* 607 */     c("display").setString("LocName", s);
/* 608 */     return this;
/*     */   }
/*     */   
/*     */   public ItemStack g(String s) {
/* 612 */     c("display").setString("Name", s);
/* 613 */     return this;
/*     */   }
/*     */   
/*     */   public void s() {
/* 617 */     NBTTagCompound nbttagcompound = d("display");
/*     */     
/* 619 */     if (nbttagcompound != null) {
/* 620 */       nbttagcompound.remove("Name");
/* 621 */       if (nbttagcompound.isEmpty()) {
/* 622 */         e("display");
/*     */       }
/*     */     } 
/*     */     
/* 626 */     if (this.tag != null && this.tag.isEmpty()) {
/* 627 */       this.tag = null;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasName() {
/* 633 */     NBTTagCompound nbttagcompound = d("display");
/*     */     
/* 635 */     return (nbttagcompound != null && nbttagcompound.hasKeyOfType("Name", 8));
/*     */   }
/*     */   
/*     */   public EnumItemRarity v() {
/* 639 */     return getItem().g(this);
/*     */   }
/*     */   
/*     */   public boolean canEnchant() {
/* 643 */     return !getItem().g_(this) ? false : (!hasEnchantments());
/*     */   }
/*     */   
/*     */   public void addEnchantment(Enchantment enchantment, int i) {
/* 647 */     if (this.tag == null) {
/* 648 */       setTag(new NBTTagCompound());
/*     */     }
/*     */     
/* 651 */     if (!this.tag.hasKeyOfType("ench", 9)) {
/* 652 */       this.tag.set("ench", new NBTTagList());
/*     */     }
/*     */     
/* 655 */     NBTTagList nbttaglist = this.tag.getList("ench", 10);
/* 656 */     NBTTagCompound nbttagcompound = new NBTTagCompound();
/*     */     
/* 658 */     nbttagcompound.setShort("id", (short)Enchantment.getId(enchantment));
/* 659 */     nbttagcompound.setShort("lvl", (short)(byte)i);
/* 660 */     nbttaglist.add(nbttagcompound);
/*     */   }
/*     */   
/*     */   public boolean hasEnchantments() {
/* 664 */     return (this.tag != null && this.tag.hasKeyOfType("ench", 9)) ? (!this.tag.getList("ench", 10).isEmpty()) : false;
/*     */   }
/*     */   
/*     */   public void a(String s, NBTBase nbtbase) {
/* 668 */     if (this.tag == null) {
/* 669 */       setTag(new NBTTagCompound());
/*     */     }
/*     */     
/* 672 */     this.tag.set(s, nbtbase);
/*     */   }
/*     */   
/*     */   public boolean y() {
/* 676 */     return getItem().s();
/*     */   }
/*     */   
/*     */   public boolean z() {
/* 680 */     return (this.i != null);
/*     */   }
/*     */   
/*     */   public void a(EntityItemFrame entityitemframe) {
/* 684 */     this.i = entityitemframe;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public EntityItemFrame A() {
/* 689 */     return this.g ? null : this.i;
/*     */   }
/*     */   
/*     */   public int getRepairCost() {
/* 693 */     return (hasTag() && this.tag.hasKeyOfType("RepairCost", 3)) ? this.tag.getInt("RepairCost") : 0;
/*     */   }
/*     */   
/*     */   public void setRepairCost(int i) {
/* 697 */     if (!hasTag()) {
/* 698 */       this.tag = new NBTTagCompound();
/*     */     }
/*     */     
/* 701 */     this.tag.setInt("RepairCost", i);
/*     */   }
/*     */ 
/*     */   
/*     */   public Multimap<String, AttributeModifier> a(EnumItemSlot enumitemslot) {
/*     */     Object<String, AttributeModifier> object;
/* 707 */     if (hasTag() && this.tag.hasKeyOfType("AttributeModifiers", 9)) {
/* 708 */       object = (Object<String, AttributeModifier>)HashMultimap.create();
/* 709 */       NBTTagList nbttaglist = this.tag.getList("AttributeModifiers", 10);
/*     */       
/* 711 */       for (int i = 0; i < nbttaglist.size(); i++) {
/* 712 */         NBTTagCompound nbttagcompound = nbttaglist.get(i);
/* 713 */         AttributeModifier attributemodifier = GenericAttributes.a(nbttagcompound);
/*     */         
/* 715 */         if (attributemodifier != null && (!nbttagcompound.hasKeyOfType("Slot", 8) || nbttagcompound.getString("Slot").equals(enumitemslot.d())) && attributemodifier.a().getLeastSignificantBits() != 0L && attributemodifier.a().getMostSignificantBits() != 0L) {
/* 716 */           ((Multimap)object).put(nbttagcompound.getString("AttributeName"), attributemodifier);
/*     */         }
/*     */       } 
/*     */     } else {
/* 720 */       object = (Object<String, AttributeModifier>)getItem().a(enumitemslot);
/*     */     } 
/*     */     
/* 723 */     return (Multimap<String, AttributeModifier>)object;
/*     */   }
/*     */   
/*     */   public void a(String s, AttributeModifier attributemodifier, @Nullable EnumItemSlot enumitemslot) {
/* 727 */     if (this.tag == null) {
/* 728 */       this.tag = new NBTTagCompound();
/*     */     }
/*     */     
/* 731 */     if (!this.tag.hasKeyOfType("AttributeModifiers", 9)) {
/* 732 */       this.tag.set("AttributeModifiers", new NBTTagList());
/*     */     }
/*     */     
/* 735 */     NBTTagList nbttaglist = this.tag.getList("AttributeModifiers", 10);
/* 736 */     NBTTagCompound nbttagcompound = GenericAttributes.a(attributemodifier);
/*     */     
/* 738 */     nbttagcompound.setString("AttributeName", s);
/* 739 */     if (enumitemslot != null) {
/* 740 */       nbttagcompound.setString("Slot", enumitemslot.d());
/*     */     }
/*     */     
/* 743 */     nbttaglist.add(nbttagcompound);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public void setItem(Item item) {
/* 748 */     this.item = item;
/* 749 */     setData(getData());
/*     */   }
/*     */   
/*     */   public IChatBaseComponent C() {
/* 753 */     ChatComponentText chatcomponenttext = new ChatComponentText(getName());
/*     */     
/* 755 */     if (hasName()) {
/* 756 */       chatcomponenttext.getChatModifier().setItalic(Boolean.valueOf(true));
/*     */     }
/*     */     
/* 759 */     IChatBaseComponent ichatbasecomponent = (new ChatComponentText("[")).addSibling(chatcomponenttext).a("]");
/*     */     
/* 761 */     if (!this.g) {
/* 762 */       NBTTagCompound nbttagcompound = save(new NBTTagCompound());
/*     */       
/* 764 */       ichatbasecomponent.getChatModifier().setChatHoverable(new ChatHoverable(ChatHoverable.EnumHoverAction.SHOW_ITEM, new ChatComponentText(nbttagcompound.toString())));
/* 765 */       ichatbasecomponent.getChatModifier().setColor((v()).e);
/*     */     } 
/*     */     
/* 768 */     return ichatbasecomponent;
/*     */   }
/*     */   
/*     */   public boolean a(Block block) {
/* 772 */     if (block == this.j) {
/* 773 */       return this.k;
/*     */     }
/* 775 */     this.j = block;
/* 776 */     if (hasTag() && this.tag.hasKeyOfType("CanDestroy", 9)) {
/* 777 */       NBTTagList nbttaglist = this.tag.getList("CanDestroy", 8);
/*     */       
/* 779 */       for (int i = 0; i < nbttaglist.size(); i++) {
/* 780 */         Block block1 = Block.getByName(nbttaglist.getString(i));
/*     */         
/* 782 */         if (block1 == block) {
/* 783 */           this.k = true;
/* 784 */           return true;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 789 */     this.k = false;
/* 790 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b(Block block) {
/* 795 */     if (block == this.l) {
/* 796 */       return this.m;
/*     */     }
/* 798 */     this.l = block;
/* 799 */     if (hasTag() && this.tag.hasKeyOfType("CanPlaceOn", 9)) {
/* 800 */       NBTTagList nbttaglist = this.tag.getList("CanPlaceOn", 8);
/*     */       
/* 802 */       for (int i = 0; i < nbttaglist.size(); i++) {
/* 803 */         Block block1 = Block.getByName(nbttaglist.getString(i));
/*     */         
/* 805 */         if (block1 == block) {
/* 806 */           this.m = true;
/* 807 */           return true;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 812 */     this.m = false;
/* 813 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int D() {
/* 818 */     return this.d;
/*     */   }
/*     */   
/*     */   public void d(int i) {
/* 822 */     this.d = i;
/*     */   }
/*     */   
/*     */   public int getCount() {
/* 826 */     return this.g ? 0 : this.count;
/*     */   }
/*     */   
/*     */   public void setCount(int i) {
/* 830 */     this.count = i;
/* 831 */     F();
/*     */   }
/*     */   
/*     */   public void add(int i) {
/* 835 */     setCount(this.count + i);
/*     */   }
/*     */   
/*     */   public void subtract(int i) {
/* 839 */     add(-i);
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemStack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */