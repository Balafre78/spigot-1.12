/*      */ package net.minecraft.server.v1_12_R1;
/*      */ 
/*      */ import com.google.common.base.Function;
/*      */ import com.google.common.base.Objects;
/*      */ import com.google.common.collect.Lists;
/*      */ import com.google.common.collect.Maps;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.ConcurrentModificationException;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Random;
/*      */ import java.util.UUID;
/*      */ import javax.annotation.Nullable;
/*      */ import org.apache.logging.log4j.LogManager;
/*      */ import org.apache.logging.log4j.Logger;
/*      */ import org.bukkit.Location;
/*      */ import org.bukkit.World;
/*      */ import org.bukkit.craftbukkit.v1_12_R1.SpigotTimings;
/*      */ import org.bukkit.craftbukkit.v1_12_R1.attribute.CraftAttributeMap;
/*      */ import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
/*      */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*      */ import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
/*      */ import org.bukkit.entity.Entity;
/*      */ import org.bukkit.entity.LivingEntity;
/*      */ import org.bukkit.entity.Player;
/*      */ import org.bukkit.event.Event;
/*      */ import org.bukkit.event.entity.EntityDamageEvent;
/*      */ import org.bukkit.event.entity.EntityRegainHealthEvent;
/*      */ import org.bukkit.event.entity.EntityResurrectEvent;
/*      */ import org.bukkit.event.entity.EntityTeleportEvent;
/*      */ import org.bukkit.event.player.PlayerItemConsumeEvent;
/*      */ import org.bukkit.inventory.ItemStack;
/*      */ import org.spigotmc.AsyncCatcher;
/*      */ 
/*      */ public abstract class EntityLiving extends Entity {
/*   38 */   private static final Logger a = LogManager.getLogger();
/*   39 */   private static final UUID b = UUID.fromString("662A6B8D-DA3E-4C1C-8813-96EA6097278D");
/*   40 */   private static final AttributeModifier c = (new AttributeModifier(b, "Sprinting speed boost", 0.30000001192092896D, 2)).a(false);
/*   41 */   protected static final DataWatcherObject<Byte> at = DataWatcher.a((Class)EntityLiving.class, DataWatcherRegistry.a);
/*   42 */   public static final DataWatcherObject<Float> HEALTH = DataWatcher.a((Class)EntityLiving.class, DataWatcherRegistry.c);
/*   43 */   private static final DataWatcherObject<Integer> g = DataWatcher.a((Class)EntityLiving.class, DataWatcherRegistry.b);
/*   44 */   private static final DataWatcherObject<Boolean> h = DataWatcher.a((Class)EntityLiving.class, DataWatcherRegistry.h);
/*   45 */   private static final DataWatcherObject<Integer> br = DataWatcher.a((Class)EntityLiving.class, DataWatcherRegistry.b);
/*      */   private AttributeMapBase attributeMap;
/*   47 */   public CombatTracker combatTracker = new CombatTracker(this);
/*   48 */   public final Map<MobEffectList, MobEffect> effects = Maps.newHashMap();
/*      */   
/*      */   private final NonNullList<ItemStack> bv;
/*      */   private final NonNullList<ItemStack> bw;
/*      */   public boolean au;
/*      */   public EnumHand av;
/*      */   public int aw;
/*      */   public int ax;
/*      */   public int hurtTicks;
/*      */   public int az;
/*      */   public float aA;
/*      */   public int deathTicks;
/*      */   public float aC;
/*      */   public float aD;
/*      */   protected int aE;
/*      */   public float aF;
/*      */   public float aG;
/*      */   public float aH;
/*      */   public int maxNoDamageTicks;
/*      */   public float aJ;
/*      */   public float aK;
/*      */   public float aL;
/*      */   public float aM;
/*      */   public float aN;
/*      */   public float aO;
/*      */   public float aP;
/*      */   public float aQ;
/*      */   public float aR;
/*      */   public EntityHuman killer;
/*      */   protected int lastDamageByPlayerTime;
/*      */   protected boolean aU;
/*      */   protected int ticksFarFromPlayer;
/*      */   protected float aW;
/*      */   protected float aX;
/*      */   protected float aY;
/*      */   protected float aZ;
/*      */   protected float ba;
/*      */   protected int bb;
/*      */   public float lastDamage;
/*      */   protected boolean bd;
/*      */   public float be;
/*      */   public float bf;
/*      */   public float bg;
/*      */   public float bh;
/*      */   protected int bi;
/*      */   protected double bj;
/*      */   protected double bk;
/*      */   protected double bl;
/*      */   protected double bm;
/*      */   protected double bn;
/*      */   public boolean updateEffects;
/*      */   public EntityLiving lastDamager;
/*      */   public int hurtTimestamp;
/*      */   private EntityLiving bA;
/*      */   private int bB;
/*      */   private float bC;
/*      */   private int bD;
/*      */   private float bE;
/*      */   protected ItemStack activeItem;
/*      */   protected int bp;
/*      */   protected int bq;
/*      */   private BlockPosition bF;
/*      */   private DamageSource bG;
/*      */   private long bH;
/*      */   public int expToDrop;
/*  113 */   public int maxAirTicks = 300;
/*      */   boolean forceDrops;
/*  115 */   ArrayList<ItemStack> drops = new ArrayList<>();
/*      */   public CraftAttributeMap craftAttributes;
/*      */   public boolean collides = true;
/*      */   public boolean canPickUpLoot;
/*      */   private boolean isTickingEffects;
/*      */   private List<Object> effectsToProcess;
/*      */   
/*      */   public void inactiveTick() {
/*  123 */     super.inactiveTick();
/*  124 */     this.ticksFarFromPlayer++;
/*      */   }
/*      */ 
/*      */   
/*      */   public void killEntity() {
/*  129 */     damageEntity(DamageSource.OUT_OF_WORLD, Float.MAX_VALUE); }
/*      */   protected void i() { this.datawatcher.register(at, Byte.valueOf((byte)0)); this.datawatcher.register(g, Integer.valueOf(0)); this.datawatcher.register(h, Boolean.valueOf(false)); this.datawatcher.register(br, Integer.valueOf(0)); this.datawatcher.register(HEALTH, Float.valueOf(1.0F)); }
/*      */   protected void initAttributes() { getAttributeMap().b(GenericAttributes.maxHealth); getAttributeMap().b(GenericAttributes.c); getAttributeMap().b(GenericAttributes.MOVEMENT_SPEED); getAttributeMap().b(GenericAttributes.h); getAttributeMap().b(GenericAttributes.i); }
/*      */   protected void a(double d0, boolean flag, IBlockData iblockdata, BlockPosition blockposition) { if (!isInWater()) aq();  if (!this.world.isClientSide && this.fallDistance > 3.0F && flag) { float f = MathHelper.f(this.fallDistance - 3.0F); if (iblockdata.getMaterial() != Material.AIR) { double d1 = Math.min((0.2F + f / 15.0F), 2.5D); int i = (int)(150.0D * d1); if (this instanceof EntityPlayer) { ((WorldServer)this.world).sendParticles((EntityPlayer)this, EnumParticle.BLOCK_DUST, false, this.locX, this.locY, this.locZ, i, 0.0D, 0.0D, 0.0D, 0.15000000596046448D, new int[] { Block.getCombinedId(iblockdata) }); } else { ((WorldServer)this.world).a(EnumParticle.BLOCK_DUST, this.locX, this.locY, this.locZ, i, 0.0D, 0.0D, 0.0D, 0.15000000596046448D, new int[] { Block.getCombinedId(iblockdata) }); }  }  }  super.a(d0, flag, iblockdata, blockposition); }
/*  133 */   public boolean bN() { return false; } public void Y() { this.aC = this.aD; super.Y(); this.world.methodProfiler.a("livingEntityBaseTick"); boolean flag = this instanceof EntityHuman; if (isAlive()) if (inBlock()) { damageEntity(DamageSource.STUCK, 1.0F); } else if (flag && !this.world.getWorldBorder().a(getBoundingBox())) { double d0 = this.world.getWorldBorder().a(this) + this.world.getWorldBorder().getDamageBuffer(); if (d0 < 0.0D) { double d1 = this.world.getWorldBorder().getDamageAmount(); if (d1 > 0.0D) damageEntity(DamageSource.STUCK, Math.max(1, MathHelper.floor(-d0 * d1)));  }  }   if (isFireProof() || this.world.isClientSide) extinguish();  boolean flag1 = (flag && ((EntityHuman)this).abilities.isInvulnerable); if (isAlive()) { if (a(Material.WATER)) { if (!bN() && !hasEffect(MobEffects.WATER_BREATHING) && !flag1) { setAirTicks(d(getAirTicks())); if (getAirTicks() == -20) { setAirTicks(0); for (int i = 0; i < 8; i++) { float f = this.random.nextFloat() - this.random.nextFloat(); float f1 = this.random.nextFloat() - this.random.nextFloat(); float f2 = this.random.nextFloat() - this.random.nextFloat(); this.world.addParticle(EnumParticle.WATER_BUBBLE, this.locX + f, this.locY + f1, this.locZ + f2, this.motX, this.motY, this.motZ, new int[0]); }  damageEntity(DamageSource.DROWN, 2.0F); }  }  if (!this.world.isClientSide && isPassenger() && bJ() instanceof EntityLiving) stopRiding();  } else if (getAirTicks() != 300) { setAirTicks(this.maxAirTicks); }  if (!this.world.isClientSide) { BlockPosition blockposition = new BlockPosition(this); if (!Objects.equal(this.bF, blockposition)) { this.bF = blockposition; b(blockposition); }  }  }  if (isAlive() && an()) extinguish();  this.aJ = this.aK; if (this.hurtTicks > 0) this.hurtTicks--;  if (this.noDamageTicks > 0 && !(this instanceof EntityPlayer)) this.noDamageTicks--;  if (getHealth() <= 0.0F) bO();  if (this.lastDamageByPlayerTime > 0) { this.lastDamageByPlayerTime--; } else { this.killer = null; }  if (this.bA != null && !this.bA.isAlive()) this.bA = null;  if (this.lastDamager != null) if (!this.lastDamager.isAlive()) { a((EntityLiving)null); } else if (this.ticksLived - this.hurtTimestamp > 100) { a((EntityLiving)null); }   tickPotionEffects(); this.aZ = this.aY; this.aO = this.aN; this.aQ = this.aP; this.lastYaw = this.yaw; this.lastPitch = this.pitch; this.world.methodProfiler.b(); } public int getExpReward() { int exp = getExpValue(this.killer); if (!this.world.isClientSide && (this.lastDamageByPlayerTime > 0 || alwaysGivesExp()) && isDropExperience() && this.world.getGameRules().getBoolean("doMobLoot")) return exp;  return 0; } public EntityLiving(World world) { super(world);
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
/*  538 */     this.isTickingEffects = false;
/*  539 */     this.effectsToProcess = Lists.newArrayList(); this.bv = NonNullList.a(2, ItemStack.a); this.bw = NonNullList.a(4, ItemStack.a); this.maxNoDamageTicks = 20; this.aR = 0.02F; this.updateEffects = true; this.activeItem = ItemStack.a; initAttributes(); this.datawatcher.set(HEALTH, Float.valueOf((float)getAttributeInstance(GenericAttributes.maxHealth).getValue())); this.i = true; this.aM = (float)((Math.random() + 1.0D) * 0.009999999776482582D); setPosition(this.locX, this.locY, this.locZ); this.aL = (float)Math.random() * 12398.0F; this.yaw = (float)(Math.random() * 6.2831854820251465D); this.aP = this.yaw; this.P = 0.6F; }
/*      */   protected void b(BlockPosition blockposition) { int i = EnchantmentManager.a(Enchantments.j, this); if (i > 0) EnchantmentFrostWalker.a(this, this.world, blockposition, i);  }
/*      */   public boolean isBaby() { return false; }
/*      */   protected void bO() { this.deathTicks++; if (this.deathTicks >= 20 && !this.dead) { int i = this.expToDrop; while (i > 0) { int j = EntityExperienceOrb.getOrbValue(i); i -= j; this.world.addEntity(new EntityExperienceOrb(this.world, this.locX, this.locY, this.locZ, j)); }  this.expToDrop = 0; die(); for (i = 0; i < 20; i++) { double d0 = this.random.nextGaussian() * 0.02D; double d1 = this.random.nextGaussian() * 0.02D; double d2 = this.random.nextGaussian() * 0.02D; this.world.addParticle(EnumParticle.EXPLOSION_NORMAL, this.locX + (this.random.nextFloat() * this.width * 2.0F) - this.width, this.locY + (this.random.nextFloat() * this.length), this.locZ + (this.random.nextFloat() * this.width * 2.0F) - this.width, d0, d1, d2, new int[0]); }  }  }
/*  543 */   protected boolean isDropExperience() { return !isBaby(); } protected int d(int i) { int j = EnchantmentManager.getOxygenEnchantmentLevel(this); return (j > 0 && this.random.nextInt(j + 1) > 0) ? i : (i - 1); } protected int getExpValue(EntityHuman entityhuman) { return 0; } protected void tickPotionEffects() { Iterator<MobEffectList> iterator = this.effects.keySet().iterator();
/*      */     
/*  545 */     this.isTickingEffects = true;
/*      */     try {
/*  547 */       while (iterator.hasNext()) {
/*  548 */         MobEffectList mobeffectlist = iterator.next();
/*  549 */         MobEffect mobeffect = this.effects.get(mobeffectlist);
/*      */         
/*  551 */         if (!mobeffect.tick(this)) {
/*  552 */           if (!this.world.isClientSide) {
/*  553 */             iterator.remove();
/*  554 */             b(mobeffect);
/*      */           }  continue;
/*  556 */         }  if (mobeffect.getDuration() % 600 == 0) {
/*  557 */           a(mobeffect, false);
/*      */         }
/*      */       } 
/*  560 */     } catch (ConcurrentModificationException concurrentModificationException) {}
/*      */ 
/*      */ 
/*      */     
/*  564 */     this.isTickingEffects = false;
/*  565 */     for (Object e : this.effectsToProcess) {
/*  566 */       if (e instanceof MobEffect) {
/*  567 */         addEffect((MobEffect)e); continue;
/*      */       } 
/*  569 */       removeEffect((MobEffectList)e);
/*      */     } 
/*      */     
/*  572 */     this.effectsToProcess.clear();
/*      */ 
/*      */     
/*  575 */     if (this.updateEffects) {
/*  576 */       if (!this.world.isClientSide) {
/*  577 */         G();
/*      */       }
/*      */       
/*  580 */       this.updateEffects = false;
/*      */     } 
/*      */     
/*  583 */     int i = ((Integer)this.datawatcher.<Integer>get(g)).intValue();
/*  584 */     boolean flag = ((Boolean)this.datawatcher.<Boolean>get(h)).booleanValue();
/*      */     
/*  586 */     if (i > 0)
/*      */     { boolean flag1;
/*      */       int j;
/*  589 */       if (isInvisible()) {
/*  590 */         flag1 = (this.random.nextInt(15) == 0);
/*      */       } else {
/*  592 */         flag1 = this.random.nextBoolean();
/*      */       } 
/*      */       
/*  595 */       if (flag) {
/*  596 */         j = flag1 & ((this.random.nextInt(5) == 0) ? 1 : 0);
/*      */       }
/*      */       
/*  599 */       if (j != 0 && i > 0)
/*  600 */       { double d0 = (i >> 16 & 0xFF) / 255.0D;
/*  601 */         double d1 = (i >> 8 & 0xFF) / 255.0D;
/*  602 */         double d2 = (i >> 0 & 0xFF) / 255.0D;
/*      */         
/*  604 */         this.world.addParticle(flag ? EnumParticle.SPELL_MOB_AMBIENT : EnumParticle.SPELL_MOB, this.locX + (this.random.nextDouble() - 0.5D) * this.width, this.locY + this.random.nextDouble() * this.length, this.locZ + (this.random.nextDouble() - 0.5D) * this.width, d0, d1, d2, new int[0]); }  }  } protected boolean alwaysGivesExp() { return false; } public Random getRandom() { return this.random; } @Nullable public EntityLiving getLastDamager() { return this.lastDamager; } public int bT() { return this.hurtTimestamp; } public void a(@Nullable EntityLiving entityliving) { this.lastDamager = entityliving; this.hurtTimestamp = this.ticksLived; } public EntityLiving bU() { return this.bA; }
/*      */   public int bV() { return this.bB; }
/*      */   public void z(Entity entity) { if (entity instanceof EntityLiving) { this.bA = (EntityLiving)entity; } else { this.bA = null; }  this.bB = this.ticksLived; }
/*      */   public int bW() { return this.ticksFarFromPlayer; }
/*      */   protected void a_(ItemStack itemstack) { if (!itemstack.isEmpty()) { SoundEffect soundeffect = SoundEffects.q; Item item = itemstack.getItem(); if (item instanceof ItemArmor) { soundeffect = ((ItemArmor)item).d().b(); } else if (item == Items.cS) { soundeffect = SoundEffects.p; }  a(soundeffect, 1.0F, 1.0F); }  }
/*      */   public void b(NBTTagCompound nbttagcompound) { nbttagcompound.setFloat("Health", getHealth()); nbttagcompound.setShort("HurtTime", (short)this.hurtTicks); nbttagcompound.setInt("HurtByTimestamp", this.hurtTimestamp); nbttagcompound.setShort("DeathTime", (short)this.deathTicks); nbttagcompound.setFloat("AbsorptionAmount", getAbsorptionHearts()); EnumItemSlot[] aenumitemslot = EnumItemSlot.values(); int i = aenumitemslot.length; int j; for (j = 0; j < i; j++) { EnumItemSlot enumitemslot = aenumitemslot[j]; ItemStack itemstack = getEquipment(enumitemslot); if (!itemstack.isEmpty()) getAttributeMap().a(itemstack.a(enumitemslot));  }  nbttagcompound.set("Attributes", GenericAttributes.a(getAttributeMap())); aenumitemslot = EnumItemSlot.values(); i = aenumitemslot.length; for (j = 0; j < i; j++) { EnumItemSlot enumitemslot = aenumitemslot[j]; ItemStack itemstack = getEquipment(enumitemslot); if (!itemstack.isEmpty()) getAttributeMap().b(itemstack.a(enumitemslot));  }  if (!this.effects.isEmpty()) { NBTTagList nbttaglist = new NBTTagList(); Iterator<MobEffect> iterator = this.effects.values().iterator(); while (iterator.hasNext()) { MobEffect mobeffect = iterator.next(); nbttaglist.add(mobeffect.a(new NBTTagCompound())); }  nbttagcompound.set("ActiveEffects", nbttaglist); }  nbttagcompound.setBoolean("FallFlying", cP()); }
/*      */   public void a(NBTTagCompound nbttagcompound) { setAbsorptionHearts(nbttagcompound.getFloat("AbsorptionAmount")); if (nbttagcompound.hasKeyOfType("Attributes", 9) && this.world != null && !this.world.isClientSide) GenericAttributes.a(getAttributeMap(), nbttagcompound.getList("Attributes", 10));  if (nbttagcompound.hasKeyOfType("ActiveEffects", 9)) { NBTTagList nbttaglist = nbttagcompound.getList("ActiveEffects", 10); for (int i = 0; i < nbttaglist.size(); i++) { NBTTagCompound nbttagcompound1 = nbttaglist.get(i); MobEffect mobeffect = MobEffect.b(nbttagcompound1); if (mobeffect != null) this.effects.put(mobeffect.getMobEffect(), mobeffect);  }  }  if (nbttagcompound.hasKey("Bukkit.MaxHealth")) { NBTBase nbtbase = nbttagcompound.get("Bukkit.MaxHealth"); if (nbtbase.getTypeId() == 5) { getAttributeInstance(GenericAttributes.maxHealth).setValue(((NBTTagFloat)nbtbase).asDouble()); } else if (nbtbase.getTypeId() == 3) { getAttributeInstance(GenericAttributes.maxHealth).setValue(((NBTTagInt)nbtbase).asDouble()); }  }  if (nbttagcompound.hasKeyOfType("Health", 99)) setHealth(nbttagcompound.getFloat("Health"));  this.hurtTicks = nbttagcompound.getShort("HurtTime"); this.deathTicks = nbttagcompound.getShort("DeathTime"); this.hurtTimestamp = nbttagcompound.getInt("HurtByTimestamp"); if (nbttagcompound.hasKeyOfType("Team", 8)) { String s = nbttagcompound.getString("Team"); boolean flag = this.world.getScoreboard().addPlayerToTeam(bn(), s); if (!flag) a.warn("Unable to add mob to team \"" + s + "\" (that team probably doesn't exist)");  }  if (nbttagcompound.getBoolean("FallFlying")) setFlag(7, true);  }
/*  611 */   protected void G() { if (this.effects.isEmpty()) {
/*  612 */       bY();
/*  613 */       setInvisible(false);
/*      */     } else {
/*  615 */       Collection<MobEffect> collection = this.effects.values();
/*      */       
/*  617 */       this.datawatcher.set(h, Boolean.valueOf(a(collection)));
/*  618 */       this.datawatcher.set(g, Integer.valueOf(PotionUtil.a(collection)));
/*  619 */       setInvisible(hasEffect(MobEffects.INVISIBILITY));
/*      */     }  }
/*      */ 
/*      */   
/*      */   public static boolean a(Collection<MobEffect> collection) {
/*      */     MobEffect mobeffect;
/*  625 */     Iterator<MobEffect> iterator = collection.iterator();
/*      */ 
/*      */ 
/*      */     
/*      */     do {
/*  630 */       if (!iterator.hasNext()) {
/*  631 */         return true;
/*      */       }
/*      */       
/*  634 */       mobeffect = iterator.next();
/*  635 */     } while (mobeffect.isAmbient());
/*      */     
/*  637 */     return false;
/*      */   }
/*      */   
/*      */   protected void bY() {
/*  641 */     this.datawatcher.set(h, Boolean.valueOf(false));
/*  642 */     this.datawatcher.set(g, Integer.valueOf(0));
/*      */   }
/*      */   
/*      */   public void removeAllEffects() {
/*  646 */     if (!this.world.isClientSide) {
/*  647 */       Iterator<MobEffect> iterator = this.effects.values().iterator();
/*      */       
/*  649 */       while (iterator.hasNext()) {
/*  650 */         b(iterator.next());
/*  651 */         iterator.remove();
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public Collection<MobEffect> getEffects() {
/*  658 */     return this.effects.values();
/*      */   }
/*      */   
/*      */   public Map<MobEffectList, MobEffect> cb() {
/*  662 */     return this.effects;
/*      */   }
/*      */   
/*      */   public boolean hasEffect(MobEffectList mobeffectlist) {
/*  666 */     return this.effects.containsKey(mobeffectlist);
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public MobEffect getEffect(MobEffectList mobeffectlist) {
/*  671 */     return this.effects.get(mobeffectlist);
/*      */   }
/*      */   
/*      */   public void addEffect(MobEffect mobeffect) {
/*  675 */     AsyncCatcher.catchOp("effect add");
/*      */     
/*  677 */     if (this.isTickingEffects) {
/*  678 */       this.effectsToProcess.add(mobeffect);
/*      */       
/*      */       return;
/*      */     } 
/*  682 */     if (d(mobeffect)) {
/*  683 */       MobEffect mobeffect1 = this.effects.get(mobeffect.getMobEffect());
/*      */       
/*  685 */       if (mobeffect1 == null) {
/*  686 */         this.effects.put(mobeffect.getMobEffect(), mobeffect);
/*  687 */         a(mobeffect);
/*      */       } else {
/*  689 */         mobeffect1.a(mobeffect);
/*  690 */         a(mobeffect1, true);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean d(MobEffect mobeffect) {
/*  697 */     if (getMonsterType() == EnumMonsterType.UNDEAD) {
/*  698 */       MobEffectList mobeffectlist = mobeffect.getMobEffect();
/*      */       
/*  700 */       if (mobeffectlist == MobEffects.REGENERATION || mobeffectlist == MobEffects.POISON) {
/*  701 */         return false;
/*      */       }
/*      */     } 
/*      */     
/*  705 */     return true;
/*      */   }
/*      */   
/*      */   public boolean cc() {
/*  709 */     return (getMonsterType() == EnumMonsterType.UNDEAD);
/*      */   }
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public MobEffect c(@Nullable MobEffectList mobeffectlist) {
/*  715 */     if (this.isTickingEffects) {
/*  716 */       this.effectsToProcess.add(mobeffectlist);
/*  717 */       return null;
/*      */     } 
/*      */     
/*  720 */     return this.effects.remove(mobeffectlist);
/*      */   }
/*      */   
/*      */   public void removeEffect(MobEffectList mobeffectlist) {
/*  724 */     MobEffect mobeffect = c(mobeffectlist);
/*      */     
/*  726 */     if (mobeffect != null) {
/*  727 */       b(mobeffect);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected void a(MobEffect mobeffect) {
/*  733 */     this.updateEffects = true;
/*  734 */     if (!this.world.isClientSide) {
/*  735 */       mobeffect.getMobEffect().b(this, getAttributeMap(), mobeffect.getAmplifier());
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected void a(MobEffect mobeffect, boolean flag) {
/*  741 */     this.updateEffects = true;
/*  742 */     if (flag && !this.world.isClientSide) {
/*  743 */       MobEffectList mobeffectlist = mobeffect.getMobEffect();
/*      */       
/*  745 */       mobeffectlist.a(this, getAttributeMap(), mobeffect.getAmplifier());
/*  746 */       mobeffectlist.b(this, getAttributeMap(), mobeffect.getAmplifier());
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void b(MobEffect mobeffect) {
/*  752 */     this.updateEffects = true;
/*  753 */     if (!this.world.isClientSide) {
/*  754 */       mobeffect.getMobEffect().a(this, getAttributeMap(), mobeffect.getAmplifier());
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void heal(float f) {
/*  761 */     heal(f, EntityRegainHealthEvent.RegainReason.CUSTOM);
/*      */   }
/*      */   
/*      */   public void heal(float f, EntityRegainHealthEvent.RegainReason regainReason) {
/*  765 */     float f1 = getHealth();
/*      */     
/*  767 */     if (f1 > 0.0F) {
/*  768 */       EntityRegainHealthEvent event = new EntityRegainHealthEvent((Entity)getBukkitEntity(), f, regainReason);
/*  769 */       this.world.getServer().getPluginManager().callEvent((Event)event);
/*      */       
/*  771 */       if (!event.isCancelled()) {
/*  772 */         setHealth((float)(getHealth() + event.getAmount()));
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final float getHealth() {
/*  781 */     if (this instanceof EntityPlayer) {
/*  782 */       return (float)((EntityPlayer)this).getBukkitEntity().getHealth();
/*      */     }
/*      */     
/*  785 */     return ((Float)this.datawatcher.<Float>get(HEALTH)).floatValue();
/*      */   }
/*      */ 
/*      */   
/*      */   public void setHealth(float f) {
/*  790 */     if (this instanceof EntityPlayer) {
/*  791 */       CraftPlayer player = ((EntityPlayer)this).getBukkitEntity();
/*      */       
/*  793 */       if (f < 0.0F) {
/*  794 */         player.setRealHealth(0.0D);
/*  795 */       } else if (f > player.getMaxHealth()) {
/*  796 */         player.setRealHealth(player.getMaxHealth());
/*      */       } else {
/*  798 */         player.setRealHealth(f);
/*      */       } 
/*      */       
/*  801 */       this.datawatcher.set(HEALTH, Float.valueOf(player.getScaledHealth()));
/*      */       
/*      */       return;
/*      */     } 
/*  805 */     this.datawatcher.set(HEALTH, Float.valueOf(MathHelper.a(f, 0.0F, getMaxHealth())));
/*      */   }
/*      */   
/*      */   public boolean damageEntity(DamageSource damagesource, float f) {
/*  809 */     if (isInvulnerable(damagesource))
/*  810 */       return false; 
/*  811 */     if (this.world.isClientSide) {
/*  812 */       return false;
/*      */     }
/*  814 */     this.ticksFarFromPlayer = 0;
/*  815 */     if (getHealth() <= 0.0F)
/*  816 */       return false; 
/*  817 */     if (damagesource.o() && hasEffect(MobEffects.FIRE_RESISTANCE)) {
/*  818 */       return false;
/*      */     }
/*  820 */     float f1 = f;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  828 */     boolean flag = (f > 0.0F && applyBlockingModifier(damagesource));
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
/*  845 */     this.aG = 1.5F;
/*  846 */     boolean flag1 = true;
/*      */     
/*  848 */     if (this.noDamageTicks > this.maxNoDamageTicks / 2.0F) {
/*  849 */       if (f <= this.lastDamage) {
/*  850 */         this.forceExplosionKnockback = true;
/*  851 */         return false;
/*      */       } 
/*      */ 
/*      */       
/*  855 */       if (!damageEntity0(damagesource, f - this.lastDamage)) {
/*  856 */         return false;
/*      */       }
/*      */       
/*  859 */       this.lastDamage = f;
/*  860 */       flag1 = false;
/*      */     } else {
/*      */       
/*  863 */       if (!damageEntity0(damagesource, f)) {
/*  864 */         return false;
/*      */       }
/*  866 */       this.lastDamage = f;
/*  867 */       this.noDamageTicks = this.maxNoDamageTicks;
/*      */ 
/*      */       
/*  870 */       this.az = 10;
/*  871 */       this.hurtTicks = this.az;
/*      */     } 
/*      */ 
/*      */     
/*  875 */     if (this instanceof EntityAnimal) {
/*  876 */       ((EntityAnimal)this).resetLove();
/*  877 */       if (this instanceof EntityTameableAnimal) {
/*  878 */         ((EntityTameableAnimal)this).getGoalSit().setSitting(false);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  883 */     this.aA = 0.0F;
/*  884 */     Entity entity1 = damagesource.getEntity();
/*      */     
/*  886 */     if (entity1 != null) {
/*  887 */       if (entity1 instanceof EntityLiving) {
/*  888 */         a((EntityLiving)entity1);
/*      */       }
/*      */       
/*  891 */       if (entity1 instanceof EntityHuman) {
/*  892 */         this.lastDamageByPlayerTime = 100;
/*  893 */         this.killer = (EntityHuman)entity1;
/*  894 */       } else if (entity1 instanceof EntityWolf) {
/*  895 */         EntityWolf entitywolf = (EntityWolf)entity1;
/*      */         
/*  897 */         if (entitywolf.isTamed()) {
/*  898 */           this.lastDamageByPlayerTime = 100;
/*  899 */           this.killer = null;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  904 */     if (flag1) {
/*  905 */       if (flag) {
/*  906 */         this.world.broadcastEntityEffect(this, (byte)29);
/*  907 */       } else if (damagesource instanceof EntityDamageSource && ((EntityDamageSource)damagesource).x()) {
/*  908 */         this.world.broadcastEntityEffect(this, (byte)33);
/*      */       } else {
/*      */         byte b0;
/*      */         
/*  912 */         if (damagesource == DamageSource.DROWN) {
/*  913 */           b0 = 36;
/*  914 */         } else if (damagesource.o()) {
/*  915 */           b0 = 37;
/*      */         } else {
/*  917 */           b0 = 2;
/*      */         } 
/*      */         
/*  920 */         this.world.broadcastEntityEffect(this, b0);
/*      */       } 
/*      */       
/*  923 */       if (damagesource != DamageSource.DROWN && (!flag || f > 0.0F)) {
/*  924 */         ax();
/*      */       }
/*      */       
/*  927 */       if (entity1 != null) {
/*  928 */         double d0 = entity1.locX - this.locX;
/*      */         
/*      */         double d1;
/*      */         
/*  932 */         for (d1 = entity1.locZ - this.locZ; d0 * d0 + d1 * d1 < 1.0E-4D; d1 = (Math.random() - Math.random()) * 0.01D) {
/*  933 */           d0 = (Math.random() - Math.random()) * 0.01D;
/*      */         }
/*      */         
/*  936 */         this.aA = (float)(MathHelper.c(d1, d0) * 57.2957763671875D - this.yaw);
/*  937 */         a(entity1, 0.4F, d0, d1);
/*      */       } else {
/*  939 */         this.aA = ((int)(Math.random() * 2.0D) * 180);
/*      */       } 
/*      */     } 
/*      */     
/*  943 */     if (getHealth() <= 0.0F) {
/*  944 */       if (!e(damagesource)) {
/*  945 */         SoundEffect soundeffect = cf();
/*      */         
/*  947 */         if (flag1 && soundeffect != null) {
/*  948 */           a(soundeffect, cq(), cr());
/*      */         }
/*      */         
/*  951 */         die(damagesource);
/*      */       } 
/*  953 */     } else if (flag1) {
/*  954 */       c(damagesource);
/*      */     } 
/*      */     
/*  957 */     boolean flag2 = !(flag && f <= 0.0F);
/*      */     
/*  959 */     if (flag2) {
/*  960 */       this.bG = damagesource;
/*  961 */       this.bH = this.world.getTime();
/*      */     } 
/*      */     
/*  964 */     if (this instanceof EntityPlayer) {
/*  965 */       CriterionTriggers.h.a((EntityPlayer)this, damagesource, f1, f, flag);
/*      */     }
/*      */     
/*  968 */     if (entity1 instanceof EntityPlayer) {
/*  969 */       CriterionTriggers.g.a((EntityPlayer)entity1, this, damagesource, f1, f, flag);
/*      */     }
/*      */     
/*  972 */     return flag2;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void c(EntityLiving entityliving) {
/*  978 */     entityliving.a(this, 0.5F, this.locX - entityliving.locX, this.locZ - entityliving.locZ);
/*      */   }
/*      */   
/*      */   private boolean e(DamageSource damagesource) {
/*  982 */     if (damagesource.ignoresInvulnerability()) {
/*  983 */       return false;
/*      */     }
/*  985 */     ItemStack itemstack = null;
/*  986 */     EnumHand[] aenumhand = EnumHand.values();
/*  987 */     int i = aenumhand.length;
/*      */ 
/*      */     
/*  990 */     ItemStack itemstack1 = ItemStack.a;
/*  991 */     for (int j = 0; j < i; j++) {
/*  992 */       EnumHand enumhand = aenumhand[j];
/*  993 */       itemstack1 = b(enumhand);
/*      */       
/*  995 */       if (itemstack1.getItem() == Items.cY) {
/*  996 */         itemstack = itemstack1.cloneItemStack();
/*      */         
/*      */         break;
/*      */       } 
/*      */     } 
/*      */     
/* 1002 */     EntityResurrectEvent event = new EntityResurrectEvent((LivingEntity)getBukkitEntity());
/* 1003 */     event.setCancelled((itemstack == null));
/* 1004 */     this.world.getServer().getPluginManager().callEvent((Event)event);
/*      */     
/* 1006 */     if (!event.isCancelled()) {
/* 1007 */       itemstack1.subtract(1);
/*      */       
/* 1009 */       if (this instanceof EntityPlayer) {
/* 1010 */         EntityPlayer entityplayer = (EntityPlayer)this;
/*      */         
/* 1012 */         entityplayer.b(StatisticList.b(Items.cY));
/* 1013 */         CriterionTriggers.A.a(entityplayer, itemstack);
/*      */       } 
/*      */       
/* 1016 */       setHealth(1.0F);
/* 1017 */       removeAllEffects();
/* 1018 */       addEffect(new MobEffect(MobEffects.REGENERATION, 900, 1));
/* 1019 */       addEffect(new MobEffect(MobEffects.ABSORBTION, 100, 1));
/* 1020 */       this.world.broadcastEntityEffect(this, (byte)35);
/*      */     } 
/*      */     
/* 1023 */     return !event.isCancelled();
/*      */   }
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public DamageSource ce() {
/* 1029 */     if (this.world.getTime() - this.bH > 40L) {
/* 1030 */       this.bG = null;
/*      */     }
/*      */     
/* 1033 */     return this.bG;
/*      */   }
/*      */   
/*      */   protected void c(DamageSource damagesource) {
/* 1037 */     SoundEffect soundeffect = d(damagesource);
/*      */     
/* 1039 */     if (soundeffect != null) {
/* 1040 */       a(soundeffect, cq(), cr());
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean applyBlockingModifier(DamageSource damagesource) {
/* 1046 */     if (!damagesource.ignoresArmor() && isBlocking()) {
/* 1047 */       Vec3D vec3d = damagesource.v();
/*      */       
/* 1049 */       if (vec3d != null) {
/* 1050 */         Vec3D vec3d1 = e(1.0F);
/* 1051 */         Vec3D vec3d2 = vec3d.a(new Vec3D(this.locX, this.locY, this.locZ)).a();
/*      */         
/* 1053 */         vec3d2 = new Vec3D(vec3d2.x, 0.0D, vec3d2.z);
/* 1054 */         if (vec3d2.b(vec3d1) < 0.0D) {
/* 1055 */           return true;
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 1060 */     return false;
/*      */   }
/*      */   
/*      */   public void b(ItemStack itemstack) {
/* 1064 */     a(SoundEffects.dw, 0.8F, 0.8F + this.world.random.nextFloat() * 0.4F);
/*      */     
/* 1066 */     for (int i = 0; i < 5; i++) {
/* 1067 */       Vec3D vec3d = new Vec3D((this.random.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D);
/*      */       
/* 1069 */       vec3d = vec3d.a(-this.pitch * 0.017453292F);
/* 1070 */       vec3d = vec3d.b(-this.yaw * 0.017453292F);
/* 1071 */       double d0 = -this.random.nextFloat() * 0.6D - 0.3D;
/* 1072 */       Vec3D vec3d1 = new Vec3D((this.random.nextFloat() - 0.5D) * 0.3D, d0, 0.6D);
/*      */       
/* 1074 */       vec3d1 = vec3d1.a(-this.pitch * 0.017453292F);
/* 1075 */       vec3d1 = vec3d1.b(-this.yaw * 0.017453292F);
/* 1076 */       vec3d1 = vec3d1.add(this.locX, this.locY + getHeadHeight(), this.locZ);
/* 1077 */       this.world.addParticle(EnumParticle.ITEM_CRACK, vec3d1.x, vec3d1.y, vec3d1.z, vec3d.x, vec3d.y + 0.05D, vec3d.z, new int[] { Item.getId(itemstack.getItem()) });
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void die(DamageSource damagesource) {
/* 1083 */     if (!this.aU) {
/* 1084 */       Entity entity = damagesource.getEntity();
/* 1085 */       EntityLiving entityliving = ci();
/*      */       
/* 1087 */       if (this.bb >= 0 && entityliving != null) {
/* 1088 */         entityliving.a(this, this.bb, damagesource);
/*      */       }
/*      */       
/* 1091 */       if (entity != null) {
/* 1092 */         entity.b(this);
/*      */       }
/*      */       
/* 1095 */       this.aU = true;
/* 1096 */       getCombatTracker().g();
/* 1097 */       if (!this.world.isClientSide) {
/* 1098 */         int i = 0;
/*      */         
/* 1100 */         if (entity instanceof EntityHuman) {
/* 1101 */           i = EnchantmentManager.g((EntityLiving)entity);
/*      */         }
/*      */         
/* 1104 */         if (isDropExperience() && this.world.getGameRules().getBoolean("doMobLoot")) {
/* 1105 */           boolean flag = (this.lastDamageByPlayerTime > 0);
/*      */           
/* 1107 */           a(flag, i, damagesource);
/*      */           
/* 1109 */           CraftEventFactory.callEntityDeathEvent(this, this.drops);
/* 1110 */           this.drops = new ArrayList<>();
/*      */         } else {
/* 1112 */           CraftEventFactory.callEntityDeathEvent(this);
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/* 1117 */       this.world.broadcastEntityEffect(this, (byte)3);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void a(boolean flag, int i, DamageSource damagesource) {
/* 1122 */     dropDeathLoot(flag, i);
/* 1123 */     dropEquipment(flag, i);
/*      */   }
/*      */   
/*      */   protected void dropEquipment(boolean flag, int i) {}
/*      */   
/*      */   public void a(Entity entity, float f, double d0, double d1) {
/* 1129 */     if (this.random.nextDouble() >= getAttributeInstance(GenericAttributes.c).getValue()) {
/* 1130 */       this.impulse = true;
/* 1131 */       float f1 = MathHelper.sqrt(d0 * d0 + d1 * d1);
/*      */       
/* 1133 */       this.motX /= 2.0D;
/* 1134 */       this.motZ /= 2.0D;
/* 1135 */       this.motX -= d0 / f1 * f;
/* 1136 */       this.motZ -= d1 / f1 * f;
/* 1137 */       if (this.onGround) {
/* 1138 */         this.motY /= 2.0D;
/* 1139 */         this.motY += f;
/* 1140 */         if (this.motY > 0.4000000059604645D) {
/* 1141 */           this.motY = 0.4000000059604645D;
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   protected SoundEffect d(DamageSource damagesource) {
/* 1150 */     return SoundEffects.bX;
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   protected SoundEffect cf() {
/* 1155 */     return SoundEffects.bS;
/*      */   }
/*      */   
/*      */   protected SoundEffect e(int i) {
/* 1159 */     return (i > 4) ? SoundEffects.bQ : SoundEffects.bY;
/*      */   }
/*      */   
/*      */   protected void dropDeathLoot(boolean flag, int i) {}
/*      */   
/*      */   public boolean m_() {
/* 1165 */     int i = MathHelper.floor(this.locX);
/* 1166 */     int j = MathHelper.floor((getBoundingBox()).b);
/* 1167 */     int k = MathHelper.floor(this.locZ);
/*      */     
/* 1169 */     if (this instanceof EntityHuman && ((EntityHuman)this).isSpectator()) {
/* 1170 */       return false;
/*      */     }
/* 1172 */     BlockPosition blockposition = new BlockPosition(i, j, k);
/* 1173 */     IBlockData iblockdata = this.world.getType(blockposition);
/* 1174 */     Block block = iblockdata.getBlock();
/*      */     
/* 1176 */     return (block != Blocks.LADDER && block != Blocks.VINE) ? ((block instanceof BlockTrapdoor && a(blockposition, iblockdata))) : true;
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean a(BlockPosition blockposition, IBlockData iblockdata) {
/* 1181 */     if (((Boolean)iblockdata.<Boolean>get(BlockTrapdoor.OPEN)).booleanValue()) {
/* 1182 */       IBlockData iblockdata1 = this.world.getType(blockposition.down());
/*      */       
/* 1184 */       if (iblockdata1.getBlock() == Blocks.LADDER && iblockdata1.get(BlockLadder.FACING) == iblockdata.get(BlockTrapdoor.FACING)) {
/* 1185 */         return true;
/*      */       }
/*      */     } 
/*      */     
/* 1189 */     return false;
/*      */   }
/*      */   
/*      */   public boolean isAlive() {
/* 1193 */     return (!this.dead && getHealth() > 0.0F);
/*      */   }
/*      */   
/*      */   public void e(float f, float f1) {
/* 1197 */     super.e(f, f1);
/* 1198 */     MobEffect mobeffect = getEffect(MobEffects.JUMP);
/* 1199 */     float f2 = (mobeffect == null) ? 0.0F : (mobeffect.getAmplifier() + 1);
/* 1200 */     int i = MathHelper.f((f - 3.0F - f2) * f1);
/*      */     
/* 1202 */     if (i > 0) {
/*      */       
/* 1204 */       if (!damageEntity(DamageSource.FALL, i)) {
/*      */         return;
/*      */       }
/*      */       
/* 1208 */       a(e(i), 1.0F, 1.0F);
/*      */       
/* 1210 */       int j = MathHelper.floor(this.locX);
/* 1211 */       int k = MathHelper.floor(this.locY - 0.20000000298023224D);
/* 1212 */       int l = MathHelper.floor(this.locZ);
/* 1213 */       IBlockData iblockdata = this.world.getType(new BlockPosition(j, k, l));
/*      */       
/* 1215 */       if (iblockdata.getMaterial() != Material.AIR) {
/* 1216 */         SoundEffectType soundeffecttype = iblockdata.getBlock().getStepSound();
/*      */         
/* 1218 */         a(soundeffecttype.g(), soundeffecttype.a() * 0.5F, soundeffecttype.b() * 0.75F);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public int getArmorStrength() {
/* 1225 */     AttributeInstance attributeinstance = getAttributeInstance(GenericAttributes.h);
/*      */     
/* 1227 */     return MathHelper.floor(attributeinstance.getValue());
/*      */   }
/*      */   
/*      */   protected void damageArmor(float f) {}
/*      */   
/*      */   protected void damageShield(float f) {}
/*      */   
/*      */   protected float applyArmorModifier(DamageSource damagesource, float f) {
/* 1235 */     if (!damagesource.ignoresArmor())
/*      */     {
/* 1237 */       f = CombatMath.a(f, getArmorStrength(), (float)getAttributeInstance(GenericAttributes.i).getValue());
/*      */     }
/*      */     
/* 1240 */     return f;
/*      */   }
/*      */   
/*      */   protected float applyMagicModifier(DamageSource damagesource, float f) {
/* 1244 */     if (damagesource.isStarvation()) {
/* 1245 */       return f;
/*      */     }
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
/* 1258 */     if (f <= 0.0F) {
/* 1259 */       return 0.0F;
/*      */     }
/* 1261 */     int i = EnchantmentManager.a(getArmorItems(), damagesource);
/* 1262 */     if (i > 0) {
/* 1263 */       f = CombatMath.a(f, i);
/*      */     }
/*      */     
/* 1266 */     return f;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean damageEntity0(final DamageSource damagesource, float f) {
/* 1273 */     if (!isInvulnerable(damagesource)) {
/* 1274 */       boolean human = this instanceof EntityHuman;
/* 1275 */       float originalDamage = f;
/* 1276 */       Function<Double, Double> hardHat = new Function<Double, Double>()
/*      */         {
/*      */           public Double apply(Double f) {
/* 1279 */             if ((damagesource == DamageSource.ANVIL || damagesource == DamageSource.FALLING_BLOCK) && !EntityLiving.this.getEquipment(EnumItemSlot.HEAD).isEmpty()) {
/* 1280 */               return Double.valueOf(-(f.doubleValue() - f.doubleValue() * 0.75D));
/*      */             }
/*      */             
/* 1283 */             return Double.valueOf(-0.0D);
/*      */           }
/*      */         };
/* 1286 */       float hardHatModifier = ((Double)hardHat.apply(Double.valueOf(f))).floatValue();
/* 1287 */       f += hardHatModifier;
/*      */       
/* 1289 */       Function<Double, Double> blocking = new Function<Double, Double>()
/*      */         {
/*      */           public Double apply(Double f) {
/* 1292 */             return Double.valueOf(-(EntityLiving.this.applyBlockingModifier(damagesource) ? f.doubleValue() : 0.0D));
/*      */           }
/*      */         };
/* 1295 */       float blockingModifier = ((Double)blocking.apply(Double.valueOf(f))).floatValue();
/* 1296 */       f += blockingModifier;
/*      */       
/* 1298 */       Function<Double, Double> armor = new Function<Double, Double>()
/*      */         {
/*      */           public Double apply(Double f) {
/* 1301 */             return Double.valueOf(-(f.doubleValue() - EntityLiving.this.applyArmorModifier(damagesource, f.floatValue())));
/*      */           }
/*      */         };
/* 1304 */       float armorModifier = ((Double)armor.apply(Double.valueOf(f))).floatValue();
/* 1305 */       f += armorModifier;
/*      */       
/* 1307 */       Function<Double, Double> resistance = new Function<Double, Double>()
/*      */         {
/*      */           public Double apply(Double f) {
/* 1310 */             if (!damagesource.isStarvation() && EntityLiving.this.hasEffect(MobEffects.RESISTANCE) && damagesource != DamageSource.OUT_OF_WORLD) {
/* 1311 */               int i = (EntityLiving.this.getEffect(MobEffects.RESISTANCE).getAmplifier() + 1) * 5;
/* 1312 */               int j = 25 - i;
/* 1313 */               float f1 = f.floatValue() * j;
/* 1314 */               return Double.valueOf(-(f.doubleValue() - (f1 / 25.0F)));
/*      */             } 
/* 1316 */             return Double.valueOf(-0.0D);
/*      */           }
/*      */         };
/* 1319 */       float resistanceModifier = ((Double)resistance.apply(Double.valueOf(f))).floatValue();
/* 1320 */       f += resistanceModifier;
/*      */       
/* 1322 */       Function<Double, Double> magic = new Function<Double, Double>()
/*      */         {
/*      */           public Double apply(Double f) {
/* 1325 */             return Double.valueOf(-(f.doubleValue() - EntityLiving.this.applyMagicModifier(damagesource, f.floatValue())));
/*      */           }
/*      */         };
/* 1328 */       float magicModifier = ((Double)magic.apply(Double.valueOf(f))).floatValue();
/* 1329 */       f += magicModifier;
/*      */       
/* 1331 */       Function<Double, Double> absorption = new Function<Double, Double>()
/*      */         {
/*      */           public Double apply(Double f) {
/* 1334 */             return Double.valueOf(-Math.max(f.doubleValue() - Math.max(f.doubleValue() - EntityLiving.this.getAbsorptionHearts(), 0.0D), 0.0D));
/*      */           }
/*      */         };
/* 1337 */       float absorptionModifier = ((Double)absorption.apply(Double.valueOf(f))).floatValue();
/*      */       
/* 1339 */       EntityDamageEvent event = CraftEventFactory.handleLivingEntityDamageEvent(this, damagesource, originalDamage, hardHatModifier, blockingModifier, armorModifier, resistanceModifier, magicModifier, absorptionModifier, hardHat, blocking, armor, resistance, magic, absorption);
/* 1340 */       if (event.isCancelled()) {
/* 1341 */         return false;
/*      */       }
/*      */       
/* 1344 */       f = (float)event.getFinalDamage();
/*      */ 
/*      */       
/* 1347 */       if ((damagesource == DamageSource.ANVIL || damagesource == DamageSource.FALLING_BLOCK) && getEquipment(EnumItemSlot.HEAD) != null) {
/* 1348 */         getEquipment(EnumItemSlot.HEAD).damage((int)(event.getDamage() * 4.0D + this.random.nextFloat() * event.getDamage() * 2.0D), this);
/*      */       }
/*      */ 
/*      */       
/* 1352 */       if (!damagesource.ignoresArmor()) {
/* 1353 */         float armorDamage = (float)(event.getDamage() + event.getDamage(EntityDamageEvent.DamageModifier.BLOCKING) + event.getDamage(EntityDamageEvent.DamageModifier.HARD_HAT));
/* 1354 */         damageArmor(armorDamage);
/*      */       } 
/*      */ 
/*      */       
/* 1358 */       if (event.getDamage(EntityDamageEvent.DamageModifier.BLOCKING) < 0.0D) {
/* 1359 */         damageShield((float)-event.getDamage(EntityDamageEvent.DamageModifier.BLOCKING));
/* 1360 */         Entity entity = damagesource.i();
/*      */         
/* 1362 */         if (entity instanceof EntityLiving) {
/* 1363 */           c((EntityLiving)entity);
/*      */         }
/*      */       } 
/*      */       
/* 1367 */       absorptionModifier = (float)-event.getDamage(EntityDamageEvent.DamageModifier.ABSORPTION);
/* 1368 */       setAbsorptionHearts(Math.max(getAbsorptionHearts() - absorptionModifier, 0.0F));
/* 1369 */       if (f > 0.0F || !human) {
/* 1370 */         if (human) {
/*      */           
/* 1372 */           ((EntityHuman)this).applyExhaustion(damagesource.getExhaustionCost());
/* 1373 */           if (f < 3.4028235E37F) {
/* 1374 */             ((EntityHuman)this).a(StatisticList.z, Math.round(f * 10.0F));
/*      */           }
/*      */         } 
/*      */         
/* 1378 */         float f2 = getHealth();
/*      */         
/* 1380 */         setHealth(f2 - f);
/* 1381 */         getCombatTracker().trackDamage(damagesource, f2, f);
/*      */         
/* 1383 */         if (!human) {
/* 1384 */           setAbsorptionHearts(getAbsorptionHearts() - f);
/*      */         }
/*      */         
/* 1387 */         return true;
/*      */       } 
/*      */       
/* 1390 */       if (event.getDamage(EntityDamageEvent.DamageModifier.BLOCKING) < 0.0D) {
/* 1391 */         if (this instanceof EntityPlayer) {
/* 1392 */           CriterionTriggers.h.a((EntityPlayer)this, damagesource, f, originalDamage, true);
/*      */         }
/*      */         
/* 1395 */         if (damagesource.getEntity() instanceof EntityPlayer) {
/* 1396 */           CriterionTriggers.g.a((EntityPlayer)damagesource.getEntity(), this, damagesource, f, originalDamage, true);
/*      */         }
/*      */         
/* 1399 */         return false;
/*      */       } 
/* 1401 */       return (originalDamage > 0.0F);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1406 */     return false;
/*      */   }
/*      */   
/*      */   public CombatTracker getCombatTracker() {
/* 1410 */     return this.combatTracker;
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public EntityLiving ci() {
/* 1415 */     return (this.combatTracker.c() != null) ? this.combatTracker.c() : ((this.killer != null) ? this.killer : ((this.lastDamager != null) ? this.lastDamager : null));
/*      */   }
/*      */   
/*      */   public final float getMaxHealth() {
/* 1419 */     return (float)getAttributeInstance(GenericAttributes.maxHealth).getValue();
/*      */   }
/*      */   
/*      */   public final int getArrowCount() {
/* 1423 */     return ((Integer)this.datawatcher.<Integer>get(br)).intValue();
/*      */   }
/*      */   
/*      */   public final void setArrowCount(int i) {
/* 1427 */     this.datawatcher.set(br, Integer.valueOf(i));
/*      */   }
/*      */   
/*      */   private int p() {
/* 1431 */     return hasEffect(MobEffects.FASTER_DIG) ? (6 - 1 + getEffect(MobEffects.FASTER_DIG).getAmplifier()) : (hasEffect(MobEffects.SLOWER_DIG) ? (6 + (1 + getEffect(MobEffects.SLOWER_DIG).getAmplifier()) * 2) : 6);
/*      */   }
/*      */   
/*      */   public void a(EnumHand enumhand) {
/* 1435 */     if (!this.au || this.aw >= p() / 2 || this.aw < 0) {
/* 1436 */       this.aw = -1;
/* 1437 */       this.au = true;
/* 1438 */       this.av = enumhand;
/* 1439 */       if (this.world instanceof WorldServer) {
/* 1440 */         ((WorldServer)this.world).getTracker().a(this, new PacketPlayOutAnimation(this, (enumhand == EnumHand.MAIN_HAND) ? 0 : 3));
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void ac() {
/* 1447 */     damageEntity(DamageSource.OUT_OF_WORLD, 4.0F);
/*      */   }
/*      */   
/*      */   protected void cl() {
/* 1451 */     int i = p();
/*      */     
/* 1453 */     if (this.au) {
/* 1454 */       this.aw++;
/* 1455 */       if (this.aw >= i) {
/* 1456 */         this.aw = 0;
/* 1457 */         this.au = false;
/*      */       } 
/*      */     } else {
/* 1460 */       this.aw = 0;
/*      */     } 
/*      */     
/* 1463 */     this.aD = this.aw / i;
/*      */   }
/*      */   
/*      */   public AttributeInstance getAttributeInstance(IAttribute iattribute) {
/* 1467 */     return getAttributeMap().a(iattribute);
/*      */   }
/*      */   
/*      */   public AttributeMapBase getAttributeMap() {
/* 1471 */     if (this.attributeMap == null) {
/* 1472 */       this.attributeMap = new AttributeMapServer();
/* 1473 */       this.craftAttributes = new CraftAttributeMap(this.attributeMap);
/*      */     } 
/*      */     
/* 1476 */     return this.attributeMap;
/*      */   }
/*      */   
/*      */   public EnumMonsterType getMonsterType() {
/* 1480 */     return EnumMonsterType.UNDEFINED;
/*      */   }
/*      */   
/*      */   public ItemStack getItemInMainHand() {
/* 1484 */     return getEquipment(EnumItemSlot.MAINHAND);
/*      */   }
/*      */   
/*      */   public ItemStack getItemInOffHand() {
/* 1488 */     return getEquipment(EnumItemSlot.OFFHAND);
/*      */   }
/*      */   
/*      */   public ItemStack b(EnumHand enumhand) {
/* 1492 */     if (enumhand == EnumHand.MAIN_HAND)
/* 1493 */       return getEquipment(EnumItemSlot.MAINHAND); 
/* 1494 */     if (enumhand == EnumHand.OFF_HAND) {
/* 1495 */       return getEquipment(EnumItemSlot.OFFHAND);
/*      */     }
/* 1497 */     throw new IllegalArgumentException("Invalid hand " + enumhand);
/*      */   }
/*      */ 
/*      */   
/*      */   public void a(EnumHand enumhand, ItemStack itemstack) {
/* 1502 */     if (enumhand == EnumHand.MAIN_HAND) {
/* 1503 */       setSlot(EnumItemSlot.MAINHAND, itemstack);
/*      */     } else {
/* 1505 */       if (enumhand != EnumHand.OFF_HAND) {
/* 1506 */         throw new IllegalArgumentException("Invalid hand " + enumhand);
/*      */       }
/*      */       
/* 1509 */       setSlot(EnumItemSlot.OFFHAND, itemstack);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean a(EnumItemSlot enumitemslot) {
/* 1515 */     return !getEquipment(enumitemslot).isEmpty();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSprinting(boolean flag) {
/* 1525 */     super.setSprinting(flag);
/* 1526 */     AttributeInstance attributeinstance = getAttributeInstance(GenericAttributes.MOVEMENT_SPEED);
/*      */     
/* 1528 */     if (attributeinstance.a(b) != null) {
/* 1529 */       attributeinstance.c(c);
/*      */     }
/*      */     
/* 1532 */     if (flag) {
/* 1533 */       attributeinstance.b(c);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected float cq() {
/* 1539 */     return 1.0F;
/*      */   }
/*      */   
/*      */   protected float cr() {
/* 1543 */     return isBaby() ? ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.5F) : ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
/*      */   }
/*      */   
/*      */   protected boolean isFrozen() {
/* 1547 */     return (getHealth() <= 0.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void A(Entity entity) {
/* 1553 */     if (!(entity instanceof EntityBoat) && !(entity instanceof EntityHorseAbstract)) {
/* 1554 */       double d1 = entity.locX;
/* 1555 */       double d2 = (entity.getBoundingBox()).b + entity.length;
/*      */       
/* 1557 */       double d0 = entity.locZ;
/* 1558 */       EnumDirection enumdirection = entity.bu();
/*      */       
/* 1560 */       if (enumdirection != null) {
/* 1561 */         EnumDirection enumdirection1 = enumdirection.e();
/* 1562 */         int[][] aint = { { 0, 1 }, { 0, -1 }, { -1, 1 }, { -1, -1 }, { 1, 1 }, { 1, -1 }, { -1 }, { 1 }, { 0, 1 } };
/* 1563 */         double d3 = Math.floor(this.locX) + 0.5D;
/* 1564 */         double d4 = Math.floor(this.locZ) + 0.5D;
/* 1565 */         double d5 = (getBoundingBox()).d - (getBoundingBox()).a;
/* 1566 */         double d6 = (getBoundingBox()).f - (getBoundingBox()).c;
/* 1567 */         AxisAlignedBB axisalignedbb = new AxisAlignedBB(d3 - d5 / 2.0D, (entity.getBoundingBox()).b, d4 - d6 / 2.0D, d3 + d5 / 2.0D, Math.floor((entity.getBoundingBox()).b) + this.length, d4 + d6 / 2.0D);
/* 1568 */         int[][] aint1 = aint;
/* 1569 */         int i = aint.length;
/*      */         
/* 1571 */         for (int j = 0; j < i; j++) {
/* 1572 */           int[] aint2 = aint1[j];
/* 1573 */           double d7 = (enumdirection.getAdjacentX() * aint2[0] + enumdirection1.getAdjacentX() * aint2[1]);
/* 1574 */           double d8 = (enumdirection.getAdjacentZ() * aint2[0] + enumdirection1.getAdjacentZ() * aint2[1]);
/* 1575 */           double d9 = d3 + d7;
/* 1576 */           double d10 = d4 + d8;
/* 1577 */           AxisAlignedBB axisalignedbb1 = axisalignedbb.d(d7, 0.0D, d8);
/*      */           
/* 1579 */           if (!this.world.a(axisalignedbb1)) {
/* 1580 */             if (this.world.getType(new BlockPosition(d9, this.locY, d10)).q()) {
/* 1581 */               enderTeleportTo(d9, this.locY + 1.0D, d10);
/*      */               
/*      */               return;
/*      */             } 
/* 1585 */             BlockPosition blockposition = new BlockPosition(d9, this.locY - 1.0D, d10);
/*      */             
/* 1587 */             if (this.world.getType(blockposition).q() || this.world.getType(blockposition).getMaterial() == Material.WATER) {
/* 1588 */               d1 = d9;
/* 1589 */               d2 = this.locY + 1.0D;
/* 1590 */               d0 = d10;
/*      */             } 
/* 1592 */           } else if (!this.world.a(axisalignedbb1.d(0.0D, 1.0D, 0.0D)) && this.world.getType(new BlockPosition(d9, this.locY + 1.0D, d10)).q()) {
/* 1593 */             d1 = d9;
/* 1594 */             d2 = this.locY + 2.0D;
/* 1595 */             d0 = d10;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/* 1600 */       enderTeleportTo(d1, d2, d0);
/*      */     } else {
/* 1602 */       float f; double d11 = (this.width / 2.0F + entity.width / 2.0F) + 0.4D;
/*      */ 
/*      */       
/* 1605 */       if (entity instanceof EntityBoat) {
/* 1606 */         f = 0.0F;
/*      */       } else {
/* 1608 */         f = 1.5707964F * ((getMainHand() == EnumMainHand.RIGHT) ? -1 : true);
/*      */       } 
/*      */       
/* 1611 */       float f1 = -MathHelper.sin(-this.yaw * 0.017453292F - 3.1415927F + f);
/* 1612 */       float f2 = -MathHelper.cos(-this.yaw * 0.017453292F - 3.1415927F + f);
/*      */       
/* 1614 */       double d0 = (Math.abs(f1) > Math.abs(f2)) ? (d11 / Math.abs(f1)) : (d11 / Math.abs(f2));
/* 1615 */       double d12 = this.locX + f1 * d0;
/* 1616 */       double d13 = this.locZ + f2 * d0;
/*      */       
/* 1618 */       setPosition(d12, entity.locY + entity.length + 0.001D, d13);
/* 1619 */       if (this.world.a(getBoundingBox())) {
/* 1620 */         setPosition(d12, entity.locY + entity.length + 1.001D, d13);
/* 1621 */         if (this.world.a(getBoundingBox())) {
/* 1622 */           setPosition(entity.locX, entity.locY + this.length + 0.001D, entity.locZ);
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   protected float ct() {
/* 1629 */     return 0.42F;
/*      */   }
/*      */   
/*      */   protected void cu() {
/* 1633 */     this.motY = ct();
/* 1634 */     if (hasEffect(MobEffects.JUMP)) {
/* 1635 */       this.motY += ((getEffect(MobEffects.JUMP).getAmplifier() + 1) * 0.1F);
/*      */     }
/*      */     
/* 1638 */     if (isSprinting()) {
/* 1639 */       float f = this.yaw * 0.017453292F;
/*      */       
/* 1641 */       this.motX -= (MathHelper.sin(f) * 0.2F);
/* 1642 */       this.motZ += (MathHelper.cos(f) * 0.2F);
/*      */     } 
/*      */     
/* 1645 */     this.impulse = true;
/*      */   }
/*      */   
/*      */   protected void cv() {
/* 1649 */     this.motY += 0.03999999910593033D;
/*      */   }
/*      */   
/*      */   protected void cw() {
/* 1653 */     this.motY += 0.03999999910593033D;
/*      */   }
/*      */   
/*      */   protected float cx() {
/* 1657 */     return 0.8F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void a(float f, float f1, float f2) {
/* 1665 */     if (cC() || bI())
/*      */     {
/*      */ 
/*      */ 
/*      */       
/* 1670 */       if (isInWater() && (!(this instanceof EntityHuman) || !((EntityHuman)this).abilities.isFlying)) {
/* 1671 */         double d = this.locY;
/* 1672 */         float f4 = cx();
/* 1673 */         float f3 = 0.02F;
/* 1674 */         float f5 = EnchantmentManager.e(this);
/* 1675 */         if (f5 > 3.0F) {
/* 1676 */           f5 = 3.0F;
/*      */         }
/*      */         
/* 1679 */         if (!this.onGround) {
/* 1680 */           f5 *= 0.5F;
/*      */         }
/*      */         
/* 1683 */         if (f5 > 0.0F) {
/* 1684 */           f4 += (0.54600006F - f4) * f5 / 3.0F;
/* 1685 */           f3 += (cy() - f3) * f5 / 3.0F;
/*      */         } 
/*      */         
/* 1688 */         b(f, f1, f2, f3);
/* 1689 */         move(EnumMoveType.SELF, this.motX, this.motY, this.motZ);
/* 1690 */         this.motX *= f4;
/* 1691 */         this.motY *= 0.800000011920929D;
/* 1692 */         this.motZ *= f4;
/* 1693 */         if (!isNoGravity()) {
/* 1694 */           this.motY -= 0.02D;
/*      */         }
/*      */         
/* 1697 */         if (this.positionChanged && c(this.motX, this.motY + 0.6000000238418579D - this.locY + d, this.motZ)) {
/* 1698 */           this.motY = 0.30000001192092896D;
/*      */         }
/* 1700 */       } else if (au() && (!(this instanceof EntityHuman) || !((EntityHuman)this).abilities.isFlying)) {
/* 1701 */         double d = this.locY;
/* 1702 */         b(f, f1, f2, 0.02F);
/* 1703 */         move(EnumMoveType.SELF, this.motX, this.motY, this.motZ);
/* 1704 */         this.motX *= 0.5D;
/* 1705 */         this.motY *= 0.5D;
/* 1706 */         this.motZ *= 0.5D;
/* 1707 */         if (!isNoGravity()) {
/* 1708 */           this.motY -= 0.02D;
/*      */         }
/*      */         
/* 1711 */         if (this.positionChanged && c(this.motX, this.motY + 0.6000000238418579D - this.locY + d, this.motZ)) {
/* 1712 */           this.motY = 0.30000001192092896D;
/*      */         }
/* 1714 */       } else if (cP()) {
/* 1715 */         if (this.motY > -0.5D) {
/* 1716 */           this.fallDistance = 1.0F;
/*      */         }
/*      */         
/* 1719 */         Vec3D vec3d = aJ();
/* 1720 */         float f6 = this.pitch * 0.017453292F;
/*      */         
/* 1722 */         double d4 = Math.sqrt(vec3d.x * vec3d.x + vec3d.z * vec3d.z);
/* 1723 */         double d5 = Math.sqrt(this.motX * this.motX + this.motZ * this.motZ);
/* 1724 */         double d3 = vec3d.b();
/* 1725 */         float f7 = MathHelper.cos(f6);
/*      */         
/* 1727 */         f7 = (float)(f7 * f7 * Math.min(1.0D, d3 / 0.4D));
/* 1728 */         this.motY += -0.08D + f7 * 0.06D;
/*      */ 
/*      */         
/* 1731 */         if (this.motY < 0.0D && d4 > 0.0D) {
/* 1732 */           double d = this.motY * -0.1D * f7;
/* 1733 */           this.motY += d;
/* 1734 */           this.motX += vec3d.x * d / d4;
/* 1735 */           this.motZ += vec3d.z * d / d4;
/*      */         } 
/*      */         
/* 1738 */         if (f6 < 0.0F) {
/* 1739 */           double d = d5 * -MathHelper.sin(f6) * 0.04D;
/* 1740 */           this.motY += d * 3.2D;
/* 1741 */           this.motX -= vec3d.x * d / d4;
/* 1742 */           this.motZ -= vec3d.z * d / d4;
/*      */         } 
/*      */         
/* 1745 */         if (d4 > 0.0D) {
/* 1746 */           this.motX += (vec3d.x / d4 * d5 - this.motX) * 0.1D;
/* 1747 */           this.motZ += (vec3d.z / d4 * d5 - this.motZ) * 0.1D;
/*      */         } 
/*      */         
/* 1750 */         this.motX *= 0.9900000095367432D;
/* 1751 */         this.motY *= 0.9800000190734863D;
/* 1752 */         this.motZ *= 0.9900000095367432D;
/* 1753 */         move(EnumMoveType.SELF, this.motX, this.motY, this.motZ);
/* 1754 */         if (this.positionChanged && !this.world.isClientSide) {
/* 1755 */           double d6 = Math.sqrt(this.motX * this.motX + this.motZ * this.motZ);
/* 1756 */           double d7 = d5 - d6;
/* 1757 */           float f8 = (float)(d7 * 10.0D - 3.0D);
/*      */           
/* 1759 */           if (f8 > 0.0F) {
/* 1760 */             a(e((int)f8), 1.0F, 1.0F);
/* 1761 */             damageEntity(DamageSource.FLY_INTO_WALL, f8);
/*      */           } 
/*      */         } 
/*      */         
/* 1765 */         if (this.onGround && !this.world.isClientSide && 
/* 1766 */           getFlag(7) && !CraftEventFactory.callToggleGlideEvent(this, false).isCancelled()) {
/* 1767 */           setFlag(7, false);
/*      */         }
/*      */       } else {
/* 1770 */         float f3, f9 = 0.91F;
/* 1771 */         BlockPosition.PooledBlockPosition blockposition_pooledblockposition = BlockPosition.PooledBlockPosition.d(this.locX, (getBoundingBox()).b - 1.0D, this.locZ);
/*      */         
/* 1773 */         if (this.onGround) {
/* 1774 */           f9 = (this.world.getType(blockposition_pooledblockposition).getBlock()).frictionFactor * 0.91F;
/*      */         }
/*      */         
/* 1777 */         float f4 = 0.16277136F / f9 * f9 * f9;
/* 1778 */         if (this.onGround) {
/* 1779 */           f3 = cy() * f4;
/*      */         } else {
/* 1781 */           f3 = this.aR;
/*      */         } 
/*      */         
/* 1784 */         b(f, f1, f2, f3);
/* 1785 */         f9 = 0.91F;
/* 1786 */         if (this.onGround) {
/* 1787 */           f9 = (this.world.getType(blockposition_pooledblockposition.e(this.locX, (getBoundingBox()).b - 1.0D, this.locZ)).getBlock()).frictionFactor * 0.91F;
/*      */         }
/*      */         
/* 1790 */         if (m_()) {
/* 1791 */           float f5 = 0.15F;
/* 1792 */           this.motX = MathHelper.a(this.motX, -0.15000000596046448D, 0.15000000596046448D);
/* 1793 */           this.motZ = MathHelper.a(this.motZ, -0.15000000596046448D, 0.15000000596046448D);
/* 1794 */           this.fallDistance = 0.0F;
/* 1795 */           if (this.motY < -0.15D) {
/* 1796 */             this.motY = -0.15D;
/*      */           }
/*      */           
/* 1799 */           boolean flag = (isSneaking() && this instanceof EntityHuman);
/*      */           
/* 1801 */           if (flag && this.motY < 0.0D) {
/* 1802 */             this.motY = 0.0D;
/*      */           }
/*      */         } 
/*      */         
/* 1806 */         move(EnumMoveType.SELF, this.motX, this.motY, this.motZ);
/* 1807 */         if (this.positionChanged && m_()) {
/* 1808 */           this.motY = 0.2D;
/*      */         }
/*      */         
/* 1811 */         if (hasEffect(MobEffects.LEVITATION)) {
/* 1812 */           this.motY += (0.05D * (getEffect(MobEffects.LEVITATION).getAmplifier() + 1) - this.motY) * 0.2D;
/*      */         } else {
/* 1814 */           blockposition_pooledblockposition.e(this.locX, 0.0D, this.locZ);
/* 1815 */           if (this.world.isClientSide && (!this.world.isLoaded(blockposition_pooledblockposition) || !this.world.getChunkAtWorldCoords(blockposition_pooledblockposition).p())) {
/* 1816 */             if (this.locY > 0.0D) {
/* 1817 */               this.motY = -0.1D;
/*      */             } else {
/* 1819 */               this.motY = 0.0D;
/*      */             } 
/* 1821 */           } else if (!isNoGravity()) {
/* 1822 */             this.motY -= 0.08D;
/*      */           } 
/*      */         } 
/*      */         
/* 1826 */         this.motY *= 0.9800000190734863D;
/* 1827 */         this.motX *= f9;
/* 1828 */         this.motZ *= f9;
/* 1829 */         blockposition_pooledblockposition.t();
/*      */       } 
/*      */     }
/*      */     
/* 1833 */     this.aF = this.aG;
/* 1834 */     double d2 = this.locX - this.lastX;
/* 1835 */     double d0 = this.locZ - this.lastZ;
/* 1836 */     double d1 = (this instanceof EntityBird) ? (this.locY - this.lastY) : 0.0D;
/* 1837 */     float f10 = MathHelper.sqrt(d2 * d2 + d1 * d1 + d0 * d0) * 4.0F;
/*      */     
/* 1839 */     if (f10 > 1.0F) {
/* 1840 */       f10 = 1.0F;
/*      */     }
/*      */     
/* 1843 */     this.aG += (f10 - this.aG) * 0.4F;
/* 1844 */     this.aH += this.aG;
/*      */   }
/*      */   
/*      */   public float cy() {
/* 1848 */     return this.bC;
/*      */   }
/*      */   
/*      */   public void k(float f) {
/* 1852 */     this.bC = f;
/*      */   }
/*      */   
/*      */   public boolean B(Entity entity) {
/* 1856 */     z(entity);
/* 1857 */     return false;
/*      */   }
/*      */   
/*      */   public boolean isSleeping() {
/* 1861 */     return false;
/*      */   }
/*      */   
/*      */   public void B_() {
/* 1865 */     SpigotTimings.timerEntityBaseTick.startTiming();
/* 1866 */     super.B_();
/* 1867 */     cI();
/* 1868 */     if (!this.world.isClientSide) {
/* 1869 */       int i = getArrowCount();
/*      */       
/* 1871 */       if (i > 0) {
/* 1872 */         if (this.ax <= 0) {
/* 1873 */           this.ax = 20 * (30 - i);
/*      */         }
/*      */         
/* 1876 */         this.ax--;
/* 1877 */         if (this.ax <= 0) {
/* 1878 */           setArrowCount(i - 1);
/*      */         }
/*      */       } 
/*      */       
/* 1882 */       EnumItemSlot[] aenumitemslot = EnumItemSlot.values();
/* 1883 */       int j = aenumitemslot.length;
/*      */       
/* 1885 */       for (int k = 0; k < j; ) {
/* 1886 */         ItemStack itemstack; EnumItemSlot enumitemslot = aenumitemslot[k];
/*      */ 
/*      */         
/* 1889 */         switch (enumitemslot.a()) {
/*      */           case HAND:
/* 1891 */             itemstack = this.bv.get(enumitemslot.b());
/*      */             break;
/*      */           
/*      */           case null:
/* 1895 */             itemstack = this.bw.get(enumitemslot.b());
/*      */             break;
/*      */           
/*      */           default:
/*      */             k++;
/*      */             continue;
/*      */         } 
/* 1902 */         ItemStack itemstack1 = getEquipment(enumitemslot);
/*      */         
/* 1904 */         if (!ItemStack.matches(itemstack1, itemstack)) {
/* 1905 */           ((WorldServer)this.world).getTracker().a(this, new PacketPlayOutEntityEquipment(getId(), enumitemslot, itemstack1));
/* 1906 */           if (!itemstack.isEmpty()) {
/* 1907 */             getAttributeMap().a(itemstack.a(enumitemslot));
/*      */           }
/*      */           
/* 1910 */           if (!itemstack1.isEmpty()) {
/* 1911 */             getAttributeMap().b(itemstack1.a(enumitemslot));
/*      */           }
/*      */           
/* 1914 */           switch (enumitemslot.a()) {
/*      */             case HAND:
/* 1916 */               this.bv.set(enumitemslot.b(), itemstack1.isEmpty() ? ItemStack.a : itemstack1.cloneItemStack());
/*      */ 
/*      */             
/*      */             case null:
/* 1920 */               this.bw.set(enumitemslot.b(), itemstack1.isEmpty() ? ItemStack.a : itemstack1.cloneItemStack());
/*      */           } 
/*      */         
/*      */         } 
/*      */       } 
/* 1925 */       if (this.ticksLived % 20 == 0) {
/* 1926 */         getCombatTracker().g();
/*      */       }
/*      */       
/* 1929 */       if (!this.glowing) {
/* 1930 */         boolean flag = hasEffect(MobEffects.GLOWING);
/*      */         
/* 1932 */         if (getFlag(6) != flag) {
/* 1933 */           setFlag(6, flag);
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 1938 */     SpigotTimings.timerEntityBaseTick.stopTiming();
/* 1939 */     n();
/* 1940 */     SpigotTimings.timerEntityTickRest.startTiming();
/* 1941 */     double d0 = this.locX - this.lastX;
/* 1942 */     double d1 = this.locZ - this.lastZ;
/* 1943 */     float f = (float)(d0 * d0 + d1 * d1);
/* 1944 */     float f1 = this.aN;
/* 1945 */     float f2 = 0.0F;
/*      */     
/* 1947 */     this.aW = this.aX;
/* 1948 */     float f3 = 0.0F;
/*      */     
/* 1950 */     if (f > 0.0025000002F) {
/* 1951 */       f3 = 1.0F;
/* 1952 */       f2 = (float)Math.sqrt(f) * 3.0F;
/* 1953 */       float f4 = (float)MathHelper.c(d1, d0) * 57.295776F - 90.0F;
/* 1954 */       float f5 = MathHelper.e(MathHelper.g(this.yaw) - f4);
/*      */       
/* 1956 */       if (95.0F < f5 && f5 < 265.0F) {
/* 1957 */         float f6 = f4 - 180.0F;
/*      */       } else {
/* 1959 */         f1 = f4;
/*      */       } 
/*      */     } 
/*      */     
/* 1963 */     if (this.aD > 0.0F) {
/* 1964 */       f1 = this.yaw;
/*      */     }
/*      */     
/* 1967 */     if (!this.onGround) {
/* 1968 */       f3 = 0.0F;
/*      */     }
/*      */     
/* 1971 */     this.aX += (f3 - this.aX) * 0.3F;
/* 1972 */     this.world.methodProfiler.a("headTurn");
/* 1973 */     f2 = g(f1, f2);
/* 1974 */     this.world.methodProfiler.b();
/* 1975 */     this.world.methodProfiler.a("rangeChecks");
/*      */     
/* 1977 */     while (this.yaw - this.lastYaw < -180.0F) {
/* 1978 */       this.lastYaw -= 360.0F;
/*      */     }
/*      */     
/* 1981 */     while (this.yaw - this.lastYaw >= 180.0F) {
/* 1982 */       this.lastYaw += 360.0F;
/*      */     }
/*      */     
/* 1985 */     while (this.aN - this.aO < -180.0F) {
/* 1986 */       this.aO -= 360.0F;
/*      */     }
/*      */     
/* 1989 */     while (this.aN - this.aO >= 180.0F) {
/* 1990 */       this.aO += 360.0F;
/*      */     }
/*      */     
/* 1993 */     while (this.pitch - this.lastPitch < -180.0F) {
/* 1994 */       this.lastPitch -= 360.0F;
/*      */     }
/*      */     
/* 1997 */     while (this.pitch - this.lastPitch >= 180.0F) {
/* 1998 */       this.lastPitch += 360.0F;
/*      */     }
/*      */     
/* 2001 */     while (this.aP - this.aQ < -180.0F) {
/* 2002 */       this.aQ -= 360.0F;
/*      */     }
/*      */     
/* 2005 */     while (this.aP - this.aQ >= 180.0F) {
/* 2006 */       this.aQ += 360.0F;
/*      */     }
/*      */     
/* 2009 */     this.world.methodProfiler.b();
/* 2010 */     this.aY += f2;
/* 2011 */     if (cP()) {
/* 2012 */       this.bq++;
/*      */     } else {
/* 2014 */       this.bq = 0;
/*      */     } 
/*      */     
/* 2017 */     SpigotTimings.timerEntityTickRest.stopTiming();
/*      */   }
/*      */   
/*      */   protected float g(float f, float f1) {
/* 2021 */     float f2 = MathHelper.g(f - this.aN);
/*      */     
/* 2023 */     this.aN += f2 * 0.3F;
/* 2024 */     float f3 = MathHelper.g(this.yaw - this.aN);
/* 2025 */     boolean flag = !(f3 >= -90.0F && f3 < 90.0F);
/*      */     
/* 2027 */     if (f3 < -75.0F) {
/* 2028 */       f3 = -75.0F;
/*      */     }
/*      */     
/* 2031 */     if (f3 >= 75.0F) {
/* 2032 */       f3 = 75.0F;
/*      */     }
/*      */     
/* 2035 */     this.aN = this.yaw - f3;
/* 2036 */     if (f3 * f3 > 2500.0F) {
/* 2037 */       this.aN += f3 * 0.2F;
/*      */     }
/*      */     
/* 2040 */     if (flag) {
/* 2041 */       f1 *= -1.0F;
/*      */     }
/*      */     
/* 2044 */     return f1;
/*      */   }
/*      */   
/*      */   public void n() {
/* 2048 */     if (this.bD > 0) {
/* 2049 */       this.bD--;
/*      */     }
/*      */     
/* 2052 */     if (this.bi > 0 && !bI()) {
/* 2053 */       double d0 = this.locX + (this.bj - this.locX) / this.bi;
/* 2054 */       double d1 = this.locY + (this.bk - this.locY) / this.bi;
/* 2055 */       double d2 = this.locZ + (this.bl - this.locZ) / this.bi;
/* 2056 */       double d3 = MathHelper.g(this.bm - this.yaw);
/*      */       
/* 2058 */       this.yaw = (float)(this.yaw + d3 / this.bi);
/* 2059 */       this.pitch = (float)(this.pitch + (this.bn - this.pitch) / this.bi);
/* 2060 */       this.bi--;
/* 2061 */       setPosition(d0, d1, d2);
/* 2062 */       setYawPitch(this.yaw, this.pitch);
/* 2063 */     } else if (!cC()) {
/* 2064 */       this.motX *= 0.98D;
/* 2065 */       this.motY *= 0.98D;
/* 2066 */       this.motZ *= 0.98D;
/*      */     } 
/*      */     
/* 2069 */     if (Math.abs(this.motX) < 0.003D) {
/* 2070 */       this.motX = 0.0D;
/*      */     }
/*      */     
/* 2073 */     if (Math.abs(this.motY) < 0.003D) {
/* 2074 */       this.motY = 0.0D;
/*      */     }
/*      */     
/* 2077 */     if (Math.abs(this.motZ) < 0.003D) {
/* 2078 */       this.motZ = 0.0D;
/*      */     }
/*      */     
/* 2081 */     this.world.methodProfiler.a("ai");
/* 2082 */     SpigotTimings.timerEntityAI.startTiming();
/* 2083 */     if (isFrozen()) {
/* 2084 */       this.bd = false;
/* 2085 */       this.be = 0.0F;
/* 2086 */       this.bg = 0.0F;
/* 2087 */       this.bh = 0.0F;
/* 2088 */     } else if (cC()) {
/* 2089 */       this.world.methodProfiler.a("newAi");
/* 2090 */       doTick();
/* 2091 */       this.world.methodProfiler.b();
/*      */     } 
/* 2093 */     SpigotTimings.timerEntityAI.stopTiming();
/*      */     
/* 2095 */     this.world.methodProfiler.b();
/* 2096 */     this.world.methodProfiler.a("jump");
/* 2097 */     if (this.bd) {
/* 2098 */       if (isInWater()) {
/* 2099 */         cv();
/* 2100 */       } else if (au()) {
/* 2101 */         cw();
/* 2102 */       } else if (this.onGround && this.bD == 0) {
/* 2103 */         cu();
/* 2104 */         this.bD = 10;
/*      */       } 
/*      */     } else {
/* 2107 */       this.bD = 0;
/*      */     } 
/*      */     
/* 2110 */     this.world.methodProfiler.b();
/* 2111 */     this.world.methodProfiler.a("travel");
/* 2112 */     this.be *= 0.98F;
/* 2113 */     this.bg *= 0.98F;
/* 2114 */     this.bh *= 0.9F;
/* 2115 */     r();
/* 2116 */     SpigotTimings.timerEntityAIMove.startTiming();
/* 2117 */     a(this.be, this.bf, this.bg);
/* 2118 */     SpigotTimings.timerEntityAIMove.stopTiming();
/* 2119 */     this.world.methodProfiler.b();
/* 2120 */     this.world.methodProfiler.a("push");
/* 2121 */     SpigotTimings.timerEntityAICollision.startTiming();
/* 2122 */     cB();
/* 2123 */     SpigotTimings.timerEntityAICollision.stopTiming();
/* 2124 */     this.world.methodProfiler.b();
/*      */   }
/*      */   
/*      */   private void r() {
/* 2128 */     boolean flag = getFlag(7);
/*      */     
/* 2130 */     if (flag && !this.onGround && !isPassenger()) {
/* 2131 */       ItemStack itemstack = getEquipment(EnumItemSlot.CHEST);
/*      */       
/* 2133 */       if (itemstack.getItem() == Items.cS && ItemElytra.d(itemstack)) {
/* 2134 */         flag = true;
/* 2135 */         if (!this.world.isClientSide && (this.bq + 1) % 20 == 0) {
/* 2136 */           itemstack.damage(1, this);
/*      */         }
/*      */       } else {
/* 2139 */         flag = false;
/*      */       } 
/*      */     } else {
/* 2142 */       flag = false;
/*      */     } 
/*      */     
/* 2145 */     if (!this.world.isClientSide && 
/* 2146 */       flag != getFlag(7) && !CraftEventFactory.callToggleGlideEvent(this, flag).isCancelled()) {
/* 2147 */       setFlag(7, flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected void doTick() {}
/*      */   
/*      */   protected void cB() {
/* 2155 */     List<Entity> list = this.world.getEntities(this, getBoundingBox(), IEntitySelector.a(this));
/*      */     
/* 2157 */     if (!list.isEmpty()) {
/* 2158 */       int i = this.world.getGameRules().c("maxEntityCramming");
/*      */ 
/*      */       
/* 2161 */       if (i > 0 && list.size() > i - 1 && this.random.nextInt(4) == 0) {
/* 2162 */         int m = 0;
/*      */         
/* 2164 */         for (int k = 0; k < list.size(); k++) {
/* 2165 */           if (!((Entity)list.get(k)).isPassenger()) {
/* 2166 */             m++;
/*      */           }
/*      */         } 
/*      */         
/* 2170 */         if (m > i - 1) {
/* 2171 */           damageEntity(DamageSource.CRAMMING, 6.0F);
/*      */         }
/*      */       } 
/*      */       
/* 2175 */       for (int j = 0; j < list.size(); j++) {
/* 2176 */         Entity entity = list.get(j);
/*      */         
/* 2178 */         C(entity);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void C(Entity entity) {
/* 2185 */     entity.collide(this);
/*      */   }
/*      */   
/*      */   public void stopRiding() {
/* 2189 */     Entity entity = bJ();
/*      */     
/* 2191 */     super.stopRiding();
/* 2192 */     if (entity != null && entity != bJ() && !this.world.isClientSide) {
/* 2193 */       A(entity);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void aE() {
/* 2199 */     super.aE();
/* 2200 */     this.aW = this.aX;
/* 2201 */     this.aX = 0.0F;
/* 2202 */     this.fallDistance = 0.0F;
/*      */   }
/*      */   
/*      */   public void l(boolean flag) {
/* 2206 */     this.bd = flag;
/*      */   }
/*      */   
/*      */   public void receive(Entity entity, int i) {
/* 2210 */     if (!entity.dead && !this.world.isClientSide) {
/* 2211 */       EntityTracker entitytracker = ((WorldServer)this.world).getTracker();
/*      */       
/* 2213 */       if (entity instanceof EntityItem || entity instanceof EntityArrow || entity instanceof EntityExperienceOrb) {
/* 2214 */         entitytracker.a(entity, new PacketPlayOutCollect(entity.getId(), getId(), i));
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean hasLineOfSight(Entity entity) {
/* 2221 */     return (this.world.rayTrace(new Vec3D(this.locX, this.locY + getHeadHeight(), this.locZ), new Vec3D(entity.locX, entity.locY + entity.getHeadHeight(), entity.locZ), false, true, false) == null);
/*      */   }
/*      */   
/*      */   public Vec3D e(float f) {
/* 2225 */     if (f == 1.0F) {
/* 2226 */       return f(this.pitch, this.aP);
/*      */     }
/* 2228 */     float f1 = this.lastPitch + (this.pitch - this.lastPitch) * f;
/* 2229 */     float f2 = this.aQ + (this.aP - this.aQ) * f;
/*      */     
/* 2231 */     return f(f1, f2);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean cC() {
/* 2236 */     return !this.world.isClientSide;
/*      */   }
/*      */   
/*      */   public boolean isInteractable() {
/* 2240 */     return (!this.dead && this.collides);
/*      */   }
/*      */   
/*      */   public boolean isCollidable() {
/* 2244 */     return (isAlive() && !m_() && this.collides);
/*      */   }
/*      */   
/*      */   protected void ax() {
/* 2248 */     this.velocityChanged = (this.random.nextDouble() >= getAttributeInstance(GenericAttributes.c).getValue());
/*      */   }
/*      */   
/*      */   public float getHeadRotation() {
/* 2252 */     return this.aP;
/*      */   }
/*      */   
/*      */   public void setHeadRotation(float f) {
/* 2256 */     this.aP = f;
/*      */   }
/*      */   
/*      */   public void h(float f) {
/* 2260 */     this.aN = f;
/*      */   }
/*      */   
/*      */   public float getAbsorptionHearts() {
/* 2264 */     return this.bE;
/*      */   }
/*      */   
/*      */   public void setAbsorptionHearts(float f) {
/* 2268 */     if (f < 0.0F) {
/* 2269 */       f = 0.0F;
/*      */     }
/*      */     
/* 2272 */     this.bE = f;
/*      */   }
/*      */   
/*      */   public void enterCombat() {}
/*      */   
/*      */   public void exitCombat() {}
/*      */   
/*      */   protected void cE() {
/* 2280 */     this.updateEffects = true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isHandRaised() {
/* 2286 */     return ((((Byte)this.datawatcher.<Byte>get(at)).byteValue() & 0x1) > 0);
/*      */   }
/*      */   
/*      */   public EnumHand cH() {
/* 2290 */     return ((((Byte)this.datawatcher.<Byte>get(at)).byteValue() & 0x2) > 0) ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND;
/*      */   }
/*      */   
/*      */   protected void cI() {
/* 2294 */     if (isHandRaised()) {
/* 2295 */       ItemStack itemstack = b(cH());
/*      */       
/* 2297 */       if (itemstack == this.activeItem) {
/* 2298 */         if (cK() <= 25 && cK() % 4 == 0) {
/* 2299 */           b(this.activeItem, 5);
/*      */         }
/*      */         
/* 2302 */         if (--this.bp == 0 && !this.world.isClientSide) {
/* 2303 */           v();
/*      */         }
/*      */       } else {
/* 2306 */         cN();
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void c(EnumHand enumhand) {
/* 2313 */     ItemStack itemstack = b(enumhand);
/*      */     
/* 2315 */     if (!itemstack.isEmpty() && !isHandRaised()) {
/* 2316 */       this.activeItem = itemstack;
/* 2317 */       this.bp = itemstack.m();
/* 2318 */       if (!this.world.isClientSide) {
/* 2319 */         int i = 1;
/*      */         
/* 2321 */         if (enumhand == EnumHand.OFF_HAND) {
/* 2322 */           i |= 0x2;
/*      */         }
/*      */         
/* 2325 */         this.datawatcher.set(at, Byte.valueOf((byte)i));
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void a(DataWatcherObject<?> datawatcherobject) {
/* 2332 */     super.a(datawatcherobject);
/* 2333 */     if (at.equals(datawatcherobject) && this.world.isClientSide) {
/* 2334 */       if (isHandRaised() && this.activeItem.isEmpty()) {
/* 2335 */         this.activeItem = b(cH());
/* 2336 */         if (!this.activeItem.isEmpty()) {
/* 2337 */           this.bp = this.activeItem.m();
/*      */         }
/* 2339 */       } else if (!isHandRaised() && !this.activeItem.isEmpty()) {
/* 2340 */         this.activeItem = ItemStack.a;
/* 2341 */         this.bp = 0;
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected void b(ItemStack itemstack, int i) {
/* 2348 */     if (!itemstack.isEmpty() && isHandRaised()) {
/* 2349 */       if (itemstack.n() == EnumAnimation.DRINK) {
/* 2350 */         a(SoundEffects.bT, 0.5F, this.world.random.nextFloat() * 0.1F + 0.9F);
/*      */       }
/*      */       
/* 2353 */       if (itemstack.n() == EnumAnimation.EAT) {
/* 2354 */         for (int j = 0; j < i; j++) {
/* 2355 */           Vec3D vec3d = new Vec3D((this.random.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D);
/*      */           
/* 2357 */           vec3d = vec3d.a(-this.pitch * 0.017453292F);
/* 2358 */           vec3d = vec3d.b(-this.yaw * 0.017453292F);
/* 2359 */           double d0 = -this.random.nextFloat() * 0.6D - 0.3D;
/* 2360 */           Vec3D vec3d1 = new Vec3D((this.random.nextFloat() - 0.5D) * 0.3D, d0, 0.6D);
/*      */           
/* 2362 */           vec3d1 = vec3d1.a(-this.pitch * 0.017453292F);
/* 2363 */           vec3d1 = vec3d1.b(-this.yaw * 0.017453292F);
/* 2364 */           vec3d1 = vec3d1.add(this.locX, this.locY + getHeadHeight(), this.locZ);
/* 2365 */           if (itemstack.usesData()) {
/* 2366 */             this.world.addParticle(EnumParticle.ITEM_CRACK, vec3d1.x, vec3d1.y, vec3d1.z, vec3d.x, vec3d.y + 0.05D, vec3d.z, new int[] { Item.getId(itemstack.getItem()), itemstack.getData() });
/*      */           } else {
/* 2368 */             this.world.addParticle(EnumParticle.ITEM_CRACK, vec3d1.x, vec3d1.y, vec3d1.z, vec3d.x, vec3d.y + 0.05D, vec3d.z, new int[] { Item.getId(itemstack.getItem()) });
/*      */           } 
/*      */         } 
/*      */         
/* 2372 */         a(SoundEffects.bU, 0.5F + 0.5F * this.random.nextInt(2), (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void v() {
/* 2379 */     if (!this.activeItem.isEmpty() && isHandRaised()) {
/* 2380 */       ItemStack itemstack; b(this.activeItem, 16);
/*      */ 
/*      */       
/* 2383 */       if (this instanceof EntityPlayer) {
/* 2384 */         ItemStack craftItem = CraftItemStack.asBukkitCopy(this.activeItem);
/* 2385 */         PlayerItemConsumeEvent event = new PlayerItemConsumeEvent((Player)getBukkitEntity(), craftItem);
/* 2386 */         this.world.getServer().getPluginManager().callEvent((Event)event);
/*      */         
/* 2388 */         if (event.isCancelled()) {
/*      */           
/* 2390 */           ((EntityPlayer)this).getBukkitEntity().updateInventory();
/* 2391 */           ((EntityPlayer)this).getBukkitEntity().updateScaledHealth();
/*      */           
/*      */           return;
/*      */         } 
/* 2395 */         itemstack = craftItem.equals(event.getItem()) ? this.activeItem.a(this.world, this) : CraftItemStack.asNMSCopy(event.getItem()).a(this.world, this);
/*      */       } else {
/* 2397 */         itemstack = this.activeItem.a(this.world, this);
/*      */       } 
/*      */       
/* 2400 */       a(cH(), itemstack);
/*      */       
/* 2402 */       cN();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public ItemStack cJ() {
/* 2408 */     return this.activeItem;
/*      */   }
/*      */   
/*      */   public int cK() {
/* 2412 */     return this.bp;
/*      */   }
/*      */   
/*      */   public int cL() {
/* 2416 */     return isHandRaised() ? (this.activeItem.m() - cK()) : 0;
/*      */   }
/*      */   
/*      */   public void clearActiveItem() {
/* 2420 */     if (!this.activeItem.isEmpty()) {
/* 2421 */       this.activeItem.a(this.world, this, cK());
/*      */     }
/*      */     
/* 2424 */     cN();
/*      */   }
/*      */   
/*      */   public void cN() {
/* 2428 */     if (!this.world.isClientSide) {
/* 2429 */       this.datawatcher.set(at, Byte.valueOf((byte)0));
/*      */     }
/*      */     
/* 2432 */     this.activeItem = ItemStack.a;
/* 2433 */     this.bp = 0;
/*      */   }
/*      */   
/*      */   public boolean isBlocking() {
/* 2437 */     if (isHandRaised() && !this.activeItem.isEmpty()) {
/* 2438 */       Item item = this.activeItem.getItem();
/*      */       
/* 2440 */       return (item.f(this.activeItem) != EnumAnimation.BLOCK) ? false : ((item.e(this.activeItem) - this.bp >= 5));
/*      */     } 
/* 2442 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean cP() {
/* 2447 */     return getFlag(7);
/*      */   }
/*      */   
/*      */   public boolean j(double d0, double d1, double d2) {
/* 2451 */     double d3 = this.locX;
/* 2452 */     double d4 = this.locY;
/* 2453 */     double d5 = this.locZ;
/*      */     
/* 2455 */     this.locX = d0;
/* 2456 */     this.locY = d1;
/* 2457 */     this.locZ = d2;
/* 2458 */     boolean flag = false;
/* 2459 */     BlockPosition blockposition = new BlockPosition(this);
/* 2460 */     World world = this.world;
/* 2461 */     Random random = getRandom();
/*      */ 
/*      */     
/* 2464 */     if (world.isLoaded(blockposition)) {
/* 2465 */       boolean bool = false;
/*      */       
/* 2467 */       while (!bool && blockposition.getY() > 0) {
/* 2468 */         BlockPosition blockposition1 = blockposition.down();
/* 2469 */         IBlockData iblockdata = world.getType(blockposition1);
/*      */         
/* 2471 */         if (iblockdata.getMaterial().isSolid()) {
/* 2472 */           bool = true; continue;
/*      */         } 
/* 2474 */         this.locY--;
/* 2475 */         blockposition = blockposition1;
/*      */       } 
/*      */ 
/*      */       
/* 2479 */       if (bool) {
/*      */ 
/*      */         
/* 2482 */         EntityTeleportEvent teleport = new EntityTeleportEvent((Entity)getBukkitEntity(), new Location((World)this.world.getWorld(), d3, d4, d5), new Location((World)this.world.getWorld(), this.locX, this.locY, this.locZ));
/* 2483 */         this.world.getServer().getPluginManager().callEvent((Event)teleport);
/* 2484 */         if (!teleport.isCancelled()) {
/* 2485 */           Location to = teleport.getTo();
/* 2486 */           enderTeleportTo(to.getX(), to.getY(), to.getZ());
/* 2487 */           if (world.getCubes(this, getBoundingBox()).isEmpty() && !world.containsLiquid(getBoundingBox())) {
/* 2488 */             flag = true;
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 2495 */     if (!flag) {
/* 2496 */       enderTeleportTo(d3, d4, d5);
/* 2497 */       return false;
/*      */     } 
/* 2499 */     boolean flag1 = true;
/*      */     
/* 2501 */     for (int i = 0; i < 128; i++) {
/* 2502 */       double d6 = i / 127.0D;
/* 2503 */       float f = (random.nextFloat() - 0.5F) * 0.2F;
/* 2504 */       float f1 = (random.nextFloat() - 0.5F) * 0.2F;
/* 2505 */       float f2 = (random.nextFloat() - 0.5F) * 0.2F;
/* 2506 */       double d7 = d3 + (this.locX - d3) * d6 + (random.nextDouble() - 0.5D) * this.width * 2.0D;
/* 2507 */       double d8 = d4 + (this.locY - d4) * d6 + random.nextDouble() * this.length;
/* 2508 */       double d9 = d5 + (this.locZ - d5) * d6 + (random.nextDouble() - 0.5D) * this.width * 2.0D;
/*      */       
/* 2510 */       world.addParticle(EnumParticle.PORTAL, d7, d8, d9, f, f1, f2, new int[0]);
/*      */     } 
/*      */     
/* 2513 */     if (this instanceof EntityCreature) {
/* 2514 */       ((EntityCreature)this).getNavigation().p();
/*      */     }
/*      */     
/* 2517 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean cR() {
/* 2522 */     return true;
/*      */   }
/*      */   
/*      */   public boolean cS() {
/* 2526 */     return true;
/*      */   }
/*      */   
/*      */   public abstract Iterable<ItemStack> getArmorItems();
/*      */   
/*      */   public abstract ItemStack getEquipment(EnumItemSlot paramEnumItemSlot);
/*      */   
/*      */   public abstract void setSlot(EnumItemSlot paramEnumItemSlot, ItemStack paramItemStack);
/*      */   
/*      */   public abstract EnumMainHand getMainHand();
/*      */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityLiving.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */