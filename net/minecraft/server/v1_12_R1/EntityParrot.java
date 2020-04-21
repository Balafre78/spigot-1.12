/*     */ package net.minecraft.server.v1_12_R1;
/*     */ import com.google.common.base.Predicate;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.libs.it.unimi.dsi.fastutil.ints.Int2ObjectMap;
/*     */ import org.bukkit.craftbukkit.libs.it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*     */ 
/*     */ public class EntityParrot extends EntityPerchable implements EntityBird {
/*  15 */   private static final DataWatcherObject<Integer> bG = DataWatcher.a((Class)EntityParrot.class, DataWatcherRegistry.b);
/*  16 */   private static final Predicate<EntityInsentient> bH = new Predicate() {
/*     */       public boolean a(@Nullable EntityInsentient entityinsentient) {
/*  18 */         return (entityinsentient != null && EntityParrot.bK.containsKey(EntityTypes.b.a(entityinsentient.getClass())));
/*     */       }
/*     */       
/*     */       public boolean apply(@Nullable Object object) {
/*  22 */         return a((EntityInsentient)object);
/*     */       }
/*     */     };
/*  25 */   private static final Item bI = Items.COOKIE;
/*  26 */   private static final Set<Item> bJ = Sets.newHashSet((Object[])new Item[] { Items.WHEAT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS, Items.BEETROOT_SEEDS });
/*  27 */   private static final Int2ObjectMap<SoundEffect> bK = (Int2ObjectMap<SoundEffect>)new Int2ObjectOpenHashMap(32);
/*     */   public float bB;
/*     */   public float bC;
/*     */   public float bD;
/*     */   public float bE;
/*  32 */   public float bF = 1.0F;
/*     */   private boolean bL;
/*     */   private BlockPosition bM;
/*     */   
/*     */   public EntityParrot(World world) {
/*  37 */     super(world);
/*  38 */     setSize(0.5F, 0.9F);
/*  39 */     this.moveController = new ControllerMoveFlying(this);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public GroupDataEntity prepare(DifficultyDamageScaler difficultydamagescaler, @Nullable GroupDataEntity groupdataentity) {
/*  44 */     setVariant(this.random.nextInt(5));
/*  45 */     return super.prepare(difficultydamagescaler, groupdataentity);
/*     */   }
/*     */   
/*     */   protected void r() {
/*  49 */     this.goalSit = new PathfinderGoalSit(this);
/*  50 */     this.goalSelector.a(0, new PathfinderGoalPanic(this, 1.25D));
/*  51 */     this.goalSelector.a(0, new PathfinderGoalFloat(this));
/*  52 */     this.goalSelector.a(1, new PathfinderGoalLookAtPlayer(this, (Class)EntityHuman.class, 8.0F));
/*  53 */     this.goalSelector.a(2, this.goalSit);
/*  54 */     this.goalSelector.a(2, new PathfinderGoalFollowOwnerParrot(this, 1.0D, 5.0F, 1.0F));
/*  55 */     this.goalSelector.a(2, new PathfinderGoalRandomFly(this, 1.0D));
/*  56 */     this.goalSelector.a(3, new PathfinderGoalPerch(this));
/*  57 */     this.goalSelector.a(3, new PathfinderGoalFollowEntity(this, 1.0D, 3.0F, 7.0F));
/*     */   }
/*     */   
/*     */   protected void initAttributes() {
/*  61 */     super.initAttributes();
/*  62 */     getAttributeMap().b(GenericAttributes.e);
/*  63 */     getAttributeInstance(GenericAttributes.maxHealth).setValue(6.0D);
/*  64 */     getAttributeInstance(GenericAttributes.e).setValue(0.4000000059604645D);
/*  65 */     getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.20000000298023224D);
/*     */   }
/*     */   
/*     */   protected NavigationAbstract b(World world) {
/*  69 */     NavigationFlying navigationflying = new NavigationFlying(this, world);
/*     */     
/*  71 */     navigationflying.a(false);
/*  72 */     navigationflying.c(true);
/*  73 */     navigationflying.b(true);
/*  74 */     return navigationflying;
/*     */   }
/*     */   
/*     */   public float getHeadHeight() {
/*  78 */     return this.length * 0.6F;
/*     */   }
/*     */   
/*     */   public void n() {
/*  82 */     b(this.world, this);
/*  83 */     if (this.bM == null || this.bM.distanceSquared(this.locX, this.locY, this.locZ) > 12.0D || this.world.getType(this.bM).getBlock() != Blocks.JUKEBOX) {
/*  84 */       this.bL = false;
/*  85 */       this.bM = null;
/*     */     } 
/*     */     
/*  88 */     super.n();
/*  89 */     dx();
/*     */   }
/*     */   
/*     */   private void dx() {
/*  93 */     this.bE = this.bB;
/*  94 */     this.bD = this.bC;
/*  95 */     this.bC = (float)(this.bC + (this.onGround ? -1 : 4) * 0.3D);
/*  96 */     this.bC = MathHelper.a(this.bC, 0.0F, 1.0F);
/*  97 */     if (!this.onGround && this.bF < 1.0F) {
/*  98 */       this.bF = 1.0F;
/*     */     }
/*     */     
/* 101 */     this.bF = (float)(this.bF * 0.9D);
/* 102 */     if (!this.onGround && this.motY < 0.0D) {
/* 103 */       this.motY *= 0.6D;
/*     */     }
/*     */     
/* 106 */     this.bB += this.bF * 2.0F;
/*     */   }
/*     */   
/*     */   private static boolean b(World world, Entity entity) {
/* 110 */     if (!entity.isSilent() && world.random.nextInt(50) == 0) {
/* 111 */       List<EntityInsentient> list = world.a(EntityInsentient.class, entity.getBoundingBox().g(20.0D), bH);
/*     */       
/* 113 */       if (!list.isEmpty()) {
/* 114 */         EntityInsentient entityinsentient = list.get(world.random.nextInt(list.size()));
/*     */         
/* 116 */         if (!entityinsentient.isSilent()) {
/* 117 */           SoundEffect soundeffect = g(EntityTypes.b.a(entityinsentient.getClass()));
/*     */           
/* 119 */           world.a((EntityHuman)null, entity.locX, entity.locY, entity.locZ, soundeffect, entity.bK(), 0.7F, b(world.random));
/* 120 */           return true;
/*     */         } 
/*     */       } 
/*     */       
/* 124 */       return false;
/*     */     } 
/* 126 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(EntityHuman entityhuman, EnumHand enumhand) {
/* 131 */     ItemStack itemstack = entityhuman.b(enumhand);
/*     */     
/* 133 */     if (!isTamed() && bJ.contains(itemstack.getItem())) {
/* 134 */       if (!entityhuman.abilities.canInstantlyBuild) {
/* 135 */         itemstack.subtract(1);
/*     */       }
/*     */       
/* 138 */       if (!isSilent()) {
/* 139 */         this.world.a((EntityHuman)null, this.locX, this.locY, this.locZ, SoundEffects.eJ, bK(), 1.0F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
/*     */       }
/*     */       
/* 142 */       if (!this.world.isClientSide) {
/* 143 */         if (this.random.nextInt(10) == 0 && !CraftEventFactory.callEntityTameEvent(this, entityhuman).isCancelled()) {
/* 144 */           c(entityhuman);
/* 145 */           p(true);
/* 146 */           this.world.broadcastEntityEffect(this, (byte)7);
/*     */         } else {
/* 148 */           p(false);
/* 149 */           this.world.broadcastEntityEffect(this, (byte)6);
/*     */         } 
/*     */       }
/*     */       
/* 153 */       return true;
/* 154 */     }  if (itemstack.getItem() == bI) {
/* 155 */       if (!entityhuman.abilities.canInstantlyBuild) {
/* 156 */         itemstack.subtract(1);
/*     */       }
/*     */       
/* 159 */       addEffect(new MobEffect(MobEffects.POISON, 900));
/* 160 */       if (entityhuman.z() || !be()) {
/* 161 */         damageEntity(DamageSource.playerAttack(entityhuman), Float.MAX_VALUE);
/*     */       }
/*     */       
/* 164 */       return true;
/*     */     } 
/* 166 */     if (!this.world.isClientSide && !a() && isTamed() && e(entityhuman)) {
/* 167 */       this.goalSit.setSitting(!isSitting());
/*     */     }
/*     */     
/* 170 */     return super.a(entityhuman, enumhand);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean e(ItemStack itemstack) {
/* 175 */     return false;
/*     */   }
/*     */   
/*     */   public boolean P() {
/* 179 */     int i = MathHelper.floor(this.locX);
/* 180 */     int j = MathHelper.floor((getBoundingBox()).b);
/* 181 */     int k = MathHelper.floor(this.locZ);
/* 182 */     BlockPosition blockposition = new BlockPosition(i, j, k);
/* 183 */     Block block = this.world.getType(blockposition.down()).getBlock();
/*     */     
/* 185 */     return !(!(block instanceof BlockLeaves) && block != Blocks.GRASS && !(block instanceof BlockLogAbstract) && (block != Blocks.AIR || this.world.j(blockposition) <= 8 || !super.P()));
/*     */   }
/*     */   
/*     */   public void e(float f, float f1) {}
/*     */   
/*     */   protected void a(double d0, boolean flag, IBlockData iblockdata, BlockPosition blockposition) {}
/*     */   
/*     */   public boolean mate(EntityAnimal entityanimal) {
/* 193 */     return false;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public EntityAgeable createChild(EntityAgeable entityageable) {
/* 198 */     return null;
/*     */   }
/*     */   
/*     */   public static void a(World world, Entity entity) {
/* 202 */     if (!entity.isSilent() && !b(world, entity) && world.random.nextInt(200) == 0) {
/* 203 */       world.a((EntityHuman)null, entity.locX, entity.locY, entity.locZ, a(world.random), entity.bK(), 1.0F, b(world.random));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean B(Entity entity) {
/* 209 */     return entity.damageEntity(DamageSource.mobAttack(this), 3.0F);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public SoundEffect F() {
/* 214 */     return a(this.random);
/*     */   }
/*     */   
/*     */   private static SoundEffect a(Random random) {
/* 218 */     if (random.nextInt(1000) == 0) {
/* 219 */       ArrayList<Integer> arraylist = new ArrayList((Collection<?>)bK.keySet());
/*     */       
/* 221 */       return g(((Integer)arraylist.get(random.nextInt(arraylist.size()))).intValue());
/*     */     } 
/* 223 */     return SoundEffects.eH;
/*     */   }
/*     */ 
/*     */   
/*     */   public static SoundEffect g(int i) {
/* 228 */     return bK.containsKey(i) ? (SoundEffect)bK.get(i) : SoundEffects.eH;
/*     */   }
/*     */   
/*     */   protected SoundEffect d(DamageSource damagesource) {
/* 232 */     return SoundEffects.eL;
/*     */   }
/*     */   
/*     */   protected SoundEffect cf() {
/* 236 */     return SoundEffects.eI;
/*     */   }
/*     */   
/*     */   protected void a(BlockPosition blockposition, Block block) {
/* 240 */     a(SoundEffects.fn, 0.15F, 1.0F);
/*     */   }
/*     */   
/*     */   protected float d(float f) {
/* 244 */     a(SoundEffects.eK, 0.15F, 1.0F);
/* 245 */     return f + this.bC / 2.0F;
/*     */   }
/*     */   
/*     */   protected boolean ah() {
/* 249 */     return true;
/*     */   }
/*     */   
/*     */   protected float cr() {
/* 253 */     return b(this.random);
/*     */   }
/*     */   
/*     */   private static float b(Random random) {
/* 257 */     return (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F;
/*     */   }
/*     */   
/*     */   public SoundCategory bK() {
/* 261 */     return SoundCategory.NEUTRAL;
/*     */   }
/*     */   
/*     */   public boolean isCollidable() {
/* 265 */     return true;
/*     */   }
/*     */   
/*     */   protected void C(Entity entity) {
/* 269 */     if (!(entity instanceof EntityHuman)) {
/* 270 */       super.C(entity);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean damageEntity(DamageSource damagesource, float f) {
/* 275 */     if (isInvulnerable(damagesource)) {
/* 276 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 283 */     return super.damageEntity(damagesource, f);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getVariant() {
/* 288 */     return MathHelper.clamp(((Integer)this.datawatcher.<Integer>get(bG)).intValue(), 0, 4);
/*     */   }
/*     */   
/*     */   public void setVariant(int i) {
/* 292 */     this.datawatcher.set(bG, Integer.valueOf(i));
/*     */   }
/*     */   
/*     */   protected void i() {
/* 296 */     super.i();
/* 297 */     this.datawatcher.register(bG, Integer.valueOf(0));
/*     */   }
/*     */   
/*     */   public void b(NBTTagCompound nbttagcompound) {
/* 301 */     super.b(nbttagcompound);
/* 302 */     nbttagcompound.setInt("Variant", getVariant());
/*     */   }
/*     */   
/*     */   public void a(NBTTagCompound nbttagcompound) {
/* 306 */     super.a(nbttagcompound);
/* 307 */     setVariant(nbttagcompound.getInt("Variant"));
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   protected MinecraftKey J() {
/* 312 */     return LootTables.ax;
/*     */   }
/*     */   
/*     */   public boolean a() {
/* 316 */     return !this.onGround;
/*     */   }
/*     */ 
/*     */   
/*     */   static {
/* 321 */     bK.put(EntityTypes.b.a(EntityBlaze.class), SoundEffects.eM);
/* 322 */     bK.put(EntityTypes.b.a(EntityCaveSpider.class), SoundEffects.fc);
/* 323 */     bK.put(EntityTypes.b.a(EntityCreeper.class), SoundEffects.eN);
/* 324 */     bK.put(EntityTypes.b.a(EntityGuardianElder.class), SoundEffects.eO);
/* 325 */     bK.put(EntityTypes.b.a(EntityEnderDragon.class), SoundEffects.eP);
/* 326 */     bK.put(EntityTypes.b.a(EntityEnderman.class), SoundEffects.eQ);
/* 327 */     bK.put(EntityTypes.b.a(EntityEndermite.class), SoundEffects.eR);
/* 328 */     bK.put(EntityTypes.b.a(EntityEvoker.class), SoundEffects.eS);
/* 329 */     bK.put(EntityTypes.b.a(EntityGhast.class), SoundEffects.eT);
/* 330 */     bK.put(EntityTypes.b.a(EntityZombieHusk.class), SoundEffects.eU);
/* 331 */     bK.put(EntityTypes.b.a(EntityIllagerIllusioner.class), SoundEffects.eV);
/* 332 */     bK.put(EntityTypes.b.a(EntityMagmaCube.class), SoundEffects.eW);
/* 333 */     bK.put(EntityTypes.b.a(EntityPigZombie.class), SoundEffects.fl);
/* 334 */     bK.put(EntityTypes.b.a(EntityPolarBear.class), SoundEffects.eX);
/* 335 */     bK.put(EntityTypes.b.a(EntityShulker.class), SoundEffects.eY);
/* 336 */     bK.put(EntityTypes.b.a(EntitySilverfish.class), SoundEffects.eZ);
/* 337 */     bK.put(EntityTypes.b.a(EntitySkeleton.class), SoundEffects.fa);
/* 338 */     bK.put(EntityTypes.b.a(EntitySlime.class), SoundEffects.fb);
/* 339 */     bK.put(EntityTypes.b.a(EntitySpider.class), SoundEffects.fc);
/* 340 */     bK.put(EntityTypes.b.a(EntitySkeletonStray.class), SoundEffects.fd);
/* 341 */     bK.put(EntityTypes.b.a(EntityVex.class), SoundEffects.fe);
/* 342 */     bK.put(EntityTypes.b.a(EntityVindicator.class), SoundEffects.ff);
/* 343 */     bK.put(EntityTypes.b.a(EntityWitch.class), SoundEffects.fg);
/* 344 */     bK.put(EntityTypes.b.a(EntityWither.class), SoundEffects.fh);
/* 345 */     bK.put(EntityTypes.b.a(EntitySkeletonWither.class), SoundEffects.fi);
/* 346 */     bK.put(EntityTypes.b.a(EntityWolf.class), SoundEffects.fj);
/* 347 */     bK.put(EntityTypes.b.a(EntityZombie.class), SoundEffects.fk);
/* 348 */     bK.put(EntityTypes.b.a(EntityZombieVillager.class), SoundEffects.fm);
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityParrot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */