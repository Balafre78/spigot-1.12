/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityPositionTypes
/*     */ {
/*  49 */   private static final Map<Class<?>, EntityInsentient.EnumEntityPositionType> a = Maps.newHashMap();
/*     */   
/*     */   public static EntityInsentient.EnumEntityPositionType a(Class<?> paramClass) {
/*  52 */     return a.get(paramClass);
/*     */   }
/*     */ 
/*     */   
/*     */   static {
/*  57 */     a.put(EntityBat.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
/*  58 */     a.put(EntityChicken.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
/*  59 */     a.put(EntityCow.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
/*  60 */     a.put(EntityHorse.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
/*  61 */     a.put(EntityHorseSkeleton.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
/*  62 */     a.put(EntityHorseZombie.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
/*  63 */     a.put(EntityHorseDonkey.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
/*  64 */     a.put(EntityHorseMule.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
/*  65 */     a.put(EntityMushroomCow.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
/*  66 */     a.put(EntityOcelot.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
/*  67 */     a.put(EntityPig.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
/*  68 */     a.put(EntityRabbit.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
/*  69 */     a.put(EntityParrot.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
/*  70 */     a.put(EntitySheep.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
/*  71 */     a.put(EntitySnowman.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
/*  72 */     a.put(EntitySquid.class, EntityInsentient.EnumEntityPositionType.IN_WATER);
/*  73 */     a.put(EntityIronGolem.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
/*  74 */     a.put(EntityWolf.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
/*     */     
/*  76 */     a.put(EntityVillager.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
/*     */     
/*  78 */     a.put(EntityEnderDragon.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
/*  79 */     a.put(EntityWither.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
/*     */     
/*  81 */     a.put(EntityBlaze.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
/*  82 */     a.put(EntityCaveSpider.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
/*  83 */     a.put(EntityCreeper.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
/*  84 */     a.put(EntityEnderman.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
/*  85 */     a.put(EntityEndermite.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
/*  86 */     a.put(EntityGhast.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
/*  87 */     a.put(EntityGiantZombie.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
/*  88 */     a.put(EntityGuardian.class, EntityInsentient.EnumEntityPositionType.IN_WATER);
/*  89 */     a.put(EntityMagmaCube.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
/*  90 */     a.put(EntityPigZombie.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
/*  91 */     a.put(EntitySilverfish.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
/*  92 */     a.put(EntitySkeleton.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
/*  93 */     a.put(EntitySkeletonWither.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
/*  94 */     a.put(EntitySkeletonStray.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
/*  95 */     a.put(EntitySlime.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
/*  96 */     a.put(EntitySpider.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
/*  97 */     a.put(EntityWitch.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
/*  98 */     a.put(EntityZombie.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
/*  99 */     a.put(EntityZombieVillager.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
/* 100 */     a.put(EntityZombieHusk.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityPositionTypes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */