/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityTypes
/*     */ {
/* 107 */   public static final MinecraftKey a = new MinecraftKey("lightning_bolt");
/* 108 */   private static final MinecraftKey e = new MinecraftKey("player");
/*     */   
/* 110 */   private static final Logger f = LogManager.getLogger();
/* 111 */   public static final RegistryMaterials<MinecraftKey, Class<? extends Entity>> b = new RegistryMaterials<>();
/*     */   
/* 113 */   public static final Map<MinecraftKey, MonsterEggInfo> eggInfo = Maps.newLinkedHashMap();
/* 114 */   public static final Set<MinecraftKey> d = Sets.newHashSet();
/* 115 */   private static final List<String> g = Lists.newArrayList();
/*     */   
/*     */   @Nullable
/*     */   public static MinecraftKey a(Entity paramEntity) {
/* 119 */     return getName((Class)paramEntity.getClass());
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static MinecraftKey getName(Class<? extends Entity> paramClass) {
/* 124 */     return b.b(paramClass);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static String b(Entity paramEntity) {
/* 129 */     int i = b.a(paramEntity.getClass());
/* 130 */     return (i == -1) ? null : g.get(i);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static String a(@Nullable MinecraftKey paramMinecraftKey) {
/* 135 */     int i = b.a(b.get(paramMinecraftKey));
/* 136 */     return (i == -1) ? null : g.get(i);
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
/*     */   
/*     */   @Nullable
/*     */   public static Entity a(@Nullable Class<? extends Entity> paramClass, World paramWorld) {
/* 151 */     if (paramClass == null) {
/* 152 */       return null;
/*     */     }
/*     */     try {
/* 155 */       return paramClass.getConstructor(new Class[] { World.class }).newInstance(new Object[] { paramWorld });
/* 156 */     } catch (Exception exception) {
/* 157 */       exception.printStackTrace();
/*     */       
/* 159 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static Entity a(MinecraftKey paramMinecraftKey, World paramWorld) {
/* 169 */     return a(b.get(paramMinecraftKey), paramWorld);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static Entity a(NBTTagCompound paramNBTTagCompound, World paramWorld) {
/* 174 */     MinecraftKey minecraftKey = new MinecraftKey(paramNBTTagCompound.getString("id"));
/*     */     
/* 176 */     Entity entity = a(minecraftKey, paramWorld);
/* 177 */     if (entity == null) {
/* 178 */       f.warn("Skipping Entity with id {}", minecraftKey);
/*     */     } else {
/* 180 */       entity.f(paramNBTTagCompound);
/*     */     } 
/* 182 */     return entity;
/*     */   }
/*     */   
/*     */   public static Set<MinecraftKey> a() {
/* 186 */     return d;
/*     */   }
/*     */   
/*     */   public static boolean a(Entity paramEntity, MinecraftKey paramMinecraftKey) {
/* 190 */     MinecraftKey minecraftKey = getName((Class)paramEntity.getClass());
/* 191 */     if (minecraftKey != null) {
/* 192 */       return minecraftKey.equals(paramMinecraftKey);
/*     */     }
/*     */     
/* 195 */     if (paramEntity instanceof EntityHuman) {
/* 196 */       return e.equals(paramMinecraftKey);
/*     */     }
/* 198 */     if (paramEntity instanceof EntityLightning) {
/* 199 */       return a.equals(paramMinecraftKey);
/*     */     }
/* 201 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean b(MinecraftKey paramMinecraftKey) {
/* 205 */     return (e.equals(paramMinecraftKey) || a().contains(paramMinecraftKey));
/*     */   }
/*     */   
/*     */   public static String b() {
/* 209 */     StringBuilder stringBuilder = new StringBuilder();
/* 210 */     for (MinecraftKey minecraftKey : a()) {
/* 211 */       stringBuilder.append(minecraftKey).append(", ");
/*     */     }
/* 213 */     stringBuilder.append(e);
/* 214 */     return stringBuilder.toString();
/*     */   }
/*     */   
/*     */   public static void c() {
/* 218 */     a(1, "item", (Class)EntityItem.class, "Item");
/* 219 */     a(2, "xp_orb", (Class)EntityExperienceOrb.class, "XPOrb");
/* 220 */     a(3, "area_effect_cloud", (Class)EntityAreaEffectCloud.class, "AreaEffectCloud");
/* 221 */     a(4, "elder_guardian", (Class)EntityGuardianElder.class, "ElderGuardian");
/* 222 */     a(5, "wither_skeleton", (Class)EntitySkeletonWither.class, "WitherSkeleton");
/* 223 */     a(6, "stray", (Class)EntitySkeletonStray.class, "Stray");
/* 224 */     a(7, "egg", (Class)EntityEgg.class, "ThrownEgg");
/* 225 */     a(8, "leash_knot", (Class)EntityLeash.class, "LeashKnot");
/* 226 */     a(9, "painting", (Class)EntityPainting.class, "Painting");
/* 227 */     a(10, "arrow", (Class)EntityTippedArrow.class, "Arrow");
/* 228 */     a(11, "snowball", (Class)EntitySnowball.class, "Snowball");
/* 229 */     a(12, "fireball", (Class)EntityLargeFireball.class, "Fireball");
/* 230 */     a(13, "small_fireball", (Class)EntitySmallFireball.class, "SmallFireball");
/* 231 */     a(14, "ender_pearl", (Class)EntityEnderPearl.class, "ThrownEnderpearl");
/* 232 */     a(15, "eye_of_ender_signal", (Class)EntityEnderSignal.class, "EyeOfEnderSignal");
/* 233 */     a(16, "potion", (Class)EntityPotion.class, "ThrownPotion");
/* 234 */     a(17, "xp_bottle", (Class)EntityThrownExpBottle.class, "ThrownExpBottle");
/* 235 */     a(18, "item_frame", (Class)EntityItemFrame.class, "ItemFrame");
/* 236 */     a(19, "wither_skull", (Class)EntityWitherSkull.class, "WitherSkull");
/* 237 */     a(20, "tnt", (Class)EntityTNTPrimed.class, "PrimedTnt");
/* 238 */     a(21, "falling_block", (Class)EntityFallingBlock.class, "FallingSand");
/* 239 */     a(22, "fireworks_rocket", (Class)EntityFireworks.class, "FireworksRocketEntity");
/* 240 */     a(23, "husk", (Class)EntityZombieHusk.class, "Husk");
/* 241 */     a(24, "spectral_arrow", (Class)EntitySpectralArrow.class, "SpectralArrow");
/* 242 */     a(25, "shulker_bullet", (Class)EntityShulkerBullet.class, "ShulkerBullet");
/* 243 */     a(26, "dragon_fireball", (Class)EntityDragonFireball.class, "DragonFireball");
/* 244 */     a(27, "zombie_villager", (Class)EntityZombieVillager.class, "ZombieVillager");
/* 245 */     a(28, "skeleton_horse", (Class)EntityHorseSkeleton.class, "SkeletonHorse");
/* 246 */     a(29, "zombie_horse", (Class)EntityHorseZombie.class, "ZombieHorse");
/* 247 */     a(30, "armor_stand", (Class)EntityArmorStand.class, "ArmorStand");
/* 248 */     a(31, "donkey", (Class)EntityHorseDonkey.class, "Donkey");
/* 249 */     a(32, "mule", (Class)EntityHorseMule.class, "Mule");
/* 250 */     a(33, "evocation_fangs", (Class)EntityEvokerFangs.class, "EvocationFangs");
/* 251 */     a(34, "evocation_illager", (Class)EntityEvoker.class, "EvocationIllager");
/* 252 */     a(35, "vex", (Class)EntityVex.class, "Vex");
/* 253 */     a(36, "vindication_illager", (Class)EntityVindicator.class, "VindicationIllager");
/* 254 */     a(37, "illusion_illager", (Class)EntityIllagerIllusioner.class, "IllusionIllager");
/*     */ 
/*     */     
/* 257 */     a(40, "commandblock_minecart", (Class)EntityMinecartCommandBlock.class, EntityMinecartAbstract.EnumMinecartType.COMMAND_BLOCK.b());
/* 258 */     a(41, "boat", (Class)EntityBoat.class, "Boat");
/* 259 */     a(42, "minecart", (Class)EntityMinecartRideable.class, EntityMinecartAbstract.EnumMinecartType.RIDEABLE.b());
/* 260 */     a(43, "chest_minecart", (Class)EntityMinecartChest.class, EntityMinecartAbstract.EnumMinecartType.CHEST.b());
/* 261 */     a(44, "furnace_minecart", (Class)EntityMinecartFurnace.class, EntityMinecartAbstract.EnumMinecartType.FURNACE.b());
/* 262 */     a(45, "tnt_minecart", (Class)EntityMinecartTNT.class, EntityMinecartAbstract.EnumMinecartType.TNT.b());
/* 263 */     a(46, "hopper_minecart", (Class)EntityMinecartHopper.class, EntityMinecartAbstract.EnumMinecartType.HOPPER.b());
/* 264 */     a(47, "spawner_minecart", (Class)EntityMinecartMobSpawner.class, EntityMinecartAbstract.EnumMinecartType.SPAWNER.b());
/*     */     
/* 266 */     a(50, "creeper", (Class)EntityCreeper.class, "Creeper");
/* 267 */     a(51, "skeleton", (Class)EntitySkeleton.class, "Skeleton");
/* 268 */     a(52, "spider", (Class)EntitySpider.class, "Spider");
/* 269 */     a(53, "giant", (Class)EntityGiantZombie.class, "Giant");
/* 270 */     a(54, "zombie", (Class)EntityZombie.class, "Zombie");
/* 271 */     a(55, "slime", (Class)EntitySlime.class, "Slime");
/* 272 */     a(56, "ghast", (Class)EntityGhast.class, "Ghast");
/* 273 */     a(57, "zombie_pigman", (Class)EntityPigZombie.class, "PigZombie");
/* 274 */     a(58, "enderman", (Class)EntityEnderman.class, "Enderman");
/* 275 */     a(59, "cave_spider", (Class)EntityCaveSpider.class, "CaveSpider");
/* 276 */     a(60, "silverfish", (Class)EntitySilverfish.class, "Silverfish");
/* 277 */     a(61, "blaze", (Class)EntityBlaze.class, "Blaze");
/* 278 */     a(62, "magma_cube", (Class)EntityMagmaCube.class, "LavaSlime");
/* 279 */     a(63, "ender_dragon", (Class)EntityEnderDragon.class, "EnderDragon");
/* 280 */     a(64, "wither", (Class)EntityWither.class, "WitherBoss");
/* 281 */     a(65, "bat", (Class)EntityBat.class, "Bat");
/* 282 */     a(66, "witch", (Class)EntityWitch.class, "Witch");
/* 283 */     a(67, "endermite", (Class)EntityEndermite.class, "Endermite");
/* 284 */     a(68, "guardian", (Class)EntityGuardian.class, "Guardian");
/* 285 */     a(69, "shulker", (Class)EntityShulker.class, "Shulker");
/*     */     
/* 287 */     a(90, "pig", (Class)EntityPig.class, "Pig");
/* 288 */     a(91, "sheep", (Class)EntitySheep.class, "Sheep");
/* 289 */     a(92, "cow", (Class)EntityCow.class, "Cow");
/* 290 */     a(93, "chicken", (Class)EntityChicken.class, "Chicken");
/* 291 */     a(94, "squid", (Class)EntitySquid.class, "Squid");
/* 292 */     a(95, "wolf", (Class)EntityWolf.class, "Wolf");
/* 293 */     a(96, "mooshroom", (Class)EntityMushroomCow.class, "MushroomCow");
/* 294 */     a(97, "snowman", (Class)EntitySnowman.class, "SnowMan");
/* 295 */     a(98, "ocelot", (Class)EntityOcelot.class, "Ozelot");
/* 296 */     a(99, "villager_golem", (Class)EntityIronGolem.class, "VillagerGolem");
/* 297 */     a(100, "horse", (Class)EntityHorse.class, "Horse");
/* 298 */     a(101, "rabbit", (Class)EntityRabbit.class, "Rabbit");
/* 299 */     a(102, "polar_bear", (Class)EntityPolarBear.class, "PolarBear");
/* 300 */     a(103, "llama", (Class)EntityLlama.class, "Llama");
/* 301 */     a(104, "llama_spit", (Class)EntityLlamaSpit.class, "LlamaSpit");
/* 302 */     a(105, "parrot", (Class)EntityParrot.class, "Parrot");
/*     */     
/* 304 */     a(120, "villager", (Class)EntityVillager.class, "Villager");
/*     */     
/* 306 */     a(200, "ender_crystal", (Class)EntityEnderCrystal.class, "EnderCrystal");
/*     */     
/* 308 */     a("bat", 4996656, 986895);
/* 309 */     a("blaze", 16167425, 16775294);
/* 310 */     a("cave_spider", 803406, 11013646);
/* 311 */     a("chicken", 10592673, 16711680);
/* 312 */     a("cow", 4470310, 10592673);
/* 313 */     a("creeper", 894731, 0);
/* 314 */     a("donkey", 5457209, 8811878);
/* 315 */     a("elder_guardian", 13552826, 7632531);
/* 316 */     a("enderman", 1447446, 0);
/* 317 */     a("endermite", 1447446, 7237230);
/* 318 */     a("evocation_illager", 9804699, 1973274);
/* 319 */     a("ghast", 16382457, 12369084);
/* 320 */     a("guardian", 5931634, 15826224);
/* 321 */     a("horse", 12623485, 15656192);
/* 322 */     a("husk", 7958625, 15125652);
/* 323 */     a("llama", 12623485, 10051392);
/* 324 */     a("magma_cube", 3407872, 16579584);
/* 325 */     a("mooshroom", 10489616, 12040119);
/* 326 */     a("mule", 1769984, 5321501);
/* 327 */     a("ocelot", 15720061, 5653556);
/* 328 */     a("parrot", 894731, 16711680);
/* 329 */     a("pig", 15771042, 14377823);
/* 330 */     a("polar_bear", 15921906, 9803152);
/* 331 */     a("rabbit", 10051392, 7555121);
/* 332 */     a("sheep", 15198183, 16758197);
/* 333 */     a("shulker", 9725844, 5060690);
/* 334 */     a("silverfish", 7237230, 3158064);
/* 335 */     a("skeleton", 12698049, 4802889);
/* 336 */     a("skeleton_horse", 6842447, 15066584);
/* 337 */     a("slime", 5349438, 8306542);
/* 338 */     a("spider", 3419431, 11013646);
/* 339 */     a("squid", 2243405, 7375001);
/* 340 */     a("stray", 6387319, 14543594);
/* 341 */     a("vex", 8032420, 15265265);
/* 342 */     a("villager", 5651507, 12422002);
/* 343 */     a("vindication_illager", 9804699, 2580065);
/* 344 */     a("witch", 3407872, 5349438);
/* 345 */     a("wither_skeleton", 1315860, 4672845);
/* 346 */     a("wolf", 14144467, 13545366);
/* 347 */     a("zombie", 44975, 7969893);
/* 348 */     a("zombie_horse", 3232308, 9945732);
/* 349 */     a("zombie_pigman", 15373203, 5009705);
/* 350 */     a("zombie_villager", 5651507, 7969893);
/*     */     
/* 352 */     d.add(a);
/*     */   }
/*     */   
/*     */   private static void a(int paramInt, String paramString1, Class<? extends Entity> paramClass, String paramString2) {
/*     */     try {
/* 357 */       paramClass.getConstructor(new Class[] { World.class });
/* 358 */     } catch (NoSuchMethodException noSuchMethodException) {
/* 359 */       throw new RuntimeException("Invalid class " + paramClass + " no constructor taking " + World.class.getName());
/*     */     } 
/* 361 */     if ((paramClass.getModifiers() & 0x400) == 1024) {
/* 362 */       throw new RuntimeException("Invalid abstract class " + paramClass);
/*     */     }
/*     */     
/* 365 */     MinecraftKey minecraftKey = new MinecraftKey(paramString1);
/* 366 */     b.a(paramInt, minecraftKey, paramClass);
/* 367 */     d.add(minecraftKey);
/*     */ 
/*     */     
/* 370 */     while (g.size() <= paramInt) {
/* 371 */       g.add(null);
/*     */     }
/* 373 */     g.set(paramInt, paramString2);
/*     */   }
/*     */   
/*     */   protected static MonsterEggInfo a(String paramString, int paramInt1, int paramInt2) {
/* 377 */     MinecraftKey minecraftKey = new MinecraftKey(paramString);
/* 378 */     return eggInfo.put(minecraftKey, new MonsterEggInfo(minecraftKey, paramInt1, paramInt2));
/*     */   }
/*     */   
/*     */   public static class MonsterEggInfo {
/*     */     public final MinecraftKey a;
/*     */     public final int b;
/*     */     public final int c;
/*     */     public final Statistic killEntityStatistic;
/*     */     public final Statistic killedByEntityStatistic;
/*     */     
/*     */     public MonsterEggInfo(MinecraftKey param1MinecraftKey, int param1Int1, int param1Int2) {
/* 389 */       this.a = param1MinecraftKey;
/* 390 */       this.b = param1Int1;
/* 391 */       this.c = param1Int2;
/* 392 */       this.killEntityStatistic = StatisticList.a(this);
/* 393 */       this.killedByEntityStatistic = StatisticList.b(this);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityTypes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */