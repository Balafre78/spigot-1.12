/*     */ package net.minecraft.server.v1_12_R1;
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.TreeType;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.BlockState;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.projectiles.CraftBlockProjectileSource;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.block.BlockDispenseEvent;
/*     */ import org.bukkit.event.entity.CreatureSpawnEvent;
/*     */ import org.bukkit.event.world.StructureGrowEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.projectiles.ProjectileSource;
/*     */ import org.bukkit.util.Vector;
/*     */ 
/*     */ public class DispenserRegistry {
/*  22 */   public static final PrintStream a = System.out;
/*     */   private static boolean c;
/*     */   public static boolean b;
/*  25 */   private static final Logger d = LogManager.getLogger();
/*     */   
/*     */   public static boolean a() {
/*  28 */     return c;
/*     */   }
/*     */   
/*     */   static void b() {
/*  32 */     BlockDispenser.REGISTRY.a(Items.ARROW, new DispenseBehaviorProjectile() {
/*     */           protected IProjectile a(World world, IPosition iposition, ItemStack itemstack) {
/*  34 */             EntityTippedArrow entitytippedarrow = new EntityTippedArrow(world, iposition.getX(), iposition.getY(), iposition.getZ());
/*     */             
/*  36 */             entitytippedarrow.fromPlayer = EntityArrow.PickupStatus.ALLOWED;
/*  37 */             return entitytippedarrow;
/*     */           }
/*     */         });
/*  40 */     BlockDispenser.REGISTRY.a(Items.TIPPED_ARROW, new DispenseBehaviorProjectile() {
/*     */           protected IProjectile a(World world, IPosition iposition, ItemStack itemstack) {
/*  42 */             EntityTippedArrow entitytippedarrow = new EntityTippedArrow(world, iposition.getX(), iposition.getY(), iposition.getZ());
/*     */             
/*  44 */             entitytippedarrow.a(itemstack);
/*  45 */             entitytippedarrow.fromPlayer = EntityArrow.PickupStatus.ALLOWED;
/*  46 */             return entitytippedarrow;
/*     */           }
/*     */         });
/*  49 */     BlockDispenser.REGISTRY.a(Items.SPECTRAL_ARROW, new DispenseBehaviorProjectile() {
/*     */           protected IProjectile a(World world, IPosition iposition, ItemStack itemstack) {
/*  51 */             EntitySpectralArrow entityspectralarrow = new EntitySpectralArrow(world, iposition.getX(), iposition.getY(), iposition.getZ());
/*     */             
/*  53 */             entityspectralarrow.fromPlayer = EntityArrow.PickupStatus.ALLOWED;
/*  54 */             return entityspectralarrow;
/*     */           }
/*     */         });
/*  57 */     BlockDispenser.REGISTRY.a(Items.EGG, new DispenseBehaviorProjectile() {
/*     */           protected IProjectile a(World world, IPosition iposition, ItemStack itemstack) {
/*  59 */             return new EntityEgg(world, iposition.getX(), iposition.getY(), iposition.getZ());
/*     */           }
/*     */         });
/*  62 */     BlockDispenser.REGISTRY.a(Items.SNOWBALL, new DispenseBehaviorProjectile() {
/*     */           protected IProjectile a(World world, IPosition iposition, ItemStack itemstack) {
/*  64 */             return new EntitySnowball(world, iposition.getX(), iposition.getY(), iposition.getZ());
/*     */           }
/*     */         });
/*  67 */     BlockDispenser.REGISTRY.a(Items.EXPERIENCE_BOTTLE, new DispenseBehaviorProjectile() {
/*     */           protected IProjectile a(World world, IPosition iposition, ItemStack itemstack) {
/*  69 */             return new EntityThrownExpBottle(world, iposition.getX(), iposition.getY(), iposition.getZ());
/*     */           }
/*     */           
/*     */           protected float a() {
/*  73 */             return super.a() * 0.5F;
/*     */           }
/*     */           
/*     */           protected float getPower() {
/*  77 */             return super.getPower() * 1.25F;
/*     */           }
/*     */         });
/*  80 */     BlockDispenser.REGISTRY.a(Items.SPLASH_POTION, new IDispenseBehavior() {
/*     */           public ItemStack a(ISourceBlock isourceblock, ItemStack itemstack) {
/*  82 */             return (new DispenseBehaviorProjectile() {
/*     */                 protected IProjectile a(World world, IPosition iposition, ItemStack itemstack1) {
/*  84 */                   return new EntityPotion(world, iposition.getX(), iposition.getY(), iposition.getZ(), itemstack1.cloneItemStack());
/*     */                 }
/*     */                 
/*     */                 protected float a() {
/*  88 */                   return super.a() * 0.5F;
/*     */                 }
/*     */                 
/*     */                 protected float getPower() {
/*  92 */                   return super.getPower() * 1.25F;
/*     */                 }
/*  94 */               }).a(isourceblock, itemstack);
/*     */           }
/*     */         });
/*  97 */     BlockDispenser.REGISTRY.a(Items.LINGERING_POTION, new IDispenseBehavior() {
/*     */           public ItemStack a(ISourceBlock isourceblock, ItemStack itemstack) {
/*  99 */             return (new DispenseBehaviorProjectile() {
/*     */                 protected IProjectile a(World world, IPosition iposition, ItemStack itemstack1) {
/* 101 */                   return new EntityPotion(world, iposition.getX(), iposition.getY(), iposition.getZ(), itemstack1.cloneItemStack());
/*     */                 }
/*     */                 
/*     */                 protected float a() {
/* 105 */                   return super.a() * 0.5F;
/*     */                 }
/*     */                 
/*     */                 protected float getPower() {
/* 109 */                   return super.getPower() * 1.25F;
/*     */                 }
/* 111 */               }).a(isourceblock, itemstack);
/*     */           }
/*     */         });
/* 114 */     BlockDispenser.REGISTRY.a(Items.SPAWN_EGG, new DispenseBehaviorItem() {
/*     */           public ItemStack b(ISourceBlock isourceblock, ItemStack itemstack) {
/* 116 */             EnumDirection enumdirection = isourceblock.e().<EnumDirection>get(BlockDispenser.FACING);
/* 117 */             double d0 = isourceblock.getX() + enumdirection.getAdjacentX();
/* 118 */             double d1 = ((isourceblock.getBlockPosition().getY() + enumdirection.getAdjacentY()) + 0.2F);
/* 119 */             double d2 = isourceblock.getZ() + enumdirection.getAdjacentZ();
/*     */ 
/*     */ 
/*     */             
/* 123 */             World world = isourceblock.getWorld();
/* 124 */             ItemStack itemstack1 = itemstack.cloneAndSubtract(1);
/* 125 */             Block block = world.getWorld().getBlockAt(isourceblock.getBlockPosition().getX(), isourceblock.getBlockPosition().getY(), isourceblock.getBlockPosition().getZ());
/* 126 */             CraftItemStack craftItem = CraftItemStack.asCraftMirror(itemstack1);
/*     */             
/* 128 */             BlockDispenseEvent event = new BlockDispenseEvent(block, (ItemStack)craftItem.clone(), new Vector(d0, d1, d2));
/* 129 */             if (!BlockDispenser.eventFired) {
/* 130 */               world.getServer().getPluginManager().callEvent((Event)event);
/*     */             }
/*     */             
/* 133 */             if (event.isCancelled()) {
/* 134 */               itemstack.add(1);
/* 135 */               return itemstack;
/*     */             } 
/*     */             
/* 138 */             if (!event.getItem().equals(craftItem)) {
/* 139 */               itemstack.add(1);
/*     */               
/* 141 */               ItemStack eventStack = CraftItemStack.asNMSCopy(event.getItem());
/* 142 */               IDispenseBehavior idispensebehavior = BlockDispenser.REGISTRY.get(eventStack.getItem());
/* 143 */               if (idispensebehavior != IDispenseBehavior.NONE && idispensebehavior != this) {
/* 144 */                 idispensebehavior.a(isourceblock, eventStack);
/* 145 */                 return itemstack;
/*     */               } 
/*     */             } 
/*     */             
/* 149 */             itemstack1 = CraftItemStack.asNMSCopy(event.getItem());
/*     */             
/* 151 */             Entity entity = ItemMonsterEgg.spawnCreature(isourceblock.getWorld(), ItemMonsterEgg.h(itemstack), event.getVelocity().getX(), event.getVelocity().getY(), event.getVelocity().getZ(), CreatureSpawnEvent.SpawnReason.DISPENSE_EGG);
/*     */             
/* 153 */             if (entity instanceof EntityLiving && itemstack.hasName()) {
/* 154 */               entity.setCustomName(itemstack.getName());
/*     */             }
/*     */             
/* 157 */             ItemMonsterEgg.a(isourceblock.getWorld(), null, itemstack, entity);
/*     */ 
/*     */             
/* 160 */             return itemstack;
/*     */           }
/*     */         });
/* 163 */     BlockDispenser.REGISTRY.a(Items.FIREWORKS, new DispenseBehaviorItem() {
/*     */           public ItemStack b(ISourceBlock isourceblock, ItemStack itemstack) {
/* 165 */             EnumDirection enumdirection = isourceblock.e().<EnumDirection>get(BlockDispenser.FACING);
/* 166 */             double d0 = isourceblock.getX() + enumdirection.getAdjacentX();
/* 167 */             double d1 = (isourceblock.getBlockPosition().getY() + 0.2F);
/* 168 */             double d2 = isourceblock.getZ() + enumdirection.getAdjacentZ();
/*     */             
/* 170 */             World world = isourceblock.getWorld();
/* 171 */             ItemStack itemstack1 = itemstack.cloneAndSubtract(1);
/* 172 */             Block block = world.getWorld().getBlockAt(isourceblock.getBlockPosition().getX(), isourceblock.getBlockPosition().getY(), isourceblock.getBlockPosition().getZ());
/* 173 */             CraftItemStack craftItem = CraftItemStack.asCraftMirror(itemstack1);
/*     */             
/* 175 */             BlockDispenseEvent event = new BlockDispenseEvent(block, (ItemStack)craftItem.clone(), new Vector(d0, d1, d2));
/* 176 */             if (!BlockDispenser.eventFired) {
/* 177 */               world.getServer().getPluginManager().callEvent((Event)event);
/*     */             }
/*     */             
/* 180 */             if (event.isCancelled()) {
/* 181 */               itemstack.add(1);
/* 182 */               return itemstack;
/*     */             } 
/*     */             
/* 185 */             if (!event.getItem().equals(craftItem)) {
/* 186 */               itemstack.add(1);
/*     */               
/* 188 */               ItemStack eventStack = CraftItemStack.asNMSCopy(event.getItem());
/* 189 */               IDispenseBehavior idispensebehavior = BlockDispenser.REGISTRY.get(eventStack.getItem());
/* 190 */               if (idispensebehavior != IDispenseBehavior.NONE && idispensebehavior != this) {
/* 191 */                 idispensebehavior.a(isourceblock, eventStack);
/* 192 */                 return itemstack;
/*     */               } 
/*     */             } 
/*     */             
/* 196 */             itemstack1 = CraftItemStack.asNMSCopy(event.getItem());
/* 197 */             EntityFireworks entityfireworks = new EntityFireworks(isourceblock.getWorld(), event.getVelocity().getX(), event.getVelocity().getY(), event.getVelocity().getZ(), itemstack1);
/*     */             
/* 199 */             isourceblock.getWorld().addEntity(entityfireworks);
/*     */ 
/*     */             
/* 202 */             return itemstack;
/*     */           }
/*     */           
/*     */           protected void a(ISourceBlock isourceblock) {
/* 206 */             isourceblock.getWorld().triggerEffect(1004, isourceblock.getBlockPosition(), 0);
/*     */           }
/*     */         });
/* 209 */     BlockDispenser.REGISTRY.a(Items.FIRE_CHARGE, new DispenseBehaviorItem() {
/*     */           public ItemStack b(ISourceBlock isourceblock, ItemStack itemstack) {
/* 211 */             EnumDirection enumdirection = isourceblock.e().<EnumDirection>get(BlockDispenser.FACING);
/* 212 */             IPosition iposition = BlockDispenser.a(isourceblock);
/* 213 */             double d0 = iposition.getX() + (enumdirection.getAdjacentX() * 0.3F);
/* 214 */             double d1 = iposition.getY() + (enumdirection.getAdjacentY() * 0.3F);
/* 215 */             double d2 = iposition.getZ() + (enumdirection.getAdjacentZ() * 0.3F);
/* 216 */             World world = isourceblock.getWorld();
/* 217 */             Random random = world.random;
/* 218 */             double d3 = random.nextGaussian() * 0.05D + enumdirection.getAdjacentX();
/* 219 */             double d4 = random.nextGaussian() * 0.05D + enumdirection.getAdjacentY();
/* 220 */             double d5 = random.nextGaussian() * 0.05D + enumdirection.getAdjacentZ();
/*     */ 
/*     */             
/* 223 */             ItemStack itemstack1 = itemstack.cloneAndSubtract(1);
/* 224 */             Block block = world.getWorld().getBlockAt(isourceblock.getBlockPosition().getX(), isourceblock.getBlockPosition().getY(), isourceblock.getBlockPosition().getZ());
/* 225 */             CraftItemStack craftItem = CraftItemStack.asCraftMirror(itemstack1);
/*     */             
/* 227 */             BlockDispenseEvent event = new BlockDispenseEvent(block, (ItemStack)craftItem.clone(), new Vector(d3, d4, d5));
/* 228 */             if (!BlockDispenser.eventFired) {
/* 229 */               world.getServer().getPluginManager().callEvent((Event)event);
/*     */             }
/*     */             
/* 232 */             if (event.isCancelled()) {
/* 233 */               itemstack.add(1);
/* 234 */               return itemstack;
/*     */             } 
/*     */             
/* 237 */             if (!event.getItem().equals(craftItem)) {
/* 238 */               itemstack.add(1);
/*     */               
/* 240 */               ItemStack eventStack = CraftItemStack.asNMSCopy(event.getItem());
/* 241 */               IDispenseBehavior idispensebehavior = BlockDispenser.REGISTRY.get(eventStack.getItem());
/* 242 */               if (idispensebehavior != IDispenseBehavior.NONE && idispensebehavior != this) {
/* 243 */                 idispensebehavior.a(isourceblock, eventStack);
/* 244 */                 return itemstack;
/*     */               } 
/*     */             } 
/*     */             
/* 248 */             EntitySmallFireball fireball = new EntitySmallFireball(world, d0, d1, d2, event.getVelocity().getX(), event.getVelocity().getY(), event.getVelocity().getZ());
/* 249 */             fireball.projectileSource = (ProjectileSource)new CraftBlockProjectileSource(isourceblock.<TileEntityDispenser>getTileEntity());
/*     */             
/* 251 */             world.addEntity(fireball);
/*     */ 
/*     */             
/* 254 */             return itemstack;
/*     */           }
/*     */           
/*     */           protected void a(ISourceBlock isourceblock) {
/* 258 */             isourceblock.getWorld().triggerEffect(1018, isourceblock.getBlockPosition(), 0);
/*     */           }
/*     */         });
/* 261 */     BlockDispenser.REGISTRY.a(Items.aH, new a(EntityBoat.EnumBoatType.OAK));
/* 262 */     BlockDispenser.REGISTRY.a(Items.aI, new a(EntityBoat.EnumBoatType.SPRUCE));
/* 263 */     BlockDispenser.REGISTRY.a(Items.aJ, new a(EntityBoat.EnumBoatType.BIRCH));
/* 264 */     BlockDispenser.REGISTRY.a(Items.aK, new a(EntityBoat.EnumBoatType.JUNGLE));
/* 265 */     BlockDispenser.REGISTRY.a(Items.aM, new a(EntityBoat.EnumBoatType.DARK_OAK));
/* 266 */     BlockDispenser.REGISTRY.a(Items.aL, new a(EntityBoat.EnumBoatType.ACACIA));
/* 267 */     DispenseBehaviorItem dispensebehavioritem = new DispenseBehaviorItem() {
/* 268 */         private final DispenseBehaviorItem b = new DispenseBehaviorItem();
/*     */         
/*     */         public ItemStack b(ISourceBlock isourceblock, ItemStack itemstack) {
/* 271 */           ItemBucket itembucket = (ItemBucket)itemstack.getItem();
/* 272 */           BlockPosition blockposition = isourceblock.getBlockPosition().shift(isourceblock.e().<EnumDirection>get(BlockDispenser.FACING));
/*     */ 
/*     */           
/* 275 */           World world = isourceblock.getWorld();
/* 276 */           int x = blockposition.getX();
/* 277 */           int y = blockposition.getY();
/* 278 */           int z = blockposition.getZ();
/* 279 */           if (world.isEmpty(blockposition) || !world.getType(blockposition).getMaterial().isBuildable()) {
/* 280 */             Block block = world.getWorld().getBlockAt(isourceblock.getBlockPosition().getX(), isourceblock.getBlockPosition().getY(), isourceblock.getBlockPosition().getZ());
/* 281 */             CraftItemStack craftItem = CraftItemStack.asCraftMirror(itemstack);
/*     */             
/* 283 */             BlockDispenseEvent event = new BlockDispenseEvent(block, (ItemStack)craftItem.clone(), new Vector(x, y, z));
/* 284 */             if (!BlockDispenser.eventFired) {
/* 285 */               world.getServer().getPluginManager().callEvent((Event)event);
/*     */             }
/*     */             
/* 288 */             if (event.isCancelled()) {
/* 289 */               return itemstack;
/*     */             }
/*     */             
/* 292 */             if (!event.getItem().equals(craftItem)) {
/*     */               
/* 294 */               ItemStack eventStack = CraftItemStack.asNMSCopy(event.getItem());
/* 295 */               IDispenseBehavior idispensebehavior = BlockDispenser.REGISTRY.get(eventStack.getItem());
/* 296 */               if (idispensebehavior != IDispenseBehavior.NONE && idispensebehavior != this) {
/* 297 */                 idispensebehavior.a(isourceblock, eventStack);
/* 298 */                 return itemstack;
/*     */               } 
/*     */             } 
/*     */             
/* 302 */             itembucket = (ItemBucket)CraftItemStack.asNMSCopy(event.getItem()).getItem();
/*     */           } 
/*     */ 
/*     */           
/* 306 */           if (itembucket.a((EntityHuman)null, isourceblock.getWorld(), blockposition)) {
/*     */             
/* 308 */             Item item = Items.BUCKET;
/* 309 */             itemstack.subtract(1);
/* 310 */             if (itemstack.isEmpty()) {
/* 311 */               itemstack.setItem(Items.BUCKET);
/* 312 */               itemstack.setCount(1);
/* 313 */             } else if (((TileEntityDispenser)isourceblock.<TileEntityDispenser>getTileEntity()).addItem(new ItemStack(item)) < 0) {
/* 314 */               this.b.a(isourceblock, new ItemStack(item));
/*     */             } 
/*     */             
/* 317 */             return itemstack;
/*     */           } 
/* 319 */           return this.b.a(isourceblock, itemstack);
/*     */         }
/*     */       };
/*     */ 
/*     */     
/* 324 */     BlockDispenser.REGISTRY.a(Items.LAVA_BUCKET, dispensebehavioritem);
/* 325 */     BlockDispenser.REGISTRY.a(Items.WATER_BUCKET, dispensebehavioritem);
/* 326 */     BlockDispenser.REGISTRY.a(Items.BUCKET, new DispenseBehaviorItem() {
/* 327 */           private final DispenseBehaviorItem b = new DispenseBehaviorItem();
/*     */           public ItemStack b(ISourceBlock isourceblock, ItemStack itemstack) {
/*     */             Item item;
/* 330 */             World world = isourceblock.getWorld();
/* 331 */             BlockPosition blockposition = isourceblock.getBlockPosition().shift(isourceblock.e().<EnumDirection>get(BlockDispenser.FACING));
/* 332 */             IBlockData iblockdata = world.getType(blockposition);
/* 333 */             Block block = iblockdata.getBlock();
/* 334 */             Material material = iblockdata.getMaterial();
/*     */ 
/*     */             
/* 337 */             if (Material.WATER.equals(material) && block instanceof BlockFluids && ((Integer)iblockdata.<Integer>get(BlockFluids.LEVEL)).intValue() == 0) {
/* 338 */               item = Items.WATER_BUCKET;
/*     */             } else {
/* 340 */               if (!Material.LAVA.equals(material) || !(block instanceof BlockFluids) || ((Integer)iblockdata.<Integer>get(BlockFluids.LEVEL)).intValue() != 0) {
/* 341 */                 return super.b(isourceblock, itemstack);
/*     */               }
/*     */               
/* 344 */               item = Items.LAVA_BUCKET;
/*     */             } 
/*     */ 
/*     */             
/* 348 */             Block bukkitBlock = world.getWorld().getBlockAt(isourceblock.getBlockPosition().getX(), isourceblock.getBlockPosition().getY(), isourceblock.getBlockPosition().getZ());
/* 349 */             CraftItemStack craftItem = CraftItemStack.asCraftMirror(itemstack);
/*     */             
/* 351 */             BlockDispenseEvent event = new BlockDispenseEvent(bukkitBlock, (ItemStack)craftItem.clone(), new Vector(blockposition.getX(), blockposition.getY(), blockposition.getZ()));
/* 352 */             if (!BlockDispenser.eventFired) {
/* 353 */               world.getServer().getPluginManager().callEvent((Event)event);
/*     */             }
/*     */             
/* 356 */             if (event.isCancelled()) {
/* 357 */               return itemstack;
/*     */             }
/*     */             
/* 360 */             if (!event.getItem().equals(craftItem)) {
/*     */               
/* 362 */               ItemStack eventStack = CraftItemStack.asNMSCopy(event.getItem());
/* 363 */               IDispenseBehavior idispensebehavior = BlockDispenser.REGISTRY.get(eventStack.getItem());
/* 364 */               if (idispensebehavior != IDispenseBehavior.NONE && idispensebehavior != this) {
/* 365 */                 idispensebehavior.a(isourceblock, eventStack);
/* 366 */                 return itemstack;
/*     */               } 
/*     */             } 
/*     */ 
/*     */             
/* 371 */             world.setAir(blockposition);
/* 372 */             itemstack.subtract(1);
/* 373 */             if (itemstack.isEmpty()) {
/* 374 */               return new ItemStack(item);
/*     */             }
/* 376 */             if (((TileEntityDispenser)isourceblock.<TileEntityDispenser>getTileEntity()).addItem(new ItemStack(item)) < 0) {
/* 377 */               this.b.a(isourceblock, new ItemStack(item));
/*     */             }
/*     */             
/* 380 */             return itemstack;
/*     */           }
/*     */         });
/*     */     
/* 384 */     BlockDispenser.REGISTRY.a(Items.FLINT_AND_STEEL, new b() {
/*     */           protected ItemStack b(ISourceBlock isourceblock, ItemStack itemstack) {
/* 386 */             World world = isourceblock.getWorld();
/*     */ 
/*     */             
/* 389 */             Block block = world.getWorld().getBlockAt(isourceblock.getBlockPosition().getX(), isourceblock.getBlockPosition().getY(), isourceblock.getBlockPosition().getZ());
/* 390 */             CraftItemStack craftItem = CraftItemStack.asCraftMirror(itemstack);
/*     */             
/* 392 */             BlockDispenseEvent event = new BlockDispenseEvent(block, (ItemStack)craftItem.clone(), new Vector(0, 0, 0));
/* 393 */             if (!BlockDispenser.eventFired) {
/* 394 */               world.getServer().getPluginManager().callEvent((Event)event);
/*     */             }
/*     */             
/* 397 */             if (event.isCancelled()) {
/* 398 */               return itemstack;
/*     */             }
/*     */             
/* 401 */             if (!event.getItem().equals(craftItem)) {
/*     */               
/* 403 */               ItemStack eventStack = CraftItemStack.asNMSCopy(event.getItem());
/* 404 */               IDispenseBehavior idispensebehavior = BlockDispenser.REGISTRY.get(eventStack.getItem());
/* 405 */               if (idispensebehavior != IDispenseBehavior.NONE && idispensebehavior != this) {
/* 406 */                 idispensebehavior.a(isourceblock, eventStack);
/* 407 */                 return itemstack;
/*     */               } 
/*     */             } 
/*     */ 
/*     */             
/* 412 */             this.b = true;
/* 413 */             BlockPosition blockposition = isourceblock.getBlockPosition().shift(isourceblock.e().<EnumDirection>get(BlockDispenser.FACING));
/*     */             
/* 415 */             if (world.isEmpty(blockposition)) {
/*     */               
/* 417 */               if (!CraftEventFactory.callBlockIgniteEvent(world, blockposition.getX(), blockposition.getY(), blockposition.getZ(), isourceblock.getBlockPosition().getX(), isourceblock.getBlockPosition().getY(), isourceblock.getBlockPosition().getZ()).isCancelled()) {
/* 418 */                 world.setTypeUpdate(blockposition, Blocks.FIRE.getBlockData());
/* 419 */                 if (itemstack.isDamaged(1, world.random, null)) {
/* 420 */                   itemstack.setCount(0);
/*     */                 }
/*     */               }
/*     */             
/* 424 */             } else if (world.getType(blockposition).getBlock() == Blocks.TNT) {
/* 425 */               Blocks.TNT.postBreak(world, blockposition, Blocks.TNT.getBlockData().set(BlockTNT.EXPLODE, Boolean.valueOf(true)));
/* 426 */               world.setAir(blockposition);
/*     */             } else {
/* 428 */               this.b = false;
/*     */             } 
/*     */             
/* 431 */             return itemstack;
/*     */           }
/*     */         });
/* 434 */     BlockDispenser.REGISTRY.a(Items.DYE, new b() {
/*     */           protected ItemStack b(ISourceBlock isourceblock, ItemStack itemstack) {
/* 436 */             this.b = true;
/* 437 */             if (EnumColor.WHITE == EnumColor.fromInvColorIndex(itemstack.getData())) {
/* 438 */               World world = isourceblock.getWorld();
/* 439 */               BlockPosition blockposition = isourceblock.getBlockPosition().shift(isourceblock.e().<EnumDirection>get(BlockDispenser.FACING));
/*     */ 
/*     */               
/* 442 */               Block block = world.getWorld().getBlockAt(isourceblock.getBlockPosition().getX(), isourceblock.getBlockPosition().getY(), isourceblock.getBlockPosition().getZ());
/* 443 */               CraftItemStack craftItem = CraftItemStack.asCraftMirror(itemstack);
/*     */               
/* 445 */               BlockDispenseEvent event = new BlockDispenseEvent(block, (ItemStack)craftItem.clone(), new Vector(0, 0, 0));
/* 446 */               if (!BlockDispenser.eventFired) {
/* 447 */                 world.getServer().getPluginManager().callEvent((Event)event);
/*     */               }
/*     */               
/* 450 */               if (event.isCancelled()) {
/* 451 */                 return itemstack;
/*     */               }
/*     */               
/* 454 */               if (!event.getItem().equals(craftItem)) {
/*     */                 
/* 456 */                 ItemStack eventStack = CraftItemStack.asNMSCopy(event.getItem());
/* 457 */                 IDispenseBehavior idispensebehavior = BlockDispenser.REGISTRY.get(eventStack.getItem());
/* 458 */                 if (idispensebehavior != IDispenseBehavior.NONE && idispensebehavior != this) {
/* 459 */                   idispensebehavior.a(isourceblock, eventStack);
/* 460 */                   return itemstack;
/*     */                 } 
/*     */               } 
/*     */               
/* 464 */               world.captureTreeGeneration = true;
/*     */ 
/*     */               
/* 467 */               if (ItemDye.a(itemstack, world, blockposition)) {
/* 468 */                 if (!world.isClientSide) {
/* 469 */                   world.triggerEffect(2005, blockposition, 0);
/*     */                 }
/*     */               } else {
/* 472 */                 this.b = false;
/*     */               } 
/*     */               
/* 475 */               world.captureTreeGeneration = false;
/* 476 */               if (world.capturedBlockStates.size() > 0) {
/* 477 */                 TreeType treeType = BlockSapling.treeType;
/* 478 */                 BlockSapling.treeType = null;
/* 479 */                 Location location = new Location((World)world.getWorld(), blockposition.getX(), blockposition.getY(), blockposition.getZ());
/* 480 */                 List<BlockState> blocks = (List<BlockState>)world.capturedBlockStates.clone();
/* 481 */                 world.capturedBlockStates.clear();
/* 482 */                 StructureGrowEvent structureEvent = null;
/* 483 */                 if (treeType != null) {
/* 484 */                   structureEvent = new StructureGrowEvent(location, treeType, false, null, blocks);
/* 485 */                   Bukkit.getPluginManager().callEvent((Event)structureEvent);
/*     */                 } 
/* 487 */                 if (structureEvent == null || !structureEvent.isCancelled()) {
/* 488 */                   for (BlockState blockstate : blocks) {
/* 489 */                     blockstate.update(true);
/*     */                   }
/*     */                 }
/*     */               } 
/*     */ 
/*     */               
/* 495 */               return itemstack;
/*     */             } 
/* 497 */             return super.b(isourceblock, itemstack);
/*     */           }
/*     */         });
/*     */     
/* 501 */     BlockDispenser.REGISTRY.a(Item.getItemOf(Blocks.TNT), new DispenseBehaviorItem() {
/*     */           protected ItemStack b(ISourceBlock isourceblock, ItemStack itemstack) {
/* 503 */             World world = isourceblock.getWorld();
/* 504 */             BlockPosition blockposition = isourceblock.getBlockPosition().shift(isourceblock.e().<EnumDirection>get(BlockDispenser.FACING));
/*     */ 
/*     */ 
/*     */             
/* 508 */             ItemStack itemstack1 = itemstack.cloneAndSubtract(1);
/* 509 */             Block block = world.getWorld().getBlockAt(isourceblock.getBlockPosition().getX(), isourceblock.getBlockPosition().getY(), isourceblock.getBlockPosition().getZ());
/* 510 */             CraftItemStack craftItem = CraftItemStack.asCraftMirror(itemstack1);
/*     */             
/* 512 */             BlockDispenseEvent event = new BlockDispenseEvent(block, (ItemStack)craftItem.clone(), new Vector(blockposition.getX() + 0.5D, blockposition.getY(), blockposition.getZ() + 0.5D));
/* 513 */             if (!BlockDispenser.eventFired) {
/* 514 */               world.getServer().getPluginManager().callEvent((Event)event);
/*     */             }
/*     */             
/* 517 */             if (event.isCancelled()) {
/* 518 */               itemstack.add(1);
/* 519 */               return itemstack;
/*     */             } 
/*     */             
/* 522 */             if (!event.getItem().equals(craftItem)) {
/* 523 */               itemstack.add(1);
/*     */               
/* 525 */               ItemStack eventStack = CraftItemStack.asNMSCopy(event.getItem());
/* 526 */               IDispenseBehavior idispensebehavior = BlockDispenser.REGISTRY.get(eventStack.getItem());
/* 527 */               if (idispensebehavior != IDispenseBehavior.NONE && idispensebehavior != this) {
/* 528 */                 idispensebehavior.a(isourceblock, eventStack);
/* 529 */                 return itemstack;
/*     */               } 
/*     */             } 
/*     */             
/* 533 */             EntityTNTPrimed entitytntprimed = new EntityTNTPrimed(world, event.getVelocity().getX(), event.getVelocity().getY(), event.getVelocity().getZ(), null);
/*     */ 
/*     */             
/* 536 */             world.addEntity(entitytntprimed);
/* 537 */             world.a((EntityHuman)null, entitytntprimed.locX, entitytntprimed.locY, entitytntprimed.locZ, SoundEffects.hW, SoundCategory.BLOCKS, 1.0F, 1.0F);
/*     */             
/* 539 */             return itemstack;
/*     */           }
/*     */         });
/* 542 */     BlockDispenser.REGISTRY.a(Items.SKULL, new b() {
/*     */           protected ItemStack b(ISourceBlock isourceblock, ItemStack itemstack) {
/* 544 */             World world = isourceblock.getWorld();
/* 545 */             EnumDirection enumdirection = isourceblock.e().<EnumDirection>get(BlockDispenser.FACING);
/* 546 */             BlockPosition blockposition = isourceblock.getBlockPosition().shift(enumdirection);
/* 547 */             BlockSkull blockskull = Blocks.SKULL;
/*     */ 
/*     */             
/* 550 */             Block bukkitBlock = world.getWorld().getBlockAt(isourceblock.getBlockPosition().getX(), isourceblock.getBlockPosition().getY(), isourceblock.getBlockPosition().getZ());
/* 551 */             CraftItemStack craftItem = CraftItemStack.asCraftMirror(itemstack);
/*     */             
/* 553 */             BlockDispenseEvent event = new BlockDispenseEvent(bukkitBlock, (ItemStack)craftItem.clone(), new Vector(blockposition.getX(), blockposition.getY(), blockposition.getZ()));
/* 554 */             if (!BlockDispenser.eventFired) {
/* 555 */               world.getServer().getPluginManager().callEvent((Event)event);
/*     */             }
/*     */             
/* 558 */             if (event.isCancelled()) {
/* 559 */               return itemstack;
/*     */             }
/*     */             
/* 562 */             if (!event.getItem().equals(craftItem)) {
/*     */               
/* 564 */               ItemStack eventStack = CraftItemStack.asNMSCopy(event.getItem());
/* 565 */               IDispenseBehavior idispensebehavior = BlockDispenser.REGISTRY.get(eventStack.getItem());
/* 566 */               if (idispensebehavior != IDispenseBehavior.NONE && idispensebehavior != this) {
/* 567 */                 idispensebehavior.a(isourceblock, eventStack);
/* 568 */                 return itemstack;
/*     */               } 
/*     */             } 
/*     */ 
/*     */             
/* 573 */             this.b = true;
/* 574 */             if (world.isEmpty(blockposition) && blockskull.b(world, blockposition, itemstack)) {
/* 575 */               if (!world.isClientSide) {
/* 576 */                 world.setTypeAndData(blockposition, blockskull.getBlockData().set(BlockSkull.FACING, EnumDirection.UP), 3);
/* 577 */                 TileEntity tileentity = world.getTileEntity(blockposition);
/*     */                 
/* 579 */                 if (tileentity instanceof TileEntitySkull) {
/* 580 */                   if (itemstack.getData() == 3) {
/* 581 */                     GameProfile gameprofile = null;
/*     */                     
/* 583 */                     if (itemstack.hasTag()) {
/* 584 */                       NBTTagCompound nbttagcompound = itemstack.getTag();
/*     */                       
/* 586 */                       if (nbttagcompound.hasKeyOfType("SkullOwner", 10)) {
/* 587 */                         gameprofile = GameProfileSerializer.deserialize(nbttagcompound.getCompound("SkullOwner"));
/* 588 */                       } else if (nbttagcompound.hasKeyOfType("SkullOwner", 8)) {
/* 589 */                         String s = nbttagcompound.getString("SkullOwner");
/*     */                         
/* 591 */                         if (!UtilColor.b(s)) {
/* 592 */                           gameprofile = new GameProfile(null, s);
/*     */                         }
/*     */                       } 
/*     */                     } 
/*     */                     
/* 597 */                     ((TileEntitySkull)tileentity).setGameProfile(gameprofile);
/*     */                   } else {
/* 599 */                     ((TileEntitySkull)tileentity).setSkullType(itemstack.getData());
/*     */                   } 
/*     */                   
/* 602 */                   ((TileEntitySkull)tileentity).setRotation(enumdirection.opposite().get2DRotationValue() * 4);
/* 603 */                   Blocks.SKULL.a(world, blockposition, (TileEntitySkull)tileentity);
/*     */                 } 
/*     */                 
/* 606 */                 itemstack.subtract(1);
/*     */               } 
/* 608 */             } else if (ItemArmor.a(isourceblock, itemstack).isEmpty()) {
/* 609 */               this.b = false;
/*     */             } 
/*     */             
/* 612 */             return itemstack;
/*     */           }
/*     */         });
/* 615 */     BlockDispenser.REGISTRY.a(Item.getItemOf(Blocks.PUMPKIN), new b() {
/*     */           protected ItemStack b(ISourceBlock isourceblock, ItemStack itemstack) {
/* 617 */             World world = isourceblock.getWorld();
/* 618 */             BlockPosition blockposition = isourceblock.getBlockPosition().shift(isourceblock.e().<EnumDirection>get(BlockDispenser.FACING));
/* 619 */             BlockPumpkin blockpumpkin = (BlockPumpkin)Blocks.PUMPKIN;
/*     */ 
/*     */             
/* 622 */             Block bukkitBlock = world.getWorld().getBlockAt(isourceblock.getBlockPosition().getX(), isourceblock.getBlockPosition().getY(), isourceblock.getBlockPosition().getZ());
/* 623 */             CraftItemStack craftItem = CraftItemStack.asCraftMirror(itemstack);
/*     */             
/* 625 */             BlockDispenseEvent event = new BlockDispenseEvent(bukkitBlock, (ItemStack)craftItem.clone(), new Vector(blockposition.getX(), blockposition.getY(), blockposition.getZ()));
/* 626 */             if (!BlockDispenser.eventFired) {
/* 627 */               world.getServer().getPluginManager().callEvent((Event)event);
/*     */             }
/*     */             
/* 630 */             if (event.isCancelled()) {
/* 631 */               return itemstack;
/*     */             }
/*     */             
/* 634 */             if (!event.getItem().equals(craftItem)) {
/*     */               
/* 636 */               ItemStack eventStack = CraftItemStack.asNMSCopy(event.getItem());
/* 637 */               IDispenseBehavior idispensebehavior = BlockDispenser.REGISTRY.get(eventStack.getItem());
/* 638 */               if (idispensebehavior != IDispenseBehavior.NONE && idispensebehavior != this) {
/* 639 */                 idispensebehavior.a(isourceblock, eventStack);
/* 640 */                 return itemstack;
/*     */               } 
/*     */             } 
/*     */ 
/*     */             
/* 645 */             this.b = true;
/* 646 */             if (world.isEmpty(blockposition) && blockpumpkin.b(world, blockposition)) {
/* 647 */               if (!world.isClientSide) {
/* 648 */                 world.setTypeAndData(blockposition, blockpumpkin.getBlockData(), 3);
/*     */               }
/*     */               
/* 651 */               itemstack.subtract(1);
/*     */             } else {
/* 653 */               ItemStack itemstack1 = ItemArmor.a(isourceblock, itemstack);
/*     */               
/* 655 */               if (itemstack1.isEmpty()) {
/* 656 */                 this.b = false;
/*     */               }
/*     */             } 
/*     */             
/* 660 */             return itemstack;
/*     */           }
/*     */         });
/* 663 */     EnumColor[] aenumcolor = EnumColor.values();
/* 664 */     int i = aenumcolor.length;
/*     */     
/* 666 */     for (int j = 0; j < i; j++) {
/* 667 */       EnumColor enumcolor = aenumcolor[j];
/*     */       
/* 669 */       BlockDispenser.REGISTRY.a(Item.getItemOf(BlockShulkerBox.a(enumcolor)), new c(null));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void c() {
/* 675 */     if (!c) {
/* 676 */       c = true;
/* 677 */       d();
/* 678 */       SoundEffect.b();
/* 679 */       Block.w();
/* 680 */       BlockFire.e();
/* 681 */       MobEffectList.k();
/* 682 */       Enchantment.g();
/* 683 */       Item.t();
/* 684 */       PotionRegistry.b();
/* 685 */       PotionBrewer.a();
/* 686 */       EntityTypes.c();
/* 687 */       BiomeBase.q();
/* 688 */       b();
/* 689 */       if (!CraftingManager.init()) {
/* 690 */         b = true;
/* 691 */         d.error("Errors with built-in recipes!");
/*     */       } 
/*     */       
/* 694 */       StatisticList.a();
/* 695 */       if (d.isDebugEnabled()) {
/* 696 */         if ((new AdvancementDataWorld(null)).b()) {
/* 697 */           b = true;
/* 698 */           d.error("Errors with built-in advancements!");
/*     */         } 
/*     */         
/* 701 */         if (!LootTables.b()) {
/* 702 */           b = true;
/* 703 */           d.error("Errors with built-in loot tables");
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static void d() {
/* 711 */     if (d.isDebugEnabled()) {
/* 712 */       System.setErr(new DebugOutputStream("STDERR", System.err));
/* 713 */       System.setOut(new DebugOutputStream("STDOUT", a));
/*     */     } else {
/* 715 */       System.setErr(new RedirectStream("STDERR", System.err));
/* 716 */       System.setOut(new RedirectStream("STDOUT", a));
/*     */     } 
/*     */   }
/*     */   
/*     */   static class c
/*     */     extends b
/*     */   {
/*     */     private c() {}
/*     */     
/*     */     protected ItemStack b(ISourceBlock isourceblock, ItemStack itemstack) {
/* 726 */       Block block = Block.asBlock(itemstack.getItem());
/* 727 */       World world = isourceblock.getWorld();
/* 728 */       EnumDirection enumdirection = isourceblock.e().<EnumDirection>get(BlockDispenser.FACING);
/* 729 */       BlockPosition blockposition = isourceblock.getBlockPosition().shift(enumdirection);
/*     */ 
/*     */       
/* 732 */       Block bukkitBlock = world.getWorld().getBlockAt(isourceblock.getBlockPosition().getX(), isourceblock.getBlockPosition().getY(), isourceblock.getBlockPosition().getZ());
/* 733 */       CraftItemStack craftItem = CraftItemStack.asCraftMirror(itemstack);
/*     */       
/* 735 */       BlockDispenseEvent event = new BlockDispenseEvent(bukkitBlock, (ItemStack)craftItem.clone(), new Vector(blockposition.getX(), blockposition.getY(), blockposition.getZ()));
/* 736 */       if (!BlockDispenser.eventFired) {
/* 737 */         world.getServer().getPluginManager().callEvent((Event)event);
/*     */       }
/*     */       
/* 740 */       if (event.isCancelled()) {
/* 741 */         return itemstack;
/*     */       }
/*     */       
/* 744 */       if (!event.getItem().equals(craftItem)) {
/*     */         
/* 746 */         ItemStack eventStack = CraftItemStack.asNMSCopy(event.getItem());
/* 747 */         IDispenseBehavior idispensebehavior = BlockDispenser.REGISTRY.get(eventStack.getItem());
/* 748 */         if (idispensebehavior != IDispenseBehavior.NONE && idispensebehavior != this) {
/* 749 */           idispensebehavior.a(isourceblock, eventStack);
/* 750 */           return itemstack;
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 755 */       this.b = world.a(block, blockposition, false, EnumDirection.DOWN, (Entity)null);
/* 756 */       if (this.b) {
/* 757 */         EnumDirection enumdirection1 = world.isEmpty(blockposition.down()) ? enumdirection : EnumDirection.UP;
/* 758 */         IBlockData iblockdata = block.getBlockData().set(BlockShulkerBox.a, enumdirection1);
/*     */         
/* 760 */         world.setTypeUpdate(blockposition, iblockdata);
/* 761 */         TileEntity tileentity = world.getTileEntity(blockposition);
/* 762 */         ItemStack itemstack1 = itemstack.cloneAndSubtract(1);
/*     */         
/* 764 */         if (itemstack1.hasTag()) {
/* 765 */           ((TileEntityShulkerBox)tileentity).e(itemstack1.getTag().getCompound("BlockEntityTag"));
/*     */         }
/*     */         
/* 768 */         if (itemstack1.hasName()) {
/* 769 */           ((TileEntityShulkerBox)tileentity).setCustomName(itemstack1.getName());
/*     */         }
/*     */         
/* 772 */         world.updateAdjacentComparators(blockposition, iblockdata.getBlock());
/*     */       } 
/*     */       
/* 775 */       return itemstack;
/*     */     }
/*     */     
/*     */     c(Object object) {
/* 779 */       this();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static abstract class b
/*     */     extends DispenseBehaviorItem
/*     */   {
/*     */     protected boolean b = true;
/*     */     
/*     */     protected void a(ISourceBlock isourceblock) {
/* 790 */       isourceblock.getWorld().triggerEffect(this.b ? 1000 : 1001, isourceblock.getBlockPosition(), 0);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class a
/*     */     extends DispenseBehaviorItem {
/* 796 */     private final DispenseBehaviorItem b = new DispenseBehaviorItem();
/*     */     private final EntityBoat.EnumBoatType c;
/*     */     
/*     */     public a(EntityBoat.EnumBoatType entityboat_enumboattype) {
/* 800 */       this.c = entityboat_enumboattype;
/*     */     }
/*     */     public ItemStack b(ISourceBlock isourceblock, ItemStack itemstack) {
/*     */       double d3;
/* 804 */       EnumDirection enumdirection = isourceblock.e().<EnumDirection>get(BlockDispenser.FACING);
/* 805 */       World world = isourceblock.getWorld();
/* 806 */       double d0 = isourceblock.getX() + (enumdirection.getAdjacentX() * 1.125F);
/* 807 */       double d1 = isourceblock.getY() + (enumdirection.getAdjacentY() * 1.125F);
/* 808 */       double d2 = isourceblock.getZ() + (enumdirection.getAdjacentZ() * 1.125F);
/* 809 */       BlockPosition blockposition = isourceblock.getBlockPosition().shift(enumdirection);
/* 810 */       Material material = world.getType(blockposition).getMaterial();
/*     */ 
/*     */       
/* 813 */       if (Material.WATER.equals(material)) {
/* 814 */         d3 = 1.0D;
/*     */       } else {
/* 816 */         if (!Material.AIR.equals(material) || !Material.WATER.equals(world.getType(blockposition.down()).getMaterial())) {
/* 817 */           return this.b.a(isourceblock, itemstack);
/*     */         }
/*     */         
/* 820 */         d3 = 0.0D;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 825 */       ItemStack itemstack1 = itemstack.cloneAndSubtract(1);
/* 826 */       Block block = world.getWorld().getBlockAt(isourceblock.getBlockPosition().getX(), isourceblock.getBlockPosition().getY(), isourceblock.getBlockPosition().getZ());
/* 827 */       CraftItemStack craftItem = CraftItemStack.asCraftMirror(itemstack1);
/*     */       
/* 829 */       BlockDispenseEvent event = new BlockDispenseEvent(block, (ItemStack)craftItem.clone(), new Vector(d0, d1 + d3, d2));
/* 830 */       if (!BlockDispenser.eventFired) {
/* 831 */         world.getServer().getPluginManager().callEvent((Event)event);
/*     */       }
/*     */       
/* 834 */       if (event.isCancelled()) {
/* 835 */         itemstack.add(1);
/* 836 */         return itemstack;
/*     */       } 
/*     */       
/* 839 */       if (!event.getItem().equals(craftItem)) {
/* 840 */         itemstack.add(1);
/*     */         
/* 842 */         ItemStack eventStack = CraftItemStack.asNMSCopy(event.getItem());
/* 843 */         IDispenseBehavior idispensebehavior = BlockDispenser.REGISTRY.get(eventStack.getItem());
/* 844 */         if (idispensebehavior != IDispenseBehavior.NONE && idispensebehavior != this) {
/* 845 */           idispensebehavior.a(isourceblock, eventStack);
/* 846 */           return itemstack;
/*     */         } 
/*     */       } 
/*     */       
/* 850 */       EntityBoat entityboat = new EntityBoat(world, event.getVelocity().getX(), event.getVelocity().getY(), event.getVelocity().getZ());
/*     */ 
/*     */       
/* 853 */       entityboat.setType(this.c);
/* 854 */       entityboat.yaw = enumdirection.l();
/* 855 */       if (!world.addEntity(entityboat)) itemstack.add(1);
/*     */       
/* 857 */       return itemstack;
/*     */     }
/*     */     
/*     */     protected void a(ISourceBlock isourceblock) {
/* 861 */       isourceblock.getWorld().triggerEffect(1000, isourceblock.getBlockPosition(), 0);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DispenserRegistry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */