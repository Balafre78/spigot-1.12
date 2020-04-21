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
/*     */ public class EntityIllagerIllusioner
/*     */   extends EntityIllagerWizard
/*     */   implements IRangedEntity
/*     */ {
/*     */   private int c;
/*     */   private final Vec3D[][] bx;
/*     */   
/*     */   public EntityIllagerIllusioner(World paramWorld) {
/*  51 */     super(paramWorld);
/*     */     
/*  53 */     setSize(0.6F, 1.95F);
/*     */     
/*  55 */     this.b_ = 5;
/*     */     
/*  57 */     this.bx = new Vec3D[2][4];
/*  58 */     for (byte b = 0; b < 4; b++) {
/*  59 */       this.bx[0][b] = new Vec3D(0.0D, 0.0D, 0.0D);
/*  60 */       this.bx[1][b] = new Vec3D(0.0D, 0.0D, 0.0D);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void r() {
/*  66 */     super.r();
/*     */     
/*  68 */     this.goalSelector.a(0, new PathfinderGoalFloat(this));
/*  69 */     this.goalSelector.a(1, new EntityIllagerWizard.b(this));
/*  70 */     this.goalSelector.a(4, new b());
/*  71 */     this.goalSelector.a(5, new a());
/*  72 */     this.goalSelector.a(6, new PathfinderGoalBowShoot<>(this, 0.5D, 20, 15.0F));
/*  73 */     this.goalSelector.a(8, new PathfinderGoalRandomStroll(this, 0.6D));
/*  74 */     this.goalSelector.a(9, new PathfinderGoalLookAtPlayer(this, (Class)EntityHuman.class, 3.0F, 1.0F));
/*  75 */     this.goalSelector.a(10, new PathfinderGoalLookAtPlayer(this, (Class)EntityInsentient.class, 8.0F));
/*     */     
/*  77 */     this.targetSelector.a(1, new PathfinderGoalHurtByTarget(this, true, new Class[] { EntityIllagerIllusioner.class }));
/*  78 */     this.targetSelector.a(2, (new PathfinderGoalNearestAttackableTarget<>(this, (Class)EntityHuman.class, true)).b(300));
/*  79 */     this.targetSelector.a(3, (new PathfinderGoalNearestAttackableTarget<>(this, (Class)EntityVillager.class, false)).b(300));
/*  80 */     this.targetSelector.a(3, (new PathfinderGoalNearestAttackableTarget<>(this, (Class)EntityIronGolem.class, false)).b(300));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initAttributes() {
/*  85 */     super.initAttributes();
/*     */     
/*  87 */     getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.5D);
/*  88 */     getAttributeInstance(GenericAttributes.FOLLOW_RANGE).setValue(18.0D);
/*  89 */     getAttributeInstance(GenericAttributes.maxHealth).setValue(32.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public GroupDataEntity prepare(DifficultyDamageScaler paramDifficultyDamageScaler, GroupDataEntity paramGroupDataEntity) {
/*  94 */     setSlot(EnumItemSlot.MAINHAND, new ItemStack(Items.BOW));
/*     */     
/*  96 */     return super.prepare(paramDifficultyDamageScaler, paramGroupDataEntity);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void i() {
/* 101 */     super.i();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected MinecraftKey J() {
/* 110 */     return LootTables.a;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void n() {
/* 120 */     super.n();
/*     */     
/* 122 */     if (this.world.isClientSide && isInvisible()) {
/* 123 */       this.c--;
/* 124 */       if (this.c < 0) {
/* 125 */         this.c = 0;
/*     */       }
/*     */       
/* 128 */       if (this.hurtTicks == 1 || this.ticksLived % 1200 == 0) {
/* 129 */         this.c = 3;
/*     */         
/* 131 */         float f = -6.0F;
/* 132 */         byte b1 = 13;
/*     */         byte b2;
/* 134 */         for (b2 = 0; b2 < 4; b2++) {
/* 135 */           this.bx[0][b2] = this.bx[1][b2];
/* 136 */           this.bx[1][b2] = new Vec3D((-6.0F + this.random.nextInt(13)) * 0.5D, Math.max(0, this.random.nextInt(6) - 4), (-6.0F + this.random.nextInt(13)) * 0.5D);
/*     */         } 
/* 138 */         for (b2 = 0; b2 < 16; b2++) {
/* 139 */           this.world.addParticle(EnumParticle.CLOUD, this.locX + (this.random.nextDouble() - 0.5D) * this.width, this.locY + this.random.nextDouble() * this.length, this.locZ + (this.random.nextDouble() - 0.5D) * this.width, 0.0D, 0.0D, 0.0D, new int[0]);
/*     */         }
/*     */         
/* 142 */         this.world.a(this.locX, this.locY, this.locZ, SoundEffects.dg, bK(), 1.0F, 1.0F, false);
/* 143 */       } else if (this.hurtTicks == this.az - 1) {
/* 144 */         this.c = 3;
/* 145 */         for (byte b = 0; b < 4; b++) {
/* 146 */           this.bx[0][b] = this.bx[1][b];
/* 147 */           this.bx[1][b] = new Vec3D(0.0D, 0.0D, 0.0D);
/*     */         } 
/*     */       } 
/*     */     } 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean r(Entity paramEntity) {
/* 168 */     if (super.r(paramEntity)) {
/* 169 */       return true;
/*     */     }
/* 171 */     if (paramEntity instanceof EntityLiving && ((EntityLiving)paramEntity).getMonsterType() == EnumMonsterType.ILLAGER)
/*     */     {
/* 173 */       return (aY() == null && paramEntity.aY() == null);
/*     */     }
/* 175 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect F() {
/* 180 */     return SoundEffects.dc;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect cf() {
/* 185 */     return SoundEffects.de;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect d(DamageSource paramDamageSource) {
/* 190 */     return SoundEffects.df;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect dm() {
/* 195 */     return SoundEffects.dd;
/*     */   }
/*     */   
/*     */   class b
/*     */     extends EntityIllagerWizard.c {
/*     */     public boolean a() {
/* 201 */       if (!super.a()) {
/* 202 */         return false;
/*     */       }
/* 204 */       if (this.a.hasEffect(MobEffects.INVISIBILITY)) {
/* 205 */         return false;
/*     */       }
/* 207 */       return true;
/*     */     }
/*     */     private b(EntityIllagerIllusioner this$0) {}
/*     */     
/*     */     protected int f() {
/* 212 */       return 20;
/*     */     }
/*     */ 
/*     */     
/*     */     protected int i() {
/* 217 */       return 340;
/*     */     }
/*     */     
/*     */     protected void j() {
/* 221 */       this.a.addEffect(new MobEffect(MobEffects.INVISIBILITY, 1200));
/*     */     }
/*     */ 
/*     */     
/*     */     @Nullable
/*     */     protected SoundEffect k() {
/* 227 */       return SoundEffects.di;
/*     */     }
/*     */ 
/*     */     
/*     */     protected EntityIllagerWizard.Spell l() {
/* 232 */       return EntityIllagerWizard.Spell.DISAPPEAR;
/*     */     } }
/*     */   
/*     */   class a extends EntityIllagerWizard.c {
/*     */     private int b;
/*     */     
/*     */     private a(EntityIllagerIllusioner this$0) {}
/*     */     
/*     */     public boolean a() {
/* 241 */       if (!super.a()) {
/* 242 */         return false;
/*     */       }
/* 244 */       if (this.a.getGoalTarget() == null) {
/* 245 */         return false;
/*     */       }
/* 247 */       if (this.a.getGoalTarget().getId() == this.b) {
/* 248 */         return false;
/*     */       }
/* 250 */       if (!this.a.world.D(new BlockPosition(this.a)).a(EnumDifficulty.NORMAL.ordinal())) {
/* 251 */         return false;
/*     */       }
/* 253 */       return true;
/*     */     }
/*     */ 
/*     */     
/*     */     public void c() {
/* 258 */       super.c();
/*     */       
/* 260 */       this.b = this.a.getGoalTarget().getId();
/*     */     }
/*     */ 
/*     */     
/*     */     protected int f() {
/* 265 */       return 20;
/*     */     }
/*     */ 
/*     */     
/*     */     protected int i() {
/* 270 */       return 180;
/*     */     }
/*     */     
/*     */     protected void j() {
/* 274 */       this.a.getGoalTarget().addEffect(new MobEffect(MobEffects.BLINDNESS, 400));
/*     */     }
/*     */ 
/*     */     
/*     */     protected SoundEffect k() {
/* 279 */       return SoundEffects.dh;
/*     */     }
/*     */ 
/*     */     
/*     */     protected EntityIllagerWizard.Spell l() {
/* 284 */       return EntityIllagerWizard.Spell.BLINDNESS;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(EntityLiving paramEntityLiving, float paramFloat) {
/* 290 */     EntityArrow entityArrow = r(paramFloat);
/*     */     
/* 292 */     double d1 = paramEntityLiving.locX - this.locX;
/* 293 */     double d2 = (paramEntityLiving.getBoundingBox()).b + (paramEntityLiving.length / 3.0F) - entityArrow.locY;
/* 294 */     double d3 = paramEntityLiving.locZ - this.locZ;
/* 295 */     double d4 = MathHelper.sqrt(d1 * d1 + d3 * d3);
/* 296 */     entityArrow.shoot(d1, d2 + d4 * 0.20000000298023224D, d3, 1.6F, (14 - this.world.getDifficulty().a() * 4));
/* 297 */     a(SoundEffects.gW, 1.0F, 1.0F / (getRandom().nextFloat() * 0.4F + 0.8F));
/* 298 */     this.world.addEntity(entityArrow);
/*     */   }
/*     */   
/*     */   protected EntityArrow r(float paramFloat) {
/* 302 */     EntityTippedArrow entityTippedArrow = new EntityTippedArrow(this.world, this);
/* 303 */     entityTippedArrow.a(this, paramFloat);
/* 304 */     return entityTippedArrow;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void p(boolean paramBoolean) {
/* 312 */     a(1, paramBoolean);
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityIllagerIllusioner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */