/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
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
/*     */ public class EntityBlaze
/*     */   extends EntityMonster
/*     */ {
/*  36 */   private float a = 0.5F;
/*     */   
/*     */   private int b;
/*  39 */   private static final DataWatcherObject<Byte> c = DataWatcher.a((Class)EntityBlaze.class, DataWatcherRegistry.a);
/*     */   
/*     */   public EntityBlaze(World paramWorld) {
/*  42 */     super(paramWorld);
/*     */     
/*  44 */     a(PathType.WATER, -1.0F);
/*  45 */     a(PathType.LAVA, 8.0F);
/*  46 */     a(PathType.DANGER_FIRE, 0.0F);
/*  47 */     a(PathType.DAMAGE_FIRE, 0.0F);
/*  48 */     this.fireProof = true;
/*  49 */     this.b_ = 10;
/*     */   }
/*     */   
/*     */   public static void a(DataConverterManager paramDataConverterManager) {
/*  53 */     EntityInsentient.a(paramDataConverterManager, EntityBlaze.class);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void r() {
/*  58 */     this.goalSelector.a(4, new PathfinderGoalBlazeFireball(this));
/*  59 */     this.goalSelector.a(5, new PathfinderGoalMoveTowardsRestriction(this, 1.0D));
/*  60 */     this.goalSelector.a(7, new PathfinderGoalRandomStrollLand(this, 1.0D, 0.0F));
/*  61 */     this.goalSelector.a(8, new PathfinderGoalLookAtPlayer(this, (Class)EntityHuman.class, 8.0F));
/*  62 */     this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
/*     */     
/*  64 */     this.targetSelector.a(1, new PathfinderGoalHurtByTarget(this, true, new Class[0]));
/*  65 */     this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget<>(this, EntityHuman.class, true));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initAttributes() {
/*  70 */     super.initAttributes();
/*  71 */     getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(6.0D);
/*  72 */     getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.23000000417232513D);
/*  73 */     getAttributeInstance(GenericAttributes.FOLLOW_RANGE).setValue(48.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void i() {
/*  78 */     super.i();
/*     */     
/*  80 */     this.datawatcher.register(c, Byte.valueOf((byte)0));
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect F() {
/*  85 */     return SoundEffects.C;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect d(DamageSource paramDamageSource) {
/*  90 */     return SoundEffects.F;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect cf() {
/*  95 */     return SoundEffects.E;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float aw() {
/* 105 */     return 1.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void n() {
/* 111 */     if (!this.onGround && this.motY < 0.0D) {
/* 112 */       this.motY *= 0.6D;
/*     */     }
/*     */     
/* 115 */     if (this.world.isClientSide) {
/* 116 */       if (this.random.nextInt(24) == 0 && !isSilent()) {
/* 117 */         this.world.a(this.locX + 0.5D, this.locY + 0.5D, this.locZ + 0.5D, SoundEffects.D, bK(), 1.0F + this.random.nextFloat(), this.random.nextFloat() * 0.7F + 0.3F, false);
/*     */       }
/* 119 */       for (byte b = 0; b < 2; b++) {
/* 120 */         this.world.addParticle(EnumParticle.SMOKE_LARGE, this.locX + (this.random.nextDouble() - 0.5D) * this.width, this.locY + this.random.nextDouble() * this.length, this.locZ + (this.random.nextDouble() - 0.5D) * this.width, 0.0D, 0.0D, 0.0D, new int[0]);
/*     */       }
/*     */     } 
/*     */     
/* 124 */     super.n();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void M() {
/* 129 */     if (an()) {
/* 130 */       damageEntity(DamageSource.DROWN, 1.0F);
/*     */     }
/*     */     
/* 133 */     this.b--;
/* 134 */     if (this.b <= 0) {
/* 135 */       this.b = 100;
/* 136 */       this.a = 0.5F + (float)this.random.nextGaussian() * 3.0F;
/*     */     } 
/*     */     
/* 139 */     EntityLiving entityLiving = getGoalTarget();
/* 140 */     if (entityLiving != null && entityLiving.locY + entityLiving.getHeadHeight() > this.locY + getHeadHeight() + this.a) {
/* 141 */       this.motY += (0.30000001192092896D - this.motY) * 0.30000001192092896D;
/* 142 */       this.impulse = true;
/*     */     } 
/*     */     
/* 145 */     super.M();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void e(float paramFloat1, float paramFloat2) {}
/*     */ 
/*     */   
/*     */   public boolean isBurning() {
/* 154 */     return p();
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   protected MinecraftKey J() {
/* 160 */     return LootTables.q;
/*     */   }
/*     */   
/*     */   public boolean p() {
/* 164 */     return ((((Byte)this.datawatcher.<Byte>get(c)).byteValue() & 0x1) != 0);
/*     */   }
/*     */   
/*     */   public void a(boolean paramBoolean) {
/* 168 */     byte b = ((Byte)this.datawatcher.<Byte>get(c)).byteValue();
/* 169 */     if (paramBoolean) {
/* 170 */       b = (byte)(b | 0x1);
/*     */     } else {
/* 172 */       b = (byte)(b & 0xFFFFFFFE);
/*     */     } 
/* 174 */     this.datawatcher.set(c, Byte.valueOf(b));
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean s_() {
/* 179 */     return true;
/*     */   }
/*     */   
/*     */   static class PathfinderGoalBlazeFireball extends PathfinderGoal {
/*     */     private final EntityBlaze a;
/*     */     private int b;
/*     */     private int c;
/*     */     
/*     */     public PathfinderGoalBlazeFireball(EntityBlaze param1EntityBlaze) {
/* 188 */       this.a = param1EntityBlaze;
/*     */       
/* 190 */       a(3);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 195 */       EntityLiving entityLiving = this.a.getGoalTarget();
/* 196 */       return (entityLiving != null && entityLiving.isAlive());
/*     */     }
/*     */ 
/*     */     
/*     */     public void c() {
/* 201 */       this.b = 0;
/*     */     }
/*     */ 
/*     */     
/*     */     public void d() {
/* 206 */       this.a.a(false);
/*     */     }
/*     */ 
/*     */     
/*     */     public void e() {
/* 211 */       this.c--;
/*     */       
/* 213 */       EntityLiving entityLiving = this.a.getGoalTarget();
/*     */       
/* 215 */       double d = this.a.h(entityLiving);
/*     */       
/* 217 */       if (d < 4.0D) {
/*     */         
/* 219 */         if (this.c <= 0) {
/* 220 */           this.c = 20;
/* 221 */           this.a.B(entityLiving);
/*     */         } 
/* 223 */         this.a.getControllerMove().a(entityLiving.locX, entityLiving.locY, entityLiving.locZ, 1.0D);
/* 224 */       } else if (d < f() * f()) {
/* 225 */         double d1 = entityLiving.locX - this.a.locX;
/* 226 */         double d2 = (entityLiving.getBoundingBox()).b + (entityLiving.length / 2.0F) - this.a.locY + (this.a.length / 2.0F);
/* 227 */         double d3 = entityLiving.locZ - this.a.locZ;
/*     */         
/* 229 */         if (this.c <= 0) {
/* 230 */           this.b++;
/* 231 */           if (this.b == 1) {
/* 232 */             this.c = 60;
/* 233 */             this.a.a(true);
/* 234 */           } else if (this.b <= 4) {
/* 235 */             this.c = 6;
/*     */           } else {
/* 237 */             this.c = 100;
/* 238 */             this.b = 0;
/* 239 */             this.a.a(false);
/*     */           } 
/*     */           
/* 242 */           if (this.b > 1) {
/* 243 */             float f = MathHelper.c(MathHelper.sqrt(d)) * 0.5F;
/*     */             
/* 245 */             this.a.world.a((EntityHuman)null, 1018, new BlockPosition((int)this.a.locX, (int)this.a.locY, (int)this.a.locZ), 0);
/* 246 */             for (byte b = 0; b < 1; b++) {
/* 247 */               EntitySmallFireball entitySmallFireball = new EntitySmallFireball(this.a.world, this.a, d1 + this.a.getRandom().nextGaussian() * f, d2, d3 + this.a.getRandom().nextGaussian() * f);
/* 248 */               entitySmallFireball.locY = this.a.locY + (this.a.length / 2.0F) + 0.5D;
/* 249 */               this.a.world.addEntity(entitySmallFireball);
/*     */             } 
/*     */           } 
/*     */         } 
/* 253 */         this.a.getControllerLook().a(entityLiving, 10.0F, 10.0F);
/*     */       } else {
/* 255 */         this.a.getNavigation().p();
/* 256 */         this.a.getControllerMove().a(entityLiving.locX, entityLiving.locY, entityLiving.locZ, 1.0D);
/*     */       } 
/*     */       
/* 259 */       super.e();
/*     */     }
/*     */     
/*     */     private double f() {
/* 263 */       AttributeInstance attributeInstance = this.a.getAttributeInstance(GenericAttributes.FOLLOW_RANGE);
/* 264 */       return (attributeInstance == null) ? 16.0D : attributeInstance.getValue();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityBlaze.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */