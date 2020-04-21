/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.base.Predicate;
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
/*     */ 
/*     */ public class EntityLlama
/*     */   extends EntityHorseChestedAbstract
/*     */   implements IRangedEntity
/*     */ {
/*  61 */   private static final DataWatcherObject<Integer> bH = DataWatcher.a((Class)EntityLlama.class, DataWatcherRegistry.b);
/*  62 */   private static final DataWatcherObject<Integer> bI = DataWatcher.a((Class)EntityLlama.class, DataWatcherRegistry.b);
/*  63 */   private static final DataWatcherObject<Integer> bJ = DataWatcher.a((Class)EntityLlama.class, DataWatcherRegistry.b);
/*     */   private boolean bK;
/*     */   @Nullable
/*     */   private EntityLlama bL;
/*     */   @Nullable
/*     */   private EntityLlama bM;
/*     */   
/*     */   public EntityLlama(World paramWorld) {
/*  71 */     super(paramWorld);
/*  72 */     setSize(0.9F, 1.87F);
/*     */   }
/*     */   
/*     */   public void setStrength(int paramInt) {
/*  76 */     this.datawatcher.set(bH, Integer.valueOf(Math.max(1, Math.min(5, paramInt))));
/*     */   }
/*     */   
/*     */   private void dY() {
/*  80 */     byte b = (this.random.nextFloat() < 0.04F) ? 5 : 3;
/*     */     
/*  82 */     setStrength(1 + this.random.nextInt(b));
/*     */   }
/*     */   
/*     */   public int getStrength() {
/*  86 */     return ((Integer)this.datawatcher.<Integer>get(bH)).intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void b(NBTTagCompound paramNBTTagCompound) {
/*  96 */     super.b(paramNBTTagCompound);
/*     */     
/*  98 */     paramNBTTagCompound.setInt("Variant", getVariant());
/*  99 */     paramNBTTagCompound.setInt("Strength", getStrength());
/*     */     
/* 101 */     if (!this.inventoryChest.getItem(1).isEmpty()) {
/* 102 */       paramNBTTagCompound.set("DecorItem", this.inventoryChest.getItem(1).save(new NBTTagCompound()));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(NBTTagCompound paramNBTTagCompound) {
/* 108 */     setStrength(paramNBTTagCompound.getInt("Strength"));
/*     */     
/* 110 */     super.a(paramNBTTagCompound);
/* 111 */     setVariant(paramNBTTagCompound.getInt("Variant"));
/*     */     
/* 113 */     if (paramNBTTagCompound.hasKeyOfType("DecorItem", 10)) {
/* 114 */       this.inventoryChest.setItem(1, new ItemStack(paramNBTTagCompound.getCompound("DecorItem")));
/*     */     }
/*     */     
/* 117 */     dD();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void r() {
/* 122 */     this.goalSelector.a(0, new PathfinderGoalFloat(this));
/* 123 */     this.goalSelector.a(1, new PathfinderGoalTame(this, 1.2D));
/* 124 */     this.goalSelector.a(2, new PathfinderGoalLlamaFollow(this, 2.0999999046325684D));
/* 125 */     this.goalSelector.a(3, new PathfinderGoalArrowAttack(this, 1.25D, 40, 20.0F));
/* 126 */     this.goalSelector.a(3, new PathfinderGoalPanic(this, 1.2D));
/* 127 */     this.goalSelector.a(4, new PathfinderGoalBreed(this, 1.0D));
/* 128 */     this.goalSelector.a(5, new PathfinderGoalFollowParent(this, 1.0D));
/* 129 */     this.goalSelector.a(6, new PathfinderGoalRandomStrollLand(this, 0.7D));
/* 130 */     this.goalSelector.a(7, new PathfinderGoalLookAtPlayer(this, (Class)EntityHuman.class, 6.0F));
/* 131 */     this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
/*     */     
/* 133 */     this.targetSelector.a(1, new c(this));
/* 134 */     this.targetSelector.a(2, new a(this));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initAttributes() {
/* 139 */     super.initAttributes();
/* 140 */     getAttributeInstance(GenericAttributes.FOLLOW_RANGE).setValue(40.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void i() {
/* 145 */     super.i();
/*     */     
/* 147 */     this.datawatcher.register(bH, Integer.valueOf(0));
/* 148 */     this.datawatcher.register(bI, Integer.valueOf(-1));
/* 149 */     this.datawatcher.register(bJ, Integer.valueOf(0));
/*     */   }
/*     */   
/*     */   public int getVariant() {
/* 153 */     return MathHelper.clamp(((Integer)this.datawatcher.<Integer>get(bJ)).intValue(), 0, 3);
/*     */   }
/*     */   
/*     */   public void setVariant(int paramInt) {
/* 157 */     this.datawatcher.set(bJ, Integer.valueOf(paramInt));
/*     */   }
/*     */ 
/*     */   
/*     */   protected int dn() {
/* 162 */     if (isCarryingChest()) {
/* 163 */       return 2 + 3 * dt();
/*     */     }
/* 165 */     return super.dn();
/*     */   }
/*     */ 
/*     */   
/*     */   public void k(Entity paramEntity) {
/* 170 */     if (!w(paramEntity)) {
/*     */       return;
/*     */     }
/* 173 */     float f1 = MathHelper.cos(this.aN * 0.017453292F);
/* 174 */     float f2 = MathHelper.sin(this.aN * 0.017453292F);
/* 175 */     float f3 = 0.3F;
/* 176 */     paramEntity.setPosition(this.locX + (0.3F * f2), this.locY + aG() + paramEntity.aF(), this.locZ - (0.3F * f1));
/*     */   }
/*     */ 
/*     */   
/*     */   public double aG() {
/* 181 */     return this.length * 0.67D;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean cV() {
/* 186 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean b(EntityHuman paramEntityHuman, ItemStack paramItemStack) {
/* 191 */     byte b1 = 0;
/* 192 */     byte b2 = 0;
/* 193 */     float f = 0.0F;
/* 194 */     boolean bool = false;
/*     */     
/* 196 */     Item item = paramItemStack.getItem();
/* 197 */     if (item == Items.WHEAT) {
/* 198 */       b1 = 10;
/* 199 */       b2 = 3;
/* 200 */       f = 2.0F;
/* 201 */     } else if (item == Item.getItemOf(Blocks.HAY_BLOCK)) {
/* 202 */       b1 = 90;
/* 203 */       b2 = 6;
/* 204 */       f = 10.0F;
/* 205 */       if (isTamed() && getAge() == 0) {
/* 206 */         bool = true;
/* 207 */         f(paramEntityHuman);
/*     */       } 
/*     */     } 
/* 210 */     if (getHealth() < getMaxHealth() && f > 0.0F) {
/* 211 */       heal(f);
/* 212 */       bool = true;
/*     */     } 
/* 214 */     if (isBaby() && b1 > 0) {
/* 215 */       this.world.addParticle(EnumParticle.VILLAGER_HAPPY, this.locX + (this.random.nextFloat() * this.width * 2.0F) - this.width, this.locY + 0.5D + (this.random.nextFloat() * this.length), this.locZ + (this.random.nextFloat() * this.width * 2.0F) - this.width, 0.0D, 0.0D, 0.0D, new int[0]);
/* 216 */       if (!this.world.isClientSide) {
/* 217 */         setAge(b1);
/*     */       }
/* 219 */       bool = true;
/*     */     } 
/* 221 */     if (b2 > 0 && (bool || !isTamed()) && getTemper() < getMaxDomestication()) {
/* 222 */       bool = true;
/* 223 */       if (!this.world.isClientSide) {
/* 224 */         n(b2);
/*     */       }
/*     */     } 
/* 227 */     if (bool && !isSilent()) {
/* 228 */       this.world.a((EntityHuman)null, this.locX, this.locY, this.locZ, SoundEffects.dQ, bK(), 1.0F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
/*     */     }
/*     */     
/* 231 */     return bool;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isFrozen() {
/* 236 */     return (getHealth() <= 0.0F || dy());
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public GroupDataEntity prepare(DifficultyDamageScaler paramDifficultyDamageScaler, @Nullable GroupDataEntity paramGroupDataEntity) {
/*     */     int i;
/* 242 */     paramGroupDataEntity = super.prepare(paramDifficultyDamageScaler, paramGroupDataEntity);
/* 243 */     dY();
/*     */ 
/*     */     
/* 246 */     if (paramGroupDataEntity instanceof b) {
/* 247 */       i = ((b)paramGroupDataEntity).a;
/*     */     } else {
/* 249 */       i = this.random.nextInt(4);
/* 250 */       paramGroupDataEntity = new b(i);
/*     */     } 
/* 252 */     setVariant(i);
/*     */     
/* 254 */     return paramGroupDataEntity;
/*     */   }
/*     */ 
/*     */   
/*     */   static class b
/*     */     implements GroupDataEntity
/*     */   {
/*     */     public int a;
/*     */ 
/*     */     
/*     */     private b(int param1Int) {
/* 265 */       this.a = param1Int;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect do_() {
/* 271 */     return SoundEffects.dN;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect F() {
/* 276 */     return SoundEffects.dM;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect d(DamageSource paramDamageSource) {
/* 281 */     return SoundEffects.dR;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect cf() {
/* 286 */     return SoundEffects.dP;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(BlockPosition paramBlockPosition, Block paramBlock) {
/* 291 */     a(SoundEffects.dT, 0.15F, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void dp() {
/* 296 */     a(SoundEffects.dO, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void dK() {
/* 301 */     SoundEffect soundEffect = do_();
/* 302 */     if (soundEffect != null) {
/* 303 */       a(soundEffect, cq(), cr());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   protected MinecraftKey J() {
/* 310 */     return LootTables.aw;
/*     */   }
/*     */ 
/*     */   
/*     */   public int dt() {
/* 315 */     return getStrength();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean dP() {
/* 320 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean f(ItemStack paramItemStack) {
/* 325 */     return (paramItemStack.getItem() == Item.getItemOf(Blocks.CARPET));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean dF() {
/* 330 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(IInventory paramIInventory) {
/* 335 */     EnumColor enumColor1 = dT();
/* 336 */     super.a(paramIInventory);
/*     */     
/* 338 */     EnumColor enumColor2 = dT();
/* 339 */     if (this.ticksLived > 20 && enumColor2 != null && enumColor2 != enumColor1) {
/* 340 */       a(SoundEffects.dU, 0.5F, 1.0F);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void dD() {
/* 346 */     if (this.world.isClientSide) {
/*     */       return;
/*     */     }
/*     */     
/* 350 */     super.dD();
/*     */     
/* 352 */     g(this.inventoryChest.getItem(1));
/*     */   }
/*     */   
/*     */   private void a(@Nullable EnumColor paramEnumColor) {
/* 356 */     this.datawatcher.set(bI, Integer.valueOf((paramEnumColor == null) ? -1 : paramEnumColor.getColorIndex()));
/*     */   }
/*     */   
/*     */   private void g(ItemStack paramItemStack) {
/* 360 */     if (f(paramItemStack)) {
/* 361 */       a(EnumColor.fromColorIndex(paramItemStack.getData()));
/*     */     } else {
/* 363 */       a((EnumColor)null);
/*     */     } 
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public EnumColor dT() {
/* 369 */     int i = ((Integer)this.datawatcher.<Integer>get(bI)).intValue();
/* 370 */     return (i == -1) ? null : EnumColor.fromColorIndex(i);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxDomestication() {
/* 375 */     return 30;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean mate(EntityAnimal paramEntityAnimal) {
/* 380 */     return (paramEntityAnimal != this && paramEntityAnimal instanceof EntityLlama && dL() && ((EntityLlama)paramEntityAnimal).dL());
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityLlama b(EntityAgeable paramEntityAgeable) {
/* 385 */     EntityLlama entityLlama1 = new EntityLlama(this.world);
/*     */     
/* 387 */     a(paramEntityAgeable, entityLlama1);
/*     */     
/* 389 */     EntityLlama entityLlama2 = (EntityLlama)paramEntityAgeable;
/*     */     
/* 391 */     int i = this.random.nextInt(Math.max(getStrength(), entityLlama2.getStrength())) + 1;
/* 392 */     if (this.random.nextFloat() < 0.03F) {
/* 393 */       i++;
/*     */     }
/* 395 */     entityLlama1.setStrength(i);
/*     */     
/* 397 */     entityLlama1.setVariant(this.random.nextBoolean() ? getVariant() : entityLlama2.getVariant());
/*     */     
/* 399 */     return entityLlama1;
/*     */   }
/*     */   
/*     */   private void e(EntityLiving paramEntityLiving) {
/* 403 */     EntityLlamaSpit entityLlamaSpit = new EntityLlamaSpit(this.world, this);
/* 404 */     double d1 = paramEntityLiving.locX - this.locX;
/* 405 */     double d2 = (paramEntityLiving.getBoundingBox()).b + (paramEntityLiving.length / 3.0F) - entityLlamaSpit.locY;
/* 406 */     double d3 = paramEntityLiving.locZ - this.locZ;
/* 407 */     float f = MathHelper.sqrt(d1 * d1 + d3 * d3) * 0.2F;
/* 408 */     entityLlamaSpit.shoot(d1, d2 + f, d3, 1.5F, 10.0F);
/* 409 */     this.world.a((EntityHuman)null, this.locX, this.locY, this.locZ, SoundEffects.dS, bK(), 1.0F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
/*     */     
/* 411 */     this.world.addEntity(entityLlamaSpit);
/* 412 */     this.bK = true;
/*     */   }
/*     */   
/*     */   private void y(boolean paramBoolean) {
/* 416 */     this.bK = paramBoolean;
/*     */   }
/*     */ 
/*     */   
/*     */   public void e(float paramFloat1, float paramFloat2) {
/* 421 */     int i = MathHelper.f((paramFloat1 * 0.5F - 3.0F) * paramFloat2);
/* 422 */     if (i <= 0) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 427 */     if (paramFloat1 >= 6.0F) {
/* 428 */       damageEntity(DamageSource.FALL, i);
/*     */       
/* 430 */       if (isVehicle()) {
/* 431 */         for (Entity entity : bG()) {
/* 432 */           entity.damageEntity(DamageSource.FALL, i);
/*     */         }
/*     */       }
/*     */     } 
/*     */     
/* 437 */     IBlockData iBlockData = this.world.getType(new BlockPosition(this.locX, this.locY - 0.2D - this.lastYaw, this.locZ));
/* 438 */     Block block = iBlockData.getBlock();
/* 439 */     if (iBlockData.getMaterial() != Material.AIR && !isSilent()) {
/* 440 */       SoundEffectType soundEffectType = block.getStepSound();
/* 441 */       this.world.a((EntityHuman)null, this.locX, this.locY, this.locZ, soundEffectType.d(), bK(), soundEffectType.a() * 0.5F, soundEffectType.b() * 0.75F);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void dU() {
/* 446 */     if (this.bL != null) {
/* 447 */       this.bL.bM = null;
/*     */     }
/* 449 */     this.bL = null;
/*     */   }
/*     */   
/*     */   public void a(EntityLlama paramEntityLlama) {
/* 453 */     this.bL = paramEntityLlama;
/* 454 */     this.bL.bM = this;
/*     */   }
/*     */   
/*     */   public boolean dV() {
/* 458 */     return (this.bM != null);
/*     */   }
/*     */   
/*     */   public boolean dW() {
/* 462 */     return (this.bL != null);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public EntityLlama dX() {
/* 467 */     return this.bL;
/*     */   }
/*     */ 
/*     */   
/*     */   protected double dk() {
/* 472 */     return 2.0D;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void dI() {
/* 477 */     if (!dW() && isBaby()) {
/* 478 */       super.dI();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean dJ() {
/* 484 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(EntityLiving paramEntityLiving, float paramFloat) {
/* 489 */     e(paramEntityLiving);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void p(boolean paramBoolean) {}
/*     */ 
/*     */ 
/*     */   
/*     */   static class c
/*     */     extends PathfinderGoalHurtByTarget
/*     */   {
/*     */     public c(EntityLlama param1EntityLlama) {
/* 503 */       super(param1EntityLlama, false, new Class[0]);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean b() {
/* 508 */       if (this.e instanceof EntityLlama) {
/* 509 */         EntityLlama entityLlama = (EntityLlama)this.e;
/* 510 */         if (EntityLlama.b(entityLlama)) {
/* 511 */           EntityLlama.a(entityLlama, false);
/* 512 */           return false;
/*     */         } 
/*     */       } 
/* 515 */       return super.b();
/*     */     }
/*     */   }
/*     */   
/*     */   static class a extends PathfinderGoalNearestAttackableTarget<EntityWolf> {
/*     */     public a(EntityLlama param1EntityLlama) {
/* 521 */       super(param1EntityLlama, EntityWolf.class, 16, false, true, (Predicate<? super EntityWolf>)null);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 526 */       if (super.a() && this.d != null && !this.d.isTamed()) {
/* 527 */         return true;
/*     */       }
/*     */       
/* 530 */       this.e.setGoalTarget((EntityLiving)null);
/* 531 */       return false;
/*     */     }
/*     */ 
/*     */     
/*     */     protected double i() {
/* 536 */       return super.i() * 0.25D;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityLlama.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */