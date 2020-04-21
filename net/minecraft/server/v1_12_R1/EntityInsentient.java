/*      */ package net.minecraft.server.v1_12_R1;
/*      */ 
/*      */ import com.google.common.collect.Maps;
/*      */ import java.util.Arrays;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Random;
/*      */ import java.util.UUID;
/*      */ import java.util.logging.Level;
/*      */ import javax.annotation.Nullable;
/*      */ import org.bukkit.craftbukkit.v1_12_R1.entity.CraftLivingEntity;
/*      */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*      */ import org.bukkit.entity.Entity;
/*      */ import org.bukkit.entity.Item;
/*      */ import org.bukkit.entity.LivingEntity;
/*      */ import org.bukkit.event.Event;
/*      */ import org.bukkit.event.entity.EntityPickupItemEvent;
/*      */ import org.bukkit.event.entity.EntityTargetEvent;
/*      */ import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
/*      */ import org.bukkit.event.entity.EntityUnleashEvent;
/*      */ 
/*      */ public abstract class EntityInsentient
/*      */   extends EntityLiving {
/*   25 */   private static final DataWatcherObject<Byte> a = DataWatcher.a((Class)EntityInsentient.class, DataWatcherRegistry.a);
/*      */   
/*      */   public int a_;
/*      */   protected int b_;
/*      */   private final ControllerLook lookController;
/*      */   protected ControllerMove moveController;
/*      */   protected ControllerJump g;
/*      */   private final EntityAIBodyControl c;
/*      */   protected NavigationAbstract navigation;
/*      */   public PathfinderGoalSelector goalSelector;
/*      */   public PathfinderGoalSelector targetSelector;
/*      */   private EntityLiving goalTarget;
/*      */   private final EntitySenses bw;
/*      */   private final NonNullList<ItemStack> bx;
/*      */   public float[] dropChanceHand;
/*      */   private final NonNullList<ItemStack> by;
/*      */   public float[] dropChanceArmor;
/*      */   public boolean persistent;
/*      */   private final Map<PathType, Float> bB;
/*      */   private MinecraftKey bC;
/*      */   private long bD;
/*      */   private boolean bE;
/*      */   private Entity leashHolder;
/*      */   private NBTTagCompound bG;
/*      */   
/*      */   public EntityInsentient(World world) {
/*   51 */     super(world);
/*   52 */     this.bx = NonNullList.a(2, ItemStack.a);
/*   53 */     this.dropChanceHand = new float[2];
/*   54 */     this.by = NonNullList.a(4, ItemStack.a);
/*   55 */     this.dropChanceArmor = new float[4];
/*   56 */     this.bB = Maps.newEnumMap(PathType.class);
/*   57 */     this.goalSelector = new PathfinderGoalSelector((world != null && world.methodProfiler != null) ? world.methodProfiler : null);
/*   58 */     this.targetSelector = new PathfinderGoalSelector((world != null && world.methodProfiler != null) ? world.methodProfiler : null);
/*   59 */     this.lookController = new ControllerLook(this);
/*   60 */     this.moveController = new ControllerMove(this);
/*   61 */     this.g = new ControllerJump(this);
/*   62 */     this.c = s();
/*   63 */     this.navigation = b(world);
/*   64 */     this.bw = new EntitySenses(this);
/*   65 */     Arrays.fill(this.dropChanceArmor, 0.085F);
/*   66 */     Arrays.fill(this.dropChanceHand, 0.085F);
/*   67 */     if (world != null && !world.isClientSide) {
/*   68 */       r();
/*      */     }
/*      */ 
/*      */     
/*   72 */     this.persistent = !isTypeNotPersistent();
/*      */   }
/*      */ 
/*      */   
/*      */   protected void r() {}
/*      */   
/*      */   protected void initAttributes() {
/*   79 */     super.initAttributes();
/*   80 */     getAttributeMap().b(GenericAttributes.FOLLOW_RANGE).setValue(16.0D);
/*      */   }
/*      */   
/*      */   protected NavigationAbstract b(World world) {
/*   84 */     return new Navigation(this, world);
/*      */   }
/*      */   
/*      */   public float a(PathType pathtype) {
/*   88 */     Float ofloat = this.bB.get(pathtype);
/*      */     
/*   90 */     return (ofloat == null) ? pathtype.a() : ofloat.floatValue();
/*      */   }
/*      */   
/*      */   public void a(PathType pathtype, float f) {
/*   94 */     this.bB.put(pathtype, Float.valueOf(f));
/*      */   }
/*      */   
/*      */   protected EntityAIBodyControl s() {
/*   98 */     return new EntityAIBodyControl(this);
/*      */   }
/*      */   
/*      */   public ControllerLook getControllerLook() {
/*  102 */     return this.lookController;
/*      */   }
/*      */   
/*      */   public ControllerMove getControllerMove() {
/*  106 */     return this.moveController;
/*      */   }
/*      */   
/*      */   public ControllerJump getControllerJump() {
/*  110 */     return this.g;
/*      */   }
/*      */   
/*      */   public NavigationAbstract getNavigation() {
/*  114 */     return this.navigation;
/*      */   }
/*      */   
/*      */   public EntitySenses getEntitySenses() {
/*  118 */     return this.bw;
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public EntityLiving getGoalTarget() {
/*  123 */     return this.goalTarget;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setGoalTarget(@Nullable EntityLiving entityliving) {
/*  128 */     setGoalTarget(entityliving, EntityTargetEvent.TargetReason.UNKNOWN, true);
/*      */   }
/*      */   
/*      */   public boolean setGoalTarget(EntityLiving entityliving, EntityTargetEvent.TargetReason reason, boolean fireEvent) {
/*  132 */     if (getGoalTarget() == entityliving) return false; 
/*  133 */     if (fireEvent) {
/*  134 */       if (reason == EntityTargetEvent.TargetReason.UNKNOWN && getGoalTarget() != null && entityliving == null) {
/*  135 */         reason = getGoalTarget().isAlive() ? EntityTargetEvent.TargetReason.FORGOT_TARGET : EntityTargetEvent.TargetReason.TARGET_DIED;
/*      */       }
/*  137 */       if (reason == EntityTargetEvent.TargetReason.UNKNOWN) {
/*  138 */         this.world.getServer().getLogger().log(Level.WARNING, "Unknown target reason, please report on the issue tracker", new Exception());
/*      */       }
/*  140 */       CraftLivingEntity ctarget = null;
/*  141 */       if (entityliving != null) {
/*  142 */         ctarget = (CraftLivingEntity)entityliving.getBukkitEntity();
/*      */       }
/*  144 */       EntityTargetLivingEntityEvent event = new EntityTargetLivingEntityEvent((Entity)getBukkitEntity(), (LivingEntity)ctarget, reason);
/*  145 */       this.world.getServer().getPluginManager().callEvent((Event)event);
/*  146 */       if (event.isCancelled()) {
/*  147 */         return false;
/*      */       }
/*      */       
/*  150 */       if (event.getTarget() != null) {
/*  151 */         entityliving = ((CraftLivingEntity)event.getTarget()).getHandle();
/*      */       } else {
/*  153 */         entityliving = null;
/*      */       } 
/*      */     } 
/*  156 */     this.goalTarget = entityliving;
/*  157 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean d(Class<? extends EntityLiving> oclass) {
/*  162 */     return (oclass != EntityGhast.class);
/*      */   }
/*      */   
/*      */   public void A() {}
/*      */   
/*      */   protected void i() {
/*  168 */     super.i();
/*  169 */     this.datawatcher.register(a, Byte.valueOf((byte)0));
/*      */   }
/*      */   
/*      */   public int C() {
/*  173 */     return 80;
/*      */   }
/*      */   
/*      */   public void D() {
/*  177 */     SoundEffect soundeffect = F();
/*      */     
/*  179 */     if (soundeffect != null) {
/*  180 */       a(soundeffect, cq(), cr());
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void Y() {
/*  186 */     super.Y();
/*  187 */     this.world.methodProfiler.a("mobBaseTick");
/*  188 */     if (isAlive() && this.random.nextInt(1000) < this.a_++) {
/*  189 */       p();
/*  190 */       D();
/*      */     } 
/*      */     
/*  193 */     this.world.methodProfiler.b();
/*      */   }
/*      */   
/*      */   protected void c(DamageSource damagesource) {
/*  197 */     p();
/*  198 */     super.c(damagesource);
/*      */   }
/*      */   
/*      */   private void p() {
/*  202 */     this.a_ = -C();
/*      */   }
/*      */   
/*      */   protected int getExpValue(EntityHuman entityhuman) {
/*  206 */     if (this.b_ > 0) {
/*  207 */       int i = this.b_;
/*      */       
/*      */       int j;
/*      */       
/*  211 */       for (j = 0; j < this.by.size(); j++) {
/*  212 */         if (!((ItemStack)this.by.get(j)).isEmpty() && this.dropChanceArmor[j] <= 1.0F) {
/*  213 */           i += 1 + this.random.nextInt(3);
/*      */         }
/*      */       } 
/*      */       
/*  217 */       for (j = 0; j < this.bx.size(); j++) {
/*  218 */         if (!((ItemStack)this.bx.get(j)).isEmpty() && this.dropChanceHand[j] <= 1.0F) {
/*  219 */           i += 1 + this.random.nextInt(3);
/*      */         }
/*      */       } 
/*      */       
/*  223 */       return i;
/*      */     } 
/*  225 */     return this.b_;
/*      */   }
/*      */ 
/*      */   
/*      */   public void doSpawnEffect() {
/*  230 */     if (this.world.isClientSide) {
/*  231 */       for (int i = 0; i < 20; i++) {
/*  232 */         double d0 = this.random.nextGaussian() * 0.02D;
/*  233 */         double d1 = this.random.nextGaussian() * 0.02D;
/*  234 */         double d2 = this.random.nextGaussian() * 0.02D;
/*      */ 
/*      */         
/*  237 */         this.world.addParticle(EnumParticle.EXPLOSION_NORMAL, this.locX + (this.random.nextFloat() * this.width * 2.0F) - this.width - d0 * 10.0D, this.locY + (this.random.nextFloat() * this.length) - d1 * 10.0D, this.locZ + (this.random.nextFloat() * this.width * 2.0F) - this.width - d2 * 10.0D, d0, d1, d2, new int[0]);
/*      */       } 
/*      */     } else {
/*  240 */       this.world.broadcastEntityEffect(this, (byte)20);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void B_() {
/*  246 */     super.B_();
/*  247 */     if (!this.world.isClientSide) {
/*  248 */       cZ();
/*  249 */       if (this.ticksLived % 5 == 0) {
/*  250 */         boolean flag = !(bE() instanceof EntityInsentient);
/*  251 */         boolean flag1 = !(bJ() instanceof EntityBoat);
/*      */         
/*  253 */         this.goalSelector.a(1, flag);
/*  254 */         this.goalSelector.a(4, (flag && flag1));
/*  255 */         this.goalSelector.a(2, flag);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected float g(float f, float f1) {
/*  262 */     this.c.a();
/*  263 */     return f1;
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   protected SoundEffect F() {
/*  268 */     return null;
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   protected Item getLoot() {
/*  273 */     return null;
/*      */   }
/*      */   
/*      */   protected void dropDeathLoot(boolean flag, int i) {
/*  277 */     Item item = getLoot();
/*      */     
/*  279 */     if (item != null) {
/*  280 */       int j = this.random.nextInt(3);
/*      */       
/*  282 */       if (i > 0) {
/*  283 */         j += this.random.nextInt(i + 1);
/*      */       }
/*      */       
/*  286 */       for (int k = 0; k < j; k++) {
/*  287 */         a(item, 1);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void a(DataConverterManager dataconvertermanager, Class<?> oclass) {
/*  294 */     dataconvertermanager.a(DataConverterTypes.ENTITY, new DataInspectorItemList(oclass, new String[] { "ArmorItems", "HandItems" }));
/*      */   }
/*      */   
/*      */   public void b(NBTTagCompound nbttagcompound) {
/*  298 */     super.b(nbttagcompound);
/*  299 */     nbttagcompound.setBoolean("CanPickUpLoot", cX());
/*  300 */     nbttagcompound.setBoolean("PersistenceRequired", this.persistent);
/*  301 */     NBTTagList nbttaglist = new NBTTagList();
/*      */ 
/*      */ 
/*      */     
/*  305 */     for (Iterator<ItemStack> iterator = this.by.iterator(); iterator.hasNext(); nbttaglist.add(nbttagcompound1)) {
/*  306 */       ItemStack itemstack = iterator.next();
/*      */       
/*  308 */       NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/*  309 */       if (!itemstack.isEmpty()) {
/*  310 */         itemstack.save(nbttagcompound1);
/*      */       }
/*      */     } 
/*      */     
/*  314 */     nbttagcompound.set("ArmorItems", nbttaglist);
/*  315 */     NBTTagList nbttaglist1 = new NBTTagList();
/*      */ 
/*      */ 
/*      */     
/*  319 */     for (Iterator<ItemStack> iterator1 = this.bx.iterator(); iterator1.hasNext(); nbttaglist1.add(nbttagcompound2)) {
/*  320 */       ItemStack itemstack1 = iterator1.next();
/*      */       
/*  322 */       NBTTagCompound nbttagcompound2 = new NBTTagCompound();
/*  323 */       if (!itemstack1.isEmpty()) {
/*  324 */         itemstack1.save(nbttagcompound2);
/*      */       }
/*      */     } 
/*      */     
/*  328 */     nbttagcompound.set("HandItems", nbttaglist1);
/*  329 */     NBTTagList nbttaglist2 = new NBTTagList();
/*  330 */     float[] afloat = this.dropChanceArmor;
/*  331 */     int i = afloat.length;
/*      */     
/*      */     int j;
/*      */     
/*  335 */     for (j = 0; j < i; j++) {
/*  336 */       float f = afloat[j];
/*      */       
/*  338 */       nbttaglist2.add(new NBTTagFloat(f));
/*      */     } 
/*      */     
/*  341 */     nbttagcompound.set("ArmorDropChances", nbttaglist2);
/*  342 */     NBTTagList nbttaglist3 = new NBTTagList();
/*  343 */     float[] afloat1 = this.dropChanceHand;
/*      */     
/*  345 */     j = afloat1.length;
/*      */     
/*  347 */     for (int k = 0; k < j; k++) {
/*  348 */       float f1 = afloat1[k];
/*      */       
/*  350 */       nbttaglist3.add(new NBTTagFloat(f1));
/*      */     } 
/*      */     
/*  353 */     nbttagcompound.set("HandDropChances", nbttaglist3);
/*  354 */     nbttagcompound.setBoolean("Leashed", this.bE);
/*  355 */     if (this.leashHolder != null) {
/*  356 */       NBTTagCompound nbttagcompound2 = new NBTTagCompound();
/*  357 */       if (this.leashHolder instanceof EntityLiving) {
/*  358 */         UUID uuid = this.leashHolder.getUniqueID();
/*      */         
/*  360 */         nbttagcompound2.a("UUID", uuid);
/*  361 */       } else if (this.leashHolder instanceof EntityHanging) {
/*  362 */         BlockPosition blockposition = ((EntityHanging)this.leashHolder).getBlockPosition();
/*      */         
/*  364 */         nbttagcompound2.setInt("X", blockposition.getX());
/*  365 */         nbttagcompound2.setInt("Y", blockposition.getY());
/*  366 */         nbttagcompound2.setInt("Z", blockposition.getZ());
/*      */       } 
/*      */       
/*  369 */       nbttagcompound.set("Leash", nbttagcompound2);
/*      */     } 
/*      */     
/*  372 */     nbttagcompound.setBoolean("LeftHanded", dd());
/*  373 */     if (this.bC != null) {
/*  374 */       nbttagcompound.setString("DeathLootTable", this.bC.toString());
/*  375 */       if (this.bD != 0L) {
/*  376 */         nbttagcompound.setLong("DeathLootTableSeed", this.bD);
/*      */       }
/*      */     } 
/*      */     
/*  380 */     if (isNoAI()) {
/*  381 */       nbttagcompound.setBoolean("NoAI", isNoAI());
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void a(NBTTagCompound nbttagcompound) {
/*  387 */     super.a(nbttagcompound);
/*      */ 
/*      */     
/*  390 */     if (nbttagcompound.hasKeyOfType("CanPickUpLoot", 1)) {
/*  391 */       boolean bool = nbttagcompound.getBoolean("CanPickUpLoot");
/*  392 */       if (isLevelAtLeast(nbttagcompound, 1) || bool) {
/*  393 */         m(bool);
/*      */       }
/*      */     } 
/*      */     
/*  397 */     boolean data = nbttagcompound.getBoolean("PersistenceRequired");
/*  398 */     if (isLevelAtLeast(nbttagcompound, 1) || data) {
/*  399 */       this.persistent = data;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  405 */     if (nbttagcompound.hasKeyOfType("ArmorItems", 9)) {
/*  406 */       NBTTagList nbttaglist = nbttagcompound.getList("ArmorItems", 10);
/*      */       
/*  408 */       for (int i = 0; i < this.by.size(); i++) {
/*  409 */         this.by.set(i, new ItemStack(nbttaglist.get(i)));
/*      */       }
/*      */     } 
/*      */     
/*  413 */     if (nbttagcompound.hasKeyOfType("HandItems", 9)) {
/*  414 */       NBTTagList nbttaglist = nbttagcompound.getList("HandItems", 10);
/*      */       
/*  416 */       for (int i = 0; i < this.bx.size(); i++) {
/*  417 */         this.bx.set(i, new ItemStack(nbttaglist.get(i)));
/*      */       }
/*      */     } 
/*      */     
/*  421 */     if (nbttagcompound.hasKeyOfType("ArmorDropChances", 9)) {
/*  422 */       NBTTagList nbttaglist = nbttagcompound.getList("ArmorDropChances", 5);
/*      */       
/*  424 */       for (int i = 0; i < nbttaglist.size(); i++) {
/*  425 */         this.dropChanceArmor[i] = nbttaglist.g(i);
/*      */       }
/*      */     } 
/*      */     
/*  429 */     if (nbttagcompound.hasKeyOfType("HandDropChances", 9)) {
/*  430 */       NBTTagList nbttaglist = nbttagcompound.getList("HandDropChances", 5);
/*      */       
/*  432 */       for (int i = 0; i < nbttaglist.size(); i++) {
/*  433 */         this.dropChanceHand[i] = nbttaglist.g(i);
/*      */       }
/*      */     } 
/*      */     
/*  437 */     this.bE = nbttagcompound.getBoolean("Leashed");
/*  438 */     if (this.bE && nbttagcompound.hasKeyOfType("Leash", 10)) {
/*  439 */       this.bG = nbttagcompound.getCompound("Leash");
/*      */     }
/*      */     
/*  442 */     o(nbttagcompound.getBoolean("LeftHanded"));
/*  443 */     if (nbttagcompound.hasKeyOfType("DeathLootTable", 8)) {
/*  444 */       this.bC = new MinecraftKey(nbttagcompound.getString("DeathLootTable"));
/*  445 */       this.bD = nbttagcompound.getLong("DeathLootTableSeed");
/*      */     } 
/*      */     
/*  448 */     setNoAI(nbttagcompound.getBoolean("NoAI"));
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   protected MinecraftKey J() {
/*  453 */     return null;
/*      */   }
/*      */   
/*      */   protected void a(boolean flag, int i, DamageSource damagesource) {
/*  457 */     MinecraftKey minecraftkey = this.bC;
/*      */     
/*  459 */     if (minecraftkey == null) {
/*  460 */       minecraftkey = J();
/*      */     }
/*      */     
/*  463 */     if (minecraftkey != null) {
/*  464 */       LootTable loottable = this.world.getLootTableRegistry().a(minecraftkey);
/*      */       
/*  466 */       this.bC = null;
/*  467 */       LootTableInfo.a loottableinfo_a = (new LootTableInfo.a((WorldServer)this.world)).a(this).a(damagesource);
/*      */       
/*  469 */       if (flag && this.killer != null) {
/*  470 */         loottableinfo_a = loottableinfo_a.a(this.killer).a(this.killer.du());
/*      */       }
/*      */       
/*  473 */       List<ItemStack> list = loottable.a((this.bD == 0L) ? this.random : new Random(this.bD), loottableinfo_a.a());
/*  474 */       Iterator<ItemStack> iterator = list.iterator();
/*      */       
/*  476 */       while (iterator.hasNext()) {
/*  477 */         ItemStack itemstack = iterator.next();
/*      */         
/*  479 */         a(itemstack, 0.0F);
/*      */       } 
/*      */       
/*  482 */       dropEquipment(flag, i);
/*      */     } else {
/*  484 */       super.a(flag, i, damagesource);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void n(float f) {
/*  490 */     this.bg = f;
/*      */   }
/*      */   
/*      */   public void o(float f) {
/*  494 */     this.bf = f;
/*      */   }
/*      */   
/*      */   public void p(float f) {
/*  498 */     this.be = f;
/*      */   }
/*      */   
/*      */   public void k(float f) {
/*  502 */     super.k(f);
/*  503 */     n(f);
/*      */   }
/*      */   
/*      */   public void n() {
/*  507 */     super.n();
/*  508 */     this.world.methodProfiler.a("looting");
/*  509 */     if (!this.world.isClientSide && cX() && !this.aU && this.world.getGameRules().getBoolean("mobGriefing")) {
/*  510 */       List<EntityItem> list = this.world.a(EntityItem.class, getBoundingBox().grow(1.0D, 0.0D, 1.0D));
/*  511 */       Iterator<EntityItem> iterator = list.iterator();
/*      */       
/*  513 */       while (iterator.hasNext()) {
/*  514 */         EntityItem entityitem = iterator.next();
/*      */         
/*  516 */         if (!entityitem.dead && !entityitem.getItemStack().isEmpty() && !entityitem.t()) {
/*  517 */           a(entityitem);
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  522 */     this.world.methodProfiler.b();
/*      */   }
/*      */   
/*      */   protected void a(EntityItem entityitem) {
/*  526 */     ItemStack itemstack = entityitem.getItemStack();
/*  527 */     EnumItemSlot enumitemslot = d(itemstack);
/*  528 */     boolean flag = true;
/*  529 */     ItemStack itemstack1 = getEquipment(enumitemslot);
/*      */     
/*  531 */     if (!itemstack1.isEmpty()) {
/*  532 */       if (enumitemslot.a() == EnumItemSlot.Function.HAND) {
/*  533 */         if (itemstack.getItem() instanceof ItemSword && !(itemstack1.getItem() instanceof ItemSword)) {
/*  534 */           flag = true;
/*  535 */         } else if (itemstack.getItem() instanceof ItemSword && itemstack1.getItem() instanceof ItemSword) {
/*  536 */           ItemSword itemsword = (ItemSword)itemstack.getItem();
/*  537 */           ItemSword itemsword1 = (ItemSword)itemstack1.getItem();
/*      */           
/*  539 */           if (itemsword.g() == itemsword1.g()) {
/*  540 */             flag = !(itemstack.getData() <= itemstack1.getData() && (!itemstack.hasTag() || itemstack1.hasTag()));
/*      */           } else {
/*  542 */             flag = (itemsword.g() > itemsword1.g());
/*      */           } 
/*  544 */         } else if (itemstack.getItem() instanceof ItemBow && itemstack1.getItem() instanceof ItemBow) {
/*  545 */           flag = (itemstack.hasTag() && !itemstack1.hasTag());
/*      */         } else {
/*  547 */           flag = false;
/*      */         } 
/*  549 */       } else if (itemstack.getItem() instanceof ItemArmor && !(itemstack1.getItem() instanceof ItemArmor)) {
/*  550 */         flag = true;
/*  551 */       } else if (itemstack.getItem() instanceof ItemArmor && itemstack1.getItem() instanceof ItemArmor && !EnchantmentManager.d(itemstack1)) {
/*  552 */         ItemArmor itemarmor = (ItemArmor)itemstack.getItem();
/*  553 */         ItemArmor itemarmor1 = (ItemArmor)itemstack1.getItem();
/*      */         
/*  555 */         if (itemarmor.d == itemarmor1.d) {
/*  556 */           flag = !(itemstack.getData() <= itemstack1.getData() && (!itemstack.hasTag() || itemstack1.hasTag()));
/*      */         } else {
/*  558 */           flag = (itemarmor.d > itemarmor1.d);
/*      */         } 
/*      */       } else {
/*  561 */         flag = false;
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*  566 */     boolean canPickup = (flag && c(itemstack));
/*      */     
/*  568 */     EntityPickupItemEvent entityEvent = new EntityPickupItemEvent((LivingEntity)getBukkitEntity(), (Item)entityitem.getBukkitEntity(), 0);
/*  569 */     entityEvent.setCancelled(!canPickup);
/*  570 */     this.world.getServer().getPluginManager().callEvent((Event)entityEvent);
/*  571 */     canPickup = !entityEvent.isCancelled();
/*  572 */     if (canPickup) {
/*      */       double d0;
/*      */ 
/*      */       
/*  576 */       switch (enumitemslot.a()) {
/*      */         case HAND:
/*  578 */           d0 = this.dropChanceHand[enumitemslot.b()];
/*      */           break;
/*      */         
/*      */         case null:
/*  582 */           d0 = this.dropChanceArmor[enumitemslot.b()];
/*      */           break;
/*      */         
/*      */         default:
/*  586 */           d0 = 0.0D;
/*      */           break;
/*      */       } 
/*  589 */       if (!itemstack1.isEmpty() && (this.random.nextFloat() - 0.1F) < d0) {
/*  590 */         this.forceDrops = true;
/*  591 */         a(itemstack1, 0.0F);
/*  592 */         this.forceDrops = false;
/*      */       } 
/*      */       
/*  595 */       setSlot(enumitemslot, itemstack);
/*  596 */       switch (enumitemslot.a()) {
/*      */         case HAND:
/*  598 */           this.dropChanceHand[enumitemslot.b()] = 2.0F;
/*      */           break;
/*      */         
/*      */         case null:
/*  602 */           this.dropChanceArmor[enumitemslot.b()] = 2.0F;
/*      */           break;
/*      */       } 
/*  605 */       this.persistent = true;
/*  606 */       receive(entityitem, itemstack.getCount());
/*  607 */       entityitem.die();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean c(ItemStack itemstack) {
/*  613 */     return true;
/*      */   }
/*      */   
/*      */   protected boolean isTypeNotPersistent() {
/*  617 */     return true;
/*      */   }
/*      */   
/*      */   protected void L() {
/*  621 */     if (this.persistent) {
/*  622 */       this.ticksFarFromPlayer = 0;
/*      */     } else {
/*  624 */       EntityHuman entityhuman = this.world.findNearbyPlayer(this, -1.0D);
/*      */       
/*  626 */       if (entityhuman != null) {
/*  627 */         double d0 = entityhuman.locX - this.locX;
/*  628 */         double d1 = entityhuman.locY - this.locY;
/*  629 */         double d2 = entityhuman.locZ - this.locZ;
/*  630 */         double d3 = d0 * d0 + d1 * d1 + d2 * d2;
/*      */         
/*  632 */         if (d3 > 16384.0D) {
/*  633 */           die();
/*      */         }
/*      */         
/*  636 */         if (this.ticksFarFromPlayer > 600 && this.random.nextInt(800) == 0 && d3 > 1024.0D) {
/*  637 */           die();
/*  638 */         } else if (d3 < 1024.0D) {
/*  639 */           this.ticksFarFromPlayer = 0;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected final void doTick() {
/*  647 */     this.ticksFarFromPlayer++;
/*  648 */     this.world.methodProfiler.a("checkDespawn");
/*  649 */     L();
/*  650 */     this.world.methodProfiler.b();
/*      */     
/*  652 */     if (this.fromMobSpawner) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/*  657 */     this.world.methodProfiler.a("sensing");
/*  658 */     this.bw.a();
/*  659 */     this.world.methodProfiler.b();
/*  660 */     this.world.methodProfiler.a("targetSelector");
/*  661 */     this.targetSelector.a();
/*  662 */     this.world.methodProfiler.b();
/*  663 */     this.world.methodProfiler.a("goalSelector");
/*  664 */     this.goalSelector.a();
/*  665 */     this.world.methodProfiler.b();
/*  666 */     this.world.methodProfiler.a("navigation");
/*  667 */     this.navigation.d();
/*  668 */     this.world.methodProfiler.b();
/*  669 */     this.world.methodProfiler.a("mob tick");
/*  670 */     M();
/*  671 */     this.world.methodProfiler.b();
/*  672 */     if (isPassenger() && bJ() instanceof EntityInsentient) {
/*  673 */       EntityInsentient entityinsentient = (EntityInsentient)bJ();
/*      */       
/*  675 */       entityinsentient.getNavigation().a(getNavigation().l(), 1.5D);
/*  676 */       entityinsentient.getControllerMove().a(getControllerMove());
/*      */     } 
/*      */     
/*  679 */     this.world.methodProfiler.a("controls");
/*  680 */     this.world.methodProfiler.a("move");
/*  681 */     this.moveController.a();
/*  682 */     this.world.methodProfiler.c("look");
/*  683 */     this.lookController.a();
/*  684 */     this.world.methodProfiler.c("jump");
/*  685 */     this.g.b();
/*  686 */     this.world.methodProfiler.b();
/*  687 */     this.world.methodProfiler.b();
/*      */   }
/*      */   
/*      */   protected void M() {}
/*      */   
/*      */   public int N() {
/*  693 */     return 40;
/*      */   }
/*      */   
/*      */   public int O() {
/*  697 */     return 10;
/*      */   }
/*      */   
/*      */   public void a(Entity entity, float f, float f1) {
/*  701 */     double d2, d0 = entity.locX - this.locX;
/*  702 */     double d1 = entity.locZ - this.locZ;
/*      */ 
/*      */     
/*  705 */     if (entity instanceof EntityLiving) {
/*  706 */       EntityLiving entityliving = (EntityLiving)entity;
/*      */       
/*  708 */       d2 = entityliving.locY + entityliving.getHeadHeight() - this.locY + getHeadHeight();
/*      */     } else {
/*  710 */       d2 = ((entity.getBoundingBox()).b + (entity.getBoundingBox()).e) / 2.0D - this.locY + getHeadHeight();
/*      */     } 
/*      */     
/*  713 */     double d3 = MathHelper.sqrt(d0 * d0 + d1 * d1);
/*  714 */     float f2 = (float)(MathHelper.c(d1, d0) * 57.2957763671875D) - 90.0F;
/*  715 */     float f3 = (float)-(MathHelper.c(d2, d3) * 57.2957763671875D);
/*      */     
/*  717 */     this.pitch = b(this.pitch, f3, f1);
/*  718 */     this.yaw = b(this.yaw, f2, f);
/*      */   }
/*      */   
/*      */   private float b(float f, float f1, float f2) {
/*  722 */     float f3 = MathHelper.g(f1 - f);
/*      */     
/*  724 */     if (f3 > f2) {
/*  725 */       f3 = f2;
/*      */     }
/*      */     
/*  728 */     if (f3 < -f2) {
/*  729 */       f3 = -f2;
/*      */     }
/*      */     
/*  732 */     return f + f3;
/*      */   }
/*      */   
/*      */   public boolean P() {
/*  736 */     IBlockData iblockdata = this.world.getType((new BlockPosition(this)).down());
/*      */     
/*  738 */     return iblockdata.a(this);
/*      */   }
/*      */   
/*      */   public boolean canSpawn() {
/*  742 */     return (!this.world.containsLiquid(getBoundingBox()) && this.world.getCubes(this, getBoundingBox()).isEmpty() && this.world.a(getBoundingBox(), this));
/*      */   }
/*      */   
/*      */   public int cU() {
/*  746 */     return 4;
/*      */   }
/*      */   
/*      */   public int bg() {
/*  750 */     if (getGoalTarget() == null) {
/*  751 */       return 3;
/*      */     }
/*  753 */     int i = (int)(getHealth() - getMaxHealth() * 0.33F);
/*      */     
/*  755 */     i -= (3 - this.world.getDifficulty().a()) * 4;
/*  756 */     if (i < 0) {
/*  757 */       i = 0;
/*      */     }
/*      */     
/*  760 */     return i + 3;
/*      */   }
/*      */ 
/*      */   
/*      */   public Iterable<ItemStack> aO() {
/*  765 */     return this.bx;
/*      */   }
/*      */   
/*      */   public Iterable<ItemStack> getArmorItems() {
/*  769 */     return this.by;
/*      */   }
/*      */   
/*      */   public ItemStack getEquipment(EnumItemSlot enumitemslot) {
/*  773 */     switch (enumitemslot.a()) {
/*      */       case HAND:
/*  775 */         return this.bx.get(enumitemslot.b());
/*      */       
/*      */       case null:
/*  778 */         return this.by.get(enumitemslot.b());
/*      */     } 
/*      */     
/*  781 */     return ItemStack.a;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setSlot(EnumItemSlot enumitemslot, ItemStack itemstack) {
/*  786 */     switch (enumitemslot.a()) {
/*      */       case HAND:
/*  788 */         this.bx.set(enumitemslot.b(), itemstack);
/*      */         break;
/*      */       
/*      */       case null:
/*  792 */         this.by.set(enumitemslot.b(), itemstack);
/*      */         break;
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void dropEquipment(boolean flag, int i) {
/*  798 */     EnumItemSlot[] aenumitemslot = EnumItemSlot.values();
/*  799 */     int j = aenumitemslot.length;
/*      */     
/*  801 */     for (int k = 0; k < j; k++) {
/*  802 */       double d0; EnumItemSlot enumitemslot = aenumitemslot[k];
/*  803 */       ItemStack itemstack = getEquipment(enumitemslot);
/*      */ 
/*      */       
/*  806 */       switch (enumitemslot.a()) {
/*      */         case HAND:
/*  808 */           d0 = this.dropChanceHand[enumitemslot.b()];
/*      */           break;
/*      */         
/*      */         case null:
/*  812 */           d0 = this.dropChanceArmor[enumitemslot.b()];
/*      */           break;
/*      */         
/*      */         default:
/*  816 */           d0 = 0.0D;
/*      */           break;
/*      */       } 
/*  819 */       boolean flag1 = (d0 > 1.0D);
/*      */       
/*  821 */       if (!itemstack.isEmpty() && !EnchantmentManager.shouldNotDrop(itemstack) && (flag || flag1) && (this.random.nextFloat() - i * 0.01F) < d0) {
/*  822 */         if (!flag1 && itemstack.f()) {
/*  823 */           itemstack.setData(itemstack.k() - this.random.nextInt(1 + this.random.nextInt(Math.max(itemstack.k() - 3, 1))));
/*      */         }
/*      */         
/*  826 */         a(itemstack, 0.0F);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void a(DifficultyDamageScaler difficultydamagescaler) {
/*  833 */     if (this.random.nextFloat() < 0.15F * difficultydamagescaler.d()) {
/*  834 */       int i = this.random.nextInt(2);
/*  835 */       float f = (this.world.getDifficulty() == EnumDifficulty.HARD) ? 0.1F : 0.25F;
/*      */       
/*  837 */       if (this.random.nextFloat() < 0.095F) {
/*  838 */         i++;
/*      */       }
/*      */       
/*  841 */       if (this.random.nextFloat() < 0.095F) {
/*  842 */         i++;
/*      */       }
/*      */       
/*  845 */       if (this.random.nextFloat() < 0.095F) {
/*  846 */         i++;
/*      */       }
/*      */       
/*  849 */       boolean flag = true;
/*  850 */       EnumItemSlot[] aenumitemslot = EnumItemSlot.values();
/*  851 */       int j = aenumitemslot.length;
/*      */       
/*  853 */       for (int k = 0; k < j; k++) {
/*  854 */         EnumItemSlot enumitemslot = aenumitemslot[k];
/*      */         
/*  856 */         if (enumitemslot.a() == EnumItemSlot.Function.ARMOR) {
/*  857 */           ItemStack itemstack = getEquipment(enumitemslot);
/*      */           
/*  859 */           if (!flag && this.random.nextFloat() < f) {
/*      */             break;
/*      */           }
/*      */           
/*  863 */           flag = false;
/*  864 */           if (itemstack.isEmpty()) {
/*  865 */             Item item = a(enumitemslot, i);
/*      */             
/*  867 */             if (item != null) {
/*  868 */               setSlot(enumitemslot, new ItemStack(item));
/*      */             }
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static EnumItemSlot d(ItemStack itemstack) {
/*  878 */     return (itemstack.getItem() != Item.getItemOf(Blocks.PUMPKIN) && itemstack.getItem() != Items.SKULL) ? ((itemstack.getItem() instanceof ItemArmor) ? ((ItemArmor)itemstack.getItem()).c : ((itemstack.getItem() == Items.cS) ? EnumItemSlot.CHEST : ((itemstack.getItem() == Items.SHIELD) ? EnumItemSlot.OFFHAND : EnumItemSlot.MAINHAND))) : EnumItemSlot.HEAD;
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public static Item a(EnumItemSlot enumitemslot, int i) {
/*  883 */     switch (enumitemslot) {
/*      */       case HEAD:
/*  885 */         if (i == 0)
/*  886 */           return Items.LEATHER_HELMET; 
/*  887 */         if (i == 1)
/*  888 */           return Items.GOLDEN_HELMET; 
/*  889 */         if (i == 2)
/*  890 */           return Items.CHAINMAIL_HELMET; 
/*  891 */         if (i == 3)
/*  892 */           return Items.IRON_HELMET; 
/*  893 */         if (i == 4) {
/*  894 */           return Items.DIAMOND_HELMET;
/*      */         }
/*      */       
/*      */       case null:
/*  898 */         if (i == 0)
/*  899 */           return Items.LEATHER_CHESTPLATE; 
/*  900 */         if (i == 1)
/*  901 */           return Items.GOLDEN_CHESTPLATE; 
/*  902 */         if (i == 2)
/*  903 */           return Items.CHAINMAIL_CHESTPLATE; 
/*  904 */         if (i == 3)
/*  905 */           return Items.IRON_CHESTPLATE; 
/*  906 */         if (i == 4) {
/*  907 */           return Items.DIAMOND_CHESTPLATE;
/*      */         }
/*      */       
/*      */       case LEGS:
/*  911 */         if (i == 0)
/*  912 */           return Items.LEATHER_LEGGINGS; 
/*  913 */         if (i == 1)
/*  914 */           return Items.GOLDEN_LEGGINGS; 
/*  915 */         if (i == 2)
/*  916 */           return Items.CHAINMAIL_LEGGINGS; 
/*  917 */         if (i == 3)
/*  918 */           return Items.IRON_LEGGINGS; 
/*  919 */         if (i == 4) {
/*  920 */           return Items.DIAMOND_LEGGINGS;
/*      */         }
/*      */       
/*      */       case FEET:
/*  924 */         if (i == 0)
/*  925 */           return Items.LEATHER_BOOTS; 
/*  926 */         if (i == 1)
/*  927 */           return Items.GOLDEN_BOOTS; 
/*  928 */         if (i == 2)
/*  929 */           return Items.CHAINMAIL_BOOTS; 
/*  930 */         if (i == 3)
/*  931 */           return Items.IRON_BOOTS; 
/*  932 */         if (i == 4) {
/*  933 */           return Items.DIAMOND_BOOTS;
/*      */         }
/*      */         break;
/*      */     } 
/*  937 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void b(DifficultyDamageScaler difficultydamagescaler) {
/*  942 */     float f = difficultydamagescaler.d();
/*      */     
/*  944 */     if (!getItemInMainHand().isEmpty() && this.random.nextFloat() < 0.25F * f) {
/*  945 */       setSlot(EnumItemSlot.MAINHAND, EnchantmentManager.a(this.random, getItemInMainHand(), (int)(5.0F + f * this.random.nextInt(18)), false));
/*      */     }
/*      */     
/*  948 */     EnumItemSlot[] aenumitemslot = EnumItemSlot.values();
/*  949 */     int i = aenumitemslot.length;
/*      */     
/*  951 */     for (int j = 0; j < i; j++) {
/*  952 */       EnumItemSlot enumitemslot = aenumitemslot[j];
/*      */       
/*  954 */       if (enumitemslot.a() == EnumItemSlot.Function.ARMOR) {
/*  955 */         ItemStack itemstack = getEquipment(enumitemslot);
/*      */         
/*  957 */         if (!itemstack.isEmpty() && this.random.nextFloat() < 0.5F * f) {
/*  958 */           setSlot(enumitemslot, EnchantmentManager.a(this.random, itemstack, (int)(5.0F + f * this.random.nextInt(18)), false));
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public GroupDataEntity prepare(DifficultyDamageScaler difficultydamagescaler, @Nullable GroupDataEntity groupdataentity) {
/*  967 */     getAttributeInstance(GenericAttributes.FOLLOW_RANGE).b(new AttributeModifier("Random spawn bonus", this.random.nextGaussian() * 0.05D, 1));
/*  968 */     if (this.random.nextFloat() < 0.05F) {
/*  969 */       o(true);
/*      */     } else {
/*  971 */       o(false);
/*      */     } 
/*      */     
/*  974 */     return groupdataentity;
/*      */   }
/*      */   
/*      */   public boolean cV() {
/*  978 */     return false;
/*      */   }
/*      */   
/*      */   public void cW() {
/*  982 */     this.persistent = true;
/*      */   }
/*      */   
/*      */   public void a(EnumItemSlot enumitemslot, float f) {
/*  986 */     switch (enumitemslot.a()) {
/*      */       case HAND:
/*  988 */         this.dropChanceHand[enumitemslot.b()] = f;
/*      */         break;
/*      */       
/*      */       case null:
/*  992 */         this.dropChanceArmor[enumitemslot.b()] = f;
/*      */         break;
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean cX() {
/*  998 */     return this.canPickUpLoot;
/*      */   }
/*      */   
/*      */   public void m(boolean flag) {
/* 1002 */     this.canPickUpLoot = flag;
/*      */   }
/*      */   
/*      */   public boolean isPersistent() {
/* 1006 */     return this.persistent;
/*      */   }
/*      */   
/*      */   public final boolean b(EntityHuman entityhuman, EnumHand enumhand) {
/* 1010 */     if (isLeashed() && getLeashHolder() == entityhuman) {
/*      */       
/* 1012 */       if (CraftEventFactory.callPlayerUnleashEntityEvent(this, entityhuman).isCancelled()) {
/* 1013 */         ((EntityPlayer)entityhuman).playerConnection.sendPacket(new PacketPlayOutAttachEntity(this, getLeashHolder()));
/* 1014 */         return false;
/*      */       } 
/*      */       
/* 1017 */       unleash(true, !entityhuman.abilities.canInstantlyBuild);
/* 1018 */       return true;
/*      */     } 
/* 1020 */     ItemStack itemstack = entityhuman.b(enumhand);
/*      */     
/* 1022 */     if (itemstack.getItem() == Items.LEAD && a(entityhuman)) {
/*      */       
/* 1024 */       if (CraftEventFactory.callPlayerLeashEntityEvent(this, entityhuman, entityhuman).isCancelled()) {
/* 1025 */         ((EntityPlayer)entityhuman).playerConnection.sendPacket(new PacketPlayOutAttachEntity(this, getLeashHolder()));
/* 1026 */         return false;
/*      */       } 
/*      */       
/* 1029 */       setLeashHolder(entityhuman, true);
/* 1030 */       itemstack.subtract(1);
/* 1031 */       return true;
/*      */     } 
/* 1033 */     return a(entityhuman, enumhand) ? true : super.b(entityhuman, enumhand);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean a(EntityHuman entityhuman, EnumHand enumhand) {
/* 1039 */     return false;
/*      */   }
/*      */   
/*      */   protected void cZ() {
/* 1043 */     if (this.bG != null) {
/* 1044 */       de();
/*      */     }
/*      */     
/* 1047 */     if (this.bE) {
/* 1048 */       if (!isAlive()) {
/* 1049 */         this.world.getServer().getPluginManager().callEvent((Event)new EntityUnleashEvent((Entity)getBukkitEntity(), EntityUnleashEvent.UnleashReason.PLAYER_UNLEASH));
/* 1050 */         unleash(true, true);
/*      */       } 
/*      */       
/* 1053 */       if (this.leashHolder == null || this.leashHolder.dead) {
/* 1054 */         this.world.getServer().getPluginManager().callEvent((Event)new EntityUnleashEvent((Entity)getBukkitEntity(), EntityUnleashEvent.UnleashReason.HOLDER_GONE));
/* 1055 */         unleash(true, true);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void unleash(boolean flag, boolean flag1) {
/* 1061 */     if (this.bE) {
/* 1062 */       this.bE = false;
/* 1063 */       this.leashHolder = null;
/* 1064 */       if (!this.world.isClientSide && flag1) {
/* 1065 */         this.forceDrops = true;
/* 1066 */         a(Items.LEAD, 1);
/* 1067 */         this.forceDrops = false;
/*      */       } 
/*      */       
/* 1070 */       if (!this.world.isClientSide && flag && this.world instanceof WorldServer) {
/* 1071 */         ((WorldServer)this.world).getTracker().a(this, new PacketPlayOutAttachEntity(this, null));
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean a(EntityHuman entityhuman) {
/* 1078 */     return (!isLeashed() && !(this instanceof IMonster));
/*      */   }
/*      */   
/*      */   public boolean isLeashed() {
/* 1082 */     return this.bE;
/*      */   }
/*      */   
/*      */   public Entity getLeashHolder() {
/* 1086 */     return this.leashHolder;
/*      */   }
/*      */   
/*      */   public void setLeashHolder(Entity entity, boolean flag) {
/* 1090 */     this.bE = true;
/* 1091 */     this.leashHolder = entity;
/* 1092 */     if (!this.world.isClientSide && flag && this.world instanceof WorldServer) {
/* 1093 */       ((WorldServer)this.world).getTracker().a(this, new PacketPlayOutAttachEntity(this, this.leashHolder));
/*      */     }
/*      */     
/* 1096 */     if (isPassenger()) {
/* 1097 */       stopRiding();
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean a(Entity entity, boolean flag) {
/* 1103 */     boolean flag1 = super.a(entity, flag);
/*      */     
/* 1105 */     if (flag1 && isLeashed()) {
/* 1106 */       unleash(true, true);
/*      */     }
/*      */     
/* 1109 */     return flag1;
/*      */   }
/*      */   
/*      */   private void de() {
/* 1113 */     if (this.bE && this.bG != null) {
/* 1114 */       if (this.bG.b("UUID")) {
/* 1115 */         UUID uuid = this.bG.a("UUID");
/* 1116 */         List<EntityLiving> list = this.world.a(EntityLiving.class, getBoundingBox().g(10.0D));
/* 1117 */         Iterator<EntityLiving> iterator = list.iterator();
/*      */         
/* 1119 */         while (iterator.hasNext()) {
/* 1120 */           EntityLiving entityliving = iterator.next();
/*      */           
/* 1122 */           if (entityliving.getUniqueID().equals(uuid)) {
/* 1123 */             setLeashHolder(entityliving, true);
/*      */             break;
/*      */           } 
/*      */         } 
/* 1127 */       } else if (this.bG.hasKeyOfType("X", 99) && this.bG.hasKeyOfType("Y", 99) && this.bG.hasKeyOfType("Z", 99)) {
/* 1128 */         BlockPosition blockposition = new BlockPosition(this.bG.getInt("X"), this.bG.getInt("Y"), this.bG.getInt("Z"));
/* 1129 */         EntityLeash entityleash = EntityLeash.b(this.world, blockposition);
/*      */         
/* 1131 */         if (entityleash == null) {
/* 1132 */           entityleash = EntityLeash.a(this.world, blockposition);
/*      */         }
/*      */         
/* 1135 */         setLeashHolder(entityleash, true);
/*      */       } else {
/* 1137 */         this.world.getServer().getPluginManager().callEvent((Event)new EntityUnleashEvent((Entity)getBukkitEntity(), EntityUnleashEvent.UnleashReason.UNKNOWN));
/* 1138 */         unleash(false, true);
/*      */       } 
/*      */     }
/*      */     
/* 1142 */     this.bG = null;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean c(int i, ItemStack itemstack) {
/*      */     EnumItemSlot enumitemslot;
/* 1148 */     if (i == 98) {
/* 1149 */       enumitemslot = EnumItemSlot.MAINHAND;
/* 1150 */     } else if (i == 99) {
/* 1151 */       enumitemslot = EnumItemSlot.OFFHAND;
/* 1152 */     } else if (i == 100 + EnumItemSlot.HEAD.b()) {
/* 1153 */       enumitemslot = EnumItemSlot.HEAD;
/* 1154 */     } else if (i == 100 + EnumItemSlot.CHEST.b()) {
/* 1155 */       enumitemslot = EnumItemSlot.CHEST;
/* 1156 */     } else if (i == 100 + EnumItemSlot.LEGS.b()) {
/* 1157 */       enumitemslot = EnumItemSlot.LEGS;
/*      */     } else {
/* 1159 */       if (i != 100 + EnumItemSlot.FEET.b()) {
/* 1160 */         return false;
/*      */       }
/*      */       
/* 1163 */       enumitemslot = EnumItemSlot.FEET;
/*      */     } 
/*      */     
/* 1166 */     if (!itemstack.isEmpty() && !b(enumitemslot, itemstack) && enumitemslot != EnumItemSlot.HEAD) {
/* 1167 */       return false;
/*      */     }
/* 1169 */     setSlot(enumitemslot, itemstack);
/* 1170 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean bI() {
/* 1175 */     return (cV() && super.bI());
/*      */   }
/*      */   
/*      */   public static boolean b(EnumItemSlot enumitemslot, ItemStack itemstack) {
/* 1179 */     EnumItemSlot enumitemslot1 = d(itemstack);
/*      */     
/* 1181 */     return !(enumitemslot1 != enumitemslot && (enumitemslot1 != EnumItemSlot.MAINHAND || enumitemslot != EnumItemSlot.OFFHAND) && (enumitemslot1 != EnumItemSlot.OFFHAND || enumitemslot != EnumItemSlot.MAINHAND));
/*      */   }
/*      */   
/*      */   public boolean cC() {
/* 1185 */     return (super.cC() && !isNoAI());
/*      */   }
/*      */   
/*      */   public void setNoAI(boolean flag) {
/* 1189 */     byte b0 = ((Byte)this.datawatcher.<Byte>get(a)).byteValue();
/*      */     
/* 1191 */     this.datawatcher.set(a, Byte.valueOf(flag ? (byte)(b0 | 0x1) : (byte)(b0 & 0xFFFFFFFE)));
/*      */   }
/*      */   
/*      */   public void o(boolean flag) {
/* 1195 */     byte b0 = ((Byte)this.datawatcher.<Byte>get(a)).byteValue();
/*      */     
/* 1197 */     this.datawatcher.set(a, Byte.valueOf(flag ? (byte)(b0 | 0x2) : (byte)(b0 & 0xFFFFFFFD)));
/*      */   }
/*      */   
/*      */   public boolean isNoAI() {
/* 1201 */     return ((((Byte)this.datawatcher.<Byte>get(a)).byteValue() & 0x1) != 0);
/*      */   }
/*      */   
/*      */   public boolean dd() {
/* 1205 */     return ((((Byte)this.datawatcher.<Byte>get(a)).byteValue() & 0x2) != 0);
/*      */   }
/*      */   
/*      */   public EnumMainHand getMainHand() {
/* 1209 */     return dd() ? EnumMainHand.LEFT : EnumMainHand.RIGHT;
/*      */   }
/*      */   
/*      */   public enum EnumEntityPositionType
/*      */   {
/* 1214 */     ON_GROUND, IN_AIR, IN_WATER;
/*      */   }
/*      */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityInsentient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */