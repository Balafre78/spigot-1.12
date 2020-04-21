/*     */ package net.minecraft.server.v1_12_R1;
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.entity.CraftEntity;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.Vehicle;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.vehicle.VehicleDamageEvent;
/*     */ import org.bukkit.event.vehicle.VehicleDestroyEvent;
/*     */ import org.bukkit.event.vehicle.VehicleEntityCollisionEvent;
/*     */ import org.bukkit.event.vehicle.VehicleMoveEvent;
/*     */ import org.bukkit.util.Vector;
/*     */ 
/*     */ public abstract class EntityMinecartAbstract extends Entity implements INamableTileEntity {
/*  20 */   private static final DataWatcherObject<Integer> a = DataWatcher.a((Class)EntityMinecartAbstract.class, DataWatcherRegistry.b);
/*  21 */   private static final DataWatcherObject<Integer> b = DataWatcher.a((Class)EntityMinecartAbstract.class, DataWatcherRegistry.b);
/*  22 */   private static final DataWatcherObject<Float> c = DataWatcher.a((Class)EntityMinecartAbstract.class, DataWatcherRegistry.c);
/*  23 */   private static final DataWatcherObject<Integer> d = DataWatcher.a((Class)EntityMinecartAbstract.class, DataWatcherRegistry.b);
/*  24 */   private static final DataWatcherObject<Integer> e = DataWatcher.a((Class)EntityMinecartAbstract.class, DataWatcherRegistry.b);
/*  25 */   private static final DataWatcherObject<Boolean> f = DataWatcher.a((Class)EntityMinecartAbstract.class, DataWatcherRegistry.h);
/*     */   private boolean g;
/*  27 */   private static final int[][][] h = new int[][][] { { { 0, 0, -1 }, { 0, 0, 1 } }, { { -1 }, { 1 } }, { { -1, -1 }, { 1 } }, { { -1 }, { 1, -1 } }, { { 0, 0, -1 }, { 0, -1, 1 } }, { { 0, -1, -1 }, { 0, 0, 1 } }, { { 0, 0, 1 }, { 1 } }, { { 0, 0, 1 }, { -1 } }, { { 0, 0, -1 }, { -1 } }, { { 0, 0, -1 }, { 1 } } };
/*     */   
/*     */   private int at;
/*     */   
/*     */   private double au;
/*     */   private double av;
/*     */   private double aw;
/*     */   private double ax;
/*     */   private double ay;
/*     */   public boolean slowWhenEmpty = true;
/*  37 */   private double derailedX = 0.5D;
/*  38 */   private double derailedY = 0.5D;
/*  39 */   private double derailedZ = 0.5D;
/*  40 */   private double flyingX = 0.95D;
/*  41 */   private double flyingY = 0.95D;
/*  42 */   private double flyingZ = 0.95D;
/*  43 */   public double maxSpeed = 0.4D;
/*     */ 
/*     */   
/*     */   public EntityMinecartAbstract(World world) {
/*  47 */     super(world);
/*  48 */     this.i = true;
/*  49 */     setSize(0.98F, 0.7F);
/*     */   }
/*     */   
/*     */   public static EntityMinecartAbstract a(World world, double d0, double d1, double d2, EnumMinecartType entityminecartabstract_enumminecarttype) {
/*  53 */     switch (entityminecartabstract_enumminecarttype) {
/*     */       case null:
/*  55 */         return new EntityMinecartChest(world, d0, d1, d2);
/*     */       
/*     */       case FURNACE:
/*  58 */         return new EntityMinecartFurnace(world, d0, d1, d2);
/*     */       
/*     */       case TNT:
/*  61 */         return new EntityMinecartTNT(world, d0, d1, d2);
/*     */       
/*     */       case SPAWNER:
/*  64 */         return new EntityMinecartMobSpawner(world, d0, d1, d2);
/*     */       
/*     */       case HOPPER:
/*  67 */         return new EntityMinecartHopper(world, d0, d1, d2);
/*     */       
/*     */       case COMMAND_BLOCK:
/*  70 */         return new EntityMinecartCommandBlock(world, d0, d1, d2);
/*     */     } 
/*     */     
/*  73 */     return new EntityMinecartRideable(world, d0, d1, d2);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean playStepSound() {
/*  78 */     return false;
/*     */   }
/*     */   
/*     */   protected void i() {
/*  82 */     this.datawatcher.register(a, Integer.valueOf(0));
/*  83 */     this.datawatcher.register(b, Integer.valueOf(1));
/*  84 */     this.datawatcher.register(c, Float.valueOf(0.0F));
/*  85 */     this.datawatcher.register(d, Integer.valueOf(0));
/*  86 */     this.datawatcher.register(e, Integer.valueOf(6));
/*  87 */     this.datawatcher.register(f, Boolean.valueOf(false));
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public AxisAlignedBB j(Entity entity) {
/*  92 */     return entity.isCollidable() ? entity.getBoundingBox() : null;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public AxisAlignedBB al() {
/*  97 */     return null;
/*     */   }
/*     */   
/*     */   public boolean isCollidable() {
/* 101 */     return true;
/*     */   }
/*     */   
/*     */   public EntityMinecartAbstract(World world, double d0, double d1, double d2) {
/* 105 */     this(world);
/* 106 */     setPosition(d0, d1, d2);
/* 107 */     this.motX = 0.0D;
/* 108 */     this.motY = 0.0D;
/* 109 */     this.motZ = 0.0D;
/* 110 */     this.lastX = d0;
/* 111 */     this.lastY = d1;
/* 112 */     this.lastZ = d2;
/*     */   }
/*     */   
/*     */   public double aG() {
/* 116 */     return 0.0D;
/*     */   }
/*     */   
/*     */   public boolean damageEntity(DamageSource damagesource, float f) {
/* 120 */     if (!this.world.isClientSide && !this.dead) {
/* 121 */       if (isInvulnerable(damagesource)) {
/* 122 */         return false;
/*     */       }
/*     */       
/* 125 */       Vehicle vehicle = (Vehicle)getBukkitEntity();
/* 126 */       CraftEntity craftEntity = (damagesource.getEntity() == null) ? null : damagesource.getEntity().getBukkitEntity();
/*     */       
/* 128 */       VehicleDamageEvent event = new VehicleDamageEvent(vehicle, (Entity)craftEntity, f);
/* 129 */       this.world.getServer().getPluginManager().callEvent((Event)event);
/*     */       
/* 131 */       if (event.isCancelled()) {
/* 132 */         return true;
/*     */       }
/*     */       
/* 135 */       f = (float)event.getDamage();
/*     */       
/* 137 */       e(-u());
/* 138 */       d(10);
/* 139 */       ax();
/* 140 */       setDamage(getDamage() + f * 10.0F);
/* 141 */       boolean flag = (damagesource.getEntity() instanceof EntityHuman && ((EntityHuman)damagesource.getEntity()).abilities.canInstantlyBuild);
/*     */       
/* 143 */       if (flag || getDamage() > 40.0F) {
/*     */         
/* 145 */         VehicleDestroyEvent destroyEvent = new VehicleDestroyEvent(vehicle, (Entity)craftEntity);
/* 146 */         this.world.getServer().getPluginManager().callEvent((Event)destroyEvent);
/*     */         
/* 148 */         if (destroyEvent.isCancelled()) {
/* 149 */           setDamage(40.0F);
/* 150 */           return true;
/*     */         } 
/*     */         
/* 153 */         ejectPassengers();
/* 154 */         if (flag && !hasCustomName()) {
/* 155 */           die();
/*     */         } else {
/* 157 */           a(damagesource);
/*     */         } 
/*     */       } 
/*     */       
/* 161 */       return true;
/*     */     } 
/*     */     
/* 164 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(DamageSource damagesource) {
/* 169 */     die();
/* 170 */     if (this.world.getGameRules().getBoolean("doEntityDrops")) {
/* 171 */       ItemStack itemstack = new ItemStack(Items.MINECART, 1);
/*     */       
/* 173 */       if (hasCustomName()) {
/* 174 */         itemstack.g(getCustomName());
/*     */       }
/*     */       
/* 177 */       a(itemstack, 0.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isInteractable() {
/* 183 */     return !this.dead;
/*     */   }
/*     */   
/*     */   public EnumDirection bu() {
/* 187 */     return this.g ? getDirection().opposite().e() : getDirection().e();
/*     */   }
/*     */ 
/*     */   
/*     */   public void B_() {
/* 192 */     double prevX = this.locX;
/* 193 */     double prevY = this.locY;
/* 194 */     double prevZ = this.locZ;
/* 195 */     float prevYaw = this.yaw;
/* 196 */     float prevPitch = this.pitch;
/*     */ 
/*     */     
/* 199 */     if (getType() > 0) {
/* 200 */       d(getType() - 1);
/*     */     }
/*     */     
/* 203 */     if (getDamage() > 0.0F) {
/* 204 */       setDamage(getDamage() - 1.0F);
/*     */     }
/*     */     
/* 207 */     if (this.locY < -64.0D) {
/* 208 */       ac();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 213 */     if (!this.world.isClientSide && this.world instanceof WorldServer) {
/* 214 */       this.world.methodProfiler.a("portal");
/* 215 */       MinecraftServer minecraftserver = this.world.getMinecraftServer();
/*     */       
/* 217 */       int i = Z();
/* 218 */       if (this.ak) {
/*     */         
/* 220 */         if (!isPassenger() && this.al++ >= i) {
/* 221 */           byte b0; this.al = i;
/* 222 */           this.portalCooldown = aM();
/*     */ 
/*     */           
/* 225 */           if (this.world.worldProvider.getDimensionManager().getDimensionID() == -1) {
/* 226 */             b0 = 0;
/*     */           } else {
/* 228 */             b0 = -1;
/*     */           } 
/*     */           
/* 231 */           b(b0);
/*     */         } 
/*     */         
/* 234 */         this.ak = false;
/*     */       } else {
/*     */         
/* 237 */         if (this.al > 0) {
/* 238 */           this.al -= 4;
/*     */         }
/*     */         
/* 241 */         if (this.al < 0) {
/* 242 */           this.al = 0;
/*     */         }
/*     */       } 
/*     */       
/* 246 */       if (this.portalCooldown > 0) {
/* 247 */         this.portalCooldown--;
/*     */       }
/*     */       
/* 250 */       this.world.methodProfiler.b();
/*     */     } 
/*     */     
/* 253 */     if (this.world.isClientSide) {
/* 254 */       if (this.at > 0) {
/* 255 */         double d0 = this.locX + (this.au - this.locX) / this.at;
/* 256 */         double d1 = this.locY + (this.av - this.locY) / this.at;
/* 257 */         double d2 = this.locZ + (this.aw - this.locZ) / this.at;
/* 258 */         double d3 = MathHelper.g(this.ax - this.yaw);
/*     */         
/* 260 */         this.yaw = (float)(this.yaw + d3 / this.at);
/* 261 */         this.pitch = (float)(this.pitch + (this.ay - this.pitch) / this.at);
/* 262 */         this.at--;
/* 263 */         setPosition(d0, d1, d2);
/* 264 */         setYawPitch(this.yaw, this.pitch);
/*     */       } else {
/* 266 */         setPosition(this.locX, this.locY, this.locZ);
/* 267 */         setYawPitch(this.yaw, this.pitch);
/*     */       } 
/*     */     } else {
/*     */       
/* 271 */       this.lastX = this.locX;
/* 272 */       this.lastY = this.locY;
/* 273 */       this.lastZ = this.locZ;
/* 274 */       if (!isNoGravity()) {
/* 275 */         this.motY -= 0.03999999910593033D;
/*     */       }
/*     */       
/* 278 */       int j = MathHelper.floor(this.locX);
/*     */       
/* 280 */       int i = MathHelper.floor(this.locY);
/* 281 */       int k = MathHelper.floor(this.locZ);
/*     */       
/* 283 */       if (BlockMinecartTrackAbstract.b(this.world, new BlockPosition(j, i - 1, k))) {
/* 284 */         i--;
/*     */       }
/*     */       
/* 287 */       BlockPosition blockposition = new BlockPosition(j, i, k);
/* 288 */       IBlockData iblockdata = this.world.getType(blockposition);
/*     */       
/* 290 */       if (BlockMinecartTrackAbstract.i(iblockdata)) {
/* 291 */         a(blockposition, iblockdata);
/* 292 */         if (iblockdata.getBlock() == Blocks.ACTIVATOR_RAIL) {
/* 293 */           a(j, i, k, ((Boolean)iblockdata.<Boolean>get(BlockPoweredRail.POWERED)).booleanValue());
/*     */         }
/*     */       } else {
/* 296 */         q();
/*     */       } 
/*     */       
/* 299 */       checkBlockCollisions();
/* 300 */       this.pitch = 0.0F;
/* 301 */       double d4 = this.lastX - this.locX;
/* 302 */       double d5 = this.lastZ - this.locZ;
/*     */       
/* 304 */       if (d4 * d4 + d5 * d5 > 0.001D) {
/* 305 */         this.yaw = (float)(MathHelper.c(d5, d4) * 180.0D / Math.PI);
/* 306 */         if (this.g) {
/* 307 */           this.yaw += 180.0F;
/*     */         }
/*     */       } 
/*     */       
/* 311 */       double d6 = MathHelper.g(this.yaw - this.lastYaw);
/*     */       
/* 313 */       if (d6 < -170.0D || d6 >= 170.0D) {
/* 314 */         this.yaw += 180.0F;
/* 315 */         this.g = !this.g;
/*     */       } 
/*     */       
/* 318 */       setYawPitch(this.yaw, this.pitch);
/*     */       
/* 320 */       CraftWorld craftWorld = this.world.getWorld();
/* 321 */       Location from = new Location((World)craftWorld, prevX, prevY, prevZ, prevYaw, prevPitch);
/* 322 */       Location to = new Location((World)craftWorld, this.locX, this.locY, this.locZ, this.yaw, this.pitch);
/* 323 */       Vehicle vehicle = (Vehicle)getBukkitEntity();
/*     */       
/* 325 */       this.world.getServer().getPluginManager().callEvent((Event)new VehicleUpdateEvent(vehicle));
/*     */       
/* 327 */       if (!from.equals(to)) {
/* 328 */         this.world.getServer().getPluginManager().callEvent((Event)new VehicleMoveEvent(vehicle, from, to));
/*     */       }
/*     */       
/* 331 */       if (v() == EnumMinecartType.RIDEABLE && this.motX * this.motX + this.motZ * this.motZ > 0.01D) {
/* 332 */         List<Entity> list = this.world.getEntities(this, getBoundingBox().grow(0.20000000298023224D, 0.0D, 0.20000000298023224D), IEntitySelector.a(this));
/*     */         
/* 334 */         if (!list.isEmpty())
/* 335 */           for (int l = 0; l < list.size(); l++) {
/* 336 */             Entity entity = list.get(l);
/*     */             
/* 338 */             if (!(entity instanceof EntityHuman) && !(entity instanceof EntityIronGolem) && !(entity instanceof EntityMinecartAbstract) && !isVehicle() && !entity.isPassenger()) {
/*     */               
/* 340 */               VehicleEntityCollisionEvent collisionEvent = new VehicleEntityCollisionEvent(vehicle, (Entity)entity.getBukkitEntity());
/* 341 */               this.world.getServer().getPluginManager().callEvent((Event)collisionEvent);
/*     */               
/* 343 */               if (!collisionEvent.isCancelled())
/*     */               {
/*     */ 
/*     */                 
/* 347 */                 entity.startRiding(this);
/*     */               }
/*     */             } else {
/* 350 */               VehicleEntityCollisionEvent collisionEvent = new VehicleEntityCollisionEvent(vehicle, (Entity)entity.getBukkitEntity());
/* 351 */               this.world.getServer().getPluginManager().callEvent((Event)collisionEvent);
/*     */               
/* 353 */               if (!collisionEvent.isCancelled())
/*     */               {
/*     */ 
/*     */                 
/* 357 */                 entity.collide(this);
/*     */               }
/*     */             } 
/*     */           }  
/*     */       } else {
/* 362 */         Iterator<Entity> iterator = this.world.getEntities(this, getBoundingBox().grow(0.20000000298023224D, 0.0D, 0.20000000298023224D)).iterator();
/*     */         
/* 364 */         while (iterator.hasNext()) {
/* 365 */           Entity entity1 = iterator.next();
/*     */           
/* 367 */           if (!w(entity1) && entity1.isCollidable() && entity1 instanceof EntityMinecartAbstract) {
/*     */             
/* 369 */             VehicleEntityCollisionEvent collisionEvent = new VehicleEntityCollisionEvent(vehicle, (Entity)entity1.getBukkitEntity());
/* 370 */             this.world.getServer().getPluginManager().callEvent((Event)collisionEvent);
/*     */             
/* 372 */             if (collisionEvent.isCancelled()) {
/*     */               continue;
/*     */             }
/*     */             
/* 376 */             entity1.collide(this);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 381 */       aq();
/*     */     } 
/*     */   }
/*     */   
/*     */   protected double p() {
/* 386 */     return this.maxSpeed;
/*     */   }
/*     */   
/*     */   public void a(int i, int j, int k, boolean flag) {}
/*     */   
/*     */   protected void q() {
/* 392 */     double d0 = p();
/*     */     
/* 394 */     this.motX = MathHelper.a(this.motX, -d0, d0);
/* 395 */     this.motZ = MathHelper.a(this.motZ, -d0, d0);
/* 396 */     if (this.onGround) {
/*     */       
/* 398 */       this.motX *= this.derailedX;
/* 399 */       this.motY *= this.derailedY;
/* 400 */       this.motZ *= this.derailedZ;
/*     */     } 
/*     */ 
/*     */     
/* 404 */     move(EnumMoveType.SELF, this.motX, this.motY, this.motZ);
/* 405 */     if (!this.onGround) {
/*     */       
/* 407 */       this.motX *= this.flyingX;
/* 408 */       this.motY *= this.flyingY;
/* 409 */       this.motZ *= this.flyingZ;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(BlockPosition blockposition, IBlockData iblockdata) {
/*     */     double d10;
/* 416 */     this.fallDistance = 0.0F;
/* 417 */     Vec3D vec3d = j(this.locX, this.locY, this.locZ);
/*     */     
/* 419 */     this.locY = blockposition.getY();
/* 420 */     boolean flag = false;
/* 421 */     boolean flag1 = false;
/* 422 */     BlockMinecartTrackAbstract blockminecarttrackabstract = (BlockMinecartTrackAbstract)iblockdata.getBlock();
/*     */     
/* 424 */     if (blockminecarttrackabstract == Blocks.GOLDEN_RAIL) {
/* 425 */       flag = ((Boolean)iblockdata.<Boolean>get(BlockPoweredRail.POWERED)).booleanValue();
/* 426 */       flag1 = !flag;
/*     */     } 
/*     */ 
/*     */     
/* 430 */     BlockMinecartTrackAbstract.EnumTrackPosition blockminecarttrackabstract_enumtrackposition = iblockdata.<BlockMinecartTrackAbstract.EnumTrackPosition>get(blockminecarttrackabstract.g());
/*     */     
/* 432 */     switch (blockminecarttrackabstract_enumtrackposition) {
/*     */       case null:
/* 434 */         this.motX -= 0.0078125D;
/* 435 */         this.locY++;
/*     */         break;
/*     */       
/*     */       case ASCENDING_WEST:
/* 439 */         this.motX += 0.0078125D;
/* 440 */         this.locY++;
/*     */         break;
/*     */       
/*     */       case ASCENDING_NORTH:
/* 444 */         this.motZ += 0.0078125D;
/* 445 */         this.locY++;
/*     */         break;
/*     */       
/*     */       case ASCENDING_SOUTH:
/* 449 */         this.motZ -= 0.0078125D;
/* 450 */         this.locY++;
/*     */         break;
/*     */     } 
/* 453 */     int[][] aint = h[blockminecarttrackabstract_enumtrackposition.a()];
/* 454 */     double d1 = (aint[1][0] - aint[0][0]);
/* 455 */     double d2 = (aint[1][2] - aint[0][2]);
/* 456 */     double d3 = Math.sqrt(d1 * d1 + d2 * d2);
/* 457 */     double d4 = this.motX * d1 + this.motZ * d2;
/*     */     
/* 459 */     if (d4 < 0.0D) {
/* 460 */       d1 = -d1;
/* 461 */       d2 = -d2;
/*     */     } 
/*     */     
/* 464 */     double d5 = Math.sqrt(this.motX * this.motX + this.motZ * this.motZ);
/*     */     
/* 466 */     if (d5 > 2.0D) {
/* 467 */       d5 = 2.0D;
/*     */     }
/*     */     
/* 470 */     this.motX = d5 * d1 / d3;
/* 471 */     this.motZ = d5 * d2 / d3;
/* 472 */     Entity entity = bF().isEmpty() ? null : bF().get(0);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 478 */     if (entity instanceof EntityLiving) {
/* 479 */       double d = ((EntityLiving)entity).bg;
/* 480 */       if (d > 0.0D) {
/* 481 */         double d14 = -Math.sin((entity.yaw * 0.017453292F));
/* 482 */         double d15 = Math.cos((entity.yaw * 0.017453292F));
/* 483 */         double d16 = this.motX * this.motX + this.motZ * this.motZ;
/* 484 */         if (d16 < 0.01D) {
/* 485 */           this.motX += d14 * 0.1D;
/* 486 */           this.motZ += d15 * 0.1D;
/* 487 */           flag1 = false;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 492 */     if (flag1) {
/* 493 */       double d = Math.sqrt(this.motX * this.motX + this.motZ * this.motZ);
/* 494 */       if (d < 0.03D) {
/* 495 */         this.motX *= 0.0D;
/* 496 */         this.motY *= 0.0D;
/* 497 */         this.motZ *= 0.0D;
/*     */       } else {
/* 499 */         this.motX *= 0.5D;
/* 500 */         this.motY *= 0.0D;
/* 501 */         this.motZ *= 0.5D;
/*     */       } 
/*     */     } 
/*     */     
/* 505 */     double d6 = blockposition.getX() + 0.5D + aint[0][0] * 0.5D;
/* 506 */     double d7 = blockposition.getZ() + 0.5D + aint[0][2] * 0.5D;
/* 507 */     double d8 = blockposition.getX() + 0.5D + aint[1][0] * 0.5D;
/* 508 */     double d9 = blockposition.getZ() + 0.5D + aint[1][2] * 0.5D;
/* 509 */     d1 = d8 - d6;
/* 510 */     d2 = d9 - d7;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 515 */     if (d1 == 0.0D) {
/* 516 */       this.locX = blockposition.getX() + 0.5D;
/* 517 */       d10 = this.locZ - blockposition.getZ();
/* 518 */     } else if (d2 == 0.0D) {
/* 519 */       this.locZ = blockposition.getZ() + 0.5D;
/* 520 */       d10 = this.locX - blockposition.getX();
/*     */     } else {
/* 522 */       double d14 = this.locX - d6;
/* 523 */       double d15 = this.locZ - d7;
/* 524 */       d10 = (d14 * d1 + d15 * d2) * 2.0D;
/*     */     } 
/*     */     
/* 527 */     this.locX = d6 + d1 * d10;
/* 528 */     this.locZ = d7 + d2 * d10;
/* 529 */     setPosition(this.locX, this.locY, this.locZ);
/* 530 */     double d11 = this.motX;
/* 531 */     double d12 = this.motZ;
/* 532 */     if (isVehicle()) {
/* 533 */       d11 *= 0.75D;
/* 534 */       d12 *= 0.75D;
/*     */     } 
/*     */     
/* 537 */     double d13 = p();
/*     */     
/* 539 */     d11 = MathHelper.a(d11, -d13, d13);
/* 540 */     d12 = MathHelper.a(d12, -d13, d13);
/* 541 */     move(EnumMoveType.SELF, d11, 0.0D, d12);
/* 542 */     if (aint[0][1] != 0 && MathHelper.floor(this.locX) - blockposition.getX() == aint[0][0] && MathHelper.floor(this.locZ) - blockposition.getZ() == aint[0][2]) {
/* 543 */       setPosition(this.locX, this.locY + aint[0][1], this.locZ);
/* 544 */     } else if (aint[1][1] != 0 && MathHelper.floor(this.locX) - blockposition.getX() == aint[1][0] && MathHelper.floor(this.locZ) - blockposition.getZ() == aint[1][2]) {
/* 545 */       setPosition(this.locX, this.locY + aint[1][1], this.locZ);
/*     */     } 
/*     */     
/* 548 */     r();
/* 549 */     Vec3D vec3d1 = j(this.locX, this.locY, this.locZ);
/*     */     
/* 551 */     if (vec3d1 != null && vec3d != null) {
/* 552 */       double d14 = (vec3d.y - vec3d1.y) * 0.05D;
/*     */       
/* 554 */       d5 = Math.sqrt(this.motX * this.motX + this.motZ * this.motZ);
/* 555 */       if (d5 > 0.0D) {
/* 556 */         this.motX = this.motX / d5 * (d5 + d14);
/* 557 */         this.motZ = this.motZ / d5 * (d5 + d14);
/*     */       } 
/*     */       
/* 560 */       setPosition(this.locX, vec3d1.y, this.locZ);
/*     */     } 
/*     */     
/* 563 */     int i = MathHelper.floor(this.locX);
/* 564 */     int j = MathHelper.floor(this.locZ);
/*     */     
/* 566 */     if (i != blockposition.getX() || j != blockposition.getZ()) {
/* 567 */       d5 = Math.sqrt(this.motX * this.motX + this.motZ * this.motZ);
/* 568 */       this.motX = d5 * (i - blockposition.getX());
/* 569 */       this.motZ = d5 * (j - blockposition.getZ());
/*     */     } 
/*     */     
/* 572 */     if (flag) {
/* 573 */       double d15 = Math.sqrt(this.motX * this.motX + this.motZ * this.motZ);
/*     */       
/* 575 */       if (d15 > 0.01D) {
/*     */ 
/*     */         
/* 578 */         this.motX += this.motX / d15 * 0.06D;
/* 579 */         this.motZ += this.motZ / d15 * 0.06D;
/* 580 */       } else if (blockminecarttrackabstract_enumtrackposition == BlockMinecartTrackAbstract.EnumTrackPosition.EAST_WEST) {
/* 581 */         if (this.world.getType(blockposition.west()).l()) {
/* 582 */           this.motX = 0.02D;
/* 583 */         } else if (this.world.getType(blockposition.east()).l()) {
/* 584 */           this.motX = -0.02D;
/*     */         } 
/* 586 */       } else if (blockminecarttrackabstract_enumtrackposition == BlockMinecartTrackAbstract.EnumTrackPosition.NORTH_SOUTH) {
/* 587 */         if (this.world.getType(blockposition.north()).l()) {
/* 588 */           this.motZ = 0.02D;
/* 589 */         } else if (this.world.getType(blockposition.south()).l()) {
/* 590 */           this.motZ = -0.02D;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void r() {
/* 598 */     if (isVehicle() || !this.slowWhenEmpty) {
/* 599 */       this.motX *= 0.996999979019165D;
/* 600 */       this.motY *= 0.0D;
/* 601 */       this.motZ *= 0.996999979019165D;
/*     */     } else {
/* 603 */       this.motX *= 0.9599999785423279D;
/* 604 */       this.motY *= 0.0D;
/* 605 */       this.motZ *= 0.9599999785423279D;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPosition(double d0, double d1, double d2) {
/* 611 */     this.locX = d0;
/* 612 */     this.locY = d1;
/* 613 */     this.locZ = d2;
/* 614 */     float f = this.width / 2.0F;
/* 615 */     float f1 = this.length;
/*     */     
/* 617 */     a(new AxisAlignedBB(d0 - f, d1, d2 - f, d0 + f, d1 + f1, d2 + f));
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public Vec3D j(double d0, double d1, double d2) {
/* 622 */     int i = MathHelper.floor(d0);
/* 623 */     int j = MathHelper.floor(d1);
/* 624 */     int k = MathHelper.floor(d2);
/*     */     
/* 626 */     if (BlockMinecartTrackAbstract.b(this.world, new BlockPosition(i, j - 1, k))) {
/* 627 */       j--;
/*     */     }
/*     */     
/* 630 */     IBlockData iblockdata = this.world.getType(new BlockPosition(i, j, k));
/*     */     
/* 632 */     if (BlockMinecartTrackAbstract.i(iblockdata)) {
/* 633 */       double d12; BlockMinecartTrackAbstract.EnumTrackPosition blockminecarttrackabstract_enumtrackposition = iblockdata.<BlockMinecartTrackAbstract.EnumTrackPosition>get(((BlockMinecartTrackAbstract)iblockdata.getBlock()).g());
/* 634 */       int[][] aint = h[blockminecarttrackabstract_enumtrackposition.a()];
/* 635 */       double d3 = i + 0.5D + aint[0][0] * 0.5D;
/* 636 */       double d4 = j + 0.0625D + aint[0][1] * 0.5D;
/* 637 */       double d5 = k + 0.5D + aint[0][2] * 0.5D;
/* 638 */       double d6 = i + 0.5D + aint[1][0] * 0.5D;
/* 639 */       double d7 = j + 0.0625D + aint[1][1] * 0.5D;
/* 640 */       double d8 = k + 0.5D + aint[1][2] * 0.5D;
/* 641 */       double d9 = d6 - d3;
/* 642 */       double d10 = (d7 - d4) * 2.0D;
/* 643 */       double d11 = d8 - d5;
/*     */ 
/*     */       
/* 646 */       if (d9 == 0.0D) {
/* 647 */         d12 = d2 - k;
/* 648 */       } else if (d11 == 0.0D) {
/* 649 */         d12 = d0 - i;
/*     */       } else {
/* 651 */         double d13 = d0 - d3;
/* 652 */         double d14 = d2 - d5;
/*     */         
/* 654 */         d12 = (d13 * d9 + d14 * d11) * 2.0D;
/*     */       } 
/*     */       
/* 657 */       d0 = d3 + d9 * d12;
/* 658 */       d1 = d4 + d10 * d12;
/* 659 */       d2 = d5 + d11 * d12;
/* 660 */       if (d10 < 0.0D) {
/* 661 */         d1++;
/*     */       }
/*     */       
/* 664 */       if (d10 > 0.0D) {
/* 665 */         d1 += 0.5D;
/*     */       }
/*     */       
/* 668 */       return new Vec3D(d0, d1, d2);
/*     */     } 
/* 670 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void a(DataConverterManager dataconvertermanager, Class<?> oclass) {}
/*     */   
/*     */   protected void a(NBTTagCompound nbttagcompound) {
/* 677 */     if (nbttagcompound.getBoolean("CustomDisplayTile")) {
/*     */       Block block;
/*     */       
/* 680 */       if (nbttagcompound.hasKeyOfType("DisplayTile", 8)) {
/* 681 */         block = Block.getByName(nbttagcompound.getString("DisplayTile"));
/*     */       } else {
/* 683 */         block = Block.getById(nbttagcompound.getInt("DisplayTile"));
/*     */       } 
/*     */       
/* 686 */       int i = nbttagcompound.getInt("DisplayData");
/*     */       
/* 688 */       setDisplayBlock((block == null) ? Blocks.AIR.getBlockData() : block.fromLegacyData(i));
/* 689 */       setDisplayBlockOffset(nbttagcompound.getInt("DisplayOffset"));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void b(NBTTagCompound nbttagcompound) {
/* 695 */     if (A()) {
/* 696 */       nbttagcompound.setBoolean("CustomDisplayTile", true);
/* 697 */       IBlockData iblockdata = getDisplayBlock();
/* 698 */       MinecraftKey minecraftkey = Block.REGISTRY.b(iblockdata.getBlock());
/*     */       
/* 700 */       nbttagcompound.setString("DisplayTile", (minecraftkey == null) ? "" : minecraftkey.toString());
/* 701 */       nbttagcompound.setInt("DisplayData", iblockdata.getBlock().toLegacyData(iblockdata));
/* 702 */       nbttagcompound.setInt("DisplayOffset", getDisplayBlockOffset());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void collide(Entity entity) {
/* 708 */     if (!this.world.isClientSide && 
/* 709 */       !entity.noclip && !this.noclip && 
/* 710 */       !w(entity)) {
/* 711 */       double d0 = entity.locX - this.locX;
/* 712 */       double d1 = entity.locZ - this.locZ;
/* 713 */       double d2 = d0 * d0 + d1 * d1;
/*     */       
/* 715 */       if (d2 >= 9.999999747378752E-5D) {
/* 716 */         d2 = MathHelper.sqrt(d2);
/* 717 */         d0 /= d2;
/* 718 */         d1 /= d2;
/* 719 */         double d3 = 1.0D / d2;
/*     */         
/* 721 */         if (d3 > 1.0D) {
/* 722 */           d3 = 1.0D;
/*     */         }
/*     */         
/* 725 */         d0 *= d3;
/* 726 */         d1 *= d3;
/* 727 */         d0 *= 0.10000000149011612D;
/* 728 */         d1 *= 0.10000000149011612D;
/* 729 */         d0 *= (1.0F - this.R);
/* 730 */         d1 *= (1.0F - this.R);
/* 731 */         d0 *= 0.5D;
/* 732 */         d1 *= 0.5D;
/* 733 */         if (entity instanceof EntityMinecartAbstract) {
/* 734 */           double d4 = entity.locX - this.locX;
/* 735 */           double d5 = entity.locZ - this.locZ;
/* 736 */           Vec3D vec3d = (new Vec3D(d4, 0.0D, d5)).a();
/* 737 */           Vec3D vec3d1 = (new Vec3D(MathHelper.cos(this.yaw * 0.017453292F), 0.0D, MathHelper.sin(this.yaw * 0.017453292F))).a();
/* 738 */           double d6 = Math.abs(vec3d.b(vec3d1));
/*     */           
/* 740 */           if (d6 < 0.800000011920929D) {
/*     */             return;
/*     */           }
/*     */           
/* 744 */           double d7 = entity.motX + this.motX;
/* 745 */           double d8 = entity.motZ + this.motZ;
/*     */           
/* 747 */           if (((EntityMinecartAbstract)entity).v() == EnumMinecartType.FURNACE && v() != EnumMinecartType.FURNACE) {
/* 748 */             this.motX *= 0.20000000298023224D;
/* 749 */             this.motZ *= 0.20000000298023224D;
/* 750 */             f(entity.motX - d0, 0.0D, entity.motZ - d1);
/* 751 */             entity.motX *= 0.949999988079071D;
/* 752 */             entity.motZ *= 0.949999988079071D;
/* 753 */           } else if (((EntityMinecartAbstract)entity).v() != EnumMinecartType.FURNACE && v() == EnumMinecartType.FURNACE) {
/* 754 */             entity.motX *= 0.20000000298023224D;
/* 755 */             entity.motZ *= 0.20000000298023224D;
/* 756 */             entity.f(this.motX + d0, 0.0D, this.motZ + d1);
/* 757 */             this.motX *= 0.949999988079071D;
/* 758 */             this.motZ *= 0.949999988079071D;
/*     */           } else {
/* 760 */             d7 /= 2.0D;
/* 761 */             d8 /= 2.0D;
/* 762 */             this.motX *= 0.20000000298023224D;
/* 763 */             this.motZ *= 0.20000000298023224D;
/* 764 */             f(d7 - d0, 0.0D, d8 - d1);
/* 765 */             entity.motX *= 0.20000000298023224D;
/* 766 */             entity.motZ *= 0.20000000298023224D;
/* 767 */             entity.f(d7 + d0, 0.0D, d8 + d1);
/*     */           } 
/*     */         } else {
/* 770 */           f(-d0, 0.0D, -d1);
/* 771 */           entity.f(d0 / 4.0D, 0.0D, d1 / 4.0D);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDamage(float f) {
/* 781 */     this.datawatcher.set(c, Float.valueOf(f));
/*     */   }
/*     */   
/*     */   public float getDamage() {
/* 785 */     return ((Float)this.datawatcher.<Float>get(c)).floatValue();
/*     */   }
/*     */   
/*     */   public void d(int i) {
/* 789 */     this.datawatcher.set(a, Integer.valueOf(i));
/*     */   }
/*     */   
/*     */   public int getType() {
/* 793 */     return ((Integer)this.datawatcher.<Integer>get(a)).intValue();
/*     */   }
/*     */   
/*     */   public void e(int i) {
/* 797 */     this.datawatcher.set(b, Integer.valueOf(i));
/*     */   }
/*     */   
/*     */   public int u() {
/* 801 */     return ((Integer)this.datawatcher.<Integer>get(b)).intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockData getDisplayBlock() {
/* 807 */     return !A() ? x() : Block.getByCombinedId(((Integer)getDataWatcher().<Integer>get(d)).intValue());
/*     */   }
/*     */   
/*     */   public IBlockData x() {
/* 811 */     return Blocks.AIR.getBlockData();
/*     */   }
/*     */   
/*     */   public int getDisplayBlockOffset() {
/* 815 */     return !A() ? z() : ((Integer)getDataWatcher().<Integer>get(e)).intValue();
/*     */   }
/*     */   
/*     */   public int z() {
/* 819 */     return 6;
/*     */   }
/*     */   
/*     */   public void setDisplayBlock(IBlockData iblockdata) {
/* 823 */     getDataWatcher().set(d, Integer.valueOf(Block.getCombinedId(iblockdata)));
/* 824 */     a(true);
/*     */   }
/*     */   
/*     */   public void setDisplayBlockOffset(int i) {
/* 828 */     getDataWatcher().set(e, Integer.valueOf(i));
/* 829 */     a(true);
/*     */   }
/*     */   
/*     */   public boolean A() {
/* 833 */     return ((Boolean)getDataWatcher().<Boolean>get(f)).booleanValue();
/*     */   }
/*     */   
/*     */   public void a(boolean flag) {
/* 837 */     getDataWatcher().set(f, Boolean.valueOf(flag));
/*     */   }
/*     */   
/*     */   public enum EnumMinecartType
/*     */   {
/* 842 */     RIDEABLE(0, "MinecartRideable"), CHEST(1, "MinecartChest"), FURNACE(2, "MinecartFurnace"), TNT(3, "MinecartTNT"), SPAWNER(4, "MinecartSpawner"), HOPPER(5, "MinecartHopper"), COMMAND_BLOCK(6, "MinecartCommandBlock");
/*     */     
/* 844 */     private static final Map<Integer, EnumMinecartType> h = Maps.newHashMap();
/*     */     
/*     */     private final int i;
/*     */     
/*     */     private final String j;
/*     */ 
/*     */     
/*     */     EnumMinecartType(int i, String s) {
/*     */       this.i = i;
/*     */       this.j = s;
/*     */     }
/*     */ 
/*     */     
/*     */     public int a() {
/*     */       return this.i;
/*     */     }
/*     */     
/*     */     static {
/* 862 */       EnumMinecartType[] aentityminecartabstract_enumminecarttype = values();
/* 863 */       int i = aentityminecartabstract_enumminecarttype.length;
/*     */       
/* 865 */       for (int j = 0; j < i; j++) {
/* 866 */         EnumMinecartType entityminecartabstract_enumminecarttype = aentityminecartabstract_enumminecarttype[j];
/*     */         
/* 868 */         h.put(Integer.valueOf(entityminecartabstract_enumminecarttype.a()), entityminecartabstract_enumminecarttype);
/*     */       } 
/*     */     }
/*     */     public String b() {
/*     */       return this.j;
/*     */     } }
/*     */   
/*     */   public Vector getFlyingVelocityMod() {
/* 876 */     return new Vector(this.flyingX, this.flyingY, this.flyingZ);
/*     */   }
/*     */   
/*     */   public void setFlyingVelocityMod(Vector flying) {
/* 880 */     this.flyingX = flying.getX();
/* 881 */     this.flyingY = flying.getY();
/* 882 */     this.flyingZ = flying.getZ();
/*     */   }
/*     */   
/*     */   public Vector getDerailedVelocityMod() {
/* 886 */     return new Vector(this.derailedX, this.derailedY, this.derailedZ);
/*     */   }
/*     */   
/*     */   public void setDerailedVelocityMod(Vector derailed) {
/* 890 */     this.derailedX = derailed.getX();
/* 891 */     this.derailedY = derailed.getY();
/* 892 */     this.derailedZ = derailed.getZ();
/*     */   }
/*     */   
/*     */   public abstract EnumMinecartType v();
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityMinecartAbstract.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */