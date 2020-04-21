/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import java.util.List;
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
/*     */ public class EntityPolarBear
/*     */   extends EntityAnimal
/*     */ {
/*  39 */   private static final DataWatcherObject<Boolean> bx = DataWatcher.a((Class)EntityPolarBear.class, DataWatcherRegistry.h);
/*     */   
/*     */   private float by;
/*     */   private float bz;
/*     */   private int bB;
/*     */   
/*     */   public EntityPolarBear(World paramWorld) {
/*  46 */     super(paramWorld);
/*  47 */     setSize(1.3F, 1.4F);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityAgeable createChild(EntityAgeable paramEntityAgeable) {
/*  52 */     return new EntityPolarBear(this.world);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean e(ItemStack paramItemStack) {
/*  57 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void r() {
/*  62 */     super.r();
/*     */     
/*  64 */     this.goalSelector.a(0, new PathfinderGoalFloat(this));
/*  65 */     this.goalSelector.a(1, new d(this));
/*  66 */     this.goalSelector.a(1, new e(this));
/*  67 */     this.goalSelector.a(4, new PathfinderGoalFollowParent(this, 1.25D));
/*  68 */     this.goalSelector.a(5, new PathfinderGoalRandomStroll(this, 1.0D));
/*  69 */     this.goalSelector.a(6, new PathfinderGoalLookAtPlayer(this, (Class)EntityHuman.class, 6.0F));
/*  70 */     this.goalSelector.a(7, new PathfinderGoalRandomLookaround(this));
/*     */     
/*  72 */     this.targetSelector.a(1, new c(this));
/*  73 */     this.targetSelector.a(2, new a(this));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initAttributes() {
/*  78 */     super.initAttributes();
/*     */     
/*  80 */     getAttributeInstance(GenericAttributes.maxHealth).setValue(30.0D);
/*  81 */     getAttributeInstance(GenericAttributes.FOLLOW_RANGE).setValue(20.0D);
/*  82 */     getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.25D);
/*     */     
/*  84 */     getAttributeMap().b(GenericAttributes.ATTACK_DAMAGE);
/*  85 */     getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(6.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect F() {
/*  90 */     if (isBaby()) {
/*  91 */       return SoundEffects.fN;
/*     */     }
/*  93 */     return SoundEffects.fM;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect d(DamageSource paramDamageSource) {
/*  98 */     return SoundEffects.fP;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect cf() {
/* 103 */     return SoundEffects.fO;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(BlockPosition paramBlockPosition, Block paramBlock) {
/* 108 */     a(SoundEffects.fQ, 0.15F, 1.0F);
/*     */   }
/*     */   
/*     */   protected void dl() {
/* 112 */     if (this.bB <= 0) {
/* 113 */       a(SoundEffects.fR, 1.0F, 1.0F);
/*     */       
/* 115 */       this.bB = 40;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   protected MinecraftKey J() {
/* 122 */     return LootTables.F;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void i() {
/* 127 */     super.i();
/*     */     
/* 129 */     this.datawatcher.register(bx, Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */   
/*     */   public void B_() {
/* 134 */     super.B_();
/*     */     
/* 136 */     if (this.world.isClientSide) {
/* 137 */       this.by = this.bz;
/* 138 */       if (dm()) {
/* 139 */         this.bz = MathHelper.a(this.bz + 1.0F, 0.0F, 6.0F);
/*     */       } else {
/* 141 */         this.bz = MathHelper.a(this.bz - 1.0F, 0.0F, 6.0F);
/*     */       } 
/*     */     } 
/*     */     
/* 145 */     if (this.bB > 0) {
/* 146 */       this.bB--;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean B(Entity paramEntity) {
/* 153 */     boolean bool = paramEntity.damageEntity(DamageSource.mobAttack(this), (int)getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).getValue());
/* 154 */     if (bool) {
/* 155 */       a(this, paramEntity);
/*     */     }
/* 157 */     return bool;
/*     */   }
/*     */   
/*     */   public boolean dm() {
/* 161 */     return ((Boolean)this.datawatcher.<Boolean>get(bx)).booleanValue();
/*     */   }
/*     */   
/*     */   public void p(boolean paramBoolean) {
/* 165 */     this.datawatcher.set(bx, Boolean.valueOf(paramBoolean));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected float cx() {
/* 174 */     return 0.98F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public GroupDataEntity prepare(DifficultyDamageScaler paramDifficultyDamageScaler, GroupDataEntity paramGroupDataEntity) {
/* 180 */     if (paramGroupDataEntity instanceof b) {
/* 181 */       if (((b)paramGroupDataEntity).a) {
/* 182 */         setAgeRaw(-24000);
/*     */       }
/*     */     } else {
/* 185 */       b b = new b();
/* 186 */       b.a = true;
/* 187 */       paramGroupDataEntity = b;
/*     */     } 
/*     */     
/* 190 */     return paramGroupDataEntity;
/*     */   }
/*     */   
/*     */   static class b
/*     */     implements GroupDataEntity {
/*     */     public boolean a;
/*     */     
/*     */     private b() {}
/*     */   }
/*     */   
/*     */   class c
/*     */     extends PathfinderGoalHurtByTarget {
/*     */     public c(EntityPolarBear this$0) {
/* 203 */       super(this$0, false, new Class[0]);
/*     */     }
/*     */ 
/*     */     
/*     */     public void c() {
/* 208 */       super.c();
/* 209 */       if (this.a.isBaby()) {
/* 210 */         f();
/* 211 */         d();
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     protected void a(EntityCreature param1EntityCreature, EntityLiving param1EntityLiving) {
/* 217 */       if (param1EntityCreature instanceof EntityPolarBear && 
/* 218 */         !param1EntityCreature.isBaby()) {
/* 219 */         super.a(param1EntityCreature, param1EntityLiving);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   class a
/*     */     extends PathfinderGoalNearestAttackableTarget<EntityHuman>
/*     */   {
/*     */     public a(EntityPolarBear this$0) {
/* 231 */       super(this$0, EntityHuman.class, 20, true, true, (Predicate<? super EntityHuman>)null);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 236 */       if (this.i.isBaby()) {
/* 237 */         return false;
/*     */       }
/*     */ 
/*     */       
/* 241 */       if (super.a()) {
/* 242 */         List<EntityPolarBear> list = this.i.world.a(EntityPolarBear.class, this.i.getBoundingBox().grow(8.0D, 4.0D, 8.0D));
/* 243 */         for (EntityPolarBear entityPolarBear : list) {
/* 244 */           if (entityPolarBear.isBaby()) {
/* 245 */             return true;
/*     */           }
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 251 */       this.i.setGoalTarget((EntityLiving)null);
/* 252 */       return false;
/*     */     }
/*     */ 
/*     */     
/*     */     protected double i() {
/* 257 */       return super.i() * 0.5D;
/*     */     }
/*     */   }
/*     */   
/*     */   class d
/*     */     extends PathfinderGoalMeleeAttack
/*     */   {
/*     */     public d(EntityPolarBear this$0) {
/* 265 */       super(this$0, 1.25D, true);
/*     */     }
/*     */ 
/*     */     
/*     */     protected void a(EntityLiving param1EntityLiving, double param1Double) {
/* 270 */       double d1 = a(param1EntityLiving);
/* 271 */       if (param1Double <= d1 && this.c <= 0) {
/* 272 */         this.c = 20;
/* 273 */         this.b.B(param1EntityLiving);
/* 274 */         this.h.p(false);
/* 275 */       } else if (param1Double <= d1 * 2.0D) {
/* 276 */         if (this.c <= 0) {
/* 277 */           this.h.p(false);
/* 278 */           this.c = 20;
/*     */         } 
/* 280 */         if (this.c <= 10) {
/* 281 */           this.h.p(true);
/* 282 */           this.h.dl();
/*     */         } 
/*     */       } else {
/*     */         
/* 286 */         this.c = 20;
/* 287 */         this.h.p(false);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public void d() {
/* 293 */       this.h.p(false);
/* 294 */       super.d();
/*     */     }
/*     */ 
/*     */     
/*     */     protected double a(EntityLiving param1EntityLiving) {
/* 299 */       return (4.0F + param1EntityLiving.width);
/*     */     }
/*     */   }
/*     */   
/*     */   class e extends PathfinderGoalPanic {
/*     */     public e(EntityPolarBear this$0) {
/* 305 */       super(this$0, 2.0D);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 310 */       if (!this.f.isBaby() && !this.f.isBurning()) {
/* 311 */         return false;
/*     */       }
/* 313 */       return super.a();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityPolarBear.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */