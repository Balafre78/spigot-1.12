/*     */ package net.minecraft.server.v1_12_R1;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.CraftServer;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.entity.CraftEntity;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.Vehicle;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.vehicle.VehicleDamageEvent;
/*     */ import org.bukkit.event.vehicle.VehicleDestroyEvent;
/*     */ import org.bukkit.event.vehicle.VehicleEntityCollisionEvent;
/*     */ import org.bukkit.event.vehicle.VehicleMoveEvent;
/*     */ import org.bukkit.event.vehicle.VehicleUpdateEvent;
/*     */ 
/*     */ public class EntityBoat extends Entity {
/*  19 */   private static final DataWatcherObject<Integer> a = DataWatcher.a((Class)EntityBoat.class, DataWatcherRegistry.b);
/*  20 */   private static final DataWatcherObject<Integer> b = DataWatcher.a((Class)EntityBoat.class, DataWatcherRegistry.b);
/*  21 */   private static final DataWatcherObject<Float> c = DataWatcher.a((Class)EntityBoat.class, DataWatcherRegistry.c);
/*  22 */   private static final DataWatcherObject<Integer> d = DataWatcher.a((Class)EntityBoat.class, DataWatcherRegistry.b);
/*  23 */   private static final DataWatcherObject<Boolean>[] e = new DataWatcherObject[] { DataWatcher.a((Class)EntityBoat.class, DataWatcherRegistry.h), DataWatcher.a((Class)EntityBoat.class, DataWatcherRegistry.h) };
/*     */   
/*     */   private final float[] f;
/*     */   
/*     */   private float g;
/*     */   
/*     */   private float h;
/*     */   private float at;
/*     */   private int au;
/*     */   private double av;
/*     */   private double aw;
/*     */   private double ax;
/*     */   private double ay;
/*     */   private double az;
/*     */   private boolean aA;
/*     */   private boolean aB;
/*     */   private boolean aC;
/*     */   private boolean aD;
/*     */   private double aE;
/*     */   private float aF;
/*     */   private EnumStatus aG;
/*     */   private EnumStatus aH;
/*     */   private double aI;
/*  46 */   public double maxSpeed = 0.4D;
/*  47 */   public double occupiedDeceleration = 0.2D;
/*  48 */   public double unoccupiedDeceleration = -1.0D;
/*     */   public boolean landBoats = false;
/*     */   private Location lastLocation;
/*     */   
/*     */   public EntityBoat(World world) {
/*  53 */     super(world);
/*  54 */     this.f = new float[2];
/*  55 */     this.i = true;
/*  56 */     setSize(1.375F, 0.5625F);
/*     */   }
/*     */   
/*     */   public EntityBoat(World world, double d0, double d1, double d2) {
/*  60 */     this(world);
/*  61 */     setPosition(d0, d1, d2);
/*  62 */     this.motX = 0.0D;
/*  63 */     this.motY = 0.0D;
/*  64 */     this.motZ = 0.0D;
/*  65 */     this.lastX = d0;
/*  66 */     this.lastY = d1;
/*  67 */     this.lastZ = d2;
/*     */   }
/*     */   
/*     */   protected boolean playStepSound() {
/*  71 */     return false;
/*     */   }
/*     */   
/*     */   protected void i() {
/*  75 */     this.datawatcher.register(a, Integer.valueOf(0));
/*  76 */     this.datawatcher.register(b, Integer.valueOf(1));
/*  77 */     this.datawatcher.register(c, Float.valueOf(0.0F));
/*  78 */     this.datawatcher.register(d, Integer.valueOf(EnumBoatType.OAK.ordinal()));
/*  79 */     DataWatcherObject<Boolean>[] arrayOfDataWatcherObject = e;
/*  80 */     int i = arrayOfDataWatcherObject.length;
/*     */     
/*  82 */     for (int j = 0; j < i; j++) {
/*  83 */       DataWatcherObject<Boolean> datawatcherobject = arrayOfDataWatcherObject[j];
/*     */       
/*  85 */       this.datawatcher.register(datawatcherobject, Boolean.valueOf(false));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public AxisAlignedBB j(Entity entity) {
/*  92 */     return entity.isCollidable() ? entity.getBoundingBox() : null;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public AxisAlignedBB al() {
/*  97 */     return getBoundingBox();
/*     */   }
/*     */   
/*     */   public boolean isCollidable() {
/* 101 */     return true;
/*     */   }
/*     */   
/*     */   public double aG() {
/* 105 */     return -0.1D;
/*     */   }
/*     */   
/*     */   public boolean damageEntity(DamageSource damagesource, float f) {
/* 109 */     if (isInvulnerable(damagesource))
/* 110 */       return false; 
/* 111 */     if (!this.world.isClientSide && !this.dead) {
/* 112 */       if (damagesource instanceof EntityDamageSourceIndirect && damagesource.getEntity() != null && w(damagesource.getEntity())) {
/* 113 */         return false;
/*     */       }
/*     */       
/* 116 */       Vehicle vehicle = (Vehicle)getBukkitEntity();
/* 117 */       CraftEntity craftEntity = (damagesource.getEntity() == null) ? null : damagesource.getEntity().getBukkitEntity();
/*     */       
/* 119 */       VehicleDamageEvent event = new VehicleDamageEvent(vehicle, (Entity)craftEntity, f);
/* 120 */       this.world.getServer().getPluginManager().callEvent((Event)event);
/*     */       
/* 122 */       if (event.isCancelled()) {
/* 123 */         return true;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 128 */       d(-r());
/* 129 */       c(10);
/* 130 */       setDamage(p() + f * 10.0F);
/* 131 */       ax();
/* 132 */       boolean flag = (damagesource.getEntity() instanceof EntityHuman && ((EntityHuman)damagesource.getEntity()).abilities.canInstantlyBuild);
/*     */       
/* 134 */       if (flag || p() > 40.0F) {
/*     */         
/* 136 */         VehicleDestroyEvent destroyEvent = new VehicleDestroyEvent(vehicle, (Entity)craftEntity);
/* 137 */         this.world.getServer().getPluginManager().callEvent((Event)destroyEvent);
/*     */         
/* 139 */         if (destroyEvent.isCancelled()) {
/* 140 */           setDamage(40.0F);
/* 141 */           return true;
/*     */         } 
/*     */         
/* 144 */         if (!flag && this.world.getGameRules().getBoolean("doEntityDrops")) {
/* 145 */           a(j(), 1, 0.0F);
/*     */         }
/*     */         
/* 148 */         die();
/*     */       } 
/*     */       
/* 151 */       return true;
/*     */     } 
/*     */     
/* 154 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void collide(Entity entity) {
/* 159 */     if (entity instanceof EntityBoat) {
/* 160 */       if ((entity.getBoundingBox()).b < (getBoundingBox()).e) {
/*     */         
/* 162 */         VehicleEntityCollisionEvent event = new VehicleEntityCollisionEvent((Vehicle)getBukkitEntity(), (Entity)entity.getBukkitEntity());
/* 163 */         this.world.getServer().getPluginManager().callEvent((Event)event);
/*     */         
/* 165 */         if (event.isCancelled()) {
/*     */           return;
/*     */         }
/*     */         
/* 169 */         super.collide(entity);
/*     */       } 
/* 171 */     } else if ((entity.getBoundingBox()).b <= (getBoundingBox()).b) {
/*     */       
/* 173 */       VehicleEntityCollisionEvent event = new VehicleEntityCollisionEvent((Vehicle)getBukkitEntity(), (Entity)entity.getBukkitEntity());
/* 174 */       this.world.getServer().getPluginManager().callEvent((Event)event);
/*     */       
/* 176 */       if (event.isCancelled()) {
/*     */         return;
/*     */       }
/*     */       
/* 180 */       super.collide(entity);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Item j() {
/* 186 */     switch (getType()) {
/*     */       
/*     */       default:
/* 189 */         return Items.aH;
/*     */       
/*     */       case SPRUCE:
/* 192 */         return Items.aI;
/*     */       
/*     */       case BIRCH:
/* 195 */         return Items.aJ;
/*     */       
/*     */       case JUNGLE:
/* 198 */         return Items.aK;
/*     */       
/*     */       case null:
/* 201 */         return Items.aL;
/*     */       case DARK_OAK:
/*     */         break;
/* 204 */     }  return Items.aM;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isInteractable() {
/* 209 */     return !this.dead;
/*     */   }
/*     */   
/*     */   public EnumDirection bu() {
/* 213 */     return getDirection().e();
/*     */   }
/*     */ 
/*     */   
/*     */   public void B_() {
/* 218 */     this.aH = this.aG;
/* 219 */     this.aG = u();
/* 220 */     if (this.aG != EnumStatus.UNDER_WATER && this.aG != EnumStatus.UNDER_FLOWING_WATER) {
/* 221 */       this.h = 0.0F;
/*     */     } else {
/* 223 */       this.h++;
/*     */     } 
/*     */     
/* 226 */     if (!this.world.isClientSide && this.h >= 60.0F) {
/* 227 */       ejectPassengers();
/*     */     }
/*     */     
/* 230 */     if (q() > 0) {
/* 231 */       c(q() - 1);
/*     */     }
/*     */     
/* 234 */     if (p() > 0.0F) {
/* 235 */       setDamage(p() - 1.0F);
/*     */     }
/* 237 */     this.lastX = this.locX;
/* 238 */     this.lastY = this.locY;
/* 239 */     this.lastZ = this.locZ;
/* 240 */     super.B_();
/* 241 */     t();
/* 242 */     if (bI()) {
/* 243 */       if (bF().isEmpty() || !(bF().get(0) instanceof EntityHuman)) {
/* 244 */         a(false, false);
/*     */       }
/*     */       
/* 247 */       x();
/* 248 */       if (this.world.isClientSide) {
/* 249 */         y();
/* 250 */         this.world.a(new PacketPlayInBoatMove(a(0), a(1)));
/*     */       } 
/*     */       
/* 253 */       move(EnumMoveType.SELF, this.motX, this.motY, this.motZ);
/*     */     } else {
/* 255 */       this.motX = 0.0D;
/* 256 */       this.motY = 0.0D;
/* 257 */       this.motZ = 0.0D;
/*     */     } 
/*     */ 
/*     */     
/* 261 */     CraftServer craftServer = this.world.getServer();
/* 262 */     CraftWorld craftWorld = this.world.getWorld();
/*     */     
/* 264 */     Location to = new Location((World)craftWorld, this.locX, this.locY, this.locZ, this.yaw, this.pitch);
/* 265 */     Vehicle vehicle = (Vehicle)getBukkitEntity();
/*     */     
/* 267 */     craftServer.getPluginManager().callEvent((Event)new VehicleUpdateEvent(vehicle));
/*     */     
/* 269 */     if (this.lastLocation != null && !this.lastLocation.equals(to)) {
/* 270 */       VehicleMoveEvent event = new VehicleMoveEvent(vehicle, this.lastLocation, to);
/* 271 */       craftServer.getPluginManager().callEvent((Event)event);
/*     */     } 
/* 273 */     this.lastLocation = vehicle.getLocation();
/*     */ 
/*     */     
/* 276 */     for (int i = 0; i <= 1; i++) {
/* 277 */       if (a(i)) {
/* 278 */         if (!isSilent() && (this.f[i] % 6.2831855F) <= 0.7853981852531433D && (this.f[i] + 0.39269909262657166D) % 6.2831854820251465D >= 0.7853981852531433D) {
/* 279 */           SoundEffect soundeffect = k();
/*     */           
/* 281 */           if (soundeffect != null) {
/* 282 */             Vec3D vec3d = e(1.0F);
/* 283 */             double d0 = (i == 1) ? -vec3d.z : vec3d.z;
/* 284 */             double d1 = (i == 1) ? vec3d.x : -vec3d.x;
/*     */             
/* 286 */             this.world.a((EntityHuman)null, this.locX + d0, this.locY, this.locZ + d1, soundeffect, bK(), 1.0F, 0.8F + 0.4F * this.random.nextFloat());
/*     */           } 
/*     */         } 
/*     */         
/* 290 */         this.f[i] = (float)(this.f[i] + 0.39269909262657166D);
/*     */       } else {
/* 292 */         this.f[i] = 0.0F;
/*     */       } 
/*     */     } 
/*     */     
/* 296 */     checkBlockCollisions();
/* 297 */     List<Entity> list = this.world.getEntities(this, getBoundingBox().grow(0.20000000298023224D, -0.009999999776482582D, 0.20000000298023224D), IEntitySelector.a(this));
/*     */     
/* 299 */     if (!list.isEmpty()) {
/* 300 */       boolean flag = (!this.world.isClientSide && !(bE() instanceof EntityHuman));
/*     */       
/* 302 */       for (int j = 0; j < list.size(); j++) {
/* 303 */         Entity entity = list.get(j);
/*     */         
/* 305 */         if (!entity.w(this)) {
/* 306 */           if (flag && bF().size() < 2 && !entity.isPassenger() && entity.width < this.width && entity instanceof EntityLiving && !(entity instanceof EntityWaterAnimal) && !(entity instanceof EntityHuman)) {
/* 307 */             entity.startRiding(this);
/*     */           } else {
/* 309 */             collide(entity);
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   protected SoundEffect k() {
/* 319 */     switch (u()) {
/*     */       case IN_WATER:
/*     */       case UNDER_WATER:
/*     */       case UNDER_FLOWING_WATER:
/* 323 */         return SoundEffects.I;
/*     */       
/*     */       case ON_LAND:
/* 326 */         return SoundEffects.H;
/*     */     } 
/*     */ 
/*     */     
/* 330 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private void t() {
/* 335 */     if (this.au > 0 && !bI()) {
/* 336 */       double d0 = this.locX + (this.av - this.locX) / this.au;
/* 337 */       double d1 = this.locY + (this.aw - this.locY) / this.au;
/* 338 */       double d2 = this.locZ + (this.ax - this.locZ) / this.au;
/* 339 */       double d3 = MathHelper.g(this.ay - this.yaw);
/*     */       
/* 341 */       this.yaw = (float)(this.yaw + d3 / this.au);
/* 342 */       this.pitch = (float)(this.pitch + (this.az - this.pitch) / this.au);
/* 343 */       this.au--;
/* 344 */       setPosition(d0, d1, d2);
/* 345 */       setYawPitch(this.yaw, this.pitch);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void a(boolean flag, boolean flag1) {
/* 350 */     this.datawatcher.set(e[0], Boolean.valueOf(flag));
/* 351 */     this.datawatcher.set(e[1], Boolean.valueOf(flag1));
/*     */   }
/*     */   
/*     */   private EnumStatus u() {
/* 355 */     EnumStatus entityboat_enumstatus = w();
/*     */     
/* 357 */     if (entityboat_enumstatus != null) {
/* 358 */       this.aE = (getBoundingBox()).e;
/* 359 */       return entityboat_enumstatus;
/* 360 */     }  if (v()) {
/* 361 */       return EnumStatus.IN_WATER;
/*     */     }
/* 363 */     float f = n();
/*     */     
/* 365 */     if (f > 0.0F) {
/* 366 */       this.aF = f;
/* 367 */       return EnumStatus.ON_LAND;
/*     */     } 
/* 369 */     return EnumStatus.IN_AIR;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float l() {
/* 375 */     AxisAlignedBB axisalignedbb = getBoundingBox();
/* 376 */     int i = MathHelper.floor(axisalignedbb.a);
/* 377 */     int j = MathHelper.f(axisalignedbb.d);
/* 378 */     int k = MathHelper.floor(axisalignedbb.e);
/* 379 */     int l = MathHelper.f(axisalignedbb.e - this.aI);
/* 380 */     int i1 = MathHelper.floor(axisalignedbb.c);
/* 381 */     int j1 = MathHelper.f(axisalignedbb.f);
/* 382 */     BlockPosition.PooledBlockPosition blockposition_pooledblockposition = BlockPosition.PooledBlockPosition.s();
/*     */ 
/*     */     
/*     */     try {
/* 386 */       for (int k1 = k; k1 < l; k1++) {
/* 387 */         float f = 0.0F;
/*     */         
/* 389 */         for (int l1 = i; l1 < j; l1++) {
/* 390 */           for (int i2 = i1; i2 < j1; i2++) {
/* 391 */             blockposition_pooledblockposition.f(l1, k1, i2);
/* 392 */             IBlockData iblockdata = this.world.getType(blockposition_pooledblockposition);
/*     */             
/* 394 */             if (iblockdata.getMaterial() == Material.WATER) {
/* 395 */               f = Math.max(f, BlockFluids.g(iblockdata, this.world, blockposition_pooledblockposition));
/*     */             }
/*     */             
/* 398 */             if (f >= 1.0F) {
/*     */               // Byte code: goto -> 203
/*     */             }
/*     */           } 
/*     */         } 
/*     */         
/* 404 */         if (f < 1.0F) {
/* 405 */           float f1 = blockposition_pooledblockposition.getY() + f;
/*     */           
/* 407 */           return f1;
/*     */         } 
/*     */       } 
/*     */       
/* 411 */       float f2 = (l + 1);
/*     */       
/* 413 */       return f2;
/*     */     } finally {
/* 415 */       blockposition_pooledblockposition.t();
/*     */     } 
/*     */   }
/*     */   
/*     */   public float n() {
/* 420 */     AxisAlignedBB axisalignedbb = getBoundingBox();
/* 421 */     AxisAlignedBB axisalignedbb1 = new AxisAlignedBB(axisalignedbb.a, axisalignedbb.b - 0.001D, axisalignedbb.c, axisalignedbb.d, axisalignedbb.b, axisalignedbb.f);
/* 422 */     int i = MathHelper.floor(axisalignedbb1.a) - 1;
/* 423 */     int j = MathHelper.f(axisalignedbb1.d) + 1;
/* 424 */     int k = MathHelper.floor(axisalignedbb1.b) - 1;
/* 425 */     int l = MathHelper.f(axisalignedbb1.e) + 1;
/* 426 */     int i1 = MathHelper.floor(axisalignedbb1.c) - 1;
/* 427 */     int j1 = MathHelper.f(axisalignedbb1.f) + 1;
/* 428 */     ArrayList<AxisAlignedBB> arraylist = Lists.newArrayList();
/* 429 */     float f = 0.0F;
/* 430 */     int k1 = 0;
/* 431 */     BlockPosition.PooledBlockPosition blockposition_pooledblockposition = BlockPosition.PooledBlockPosition.s();
/*     */     
/*     */     try {
/* 434 */       for (int l1 = i; l1 < j; l1++) {
/* 435 */         for (int i2 = i1; i2 < j1; i2++) {
/* 436 */           int j2 = ((l1 != i && l1 != j - 1) ? 0 : 1) + ((i2 != i1 && i2 != j1 - 1) ? 0 : 1);
/*     */           
/* 438 */           if (j2 != 2) {
/* 439 */             for (int k2 = k; k2 < l; k2++) {
/* 440 */               if (j2 <= 0 || (k2 != k && k2 != l - 1)) {
/* 441 */                 blockposition_pooledblockposition.f(l1, k2, i2);
/* 442 */                 IBlockData iblockdata = this.world.getType(blockposition_pooledblockposition);
/*     */                 
/* 444 */                 iblockdata.a(this.world, blockposition_pooledblockposition, axisalignedbb1, arraylist, this, false);
/* 445 */                 if (!arraylist.isEmpty()) {
/* 446 */                   f += (iblockdata.getBlock()).frictionFactor;
/* 447 */                   k1++;
/*     */                 } 
/*     */                 
/* 450 */                 arraylist.clear();
/*     */               } 
/*     */             } 
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } finally {
/* 457 */       blockposition_pooledblockposition.t();
/*     */     } 
/*     */     
/* 460 */     return f / k1;
/*     */   }
/*     */   private boolean v() {
/*     */     int m;
/* 464 */     AxisAlignedBB axisalignedbb = getBoundingBox();
/* 465 */     int i = MathHelper.floor(axisalignedbb.a);
/* 466 */     int j = MathHelper.f(axisalignedbb.d);
/* 467 */     int k = MathHelper.floor(axisalignedbb.b);
/* 468 */     int l = MathHelper.f(axisalignedbb.b + 0.001D);
/* 469 */     int i1 = MathHelper.floor(axisalignedbb.c);
/* 470 */     int j1 = MathHelper.f(axisalignedbb.f);
/* 471 */     boolean flag = false;
/*     */     
/* 473 */     this.aE = Double.MIN_VALUE;
/* 474 */     BlockPosition.PooledBlockPosition blockposition_pooledblockposition = BlockPosition.PooledBlockPosition.s();
/*     */     
/*     */     try {
/* 477 */       for (int k1 = i; k1 < j; k1++) {
/* 478 */         for (int l1 = k; l1 < l; l1++) {
/* 479 */           for (int i2 = i1; i2 < j1; i2++) {
/* 480 */             blockposition_pooledblockposition.f(k1, l1, i2);
/* 481 */             IBlockData iblockdata = this.world.getType(blockposition_pooledblockposition);
/*     */             
/* 483 */             if (iblockdata.getMaterial() == Material.WATER) {
/* 484 */               float f = BlockFluids.h(iblockdata, this.world, blockposition_pooledblockposition);
/*     */               
/* 486 */               this.aE = Math.max(f, this.aE);
/* 487 */               m = flag | ((axisalignedbb.b < f) ? 1 : 0);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } finally {
/* 493 */       blockposition_pooledblockposition.t();
/*     */     } 
/*     */     
/* 496 */     return m;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private EnumStatus w() {
/* 501 */     AxisAlignedBB axisalignedbb = getBoundingBox();
/* 502 */     double d0 = axisalignedbb.e + 0.001D;
/* 503 */     int i = MathHelper.floor(axisalignedbb.a);
/* 504 */     int j = MathHelper.f(axisalignedbb.d);
/* 505 */     int k = MathHelper.floor(axisalignedbb.e);
/* 506 */     int l = MathHelper.f(d0);
/* 507 */     int i1 = MathHelper.floor(axisalignedbb.c);
/* 508 */     int j1 = MathHelper.f(axisalignedbb.f);
/* 509 */     boolean flag = false;
/* 510 */     BlockPosition.PooledBlockPosition blockposition_pooledblockposition = BlockPosition.PooledBlockPosition.s();
/*     */     
/*     */     try {
/* 513 */       for (int k1 = i; k1 < j; k1++) {
/* 514 */         for (int l1 = k; l1 < l; l1++) {
/* 515 */           for (int i2 = i1; i2 < j1; i2++) {
/* 516 */             blockposition_pooledblockposition.f(k1, l1, i2);
/* 517 */             IBlockData iblockdata = this.world.getType(blockposition_pooledblockposition);
/*     */             
/* 519 */             if (iblockdata.getMaterial() == Material.WATER && d0 < BlockFluids.h(iblockdata, this.world, blockposition_pooledblockposition)) {
/* 520 */               if (((Integer)iblockdata.<Integer>get(BlockFluids.LEVEL)).intValue() != 0) {
/* 521 */                 EnumStatus entityboat_enumstatus = EnumStatus.UNDER_FLOWING_WATER;
/*     */                 
/* 523 */                 return entityboat_enumstatus;
/*     */               } 
/*     */               
/* 526 */               flag = true;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 532 */       return flag ? EnumStatus.UNDER_WATER : null;
/*     */     } finally {
/* 534 */       blockposition_pooledblockposition.t();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void x() {
/* 540 */     double d1 = isNoGravity() ? 0.0D : -0.03999999910593033D;
/* 541 */     double d2 = 0.0D;
/*     */     
/* 543 */     this.g = 0.05F;
/* 544 */     if (this.aH == EnumStatus.IN_AIR && this.aG != EnumStatus.IN_AIR && this.aG != EnumStatus.ON_LAND) {
/* 545 */       this.aE = (getBoundingBox()).b + this.length;
/* 546 */       setPosition(this.locX, (l() - this.length) + 0.101D, this.locZ);
/* 547 */       this.motY = 0.0D;
/* 548 */       this.aI = 0.0D;
/* 549 */       this.aG = EnumStatus.IN_WATER;
/*     */     } else {
/* 551 */       if (this.aG == EnumStatus.IN_WATER) {
/* 552 */         d2 = (this.aE - (getBoundingBox()).b) / this.length;
/* 553 */         this.g = 0.9F;
/* 554 */       } else if (this.aG == EnumStatus.UNDER_FLOWING_WATER) {
/* 555 */         d1 = -7.0E-4D;
/* 556 */         this.g = 0.9F;
/* 557 */       } else if (this.aG == EnumStatus.UNDER_WATER) {
/* 558 */         d2 = 0.009999999776482582D;
/* 559 */         this.g = 0.45F;
/* 560 */       } else if (this.aG == EnumStatus.IN_AIR) {
/* 561 */         this.g = 0.9F;
/* 562 */       } else if (this.aG == EnumStatus.ON_LAND) {
/* 563 */         this.g = this.aF;
/* 564 */         if (bE() instanceof EntityHuman) {
/* 565 */           this.aF /= 2.0F;
/*     */         }
/*     */       } 
/*     */       
/* 569 */       this.motX *= this.g;
/* 570 */       this.motZ *= this.g;
/* 571 */       this.at *= this.g;
/* 572 */       this.motY += d1;
/* 573 */       if (d2 > 0.0D) {
/*     */ 
/*     */         
/* 576 */         this.motY += d2 * 0.06153846016296973D;
/*     */ 
/*     */         
/* 579 */         this.motY *= 0.75D;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void y() {
/* 586 */     if (isVehicle()) {
/* 587 */       float f = 0.0F;
/*     */       
/* 589 */       if (this.aA) {
/* 590 */         this.at--;
/*     */       }
/*     */       
/* 593 */       if (this.aB) {
/* 594 */         this.at++;
/*     */       }
/*     */       
/* 597 */       if (this.aB != this.aA && !this.aC && !this.aD) {
/* 598 */         f += 0.005F;
/*     */       }
/*     */       
/* 601 */       this.yaw += this.at;
/* 602 */       if (this.aC) {
/* 603 */         f += 0.04F;
/*     */       }
/*     */       
/* 606 */       if (this.aD) {
/* 607 */         f -= 0.005F;
/*     */       }
/*     */       
/* 610 */       this.motX += (MathHelper.sin(-this.yaw * 0.017453292F) * f);
/* 611 */       this.motZ += (MathHelper.cos(this.yaw * 0.017453292F) * f);
/* 612 */       a(!((!this.aB || this.aA) && !this.aC), !((!this.aA || this.aB) && !this.aC));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void k(Entity entity) {
/* 617 */     if (w(entity)) {
/* 618 */       float f = 0.0F;
/* 619 */       float f1 = (float)((this.dead ? 0.009999999776482582D : aG()) + entity.aF());
/*     */       
/* 621 */       if (bF().size() > 1) {
/* 622 */         int i = bF().indexOf(entity);
/*     */         
/* 624 */         if (i == 0) {
/* 625 */           f = 0.2F;
/*     */         } else {
/* 627 */           f = -0.6F;
/*     */         } 
/*     */         
/* 630 */         if (entity instanceof EntityAnimal) {
/* 631 */           f = (float)(f + 0.2D);
/*     */         }
/*     */       } 
/*     */       
/* 635 */       Vec3D vec3d = (new Vec3D(f, 0.0D, 0.0D)).b(-this.yaw * 0.017453292F - 1.5707964F);
/*     */       
/* 637 */       entity.setPosition(this.locX + vec3d.x, this.locY + f1, this.locZ + vec3d.z);
/* 638 */       entity.yaw += this.at;
/* 639 */       entity.setHeadRotation(entity.getHeadRotation() + this.at);
/* 640 */       a(entity);
/* 641 */       if (entity instanceof EntityAnimal && bF().size() > 1) {
/* 642 */         int j = (entity.getId() % 2 == 0) ? 90 : 270;
/*     */         
/* 644 */         entity.h(((EntityAnimal)entity).aN + j);
/* 645 */         entity.setHeadRotation(entity.getHeadRotation() + j);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(Entity entity) {
/* 652 */     entity.h(this.yaw);
/* 653 */     float f = MathHelper.g(entity.yaw - this.yaw);
/* 654 */     float f1 = MathHelper.a(f, -105.0F, 105.0F);
/*     */     
/* 656 */     entity.lastYaw += f1 - f;
/* 657 */     entity.yaw += f1 - f;
/* 658 */     entity.setHeadRotation(entity.yaw);
/*     */   }
/*     */   
/*     */   protected void b(NBTTagCompound nbttagcompound) {
/* 662 */     nbttagcompound.setString("Type", getType().a());
/*     */   }
/*     */   
/*     */   protected void a(NBTTagCompound nbttagcompound) {
/* 666 */     if (nbttagcompound.hasKeyOfType("Type", 8)) {
/* 667 */       setType(EnumBoatType.a(nbttagcompound.getString("Type")));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b(EntityHuman entityhuman, EnumHand enumhand) {
/* 673 */     if (entityhuman.isSneaking()) {
/* 674 */       return false;
/*     */     }
/* 676 */     if (!this.world.isClientSide && this.h < 60.0F) {
/* 677 */       entityhuman.startRiding(this);
/*     */     }
/*     */     
/* 680 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(double d0, boolean flag, IBlockData iblockdata, BlockPosition blockposition) {
/* 685 */     this.aI = this.motY;
/* 686 */     if (!isPassenger()) {
/* 687 */       if (flag) {
/* 688 */         if (this.fallDistance > 3.0F) {
/* 689 */           if (this.aG != EnumStatus.ON_LAND) {
/* 690 */             this.fallDistance = 0.0F;
/*     */             
/*     */             return;
/*     */           } 
/* 694 */           e(this.fallDistance, 1.0F);
/* 695 */           if (!this.world.isClientSide && !this.dead) {
/*     */             
/* 697 */             Vehicle vehicle = (Vehicle)getBukkitEntity();
/* 698 */             VehicleDestroyEvent destroyEvent = new VehicleDestroyEvent(vehicle, null);
/* 699 */             this.world.getServer().getPluginManager().callEvent((Event)destroyEvent);
/* 700 */             if (!destroyEvent.isCancelled()) {
/* 701 */               die();
/* 702 */               if (this.world.getGameRules().getBoolean("doEntityDrops")) {
/*     */                 int i;
/*     */                 
/* 705 */                 for (i = 0; i < 3; i++) {
/* 706 */                   a(new ItemStack(Item.getItemOf(Blocks.PLANKS), 1, getType().b()), 0.0F);
/*     */                 }
/*     */                 
/* 709 */                 for (i = 0; i < 2; i++) {
/* 710 */                   a(Items.STICK, 1, 0.0F);
/*     */                 }
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 717 */         this.fallDistance = 0.0F;
/* 718 */       } else if (this.world.getType((new BlockPosition(this)).down()).getMaterial() != Material.WATER && d0 < 0.0D) {
/* 719 */         this.fallDistance = (float)(this.fallDistance - d0);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(int i) {
/* 726 */     return (((Boolean)this.datawatcher.<Boolean>get(e[i])).booleanValue() && bE() != null);
/*     */   }
/*     */   
/*     */   public void setDamage(float f) {
/* 730 */     this.datawatcher.set(c, Float.valueOf(f));
/*     */   }
/*     */   
/*     */   public float p() {
/* 734 */     return ((Float)this.datawatcher.<Float>get(c)).floatValue();
/*     */   }
/*     */   
/*     */   public void c(int i) {
/* 738 */     this.datawatcher.set(a, Integer.valueOf(i));
/*     */   }
/*     */   
/*     */   public int q() {
/* 742 */     return ((Integer)this.datawatcher.<Integer>get(a)).intValue();
/*     */   }
/*     */   
/*     */   public void d(int i) {
/* 746 */     this.datawatcher.set(b, Integer.valueOf(i));
/*     */   }
/*     */   
/*     */   public int r() {
/* 750 */     return ((Integer)this.datawatcher.<Integer>get(b)).intValue();
/*     */   }
/*     */   
/*     */   public void setType(EnumBoatType entityboat_enumboattype) {
/* 754 */     this.datawatcher.set(d, Integer.valueOf(entityboat_enumboattype.ordinal()));
/*     */   }
/*     */   
/*     */   public EnumBoatType getType() {
/* 758 */     return EnumBoatType.a(((Integer)this.datawatcher.<Integer>get(d)).intValue());
/*     */   }
/*     */   
/*     */   protected boolean q(Entity entity) {
/* 762 */     return (bF().size() < 2);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public Entity bE() {
/* 767 */     List<Entity> list = bF();
/*     */     
/* 769 */     return list.isEmpty() ? null : list.get(0);
/*     */   }
/*     */   
/*     */   public enum EnumBoatType
/*     */   {
/* 774 */     OAK(BlockWood.EnumLogVariant.OAK.a(), "oak"), SPRUCE(BlockWood.EnumLogVariant.SPRUCE.a(), "spruce"), BIRCH(BlockWood.EnumLogVariant.BIRCH.a(), "birch"), JUNGLE(BlockWood.EnumLogVariant.JUNGLE.a(), "jungle"), ACACIA(BlockWood.EnumLogVariant.ACACIA.a(), "acacia"), DARK_OAK(BlockWood.EnumLogVariant.DARK_OAK.a(), "dark_oak");
/*     */     
/*     */     private final int h;
/*     */     private final String g;
/*     */     
/*     */     EnumBoatType(int i, String s) {
/* 780 */       this.g = s;
/* 781 */       this.h = i;
/*     */     }
/*     */     
/*     */     public String a() {
/* 785 */       return this.g;
/*     */     }
/*     */     
/*     */     public int b() {
/* 789 */       return this.h;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 793 */       return this.g;
/*     */     }
/*     */     
/*     */     public static EnumBoatType a(int i) {
/* 797 */       if (i < 0 || i >= (values()).length) {
/* 798 */         i = 0;
/*     */       }
/*     */       
/* 801 */       return values()[i];
/*     */     }
/*     */     
/*     */     public static EnumBoatType a(String s) {
/* 805 */       for (int i = 0; i < (values()).length; i++) {
/* 806 */         if (values()[i].a().equals(s)) {
/* 807 */           return values()[i];
/*     */         }
/*     */       } 
/*     */       
/* 811 */       return values()[0];
/*     */     }
/*     */   }
/*     */   
/*     */   public enum EnumStatus
/*     */   {
/* 817 */     IN_WATER, UNDER_WATER, UNDER_FLOWING_WATER, ON_LAND, IN_AIR;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityBoat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */