/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ public class DataConverterEntity
/*     */   implements IDataConverter
/*     */ {
/*  10 */   private static final Map<String, String> a = Maps.newHashMap();
/*     */   static {
/*  12 */     a.put("AreaEffectCloud", "minecraft:area_effect_cloud");
/*  13 */     a.put("ArmorStand", "minecraft:armor_stand");
/*  14 */     a.put("Arrow", "minecraft:arrow");
/*  15 */     a.put("Bat", "minecraft:bat");
/*  16 */     a.put("Blaze", "minecraft:blaze");
/*  17 */     a.put("Boat", "minecraft:boat");
/*  18 */     a.put("CaveSpider", "minecraft:cave_spider");
/*  19 */     a.put("Chicken", "minecraft:chicken");
/*  20 */     a.put("Cow", "minecraft:cow");
/*  21 */     a.put("Creeper", "minecraft:creeper");
/*  22 */     a.put("Donkey", "minecraft:donkey");
/*  23 */     a.put("DragonFireball", "minecraft:dragon_fireball");
/*  24 */     a.put("ElderGuardian", "minecraft:elder_guardian");
/*  25 */     a.put("EnderCrystal", "minecraft:ender_crystal");
/*  26 */     a.put("EnderDragon", "minecraft:ender_dragon");
/*  27 */     a.put("Enderman", "minecraft:enderman");
/*  28 */     a.put("Endermite", "minecraft:endermite");
/*  29 */     a.put("EyeOfEnderSignal", "minecraft:eye_of_ender_signal");
/*  30 */     a.put("FallingSand", "minecraft:falling_block");
/*  31 */     a.put("Fireball", "minecraft:fireball");
/*  32 */     a.put("FireworksRocketEntity", "minecraft:fireworks_rocket");
/*  33 */     a.put("Ghast", "minecraft:ghast");
/*  34 */     a.put("Giant", "minecraft:giant");
/*  35 */     a.put("Guardian", "minecraft:guardian");
/*  36 */     a.put("Horse", "minecraft:horse");
/*  37 */     a.put("Husk", "minecraft:husk");
/*  38 */     a.put("Item", "minecraft:item");
/*  39 */     a.put("ItemFrame", "minecraft:item_frame");
/*  40 */     a.put("LavaSlime", "minecraft:magma_cube");
/*  41 */     a.put("LeashKnot", "minecraft:leash_knot");
/*  42 */     a.put("MinecartChest", "minecraft:chest_minecart");
/*  43 */     a.put("MinecartCommandBlock", "minecraft:commandblock_minecart");
/*  44 */     a.put("MinecartFurnace", "minecraft:furnace_minecart");
/*  45 */     a.put("MinecartHopper", "minecraft:hopper_minecart");
/*  46 */     a.put("MinecartRideable", "minecraft:minecart");
/*  47 */     a.put("MinecartSpawner", "minecraft:spawner_minecart");
/*  48 */     a.put("MinecartTNT", "minecraft:tnt_minecart");
/*  49 */     a.put("Mule", "minecraft:mule");
/*  50 */     a.put("MushroomCow", "minecraft:mooshroom");
/*  51 */     a.put("Ozelot", "minecraft:ocelot");
/*  52 */     a.put("Painting", "minecraft:painting");
/*  53 */     a.put("Pig", "minecraft:pig");
/*  54 */     a.put("PigZombie", "minecraft:zombie_pigman");
/*  55 */     a.put("PolarBear", "minecraft:polar_bear");
/*  56 */     a.put("PrimedTnt", "minecraft:tnt");
/*  57 */     a.put("Rabbit", "minecraft:rabbit");
/*  58 */     a.put("Sheep", "minecraft:sheep");
/*  59 */     a.put("Shulker", "minecraft:shulker");
/*  60 */     a.put("ShulkerBullet", "minecraft:shulker_bullet");
/*  61 */     a.put("Silverfish", "minecraft:silverfish");
/*  62 */     a.put("Skeleton", "minecraft:skeleton");
/*  63 */     a.put("SkeletonHorse", "minecraft:skeleton_horse");
/*  64 */     a.put("Slime", "minecraft:slime");
/*  65 */     a.put("SmallFireball", "minecraft:small_fireball");
/*  66 */     a.put("SnowMan", "minecraft:snowman");
/*  67 */     a.put("Snowball", "minecraft:snowball");
/*  68 */     a.put("SpectralArrow", "minecraft:spectral_arrow");
/*  69 */     a.put("Spider", "minecraft:spider");
/*  70 */     a.put("Squid", "minecraft:squid");
/*  71 */     a.put("Stray", "minecraft:stray");
/*  72 */     a.put("ThrownEgg", "minecraft:egg");
/*  73 */     a.put("ThrownEnderpearl", "minecraft:ender_pearl");
/*  74 */     a.put("ThrownExpBottle", "minecraft:xp_bottle");
/*  75 */     a.put("ThrownPotion", "minecraft:potion");
/*  76 */     a.put("Villager", "minecraft:villager");
/*  77 */     a.put("VillagerGolem", "minecraft:villager_golem");
/*  78 */     a.put("Witch", "minecraft:witch");
/*  79 */     a.put("WitherBoss", "minecraft:wither");
/*  80 */     a.put("WitherSkeleton", "minecraft:wither_skeleton");
/*  81 */     a.put("WitherSkull", "minecraft:wither_skull");
/*  82 */     a.put("Wolf", "minecraft:wolf");
/*  83 */     a.put("XPOrb", "minecraft:xp_orb");
/*  84 */     a.put("Zombie", "minecraft:zombie");
/*  85 */     a.put("ZombieHorse", "minecraft:zombie_horse");
/*  86 */     a.put("ZombieVillager", "minecraft:zombie_villager");
/*     */   }
/*     */ 
/*     */   
/*     */   public int a() {
/*  91 */     return 704;
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagCompound a(NBTTagCompound paramNBTTagCompound) {
/*  96 */     String str = a.get(paramNBTTagCompound.getString("id"));
/*  97 */     if (str != null) {
/*  98 */       paramNBTTagCompound.setString("id", str);
/*     */     }
/*     */     
/* 101 */     return paramNBTTagCompound;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DataConverterEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */