/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*     */ 
/*     */ public class EntitySilverfish extends EntityMonster {
/*     */   private PathfinderGoalSilverfishWakeOthers a;
/*     */   
/*     */   public EntitySilverfish(World world) {
/*  11 */     super(world);
/*  12 */     setSize(0.4F, 0.3F);
/*     */   }
/*     */   
/*     */   public static void a(DataConverterManager dataconvertermanager) {
/*  16 */     EntityInsentient.a(dataconvertermanager, EntitySilverfish.class);
/*     */   }
/*     */   
/*     */   protected void r() {
/*  20 */     this.a = new PathfinderGoalSilverfishWakeOthers(this);
/*  21 */     this.goalSelector.a(1, new PathfinderGoalFloat(this));
/*  22 */     this.goalSelector.a(3, this.a);
/*  23 */     this.goalSelector.a(4, new PathfinderGoalMeleeAttack(this, 1.0D, false));
/*  24 */     this.goalSelector.a(5, new PathfinderGoalSilverfishHideInBlock(this));
/*  25 */     this.targetSelector.a(1, new PathfinderGoalHurtByTarget(this, true, new Class[0]));
/*  26 */     this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget<>(this, EntityHuman.class, true));
/*     */   }
/*     */   
/*     */   public double aF() {
/*  30 */     return 0.1D;
/*     */   }
/*     */   
/*     */   public float getHeadHeight() {
/*  34 */     return 0.1F;
/*     */   }
/*     */   
/*     */   protected void initAttributes() {
/*  38 */     super.initAttributes();
/*  39 */     getAttributeInstance(GenericAttributes.maxHealth).setValue(8.0D);
/*  40 */     getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.25D);
/*  41 */     getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(1.0D);
/*     */   }
/*     */   
/*     */   protected boolean playStepSound() {
/*  45 */     return false;
/*     */   }
/*     */   
/*     */   protected SoundEffect F() {
/*  49 */     return SoundEffects.gM;
/*     */   }
/*     */   
/*     */   protected SoundEffect d(DamageSource damagesource) {
/*  53 */     return SoundEffects.gO;
/*     */   }
/*     */   
/*     */   protected SoundEffect cf() {
/*  57 */     return SoundEffects.gN;
/*     */   }
/*     */   
/*     */   protected void a(BlockPosition blockposition, Block block) {
/*  61 */     a(SoundEffects.gP, 0.15F, 1.0F);
/*     */   }
/*     */   
/*     */   public boolean damageEntity(DamageSource damagesource, float f) {
/*  65 */     if (isInvulnerable(damagesource)) {
/*  66 */       return false;
/*     */     }
/*  68 */     if ((damagesource instanceof EntityDamageSource || damagesource == DamageSource.MAGIC) && this.a != null) {
/*  69 */       this.a.f();
/*     */     }
/*     */     
/*  72 */     return super.damageEntity(damagesource, f);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   protected MinecraftKey J() {
/*  78 */     return LootTables.v;
/*     */   }
/*     */   
/*     */   public void B_() {
/*  82 */     this.aN = this.yaw;
/*  83 */     super.B_();
/*     */   }
/*     */   
/*     */   public void h(float f) {
/*  87 */     this.yaw = f;
/*  88 */     super.h(f);
/*     */   }
/*     */   
/*     */   public float a(BlockPosition blockposition) {
/*  92 */     return (this.world.getType(blockposition.down()).getBlock() == Blocks.STONE) ? 10.0F : super.a(blockposition);
/*     */   }
/*     */   
/*     */   protected boolean s_() {
/*  96 */     return true;
/*     */   }
/*     */   
/*     */   public boolean P() {
/* 100 */     if (super.P()) {
/* 101 */       EntityHuman entityhuman = this.world.b(this, 5.0D);
/*     */       
/* 103 */       return (entityhuman == null);
/*     */     } 
/* 105 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumMonsterType getMonsterType() {
/* 110 */     return EnumMonsterType.ARTHROPOD;
/*     */   }
/*     */   
/*     */   static class PathfinderGoalSilverfishHideInBlock
/*     */     extends PathfinderGoalRandomStroll {
/*     */     private EnumDirection h;
/*     */     private boolean i;
/*     */     
/*     */     public PathfinderGoalSilverfishHideInBlock(EntitySilverfish entitysilverfish) {
/* 119 */       super(entitysilverfish, 1.0D, 10);
/* 120 */       a(1);
/*     */     }
/*     */     
/*     */     public boolean a() {
/* 124 */       if (this.a.getGoalTarget() != null)
/* 125 */         return false; 
/* 126 */       if (!this.a.getNavigation().o()) {
/* 127 */         return false;
/*     */       }
/* 129 */       Random random = this.a.getRandom();
/*     */       
/* 131 */       if (this.a.world.getGameRules().getBoolean("mobGriefing") && random.nextInt(10) == 0) {
/* 132 */         this.h = EnumDirection.a(random);
/* 133 */         BlockPosition blockposition = (new BlockPosition(this.a.locX, this.a.locY + 0.5D, this.a.locZ)).shift(this.h);
/* 134 */         IBlockData iblockdata = this.a.world.getType(blockposition);
/*     */         
/* 136 */         if (BlockMonsterEggs.x(iblockdata)) {
/* 137 */           this.i = true;
/* 138 */           return true;
/*     */         } 
/*     */       } 
/*     */       
/* 142 */       this.i = false;
/* 143 */       return super.a();
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean b() {
/* 148 */       return this.i ? false : super.b();
/*     */     }
/*     */     
/*     */     public void c() {
/* 152 */       if (!this.i) {
/* 153 */         super.c();
/*     */       } else {
/* 155 */         World world = this.a.world;
/* 156 */         BlockPosition blockposition = (new BlockPosition(this.a.locX, this.a.locY + 0.5D, this.a.locZ)).shift(this.h);
/* 157 */         IBlockData iblockdata = world.getType(blockposition);
/*     */         
/* 159 */         if (BlockMonsterEggs.x(iblockdata)) {
/*     */           
/* 161 */           if (CraftEventFactory.callEntityChangeBlockEvent(this.a, blockposition, Blocks.MONSTER_EGG, Block.getId(BlockMonsterEggs.getById(iblockdata.getBlock().toLegacyData(iblockdata)))).isCancelled()) {
/*     */             return;
/*     */           }
/*     */           
/* 165 */           world.setTypeAndData(blockposition, Blocks.MONSTER_EGG.getBlockData().set(BlockMonsterEggs.VARIANT, BlockMonsterEggs.EnumMonsterEggVarient.a(iblockdata)), 3);
/* 166 */           this.a.doSpawnEffect();
/* 167 */           this.a.die();
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   static class PathfinderGoalSilverfishWakeOthers
/*     */     extends PathfinderGoal
/*     */   {
/*     */     private final EntitySilverfish silverfish;
/*     */     private int b;
/*     */     
/*     */     public PathfinderGoalSilverfishWakeOthers(EntitySilverfish entitysilverfish) {
/* 180 */       this.silverfish = entitysilverfish;
/*     */     }
/*     */     
/*     */     public void f() {
/* 184 */       if (this.b == 0) {
/* 185 */         this.b = 20;
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 191 */       return (this.b > 0);
/*     */     }
/*     */     
/*     */     public void e() {
/* 195 */       this.b--;
/* 196 */       if (this.b <= 0) {
/* 197 */         World world = this.silverfish.world;
/* 198 */         Random random = this.silverfish.getRandom();
/* 199 */         BlockPosition blockposition = new BlockPosition(this.silverfish);
/*     */         
/* 201 */         for (int i = 0; i <= 5 && i >= -5; i = ((i <= 0) ? 1 : 0) - i) {
/* 202 */           for (int j = 0; j <= 10 && j >= -10; j = ((j <= 0) ? 1 : 0) - j) {
/* 203 */             for (int k = 0; k <= 10 && k >= -10; k = ((k <= 0) ? 1 : 0) - k) {
/* 204 */               BlockPosition blockposition1 = blockposition.a(j, i, k);
/* 205 */               IBlockData iblockdata = world.getType(blockposition1);
/*     */               
/* 207 */               if (iblockdata.getBlock() == Blocks.MONSTER_EGG)
/*     */               {
/* 209 */                 if (!CraftEventFactory.callEntityChangeBlockEvent(this.silverfish, blockposition1, Blocks.AIR, 0).isCancelled()) {
/*     */ 
/*     */ 
/*     */                   
/* 213 */                   if (world.getGameRules().getBoolean("mobGriefing")) {
/* 214 */                     world.setAir(blockposition1, true);
/*     */                   } else {
/* 216 */                     world.setTypeAndData(blockposition1, ((BlockMonsterEggs.EnumMonsterEggVarient)iblockdata.<BlockMonsterEggs.EnumMonsterEggVarient>get(BlockMonsterEggs.VARIANT)).d(), 3);
/*     */                   } 
/*     */                   
/* 219 */                   if (random.nextBoolean())
/*     */                     return; 
/*     */                 } 
/*     */               }
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntitySilverfish.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */