/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*     */ import org.bukkit.event.entity.CreatureSpawnEvent;
/*     */ 
/*     */ public class EntityOcelot extends EntityTameableAnimal {
/*   8 */   private static final DataWatcherObject<Integer> bB = DataWatcher.a((Class)EntityOcelot.class, DataWatcherRegistry.b);
/*     */   private PathfinderGoalAvoidTarget<EntityHuman> bC;
/*     */   private PathfinderGoalTempt bD;
/*     */   public boolean spawnBonus = true;
/*     */   
/*     */   public EntityOcelot(World world) {
/*  14 */     super(world);
/*  15 */     setSize(0.6F, 0.7F);
/*     */   }
/*     */   
/*     */   protected void r() {
/*  19 */     this.goalSit = new PathfinderGoalSit(this);
/*  20 */     this.bD = new PathfinderGoalTempt(this, 0.6D, Items.FISH, true);
/*  21 */     this.goalSelector.a(1, new PathfinderGoalFloat(this));
/*  22 */     this.goalSelector.a(2, this.goalSit);
/*  23 */     this.goalSelector.a(3, this.bD);
/*  24 */     this.goalSelector.a(5, new PathfinderGoalFollowOwner(this, 1.0D, 10.0F, 5.0F));
/*  25 */     this.goalSelector.a(6, new PathfinderGoalJumpOnBlock(this, 0.8D));
/*  26 */     this.goalSelector.a(7, new PathfinderGoalLeapAtTarget(this, 0.3F));
/*  27 */     this.goalSelector.a(8, new PathfinderGoalOcelotAttack(this));
/*  28 */     this.goalSelector.a(9, new PathfinderGoalBreed(this, 0.8D));
/*  29 */     this.goalSelector.a(10, new PathfinderGoalRandomStrollLand(this, 0.8D, 1.0000001E-5F));
/*  30 */     this.goalSelector.a(11, new PathfinderGoalLookAtPlayer(this, (Class)EntityHuman.class, 10.0F));
/*  31 */     this.targetSelector.a(1, new PathfinderGoalRandomTargetNonTamed<>(this, EntityChicken.class, false, null));
/*     */   }
/*     */   
/*     */   protected void i() {
/*  35 */     super.i();
/*  36 */     this.datawatcher.register(bB, Integer.valueOf(0));
/*     */   }
/*     */   
/*     */   public void M() {
/*  40 */     if (getControllerMove().b()) {
/*  41 */       double d0 = getControllerMove().c();
/*     */       
/*  43 */       if (d0 == 0.6D) {
/*  44 */         setSneaking(true);
/*  45 */         setSprinting(false);
/*  46 */       } else if (d0 == 1.33D) {
/*  47 */         setSneaking(false);
/*  48 */         setSprinting(true);
/*     */       } else {
/*  50 */         setSneaking(false);
/*  51 */         setSprinting(false);
/*     */       } 
/*     */     } else {
/*  54 */       setSneaking(false);
/*  55 */       setSprinting(false);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isTypeNotPersistent() {
/*  61 */     return !isTamed();
/*     */   }
/*     */   
/*     */   protected void initAttributes() {
/*  65 */     super.initAttributes();
/*  66 */     getAttributeInstance(GenericAttributes.maxHealth).setValue(10.0D);
/*  67 */     getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.30000001192092896D);
/*     */   }
/*     */   
/*     */   public void e(float f, float f1) {}
/*     */   
/*     */   public static void a(DataConverterManager dataconvertermanager) {
/*  73 */     EntityInsentient.a(dataconvertermanager, EntityOcelot.class);
/*     */   }
/*     */   
/*     */   public void b(NBTTagCompound nbttagcompound) {
/*  77 */     super.b(nbttagcompound);
/*  78 */     nbttagcompound.setInt("CatType", getCatType());
/*     */   }
/*     */   
/*     */   public void a(NBTTagCompound nbttagcompound) {
/*  82 */     super.a(nbttagcompound);
/*  83 */     setCatType(nbttagcompound.getInt("CatType"));
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   protected SoundEffect F() {
/*  88 */     return isTamed() ? (isInLove() ? SoundEffects.Y : ((this.random.nextInt(4) == 0) ? SoundEffects.Z : SoundEffects.U)) : null;
/*     */   }
/*     */   
/*     */   protected SoundEffect d(DamageSource damagesource) {
/*  92 */     return SoundEffects.X;
/*     */   }
/*     */   
/*     */   protected SoundEffect cf() {
/*  96 */     return SoundEffects.V;
/*     */   }
/*     */   
/*     */   protected float cq() {
/* 100 */     return 0.4F;
/*     */   }
/*     */   
/*     */   public boolean B(Entity entity) {
/* 104 */     return entity.damageEntity(DamageSource.mobAttack(this), 3.0F);
/*     */   }
/*     */   
/*     */   public boolean damageEntity(DamageSource damagesource, float f) {
/* 108 */     if (isInvulnerable(damagesource)) {
/* 109 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 116 */     return super.damageEntity(damagesource, f);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   protected MinecraftKey J() {
/* 122 */     return LootTables.O;
/*     */   }
/*     */   
/*     */   public boolean a(EntityHuman entityhuman, EnumHand enumhand) {
/* 126 */     ItemStack itemstack = entityhuman.b(enumhand);
/*     */     
/* 128 */     if (isTamed()) {
/* 129 */       if (e(entityhuman) && !this.world.isClientSide && !e(itemstack)) {
/* 130 */         this.goalSit.setSitting(!isSitting());
/*     */       }
/* 132 */     } else if ((this.bD == null || this.bD.f()) && itemstack.getItem() == Items.FISH && entityhuman.h(this) < 9.0D) {
/* 133 */       if (!entityhuman.abilities.canInstantlyBuild) {
/* 134 */         itemstack.subtract(1);
/*     */       }
/*     */       
/* 137 */       if (!this.world.isClientSide)
/*     */       {
/* 139 */         if (this.random.nextInt(3) == 0 && !CraftEventFactory.callEntityTameEvent(this, entityhuman).isCancelled()) {
/* 140 */           c(entityhuman);
/* 141 */           setCatType(1 + this.world.random.nextInt(3));
/* 142 */           p(true);
/* 143 */           this.goalSit.setSitting(true);
/* 144 */           this.world.broadcastEntityEffect(this, (byte)7);
/*     */         } else {
/* 146 */           p(false);
/* 147 */           this.world.broadcastEntityEffect(this, (byte)6);
/*     */         } 
/*     */       }
/*     */       
/* 151 */       return true;
/*     */     } 
/*     */     
/* 154 */     return super.a(entityhuman, enumhand);
/*     */   }
/*     */   
/*     */   public EntityOcelot b(EntityAgeable entityageable) {
/* 158 */     EntityOcelot entityocelot = new EntityOcelot(this.world);
/*     */     
/* 160 */     if (isTamed()) {
/* 161 */       entityocelot.setOwnerUUID(getOwnerUUID());
/* 162 */       entityocelot.setTamed(true);
/* 163 */       entityocelot.setCatType(getCatType());
/*     */     } 
/*     */     
/* 166 */     return entityocelot;
/*     */   }
/*     */   
/*     */   public boolean e(ItemStack itemstack) {
/* 170 */     return (itemstack.getItem() == Items.FISH);
/*     */   }
/*     */   
/*     */   public boolean mate(EntityAnimal entityanimal) {
/* 174 */     if (entityanimal == this)
/* 175 */       return false; 
/* 176 */     if (!isTamed())
/* 177 */       return false; 
/* 178 */     if (!(entityanimal instanceof EntityOcelot)) {
/* 179 */       return false;
/*     */     }
/* 181 */     EntityOcelot entityocelot = (EntityOcelot)entityanimal;
/*     */     
/* 183 */     return !entityocelot.isTamed() ? false : ((isInLove() && entityocelot.isInLove()));
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCatType() {
/* 188 */     return ((Integer)this.datawatcher.<Integer>get(bB)).intValue();
/*     */   }
/*     */   
/*     */   public void setCatType(int i) {
/* 192 */     this.datawatcher.set(bB, Integer.valueOf(i));
/*     */   }
/*     */   
/*     */   public boolean P() {
/* 196 */     return (this.world.random.nextInt(3) != 0);
/*     */   }
/*     */   
/*     */   public boolean canSpawn() {
/* 200 */     if (this.world.a(getBoundingBox(), this) && this.world.getCubes(this, getBoundingBox()).isEmpty() && !this.world.containsLiquid(getBoundingBox())) {
/* 201 */       BlockPosition blockposition = new BlockPosition(this.locX, (getBoundingBox()).b, this.locZ);
/*     */       
/* 203 */       if (blockposition.getY() < this.world.getSeaLevel()) {
/* 204 */         return false;
/*     */       }
/*     */       
/* 207 */       IBlockData iblockdata = this.world.getType(blockposition.down());
/* 208 */       Block block = iblockdata.getBlock();
/*     */       
/* 210 */       if (block == Blocks.GRASS || iblockdata.getMaterial() == Material.LEAVES) {
/* 211 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 215 */     return false;
/*     */   }
/*     */   
/*     */   public String getName() {
/* 219 */     return hasCustomName() ? getCustomName() : (isTamed() ? LocaleI18n.get("entity.Cat.name") : super.getName());
/*     */   }
/*     */   
/*     */   protected void dm() {
/* 223 */     if (this.bC == null) {
/* 224 */       this.bC = new PathfinderGoalAvoidTarget<>(this, EntityHuman.class, 16.0F, 0.8D, 1.33D);
/*     */     }
/*     */     
/* 227 */     this.goalSelector.a(this.bC);
/* 228 */     if (!isTamed()) {
/* 229 */       this.goalSelector.a(4, this.bC);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public GroupDataEntity prepare(DifficultyDamageScaler difficultydamagescaler, @Nullable GroupDataEntity groupdataentity) {
/* 236 */     groupdataentity = super.prepare(difficultydamagescaler, groupdataentity);
/* 237 */     if (this.spawnBonus && getCatType() == 0 && this.world.random.nextInt(7) == 0) {
/* 238 */       for (int i = 0; i < 2; i++) {
/* 239 */         EntityOcelot entityocelot = new EntityOcelot(this.world);
/*     */         
/* 241 */         entityocelot.setPositionRotation(this.locX, this.locY, this.locZ, this.yaw, 0.0F);
/* 242 */         entityocelot.setAgeRaw(-24000);
/* 243 */         this.world.addEntity(entityocelot, CreatureSpawnEvent.SpawnReason.OCELOT_BABY);
/*     */       } 
/*     */     }
/*     */     
/* 247 */     return groupdataentity;
/*     */   }
/*     */   
/*     */   public EntityAgeable createChild(EntityAgeable entityageable) {
/* 251 */     return b(entityageable);
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityOcelot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */