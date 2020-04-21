/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import java.util.List;
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
/*     */ public class EntityGuardianElder
/*     */   extends EntityGuardian
/*     */ {
/*     */   public EntityGuardianElder(World paramWorld) {
/*  25 */     super(paramWorld);
/*     */     
/*  27 */     setSize(this.width * 2.35F, this.length * 2.35F);
/*  28 */     cW();
/*     */ 
/*     */     
/*  31 */     if (this.goalRandomStroll != null) {
/*  32 */       this.goalRandomStroll.setTimeBetweenMovement(400);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initAttributes() {
/*  38 */     super.initAttributes();
/*  39 */     getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.30000001192092896D);
/*  40 */     getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(8.0D);
/*  41 */     getAttributeInstance(GenericAttributes.maxHealth).setValue(80.0D);
/*     */   }
/*     */   
/*     */   public static void a(DataConverterManager paramDataConverterManager) {
/*  45 */     EntityInsentient.a(paramDataConverterManager, EntityGuardianElder.class);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   protected MinecraftKey J() {
/*  51 */     return LootTables.y;
/*     */   }
/*     */ 
/*     */   
/*     */   public int p() {
/*  56 */     return 60;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SoundEffect F() {
/*  66 */     return isInWater() ? SoundEffects.aI : SoundEffects.aJ;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect d(DamageSource paramDamageSource) {
/*  71 */     return isInWater() ? SoundEffects.aO : SoundEffects.aP;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect cf() {
/*  76 */     return isInWater() ? SoundEffects.aL : SoundEffects.aM;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect dn() {
/*  81 */     return SoundEffects.aN;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void M() {
/*  86 */     super.M();
/*     */ 
/*     */     
/*  89 */     char c = 'Ұ';
/*  90 */     if ((this.ticksLived + getId()) % 1200 == 0) {
/*  91 */       MobEffectList mobEffectList = MobEffects.SLOWER_DIG;
/*     */       
/*  93 */       List<EntityPlayer> list = this.world.b(EntityPlayer.class, new Predicate<EntityPlayer>(this)
/*     */           {
/*     */             public boolean a(@Nullable EntityPlayer param1EntityPlayer) {
/*  96 */               return (this.a.h(param1EntityPlayer) < 2500.0D && param1EntityPlayer.playerInteractManager.c());
/*     */             }
/*     */           });
/*     */       
/* 100 */       byte b = 2;
/* 101 */       char c1 = 'ᝰ';
/* 102 */       char c2 = 'Ұ';
/*     */       
/* 104 */       for (EntityPlayer entityPlayer : list) {
/* 105 */         if (!entityPlayer.hasEffect(mobEffectList) || entityPlayer.getEffect(mobEffectList).getAmplifier() < 2 || entityPlayer.getEffect(mobEffectList).getDuration() < 1200) {
/* 106 */           entityPlayer.playerConnection.sendPacket(new PacketPlayOutGameStateChange(10, 0.0F));
/* 107 */           entityPlayer.addEffect(new MobEffect(mobEffectList, 6000, 2));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 113 */     if (!dj())
/* 114 */       a(new BlockPosition(this), 16); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityGuardianElder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */