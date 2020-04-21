/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import com.google.common.base.Predicates;
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
/*     */ public final class IEntitySelector
/*     */ {
/*  17 */   public static final Predicate<Entity> a = new Predicate<Entity>()
/*     */     {
/*     */       public boolean a(@Nullable Entity param1Entity) {
/*  20 */         return param1Entity.isAlive();
/*     */       }
/*     */     };
/*  23 */   public static final Predicate<Entity> b = new Predicate<Entity>()
/*     */     {
/*     */       public boolean a(@Nullable Entity param1Entity) {
/*  26 */         return (param1Entity.isAlive() && !param1Entity.isVehicle() && !param1Entity.isPassenger());
/*     */       }
/*     */     };
/*  29 */   public static final Predicate<Entity> c = new Predicate<Entity>()
/*     */     {
/*     */       public boolean a(@Nullable Entity param1Entity) {
/*  32 */         return (param1Entity instanceof IInventory && param1Entity.isAlive());
/*     */       }
/*     */     };
/*  35 */   public static final Predicate<Entity> d = new Predicate<Entity>()
/*     */     {
/*     */       public boolean a(@Nullable Entity param1Entity) {
/*  38 */         return (!(param1Entity instanceof EntityHuman) || (!((EntityHuman)param1Entity).isSpectator() && !((EntityHuman)param1Entity).z()));
/*     */       }
/*     */     };
/*  41 */   public static final Predicate<Entity> e = new Predicate<Entity>()
/*     */     {
/*     */       public boolean a(@Nullable Entity param1Entity) {
/*  44 */         return (!(param1Entity instanceof EntityHuman) || !((EntityHuman)param1Entity).isSpectator());
/*     */       }
/*     */     };
/*     */   
/*     */   public static class EntitySelectorEquipable implements Predicate<Entity> {
/*     */     private final ItemStack a;
/*     */     
/*     */     public EntitySelectorEquipable(ItemStack param1ItemStack) {
/*  52 */       this.a = param1ItemStack;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a(@Nullable Entity param1Entity) {
/*  57 */       if (!param1Entity.isAlive()) {
/*  58 */         return false;
/*     */       }
/*  60 */       if (!(param1Entity instanceof EntityLiving)) {
/*  61 */         return false;
/*     */       }
/*  63 */       EntityLiving entityLiving = (EntityLiving)param1Entity;
/*  64 */       if (!entityLiving.getEquipment(EntityInsentient.d(this.a)).isEmpty()) {
/*  65 */         return false;
/*     */       }
/*     */       
/*  68 */       if (entityLiving instanceof EntityInsentient)
/*  69 */         return ((EntityInsentient)entityLiving).cX(); 
/*  70 */       if (entityLiving instanceof EntityArmorStand)
/*  71 */         return true; 
/*  72 */       if (entityLiving instanceof EntityHuman) {
/*  73 */         return true;
/*     */       }
/*     */       
/*  76 */       return false;
/*     */     }
/*     */   }
/*     */   
/*     */   public static <T extends Entity> Predicate<T> a(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4) {
/*  81 */     double d = paramDouble4 * paramDouble4;
/*  82 */     return new Predicate<T>(paramDouble1, paramDouble2, paramDouble3, d)
/*     */       {
/*     */         public boolean a(@Nullable T param1T) {
/*  85 */           return (param1T != null && param1T.d(this.a, this.b, this.c) <= this.d);
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public static <T extends Entity> Predicate<T> a(Entity paramEntity) {
/*  91 */     ScoreboardTeamBase scoreboardTeamBase = paramEntity.aY();
/*  92 */     ScoreboardTeamBase.EnumTeamPush enumTeamPush = (scoreboardTeamBase == null) ? ScoreboardTeamBase.EnumTeamPush.ALWAYS : scoreboardTeamBase.getCollisionRule();
/*  93 */     if (enumTeamPush == ScoreboardTeamBase.EnumTeamPush.NEVER) {
/*  94 */       return Predicates.alwaysFalse();
/*     */     }
/*  96 */     return Predicates.and(e, new Predicate<Entity>(paramEntity, scoreboardTeamBase, enumTeamPush)
/*     */         {
/*     */           public boolean a(@Nullable Entity param1Entity) {
/*  99 */             if (!param1Entity.isCollidable()) {
/* 100 */               return false;
/*     */             }
/* 102 */             if (this.a.world.isClientSide && (!(param1Entity instanceof EntityHuman) || !((EntityHuman)param1Entity).cZ())) {
/* 103 */               return false;
/*     */             }
/* 105 */             ScoreboardTeamBase scoreboardTeamBase = param1Entity.aY();
/* 106 */             ScoreboardTeamBase.EnumTeamPush enumTeamPush = (scoreboardTeamBase == null) ? ScoreboardTeamBase.EnumTeamPush.ALWAYS : scoreboardTeamBase.getCollisionRule();
/* 107 */             if (enumTeamPush == ScoreboardTeamBase.EnumTeamPush.NEVER) {
/* 108 */               return false;
/*     */             }
/* 110 */             boolean bool = (this.b != null && this.b.isAlly(scoreboardTeamBase)) ? true : false;
/* 111 */             if ((this.c == ScoreboardTeamBase.EnumTeamPush.HIDE_FOR_OWN_TEAM || enumTeamPush == ScoreboardTeamBase.EnumTeamPush.HIDE_FOR_OWN_TEAM) && bool) {
/* 112 */               return false;
/*     */             }
/* 114 */             if ((this.c == ScoreboardTeamBase.EnumTeamPush.HIDE_FOR_OTHER_TEAMS || enumTeamPush == ScoreboardTeamBase.EnumTeamPush.HIDE_FOR_OTHER_TEAMS) && !bool) {
/* 115 */               return false;
/*     */             }
/* 117 */             return true;
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public static Predicate<Entity> b(Entity paramEntity) {
/* 123 */     return new Predicate<Entity>(paramEntity)
/*     */       {
/*     */         public boolean a(@Nullable Entity param1Entity) {
/* 126 */           while (param1Entity.isPassenger()) {
/* 127 */             param1Entity = param1Entity.bJ();
/* 128 */             if (param1Entity == this.a) {
/* 129 */               return false;
/*     */             }
/*     */           } 
/* 132 */           return true;
/*     */         }
/*     */       };
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\IEntitySelector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */