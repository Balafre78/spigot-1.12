/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import java.util.List;
/*     */ import java.util.Random;
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
/*     */ public class EntityEvoker
/*     */   extends EntityIllagerWizard
/*     */ {
/*     */   private EntitySheep c;
/*     */   
/*     */   public EntityEvoker(World paramWorld) {
/*  43 */     super(paramWorld);
/*     */     
/*  45 */     setSize(0.6F, 1.95F);
/*     */     
/*  47 */     this.b_ = 10;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void r() {
/*  52 */     super.r();
/*     */     
/*  54 */     this.goalSelector.a(0, new PathfinderGoalFloat(this));
/*  55 */     this.goalSelector.a(1, new b());
/*  56 */     this.goalSelector.a(2, new PathfinderGoalAvoidTarget<>(this, EntityHuman.class, 8.0F, 0.6D, 1.0D));
/*  57 */     this.goalSelector.a(4, new c());
/*  58 */     this.goalSelector.a(5, new a());
/*  59 */     this.goalSelector.a(6, new d(this));
/*  60 */     this.goalSelector.a(8, new PathfinderGoalRandomStroll(this, 0.6D));
/*  61 */     this.goalSelector.a(9, new PathfinderGoalLookAtPlayer(this, (Class)EntityHuman.class, 3.0F, 1.0F));
/*  62 */     this.goalSelector.a(10, new PathfinderGoalLookAtPlayer(this, (Class)EntityInsentient.class, 8.0F));
/*     */     
/*  64 */     this.targetSelector.a(1, new PathfinderGoalHurtByTarget(this, true, new Class[] { EntityEvoker.class }));
/*  65 */     this.targetSelector.a(2, (new PathfinderGoalNearestAttackableTarget<>(this, (Class)EntityHuman.class, true)).b(300));
/*  66 */     this.targetSelector.a(3, (new PathfinderGoalNearestAttackableTarget<>(this, (Class)EntityVillager.class, false)).b(300));
/*  67 */     this.targetSelector.a(3, new PathfinderGoalNearestAttackableTarget<>(this, EntityIronGolem.class, false));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initAttributes() {
/*  72 */     super.initAttributes();
/*     */     
/*  74 */     getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.5D);
/*  75 */     getAttributeInstance(GenericAttributes.FOLLOW_RANGE).setValue(12.0D);
/*  76 */     getAttributeInstance(GenericAttributes.maxHealth).setValue(24.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void i() {
/*  81 */     super.i();
/*     */   }
/*     */   
/*     */   public static void a(DataConverterManager paramDataConverterManager) {
/*  85 */     EntityInsentient.a(paramDataConverterManager, EntityEvoker.class);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(NBTTagCompound paramNBTTagCompound) {
/*  90 */     super.a(paramNBTTagCompound);
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(NBTTagCompound paramNBTTagCompound) {
/*  95 */     super.b(paramNBTTagCompound);
/*     */   }
/*     */ 
/*     */   
/*     */   protected MinecraftKey J() {
/* 100 */     return LootTables.au;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void M() {
/* 105 */     super.M();
/*     */   }
/*     */ 
/*     */   
/*     */   public void B_() {
/* 110 */     super.B_();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean r(Entity paramEntity) {
/* 115 */     if (paramEntity == null) {
/* 116 */       return false;
/*     */     }
/* 118 */     if (paramEntity == this) {
/* 119 */       return true;
/*     */     }
/* 121 */     if (super.r(paramEntity)) {
/* 122 */       return true;
/*     */     }
/* 124 */     if (paramEntity instanceof EntityVex) {
/* 125 */       return r(((EntityVex)paramEntity).p());
/*     */     }
/* 127 */     if (paramEntity instanceof EntityLiving && ((EntityLiving)paramEntity).getMonsterType() == EnumMonsterType.ILLAGER)
/*     */     {
/* 129 */       return (aY() == null && paramEntity.aY() == null);
/*     */     }
/* 131 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect F() {
/* 136 */     return SoundEffects.bs;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect cf() {
/* 141 */     return SoundEffects.bu;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect d(DamageSource paramDamageSource) {
/* 146 */     return SoundEffects.bv;
/*     */   }
/*     */   
/*     */   private void a(@Nullable EntitySheep paramEntitySheep) {
/* 150 */     this.c = paramEntitySheep;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private EntitySheep dq() {
/* 155 */     return this.c;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect dm() {
/* 160 */     return SoundEffects.bt;
/*     */   }
/*     */   
/*     */   class b extends EntityIllagerWizard.b { private b(EntityEvoker this$0) {}
/*     */     
/*     */     public void e() {
/* 166 */       if (this.a.getGoalTarget() != null) {
/* 167 */         this.a.getControllerLook().a(this.a.getGoalTarget(), this.a.O(), this.a.N());
/* 168 */       } else if (EntityEvoker.a(this.a) != null) {
/* 169 */         this.a.getControllerLook().a(EntityEvoker.a(this.a), this.a.O(), this.a.N());
/*     */       } 
/*     */     } }
/*     */   
/*     */   class a extends EntityIllagerWizard.c {
/*     */     private a(EntityEvoker this$0) {}
/*     */     
/*     */     protected int f() {
/* 177 */       return 40;
/*     */     }
/*     */ 
/*     */     
/*     */     protected int i() {
/* 182 */       return 100;
/*     */     }
/*     */ 
/*     */     
/*     */     protected void j() {
/* 187 */       EntityLiving entityLiving = this.a.getGoalTarget();
/* 188 */       double d1 = Math.min(entityLiving.locY, this.a.locY);
/* 189 */       double d2 = Math.max(entityLiving.locY, this.a.locY) + 1.0D;
/* 190 */       float f = (float)MathHelper.c(entityLiving.locZ - this.a.locZ, entityLiving.locX - this.a.locX);
/* 191 */       if (this.a.h(entityLiving) < 9.0D) {
/*     */         byte b;
/* 193 */         for (b = 0; b < 5; b++) {
/* 194 */           float f1 = f + b * 3.1415927F * 0.4F;
/* 195 */           a(this.a.locX + MathHelper.cos(f1) * 1.5D, this.a.locZ + MathHelper.sin(f1) * 1.5D, d1, d2, f1, 0);
/*     */         } 
/*     */         
/* 198 */         for (b = 0; b < 8; b++) {
/* 199 */           float f1 = f + b * 3.1415927F * 2.0F / 8.0F + 1.2566371F;
/* 200 */           a(this.a.locX + MathHelper.cos(f1) * 2.5D, this.a.locZ + MathHelper.sin(f1) * 2.5D, d1, d2, f1, 3);
/*     */         } 
/*     */       } else {
/*     */         
/* 204 */         for (byte b = 0; b < 16; b++) {
/* 205 */           double d = 1.25D * (b + 1);
/* 206 */           int i = 1 * b;
/* 207 */           a(this.a.locX + MathHelper.cos(f) * d, this.a.locZ + MathHelper.sin(f) * d, d1, d2, f, i);
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     private void a(double param1Double1, double param1Double2, double param1Double3, double param1Double4, float param1Float, int param1Int) {
/* 214 */       BlockPosition blockPosition = new BlockPosition(param1Double1, param1Double4, param1Double2);
/* 215 */       boolean bool = false;
/* 216 */       double d = 0.0D;
/*     */       do {
/* 218 */         if (!this.a.world.d(blockPosition, true) && this.a.world.d(blockPosition.down(), true)) {
/* 219 */           if (!this.a.world.isEmpty(blockPosition)) {
/* 220 */             IBlockData iBlockData = this.a.world.getType(blockPosition);
/* 221 */             AxisAlignedBB axisAlignedBB = iBlockData.d(this.a.world, blockPosition);
/* 222 */             if (axisAlignedBB != null) {
/* 223 */               d = axisAlignedBB.e;
/*     */             }
/*     */           } 
/* 226 */           bool = true;
/*     */           break;
/*     */         } 
/* 229 */         blockPosition = blockPosition.down();
/* 230 */       } while (blockPosition.getY() >= MathHelper.floor(param1Double3) - 1);
/* 231 */       if (bool) {
/* 232 */         EntityEvokerFangs entityEvokerFangs = new EntityEvokerFangs(this.a.world, param1Double1, blockPosition.getY() + d, param1Double2, param1Float, param1Int, this.a);
/* 233 */         this.a.world.addEntity(entityEvokerFangs);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     protected SoundEffect k() {
/* 239 */       return SoundEffects.bw;
/*     */     }
/*     */ 
/*     */     
/*     */     protected EntityIllagerWizard.Spell l() {
/* 244 */       return EntityIllagerWizard.Spell.FANGS;
/*     */     }
/*     */   }
/*     */   
/*     */   class c extends EntityIllagerWizard.c { private c(EntityEvoker this$0) {}
/*     */     
/*     */     public boolean a() {
/* 251 */       if (!super.a()) {
/* 252 */         return false;
/*     */       }
/*     */ 
/*     */       
/* 256 */       int i = this.a.world.<EntityVex>a(EntityVex.class, this.a.getBoundingBox().g(16.0D)).size();
/* 257 */       return (EntityEvoker.b(this.a).nextInt(8) + 1 > i);
/*     */     }
/*     */ 
/*     */     
/*     */     protected int f() {
/* 262 */       return 100;
/*     */     }
/*     */ 
/*     */     
/*     */     protected int i() {
/* 267 */       return 340;
/*     */     }
/*     */     
/*     */     protected void j() {
/* 271 */       for (byte b = 0; b < 3; b++) {
/* 272 */         BlockPosition blockPosition = (new BlockPosition(this.a)).a(-2 + EntityEvoker.c(this.a).nextInt(5), 1, -2 + EntityEvoker.d(this.a).nextInt(5));
/* 273 */         EntityVex entityVex = new EntityVex(this.a.world);
/* 274 */         entityVex.setPositionRotation(blockPosition, 0.0F, 0.0F);
/* 275 */         entityVex.prepare(this.a.world.D(blockPosition), (GroupDataEntity)null);
/* 276 */         entityVex.a(this.a);
/* 277 */         entityVex.g(blockPosition);
/* 278 */         entityVex.a(20 * (30 + EntityEvoker.e(this.a).nextInt(90)));
/* 279 */         this.a.world.addEntity(entityVex);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     protected SoundEffect k() {
/* 285 */       return SoundEffects.bx;
/*     */     }
/*     */ 
/*     */     
/*     */     protected EntityIllagerWizard.Spell l() {
/* 290 */       return EntityIllagerWizard.Spell.SUMMON_VEX;
/*     */     } }
/*     */   
/*     */   public class d extends EntityIllagerWizard.c { public d(EntityEvoker this$0) {
/* 294 */       super(this$0);
/* 295 */       this.a = new Predicate<EntitySheep>(this) {
/*     */           public boolean a(EntitySheep param2EntitySheep) {
/* 297 */             return (param2EntitySheep.getColor() == EnumColor.BLUE);
/*     */           }
/*     */         };
/*     */     }
/*     */     final Predicate<EntitySheep> a;
/*     */     public boolean a() {
/* 303 */       if (this.b.getGoalTarget() != null)
/*     */       {
/* 305 */         return false;
/*     */       }
/* 307 */       if (this.b.dn())
/*     */       {
/* 309 */         return false;
/*     */       }
/* 311 */       if (this.b.ticksLived < this.d) {
/* 312 */         return false;
/*     */       }
/* 314 */       if (!this.b.world.getGameRules().getBoolean("mobGriefing")) {
/* 315 */         return false;
/*     */       }
/*     */       
/* 318 */       List<EntitySheep> list = this.b.world.a(EntitySheep.class, this.b.getBoundingBox().grow(16.0D, 4.0D, 16.0D), this.a);
/*     */       
/* 320 */       if (list.isEmpty()) {
/* 321 */         return false;
/*     */       }
/* 323 */       EntityEvoker.a(this.b, list.get(EntityEvoker.f(this.b).nextInt(list.size())));
/* 324 */       return true;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean b() {
/* 330 */       return (EntityEvoker.a(this.b) != null && this.c > 0);
/*     */     }
/*     */ 
/*     */     
/*     */     public void d() {
/* 335 */       super.d();
/* 336 */       EntityEvoker.a(this.b, (EntitySheep)null);
/*     */     }
/*     */ 
/*     */     
/*     */     protected void j() {
/* 341 */       EntitySheep entitySheep = EntityEvoker.a(this.b);
/* 342 */       if (entitySheep != null && entitySheep.isAlive()) {
/* 343 */         entitySheep.setColor(EnumColor.RED);
/*     */       }
/*     */     }
/*     */     
/*     */     protected int m() {
/* 348 */       return 40;
/*     */     }
/*     */ 
/*     */     
/*     */     protected int f() {
/* 353 */       return 60;
/*     */     }
/*     */ 
/*     */     
/*     */     protected int i() {
/* 358 */       return 140;
/*     */     }
/*     */ 
/*     */     
/*     */     protected SoundEffect k() {
/* 363 */       return SoundEffects.by;
/*     */     }
/*     */ 
/*     */     
/*     */     protected EntityIllagerWizard.Spell l() {
/* 368 */       return EntityIllagerWizard.Spell.WOLOLO;
/*     */     } }
/*     */ 
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityEvoker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */