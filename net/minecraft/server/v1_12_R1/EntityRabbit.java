/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*     */ 
/*     */ public class EntityRabbit extends EntityAnimal {
/*   7 */   private static final DataWatcherObject<Integer> bx = DataWatcher.a((Class)EntityRabbit.class, DataWatcherRegistry.b);
/*     */   private int by;
/*     */   private int bz;
/*     */   private boolean bB;
/*     */   private int bC;
/*     */   private int bD;
/*     */   
/*     */   public EntityRabbit(World world) {
/*  15 */     super(world);
/*  16 */     setSize(0.4F, 0.5F);
/*  17 */     this.g = new ControllerJumpRabbit(this);
/*  18 */     this.moveController = new ControllerMoveRabbit(this);
/*  19 */     initializePathFinderGoals();
/*     */   }
/*     */ 
/*     */   
/*     */   public void initializePathFinderGoals() {
/*  24 */     c(0.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void r() {
/*  29 */     this.goalSelector.a(1, new PathfinderGoalFloat(this));
/*  30 */     this.goalSelector.a(1, new PathfinderGoalRabbitPanic(this, 2.2D));
/*  31 */     this.goalSelector.a(2, new PathfinderGoalBreed(this, 0.8D));
/*  32 */     this.goalSelector.a(3, new PathfinderGoalTempt(this, 1.0D, Items.CARROT, false));
/*  33 */     this.goalSelector.a(3, new PathfinderGoalTempt(this, 1.0D, Items.GOLDEN_CARROT, false));
/*  34 */     this.goalSelector.a(3, new PathfinderGoalTempt(this, 1.0D, Item.getItemOf(Blocks.YELLOW_FLOWER), false));
/*  35 */     this.goalSelector.a(4, new PathfinderGoalRabbitAvoidTarget<>(this, EntityHuman.class, 8.0F, 2.2D, 2.2D));
/*  36 */     this.goalSelector.a(4, new PathfinderGoalRabbitAvoidTarget<>(this, EntityWolf.class, 10.0F, 2.2D, 2.2D));
/*  37 */     this.goalSelector.a(4, new PathfinderGoalRabbitAvoidTarget<>(this, EntityMonster.class, 4.0F, 2.2D, 2.2D));
/*  38 */     this.goalSelector.a(5, new PathfinderGoalEatCarrots(this));
/*  39 */     this.goalSelector.a(6, new PathfinderGoalRandomStrollLand(this, 0.6D));
/*  40 */     this.goalSelector.a(11, new PathfinderGoalLookAtPlayer(this, (Class)EntityHuman.class, 10.0F));
/*     */   }
/*     */   
/*     */   protected float ct() {
/*  44 */     if (!this.positionChanged && (!this.moveController.b() || this.moveController.e() <= this.locY + 0.5D)) {
/*  45 */       PathEntity pathentity = this.navigation.l();
/*     */       
/*  47 */       if (pathentity != null && pathentity.e() < pathentity.d()) {
/*  48 */         Vec3D vec3d = pathentity.a(this);
/*     */         
/*  50 */         if (vec3d.y > this.locY + 0.5D) {
/*  51 */           return 0.5F;
/*     */         }
/*     */       } 
/*     */       
/*  55 */       return (this.moveController.c() <= 0.6D) ? 0.2F : 0.3F;
/*     */     } 
/*  57 */     return 0.5F;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void cu() {
/*  62 */     super.cu();
/*  63 */     double d0 = this.moveController.c();
/*     */     
/*  65 */     if (d0 > 0.0D) {
/*  66 */       double d1 = this.motX * this.motX + this.motZ * this.motZ;
/*     */       
/*  68 */       if (d1 < 0.010000000000000002D) {
/*  69 */         b(0.0F, 0.0F, 1.0F, 0.1F);
/*     */       }
/*     */     } 
/*     */     
/*  73 */     if (!this.world.isClientSide) {
/*  74 */       this.world.broadcastEntityEffect(this, (byte)1);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void c(double d0) {
/*  80 */     getNavigation().a(d0);
/*  81 */     this.moveController.a(this.moveController.d(), this.moveController.e(), this.moveController.f(), d0);
/*     */   }
/*     */   
/*     */   public void l(boolean flag) {
/*  85 */     super.l(flag);
/*  86 */     if (flag) {
/*  87 */       a(dm(), cq(), ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F) * 0.8F);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void dl() {
/*  93 */     l(true);
/*  94 */     this.bz = 10;
/*  95 */     this.by = 0;
/*     */   }
/*     */   
/*     */   protected void i() {
/*  99 */     super.i();
/* 100 */     this.datawatcher.register(bx, Integer.valueOf(0));
/*     */   }
/*     */   
/*     */   public void M() {
/* 104 */     if (this.bC > 0) {
/* 105 */       this.bC--;
/*     */     }
/*     */     
/* 108 */     if (this.bD > 0) {
/* 109 */       this.bD -= this.random.nextInt(3);
/* 110 */       if (this.bD < 0) {
/* 111 */         this.bD = 0;
/*     */       }
/*     */     } 
/*     */     
/* 115 */     if (this.onGround) {
/* 116 */       if (!this.bB) {
/* 117 */         l(false);
/* 118 */         dv();
/*     */       } 
/*     */       
/* 121 */       if (getRabbitType() == 99 && this.bC == 0) {
/* 122 */         EntityLiving entityliving = getGoalTarget();
/*     */         
/* 124 */         if (entityliving != null && h(entityliving) < 16.0D) {
/* 125 */           a(entityliving.locX, entityliving.locZ);
/* 126 */           this.moveController.a(entityliving.locX, entityliving.locY, entityliving.locZ, this.moveController.c());
/* 127 */           dl();
/* 128 */           this.bB = true;
/*     */         } 
/*     */       } 
/*     */       
/* 132 */       ControllerJumpRabbit entityrabbit_controllerjumprabbit = (ControllerJumpRabbit)this.g;
/*     */       
/* 134 */       if (!entityrabbit_controllerjumprabbit.c()) {
/* 135 */         if (this.moveController.b() && this.bC == 0) {
/* 136 */           PathEntity pathentity = this.navigation.l();
/* 137 */           Vec3D vec3d = new Vec3D(this.moveController.d(), this.moveController.e(), this.moveController.f());
/*     */           
/* 139 */           if (pathentity != null && pathentity.e() < pathentity.d()) {
/* 140 */             vec3d = pathentity.a(this);
/*     */           }
/*     */           
/* 143 */           a(vec3d.x, vec3d.z);
/* 144 */           dl();
/*     */         } 
/* 146 */       } else if (!entityrabbit_controllerjumprabbit.d()) {
/* 147 */         dp();
/*     */       } 
/*     */     } 
/*     */     
/* 151 */     this.bB = this.onGround;
/*     */   }
/*     */   
/*     */   public void as() {}
/*     */   
/*     */   private void a(double d0, double d1) {
/* 157 */     this.yaw = (float)(MathHelper.c(d1 - this.locZ, d0 - this.locX) * 57.2957763671875D) - 90.0F;
/*     */   }
/*     */   
/*     */   private void dp() {
/* 161 */     ((ControllerJumpRabbit)this.g).a(true);
/*     */   }
/*     */   
/*     */   private void dt() {
/* 165 */     ((ControllerJumpRabbit)this.g).a(false);
/*     */   }
/*     */   
/*     */   private void du() {
/* 169 */     if (this.moveController.c() < 2.2D) {
/* 170 */       this.bC = 10;
/*     */     } else {
/* 172 */       this.bC = 1;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void dv() {
/* 178 */     du();
/* 179 */     dt();
/*     */   }
/*     */   
/*     */   public void n() {
/* 183 */     super.n();
/* 184 */     if (this.by != this.bz) {
/* 185 */       this.by++;
/* 186 */     } else if (this.bz != 0) {
/* 187 */       this.by = 0;
/* 188 */       this.bz = 0;
/* 189 */       l(false);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initAttributes() {
/* 195 */     super.initAttributes();
/* 196 */     getAttributeInstance(GenericAttributes.maxHealth).setValue(3.0D);
/* 197 */     getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.30000001192092896D);
/*     */   }
/*     */   
/*     */   public static void a(DataConverterManager dataconvertermanager) {
/* 201 */     EntityInsentient.a(dataconvertermanager, EntityRabbit.class);
/*     */   }
/*     */   
/*     */   public void b(NBTTagCompound nbttagcompound) {
/* 205 */     super.b(nbttagcompound);
/* 206 */     nbttagcompound.setInt("RabbitType", getRabbitType());
/* 207 */     nbttagcompound.setInt("MoreCarrotTicks", this.bD);
/*     */   }
/*     */   
/*     */   public void a(NBTTagCompound nbttagcompound) {
/* 211 */     super.a(nbttagcompound);
/* 212 */     setRabbitType(nbttagcompound.getInt("RabbitType"));
/* 213 */     this.bD = nbttagcompound.getInt("MoreCarrotTicks");
/*     */   }
/*     */   
/*     */   protected SoundEffect dm() {
/* 217 */     return SoundEffects.fZ;
/*     */   }
/*     */   
/*     */   protected SoundEffect F() {
/* 221 */     return SoundEffects.fV;
/*     */   }
/*     */   
/*     */   protected SoundEffect d(DamageSource damagesource) {
/* 225 */     return SoundEffects.fY;
/*     */   }
/*     */   
/*     */   protected SoundEffect cf() {
/* 229 */     return SoundEffects.fX;
/*     */   }
/*     */   
/*     */   public boolean B(Entity entity) {
/* 233 */     if (getRabbitType() == 99) {
/* 234 */       a(SoundEffects.fW, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
/* 235 */       return entity.damageEntity(DamageSource.mobAttack(this), 8.0F);
/*     */     } 
/* 237 */     return entity.damageEntity(DamageSource.mobAttack(this), 3.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public SoundCategory bK() {
/* 242 */     return (getRabbitType() == 99) ? SoundCategory.HOSTILE : SoundCategory.NEUTRAL;
/*     */   }
/*     */   
/*     */   public boolean damageEntity(DamageSource damagesource, float f) {
/* 246 */     return isInvulnerable(damagesource) ? false : super.damageEntity(damagesource, f);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   protected MinecraftKey J() {
/* 251 */     return LootTables.C;
/*     */   }
/*     */   
/*     */   private boolean a(Item item) {
/* 255 */     return !(item != Items.CARROT && item != Items.GOLDEN_CARROT && item != Item.getItemOf(Blocks.YELLOW_FLOWER));
/*     */   }
/*     */   
/*     */   public EntityRabbit b(EntityAgeable entityageable) {
/* 259 */     EntityRabbit entityrabbit = new EntityRabbit(this.world);
/* 260 */     int i = dw();
/*     */     
/* 262 */     if (this.random.nextInt(20) != 0) {
/* 263 */       if (entityageable instanceof EntityRabbit && this.random.nextBoolean()) {
/* 264 */         i = ((EntityRabbit)entityageable).getRabbitType();
/*     */       } else {
/* 266 */         i = getRabbitType();
/*     */       } 
/*     */     }
/*     */     
/* 270 */     entityrabbit.setRabbitType(i);
/* 271 */     return entityrabbit;
/*     */   }
/*     */   
/*     */   public boolean e(ItemStack itemstack) {
/* 275 */     return a(itemstack.getItem());
/*     */   }
/*     */   
/*     */   public int getRabbitType() {
/* 279 */     return ((Integer)this.datawatcher.<Integer>get(bx)).intValue();
/*     */   }
/*     */   
/*     */   public void setRabbitType(int i) {
/* 283 */     if (i == 99) {
/* 284 */       getAttributeInstance(GenericAttributes.h).setValue(8.0D);
/* 285 */       this.goalSelector.a(4, new PathfinderGoalKillerRabbitMeleeAttack(this));
/* 286 */       this.targetSelector.a(1, new PathfinderGoalHurtByTarget(this, false, new Class[0]));
/* 287 */       this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget<>(this, EntityHuman.class, true));
/* 288 */       this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget<>(this, EntityWolf.class, true));
/* 289 */       if (!hasCustomName()) {
/* 290 */         setCustomName(LocaleI18n.get("entity.KillerBunny.name"));
/*     */       }
/*     */     } 
/*     */     
/* 294 */     this.datawatcher.set(bx, Integer.valueOf(i));
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public GroupDataEntity prepare(DifficultyDamageScaler difficultydamagescaler, @Nullable GroupDataEntity groupdataentity) {
/* 299 */     Object object = super.prepare(difficultydamagescaler, groupdataentity);
/* 300 */     int i = dw();
/* 301 */     boolean flag = false;
/*     */     
/* 303 */     if (object instanceof GroupDataRabbit) {
/* 304 */       i = ((GroupDataRabbit)object).a;
/* 305 */       flag = true;
/*     */     } else {
/* 307 */       object = new GroupDataRabbit(i);
/*     */     } 
/*     */     
/* 310 */     setRabbitType(i);
/* 311 */     if (flag) {
/* 312 */       setAgeRaw(-24000);
/*     */     }
/*     */     
/* 315 */     return (GroupDataEntity)object;
/*     */   }
/*     */   
/*     */   private int dw() {
/* 319 */     BiomeBase biomebase = this.world.getBiome(new BlockPosition(this));
/* 320 */     int i = this.random.nextInt(100);
/*     */     
/* 322 */     return biomebase.p() ? ((i < 80) ? 1 : 3) : ((biomebase instanceof BiomeDesert) ? 4 : ((i < 50) ? 0 : ((i < 90) ? 5 : 2)));
/*     */   }
/*     */   
/*     */   private boolean dx() {
/* 326 */     return (this.bD == 0);
/*     */   }
/*     */   
/*     */   protected void do_() {
/* 330 */     BlockCarrots blockcarrots = (BlockCarrots)Blocks.CARROTS;
/* 331 */     IBlockData iblockdata = blockcarrots.setAge(blockcarrots.g());
/*     */     
/* 333 */     this.world.addParticle(EnumParticle.BLOCK_DUST, this.locX + (this.random.nextFloat() * this.width * 2.0F) - this.width, this.locY + 0.5D + (this.random.nextFloat() * this.length), this.locZ + (this.random.nextFloat() * this.width * 2.0F) - this.width, 0.0D, 0.0D, 0.0D, new int[] { Block.getCombinedId(iblockdata) });
/* 334 */     this.bD = 40;
/*     */   }
/*     */   
/*     */   public EntityAgeable createChild(EntityAgeable entityageable) {
/* 338 */     return b(entityageable);
/*     */   }
/*     */   
/*     */   static class PathfinderGoalKillerRabbitMeleeAttack
/*     */     extends PathfinderGoalMeleeAttack {
/*     */     public PathfinderGoalKillerRabbitMeleeAttack(EntityRabbit entityrabbit) {
/* 344 */       super(entityrabbit, 1.4D, true);
/*     */     }
/*     */     
/*     */     protected double a(EntityLiving entityliving) {
/* 348 */       return (4.0F + entityliving.width);
/*     */     }
/*     */   }
/*     */   
/*     */   static class PathfinderGoalRabbitPanic
/*     */     extends PathfinderGoalPanic {
/*     */     private final EntityRabbit f;
/*     */     
/*     */     public PathfinderGoalRabbitPanic(EntityRabbit entityrabbit, double d0) {
/* 357 */       super(entityrabbit, d0);
/* 358 */       this.f = entityrabbit;
/*     */     }
/*     */     
/*     */     public void e() {
/* 362 */       super.e();
/* 363 */       this.f.c(this.b);
/*     */     }
/*     */   }
/*     */   
/*     */   static class PathfinderGoalEatCarrots
/*     */     extends PathfinderGoalGotoTarget {
/*     */     private final EntityRabbit c;
/*     */     private boolean d;
/*     */     private boolean e;
/*     */     
/*     */     public PathfinderGoalEatCarrots(EntityRabbit entityrabbit) {
/* 374 */       super(entityrabbit, 0.699999988079071D, 16);
/* 375 */       this.c = entityrabbit;
/*     */     }
/*     */     
/*     */     public boolean a() {
/* 379 */       if (this.a <= 0) {
/* 380 */         if (!this.c.world.getGameRules().getBoolean("mobGriefing")) {
/* 381 */           return false;
/*     */         }
/*     */         
/* 384 */         this.e = false;
/* 385 */         this.d = this.c.dx();
/* 386 */         this.d = true;
/*     */       } 
/*     */       
/* 389 */       return super.a();
/*     */     }
/*     */     
/*     */     public boolean b() {
/* 393 */       return (this.e && super.b());
/*     */     }
/*     */     
/*     */     public void e() {
/* 397 */       super.e();
/* 398 */       this.c.getControllerLook().a(this.b.getX() + 0.5D, (this.b.getY() + 1), this.b.getZ() + 0.5D, 10.0F, this.c.N());
/* 399 */       if (f()) {
/* 400 */         World world = this.c.world;
/* 401 */         BlockPosition blockposition = this.b.up();
/* 402 */         IBlockData iblockdata = world.getType(blockposition);
/* 403 */         Block block = iblockdata.getBlock();
/*     */         
/* 405 */         if (this.e && block instanceof BlockCarrots) {
/* 406 */           Integer integer = iblockdata.<Integer>get(BlockCarrots.AGE);
/*     */           
/* 408 */           if (integer.intValue() == 0) {
/*     */             
/* 410 */             if (CraftEventFactory.callEntityChangeBlockEvent(this.c, blockposition, Blocks.AIR, 0).isCancelled()) {
/*     */               return;
/*     */             }
/*     */             
/* 414 */             world.setTypeAndData(blockposition, Blocks.AIR.getBlockData(), 2);
/* 415 */             world.setAir(blockposition, true);
/*     */           } else {
/*     */             
/* 418 */             if (CraftEventFactory.callEntityChangeBlockEvent(
/* 419 */                 this.c, 
/* 420 */                 blockposition, 
/* 421 */                 block, block.toLegacyData(iblockdata.set(BlockCarrots.AGE, Integer.valueOf(integer.intValue() - 1))))
/* 422 */               .isCancelled()) {
/*     */               return;
/*     */             }
/*     */             
/* 426 */             world.setTypeAndData(blockposition, iblockdata.set(BlockCarrots.AGE, Integer.valueOf(integer.intValue() - 1)), 2);
/* 427 */             world.triggerEffect(2001, blockposition, Block.getCombinedId(iblockdata));
/*     */           } 
/*     */           
/* 430 */           this.c.do_();
/*     */         } 
/*     */         
/* 433 */         this.e = false;
/* 434 */         this.a = 10;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     protected boolean a(World world, BlockPosition blockposition) {
/* 440 */       Block block = world.getType(blockposition).getBlock();
/*     */       
/* 442 */       if (block == Blocks.FARMLAND && this.d && !this.e) {
/* 443 */         blockposition = blockposition.up();
/* 444 */         IBlockData iblockdata = world.getType(blockposition);
/*     */         
/* 446 */         block = iblockdata.getBlock();
/* 447 */         if (block instanceof BlockCarrots && ((BlockCarrots)block).z(iblockdata)) {
/* 448 */           this.e = true;
/* 449 */           return true;
/*     */         } 
/*     */       } 
/*     */       
/* 453 */       return false;
/*     */     }
/*     */   }
/*     */   
/*     */   static class PathfinderGoalRabbitAvoidTarget<T extends Entity>
/*     */     extends PathfinderGoalAvoidTarget<T> {
/*     */     private final EntityRabbit c;
/*     */     
/*     */     public PathfinderGoalRabbitAvoidTarget(EntityRabbit entityrabbit, Class<T> oclass, float f, double d0, double d1) {
/* 462 */       super(entityrabbit, oclass, f, d0, d1);
/* 463 */       this.c = entityrabbit;
/*     */     }
/*     */     
/*     */     public boolean a() {
/* 467 */       return (this.c.getRabbitType() != 99 && super.a());
/*     */     }
/*     */   }
/*     */   
/*     */   static class ControllerMoveRabbit
/*     */     extends ControllerMove {
/*     */     private final EntityRabbit i;
/*     */     private double j;
/*     */     
/*     */     public ControllerMoveRabbit(EntityRabbit entityrabbit) {
/* 477 */       super(entityrabbit);
/* 478 */       this.i = entityrabbit;
/*     */     }
/*     */     
/*     */     public void a() {
/* 482 */       if (this.i.onGround && !this.i.bd && !((EntityRabbit.ControllerJumpRabbit)this.i.g).c()) {
/* 483 */         this.i.c(0.0D);
/* 484 */       } else if (b()) {
/* 485 */         this.i.c(this.j);
/*     */       } 
/*     */       
/* 488 */       super.a();
/*     */     }
/*     */     
/*     */     public void a(double d0, double d1, double d2, double d3) {
/* 492 */       if (this.i.isInWater()) {
/* 493 */         d3 = 1.5D;
/*     */       }
/*     */       
/* 496 */       super.a(d0, d1, d2, d3);
/* 497 */       if (d3 > 0.0D) {
/* 498 */         this.j = d3;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public class ControllerJumpRabbit
/*     */     extends ControllerJump
/*     */   {
/*     */     private final EntityRabbit c;
/*     */     private boolean d;
/*     */     
/*     */     public ControllerJumpRabbit(EntityRabbit entityrabbit) {
/* 510 */       super(entityrabbit);
/* 511 */       this.c = entityrabbit;
/*     */     }
/*     */     
/*     */     public boolean c() {
/* 515 */       return this.a;
/*     */     }
/*     */     
/*     */     public boolean d() {
/* 519 */       return this.d;
/*     */     }
/*     */     
/*     */     public void a(boolean flag) {
/* 523 */       this.d = flag;
/*     */     }
/*     */     
/*     */     public void b() {
/* 527 */       if (this.a) {
/* 528 */         this.c.dl();
/* 529 */         this.a = false;
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public static class GroupDataRabbit
/*     */     implements GroupDataEntity
/*     */   {
/*     */     public int a;
/*     */     
/*     */     public GroupDataRabbit(int i) {
/* 540 */       this.a = i;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityRabbit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */