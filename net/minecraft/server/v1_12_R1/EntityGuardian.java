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
/*     */ public class EntityGuardian
/*     */   extends EntityMonster
/*     */ {
/*  47 */   private static final DataWatcherObject<Boolean> bA = DataWatcher.a((Class)EntityGuardian.class, DataWatcherRegistry.h);
/*  48 */   private static final DataWatcherObject<Integer> bB = DataWatcher.a((Class)EntityGuardian.class, DataWatcherRegistry.b);
/*     */   
/*     */   protected float a;
/*     */   protected float b;
/*     */   protected float c;
/*     */   protected float bx;
/*     */   protected float by;
/*     */   private EntityLiving bC;
/*     */   private int bD;
/*     */   private boolean bE;
/*     */   public PathfinderGoalRandomStroll goalRandomStroll;
/*     */   
/*     */   public EntityGuardian(World paramWorld) {
/*  61 */     super(paramWorld);
/*     */     
/*  63 */     this.b_ = 10;
/*  64 */     setSize(0.85F, 0.85F);
/*     */     
/*  66 */     this.moveController = new ControllerMoveGuardian(this);
/*     */     
/*  68 */     this.a = this.random.nextFloat();
/*  69 */     this.b = this.a;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void r() {
/*  74 */     PathfinderGoalMoveTowardsRestriction pathfinderGoalMoveTowardsRestriction = new PathfinderGoalMoveTowardsRestriction(this, 1.0D);
/*  75 */     this.goalRandomStroll = new PathfinderGoalRandomStroll(this, 1.0D, 80);
/*     */     
/*  77 */     this.goalSelector.a(4, new PathfinderGoalGuardianAttack(this));
/*  78 */     this.goalSelector.a(5, pathfinderGoalMoveTowardsRestriction);
/*  79 */     this.goalSelector.a(7, this.goalRandomStroll);
/*  80 */     this.goalSelector.a(8, new PathfinderGoalLookAtPlayer(this, (Class)EntityHuman.class, 8.0F));
/*  81 */     this.goalSelector.a(8, new PathfinderGoalLookAtPlayer(this, (Class)EntityGuardian.class, 12.0F, 0.01F));
/*  82 */     this.goalSelector.a(9, new PathfinderGoalRandomLookaround(this));
/*     */ 
/*     */     
/*  85 */     this.goalRandomStroll.a(3);
/*  86 */     pathfinderGoalMoveTowardsRestriction.a(3);
/*     */     
/*  88 */     this.targetSelector.a(1, new PathfinderGoalNearestAttackableTarget<>(this, EntityLiving.class, 10, true, false, new EntitySelectorGuardianTargetHumanSquid(this)));
/*     */   }
/*     */ 
/*     */   
/*     */   public void initAttributes() {
/*  93 */     super.initAttributes();
/*  94 */     getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(6.0D);
/*  95 */     getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.5D);
/*  96 */     getAttributeInstance(GenericAttributes.FOLLOW_RANGE).setValue(16.0D);
/*  97 */     getAttributeInstance(GenericAttributes.maxHealth).setValue(30.0D);
/*     */   }
/*     */   
/*     */   public static void c(DataConverterManager paramDataConverterManager) {
/* 101 */     EntityInsentient.a(paramDataConverterManager, EntityGuardian.class);
/*     */   }
/*     */ 
/*     */   
/*     */   protected NavigationAbstract b(World paramWorld) {
/* 106 */     return new NavigationGuardian(this, paramWorld);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void i() {
/* 111 */     super.i();
/*     */     
/* 113 */     this.datawatcher.register(bA, Boolean.valueOf(false));
/* 114 */     this.datawatcher.register(bB, Integer.valueOf(0));
/*     */   }
/*     */   
/*     */   public boolean do() {
/* 118 */     return ((Boolean)this.datawatcher.<Boolean>get(bA)).booleanValue();
/*     */   }
/*     */   
/*     */   private void a(boolean paramBoolean) {
/* 122 */     this.datawatcher.set(bA, Boolean.valueOf(paramBoolean));
/*     */   }
/*     */   
/*     */   public int p() {
/* 126 */     return 80;
/*     */   }
/*     */   
/*     */   private void a(int paramInt) {
/* 130 */     this.datawatcher.set(bB, Integer.valueOf(paramInt));
/*     */   }
/*     */   
/*     */   public boolean dp() {
/* 134 */     return (((Integer)this.datawatcher.<Integer>get(bB)).intValue() != 0);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public EntityLiving dq() {
/* 139 */     if (!dp()) {
/* 140 */       return null;
/*     */     }
/* 142 */     if (this.world.isClientSide) {
/* 143 */       if (this.bC != null) {
/* 144 */         return this.bC;
/*     */       }
/* 146 */       Entity entity = this.world.getEntity(((Integer)this.datawatcher.<Integer>get(bB)).intValue());
/* 147 */       if (entity instanceof EntityLiving) {
/* 148 */         this.bC = (EntityLiving)entity;
/* 149 */         return this.bC;
/*     */       } 
/* 151 */       return null;
/*     */     } 
/* 153 */     return getGoalTarget();
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(DataWatcherObject<?> paramDataWatcherObject) {
/* 158 */     super.a(paramDataWatcherObject);
/*     */     
/* 160 */     if (bB.equals(paramDataWatcherObject)) {
/* 161 */       this.bD = 0;
/* 162 */       this.bC = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int C() {
/* 168 */     return 160;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect F() {
/* 173 */     return isInWater() ? SoundEffects.cw : SoundEffects.cx;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect d(DamageSource paramDamageSource) {
/* 178 */     return isInWater() ? SoundEffects.cC : SoundEffects.cD;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect cf() {
/* 183 */     return isInWater() ? SoundEffects.cz : SoundEffects.cA;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean playStepSound() {
/* 188 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getHeadHeight() {
/* 193 */     return this.length * 0.5F;
/*     */   }
/*     */ 
/*     */   
/*     */   public float a(BlockPosition paramBlockPosition) {
/* 198 */     if (this.world.getType(paramBlockPosition).getMaterial() == Material.WATER) {
/* 199 */       return 10.0F + this.world.n(paramBlockPosition) - 0.5F;
/*     */     }
/* 201 */     return super.a(paramBlockPosition);
/*     */   }
/*     */ 
/*     */   
/*     */   public void n() {
/* 206 */     if (this.world.isClientSide) {
/*     */       
/* 208 */       this.b = this.a;
/* 209 */       if (!isInWater()) {
/* 210 */         this.c = 2.0F;
/* 211 */         if (this.motY > 0.0D && this.bE && !isSilent()) {
/* 212 */           this.world.a(this.locX, this.locY, this.locZ, dn(), bK(), 1.0F, 1.0F, false);
/*     */         }
/* 214 */         this.bE = (this.motY < 0.0D && this.world.d((new BlockPosition(this)).down(), false));
/* 215 */       } else if (do()) {
/* 216 */         if (this.c < 0.5F) {
/* 217 */           this.c = 4.0F;
/*     */         } else {
/* 219 */           this.c += (0.5F - this.c) * 0.1F;
/*     */         } 
/*     */       } else {
/* 222 */         this.c += (0.125F - this.c) * 0.2F;
/*     */       } 
/* 224 */       this.a += this.c;
/*     */ 
/*     */       
/* 227 */       this.by = this.bx;
/* 228 */       if (!isInWater()) {
/* 229 */         this.bx = this.random.nextFloat();
/* 230 */       } else if (do()) {
/* 231 */         this.bx += (0.0F - this.bx) * 0.25F;
/*     */       } else {
/* 233 */         this.bx += (1.0F - this.bx) * 0.06F;
/*     */       } 
/*     */       
/* 236 */       if (do() && isInWater()) {
/* 237 */         Vec3D vec3D = e(0.0F);
/* 238 */         for (byte b = 0; b < 2; b++) {
/* 239 */           this.world.addParticle(EnumParticle.WATER_BUBBLE, this.locX + (this.random.nextDouble() - 0.5D) * this.width - vec3D.x * 1.5D, this.locY + this.random.nextDouble() * this.length - vec3D.y * 1.5D, this.locZ + (this.random.nextDouble() - 0.5D) * this.width - vec3D.z * 1.5D, 0.0D, 0.0D, 0.0D, new int[0]);
/*     */         }
/*     */       } 
/*     */       
/* 243 */       if (dp()) {
/* 244 */         if (this.bD < p()) {
/* 245 */           this.bD++;
/*     */         }
/* 247 */         EntityLiving entityLiving = dq();
/* 248 */         if (entityLiving != null) {
/* 249 */           getControllerLook().a(entityLiving, 90.0F, 90.0F);
/* 250 */           getControllerLook().a();
/*     */           
/* 252 */           double d1 = s(0.0F);
/* 253 */           double d2 = entityLiving.locX - this.locX;
/* 254 */           double d3 = entityLiving.locY + (entityLiving.length * 0.5F) - this.locY + getHeadHeight();
/* 255 */           double d4 = entityLiving.locZ - this.locZ;
/* 256 */           double d5 = Math.sqrt(d2 * d2 + d3 * d3 + d4 * d4);
/* 257 */           d2 /= d5;
/* 258 */           d3 /= d5;
/* 259 */           d4 /= d5;
/* 260 */           double d6 = this.random.nextDouble();
/* 261 */           while (d6 < d5) {
/* 262 */             d6 += 1.8D - d1 + this.random.nextDouble() * (1.7D - d1);
/* 263 */             this.world.addParticle(EnumParticle.WATER_BUBBLE, this.locX + d2 * d6, this.locY + d3 * d6 + getHeadHeight(), this.locZ + d4 * d6, 0.0D, 0.0D, 0.0D, new int[0]);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 269 */     if (this.inWater) {
/* 270 */       setAirTicks(300);
/*     */     }
/* 272 */     else if (this.onGround) {
/* 273 */       this.motY += 0.5D;
/* 274 */       this.motX += ((this.random.nextFloat() * 2.0F - 1.0F) * 0.4F);
/* 275 */       this.motZ += ((this.random.nextFloat() * 2.0F - 1.0F) * 0.4F);
/* 276 */       this.yaw = this.random.nextFloat() * 360.0F;
/* 277 */       this.onGround = false;
/* 278 */       this.impulse = true;
/*     */     } 
/*     */ 
/*     */     
/* 282 */     if (dp()) {
/* 283 */       this.yaw = this.aP;
/*     */     }
/*     */     
/* 286 */     super.n();
/*     */   }
/*     */   
/*     */   protected SoundEffect dn() {
/* 290 */     return SoundEffects.cB;
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
/*     */   public float s(float paramFloat) {
/* 302 */     return (this.bD + paramFloat) / p();
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   protected MinecraftKey J() {
/* 308 */     return LootTables.x;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean s_() {
/* 313 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canSpawn() {
/* 318 */     return (this.world.a(getBoundingBox(), this) && this.world.getCubes(this, getBoundingBox()).isEmpty());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean P() {
/* 324 */     return ((this.random.nextInt(20) == 0 || !this.world.i(new BlockPosition(this))) && super.P());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean damageEntity(DamageSource paramDamageSource, float paramFloat) {
/* 329 */     if (!do() && !paramDamageSource.isMagic() && paramDamageSource.i() instanceof EntityLiving) {
/* 330 */       EntityLiving entityLiving = (EntityLiving)paramDamageSource.i();
/*     */ 
/*     */       
/* 333 */       if (!paramDamageSource.isExplosion()) {
/* 334 */         entityLiving.damageEntity(DamageSource.a(this), 2.0F);
/*     */       }
/*     */     } 
/*     */     
/* 338 */     if (this.goalRandomStroll != null) {
/* 339 */       this.goalRandomStroll.i();
/*     */     }
/*     */     
/* 342 */     return super.damageEntity(paramDamageSource, paramFloat);
/*     */   }
/*     */ 
/*     */   
/*     */   public int N() {
/* 347 */     return 180;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(float paramFloat1, float paramFloat2, float paramFloat3) {
/* 352 */     if (cC() && isInWater()) {
/* 353 */       b(paramFloat1, paramFloat2, paramFloat3, 0.1F);
/* 354 */       move(EnumMoveType.SELF, this.motX, this.motY, this.motZ);
/*     */       
/* 356 */       this.motX *= 0.8999999761581421D;
/* 357 */       this.motY *= 0.8999999761581421D;
/* 358 */       this.motZ *= 0.8999999761581421D;
/* 359 */       if (!do() && getGoalTarget() == null) {
/* 360 */         this.motY -= 0.005D;
/*     */       }
/*     */     } else {
/* 363 */       super.a(paramFloat1, paramFloat2, paramFloat3);
/*     */     } 
/*     */   }
/*     */   
/*     */   static class EntitySelectorGuardianTargetHumanSquid implements Predicate<EntityLiving> {
/*     */     private final EntityGuardian a;
/*     */     
/*     */     public EntitySelectorGuardianTargetHumanSquid(EntityGuardian param1EntityGuardian) {
/* 371 */       this.a = param1EntityGuardian;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a(@Nullable EntityLiving param1EntityLiving) {
/* 376 */       return ((param1EntityLiving instanceof EntityHuman || param1EntityLiving instanceof EntitySquid) && param1EntityLiving.h(this.a) > 9.0D);
/*     */     }
/*     */   }
/*     */   
/*     */   static class PathfinderGoalGuardianAttack extends PathfinderGoal {
/*     */     private final EntityGuardian a;
/*     */     private int b;
/*     */     private final boolean c;
/*     */     
/*     */     public PathfinderGoalGuardianAttack(EntityGuardian param1EntityGuardian) {
/* 386 */       this.a = param1EntityGuardian;
/*     */ 
/*     */       
/* 389 */       this.c = param1EntityGuardian instanceof EntityGuardianElder;
/*     */       
/* 391 */       a(3);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 396 */       EntityLiving entityLiving = this.a.getGoalTarget();
/* 397 */       return (entityLiving != null && entityLiving.isAlive());
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean b() {
/* 402 */       return (super.b() && (this.c || this.a.h(this.a.getGoalTarget()) > 9.0D));
/*     */     }
/*     */ 
/*     */     
/*     */     public void c() {
/* 407 */       this.b = -10;
/* 408 */       this.a.getNavigation().p();
/* 409 */       this.a.getControllerLook().a(this.a.getGoalTarget(), 90.0F, 90.0F);
/*     */ 
/*     */       
/* 412 */       this.a.impulse = true;
/*     */     }
/*     */ 
/*     */     
/*     */     public void d() {
/* 417 */       EntityGuardian.a(this.a, 0);
/* 418 */       this.a.setGoalTarget((EntityLiving)null);
/*     */       
/* 420 */       this.a.goalRandomStroll.i();
/*     */     }
/*     */ 
/*     */     
/*     */     public void e() {
/* 425 */       EntityLiving entityLiving = this.a.getGoalTarget();
/*     */       
/* 427 */       this.a.getNavigation().p();
/* 428 */       this.a.getControllerLook().a(entityLiving, 90.0F, 90.0F);
/*     */       
/* 430 */       if (!this.a.hasLineOfSight(entityLiving)) {
/* 431 */         this.a.setGoalTarget((EntityLiving)null);
/*     */         
/*     */         return;
/*     */       } 
/* 435 */       this.b++;
/* 436 */       if (this.b == 0) {
/*     */         
/* 438 */         EntityGuardian.a(this.a, this.a.getGoalTarget().getId());
/* 439 */         this.a.world.broadcastEntityEffect(this.a, (byte)21);
/* 440 */       } else if (this.b >= this.a.p()) {
/* 441 */         float f = 1.0F;
/* 442 */         if (this.a.world.getDifficulty() == EnumDifficulty.HARD) {
/* 443 */           f += 2.0F;
/*     */         }
/* 445 */         if (this.c) {
/* 446 */           f += 2.0F;
/*     */         }
/* 448 */         entityLiving.damageEntity(DamageSource.b(this.a, this.a), f);
/* 449 */         entityLiving.damageEntity(DamageSource.mobAttack(this.a), (float)this.a.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).getValue());
/* 450 */         this.a.setGoalTarget((EntityLiving)null);
/*     */       } 
/*     */       
/* 453 */       super.e();
/*     */     }
/*     */   }
/*     */   
/*     */   static class ControllerMoveGuardian extends ControllerMove {
/*     */     private final EntityGuardian i;
/*     */     
/*     */     public ControllerMoveGuardian(EntityGuardian param1EntityGuardian) {
/* 461 */       super(param1EntityGuardian);
/* 462 */       this.i = param1EntityGuardian;
/*     */     }
/*     */ 
/*     */     
/*     */     public void a() {
/* 467 */       if (this.h != ControllerMove.Operation.MOVE_TO || this.i.getNavigation().o()) {
/*     */         
/* 469 */         this.i.k(0.0F);
/* 470 */         EntityGuardian.a(this.i, false);
/*     */         
/*     */         return;
/*     */       } 
/* 474 */       double d1 = this.b - this.i.locX;
/* 475 */       double d2 = this.c - this.i.locY;
/* 476 */       double d3 = this.d - this.i.locZ;
/* 477 */       double d4 = MathHelper.sqrt(d1 * d1 + d2 * d2 + d3 * d3);
/* 478 */       d2 /= d4;
/*     */       
/* 480 */       float f1 = (float)(MathHelper.c(d3, d1) * 57.2957763671875D) - 90.0F;
/*     */       
/* 482 */       this.i.yaw = a(this.i.yaw, f1, 90.0F);
/* 483 */       this.i.aN = this.i.yaw;
/*     */       
/* 485 */       float f2 = (float)(this.e * this.i.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).getValue());
/* 486 */       this.i.k(this.i.cy() + (f2 - this.i.cy()) * 0.125F);
/* 487 */       double d5 = Math.sin((this.i.ticksLived + this.i.getId()) * 0.5D) * 0.05D;
/* 488 */       double d6 = Math.cos((this.i.yaw * 0.017453292F));
/* 489 */       double d7 = Math.sin((this.i.yaw * 0.017453292F));
/* 490 */       this.i.motX += d5 * d6;
/* 491 */       this.i.motZ += d5 * d7;
/*     */       
/* 493 */       d5 = Math.sin((this.i.ticksLived + this.i.getId()) * 0.75D) * 0.05D;
/* 494 */       this.i.motY += d5 * (d7 + d6) * 0.25D;
/* 495 */       this.i.motY += this.i.cy() * d2 * 0.1D;
/*     */       
/* 497 */       ControllerLook controllerLook = this.i.getControllerLook();
/* 498 */       double d8 = this.i.locX + d1 / d4 * 2.0D;
/* 499 */       double d9 = this.i.getHeadHeight() + this.i.locY + d2 / d4;
/* 500 */       double d10 = this.i.locZ + d3 / d4 * 2.0D;
/* 501 */       double d11 = controllerLook.e();
/* 502 */       double d12 = controllerLook.f();
/* 503 */       double d13 = controllerLook.g();
/* 504 */       if (!controllerLook.b()) {
/* 505 */         d11 = d8;
/* 506 */         d12 = d9;
/* 507 */         d13 = d10;
/*     */       } 
/* 509 */       this.i.getControllerLook().a(d11 + (d8 - d11) * 0.125D, d12 + (d9 - d12) * 0.125D, d13 + (d10 - d13) * 0.125D, 10.0F, 40.0F);
/* 510 */       EntityGuardian.a(this.i, true);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityGuardian.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */