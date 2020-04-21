/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.Calendar;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.entity.CreatureSpawnEvent;
/*     */ import org.bukkit.event.entity.EntityCombustByEntityEvent;
/*     */ import org.bukkit.event.entity.EntityCombustEvent;
/*     */ import org.bukkit.event.entity.EntityTargetEvent;
/*     */ 
/*     */ public class EntityZombie
/*     */   extends EntityMonster
/*     */ {
/*  17 */   protected static final IAttribute a = (new AttributeRanged(null, "zombie.spawnReinforcements", 0.0D, 0.0D, 1.0D)).a("Spawn Reinforcements Chance");
/*  18 */   private static final UUID b = UUID.fromString("B9766B59-9566-4402-BC1F-2EE2A276D836");
/*  19 */   private static final AttributeModifier c = new AttributeModifier(b, "Baby speed boost", 0.5D, 1);
/*  20 */   private static final DataWatcherObject<Boolean> bx = DataWatcher.a((Class)EntityZombie.class, DataWatcherRegistry.h);
/*  21 */   private static final DataWatcherObject<Integer> by = DataWatcher.a((Class)EntityZombie.class, DataWatcherRegistry.b);
/*  22 */   private static final DataWatcherObject<Boolean> bz = DataWatcher.a((Class)EntityZombie.class, DataWatcherRegistry.h);
/*  23 */   private final PathfinderGoalBreakDoor bA = new PathfinderGoalBreakDoor(this);
/*     */   private boolean bB;
/*  25 */   private float bC = -1.0F;
/*     */   private float bD;
/*     */   
/*     */   public EntityZombie(World world) {
/*  29 */     super(world);
/*  30 */     setSize(0.6F, 1.95F);
/*     */   }
/*     */   
/*     */   protected void r() {
/*  34 */     this.goalSelector.a(0, new PathfinderGoalFloat(this));
/*  35 */     this.goalSelector.a(2, new PathfinderGoalZombieAttack(this, 1.0D, false));
/*  36 */     this.goalSelector.a(5, new PathfinderGoalMoveTowardsRestriction(this, 1.0D));
/*  37 */     this.goalSelector.a(7, new PathfinderGoalRandomStrollLand(this, 1.0D));
/*  38 */     this.goalSelector.a(8, new PathfinderGoalLookAtPlayer(this, (Class)EntityHuman.class, 8.0F));
/*  39 */     this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
/*  40 */     do_();
/*     */   }
/*     */   
/*     */   protected void do_() {
/*  44 */     this.goalSelector.a(6, new PathfinderGoalMoveThroughVillage(this, 1.0D, false));
/*  45 */     this.targetSelector.a(1, new PathfinderGoalHurtByTarget(this, true, new Class[] { EntityPigZombie.class }));
/*  46 */     this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget<>(this, EntityHuman.class, true));
/*  47 */     if (this.world.spigotConfig.zombieAggressiveTowardsVillager) this.targetSelector.a(3, new PathfinderGoalNearestAttackableTarget<>(this, EntityVillager.class, false)); 
/*  48 */     this.targetSelector.a(3, new PathfinderGoalNearestAttackableTarget<>(this, EntityIronGolem.class, true));
/*     */   }
/*     */   
/*     */   protected void initAttributes() {
/*  52 */     super.initAttributes();
/*  53 */     getAttributeInstance(GenericAttributes.FOLLOW_RANGE).setValue(35.0D);
/*  54 */     getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.23000000417232513D);
/*  55 */     getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(3.0D);
/*  56 */     getAttributeInstance(GenericAttributes.h).setValue(2.0D);
/*  57 */     getAttributeMap().b(a).setValue(this.random.nextDouble() * 0.10000000149011612D);
/*     */   }
/*     */   
/*     */   protected void i() {
/*  61 */     super.i();
/*  62 */     getDataWatcher().register(bx, Boolean.valueOf(false));
/*  63 */     getDataWatcher().register(by, Integer.valueOf(0));
/*  64 */     getDataWatcher().register(bz, Boolean.valueOf(false));
/*     */   }
/*     */   
/*     */   public void a(boolean flag) {
/*  68 */     getDataWatcher().set(bz, Boolean.valueOf(flag));
/*     */   }
/*     */   
/*     */   public boolean dr() {
/*  72 */     return this.bB;
/*     */   }
/*     */   
/*     */   public void p(boolean flag) {
/*  76 */     if (this.bB != flag) {
/*  77 */       this.bB = flag;
/*  78 */       ((Navigation)getNavigation()).a(flag);
/*  79 */       if (flag) {
/*  80 */         this.goalSelector.a(1, this.bA);
/*     */       } else {
/*  82 */         this.goalSelector.a(this.bA);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBaby() {
/*  89 */     return ((Boolean)getDataWatcher().<Boolean>get(bx)).booleanValue();
/*     */   }
/*     */   
/*     */   protected int getExpValue(EntityHuman entityhuman) {
/*  93 */     if (isBaby()) {
/*  94 */       this.b_ = (int)(this.b_ * 2.5F);
/*     */     }
/*     */     
/*  97 */     return super.getExpValue(entityhuman);
/*     */   }
/*     */   
/*     */   public void setBaby(boolean flag) {
/* 101 */     getDataWatcher().set(bx, Boolean.valueOf(flag));
/* 102 */     if (this.world != null && !this.world.isClientSide) {
/* 103 */       AttributeInstance attributeinstance = getAttributeInstance(GenericAttributes.MOVEMENT_SPEED);
/*     */       
/* 105 */       attributeinstance.c(c);
/* 106 */       if (flag) {
/* 107 */         attributeinstance.b(c);
/*     */       }
/*     */     } 
/*     */     
/* 111 */     r(flag);
/*     */   }
/*     */   
/*     */   public void a(DataWatcherObject<?> datawatcherobject) {
/* 115 */     if (bx.equals(datawatcherobject)) {
/* 116 */       r(isBaby());
/*     */     }
/*     */     
/* 119 */     super.a(datawatcherobject);
/*     */   }
/*     */   
/*     */   public void n() {
/* 123 */     if (this.world.D() && !this.world.isClientSide && !isBaby() && p()) {
/* 124 */       float f = aw();
/*     */       
/* 126 */       if (f > 0.5F && this.random.nextFloat() * 30.0F < (f - 0.4F) * 2.0F && this.world.h(new BlockPosition(this.locX, this.locY + getHeadHeight(), this.locZ))) {
/* 127 */         boolean flag = true;
/* 128 */         ItemStack itemstack = getEquipment(EnumItemSlot.HEAD);
/*     */         
/* 130 */         if (!itemstack.isEmpty()) {
/* 131 */           if (itemstack.f()) {
/* 132 */             itemstack.setData(itemstack.i() + this.random.nextInt(2));
/* 133 */             if (itemstack.i() >= itemstack.k()) {
/* 134 */               b(itemstack);
/* 135 */               setSlot(EnumItemSlot.HEAD, ItemStack.a);
/*     */             } 
/*     */           } 
/*     */           
/* 139 */           flag = false;
/*     */         } 
/*     */         
/* 142 */         if (flag) {
/*     */           
/* 144 */           EntityCombustEvent event = new EntityCombustEvent((Entity)getBukkitEntity(), 8);
/* 145 */           this.world.getServer().getPluginManager().callEvent((Event)event);
/*     */           
/* 147 */           if (!event.isCancelled()) {
/* 148 */             setOnFire(event.getDuration());
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 155 */     super.n();
/*     */   }
/*     */   
/*     */   protected boolean p() {
/* 159 */     return true;
/*     */   }
/*     */   
/*     */   public boolean damageEntity(DamageSource damagesource, float f) {
/* 163 */     if (super.damageEntity(damagesource, f)) {
/* 164 */       EntityLiving entityliving = getGoalTarget();
/*     */       
/* 166 */       if (entityliving == null && damagesource.getEntity() instanceof EntityLiving) {
/* 167 */         entityliving = (EntityLiving)damagesource.getEntity();
/*     */       }
/*     */       
/* 170 */       if (entityliving != null && this.world.getDifficulty() == EnumDifficulty.HARD && this.random.nextFloat() < getAttributeInstance(a).getValue() && this.world.getGameRules().getBoolean("doMobSpawning")) {
/* 171 */         int i = MathHelper.floor(this.locX);
/* 172 */         int j = MathHelper.floor(this.locY);
/* 173 */         int k = MathHelper.floor(this.locZ);
/* 174 */         EntityZombie entityzombie = new EntityZombie(this.world);
/*     */         
/* 176 */         for (int l = 0; l < 50; l++) {
/* 177 */           int i1 = i + MathHelper.nextInt(this.random, 7, 40) * MathHelper.nextInt(this.random, -1, 1);
/* 178 */           int j1 = j + MathHelper.nextInt(this.random, 7, 40) * MathHelper.nextInt(this.random, -1, 1);
/* 179 */           int k1 = k + MathHelper.nextInt(this.random, 7, 40) * MathHelper.nextInt(this.random, -1, 1);
/*     */           
/* 181 */           if (this.world.getType(new BlockPosition(i1, j1 - 1, k1)).q() && this.world.getLightLevel(new BlockPosition(i1, j1, k1)) < 10) {
/* 182 */             entityzombie.setPosition(i1, j1, k1);
/* 183 */             if (!this.world.isPlayerNearby(i1, j1, k1, 7.0D) && this.world.a(entityzombie.getBoundingBox(), entityzombie) && this.world.getCubes(entityzombie, entityzombie.getBoundingBox()).isEmpty() && !this.world.containsLiquid(entityzombie.getBoundingBox())) {
/* 184 */               this.world.addEntity(entityzombie, CreatureSpawnEvent.SpawnReason.REINFORCEMENTS);
/* 185 */               entityzombie.setGoalTarget(entityliving, EntityTargetEvent.TargetReason.REINFORCEMENT_TARGET, true);
/* 186 */               entityzombie.prepare(this.world.D(new BlockPosition(entityzombie)), (GroupDataEntity)null);
/* 187 */               getAttributeInstance(a).b(new AttributeModifier("Zombie reinforcement caller charge", -0.05000000074505806D, 0));
/* 188 */               entityzombie.getAttributeInstance(a).b(new AttributeModifier("Zombie reinforcement callee charge", -0.05000000074505806D, 0));
/*     */               
/*     */               break;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/* 195 */       return true;
/*     */     } 
/* 197 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean B(Entity entity) {
/* 202 */     boolean flag = super.B(entity);
/*     */     
/* 204 */     if (flag) {
/* 205 */       float f = this.world.D(new BlockPosition(this)).b();
/*     */       
/* 207 */       if (getItemInMainHand().isEmpty() && isBurning() && this.random.nextFloat() < f * 0.3F) {
/*     */         
/* 209 */         EntityCombustByEntityEvent event = new EntityCombustByEntityEvent((Entity)getBukkitEntity(), (Entity)entity.getBukkitEntity(), 2 * (int)f);
/* 210 */         this.world.getServer().getPluginManager().callEvent((Event)event);
/*     */         
/* 212 */         if (!event.isCancelled()) {
/* 213 */           entity.setOnFire(event.getDuration());
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 219 */     return flag;
/*     */   }
/*     */   
/*     */   protected SoundEffect F() {
/* 223 */     return SoundEffects.ji;
/*     */   }
/*     */   
/*     */   protected SoundEffect d(DamageSource damagesource) {
/* 227 */     return SoundEffects.jq;
/*     */   }
/*     */   
/*     */   protected SoundEffect cf() {
/* 231 */     return SoundEffects.jm;
/*     */   }
/*     */   
/*     */   protected SoundEffect dm() {
/* 235 */     return SoundEffects.jw;
/*     */   }
/*     */   
/*     */   protected void a(BlockPosition blockposition, Block block) {
/* 239 */     a(dm(), 0.15F, 1.0F);
/*     */   }
/*     */   
/*     */   public EnumMonsterType getMonsterType() {
/* 243 */     return EnumMonsterType.UNDEAD;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   protected MinecraftKey J() {
/* 248 */     return LootTables.am;
/*     */   }
/*     */   
/*     */   protected void a(DifficultyDamageScaler difficultydamagescaler) {
/* 252 */     super.a(difficultydamagescaler);
/* 253 */     if (this.random.nextFloat() < ((this.world.getDifficulty() == EnumDifficulty.HARD) ? 0.05F : 0.01F)) {
/* 254 */       int i = this.random.nextInt(3);
/*     */       
/* 256 */       if (i == 0) {
/* 257 */         setSlot(EnumItemSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));
/*     */       } else {
/* 259 */         setSlot(EnumItemSlot.MAINHAND, new ItemStack(Items.IRON_SHOVEL));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void c(DataConverterManager dataconvertermanager) {
/* 266 */     EntityInsentient.a(dataconvertermanager, EntityZombie.class);
/*     */   }
/*     */   
/*     */   public void b(NBTTagCompound nbttagcompound) {
/* 270 */     super.b(nbttagcompound);
/* 271 */     if (isBaby()) {
/* 272 */       nbttagcompound.setBoolean("IsBaby", true);
/*     */     }
/*     */     
/* 275 */     nbttagcompound.setBoolean("CanBreakDoors", dr());
/*     */   }
/*     */   
/*     */   public void a(NBTTagCompound nbttagcompound) {
/* 279 */     super.a(nbttagcompound);
/* 280 */     if (nbttagcompound.getBoolean("IsBaby")) {
/* 281 */       setBaby(true);
/*     */     }
/*     */     
/* 284 */     p(nbttagcompound.getBoolean("CanBreakDoors"));
/*     */   }
/*     */   
/*     */   public void b(EntityLiving entityliving) {
/* 288 */     super.b(entityliving);
/* 289 */     if ((this.world.getDifficulty() == EnumDifficulty.NORMAL || this.world.getDifficulty() == EnumDifficulty.HARD) && entityliving instanceof EntityVillager) {
/* 290 */       if (this.world.getDifficulty() != EnumDifficulty.HARD && this.random.nextBoolean()) {
/*     */         return;
/*     */       }
/*     */       
/* 294 */       EntityVillager entityvillager = (EntityVillager)entityliving;
/* 295 */       EntityZombieVillager entityzombievillager = new EntityZombieVillager(this.world);
/*     */       
/* 297 */       entityzombievillager.u(entityvillager);
/* 298 */       this.world.kill(entityvillager);
/* 299 */       entityzombievillager.prepare(this.world.D(new BlockPosition(entityzombievillager)), new GroupDataZombie(false, null));
/* 300 */       entityzombievillager.setProfession(entityvillager.getProfession());
/* 301 */       entityzombievillager.setBaby(entityvillager.isBaby());
/* 302 */       entityzombievillager.setNoAI(entityvillager.isNoAI());
/* 303 */       if (entityvillager.hasCustomName()) {
/* 304 */         entityzombievillager.setCustomName(entityvillager.getCustomName());
/* 305 */         entityzombievillager.setCustomNameVisible(entityvillager.getCustomNameVisible());
/*     */       } 
/*     */       
/* 308 */       this.world.addEntity(entityzombievillager, CreatureSpawnEvent.SpawnReason.INFECTION);
/* 309 */       this.world.a((EntityHuman)null, 1026, new BlockPosition(this), 0);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public float getHeadHeight() {
/* 315 */     float f = 1.74F;
/*     */     
/* 317 */     if (isBaby()) {
/* 318 */       f = (float)(f - 0.81D);
/*     */     }
/*     */     
/* 321 */     return f;
/*     */   }
/*     */   
/*     */   protected boolean c(ItemStack itemstack) {
/* 325 */     return (itemstack.getItem() == Items.EGG && isBaby() && isPassenger()) ? false : super.c(itemstack);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public GroupDataEntity prepare(DifficultyDamageScaler difficultydamagescaler, @Nullable GroupDataEntity groupdataentity) {
/* 330 */     Object object = super.prepare(difficultydamagescaler, groupdataentity);
/* 331 */     float f = difficultydamagescaler.d();
/*     */     
/* 333 */     m((this.random.nextFloat() < 0.55F * f));
/* 334 */     if (object == null) {
/* 335 */       object = new GroupDataZombie((this.world.random.nextFloat() < 0.05F), null);
/*     */     }
/*     */     
/* 338 */     if (object instanceof GroupDataZombie) {
/* 339 */       GroupDataZombie entityzombie_groupdatazombie = (GroupDataZombie)object;
/*     */       
/* 341 */       if (entityzombie_groupdatazombie.a) {
/* 342 */         setBaby(true);
/* 343 */         if (this.world.random.nextFloat() < 0.05D) {
/* 344 */           List<Entity> list = this.world.a((Class)EntityChicken.class, getBoundingBox().grow(5.0D, 3.0D, 5.0D), IEntitySelector.b);
/*     */           
/* 346 */           if (!list.isEmpty()) {
/* 347 */             EntityChicken entitychicken = (EntityChicken)list.get(0);
/*     */             
/* 349 */             entitychicken.p(true);
/* 350 */             startRiding(entitychicken);
/*     */           } 
/* 352 */         } else if (this.world.random.nextFloat() < 0.05D) {
/* 353 */           EntityChicken entitychicken1 = new EntityChicken(this.world);
/*     */           
/* 355 */           entitychicken1.setPositionRotation(this.locX, this.locY, this.locZ, this.yaw, 0.0F);
/* 356 */           entitychicken1.prepare(difficultydamagescaler, (GroupDataEntity)null);
/* 357 */           entitychicken1.p(true);
/* 358 */           this.world.addEntity(entitychicken1, CreatureSpawnEvent.SpawnReason.MOUNT);
/* 359 */           startRiding(entitychicken1);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 364 */     p((this.random.nextFloat() < f * 0.1F));
/* 365 */     a(difficultydamagescaler);
/* 366 */     b(difficultydamagescaler);
/* 367 */     if (getEquipment(EnumItemSlot.HEAD).isEmpty()) {
/* 368 */       Calendar calendar = this.world.ae();
/*     */       
/* 370 */       if (calendar.get(2) + 1 == 10 && calendar.get(5) == 31 && this.random.nextFloat() < 0.25F) {
/* 371 */         setSlot(EnumItemSlot.HEAD, new ItemStack((this.random.nextFloat() < 0.1F) ? Blocks.LIT_PUMPKIN : Blocks.PUMPKIN));
/* 372 */         this.dropChanceArmor[EnumItemSlot.HEAD.b()] = 0.0F;
/*     */       } 
/*     */     } 
/*     */     
/* 376 */     getAttributeInstance(GenericAttributes.c).b(new AttributeModifier("Random spawn bonus", this.random.nextDouble() * 0.05000000074505806D, 0));
/* 377 */     double d0 = this.random.nextDouble() * 1.5D * f;
/*     */     
/* 379 */     if (d0 > 1.0D) {
/* 380 */       getAttributeInstance(GenericAttributes.FOLLOW_RANGE).b(new AttributeModifier("Random zombie-spawn bonus", d0, 2));
/*     */     }
/*     */     
/* 383 */     if (this.random.nextFloat() < f * 0.05F) {
/* 384 */       getAttributeInstance(a).b(new AttributeModifier("Leader zombie bonus", this.random.nextDouble() * 0.25D + 0.5D, 0));
/* 385 */       getAttributeInstance(GenericAttributes.maxHealth).b(new AttributeModifier("Leader zombie bonus", this.random.nextDouble() * 3.0D + 1.0D, 2));
/* 386 */       p(true);
/*     */     } 
/*     */     
/* 389 */     return (GroupDataEntity)object;
/*     */   }
/*     */   
/*     */   public void r(boolean flag) {
/* 393 */     a(flag ? 0.5F : 1.0F);
/*     */   }
/*     */   
/*     */   public final void setSize(float f, float f1) {
/* 397 */     boolean flag = (this.bC > 0.0F && this.bD > 0.0F);
/*     */     
/* 399 */     this.bC = f;
/* 400 */     this.bD = f1;
/* 401 */     if (!flag) {
/* 402 */       a(1.0F);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected final void a(float f) {
/* 408 */     super.setSize(this.bC * f, this.bD * f);
/*     */   }
/*     */   
/*     */   public double aF() {
/* 412 */     return isBaby() ? 0.0D : -0.45D;
/*     */   }
/*     */ 
/*     */   
/*     */   public void die(DamageSource damagesource) {
/* 417 */     if (damagesource.getEntity() instanceof EntityCreeper) {
/* 418 */       EntityCreeper entitycreeper = (EntityCreeper)damagesource.getEntity();
/*     */       
/* 420 */       if (entitycreeper.isPowered() && entitycreeper.canCauseHeadDrop()) {
/* 421 */         entitycreeper.setCausedHeadDrop();
/* 422 */         ItemStack itemstack = dn();
/*     */         
/* 424 */         if (!itemstack.isEmpty()) {
/* 425 */           a(itemstack, 0.0F);
/*     */         }
/*     */       } 
/*     */     } 
/* 429 */     super.die(damagesource);
/*     */   }
/*     */ 
/*     */   
/*     */   protected ItemStack dn() {
/* 434 */     return new ItemStack(Items.SKULL, 1, 2);
/*     */   }
/*     */   
/*     */   class GroupDataZombie
/*     */     implements GroupDataEntity {
/*     */     public boolean a;
/*     */     
/*     */     private GroupDataZombie(boolean flag) {
/* 442 */       this.a = flag;
/*     */     }
/*     */     
/*     */     GroupDataZombie(boolean flag, Object object) {
/* 446 */       this(flag);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityZombie.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */