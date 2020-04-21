/*     */ package net.minecraft.server.v1_12_R1;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.entity.CraftEntity;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.util.CraftMagicNumbers;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.entity.EntityExplodeEvent;
/*     */ import org.bukkit.event.entity.EntityRegainHealthEvent;
/*     */ 
/*     */ public class EntityEnderDragon extends EntityInsentient implements IComplex, IMonster {
/*  16 */   private static final Logger bJ = LogManager.getLogger();
/*  17 */   public static final DataWatcherObject<Integer> PHASE = DataWatcher.a((Class)EntityEnderDragon.class, DataWatcherRegistry.b);
/*  18 */   public double[][] b = new double[64][3];
/*  19 */   public int c = -1;
/*     */   public EntityComplexPart[] children;
/*  21 */   public EntityComplexPart bw = new EntityComplexPart(this, "head", 6.0F, 6.0F);
/*  22 */   public EntityComplexPart bx = new EntityComplexPart(this, "neck", 6.0F, 6.0F);
/*  23 */   public EntityComplexPart by = new EntityComplexPart(this, "body", 8.0F, 8.0F);
/*  24 */   public EntityComplexPart bz = new EntityComplexPart(this, "tail", 4.0F, 4.0F);
/*  25 */   public EntityComplexPart bA = new EntityComplexPart(this, "tail", 4.0F, 4.0F);
/*  26 */   public EntityComplexPart bB = new EntityComplexPart(this, "tail", 4.0F, 4.0F);
/*  27 */   public EntityComplexPart bC = new EntityComplexPart(this, "wing", 4.0F, 4.0F);
/*  28 */   public EntityComplexPart bD = new EntityComplexPart(this, "wing", 4.0F, 4.0F);
/*     */   public float bE;
/*     */   public float bF;
/*     */   public boolean bG;
/*     */   public int bH;
/*     */   public EntityEnderCrystal currentEnderCrystal;
/*     */   private final EnderDragonBattle bK;
/*     */   private final DragonControllerManager bL;
/*  36 */   private int bM = 200;
/*     */   private int bN;
/*  38 */   private final PathPoint[] bO = new PathPoint[24];
/*  39 */   private final int[] bP = new int[24];
/*  40 */   private final Path bQ = new Path();
/*  41 */   private Explosion explosionSource = new Explosion(null, this, Double.NaN, Double.NaN, Double.NaN, Float.NaN, true, true);
/*     */   
/*     */   public EntityEnderDragon(World world) {
/*  44 */     super(world);
/*  45 */     this.children = new EntityComplexPart[] { this.bw, this.bx, this.by, this.bz, this.bA, this.bB, this.bC, this.bD };
/*  46 */     setHealth(getMaxHealth());
/*  47 */     setSize(16.0F, 8.0F);
/*  48 */     this.noclip = true;
/*  49 */     this.fireProof = true;
/*  50 */     this.bM = 100;
/*  51 */     this.ah = true;
/*  52 */     if (!world.isClientSide && world.worldProvider instanceof WorldProviderTheEnd) {
/*  53 */       this.bK = ((WorldProviderTheEnd)world.worldProvider).t();
/*     */     } else {
/*  55 */       this.bK = null;
/*     */     } 
/*     */     
/*  58 */     this.bL = new DragonControllerManager(this);
/*     */   }
/*     */   
/*     */   protected void initAttributes() {
/*  62 */     super.initAttributes();
/*  63 */     getAttributeInstance(GenericAttributes.maxHealth).setValue(200.0D);
/*     */   }
/*     */   
/*     */   protected void i() {
/*  67 */     super.i();
/*  68 */     getDataWatcher().register(PHASE, Integer.valueOf(DragonControllerPhase.k.b()));
/*     */   }
/*     */   
/*     */   public double[] a(int i, float f) {
/*  72 */     if (getHealth() <= 0.0F) {
/*  73 */       f = 0.0F;
/*     */     }
/*     */     
/*  76 */     f = 1.0F - f;
/*  77 */     int j = this.c - i & 0x3F;
/*  78 */     int k = this.c - i - 1 & 0x3F;
/*  79 */     double[] adouble = new double[3];
/*  80 */     double d0 = this.b[j][0];
/*  81 */     double d1 = MathHelper.g(this.b[k][0] - d0);
/*     */     
/*  83 */     adouble[0] = d0 + d1 * f;
/*  84 */     d0 = this.b[j][1];
/*  85 */     d1 = this.b[k][1] - d0;
/*  86 */     adouble[1] = d0 + d1 * f;
/*  87 */     adouble[2] = this.b[j][2] + (this.b[k][2] - this.b[j][2]) * f;
/*  88 */     return adouble;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void n() {
/*  95 */     if (this.world.isClientSide) {
/*  96 */       setHealth(getHealth());
/*  97 */       if (!isSilent()) {
/*  98 */         float f = MathHelper.cos(this.bF * 6.2831855F);
/*  99 */         float f1 = MathHelper.cos(this.bE * 6.2831855F);
/* 100 */         if (f1 <= -0.3F && f >= -0.3F) {
/* 101 */           this.world.a(this.locX, this.locY, this.locZ, SoundEffects.aX, bK(), 5.0F, 0.8F + this.random.nextFloat() * 0.3F, false);
/*     */         }
/*     */         
/* 104 */         if (!this.bL.a().a() && --this.bM < 0) {
/* 105 */           this.world.a(this.locX, this.locY, this.locZ, SoundEffects.aY, bK(), 2.5F, 0.8F + this.random.nextFloat() * 0.3F, false);
/* 106 */           this.bM = 200 + this.random.nextInt(200);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 111 */     this.bE = this.bF;
/*     */ 
/*     */     
/* 114 */     if (getHealth() <= 0.0F) {
/* 115 */       float f = (this.random.nextFloat() - 0.5F) * 8.0F;
/* 116 */       float f1 = (this.random.nextFloat() - 0.5F) * 4.0F;
/* 117 */       float f2 = (this.random.nextFloat() - 0.5F) * 8.0F;
/* 118 */       this.world.addParticle(EnumParticle.EXPLOSION_LARGE, this.locX + f, this.locY + 2.0D + f1, this.locZ + f2, 0.0D, 0.0D, 0.0D, new int[0]);
/*     */     } else {
/* 120 */       dg();
/* 121 */       float f = 0.2F / (MathHelper.sqrt(this.motX * this.motX + this.motZ * this.motZ) * 10.0F + 1.0F);
/* 122 */       f *= (float)Math.pow(2.0D, this.motY);
/* 123 */       if (this.bL.a().a()) {
/* 124 */         this.bF += 0.1F;
/* 125 */       } else if (this.bG) {
/* 126 */         this.bF += f * 0.5F;
/*     */       } else {
/* 128 */         this.bF += f;
/*     */       } 
/*     */       
/* 131 */       this.yaw = MathHelper.g(this.yaw);
/* 132 */       if (isNoAI()) {
/* 133 */         this.bF = 0.5F;
/*     */       } else {
/* 135 */         if (this.c < 0) {
/* 136 */           for (int i = 0; i < this.b.length; i++) {
/* 137 */             this.b[i][0] = this.yaw;
/* 138 */             this.b[i][1] = this.locY;
/*     */           } 
/*     */         }
/*     */         
/* 142 */         if (++this.c == this.b.length) {
/* 143 */           this.c = 0;
/*     */         }
/*     */         
/* 146 */         this.b[this.c][0] = this.yaw;
/* 147 */         this.b[this.c][1] = this.locY;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 154 */         if (this.world.isClientSide) {
/* 155 */           if (this.bi > 0) {
/* 156 */             double d3 = this.locX + (this.bj - this.locX) / this.bi;
/*     */             
/* 158 */             double d0 = this.locY + (this.bk - this.locY) / this.bi;
/* 159 */             double d1 = this.locZ + (this.bl - this.locZ) / this.bi;
/* 160 */             double d2 = MathHelper.g(this.bm - this.yaw);
/* 161 */             this.yaw = (float)(this.yaw + d2 / this.bi);
/* 162 */             this.pitch = (float)(this.pitch + (this.bn - this.pitch) / this.bi);
/* 163 */             this.bi--;
/* 164 */             setPosition(d3, d0, d1);
/* 165 */             setYawPitch(this.yaw, this.pitch);
/*     */           } 
/*     */           
/* 168 */           this.bL.a().b();
/*     */         } else {
/* 170 */           IDragonController idragoncontroller = this.bL.a();
/*     */           
/* 172 */           idragoncontroller.c();
/* 173 */           if (this.bL.a() != idragoncontroller) {
/* 174 */             idragoncontroller = this.bL.a();
/* 175 */             idragoncontroller.c();
/*     */           } 
/*     */           
/* 178 */           Vec3D vec3d = idragoncontroller.g();
/*     */           
/* 180 */           if (vec3d != null && idragoncontroller.getControllerPhase() != DragonControllerPhase.k) {
/* 181 */             double d0 = vec3d.x - this.locX;
/* 182 */             double d1 = vec3d.y - this.locY;
/* 183 */             double d2 = vec3d.z - this.locZ;
/* 184 */             double d4 = d0 * d0 + d1 * d1 + d2 * d2;
/*     */             
/* 186 */             float f1 = idragoncontroller.f();
/* 187 */             d1 = MathHelper.a(d1 / MathHelper.sqrt(d0 * d0 + d2 * d2), -f1, f1);
/* 188 */             this.motY += d1 * 0.10000000149011612D;
/* 189 */             this.yaw = MathHelper.g(this.yaw);
/* 190 */             double d5 = MathHelper.a(MathHelper.g(180.0D - MathHelper.c(d0, d2) * 57.2957763671875D - this.yaw), -50.0D, 50.0D);
/* 191 */             Vec3D vec3d1 = (new Vec3D(vec3d.x - this.locX, vec3d.y - this.locY, vec3d.z - this.locZ)).a();
/* 192 */             Vec3D vec3d2 = (new Vec3D(MathHelper.sin(this.yaw * 0.017453292F), this.motY, -MathHelper.cos(this.yaw * 0.017453292F))).a();
/*     */             
/* 194 */             float f4 = Math.max(((float)vec3d2.b(vec3d1) + 0.5F) / 1.5F, 0.0F);
/* 195 */             this.bh *= 0.8F;
/* 196 */             this.bh = (float)(this.bh + d5 * idragoncontroller.h());
/* 197 */             this.yaw += this.bh * 0.1F;
/* 198 */             float f5 = (float)(2.0D / (d4 + 1.0D));
/*     */ 
/*     */             
/* 201 */             b(0.0F, 0.0F, -1.0F, 0.06F * (f4 * f5 + 1.0F - f5));
/* 202 */             if (this.bG) {
/* 203 */               move(EnumMoveType.SELF, this.motX * 0.800000011920929D, this.motY * 0.800000011920929D, this.motZ * 0.800000011920929D);
/*     */             } else {
/* 205 */               move(EnumMoveType.SELF, this.motX, this.motY, this.motZ);
/*     */             } 
/*     */             
/* 208 */             Vec3D vec3d3 = (new Vec3D(this.motX, this.motY, this.motZ)).a();
/* 209 */             float f7 = ((float)vec3d3.b(vec3d2) + 1.0F) / 2.0F;
/*     */             
/* 211 */             f7 = 0.8F + 0.15F * f7;
/* 212 */             this.motX *= f7;
/* 213 */             this.motZ *= f7;
/* 214 */             this.motY *= 0.9100000262260437D;
/*     */           } 
/*     */         } 
/*     */         
/* 218 */         this.aN = this.yaw;
/* 219 */         this.bw.width = 1.0F;
/* 220 */         this.bw.length = 1.0F;
/* 221 */         this.bx.width = 3.0F;
/* 222 */         this.bx.length = 3.0F;
/* 223 */         this.bz.width = 2.0F;
/* 224 */         this.bz.length = 2.0F;
/* 225 */         this.bA.width = 2.0F;
/* 226 */         this.bA.length = 2.0F;
/* 227 */         this.bB.width = 2.0F;
/* 228 */         this.bB.length = 2.0F;
/* 229 */         this.by.length = 3.0F;
/* 230 */         this.by.width = 5.0F;
/* 231 */         this.bC.length = 2.0F;
/* 232 */         this.bC.width = 4.0F;
/* 233 */         this.bD.length = 3.0F;
/* 234 */         this.bD.width = 4.0F;
/* 235 */         Vec3D[] avec3d = new Vec3D[this.children.length];
/*     */         
/* 237 */         for (int j = 0; j < this.children.length; j++) {
/* 238 */           avec3d[j] = new Vec3D((this.children[j]).locX, (this.children[j]).locY, (this.children[j]).locZ);
/*     */         }
/*     */         
/* 241 */         float f2 = (float)(a(5, 1.0F)[1] - a(10, 1.0F)[1]) * 10.0F * 0.017453292F;
/* 242 */         float f8 = MathHelper.cos(f2);
/* 243 */         float f9 = MathHelper.sin(f2);
/* 244 */         float f10 = this.yaw * 0.017453292F;
/* 245 */         float f11 = MathHelper.sin(f10);
/* 246 */         float f12 = MathHelper.cos(f10);
/*     */         
/* 248 */         this.by.B_();
/* 249 */         this.by.setPositionRotation(this.locX + (f11 * 0.5F), this.locY, this.locZ - (f12 * 0.5F), 0.0F, 0.0F);
/* 250 */         this.bC.B_();
/* 251 */         this.bC.setPositionRotation(this.locX + (f12 * 4.5F), this.locY + 2.0D, this.locZ + (f11 * 4.5F), 0.0F, 0.0F);
/* 252 */         this.bD.B_();
/* 253 */         this.bD.setPositionRotation(this.locX - (f12 * 4.5F), this.locY + 2.0D, this.locZ - (f11 * 4.5F), 0.0F, 0.0F);
/* 254 */         if (!this.world.isClientSide && this.hurtTicks == 0) {
/* 255 */           a(this.world.getEntities(this, this.bC.getBoundingBox().grow(4.0D, 2.0D, 4.0D).d(0.0D, -2.0D, 0.0D)));
/* 256 */           a(this.world.getEntities(this, this.bD.getBoundingBox().grow(4.0D, 2.0D, 4.0D).d(0.0D, -2.0D, 0.0D)));
/* 257 */           b(this.world.getEntities(this, this.bw.getBoundingBox().g(1.0D)));
/* 258 */           b(this.world.getEntities(this, this.bx.getBoundingBox().g(1.0D)));
/*     */         } 
/*     */         
/* 261 */         double[] adouble = a(5, 1.0F);
/* 262 */         float f13 = MathHelper.sin(this.yaw * 0.017453292F - this.bh * 0.01F);
/* 263 */         float f14 = MathHelper.cos(this.yaw * 0.017453292F - this.bh * 0.01F);
/*     */         
/* 265 */         this.bw.B_();
/* 266 */         this.bx.B_();
/* 267 */         float f3 = q(1.0F);
/* 268 */         this.bw.setPositionRotation(this.locX + (f13 * 6.5F * f8), this.locY + f3 + (f9 * 6.5F), this.locZ - (f14 * 6.5F * f8), 0.0F, 0.0F);
/* 269 */         this.bx.setPositionRotation(this.locX + (f13 * 5.5F * f8), this.locY + f3 + (f9 * 5.5F), this.locZ - (f14 * 5.5F * f8), 0.0F, 0.0F);
/*     */         
/*     */         int k;
/*     */         
/* 273 */         for (k = 0; k < 3; k++) {
/* 274 */           EntityComplexPart entitycomplexpart = null;
/*     */           
/* 276 */           if (k == 0) {
/* 277 */             entitycomplexpart = this.bz;
/*     */           }
/*     */           
/* 280 */           if (k == 1) {
/* 281 */             entitycomplexpart = this.bA;
/*     */           }
/*     */           
/* 284 */           if (k == 2) {
/* 285 */             entitycomplexpart = this.bB;
/*     */           }
/*     */           
/* 288 */           double[] adouble1 = a(12 + k * 2, 1.0F);
/* 289 */           float f15 = this.yaw * 0.017453292F + c(adouble1[0] - adouble[0]) * 0.017453292F;
/* 290 */           float f16 = MathHelper.sin(f15);
/* 291 */           float f17 = MathHelper.cos(f15);
/*     */ 
/*     */           
/* 294 */           float f4 = (k + 1) * 2.0F;
/* 295 */           entitycomplexpart.B_();
/* 296 */           entitycomplexpart.setPositionRotation(this.locX - ((f11 * 1.5F + f16 * f4) * f8), this.locY + adouble1[1] - adouble[1] - ((f4 + 1.5F) * f9) + 1.5D, this.locZ + ((f12 * 1.5F + f17 * f4) * f8), 0.0F, 0.0F);
/*     */         } 
/*     */         
/* 299 */         if (!this.world.isClientSide) {
/* 300 */           this.bG = b(this.bw.getBoundingBox()) | b(this.bx.getBoundingBox()) | b(this.by.getBoundingBox());
/* 301 */           if (this.bK != null) {
/* 302 */             this.bK.b(this);
/*     */           }
/*     */         } 
/*     */         
/* 306 */         for (k = 0; k < this.children.length; k++) {
/* 307 */           (this.children[k]).lastX = (avec3d[k]).x;
/* 308 */           (this.children[k]).lastY = (avec3d[k]).y;
/* 309 */           (this.children[k]).lastZ = (avec3d[k]).z;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private float q(float f) {
/*     */     double d0;
/* 319 */     if (this.bL.a().a()) {
/* 320 */       d0 = -1.0D;
/*     */     } else {
/* 322 */       double[] adouble = a(5, 1.0F);
/* 323 */       double[] adouble1 = a(0, 1.0F);
/*     */       
/* 325 */       d0 = adouble[1] - adouble1[1];
/*     */     } 
/*     */     
/* 328 */     return (float)d0;
/*     */   }
/*     */   
/*     */   private void dg() {
/* 332 */     if (this.currentEnderCrystal != null) {
/* 333 */       if (this.currentEnderCrystal.dead) {
/* 334 */         this.currentEnderCrystal = null;
/* 335 */       } else if (this.ticksLived % 10 == 0 && getHealth() < getMaxHealth()) {
/*     */         
/* 337 */         EntityRegainHealthEvent event = new EntityRegainHealthEvent((Entity)getBukkitEntity(), 1.0D, EntityRegainHealthEvent.RegainReason.ENDER_CRYSTAL);
/* 338 */         this.world.getServer().getPluginManager().callEvent((Event)event);
/*     */         
/* 340 */         if (!event.isCancelled()) {
/* 341 */           setHealth((float)(getHealth() + event.getAmount()));
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 347 */     if (this.random.nextInt(10) == 0) {
/* 348 */       List<EntityEnderCrystal> list = this.world.a(EntityEnderCrystal.class, getBoundingBox().g(32.0D));
/* 349 */       EntityEnderCrystal entityendercrystal = null;
/* 350 */       double d0 = Double.MAX_VALUE;
/* 351 */       Iterator<EntityEnderCrystal> iterator = list.iterator();
/*     */       
/* 353 */       while (iterator.hasNext()) {
/* 354 */         EntityEnderCrystal entityendercrystal1 = iterator.next();
/* 355 */         double d1 = entityendercrystal1.h(this);
/*     */         
/* 357 */         if (d1 < d0) {
/* 358 */           d0 = d1;
/* 359 */           entityendercrystal = entityendercrystal1;
/*     */         } 
/*     */       } 
/*     */       
/* 363 */       this.currentEnderCrystal = entityendercrystal;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void a(List<Entity> list) {
/* 369 */     double d0 = ((this.by.getBoundingBox()).a + (this.by.getBoundingBox()).d) / 2.0D;
/* 370 */     double d1 = ((this.by.getBoundingBox()).c + (this.by.getBoundingBox()).f) / 2.0D;
/* 371 */     Iterator<Entity> iterator = list.iterator();
/*     */     
/* 373 */     while (iterator.hasNext()) {
/* 374 */       Entity entity = iterator.next();
/*     */       
/* 376 */       if (entity instanceof EntityLiving) {
/* 377 */         double d2 = entity.locX - d0;
/* 378 */         double d3 = entity.locZ - d1;
/* 379 */         double d4 = d2 * d2 + d3 * d3;
/*     */         
/* 381 */         entity.f(d2 / d4 * 4.0D, 0.20000000298023224D, d3 / d4 * 4.0D);
/* 382 */         if (!this.bL.a().a() && ((EntityLiving)entity).bT() < entity.ticksLived - 2) {
/* 383 */           entity.damageEntity(DamageSource.mobAttack(this), 5.0F);
/* 384 */           a(this, entity);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void b(List<Entity> list) {
/* 392 */     for (int i = 0; i < list.size(); i++) {
/* 393 */       Entity entity = list.get(i);
/*     */       
/* 395 */       if (entity instanceof EntityLiving) {
/* 396 */         entity.damageEntity(DamageSource.mobAttack(this), 10.0F);
/* 397 */         a(this, entity);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private float c(double d0) {
/* 404 */     return (float)MathHelper.g(d0);
/*     */   }
/*     */   
/*     */   private boolean b(AxisAlignedBB axisalignedbb) {
/* 408 */     int i = MathHelper.floor(axisalignedbb.a);
/* 409 */     int j = MathHelper.floor(axisalignedbb.b);
/* 410 */     int k = MathHelper.floor(axisalignedbb.c);
/* 411 */     int l = MathHelper.floor(axisalignedbb.d);
/* 412 */     int i1 = MathHelper.floor(axisalignedbb.e);
/* 413 */     int j1 = MathHelper.floor(axisalignedbb.f);
/* 414 */     boolean flag = false;
/* 415 */     boolean flag1 = false;
/*     */     
/* 417 */     List<Block> destroyedBlocks = new ArrayList<>();
/* 418 */     CraftWorld craftWorld = this.world.getWorld();
/*     */ 
/*     */     
/* 421 */     for (int k1 = i; k1 <= l; k1++) {
/* 422 */       for (int l1 = j; l1 <= i1; l1++) {
/* 423 */         for (int i2 = k; i2 <= j1; i2++) {
/* 424 */           BlockPosition blockposition = new BlockPosition(k1, l1, i2);
/* 425 */           IBlockData iblockdata = this.world.getType(blockposition);
/* 426 */           Block block = iblockdata.getBlock();
/*     */           
/* 428 */           if (iblockdata.getMaterial() != Material.AIR && iblockdata.getMaterial() != Material.FIRE) {
/* 429 */             if (!this.world.getGameRules().getBoolean("mobGriefing")) {
/* 430 */               flag = true;
/* 431 */             } else if (block != Blocks.BARRIER && block != Blocks.OBSIDIAN && block != Blocks.END_STONE && block != Blocks.BEDROCK && block != Blocks.END_PORTAL && block != Blocks.END_PORTAL_FRAME) {
/* 432 */               if (block != Blocks.COMMAND_BLOCK && block != Blocks.dc && block != Blocks.dd && block != Blocks.IRON_BARS && block != Blocks.END_GATEWAY) {
/*     */ 
/*     */                 
/* 435 */                 flag1 = true;
/* 436 */                 destroyedBlocks.add(craftWorld.getBlockAt(k1, l1, i2));
/*     */               } else {
/*     */                 
/* 439 */                 flag = true;
/*     */               } 
/*     */             } else {
/* 442 */               flag = true;
/*     */             } 
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 450 */     CraftEntity craftEntity = getBukkitEntity();
/* 451 */     EntityExplodeEvent event = new EntityExplodeEvent((Entity)craftEntity, craftEntity.getLocation(), destroyedBlocks, 0.0F);
/* 452 */     craftEntity.getServer().getPluginManager().callEvent((Event)event);
/* 453 */     if (event.isCancelled())
/*     */     {
/*     */       
/* 456 */       return flag; } 
/* 457 */     if (event.getYield() == 0.0F) {
/*     */       
/* 459 */       for (Block block : event.blockList()) {
/* 460 */         this.world.setAir(new BlockPosition(block.getX(), block.getY(), block.getZ()));
/*     */       }
/*     */     } else {
/* 463 */       for (Block block : event.blockList()) {
/* 464 */         Material blockId = block.getType();
/* 465 */         if (blockId == Material.AIR) {
/*     */           continue;
/*     */         }
/*     */         
/* 469 */         int blockX = block.getX();
/* 470 */         int blockY = block.getY();
/* 471 */         int blockZ = block.getZ();
/*     */         
/* 473 */         Block nmsBlock = CraftMagicNumbers.getBlock(blockId);
/* 474 */         if (nmsBlock.a(this.explosionSource)) {
/* 475 */           nmsBlock.dropNaturally(this.world, new BlockPosition(blockX, blockY, blockZ), nmsBlock.fromLegacyData(block.getData()), event.getYield(), 0);
/*     */         }
/* 477 */         nmsBlock.wasExploded(this.world, new BlockPosition(blockX, blockY, blockZ), this.explosionSource);
/*     */         
/* 479 */         this.world.setAir(new BlockPosition(blockX, blockY, blockZ));
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 484 */     if (flag1) {
/* 485 */       double d0 = axisalignedbb.a + (axisalignedbb.d - axisalignedbb.a) * this.random.nextFloat();
/* 486 */       double d1 = axisalignedbb.b + (axisalignedbb.e - axisalignedbb.b) * this.random.nextFloat();
/* 487 */       double d2 = axisalignedbb.c + (axisalignedbb.f - axisalignedbb.c) * this.random.nextFloat();
/*     */       
/* 489 */       this.world.addParticle(EnumParticle.EXPLOSION_LARGE, d0, d1, d2, 0.0D, 0.0D, 0.0D, new int[0]);
/*     */     } 
/*     */     
/* 492 */     return flag;
/*     */   }
/*     */   
/*     */   public boolean a(EntityComplexPart entitycomplexpart, DamageSource damagesource, float f) {
/* 496 */     f = this.bL.a().a(entitycomplexpart, damagesource, f);
/* 497 */     if (entitycomplexpart != this.bw) {
/* 498 */       f = f / 4.0F + Math.min(f, 1.0F);
/*     */     }
/*     */     
/* 501 */     if (f < 0.01F) {
/* 502 */       return false;
/*     */     }
/* 504 */     if (damagesource.getEntity() instanceof EntityHuman || damagesource.isExplosion()) {
/* 505 */       float f1 = getHealth();
/*     */       
/* 507 */       dealDamage(damagesource, f);
/* 508 */       if (getHealth() <= 0.0F && !this.bL.a().a()) {
/* 509 */         setHealth(1.0F);
/* 510 */         this.bL.setControllerPhase(DragonControllerPhase.j);
/*     */       } 
/*     */       
/* 513 */       if (this.bL.a().a()) {
/* 514 */         this.bN = (int)(this.bN + f1 - getHealth());
/* 515 */         if (this.bN > 0.25F * getMaxHealth()) {
/* 516 */           this.bN = 0;
/* 517 */           this.bL.setControllerPhase(DragonControllerPhase.e);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 522 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean damageEntity(DamageSource damagesource, float f) {
/* 527 */     if (damagesource instanceof EntityDamageSource && ((EntityDamageSource)damagesource).x()) {
/* 528 */       a(this.by, damagesource, f);
/*     */     }
/*     */     
/* 531 */     return false;
/*     */   }
/*     */   
/*     */   protected boolean dealDamage(DamageSource damagesource, float f) {
/* 535 */     return super.damageEntity(damagesource, f);
/*     */   }
/*     */   
/*     */   public void killEntity() {
/* 539 */     die();
/* 540 */     if (this.bK != null) {
/* 541 */       this.bK.b(this);
/* 542 */       this.bK.a(this);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void bO() {
/* 548 */     if (this.bK != null) {
/* 549 */       this.bK.b(this);
/*     */     }
/*     */     
/* 552 */     this.bH++;
/* 553 */     if (this.bH >= 180 && this.bH <= 200) {
/* 554 */       float f = (this.random.nextFloat() - 0.5F) * 8.0F;
/* 555 */       float f1 = (this.random.nextFloat() - 0.5F) * 4.0F;
/* 556 */       float f2 = (this.random.nextFloat() - 0.5F) * 8.0F;
/*     */       
/* 558 */       this.world.addParticle(EnumParticle.EXPLOSION_HUGE, this.locX + f, this.locY + 2.0D + f1, this.locZ + f2, 0.0D, 0.0D, 0.0D, new int[0]);
/*     */     } 
/*     */     
/* 561 */     boolean flag = this.world.getGameRules().getBoolean("doMobLoot");
/* 562 */     short short0 = 500;
/*     */     
/* 564 */     if (this.bK != null && !this.bK.d()) {
/* 565 */       short0 = 12000;
/*     */     }
/*     */     
/* 568 */     if (!this.world.isClientSide) {
/* 569 */       if (this.bH > 150 && this.bH % 5 == 0 && flag) {
/* 570 */         a(MathHelper.d(short0 * 0.08F));
/*     */       }
/*     */       
/* 573 */       if (this.bH == 1) {
/*     */ 
/*     */         
/* 576 */         int viewDistance = ((WorldServer)this.world).getServer().getViewDistance() * 16;
/* 577 */         for (EntityPlayer player : (MinecraftServer.getServer().getPlayerList()).players) {
/* 578 */           double deltaX = this.locX - player.locX;
/* 579 */           double deltaZ = this.locZ - player.locZ;
/* 580 */           double distanceSquared = deltaX * deltaX + deltaZ * deltaZ;
/* 581 */           if (this.world.spigotConfig.dragonDeathSoundRadius > 0 && distanceSquared > (this.world.spigotConfig.dragonDeathSoundRadius * this.world.spigotConfig.dragonDeathSoundRadius))
/* 582 */             continue;  if (distanceSquared > (viewDistance * viewDistance)) {
/* 583 */             double deltaLength = Math.sqrt(distanceSquared);
/* 584 */             double relativeX = player.locX + deltaX / deltaLength * viewDistance;
/* 585 */             double relativeZ = player.locZ + deltaZ / deltaLength * viewDistance;
/* 586 */             player.playerConnection.sendPacket(new PacketPlayOutWorldEvent(1028, new BlockPosition((int)relativeX, (int)this.locY, (int)relativeZ), 0, true)); continue;
/*     */           } 
/* 588 */           player.playerConnection.sendPacket(new PacketPlayOutWorldEvent(1028, new BlockPosition((int)this.locX, (int)this.locY, (int)this.locZ), 0, true));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 595 */     move(EnumMoveType.SELF, 0.0D, 0.10000000149011612D, 0.0D);
/* 596 */     this.yaw += 20.0F;
/* 597 */     this.aN = this.yaw;
/* 598 */     if (this.bH == 200 && !this.world.isClientSide) {
/* 599 */       if (flag) {
/* 600 */         a(MathHelper.d(short0 * 0.2F));
/*     */       }
/*     */       
/* 603 */       if (this.bK != null) {
/* 604 */         this.bK.a(this);
/*     */       }
/*     */       
/* 607 */       die();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void a(int i) {
/* 613 */     while (i > 0) {
/* 614 */       int j = EntityExperienceOrb.getOrbValue(i);
/*     */       
/* 616 */       i -= j;
/* 617 */       this.world.addEntity(new EntityExperienceOrb(this.world, this.locX, this.locY, this.locZ, j));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int p() {
/* 623 */     if (this.bO[0] == null) {
/* 624 */       for (int i = 0; i < 24; i++) {
/* 625 */         int k, l, j = 5;
/*     */ 
/*     */ 
/*     */         
/* 629 */         if (i < 12) {
/* 630 */           k = (int)(60.0F * MathHelper.cos(2.0F * (-3.1415927F + 0.2617994F * i)));
/* 631 */           l = (int)(60.0F * MathHelper.sin(2.0F * (-3.1415927F + 0.2617994F * i)));
/*     */ 
/*     */         
/*     */         }
/* 635 */         else if (i < 20) {
/* 636 */           int i1 = i - 12;
/* 637 */           k = (int)(40.0F * MathHelper.cos(2.0F * (-3.1415927F + 0.3926991F * i1)));
/* 638 */           l = (int)(40.0F * MathHelper.sin(2.0F * (-3.1415927F + 0.3926991F * i1)));
/* 639 */           j += 10;
/*     */         } else {
/* 641 */           int i1 = i - 20;
/* 642 */           k = (int)(20.0F * MathHelper.cos(2.0F * (-3.1415927F + 0.7853982F * i1)));
/* 643 */           l = (int)(20.0F * MathHelper.sin(2.0F * (-3.1415927F + 0.7853982F * i1)));
/*     */         } 
/*     */ 
/*     */         
/* 647 */         int j1 = Math.max(this.world.getSeaLevel() + 10, this.world.q(new BlockPosition(k, 0, l)).getY() + j);
/*     */         
/* 649 */         this.bO[i] = new PathPoint(k, j1, l);
/*     */       } 
/*     */       
/* 652 */       this.bP[0] = 6146;
/* 653 */       this.bP[1] = 8197;
/* 654 */       this.bP[2] = 8202;
/* 655 */       this.bP[3] = 16404;
/* 656 */       this.bP[4] = 32808;
/* 657 */       this.bP[5] = 32848;
/* 658 */       this.bP[6] = 65696;
/* 659 */       this.bP[7] = 131392;
/* 660 */       this.bP[8] = 131712;
/* 661 */       this.bP[9] = 263424;
/* 662 */       this.bP[10] = 526848;
/* 663 */       this.bP[11] = 525313;
/* 664 */       this.bP[12] = 1581057;
/* 665 */       this.bP[13] = 3166214;
/* 666 */       this.bP[14] = 2138120;
/* 667 */       this.bP[15] = 6373424;
/* 668 */       this.bP[16] = 4358208;
/* 669 */       this.bP[17] = 12910976;
/* 670 */       this.bP[18] = 9044480;
/* 671 */       this.bP[19] = 9706496;
/* 672 */       this.bP[20] = 15216640;
/* 673 */       this.bP[21] = 13688832;
/* 674 */       this.bP[22] = 11763712;
/* 675 */       this.bP[23] = 8257536;
/*     */     } 
/*     */     
/* 678 */     return k(this.locX, this.locY, this.locZ);
/*     */   }
/*     */   
/*     */   public int k(double d0, double d1, double d2) {
/* 682 */     float f = 10000.0F;
/* 683 */     int i = 0;
/* 684 */     PathPoint pathpoint = new PathPoint(MathHelper.floor(d0), MathHelper.floor(d1), MathHelper.floor(d2));
/* 685 */     byte b0 = 0;
/*     */     
/* 687 */     if (this.bK == null || this.bK.c() == 0) {
/* 688 */       b0 = 12;
/*     */     }
/*     */     
/* 691 */     for (int j = b0; j < 24; j++) {
/* 692 */       if (this.bO[j] != null) {
/* 693 */         float f1 = this.bO[j].b(pathpoint);
/*     */         
/* 695 */         if (f1 < f) {
/* 696 */           f = f1;
/* 697 */           i = j;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 702 */     return i;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public PathEntity a(int i, int j, @Nullable PathPoint pathpoint) {
/* 709 */     for (int k = 0; k < 24; k++) {
/* 710 */       PathPoint pathPoint = this.bO[k];
/* 711 */       pathPoint.i = false;
/* 712 */       pathPoint.g = 0.0F;
/* 713 */       pathPoint.e = 0.0F;
/* 714 */       pathPoint.f = 0.0F;
/* 715 */       pathPoint.h = null;
/* 716 */       pathPoint.d = -1;
/*     */     } 
/*     */     
/* 719 */     PathPoint pathpoint2 = this.bO[i];
/*     */     
/* 721 */     PathPoint pathpoint1 = this.bO[j];
/* 722 */     pathpoint2.e = 0.0F;
/* 723 */     pathpoint2.f = pathpoint2.a(pathpoint1);
/* 724 */     pathpoint2.g = pathpoint2.f;
/* 725 */     this.bQ.a();
/* 726 */     this.bQ.a(pathpoint2);
/* 727 */     PathPoint pathpoint3 = pathpoint2;
/* 728 */     byte b0 = 0;
/*     */     
/* 730 */     if (this.bK == null || this.bK.c() == 0) {
/* 731 */       b0 = 12;
/*     */     }
/*     */ 
/*     */     
/* 735 */     while (!this.bQ.e()) {
/* 736 */       PathPoint pathpoint4 = this.bQ.c();
/*     */       
/* 738 */       if (pathpoint4.equals(pathpoint1)) {
/* 739 */         if (pathpoint != null) {
/* 740 */           pathpoint.h = pathpoint1;
/* 741 */           pathpoint1 = pathpoint;
/*     */         } 
/*     */         
/* 744 */         return a(pathpoint2, pathpoint1);
/*     */       } 
/*     */       
/* 747 */       if (pathpoint4.a(pathpoint1) < pathpoint3.a(pathpoint1)) {
/* 748 */         pathpoint3 = pathpoint4;
/*     */       }
/*     */       
/* 751 */       pathpoint4.i = true;
/* 752 */       int l = 0;
/* 753 */       int i1 = 0;
/*     */ 
/*     */       
/* 756 */       while (i1 < 24) {
/* 757 */         if (this.bO[i1] != pathpoint4) {
/* 758 */           i1++;
/*     */           
/*     */           continue;
/*     */         } 
/* 762 */         l = i1;
/*     */       } 
/*     */       
/* 765 */       i1 = b0;
/*     */ 
/*     */       
/* 768 */       while (i1 < 24) {
/*     */ 
/*     */ 
/*     */         
/* 772 */         if ((this.bP[l] & 1 << i1) > 0) {
/* 773 */           PathPoint pathpoint5 = this.bO[i1];
/*     */           
/* 775 */           if (!pathpoint5.i) {
/* 776 */             float f = pathpoint4.e + pathpoint4.a(pathpoint5);
/*     */             
/* 778 */             if (!pathpoint5.a() || f < pathpoint5.e) {
/* 779 */               pathpoint5.h = pathpoint4;
/* 780 */               pathpoint5.e = f;
/* 781 */               pathpoint5.f = pathpoint5.a(pathpoint1);
/* 782 */               if (pathpoint5.a()) {
/* 783 */                 this.bQ.a(pathpoint5, pathpoint5.e + pathpoint5.f);
/*     */               } else {
/* 785 */                 pathpoint5.g = pathpoint5.e + pathpoint5.f;
/* 786 */                 this.bQ.a(pathpoint5);
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 792 */         i1++;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 797 */     if (pathpoint3 == pathpoint2) {
/* 798 */       return null;
/*     */     }
/* 800 */     bJ.debug("Failed to find path from {} to {}", Integer.valueOf(i), Integer.valueOf(j));
/* 801 */     if (pathpoint != null) {
/* 802 */       pathpoint.h = pathpoint3;
/* 803 */       pathpoint3 = pathpoint;
/*     */     } 
/*     */     
/* 806 */     return a(pathpoint2, pathpoint3);
/*     */   }
/*     */ 
/*     */   
/*     */   private PathEntity a(PathPoint pathpoint, PathPoint pathpoint1) {
/* 811 */     int i = 1;
/*     */     
/*     */     PathPoint pathpoint2;
/*     */     
/* 815 */     for (pathpoint2 = pathpoint1; pathpoint2.h != null; pathpoint2 = pathpoint2.h) {
/* 816 */       i++;
/*     */     }
/*     */     
/* 819 */     PathPoint[] apathpoint = new PathPoint[i];
/*     */     
/* 821 */     pathpoint2 = pathpoint1;
/* 822 */     i--;
/*     */     
/* 824 */     for (apathpoint[i] = pathpoint1; pathpoint2.h != null; apathpoint[i] = pathpoint2) {
/* 825 */       pathpoint2 = pathpoint2.h;
/* 826 */       i--;
/*     */     } 
/*     */     
/* 829 */     return new PathEntity(apathpoint);
/*     */   }
/*     */   
/*     */   public static void a(DataConverterManager dataconvertermanager) {
/* 833 */     EntityInsentient.a(dataconvertermanager, EntityEnderDragon.class);
/*     */   }
/*     */   
/*     */   public void b(NBTTagCompound nbttagcompound) {
/* 837 */     super.b(nbttagcompound);
/* 838 */     nbttagcompound.setInt("DragonPhase", this.bL.a().getControllerPhase().b());
/*     */   }
/*     */   
/*     */   public void a(NBTTagCompound nbttagcompound) {
/* 842 */     super.a(nbttagcompound);
/* 843 */     if (nbttagcompound.hasKey("DragonPhase")) {
/* 844 */       this.bL.setControllerPhase(DragonControllerPhase.getById(nbttagcompound.getInt("DragonPhase")));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void L() {}
/*     */   
/*     */   public Entity[] bb() {
/* 852 */     return (Entity[])this.children;
/*     */   }
/*     */   
/*     */   public boolean isInteractable() {
/* 856 */     return false;
/*     */   }
/*     */   
/*     */   public World a() {
/* 860 */     return this.world;
/*     */   }
/*     */   
/*     */   public SoundCategory bK() {
/* 864 */     return SoundCategory.HOSTILE;
/*     */   }
/*     */   
/*     */   protected SoundEffect F() {
/* 868 */     return SoundEffects.aU;
/*     */   }
/*     */   
/*     */   protected SoundEffect d(DamageSource damagesource) {
/* 872 */     return SoundEffects.aZ;
/*     */   }
/*     */   
/*     */   protected float cq() {
/* 876 */     return 5.0F;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   protected MinecraftKey J() {
/* 881 */     return LootTables.az;
/*     */   }
/*     */   public Vec3D a(float f) {
/*     */     Vec3D vec3d;
/* 885 */     IDragonController idragoncontroller = this.bL.a();
/* 886 */     DragonControllerPhase<? extends IDragonController> dragoncontrollerphase = idragoncontroller.getControllerPhase();
/*     */ 
/*     */ 
/*     */     
/* 890 */     if (dragoncontrollerphase != DragonControllerPhase.d && dragoncontrollerphase != DragonControllerPhase.e) {
/* 891 */       if (idragoncontroller.a()) {
/* 892 */         float f2 = this.pitch;
/*     */         
/* 894 */         float f1 = 1.5F;
/* 895 */         this.pitch = -45.0F;
/* 896 */         vec3d = e(f);
/* 897 */         this.pitch = f2;
/*     */       } else {
/* 899 */         vec3d = e(f);
/*     */       } 
/*     */     } else {
/* 902 */       BlockPosition blockposition = this.world.q(WorldGenEndTrophy.a);
/*     */       
/* 904 */       float f1 = Math.max(MathHelper.sqrt(d(blockposition)) / 4.0F, 1.0F);
/* 905 */       float f3 = 6.0F / f1;
/* 906 */       float f4 = this.pitch;
/*     */ 
/*     */       
/* 909 */       this.pitch = -f3 * 1.5F * 5.0F;
/* 910 */       vec3d = e(f);
/* 911 */       this.pitch = f4;
/*     */     } 
/*     */     
/* 914 */     return vec3d;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(EntityEnderCrystal entityendercrystal, BlockPosition blockposition, DamageSource damagesource) {
/*     */     EntityHuman entityhuman;
/* 920 */     if (damagesource.getEntity() instanceof EntityHuman) {
/* 921 */       entityhuman = (EntityHuman)damagesource.getEntity();
/*     */     } else {
/* 923 */       entityhuman = this.world.a(blockposition, 64.0D, 64.0D);
/*     */     } 
/*     */     
/* 926 */     if (entityendercrystal == this.currentEnderCrystal) {
/* 927 */       a(this.bw, DamageSource.b(entityhuman), 10.0F);
/*     */     }
/*     */     
/* 930 */     this.bL.a().a(entityendercrystal, blockposition, damagesource, entityhuman);
/*     */   }
/*     */   
/*     */   public void a(DataWatcherObject<?> datawatcherobject) {
/* 934 */     if (PHASE.equals(datawatcherobject) && this.world.isClientSide) {
/* 935 */       this.bL.setControllerPhase(DragonControllerPhase.getById(((Integer)getDataWatcher().<Integer>get(PHASE)).intValue()));
/*     */     }
/*     */     
/* 938 */     super.a(datawatcherobject);
/*     */   }
/*     */   
/*     */   public DragonControllerManager getDragonControllerManager() {
/* 942 */     return this.bL;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public EnderDragonBattle df() {
/* 947 */     return this.bK;
/*     */   }
/*     */   
/*     */   public void addEffect(MobEffect mobeffect) {}
/*     */   
/*     */   protected boolean n(Entity entity) {
/* 953 */     return false;
/*     */   }
/*     */   
/*     */   public boolean bf() {
/* 957 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityEnderDragon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */