/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.base.Optional;
/*     */ import com.google.common.base.Predicate;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*     */ import org.bukkit.event.entity.EntityTargetEvent;
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
/*     */ 
/*     */ 
/*     */ public class EntityEnderman
/*     */   extends EntityMonster
/*     */ {
/*     */   static class PathfinderGoalPlayerWhoLookedAtTarget
/*     */     extends PathfinderGoalNearestAttackableTarget<EntityHuman>
/*     */   {
/*     */     private final EntityEnderman i;
/*     */     private EntityHuman j;
/*     */     private int k;
/*     */     private int l;
/*     */     
/*     */     public PathfinderGoalPlayerWhoLookedAtTarget(EntityEnderman entityenderman) {
/* 391 */       super(entityenderman, EntityHuman.class, false);
/* 392 */       this.i = entityenderman;
/*     */     }
/*     */     
/*     */     public boolean a() {
/* 396 */       double d0 = i();
/*     */       
/* 398 */       this.j = this.i.world.a(this.i.locX, this.i.locY, this.i.locZ, d0, d0, null, new Predicate() {
/*     */             public boolean a(@Nullable EntityHuman entityhuman) {
/* 400 */               return (entityhuman != null && EntityEnderman.PathfinderGoalPlayerWhoLookedAtTarget.this.i.f(entityhuman));
/*     */             }
/*     */             
/*     */             public boolean apply(@Nullable Object object) {
/* 404 */               return a((EntityHuman)object);
/*     */             }
/*     */           });
/* 407 */       return (this.j != null);
/*     */     }
/*     */     
/*     */     public void c() {
/* 411 */       this.k = 5;
/* 412 */       this.l = 0;
/*     */     }
/*     */     
/*     */     public void d() {
/* 416 */       this.j = null;
/* 417 */       super.d();
/*     */     }
/*     */     
/*     */     public boolean b() {
/* 421 */       if (this.j != null) {
/* 422 */         if (!this.i.f(this.j)) {
/* 423 */           return false;
/*     */         }
/* 425 */         this.i.a(this.j, 10.0F, 10.0F);
/* 426 */         return true;
/*     */       } 
/*     */       
/* 429 */       return (this.d != null && this.d.isAlive()) ? true : super.b();
/*     */     }
/*     */ 
/*     */     
/*     */     public void e() {
/* 434 */       if (this.j != null) {
/* 435 */         if (--this.k <= 0) {
/* 436 */           this.d = this.j;
/* 437 */           this.j = null;
/* 438 */           super.c();
/*     */         } 
/*     */       } else {
/* 441 */         if (this.d != null) {
/* 442 */           if (this.i.f(this.d)) {
/* 443 */             if (this.d.h(this.i) < 16.0D) {
/* 444 */               this.i.dm();
/*     */             }
/*     */             
/* 447 */             this.l = 0;
/* 448 */           } else if (this.d.h(this.i) > 256.0D && this.l++ >= 30 && this.i.a(this.d)) {
/* 449 */             this.l = 0;
/*     */           } 
/*     */         }
/*     */         
/* 453 */         super.e();
/*     */       } 
/*     */     }
/*     */   }
/*     */   private static final UUID a = UUID.fromString("020E0DFB-87AE-4653-9556-831010E291A0");
/*     */   private static final AttributeModifier b = (new AttributeModifier(a, "Attacking speed boost", 0.15000000596046448D, 0)).a(false);
/*     */   private static final Set<Block> c = Sets.newIdentityHashSet();
/*     */   private static final DataWatcherObject<Optional<IBlockData>> bx = DataWatcher.a((Class)EntityEnderman.class, DataWatcherRegistry.g);
/*     */   private static final DataWatcherObject<Boolean> by = DataWatcher.a((Class)EntityEnderman.class, DataWatcherRegistry.h);
/*     */   private int bz;
/*     */   private int bA;
/*     */   
/*     */   public EntityEnderman(World world) {
/*     */     super(world);
/*     */     setSize(0.6F, 2.9F);
/*     */     this.P = 1.0F;
/*     */     a(PathType.WATER, -1.0F);
/*     */   }
/*     */   
/*     */   protected void r() {
/*     */     this.goalSelector.a(0, new PathfinderGoalFloat(this));
/*     */     this.goalSelector.a(2, new PathfinderGoalMeleeAttack(this, 1.0D, false));
/*     */     this.goalSelector.a(7, new PathfinderGoalRandomStrollLand(this, 1.0D, 0.0F));
/*     */     this.goalSelector.a(8, new PathfinderGoalLookAtPlayer(this, (Class)EntityHuman.class, 8.0F));
/*     */     this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
/*     */     this.goalSelector.a(10, new PathfinderGoalEndermanPlaceBlock(this));
/*     */     this.goalSelector.a(11, new PathfinderGoalEndermanPickupBlock(this));
/*     */     this.targetSelector.a(1, new PathfinderGoalPlayerWhoLookedAtTarget(this));
/*     */     this.targetSelector.a(2, new PathfinderGoalHurtByTarget(this, false, new Class[0]));
/*     */     this.targetSelector.a(3, new PathfinderGoalNearestAttackableTarget<>(this, EntityEndermite.class, 10, true, false, new Predicate() {
/*     */             public boolean a(@Nullable EntityEndermite entityendermite) {
/*     */               return entityendermite.p();
/*     */             }
/*     */             
/*     */             public boolean apply(@Nullable Object object) {
/*     */               return a((EntityEndermite)object);
/*     */             }
/*     */           }));
/*     */   }
/*     */   
/*     */   protected void initAttributes() {
/*     */     super.initAttributes();
/*     */     getAttributeInstance(GenericAttributes.maxHealth).setValue(40.0D);
/*     */     getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.30000001192092896D);
/*     */     getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(7.0D);
/*     */     getAttributeInstance(GenericAttributes.FOLLOW_RANGE).setValue(64.0D);
/*     */   }
/*     */   
/*     */   public void setGoalTarget(@Nullable EntityLiving entityliving) {
/*     */     setGoalTarget(entityliving, EntityTargetEvent.TargetReason.UNKNOWN, true);
/*     */   }
/*     */   
/*     */   public boolean setGoalTarget(EntityLiving entityliving, EntityTargetEvent.TargetReason reason, boolean fireEvent) {
/*     */     if (!super.setGoalTarget(entityliving, reason, fireEvent))
/*     */       return false; 
/*     */     entityliving = getGoalTarget();
/*     */     AttributeInstance attributeinstance = getAttributeInstance(GenericAttributes.MOVEMENT_SPEED);
/*     */     if (entityliving == null) {
/*     */       this.bA = 0;
/*     */       this.datawatcher.set(by, Boolean.valueOf(false));
/*     */       attributeinstance.c(b);
/*     */     } else {
/*     */       this.bA = this.ticksLived;
/*     */       this.datawatcher.set(by, Boolean.valueOf(true));
/*     */       if (!attributeinstance.a(b))
/*     */         attributeinstance.b(b); 
/*     */     } 
/*     */     return true;
/*     */   }
/*     */   
/*     */   protected void i() {
/*     */     super.i();
/*     */     this.datawatcher.register(bx, Optional.absent());
/*     */     this.datawatcher.register(by, Boolean.valueOf(false));
/*     */   }
/*     */   
/*     */   public void p() {
/*     */     if (this.ticksLived >= this.bz + 400) {
/*     */       this.bz = this.ticksLived;
/*     */       if (!isSilent())
/*     */         this.world.a(this.locX, this.locY + getHeadHeight(), this.locZ, SoundEffects.bh, bK(), 2.5F, 1.0F, false); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void a(DataWatcherObject<?> datawatcherobject) {
/*     */     if (by.equals(datawatcherobject) && do_() && this.world.isClientSide)
/*     */       p(); 
/*     */     super.a(datawatcherobject);
/*     */   }
/*     */   
/*     */   public static void a(DataConverterManager dataconvertermanager) {
/*     */     EntityInsentient.a(dataconvertermanager, EntityEnderman.class);
/*     */   }
/*     */   
/*     */   public void b(NBTTagCompound nbttagcompound) {
/*     */     super.b(nbttagcompound);
/*     */     IBlockData iblockdata = getCarried();
/*     */     if (iblockdata != null) {
/*     */       nbttagcompound.setShort("carried", (short)Block.getId(iblockdata.getBlock()));
/*     */       nbttagcompound.setShort("carriedData", (short)iblockdata.getBlock().toLegacyData(iblockdata));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void a(NBTTagCompound nbttagcompound) {
/*     */     IBlockData iblockdata;
/*     */     super.a(nbttagcompound);
/*     */     if (nbttagcompound.hasKeyOfType("carried", 8)) {
/*     */       iblockdata = Block.getByName(nbttagcompound.getString("carried")).fromLegacyData(nbttagcompound.getShort("carriedData") & 0xFFFF);
/*     */     } else {
/*     */       iblockdata = Block.getById(nbttagcompound.getShort("carried")).fromLegacyData(nbttagcompound.getShort("carriedData") & 0xFFFF);
/*     */     } 
/*     */     if (iblockdata == null || iblockdata.getBlock() == null || iblockdata.getMaterial() == Material.AIR)
/*     */       iblockdata = null; 
/*     */     setCarried(iblockdata);
/*     */   }
/*     */   
/*     */   private boolean f(EntityHuman entityhuman) {
/*     */     ItemStack itemstack = entityhuman.inventory.armor.get(3);
/*     */     if (itemstack.getItem() == Item.getItemOf(Blocks.PUMPKIN))
/*     */       return false; 
/*     */     Vec3D vec3d = entityhuman.e(1.0F).a();
/*     */     Vec3D vec3d1 = new Vec3D(this.locX - entityhuman.locX, (getBoundingBox()).b + getHeadHeight() - entityhuman.locY + entityhuman.getHeadHeight(), this.locZ - entityhuman.locZ);
/*     */     double d0 = vec3d1.b();
/*     */     vec3d1 = vec3d1.a();
/*     */     double d1 = vec3d.b(vec3d1);
/*     */     return (d1 > 1.0D - 0.025D / d0) ? entityhuman.hasLineOfSight(this) : false;
/*     */   }
/*     */   
/*     */   public float getHeadHeight() {
/*     */     return 2.55F;
/*     */   }
/*     */   
/*     */   public void n() {
/*     */     if (this.world.isClientSide)
/*     */       for (int i = 0; i < 2; i++)
/*     */         this.world.addParticle(EnumParticle.PORTAL, this.locX + (this.random.nextDouble() - 0.5D) * this.width, this.locY + this.random.nextDouble() * this.length - 0.25D, this.locZ + (this.random.nextDouble() - 0.5D) * this.width, (this.random.nextDouble() - 0.5D) * 2.0D, -this.random.nextDouble(), (this.random.nextDouble() - 0.5D) * 2.0D, new int[0]);  
/*     */     this.bd = false;
/*     */     super.n();
/*     */   }
/*     */   
/*     */   protected void M() {
/*     */     if (an())
/*     */       damageEntity(DamageSource.DROWN, 1.0F); 
/*     */     if (this.world.D() && this.ticksLived >= this.bA + 600) {
/*     */       float f = aw();
/*     */       if (f > 0.5F && this.world.h(new BlockPosition(this)) && this.random.nextFloat() * 30.0F < (f - 0.4F) * 2.0F) {
/*     */         setGoalTarget((EntityLiving)null);
/*     */         dm();
/*     */       } 
/*     */     } 
/*     */     super.M();
/*     */   }
/*     */   
/*     */   protected boolean dm() {
/*     */     double d0 = this.locX + (this.random.nextDouble() - 0.5D) * 64.0D;
/*     */     double d1 = this.locY + (this.random.nextInt(64) - 32);
/*     */     double d2 = this.locZ + (this.random.nextDouble() - 0.5D) * 64.0D;
/*     */     return k(d0, d1, d2);
/*     */   }
/*     */   
/*     */   protected boolean a(Entity entity) {
/*     */     Vec3D vec3d = new Vec3D(this.locX - entity.locX, (getBoundingBox()).b + (this.length / 2.0F) - entity.locY + entity.getHeadHeight(), this.locZ - entity.locZ);
/*     */     vec3d = vec3d.a();
/*     */     double d1 = this.locX + (this.random.nextDouble() - 0.5D) * 8.0D - vec3d.x * 16.0D;
/*     */     double d2 = this.locY + (this.random.nextInt(16) - 8) - vec3d.y * 16.0D;
/*     */     double d3 = this.locZ + (this.random.nextDouble() - 0.5D) * 8.0D - vec3d.z * 16.0D;
/*     */     return k(d1, d2, d3);
/*     */   }
/*     */   
/*     */   private boolean k(double d0, double d1, double d2) {
/*     */     boolean flag = j(d0, d1, d2);
/*     */     if (flag) {
/*     */       this.world.a((EntityHuman)null, this.lastX, this.lastY, this.lastZ, SoundEffects.bi, bK(), 1.0F, 1.0F);
/*     */       a(SoundEffects.bi, 1.0F, 1.0F);
/*     */     } 
/*     */     return flag;
/*     */   }
/*     */   
/*     */   protected SoundEffect F() {
/*     */     return do_() ? SoundEffects.bg : SoundEffects.bd;
/*     */   }
/*     */   
/*     */   protected SoundEffect d(DamageSource damagesource) {
/*     */     return SoundEffects.bf;
/*     */   }
/*     */   
/*     */   protected SoundEffect cf() {
/*     */     return SoundEffects.be;
/*     */   }
/*     */   
/*     */   protected void dropEquipment(boolean flag, int i) {
/*     */     super.dropEquipment(flag, i);
/*     */     IBlockData iblockdata = getCarried();
/*     */     if (iblockdata != null) {
/*     */       Item item = Item.getItemOf(iblockdata.getBlock());
/*     */       int j = item.k() ? iblockdata.getBlock().toLegacyData(iblockdata) : 0;
/*     */       a(new ItemStack(item, 1, j), 0.0F);
/*     */     } 
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   protected MinecraftKey J() {
/*     */     return LootTables.w;
/*     */   }
/*     */   
/*     */   public void setCarried(@Nullable IBlockData iblockdata) {
/*     */     this.datawatcher.set(bx, Optional.fromNullable(iblockdata));
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public IBlockData getCarried() {
/*     */     return (IBlockData)((Optional)this.datawatcher.<Optional>get((DataWatcherObject)bx)).orNull();
/*     */   }
/*     */   
/*     */   public boolean damageEntity(DamageSource damagesource, float f) {
/*     */     if (isInvulnerable(damagesource))
/*     */       return false; 
/*     */     if (damagesource instanceof EntityDamageSourceIndirect) {
/*     */       for (int i = 0; i < 64; i++) {
/*     */         if (dm())
/*     */           return true; 
/*     */       } 
/*     */       return false;
/*     */     } 
/*     */     boolean flag = super.damageEntity(damagesource, f);
/*     */     if (damagesource.ignoresArmor() && this.random.nextInt(10) != 0)
/*     */       dm(); 
/*     */     return flag;
/*     */   }
/*     */   
/*     */   public boolean do_() {
/*     */     return ((Boolean)this.datawatcher.<Boolean>get(by)).booleanValue();
/*     */   }
/*     */   
/*     */   static {
/*     */     c.add(Blocks.GRASS);
/*     */     c.add(Blocks.DIRT);
/*     */     c.add(Blocks.SAND);
/*     */     c.add(Blocks.GRAVEL);
/*     */     c.add(Blocks.YELLOW_FLOWER);
/*     */     c.add(Blocks.RED_FLOWER);
/*     */     c.add(Blocks.BROWN_MUSHROOM);
/*     */     c.add(Blocks.RED_MUSHROOM);
/*     */     c.add(Blocks.TNT);
/*     */     c.add(Blocks.CACTUS);
/*     */     c.add(Blocks.CLAY);
/*     */     c.add(Blocks.PUMPKIN);
/*     */     c.add(Blocks.MELON_BLOCK);
/*     */     c.add(Blocks.MYCELIUM);
/*     */     c.add(Blocks.NETHERRACK);
/*     */   }
/*     */   
/*     */   static class PathfinderGoalEndermanPickupBlock extends PathfinderGoal {
/*     */     private final EntityEnderman enderman;
/*     */     
/*     */     public PathfinderGoalEndermanPickupBlock(EntityEnderman entityenderman) {
/*     */       this.enderman = entityenderman;
/*     */     }
/*     */     
/*     */     public boolean a() {
/*     */       return (this.enderman.getCarried() != null) ? false : (!this.enderman.world.getGameRules().getBoolean("mobGriefing") ? false : ((this.enderman.getRandom().nextInt(20) == 0)));
/*     */     }
/*     */     
/*     */     public void e() {
/*     */       Random random = this.enderman.getRandom();
/*     */       World world = this.enderman.world;
/*     */       int i = MathHelper.floor(this.enderman.locX - 2.0D + random.nextDouble() * 4.0D);
/*     */       int j = MathHelper.floor(this.enderman.locY + random.nextDouble() * 3.0D);
/*     */       int k = MathHelper.floor(this.enderman.locZ - 2.0D + random.nextDouble() * 4.0D);
/*     */       BlockPosition blockposition = new BlockPosition(i, j, k);
/*     */       IBlockData iblockdata = world.getType(blockposition);
/*     */       Block block = iblockdata.getBlock();
/*     */       MovingObjectPosition movingobjectposition = world.rayTrace(new Vec3D((MathHelper.floor(this.enderman.locX) + 0.5F), (j + 0.5F), (MathHelper.floor(this.enderman.locZ) + 0.5F)), new Vec3D((i + 0.5F), (j + 0.5F), (k + 0.5F)), false, true, false);
/*     */       boolean flag = (movingobjectposition != null && movingobjectposition.a().equals(blockposition));
/*     */       if (EntityEnderman.c.contains(block) && flag)
/*     */         if (!CraftEventFactory.callEntityChangeBlockEvent(this.enderman, this.enderman.world.getWorld().getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ()), Material.AIR).isCancelled()) {
/*     */           this.enderman.setCarried(iblockdata);
/*     */           world.setAir(blockposition);
/*     */         }  
/*     */     }
/*     */   }
/*     */   
/*     */   static class PathfinderGoalEndermanPlaceBlock extends PathfinderGoal {
/*     */     private final EntityEnderman a;
/*     */     
/*     */     public PathfinderGoalEndermanPlaceBlock(EntityEnderman entityenderman) {
/*     */       this.a = entityenderman;
/*     */     }
/*     */     
/*     */     public boolean a() {
/*     */       return (this.a.getCarried() == null) ? false : (!this.a.world.getGameRules().getBoolean("mobGriefing") ? false : ((this.a.getRandom().nextInt(2000) == 0)));
/*     */     }
/*     */     
/*     */     public void e() {
/*     */       Random random = this.a.getRandom();
/*     */       World world = this.a.world;
/*     */       int i = MathHelper.floor(this.a.locX - 1.0D + random.nextDouble() * 2.0D);
/*     */       int j = MathHelper.floor(this.a.locY + random.nextDouble() * 2.0D);
/*     */       int k = MathHelper.floor(this.a.locZ - 1.0D + random.nextDouble() * 2.0D);
/*     */       BlockPosition blockposition = new BlockPosition(i, j, k);
/*     */       IBlockData iblockdata = world.getType(blockposition);
/*     */       IBlockData iblockdata1 = world.getType(blockposition.down());
/*     */       IBlockData iblockdata2 = this.a.getCarried();
/*     */       if (iblockdata2 != null && a(world, blockposition, iblockdata2.getBlock(), iblockdata, iblockdata1))
/*     */         if (!CraftEventFactory.callEntityChangeBlockEvent(this.a, blockposition, this.a.getCarried().getBlock(), this.a.getCarried().getBlock().toLegacyData(this.a.getCarried())).isCancelled()) {
/*     */           world.setTypeAndData(blockposition, iblockdata2, 3);
/*     */           this.a.setCarried((IBlockData)null);
/*     */         }  
/*     */     }
/*     */     
/*     */     private boolean a(World world, BlockPosition blockposition, Block block, IBlockData iblockdata, IBlockData iblockdata1) {
/*     */       return !block.canPlace(world, blockposition) ? false : ((iblockdata.getMaterial() != Material.AIR) ? false : ((iblockdata1.getMaterial() == Material.AIR) ? false : iblockdata1.g()));
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityEnderman.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */