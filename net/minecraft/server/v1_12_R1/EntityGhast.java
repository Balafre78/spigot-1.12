/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ public class EntityGhast
/*     */   extends EntityFlying implements IMonster {
/*   8 */   private static final DataWatcherObject<Boolean> a = DataWatcher.a((Class)EntityGhast.class, DataWatcherRegistry.h);
/*   9 */   private int b = 1;
/*     */   
/*     */   public EntityGhast(World world) {
/*  12 */     super(world);
/*  13 */     setSize(4.0F, 4.0F);
/*  14 */     this.fireProof = true;
/*  15 */     this.b_ = 5;
/*  16 */     this.moveController = new ControllerGhast(this);
/*     */   }
/*     */   
/*     */   protected void r() {
/*  20 */     this.goalSelector.a(5, new PathfinderGoalGhastIdleMove(this));
/*  21 */     this.goalSelector.a(7, new PathfinderGoalGhastMoveTowardsTarget(this));
/*  22 */     this.goalSelector.a(7, new PathfinderGoalGhastAttackTarget(this));
/*  23 */     this.targetSelector.a(1, new PathfinderGoalTargetNearestPlayer(this));
/*     */   }
/*     */   
/*     */   public void a(boolean flag) {
/*  27 */     this.datawatcher.set(a, Boolean.valueOf(flag));
/*     */   }
/*     */   
/*     */   public int getPower() {
/*  31 */     return this.b;
/*     */   }
/*     */   
/*     */   public void B_() {
/*  35 */     super.B_();
/*  36 */     if (!this.world.isClientSide && this.world.getDifficulty() == EnumDifficulty.PEACEFUL) {
/*  37 */       die();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean damageEntity(DamageSource damagesource, float f) {
/*  43 */     if (isInvulnerable(damagesource))
/*  44 */       return false; 
/*  45 */     if (damagesource.i() instanceof EntityLargeFireball && damagesource.getEntity() instanceof EntityHuman) {
/*  46 */       super.damageEntity(damagesource, 1000.0F);
/*  47 */       return true;
/*     */     } 
/*  49 */     return super.damageEntity(damagesource, f);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void i() {
/*  54 */     super.i();
/*  55 */     this.datawatcher.register(a, Boolean.valueOf(false));
/*     */   }
/*     */   
/*     */   protected void initAttributes() {
/*  59 */     super.initAttributes();
/*  60 */     getAttributeInstance(GenericAttributes.maxHealth).setValue(10.0D);
/*  61 */     getAttributeInstance(GenericAttributes.FOLLOW_RANGE).setValue(100.0D);
/*     */   }
/*     */   
/*     */   public SoundCategory bK() {
/*  65 */     return SoundCategory.HOSTILE;
/*     */   }
/*     */   
/*     */   protected SoundEffect F() {
/*  69 */     return SoundEffects.cb;
/*     */   }
/*     */   
/*     */   protected SoundEffect d(DamageSource damagesource) {
/*  73 */     return SoundEffects.cd;
/*     */   }
/*     */   
/*     */   protected SoundEffect cf() {
/*  77 */     return SoundEffects.cc;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   protected MinecraftKey J() {
/*  82 */     return LootTables.aj;
/*     */   }
/*     */   
/*     */   protected float cq() {
/*  86 */     return 10.0F;
/*     */   }
/*     */   
/*     */   public boolean P() {
/*  90 */     return (this.random.nextInt(20) == 0 && super.P() && this.world.getDifficulty() != EnumDifficulty.PEACEFUL);
/*     */   }
/*     */   
/*     */   public int cU() {
/*  94 */     return 1;
/*     */   }
/*     */   
/*     */   public static void a(DataConverterManager dataconvertermanager) {
/*  98 */     EntityInsentient.a(dataconvertermanager, EntityGhast.class);
/*     */   }
/*     */   
/*     */   public void b(NBTTagCompound nbttagcompound) {
/* 102 */     super.b(nbttagcompound);
/* 103 */     nbttagcompound.setInt("ExplosionPower", this.b);
/*     */   }
/*     */   
/*     */   public void a(NBTTagCompound nbttagcompound) {
/* 107 */     super.a(nbttagcompound);
/* 108 */     if (nbttagcompound.hasKeyOfType("ExplosionPower", 99)) {
/* 109 */       this.b = nbttagcompound.getInt("ExplosionPower");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public float getHeadHeight() {
/* 115 */     return 2.6F;
/*     */   }
/*     */   
/*     */   static class PathfinderGoalGhastAttackTarget
/*     */     extends PathfinderGoal {
/*     */     private final EntityGhast ghast;
/*     */     public int a;
/*     */     
/*     */     public PathfinderGoalGhastAttackTarget(EntityGhast entityghast) {
/* 124 */       this.ghast = entityghast;
/*     */     }
/*     */     
/*     */     public boolean a() {
/* 128 */       return (this.ghast.getGoalTarget() != null);
/*     */     }
/*     */     
/*     */     public void c() {
/* 132 */       this.a = 0;
/*     */     }
/*     */     
/*     */     public void d() {
/* 136 */       this.ghast.a(false);
/*     */     }
/*     */     
/*     */     public void e() {
/* 140 */       EntityLiving entityliving = this.ghast.getGoalTarget();
/*     */ 
/*     */       
/* 143 */       if (entityliving.h(this.ghast) < 4096.0D && this.ghast.hasLineOfSight(entityliving)) {
/* 144 */         World world = this.ghast.world;
/*     */         
/* 146 */         this.a++;
/* 147 */         if (this.a == 10) {
/* 148 */           world.a((EntityHuman)null, 1015, new BlockPosition(this.ghast), 0);
/*     */         }
/*     */         
/* 151 */         if (this.a == 20) {
/*     */           
/* 153 */           Vec3D vec3d = this.ghast.e(1.0F);
/* 154 */           double d2 = entityliving.locX - this.ghast.locX + vec3d.x * 4.0D;
/* 155 */           double d3 = (entityliving.getBoundingBox()).b + (entityliving.length / 2.0F) - 0.5D + this.ghast.locY + (this.ghast.length / 2.0F);
/* 156 */           double d4 = entityliving.locZ - this.ghast.locZ + vec3d.z * 4.0D;
/*     */           
/* 158 */           world.a((EntityHuman)null, 1016, new BlockPosition(this.ghast), 0);
/* 159 */           EntityLargeFireball entitylargefireball = new EntityLargeFireball(world, this.ghast, d2, d3, d4);
/*     */ 
/*     */           
/* 162 */           entitylargefireball.bukkitYield = (entitylargefireball.yield = this.ghast.getPower());
/* 163 */           entitylargefireball.locX = this.ghast.locX + vec3d.x * 4.0D;
/* 164 */           entitylargefireball.locY = this.ghast.locY + (this.ghast.length / 2.0F) + 0.5D;
/* 165 */           entitylargefireball.locZ = this.ghast.locZ + vec3d.z * 4.0D;
/* 166 */           world.addEntity(entitylargefireball);
/* 167 */           this.a = -40;
/*     */         } 
/* 169 */       } else if (this.a > 0) {
/* 170 */         this.a--;
/*     */       } 
/*     */       
/* 173 */       this.ghast.a((this.a > 10));
/*     */     }
/*     */   }
/*     */   
/*     */   static class PathfinderGoalGhastMoveTowardsTarget
/*     */     extends PathfinderGoal {
/*     */     private final EntityGhast a;
/*     */     
/*     */     public PathfinderGoalGhastMoveTowardsTarget(EntityGhast entityghast) {
/* 182 */       this.a = entityghast;
/* 183 */       a(2);
/*     */     }
/*     */     
/*     */     public boolean a() {
/* 187 */       return true;
/*     */     }
/*     */     
/*     */     public void e() {
/* 191 */       if (this.a.getGoalTarget() == null) {
/* 192 */         this.a.yaw = -((float)MathHelper.c(this.a.motX, this.a.motZ)) * 57.295776F;
/* 193 */         this.a.aN = this.a.yaw;
/*     */       } else {
/* 195 */         EntityLiving entityliving = this.a.getGoalTarget();
/*     */ 
/*     */         
/* 198 */         if (entityliving.h(this.a) < 4096.0D) {
/* 199 */           double d1 = entityliving.locX - this.a.locX;
/* 200 */           double d2 = entityliving.locZ - this.a.locZ;
/*     */           
/* 202 */           this.a.yaw = -((float)MathHelper.c(d1, d2)) * 57.295776F;
/* 203 */           this.a.aN = this.a.yaw;
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   static class PathfinderGoalGhastIdleMove
/*     */     extends PathfinderGoal
/*     */   {
/*     */     private final EntityGhast a;
/*     */     
/*     */     public PathfinderGoalGhastIdleMove(EntityGhast entityghast) {
/* 215 */       this.a = entityghast;
/* 216 */       a(1);
/*     */     }
/*     */     
/*     */     public boolean a() {
/* 220 */       ControllerMove controllermove = this.a.getControllerMove();
/*     */       
/* 222 */       if (!controllermove.b()) {
/* 223 */         return true;
/*     */       }
/* 225 */       double d0 = controllermove.d() - this.a.locX;
/* 226 */       double d1 = controllermove.e() - this.a.locY;
/* 227 */       double d2 = controllermove.f() - this.a.locZ;
/* 228 */       double d3 = d0 * d0 + d1 * d1 + d2 * d2;
/*     */       
/* 230 */       return !(d3 >= 1.0D && d3 <= 3600.0D);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean b() {
/* 235 */       return false;
/*     */     }
/*     */     
/*     */     public void c() {
/* 239 */       Random random = this.a.getRandom();
/* 240 */       double d0 = this.a.locX + ((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
/* 241 */       double d1 = this.a.locY + ((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
/* 242 */       double d2 = this.a.locZ + ((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
/*     */       
/* 244 */       this.a.getControllerMove().a(d0, d1, d2, 1.0D);
/*     */     }
/*     */   }
/*     */   
/*     */   static class ControllerGhast
/*     */     extends ControllerMove {
/*     */     private final EntityGhast i;
/*     */     private int j;
/*     */     
/*     */     public ControllerGhast(EntityGhast entityghast) {
/* 254 */       super(entityghast);
/* 255 */       this.i = entityghast;
/*     */     }
/*     */ 
/*     */     
/*     */     public void a() {
/* 260 */       double d0 = this.b - this.i.locX;
/* 261 */       double d1 = this.c - this.i.locY;
/* 262 */       double d2 = this.d - this.i.locZ;
/* 263 */       double d3 = d0 * d0 + d1 * d1 + d2 * d2;
/*     */       
/* 265 */       if (this.h == ControllerMove.Operation.MOVE_TO && this.j-- <= 0) {
/* 266 */         this.j += this.i.getRandom().nextInt(5) + 2;
/* 267 */         d3 = MathHelper.sqrt(d3);
/* 268 */         if (b(this.b, this.c, this.d, d3)) {
/* 269 */           this.i.motX += d0 / d3 * 0.1D;
/* 270 */           this.i.motY += d1 / d3 * 0.1D;
/* 271 */           this.i.motZ += d2 / d3 * 0.1D;
/*     */         } else {
/* 273 */           this.h = ControllerMove.Operation.WAIT;
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     private boolean b(double d0, double d1, double d2, double d3) {
/* 281 */       double d4 = (d0 - this.i.locX) / d3;
/* 282 */       double d5 = (d1 - this.i.locY) / d3;
/* 283 */       double d6 = (d2 - this.i.locZ) / d3;
/* 284 */       AxisAlignedBB axisalignedbb = this.i.getBoundingBox();
/*     */       
/* 286 */       for (int i = 1; i < d3; i++) {
/* 287 */         axisalignedbb = axisalignedbb.d(d4, d5, d6);
/* 288 */         if (!this.i.world.getCubes(this.i, axisalignedbb).isEmpty()) {
/* 289 */           return false;
/*     */         }
/*     */       } 
/*     */       
/* 293 */       return true;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityGhast.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */