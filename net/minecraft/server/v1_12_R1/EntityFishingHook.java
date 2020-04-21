/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.Fish;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.player.PlayerFishEvent;
/*     */ 
/*     */ public class EntityFishingHook extends Entity {
/*  13 */   private static final DataWatcherObject<Integer> b = DataWatcher.a((Class)EntityFishingHook.class, DataWatcherRegistry.b);
/*     */   private boolean isInGround;
/*     */   private int d;
/*     */   public EntityHuman owner;
/*     */   private int f;
/*     */   private int g;
/*     */   private int h;
/*     */   private int at;
/*     */   private float au;
/*     */   public Entity hooked;
/*     */   private HookState av;
/*     */   private int aw;
/*     */   private int ax;
/*     */   
/*     */   public EntityFishingHook(World world, EntityHuman entityhuman) {
/*  28 */     super(world);
/*  29 */     this.av = HookState.FLYING;
/*  30 */     a(entityhuman);
/*  31 */     n();
/*     */   }
/*     */   
/*     */   private void a(EntityHuman entityhuman) {
/*  35 */     setSize(0.25F, 0.25F);
/*  36 */     this.ah = true;
/*  37 */     this.owner = entityhuman;
/*  38 */     this.owner.hookedFish = this;
/*     */   }
/*     */   
/*     */   public void a(int i) {
/*  42 */     this.ax = i;
/*     */   }
/*     */   
/*     */   public void c(int i) {
/*  46 */     this.aw = i;
/*     */   }
/*     */   
/*     */   private void n() {
/*  50 */     float f = this.owner.lastPitch + this.owner.pitch - this.owner.lastPitch;
/*  51 */     float f1 = this.owner.lastYaw + this.owner.yaw - this.owner.lastYaw;
/*  52 */     float f2 = MathHelper.cos(-f1 * 0.017453292F - 3.1415927F);
/*  53 */     float f3 = MathHelper.sin(-f1 * 0.017453292F - 3.1415927F);
/*  54 */     float f4 = -MathHelper.cos(-f * 0.017453292F);
/*  55 */     float f5 = MathHelper.sin(-f * 0.017453292F);
/*  56 */     double d0 = this.owner.lastX + this.owner.locX - this.owner.lastX - f3 * 0.3D;
/*  57 */     double d1 = this.owner.lastY + this.owner.locY - this.owner.lastY + this.owner.getHeadHeight();
/*  58 */     double d2 = this.owner.lastZ + this.owner.locZ - this.owner.lastZ - f2 * 0.3D;
/*     */     
/*  60 */     setPositionRotation(d0, d1, d2, f1, f);
/*  61 */     this.motX = -f3;
/*  62 */     this.motY = MathHelper.a(-(f5 / f4), -5.0F, 5.0F);
/*  63 */     this.motZ = -f2;
/*  64 */     float f6 = MathHelper.sqrt(this.motX * this.motX + this.motY * this.motY + this.motZ * this.motZ);
/*     */     
/*  66 */     this.motX *= 0.6D / f6 + 0.5D + this.random.nextGaussian() * 0.0045D;
/*  67 */     this.motY *= 0.6D / f6 + 0.5D + this.random.nextGaussian() * 0.0045D;
/*  68 */     this.motZ *= 0.6D / f6 + 0.5D + this.random.nextGaussian() * 0.0045D;
/*  69 */     float f7 = MathHelper.sqrt(this.motX * this.motX + this.motZ * this.motZ);
/*     */     
/*  71 */     this.yaw = (float)(MathHelper.c(this.motX, this.motZ) * 57.2957763671875D);
/*  72 */     this.pitch = (float)(MathHelper.c(this.motY, f7) * 57.2957763671875D);
/*  73 */     this.lastYaw = this.yaw;
/*  74 */     this.lastPitch = this.pitch;
/*     */   }
/*     */   
/*     */   protected void i() {
/*  78 */     getDataWatcher().register(b, Integer.valueOf(0));
/*     */   }
/*     */   
/*     */   public void a(DataWatcherObject<?> datawatcherobject) {
/*  82 */     if (b.equals(datawatcherobject)) {
/*  83 */       int i = ((Integer)getDataWatcher().<Integer>get(b)).intValue();
/*     */       
/*  85 */       this.hooked = (i > 0) ? this.world.getEntity(i - 1) : null;
/*     */     } 
/*     */     
/*  88 */     super.a(datawatcherobject);
/*     */   }
/*     */   
/*     */   public void B_() {
/*  92 */     super.B_();
/*  93 */     if (this.owner == null) {
/*  94 */       die();
/*  95 */     } else if (this.world.isClientSide || !p()) {
/*  96 */       if (this.isInGround) {
/*  97 */         this.d++;
/*  98 */         if (this.d >= 1200) {
/*  99 */           die();
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/* 104 */       float f = 0.0F;
/* 105 */       BlockPosition blockposition = new BlockPosition(this);
/* 106 */       IBlockData iblockdata = this.world.getType(blockposition);
/*     */       
/* 108 */       if (iblockdata.getMaterial() == Material.WATER) {
/* 109 */         f = BlockFluids.g(iblockdata, this.world, blockposition);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 114 */       if (this.av == HookState.FLYING) {
/* 115 */         if (this.hooked != null) {
/* 116 */           this.motX = 0.0D;
/* 117 */           this.motY = 0.0D;
/* 118 */           this.motZ = 0.0D;
/* 119 */           this.av = HookState.HOOKED_IN_ENTITY;
/*     */           
/*     */           return;
/*     */         } 
/* 123 */         if (f > 0.0F) {
/* 124 */           this.motX *= 0.3D;
/* 125 */           this.motY *= 0.2D;
/* 126 */           this.motZ *= 0.3D;
/* 127 */           this.av = HookState.BOBBING;
/*     */           
/*     */           return;
/*     */         } 
/* 131 */         if (!this.world.isClientSide) {
/* 132 */           r();
/*     */         }
/*     */         
/* 135 */         if (!this.isInGround && !this.onGround && !this.positionChanged) {
/* 136 */           this.f++;
/*     */         } else {
/* 138 */           this.f = 0;
/* 139 */           this.motX = 0.0D;
/* 140 */           this.motY = 0.0D;
/* 141 */           this.motZ = 0.0D;
/*     */         } 
/*     */       } else {
/* 144 */         if (this.av == HookState.HOOKED_IN_ENTITY) {
/* 145 */           if (this.hooked != null) {
/* 146 */             if (this.hooked.dead) {
/* 147 */               this.hooked = null;
/* 148 */               this.av = HookState.FLYING;
/*     */             } else {
/* 150 */               this.locX = this.hooked.locX;
/* 151 */               double d1 = this.hooked.length;
/*     */               
/* 153 */               this.locY = (this.hooked.getBoundingBox()).b + d1 * 0.8D;
/* 154 */               this.locZ = this.hooked.locZ;
/* 155 */               setPosition(this.locX, this.locY, this.locZ);
/*     */             } 
/*     */           }
/*     */           
/*     */           return;
/*     */         } 
/*     */         
/* 162 */         if (this.av == HookState.BOBBING) {
/* 163 */           this.motX *= 0.9D;
/* 164 */           this.motZ *= 0.9D;
/* 165 */           double d = this.locY + this.motY - blockposition.getY() - f;
/* 166 */           if (Math.abs(d) < 0.01D) {
/* 167 */             d += Math.signum(d) * 0.1D;
/*     */           }
/*     */           
/* 170 */           this.motY -= d * this.random.nextFloat() * 0.2D;
/* 171 */           if (!this.world.isClientSide && f > 0.0F) {
/* 172 */             a(blockposition);
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/* 177 */       if (iblockdata.getMaterial() != Material.WATER) {
/* 178 */         this.motY -= 0.03D;
/*     */       }
/*     */       
/* 181 */       move(EnumMoveType.SELF, this.motX, this.motY, this.motZ);
/* 182 */       q();
/* 183 */       double d0 = 0.92D;
/* 184 */       this.motX *= 0.92D;
/* 185 */       this.motY *= 0.92D;
/* 186 */       this.motZ *= 0.92D;
/* 187 */       setPosition(this.locX, this.locY, this.locZ);
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean p() {
/* 192 */     ItemStack itemstack = this.owner.getItemInMainHand();
/* 193 */     ItemStack itemstack1 = this.owner.getItemInOffHand();
/* 194 */     boolean flag = (itemstack.getItem() == Items.FISHING_ROD);
/* 195 */     boolean flag1 = (itemstack1.getItem() == Items.FISHING_ROD);
/*     */     
/* 197 */     if (!this.owner.dead && this.owner.isAlive() && (flag || flag1) && h(this.owner) <= 1024.0D) {
/* 198 */       return false;
/*     */     }
/* 200 */     die();
/* 201 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private void q() {
/* 206 */     float f = MathHelper.sqrt(this.motX * this.motX + this.motZ * this.motZ);
/*     */     
/* 208 */     this.yaw = (float)(MathHelper.c(this.motX, this.motZ) * 57.2957763671875D);
/*     */     
/* 210 */     for (this.pitch = (float)(MathHelper.c(this.motY, f) * 57.2957763671875D); this.pitch - this.lastPitch < -180.0F; this.lastPitch -= 360.0F);
/*     */ 
/*     */ 
/*     */     
/* 214 */     while (this.pitch - this.lastPitch >= 180.0F) {
/* 215 */       this.lastPitch += 360.0F;
/*     */     }
/*     */     
/* 218 */     while (this.yaw - this.lastYaw < -180.0F) {
/* 219 */       this.lastYaw -= 360.0F;
/*     */     }
/*     */     
/* 222 */     while (this.yaw - this.lastYaw >= 180.0F) {
/* 223 */       this.lastYaw += 360.0F;
/*     */     }
/*     */     
/* 226 */     this.pitch = this.lastPitch + (this.pitch - this.lastPitch) * 0.2F;
/* 227 */     this.yaw = this.lastYaw + (this.yaw - this.lastYaw) * 0.2F;
/*     */   }
/*     */   
/*     */   private void r() {
/* 231 */     Vec3D vec3d = new Vec3D(this.locX, this.locY, this.locZ);
/* 232 */     Vec3D vec3d1 = new Vec3D(this.locX + this.motX, this.locY + this.motY, this.locZ + this.motZ);
/* 233 */     MovingObjectPosition movingobjectposition = this.world.rayTrace(vec3d, vec3d1, false, true, false);
/*     */     
/* 235 */     vec3d = new Vec3D(this.locX, this.locY, this.locZ);
/* 236 */     vec3d1 = new Vec3D(this.locX + this.motX, this.locY + this.motY, this.locZ + this.motZ);
/* 237 */     if (movingobjectposition != null) {
/* 238 */       vec3d1 = new Vec3D(movingobjectposition.pos.x, movingobjectposition.pos.y, movingobjectposition.pos.z);
/*     */     }
/*     */     
/* 241 */     Entity entity = null;
/* 242 */     List<Entity> list = this.world.getEntities(this, getBoundingBox().b(this.motX, this.motY, this.motZ).g(1.0D));
/* 243 */     double d0 = 0.0D;
/* 244 */     Iterator<Entity> iterator = list.iterator();
/*     */     
/* 246 */     while (iterator.hasNext()) {
/* 247 */       Entity entity1 = iterator.next();
/*     */       
/* 249 */       if (a(entity1) && (entity1 != this.owner || this.f >= 5)) {
/* 250 */         AxisAlignedBB axisalignedbb = entity1.getBoundingBox().g(0.30000001192092896D);
/* 251 */         MovingObjectPosition movingobjectposition1 = axisalignedbb.b(vec3d, vec3d1);
/*     */         
/* 253 */         if (movingobjectposition1 != null) {
/* 254 */           double d1 = vec3d.distanceSquared(movingobjectposition1.pos);
/*     */           
/* 256 */           if (d1 < d0 || d0 == 0.0D) {
/* 257 */             entity = entity1;
/* 258 */             d0 = d1;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 264 */     if (entity != null) {
/* 265 */       movingobjectposition = new MovingObjectPosition(entity);
/*     */     }
/*     */     
/* 268 */     if (movingobjectposition != null && movingobjectposition.type != MovingObjectPosition.EnumMovingObjectType.MISS) {
/* 269 */       CraftEventFactory.callProjectileHitEvent(this, movingobjectposition);
/* 270 */       if (movingobjectposition.type == MovingObjectPosition.EnumMovingObjectType.ENTITY) {
/* 271 */         this.hooked = movingobjectposition.entity;
/* 272 */         s();
/*     */       } else {
/* 274 */         this.isInGround = true;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void s() {
/* 281 */     getDataWatcher().set(b, Integer.valueOf(this.hooked.getId() + 1));
/*     */   }
/*     */   
/*     */   private void a(BlockPosition blockposition) {
/* 285 */     WorldServer worldserver = (WorldServer)this.world;
/* 286 */     int i = 1;
/* 287 */     BlockPosition blockposition1 = blockposition.up();
/*     */     
/* 289 */     if (this.random.nextFloat() < 0.25F && this.world.isRainingAt(blockposition1)) {
/* 290 */       i++;
/*     */     }
/*     */     
/* 293 */     if (this.random.nextFloat() < 0.5F && !this.world.h(blockposition1)) {
/* 294 */       i--;
/*     */     }
/*     */     
/* 297 */     if (this.g > 0) {
/* 298 */       this.g--;
/* 299 */       if (this.g <= 0) {
/* 300 */         this.h = 0;
/* 301 */         this.at = 0;
/*     */         
/* 303 */         PlayerFishEvent playerFishEvent = new PlayerFishEvent((Player)this.owner.getBukkitEntity(), null, (Fish)getBukkitEntity(), PlayerFishEvent.State.FAILED_ATTEMPT);
/* 304 */         this.world.getServer().getPluginManager().callEvent((Event)playerFishEvent);
/*     */       } else {
/*     */         
/* 307 */         this.motY -= 0.2D * this.random.nextFloat() * this.random.nextFloat();
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 318 */     else if (this.at > 0) {
/* 319 */       this.at -= i;
/* 320 */       if (this.at > 0) {
/* 321 */         this.au = (float)(this.au + this.random.nextGaussian() * 4.0D);
/* 322 */         float f = this.au * 0.017453292F;
/* 323 */         float f1 = MathHelper.sin(f);
/* 324 */         float f2 = MathHelper.cos(f);
/* 325 */         double d0 = this.locX + (f1 * this.at * 0.1F);
/* 326 */         double d1 = (MathHelper.floor((getBoundingBox()).b) + 1.0F);
/* 327 */         double d2 = this.locZ + (f2 * this.at * 0.1F);
/* 328 */         Block block = worldserver.getType(new BlockPosition(d0, d1 - 1.0D, d2)).getBlock();
/* 329 */         if (block == Blocks.WATER || block == Blocks.FLOWING_WATER) {
/* 330 */           if (this.random.nextFloat() < 0.15F) {
/* 331 */             worldserver.a(EnumParticle.WATER_BUBBLE, d0, d1 - 0.10000000149011612D, d2, 1, f1, 0.1D, f2, 0.0D, new int[0]);
/*     */           }
/*     */           
/* 334 */           float f3 = f1 * 0.04F;
/* 335 */           float f4 = f2 * 0.04F;
/*     */           
/* 337 */           worldserver.a(EnumParticle.WATER_WAKE, d0, d1, d2, 0, f4, 0.01D, -f3, 1.0D, new int[0]);
/* 338 */           worldserver.a(EnumParticle.WATER_WAKE, d0, d1, d2, 0, -f4, 0.01D, f3, 1.0D, new int[0]);
/*     */         } 
/*     */       } else {
/*     */         
/* 342 */         PlayerFishEvent playerFishEvent = new PlayerFishEvent((Player)this.owner.getBukkitEntity(), null, (Fish)getBukkitEntity(), PlayerFishEvent.State.BITE);
/* 343 */         this.world.getServer().getPluginManager().callEvent((Event)playerFishEvent);
/* 344 */         if (playerFishEvent.isCancelled()) {
/*     */           return;
/*     */         }
/*     */         
/* 348 */         this.motY = (-0.4F * MathHelper.a(this.random, 0.6F, 1.0F));
/* 349 */         a(SoundEffects.K, 0.25F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.4F);
/* 350 */         double d3 = (getBoundingBox()).b + 0.5D;
/*     */         
/* 352 */         worldserver.a(EnumParticle.WATER_BUBBLE, this.locX, d3, this.locZ, (int)(1.0F + this.width * 20.0F), this.width, 0.0D, this.width, 0.20000000298023224D, new int[0]);
/* 353 */         worldserver.a(EnumParticle.WATER_WAKE, this.locX, d3, this.locZ, (int)(1.0F + this.width * 20.0F), this.width, 0.0D, this.width, 0.20000000298023224D, new int[0]);
/* 354 */         this.g = MathHelper.nextInt(this.random, 20, 40);
/*     */       } 
/* 356 */     } else if (this.h > 0) {
/* 357 */       this.h -= i;
/* 358 */       float f = 0.15F;
/* 359 */       if (this.h < 20) {
/* 360 */         f = (float)(f + (20 - this.h) * 0.05D);
/* 361 */       } else if (this.h < 40) {
/* 362 */         f = (float)(f + (40 - this.h) * 0.02D);
/* 363 */       } else if (this.h < 60) {
/* 364 */         f = (float)(f + (60 - this.h) * 0.01D);
/*     */       } 
/*     */       
/* 367 */       if (this.random.nextFloat() < f) {
/* 368 */         float f1 = MathHelper.a(this.random, 0.0F, 360.0F) * 0.017453292F;
/* 369 */         float f2 = MathHelper.a(this.random, 25.0F, 60.0F);
/* 370 */         double d0 = this.locX + (MathHelper.sin(f1) * f2 * 0.1F);
/* 371 */         double d1 = (MathHelper.floor((getBoundingBox()).b) + 1.0F);
/* 372 */         double d2 = this.locZ + (MathHelper.cos(f1) * f2 * 0.1F);
/* 373 */         Block block = worldserver.getType(new BlockPosition((int)d0, (int)d1 - 1, (int)d2)).getBlock();
/* 374 */         if (block == Blocks.WATER || block == Blocks.FLOWING_WATER) {
/* 375 */           worldserver.a(EnumParticle.WATER_SPLASH, d0, d1, d2, 2 + this.random.nextInt(2), 0.10000000149011612D, 0.0D, 0.10000000149011612D, 0.0D, new int[0]);
/*     */         }
/*     */       } 
/*     */       
/* 379 */       if (this.h <= 0) {
/* 380 */         this.au = MathHelper.a(this.random, 0.0F, 360.0F);
/* 381 */         this.at = MathHelper.nextInt(this.random, 20, 80);
/*     */       } 
/*     */     } else {
/* 384 */       this.h = MathHelper.nextInt(this.random, 100, 600);
/* 385 */       this.h -= this.ax * 20 * 5;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean a(Entity entity) {
/* 392 */     return !(!entity.isInteractable() && !(entity instanceof EntityItem));
/*     */   }
/*     */   
/*     */   public void b(NBTTagCompound nbttagcompound) {}
/*     */   
/*     */   public void a(NBTTagCompound nbttagcompound) {}
/*     */   
/*     */   public int j() {
/* 400 */     if (!this.world.isClientSide && this.owner != null) {
/* 401 */       int i = 0;
/*     */       
/* 403 */       if (this.hooked != null) {
/*     */         
/* 405 */         PlayerFishEvent playerFishEvent = new PlayerFishEvent((Player)this.owner.getBukkitEntity(), (Entity)this.hooked.getBukkitEntity(), (Fish)getBukkitEntity(), PlayerFishEvent.State.CAUGHT_ENTITY);
/* 406 */         this.world.getServer().getPluginManager().callEvent((Event)playerFishEvent);
/*     */         
/* 408 */         if (playerFishEvent.isCancelled()) {
/* 409 */           return 0;
/*     */         }
/*     */         
/* 412 */         k();
/* 413 */         this.world.broadcastEntityEffect(this, (byte)31);
/* 414 */         i = (this.hooked instanceof EntityItem) ? 3 : 5;
/* 415 */       } else if (this.g > 0) {
/* 416 */         LootTableInfo.a loottableinfo_a = new LootTableInfo.a((WorldServer)this.world);
/*     */         
/* 418 */         loottableinfo_a.a(this.aw + this.owner.du());
/* 419 */         Iterator<ItemStack> iterator = this.world.getLootTableRegistry().a(LootTables.aA).a(this.random, loottableinfo_a.a()).iterator();
/*     */         
/* 421 */         while (iterator.hasNext()) {
/* 422 */           ItemStack itemstack = iterator.next();
/* 423 */           EntityItem entityitem = new EntityItem(this.world, this.locX, this.locY, this.locZ, itemstack);
/*     */           
/* 425 */           PlayerFishEvent playerFishEvent = new PlayerFishEvent((Player)this.owner.getBukkitEntity(), (Entity)entityitem.getBukkitEntity(), (Fish)getBukkitEntity(), PlayerFishEvent.State.CAUGHT_FISH);
/* 426 */           playerFishEvent.setExpToDrop(this.random.nextInt(6) + 1);
/* 427 */           this.world.getServer().getPluginManager().callEvent((Event)playerFishEvent);
/*     */           
/* 429 */           if (playerFishEvent.isCancelled()) {
/* 430 */             return 0;
/*     */           }
/*     */           
/* 433 */           double d0 = this.owner.locX - this.locX;
/* 434 */           double d1 = this.owner.locY - this.locY;
/* 435 */           double d2 = this.owner.locZ - this.locZ;
/* 436 */           double d3 = MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
/*     */ 
/*     */           
/* 439 */           entityitem.motX = d0 * 0.1D;
/* 440 */           entityitem.motY = d1 * 0.1D + MathHelper.sqrt(d3) * 0.08D;
/* 441 */           entityitem.motZ = d2 * 0.1D;
/* 442 */           this.world.addEntity(entityitem);
/*     */           
/* 444 */           if (playerFishEvent.getExpToDrop() > 0) {
/* 445 */             this.owner.world.addEntity(new EntityExperienceOrb(this.owner.world, this.owner.locX, this.owner.locY + 0.5D, this.owner.locZ + 0.5D, playerFishEvent.getExpToDrop()));
/*     */           }
/*     */           
/* 448 */           Item item = itemstack.getItem();
/*     */           
/* 450 */           if (item == Items.FISH || item == Items.COOKED_FISH) {
/* 451 */             this.owner.a(StatisticList.E, 1);
/*     */           }
/*     */         } 
/*     */         
/* 455 */         i = 1;
/*     */       } 
/*     */       
/* 458 */       if (this.isInGround) {
/*     */         
/* 460 */         PlayerFishEvent playerFishEvent = new PlayerFishEvent((Player)this.owner.getBukkitEntity(), null, (Fish)getBukkitEntity(), PlayerFishEvent.State.IN_GROUND);
/* 461 */         this.world.getServer().getPluginManager().callEvent((Event)playerFishEvent);
/*     */         
/* 463 */         if (playerFishEvent.isCancelled()) {
/* 464 */           return 0;
/*     */         }
/*     */         
/* 467 */         i = 2;
/*     */       } 
/*     */       
/* 470 */       if (i == 0) {
/* 471 */         PlayerFishEvent playerFishEvent = new PlayerFishEvent((Player)this.owner.getBukkitEntity(), null, (Fish)getBukkitEntity(), PlayerFishEvent.State.FAILED_ATTEMPT);
/* 472 */         this.world.getServer().getPluginManager().callEvent((Event)playerFishEvent);
/* 473 */         if (playerFishEvent.isCancelled()) {
/* 474 */           return 0;
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 479 */       die();
/* 480 */       return i;
/*     */     } 
/* 482 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void k() {
/* 487 */     if (this.owner != null) {
/* 488 */       double d0 = this.owner.locX - this.locX;
/* 489 */       double d1 = this.owner.locY - this.locY;
/* 490 */       double d2 = this.owner.locZ - this.locZ;
/*     */ 
/*     */       
/* 493 */       this.hooked.motX += d0 * 0.1D;
/* 494 */       this.hooked.motY += d1 * 0.1D;
/* 495 */       this.hooked.motZ += d2 * 0.1D;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected boolean playStepSound() {
/* 500 */     return false;
/*     */   }
/*     */   
/*     */   public void die() {
/* 504 */     super.die();
/* 505 */     if (this.owner != null) {
/* 506 */       this.owner.hookedFish = null;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityHuman l() {
/* 512 */     return this.owner;
/*     */   }
/*     */   
/*     */   enum HookState
/*     */   {
/* 517 */     FLYING, HOOKED_IN_ENTITY, BOBBING;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityFishingHook.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */