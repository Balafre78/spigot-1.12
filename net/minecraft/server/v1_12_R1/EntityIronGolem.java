/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.event.entity.EntityTargetEvent;
/*     */ 
/*     */ public class EntityIronGolem extends EntityGolem {
/*   8 */   protected static final DataWatcherObject<Byte> a = DataWatcher.a((Class)EntityIronGolem.class, DataWatcherRegistry.a);
/*     */   private int c;
/*     */   @Nullable
/*     */   Village b;
/*     */   private int bx;
/*     */   private int by;
/*     */   
/*     */   public EntityIronGolem(World world) {
/*  16 */     super(world);
/*  17 */     setSize(1.4F, 2.7F);
/*     */   }
/*     */   
/*     */   protected void r() {
/*  21 */     this.goalSelector.a(1, new PathfinderGoalMeleeAttack(this, 1.0D, true));
/*  22 */     this.goalSelector.a(2, new PathfinderGoalMoveTowardsTarget(this, 0.9D, 32.0F));
/*  23 */     this.goalSelector.a(3, new PathfinderGoalMoveThroughVillage(this, 0.6D, true));
/*  24 */     this.goalSelector.a(4, new PathfinderGoalMoveTowardsRestriction(this, 1.0D));
/*  25 */     this.goalSelector.a(5, new PathfinderGoalOfferFlower(this));
/*  26 */     this.goalSelector.a(6, new PathfinderGoalRandomStrollLand(this, 0.6D));
/*  27 */     this.goalSelector.a(7, new PathfinderGoalLookAtPlayer(this, (Class)EntityHuman.class, 6.0F));
/*  28 */     this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
/*  29 */     this.targetSelector.a(1, new PathfinderGoalDefendVillage(this));
/*  30 */     this.targetSelector.a(2, new PathfinderGoalHurtByTarget(this, false, new Class[0]));
/*  31 */     this.targetSelector.a(3, new PathfinderGoalNearestAttackableTarget<>(this, EntityInsentient.class, 10, false, true, new Predicate() {
/*     */             public boolean a(@Nullable EntityInsentient entityinsentient) {
/*  33 */               return (entityinsentient != null && IMonster.e.apply(entityinsentient) && !(entityinsentient instanceof EntityCreeper));
/*     */             }
/*     */             
/*     */             public boolean apply(@Nullable Object object) {
/*  37 */               return a((EntityInsentient)object);
/*     */             }
/*     */           }));
/*     */   }
/*     */   
/*     */   protected void i() {
/*  43 */     super.i();
/*  44 */     this.datawatcher.register(a, Byte.valueOf((byte)0));
/*     */   }
/*     */   
/*     */   protected void M() {
/*  48 */     if (--this.c <= 0) {
/*  49 */       this.c = 70 + this.random.nextInt(50);
/*  50 */       this.b = this.world.ak().getClosestVillage(new BlockPosition(this), 32);
/*  51 */       if (this.b == null) {
/*  52 */         di();
/*     */       } else {
/*  54 */         BlockPosition blockposition = this.b.a();
/*     */         
/*  56 */         a(blockposition, (int)(this.b.b() * 0.6F));
/*     */       } 
/*     */     } 
/*     */     
/*  60 */     super.M();
/*     */   }
/*     */   
/*     */   protected void initAttributes() {
/*  64 */     super.initAttributes();
/*  65 */     getAttributeInstance(GenericAttributes.maxHealth).setValue(100.0D);
/*  66 */     getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.25D);
/*  67 */     getAttributeInstance(GenericAttributes.c).setValue(1.0D);
/*     */   }
/*     */   
/*     */   protected int d(int i) {
/*  71 */     return i;
/*     */   }
/*     */   
/*     */   protected void C(Entity entity) {
/*  75 */     if (entity instanceof IMonster && !(entity instanceof EntityCreeper) && getRandom().nextInt(20) == 0) {
/*  76 */       setGoalTarget((EntityLiving)entity, EntityTargetEvent.TargetReason.COLLISION, true);
/*     */     }
/*     */     
/*  79 */     super.C(entity);
/*     */   }
/*     */   
/*     */   public void n() {
/*  83 */     super.n();
/*  84 */     if (this.bx > 0) {
/*  85 */       this.bx--;
/*     */     }
/*     */     
/*  88 */     if (this.by > 0) {
/*  89 */       this.by--;
/*     */     }
/*     */     
/*  92 */     if (this.motX * this.motX + this.motZ * this.motZ > 2.500000277905201E-7D && this.random.nextInt(5) == 0) {
/*  93 */       int i = MathHelper.floor(this.locX);
/*  94 */       int j = MathHelper.floor(this.locY - 0.20000000298023224D);
/*  95 */       int k = MathHelper.floor(this.locZ);
/*  96 */       IBlockData iblockdata = this.world.getType(new BlockPosition(i, j, k));
/*     */       
/*  98 */       if (iblockdata.getMaterial() != Material.AIR) {
/*  99 */         this.world.addParticle(EnumParticle.BLOCK_CRACK, this.locX + (this.random.nextFloat() - 0.5D) * this.width, (getBoundingBox()).b + 0.1D, this.locZ + (this.random.nextFloat() - 0.5D) * this.width, 4.0D * (this.random.nextFloat() - 0.5D), 0.5D, (this.random.nextFloat() - 0.5D) * 4.0D, new int[] { Block.getCombinedId(iblockdata) });
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean d(Class<? extends EntityLiving> oclass) {
/* 106 */     return (isPlayerCreated() && EntityHuman.class.isAssignableFrom(oclass)) ? false : ((oclass == EntityCreeper.class) ? false : super.d(oclass));
/*     */   }
/*     */   
/*     */   public static void a(DataConverterManager dataconvertermanager) {
/* 110 */     EntityInsentient.a(dataconvertermanager, EntityIronGolem.class);
/*     */   }
/*     */   
/*     */   public void b(NBTTagCompound nbttagcompound) {
/* 114 */     super.b(nbttagcompound);
/* 115 */     nbttagcompound.setBoolean("PlayerCreated", isPlayerCreated());
/*     */   }
/*     */   
/*     */   public void a(NBTTagCompound nbttagcompound) {
/* 119 */     super.a(nbttagcompound);
/* 120 */     setPlayerCreated(nbttagcompound.getBoolean("PlayerCreated"));
/*     */   }
/*     */   
/*     */   public boolean B(Entity entity) {
/* 124 */     this.bx = 10;
/* 125 */     this.world.broadcastEntityEffect(this, (byte)4);
/* 126 */     boolean flag = entity.damageEntity(DamageSource.mobAttack(this), (7 + this.random.nextInt(15)));
/*     */     
/* 128 */     if (flag) {
/* 129 */       entity.motY += 0.4000000059604645D;
/* 130 */       a(this, entity);
/*     */     } 
/*     */     
/* 133 */     a(SoundEffects.dj, 1.0F, 1.0F);
/* 134 */     return flag;
/*     */   }
/*     */   
/*     */   public Village p() {
/* 138 */     return this.b;
/*     */   }
/*     */   
/*     */   public void a(boolean flag) {
/* 142 */     if (flag) {
/* 143 */       this.by = 400;
/* 144 */       this.world.broadcastEntityEffect(this, (byte)11);
/*     */     } else {
/* 146 */       this.by = 0;
/* 147 */       this.world.broadcastEntityEffect(this, (byte)34);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect d(DamageSource damagesource) {
/* 153 */     return SoundEffects.dl;
/*     */   }
/*     */   
/*     */   protected SoundEffect cf() {
/* 157 */     return SoundEffects.dk;
/*     */   }
/*     */   
/*     */   protected void a(BlockPosition blockposition, Block block) {
/* 161 */     a(SoundEffects.dm, 1.0F, 1.0F);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   protected MinecraftKey J() {
/* 166 */     return LootTables.A;
/*     */   }
/*     */   
/*     */   public int dm() {
/* 170 */     return this.by;
/*     */   }
/*     */   
/*     */   public boolean isPlayerCreated() {
/* 174 */     return ((((Byte)this.datawatcher.<Byte>get(a)).byteValue() & 0x1) != 0);
/*     */   }
/*     */   
/*     */   public void setPlayerCreated(boolean flag) {
/* 178 */     byte b0 = ((Byte)this.datawatcher.<Byte>get(a)).byteValue();
/*     */     
/* 180 */     if (flag) {
/* 181 */       this.datawatcher.set(a, Byte.valueOf((byte)(b0 | 0x1)));
/*     */     } else {
/* 183 */       this.datawatcher.set(a, Byte.valueOf((byte)(b0 & 0xFFFFFFFE)));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void die(DamageSource damagesource) {
/* 189 */     if (!isPlayerCreated() && this.killer != null && this.b != null) {
/* 190 */       this.b.a(this.killer.getName(), -5);
/*     */     }
/*     */     
/* 193 */     super.die(damagesource);
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityIronGolem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */