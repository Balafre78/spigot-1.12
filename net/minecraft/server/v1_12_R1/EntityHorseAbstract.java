/*     */ package net.minecraft.server.v1_12_R1;
/*     */ import com.google.common.base.Optional;
/*     */ import com.google.common.base.Predicate;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*     */ import org.bukkit.event.entity.EntityRegainHealthEvent;
/*     */ import org.bukkit.event.entity.HorseJumpEvent;
/*     */ 
/*     */ public abstract class EntityHorseAbstract extends EntityAnimal implements IInventoryListener, IJumpable {
/*  13 */   private static final Predicate<Entity> bH = new Predicate() {
/*     */       public boolean a(@Nullable Entity entity) {
/*  15 */         return (entity instanceof EntityHorseAbstract && ((EntityHorseAbstract)entity).hasReproduced());
/*     */       }
/*     */       
/*     */       public boolean apply(@Nullable Object object) {
/*  19 */         return a((Entity)object);
/*     */       }
/*     */     };
/*  22 */   public static final IAttribute attributeJumpStrength = (new AttributeRanged(null, "horse.jumpStrength", 0.7D, 0.0D, 2.0D)).a("Jump Strength").a(true);
/*  23 */   private static final DataWatcherObject<Byte> bI = DataWatcher.a((Class)EntityHorseAbstract.class, DataWatcherRegistry.a);
/*  24 */   private static final DataWatcherObject<Optional<UUID>> bJ = DataWatcher.a((Class)EntityHorseAbstract.class, DataWatcherRegistry.m);
/*     */   private int bK;
/*     */   private int bL;
/*     */   private int bM;
/*     */   public int by;
/*     */   public int bz;
/*     */   protected boolean bB;
/*     */   public InventoryHorseChest inventoryChest;
/*     */   protected int bD;
/*     */   protected float jumpPower;
/*     */   private boolean canSlide;
/*     */   private float bO;
/*     */   private float bP;
/*     */   private float bQ;
/*     */   private float bR;
/*     */   private float bS;
/*     */   private float bT;
/*     */   protected boolean bF = true;
/*     */   protected int bG;
/*  43 */   public int maxDomestication = 100;
/*     */   
/*     */   public EntityHorseAbstract(World world) {
/*  46 */     super(world);
/*  47 */     setSize(1.3964844F, 1.6F);
/*  48 */     this.P = 1.0F;
/*  49 */     loadChest();
/*     */   }
/*     */   
/*     */   protected void r() {
/*  53 */     this.goalSelector.a(0, new PathfinderGoalFloat(this));
/*  54 */     this.goalSelector.a(1, new PathfinderGoalPanic(this, 1.2D));
/*  55 */     this.goalSelector.a(1, new PathfinderGoalTame(this, 1.2D));
/*  56 */     this.goalSelector.a(2, new PathfinderGoalBreed(this, 1.0D, (Class)EntityHorseAbstract.class));
/*  57 */     this.goalSelector.a(4, new PathfinderGoalFollowParent(this, 1.0D));
/*  58 */     this.goalSelector.a(6, new PathfinderGoalRandomStrollLand(this, 0.7D));
/*  59 */     this.goalSelector.a(7, new PathfinderGoalLookAtPlayer(this, (Class)EntityHuman.class, 6.0F));
/*  60 */     this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
/*     */   }
/*     */   
/*     */   protected void i() {
/*  64 */     super.i();
/*  65 */     this.datawatcher.register(bI, Byte.valueOf((byte)0));
/*  66 */     this.datawatcher.register(bJ, Optional.absent());
/*     */   }
/*     */   
/*     */   protected boolean g(int i) {
/*  70 */     return ((((Byte)this.datawatcher.<Byte>get(bI)).byteValue() & i) != 0);
/*     */   }
/*     */   
/*     */   protected void c(int i, boolean flag) {
/*  74 */     byte b0 = ((Byte)this.datawatcher.<Byte>get(bI)).byteValue();
/*     */     
/*  76 */     if (flag) {
/*  77 */       this.datawatcher.set(bI, Byte.valueOf((byte)(b0 | i)));
/*     */     } else {
/*  79 */       this.datawatcher.set(bI, Byte.valueOf((byte)(b0 & (i ^ 0xFFFFFFFF))));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTamed() {
/*  85 */     return g(2);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public UUID getOwnerUUID() {
/*  90 */     return (UUID)((Optional)this.datawatcher.<Optional>get((DataWatcherObject)bJ)).orNull();
/*     */   }
/*     */   
/*     */   public void setOwnerUUID(@Nullable UUID uuid) {
/*  94 */     this.datawatcher.set(bJ, Optional.fromNullable(uuid));
/*     */   }
/*     */   
/*     */   public float dw() {
/*  98 */     return 0.5F;
/*     */   }
/*     */   
/*     */   public void a(boolean flag) {
/* 102 */     a(flag ? dw() : 1.0F);
/*     */   }
/*     */   
/*     */   public boolean dx() {
/* 106 */     return this.bB;
/*     */   }
/*     */   
/*     */   public void setTamed(boolean flag) {
/* 110 */     c(2, flag);
/*     */   }
/*     */   
/*     */   public void s(boolean flag) {
/* 114 */     this.bB = flag;
/*     */   }
/*     */   
/*     */   public boolean a(EntityHuman entityhuman) {
/* 118 */     return (super.a(entityhuman) && getMonsterType() != EnumMonsterType.UNDEAD);
/*     */   }
/*     */   
/*     */   protected void q(float f) {
/* 122 */     if (f > 6.0F && dy()) {
/* 123 */       v(false);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean dy() {
/* 129 */     return g(16);
/*     */   }
/*     */   
/*     */   public boolean dz() {
/* 133 */     return g(32);
/*     */   }
/*     */   
/*     */   public boolean hasReproduced() {
/* 137 */     return g(8);
/*     */   }
/*     */   
/*     */   public void t(boolean flag) {
/* 141 */     c(8, flag);
/*     */   }
/*     */   
/*     */   public void u(boolean flag) {
/* 145 */     c(4, flag);
/*     */   }
/*     */   
/*     */   public int getTemper() {
/* 149 */     return this.bD;
/*     */   }
/*     */   
/*     */   public void setTemper(int i) {
/* 153 */     this.bD = i;
/*     */   }
/*     */   
/*     */   public int n(int i) {
/* 157 */     int j = MathHelper.clamp(getTemper() + i, 0, getMaxDomestication());
/*     */     
/* 159 */     setTemper(j);
/* 160 */     return j;
/*     */   }
/*     */   
/*     */   public boolean damageEntity(DamageSource damagesource, float f) {
/* 164 */     Entity entity = damagesource.getEntity();
/*     */     
/* 166 */     return (isVehicle() && entity != null && y(entity)) ? false : super.damageEntity(damagesource, f);
/*     */   }
/*     */   
/*     */   public boolean isCollidable() {
/* 170 */     return !isVehicle();
/*     */   }
/*     */   
/*     */   private void dl() {
/* 174 */     dp();
/* 175 */     if (!isSilent()) {
/* 176 */       this.world.a((EntityHuman)null, this.locX, this.locY, this.locZ, SoundEffects.cK, bK(), 1.0F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void e(float f, float f1) {
/* 182 */     if (f > 1.0F) {
/* 183 */       a(SoundEffects.cO, 0.4F, 1.0F);
/*     */     }
/*     */     
/* 186 */     int i = MathHelper.f((f * 0.5F - 3.0F) * f1);
/*     */     
/* 188 */     if (i > 0) {
/* 189 */       damageEntity(DamageSource.FALL, i);
/* 190 */       if (isVehicle()) {
/* 191 */         Iterator<Entity> iterator = bG().iterator();
/*     */         
/* 193 */         while (iterator.hasNext()) {
/* 194 */           Entity entity = iterator.next();
/*     */           
/* 196 */           entity.damageEntity(DamageSource.FALL, i);
/*     */         } 
/*     */       } 
/*     */       
/* 200 */       IBlockData iblockdata = this.world.getType(new BlockPosition(this.locX, this.locY - 0.2D - this.lastYaw, this.locZ));
/* 201 */       Block block = iblockdata.getBlock();
/*     */       
/* 203 */       if (iblockdata.getMaterial() != Material.AIR && !isSilent()) {
/* 204 */         SoundEffectType soundeffecttype = block.getStepSound();
/*     */         
/* 206 */         this.world.a((EntityHuman)null, this.locX, this.locY, this.locZ, soundeffecttype.d(), bK(), soundeffecttype.a() * 0.5F, soundeffecttype.b() * 0.75F);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected int dn() {
/* 213 */     return 2;
/*     */   }
/*     */   
/*     */   public void loadChest() {
/* 217 */     InventoryHorseChest inventoryhorsechest = this.inventoryChest;
/*     */     
/* 219 */     this.inventoryChest = new InventoryHorseChest("HorseChest", dn(), this);
/* 220 */     this.inventoryChest.a(getName());
/* 221 */     if (inventoryhorsechest != null) {
/* 222 */       inventoryhorsechest.b(this);
/* 223 */       int i = Math.min(inventoryhorsechest.getSize(), this.inventoryChest.getSize());
/*     */       
/* 225 */       for (int j = 0; j < i; j++) {
/* 226 */         ItemStack itemstack = inventoryhorsechest.getItem(j);
/*     */         
/* 228 */         if (!itemstack.isEmpty()) {
/* 229 */           this.inventoryChest.setItem(j, itemstack.cloneItemStack());
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 234 */     this.inventoryChest.a(this);
/* 235 */     dD();
/*     */   }
/*     */   
/*     */   protected void dD() {
/* 239 */     if (!this.world.isClientSide) {
/* 240 */       u((!this.inventoryChest.getItem(0).isEmpty() && dF()));
/*     */     }
/*     */   }
/*     */   
/*     */   public void a(IInventory iinventory) {
/* 245 */     boolean flag = dG();
/*     */     
/* 247 */     dD();
/* 248 */     if (this.ticksLived > 20 && !flag && dG()) {
/* 249 */       a(SoundEffects.cP, 0.5F, 1.0F);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   protected EntityHorseAbstract a(Entity entity, double d0) {
/* 256 */     double d1 = Double.MAX_VALUE;
/* 257 */     Entity entity1 = null;
/* 258 */     List<Entity> list = this.world.getEntities(entity, entity.getBoundingBox().b(d0, d0, d0), bH);
/* 259 */     Iterator<Entity> iterator = list.iterator();
/*     */     
/* 261 */     while (iterator.hasNext()) {
/* 262 */       Entity entity2 = iterator.next();
/* 263 */       double d2 = entity2.d(entity.locX, entity.locY, entity.locZ);
/*     */       
/* 265 */       if (d2 < d1) {
/* 266 */         entity1 = entity2;
/* 267 */         d1 = d2;
/*     */       } 
/*     */     } 
/*     */     
/* 271 */     return (EntityHorseAbstract)entity1;
/*     */   }
/*     */   
/*     */   public double getJumpStrength() {
/* 275 */     return getAttributeInstance(attributeJumpStrength).getValue();
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   protected SoundEffect cf() {
/* 280 */     dp();
/* 281 */     return null;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   protected SoundEffect d(DamageSource damagesource) {
/* 286 */     dp();
/* 287 */     if (this.random.nextInt(3) == 0) {
/* 288 */       dt();
/*     */     }
/*     */     
/* 291 */     return null;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   protected SoundEffect F() {
/* 296 */     dp();
/* 297 */     if (this.random.nextInt(10) == 0 && !isFrozen()) {
/* 298 */       dt();
/*     */     }
/*     */     
/* 301 */     return null;
/*     */   }
/*     */   
/*     */   public boolean dF() {
/* 305 */     return true;
/*     */   }
/*     */   
/*     */   public boolean dG() {
/* 309 */     return g(4);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   protected SoundEffect do_() {
/* 314 */     dp();
/* 315 */     dt();
/* 316 */     return null;
/*     */   }
/*     */   
/*     */   protected void a(BlockPosition blockposition, Block block) {
/* 320 */     if (!block.getBlockData().getMaterial().isLiquid()) {
/* 321 */       SoundEffectType soundeffecttype = block.getStepSound();
/*     */       
/* 323 */       if (this.world.getType(blockposition.up()).getBlock() == Blocks.SNOW_LAYER) {
/* 324 */         soundeffecttype = Blocks.SNOW_LAYER.getStepSound();
/*     */       }
/*     */       
/* 327 */       if (isVehicle() && this.bF) {
/* 328 */         this.bG++;
/* 329 */         if (this.bG > 5 && this.bG % 3 == 0) {
/* 330 */           a(soundeffecttype);
/* 331 */         } else if (this.bG <= 5) {
/* 332 */           a(SoundEffects.cR, soundeffecttype.a() * 0.15F, soundeffecttype.b());
/*     */         } 
/* 334 */       } else if (soundeffecttype == SoundEffectType.a) {
/* 335 */         a(SoundEffects.cR, soundeffecttype.a() * 0.15F, soundeffecttype.b());
/*     */       } else {
/* 337 */         a(SoundEffects.cQ, soundeffecttype.a() * 0.15F, soundeffecttype.b());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(SoundEffectType soundeffecttype) {
/* 344 */     a(SoundEffects.cL, soundeffecttype.a() * 0.15F, soundeffecttype.b());
/*     */   }
/*     */   
/*     */   protected void initAttributes() {
/* 348 */     super.initAttributes();
/* 349 */     getAttributeMap().b(attributeJumpStrength);
/* 350 */     getAttributeInstance(GenericAttributes.maxHealth).setValue(53.0D);
/* 351 */     getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.22499999403953552D);
/*     */   }
/*     */   
/*     */   public int cU() {
/* 355 */     return 6;
/*     */   }
/*     */   
/*     */   public int getMaxDomestication() {
/* 359 */     return this.maxDomestication;
/*     */   }
/*     */   
/*     */   protected float cq() {
/* 363 */     return 0.8F;
/*     */   }
/*     */   
/*     */   public int C() {
/* 367 */     return 400;
/*     */   }
/*     */   
/*     */   public void c(EntityHuman entityhuman) {
/* 371 */     if (!this.world.isClientSide && (!isVehicle() || w(entityhuman)) && isTamed()) {
/* 372 */       this.inventoryChest.a(getName());
/* 373 */       entityhuman.openHorseInventory(this, this.inventoryChest);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean b(EntityHuman entityhuman, ItemStack itemstack) {
/* 379 */     boolean flag = false;
/* 380 */     float f = 0.0F;
/* 381 */     short short0 = 0;
/* 382 */     byte b0 = 0;
/* 383 */     Item item = itemstack.getItem();
/*     */     
/* 385 */     if (item == Items.WHEAT) {
/* 386 */       f = 2.0F;
/* 387 */       short0 = 20;
/* 388 */       b0 = 3;
/* 389 */     } else if (item == Items.SUGAR) {
/* 390 */       f = 1.0F;
/* 391 */       short0 = 30;
/* 392 */       b0 = 3;
/* 393 */     } else if (item == Item.getItemOf(Blocks.HAY_BLOCK)) {
/* 394 */       f = 20.0F;
/* 395 */       short0 = 180;
/* 396 */     } else if (item == Items.APPLE) {
/* 397 */       f = 3.0F;
/* 398 */       short0 = 60;
/* 399 */       b0 = 3;
/* 400 */     } else if (item == Items.GOLDEN_CARROT) {
/* 401 */       f = 4.0F;
/* 402 */       short0 = 60;
/* 403 */       b0 = 5;
/* 404 */       if (isTamed() && getAge() == 0 && !isInLove()) {
/* 405 */         flag = true;
/* 406 */         f(entityhuman);
/*     */       } 
/* 408 */     } else if (item == Items.GOLDEN_APPLE) {
/* 409 */       f = 10.0F;
/* 410 */       short0 = 240;
/* 411 */       b0 = 10;
/* 412 */       if (isTamed() && getAge() == 0 && !isInLove()) {
/* 413 */         flag = true;
/* 414 */         f(entityhuman);
/*     */       } 
/*     */     } 
/*     */     
/* 418 */     if (getHealth() < getMaxHealth() && f > 0.0F) {
/* 419 */       heal(f, EntityRegainHealthEvent.RegainReason.EATING);
/* 420 */       flag = true;
/*     */     } 
/*     */     
/* 423 */     if (isBaby() && short0 > 0) {
/* 424 */       this.world.addParticle(EnumParticle.VILLAGER_HAPPY, this.locX + (this.random.nextFloat() * this.width * 2.0F) - this.width, this.locY + 0.5D + (this.random.nextFloat() * this.length), this.locZ + (this.random.nextFloat() * this.width * 2.0F) - this.width, 0.0D, 0.0D, 0.0D, new int[0]);
/* 425 */       if (!this.world.isClientSide) {
/* 426 */         setAge(short0);
/*     */       }
/*     */       
/* 429 */       flag = true;
/*     */     } 
/*     */     
/* 432 */     if (b0 > 0 && (flag || !isTamed()) && getTemper() < getMaxDomestication()) {
/* 433 */       flag = true;
/* 434 */       if (!this.world.isClientSide) {
/* 435 */         n(b0);
/*     */       }
/*     */     } 
/*     */     
/* 439 */     if (flag) {
/* 440 */       dl();
/*     */     }
/*     */     
/* 443 */     return flag;
/*     */   }
/*     */   
/*     */   protected void g(EntityHuman entityhuman) {
/* 447 */     entityhuman.yaw = this.yaw;
/* 448 */     entityhuman.pitch = this.pitch;
/* 449 */     v(false);
/* 450 */     setStanding(false);
/* 451 */     if (!this.world.isClientSide) {
/* 452 */       entityhuman.startRiding(this);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isFrozen() {
/* 458 */     return !((!super.isFrozen() || !isVehicle() || !dG()) && !dy() && !dz());
/*     */   }
/*     */   
/*     */   public boolean e(ItemStack itemstack) {
/* 462 */     return false;
/*     */   }
/*     */   
/*     */   private void dm() {
/* 466 */     this.by = 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void die(DamageSource damagesource) {
/* 471 */     if (!this.world.isClientSide && this.inventoryChest != null) {
/* 472 */       for (int i = 0; i < this.inventoryChest.getSize(); i++) {
/* 473 */         ItemStack itemstack = this.inventoryChest.getItem(i);
/*     */         
/* 475 */         if (!itemstack.isEmpty()) {
/* 476 */           a(itemstack, 0.0F);
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/* 481 */     super.die(damagesource);
/*     */   }
/*     */   
/*     */   public void n() {
/* 485 */     if (this.random.nextInt(200) == 0) {
/* 486 */       dm();
/*     */     }
/*     */     
/* 489 */     super.n();
/* 490 */     if (!this.world.isClientSide) {
/* 491 */       if (this.random.nextInt(900) == 0 && this.deathTicks == 0) {
/* 492 */         heal(1.0F, EntityRegainHealthEvent.RegainReason.REGEN);
/*     */       }
/*     */       
/* 495 */       if (dJ()) {
/* 496 */         if (!dy() && !isVehicle() && this.random.nextInt(300) == 0 && this.world.getType(new BlockPosition(MathHelper.floor(this.locX), MathHelper.floor(this.locY) - 1, MathHelper.floor(this.locZ))).getBlock() == Blocks.GRASS) {
/* 497 */           v(true);
/*     */         }
/*     */         
/* 500 */         if (dy() && ++this.bK > 50) {
/* 501 */           this.bK = 0;
/* 502 */           v(false);
/*     */         } 
/*     */       } 
/*     */       
/* 506 */       dI();
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void dI() {
/* 511 */     if (hasReproduced() && isBaby() && !dy()) {
/* 512 */       EntityHorseAbstract entityhorseabstract = a(this, 16.0D);
/*     */       
/* 514 */       if (entityhorseabstract != null && h(entityhorseabstract) > 4.0D) {
/* 515 */         this.navigation.a(entityhorseabstract);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean dJ() {
/* 522 */     return true;
/*     */   }
/*     */   
/*     */   public void B_() {
/* 526 */     super.B_();
/* 527 */     if (this.bL > 0 && ++this.bL > 30) {
/* 528 */       this.bL = 0;
/* 529 */       c(64, false);
/*     */     } 
/*     */     
/* 532 */     if (bI() && this.bM > 0 && ++this.bM > 20) {
/* 533 */       this.bM = 0;
/* 534 */       setStanding(false);
/*     */     } 
/*     */     
/* 537 */     if (this.by > 0 && ++this.by > 8) {
/* 538 */       this.by = 0;
/*     */     }
/*     */     
/* 541 */     if (this.bz > 0) {
/* 542 */       this.bz++;
/* 543 */       if (this.bz > 300) {
/* 544 */         this.bz = 0;
/*     */       }
/*     */     } 
/*     */     
/* 548 */     this.bP = this.bO;
/* 549 */     if (dy()) {
/* 550 */       this.bO += (1.0F - this.bO) * 0.4F + 0.05F;
/* 551 */       if (this.bO > 1.0F) {
/* 552 */         this.bO = 1.0F;
/*     */       }
/*     */     } else {
/* 555 */       this.bO += (0.0F - this.bO) * 0.4F - 0.05F;
/* 556 */       if (this.bO < 0.0F) {
/* 557 */         this.bO = 0.0F;
/*     */       }
/*     */     } 
/*     */     
/* 561 */     this.bR = this.bQ;
/* 562 */     if (dz()) {
/* 563 */       this.bO = 0.0F;
/* 564 */       this.bP = this.bO;
/* 565 */       this.bQ += (1.0F - this.bQ) * 0.4F + 0.05F;
/* 566 */       if (this.bQ > 1.0F) {
/* 567 */         this.bQ = 1.0F;
/*     */       }
/*     */     } else {
/* 570 */       this.canSlide = false;
/* 571 */       this.bQ += (0.8F * this.bQ * this.bQ * this.bQ - this.bQ) * 0.6F - 0.05F;
/* 572 */       if (this.bQ < 0.0F) {
/* 573 */         this.bQ = 0.0F;
/*     */       }
/*     */     } 
/*     */     
/* 577 */     this.bT = this.bS;
/* 578 */     if (g(64)) {
/* 579 */       this.bS += (1.0F - this.bS) * 0.7F + 0.05F;
/* 580 */       if (this.bS > 1.0F) {
/* 581 */         this.bS = 1.0F;
/*     */       }
/*     */     } else {
/* 584 */       this.bS += (0.0F - this.bS) * 0.7F - 0.05F;
/* 585 */       if (this.bS < 0.0F) {
/* 586 */         this.bS = 0.0F;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void dp() {
/* 593 */     if (!this.world.isClientSide) {
/* 594 */       this.bL = 1;
/* 595 */       c(64, true);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void v(boolean flag) {
/* 601 */     c(16, flag);
/*     */   }
/*     */   
/*     */   public void setStanding(boolean flag) {
/* 605 */     if (flag) {
/* 606 */       v(false);
/*     */     }
/*     */     
/* 609 */     c(32, flag);
/*     */   }
/*     */   
/*     */   private void dt() {
/* 613 */     if (bI()) {
/* 614 */       this.bM = 1;
/* 615 */       setStanding(true);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void dK() {
/* 621 */     dt();
/* 622 */     SoundEffect soundeffect = do_();
/*     */     
/* 624 */     if (soundeffect != null) {
/* 625 */       a(soundeffect, cq(), cr());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean h(EntityHuman entityhuman) {
/* 631 */     setOwnerUUID(entityhuman.getUniqueID());
/* 632 */     setTamed(true);
/* 633 */     if (entityhuman instanceof EntityPlayer) {
/* 634 */       CriterionTriggers.w.a((EntityPlayer)entityhuman, this);
/*     */     }
/*     */     
/* 637 */     this.world.broadcastEntityEffect(this, (byte)7);
/* 638 */     return true;
/*     */   }
/*     */   
/*     */   public void a(float f, float f1, float f2) {
/* 642 */     if (isVehicle() && cV() && dG()) {
/* 643 */       EntityLiving entityliving = (EntityLiving)bE();
/*     */       
/* 645 */       this.yaw = entityliving.yaw;
/* 646 */       this.lastYaw = this.yaw;
/* 647 */       this.pitch = entityliving.pitch * 0.5F;
/* 648 */       setYawPitch(this.yaw, this.pitch);
/* 649 */       this.aN = this.yaw;
/* 650 */       this.aP = this.aN;
/* 651 */       f = entityliving.be * 0.5F;
/* 652 */       f2 = entityliving.bg;
/* 653 */       if (f2 <= 0.0F) {
/* 654 */         f2 *= 0.25F;
/* 655 */         this.bG = 0;
/*     */       } 
/*     */       
/* 658 */       if (this.onGround && this.jumpPower == 0.0F && dz() && !this.canSlide) {
/* 659 */         f = 0.0F;
/* 660 */         f2 = 0.0F;
/*     */       } 
/*     */       
/* 663 */       if (this.jumpPower > 0.0F && !dx() && this.onGround) {
/* 664 */         this.motY = getJumpStrength() * this.jumpPower;
/* 665 */         if (hasEffect(MobEffects.JUMP)) {
/* 666 */           this.motY += ((getEffect(MobEffects.JUMP).getAmplifier() + 1) * 0.1F);
/*     */         }
/*     */         
/* 669 */         s(true);
/* 670 */         this.impulse = true;
/* 671 */         if (f2 > 0.0F) {
/* 672 */           float f3 = MathHelper.sin(this.yaw * 0.017453292F);
/* 673 */           float f4 = MathHelper.cos(this.yaw * 0.017453292F);
/*     */           
/* 675 */           this.motX += (-0.4F * f3 * this.jumpPower);
/* 676 */           this.motZ += (0.4F * f4 * this.jumpPower);
/* 677 */           a(SoundEffects.cN, 0.4F, 1.0F);
/*     */         } 
/*     */         
/* 680 */         this.jumpPower = 0.0F;
/*     */       } 
/*     */       
/* 683 */       this.aR = cy() * 0.1F;
/* 684 */       if (bI()) {
/* 685 */         k((float)getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).getValue());
/* 686 */         super.a(f, f1, f2);
/* 687 */       } else if (entityliving instanceof EntityHuman) {
/* 688 */         this.motX = 0.0D;
/* 689 */         this.motY = 0.0D;
/* 690 */         this.motZ = 0.0D;
/*     */       } 
/*     */       
/* 693 */       if (this.onGround) {
/* 694 */         this.jumpPower = 0.0F;
/* 695 */         s(false);
/*     */       } 
/*     */       
/* 698 */       this.aF = this.aG;
/* 699 */       double d0 = this.locX - this.lastX;
/* 700 */       double d1 = this.locZ - this.lastZ;
/* 701 */       float f5 = MathHelper.sqrt(d0 * d0 + d1 * d1) * 4.0F;
/*     */       
/* 703 */       if (f5 > 1.0F) {
/* 704 */         f5 = 1.0F;
/*     */       }
/*     */       
/* 707 */       this.aG += (f5 - this.aG) * 0.4F;
/* 708 */       this.aH += this.aG;
/*     */     } else {
/* 710 */       this.aR = 0.02F;
/* 711 */       super.a(f, f1, f2);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void c(DataConverterManager dataconvertermanager, Class<?> oclass) {
/* 716 */     EntityInsentient.a(dataconvertermanager, oclass);
/* 717 */     dataconvertermanager.a(DataConverterTypes.ENTITY, new DataInspectorItem(oclass, new String[] { "SaddleItem" }));
/*     */   }
/*     */   
/*     */   public void b(NBTTagCompound nbttagcompound) {
/* 721 */     super.b(nbttagcompound);
/* 722 */     nbttagcompound.setBoolean("EatingHaystack", dy());
/* 723 */     nbttagcompound.setBoolean("Bred", hasReproduced());
/* 724 */     nbttagcompound.setInt("Temper", getTemper());
/* 725 */     nbttagcompound.setBoolean("Tame", isTamed());
/* 726 */     if (getOwnerUUID() != null) {
/* 727 */       nbttagcompound.setString("OwnerUUID", getOwnerUUID().toString());
/*     */     }
/* 729 */     nbttagcompound.setInt("Bukkit.MaxDomestication", this.maxDomestication);
/*     */     
/* 731 */     if (!this.inventoryChest.getItem(0).isEmpty()) {
/* 732 */       nbttagcompound.set("SaddleItem", this.inventoryChest.getItem(0).save(new NBTTagCompound()));
/*     */     }
/*     */   }
/*     */   
/*     */   public void a(NBTTagCompound nbttagcompound) {
/*     */     String s;
/* 738 */     super.a(nbttagcompound);
/* 739 */     v(nbttagcompound.getBoolean("EatingHaystack"));
/* 740 */     t(nbttagcompound.getBoolean("Bred"));
/* 741 */     setTemper(nbttagcompound.getInt("Temper"));
/* 742 */     setTamed(nbttagcompound.getBoolean("Tame"));
/*     */ 
/*     */     
/* 745 */     if (nbttagcompound.hasKeyOfType("OwnerUUID", 8)) {
/* 746 */       s = nbttagcompound.getString("OwnerUUID");
/*     */     } else {
/* 748 */       String s1 = nbttagcompound.getString("Owner");
/*     */       
/* 750 */       s = NameReferencingFileConverter.a(C_(), s1);
/*     */     } 
/*     */     
/* 753 */     if (!s.isEmpty()) {
/* 754 */       setOwnerUUID(UUID.fromString(s));
/*     */     }
/*     */     
/* 757 */     if (nbttagcompound.hasKey("Bukkit.MaxDomestication")) {
/* 758 */       this.maxDomestication = nbttagcompound.getInt("Bukkit.MaxDomestication");
/*     */     }
/*     */ 
/*     */     
/* 762 */     AttributeInstance attributeinstance = getAttributeMap().a("Speed");
/*     */     
/* 764 */     if (attributeinstance != null) {
/* 765 */       getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(attributeinstance.b() * 0.25D);
/*     */     }
/*     */     
/* 768 */     if (nbttagcompound.hasKeyOfType("SaddleItem", 10)) {
/* 769 */       ItemStack itemstack = new ItemStack(nbttagcompound.getCompound("SaddleItem"));
/*     */       
/* 771 */       if (itemstack.getItem() == Items.SADDLE) {
/* 772 */         this.inventoryChest.setItem(0, itemstack);
/*     */       }
/*     */     } 
/*     */     
/* 776 */     dD();
/*     */   }
/*     */   
/*     */   public boolean mate(EntityAnimal entityanimal) {
/* 780 */     return false;
/*     */   }
/*     */   
/*     */   protected boolean dL() {
/* 784 */     return (!isVehicle() && !isPassenger() && isTamed() && !isBaby() && getHealth() >= getMaxHealth() && isInLove());
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public EntityAgeable createChild(EntityAgeable entityageable) {
/* 789 */     return null;
/*     */   }
/*     */   
/*     */   protected void a(EntityAgeable entityageable, EntityHorseAbstract entityhorseabstract) {
/* 793 */     double d0 = getAttributeInstance(GenericAttributes.maxHealth).b() + entityageable.getAttributeInstance(GenericAttributes.maxHealth).b() + dM();
/*     */     
/* 795 */     entityhorseabstract.getAttributeInstance(GenericAttributes.maxHealth).setValue(d0 / 3.0D);
/* 796 */     double d1 = getAttributeInstance(attributeJumpStrength).b() + entityageable.getAttributeInstance(attributeJumpStrength).b() + dN();
/*     */     
/* 798 */     entityhorseabstract.getAttributeInstance(attributeJumpStrength).setValue(d1 / 3.0D);
/* 799 */     double d2 = getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).b() + entityageable.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).b() + dO();
/*     */     
/* 801 */     entityhorseabstract.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(d2 / 3.0D);
/*     */   }
/*     */   
/*     */   public boolean cV() {
/* 805 */     return bE() instanceof EntityLiving;
/*     */   }
/*     */   
/*     */   public boolean a() {
/* 809 */     return dG();
/*     */   }
/*     */ 
/*     */   
/*     */   public void b_(int i) {
/*     */     float power;
/* 815 */     if (i >= 90) {
/* 816 */       power = 1.0F;
/*     */     } else {
/* 818 */       power = 0.4F + 0.4F * i / 90.0F;
/*     */     } 
/* 820 */     HorseJumpEvent event = CraftEventFactory.callHorseJumpEvent(this, power);
/* 821 */     if (event.isCancelled()) {
/*     */       return;
/*     */     }
/*     */     
/* 825 */     this.canSlide = true;
/* 826 */     dt();
/*     */   }
/*     */   
/*     */   public void r_() {}
/*     */   
/*     */   public void k(Entity entity) {
/* 832 */     super.k(entity);
/* 833 */     if (entity instanceof EntityInsentient) {
/* 834 */       EntityInsentient entityinsentient = (EntityInsentient)entity;
/*     */       
/* 836 */       this.aN = entityinsentient.aN;
/*     */     } 
/*     */     
/* 839 */     if (this.bR > 0.0F) {
/* 840 */       float f = MathHelper.sin(this.aN * 0.017453292F);
/* 841 */       float f1 = MathHelper.cos(this.aN * 0.017453292F);
/* 842 */       float f2 = 0.7F * this.bR;
/* 843 */       float f3 = 0.15F * this.bR;
/*     */       
/* 845 */       entity.setPosition(this.locX + (f2 * f), this.locY + aG() + entity.aF() + f3, this.locZ - (f2 * f1));
/* 846 */       if (entity instanceof EntityLiving) {
/* 847 */         ((EntityLiving)entity).aN = this.aN;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected float dM() {
/* 854 */     return 15.0F + this.random.nextInt(8) + this.random.nextInt(9);
/*     */   }
/*     */   
/*     */   protected double dN() {
/* 858 */     return 0.4000000059604645D + this.random.nextDouble() * 0.2D + this.random.nextDouble() * 0.2D + this.random.nextDouble() * 0.2D;
/*     */   }
/*     */   
/*     */   protected double dO() {
/* 862 */     return (0.44999998807907104D + this.random.nextDouble() * 0.3D + this.random.nextDouble() * 0.3D + this.random.nextDouble() * 0.3D) * 0.25D;
/*     */   }
/*     */   
/*     */   public boolean m_() {
/* 866 */     return false;
/*     */   }
/*     */   
/*     */   public float getHeadHeight() {
/* 870 */     return this.length;
/*     */   }
/*     */   
/*     */   public boolean dP() {
/* 874 */     return false;
/*     */   }
/*     */   
/*     */   public boolean f(ItemStack itemstack) {
/* 878 */     return false;
/*     */   }
/*     */   
/*     */   public boolean c(int i, ItemStack itemstack) {
/* 882 */     int j = i - 400;
/*     */     
/* 884 */     if (j >= 0 && j < 2 && j < this.inventoryChest.getSize()) {
/* 885 */       if (j == 0 && itemstack.getItem() != Items.SADDLE)
/* 886 */         return false; 
/* 887 */       if (j == 1 && (!dP() || !f(itemstack))) {
/* 888 */         return false;
/*     */       }
/* 890 */       this.inventoryChest.setItem(j, itemstack);
/* 891 */       dD();
/* 892 */       return true;
/*     */     } 
/*     */     
/* 895 */     int k = i - 500 + 2;
/*     */     
/* 897 */     if (k >= 2 && k < this.inventoryChest.getSize()) {
/* 898 */       this.inventoryChest.setItem(k, itemstack);
/* 899 */       return true;
/*     */     } 
/* 901 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Entity bE() {
/* 908 */     return bF().isEmpty() ? null : bF().get(0);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public GroupDataEntity prepare(DifficultyDamageScaler difficultydamagescaler, @Nullable GroupDataEntity groupdataentity) {
/* 913 */     groupdataentity = super.prepare(difficultydamagescaler, groupdataentity);
/* 914 */     if (this.random.nextInt(5) == 0) {
/* 915 */       setAgeRaw(-24000);
/*     */     }
/*     */     
/* 918 */     return groupdataentity;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityHorseAbstract.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */