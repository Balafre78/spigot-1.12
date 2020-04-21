/*      */ package net.minecraft.server.v1_12_R1;
/*      */ import com.mojang.authlib.GameProfile;
/*      */ import java.nio.charset.StandardCharsets;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.UUID;
/*      */ import javax.annotation.Nullable;
/*      */ import org.bukkit.Bukkit;
/*      */ import org.bukkit.OfflinePlayer;
/*      */ import org.bukkit.block.Block;
/*      */ import org.bukkit.craftbukkit.v1_12_R1.entity.CraftEntity;
/*      */ import org.bukkit.craftbukkit.v1_12_R1.entity.CraftHumanEntity;
/*      */ import org.bukkit.craftbukkit.v1_12_R1.entity.CraftItem;
/*      */ import org.bukkit.entity.Entity;
/*      */ import org.bukkit.entity.Player;
/*      */ import org.bukkit.event.Event;
/*      */ import org.bukkit.event.entity.EntityCombustByEntityEvent;
/*      */ import org.bukkit.event.player.PlayerBedEnterEvent;
/*      */ import org.bukkit.event.player.PlayerBedLeaveEvent;
/*      */ import org.bukkit.event.player.PlayerDropItemEvent;
/*      */ import org.bukkit.event.player.PlayerVelocityEvent;
/*      */ import org.bukkit.inventory.ItemStack;
/*      */ import org.bukkit.scoreboard.Team;
/*      */ import org.bukkit.util.Vector;
/*      */ 
/*      */ public abstract class EntityHuman extends EntityLiving {
/*   27 */   private static final DataWatcherObject<Float> a = DataWatcher.a((Class)EntityHuman.class, DataWatcherRegistry.c);
/*   28 */   private static final DataWatcherObject<Integer> b = DataWatcher.a((Class)EntityHuman.class, DataWatcherRegistry.b);
/*   29 */   protected static final DataWatcherObject<Byte> br = DataWatcher.a((Class)EntityHuman.class, DataWatcherRegistry.a);
/*   30 */   protected static final DataWatcherObject<Byte> bs = DataWatcher.a((Class)EntityHuman.class, DataWatcherRegistry.a);
/*   31 */   protected static final DataWatcherObject<NBTTagCompound> bt = DataWatcher.a((Class)EntityHuman.class, DataWatcherRegistry.n);
/*   32 */   protected static final DataWatcherObject<NBTTagCompound> bu = DataWatcher.a((Class)EntityHuman.class, DataWatcherRegistry.n);
/*   33 */   public PlayerInventory inventory = new PlayerInventory(this);
/*   34 */   protected InventoryEnderChest enderChest = new InventoryEnderChest(this);
/*      */   public Container defaultContainer;
/*      */   public Container activeContainer;
/*   37 */   protected FoodMetaData foodData = new FoodMetaData(this);
/*      */   protected int bA;
/*      */   public float bB;
/*      */   public float bC;
/*      */   public int bD;
/*      */   public double bE;
/*      */   public double bF;
/*      */   public double bG;
/*      */   public double bH;
/*      */   public double bI;
/*      */   public double bJ;
/*      */   public boolean sleeping;
/*      */   public BlockPosition bedPosition;
/*      */   public int sleepTicks;
/*      */   public float bM;
/*      */   public float bN;
/*      */   private BlockPosition d;
/*      */   private boolean e;
/*   55 */   public PlayerAbilities abilities = new PlayerAbilities();
/*      */   public int expLevel;
/*      */   public int expTotal;
/*      */   public float exp;
/*      */   protected int bS;
/*   60 */   protected float bT = 0.02F;
/*      */   
/*      */   private int f;
/*      */   
/*      */   private final GameProfile g;
/*      */   private ItemStack bV;
/*      */   private final ItemCooldown bW;
/*      */   @Nullable
/*      */   public EntityFishingHook hookedFish;
/*      */   public boolean fauxSleeping;
/*   70 */   public String spawnWorld = "";
/*   71 */   public int oldLevel = -1;
/*      */ 
/*      */   
/*      */   public CraftHumanEntity getBukkitEntity() {
/*   75 */     return (CraftHumanEntity)super.getBukkitEntity();
/*      */   }
/*      */ 
/*      */   
/*      */   protected ItemCooldown l() {
/*   80 */     return new ItemCooldown();
/*      */   }
/*      */   
/*      */   public EntityHuman(World world, GameProfile gameprofile) {
/*   84 */     super(world);
/*   85 */     this.bV = ItemStack.a;
/*   86 */     this.bW = l();
/*   87 */     a(a(gameprofile));
/*   88 */     this.g = gameprofile;
/*   89 */     this.defaultContainer = new ContainerPlayer(this.inventory, !world.isClientSide, this);
/*   90 */     this.activeContainer = this.defaultContainer;
/*   91 */     BlockPosition blockposition = world.getSpawn();
/*      */     
/*   93 */     setPositionRotation(blockposition.getX() + 0.5D, (blockposition.getY() + 1), blockposition.getZ() + 0.5D, 0.0F, 0.0F);
/*   94 */     this.ba = 180.0F;
/*      */   }
/*      */   
/*      */   protected void initAttributes() {
/*   98 */     super.initAttributes();
/*   99 */     getAttributeMap().b(GenericAttributes.ATTACK_DAMAGE).setValue(1.0D);
/*  100 */     getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.10000000149011612D);
/*  101 */     getAttributeMap().b(GenericAttributes.g);
/*  102 */     getAttributeMap().b(GenericAttributes.j);
/*      */   }
/*      */   
/*      */   protected void i() {
/*  106 */     super.i();
/*  107 */     this.datawatcher.register(a, Float.valueOf(0.0F));
/*  108 */     this.datawatcher.register(b, Integer.valueOf(0));
/*  109 */     this.datawatcher.register(br, Byte.valueOf((byte)0));
/*  110 */     this.datawatcher.register(bs, Byte.valueOf((byte)1));
/*  111 */     this.datawatcher.register(bt, new NBTTagCompound());
/*  112 */     this.datawatcher.register(bu, new NBTTagCompound());
/*      */   }
/*      */   
/*      */   public void B_() {
/*  116 */     this.noclip = isSpectator();
/*  117 */     if (isSpectator()) {
/*  118 */       this.onGround = false;
/*      */     }
/*      */     
/*  121 */     if (this.bD > 0) {
/*  122 */       this.bD--;
/*      */     }
/*      */     
/*  125 */     if (isSleeping()) {
/*  126 */       this.sleepTicks++;
/*  127 */       if (this.sleepTicks > 100) {
/*  128 */         this.sleepTicks = 100;
/*      */       }
/*      */       
/*  131 */       if (!this.world.isClientSide) {
/*  132 */         if (!s()) {
/*  133 */           a(true, true, false);
/*  134 */         } else if (this.world.D()) {
/*  135 */           a(false, true, true);
/*      */         } 
/*      */       }
/*  138 */     } else if (this.sleepTicks > 0) {
/*  139 */       this.sleepTicks++;
/*  140 */       if (this.sleepTicks >= 110) {
/*  141 */         this.sleepTicks = 0;
/*      */       }
/*      */     } 
/*      */     
/*  145 */     super.B_();
/*  146 */     if (!this.world.isClientSide && this.activeContainer != null && !this.activeContainer.a(this)) {
/*  147 */       closeInventory();
/*  148 */       this.activeContainer = this.defaultContainer;
/*      */     } 
/*      */     
/*  151 */     if (isBurning() && this.abilities.isInvulnerable) {
/*  152 */       extinguish();
/*      */     }
/*      */     
/*  155 */     r();
/*  156 */     if (!this.world.isClientSide) {
/*  157 */       this.foodData.a(this);
/*  158 */       b(StatisticList.g);
/*  159 */       if (isAlive()) {
/*  160 */         b(StatisticList.h);
/*      */       }
/*      */       
/*  163 */       if (isSneaking()) {
/*  164 */         b(StatisticList.i);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  169 */     double d0 = MathHelper.a(this.locX, -2.9999999E7D, 2.9999999E7D);
/*  170 */     double d1 = MathHelper.a(this.locZ, -2.9999999E7D, 2.9999999E7D);
/*      */     
/*  172 */     if (d0 != this.locX || d1 != this.locZ) {
/*  173 */       setPosition(d0, this.locY, d1);
/*      */     }
/*      */     
/*  176 */     this.aE++;
/*  177 */     ItemStack itemstack = getItemInMainHand();
/*      */     
/*  179 */     if (!ItemStack.matches(this.bV, itemstack)) {
/*  180 */       if (!ItemStack.d(this.bV, itemstack)) {
/*  181 */         ds();
/*      */       }
/*      */       
/*  184 */       this.bV = itemstack.isEmpty() ? ItemStack.a : itemstack.cloneItemStack();
/*      */     } 
/*      */     
/*  187 */     this.bW.a();
/*  188 */     cT();
/*      */   }
/*      */   
/*      */   private void r() {
/*  192 */     this.bE = this.bH;
/*  193 */     this.bF = this.bI;
/*  194 */     this.bG = this.bJ;
/*  195 */     double d0 = this.locX - this.bH;
/*  196 */     double d1 = this.locY - this.bI;
/*  197 */     double d2 = this.locZ - this.bJ;
/*      */ 
/*      */     
/*  200 */     if (d0 > 10.0D) {
/*  201 */       this.bH = this.locX;
/*  202 */       this.bE = this.bH;
/*      */     } 
/*      */     
/*  205 */     if (d2 > 10.0D) {
/*  206 */       this.bJ = this.locZ;
/*  207 */       this.bG = this.bJ;
/*      */     } 
/*      */     
/*  210 */     if (d1 > 10.0D) {
/*  211 */       this.bI = this.locY;
/*  212 */       this.bF = this.bI;
/*      */     } 
/*      */     
/*  215 */     if (d0 < -10.0D) {
/*  216 */       this.bH = this.locX;
/*  217 */       this.bE = this.bH;
/*      */     } 
/*      */     
/*  220 */     if (d2 < -10.0D) {
/*  221 */       this.bJ = this.locZ;
/*  222 */       this.bG = this.bJ;
/*      */     } 
/*      */     
/*  225 */     if (d1 < -10.0D) {
/*  226 */       this.bI = this.locY;
/*  227 */       this.bF = this.bI;
/*      */     } 
/*      */     
/*  230 */     this.bH += d0 * 0.25D;
/*  231 */     this.bJ += d2 * 0.25D;
/*  232 */     this.bI += d1 * 0.25D;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void cT() {
/*      */     float f;
/*      */     float f1;
/*  239 */     if (cP()) {
/*  240 */       f = 0.6F;
/*  241 */       f1 = 0.6F;
/*  242 */     } else if (isSleeping()) {
/*  243 */       f = 0.2F;
/*  244 */       f1 = 0.2F;
/*  245 */     } else if (isSneaking()) {
/*  246 */       f = 0.6F;
/*  247 */       f1 = 1.65F;
/*      */     } else {
/*  249 */       f = 0.6F;
/*  250 */       f1 = 1.8F;
/*      */     } 
/*      */     
/*  253 */     if (f != this.width || f1 != this.length) {
/*  254 */       AxisAlignedBB axisalignedbb = getBoundingBox();
/*      */       
/*  256 */       axisalignedbb = new AxisAlignedBB(axisalignedbb.a, axisalignedbb.b, axisalignedbb.c, axisalignedbb.a + f, axisalignedbb.b + f1, axisalignedbb.c + f);
/*  257 */       if (!this.world.a(axisalignedbb)) {
/*  258 */         setSize(f, f1);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public int Z() {
/*  265 */     return this.abilities.isInvulnerable ? 1 : 80;
/*      */   }
/*      */   
/*      */   protected SoundEffect ae() {
/*  269 */     return SoundEffects.fL;
/*      */   }
/*      */   
/*      */   protected SoundEffect af() {
/*  273 */     return SoundEffects.fK;
/*      */   }
/*      */   
/*      */   public int aM() {
/*  277 */     return 10;
/*      */   }
/*      */   
/*      */   public void a(SoundEffect soundeffect, float f, float f1) {
/*  281 */     this.world.a(this, this.locX, this.locY, this.locZ, soundeffect, bK(), f, f1);
/*      */   }
/*      */   
/*      */   public SoundCategory bK() {
/*  285 */     return SoundCategory.PLAYERS;
/*      */   }
/*      */   
/*      */   public int getMaxFireTicks() {
/*  289 */     return 20;
/*      */   }
/*      */   
/*      */   protected boolean isFrozen() {
/*  293 */     return !(getHealth() > 0.0F && !isSleeping());
/*      */   }
/*      */   
/*      */   public void closeInventory() {
/*  297 */     this.activeContainer = this.defaultContainer;
/*      */   }
/*      */   
/*      */   public void aE() {
/*  301 */     if (!this.world.isClientSide && isSneaking() && isPassenger()) {
/*  302 */       stopRiding();
/*  303 */       setSneaking(false);
/*      */     } else {
/*  305 */       double d0 = this.locX;
/*  306 */       double d1 = this.locY;
/*  307 */       double d2 = this.locZ;
/*  308 */       float f = this.yaw;
/*  309 */       float f1 = this.pitch;
/*      */       
/*  311 */       super.aE();
/*  312 */       this.bB = this.bC;
/*  313 */       this.bC = 0.0F;
/*  314 */       l(this.locX - d0, this.locY - d1, this.locZ - d2);
/*  315 */       if (bJ() instanceof EntityPig) {
/*  316 */         this.pitch = f1;
/*  317 */         this.yaw = f;
/*  318 */         this.aN = ((EntityPig)bJ()).aN;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void doTick() {
/*  325 */     super.doTick();
/*  326 */     cl();
/*  327 */     this.aP = this.yaw;
/*      */   }
/*      */   
/*      */   public void n() {
/*  331 */     if (this.bA > 0) {
/*  332 */       this.bA--;
/*      */     }
/*      */     
/*  335 */     if (this.world.getDifficulty() == EnumDifficulty.PEACEFUL && this.world.getGameRules().getBoolean("naturalRegeneration")) {
/*  336 */       if (getHealth() < getMaxHealth() && this.ticksLived % 20 == 0)
/*      */       {
/*  338 */         heal(1.0F, EntityRegainHealthEvent.RegainReason.REGEN);
/*      */       }
/*      */       
/*  341 */       if (this.foodData.c() && this.ticksLived % 10 == 0) {
/*  342 */         this.foodData.a(this.foodData.getFoodLevel() + 1);
/*      */       }
/*      */     } 
/*      */     
/*  346 */     this.inventory.n();
/*  347 */     this.bB = this.bC;
/*  348 */     super.n();
/*  349 */     AttributeInstance attributeinstance = getAttributeInstance(GenericAttributes.MOVEMENT_SPEED);
/*      */     
/*  351 */     if (!this.world.isClientSide) {
/*  352 */       attributeinstance.setValue(this.abilities.b());
/*      */     }
/*      */     
/*  355 */     this.aR = this.bT;
/*  356 */     if (isSprinting()) {
/*  357 */       this.aR = (float)(this.aR + this.bT * 0.3D);
/*      */     }
/*      */     
/*  360 */     k((float)attributeinstance.getValue());
/*  361 */     float f = MathHelper.sqrt(this.motX * this.motX + this.motZ * this.motZ);
/*  362 */     float f1 = (float)(TrigMath.atan(-this.motY * 0.20000000298023224D) * 15.0D);
/*      */     
/*  364 */     if (f > 0.1F) {
/*  365 */       f = 0.1F;
/*      */     }
/*      */     
/*  368 */     if (!this.onGround || getHealth() <= 0.0F) {
/*  369 */       f = 0.0F;
/*      */     }
/*      */     
/*  372 */     if (this.onGround || getHealth() <= 0.0F) {
/*  373 */       f1 = 0.0F;
/*      */     }
/*      */     
/*  376 */     this.bC += (f - this.bC) * 0.4F;
/*  377 */     this.aK += (f1 - this.aK) * 0.8F;
/*  378 */     if (getHealth() > 0.0F && !isSpectator()) {
/*      */       AxisAlignedBB axisalignedbb;
/*      */       
/*  381 */       if (isPassenger() && !(bJ()).dead) {
/*  382 */         axisalignedbb = getBoundingBox().b(bJ().getBoundingBox()).grow(1.0D, 0.0D, 1.0D);
/*      */       } else {
/*  384 */         axisalignedbb = getBoundingBox().grow(1.0D, 0.5D, 1.0D);
/*      */       } 
/*      */       
/*  387 */       List<Entity> list = this.world.getEntities(this, axisalignedbb);
/*      */       
/*  389 */       for (int i = 0; i < list.size(); i++) {
/*  390 */         Entity entity = list.get(i);
/*      */         
/*  392 */         if (!entity.dead) {
/*  393 */           c(entity);
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  398 */     j(getShoulderEntityLeft());
/*  399 */     j(getShoulderEntityRight());
/*  400 */     if ((!this.world.isClientSide && (this.fallDistance > 0.5F || isInWater() || isPassenger())) || this.abilities.isFlying) {
/*  401 */       releaseShoulderEntities();
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void j(@Nullable NBTTagCompound nbttagcompound) {
/*  407 */     if ((nbttagcompound != null && !nbttagcompound.hasKey("Silent")) || !nbttagcompound.getBoolean("Silent")) {
/*  408 */       String s = nbttagcompound.getString("id");
/*      */       
/*  410 */       if (s.equals(EntityTypes.getName((Class)EntityParrot.class).toString())) {
/*  411 */         EntityParrot.a(this.world, this);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void c(Entity entity) {
/*  418 */     entity.d(this);
/*      */   }
/*      */   
/*      */   public int getScore() {
/*  422 */     return ((Integer)this.datawatcher.<Integer>get(b)).intValue();
/*      */   }
/*      */   
/*      */   public void setScore(int i) {
/*  426 */     this.datawatcher.set(b, Integer.valueOf(i));
/*      */   }
/*      */   
/*      */   public void addScore(int i) {
/*  430 */     int j = getScore();
/*      */     
/*  432 */     this.datawatcher.set(b, Integer.valueOf(j + i));
/*      */   }
/*      */   
/*      */   public void die(DamageSource damagesource) {
/*  436 */     super.die(damagesource);
/*  437 */     setSize(0.2F, 0.2F);
/*  438 */     setPosition(this.locX, this.locY, this.locZ);
/*  439 */     this.motY = 0.10000000149011612D;
/*  440 */     if ("Notch".equals(getName())) {
/*  441 */       a(new ItemStack(Items.APPLE, 1), true, false);
/*      */     }
/*      */     
/*  444 */     if (!this.world.getGameRules().getBoolean("keepInventory") && !isSpectator()) {
/*  445 */       cV();
/*  446 */       this.inventory.o();
/*      */     } 
/*      */     
/*  449 */     if (damagesource != null) {
/*  450 */       this.motX = (-MathHelper.cos((this.aA + this.yaw) * 0.017453292F) * 0.1F);
/*  451 */       this.motZ = (-MathHelper.sin((this.aA + this.yaw) * 0.017453292F) * 0.1F);
/*      */     } else {
/*  453 */       this.motX = 0.0D;
/*  454 */       this.motZ = 0.0D;
/*      */     } 
/*      */     
/*  457 */     b(StatisticList.A);
/*  458 */     a(StatisticList.h);
/*  459 */     extinguish();
/*  460 */     setFlag(0, false);
/*      */   }
/*      */   
/*      */   protected void cV() {
/*  464 */     for (int i = 0; i < this.inventory.getSize(); i++) {
/*  465 */       ItemStack itemstack = this.inventory.getItem(i);
/*      */       
/*  467 */       if (!itemstack.isEmpty() && EnchantmentManager.shouldNotDrop(itemstack)) {
/*  468 */         this.inventory.splitWithoutUpdate(i);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected SoundEffect d(DamageSource damagesource) {
/*  475 */     return (damagesource == DamageSource.BURN) ? SoundEffects.fH : ((damagesource == DamageSource.DROWN) ? SoundEffects.fG : SoundEffects.fF);
/*      */   }
/*      */   
/*      */   protected SoundEffect cf() {
/*  479 */     return SoundEffects.fE;
/*      */   }
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public EntityItem a(boolean flag) {
/*  485 */     return a(this.inventory.splitStack(this.inventory.itemInHandIndex, (flag && !this.inventory.getItemInHand().isEmpty()) ? this.inventory.getItemInHand().getCount() : 1), false, true);
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public EntityItem drop(ItemStack itemstack, boolean flag) {
/*  490 */     return a(itemstack, false, flag);
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public EntityItem a(ItemStack itemstack, boolean flag, boolean flag1) {
/*  495 */     if (itemstack.isEmpty()) {
/*  496 */       return null;
/*      */     }
/*  498 */     double d0 = this.locY - 0.30000001192092896D + getHeadHeight();
/*  499 */     EntityItem entityitem = new EntityItem(this.world, this.locX, d0, this.locZ, itemstack);
/*      */     
/*  501 */     entityitem.a(40);
/*  502 */     if (flag1) {
/*  503 */       entityitem.e(getName());
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  509 */     if (flag) {
/*  510 */       float f = this.random.nextFloat() * 0.5F;
/*  511 */       float f1 = this.random.nextFloat() * 6.2831855F;
/*  512 */       entityitem.motX = (-MathHelper.sin(f1) * f);
/*  513 */       entityitem.motZ = (MathHelper.cos(f1) * f);
/*  514 */       entityitem.motY = 0.20000000298023224D;
/*      */     } else {
/*  516 */       float f = 0.3F;
/*  517 */       entityitem.motX = (-MathHelper.sin(this.yaw * 0.017453292F) * MathHelper.cos(this.pitch * 0.017453292F) * f);
/*  518 */       entityitem.motZ = (MathHelper.cos(this.yaw * 0.017453292F) * MathHelper.cos(this.pitch * 0.017453292F) * f);
/*  519 */       entityitem.motY = (-MathHelper.sin(this.pitch * 0.017453292F) * f + 0.1F);
/*  520 */       float f1 = this.random.nextFloat() * 6.2831855F;
/*  521 */       f = 0.02F * this.random.nextFloat();
/*  522 */       entityitem.motX += Math.cos(f1) * f;
/*  523 */       entityitem.motY += ((this.random.nextFloat() - this.random.nextFloat()) * 0.1F);
/*  524 */       entityitem.motZ += Math.sin(f1) * f;
/*      */     } 
/*      */ 
/*      */     
/*  528 */     Player player = (Player)getBukkitEntity();
/*  529 */     CraftItem drop = new CraftItem(this.world.getServer(), entityitem);
/*      */     
/*  531 */     PlayerDropItemEvent event = new PlayerDropItemEvent(player, (Item)drop);
/*  532 */     this.world.getServer().getPluginManager().callEvent((Event)event);
/*      */     
/*  534 */     if (event.isCancelled()) {
/*  535 */       ItemStack cur = player.getInventory().getItemInHand();
/*  536 */       if (flag1 && (cur == null || cur.getAmount() == 0)) {
/*      */         
/*  538 */         player.getInventory().setItemInHand(drop.getItemStack());
/*  539 */       } else if (flag1 && cur.isSimilar(drop.getItemStack()) && drop.getItemStack().getAmount() == 1) {
/*      */         
/*  541 */         cur.setAmount(cur.getAmount() + 1);
/*  542 */         player.getInventory().setItemInHand(cur);
/*      */       } else {
/*      */         
/*  545 */         player.getInventory().addItem(new ItemStack[] { drop.getItemStack() });
/*      */       } 
/*  547 */       return null;
/*      */     } 
/*      */ 
/*      */     
/*  551 */     ItemStack itemstack1 = a(entityitem);
/*      */     
/*  553 */     if (flag1) {
/*  554 */       if (!itemstack1.isEmpty()) {
/*  555 */         a(StatisticList.e(itemstack1.getItem()), itemstack.getCount());
/*      */       }
/*      */       
/*  558 */       b(StatisticList.x);
/*      */     } 
/*      */     
/*  561 */     return entityitem;
/*      */   }
/*      */ 
/*      */   
/*      */   protected ItemStack a(EntityItem entityitem) {
/*  566 */     this.world.addEntity(entityitem);
/*  567 */     return entityitem.getItemStack();
/*      */   }
/*      */   
/*      */   public float b(IBlockData iblockdata) {
/*  571 */     float f = this.inventory.a(iblockdata);
/*      */     
/*  573 */     if (f > 1.0F) {
/*  574 */       int i = EnchantmentManager.getDigSpeedEnchantmentLevel(this);
/*  575 */       ItemStack itemstack = getItemInMainHand();
/*      */       
/*  577 */       if (i > 0 && !itemstack.isEmpty()) {
/*  578 */         f += (i * i + 1);
/*      */       }
/*      */     } 
/*      */     
/*  582 */     if (hasEffect(MobEffects.FASTER_DIG)) {
/*  583 */       f *= 1.0F + (getEffect(MobEffects.FASTER_DIG).getAmplifier() + 1) * 0.2F;
/*      */     }
/*      */     
/*  586 */     if (hasEffect(MobEffects.SLOWER_DIG)) {
/*      */       float f1;
/*      */       
/*  589 */       switch (getEffect(MobEffects.SLOWER_DIG).getAmplifier()) {
/*      */         case 0:
/*  591 */           f1 = 0.3F;
/*      */           break;
/*      */         
/*      */         case 1:
/*  595 */           f1 = 0.09F;
/*      */           break;
/*      */         
/*      */         case 2:
/*  599 */           f1 = 0.0027F;
/*      */           break;
/*      */ 
/*      */         
/*      */         default:
/*  604 */           f1 = 8.1E-4F;
/*      */           break;
/*      */       } 
/*  607 */       f *= f1;
/*      */     } 
/*      */     
/*  610 */     if (a(Material.WATER) && !EnchantmentManager.h(this)) {
/*  611 */       f /= 5.0F;
/*      */     }
/*      */     
/*  614 */     if (!this.onGround) {
/*  615 */       f /= 5.0F;
/*      */     }
/*      */     
/*  618 */     return f;
/*      */   }
/*      */   
/*      */   public boolean hasBlock(IBlockData iblockdata) {
/*  622 */     return this.inventory.b(iblockdata);
/*      */   }
/*      */   
/*      */   public static void c(DataConverterManager dataconvertermanager) {
/*  626 */     dataconvertermanager.a(DataConverterTypes.PLAYER, new DataInspector() {
/*      */           public NBTTagCompound a(DataConverter dataconverter, NBTTagCompound nbttagcompound, int i) {
/*  628 */             DataConverterRegistry.b(dataconverter, nbttagcompound, i, "Inventory");
/*  629 */             DataConverterRegistry.b(dataconverter, nbttagcompound, i, "EnderItems");
/*  630 */             if (nbttagcompound.hasKeyOfType("ShoulderEntityLeft", 10)) {
/*  631 */               nbttagcompound.set("ShoulderEntityLeft", dataconverter.a(DataConverterTypes.ENTITY, nbttagcompound.getCompound("ShoulderEntityLeft"), i));
/*      */             }
/*      */             
/*  634 */             if (nbttagcompound.hasKeyOfType("ShoulderEntityRight", 10)) {
/*  635 */               nbttagcompound.set("ShoulderEntityRight", dataconverter.a(DataConverterTypes.ENTITY, nbttagcompound.getCompound("ShoulderEntityRight"), i));
/*      */             }
/*      */             
/*  638 */             return nbttagcompound;
/*      */           }
/*      */         });
/*      */   }
/*      */   
/*      */   public void a(NBTTagCompound nbttagcompound) {
/*  644 */     super.a(nbttagcompound);
/*  645 */     a(a(this.g));
/*  646 */     NBTTagList nbttaglist = nbttagcompound.getList("Inventory", 10);
/*      */     
/*  648 */     this.inventory.b(nbttaglist);
/*  649 */     this.inventory.itemInHandIndex = nbttagcompound.getInt("SelectedItemSlot");
/*  650 */     this.sleeping = nbttagcompound.getBoolean("Sleeping");
/*  651 */     this.sleepTicks = nbttagcompound.getShort("SleepTimer");
/*  652 */     this.exp = nbttagcompound.getFloat("XpP");
/*  653 */     this.expLevel = nbttagcompound.getInt("XpLevel");
/*  654 */     this.expTotal = nbttagcompound.getInt("XpTotal");
/*  655 */     this.bS = nbttagcompound.getInt("XpSeed");
/*  656 */     if (this.bS == 0) {
/*  657 */       this.bS = this.random.nextInt();
/*      */     }
/*      */     
/*  660 */     setScore(nbttagcompound.getInt("Score"));
/*  661 */     if (this.sleeping) {
/*  662 */       this.bedPosition = new BlockPosition(this);
/*  663 */       a(true, true, false);
/*      */     } 
/*      */ 
/*      */     
/*  667 */     this.spawnWorld = nbttagcompound.getString("SpawnWorld");
/*  668 */     if ("".equals(this.spawnWorld)) {
/*  669 */       this.spawnWorld = ((World)this.world.getServer().getWorlds().get(0)).getName();
/*      */     }
/*      */ 
/*      */     
/*  673 */     if (nbttagcompound.hasKeyOfType("SpawnX", 99) && nbttagcompound.hasKeyOfType("SpawnY", 99) && nbttagcompound.hasKeyOfType("SpawnZ", 99)) {
/*  674 */       this.d = new BlockPosition(nbttagcompound.getInt("SpawnX"), nbttagcompound.getInt("SpawnY"), nbttagcompound.getInt("SpawnZ"));
/*  675 */       this.e = nbttagcompound.getBoolean("SpawnForced");
/*      */     } 
/*      */     
/*  678 */     this.foodData.a(nbttagcompound);
/*  679 */     this.abilities.b(nbttagcompound);
/*  680 */     if (nbttagcompound.hasKeyOfType("EnderItems", 9)) {
/*  681 */       NBTTagList nbttaglist1 = nbttagcompound.getList("EnderItems", 10);
/*      */       
/*  683 */       this.enderChest.a(nbttaglist1);
/*      */     } 
/*      */     
/*  686 */     if (nbttagcompound.hasKeyOfType("ShoulderEntityLeft", 10)) {
/*  687 */       setShoulderEntityLeft(nbttagcompound.getCompound("ShoulderEntityLeft"));
/*      */     }
/*      */     
/*  690 */     if (nbttagcompound.hasKeyOfType("ShoulderEntityRight", 10)) {
/*  691 */       setShoulderEntityRight(nbttagcompound.getCompound("ShoulderEntityRight"));
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void b(NBTTagCompound nbttagcompound) {
/*  697 */     super.b(nbttagcompound);
/*  698 */     nbttagcompound.setInt("DataVersion", 1139);
/*  699 */     nbttagcompound.set("Inventory", this.inventory.a(new NBTTagList()));
/*  700 */     nbttagcompound.setInt("SelectedItemSlot", this.inventory.itemInHandIndex);
/*  701 */     nbttagcompound.setBoolean("Sleeping", this.sleeping);
/*  702 */     nbttagcompound.setShort("SleepTimer", (short)this.sleepTicks);
/*  703 */     nbttagcompound.setFloat("XpP", this.exp);
/*  704 */     nbttagcompound.setInt("XpLevel", this.expLevel);
/*  705 */     nbttagcompound.setInt("XpTotal", this.expTotal);
/*  706 */     nbttagcompound.setInt("XpSeed", this.bS);
/*  707 */     nbttagcompound.setInt("Score", getScore());
/*  708 */     if (this.d != null) {
/*  709 */       nbttagcompound.setInt("SpawnX", this.d.getX());
/*  710 */       nbttagcompound.setInt("SpawnY", this.d.getY());
/*  711 */       nbttagcompound.setInt("SpawnZ", this.d.getZ());
/*  712 */       nbttagcompound.setBoolean("SpawnForced", this.e);
/*      */     } 
/*      */     
/*  715 */     this.foodData.b(nbttagcompound);
/*  716 */     this.abilities.a(nbttagcompound);
/*  717 */     nbttagcompound.set("EnderItems", this.enderChest.i());
/*  718 */     if (!getShoulderEntityLeft().isEmpty()) {
/*  719 */       nbttagcompound.set("ShoulderEntityLeft", getShoulderEntityLeft());
/*      */     }
/*      */     
/*  722 */     if (!getShoulderEntityRight().isEmpty()) {
/*  723 */       nbttagcompound.set("ShoulderEntityRight", getShoulderEntityRight());
/*      */     }
/*  725 */     nbttagcompound.setString("SpawnWorld", this.spawnWorld);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean damageEntity(DamageSource damagesource, float f) {
/*  730 */     if (isInvulnerable(damagesource))
/*  731 */       return false; 
/*  732 */     if (this.abilities.isInvulnerable && !damagesource.ignoresInvulnerability()) {
/*  733 */       return false;
/*      */     }
/*  735 */     this.ticksFarFromPlayer = 0;
/*  736 */     if (getHealth() <= 0.0F) {
/*  737 */       return false;
/*      */     }
/*  739 */     if (isSleeping() && !this.world.isClientSide) {
/*  740 */       a(true, true, false);
/*      */     }
/*      */ 
/*      */     
/*  744 */     if (damagesource.r()) {
/*  745 */       if (this.world.getDifficulty() == EnumDifficulty.PEACEFUL) {
/*  746 */         return false;
/*      */       }
/*      */       
/*  749 */       if (this.world.getDifficulty() == EnumDifficulty.EASY) {
/*  750 */         f = Math.min(f / 2.0F + 1.0F, f);
/*      */       }
/*      */       
/*  753 */       if (this.world.getDifficulty() == EnumDifficulty.HARD) {
/*  754 */         f = f * 3.0F / 2.0F;
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  759 */     boolean damaged = super.damageEntity(damagesource, f);
/*  760 */     if (damaged) {
/*  761 */       releaseShoulderEntities();
/*      */     }
/*  763 */     return damaged;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void c(EntityLiving entityliving) {
/*  770 */     super.c(entityliving);
/*  771 */     if (entityliving.getItemInMainHand().getItem() instanceof ItemAxe) {
/*  772 */       m(true);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean a(EntityHuman entityhuman) {
/*      */     Team team;
/*  781 */     if (entityhuman instanceof EntityPlayer) {
/*  782 */       EntityPlayer thatPlayer = (EntityPlayer)entityhuman;
/*  783 */       team = thatPlayer.getBukkitEntity().getScoreboard().getPlayerTeam((OfflinePlayer)thatPlayer.getBukkitEntity());
/*  784 */       if (team == null || team.allowFriendlyFire()) {
/*  785 */         return true;
/*      */       }
/*      */     } else {
/*      */       
/*  789 */       OfflinePlayer thisPlayer = entityhuman.world.getServer().getOfflinePlayer(entityhuman.getName());
/*  790 */       team = entityhuman.world.getServer().getScoreboardManager().getMainScoreboard().getPlayerTeam(thisPlayer);
/*  791 */       if (team == null || team.allowFriendlyFire()) {
/*  792 */         return true;
/*      */       }
/*      */     } 
/*      */     
/*  796 */     if (this instanceof EntityPlayer) {
/*  797 */       return !team.hasPlayer((OfflinePlayer)((EntityPlayer)this).getBukkitEntity());
/*      */     }
/*  799 */     return !team.hasPlayer(this.world.getServer().getOfflinePlayer(getName()));
/*      */   }
/*      */ 
/*      */   
/*      */   protected void damageArmor(float f) {
/*  804 */     this.inventory.a(f);
/*      */   }
/*      */   
/*      */   protected void damageShield(float f) {
/*  808 */     if (f >= 3.0F && this.activeItem.getItem() == Items.SHIELD) {
/*  809 */       int i = 1 + MathHelper.d(f);
/*      */       
/*  811 */       this.activeItem.damage(i, this);
/*  812 */       if (this.activeItem.isEmpty()) {
/*  813 */         EnumHand enumhand = cH();
/*      */         
/*  815 */         if (enumhand == EnumHand.MAIN_HAND) {
/*  816 */           setSlot(EnumItemSlot.MAINHAND, ItemStack.a);
/*      */         } else {
/*  818 */           setSlot(EnumItemSlot.OFFHAND, ItemStack.a);
/*      */         } 
/*      */         
/*  821 */         this.activeItem = ItemStack.a;
/*  822 */         a(SoundEffects.gy, 0.8F, 0.8F + this.world.random.nextFloat() * 0.4F);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public float cW() {
/*  829 */     int i = 0;
/*  830 */     Iterator<ItemStack> iterator = this.inventory.armor.iterator();
/*      */     
/*  832 */     while (iterator.hasNext()) {
/*  833 */       ItemStack itemstack = iterator.next();
/*      */       
/*  835 */       if (!itemstack.isEmpty()) {
/*  836 */         i++;
/*      */       }
/*      */     } 
/*      */     
/*  840 */     return i / this.inventory.armor.size();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean damageEntity0(DamageSource damagesource, float f) {
/*  846 */     return super.damageEntity0(damagesource, f);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void openSign(TileEntitySign tileentitysign) {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void a(CommandBlockListenerAbstract commandblocklistenerabstract) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void a(TileEntityCommand tileentitycommand) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void a(TileEntityStructure tileentitystructure) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void openTrade(IMerchant imerchant) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void openContainer(IInventory iinventory) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void openHorseInventory(EntityHorseAbstract entityhorseabstract, IInventory iinventory) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void openTileEntity(ITileEntityContainer itileentitycontainer) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void a(ItemStack itemstack, EnumHand enumhand) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public EnumInteractionResult a(Entity entity, EnumHand enumhand) {
/*  890 */     if (isSpectator()) {
/*  891 */       if (entity instanceof IInventory) {
/*  892 */         openContainer((IInventory)entity);
/*      */       }
/*      */       
/*  895 */       return EnumInteractionResult.PASS;
/*      */     } 
/*  897 */     ItemStack itemstack = b(enumhand);
/*  898 */     ItemStack itemstack1 = itemstack.isEmpty() ? ItemStack.a : itemstack.cloneItemStack();
/*      */     
/*  900 */     if (entity.b(this, enumhand)) {
/*  901 */       if (this.abilities.canInstantlyBuild && itemstack == b(enumhand) && itemstack.getCount() < itemstack1.getCount()) {
/*  902 */         itemstack.setCount(itemstack1.getCount());
/*      */       }
/*      */       
/*  905 */       return EnumInteractionResult.SUCCESS;
/*      */     } 
/*  907 */     if (!itemstack.isEmpty() && entity instanceof EntityLiving) {
/*  908 */       if (this.abilities.canInstantlyBuild) {
/*  909 */         itemstack = itemstack1;
/*      */       }
/*      */       
/*  912 */       if (itemstack.a(this, (EntityLiving)entity, enumhand)) {
/*  913 */         if (itemstack.isEmpty() && !this.abilities.canInstantlyBuild) {
/*  914 */           a(enumhand, ItemStack.a);
/*      */         }
/*      */         
/*  917 */         return EnumInteractionResult.SUCCESS;
/*      */       } 
/*      */     } 
/*      */     
/*  921 */     return EnumInteractionResult.PASS;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public double aF() {
/*  927 */     return -0.35D;
/*      */   }
/*      */   
/*      */   public void stopRiding() {
/*  931 */     super.stopRiding();
/*  932 */     this.j = 0;
/*      */   }
/*      */   
/*      */   public void attack(Entity entity) {
/*  936 */     if (entity.bd() && 
/*  937 */       !entity.t(this)) {
/*  938 */       float f1, f = (float)getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).getValue();
/*      */ 
/*      */       
/*  941 */       if (entity instanceof EntityLiving) {
/*  942 */         f1 = EnchantmentManager.a(getItemInMainHand(), ((EntityLiving)entity).getMonsterType());
/*      */       } else {
/*  944 */         f1 = EnchantmentManager.a(getItemInMainHand(), EnumMonsterType.UNDEFINED);
/*      */       } 
/*      */       
/*  947 */       float f2 = n(0.5F);
/*      */       
/*  949 */       f *= 0.2F + f2 * f2 * 0.8F;
/*  950 */       f1 *= f2;
/*  951 */       ds();
/*  952 */       if (f > 0.0F || f1 > 0.0F) {
/*  953 */         boolean flag = (f2 > 0.9F);
/*  954 */         boolean flag1 = false;
/*  955 */         byte b0 = 0;
/*  956 */         int i = b0 + EnchantmentManager.b(this);
/*      */         
/*  958 */         if (isSprinting() && flag) {
/*  959 */           this.world.a((EntityHuman)null, this.locX, this.locY, this.locZ, SoundEffects.fw, bK(), 1.0F, 1.0F);
/*  960 */           i++;
/*  961 */           flag1 = true;
/*      */         } 
/*      */         
/*  964 */         boolean flag2 = (flag && this.fallDistance > 0.0F && !this.onGround && !m_() && !isInWater() && !hasEffect(MobEffects.BLINDNESS) && !isPassenger() && entity instanceof EntityLiving);
/*      */         
/*  966 */         flag2 = (flag2 && !isSprinting());
/*  967 */         if (flag2) {
/*  968 */           f *= 1.5F;
/*      */         }
/*      */         
/*  971 */         f += f1;
/*  972 */         boolean flag3 = false;
/*  973 */         double d0 = (this.J - this.I);
/*      */         
/*  975 */         if (flag && !flag2 && !flag1 && this.onGround && d0 < cy()) {
/*  976 */           ItemStack itemstack = b(EnumHand.MAIN_HAND);
/*      */           
/*  978 */           if (itemstack.getItem() instanceof ItemSword) {
/*  979 */             flag3 = true;
/*      */           }
/*      */         } 
/*      */         
/*  983 */         float f3 = 0.0F;
/*  984 */         boolean flag4 = false;
/*  985 */         int j = EnchantmentManager.getFireAspectEnchantmentLevel(this);
/*      */         
/*  987 */         if (entity instanceof EntityLiving) {
/*  988 */           f3 = ((EntityLiving)entity).getHealth();
/*  989 */           if (j > 0 && !entity.isBurning()) {
/*      */             
/*  991 */             EntityCombustByEntityEvent combustEvent = new EntityCombustByEntityEvent((Entity)getBukkitEntity(), (Entity)entity.getBukkitEntity(), 1);
/*  992 */             Bukkit.getPluginManager().callEvent((Event)combustEvent);
/*      */             
/*  994 */             if (!combustEvent.isCancelled()) {
/*  995 */               flag4 = true;
/*  996 */               entity.setOnFire(combustEvent.getDuration());
/*      */             } 
/*      */           } 
/*      */         } 
/*      */ 
/*      */         
/* 1002 */         double d1 = entity.motX;
/* 1003 */         double d2 = entity.motY;
/* 1004 */         double d3 = entity.motZ;
/* 1005 */         boolean flag5 = entity.damageEntity(DamageSource.playerAttack(this), f);
/*      */         
/* 1007 */         if (flag5) {
/* 1008 */           if (i > 0) {
/* 1009 */             if (entity instanceof EntityLiving) {
/* 1010 */               ((EntityLiving)entity).a(this, i * 0.5F, MathHelper.sin(this.yaw * 0.017453292F), -MathHelper.cos(this.yaw * 0.017453292F));
/*      */             } else {
/* 1012 */               entity.f((-MathHelper.sin(this.yaw * 0.017453292F) * i * 0.5F), 0.1D, (MathHelper.cos(this.yaw * 0.017453292F) * i * 0.5F));
/*      */             } 
/*      */             
/* 1015 */             this.motX *= 0.6D;
/* 1016 */             this.motZ *= 0.6D;
/* 1017 */             setSprinting(false);
/*      */           } 
/*      */           
/* 1020 */           if (flag3) {
/* 1021 */             float f4 = 1.0F + EnchantmentManager.a(this) * f;
/* 1022 */             List<EntityLiving> list = this.world.a(EntityLiving.class, entity.getBoundingBox().grow(1.0D, 0.25D, 1.0D));
/* 1023 */             Iterator<EntityLiving> iterator = list.iterator();
/*      */             
/* 1025 */             while (iterator.hasNext()) {
/* 1026 */               EntityLiving entityliving = iterator.next();
/*      */               
/* 1028 */               if (entityliving != this && entityliving != entity && !r(entityliving) && h(entityliving) < 9.0D)
/*      */               {
/* 1030 */                 if (entityliving.damageEntity(DamageSource.playerAttack(this).sweep(), f4)) {
/* 1031 */                   entityliving.a(this, 0.4F, MathHelper.sin(this.yaw * 0.017453292F), -MathHelper.cos(this.yaw * 0.017453292F));
/*      */                 }
/*      */               }
/*      */             } 
/*      */ 
/*      */             
/* 1037 */             this.world.a((EntityHuman)null, this.locX, this.locY, this.locZ, SoundEffects.fz, bK(), 1.0F, 1.0F);
/* 1038 */             cX();
/*      */           } 
/*      */           
/* 1041 */           if (entity instanceof EntityPlayer && entity.velocityChanged) {
/*      */             
/* 1043 */             boolean cancelled = false;
/* 1044 */             Player player = (Player)entity.getBukkitEntity();
/* 1045 */             Vector velocity = new Vector(d1, d2, d3);
/*      */             
/* 1047 */             PlayerVelocityEvent event = new PlayerVelocityEvent(player, velocity.clone());
/* 1048 */             this.world.getServer().getPluginManager().callEvent((Event)event);
/*      */             
/* 1050 */             if (event.isCancelled()) {
/* 1051 */               cancelled = true;
/* 1052 */             } else if (!velocity.equals(event.getVelocity())) {
/* 1053 */               player.setVelocity(event.getVelocity());
/*      */             } 
/*      */             
/* 1056 */             if (!cancelled) {
/* 1057 */               ((EntityPlayer)entity).playerConnection.sendPacket(new PacketPlayOutEntityVelocity(entity));
/* 1058 */               entity.velocityChanged = false;
/* 1059 */               entity.motX = d1;
/* 1060 */               entity.motY = d2;
/* 1061 */               entity.motZ = d3;
/*      */             } 
/*      */           } 
/*      */ 
/*      */           
/* 1066 */           if (flag2) {
/* 1067 */             this.world.a((EntityHuman)null, this.locX, this.locY, this.locZ, SoundEffects.fv, bK(), 1.0F, 1.0F);
/* 1068 */             a(entity);
/*      */           } 
/*      */           
/* 1071 */           if (!flag2 && !flag3) {
/* 1072 */             if (flag) {
/* 1073 */               this.world.a((EntityHuman)null, this.locX, this.locY, this.locZ, SoundEffects.fy, bK(), 1.0F, 1.0F);
/*      */             } else {
/* 1075 */               this.world.a((EntityHuman)null, this.locX, this.locY, this.locZ, SoundEffects.fA, bK(), 1.0F, 1.0F);
/*      */             } 
/*      */           }
/*      */           
/* 1079 */           if (f1 > 0.0F) {
/* 1080 */             b(entity);
/*      */           }
/*      */           
/* 1083 */           z(entity);
/* 1084 */           if (entity instanceof EntityLiving) {
/* 1085 */             EnchantmentManager.a((EntityLiving)entity, this);
/*      */           }
/*      */           
/* 1088 */           EnchantmentManager.b(this, entity);
/* 1089 */           ItemStack itemstack1 = getItemInMainHand();
/* 1090 */           Object object = entity;
/*      */           
/* 1092 */           if (entity instanceof EntityComplexPart) {
/* 1093 */             IComplex icomplex = ((EntityComplexPart)entity).owner;
/*      */             
/* 1095 */             if (icomplex instanceof EntityLiving) {
/* 1096 */               object = icomplex;
/*      */             }
/*      */           } 
/*      */           
/* 1100 */           if (!itemstack1.isEmpty() && object instanceof EntityLiving) {
/* 1101 */             itemstack1.a((EntityLiving)object, this);
/* 1102 */             if (itemstack1.isEmpty()) {
/* 1103 */               a(EnumHand.MAIN_HAND, ItemStack.a);
/*      */             }
/*      */           } 
/*      */           
/* 1107 */           if (entity instanceof EntityLiving) {
/* 1108 */             float f5 = f3 - ((EntityLiving)entity).getHealth();
/*      */             
/* 1110 */             a(StatisticList.y, Math.round(f5 * 10.0F));
/* 1111 */             if (j > 0) {
/*      */               
/* 1113 */               EntityCombustByEntityEvent combustEvent = new EntityCombustByEntityEvent((Entity)getBukkitEntity(), (Entity)entity.getBukkitEntity(), j * 4);
/* 1114 */               Bukkit.getPluginManager().callEvent((Event)combustEvent);
/*      */               
/* 1116 */               if (!combustEvent.isCancelled()) {
/* 1117 */                 entity.setOnFire(combustEvent.getDuration());
/*      */               }
/*      */             } 
/*      */ 
/*      */             
/* 1122 */             if (this.world instanceof WorldServer && f5 > 2.0F) {
/* 1123 */               int k = (int)(f5 * 0.5D);
/*      */               
/* 1125 */               ((WorldServer)this.world).a(EnumParticle.DAMAGE_INDICATOR, entity.locX, entity.locY + (entity.length * 0.5F), entity.locZ, k, 0.1D, 0.0D, 0.1D, 0.2D, new int[0]);
/*      */             } 
/*      */           } 
/*      */           
/* 1129 */           applyExhaustion(this.world.spigotConfig.combatExhaustion);
/*      */         } else {
/* 1131 */           this.world.a((EntityHuman)null, this.locX, this.locY, this.locZ, SoundEffects.fx, bK(), 1.0F, 1.0F);
/* 1132 */           if (flag4) {
/* 1133 */             entity.extinguish();
/*      */           }
/*      */           
/* 1136 */           if (this instanceof EntityPlayer) {
/* 1137 */             ((EntityPlayer)this).getBukkitEntity().updateInventory();
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void m(boolean flag) {
/* 1148 */     float f = 0.25F + EnchantmentManager.getDigSpeedEnchantmentLevel(this) * 0.05F;
/*      */     
/* 1150 */     if (flag) {
/* 1151 */       f += 0.75F;
/*      */     }
/*      */     
/* 1154 */     if (this.random.nextFloat() < f) {
/* 1155 */       getCooldownTracker().a(Items.SHIELD, 100);
/* 1156 */       cN();
/* 1157 */       this.world.broadcastEntityEffect(this, (byte)30);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void a(Entity entity) {}
/*      */   
/*      */   public void b(Entity entity) {}
/*      */   
/*      */   public void cX() {
/* 1167 */     double d0 = -MathHelper.sin(this.yaw * 0.017453292F);
/* 1168 */     double d1 = MathHelper.cos(this.yaw * 0.017453292F);
/*      */     
/* 1170 */     if (this.world instanceof WorldServer) {
/* 1171 */       ((WorldServer)this.world).a(EnumParticle.SWEEP_ATTACK, this.locX + d0, this.locY + this.length * 0.5D, this.locZ + d1, 0, d0, 0.0D, d1, 0.0D, new int[0]);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void die() {
/* 1177 */     super.die();
/* 1178 */     this.defaultContainer.b(this);
/* 1179 */     if (this.activeContainer != null) {
/* 1180 */       this.activeContainer.b(this);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean inBlock() {
/* 1186 */     return (!this.sleeping && super.inBlock());
/*      */   }
/*      */   
/*      */   public boolean cZ() {
/* 1190 */     return false;
/*      */   }
/*      */   
/*      */   public GameProfile getProfile() {
/* 1194 */     return this.g;
/*      */   }
/*      */   
/*      */   public EnumBedResult a(BlockPosition blockposition) {
/* 1198 */     EnumDirection enumdirection = this.world.getType(blockposition).<EnumDirection>get(BlockFacingHorizontal.FACING);
/*      */     
/* 1200 */     if (!this.world.isClientSide) {
/* 1201 */       if (isSleeping() || !isAlive()) {
/* 1202 */         return EnumBedResult.OTHER_PROBLEM;
/*      */       }
/*      */       
/* 1205 */       if (!this.world.worldProvider.d()) {
/* 1206 */         return EnumBedResult.NOT_POSSIBLE_HERE;
/*      */       }
/*      */       
/* 1209 */       if (this.world.D()) {
/* 1210 */         return EnumBedResult.NOT_POSSIBLE_NOW;
/*      */       }
/*      */       
/* 1213 */       if (!a(blockposition, enumdirection)) {
/* 1214 */         return EnumBedResult.TOO_FAR_AWAY;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1219 */       List<EntityMonster> list = this.world.a(EntityMonster.class, new AxisAlignedBB(blockposition.getX() - 8.0D, blockposition.getY() - 5.0D, blockposition.getZ() - 8.0D, blockposition.getX() + 8.0D, blockposition.getY() + 5.0D, blockposition.getZ() + 8.0D), new c(this, null));
/*      */       
/* 1221 */       if (!list.isEmpty()) {
/* 1222 */         return EnumBedResult.NOT_SAFE;
/*      */       }
/*      */     } 
/*      */     
/* 1226 */     if (isPassenger()) {
/* 1227 */       stopRiding();
/*      */     }
/*      */ 
/*      */     
/* 1231 */     if (getBukkitEntity() instanceof Player) {
/* 1232 */       Player player = (Player)getBukkitEntity();
/* 1233 */       Block bed = this.world.getWorld().getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ());
/*      */       
/* 1235 */       PlayerBedEnterEvent event = new PlayerBedEnterEvent(player, bed);
/* 1236 */       this.world.getServer().getPluginManager().callEvent((Event)event);
/*      */       
/* 1238 */       if (event.isCancelled()) {
/* 1239 */         return EnumBedResult.OTHER_PROBLEM;
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1244 */     releaseShoulderEntities();
/* 1245 */     setSize(0.2F, 0.2F);
/* 1246 */     if (this.world.isLoaded(blockposition)) {
/* 1247 */       float f = 0.5F + enumdirection.getAdjacentX() * 0.4F;
/* 1248 */       float f1 = 0.5F + enumdirection.getAdjacentZ() * 0.4F;
/*      */       
/* 1250 */       a(enumdirection);
/* 1251 */       setPosition((blockposition.getX() + f), (blockposition.getY() + 0.6875F), (blockposition.getZ() + f1));
/*      */     } else {
/* 1253 */       setPosition((blockposition.getX() + 0.5F), (blockposition.getY() + 0.6875F), (blockposition.getZ() + 0.5F));
/*      */     } 
/*      */     
/* 1256 */     this.sleeping = true;
/* 1257 */     this.sleepTicks = 0;
/* 1258 */     this.bedPosition = blockposition;
/* 1259 */     this.motX = 0.0D;
/* 1260 */     this.motY = 0.0D;
/* 1261 */     this.motZ = 0.0D;
/* 1262 */     if (!this.world.isClientSide) {
/* 1263 */       this.world.everyoneSleeping();
/*      */     }
/*      */     
/* 1266 */     return EnumBedResult.OK;
/*      */   }
/*      */   
/*      */   private boolean a(BlockPosition blockposition, EnumDirection enumdirection) {
/* 1270 */     if (Math.abs(this.locX - blockposition.getX()) <= 3.0D && Math.abs(this.locY - blockposition.getY()) <= 2.0D && Math.abs(this.locZ - blockposition.getZ()) <= 3.0D) {
/* 1271 */       return true;
/*      */     }
/* 1273 */     BlockPosition blockposition1 = blockposition.shift(enumdirection.opposite());
/*      */     
/* 1275 */     return (Math.abs(this.locX - blockposition1.getX()) <= 3.0D && Math.abs(this.locY - blockposition1.getY()) <= 2.0D && Math.abs(this.locZ - blockposition1.getZ()) <= 3.0D);
/*      */   }
/*      */ 
/*      */   
/*      */   private void a(EnumDirection enumdirection) {
/* 1280 */     this.bM = -1.8F * enumdirection.getAdjacentX();
/* 1281 */     this.bN = -1.8F * enumdirection.getAdjacentZ();
/*      */   }
/*      */   
/*      */   public void a(boolean flag, boolean flag1, boolean flag2) {
/* 1285 */     setSize(0.6F, 1.8F);
/* 1286 */     IBlockData iblockdata = this.world.getType(this.bedPosition);
/*      */     
/* 1288 */     if (this.bedPosition != null && iblockdata.getBlock() == Blocks.BED) {
/* 1289 */       this.world.setTypeAndData(this.bedPosition, iblockdata.set(BlockBed.OCCUPIED, Boolean.valueOf(false)), 4);
/* 1290 */       BlockPosition blockposition = BlockBed.a(this.world, this.bedPosition, 0);
/*      */       
/* 1292 */       if (blockposition == null) {
/* 1293 */         blockposition = this.bedPosition.up();
/*      */       }
/*      */       
/* 1296 */       setPosition((blockposition.getX() + 0.5F), (blockposition.getY() + 0.1F), (blockposition.getZ() + 0.5F));
/*      */     } 
/*      */     
/* 1299 */     this.sleeping = false;
/* 1300 */     if (!this.world.isClientSide && flag1) {
/* 1301 */       this.world.everyoneSleeping();
/*      */     }
/*      */ 
/*      */     
/* 1305 */     if (getBukkitEntity() instanceof Player) {
/* 1306 */       Block bed; Player player = (Player)getBukkitEntity();
/*      */ 
/*      */       
/* 1309 */       BlockPosition blockposition = this.bedPosition;
/* 1310 */       if (blockposition != null) {
/* 1311 */         bed = this.world.getWorld().getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ());
/*      */       } else {
/* 1313 */         bed = this.world.getWorld().getBlockAt(player.getLocation());
/*      */       } 
/*      */       
/* 1316 */       PlayerBedLeaveEvent event = new PlayerBedLeaveEvent(player, bed);
/* 1317 */       this.world.getServer().getPluginManager().callEvent((Event)event);
/*      */     } 
/*      */ 
/*      */     
/* 1321 */     this.sleepTicks = flag ? 0 : 100;
/* 1322 */     if (flag2) {
/* 1323 */       setRespawnPosition(this.bedPosition, false);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean s() {
/* 1329 */     return (this.world.getType(this.bedPosition).getBlock() == Blocks.BED);
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public static BlockPosition getBed(World world, BlockPosition blockposition, boolean flag) {
/* 1334 */     Block block = world.getType(blockposition).getBlock();
/*      */     
/* 1336 */     if (block != Blocks.BED) {
/* 1337 */       if (!flag) {
/* 1338 */         return null;
/*      */       }
/* 1340 */       boolean flag1 = block.d();
/* 1341 */       boolean flag2 = world.getType(blockposition.up()).getBlock().d();
/*      */       
/* 1343 */       return (flag1 && flag2) ? blockposition : null;
/*      */     } 
/*      */     
/* 1346 */     return BlockBed.a(world, blockposition, 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isSleeping() {
/* 1351 */     return this.sleeping;
/*      */   }
/*      */   
/*      */   public boolean isDeeplySleeping() {
/* 1355 */     return (this.sleeping && this.sleepTicks >= 100);
/*      */   }
/*      */   
/*      */   public void a(IChatBaseComponent ichatbasecomponent, boolean flag) {}
/*      */   
/*      */   public BlockPosition getBed() {
/* 1361 */     return this.d;
/*      */   }
/*      */   
/*      */   public boolean isRespawnForced() {
/* 1365 */     return this.e;
/*      */   }
/*      */   
/*      */   public void setRespawnPosition(BlockPosition blockposition, boolean flag) {
/* 1369 */     if (blockposition != null) {
/* 1370 */       this.d = blockposition;
/* 1371 */       this.e = flag;
/* 1372 */       this.spawnWorld = this.world.worldData.getName();
/*      */     } else {
/* 1374 */       this.d = null;
/* 1375 */       this.e = false;
/* 1376 */       this.spawnWorld = "";
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void b(Statistic statistic) {
/* 1382 */     a(statistic, 1);
/*      */   }
/*      */   
/*      */   public void a(Statistic statistic, int i) {}
/*      */   
/*      */   public void a(Statistic statistic) {}
/*      */   
/*      */   public void a(List<IRecipe> list) {}
/*      */   
/*      */   public void a(MinecraftKey[] aminecraftkey) {}
/*      */   
/*      */   public void b(List<IRecipe> list) {}
/*      */   
/*      */   public void cu() {
/* 1396 */     super.cu();
/* 1397 */     b(StatisticList.w);
/* 1398 */     if (isSprinting()) {
/* 1399 */       applyExhaustion(this.world.spigotConfig.jumpSprintExhaustion);
/*      */     } else {
/* 1401 */       applyExhaustion(this.world.spigotConfig.jumpWalkExhaustion);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void a(float f, float f1, float f2) {
/* 1407 */     double d0 = this.locX;
/* 1408 */     double d1 = this.locY;
/* 1409 */     double d2 = this.locZ;
/*      */     
/* 1411 */     if (this.abilities.isFlying && !isPassenger()) {
/* 1412 */       double d3 = this.motY;
/* 1413 */       float f3 = this.aR;
/*      */       
/* 1415 */       this.aR = this.abilities.a() * (isSprinting() ? 2 : true);
/* 1416 */       super.a(f, f1, f2);
/* 1417 */       this.motY = d3 * 0.6D;
/* 1418 */       this.aR = f3;
/* 1419 */       this.fallDistance = 0.0F;
/* 1420 */       setFlag(7, false);
/*      */     } else {
/* 1422 */       super.a(f, f1, f2);
/*      */     } 
/*      */     
/* 1425 */     checkMovement(this.locX - d0, this.locY - d1, this.locZ - d2);
/*      */   }
/*      */   
/*      */   public float cy() {
/* 1429 */     return (float)getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).getValue();
/*      */   }
/*      */   
/*      */   public void checkMovement(double d0, double d1, double d2) {
/* 1433 */     if (!isPassenger())
/*      */     {
/*      */       
/* 1436 */       if (a(Material.WATER)) {
/* 1437 */         int i = Math.round(MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2) * 100.0F);
/* 1438 */         if (i > 0) {
/* 1439 */           a(StatisticList.q, i);
/* 1440 */           applyExhaustion(this.world.spigotConfig.swimMultiplier * i * 0.01F);
/*      */         } 
/* 1442 */       } else if (isInWater()) {
/* 1443 */         int i = Math.round(MathHelper.sqrt(d0 * d0 + d2 * d2) * 100.0F);
/* 1444 */         if (i > 0) {
/* 1445 */           a(StatisticList.m, i);
/* 1446 */           applyExhaustion(this.world.spigotConfig.swimMultiplier * i * 0.01F);
/*      */         } 
/* 1448 */       } else if (m_()) {
/* 1449 */         if (d1 > 0.0D) {
/* 1450 */           a(StatisticList.o, (int)Math.round(d1 * 100.0D));
/*      */         }
/* 1452 */       } else if (this.onGround) {
/* 1453 */         int i = Math.round(MathHelper.sqrt(d0 * d0 + d2 * d2) * 100.0F);
/* 1454 */         if (i > 0) {
/* 1455 */           if (isSprinting()) {
/* 1456 */             a(StatisticList.l, i);
/* 1457 */             applyExhaustion(this.world.spigotConfig.sprintMultiplier * i * 0.01F);
/* 1458 */           } else if (isSneaking()) {
/* 1459 */             a(StatisticList.k, i);
/* 1460 */             applyExhaustion(this.world.spigotConfig.otherMultiplier * i * 0.01F);
/*      */           } else {
/* 1462 */             a(StatisticList.j, i);
/* 1463 */             applyExhaustion(this.world.spigotConfig.otherMultiplier * i * 0.01F);
/*      */           } 
/*      */         }
/* 1466 */       } else if (cP()) {
/* 1467 */         int i = Math.round(MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2) * 100.0F);
/* 1468 */         a(StatisticList.v, i);
/*      */       } else {
/* 1470 */         int i = Math.round(MathHelper.sqrt(d0 * d0 + d2 * d2) * 100.0F);
/* 1471 */         if (i > 25) {
/* 1472 */           a(StatisticList.p, i);
/*      */         }
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void l(double d0, double d1, double d2) {
/* 1480 */     if (isPassenger()) {
/* 1481 */       int i = Math.round(MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2) * 100.0F);
/*      */       
/* 1483 */       if (i > 0) {
/* 1484 */         if (bJ() instanceof EntityMinecartAbstract) {
/* 1485 */           a(StatisticList.r, i);
/* 1486 */         } else if (bJ() instanceof EntityBoat) {
/* 1487 */           a(StatisticList.s, i);
/* 1488 */         } else if (bJ() instanceof EntityPig) {
/* 1489 */           a(StatisticList.t, i);
/* 1490 */         } else if (bJ() instanceof EntityHorseAbstract) {
/* 1491 */           a(StatisticList.u, i);
/*      */         } 
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void e(float f, float f1) {
/* 1499 */     if (!this.abilities.canFly) {
/* 1500 */       if (f >= 2.0F) {
/* 1501 */         a(StatisticList.n, (int)Math.round(f * 100.0D));
/*      */       }
/*      */       
/* 1504 */       super.e(f, f1);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void ar() {
/* 1509 */     if (!isSpectator()) {
/* 1510 */       super.ar();
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected SoundEffect e(int i) {
/* 1516 */     return (i > 4) ? SoundEffects.fB : SoundEffects.fJ;
/*      */   }
/*      */   
/*      */   public void b(EntityLiving entityliving) {
/* 1520 */     EntityTypes.MonsterEggInfo entitytypes_monsteregginfo = EntityTypes.eggInfo.get(EntityTypes.a(entityliving));
/*      */     
/* 1522 */     if (entitytypes_monsteregginfo != null) {
/* 1523 */       b(entitytypes_monsteregginfo.killEntityStatistic);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void ba() {
/* 1529 */     if (!this.abilities.isFlying) {
/* 1530 */       super.ba();
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void giveExp(int i) {
/* 1536 */     addScore(i);
/* 1537 */     int j = Integer.MAX_VALUE - this.expTotal;
/*      */     
/* 1539 */     if (i > j) {
/* 1540 */       i = j;
/*      */     }
/*      */     
/* 1543 */     this.exp += i / getExpToLevel();
/*      */     
/* 1545 */     for (this.expTotal += i; this.exp >= 1.0F; this.exp /= getExpToLevel()) {
/* 1546 */       this.exp = (this.exp - 1.0F) * getExpToLevel();
/* 1547 */       levelDown(1);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public int dg() {
/* 1553 */     return this.bS;
/*      */   }
/*      */   
/*      */   public void enchantDone(ItemStack itemstack, int i) {
/* 1557 */     this.expLevel -= i;
/* 1558 */     if (this.expLevel < 0) {
/* 1559 */       this.expLevel = 0;
/* 1560 */       this.exp = 0.0F;
/* 1561 */       this.expTotal = 0;
/*      */     } 
/*      */     
/* 1564 */     this.bS = this.random.nextInt();
/*      */   }
/*      */   
/*      */   public void levelDown(int i) {
/* 1568 */     this.expLevel += i;
/* 1569 */     if (this.expLevel < 0) {
/* 1570 */       this.expLevel = 0;
/* 1571 */       this.exp = 0.0F;
/* 1572 */       this.expTotal = 0;
/*      */     } 
/*      */     
/* 1575 */     if (i > 0 && this.expLevel % 5 == 0 && this.f < this.ticksLived - 100.0F) {
/* 1576 */       float f = (this.expLevel > 30) ? 1.0F : (this.expLevel / 30.0F);
/*      */       
/* 1578 */       this.world.a((EntityHuman)null, this.locX, this.locY, this.locZ, SoundEffects.fI, bK(), f * 0.75F, 1.0F);
/* 1579 */       this.f = this.ticksLived;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public int getExpToLevel() {
/* 1585 */     return (this.expLevel >= 30) ? (112 + (this.expLevel - 30) * 9) : ((this.expLevel >= 15) ? (37 + (this.expLevel - 15) * 5) : (7 + this.expLevel * 2));
/*      */   }
/*      */   
/*      */   public void applyExhaustion(float f) {
/* 1589 */     if (!this.abilities.isInvulnerable && 
/* 1590 */       !this.world.isClientSide) {
/* 1591 */       this.foodData.a(f);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public FoodMetaData getFoodData() {
/* 1598 */     return this.foodData;
/*      */   }
/*      */   
/*      */   public boolean n(boolean flag) {
/* 1602 */     return ((flag || this.foodData.c()) && !this.abilities.isInvulnerable);
/*      */   }
/*      */   
/*      */   public boolean dj() {
/* 1606 */     return (getHealth() > 0.0F && getHealth() < getMaxHealth());
/*      */   }
/*      */   
/*      */   public boolean dk() {
/* 1610 */     return this.abilities.mayBuild;
/*      */   }
/*      */   
/*      */   public boolean a(BlockPosition blockposition, EnumDirection enumdirection, ItemStack itemstack) {
/* 1614 */     if (this.abilities.mayBuild)
/* 1615 */       return true; 
/* 1616 */     if (itemstack.isEmpty()) {
/* 1617 */       return false;
/*      */     }
/* 1619 */     BlockPosition blockposition1 = blockposition.shift(enumdirection.opposite());
/* 1620 */     Block block = this.world.getType(blockposition1).getBlock();
/*      */     
/* 1622 */     return !(!itemstack.b(block) && !itemstack.y());
/*      */   }
/*      */ 
/*      */   
/*      */   protected int getExpValue(EntityHuman entityhuman) {
/* 1627 */     if (!this.world.getGameRules().getBoolean("keepInventory") && !isSpectator()) {
/* 1628 */       int i = this.expLevel * 7;
/*      */       
/* 1630 */       return (i > 100) ? 100 : i;
/*      */     } 
/* 1632 */     return 0;
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean alwaysGivesExp() {
/* 1637 */     return true;
/*      */   }
/*      */   
/*      */   protected boolean playStepSound() {
/* 1641 */     return !this.abilities.isFlying;
/*      */   }
/*      */   
/*      */   public void updateAbilities() {}
/*      */   
/*      */   public void a(EnumGamemode enumgamemode) {}
/*      */   
/*      */   public String getName() {
/* 1649 */     return this.g.getName();
/*      */   }
/*      */   
/*      */   public InventoryEnderChest getEnderChest() {
/* 1653 */     return this.enderChest;
/*      */   }
/*      */   
/*      */   public ItemStack getEquipment(EnumItemSlot enumitemslot) {
/* 1657 */     return (enumitemslot == EnumItemSlot.MAINHAND) ? this.inventory.getItemInHand() : ((enumitemslot == EnumItemSlot.OFFHAND) ? this.inventory.extraSlots.get(0) : ((enumitemslot.a() == EnumItemSlot.Function.ARMOR) ? this.inventory.armor.get(enumitemslot.b()) : ItemStack.a));
/*      */   }
/*      */   
/*      */   public void setSlot(EnumItemSlot enumitemslot, ItemStack itemstack) {
/* 1661 */     if (enumitemslot == EnumItemSlot.MAINHAND) {
/* 1662 */       a_(itemstack);
/* 1663 */       this.inventory.items.set(this.inventory.itemInHandIndex, itemstack);
/* 1664 */     } else if (enumitemslot == EnumItemSlot.OFFHAND) {
/* 1665 */       a_(itemstack);
/* 1666 */       this.inventory.extraSlots.set(0, itemstack);
/* 1667 */     } else if (enumitemslot.a() == EnumItemSlot.Function.ARMOR) {
/* 1668 */       a_(itemstack);
/* 1669 */       this.inventory.armor.set(enumitemslot.b(), itemstack);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean c(ItemStack itemstack) {
/* 1675 */     a_(itemstack);
/* 1676 */     return this.inventory.pickup(itemstack);
/*      */   }
/*      */   
/*      */   public Iterable<ItemStack> aO() {
/* 1680 */     return Lists.newArrayList((Object[])new ItemStack[] { getItemInMainHand(), getItemInOffHand() });
/*      */   }
/*      */   
/*      */   public Iterable<ItemStack> getArmorItems() {
/* 1684 */     return this.inventory.armor;
/*      */   }
/*      */   
/*      */   public boolean g(NBTTagCompound nbttagcompound) {
/* 1688 */     if (!isPassenger() && this.onGround && !isInWater()) {
/* 1689 */       if (getShoulderEntityLeft().isEmpty()) {
/* 1690 */         setShoulderEntityLeft(nbttagcompound);
/* 1691 */         return true;
/* 1692 */       }  if (getShoulderEntityRight().isEmpty()) {
/* 1693 */         setShoulderEntityRight(nbttagcompound);
/* 1694 */         return true;
/*      */       } 
/* 1696 */       return false;
/*      */     } 
/*      */     
/* 1699 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void releaseShoulderEntities() {
/* 1705 */     if (spawnEntityFromShoulder(getShoulderEntityLeft())) {
/* 1706 */       setShoulderEntityLeft(new NBTTagCompound());
/*      */     }
/* 1708 */     if (spawnEntityFromShoulder(getShoulderEntityRight())) {
/* 1709 */       setShoulderEntityRight(new NBTTagCompound());
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean spawnEntityFromShoulder(@Nullable NBTTagCompound nbttagcompound) {
/* 1715 */     if (!this.world.isClientSide && !nbttagcompound.isEmpty()) {
/* 1716 */       Entity entity = EntityTypes.a(nbttagcompound, this.world);
/*      */       
/* 1718 */       if (entity instanceof EntityTameableAnimal) {
/* 1719 */         ((EntityTameableAnimal)entity).setOwnerUUID(this.uniqueID);
/*      */       }
/*      */       
/* 1722 */       entity.setPosition(this.locX, this.locY + 0.699999988079071D, this.locZ);
/* 1723 */       return this.world.addEntity(entity, CreatureSpawnEvent.SpawnReason.SHOULDER_ENTITY);
/*      */     } 
/*      */     
/* 1726 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean bo() {
/* 1734 */     return !this.abilities.isFlying;
/*      */   }
/*      */   
/*      */   public Scoreboard getScoreboard() {
/* 1738 */     return this.world.getScoreboard();
/*      */   }
/*      */   
/*      */   public ScoreboardTeamBase aY() {
/* 1742 */     return getScoreboard().getPlayerTeam(getName());
/*      */   }
/*      */   
/*      */   public IChatBaseComponent getScoreboardDisplayName() {
/* 1746 */     ChatComponentText chatcomponenttext = new ChatComponentText(ScoreboardTeam.getPlayerDisplayName(aY(), getName()));
/*      */     
/* 1748 */     chatcomponenttext.getChatModifier().setChatClickable(new ChatClickable(ChatClickable.EnumClickAction.SUGGEST_COMMAND, "/msg " + getName() + " "));
/* 1749 */     chatcomponenttext.getChatModifier().setChatHoverable(bv());
/* 1750 */     chatcomponenttext.getChatModifier().setInsertion(getName());
/* 1751 */     return chatcomponenttext;
/*      */   }
/*      */   
/*      */   public float getHeadHeight() {
/* 1755 */     float f = 1.62F;
/*      */     
/* 1757 */     if (isSleeping()) {
/* 1758 */       f = 0.2F;
/* 1759 */     } else if (!isSneaking() && this.length != 1.65F) {
/* 1760 */       if (cP() || this.length == 0.6F) {
/* 1761 */         f = 0.4F;
/*      */       }
/*      */     } else {
/* 1764 */       f -= 0.08F;
/*      */     } 
/*      */     
/* 1767 */     return f;
/*      */   }
/*      */   
/*      */   public void setAbsorptionHearts(float f) {
/* 1771 */     if (f < 0.0F) {
/* 1772 */       f = 0.0F;
/*      */     }
/*      */     
/* 1775 */     getDataWatcher().set(a, Float.valueOf(f));
/*      */   }
/*      */   
/*      */   public float getAbsorptionHearts() {
/* 1779 */     return ((Float)getDataWatcher().<Float>get(a)).floatValue();
/*      */   }
/*      */   
/*      */   public static UUID a(GameProfile gameprofile) {
/* 1783 */     UUID uuid = gameprofile.getId();
/*      */     
/* 1785 */     if (uuid == null) {
/* 1786 */       uuid = d(gameprofile.getName());
/*      */     }
/*      */     
/* 1789 */     return uuid;
/*      */   }
/*      */   
/*      */   public static UUID d(String s) {
/* 1793 */     return UUID.nameUUIDFromBytes(("OfflinePlayer:" + s).getBytes(StandardCharsets.UTF_8));
/*      */   }
/*      */   
/*      */   public boolean a(ChestLock chestlock) {
/* 1797 */     if (chestlock.a()) {
/* 1798 */       return true;
/*      */     }
/* 1800 */     ItemStack itemstack = getItemInMainHand();
/*      */     
/* 1802 */     return (!itemstack.isEmpty() && itemstack.hasName()) ? itemstack.getName().equals(chestlock.getKey()) : false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getSendCommandFeedback() {
/* 1807 */     return (C_()).worldServer[0].getGameRules().getBoolean("sendCommandFeedback");
/*      */   }
/*      */   public boolean c(int i, ItemStack itemstack) {
/*      */     EnumItemSlot enumitemslot;
/* 1811 */     if (i >= 0 && i < this.inventory.items.size()) {
/* 1812 */       this.inventory.setItem(i, itemstack);
/* 1813 */       return true;
/*      */     } 
/*      */ 
/*      */     
/* 1817 */     if (i == 100 + EnumItemSlot.HEAD.b()) {
/* 1818 */       enumitemslot = EnumItemSlot.HEAD;
/* 1819 */     } else if (i == 100 + EnumItemSlot.CHEST.b()) {
/* 1820 */       enumitemslot = EnumItemSlot.CHEST;
/* 1821 */     } else if (i == 100 + EnumItemSlot.LEGS.b()) {
/* 1822 */       enumitemslot = EnumItemSlot.LEGS;
/* 1823 */     } else if (i == 100 + EnumItemSlot.FEET.b()) {
/* 1824 */       enumitemslot = EnumItemSlot.FEET;
/*      */     } else {
/* 1826 */       enumitemslot = null;
/*      */     } 
/*      */     
/* 1829 */     if (i == 98) {
/* 1830 */       setSlot(EnumItemSlot.MAINHAND, itemstack);
/* 1831 */       return true;
/* 1832 */     }  if (i == 99) {
/* 1833 */       setSlot(EnumItemSlot.OFFHAND, itemstack);
/* 1834 */       return true;
/* 1835 */     }  if (enumitemslot == null) {
/* 1836 */       int j = i - 200;
/*      */       
/* 1838 */       if (j >= 0 && j < this.enderChest.getSize()) {
/* 1839 */         this.enderChest.setItem(j, itemstack);
/* 1840 */         return true;
/*      */       } 
/* 1842 */       return false;
/*      */     } 
/*      */     
/* 1845 */     if (!itemstack.isEmpty()) {
/* 1846 */       if (!(itemstack.getItem() instanceof ItemArmor) && !(itemstack.getItem() instanceof ItemElytra)) {
/* 1847 */         if (enumitemslot != EnumItemSlot.HEAD) {
/* 1848 */           return false;
/*      */         }
/* 1850 */       } else if (EntityInsentient.d(itemstack) != enumitemslot) {
/* 1851 */         return false;
/*      */       } 
/*      */     }
/*      */     
/* 1855 */     this.inventory.setItem(enumitemslot.b() + this.inventory.items.size(), itemstack);
/* 1856 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public EnumMainHand getMainHand() {
/* 1862 */     return (((Byte)this.datawatcher.<Byte>get(bs)).byteValue() == 0) ? EnumMainHand.LEFT : EnumMainHand.RIGHT;
/*      */   }
/*      */   
/*      */   public void a(EnumMainHand enummainhand) {
/* 1866 */     this.datawatcher.set(bs, Byte.valueOf((byte)((enummainhand == EnumMainHand.LEFT) ? 0 : 1)));
/*      */   }
/*      */   
/*      */   public NBTTagCompound getShoulderEntityLeft() {
/* 1870 */     return this.datawatcher.<NBTTagCompound>get(bt);
/*      */   }
/*      */   
/*      */   public void setShoulderEntityLeft(NBTTagCompound nbttagcompound) {
/* 1874 */     this.datawatcher.set(bt, nbttagcompound);
/*      */   }
/*      */   
/*      */   public NBTTagCompound getShoulderEntityRight() {
/* 1878 */     return this.datawatcher.<NBTTagCompound>get(bu);
/*      */   }
/*      */   
/*      */   public void setShoulderEntityRight(NBTTagCompound nbttagcompound) {
/* 1882 */     this.datawatcher.set(bu, nbttagcompound);
/*      */   }
/*      */   
/*      */   public float dr() {
/* 1886 */     return (float)(1.0D / getAttributeInstance(GenericAttributes.g).getValue() * 20.0D);
/*      */   }
/*      */   
/*      */   public float n(float f) {
/* 1890 */     return MathHelper.a((this.aE + f) / dr(), 0.0F, 1.0F);
/*      */   }
/*      */   
/*      */   public void ds() {
/* 1894 */     this.aE = 0;
/*      */   }
/*      */   
/*      */   public ItemCooldown getCooldownTracker() {
/* 1898 */     return this.bW;
/*      */   }
/*      */   
/*      */   public void collide(Entity entity) {
/* 1902 */     if (!isSleeping()) {
/* 1903 */       super.collide(entity);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public float du() {
/* 1909 */     return (float)getAttributeInstance(GenericAttributes.j).getValue();
/*      */   }
/*      */   
/*      */   public boolean isCreativeAndOp() {
/* 1913 */     return (this.abilities.canInstantlyBuild && a(2, ""));
/*      */   }
/*      */   public abstract boolean isSpectator();
/*      */   public abstract boolean z();
/*      */   
/*      */   static class c implements Predicate<EntityMonster> { private final EntityHuman a;
/*      */     
/*      */     private c(EntityHuman entityhuman) {
/* 1921 */       this.a = entityhuman;
/*      */     }
/*      */     
/*      */     public boolean a(@Nullable EntityMonster entitymonster) {
/* 1925 */       return entitymonster.c(this.a);
/*      */     }
/*      */     
/*      */     public boolean apply(@Nullable EntityMonster object) {
/* 1929 */       return a(object);
/*      */     }
/*      */     
/*      */     c(EntityHuman entityhuman, Object object) {
/* 1933 */       this(entityhuman);
/*      */     } }
/*      */ 
/*      */   
/*      */   public enum EnumBedResult
/*      */   {
/* 1939 */     OK, NOT_POSSIBLE_HERE, NOT_POSSIBLE_NOW, TOO_FAR_AWAY, OTHER_PROBLEM, NOT_SAFE;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public enum EnumChatVisibility
/*      */   {
/* 1946 */     FULL(0, "options.chat.visibility.full"), SYSTEM(1, "options.chat.visibility.system"), HIDDEN(2, "options.chat.visibility.hidden");
/*      */     
/* 1948 */     private static final EnumChatVisibility[] d = new EnumChatVisibility[(values()).length];
/*      */ 
/*      */     
/*      */     private final int e;
/*      */ 
/*      */     
/*      */     private final String f;
/*      */ 
/*      */     
/*      */     static {
/* 1958 */       EnumChatVisibility[] aentityhuman_enumchatvisibility = values();
/* 1959 */       int i = aentityhuman_enumchatvisibility.length;
/*      */       
/* 1961 */       for (int j = 0; j < i; j++) {
/* 1962 */         EnumChatVisibility entityhuman_enumchatvisibility = aentityhuman_enumchatvisibility[j];
/*      */         
/* 1964 */         d[entityhuman_enumchatvisibility.e] = entityhuman_enumchatvisibility;
/*      */       } 
/*      */     }
/*      */     
/*      */     EnumChatVisibility(int i, String s) {
/*      */       this.e = i;
/*      */       this.f = s;
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityHuman.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */