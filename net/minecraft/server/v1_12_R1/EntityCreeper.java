/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.entity.CreeperPowerEvent;
/*     */ import org.bukkit.event.entity.ExplosionPrimeEvent;
/*     */ 
/*     */ public class EntityCreeper extends EntityMonster {
/*  13 */   private static final DataWatcherObject<Integer> a = DataWatcher.a((Class)EntityCreeper.class, DataWatcherRegistry.b);
/*  14 */   private static final DataWatcherObject<Boolean> b = DataWatcher.a((Class)EntityCreeper.class, DataWatcherRegistry.h);
/*  15 */   private static final DataWatcherObject<Boolean> c = DataWatcher.a((Class)EntityCreeper.class, DataWatcherRegistry.h);
/*     */   private int bx;
/*     */   private int fuseTicks;
/*  18 */   private int maxFuseTicks = 30;
/*  19 */   private int explosionRadius = 3;
/*     */   private int bB;
/*     */   
/*     */   public EntityCreeper(World world) {
/*  23 */     super(world);
/*  24 */     setSize(0.6F, 1.7F);
/*     */   }
/*     */   
/*     */   protected void r() {
/*  28 */     this.goalSelector.a(1, new PathfinderGoalFloat(this));
/*  29 */     this.goalSelector.a(2, new PathfinderGoalSwell(this));
/*  30 */     this.goalSelector.a(3, new PathfinderGoalAvoidTarget<>(this, EntityOcelot.class, 6.0F, 1.0D, 1.2D));
/*  31 */     this.goalSelector.a(4, new PathfinderGoalMeleeAttack(this, 1.0D, false));
/*  32 */     this.goalSelector.a(5, new PathfinderGoalRandomStrollLand(this, 0.8D));
/*  33 */     this.goalSelector.a(6, new PathfinderGoalLookAtPlayer(this, (Class)EntityHuman.class, 8.0F));
/*  34 */     this.goalSelector.a(6, new PathfinderGoalRandomLookaround(this));
/*  35 */     this.targetSelector.a(1, new PathfinderGoalNearestAttackableTarget<>(this, EntityHuman.class, true));
/*  36 */     this.targetSelector.a(2, new PathfinderGoalHurtByTarget(this, false, new Class[0]));
/*     */   }
/*     */   
/*     */   protected void initAttributes() {
/*  40 */     super.initAttributes();
/*  41 */     getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.25D);
/*     */   }
/*     */   
/*     */   public int bg() {
/*  45 */     return (getGoalTarget() == null) ? 3 : (3 + (int)(getHealth() - 1.0F));
/*     */   }
/*     */   
/*     */   public void e(float f, float f1) {
/*  49 */     super.e(f, f1);
/*  50 */     this.fuseTicks = (int)(this.fuseTicks + f * 1.5F);
/*  51 */     if (this.fuseTicks > this.maxFuseTicks - 5) {
/*  52 */       this.fuseTicks = this.maxFuseTicks - 5;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void i() {
/*  58 */     super.i();
/*  59 */     this.datawatcher.register(a, Integer.valueOf(-1));
/*  60 */     this.datawatcher.register(b, Boolean.valueOf(false));
/*  61 */     this.datawatcher.register(c, Boolean.valueOf(false));
/*     */   }
/*     */   
/*     */   public static void a(DataConverterManager dataconvertermanager) {
/*  65 */     EntityInsentient.a(dataconvertermanager, EntityCreeper.class);
/*     */   }
/*     */   
/*     */   public void b(NBTTagCompound nbttagcompound) {
/*  69 */     super.b(nbttagcompound);
/*  70 */     if (((Boolean)this.datawatcher.<Boolean>get(b)).booleanValue()) {
/*  71 */       nbttagcompound.setBoolean("powered", true);
/*     */     }
/*     */     
/*  74 */     nbttagcompound.setShort("Fuse", (short)this.maxFuseTicks);
/*  75 */     nbttagcompound.setByte("ExplosionRadius", (byte)this.explosionRadius);
/*  76 */     nbttagcompound.setBoolean("ignited", isIgnited());
/*     */   }
/*     */   
/*     */   public void a(NBTTagCompound nbttagcompound) {
/*  80 */     super.a(nbttagcompound);
/*  81 */     this.datawatcher.set(b, Boolean.valueOf(nbttagcompound.getBoolean("powered")));
/*  82 */     if (nbttagcompound.hasKeyOfType("Fuse", 99)) {
/*  83 */       this.maxFuseTicks = nbttagcompound.getShort("Fuse");
/*     */     }
/*     */     
/*  86 */     if (nbttagcompound.hasKeyOfType("ExplosionRadius", 99)) {
/*  87 */       this.explosionRadius = nbttagcompound.getByte("ExplosionRadius");
/*     */     }
/*     */     
/*  90 */     if (nbttagcompound.getBoolean("ignited")) {
/*  91 */       do_();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void B_() {
/*  97 */     if (isAlive()) {
/*  98 */       this.bx = this.fuseTicks;
/*  99 */       if (isIgnited()) {
/* 100 */         a(1);
/*     */       }
/*     */       
/* 103 */       int i = dm();
/*     */       
/* 105 */       if (i > 0 && this.fuseTicks == 0) {
/* 106 */         a(SoundEffects.ay, 1.0F, 0.5F);
/*     */       }
/*     */       
/* 109 */       this.fuseTicks += i;
/* 110 */       if (this.fuseTicks < 0) {
/* 111 */         this.fuseTicks = 0;
/*     */       }
/*     */       
/* 114 */       if (this.fuseTicks >= this.maxFuseTicks) {
/* 115 */         this.fuseTicks = this.maxFuseTicks;
/* 116 */         dr();
/*     */       } 
/*     */     } 
/*     */     
/* 120 */     super.B_();
/*     */   }
/*     */   
/*     */   protected SoundEffect d(DamageSource damagesource) {
/* 124 */     return SoundEffects.ax;
/*     */   }
/*     */   
/*     */   protected SoundEffect cf() {
/* 128 */     return SoundEffects.aw;
/*     */   }
/*     */ 
/*     */   
/*     */   public void die(DamageSource damagesource) {
/* 133 */     if (this.world.getGameRules().getBoolean("doMobLoot")) {
/* 134 */       if (damagesource.getEntity() instanceof EntitySkeleton) {
/* 135 */         int i = Item.getId(Items.RECORD_13);
/* 136 */         int j = Item.getId(Items.RECORD_WAIT);
/* 137 */         int k = i + this.random.nextInt(j - i + 1);
/*     */         
/* 139 */         a(Item.getById(k), 1);
/* 140 */       } else if (damagesource.getEntity() instanceof EntityCreeper && damagesource.getEntity() != this && ((EntityCreeper)damagesource.getEntity()).isPowered() && ((EntityCreeper)damagesource.getEntity()).canCauseHeadDrop()) {
/* 141 */         ((EntityCreeper)damagesource.getEntity()).setCausedHeadDrop();
/* 142 */         a(new ItemStack(Items.SKULL, 1, 4), 0.0F);
/*     */       } 
/*     */     }
/* 145 */     super.die(damagesource);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean B(Entity entity) {
/* 150 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isPowered() {
/* 154 */     return ((Boolean)this.datawatcher.<Boolean>get(b)).booleanValue();
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   protected MinecraftKey J() {
/* 159 */     return LootTables.r;
/*     */   }
/*     */   
/*     */   public int dm() {
/* 163 */     return ((Integer)this.datawatcher.<Integer>get(a)).intValue();
/*     */   }
/*     */   
/*     */   public void a(int i) {
/* 167 */     this.datawatcher.set(a, Integer.valueOf(i));
/*     */   }
/*     */   
/*     */   public void onLightningStrike(EntityLightning entitylightning) {
/* 171 */     super.onLightningStrike(entitylightning);
/*     */     
/* 173 */     if (CraftEventFactory.callCreeperPowerEvent(this, entitylightning, CreeperPowerEvent.PowerCause.LIGHTNING).isCancelled()) {
/*     */       return;
/*     */     }
/*     */     
/* 177 */     setPowered(true);
/*     */   }
/*     */   
/*     */   public void setPowered(boolean powered) {
/* 181 */     this.datawatcher.set(b, Boolean.valueOf(powered));
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean a(EntityHuman entityhuman, EnumHand enumhand) {
/* 186 */     ItemStack itemstack = entityhuman.b(enumhand);
/*     */     
/* 188 */     if (itemstack.getItem() == Items.FLINT_AND_STEEL) {
/* 189 */       this.world.a(entityhuman, this.locX, this.locY, this.locZ, SoundEffects.bO, bK(), 1.0F, this.random.nextFloat() * 0.4F + 0.8F);
/* 190 */       entityhuman.a(enumhand);
/* 191 */       if (!this.world.isClientSide) {
/* 192 */         do_();
/* 193 */         itemstack.damage(1, entityhuman);
/* 194 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/* 198 */     return super.a(entityhuman, enumhand);
/*     */   }
/*     */   
/*     */   private void dr() {
/* 202 */     if (!this.world.isClientSide) {
/* 203 */       boolean flag = this.world.getGameRules().getBoolean("mobGriefing");
/* 204 */       float f = isPowered() ? 2.0F : 1.0F;
/*     */ 
/*     */       
/* 207 */       ExplosionPrimeEvent event = new ExplosionPrimeEvent((Entity)getBukkitEntity(), this.explosionRadius * f, false);
/* 208 */       this.world.getServer().getPluginManager().callEvent((Event)event);
/* 209 */       if (!event.isCancelled()) {
/* 210 */         this.aU = true;
/* 211 */         this.world.createExplosion(this, this.locX, this.locY, this.locZ, event.getRadius(), event.getFire(), flag);
/* 212 */         die();
/* 213 */         ds();
/*     */       } else {
/* 215 */         this.fuseTicks = 0;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void ds() {
/* 223 */     Collection<MobEffect> collection = getEffects();
/*     */     
/* 225 */     if (!collection.isEmpty()) {
/* 226 */       EntityAreaEffectCloud entityareaeffectcloud = new EntityAreaEffectCloud(this.world, this.locX, this.locY, this.locZ);
/*     */       
/* 228 */       entityareaeffectcloud.setSource(this);
/* 229 */       entityareaeffectcloud.setRadius(2.5F);
/* 230 */       entityareaeffectcloud.setRadiusOnUse(-0.5F);
/* 231 */       entityareaeffectcloud.setWaitTime(10);
/* 232 */       entityareaeffectcloud.setDuration(entityareaeffectcloud.getDuration() / 2);
/* 233 */       entityareaeffectcloud.setRadiusPerTick(-entityareaeffectcloud.getRadius() / entityareaeffectcloud.getDuration());
/* 234 */       Iterator<MobEffect> iterator = collection.iterator();
/*     */       
/* 236 */       while (iterator.hasNext()) {
/* 237 */         MobEffect mobeffect = iterator.next();
/*     */         
/* 239 */         entityareaeffectcloud.a(new MobEffect(mobeffect));
/*     */       } 
/*     */       
/* 242 */       this.world.addEntity(entityareaeffectcloud);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isIgnited() {
/* 248 */     return ((Boolean)this.datawatcher.<Boolean>get(c)).booleanValue();
/*     */   }
/*     */   
/*     */   public void do_() {
/* 252 */     this.datawatcher.set(c, Boolean.valueOf(true));
/*     */   }
/*     */   
/*     */   public boolean canCauseHeadDrop() {
/* 256 */     return (this.bB < 1 && this.world.getGameRules().getBoolean("doMobLoot"));
/*     */   }
/*     */   
/*     */   public void setCausedHeadDrop() {
/* 260 */     this.bB++;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityCreeper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */