/*      */ package net.minecraft.server.v1_12_R1;
/*      */ import com.google.common.base.Preconditions;
/*      */ import com.google.common.collect.Lists;
/*      */ import com.google.common.collect.Sets;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collection;
/*      */ import java.util.Collections;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Random;
/*      */ import java.util.Set;
/*      */ import java.util.UUID;
/*      */ import javax.annotation.Nullable;
/*      */ import org.apache.logging.log4j.LogManager;
/*      */ import org.apache.logging.log4j.Logger;
/*      */ import org.bukkit.Bukkit;
/*      */ import org.bukkit.Location;
/*      */ import org.bukkit.Material;
/*      */ import org.bukkit.Server;
/*      */ import org.bukkit.TravelAgent;
/*      */ import org.bukkit.World;
/*      */ import org.bukkit.block.Block;
/*      */ import org.bukkit.block.BlockFace;
/*      */ import org.bukkit.craftbukkit.v1_12_R1.CraftServer;
/*      */ import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
/*      */ import org.bukkit.craftbukkit.v1_12_R1.SpigotTimings;
/*      */ import org.bukkit.craftbukkit.v1_12_R1.entity.CraftEntity;
/*      */ import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
/*      */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*      */ import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
/*      */ import org.bukkit.entity.Vehicle;
/*      */ import org.bukkit.event.Event;
/*      */ import org.bukkit.event.entity.EntityAirChangeEvent;
/*      */ import org.bukkit.event.entity.EntityCombustByBlockEvent;
/*      */ import org.bukkit.event.entity.EntityCombustByEntityEvent;
/*      */ import org.bukkit.event.entity.EntityPortalEvent;
/*      */ import org.bukkit.event.hanging.HangingBreakByEntityEvent;
/*      */ import org.bukkit.event.vehicle.VehicleBlockCollisionEvent;
/*      */ import org.bukkit.event.vehicle.VehicleEnterEvent;
/*      */ import org.bukkit.event.vehicle.VehicleExitEvent;
/*      */ import org.bukkit.plugin.PluginManager;
/*      */ import org.bukkit.projectiles.ProjectileSource;
/*      */ import org.spigotmc.ActivationRange;
/*      */ import org.spigotmc.event.entity.EntityMountEvent;
/*      */ 
/*      */ public abstract class Entity implements ICommandListener {
/*      */   static boolean isLevelAtLeast(NBTTagCompound tag, int level) {
/*   49 */     return (tag.hasKey("Bukkit.updateLevel") && tag.getInt("Bukkit.updateLevel") >= level);
/*      */   }
/*      */   private static final int CURRENT_LEVEL = 2;
/*      */   protected CraftEntity bukkitEntity;
/*      */   
/*      */   public CraftEntity getBukkitEntity() {
/*   55 */     if (this.bukkitEntity == null) {
/*   56 */       this.bukkitEntity = CraftEntity.getEntity(this.world.getServer(), this);
/*      */     }
/*   58 */     return this.bukkitEntity;
/*      */   }
/*      */ 
/*      */   
/*   62 */   private static final Logger a = LogManager.getLogger();
/*   63 */   private static final List<ItemStack> b = Collections.emptyList();
/*   64 */   private static final AxisAlignedBB c = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
/*   65 */   private static double f = 1.0D;
/*      */   private static int entityCount;
/*      */   private int id;
/*      */   public boolean i;
/*      */   public final List<Entity> passengers;
/*      */   protected int j;
/*      */   private Entity au;
/*      */   public boolean attachedToPlayer;
/*      */   public World world;
/*      */   public double lastX;
/*      */   public double lastY;
/*      */   public double lastZ;
/*      */   public double locX;
/*      */   public double locY;
/*      */   public double locZ;
/*      */   public double motX;
/*      */   public double motY;
/*      */   public double motZ;
/*      */   public float yaw;
/*      */   public float pitch;
/*      */   public float lastYaw;
/*      */   public float lastPitch;
/*      */   private AxisAlignedBB boundingBox;
/*      */   public boolean onGround;
/*      */   public boolean positionChanged;
/*      */   public boolean B;
/*      */   public boolean C;
/*      */   public boolean velocityChanged;
/*      */   protected boolean E;
/*      */   private boolean aw;
/*      */   public boolean dead;
/*      */   public float width;
/*      */   public float length;
/*      */   public float I;
/*      */   public float J;
/*      */   public float K;
/*      */   public float fallDistance;
/*      */   private int ax;
/*      */   private float ay;
/*      */   public double M;
/*      */   public double N;
/*      */   public double O;
/*      */   public float P;
/*      */   public boolean noclip;
/*      */   public float R;
/*      */   protected Random random;
/*      */   public int ticksLived;
/*      */   public int fireTicks;
/*      */   public boolean inWater;
/*      */   public int noDamageTicks;
/*      */   protected boolean justCreated;
/*      */   protected boolean fireProof;
/*      */   protected DataWatcher datawatcher;
/*  118 */   protected static final DataWatcherObject<Byte> Z = DataWatcher.a(Entity.class, DataWatcherRegistry.a);
/*  119 */   private static final DataWatcherObject<Integer> aA = DataWatcher.a(Entity.class, DataWatcherRegistry.b);
/*  120 */   private static final DataWatcherObject<String> aB = DataWatcher.a(Entity.class, DataWatcherRegistry.d);
/*  121 */   private static final DataWatcherObject<Boolean> aC = DataWatcher.a(Entity.class, DataWatcherRegistry.h);
/*  122 */   private static final DataWatcherObject<Boolean> aD = DataWatcher.a(Entity.class, DataWatcherRegistry.h);
/*  123 */   private static final DataWatcherObject<Boolean> aE = DataWatcher.a(Entity.class, DataWatcherRegistry.h);
/*      */   public boolean aa;
/*      */   public int ab;
/*      */   public int ac;
/*      */   public int ad;
/*      */   public boolean ah;
/*      */   public boolean impulse;
/*      */   public int portalCooldown;
/*      */   protected boolean ak;
/*      */   protected int al;
/*      */   public int dimension;
/*      */   protected BlockPosition an;
/*      */   protected Vec3D ao;
/*      */   protected EnumDirection ap;
/*      */   private boolean invulnerable;
/*      */   protected UUID uniqueID;
/*      */   protected String ar;
/*      */   private final CommandObjectiveExecutor aG;
/*      */   public boolean glowing;
/*      */   private final Set<String> aH;
/*      */   private boolean aI;
/*      */   private final double[] aJ;
/*      */   private long aK;
/*      */   public boolean valid;
/*      */   public ProjectileSource projectileSource;
/*      */   public boolean forceExplosionKnockback;
/*  149 */   public CustomTimingsHandler tickTimer = SpigotTimings.getEntityTimings(this);
/*      */   
/*  151 */   public final byte activationType = ActivationRange.initializeEntityActivationType(this);
/*      */   public final boolean defaultActivationState;
/*  153 */   public long activatedTick = -2147483648L;
/*      */   public boolean fromMobSpawner;
/*      */   
/*      */   public void inactiveTick() {}
/*      */   
/*      */   public Entity(World world) {
/*  159 */     this.id = entityCount++;
/*  160 */     this.passengers = Lists.newArrayList();
/*  161 */     this.boundingBox = c;
/*  162 */     this.width = 0.6F;
/*  163 */     this.length = 1.8F;
/*  164 */     this.ax = 1;
/*  165 */     this.ay = 1.0F;
/*  166 */     this.random = new Random();
/*  167 */     this.fireTicks = -getMaxFireTicks();
/*  168 */     this.justCreated = true;
/*  169 */     this.uniqueID = MathHelper.a(this.random);
/*  170 */     this.ar = this.uniqueID.toString();
/*  171 */     this.aG = new CommandObjectiveExecutor();
/*  172 */     this.aH = Sets.newHashSet();
/*  173 */     this.aJ = new double[] { 0.0D, 0.0D, 0.0D };
/*  174 */     this.world = world;
/*  175 */     setPosition(0.0D, 0.0D, 0.0D);
/*  176 */     if (world != null) {
/*  177 */       this.dimension = world.worldProvider.getDimensionManager().getDimensionID();
/*      */       
/*  179 */       this.defaultActivationState = ActivationRange.initializeEntityActivationState(this, world.spigotConfig);
/*      */     } else {
/*  181 */       this.defaultActivationState = false;
/*      */     } 
/*      */ 
/*      */     
/*  185 */     this.datawatcher = new DataWatcher(this);
/*  186 */     this.datawatcher.register(Z, Byte.valueOf((byte)0));
/*  187 */     this.datawatcher.register(aA, Integer.valueOf(300));
/*  188 */     this.datawatcher.register(aC, Boolean.valueOf(false));
/*  189 */     this.datawatcher.register(aB, "");
/*  190 */     this.datawatcher.register(aD, Boolean.valueOf(false));
/*  191 */     this.datawatcher.register(aE, Boolean.valueOf(false));
/*  192 */     i();
/*      */   }
/*      */   
/*      */   public int getId() {
/*  196 */     return this.id;
/*      */   }
/*      */   
/*      */   public void h(int i) {
/*  200 */     this.id = i;
/*      */   }
/*      */   
/*      */   public Set<String> getScoreboardTags() {
/*  204 */     return this.aH;
/*      */   }
/*      */   
/*      */   public boolean addScoreboardTag(String s) {
/*  208 */     if (this.aH.size() >= 1024) {
/*  209 */       return false;
/*      */     }
/*  211 */     this.aH.add(s);
/*  212 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean removeScoreboardTag(String s) {
/*  217 */     return this.aH.remove(s);
/*      */   }
/*      */   
/*      */   public void killEntity() {
/*  221 */     die();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public DataWatcher getDataWatcher() {
/*  227 */     return this.datawatcher;
/*      */   }
/*      */   
/*      */   public boolean equals(Object object) {
/*  231 */     return (object instanceof Entity) ? ((((Entity)object).id == this.id)) : false;
/*      */   }
/*      */   
/*      */   public int hashCode() {
/*  235 */     return this.id;
/*      */   }
/*      */   
/*      */   public void die() {
/*  239 */     this.dead = true;
/*      */   }
/*      */   
/*      */   public void b(boolean flag) {}
/*      */   
/*      */   public void setSize(float f, float f1) {
/*  245 */     if (f != this.width || f1 != this.length) {
/*  246 */       float f2 = this.width;
/*      */       
/*  248 */       this.width = f;
/*  249 */       this.length = f1;
/*  250 */       if (this.width < f2) {
/*  251 */         double d0 = f / 2.0D;
/*      */         
/*  253 */         a(new AxisAlignedBB(this.locX - d0, this.locY, this.locZ - d0, this.locX + d0, this.locY + this.length, this.locZ + d0));
/*      */         
/*      */         return;
/*      */       } 
/*  257 */       AxisAlignedBB axisalignedbb = getBoundingBox();
/*      */       
/*  259 */       a(new AxisAlignedBB(axisalignedbb.a, axisalignedbb.b, axisalignedbb.c, axisalignedbb.a + this.width, axisalignedbb.b + this.length, axisalignedbb.c + this.width));
/*  260 */       if (this.width > f2 && !this.justCreated && !this.world.isClientSide) {
/*  261 */         move(EnumMoveType.SELF, (f2 - this.width), 0.0D, (f2 - this.width));
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setYawPitch(float f, float f1) {
/*  269 */     if (Float.isNaN(f)) {
/*  270 */       f = 0.0F;
/*      */     }
/*      */     
/*  273 */     if (f == Float.POSITIVE_INFINITY || f == Float.NEGATIVE_INFINITY) {
/*  274 */       if (this instanceof EntityPlayer) {
/*  275 */         this.world.getServer().getLogger().warning(String.valueOf(getName()) + " was caught trying to crash the server with an invalid yaw");
/*  276 */         ((CraftPlayer)getBukkitEntity()).kickPlayer("Infinite yaw (Hacking?)");
/*      */       } 
/*  278 */       f = 0.0F;
/*      */     } 
/*      */ 
/*      */     
/*  282 */     if (Float.isNaN(f1)) {
/*  283 */       f1 = 0.0F;
/*      */     }
/*      */     
/*  286 */     if (f1 == Float.POSITIVE_INFINITY || f1 == Float.NEGATIVE_INFINITY) {
/*  287 */       if (this instanceof EntityPlayer) {
/*  288 */         this.world.getServer().getLogger().warning(String.valueOf(getName()) + " was caught trying to crash the server with an invalid pitch");
/*  289 */         ((CraftPlayer)getBukkitEntity()).kickPlayer("Infinite pitch (Hacking?)");
/*      */       } 
/*  291 */       f1 = 0.0F;
/*      */     } 
/*      */ 
/*      */     
/*  295 */     this.yaw = f % 360.0F;
/*  296 */     this.pitch = f1 % 360.0F;
/*      */   }
/*      */   
/*      */   public void setPosition(double d0, double d1, double d2) {
/*  300 */     this.locX = d0;
/*  301 */     this.locY = d1;
/*  302 */     this.locZ = d2;
/*  303 */     float f = this.width / 2.0F;
/*  304 */     float f1 = this.length;
/*      */     
/*  306 */     a(new AxisAlignedBB(d0 - f, d1, d2 - f, d0 + f, d1 + f1, d2 + f));
/*      */   }
/*      */   
/*      */   public void B_() {
/*  310 */     if (!this.world.isClientSide) {
/*  311 */       setFlag(6, aW());
/*      */     }
/*      */     
/*  314 */     Y();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void postTick() {
/*  320 */     if (!this.world.isClientSide && this.world instanceof WorldServer) {
/*  321 */       this.world.methodProfiler.a("portal");
/*  322 */       if (this.ak) {
/*  323 */         MinecraftServer minecraftserver = this.world.getMinecraftServer();
/*      */ 
/*      */ 
/*      */         
/*  327 */         int i = Z();
/*      */         
/*  329 */         if (!isPassenger() && this.al++ >= i) {
/*  330 */           byte b0; this.al = i;
/*  331 */           this.portalCooldown = aM();
/*      */ 
/*      */           
/*  334 */           if (this.world.worldProvider.getDimensionManager().getDimensionID() == -1) {
/*  335 */             b0 = 0;
/*      */           } else {
/*  337 */             b0 = -1;
/*      */           } 
/*      */           
/*  340 */           b(b0);
/*      */         } 
/*      */ 
/*      */         
/*  344 */         this.ak = false;
/*      */       } else {
/*      */         
/*  347 */         if (this.al > 0) {
/*  348 */           this.al -= 4;
/*      */         }
/*      */         
/*  351 */         if (this.al < 0) {
/*  352 */           this.al = 0;
/*      */         }
/*      */       } 
/*      */       
/*  356 */       I();
/*  357 */       this.world.methodProfiler.b();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void Y() {
/*  363 */     this.world.methodProfiler.a("entityBaseTick");
/*  364 */     if (isPassenger() && (bJ()).dead) {
/*  365 */       stopRiding();
/*      */     }
/*      */     
/*  368 */     if (this.j > 0) {
/*  369 */       this.j--;
/*      */     }
/*      */     
/*  372 */     this.I = this.J;
/*  373 */     this.lastX = this.locX;
/*  374 */     this.lastY = this.locY;
/*  375 */     this.lastZ = this.locZ;
/*  376 */     this.lastPitch = this.pitch;
/*  377 */     this.lastYaw = this.yaw;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  421 */     as();
/*  422 */     aq();
/*  423 */     if (this.world.isClientSide) {
/*  424 */       extinguish();
/*  425 */     } else if (this.fireTicks > 0) {
/*  426 */       if (this.fireProof) {
/*  427 */         this.fireTicks -= 4;
/*  428 */         if (this.fireTicks < 0) {
/*  429 */           extinguish();
/*      */         }
/*      */       } else {
/*  432 */         if (this.fireTicks % 20 == 0) {
/*  433 */           damageEntity(DamageSource.BURN, 1.0F);
/*      */         }
/*      */         
/*  436 */         this.fireTicks--;
/*      */       } 
/*      */     } 
/*      */     
/*  440 */     if (au()) {
/*  441 */       burnFromLava();
/*  442 */       this.fallDistance *= 0.5F;
/*      */     } 
/*      */     
/*  445 */     if (this.locY < -64.0D) {
/*  446 */       ac();
/*      */     }
/*      */     
/*  449 */     if (!this.world.isClientSide) {
/*  450 */       setFlag(0, (this.fireTicks > 0));
/*      */     }
/*      */     
/*  453 */     this.justCreated = false;
/*  454 */     this.world.methodProfiler.b();
/*      */   }
/*      */   
/*      */   protected void I() {
/*  458 */     if (this.portalCooldown > 0) {
/*  459 */       this.portalCooldown--;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public int Z() {
/*  465 */     return 1;
/*      */   }
/*      */   
/*      */   protected void burnFromLava() {
/*  469 */     if (!this.fireProof) {
/*  470 */       damageEntity(DamageSource.LAVA, 4.0F);
/*      */ 
/*      */       
/*  473 */       if (this instanceof EntityLiving) {
/*  474 */         if (this.fireTicks <= 0) {
/*      */ 
/*      */           
/*  477 */           Block damager = null;
/*  478 */           CraftEntity craftEntity = getBukkitEntity();
/*  479 */           EntityCombustByBlockEvent entityCombustByBlockEvent = new EntityCombustByBlockEvent(damager, (org.bukkit.entity.Entity)craftEntity, 15);
/*  480 */           this.world.getServer().getPluginManager().callEvent((Event)entityCombustByBlockEvent);
/*      */           
/*  482 */           if (!entityCombustByBlockEvent.isCancelled()) {
/*  483 */             setOnFire(entityCombustByBlockEvent.getDuration());
/*      */           }
/*      */         } else {
/*      */           
/*  487 */           setOnFire(15);
/*      */         } 
/*      */         
/*      */         return;
/*      */       } 
/*  492 */       setOnFire(15);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void setOnFire(int i) {
/*  497 */     int j = i * 20;
/*      */     
/*  499 */     if (this instanceof EntityLiving) {
/*  500 */       j = EnchantmentProtection.a((EntityLiving)this, j);
/*      */     }
/*      */     
/*  503 */     if (this.fireTicks < j) {
/*  504 */       this.fireTicks = j;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void extinguish() {
/*  510 */     this.fireTicks = 0;
/*      */   }
/*      */   
/*      */   protected void ac() {
/*  514 */     die();
/*      */   }
/*      */   
/*      */   public boolean c(double d0, double d1, double d2) {
/*  518 */     AxisAlignedBB axisalignedbb = getBoundingBox().d(d0, d1, d2);
/*      */     
/*  520 */     return b(axisalignedbb);
/*      */   }
/*      */   
/*      */   private boolean b(AxisAlignedBB axisalignedbb) {
/*  524 */     return (this.world.getCubes(this, axisalignedbb).isEmpty() && !this.world.containsLiquid(axisalignedbb));
/*      */   }
/*      */   
/*      */   public void move(EnumMoveType enummovetype, double d0, double d1, double d2) {
/*  528 */     SpigotTimings.entityMoveTimer.startTiming();
/*  529 */     if (this.noclip) {
/*  530 */       a(getBoundingBox().d(d0, d1, d2));
/*  531 */       recalcPosition();
/*      */     } else {
/*      */ 
/*      */       
/*      */       try {
/*  536 */         checkBlockCollisions();
/*  537 */       } catch (Throwable throwable) {
/*  538 */         CrashReport crashreport = CrashReport.a(throwable, "Checking entity block collision");
/*  539 */         CrashReportSystemDetails crashreportsystemdetails = crashreport.a("Entity being checked for collision");
/*      */         
/*  541 */         appendEntityCrashDetails(crashreportsystemdetails);
/*  542 */         throw new ReportedException(crashreport);
/*      */       } 
/*      */       
/*  545 */       if (d0 == 0.0D && d1 == 0.0D && d2 == 0.0D && isVehicle() && isPassenger()) {
/*      */         return;
/*      */       }
/*      */       
/*  549 */       if (enummovetype == EnumMoveType.PISTON) {
/*  550 */         long i = this.world.getTime();
/*      */         
/*  552 */         if (i != this.aK) {
/*  553 */           Arrays.fill(this.aJ, 0.0D);
/*  554 */           this.aK = i;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  560 */         if (d0 != 0.0D) {
/*  561 */           int j = EnumDirection.EnumAxis.X.ordinal();
/*  562 */           double d3 = MathHelper.a(d0 + this.aJ[j], -0.51D, 0.51D);
/*  563 */           d0 = d3 - this.aJ[j];
/*  564 */           this.aJ[j] = d3;
/*  565 */           if (Math.abs(d0) <= 9.999999747378752E-6D) {
/*      */             return;
/*      */           }
/*  568 */         } else if (d1 != 0.0D) {
/*  569 */           int j = EnumDirection.EnumAxis.Y.ordinal();
/*  570 */           double d3 = MathHelper.a(d1 + this.aJ[j], -0.51D, 0.51D);
/*  571 */           d1 = d3 - this.aJ[j];
/*  572 */           this.aJ[j] = d3;
/*  573 */           if (Math.abs(d1) <= 9.999999747378752E-6D) {
/*      */             return;
/*      */           }
/*      */         } else {
/*  577 */           if (d2 == 0.0D) {
/*      */             return;
/*      */           }
/*      */           
/*  581 */           int j = EnumDirection.EnumAxis.Z.ordinal();
/*  582 */           double d3 = MathHelper.a(d2 + this.aJ[j], -0.51D, 0.51D);
/*  583 */           d2 = d3 - this.aJ[j];
/*  584 */           this.aJ[j] = d3;
/*  585 */           if (Math.abs(d2) <= 9.999999747378752E-6D) {
/*      */             return;
/*      */           }
/*      */         } 
/*      */       } 
/*      */       
/*  591 */       this.world.methodProfiler.a("move");
/*  592 */       double d4 = this.locX;
/*  593 */       double d5 = this.locY;
/*  594 */       double d6 = this.locZ;
/*      */       
/*  596 */       if (this.E) {
/*  597 */         this.E = false;
/*  598 */         d0 *= 0.25D;
/*  599 */         d1 *= 0.05000000074505806D;
/*  600 */         d2 *= 0.25D;
/*  601 */         this.motX = 0.0D;
/*  602 */         this.motY = 0.0D;
/*  603 */         this.motZ = 0.0D;
/*      */       } 
/*      */       
/*  606 */       double d7 = d0;
/*  607 */       double d8 = d1;
/*  608 */       double d9 = d2;
/*      */       
/*  610 */       if ((enummovetype == EnumMoveType.SELF || enummovetype == EnumMoveType.PLAYER) && this.onGround && isSneaking() && this instanceof EntityHuman) {
/*  611 */         for (; d0 != 0.0D && this.world.getCubes(this, getBoundingBox().d(d0, -this.P, 0.0D)).isEmpty(); d7 = d0) {
/*  612 */           if (d0 < 0.05D && d0 >= -0.05D) {
/*  613 */             d0 = 0.0D;
/*  614 */           } else if (d0 > 0.0D) {
/*  615 */             d0 -= 0.05D;
/*      */           } else {
/*  617 */             d0 += 0.05D;
/*      */           } 
/*      */         } 
/*      */         
/*  621 */         for (; d2 != 0.0D && this.world.getCubes(this, getBoundingBox().d(0.0D, -this.P, d2)).isEmpty(); d9 = d2) {
/*  622 */           if (d2 < 0.05D && d2 >= -0.05D) {
/*  623 */             d2 = 0.0D;
/*  624 */           } else if (d2 > 0.0D) {
/*  625 */             d2 -= 0.05D;
/*      */           } else {
/*  627 */             d2 += 0.05D;
/*      */           } 
/*      */         } 
/*      */         
/*  631 */         for (; d0 != 0.0D && d2 != 0.0D && this.world.getCubes(this, getBoundingBox().d(d0, -this.P, d2)).isEmpty(); d9 = d2) {
/*  632 */           if (d0 < 0.05D && d0 >= -0.05D) {
/*  633 */             d0 = 0.0D;
/*  634 */           } else if (d0 > 0.0D) {
/*  635 */             d0 -= 0.05D;
/*      */           } else {
/*  637 */             d0 += 0.05D;
/*      */           } 
/*      */           
/*  640 */           d7 = d0;
/*  641 */           if (d2 < 0.05D && d2 >= -0.05D) {
/*  642 */             d2 = 0.0D;
/*  643 */           } else if (d2 > 0.0D) {
/*  644 */             d2 -= 0.05D;
/*      */           } else {
/*  646 */             d2 += 0.05D;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/*  651 */       List<AxisAlignedBB> list = this.world.getCubes(this, getBoundingBox().b(d0, d1, d2));
/*  652 */       AxisAlignedBB axisalignedbb = getBoundingBox();
/*      */ 
/*      */ 
/*      */       
/*  656 */       if (d1 != 0.0D) {
/*  657 */         int k = 0;
/*      */         
/*  659 */         for (int i = list.size(); k < i; k++) {
/*  660 */           d1 = ((AxisAlignedBB)list.get(k)).b(getBoundingBox(), d1);
/*      */         }
/*      */         
/*  663 */         a(getBoundingBox().d(0.0D, d1, 0.0D));
/*      */       } 
/*      */       
/*  666 */       if (d0 != 0.0D) {
/*  667 */         int k = 0;
/*      */         
/*  669 */         for (int i = list.size(); k < i; k++) {
/*  670 */           d0 = ((AxisAlignedBB)list.get(k)).a(getBoundingBox(), d0);
/*      */         }
/*      */         
/*  673 */         if (d0 != 0.0D) {
/*  674 */           a(getBoundingBox().d(d0, 0.0D, 0.0D));
/*      */         }
/*      */       } 
/*      */       
/*  678 */       if (d2 != 0.0D) {
/*  679 */         int k = 0;
/*      */         
/*  681 */         for (int i = list.size(); k < i; k++) {
/*  682 */           d2 = ((AxisAlignedBB)list.get(k)).c(getBoundingBox(), d2);
/*      */         }
/*      */         
/*  685 */         if (d2 != 0.0D) {
/*  686 */           a(getBoundingBox().d(0.0D, 0.0D, d2));
/*      */         }
/*      */       } 
/*      */       
/*  690 */       boolean flag = !(!this.onGround && (d1 == d8 || d1 >= 0.0D));
/*      */ 
/*      */       
/*  693 */       if (this.P > 0.0F && flag && (d7 != d0 || d9 != d2)) {
/*  694 */         double d12 = d0;
/*  695 */         double d13 = d1;
/*  696 */         double d14 = d2;
/*  697 */         AxisAlignedBB axisalignedbb1 = getBoundingBox();
/*      */         
/*  699 */         a(axisalignedbb);
/*  700 */         d1 = this.P;
/*  701 */         List<AxisAlignedBB> list1 = this.world.getCubes(this, getBoundingBox().b(d7, d1, d9));
/*  702 */         AxisAlignedBB axisalignedbb2 = getBoundingBox();
/*  703 */         AxisAlignedBB axisalignedbb3 = axisalignedbb2.b(d7, 0.0D, d9);
/*      */         
/*  705 */         double d11 = d1;
/*  706 */         int i1 = 0;
/*      */         
/*  708 */         for (int j1 = list1.size(); i1 < j1; i1++) {
/*  709 */           d11 = ((AxisAlignedBB)list1.get(i1)).b(axisalignedbb3, d11);
/*      */         }
/*      */         
/*  712 */         axisalignedbb2 = axisalignedbb2.d(0.0D, d11, 0.0D);
/*  713 */         double d15 = d7;
/*  714 */         int k1 = 0;
/*      */         
/*  716 */         for (int l1 = list1.size(); k1 < l1; k1++) {
/*  717 */           d15 = ((AxisAlignedBB)list1.get(k1)).a(axisalignedbb2, d15);
/*      */         }
/*      */         
/*  720 */         axisalignedbb2 = axisalignedbb2.d(d15, 0.0D, 0.0D);
/*  721 */         double d16 = d9;
/*  722 */         int i2 = 0;
/*      */         
/*  724 */         for (int j2 = list1.size(); i2 < j2; i2++) {
/*  725 */           d16 = ((AxisAlignedBB)list1.get(i2)).c(axisalignedbb2, d16);
/*      */         }
/*      */         
/*  728 */         axisalignedbb2 = axisalignedbb2.d(0.0D, 0.0D, d16);
/*  729 */         AxisAlignedBB axisalignedbb4 = getBoundingBox();
/*  730 */         double d17 = d1;
/*  731 */         int k2 = 0;
/*      */         
/*  733 */         for (int l2 = list1.size(); k2 < l2; k2++) {
/*  734 */           d17 = ((AxisAlignedBB)list1.get(k2)).b(axisalignedbb4, d17);
/*      */         }
/*      */         
/*  737 */         axisalignedbb4 = axisalignedbb4.d(0.0D, d17, 0.0D);
/*  738 */         double d18 = d7;
/*  739 */         int i3 = 0;
/*      */         
/*  741 */         for (int j3 = list1.size(); i3 < j3; i3++) {
/*  742 */           d18 = ((AxisAlignedBB)list1.get(i3)).a(axisalignedbb4, d18);
/*      */         }
/*      */         
/*  745 */         axisalignedbb4 = axisalignedbb4.d(d18, 0.0D, 0.0D);
/*  746 */         double d19 = d9;
/*  747 */         int k3 = 0;
/*      */         
/*  749 */         for (int l3 = list1.size(); k3 < l3; k3++) {
/*  750 */           d19 = ((AxisAlignedBB)list1.get(k3)).c(axisalignedbb4, d19);
/*      */         }
/*      */         
/*  753 */         axisalignedbb4 = axisalignedbb4.d(0.0D, 0.0D, d19);
/*  754 */         double d20 = d15 * d15 + d16 * d16;
/*  755 */         double d21 = d18 * d18 + d19 * d19;
/*      */         
/*  757 */         if (d20 > d21) {
/*  758 */           d0 = d15;
/*  759 */           d2 = d16;
/*  760 */           d1 = -d11;
/*  761 */           a(axisalignedbb2);
/*      */         } else {
/*  763 */           d0 = d18;
/*  764 */           d2 = d19;
/*  765 */           d1 = -d17;
/*  766 */           a(axisalignedbb4);
/*      */         } 
/*      */         
/*  769 */         int i4 = 0;
/*      */         
/*  771 */         for (int j4 = list1.size(); i4 < j4; i4++) {
/*  772 */           d1 = ((AxisAlignedBB)list1.get(i4)).b(getBoundingBox(), d1);
/*      */         }
/*      */         
/*  775 */         a(getBoundingBox().d(0.0D, d1, 0.0D));
/*  776 */         if (d12 * d12 + d14 * d14 >= d0 * d0 + d2 * d2) {
/*  777 */           d0 = d12;
/*  778 */           d1 = d13;
/*  779 */           d2 = d14;
/*  780 */           a(axisalignedbb1);
/*      */         } 
/*      */       } 
/*      */       
/*  784 */       this.world.methodProfiler.b();
/*  785 */       this.world.methodProfiler.a("rest");
/*  786 */       recalcPosition();
/*  787 */       this.positionChanged = !(d7 == d0 && d9 == d2);
/*  788 */       this.B = (d1 != d8);
/*  789 */       this.onGround = (this.B && d8 < 0.0D);
/*  790 */       this.C = !(!this.positionChanged && !this.B);
/*  791 */       int l = MathHelper.floor(this.locX);
/*  792 */       int k4 = MathHelper.floor(this.locY - 0.20000000298023224D);
/*  793 */       int l4 = MathHelper.floor(this.locZ);
/*  794 */       BlockPosition blockposition = new BlockPosition(l, k4, l4);
/*  795 */       IBlockData iblockdata = this.world.getType(blockposition);
/*      */       
/*  797 */       if (iblockdata.getMaterial() == Material.AIR) {
/*  798 */         BlockPosition blockposition1 = blockposition.down();
/*  799 */         IBlockData iblockdata1 = this.world.getType(blockposition1);
/*  800 */         Block block = iblockdata1.getBlock();
/*      */         
/*  802 */         if (block instanceof BlockFence || block instanceof BlockCobbleWall || block instanceof BlockFenceGate) {
/*  803 */           iblockdata = iblockdata1;
/*  804 */           blockposition = blockposition1;
/*      */         } 
/*      */       } 
/*      */       
/*  808 */       a(d1, this.onGround, iblockdata, blockposition);
/*  809 */       if (d7 != d0) {
/*  810 */         this.motX = 0.0D;
/*      */       }
/*      */       
/*  813 */       if (d9 != d2) {
/*  814 */         this.motZ = 0.0D;
/*      */       }
/*      */       
/*  817 */       Block block1 = iblockdata.getBlock();
/*      */       
/*  819 */       if (d8 != d1) {
/*  820 */         block1.a(this.world, this);
/*      */       }
/*      */ 
/*      */       
/*  824 */       if (this.positionChanged && getBukkitEntity() instanceof Vehicle) {
/*  825 */         Vehicle vehicle = (Vehicle)getBukkitEntity();
/*  826 */         Block bl = this.world.getWorld().getBlockAt(MathHelper.floor(this.locX), MathHelper.floor(this.locY), MathHelper.floor(this.locZ));
/*      */         
/*  828 */         if (d7 > d0) {
/*  829 */           bl = bl.getRelative(BlockFace.EAST);
/*  830 */         } else if (d7 < d0) {
/*  831 */           bl = bl.getRelative(BlockFace.WEST);
/*  832 */         } else if (d9 > d2) {
/*  833 */           bl = bl.getRelative(BlockFace.SOUTH);
/*  834 */         } else if (d9 < d2) {
/*  835 */           bl = bl.getRelative(BlockFace.NORTH);
/*      */         } 
/*      */         
/*  838 */         if (bl.getType() != Material.AIR) {
/*  839 */           VehicleBlockCollisionEvent event = new VehicleBlockCollisionEvent(vehicle, bl);
/*  840 */           this.world.getServer().getPluginManager().callEvent((Event)event);
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/*  845 */       if (playStepSound() && (!this.onGround || !isSneaking() || !(this instanceof EntityHuman)) && !isPassenger()) {
/*  846 */         double d22 = this.locX - d4;
/*  847 */         double d23 = this.locY - d5;
/*      */         
/*  849 */         double d11 = this.locZ - d6;
/*  850 */         if (block1 != Blocks.LADDER) {
/*  851 */           d23 = 0.0D;
/*      */         }
/*      */         
/*  854 */         if (block1 != null && this.onGround) {
/*  855 */           block1.stepOn(this.world, blockposition, this);
/*      */         }
/*      */         
/*  858 */         this.J = (float)(this.J + MathHelper.sqrt(d22 * d22 + d11 * d11) * 0.6D);
/*  859 */         this.K = (float)(this.K + MathHelper.sqrt(d22 * d22 + d23 * d23 + d11 * d11) * 0.6D);
/*  860 */         if (this.K > this.ax && iblockdata.getMaterial() != Material.AIR) {
/*  861 */           this.ax = (int)this.K + 1;
/*  862 */           if (isInWater()) {
/*  863 */             Entity entity = (isVehicle() && bE() != null) ? bE() : this;
/*  864 */             float f = (entity == this) ? 0.35F : 0.4F;
/*  865 */             float f1 = MathHelper.sqrt(entity.motX * entity.motX * 0.20000000298023224D + entity.motY * entity.motY + entity.motZ * entity.motZ * 0.20000000298023224D) * f;
/*      */             
/*  867 */             if (f1 > 1.0F) {
/*  868 */               f1 = 1.0F;
/*      */             }
/*      */             
/*  871 */             a(ae(), f1, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.4F);
/*      */           } else {
/*  873 */             a(blockposition, block1);
/*      */           } 
/*  875 */         } else if (this.K > this.ay && ah() && iblockdata.getMaterial() == Material.AIR) {
/*  876 */           this.ay = d(this.K);
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  894 */       boolean flag1 = an();
/*      */       
/*  896 */       if (this.world.e(getBoundingBox().shrink(0.001D))) {
/*  897 */         burn(1.0F);
/*  898 */         if (!flag1) {
/*  899 */           this.fireTicks++;
/*  900 */           if (this.fireTicks == 0)
/*      */           {
/*  902 */             EntityCombustByBlockEvent entityCombustByBlockEvent = new EntityCombustByBlockEvent(null, (org.bukkit.entity.Entity)getBukkitEntity(), 8);
/*  903 */             this.world.getServer().getPluginManager().callEvent((Event)entityCombustByBlockEvent);
/*      */             
/*  905 */             if (!entityCombustByBlockEvent.isCancelled()) {
/*  906 */               setOnFire(entityCombustByBlockEvent.getDuration());
/*      */             }
/*      */           }
/*      */         
/*      */         } 
/*  911 */       } else if (this.fireTicks <= 0) {
/*  912 */         this.fireTicks = -getMaxFireTicks();
/*      */       } 
/*      */       
/*  915 */       if (flag1 && isBurning()) {
/*  916 */         a(SoundEffects.bW, 0.7F, 1.6F + (this.random.nextFloat() - this.random.nextFloat()) * 0.4F);
/*  917 */         this.fireTicks = -getMaxFireTicks();
/*      */       } 
/*      */       
/*  920 */       this.world.methodProfiler.b();
/*      */     } 
/*  922 */     SpigotTimings.entityMoveTimer.stopTiming();
/*      */   }
/*      */   
/*      */   public void recalcPosition() {
/*  926 */     AxisAlignedBB axisalignedbb = getBoundingBox();
/*      */     
/*  928 */     this.locX = (axisalignedbb.a + axisalignedbb.d) / 2.0D;
/*  929 */     this.locY = axisalignedbb.b;
/*  930 */     this.locZ = (axisalignedbb.c + axisalignedbb.f) / 2.0D;
/*      */   }
/*      */   
/*      */   protected SoundEffect ae() {
/*  934 */     return SoundEffects.ca;
/*      */   }
/*      */   
/*      */   protected SoundEffect af() {
/*  938 */     return SoundEffects.bZ;
/*      */   }
/*      */   
/*      */   protected void checkBlockCollisions() {
/*  942 */     AxisAlignedBB axisalignedbb = getBoundingBox();
/*  943 */     BlockPosition.PooledBlockPosition blockposition_pooledblockposition = BlockPosition.PooledBlockPosition.d(axisalignedbb.a + 0.001D, axisalignedbb.b + 0.001D, axisalignedbb.c + 0.001D);
/*  944 */     BlockPosition.PooledBlockPosition blockposition_pooledblockposition1 = BlockPosition.PooledBlockPosition.d(axisalignedbb.d - 0.001D, axisalignedbb.e - 0.001D, axisalignedbb.f - 0.001D);
/*  945 */     BlockPosition.PooledBlockPosition blockposition_pooledblockposition2 = BlockPosition.PooledBlockPosition.s();
/*      */     
/*  947 */     if (this.world.areChunksLoadedBetween(blockposition_pooledblockposition, blockposition_pooledblockposition1)) {
/*  948 */       for (int i = blockposition_pooledblockposition.getX(); i <= blockposition_pooledblockposition1.getX(); i++) {
/*  949 */         for (int j = blockposition_pooledblockposition.getY(); j <= blockposition_pooledblockposition1.getY(); j++) {
/*  950 */           for (int k = blockposition_pooledblockposition.getZ(); k <= blockposition_pooledblockposition1.getZ(); k++) {
/*  951 */             blockposition_pooledblockposition2.f(i, j, k);
/*  952 */             IBlockData iblockdata = this.world.getType(blockposition_pooledblockposition2);
/*      */             
/*      */             try {
/*  955 */               iblockdata.getBlock().a(this.world, blockposition_pooledblockposition2, iblockdata, this);
/*  956 */               a(iblockdata);
/*  957 */             } catch (Throwable throwable) {
/*  958 */               CrashReport crashreport = CrashReport.a(throwable, "Colliding entity with block");
/*  959 */               CrashReportSystemDetails crashreportsystemdetails = crashreport.a("Block being collided with");
/*      */               
/*  961 */               CrashReportSystemDetails.a(crashreportsystemdetails, blockposition_pooledblockposition2, iblockdata);
/*  962 */               throw new ReportedException(crashreport);
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/*  969 */     blockposition_pooledblockposition.t();
/*  970 */     blockposition_pooledblockposition1.t();
/*  971 */     blockposition_pooledblockposition2.t();
/*      */   }
/*      */   
/*      */   protected void a(IBlockData iblockdata) {}
/*      */   
/*      */   protected void a(BlockPosition blockposition, Block block) {
/*  977 */     SoundEffectType soundeffecttype = block.getStepSound();
/*      */     
/*  979 */     if (this.world.getType(blockposition.up()).getBlock() == Blocks.SNOW_LAYER) {
/*  980 */       soundeffecttype = Blocks.SNOW_LAYER.getStepSound();
/*  981 */       a(soundeffecttype.d(), soundeffecttype.a() * 0.15F, soundeffecttype.b());
/*  982 */     } else if (!block.getBlockData().getMaterial().isLiquid()) {
/*  983 */       a(soundeffecttype.d(), soundeffecttype.a() * 0.15F, soundeffecttype.b());
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected float d(float f) {
/*  989 */     return 0.0F;
/*      */   }
/*      */   
/*      */   protected boolean ah() {
/*  993 */     return false;
/*      */   }
/*      */   
/*      */   public void a(SoundEffect soundeffect, float f, float f1) {
/*  997 */     if (!isSilent()) {
/*  998 */       this.world.a((EntityHuman)null, this.locX, this.locY, this.locZ, soundeffect, bK(), f, f1);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isSilent() {
/* 1004 */     return ((Boolean)this.datawatcher.<Boolean>get(aD)).booleanValue();
/*      */   }
/*      */   
/*      */   public void setSilent(boolean flag) {
/* 1008 */     this.datawatcher.set(aD, Boolean.valueOf(flag));
/*      */   }
/*      */   
/*      */   public boolean isNoGravity() {
/* 1012 */     return ((Boolean)this.datawatcher.<Boolean>get(aE)).booleanValue();
/*      */   }
/*      */   
/*      */   public void setNoGravity(boolean flag) {
/* 1016 */     this.datawatcher.set(aE, Boolean.valueOf(flag));
/*      */   }
/*      */   
/*      */   protected boolean playStepSound() {
/* 1020 */     return true;
/*      */   }
/*      */   
/*      */   protected void a(double d0, boolean flag, IBlockData iblockdata, BlockPosition blockposition) {
/* 1024 */     if (flag) {
/* 1025 */       if (this.fallDistance > 0.0F) {
/* 1026 */         iblockdata.getBlock().fallOn(this.world, blockposition, this, this.fallDistance);
/*      */       }
/*      */       
/* 1029 */       this.fallDistance = 0.0F;
/* 1030 */     } else if (d0 < 0.0D) {
/* 1031 */       this.fallDistance = (float)(this.fallDistance - d0);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public AxisAlignedBB al() {
/* 1038 */     return null;
/*      */   }
/*      */   
/*      */   protected void burn(float i) {
/* 1042 */     if (!this.fireProof) {
/* 1043 */       damageEntity(DamageSource.FIRE, i);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public final boolean isFireProof() {
/* 1049 */     return this.fireProof;
/*      */   }
/*      */   
/*      */   public void e(float f, float f1) {
/* 1053 */     if (isVehicle()) {
/* 1054 */       Iterator<Entity> iterator = bF().iterator();
/*      */       
/* 1056 */       while (iterator.hasNext()) {
/* 1057 */         Entity entity = iterator.next();
/*      */         
/* 1059 */         entity.e(f, f1);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean an() {
/* 1066 */     if (this.inWater) {
/* 1067 */       return true;
/*      */     }
/* 1069 */     BlockPosition.PooledBlockPosition blockposition_pooledblockposition = BlockPosition.PooledBlockPosition.d(this.locX, this.locY, this.locZ);
/*      */     
/* 1071 */     if (!this.world.isRainingAt(blockposition_pooledblockposition) && !this.world.isRainingAt(blockposition_pooledblockposition.e(this.locX, this.locY + this.length, this.locZ))) {
/* 1072 */       blockposition_pooledblockposition.t();
/* 1073 */       return false;
/*      */     } 
/* 1075 */     blockposition_pooledblockposition.t();
/* 1076 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isInWater() {
/* 1082 */     return this.inWater;
/*      */   }
/*      */   
/*      */   public boolean ap() {
/* 1086 */     return this.world.a(getBoundingBox().grow(0.0D, -20.0D, 0.0D).shrink(0.001D), Material.WATER, this);
/*      */   }
/*      */   
/*      */   public boolean aq() {
/* 1090 */     if (bJ() instanceof EntityBoat) {
/* 1091 */       this.inWater = false;
/* 1092 */     } else if (this.world.a(getBoundingBox().grow(0.0D, -0.4000000059604645D, 0.0D).shrink(0.001D), Material.WATER, this)) {
/* 1093 */       if (!this.inWater && !this.justCreated) {
/* 1094 */         ar();
/*      */       }
/*      */       
/* 1097 */       this.fallDistance = 0.0F;
/* 1098 */       this.inWater = true;
/* 1099 */       extinguish();
/*      */     } else {
/* 1101 */       this.inWater = false;
/*      */     } 
/*      */     
/* 1104 */     return this.inWater;
/*      */   }
/*      */   
/*      */   protected void ar() {
/* 1108 */     Entity entity = (isVehicle() && bE() != null) ? bE() : this;
/* 1109 */     float f = (entity == this) ? 0.2F : 0.9F;
/* 1110 */     float f1 = MathHelper.sqrt(entity.motX * entity.motX * 0.20000000298023224D + entity.motY * entity.motY + entity.motZ * entity.motZ * 0.20000000298023224D) * f;
/*      */     
/* 1112 */     if (f1 > 1.0F) {
/* 1113 */       f1 = 1.0F;
/*      */     }
/*      */     
/* 1116 */     a(af(), f1, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.4F);
/* 1117 */     float f2 = MathHelper.floor((getBoundingBox()).b);
/*      */ 
/*      */     
/*      */     int i;
/*      */ 
/*      */     
/* 1123 */     for (i = 0; i < 1.0F + this.width * 20.0F; i++) {
/* 1124 */       float f3 = (this.random.nextFloat() * 2.0F - 1.0F) * this.width;
/* 1125 */       float f4 = (this.random.nextFloat() * 2.0F - 1.0F) * this.width;
/* 1126 */       this.world.addParticle(EnumParticle.WATER_BUBBLE, this.locX + f3, (f2 + 1.0F), this.locZ + f4, this.motX, this.motY - (this.random.nextFloat() * 0.2F), this.motZ, new int[0]);
/*      */     } 
/*      */     
/* 1129 */     for (i = 0; i < 1.0F + this.width * 20.0F; i++) {
/* 1130 */       float f3 = (this.random.nextFloat() * 2.0F - 1.0F) * this.width;
/* 1131 */       float f4 = (this.random.nextFloat() * 2.0F - 1.0F) * this.width;
/* 1132 */       this.world.addParticle(EnumParticle.WATER_SPLASH, this.locX + f3, (f2 + 1.0F), this.locZ + f4, this.motX, this.motY, this.motZ, new int[0]);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void as() {
/* 1138 */     if (isSprinting() && !isInWater()) {
/* 1139 */       at();
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected void at() {
/* 1145 */     int i = MathHelper.floor(this.locX);
/* 1146 */     int j = MathHelper.floor(this.locY - 0.20000000298023224D);
/* 1147 */     int k = MathHelper.floor(this.locZ);
/* 1148 */     BlockPosition blockposition = new BlockPosition(i, j, k);
/* 1149 */     IBlockData iblockdata = this.world.getType(blockposition);
/*      */     
/* 1151 */     if (iblockdata.i() != EnumRenderType.INVISIBLE) {
/* 1152 */       this.world.addParticle(EnumParticle.BLOCK_CRACK, this.locX + (this.random.nextFloat() - 0.5D) * this.width, (getBoundingBox()).b + 0.1D, this.locZ + (this.random.nextFloat() - 0.5D) * this.width, -this.motX * 4.0D, 1.5D, -this.motZ * 4.0D, new int[] { Block.getCombinedId(iblockdata) });
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean a(Material material) {
/* 1158 */     if (bJ() instanceof EntityBoat) {
/* 1159 */       return false;
/*      */     }
/* 1161 */     double d0 = this.locY + getHeadHeight();
/* 1162 */     BlockPosition blockposition = new BlockPosition(this.locX, d0, this.locZ);
/* 1163 */     IBlockData iblockdata = this.world.getType(blockposition);
/*      */     
/* 1165 */     if (iblockdata.getMaterial() == material) {
/* 1166 */       float f = BlockFluids.b(iblockdata.getBlock().toLegacyData(iblockdata)) - 0.11111111F;
/* 1167 */       float f1 = (blockposition.getY() + 1) - f;
/* 1168 */       boolean flag = (d0 < f1);
/*      */       
/* 1170 */       return (!flag && this instanceof EntityHuman) ? false : flag;
/*      */     } 
/* 1172 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean au() {
/* 1178 */     return this.world.a(getBoundingBox().grow(-0.10000000149011612D, -0.4000000059604645D, -0.10000000149011612D), Material.LAVA);
/*      */   }
/*      */   
/*      */   public void b(float f, float f1, float f2, float f3) {
/* 1182 */     float f4 = f * f + f1 * f1 + f2 * f2;
/*      */     
/* 1184 */     if (f4 >= 1.0E-4F) {
/* 1185 */       f4 = MathHelper.c(f4);
/* 1186 */       if (f4 < 1.0F) {
/* 1187 */         f4 = 1.0F;
/*      */       }
/*      */       
/* 1190 */       f4 = f3 / f4;
/* 1191 */       f *= f4;
/* 1192 */       f1 *= f4;
/* 1193 */       f2 *= f4;
/* 1194 */       float f5 = MathHelper.sin(this.yaw * 0.017453292F);
/* 1195 */       float f6 = MathHelper.cos(this.yaw * 0.017453292F);
/*      */       
/* 1197 */       this.motX += (f * f6 - f2 * f5);
/* 1198 */       this.motY += f1;
/* 1199 */       this.motZ += (f2 * f6 + f * f5);
/*      */     } 
/*      */   }
/*      */   
/*      */   public float aw() {
/* 1204 */     BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition(MathHelper.floor(this.locX), 0, MathHelper.floor(this.locZ));
/*      */     
/* 1206 */     if (this.world.isLoaded(blockposition_mutableblockposition)) {
/* 1207 */       blockposition_mutableblockposition.p(MathHelper.floor(this.locY + getHeadHeight()));
/* 1208 */       return this.world.n(blockposition_mutableblockposition);
/*      */     } 
/* 1210 */     return 0.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void spawnIn(World world) {
/* 1216 */     if (world == null) {
/* 1217 */       die();
/* 1218 */       this.world = ((CraftWorld)Bukkit.getServer().getWorlds().get(0)).getHandle();
/*      */       
/*      */       return;
/*      */     } 
/* 1222 */     this.world = world;
/*      */   }
/*      */   
/*      */   public void setLocation(double d0, double d1, double d2, float f, float f1) {
/* 1226 */     this.locX = MathHelper.a(d0, -3.0E7D, 3.0E7D);
/* 1227 */     this.locY = d1;
/* 1228 */     this.locZ = MathHelper.a(d2, -3.0E7D, 3.0E7D);
/* 1229 */     this.lastX = this.locX;
/* 1230 */     this.lastY = this.locY;
/* 1231 */     this.lastZ = this.locZ;
/* 1232 */     f1 = MathHelper.a(f1, -90.0F, 90.0F);
/* 1233 */     this.yaw = f;
/* 1234 */     this.pitch = f1;
/* 1235 */     this.lastYaw = this.yaw;
/* 1236 */     this.lastPitch = this.pitch;
/* 1237 */     double d3 = (this.lastYaw - f);
/*      */     
/* 1239 */     if (d3 < -180.0D) {
/* 1240 */       this.lastYaw += 360.0F;
/*      */     }
/*      */     
/* 1243 */     if (d3 >= 180.0D) {
/* 1244 */       this.lastYaw -= 360.0F;
/*      */     }
/*      */     
/* 1247 */     setPosition(this.locX, this.locY, this.locZ);
/* 1248 */     setYawPitch(f, f1);
/*      */   }
/*      */   
/*      */   public void setPositionRotation(BlockPosition blockposition, float f, float f1) {
/* 1252 */     setPositionRotation(blockposition.getX() + 0.5D, blockposition.getY(), blockposition.getZ() + 0.5D, f, f1);
/*      */   }
/*      */   
/*      */   public void setPositionRotation(double d0, double d1, double d2, float f, float f1) {
/* 1256 */     this.locX = d0;
/* 1257 */     this.locY = d1;
/* 1258 */     this.locZ = d2;
/* 1259 */     this.lastX = this.locX;
/* 1260 */     this.lastY = this.locY;
/* 1261 */     this.lastZ = this.locZ;
/* 1262 */     this.M = this.locX;
/* 1263 */     this.N = this.locY;
/* 1264 */     this.O = this.locZ;
/* 1265 */     this.yaw = f;
/* 1266 */     this.pitch = f1;
/* 1267 */     setPosition(this.locX, this.locY, this.locZ);
/*      */   }
/*      */   
/*      */   public float g(Entity entity) {
/* 1271 */     float f = (float)(this.locX - entity.locX);
/* 1272 */     float f1 = (float)(this.locY - entity.locY);
/* 1273 */     float f2 = (float)(this.locZ - entity.locZ);
/*      */     
/* 1275 */     return MathHelper.c(f * f + f1 * f1 + f2 * f2);
/*      */   }
/*      */   
/*      */   public double d(double d0, double d1, double d2) {
/* 1279 */     double d3 = this.locX - d0;
/* 1280 */     double d4 = this.locY - d1;
/* 1281 */     double d5 = this.locZ - d2;
/*      */     
/* 1283 */     return d3 * d3 + d4 * d4 + d5 * d5;
/*      */   }
/*      */   
/*      */   public double c(BlockPosition blockposition) {
/* 1287 */     return blockposition.distanceSquared(this.locX, this.locY, this.locZ);
/*      */   }
/*      */   
/*      */   public double d(BlockPosition blockposition) {
/* 1291 */     return blockposition.g(this.locX, this.locY, this.locZ);
/*      */   }
/*      */   
/*      */   public double e(double d0, double d1, double d2) {
/* 1295 */     double d3 = this.locX - d0;
/* 1296 */     double d4 = this.locY - d1;
/* 1297 */     double d5 = this.locZ - d2;
/*      */     
/* 1299 */     return MathHelper.sqrt(d3 * d3 + d4 * d4 + d5 * d5);
/*      */   }
/*      */   
/*      */   public double h(Entity entity) {
/* 1303 */     double d0 = this.locX - entity.locX;
/* 1304 */     double d1 = this.locY - entity.locY;
/* 1305 */     double d2 = this.locZ - entity.locZ;
/*      */     
/* 1307 */     return d0 * d0 + d1 * d1 + d2 * d2;
/*      */   }
/*      */   
/*      */   public void d(EntityHuman entityhuman) {}
/*      */   
/*      */   public void collide(Entity entity) {
/* 1313 */     if (!x(entity) && 
/* 1314 */       !entity.noclip && !this.noclip) {
/* 1315 */       double d0 = entity.locX - this.locX;
/* 1316 */       double d1 = entity.locZ - this.locZ;
/* 1317 */       double d2 = MathHelper.a(d0, d1);
/*      */       
/* 1319 */       if (d2 >= 0.009999999776482582D) {
/* 1320 */         d2 = MathHelper.sqrt(d2);
/* 1321 */         d0 /= d2;
/* 1322 */         d1 /= d2;
/* 1323 */         double d3 = 1.0D / d2;
/*      */         
/* 1325 */         if (d3 > 1.0D) {
/* 1326 */           d3 = 1.0D;
/*      */         }
/*      */         
/* 1329 */         d0 *= d3;
/* 1330 */         d1 *= d3;
/* 1331 */         d0 *= 0.05000000074505806D;
/* 1332 */         d1 *= 0.05000000074505806D;
/* 1333 */         d0 *= (1.0F - this.R);
/* 1334 */         d1 *= (1.0F - this.R);
/* 1335 */         if (!isVehicle()) {
/* 1336 */           f(-d0, 0.0D, -d1);
/*      */         }
/*      */         
/* 1339 */         if (!entity.isVehicle()) {
/* 1340 */           entity.f(d0, 0.0D, d1);
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void f(double d0, double d1, double d2) {
/* 1349 */     this.motX += d0;
/* 1350 */     this.motY += d1;
/* 1351 */     this.motZ += d2;
/* 1352 */     this.impulse = true;
/*      */   }
/*      */   
/*      */   protected void ax() {
/* 1356 */     this.velocityChanged = true;
/*      */   }
/*      */   
/*      */   public boolean damageEntity(DamageSource damagesource, float f) {
/* 1360 */     if (isInvulnerable(damagesource)) {
/* 1361 */       return false;
/*      */     }
/* 1363 */     ax();
/* 1364 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public Vec3D e(float f) {
/* 1369 */     if (f == 1.0F) {
/* 1370 */       return f(this.pitch, this.yaw);
/*      */     }
/* 1372 */     float f1 = this.lastPitch + (this.pitch - this.lastPitch) * f;
/* 1373 */     float f2 = this.lastYaw + (this.yaw - this.lastYaw) * f;
/*      */     
/* 1375 */     return f(f1, f2);
/*      */   }
/*      */ 
/*      */   
/*      */   protected final Vec3D f(float f, float f1) {
/* 1380 */     float f2 = MathHelper.cos(-f1 * 0.017453292F - 3.1415927F);
/* 1381 */     float f3 = MathHelper.sin(-f1 * 0.017453292F - 3.1415927F);
/* 1382 */     float f4 = -MathHelper.cos(-f * 0.017453292F);
/* 1383 */     float f5 = MathHelper.sin(-f * 0.017453292F);
/*      */     
/* 1385 */     return new Vec3D((f3 * f4), f5, (f2 * f4));
/*      */   }
/*      */   
/*      */   public Vec3D f(float f) {
/* 1389 */     if (f == 1.0F) {
/* 1390 */       return new Vec3D(this.locX, this.locY + getHeadHeight(), this.locZ);
/*      */     }
/* 1392 */     double d0 = this.lastX + (this.locX - this.lastX) * f;
/* 1393 */     double d1 = this.lastY + (this.locY - this.lastY) * f + getHeadHeight();
/* 1394 */     double d2 = this.lastZ + (this.locZ - this.lastZ) * f;
/*      */     
/* 1396 */     return new Vec3D(d0, d1, d2);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isInteractable() {
/* 1401 */     return false;
/*      */   }
/*      */   
/*      */   public boolean isCollidable() {
/* 1405 */     return false;
/*      */   }
/*      */   
/*      */   public void a(Entity entity, int i, DamageSource damagesource) {
/* 1409 */     if (entity instanceof EntityPlayer) {
/* 1410 */       CriterionTriggers.c.a((EntityPlayer)entity, this, damagesource);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean c(NBTTagCompound nbttagcompound) {
/* 1416 */     String s = getSaveID();
/*      */     
/* 1418 */     if (!this.dead && s != null) {
/* 1419 */       nbttagcompound.setString("id", s);
/* 1420 */       save(nbttagcompound);
/* 1421 */       return true;
/*      */     } 
/* 1423 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean d(NBTTagCompound nbttagcompound) {
/* 1428 */     String s = getSaveID();
/*      */     
/* 1430 */     if (!this.dead && s != null && !isPassenger()) {
/* 1431 */       nbttagcompound.setString("id", s);
/* 1432 */       save(nbttagcompound);
/* 1433 */       return true;
/*      */     } 
/* 1435 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void b(DataConverterManager dataconvertermanager) {
/* 1440 */     dataconvertermanager.a(DataConverterTypes.ENTITY, new DataInspector() {
/*      */           public NBTTagCompound a(DataConverter dataconverter, NBTTagCompound nbttagcompound, int i) {
/* 1442 */             if (nbttagcompound.hasKeyOfType("Passengers", 9)) {
/* 1443 */               NBTTagList nbttaglist = nbttagcompound.getList("Passengers", 10);
/*      */               
/* 1445 */               for (int j = 0; j < nbttaglist.size(); j++) {
/* 1446 */                 nbttaglist.a(j, dataconverter.a(DataConverterTypes.ENTITY, nbttaglist.get(j), i));
/*      */               }
/*      */             } 
/*      */             
/* 1450 */             return nbttagcompound;
/*      */           }
/*      */         });
/*      */   }
/*      */   
/*      */   public NBTTagCompound save(NBTTagCompound nbttagcompound) {
/*      */     try {
/* 1457 */       nbttagcompound.set("Pos", a(new double[] { this.locX, this.locY, this.locZ }));
/* 1458 */       nbttagcompound.set("Motion", a(new double[] { this.motX, this.motY, this.motZ }));
/*      */ 
/*      */ 
/*      */       
/* 1462 */       if (Float.isNaN(this.yaw)) {
/* 1463 */         this.yaw = 0.0F;
/*      */       }
/*      */       
/* 1466 */       if (Float.isNaN(this.pitch)) {
/* 1467 */         this.pitch = 0.0F;
/*      */       }
/*      */ 
/*      */       
/* 1471 */       nbttagcompound.set("Rotation", a(new float[] { this.yaw, this.pitch }));
/* 1472 */       nbttagcompound.setFloat("FallDistance", this.fallDistance);
/* 1473 */       nbttagcompound.setShort("Fire", (short)this.fireTicks);
/* 1474 */       nbttagcompound.setShort("Air", (short)getAirTicks());
/* 1475 */       nbttagcompound.setBoolean("OnGround", this.onGround);
/* 1476 */       nbttagcompound.setInt("Dimension", this.dimension);
/* 1477 */       nbttagcompound.setBoolean("Invulnerable", this.invulnerable);
/* 1478 */       nbttagcompound.setInt("PortalCooldown", this.portalCooldown);
/* 1479 */       nbttagcompound.a("UUID", getUniqueID());
/*      */ 
/*      */       
/* 1482 */       nbttagcompound.setLong("WorldUUIDLeast", this.world.getDataManager().getUUID().getLeastSignificantBits());
/* 1483 */       nbttagcompound.setLong("WorldUUIDMost", this.world.getDataManager().getUUID().getMostSignificantBits());
/* 1484 */       nbttagcompound.setInt("Bukkit.updateLevel", 2);
/* 1485 */       nbttagcompound.setInt("Spigot.ticksLived", this.ticksLived);
/*      */       
/* 1487 */       if (hasCustomName()) {
/* 1488 */         nbttagcompound.setString("CustomName", getCustomName());
/*      */       }
/*      */       
/* 1491 */       if (getCustomNameVisible()) {
/* 1492 */         nbttagcompound.setBoolean("CustomNameVisible", getCustomNameVisible());
/*      */       }
/*      */       
/* 1495 */       this.aG.b(nbttagcompound);
/* 1496 */       if (isSilent()) {
/* 1497 */         nbttagcompound.setBoolean("Silent", isSilent());
/*      */       }
/*      */       
/* 1500 */       if (isNoGravity()) {
/* 1501 */         nbttagcompound.setBoolean("NoGravity", isNoGravity());
/*      */       }
/*      */       
/* 1504 */       if (this.glowing) {
/* 1505 */         nbttagcompound.setBoolean("Glowing", this.glowing);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1511 */       if (!this.aH.isEmpty()) {
/* 1512 */         NBTTagList nbttaglist = new NBTTagList();
/* 1513 */         Iterator<String> iterator = this.aH.iterator();
/*      */         
/* 1515 */         while (iterator.hasNext()) {
/* 1516 */           String s = iterator.next();
/*      */           
/* 1518 */           nbttaglist.add(new NBTTagString(s));
/*      */         } 
/*      */         
/* 1521 */         nbttagcompound.set("Tags", nbttaglist);
/*      */       } 
/*      */       
/* 1524 */       b(nbttagcompound);
/* 1525 */       if (isVehicle()) {
/* 1526 */         NBTTagList nbttaglist = new NBTTagList();
/* 1527 */         Iterator<Entity> iterator = bF().iterator();
/*      */         
/* 1529 */         while (iterator.hasNext()) {
/* 1530 */           Entity entity = iterator.next();
/* 1531 */           NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/*      */           
/* 1533 */           if (entity.c(nbttagcompound1)) {
/* 1534 */             nbttaglist.add(nbttagcompound1);
/*      */           }
/*      */         } 
/*      */         
/* 1538 */         if (!nbttaglist.isEmpty()) {
/* 1539 */           nbttagcompound.set("Passengers", nbttaglist);
/*      */         }
/*      */       } 
/*      */       
/* 1543 */       return nbttagcompound;
/* 1544 */     } catch (Throwable throwable) {
/* 1545 */       CrashReport crashreport = CrashReport.a(throwable, "Saving entity NBT");
/* 1546 */       CrashReportSystemDetails crashreportsystemdetails = crashreport.a("Entity being saved");
/*      */       
/* 1548 */       appendEntityCrashDetails(crashreportsystemdetails);
/* 1549 */       throw new ReportedException(crashreport);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void f(NBTTagCompound nbttagcompound) {
/*      */     try {
/* 1555 */       NBTTagList nbttaglist = nbttagcompound.getList("Pos", 6);
/* 1556 */       NBTTagList nbttaglist1 = nbttagcompound.getList("Motion", 6);
/* 1557 */       NBTTagList nbttaglist2 = nbttagcompound.getList("Rotation", 5);
/*      */       
/* 1559 */       this.motX = nbttaglist1.f(0);
/* 1560 */       this.motY = nbttaglist1.f(1);
/* 1561 */       this.motZ = nbttaglist1.f(2);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1577 */       this.locX = nbttaglist.f(0);
/* 1578 */       this.locY = nbttaglist.f(1);
/* 1579 */       this.locZ = nbttaglist.f(2);
/* 1580 */       this.M = this.locX;
/* 1581 */       this.N = this.locY;
/* 1582 */       this.O = this.locZ;
/* 1583 */       this.lastX = this.locX;
/* 1584 */       this.lastY = this.locY;
/* 1585 */       this.lastZ = this.locZ;
/* 1586 */       this.yaw = nbttaglist2.g(0);
/* 1587 */       this.pitch = nbttaglist2.g(1);
/* 1588 */       this.lastYaw = this.yaw;
/* 1589 */       this.lastPitch = this.pitch;
/* 1590 */       setHeadRotation(this.yaw);
/* 1591 */       h(this.yaw);
/* 1592 */       this.fallDistance = nbttagcompound.getFloat("FallDistance");
/* 1593 */       this.fireTicks = nbttagcompound.getShort("Fire");
/* 1594 */       setAirTicks(nbttagcompound.getShort("Air"));
/* 1595 */       this.onGround = nbttagcompound.getBoolean("OnGround");
/* 1596 */       if (nbttagcompound.hasKey("Dimension")) {
/* 1597 */         this.dimension = nbttagcompound.getInt("Dimension");
/*      */       }
/*      */       
/* 1600 */       this.invulnerable = nbttagcompound.getBoolean("Invulnerable");
/* 1601 */       this.portalCooldown = nbttagcompound.getInt("PortalCooldown");
/* 1602 */       if (nbttagcompound.b("UUID")) {
/* 1603 */         this.uniqueID = nbttagcompound.a("UUID");
/* 1604 */         this.ar = this.uniqueID.toString();
/*      */       } 
/*      */       
/* 1607 */       setPosition(this.locX, this.locY, this.locZ);
/* 1608 */       setYawPitch(this.yaw, this.pitch);
/* 1609 */       if (nbttagcompound.hasKeyOfType("CustomName", 8)) {
/* 1610 */         setCustomName(nbttagcompound.getString("CustomName"));
/*      */       }
/*      */       
/* 1613 */       setCustomNameVisible(nbttagcompound.getBoolean("CustomNameVisible"));
/* 1614 */       this.aG.a(nbttagcompound);
/* 1615 */       setSilent(nbttagcompound.getBoolean("Silent"));
/* 1616 */       setNoGravity(nbttagcompound.getBoolean("NoGravity"));
/* 1617 */       g(nbttagcompound.getBoolean("Glowing"));
/* 1618 */       if (nbttagcompound.hasKeyOfType("Tags", 9)) {
/* 1619 */         this.aH.clear();
/* 1620 */         NBTTagList nbttaglist3 = nbttagcompound.getList("Tags", 8);
/* 1621 */         int i = Math.min(nbttaglist3.size(), 1024);
/*      */         
/* 1623 */         for (int j = 0; j < i; j++) {
/* 1624 */           this.aH.add(nbttaglist3.getString(j));
/*      */         }
/*      */       } 
/*      */       
/* 1628 */       a(nbttagcompound);
/* 1629 */       if (aA()) {
/* 1630 */         setPosition(this.locX, this.locY, this.locZ);
/*      */       }
/*      */ 
/*      */       
/* 1634 */       if (this instanceof EntityLiving) {
/* 1635 */         EntityLiving entity = (EntityLiving)this;
/*      */         
/* 1637 */         this.ticksLived = nbttagcompound.getInt("Spigot.ticksLived");
/*      */ 
/*      */         
/* 1640 */         if (entity instanceof EntityTameableAnimal && !isLevelAtLeast(nbttagcompound, 2) && !nbttagcompound.getBoolean("PersistenceRequired")) {
/* 1641 */           EntityInsentient entityinsentient = (EntityInsentient)entity;
/* 1642 */           entityinsentient.persistent = !entityinsentient.isTypeNotPersistent();
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1648 */       if (!(getBukkitEntity() instanceof Vehicle)) {
/* 1649 */         if (Math.abs(this.motX) > 10.0D) {
/* 1650 */           this.motX = 0.0D;
/*      */         }
/*      */         
/* 1653 */         if (Math.abs(this.motY) > 10.0D) {
/* 1654 */           this.motY = 0.0D;
/*      */         }
/*      */         
/* 1657 */         if (Math.abs(this.motZ) > 10.0D) {
/* 1658 */           this.motZ = 0.0D;
/*      */         }
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1664 */       if (this instanceof EntityPlayer) {
/* 1665 */         CraftWorld craftWorld; Server server = Bukkit.getServer();
/* 1666 */         World bworld = null;
/*      */ 
/*      */         
/* 1669 */         String worldName = nbttagcompound.getString("world");
/*      */         
/* 1671 */         if (nbttagcompound.hasKey("WorldUUIDMost") && nbttagcompound.hasKey("WorldUUIDLeast")) {
/* 1672 */           UUID uid = new UUID(nbttagcompound.getLong("WorldUUIDMost"), nbttagcompound.getLong("WorldUUIDLeast"));
/* 1673 */           bworld = server.getWorld(uid);
/*      */         } else {
/* 1675 */           bworld = server.getWorld(worldName);
/*      */         } 
/*      */         
/* 1678 */         if (bworld == null) {
/* 1679 */           EntityPlayer entityPlayer = (EntityPlayer)this;
/* 1680 */           craftWorld = ((CraftServer)server).getServer().getWorldServer(entityPlayer.dimension).getWorld();
/*      */         } 
/*      */         
/* 1683 */         spawnIn((craftWorld == null) ? null : craftWorld.getHandle());
/*      */       }
/*      */     
/*      */     }
/* 1687 */     catch (Throwable throwable) {
/* 1688 */       CrashReport crashreport = CrashReport.a(throwable, "Loading entity NBT");
/* 1689 */       CrashReportSystemDetails crashreportsystemdetails = crashreport.a("Entity being loaded");
/*      */       
/* 1691 */       appendEntityCrashDetails(crashreportsystemdetails);
/* 1692 */       throw new ReportedException(crashreport);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected boolean aA() {
/* 1697 */     return true;
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public final String getSaveID() {
/* 1702 */     MinecraftKey minecraftkey = EntityTypes.a(this);
/*      */     
/* 1704 */     return (minecraftkey == null) ? null : minecraftkey.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected NBTTagList a(double... adouble) {
/* 1712 */     NBTTagList nbttaglist = new NBTTagList();
/* 1713 */     double[] adouble1 = adouble;
/* 1714 */     int i = adouble.length;
/*      */     
/* 1716 */     for (int j = 0; j < i; j++) {
/* 1717 */       double d0 = adouble1[j];
/*      */       
/* 1719 */       nbttaglist.add(new NBTTagDouble(d0));
/*      */     } 
/*      */     
/* 1722 */     return nbttaglist;
/*      */   }
/*      */   
/*      */   protected NBTTagList a(float... afloat) {
/* 1726 */     NBTTagList nbttaglist = new NBTTagList();
/* 1727 */     float[] afloat1 = afloat;
/* 1728 */     int i = afloat.length;
/*      */     
/* 1730 */     for (int j = 0; j < i; j++) {
/* 1731 */       float f = afloat1[j];
/*      */       
/* 1733 */       nbttaglist.add(new NBTTagFloat(f));
/*      */     } 
/*      */     
/* 1736 */     return nbttaglist;
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public EntityItem a(Item item, int i) {
/* 1741 */     return a(item, i, 0.0F);
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public EntityItem a(Item item, int i, float f) {
/* 1746 */     return a(new ItemStack(item, i, 0), f);
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public EntityItem a(ItemStack itemstack, float f) {
/* 1751 */     if (itemstack.isEmpty()) {
/* 1752 */       return null;
/*      */     }
/*      */     
/* 1755 */     if (this instanceof EntityLiving && !((EntityLiving)this).forceDrops) {
/* 1756 */       ((EntityLiving)this).drops.add(CraftItemStack.asBukkitCopy(itemstack));
/* 1757 */       return null;
/*      */     } 
/*      */     
/* 1760 */     EntityItem entityitem = new EntityItem(this.world, this.locX, this.locY + f, this.locZ, itemstack);
/*      */     
/* 1762 */     entityitem.q();
/* 1763 */     this.world.addEntity(entityitem);
/* 1764 */     return entityitem;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isAlive() {
/* 1769 */     return !this.dead;
/*      */   }
/*      */   
/*      */   public boolean inBlock() {
/* 1773 */     if (this.noclip) {
/* 1774 */       return false;
/*      */     }
/* 1776 */     BlockPosition.PooledBlockPosition blockposition_pooledblockposition = BlockPosition.PooledBlockPosition.s();
/*      */     
/* 1778 */     for (int i = 0; i < 8; i++) {
/* 1779 */       int j = MathHelper.floor(this.locY + ((((i >> 0) % 2) - 0.5F) * 0.1F) + getHeadHeight());
/* 1780 */       int k = MathHelper.floor(this.locX + ((((i >> 1) % 2) - 0.5F) * this.width * 0.8F));
/* 1781 */       int l = MathHelper.floor(this.locZ + ((((i >> 2) % 2) - 0.5F) * this.width * 0.8F));
/*      */       
/* 1783 */       if (blockposition_pooledblockposition.getX() != k || blockposition_pooledblockposition.getY() != j || blockposition_pooledblockposition.getZ() != l) {
/* 1784 */         blockposition_pooledblockposition.f(k, j, l);
/* 1785 */         if (this.world.getType(blockposition_pooledblockposition).r()) {
/* 1786 */           blockposition_pooledblockposition.t();
/* 1787 */           return true;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 1792 */     blockposition_pooledblockposition.t();
/* 1793 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean b(EntityHuman entityhuman, EnumHand enumhand) {
/* 1798 */     return false;
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public AxisAlignedBB j(Entity entity) {
/* 1803 */     return null;
/*      */   }
/*      */   
/*      */   public void aE() {
/* 1807 */     Entity entity = bJ();
/*      */     
/* 1809 */     if (isPassenger() && entity.dead) {
/* 1810 */       stopRiding();
/*      */     } else {
/* 1812 */       this.motX = 0.0D;
/* 1813 */       this.motY = 0.0D;
/* 1814 */       this.motZ = 0.0D;
/* 1815 */       B_();
/* 1816 */       if (isPassenger()) {
/* 1817 */         entity.k(this);
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   public void k(Entity entity) {
/* 1823 */     if (w(entity)) {
/* 1824 */       entity.setPosition(this.locX, this.locY + aG() + entity.aF(), this.locZ);
/*      */     }
/*      */   }
/*      */   
/*      */   public double aF() {
/* 1829 */     return 0.0D;
/*      */   }
/*      */   
/*      */   public double aG() {
/* 1833 */     return this.length * 0.75D;
/*      */   }
/*      */   
/*      */   public boolean startRiding(Entity entity) {
/* 1837 */     return a(entity, false);
/*      */   }
/*      */   
/*      */   public boolean a(Entity entity, boolean flag) {
/* 1841 */     for (Entity entity1 = entity; entity1.au != null; entity1 = entity1.au) {
/* 1842 */       if (entity1.au == this) {
/* 1843 */         return false;
/*      */       }
/*      */     } 
/*      */     
/* 1847 */     if (!flag && (!n(entity) || !entity.q(this))) {
/* 1848 */       return false;
/*      */     }
/* 1850 */     if (isPassenger()) {
/* 1851 */       stopRiding();
/*      */     }
/*      */     
/* 1854 */     this.au = entity;
/* 1855 */     this.au.o(this);
/* 1856 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean n(Entity entity) {
/* 1861 */     return (this.j <= 0);
/*      */   }
/*      */   
/*      */   public void ejectPassengers() {
/* 1865 */     for (int i = this.passengers.size() - 1; i >= 0; i--) {
/* 1866 */       ((Entity)this.passengers.get(i)).stopRiding();
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void stopRiding() {
/* 1872 */     if (this.au != null) {
/* 1873 */       Entity entity = this.au;
/*      */       
/* 1875 */       this.au = null;
/* 1876 */       entity.p(this);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void o(Entity entity) {
/* 1882 */     if (entity.bJ() != this) {
/* 1883 */       throw new IllegalStateException("Use x.startRiding(y), not y.addPassenger(x)");
/*      */     }
/*      */     
/* 1886 */     Preconditions.checkState(!entity.passengers.contains(this), "Circular entity riding! %s %s", this, entity);
/*      */     
/* 1888 */     CraftEntity craft = (CraftEntity)entity.getBukkitEntity().getVehicle();
/* 1889 */     Entity orig = (craft == null) ? null : craft.getHandle();
/* 1890 */     if (getBukkitEntity() instanceof Vehicle && entity.getBukkitEntity() instanceof LivingEntity && entity.world.isChunkLoaded((int)entity.locX >> 4, (int)entity.locZ >> 4, false)) {
/* 1891 */       VehicleEnterEvent vehicleEnterEvent = new VehicleEnterEvent(
/* 1892 */           (Vehicle)getBukkitEntity(), 
/* 1893 */           (org.bukkit.entity.Entity)entity.getBukkitEntity());
/*      */       
/* 1895 */       Bukkit.getPluginManager().callEvent((Event)vehicleEnterEvent);
/* 1896 */       CraftEntity craftn = (CraftEntity)entity.getBukkitEntity().getVehicle();
/* 1897 */       Entity n = (craftn == null) ? null : craftn.getHandle();
/* 1898 */       if (vehicleEnterEvent.isCancelled() || n != orig) {
/*      */         return;
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1904 */     EntityMountEvent event = new EntityMountEvent((org.bukkit.entity.Entity)entity.getBukkitEntity(), (org.bukkit.entity.Entity)getBukkitEntity());
/* 1905 */     Bukkit.getPluginManager().callEvent((Event)event);
/* 1906 */     if (event.isCancelled()) {
/*      */       return;
/*      */     }
/*      */     
/* 1910 */     if (!this.world.isClientSide && entity instanceof EntityHuman && !(bE() instanceof EntityHuman)) {
/* 1911 */       this.passengers.add(0, entity);
/*      */     } else {
/* 1913 */       this.passengers.add(entity);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void p(Entity entity) {
/* 1920 */     if (entity.bJ() == this) {
/* 1921 */       throw new IllegalStateException("Use x.stopRiding(y), not y.removePassenger(x)");
/*      */     }
/*      */     
/* 1924 */     CraftEntity craft = (CraftEntity)entity.getBukkitEntity().getVehicle();
/* 1925 */     Entity orig = (craft == null) ? null : craft.getHandle();
/* 1926 */     if (getBukkitEntity() instanceof Vehicle && entity.getBukkitEntity() instanceof LivingEntity) {
/* 1927 */       VehicleExitEvent event = new VehicleExitEvent(
/* 1928 */           (Vehicle)getBukkitEntity(), 
/* 1929 */           (LivingEntity)entity.getBukkitEntity());
/*      */       
/* 1931 */       Bukkit.getPluginManager().callEvent((Event)event);
/* 1932 */       CraftEntity craftn = (CraftEntity)entity.getBukkitEntity().getVehicle();
/* 1933 */       Entity n = (craftn == null) ? null : craftn.getHandle();
/* 1934 */       if (event.isCancelled() || n != orig) {
/*      */         return;
/*      */       }
/*      */     } 
/*      */     
/* 1939 */     Bukkit.getPluginManager().callEvent((Event)new EntityDismountEvent((org.bukkit.entity.Entity)entity.getBukkitEntity(), (org.bukkit.entity.Entity)getBukkitEntity()));
/* 1940 */     this.passengers.remove(entity);
/* 1941 */     entity.j = 60;
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean q(Entity entity) {
/* 1946 */     return (bF().size() < 1);
/*      */   }
/*      */   
/*      */   public float aI() {
/* 1950 */     return 0.0F;
/*      */   }
/*      */   
/*      */   public Vec3D aJ() {
/* 1954 */     return f(this.pitch, this.yaw);
/*      */   }
/*      */   
/*      */   public void e(BlockPosition blockposition) {
/* 1958 */     if (this.portalCooldown > 0) {
/* 1959 */       this.portalCooldown = aM();
/*      */     } else {
/* 1961 */       if (!this.world.isClientSide && !blockposition.equals(this.an)) {
/* 1962 */         this.an = new BlockPosition(blockposition);
/* 1963 */         ShapeDetector.ShapeDetectorCollection shapedetector_shapedetectorcollection = Blocks.PORTAL.c(this.world, this.an);
/* 1964 */         double d0 = (shapedetector_shapedetectorcollection.getFacing().k() == EnumDirection.EnumAxis.X) ? shapedetector_shapedetectorcollection.a().getZ() : shapedetector_shapedetectorcollection.a().getX();
/* 1965 */         double d1 = (shapedetector_shapedetectorcollection.getFacing().k() == EnumDirection.EnumAxis.X) ? this.locZ : this.locX;
/*      */         
/* 1967 */         d1 = Math.abs(MathHelper.c(d1 - ((shapedetector_shapedetectorcollection.getFacing().e().c() == EnumDirection.EnumAxisDirection.NEGATIVE) ? true : false), d0, d0 - shapedetector_shapedetectorcollection.d()));
/* 1968 */         double d2 = MathHelper.c(this.locY - 1.0D, shapedetector_shapedetectorcollection.a().getY(), (shapedetector_shapedetectorcollection.a().getY() - shapedetector_shapedetectorcollection.e()));
/*      */         
/* 1970 */         this.ao = new Vec3D(d1, d2, 0.0D);
/* 1971 */         this.ap = shapedetector_shapedetectorcollection.getFacing();
/*      */       } 
/*      */       
/* 1974 */       this.ak = true;
/*      */     } 
/*      */   }
/*      */   
/*      */   public int aM() {
/* 1979 */     return 300;
/*      */   }
/*      */   
/*      */   public Iterable<ItemStack> aO() {
/* 1983 */     return b;
/*      */   }
/*      */   
/*      */   public Iterable<ItemStack> getArmorItems() {
/* 1987 */     return b;
/*      */   }
/*      */   
/*      */   public Iterable<ItemStack> aQ() {
/* 1991 */     return Iterables.concat(aO(), getArmorItems());
/*      */   }
/*      */   
/*      */   public void setEquipment(EnumItemSlot enumitemslot, ItemStack itemstack) {}
/*      */   
/*      */   public boolean isBurning() {
/* 1997 */     boolean flag = (this.world != null && this.world.isClientSide);
/*      */     
/* 1999 */     return (!this.fireProof && (this.fireTicks > 0 || (flag && getFlag(0))));
/*      */   }
/*      */   
/*      */   public boolean isPassenger() {
/* 2003 */     return (bJ() != null);
/*      */   }
/*      */   
/*      */   public boolean isVehicle() {
/* 2007 */     return !bF().isEmpty();
/*      */   }
/*      */   
/*      */   public boolean isSneaking() {
/* 2011 */     return getFlag(1);
/*      */   }
/*      */   
/*      */   public void setSneaking(boolean flag) {
/* 2015 */     setFlag(1, flag);
/*      */   }
/*      */   
/*      */   public boolean isSprinting() {
/* 2019 */     return getFlag(3);
/*      */   }
/*      */   
/*      */   public void setSprinting(boolean flag) {
/* 2023 */     setFlag(3, flag);
/*      */   }
/*      */   
/*      */   public boolean aW() {
/* 2027 */     return !(!this.glowing && (!this.world.isClientSide || !getFlag(6)));
/*      */   }
/*      */   
/*      */   public void g(boolean flag) {
/* 2031 */     this.glowing = flag;
/* 2032 */     if (!this.world.isClientSide) {
/* 2033 */       setFlag(6, this.glowing);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isInvisible() {
/* 2039 */     return getFlag(5);
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public ScoreboardTeamBase aY() {
/* 2044 */     return this.world.getScoreboard().getPlayerTeam(bn());
/*      */   }
/*      */   
/*      */   public boolean r(Entity entity) {
/* 2048 */     return a(entity.aY());
/*      */   }
/*      */   
/*      */   public boolean a(ScoreboardTeamBase scoreboardteambase) {
/* 2052 */     return (aY() != null) ? aY().isAlly(scoreboardteambase) : false;
/*      */   }
/*      */   
/*      */   public void setInvisible(boolean flag) {
/* 2056 */     setFlag(5, flag);
/*      */   }
/*      */   
/*      */   public boolean getFlag(int i) {
/* 2060 */     return ((((Byte)this.datawatcher.<Byte>get(Z)).byteValue() & 1 << i) != 0);
/*      */   }
/*      */   
/*      */   public void setFlag(int i, boolean flag) {
/* 2064 */     byte b0 = ((Byte)this.datawatcher.<Byte>get(Z)).byteValue();
/*      */     
/* 2066 */     if (flag) {
/* 2067 */       this.datawatcher.set(Z, Byte.valueOf((byte)(b0 | 1 << i)));
/*      */     } else {
/* 2069 */       this.datawatcher.set(Z, Byte.valueOf((byte)(b0 & (1 << i ^ 0xFFFFFFFF))));
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public int getAirTicks() {
/* 2075 */     return ((Integer)this.datawatcher.<Integer>get(aA)).intValue();
/*      */   }
/*      */ 
/*      */   
/*      */   public void setAirTicks(int i) {
/* 2080 */     EntityAirChangeEvent event = new EntityAirChangeEvent((org.bukkit.entity.Entity)getBukkitEntity(), i);
/* 2081 */     event.getEntity().getServer().getPluginManager().callEvent((Event)event);
/* 2082 */     if (event.isCancelled()) {
/*      */       return;
/*      */     }
/* 2085 */     this.datawatcher.set(aA, Integer.valueOf(event.getAmount()));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void onLightningStrike(EntityLightning entitylightning) {
/* 2091 */     CraftEntity craftEntity1 = getBukkitEntity();
/* 2092 */     CraftEntity craftEntity2 = entitylightning.getBukkitEntity();
/* 2093 */     PluginManager pluginManager = Bukkit.getPluginManager();
/*      */     
/* 2095 */     if (craftEntity1 instanceof Hanging) {
/* 2096 */       HangingBreakByEntityEvent hangingEvent = new HangingBreakByEntityEvent((Hanging)craftEntity1, (org.bukkit.entity.Entity)craftEntity2);
/* 2097 */       pluginManager.callEvent((Event)hangingEvent);
/*      */       
/* 2099 */       if (hangingEvent.isCancelled()) {
/*      */         return;
/*      */       }
/*      */     } 
/*      */     
/* 2104 */     if (this.fireProof) {
/*      */       return;
/*      */     }
/* 2107 */     CraftEventFactory.entityDamage = entitylightning;
/* 2108 */     if (!damageEntity(DamageSource.LIGHTNING, 5.0F)) {
/* 2109 */       CraftEventFactory.entityDamage = null;
/*      */       
/*      */       return;
/*      */     } 
/* 2113 */     this.fireTicks++;
/* 2114 */     if (this.fireTicks == 0) {
/*      */       
/* 2116 */       EntityCombustByEntityEvent entityCombustEvent = new EntityCombustByEntityEvent((org.bukkit.entity.Entity)craftEntity2, (org.bukkit.entity.Entity)craftEntity1, 8);
/* 2117 */       pluginManager.callEvent((Event)entityCombustEvent);
/* 2118 */       if (!entityCombustEvent.isCancelled()) {
/* 2119 */         setOnFire(entityCombustEvent.getDuration());
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void b(EntityLiving entityliving) {}
/*      */ 
/*      */   
/*      */   protected boolean i(double d0, double d1, double d2) {
/* 2129 */     BlockPosition blockposition = new BlockPosition(d0, d1, d2);
/* 2130 */     double d3 = d0 - blockposition.getX();
/* 2131 */     double d4 = d1 - blockposition.getY();
/* 2132 */     double d5 = d2 - blockposition.getZ();
/*      */     
/* 2134 */     if (!this.world.a(getBoundingBox())) {
/* 2135 */       return false;
/*      */     }
/* 2137 */     EnumDirection enumdirection = EnumDirection.UP;
/* 2138 */     double d6 = Double.MAX_VALUE;
/*      */     
/* 2140 */     if (!this.world.t(blockposition.west()) && d3 < d6) {
/* 2141 */       d6 = d3;
/* 2142 */       enumdirection = EnumDirection.WEST;
/*      */     } 
/*      */     
/* 2145 */     if (!this.world.t(blockposition.east()) && 1.0D - d3 < d6) {
/* 2146 */       d6 = 1.0D - d3;
/* 2147 */       enumdirection = EnumDirection.EAST;
/*      */     } 
/*      */     
/* 2150 */     if (!this.world.t(blockposition.north()) && d5 < d6) {
/* 2151 */       d6 = d5;
/* 2152 */       enumdirection = EnumDirection.NORTH;
/*      */     } 
/*      */     
/* 2155 */     if (!this.world.t(blockposition.south()) && 1.0D - d5 < d6) {
/* 2156 */       d6 = 1.0D - d5;
/* 2157 */       enumdirection = EnumDirection.SOUTH;
/*      */     } 
/*      */     
/* 2160 */     if (!this.world.t(blockposition.up()) && 1.0D - d4 < d6) {
/* 2161 */       d6 = 1.0D - d4;
/* 2162 */       enumdirection = EnumDirection.UP;
/*      */     } 
/*      */     
/* 2165 */     float f = this.random.nextFloat() * 0.2F + 0.1F;
/* 2166 */     float f1 = enumdirection.c().a();
/*      */     
/* 2168 */     if (enumdirection.k() == EnumDirection.EnumAxis.X) {
/* 2169 */       this.motX = (f1 * f);
/* 2170 */       this.motY *= 0.75D;
/* 2171 */       this.motZ *= 0.75D;
/* 2172 */     } else if (enumdirection.k() == EnumDirection.EnumAxis.Y) {
/* 2173 */       this.motX *= 0.75D;
/* 2174 */       this.motY = (f1 * f);
/* 2175 */       this.motZ *= 0.75D;
/* 2176 */     } else if (enumdirection.k() == EnumDirection.EnumAxis.Z) {
/* 2177 */       this.motX *= 0.75D;
/* 2178 */       this.motY *= 0.75D;
/* 2179 */       this.motZ = (f1 * f);
/*      */     } 
/*      */     
/* 2182 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public void ba() {
/* 2187 */     this.E = true;
/* 2188 */     this.fallDistance = 0.0F;
/*      */   }
/*      */   
/*      */   public String getName() {
/* 2192 */     if (hasCustomName()) {
/* 2193 */       return getCustomName();
/*      */     }
/* 2195 */     String s = EntityTypes.b(this);
/*      */     
/* 2197 */     if (s == null) {
/* 2198 */       s = "generic";
/*      */     }
/*      */     
/* 2201 */     return LocaleI18n.get("entity." + s + ".name");
/*      */   }
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public Entity[] bb() {
/* 2207 */     return null;
/*      */   }
/*      */   
/*      */   public boolean s(Entity entity) {
/* 2211 */     return (this == entity);
/*      */   }
/*      */   
/*      */   public float getHeadRotation() {
/* 2215 */     return 0.0F;
/*      */   }
/*      */   
/*      */   public void setHeadRotation(float f) {}
/*      */   
/*      */   public void h(float f) {}
/*      */   
/*      */   public boolean bd() {
/* 2223 */     return true;
/*      */   }
/*      */   
/*      */   public boolean t(Entity entity) {
/* 2227 */     return false;
/*      */   }
/*      */   
/*      */   public String toString() {
/* 2231 */     return String.format("%s['%s'/%d, l='%s', x=%.2f, y=%.2f, z=%.2f]", new Object[] { getClass().getSimpleName(), getName(), Integer.valueOf(this.id), (this.world == null) ? "~NULL~" : this.world.getWorldData().getName(), Double.valueOf(this.locX), Double.valueOf(this.locY), Double.valueOf(this.locZ) });
/*      */   }
/*      */   
/*      */   public boolean isInvulnerable(DamageSource damagesource) {
/* 2235 */     return (this.invulnerable && damagesource != DamageSource.OUT_OF_WORLD && !damagesource.u());
/*      */   }
/*      */   
/*      */   public boolean be() {
/* 2239 */     return this.invulnerable;
/*      */   }
/*      */   
/*      */   public void setInvulnerable(boolean flag) {
/* 2243 */     this.invulnerable = flag;
/*      */   }
/*      */   
/*      */   public void u(Entity entity) {
/* 2247 */     setPositionRotation(entity.locX, entity.locY, entity.locZ, entity.yaw, entity.pitch);
/*      */   }
/*      */   
/*      */   private void a(Entity entity) {
/* 2251 */     NBTTagCompound nbttagcompound = entity.save(new NBTTagCompound());
/*      */     
/* 2253 */     nbttagcompound.remove("Dimension");
/* 2254 */     f(nbttagcompound);
/* 2255 */     this.portalCooldown = entity.portalCooldown;
/* 2256 */     this.an = entity.an;
/* 2257 */     this.ao = entity.ao;
/* 2258 */     this.ap = entity.ap;
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public Entity b(int i) {
/* 2263 */     if (!this.world.isClientSide && !this.dead) {
/* 2264 */       this.world.methodProfiler.a("changeDimension");
/* 2265 */       MinecraftServer minecraftserver = C_();
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2270 */       WorldServer exitWorld = null;
/* 2271 */       if (this.dimension < 10)
/*      */       {
/* 2273 */         for (WorldServer world : minecraftserver.worlds) {
/* 2274 */           if (world.dimension == i) {
/* 2275 */             exitWorld = world;
/*      */           }
/*      */         } 
/*      */       }
/*      */       
/* 2280 */       BlockPosition blockposition = null;
/* 2281 */       Location enter = getBukkitEntity().getLocation();
/*      */       
/* 2283 */       if (exitWorld != null) {
/* 2284 */         if (blockposition != null) {
/* 2285 */           exit = new Location((World)exitWorld.getWorld(), blockposition.getX(), blockposition.getY(), blockposition.getZ());
/*      */         } else {
/* 2287 */           exit = minecraftserver.getPlayerList().calculateTarget(enter, minecraftserver.getWorldServer(i));
/*      */         } 
/*      */       } else {
/*      */         
/* 2291 */         exit = null;
/*      */       } 
/* 2293 */       boolean useTravelAgent = (exitWorld != null && (this.dimension != 1 || exitWorld.dimension != 1));
/*      */       
/* 2295 */       TravelAgent agent = (exit != null) ? (TravelAgent)((CraftWorld)exit.getWorld()).getHandle().getTravelAgent() : CraftTravelAgent.DEFAULT;
/* 2296 */       boolean oldCanCreate = agent.getCanCreatePortal();
/* 2297 */       agent.setCanCreatePortal(false);
/*      */       
/* 2299 */       EntityPortalEvent event = new EntityPortalEvent((org.bukkit.entity.Entity)getBukkitEntity(), enter, exit, agent);
/* 2300 */       event.useTravelAgent(useTravelAgent);
/* 2301 */       event.getEntity().getServer().getPluginManager().callEvent((Event)event);
/* 2302 */       if (event.isCancelled() || event.getTo() == null || event.getTo().getWorld() == null || !isAlive()) {
/* 2303 */         return null;
/*      */       }
/* 2305 */       Location exit = event.useTravelAgent() ? event.getPortalTravelAgent().findOrCreate(event.getTo()) : event.getTo();
/* 2306 */       agent.setCanCreatePortal(oldCanCreate);
/*      */ 
/*      */       
/* 2309 */       Entity entity = teleportTo(exit, true);
/* 2310 */       this.world.methodProfiler.b();
/* 2311 */       return entity;
/*      */     } 
/* 2313 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public Entity teleportTo(Location exit, boolean portal) {
/* 2318 */     WorldServer worldserver = ((CraftWorld)getBukkitEntity().getLocation().getWorld()).getHandle();
/* 2319 */     WorldServer worldserver1 = ((CraftWorld)exit.getWorld()).getHandle();
/* 2320 */     int i = worldserver1.dimension;
/*      */ 
/*      */     
/* 2323 */     this.dimension = i;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2331 */     this.world.kill(this);
/* 2332 */     this.dead = false;
/* 2333 */     this.world.methodProfiler.a("reposition");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2366 */     worldserver1.getMinecraftServer().getPlayerList().repositionEntity(this, exit, portal);
/*      */ 
/*      */     
/* 2369 */     this.world.methodProfiler.c("reloading");
/* 2370 */     Entity entity = EntityTypes.a((Class)getClass(), worldserver1);
/*      */     
/* 2372 */     if (entity != null) {
/* 2373 */       entity.a(this);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2384 */       boolean flag = entity.attachedToPlayer;
/*      */       
/* 2386 */       entity.attachedToPlayer = true;
/* 2387 */       worldserver1.addEntity(entity);
/* 2388 */       entity.attachedToPlayer = flag;
/* 2389 */       worldserver1.entityJoinedWorld(entity, false);
/*      */       
/* 2391 */       getBukkitEntity().setHandle(entity);
/* 2392 */       entity.bukkitEntity = getBukkitEntity();
/*      */       
/* 2394 */       if (this instanceof EntityInsentient) {
/* 2395 */         ((EntityInsentient)this).unleash(true, false);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 2400 */     this.dead = true;
/* 2401 */     this.world.methodProfiler.b();
/* 2402 */     worldserver.m();
/* 2403 */     worldserver1.m();
/*      */     
/* 2405 */     return entity;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean bf() {
/* 2412 */     return true;
/*      */   }
/*      */   
/*      */   public float a(Explosion explosion, World world, BlockPosition blockposition, IBlockData iblockdata) {
/* 2416 */     return iblockdata.getBlock().a(this);
/*      */   }
/*      */   
/*      */   public boolean a(Explosion explosion, World world, BlockPosition blockposition, IBlockData iblockdata, float f) {
/* 2420 */     return true;
/*      */   }
/*      */   
/*      */   public int bg() {
/* 2424 */     return 3;
/*      */   }
/*      */   
/*      */   public Vec3D getPortalOffset() {
/* 2428 */     return this.ao;
/*      */   }
/*      */   
/*      */   public EnumDirection getPortalDirection() {
/* 2432 */     return this.ap;
/*      */   }
/*      */   
/*      */   public boolean isIgnoreBlockTrigger() {
/* 2436 */     return false;
/*      */   }
/*      */   
/*      */   public void appendEntityCrashDetails(CrashReportSystemDetails crashreportsystemdetails) {
/* 2440 */     crashreportsystemdetails.a("Entity Type", new CrashReportCallable<String>() {
/*      */           public String a() throws Exception {
/* 2442 */             return EntityTypes.a(Entity.this) + " (" + Entity.this.getClass().getCanonicalName() + ")";
/*      */           }
/*      */           
/*      */           public Object call() throws Exception {
/* 2446 */             return a();
/*      */           }
/*      */         });
/* 2449 */     crashreportsystemdetails.a("Entity ID", Integer.valueOf(this.id));
/* 2450 */     crashreportsystemdetails.a("Entity Name", new CrashReportCallable<String>() {
/*      */           public String a() throws Exception {
/* 2452 */             return Entity.this.getName();
/*      */           }
/*      */           
/*      */           public Object call() throws Exception {
/* 2456 */             return a();
/*      */           }
/*      */         });
/* 2459 */     crashreportsystemdetails.a("Entity's Exact location", String.format("%.2f, %.2f, %.2f", new Object[] { Double.valueOf(this.locX), Double.valueOf(this.locY), Double.valueOf(this.locZ) }));
/* 2460 */     crashreportsystemdetails.a("Entity's Block location", CrashReportSystemDetails.a(MathHelper.floor(this.locX), MathHelper.floor(this.locY), MathHelper.floor(this.locZ)));
/* 2461 */     crashreportsystemdetails.a("Entity's Momentum", String.format("%.2f, %.2f, %.2f", new Object[] { Double.valueOf(this.motX), Double.valueOf(this.motY), Double.valueOf(this.motZ) }));
/* 2462 */     crashreportsystemdetails.a("Entity's Passengers", new CrashReportCallable<String>() {
/*      */           public String a() throws Exception {
/* 2464 */             return Entity.this.bF().toString();
/*      */           }
/*      */           
/*      */           public Object call() throws Exception {
/* 2468 */             return a();
/*      */           }
/*      */         });
/* 2471 */     crashreportsystemdetails.a("Entity's Vehicle", new CrashReportCallable<String>() {
/*      */           public String a() throws Exception {
/* 2473 */             return Entity.this.bJ().toString();
/*      */           }
/*      */           
/*      */           public Object call() throws Exception {
/* 2477 */             return a();
/*      */           }
/*      */         });
/*      */   }
/*      */   
/*      */   public void a(UUID uuid) {
/* 2483 */     this.uniqueID = uuid;
/* 2484 */     this.ar = this.uniqueID.toString();
/*      */   }
/*      */   
/*      */   public UUID getUniqueID() {
/* 2488 */     return this.uniqueID;
/*      */   }
/*      */   
/*      */   public String bn() {
/* 2492 */     return this.ar;
/*      */   }
/*      */   
/*      */   public boolean bo() {
/* 2496 */     return true;
/*      */   }
/*      */   
/*      */   public IChatBaseComponent getScoreboardDisplayName() {
/* 2500 */     ChatComponentText chatcomponenttext = new ChatComponentText(ScoreboardTeam.getPlayerDisplayName(aY(), getName()));
/*      */     
/* 2502 */     chatcomponenttext.getChatModifier().setChatHoverable(bv());
/* 2503 */     chatcomponenttext.getChatModifier().setInsertion(bn());
/* 2504 */     return chatcomponenttext;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setCustomName(String s) {
/* 2509 */     if (s.length() > 256) {
/* 2510 */       s = s.substring(0, 256);
/*      */     }
/*      */     
/* 2513 */     this.datawatcher.set(aB, s);
/*      */   }
/*      */   
/*      */   public String getCustomName() {
/* 2517 */     return this.datawatcher.<String>get(aB);
/*      */   }
/*      */   
/*      */   public boolean hasCustomName() {
/* 2521 */     return !((String)this.datawatcher.<String>get(aB)).isEmpty();
/*      */   }
/*      */   
/*      */   public void setCustomNameVisible(boolean flag) {
/* 2525 */     this.datawatcher.set(aC, Boolean.valueOf(flag));
/*      */   }
/*      */   
/*      */   public boolean getCustomNameVisible() {
/* 2529 */     return ((Boolean)this.datawatcher.<Boolean>get(aC)).booleanValue();
/*      */   }
/*      */   
/*      */   public void enderTeleportTo(double d0, double d1, double d2) {
/* 2533 */     this.aI = true;
/* 2534 */     setPositionRotation(d0, d1, d2, this.yaw, this.pitch);
/* 2535 */     this.world.entityJoinedWorld(this, false);
/*      */   }
/*      */   
/*      */   public void a(DataWatcherObject<?> datawatcherobject) {}
/*      */   
/*      */   public EnumDirection getDirection() {
/* 2541 */     return EnumDirection.fromType2(MathHelper.floor((this.yaw * 4.0F / 360.0F) + 0.5D) & 0x3);
/*      */   }
/*      */   
/*      */   public EnumDirection bu() {
/* 2545 */     return getDirection();
/*      */   }
/*      */   
/*      */   protected ChatHoverable bv() {
/* 2549 */     NBTTagCompound nbttagcompound = new NBTTagCompound();
/* 2550 */     MinecraftKey minecraftkey = EntityTypes.a(this);
/*      */     
/* 2552 */     nbttagcompound.setString("id", bn());
/* 2553 */     if (minecraftkey != null) {
/* 2554 */       nbttagcompound.setString("type", minecraftkey.toString());
/*      */     }
/*      */     
/* 2557 */     nbttagcompound.setString("name", getName());
/* 2558 */     return new ChatHoverable(ChatHoverable.EnumHoverAction.SHOW_ENTITY, new ChatComponentText(nbttagcompound.toString()));
/*      */   }
/*      */   
/*      */   public boolean a(EntityPlayer entityplayer) {
/* 2562 */     return true;
/*      */   }
/*      */   
/*      */   public AxisAlignedBB getBoundingBox() {
/* 2566 */     return this.boundingBox;
/*      */   }
/*      */ 
/*      */   
/*      */   public void a(AxisAlignedBB axisalignedbb) {
/* 2571 */     double a = axisalignedbb.a;
/* 2572 */     double b = axisalignedbb.b;
/* 2573 */     double c = axisalignedbb.c;
/* 2574 */     double d = axisalignedbb.d;
/* 2575 */     double e = axisalignedbb.e;
/* 2576 */     double f = axisalignedbb.f;
/* 2577 */     double len = axisalignedbb.d - axisalignedbb.a;
/* 2578 */     if (len < 0.0D) d = a; 
/* 2579 */     if (len > 64.0D) d = a + 64.0D;
/*      */     
/* 2581 */     len = axisalignedbb.e - axisalignedbb.b;
/* 2582 */     if (len < 0.0D) e = b; 
/* 2583 */     if (len > 64.0D) e = b + 64.0D;
/*      */     
/* 2585 */     len = axisalignedbb.f - axisalignedbb.c;
/* 2586 */     if (len < 0.0D) f = c; 
/* 2587 */     if (len > 64.0D) f = c + 64.0D; 
/* 2588 */     this.boundingBox = new AxisAlignedBB(a, b, c, d, e, f);
/*      */   }
/*      */ 
/*      */   
/*      */   public float getHeadHeight() {
/* 2593 */     return this.length * 0.85F;
/*      */   }
/*      */   
/*      */   public boolean bz() {
/* 2597 */     return this.aw;
/*      */   }
/*      */   
/*      */   public void k(boolean flag) {
/* 2601 */     this.aw = flag;
/*      */   }
/*      */   
/*      */   public boolean c(int i, ItemStack itemstack) {
/* 2605 */     return false;
/*      */   }
/*      */   
/*      */   public void sendMessage(IChatBaseComponent ichatbasecomponent) {}
/*      */   
/*      */   public boolean a(int i, String s) {
/* 2611 */     return true;
/*      */   }
/*      */   
/*      */   public BlockPosition getChunkCoordinates() {
/* 2615 */     return new BlockPosition(this.locX, this.locY + 0.5D, this.locZ);
/*      */   }
/*      */   
/*      */   public Vec3D d() {
/* 2619 */     return new Vec3D(this.locX, this.locY, this.locZ);
/*      */   }
/*      */   
/*      */   public World getWorld() {
/* 2623 */     return this.world;
/*      */   }
/*      */   
/*      */   public Entity f() {
/* 2627 */     return this;
/*      */   }
/*      */   
/*      */   public boolean getSendCommandFeedback() {
/* 2631 */     return false;
/*      */   }
/*      */   
/*      */   public void a(CommandObjectiveExecutor.EnumCommandResult commandobjectiveexecutor_enumcommandresult, int i) {
/* 2635 */     if (this.world != null && !this.world.isClientSide) {
/* 2636 */       this.aG.a(this.world.getMinecraftServer(), this, commandobjectiveexecutor_enumcommandresult, i);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public MinecraftServer C_() {
/* 2643 */     return this.world.getMinecraftServer();
/*      */   }
/*      */   
/*      */   public CommandObjectiveExecutor bA() {
/* 2647 */     return this.aG;
/*      */   }
/*      */   
/*      */   public void v(Entity entity) {
/* 2651 */     this.aG.a(entity.bA());
/*      */   }
/*      */   
/*      */   public EnumInteractionResult a(EntityHuman entityhuman, Vec3D vec3d, EnumHand enumhand) {
/* 2655 */     return EnumInteractionResult.PASS;
/*      */   }
/*      */   
/*      */   public boolean bB() {
/* 2659 */     return false;
/*      */   }
/*      */   
/*      */   protected void a(EntityLiving entityliving, Entity entity) {
/* 2663 */     if (entity instanceof EntityLiving) {
/* 2664 */       EnchantmentManager.a((EntityLiving)entity, entityliving);
/*      */     }
/*      */     
/* 2667 */     EnchantmentManager.b(entityliving, entity);
/*      */   }
/*      */   
/*      */   public void b(EntityPlayer entityplayer) {}
/*      */   
/*      */   public void c(EntityPlayer entityplayer) {}
/*      */   
/*      */   public float a(EnumBlockRotation enumblockrotation) {
/* 2675 */     float f = MathHelper.g(this.yaw);
/*      */     
/* 2677 */     switch (enumblockrotation) {
/*      */       case null:
/* 2679 */         return f + 180.0F;
/*      */       
/*      */       case COUNTERCLOCKWISE_90:
/* 2682 */         return f + 270.0F;
/*      */       
/*      */       case CLOCKWISE_90:
/* 2685 */         return f + 90.0F;
/*      */     } 
/*      */     
/* 2688 */     return f;
/*      */   }
/*      */ 
/*      */   
/*      */   public float a(EnumBlockMirror enumblockmirror) {
/* 2693 */     float f = MathHelper.g(this.yaw);
/*      */     
/* 2695 */     switch (enumblockmirror) {
/*      */       case LEFT_RIGHT:
/* 2697 */         return -f;
/*      */       
/*      */       case null:
/* 2700 */         return 180.0F - f;
/*      */     } 
/*      */     
/* 2703 */     return f;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean bC() {
/* 2708 */     return false;
/*      */   }
/*      */   
/*      */   public boolean bD() {
/* 2712 */     boolean flag = this.aI;
/*      */     
/* 2714 */     this.aI = false;
/* 2715 */     return flag;
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public Entity bE() {
/* 2720 */     return null;
/*      */   }
/*      */   
/*      */   public List<Entity> bF() {
/* 2724 */     return this.passengers.isEmpty() ? Collections.<Entity>emptyList() : Lists.newArrayList(this.passengers);
/*      */   }
/*      */   public boolean w(Entity entity) {
/*      */     Entity entity1;
/* 2728 */     Iterator<Entity> iterator = bF().iterator();
/*      */ 
/*      */ 
/*      */     
/*      */     do {
/* 2733 */       if (!iterator.hasNext()) {
/* 2734 */         return false;
/*      */       }
/*      */       
/* 2737 */       entity1 = iterator.next();
/* 2738 */     } while (!entity1.equals(entity));
/*      */     
/* 2740 */     return true;
/*      */   }
/*      */   
/*      */   public Collection<Entity> bG() {
/* 2744 */     HashSet<Entity> hashset = Sets.newHashSet();
/*      */     
/* 2746 */     a(Entity.class, hashset);
/* 2747 */     return hashset;
/*      */   }
/*      */   
/*      */   public <T extends Entity> Collection<T> b(Class<T> oclass) {
/* 2751 */     HashSet<T> hashset = Sets.newHashSet();
/*      */     
/* 2753 */     a(oclass, hashset);
/* 2754 */     return hashset;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private <T extends Entity> void a(Class<T> oclass, Set<T> set) {
/* 2760 */     for (Iterator<Entity> iterator = bF().iterator(); iterator.hasNext(); entity.a(oclass, set)) {
/* 2761 */       Entity entity = iterator.next();
/* 2762 */       if (oclass.isAssignableFrom(entity.getClass())) {
/* 2763 */         set.add((T)entity);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Entity getVehicle() {
/* 2772 */     for (Entity entity = this; entity.isPassenger(); entity = entity.bJ());
/*      */ 
/*      */ 
/*      */     
/* 2776 */     return entity;
/*      */   }
/*      */   
/*      */   public boolean x(Entity entity) {
/* 2780 */     return (getVehicle() == entity.getVehicle());
/*      */   }
/*      */   public boolean y(Entity entity) {
/*      */     Entity entity1;
/* 2784 */     Iterator<Entity> iterator = bF().iterator();
/*      */ 
/*      */ 
/*      */     
/*      */     do {
/* 2789 */       if (!iterator.hasNext()) {
/* 2790 */         return false;
/*      */       }
/*      */       
/* 2793 */       entity1 = iterator.next();
/* 2794 */       if (entity1.equals(entity)) {
/* 2795 */         return true;
/*      */       }
/* 2797 */     } while (!entity1.y(entity));
/*      */     
/* 2799 */     return true;
/*      */   }
/*      */   
/*      */   public boolean bI() {
/* 2803 */     Entity entity = bE();
/*      */     
/* 2805 */     return (entity instanceof EntityHuman) ? ((EntityHuman)entity).cZ() : (!this.world.isClientSide);
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public Entity bJ() {
/* 2810 */     return this.au;
/*      */   }
/*      */   
/*      */   public EnumPistonReaction o_() {
/* 2814 */     return EnumPistonReaction.NORMAL;
/*      */   }
/*      */   
/*      */   public SoundCategory bK() {
/* 2818 */     return SoundCategory.NEUTRAL;
/*      */   }
/*      */   
/*      */   public int getMaxFireTicks() {
/* 2822 */     return 1;
/*      */   }
/*      */   
/*      */   protected abstract void i();
/*      */   
/*      */   protected abstract void a(NBTTagCompound paramNBTTagCompound);
/*      */   
/*      */   protected abstract void b(NBTTagCompound paramNBTTagCompound);
/*      */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\Entity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */