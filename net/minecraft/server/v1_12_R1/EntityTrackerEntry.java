/*     */ package net.minecraft.server.v1_12_R1;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.player.PlayerVelocityEvent;
/*     */ import org.bukkit.util.Vector;
/*     */ import org.spigotmc.AsyncCatcher;
/*     */ 
/*     */ public class EntityTrackerEntry {
/*  19 */   private static final Logger c = LogManager.getLogger();
/*     */   private final Entity tracker;
/*     */   private final int e;
/*     */   private int f;
/*     */   private final int g;
/*     */   private long xLoc;
/*     */   private long yLoc;
/*     */   private long zLoc;
/*     */   private int yRot;
/*     */   private int xRot;
/*     */   private int headYaw;
/*     */   private double n;
/*     */   private double o;
/*     */   private double p;
/*     */   public int a;
/*     */   private double q;
/*     */   private double r;
/*     */   private double s;
/*     */   private boolean isMoving;
/*     */   private final boolean u;
/*     */   private int v;
/*  40 */   private List<Entity> w = Collections.emptyList();
/*     */   private boolean x;
/*     */   private boolean y;
/*     */   public boolean b;
/*  44 */   public final Set<EntityPlayer> trackedPlayers = Sets.newHashSet();
/*     */   
/*     */   public EntityTrackerEntry(Entity entity, int i, int j, int k, boolean flag) {
/*  47 */     this.tracker = entity;
/*  48 */     this.e = i;
/*  49 */     this.f = j;
/*  50 */     this.g = k;
/*  51 */     this.u = flag;
/*  52 */     this.xLoc = EntityTracker.a(entity.locX);
/*  53 */     this.yLoc = EntityTracker.a(entity.locY);
/*  54 */     this.zLoc = EntityTracker.a(entity.locZ);
/*  55 */     this.yRot = MathHelper.d(entity.yaw * 256.0F / 360.0F);
/*  56 */     this.xRot = MathHelper.d(entity.pitch * 256.0F / 360.0F);
/*  57 */     this.headYaw = MathHelper.d(entity.getHeadRotation() * 256.0F / 360.0F);
/*  58 */     this.y = entity.onGround;
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/*  62 */     return (object instanceof EntityTrackerEntry) ? ((((EntityTrackerEntry)object).tracker.getId() == this.tracker.getId())) : false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  66 */     return this.tracker.getId();
/*     */   }
/*     */   
/*     */   public void track(List<EntityHuman> list) {
/*  70 */     this.b = false;
/*  71 */     if (!this.isMoving || this.tracker.d(this.q, this.r, this.s) > 16.0D) {
/*  72 */       this.q = this.tracker.locX;
/*  73 */       this.r = this.tracker.locY;
/*  74 */       this.s = this.tracker.locZ;
/*  75 */       this.isMoving = true;
/*  76 */       this.b = true;
/*  77 */       scanPlayers(list);
/*     */     } 
/*     */     
/*  80 */     List<Entity> list1 = this.tracker.bF();
/*     */     
/*  82 */     if (!list1.equals(this.w)) {
/*  83 */       this.w = list1;
/*  84 */       broadcastIncludingSelf(new PacketPlayOutMount(this.tracker));
/*     */     } 
/*     */ 
/*     */     
/*  88 */     if (this.tracker instanceof EntityItemFrame) {
/*  89 */       EntityItemFrame entityitemframe = (EntityItemFrame)this.tracker;
/*  90 */       ItemStack itemstack = entityitemframe.getItem();
/*     */       
/*  92 */       if (this.a % 10 == 0 && itemstack.getItem() instanceof ItemWorldMap) {
/*  93 */         WorldMap worldmap = Items.FILLED_MAP.getSavedMap(itemstack, this.tracker.world);
/*  94 */         Iterator<EntityPlayer> iterator = this.trackedPlayers.iterator();
/*     */         
/*  96 */         while (iterator.hasNext()) {
/*  97 */           EntityHuman entityhuman = iterator.next();
/*  98 */           EntityPlayer entityplayer = (EntityPlayer)entityhuman;
/*     */           
/* 100 */           worldmap.a(entityplayer, itemstack);
/* 101 */           Packet<?> packet = Items.FILLED_MAP.a(itemstack, this.tracker.world, entityplayer);
/*     */           
/* 103 */           if (packet != null) {
/* 104 */             entityplayer.playerConnection.sendPacket(packet);
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/* 109 */       d();
/*     */     } 
/*     */     
/* 112 */     if (this.a % this.g == 0 || this.tracker.impulse || this.tracker.getDataWatcher().a()) {
/*     */ 
/*     */       
/* 115 */       if (this.tracker.isPassenger()) {
/* 116 */         int k = MathHelper.d(this.tracker.yaw * 256.0F / 360.0F);
/* 117 */         int j = MathHelper.d(this.tracker.pitch * 256.0F / 360.0F);
/* 118 */         boolean flag = !(Math.abs(k - this.yRot) < 1 && Math.abs(j - this.xRot) < 1);
/*     */         
/* 120 */         if (flag) {
/* 121 */           broadcast(new PacketPlayOutEntity.PacketPlayOutEntityLook(this.tracker.getId(), (byte)k, (byte)j, this.tracker.onGround));
/* 122 */           this.yRot = k;
/* 123 */           this.xRot = j;
/*     */         } 
/*     */         
/* 126 */         this.xLoc = EntityTracker.a(this.tracker.locX);
/* 127 */         this.yLoc = EntityTracker.a(this.tracker.locY);
/* 128 */         this.zLoc = EntityTracker.a(this.tracker.locZ);
/* 129 */         d();
/* 130 */         this.x = true;
/*     */       } else {
/* 132 */         this.v++;
/* 133 */         long k = EntityTracker.a(this.tracker.locX);
/* 134 */         long l = EntityTracker.a(this.tracker.locY);
/* 135 */         long i1 = EntityTracker.a(this.tracker.locZ);
/* 136 */         int j1 = MathHelper.d(this.tracker.yaw * 256.0F / 360.0F);
/* 137 */         int k1 = MathHelper.d(this.tracker.pitch * 256.0F / 360.0F);
/* 138 */         long l1 = k - this.xLoc;
/* 139 */         long i2 = l - this.yLoc;
/* 140 */         long j2 = i1 - this.zLoc;
/* 141 */         Object object = null;
/* 142 */         boolean flag1 = !(l1 * l1 + i2 * i2 + j2 * j2 < 128L && this.a % 60 != 0);
/* 143 */         boolean flag2 = !(Math.abs(j1 - this.yRot) < 1 && Math.abs(k1 - this.xRot) < 1);
/*     */ 
/*     */         
/* 146 */         if (flag1) {
/* 147 */           this.xLoc = k;
/* 148 */           this.yLoc = l;
/* 149 */           this.zLoc = i1;
/*     */         } 
/*     */         
/* 152 */         if (flag2) {
/* 153 */           this.yRot = j1;
/* 154 */           this.xRot = k1;
/*     */         } 
/*     */ 
/*     */         
/* 158 */         if (this.a > 0 || this.tracker instanceof EntityArrow) {
/* 159 */           if (l1 >= -32768L && l1 < 32768L && i2 >= -32768L && i2 < 32768L && j2 >= -32768L && j2 < 32768L && this.v <= 400 && !this.x && this.y == this.tracker.onGround) {
/* 160 */             if ((!flag1 || !flag2) && !(this.tracker instanceof EntityArrow)) {
/* 161 */               if (flag1) {
/* 162 */                 object = new PacketPlayOutEntity.PacketPlayOutRelEntityMove(this.tracker.getId(), l1, i2, j2, this.tracker.onGround);
/* 163 */               } else if (flag2) {
/* 164 */                 object = new PacketPlayOutEntity.PacketPlayOutEntityLook(this.tracker.getId(), (byte)j1, (byte)k1, this.tracker.onGround);
/*     */               } 
/*     */             } else {
/* 167 */               object = new PacketPlayOutEntity.PacketPlayOutRelEntityMoveLook(this.tracker.getId(), l1, i2, j2, (byte)j1, (byte)k1, this.tracker.onGround);
/*     */             } 
/*     */           } else {
/* 170 */             this.y = this.tracker.onGround;
/* 171 */             this.v = 0;
/*     */             
/* 173 */             if (this.tracker instanceof EntityPlayer) {
/* 174 */               scanPlayers(new ArrayList<>((Collection)this.trackedPlayers));
/*     */             }
/*     */             
/* 177 */             c();
/* 178 */             object = new PacketPlayOutEntityTeleport(this.tracker);
/*     */           } 
/*     */         }
/*     */         
/* 182 */         boolean flag3 = this.u;
/*     */         
/* 184 */         if (this.tracker instanceof EntityLiving && ((EntityLiving)this.tracker).cP()) {
/* 185 */           flag3 = true;
/*     */         }
/*     */         
/* 188 */         if (flag3 && this.a > 0) {
/* 189 */           double d0 = this.tracker.motX - this.n;
/* 190 */           double d1 = this.tracker.motY - this.o;
/* 191 */           double d2 = this.tracker.motZ - this.p;
/*     */           
/* 193 */           double d4 = d0 * d0 + d1 * d1 + d2 * d2;
/*     */           
/* 195 */           if (d4 > 4.0E-4D || (d4 > 0.0D && this.tracker.motX == 0.0D && this.tracker.motY == 0.0D && this.tracker.motZ == 0.0D)) {
/* 196 */             this.n = this.tracker.motX;
/* 197 */             this.o = this.tracker.motY;
/* 198 */             this.p = this.tracker.motZ;
/* 199 */             broadcast(new PacketPlayOutEntityVelocity(this.tracker.getId(), this.n, this.o, this.p));
/*     */           } 
/*     */         } 
/*     */         
/* 203 */         if (object != null) {
/* 204 */           broadcast((Packet)object);
/*     */         }
/*     */         
/* 207 */         d();
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
/* 221 */         this.x = false;
/*     */       } 
/*     */       
/* 224 */       int i = MathHelper.d(this.tracker.getHeadRotation() * 256.0F / 360.0F);
/* 225 */       if (Math.abs(i - this.headYaw) >= 1) {
/* 226 */         broadcast(new PacketPlayOutEntityHeadRotation(this.tracker, (byte)i));
/* 227 */         this.headYaw = i;
/*     */       } 
/*     */       
/* 230 */       this.tracker.impulse = false;
/*     */     } 
/*     */     
/* 233 */     this.a++;
/* 234 */     if (this.tracker.velocityChanged) {
/*     */       
/* 236 */       boolean cancelled = false;
/*     */       
/* 238 */       if (this.tracker instanceof EntityPlayer) {
/* 239 */         Player player = (Player)this.tracker.getBukkitEntity();
/* 240 */         Vector velocity = player.getVelocity();
/*     */         
/* 242 */         PlayerVelocityEvent event = new PlayerVelocityEvent(player, velocity.clone());
/* 243 */         this.tracker.world.getServer().getPluginManager().callEvent((Event)event);
/*     */         
/* 245 */         if (event.isCancelled()) {
/* 246 */           cancelled = true;
/* 247 */         } else if (!velocity.equals(event.getVelocity())) {
/* 248 */           player.setVelocity(event.getVelocity());
/*     */         } 
/*     */       } 
/*     */       
/* 252 */       if (!cancelled) {
/* 253 */         broadcastIncludingSelf(new PacketPlayOutEntityVelocity(this.tracker));
/*     */       }
/*     */       
/* 256 */       this.tracker.velocityChanged = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void d() {
/* 262 */     DataWatcher datawatcher = this.tracker.getDataWatcher();
/*     */     
/* 264 */     if (datawatcher.a()) {
/* 265 */       broadcastIncludingSelf(new PacketPlayOutEntityMetadata(this.tracker.getId(), datawatcher, false));
/*     */     }
/*     */     
/* 268 */     if (this.tracker instanceof EntityLiving) {
/* 269 */       AttributeMapServer attributemapserver = (AttributeMapServer)((EntityLiving)this.tracker).getAttributeMap();
/* 270 */       Set<AttributeInstance> set = attributemapserver.getAttributes();
/*     */       
/* 272 */       if (!set.isEmpty()) {
/*     */         
/* 274 */         if (this.tracker instanceof EntityPlayer) {
/* 275 */           ((EntityPlayer)this.tracker).getBukkitEntity().injectScaledMaxHealth(set, false);
/*     */         }
/*     */         
/* 278 */         broadcastIncludingSelf(new PacketPlayOutUpdateAttributes(this.tracker.getId(), set));
/*     */       } 
/*     */       
/* 281 */       set.clear();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void broadcast(Packet<?> packet) {
/* 287 */     Iterator<EntityPlayer> iterator = this.trackedPlayers.iterator();
/*     */     
/* 289 */     while (iterator.hasNext()) {
/* 290 */       EntityPlayer entityplayer = iterator.next();
/*     */       
/* 292 */       entityplayer.playerConnection.sendPacket(packet);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void broadcastIncludingSelf(Packet<?> packet) {
/* 298 */     broadcast(packet);
/* 299 */     if (this.tracker instanceof EntityPlayer) {
/* 300 */       ((EntityPlayer)this.tracker).playerConnection.sendPacket(packet);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void a() {
/* 306 */     Iterator<EntityPlayer> iterator = this.trackedPlayers.iterator();
/*     */     
/* 308 */     while (iterator.hasNext()) {
/* 309 */       EntityPlayer entityplayer = iterator.next();
/*     */       
/* 311 */       this.tracker.c(entityplayer);
/* 312 */       entityplayer.c(this.tracker);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(EntityPlayer entityplayer) {
/* 318 */     if (this.trackedPlayers.contains(entityplayer)) {
/* 319 */       this.tracker.c(entityplayer);
/* 320 */       entityplayer.c(this.tracker);
/* 321 */       this.trackedPlayers.remove(entityplayer);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void updatePlayer(EntityPlayer entityplayer) {
/* 327 */     AsyncCatcher.catchOp("player tracker update");
/* 328 */     if (entityplayer != this.tracker) {
/* 329 */       if (c(entityplayer)) {
/* 330 */         if (!this.trackedPlayers.contains(entityplayer) && (e(entityplayer) || this.tracker.attachedToPlayer)) {
/*     */           
/* 332 */           if (this.tracker instanceof EntityPlayer) {
/* 333 */             CraftPlayer craftPlayer = ((EntityPlayer)this.tracker).getBukkitEntity();
/* 334 */             if (!entityplayer.getBukkitEntity().canSee((Player)craftPlayer)) {
/*     */               return;
/*     */             }
/*     */           } 
/*     */           
/* 339 */           entityplayer.removeQueue.remove(Integer.valueOf(this.tracker.getId()));
/*     */           
/* 341 */           this.trackedPlayers.add(entityplayer);
/* 342 */           Packet<?> packet = e();
/*     */           
/* 344 */           entityplayer.playerConnection.sendPacket(packet);
/* 345 */           if (!this.tracker.getDataWatcher().d()) {
/* 346 */             entityplayer.playerConnection.sendPacket(new PacketPlayOutEntityMetadata(this.tracker.getId(), this.tracker.getDataWatcher(), true));
/*     */           }
/*     */           
/* 349 */           boolean flag = this.u;
/*     */           
/* 351 */           if (this.tracker instanceof EntityLiving) {
/* 352 */             AttributeMapServer attributemapserver = (AttributeMapServer)((EntityLiving)this.tracker).getAttributeMap();
/* 353 */             Collection<AttributeInstance> collection = attributemapserver.c();
/*     */ 
/*     */             
/* 356 */             if (this.tracker.getId() == entityplayer.getId()) {
/* 357 */               ((EntityPlayer)this.tracker).getBukkitEntity().injectScaledMaxHealth(collection, false);
/*     */             }
/*     */ 
/*     */             
/* 361 */             if (!collection.isEmpty()) {
/* 362 */               entityplayer.playerConnection.sendPacket(new PacketPlayOutUpdateAttributes(this.tracker.getId(), collection));
/*     */             }
/*     */             
/* 365 */             if (((EntityLiving)this.tracker).cP()) {
/* 366 */               flag = true;
/*     */             }
/*     */           } 
/*     */           
/* 370 */           this.n = this.tracker.motX;
/* 371 */           this.o = this.tracker.motY;
/* 372 */           this.p = this.tracker.motZ;
/* 373 */           if (flag && !(packet instanceof PacketPlayOutSpawnEntityLiving)) {
/* 374 */             entityplayer.playerConnection.sendPacket(new PacketPlayOutEntityVelocity(this.tracker.getId(), this.tracker.motX, this.tracker.motY, this.tracker.motZ));
/*     */           }
/*     */           
/* 377 */           if (this.tracker instanceof EntityLiving) {
/* 378 */             EnumItemSlot[] aenumitemslot = EnumItemSlot.values();
/* 379 */             int i = aenumitemslot.length;
/*     */             
/* 381 */             for (int j = 0; j < i; j++) {
/* 382 */               EnumItemSlot enumitemslot = aenumitemslot[j];
/* 383 */               ItemStack itemstack = ((EntityLiving)this.tracker).getEquipment(enumitemslot);
/*     */               
/* 385 */               if (!itemstack.isEmpty()) {
/* 386 */                 entityplayer.playerConnection.sendPacket(new PacketPlayOutEntityEquipment(this.tracker.getId(), enumitemslot, itemstack));
/*     */               }
/*     */             } 
/*     */           } 
/*     */           
/* 391 */           if (this.tracker instanceof EntityHuman) {
/* 392 */             EntityHuman entityhuman = (EntityHuman)this.tracker;
/*     */             
/* 394 */             if (entityhuman.isSleeping()) {
/* 395 */               entityplayer.playerConnection.sendPacket(new PacketPlayOutBed(entityhuman, new BlockPosition(this.tracker)));
/*     */             }
/*     */           } 
/*     */ 
/*     */           
/* 400 */           this.headYaw = MathHelper.d(this.tracker.getHeadRotation() * 256.0F / 360.0F);
/* 401 */           broadcast(new PacketPlayOutEntityHeadRotation(this.tracker, (byte)this.headYaw));
/*     */ 
/*     */           
/* 404 */           if (this.tracker instanceof EntityLiving) {
/* 405 */             EntityLiving entityliving = (EntityLiving)this.tracker;
/* 406 */             Iterator<MobEffect> iterator = entityliving.getEffects().iterator();
/*     */             
/* 408 */             while (iterator.hasNext()) {
/* 409 */               MobEffect mobeffect = iterator.next();
/*     */               
/* 411 */               entityplayer.playerConnection.sendPacket(new PacketPlayOutEntityEffect(this.tracker.getId(), mobeffect));
/*     */             } 
/*     */           } 
/*     */           
/* 415 */           if (!this.tracker.bF().isEmpty()) {
/* 416 */             entityplayer.playerConnection.sendPacket(new PacketPlayOutMount(this.tracker));
/*     */           }
/*     */           
/* 419 */           if (this.tracker.isPassenger()) {
/* 420 */             entityplayer.playerConnection.sendPacket(new PacketPlayOutMount(this.tracker.bJ()));
/*     */           }
/*     */           
/* 423 */           this.tracker.b(entityplayer);
/* 424 */           entityplayer.d(this.tracker);
/*     */         } 
/* 426 */       } else if (this.trackedPlayers.contains(entityplayer)) {
/* 427 */         this.trackedPlayers.remove(entityplayer);
/* 428 */         this.tracker.c(entityplayer);
/* 429 */         entityplayer.c(this.tracker);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean c(EntityPlayer entityplayer) {
/* 436 */     double d0 = entityplayer.locX - this.xLoc / 4096.0D;
/* 437 */     double d1 = entityplayer.locZ - this.zLoc / 4096.0D;
/* 438 */     int i = Math.min(this.e, this.f);
/*     */     
/* 440 */     return (d0 >= -i && d0 <= i && d1 >= -i && d1 <= i && this.tracker.a(entityplayer));
/*     */   }
/*     */   
/*     */   private boolean e(EntityPlayer entityplayer) {
/* 444 */     return entityplayer.x().getPlayerChunkMap().a(entityplayer, this.tracker.ab, this.tracker.ad);
/*     */   }
/*     */   
/*     */   public void scanPlayers(List<EntityHuman> list) {
/* 448 */     for (int i = 0; i < list.size(); i++) {
/* 449 */       updatePlayer((EntityPlayer)list.get(i));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private Packet<?> e() {
/* 455 */     if (this.tracker.dead)
/*     */     {
/*     */       
/* 458 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 462 */     if (this.tracker instanceof EntityPlayer)
/* 463 */       return new PacketPlayOutNamedEntitySpawn((EntityHuman)this.tracker); 
/* 464 */     if (this.tracker instanceof IAnimal) {
/* 465 */       this.headYaw = MathHelper.d(this.tracker.getHeadRotation() * 256.0F / 360.0F);
/* 466 */       return new PacketPlayOutSpawnEntityLiving((EntityLiving)this.tracker);
/* 467 */     }  if (this.tracker instanceof EntityPainting)
/* 468 */       return new PacketPlayOutSpawnEntityPainting((EntityPainting)this.tracker); 
/* 469 */     if (this.tracker instanceof EntityItem)
/* 470 */       return new PacketPlayOutSpawnEntity(this.tracker, 2, 1); 
/* 471 */     if (this.tracker instanceof EntityMinecartAbstract) {
/* 472 */       EntityMinecartAbstract entityminecartabstract = (EntityMinecartAbstract)this.tracker;
/*     */       
/* 474 */       return new PacketPlayOutSpawnEntity(this.tracker, 10, entityminecartabstract.v().a());
/* 475 */     }  if (this.tracker instanceof EntityBoat)
/* 476 */       return new PacketPlayOutSpawnEntity(this.tracker, 1); 
/* 477 */     if (this.tracker instanceof EntityExperienceOrb)
/* 478 */       return new PacketPlayOutSpawnEntityExperienceOrb((EntityExperienceOrb)this.tracker); 
/* 479 */     if (this.tracker instanceof EntityFishingHook) {
/* 480 */       EntityHuman entityhuman = ((EntityFishingHook)this.tracker).l();
/*     */       
/* 482 */       return new PacketPlayOutSpawnEntity(this.tracker, 90, (entityhuman == null) ? this.tracker.getId() : entityhuman.getId());
/*     */     } 
/*     */ 
/*     */     
/* 486 */     if (this.tracker instanceof EntitySpectralArrow) {
/* 487 */       Entity entity = ((EntitySpectralArrow)this.tracker).shooter;
/* 488 */       return new PacketPlayOutSpawnEntity(this.tracker, 91, 1 + ((entity == null) ? this.tracker.getId() : entity.getId()));
/* 489 */     }  if (this.tracker instanceof EntityTippedArrow) {
/* 490 */       Entity entity = ((EntityArrow)this.tracker).shooter;
/* 491 */       return new PacketPlayOutSpawnEntity(this.tracker, 60, 1 + ((entity == null) ? this.tracker.getId() : entity.getId()));
/* 492 */     }  if (this.tracker instanceof EntitySnowball)
/* 493 */       return new PacketPlayOutSpawnEntity(this.tracker, 61); 
/* 494 */     if (this.tracker instanceof EntityLlamaSpit)
/* 495 */       return new PacketPlayOutSpawnEntity(this.tracker, 68); 
/* 496 */     if (this.tracker instanceof EntityPotion)
/* 497 */       return new PacketPlayOutSpawnEntity(this.tracker, 73); 
/* 498 */     if (this.tracker instanceof EntityThrownExpBottle)
/* 499 */       return new PacketPlayOutSpawnEntity(this.tracker, 75); 
/* 500 */     if (this.tracker instanceof EntityEnderPearl)
/* 501 */       return new PacketPlayOutSpawnEntity(this.tracker, 65); 
/* 502 */     if (this.tracker instanceof EntityEnderSignal)
/* 503 */       return new PacketPlayOutSpawnEntity(this.tracker, 72); 
/* 504 */     if (this.tracker instanceof EntityFireworks)
/* 505 */       return new PacketPlayOutSpawnEntity(this.tracker, 76); 
/* 506 */     if (this.tracker instanceof EntityFireball) {
/* 507 */       EntityFireball entityfireball = (EntityFireball)this.tracker;
/* 508 */       PacketPlayOutSpawnEntity packetplayoutspawnentity = null;
/* 509 */       byte b0 = 63;
/*     */       
/* 511 */       if (this.tracker instanceof EntitySmallFireball) {
/* 512 */         b0 = 64;
/* 513 */       } else if (this.tracker instanceof EntityDragonFireball) {
/* 514 */         b0 = 93;
/* 515 */       } else if (this.tracker instanceof EntityWitherSkull) {
/* 516 */         b0 = 66;
/*     */       } 
/*     */       
/* 519 */       if (entityfireball.shooter != null) {
/* 520 */         packetplayoutspawnentity = new PacketPlayOutSpawnEntity(this.tracker, b0, ((EntityFireball)this.tracker).shooter.getId());
/*     */       } else {
/* 522 */         packetplayoutspawnentity = new PacketPlayOutSpawnEntity(this.tracker, b0, 0);
/*     */       } 
/*     */       
/* 525 */       packetplayoutspawnentity.a((int)(entityfireball.dirX * 8000.0D));
/* 526 */       packetplayoutspawnentity.b((int)(entityfireball.dirY * 8000.0D));
/* 527 */       packetplayoutspawnentity.c((int)(entityfireball.dirZ * 8000.0D));
/* 528 */       return packetplayoutspawnentity;
/* 529 */     }  if (this.tracker instanceof EntityShulkerBullet) {
/* 530 */       PacketPlayOutSpawnEntity packetplayoutspawnentity1 = new PacketPlayOutSpawnEntity(this.tracker, 67, 0);
/*     */       
/* 532 */       packetplayoutspawnentity1.a((int)(this.tracker.motX * 8000.0D));
/* 533 */       packetplayoutspawnentity1.b((int)(this.tracker.motY * 8000.0D));
/* 534 */       packetplayoutspawnentity1.c((int)(this.tracker.motZ * 8000.0D));
/* 535 */       return packetplayoutspawnentity1;
/* 536 */     }  if (this.tracker instanceof EntityEgg)
/* 537 */       return new PacketPlayOutSpawnEntity(this.tracker, 62); 
/* 538 */     if (this.tracker instanceof EntityEvokerFangs)
/* 539 */       return new PacketPlayOutSpawnEntity(this.tracker, 79); 
/* 540 */     if (this.tracker instanceof EntityTNTPrimed)
/* 541 */       return new PacketPlayOutSpawnEntity(this.tracker, 50); 
/* 542 */     if (this.tracker instanceof EntityEnderCrystal)
/* 543 */       return new PacketPlayOutSpawnEntity(this.tracker, 51); 
/* 544 */     if (this.tracker instanceof EntityFallingBlock) {
/* 545 */       EntityFallingBlock entityfallingblock = (EntityFallingBlock)this.tracker;
/*     */       
/* 547 */       return new PacketPlayOutSpawnEntity(this.tracker, 70, Block.getCombinedId(entityfallingblock.getBlock()));
/* 548 */     }  if (this.tracker instanceof EntityArmorStand)
/* 549 */       return new PacketPlayOutSpawnEntity(this.tracker, 78); 
/* 550 */     if (this.tracker instanceof EntityItemFrame) {
/* 551 */       EntityItemFrame entityitemframe = (EntityItemFrame)this.tracker;
/*     */       
/* 553 */       return new PacketPlayOutSpawnEntity(this.tracker, 71, entityitemframe.direction.get2DRotationValue(), entityitemframe.getBlockPosition());
/* 554 */     }  if (this.tracker instanceof EntityLeash) {
/* 555 */       EntityLeash entityleash = (EntityLeash)this.tracker;
/*     */       
/* 557 */       return new PacketPlayOutSpawnEntity(this.tracker, 77, 0, entityleash.getBlockPosition());
/* 558 */     }  if (this.tracker instanceof EntityAreaEffectCloud) {
/* 559 */       return new PacketPlayOutSpawnEntity(this.tracker, 3);
/*     */     }
/* 561 */     throw new IllegalArgumentException("Don't know how to add " + this.tracker.getClass() + "!");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear(EntityPlayer entityplayer) {
/* 567 */     AsyncCatcher.catchOp("player tracker clear");
/* 568 */     if (this.trackedPlayers.contains(entityplayer)) {
/* 569 */       this.trackedPlayers.remove(entityplayer);
/* 570 */       this.tracker.c(entityplayer);
/* 571 */       entityplayer.c(this.tracker);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Entity b() {
/* 577 */     return this.tracker;
/*     */   }
/*     */   
/*     */   public void a(int i) {
/* 581 */     this.f = i;
/*     */   }
/*     */   
/*     */   public void c() {
/* 585 */     this.isMoving = false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityTrackerEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */