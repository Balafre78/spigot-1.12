/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.event.entity.CreatureSpawnEvent;
/*     */ 
/*     */ public class EntitySpider extends EntityMonster {
/*   8 */   private static final DataWatcherObject<Byte> a = DataWatcher.a((Class)EntitySpider.class, DataWatcherRegistry.a);
/*     */   
/*     */   public EntitySpider(World world) {
/*  11 */     super(world);
/*  12 */     setSize(1.4F, 0.9F);
/*     */   }
/*     */   
/*     */   public static void c(DataConverterManager dataconvertermanager) {
/*  16 */     EntityInsentient.a(dataconvertermanager, EntitySpider.class);
/*     */   }
/*     */   
/*     */   protected void r() {
/*  20 */     this.goalSelector.a(1, new PathfinderGoalFloat(this));
/*  21 */     this.goalSelector.a(3, new PathfinderGoalLeapAtTarget(this, 0.4F));
/*  22 */     this.goalSelector.a(4, new PathfinderGoalSpiderMeleeAttack(this));
/*  23 */     this.goalSelector.a(5, new PathfinderGoalRandomStrollLand(this, 0.8D));
/*  24 */     this.goalSelector.a(6, new PathfinderGoalLookAtPlayer(this, (Class)EntityHuman.class, 8.0F));
/*  25 */     this.goalSelector.a(6, new PathfinderGoalRandomLookaround(this));
/*  26 */     this.targetSelector.a(1, new PathfinderGoalHurtByTarget(this, false, new Class[0]));
/*  27 */     this.targetSelector.a(2, new PathfinderGoalSpiderNearestAttackableTarget<>(this, EntityHuman.class));
/*  28 */     this.targetSelector.a(3, new PathfinderGoalSpiderNearestAttackableTarget<>(this, EntityIronGolem.class));
/*     */   }
/*     */   
/*     */   public double aG() {
/*  32 */     return (this.length * 0.5F);
/*     */   }
/*     */   
/*     */   protected NavigationAbstract b(World world) {
/*  36 */     return new NavigationSpider(this, world);
/*     */   }
/*     */   
/*     */   protected void i() {
/*  40 */     super.i();
/*  41 */     this.datawatcher.register(a, Byte.valueOf((byte)0));
/*     */   }
/*     */   
/*     */   public void B_() {
/*  45 */     super.B_();
/*  46 */     if (!this.world.isClientSide) {
/*  47 */       a(this.positionChanged);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initAttributes() {
/*  53 */     super.initAttributes();
/*  54 */     getAttributeInstance(GenericAttributes.maxHealth).setValue(16.0D);
/*  55 */     getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.30000001192092896D);
/*     */   }
/*     */   
/*     */   protected SoundEffect F() {
/*  59 */     return SoundEffects.hz;
/*     */   }
/*     */   
/*     */   protected SoundEffect d(DamageSource damagesource) {
/*  63 */     return SoundEffects.hB;
/*     */   }
/*     */   
/*     */   protected SoundEffect cf() {
/*  67 */     return SoundEffects.hA;
/*     */   }
/*     */   
/*     */   protected void a(BlockPosition blockposition, Block block) {
/*  71 */     a(SoundEffects.hC, 0.15F, 1.0F);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   protected MinecraftKey J() {
/*  76 */     return LootTables.s;
/*     */   }
/*     */   
/*     */   public boolean m_() {
/*  80 */     return p();
/*     */   }
/*     */   
/*     */   public void ba() {}
/*     */   
/*     */   public EnumMonsterType getMonsterType() {
/*  86 */     return EnumMonsterType.ARTHROPOD;
/*     */   }
/*     */   
/*     */   public boolean d(MobEffect mobeffect) {
/*  90 */     return (mobeffect.getMobEffect() == MobEffects.POISON) ? false : super.d(mobeffect);
/*     */   }
/*     */   
/*     */   public boolean p() {
/*  94 */     return ((((Byte)this.datawatcher.<Byte>get(a)).byteValue() & 0x1) != 0);
/*     */   }
/*     */   
/*     */   public void a(boolean flag) {
/*  98 */     byte b0 = ((Byte)this.datawatcher.<Byte>get(a)).byteValue();
/*     */     
/* 100 */     if (flag) {
/* 101 */       b0 = (byte)(b0 | 0x1);
/*     */     } else {
/* 103 */       b0 = (byte)(b0 & 0xFFFFFFFE);
/*     */     } 
/*     */     
/* 106 */     this.datawatcher.set(a, Byte.valueOf(b0));
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public GroupDataEntity prepare(DifficultyDamageScaler difficultydamagescaler, @Nullable GroupDataEntity groupdataentity) {
/* 111 */     Object object = super.prepare(difficultydamagescaler, groupdataentity);
/*     */     
/* 113 */     if (this.world.random.nextInt(100) == 0) {
/* 114 */       EntitySkeleton entityskeleton = new EntitySkeleton(this.world);
/*     */       
/* 116 */       entityskeleton.setPositionRotation(this.locX, this.locY, this.locZ, this.yaw, 0.0F);
/* 117 */       entityskeleton.prepare(difficultydamagescaler, (GroupDataEntity)null);
/* 118 */       this.world.addEntity(entityskeleton, CreatureSpawnEvent.SpawnReason.JOCKEY);
/* 119 */       entityskeleton.startRiding(this);
/*     */     } 
/*     */     
/* 122 */     if (object == null) {
/* 123 */       object = new GroupDataSpider();
/* 124 */       if (this.world.getDifficulty() == EnumDifficulty.HARD && this.world.random.nextFloat() < 0.1F * difficultydamagescaler.d()) {
/* 125 */         ((GroupDataSpider)object).a(this.world.random);
/*     */       }
/*     */     } 
/*     */     
/* 129 */     if (object instanceof GroupDataSpider) {
/* 130 */       MobEffectList mobeffectlist = ((GroupDataSpider)object).a;
/*     */       
/* 132 */       if (mobeffectlist != null) {
/* 133 */         addEffect(new MobEffect(mobeffectlist, 2147483647));
/*     */       }
/*     */     } 
/*     */     
/* 137 */     return (GroupDataEntity)object;
/*     */   }
/*     */   
/*     */   public float getHeadHeight() {
/* 141 */     return 0.65F;
/*     */   }
/*     */   
/*     */   static class PathfinderGoalSpiderNearestAttackableTarget<T extends EntityLiving>
/*     */     extends PathfinderGoalNearestAttackableTarget<T> {
/*     */     public PathfinderGoalSpiderNearestAttackableTarget(EntitySpider entityspider, Class<T> oclass) {
/* 147 */       super(entityspider, oclass, true);
/*     */     }
/*     */     
/*     */     public boolean a() {
/* 151 */       float f = this.e.aw();
/*     */       
/* 153 */       return (f >= 0.5F) ? false : super.a();
/*     */     }
/*     */   }
/*     */   
/*     */   static class PathfinderGoalSpiderMeleeAttack
/*     */     extends PathfinderGoalMeleeAttack {
/*     */     public PathfinderGoalSpiderMeleeAttack(EntitySpider entityspider) {
/* 160 */       super(entityspider, 1.0D, true);
/*     */     }
/*     */     
/*     */     public boolean b() {
/* 164 */       float f = this.b.aw();
/*     */       
/* 166 */       if (f >= 0.5F && this.b.getRandom().nextInt(100) == 0) {
/* 167 */         this.b.setGoalTarget((EntityLiving)null);
/* 168 */         return false;
/*     */       } 
/* 170 */       return super.b();
/*     */     }
/*     */ 
/*     */     
/*     */     protected double a(EntityLiving entityliving) {
/* 175 */       return (4.0F + entityliving.width);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static class GroupDataSpider
/*     */     implements GroupDataEntity
/*     */   {
/*     */     public MobEffectList a;
/*     */     
/*     */     public void a(Random random) {
/* 186 */       int i = random.nextInt(5);
/*     */       
/* 188 */       if (i <= 1) {
/* 189 */         this.a = MobEffects.FASTER_MOVEMENT;
/* 190 */       } else if (i <= 2) {
/* 191 */         this.a = MobEffects.INCREASE_DAMAGE;
/* 192 */       } else if (i <= 3) {
/* 193 */         this.a = MobEffects.REGENERATION;
/* 194 */       } else if (i <= 4) {
/* 195 */         this.a = MobEffects.INVISIBILITY;
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntitySpider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */