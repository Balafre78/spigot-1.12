/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityVindicator
/*     */   extends EntityIllagerAbstract
/*     */ {
/*     */   private boolean b;
/*     */   
/*     */   public EntityVindicator(World paramWorld) {
/*  40 */     super(paramWorld);
/*     */     
/*  42 */     setSize(0.6F, 1.95F);
/*     */   }
/*     */   
/*     */   public static void a(DataConverterManager paramDataConverterManager) {
/*  46 */     EntityInsentient.a(paramDataConverterManager, EntityVindicator.class);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void r() {
/*  51 */     super.r();
/*     */     
/*  53 */     this.goalSelector.a(0, new PathfinderGoalFloat(this));
/*  54 */     this.goalSelector.a(4, new PathfinderGoalMeleeAttack(this, 1.0D, false));
/*  55 */     this.goalSelector.a(8, new PathfinderGoalRandomStroll(this, 0.6D));
/*  56 */     this.goalSelector.a(9, new PathfinderGoalLookAtPlayer(this, (Class)EntityHuman.class, 3.0F, 1.0F));
/*  57 */     this.goalSelector.a(10, new PathfinderGoalLookAtPlayer(this, (Class)EntityInsentient.class, 8.0F));
/*     */     
/*  59 */     this.targetSelector.a(1, new PathfinderGoalHurtByTarget(this, true, new Class[] { EntityVindicator.class }));
/*  60 */     this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget<>(this, EntityHuman.class, true));
/*  61 */     this.targetSelector.a(3, new PathfinderGoalNearestAttackableTarget<>(this, EntityVillager.class, true));
/*  62 */     this.targetSelector.a(3, new PathfinderGoalNearestAttackableTarget<>(this, EntityIronGolem.class, true));
/*  63 */     this.targetSelector.a(4, new a(this));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initAttributes() {
/*  68 */     super.initAttributes();
/*     */     
/*  70 */     getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.3499999940395355D);
/*  71 */     getAttributeInstance(GenericAttributes.FOLLOW_RANGE).setValue(12.0D);
/*  72 */     getAttributeInstance(GenericAttributes.maxHealth).setValue(24.0D);
/*  73 */     getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(5.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void i() {
/*  78 */     super.i();
/*     */   }
/*     */ 
/*     */   
/*     */   protected MinecraftKey J() {
/*  83 */     return LootTables.av;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(boolean paramBoolean) {
/*  91 */     a(1, paramBoolean);
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(NBTTagCompound paramNBTTagCompound) {
/*  96 */     super.b(paramNBTTagCompound);
/*     */     
/*  98 */     if (this.b) {
/*  99 */       paramNBTTagCompound.setBoolean("Johnny", true);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(NBTTagCompound paramNBTTagCompound) {
/* 113 */     super.a(paramNBTTagCompound);
/*     */     
/* 115 */     if (paramNBTTagCompound.hasKeyOfType("Johnny", 99)) {
/* 116 */       this.b = paramNBTTagCompound.getBoolean("Johnny");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public GroupDataEntity prepare(DifficultyDamageScaler paramDifficultyDamageScaler, @Nullable GroupDataEntity paramGroupDataEntity) {
/* 123 */     GroupDataEntity groupDataEntity = super.prepare(paramDifficultyDamageScaler, paramGroupDataEntity);
/*     */     
/* 125 */     a(paramDifficultyDamageScaler);
/* 126 */     b(paramDifficultyDamageScaler);
/*     */     
/* 128 */     return groupDataEntity;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(DifficultyDamageScaler paramDifficultyDamageScaler) {
/* 133 */     setSlot(EnumItemSlot.MAINHAND, new ItemStack(Items.IRON_AXE));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void M() {
/* 138 */     super.M();
/*     */     
/* 140 */     a((getGoalTarget() != null));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean r(Entity paramEntity) {
/* 145 */     if (super.r(paramEntity)) {
/* 146 */       return true;
/*     */     }
/* 148 */     if (paramEntity instanceof EntityLiving && ((EntityLiving)paramEntity).getMonsterType() == EnumMonsterType.ILLAGER)
/*     */     {
/* 150 */       return (aY() == null && paramEntity.aY() == null);
/*     */     }
/* 152 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCustomName(String paramString) {
/* 157 */     super.setCustomName(paramString);
/* 158 */     if (!this.b && "Johnny".equals(paramString)) {
/* 159 */       this.b = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect F() {
/* 165 */     return SoundEffects.iq;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect cf() {
/* 170 */     return SoundEffects.ir;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect d(DamageSource paramDamageSource) {
/* 175 */     return SoundEffects.is;
/*     */   }
/*     */   
/*     */   static class a extends PathfinderGoalNearestAttackableTarget<EntityLiving> {
/*     */     public a(EntityVindicator param1EntityVindicator) {
/* 180 */       super(param1EntityVindicator, EntityLiving.class, 0, true, true, EntityVindicator.dn());
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 185 */       return (EntityVindicator.a((EntityVindicator)this.e) && super.a());
/*     */     }
/*     */   }
/*     */   
/* 189 */   private static final Predicate<Entity> c = new Predicate<Entity>()
/*     */     {
/*     */       public boolean a(@Nullable Entity param1Entity) {
/* 192 */         return (param1Entity instanceof EntityLiving && ((EntityLiving)param1Entity).cS());
/*     */       }
/*     */     };
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityVindicator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */