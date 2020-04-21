/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.entity.Slime;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.entity.CreatureSpawnEvent;
/*     */ import org.bukkit.event.entity.SlimeSplitEvent;
/*     */ 
/*     */ public class EntitySlime extends EntityInsentient implements IMonster {
/*  10 */   private static final DataWatcherObject<Integer> bv = DataWatcher.a((Class)EntitySlime.class, DataWatcherRegistry.b);
/*     */   public float a;
/*     */   public float b;
/*     */   public float c;
/*     */   private boolean bw;
/*     */   
/*     */   public EntitySlime(World world) {
/*  17 */     super(world);
/*  18 */     this.moveController = new ControllerMoveSlime(this);
/*     */   }
/*     */   
/*     */   protected void r() {
/*  22 */     this.goalSelector.a(1, new PathfinderGoalSlimeRandomJump(this));
/*  23 */     this.goalSelector.a(2, new PathfinderGoalSlimeNearestPlayer(this));
/*  24 */     this.goalSelector.a(3, new PathfinderGoalSlimeRandomDirection(this));
/*  25 */     this.goalSelector.a(5, new PathfinderGoalSlimeIdle(this));
/*  26 */     this.targetSelector.a(1, new PathfinderGoalTargetNearestPlayer(this));
/*  27 */     this.targetSelector.a(3, new PathfinderGoalNearestAttackableTargetInsentient(this, (Class)EntityIronGolem.class));
/*     */   }
/*     */   
/*     */   protected void i() {
/*  31 */     super.i();
/*  32 */     this.datawatcher.register(bv, Integer.valueOf(1));
/*     */   }
/*     */   
/*     */   public void setSize(int i, boolean flag) {
/*  36 */     this.datawatcher.set(bv, Integer.valueOf(i));
/*  37 */     setSize(0.51000005F * i, 0.51000005F * i);
/*  38 */     setPosition(this.locX, this.locY, this.locZ);
/*  39 */     getAttributeInstance(GenericAttributes.maxHealth).setValue((i * i));
/*  40 */     getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue((0.2F + 0.1F * i));
/*  41 */     if (flag) {
/*  42 */       setHealth(getMaxHealth());
/*     */     }
/*     */     
/*  45 */     this.b_ = i;
/*     */   }
/*     */   
/*     */   public int getSize() {
/*  49 */     return ((Integer)this.datawatcher.<Integer>get(bv)).intValue();
/*     */   }
/*     */   
/*     */   public static void c(DataConverterManager dataconvertermanager) {
/*  53 */     EntityInsentient.a(dataconvertermanager, EntitySlime.class);
/*     */   }
/*     */   
/*     */   public void b(NBTTagCompound nbttagcompound) {
/*  57 */     super.b(nbttagcompound);
/*  58 */     nbttagcompound.setInt("Size", getSize() - 1);
/*  59 */     nbttagcompound.setBoolean("wasOnGround", this.bw);
/*     */   }
/*     */   
/*     */   public void a(NBTTagCompound nbttagcompound) {
/*  63 */     super.a(nbttagcompound);
/*  64 */     int i = nbttagcompound.getInt("Size");
/*     */     
/*  66 */     if (i < 0) {
/*  67 */       i = 0;
/*     */     }
/*     */     
/*  70 */     setSize(i + 1, false);
/*  71 */     this.bw = nbttagcompound.getBoolean("wasOnGround");
/*     */   }
/*     */   
/*     */   public boolean dm() {
/*  75 */     return (getSize() <= 1);
/*     */   }
/*     */   
/*     */   protected EnumParticle p() {
/*  79 */     return EnumParticle.SLIME;
/*     */   }
/*     */   
/*     */   public void B_() {
/*  83 */     if (!this.world.isClientSide && this.world.getDifficulty() == EnumDifficulty.PEACEFUL && getSize() > 0) {
/*  84 */       this.dead = true;
/*     */     }
/*     */     
/*  87 */     this.b += (this.a - this.b) * 0.5F;
/*  88 */     this.c = this.b;
/*  89 */     super.B_();
/*  90 */     if (this.onGround && !this.bw) {
/*  91 */       int i = getSize();
/*     */       
/*  93 */       for (int j = 0; j < i * 8; j++) {
/*  94 */         float f = this.random.nextFloat() * 6.2831855F;
/*  95 */         float f1 = this.random.nextFloat() * 0.5F + 0.5F;
/*  96 */         float f2 = MathHelper.sin(f) * i * 0.5F * f1;
/*  97 */         float f3 = MathHelper.cos(f) * i * 0.5F * f1;
/*  98 */         World world = this.world;
/*  99 */         EnumParticle enumparticle = p();
/* 100 */         double d0 = this.locX + f2;
/* 101 */         double d1 = this.locZ + f3;
/*     */         
/* 103 */         world.addParticle(enumparticle, d0, (getBoundingBox()).b, d1, 0.0D, 0.0D, 0.0D, new int[0]);
/*     */       } 
/*     */       
/* 106 */       a(dj(), cq(), ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F) / 0.8F);
/* 107 */       this.a = -0.5F;
/* 108 */     } else if (!this.onGround && this.bw) {
/* 109 */       this.a = 1.0F;
/*     */     } 
/*     */     
/* 112 */     this.bw = this.onGround;
/* 113 */     dg();
/*     */   }
/*     */   
/*     */   protected void dg() {
/* 117 */     this.a *= 0.6F;
/*     */   }
/*     */   
/*     */   protected int df() {
/* 121 */     return this.random.nextInt(20) + 10;
/*     */   }
/*     */   
/*     */   protected EntitySlime de() {
/* 125 */     return new EntitySlime(this.world);
/*     */   }
/*     */   
/*     */   public void a(DataWatcherObject<?> datawatcherobject) {
/* 129 */     if (bv.equals(datawatcherobject)) {
/* 130 */       int i = getSize();
/*     */       
/* 132 */       setSize(0.51000005F * i, 0.51000005F * i);
/* 133 */       this.yaw = this.aP;
/* 134 */       this.aN = this.aP;
/* 135 */       if (isInWater() && this.random.nextInt(20) == 0) {
/* 136 */         ar();
/*     */       }
/*     */     } 
/*     */     
/* 140 */     super.a(datawatcherobject);
/*     */   }
/*     */   
/*     */   public void die() {
/* 144 */     int i = getSize();
/*     */     
/* 146 */     if (!this.world.isClientSide && i > 1 && getHealth() <= 0.0F) {
/* 147 */       int j = 2 + this.random.nextInt(3);
/*     */ 
/*     */       
/* 150 */       SlimeSplitEvent event = new SlimeSplitEvent((Slime)getBukkitEntity(), j);
/* 151 */       this.world.getServer().getPluginManager().callEvent((Event)event);
/*     */       
/* 153 */       if (!event.isCancelled() && event.getCount() > 0) {
/* 154 */         j = event.getCount();
/*     */       } else {
/* 156 */         super.die();
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 161 */       for (int k = 0; k < j; k++) {
/* 162 */         float f = ((k % 2) - 0.5F) * i / 4.0F;
/* 163 */         float f1 = ((k / 2) - 0.5F) * i / 4.0F;
/* 164 */         EntitySlime entityslime = de();
/*     */         
/* 166 */         if (hasCustomName()) {
/* 167 */           entityslime.setCustomName(getCustomName());
/*     */         }
/*     */         
/* 170 */         if (isPersistent()) {
/* 171 */           entityslime.cW();
/*     */         }
/*     */         
/* 174 */         entityslime.setSize(i / 2, true);
/* 175 */         entityslime.setPositionRotation(this.locX + f, this.locY + 0.5D, this.locZ + f1, this.random.nextFloat() * 360.0F, 0.0F);
/* 176 */         this.world.addEntity(entityslime, CreatureSpawnEvent.SpawnReason.SLIME_SPLIT);
/*     */       } 
/*     */     } 
/*     */     
/* 180 */     super.die();
/*     */   }
/*     */   
/*     */   public void collide(Entity entity) {
/* 184 */     super.collide(entity);
/* 185 */     if (entity instanceof EntityIronGolem && dh()) {
/* 186 */       e((EntityLiving)entity);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void d(EntityHuman entityhuman) {
/* 192 */     if (dh()) {
/* 193 */       e(entityhuman);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void e(EntityLiving entityliving) {
/* 199 */     int i = getSize();
/*     */     
/* 201 */     if (hasLineOfSight(entityliving) && h(entityliving) < 0.6D * i * 0.6D * i && entityliving.damageEntity(DamageSource.mobAttack(this), di())) {
/* 202 */       a(SoundEffects.gY, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
/* 203 */       a(this, entityliving);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public float getHeadHeight() {
/* 209 */     return 0.625F * this.length;
/*     */   }
/*     */   
/*     */   protected boolean dh() {
/* 213 */     return !dm();
/*     */   }
/*     */   
/*     */   protected int di() {
/* 217 */     return getSize();
/*     */   }
/*     */   
/*     */   protected SoundEffect d(DamageSource damagesource) {
/* 221 */     return dm() ? SoundEffects.hm : SoundEffects.hd;
/*     */   }
/*     */   
/*     */   protected SoundEffect cf() {
/* 225 */     return dm() ? SoundEffects.hl : SoundEffects.ha;
/*     */   }
/*     */   
/*     */   protected SoundEffect dj() {
/* 229 */     return dm() ? SoundEffects.ho : SoundEffects.hg;
/*     */   }
/*     */   
/*     */   protected Item getLoot() {
/* 233 */     return (getSize() == 1) ? Items.SLIME : null;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   protected MinecraftKey J() {
/* 238 */     return (getSize() == 1) ? LootTables.ah : LootTables.a;
/*     */   }
/*     */   
/*     */   public boolean P() {
/* 242 */     BlockPosition blockposition = new BlockPosition(MathHelper.floor(this.locX), 0, MathHelper.floor(this.locZ));
/* 243 */     Chunk chunk = this.world.getChunkAtWorldCoords(blockposition);
/*     */     
/* 245 */     if (this.world.getWorldData().getType() == WorldType.FLAT && this.random.nextInt(4) != 1) {
/* 246 */       return false;
/*     */     }
/* 248 */     if (this.world.getDifficulty() != EnumDifficulty.PEACEFUL) {
/* 249 */       BiomeBase biomebase = this.world.getBiome(blockposition);
/*     */       
/* 251 */       if (biomebase == Biomes.h && this.locY > 50.0D && this.locY < 70.0D && this.random.nextFloat() < 0.5F && this.random.nextFloat() < this.world.G() && this.world.getLightLevel(new BlockPosition(this)) <= this.random.nextInt(8)) {
/* 252 */         return super.P();
/*     */       }
/*     */       
/* 255 */       if (this.random.nextInt(10) == 0 && chunk.a(this.world.spigotConfig.slimeSeed).nextInt(10) == 0 && this.locY < 40.0D) {
/* 256 */         return super.P();
/*     */       }
/*     */     } 
/*     */     
/* 260 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected float cq() {
/* 265 */     return 0.4F * getSize();
/*     */   }
/*     */   
/*     */   public int N() {
/* 269 */     return 0;
/*     */   }
/*     */   
/*     */   protected boolean dn() {
/* 273 */     return (getSize() > 0);
/*     */   }
/*     */   
/*     */   protected void cu() {
/* 277 */     this.motY = 0.41999998688697815D;
/* 278 */     this.impulse = true;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public GroupDataEntity prepare(DifficultyDamageScaler difficultydamagescaler, @Nullable GroupDataEntity groupdataentity) {
/* 283 */     int i = this.random.nextInt(3);
/*     */     
/* 285 */     if (i < 2 && this.random.nextFloat() < 0.5F * difficultydamagescaler.d()) {
/* 286 */       i++;
/*     */     }
/*     */     
/* 289 */     int j = 1 << i;
/*     */     
/* 291 */     setSize(j, true);
/* 292 */     return super.prepare(difficultydamagescaler, groupdataentity);
/*     */   }
/*     */   
/*     */   protected SoundEffect dk() {
/* 296 */     return dm() ? SoundEffects.hn : SoundEffects.he;
/*     */   }
/*     */   
/*     */   static class PathfinderGoalSlimeIdle
/*     */     extends PathfinderGoal {
/*     */     private final EntitySlime a;
/*     */     
/*     */     public PathfinderGoalSlimeIdle(EntitySlime entityslime) {
/* 304 */       this.a = entityslime;
/* 305 */       a(5);
/*     */     }
/*     */     
/*     */     public boolean a() {
/* 309 */       return true;
/*     */     }
/*     */     
/*     */     public void e() {
/* 313 */       ((EntitySlime.ControllerMoveSlime)this.a.getControllerMove()).a(1.0D);
/*     */     }
/*     */   }
/*     */   
/*     */   static class PathfinderGoalSlimeRandomJump
/*     */     extends PathfinderGoal {
/*     */     private final EntitySlime a;
/*     */     
/*     */     public PathfinderGoalSlimeRandomJump(EntitySlime entityslime) {
/* 322 */       this.a = entityslime;
/* 323 */       a(5);
/* 324 */       ((Navigation)entityslime.getNavigation()).c(true);
/*     */     }
/*     */     
/*     */     public boolean a() {
/* 328 */       return !(!this.a.isInWater() && !this.a.au());
/*     */     }
/*     */     
/*     */     public void e() {
/* 332 */       if (this.a.getRandom().nextFloat() < 0.8F) {
/* 333 */         this.a.getControllerJump().a();
/*     */       }
/*     */       
/* 336 */       ((EntitySlime.ControllerMoveSlime)this.a.getControllerMove()).a(1.2D);
/*     */     }
/*     */   }
/*     */   
/*     */   static class PathfinderGoalSlimeRandomDirection
/*     */     extends PathfinderGoal {
/*     */     private final EntitySlime a;
/*     */     private float b;
/*     */     private int c;
/*     */     
/*     */     public PathfinderGoalSlimeRandomDirection(EntitySlime entityslime) {
/* 347 */       this.a = entityslime;
/* 348 */       a(2);
/*     */     }
/*     */     
/*     */     public boolean a() {
/* 352 */       return (this.a.getGoalTarget() == null && (this.a.onGround || this.a.isInWater() || this.a.au() || this.a.hasEffect(MobEffects.LEVITATION)));
/*     */     }
/*     */     
/*     */     public void e() {
/* 356 */       if (--this.c <= 0) {
/* 357 */         this.c = 40 + this.a.getRandom().nextInt(60);
/* 358 */         this.b = this.a.getRandom().nextInt(360);
/*     */       } 
/*     */       
/* 361 */       ((EntitySlime.ControllerMoveSlime)this.a.getControllerMove()).a(this.b, false);
/*     */     }
/*     */   }
/*     */   
/*     */   static class PathfinderGoalSlimeNearestPlayer
/*     */     extends PathfinderGoal {
/*     */     private final EntitySlime a;
/*     */     private int b;
/*     */     
/*     */     public PathfinderGoalSlimeNearestPlayer(EntitySlime entityslime) {
/* 371 */       this.a = entityslime;
/* 372 */       a(2);
/*     */     }
/*     */     
/*     */     public boolean a() {
/* 376 */       EntityLiving entityliving = this.a.getGoalTarget();
/*     */       
/* 378 */       return (entityliving == null) ? false : (!entityliving.isAlive() ? false : (!(entityliving instanceof EntityHuman && ((EntityHuman)entityliving).abilities.isInvulnerable)));
/*     */     }
/*     */     
/*     */     public void c() {
/* 382 */       this.b = 300;
/* 383 */       super.c();
/*     */     }
/*     */     
/*     */     public boolean b() {
/* 387 */       EntityLiving entityliving = this.a.getGoalTarget();
/*     */       
/* 389 */       return (entityliving == null) ? false : (!entityliving.isAlive() ? false : ((entityliving instanceof EntityHuman && ((EntityHuman)entityliving).abilities.isInvulnerable) ? false : ((--this.b > 0))));
/*     */     }
/*     */     
/*     */     public void e() {
/* 393 */       this.a.a(this.a.getGoalTarget(), 10.0F, 10.0F);
/* 394 */       ((EntitySlime.ControllerMoveSlime)this.a.getControllerMove()).a(this.a.yaw, this.a.dh());
/*     */     }
/*     */   }
/*     */   
/*     */   static class ControllerMoveSlime
/*     */     extends ControllerMove {
/*     */     private float i;
/*     */     private int j;
/*     */     private final EntitySlime k;
/*     */     private boolean l;
/*     */     
/*     */     public ControllerMoveSlime(EntitySlime entityslime) {
/* 406 */       super(entityslime);
/* 407 */       this.k = entityslime;
/* 408 */       this.i = 180.0F * entityslime.yaw / 3.1415927F;
/*     */     }
/*     */     
/*     */     public void a(float f, boolean flag) {
/* 412 */       this.i = f;
/* 413 */       this.l = flag;
/*     */     }
/*     */     
/*     */     public void a(double d0) {
/* 417 */       this.e = d0;
/* 418 */       this.h = ControllerMove.Operation.MOVE_TO;
/*     */     }
/*     */     
/*     */     public void a() {
/* 422 */       this.a.yaw = a(this.a.yaw, this.i, 90.0F);
/* 423 */       this.a.aP = this.a.yaw;
/* 424 */       this.a.aN = this.a.yaw;
/* 425 */       if (this.h != ControllerMove.Operation.MOVE_TO) {
/* 426 */         this.a.n(0.0F);
/*     */       } else {
/* 428 */         this.h = ControllerMove.Operation.WAIT;
/* 429 */         if (this.a.onGround) {
/* 430 */           this.a.k((float)(this.e * this.a.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).getValue()));
/* 431 */           if (this.j-- <= 0) {
/* 432 */             this.j = this.k.df();
/* 433 */             if (this.l) {
/* 434 */               this.j /= 3;
/*     */             }
/*     */             
/* 437 */             this.k.getControllerJump().a();
/* 438 */             if (this.k.dn()) {
/* 439 */               this.k.a(this.k.dk(), this.k.cq(), ((this.k.getRandom().nextFloat() - this.k.getRandom().nextFloat()) * 0.2F + 1.0F) * 0.8F);
/*     */             }
/*     */           } else {
/* 442 */             this.k.be = 0.0F;
/* 443 */             this.k.bg = 0.0F;
/* 444 */             this.a.k(0.0F);
/*     */           } 
/*     */         } else {
/* 447 */           this.a.k((float)(this.e * this.a.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).getValue()));
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntitySlime.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */