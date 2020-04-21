/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
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
/*     */ public class EntityHorseZombie
/*     */   extends EntityHorseAbstract
/*     */ {
/*     */   public EntityHorseZombie(World paramWorld) {
/*  21 */     super(paramWorld);
/*     */   }
/*     */   
/*     */   public static void a(DataConverterManager paramDataConverterManager) {
/*  25 */     EntityHorseAbstract.c(paramDataConverterManager, EntityHorseZombie.class);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initAttributes() {
/*  30 */     super.initAttributes();
/*     */     
/*  32 */     getAttributeInstance(GenericAttributes.maxHealth).setValue(15.0D);
/*  33 */     getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.20000000298023224D);
/*  34 */     getAttributeInstance(attributeJumpStrength).setValue(dN());
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumMonsterType getMonsterType() {
/*  39 */     return EnumMonsterType.UNDEAD;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect F() {
/*  44 */     super.F();
/*  45 */     return SoundEffects.jn;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect cf() {
/*  50 */     super.cf();
/*  51 */     return SoundEffects.jo;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect d(DamageSource paramDamageSource) {
/*  56 */     super.d(paramDamageSource);
/*  57 */     return SoundEffects.jp;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   protected MinecraftKey J() {
/*  63 */     return LootTables.J;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(EntityHuman paramEntityHuman, EnumHand paramEnumHand) {
/*  68 */     ItemStack itemStack = paramEntityHuman.b(paramEnumHand);
/*  69 */     boolean bool = !itemStack.isEmpty() ? true : false;
/*  70 */     if (bool && itemStack.getItem() == Items.SPAWN_EGG) {
/*  71 */       return super.a(paramEntityHuman, paramEnumHand);
/*     */     }
/*     */     
/*  74 */     if (!isTamed()) {
/*  75 */       return false;
/*     */     }
/*     */     
/*  78 */     if (isBaby()) {
/*  79 */       return super.a(paramEntityHuman, paramEnumHand);
/*     */     }
/*     */     
/*  82 */     if (paramEntityHuman.isSneaking()) {
/*  83 */       c(paramEntityHuman);
/*  84 */       return true;
/*     */     } 
/*     */     
/*  87 */     if (isVehicle()) {
/*  88 */       return super.a(paramEntityHuman, paramEnumHand);
/*     */     }
/*     */     
/*  91 */     if (bool) {
/*  92 */       if (!dG() && itemStack.getItem() == Items.SADDLE) {
/*  93 */         c(paramEntityHuman);
/*  94 */         return true;
/*     */       } 
/*     */       
/*  97 */       if (itemStack.a(paramEntityHuman, this, paramEnumHand)) {
/*  98 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 102 */     g(paramEntityHuman);
/* 103 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityHorseZombie.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */