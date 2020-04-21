/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*     */ import org.bukkit.event.entity.EntityRegainHealthEvent;
/*     */ import org.bukkit.event.entity.EntityTargetEvent;
/*     */ 
/*     */ 
/*     */ public class EntityWolf
/*     */   extends EntityTameableAnimal
/*     */ {
/*  14 */   private static final DataWatcherObject<Float> DATA_HEALTH = DataWatcher.a((Class)EntityWolf.class, DataWatcherRegistry.c);
/*  15 */   private static final DataWatcherObject<Boolean> bC = DataWatcher.a((Class)EntityWolf.class, DataWatcherRegistry.h);
/*  16 */   private static final DataWatcherObject<Integer> bD = DataWatcher.a((Class)EntityWolf.class, DataWatcherRegistry.b);
/*     */   private float bE;
/*     */   private float bF;
/*     */   private boolean bG;
/*     */   private boolean bH;
/*     */   private float bI;
/*     */   private float bJ;
/*     */   
/*     */   public EntityWolf(World world) {
/*  25 */     super(world);
/*  26 */     setSize(0.6F, 0.85F);
/*  27 */     setTamed(false);
/*     */   }
/*     */   
/*     */   protected void r() {
/*  31 */     this.goalSit = new PathfinderGoalSit(this);
/*  32 */     this.goalSelector.a(1, new PathfinderGoalFloat(this));
/*  33 */     this.goalSelector.a(2, this.goalSit);
/*  34 */     this.goalSelector.a(3, new a<>(this, EntityLlama.class, 24.0F, 1.5D, 1.5D));
/*  35 */     this.goalSelector.a(4, new PathfinderGoalLeapAtTarget(this, 0.4F));
/*  36 */     this.goalSelector.a(5, new PathfinderGoalMeleeAttack(this, 1.0D, true));
/*  37 */     this.goalSelector.a(6, new PathfinderGoalFollowOwner(this, 1.0D, 10.0F, 2.0F));
/*  38 */     this.goalSelector.a(7, new PathfinderGoalBreed(this, 1.0D));
/*  39 */     this.goalSelector.a(8, new PathfinderGoalRandomStrollLand(this, 1.0D));
/*  40 */     this.goalSelector.a(9, new PathfinderGoalBeg(this, 8.0F));
/*  41 */     this.goalSelector.a(10, new PathfinderGoalLookAtPlayer(this, (Class)EntityHuman.class, 8.0F));
/*  42 */     this.goalSelector.a(10, new PathfinderGoalRandomLookaround(this));
/*  43 */     this.targetSelector.a(1, new PathfinderGoalOwnerHurtByTarget(this));
/*  44 */     this.targetSelector.a(2, new PathfinderGoalOwnerHurtTarget(this));
/*  45 */     this.targetSelector.a(3, new PathfinderGoalHurtByTarget(this, true, new Class[0]));
/*  46 */     this.targetSelector.a(4, new PathfinderGoalRandomTargetNonTamed<>(this, EntityAnimal.class, false, new Predicate() {
/*     */             public boolean a(@Nullable Entity entity) {
/*  48 */               return !(!(entity instanceof EntitySheep) && !(entity instanceof EntityRabbit));
/*     */             }
/*     */             
/*     */             public boolean apply(@Nullable Object object) {
/*  52 */               return a((Entity)object);
/*     */             }
/*     */           }));
/*  55 */     this.targetSelector.a(5, new PathfinderGoalNearestAttackableTarget<>(this, EntitySkeletonAbstract.class, false));
/*     */   }
/*     */   
/*     */   protected void initAttributes() {
/*  59 */     super.initAttributes();
/*  60 */     getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.30000001192092896D);
/*  61 */     if (isTamed()) {
/*  62 */       getAttributeInstance(GenericAttributes.maxHealth).setValue(20.0D);
/*     */     } else {
/*  64 */       getAttributeInstance(GenericAttributes.maxHealth).setValue(8.0D);
/*     */     } 
/*     */     
/*  67 */     getAttributeMap().b(GenericAttributes.ATTACK_DAMAGE).setValue(2.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean setGoalTarget(EntityLiving entityliving, EntityTargetEvent.TargetReason reason, boolean fire) {
/*  73 */     if (!super.setGoalTarget(entityliving, reason, fire)) {
/*  74 */       return false;
/*     */     }
/*  76 */     entityliving = getGoalTarget();
/*  77 */     if (entityliving == null) {
/*  78 */       setAngry(false);
/*  79 */     } else if (!isTamed()) {
/*  80 */       setAngry(true);
/*     */     } 
/*  82 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setGoalTarget(@Nullable EntityLiving entityliving) {
/*  87 */     super.setGoalTarget(entityliving);
/*  88 */     if (entityliving == null) {
/*  89 */       setAngry(false);
/*  90 */     } else if (!isTamed()) {
/*  91 */       setAngry(true);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void M() {
/*  97 */     this.datawatcher.set(DATA_HEALTH, Float.valueOf(getHealth()));
/*     */   }
/*     */   
/*     */   protected void i() {
/* 101 */     super.i();
/* 102 */     this.datawatcher.register(DATA_HEALTH, Float.valueOf(getHealth()));
/* 103 */     this.datawatcher.register(bC, Boolean.valueOf(false));
/* 104 */     this.datawatcher.register(bD, Integer.valueOf(EnumColor.RED.getInvColorIndex()));
/*     */   }
/*     */   
/*     */   protected void a(BlockPosition blockposition, Block block) {
/* 108 */     a(SoundEffects.iT, 0.15F, 1.0F);
/*     */   }
/*     */   
/*     */   public static void a(DataConverterManager dataconvertermanager) {
/* 112 */     EntityInsentient.a(dataconvertermanager, EntityWolf.class);
/*     */   }
/*     */   
/*     */   public void b(NBTTagCompound nbttagcompound) {
/* 116 */     super.b(nbttagcompound);
/* 117 */     nbttagcompound.setBoolean("Angry", isAngry());
/* 118 */     nbttagcompound.setByte("CollarColor", (byte)getCollarColor().getInvColorIndex());
/*     */   }
/*     */   
/*     */   public void a(NBTTagCompound nbttagcompound) {
/* 122 */     super.a(nbttagcompound);
/* 123 */     setAngry(nbttagcompound.getBoolean("Angry"));
/* 124 */     if (nbttagcompound.hasKeyOfType("CollarColor", 99)) {
/* 125 */       setCollarColor(EnumColor.fromInvColorIndex(nbttagcompound.getByte("CollarColor")));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect F() {
/* 131 */     return isAngry() ? SoundEffects.iO : ((this.random.nextInt(3) == 0) ? ((isTamed() && ((Float)this.datawatcher.<Float>get(DATA_HEALTH)).floatValue() < 10.0F) ? SoundEffects.iU : SoundEffects.iR) : SoundEffects.iM);
/*     */   }
/*     */   
/*     */   protected SoundEffect d(DamageSource damagesource) {
/* 135 */     return SoundEffects.iQ;
/*     */   }
/*     */   
/*     */   protected SoundEffect cf() {
/* 139 */     return SoundEffects.iN;
/*     */   }
/*     */   
/*     */   protected float cq() {
/* 143 */     return 0.4F;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   protected MinecraftKey J() {
/* 148 */     return LootTables.N;
/*     */   }
/*     */   
/*     */   public void n() {
/* 152 */     super.n();
/* 153 */     if (!this.world.isClientSide && this.bG && !this.bH && !de() && this.onGround) {
/* 154 */       this.bH = true;
/* 155 */       this.bI = 0.0F;
/* 156 */       this.bJ = 0.0F;
/* 157 */       this.world.broadcastEntityEffect(this, (byte)8);
/*     */     } 
/*     */     
/* 160 */     if (!this.world.isClientSide && getGoalTarget() == null && isAngry()) {
/* 161 */       setAngry(false);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void B_() {
/* 167 */     super.B_();
/* 168 */     this.bF = this.bE;
/* 169 */     if (dx()) {
/* 170 */       this.bE += (1.0F - this.bE) * 0.4F;
/*     */     } else {
/* 172 */       this.bE += (0.0F - this.bE) * 0.4F;
/*     */     } 
/*     */     
/* 175 */     if (an()) {
/* 176 */       this.bG = true;
/* 177 */       this.bH = false;
/* 178 */       this.bI = 0.0F;
/* 179 */       this.bJ = 0.0F;
/* 180 */     } else if ((this.bG || this.bH) && this.bH) {
/* 181 */       if (this.bI == 0.0F) {
/* 182 */         a(SoundEffects.iS, cq(), (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
/*     */       }
/*     */       
/* 185 */       this.bJ = this.bI;
/* 186 */       this.bI += 0.05F;
/* 187 */       if (this.bJ >= 2.0F) {
/* 188 */         this.bG = false;
/* 189 */         this.bH = false;
/* 190 */         this.bJ = 0.0F;
/* 191 */         this.bI = 0.0F;
/*     */       } 
/*     */       
/* 194 */       if (this.bI > 0.4F) {
/* 195 */         float f = (float)(getBoundingBox()).b;
/* 196 */         int i = (int)(MathHelper.sin((this.bI - 0.4F) * 3.1415927F) * 7.0F);
/*     */         
/* 198 */         for (int j = 0; j < i; j++) {
/* 199 */           float f1 = (this.random.nextFloat() * 2.0F - 1.0F) * this.width * 0.5F;
/* 200 */           float f2 = (this.random.nextFloat() * 2.0F - 1.0F) * this.width * 0.5F;
/*     */           
/* 202 */           this.world.addParticle(EnumParticle.WATER_SPLASH, this.locX + f1, (f + 0.8F), this.locZ + f2, this.motX, this.motY, this.motZ, new int[0]);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public float getHeadHeight() {
/* 210 */     return this.length * 0.8F;
/*     */   }
/*     */   
/*     */   public int N() {
/* 214 */     return isSitting() ? 20 : super.N();
/*     */   }
/*     */   
/*     */   public boolean damageEntity(DamageSource damagesource, float f) {
/* 218 */     if (isInvulnerable(damagesource)) {
/* 219 */       return false;
/*     */     }
/* 221 */     Entity entity = damagesource.getEntity();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 227 */     if (entity != null && !(entity instanceof EntityHuman) && !(entity instanceof EntityArrow)) {
/* 228 */       f = (f + 1.0F) / 2.0F;
/*     */     }
/*     */     
/* 231 */     return super.damageEntity(damagesource, f);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean B(Entity entity) {
/* 236 */     boolean flag = entity.damageEntity(DamageSource.mobAttack(this), (int)getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).getValue());
/*     */     
/* 238 */     if (flag) {
/* 239 */       a(this, entity);
/*     */     }
/*     */     
/* 242 */     return flag;
/*     */   }
/*     */   
/*     */   public void setTamed(boolean flag) {
/* 246 */     super.setTamed(flag);
/* 247 */     if (flag) {
/* 248 */       getAttributeInstance(GenericAttributes.maxHealth).setValue(20.0D);
/*     */     } else {
/* 250 */       getAttributeInstance(GenericAttributes.maxHealth).setValue(8.0D);
/*     */     } 
/*     */     
/* 253 */     getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(4.0D);
/*     */   }
/*     */   
/*     */   public boolean a(EntityHuman entityhuman, EnumHand enumhand) {
/* 257 */     ItemStack itemstack = entityhuman.b(enumhand);
/*     */     
/* 259 */     if (isTamed()) {
/* 260 */       if (!itemstack.isEmpty()) {
/* 261 */         if (itemstack.getItem() instanceof ItemFood) {
/* 262 */           ItemFood itemfood = (ItemFood)itemstack.getItem();
/*     */           
/* 264 */           if (itemfood.g() && ((Float)this.datawatcher.<Float>get(DATA_HEALTH)).floatValue() < 20.0F) {
/* 265 */             if (!entityhuman.abilities.canInstantlyBuild) {
/* 266 */               itemstack.subtract(1);
/*     */             }
/*     */             
/* 269 */             heal(itemfood.getNutrition(itemstack), EntityRegainHealthEvent.RegainReason.EATING);
/* 270 */             return true;
/*     */           } 
/* 272 */         } else if (itemstack.getItem() == Items.DYE) {
/* 273 */           EnumColor enumcolor = EnumColor.fromInvColorIndex(itemstack.getData());
/*     */           
/* 275 */           if (enumcolor != getCollarColor()) {
/* 276 */             setCollarColor(enumcolor);
/* 277 */             if (!entityhuman.abilities.canInstantlyBuild) {
/* 278 */               itemstack.subtract(1);
/*     */             }
/*     */             
/* 281 */             return true;
/*     */           } 
/*     */         } 
/*     */       }
/*     */       
/* 286 */       if (e(entityhuman) && !this.world.isClientSide && !e(itemstack)) {
/* 287 */         this.goalSit.setSitting(!isSitting());
/* 288 */         this.bd = false;
/* 289 */         this.navigation.p();
/* 290 */         setGoalTarget((EntityLiving)null, EntityTargetEvent.TargetReason.FORGOT_TARGET, true);
/*     */       } 
/* 292 */     } else if (itemstack.getItem() == Items.BONE && !isAngry()) {
/* 293 */       if (!entityhuman.abilities.canInstantlyBuild) {
/* 294 */         itemstack.subtract(1);
/*     */       }
/*     */       
/* 297 */       if (!this.world.isClientSide)
/*     */       {
/* 299 */         if (this.random.nextInt(3) == 0 && !CraftEventFactory.callEntityTameEvent(this, entityhuman).isCancelled()) {
/* 300 */           c(entityhuman);
/* 301 */           this.navigation.p();
/* 302 */           setGoalTarget((EntityLiving)null);
/* 303 */           this.goalSit.setSitting(true);
/* 304 */           setHealth(getMaxHealth());
/* 305 */           p(true);
/* 306 */           this.world.broadcastEntityEffect(this, (byte)7);
/*     */         } else {
/* 308 */           p(false);
/* 309 */           this.world.broadcastEntityEffect(this, (byte)6);
/*     */         } 
/*     */       }
/*     */       
/* 313 */       return true;
/*     */     } 
/*     */     
/* 316 */     return super.a(entityhuman, enumhand);
/*     */   }
/*     */   
/*     */   public boolean e(ItemStack itemstack) {
/* 320 */     return (itemstack.getItem() instanceof ItemFood && ((ItemFood)itemstack.getItem()).g());
/*     */   }
/*     */   
/*     */   public int cU() {
/* 324 */     return 8;
/*     */   }
/*     */   
/*     */   public boolean isAngry() {
/* 328 */     return ((((Byte)this.datawatcher.<Byte>get(bx)).byteValue() & 0x2) != 0);
/*     */   }
/*     */   
/*     */   public void setAngry(boolean flag) {
/* 332 */     byte b0 = ((Byte)this.datawatcher.<Byte>get(bx)).byteValue();
/*     */     
/* 334 */     if (flag) {
/* 335 */       this.datawatcher.set(bx, Byte.valueOf((byte)(b0 | 0x2)));
/*     */     } else {
/* 337 */       this.datawatcher.set(bx, Byte.valueOf((byte)(b0 & 0xFFFFFFFD)));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumColor getCollarColor() {
/* 343 */     return EnumColor.fromInvColorIndex(((Integer)this.datawatcher.<Integer>get(bD)).intValue() & 0xF);
/*     */   }
/*     */   
/*     */   public void setCollarColor(EnumColor enumcolor) {
/* 347 */     this.datawatcher.set(bD, Integer.valueOf(enumcolor.getInvColorIndex()));
/*     */   }
/*     */   
/*     */   public EntityWolf b(EntityAgeable entityageable) {
/* 351 */     EntityWolf entitywolf = new EntityWolf(this.world);
/* 352 */     UUID uuid = getOwnerUUID();
/*     */     
/* 354 */     if (uuid != null) {
/* 355 */       entitywolf.setOwnerUUID(uuid);
/* 356 */       entitywolf.setTamed(true);
/*     */     } 
/*     */     
/* 359 */     return entitywolf;
/*     */   }
/*     */   
/*     */   public void t(boolean flag) {
/* 363 */     this.datawatcher.set(bC, Boolean.valueOf(flag));
/*     */   }
/*     */   
/*     */   public boolean mate(EntityAnimal entityanimal) {
/* 367 */     if (entityanimal == this)
/* 368 */       return false; 
/* 369 */     if (!isTamed())
/* 370 */       return false; 
/* 371 */     if (!(entityanimal instanceof EntityWolf)) {
/* 372 */       return false;
/*     */     }
/* 374 */     EntityWolf entitywolf = (EntityWolf)entityanimal;
/*     */     
/* 376 */     return !entitywolf.isTamed() ? false : (entitywolf.isSitting() ? false : ((isInLove() && entitywolf.isInLove())));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean dx() {
/* 381 */     return ((Boolean)this.datawatcher.<Boolean>get(bC)).booleanValue();
/*     */   }
/*     */   
/*     */   public boolean a(EntityLiving entityliving, EntityLiving entityliving1) {
/* 385 */     if (!(entityliving instanceof EntityCreeper) && !(entityliving instanceof EntityGhast)) {
/* 386 */       if (entityliving instanceof EntityWolf) {
/* 387 */         EntityWolf entitywolf = (EntityWolf)entityliving;
/*     */         
/* 389 */         if (entitywolf.isTamed() && entitywolf.getOwner() == entityliving1) {
/* 390 */           return false;
/*     */         }
/*     */       } 
/*     */       
/* 394 */       return (entityliving instanceof EntityHuman && entityliving1 instanceof EntityHuman && !((EntityHuman)entityliving1).a((EntityHuman)entityliving)) ? false : (!(entityliving instanceof EntityHorseAbstract && ((EntityHorseAbstract)entityliving).isTamed()));
/*     */     } 
/* 396 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(EntityHuman entityhuman) {
/* 401 */     return (!isAngry() && super.a(entityhuman));
/*     */   }
/*     */   
/*     */   public EntityAgeable createChild(EntityAgeable entityageable) {
/* 405 */     return b(entityageable);
/*     */   }
/*     */   
/*     */   class a<T extends Entity>
/*     */     extends PathfinderGoalAvoidTarget<T> {
/*     */     private final EntityWolf d;
/*     */     
/*     */     public a(EntityWolf entitywolf, Class<T> oclass, float f, double d0, double d1) {
/* 413 */       super(entitywolf, oclass, f, d0, d1);
/* 414 */       this.d = entitywolf;
/*     */     }
/*     */     
/*     */     public boolean a() {
/* 418 */       return (super.a() && this.b instanceof EntityLlama) ? ((!this.d.isTamed() && a((EntityLlama)this.b))) : false;
/*     */     }
/*     */     
/*     */     private boolean a(EntityLlama entityllama) {
/* 422 */       return (entityllama.getStrength() >= EntityWolf.this.random.nextInt(5));
/*     */     }
/*     */     
/*     */     public void c() {
/* 426 */       EntityWolf.this.setGoalTarget((EntityLiving)null);
/* 427 */       super.c();
/*     */     }
/*     */     
/*     */     public void e() {
/* 431 */       EntityWolf.this.setGoalTarget((EntityLiving)null);
/* 432 */       super.e();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityWolf.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */