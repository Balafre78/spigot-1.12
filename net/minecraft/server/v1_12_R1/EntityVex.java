/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.event.entity.EntityTargetEvent;
/*     */ 
/*     */ public class EntityVex
/*     */   extends EntityMonster {
/*   8 */   protected static final DataWatcherObject<Byte> a = DataWatcher.a((Class)EntityVex.class, DataWatcherRegistry.a);
/*     */   private EntityInsentient b;
/*     */   @Nullable
/*     */   private BlockPosition c;
/*     */   private boolean bx;
/*     */   private int by;
/*     */   
/*     */   public EntityVex(World world) {
/*  16 */     super(world);
/*  17 */     this.fireProof = true;
/*  18 */     this.moveController = new c(this);
/*  19 */     setSize(0.4F, 0.8F);
/*  20 */     this.b_ = 3;
/*     */   }
/*     */   
/*     */   public void move(EnumMoveType enummovetype, double d0, double d1, double d2) {
/*  24 */     super.move(enummovetype, d0, d1, d2);
/*  25 */     checkBlockCollisions();
/*     */   }
/*     */   
/*     */   public void B_() {
/*  29 */     this.noclip = true;
/*  30 */     super.B_();
/*  31 */     this.noclip = false;
/*  32 */     setNoGravity(true);
/*  33 */     if (this.bx && --this.by <= 0) {
/*  34 */       this.by = 20;
/*  35 */       damageEntity(DamageSource.STARVE, 1.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void r() {
/*  41 */     super.r();
/*  42 */     this.goalSelector.a(0, new PathfinderGoalFloat(this));
/*  43 */     this.goalSelector.a(4, new a());
/*  44 */     this.goalSelector.a(8, new d());
/*  45 */     this.goalSelector.a(9, new PathfinderGoalLookAtPlayer(this, (Class)EntityHuman.class, 3.0F, 1.0F));
/*  46 */     this.goalSelector.a(10, new PathfinderGoalLookAtPlayer(this, (Class)EntityInsentient.class, 8.0F));
/*  47 */     this.targetSelector.a(1, new PathfinderGoalHurtByTarget(this, true, new Class[] { EntityVex.class }));
/*  48 */     this.targetSelector.a(2, new b(this));
/*  49 */     this.targetSelector.a(3, new PathfinderGoalNearestAttackableTarget<>(this, EntityHuman.class, true));
/*     */   }
/*     */   
/*     */   protected void initAttributes() {
/*  53 */     super.initAttributes();
/*  54 */     getAttributeInstance(GenericAttributes.maxHealth).setValue(14.0D);
/*  55 */     getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(4.0D);
/*     */   }
/*     */   
/*     */   protected void i() {
/*  59 */     super.i();
/*  60 */     this.datawatcher.register(a, Byte.valueOf((byte)0));
/*     */   }
/*     */   
/*     */   public static void a(DataConverterManager dataconvertermanager) {
/*  64 */     EntityInsentient.a(dataconvertermanager, EntityVex.class);
/*     */   }
/*     */   
/*     */   public void a(NBTTagCompound nbttagcompound) {
/*  68 */     super.a(nbttagcompound);
/*  69 */     if (nbttagcompound.hasKey("BoundX")) {
/*  70 */       this.c = new BlockPosition(nbttagcompound.getInt("BoundX"), nbttagcompound.getInt("BoundY"), nbttagcompound.getInt("BoundZ"));
/*     */     }
/*     */     
/*  73 */     if (nbttagcompound.hasKey("LifeTicks")) {
/*  74 */       a(nbttagcompound.getInt("LifeTicks"));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(NBTTagCompound nbttagcompound) {
/*  80 */     super.b(nbttagcompound);
/*  81 */     if (this.c != null) {
/*  82 */       nbttagcompound.setInt("BoundX", this.c.getX());
/*  83 */       nbttagcompound.setInt("BoundY", this.c.getY());
/*  84 */       nbttagcompound.setInt("BoundZ", this.c.getZ());
/*     */     } 
/*     */     
/*  87 */     if (this.bx) {
/*  88 */       nbttagcompound.setInt("LifeTicks", this.by);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityInsentient p() {
/*  94 */     return this.b;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public BlockPosition dm() {
/*  99 */     return this.c;
/*     */   }
/*     */   
/*     */   public void g(@Nullable BlockPosition blockposition) {
/* 103 */     this.c = blockposition;
/*     */   }
/*     */   
/*     */   private boolean c(int i) {
/* 107 */     byte b0 = ((Byte)this.datawatcher.<Byte>get(a)).byteValue();
/*     */     
/* 109 */     return ((b0 & i) != 0);
/*     */   }
/*     */   private void a(int i, boolean flag) {
/*     */     int j;
/* 113 */     byte b0 = ((Byte)this.datawatcher.<Byte>get(a)).byteValue();
/*     */ 
/*     */     
/* 116 */     if (flag) {
/* 117 */       j = b0 | i;
/*     */     } else {
/* 119 */       j = b0 & (i ^ 0xFFFFFFFF);
/*     */     } 
/*     */     
/* 122 */     this.datawatcher.set(a, Byte.valueOf((byte)(j & 0xFF)));
/*     */   }
/*     */   
/*     */   public boolean dn() {
/* 126 */     return c(1);
/*     */   }
/*     */   
/*     */   public void a(boolean flag) {
/* 130 */     a(1, flag);
/*     */   }
/*     */   
/*     */   public void a(EntityInsentient entityinsentient) {
/* 134 */     this.b = entityinsentient;
/*     */   }
/*     */   
/*     */   public void a(int i) {
/* 138 */     this.bx = true;
/* 139 */     this.by = i;
/*     */   }
/*     */   
/*     */   protected SoundEffect F() {
/* 143 */     return SoundEffects.ig;
/*     */   }
/*     */   
/*     */   protected SoundEffect cf() {
/* 147 */     return SoundEffects.ii;
/*     */   }
/*     */   
/*     */   protected SoundEffect d(DamageSource damagesource) {
/* 151 */     return SoundEffects.ij;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   protected MinecraftKey J() {
/* 156 */     return LootTables.ay;
/*     */   }
/*     */   
/*     */   public float aw() {
/* 160 */     return 1.0F;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public GroupDataEntity prepare(DifficultyDamageScaler difficultydamagescaler, @Nullable GroupDataEntity groupdataentity) {
/* 165 */     a(difficultydamagescaler);
/* 166 */     b(difficultydamagescaler);
/* 167 */     return super.prepare(difficultydamagescaler, groupdataentity);
/*     */   }
/*     */   
/*     */   protected void a(DifficultyDamageScaler difficultydamagescaler) {
/* 171 */     setSlot(EnumItemSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));
/* 172 */     a(EnumItemSlot.MAINHAND, 0.0F);
/*     */   }
/*     */   
/*     */   class b
/*     */     extends PathfinderGoalTarget {
/*     */     public b(EntityCreature entitycreature) {
/* 178 */       super(entitycreature, false);
/*     */     }
/*     */     
/*     */     public boolean a() {
/* 182 */       return (EntityVex.this.b != null && EntityVex.this.b.getGoalTarget() != null && a(EntityVex.this.b.getGoalTarget(), false));
/*     */     }
/*     */     
/*     */     public void c() {
/* 186 */       EntityVex.this.setGoalTarget(EntityVex.this.b.getGoalTarget(), EntityTargetEvent.TargetReason.OWNER_ATTACKED_TARGET, true);
/* 187 */       super.c();
/*     */     }
/*     */   }
/*     */   
/*     */   class d
/*     */     extends PathfinderGoal {
/*     */     public d() {
/* 194 */       a(1);
/*     */     }
/*     */     
/*     */     public boolean a() {
/* 198 */       return (!EntityVex.this.getControllerMove().b() && EntityVex.this.random.nextInt(7) == 0);
/*     */     }
/*     */     
/*     */     public boolean b() {
/* 202 */       return false;
/*     */     }
/*     */     
/*     */     public void e() {
/* 206 */       BlockPosition blockposition = EntityVex.this.dm();
/*     */       
/* 208 */       if (blockposition == null) {
/* 209 */         blockposition = new BlockPosition(EntityVex.this);
/*     */       }
/*     */       
/* 212 */       for (int i = 0; i < 3; i++) {
/* 213 */         BlockPosition blockposition1 = blockposition.a(EntityVex.this.random.nextInt(15) - 7, EntityVex.this.random.nextInt(11) - 5, EntityVex.this.random.nextInt(15) - 7);
/*     */         
/* 215 */         if (EntityVex.this.world.isEmpty(blockposition1)) {
/* 216 */           EntityVex.this.moveController.a(blockposition1.getX() + 0.5D, blockposition1.getY() + 0.5D, blockposition1.getZ() + 0.5D, 0.25D);
/* 217 */           if (EntityVex.this.getGoalTarget() == null) {
/* 218 */             EntityVex.this.getControllerLook().a(blockposition1.getX() + 0.5D, blockposition1.getY() + 0.5D, blockposition1.getZ() + 0.5D, 180.0F, 20.0F);
/*     */           }
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   class a
/*     */     extends PathfinderGoal
/*     */   {
/*     */     public a() {
/* 230 */       a(1);
/*     */     }
/*     */     
/*     */     public boolean a() {
/* 234 */       return (EntityVex.this.getGoalTarget() != null && !EntityVex.this.getControllerMove().b() && EntityVex.this.random.nextInt(7) == 0) ? ((EntityVex.this.h(EntityVex.this.getGoalTarget()) > 4.0D)) : false;
/*     */     }
/*     */     
/*     */     public boolean b() {
/* 238 */       return (EntityVex.this.getControllerMove().b() && EntityVex.this.dn() && EntityVex.this.getGoalTarget() != null && EntityVex.this.getGoalTarget().isAlive());
/*     */     }
/*     */     
/*     */     public void c() {
/* 242 */       EntityLiving entityliving = EntityVex.this.getGoalTarget();
/* 243 */       Vec3D vec3d = entityliving.f(1.0F);
/*     */       
/* 245 */       EntityVex.this.moveController.a(vec3d.x, vec3d.y, vec3d.z, 1.0D);
/* 246 */       EntityVex.this.a(true);
/* 247 */       EntityVex.this.a(SoundEffects.ih, 1.0F, 1.0F);
/*     */     }
/*     */     
/*     */     public void d() {
/* 251 */       EntityVex.this.a(false);
/*     */     }
/*     */     
/*     */     public void e() {
/* 255 */       EntityLiving entityliving = EntityVex.this.getGoalTarget();
/*     */       
/* 257 */       if (EntityVex.this.getBoundingBox().c(entityliving.getBoundingBox())) {
/* 258 */         EntityVex.this.B(entityliving);
/* 259 */         EntityVex.this.a(false);
/*     */       } else {
/* 261 */         double d0 = EntityVex.this.h(entityliving);
/*     */         
/* 263 */         if (d0 < 9.0D) {
/* 264 */           Vec3D vec3d = entityliving.f(1.0F);
/*     */           
/* 266 */           EntityVex.this.moveController.a(vec3d.x, vec3d.y, vec3d.z, 1.0D);
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   class c
/*     */     extends ControllerMove
/*     */   {
/*     */     public c(EntityVex entityvex) {
/* 276 */       super(entityvex);
/*     */     }
/*     */     
/*     */     public void a() {
/* 280 */       if (this.h == ControllerMove.Operation.MOVE_TO) {
/* 281 */         double d0 = this.b - EntityVex.this.locX;
/* 282 */         double d1 = this.c - EntityVex.this.locY;
/* 283 */         double d2 = this.d - EntityVex.this.locZ;
/* 284 */         double d3 = d0 * d0 + d1 * d1 + d2 * d2;
/*     */         
/* 286 */         d3 = MathHelper.sqrt(d3);
/* 287 */         if (d3 < EntityVex.this.getBoundingBox().a()) {
/* 288 */           this.h = ControllerMove.Operation.WAIT;
/* 289 */           EntityVex.this.motX *= 0.5D;
/* 290 */           EntityVex.this.motY *= 0.5D;
/* 291 */           EntityVex.this.motZ *= 0.5D;
/*     */         } else {
/* 293 */           EntityVex.this.motX += d0 / d3 * 0.05D * this.e;
/* 294 */           EntityVex.this.motY += d1 / d3 * 0.05D * this.e;
/* 295 */           EntityVex.this.motZ += d2 / d3 * 0.05D * this.e;
/* 296 */           if (EntityVex.this.getGoalTarget() == null) {
/* 297 */             EntityVex.this.yaw = -((float)MathHelper.c(EntityVex.this.motX, EntityVex.this.motZ)) * 57.295776F;
/* 298 */             EntityVex.this.aN = EntityVex.this.yaw;
/*     */           } else {
/* 300 */             double d4 = (EntityVex.this.getGoalTarget()).locX - EntityVex.this.locX;
/* 301 */             double d5 = (EntityVex.this.getGoalTarget()).locZ - EntityVex.this.locZ;
/*     */             
/* 303 */             EntityVex.this.yaw = -((float)MathHelper.c(d4, d5)) * 57.295776F;
/* 304 */             EntityVex.this.aN = EntityVex.this.yaw;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityVex.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */