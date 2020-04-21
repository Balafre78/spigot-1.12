/*     */ package net.minecraft.server.v1_12_R1;
/*     */ import java.util.Calendar;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.event.entity.EntityCombustEvent;
/*     */ import org.bukkit.event.entity.EntityShootBowEvent;
/*     */ 
/*     */ public abstract class EntitySkeletonAbstract extends EntityMonster implements IRangedEntity {
/*   9 */   private static final DataWatcherObject<Boolean> a = DataWatcher.a((Class)EntitySkeletonAbstract.class, DataWatcherRegistry.h);
/*  10 */   private final PathfinderGoalBowShoot<EntitySkeletonAbstract> b = new PathfinderGoalBowShoot<>(this, 1.0D, 20, 15.0F);
/*  11 */   private final PathfinderGoalMeleeAttack c = new PathfinderGoalMeleeAttack(this, 1.2D, false) {
/*     */       public void d() {
/*  13 */         super.d();
/*  14 */         EntitySkeletonAbstract.this.p(false);
/*     */       }
/*     */       
/*     */       public void c() {
/*  18 */         super.c();
/*  19 */         EntitySkeletonAbstract.this.p(true);
/*     */       }
/*     */     };
/*     */   
/*     */   public EntitySkeletonAbstract(World world) {
/*  24 */     super(world);
/*  25 */     setSize(0.6F, 1.99F);
/*  26 */     dm();
/*     */   }
/*     */   
/*     */   protected void r() {
/*  30 */     this.goalSelector.a(1, new PathfinderGoalFloat(this));
/*  31 */     this.goalSelector.a(2, new PathfinderGoalRestrictSun(this));
/*  32 */     this.goalSelector.a(3, new PathfinderGoalFleeSun(this, 1.0D));
/*  33 */     this.goalSelector.a(3, new PathfinderGoalAvoidTarget<>(this, EntityWolf.class, 6.0F, 1.0D, 1.2D));
/*  34 */     this.goalSelector.a(5, new PathfinderGoalRandomStrollLand(this, 1.0D));
/*  35 */     this.goalSelector.a(6, new PathfinderGoalLookAtPlayer(this, (Class)EntityHuman.class, 8.0F));
/*  36 */     this.goalSelector.a(6, new PathfinderGoalRandomLookaround(this));
/*  37 */     this.targetSelector.a(1, new PathfinderGoalHurtByTarget(this, false, new Class[0]));
/*  38 */     this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget<>(this, EntityHuman.class, true));
/*  39 */     this.targetSelector.a(3, new PathfinderGoalNearestAttackableTarget<>(this, EntityIronGolem.class, true));
/*     */   }
/*     */   
/*     */   protected void initAttributes() {
/*  43 */     super.initAttributes();
/*  44 */     getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.25D);
/*     */   }
/*     */   
/*     */   protected void i() {
/*  48 */     super.i();
/*  49 */     this.datawatcher.register(a, Boolean.valueOf(false));
/*     */   }
/*     */   
/*     */   protected void a(BlockPosition blockposition, Block block) {
/*  53 */     a(p(), 0.15F, 1.0F);
/*     */   }
/*     */   
/*     */   abstract SoundEffect p();
/*     */   
/*     */   public EnumMonsterType getMonsterType() {
/*  59 */     return EnumMonsterType.UNDEAD;
/*     */   }
/*     */   
/*     */   public void n() {
/*  63 */     if (this.world.D() && !this.world.isClientSide) {
/*  64 */       float f = aw();
/*  65 */       BlockPosition blockposition = (bJ() instanceof EntityBoat) ? (new BlockPosition(this.locX, Math.round(this.locY), this.locZ)).up() : new BlockPosition(this.locX, Math.round(this.locY), this.locZ);
/*     */       
/*  67 */       if (f > 0.5F && this.random.nextFloat() * 30.0F < (f - 0.4F) * 2.0F && this.world.h(blockposition)) {
/*  68 */         boolean flag = true;
/*  69 */         ItemStack itemstack = getEquipment(EnumItemSlot.HEAD);
/*     */         
/*  71 */         if (!itemstack.isEmpty()) {
/*  72 */           if (itemstack.f()) {
/*  73 */             itemstack.setData(itemstack.i() + this.random.nextInt(2));
/*  74 */             if (itemstack.i() >= itemstack.k()) {
/*  75 */               b(itemstack);
/*  76 */               setSlot(EnumItemSlot.HEAD, ItemStack.a);
/*     */             } 
/*     */           } 
/*     */           
/*  80 */           flag = false;
/*     */         } 
/*     */         
/*  83 */         if (flag) {
/*     */           
/*  85 */           EntityCombustEvent event = new EntityCombustEvent((Entity)getBukkitEntity(), 8);
/*  86 */           this.world.getServer().getPluginManager().callEvent((Event)event);
/*     */           
/*  88 */           if (!event.isCancelled()) {
/*  89 */             setOnFire(event.getDuration());
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*  96 */     super.n();
/*     */   }
/*     */   
/*     */   public void aE() {
/* 100 */     super.aE();
/* 101 */     if (bJ() instanceof EntityCreature) {
/* 102 */       EntityCreature entitycreature = (EntityCreature)bJ();
/*     */       
/* 104 */       this.aN = entitycreature.aN;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(DifficultyDamageScaler difficultydamagescaler) {
/* 110 */     super.a(difficultydamagescaler);
/* 111 */     setSlot(EnumItemSlot.MAINHAND, new ItemStack(Items.BOW));
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public GroupDataEntity prepare(DifficultyDamageScaler difficultydamagescaler, @Nullable GroupDataEntity groupdataentity) {
/* 116 */     groupdataentity = super.prepare(difficultydamagescaler, groupdataentity);
/* 117 */     a(difficultydamagescaler);
/* 118 */     b(difficultydamagescaler);
/* 119 */     dm();
/* 120 */     m((this.random.nextFloat() < 0.55F * difficultydamagescaler.d()));
/* 121 */     if (getEquipment(EnumItemSlot.HEAD).isEmpty()) {
/* 122 */       Calendar calendar = this.world.ae();
/*     */       
/* 124 */       if (calendar.get(2) + 1 == 10 && calendar.get(5) == 31 && this.random.nextFloat() < 0.25F) {
/* 125 */         setSlot(EnumItemSlot.HEAD, new ItemStack((this.random.nextFloat() < 0.1F) ? Blocks.LIT_PUMPKIN : Blocks.PUMPKIN));
/* 126 */         this.dropChanceArmor[EnumItemSlot.HEAD.b()] = 0.0F;
/*     */       } 
/*     */     } 
/*     */     
/* 130 */     return groupdataentity;
/*     */   }
/*     */   
/*     */   public void dm() {
/* 134 */     if (this.world != null && !this.world.isClientSide) {
/* 135 */       this.goalSelector.a(this.c);
/* 136 */       this.goalSelector.a(this.b);
/* 137 */       ItemStack itemstack = getItemInMainHand();
/*     */       
/* 139 */       if (itemstack.getItem() == Items.BOW) {
/* 140 */         byte b0 = 20;
/*     */         
/* 142 */         if (this.world.getDifficulty() != EnumDifficulty.HARD) {
/* 143 */           b0 = 40;
/*     */         }
/*     */         
/* 146 */         this.b.b(b0);
/* 147 */         this.goalSelector.a(4, this.b);
/*     */       } else {
/* 149 */         this.goalSelector.a(4, this.c);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(EntityLiving entityliving, float f) {
/* 156 */     EntityArrow entityarrow = a(f);
/* 157 */     double d0 = entityliving.locX - this.locX;
/* 158 */     double d1 = (entityliving.getBoundingBox()).b + (entityliving.length / 3.0F) - entityarrow.locY;
/* 159 */     double d2 = entityliving.locZ - this.locZ;
/* 160 */     double d3 = MathHelper.sqrt(d0 * d0 + d2 * d2);
/*     */     
/* 162 */     entityarrow.shoot(d0, d1 + d3 * 0.20000000298023224D, d2, 1.6F, (14 - this.world.getDifficulty().a() * 4));
/*     */     
/* 164 */     EntityShootBowEvent event = CraftEventFactory.callEntityShootBowEvent(this, getItemInMainHand(), entityarrow, 0.8F);
/* 165 */     if (event.isCancelled()) {
/* 166 */       event.getProjectile().remove();
/*     */       
/*     */       return;
/*     */     } 
/* 170 */     if (event.getProjectile() == entityarrow.getBukkitEntity()) {
/* 171 */       this.world.addEntity(entityarrow);
/*     */     }
/*     */     
/* 174 */     a(SoundEffects.gW, 1.0F, 1.0F / (getRandom().nextFloat() * 0.4F + 0.8F));
/*     */   }
/*     */ 
/*     */   
/*     */   protected EntityArrow a(float f) {
/* 179 */     EntityTippedArrow entitytippedarrow = new EntityTippedArrow(this.world, this);
/*     */     
/* 181 */     entitytippedarrow.a(this, f);
/* 182 */     return entitytippedarrow;
/*     */   }
/*     */   
/*     */   public void a(NBTTagCompound nbttagcompound) {
/* 186 */     super.a(nbttagcompound);
/* 187 */     dm();
/*     */   }
/*     */   
/*     */   public void setSlot(EnumItemSlot enumitemslot, ItemStack itemstack) {
/* 191 */     super.setSlot(enumitemslot, itemstack);
/* 192 */     if (!this.world.isClientSide && enumitemslot == EnumItemSlot.MAINHAND) {
/* 193 */       dm();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public float getHeadHeight() {
/* 199 */     return 1.74F;
/*     */   }
/*     */   
/*     */   public double aF() {
/* 203 */     return -0.6D;
/*     */   }
/*     */   
/*     */   public void p(boolean flag) {
/* 207 */     this.datawatcher.set(a, Boolean.valueOf(flag));
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntitySkeletonAbstract.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */