/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.Collections;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ public class LootTables
/*     */ {
/*  10 */   private static final Set<MinecraftKey> aE = Sets.newHashSet();
/*  11 */   private static final Set<MinecraftKey> aF = Collections.unmodifiableSet(aE);
/*     */   
/*  13 */   public static final MinecraftKey a = a("empty");
/*     */ 
/*     */   
/*  16 */   public static final MinecraftKey b = a("chests/spawn_bonus_chest");
/*  17 */   public static final MinecraftKey c = a("chests/end_city_treasure");
/*  18 */   public static final MinecraftKey d = a("chests/simple_dungeon");
/*  19 */   public static final MinecraftKey e = a("chests/village_blacksmith");
/*  20 */   public static final MinecraftKey f = a("chests/abandoned_mineshaft");
/*  21 */   public static final MinecraftKey g = a("chests/nether_bridge");
/*  22 */   public static final MinecraftKey h = a("chests/stronghold_library");
/*  23 */   public static final MinecraftKey i = a("chests/stronghold_crossing");
/*  24 */   public static final MinecraftKey j = a("chests/stronghold_corridor");
/*  25 */   public static final MinecraftKey k = a("chests/desert_pyramid");
/*  26 */   public static final MinecraftKey l = a("chests/jungle_temple");
/*  27 */   public static final MinecraftKey m = a("chests/jungle_temple_dispenser");
/*  28 */   public static final MinecraftKey n = a("chests/igloo_chest");
/*  29 */   public static final MinecraftKey o = a("chests/woodland_mansion");
/*     */ 
/*     */   
/*  32 */   public static final MinecraftKey p = a("entities/witch");
/*  33 */   public static final MinecraftKey q = a("entities/blaze");
/*  34 */   public static final MinecraftKey r = a("entities/creeper");
/*  35 */   public static final MinecraftKey s = a("entities/spider");
/*  36 */   public static final MinecraftKey t = a("entities/cave_spider");
/*  37 */   public static final MinecraftKey u = a("entities/giant");
/*  38 */   public static final MinecraftKey v = a("entities/silverfish");
/*  39 */   public static final MinecraftKey w = a("entities/enderman");
/*  40 */   public static final MinecraftKey x = a("entities/guardian");
/*  41 */   public static final MinecraftKey y = a("entities/elder_guardian");
/*  42 */   public static final MinecraftKey z = a("entities/shulker");
/*  43 */   public static final MinecraftKey A = a("entities/iron_golem");
/*  44 */   public static final MinecraftKey B = a("entities/snowman");
/*  45 */   public static final MinecraftKey C = a("entities/rabbit");
/*  46 */   public static final MinecraftKey D = a("entities/chicken");
/*  47 */   public static final MinecraftKey E = a("entities/pig");
/*  48 */   public static final MinecraftKey F = a("entities/polar_bear");
/*  49 */   public static final MinecraftKey G = a("entities/horse");
/*  50 */   public static final MinecraftKey H = a("entities/donkey");
/*  51 */   public static final MinecraftKey I = a("entities/mule");
/*  52 */   public static final MinecraftKey J = a("entities/zombie_horse");
/*  53 */   public static final MinecraftKey K = a("entities/skeleton_horse");
/*  54 */   public static final MinecraftKey L = a("entities/cow");
/*  55 */   public static final MinecraftKey M = a("entities/mushroom_cow");
/*  56 */   public static final MinecraftKey N = a("entities/wolf");
/*  57 */   public static final MinecraftKey O = a("entities/ocelot");
/*  58 */   public static final MinecraftKey P = a("entities/sheep");
/*  59 */   public static final MinecraftKey Q = a("entities/sheep/white");
/*  60 */   public static final MinecraftKey R = a("entities/sheep/orange");
/*  61 */   public static final MinecraftKey S = a("entities/sheep/magenta");
/*  62 */   public static final MinecraftKey T = a("entities/sheep/light_blue");
/*  63 */   public static final MinecraftKey U = a("entities/sheep/yellow");
/*  64 */   public static final MinecraftKey V = a("entities/sheep/lime");
/*  65 */   public static final MinecraftKey W = a("entities/sheep/pink");
/*  66 */   public static final MinecraftKey X = a("entities/sheep/gray");
/*  67 */   public static final MinecraftKey Y = a("entities/sheep/silver");
/*  68 */   public static final MinecraftKey Z = a("entities/sheep/cyan");
/*  69 */   public static final MinecraftKey aa = a("entities/sheep/purple");
/*  70 */   public static final MinecraftKey ab = a("entities/sheep/blue");
/*  71 */   public static final MinecraftKey ac = a("entities/sheep/brown");
/*  72 */   public static final MinecraftKey ad = a("entities/sheep/green");
/*  73 */   public static final MinecraftKey ae = a("entities/sheep/red");
/*  74 */   public static final MinecraftKey af = a("entities/sheep/black");
/*  75 */   public static final MinecraftKey ag = a("entities/bat");
/*  76 */   public static final MinecraftKey ah = a("entities/slime");
/*  77 */   public static final MinecraftKey ai = a("entities/magma_cube");
/*  78 */   public static final MinecraftKey aj = a("entities/ghast");
/*  79 */   public static final MinecraftKey ak = a("entities/squid");
/*  80 */   public static final MinecraftKey al = a("entities/endermite");
/*  81 */   public static final MinecraftKey am = a("entities/zombie");
/*  82 */   public static final MinecraftKey an = a("entities/zombie_pigman");
/*  83 */   public static final MinecraftKey ao = a("entities/skeleton");
/*  84 */   public static final MinecraftKey ap = a("entities/wither_skeleton");
/*  85 */   public static final MinecraftKey aq = a("entities/stray");
/*  86 */   public static final MinecraftKey ar = a("entities/husk");
/*  87 */   public static final MinecraftKey as = a("entities/zombie_villager");
/*  88 */   public static final MinecraftKey at = a("entities/villager");
/*  89 */   public static final MinecraftKey au = a("entities/evocation_illager");
/*  90 */   public static final MinecraftKey av = a("entities/vindication_illager");
/*  91 */   public static final MinecraftKey aw = a("entities/llama");
/*  92 */   public static final MinecraftKey ax = a("entities/parrot");
/*  93 */   public static final MinecraftKey ay = a("entities/vex");
/*  94 */   public static final MinecraftKey az = a("entities/ender_dragon");
/*     */ 
/*     */   
/*  97 */   public static final MinecraftKey aA = a("gameplay/fishing");
/*  98 */   public static final MinecraftKey aB = a("gameplay/fishing/junk");
/*  99 */   public static final MinecraftKey aC = a("gameplay/fishing/treasure");
/* 100 */   public static final MinecraftKey aD = a("gameplay/fishing/fish");
/*     */   
/*     */   private static MinecraftKey a(String paramString) {
/* 103 */     return a(new MinecraftKey("minecraft", paramString));
/*     */   }
/*     */   
/*     */   public static MinecraftKey a(MinecraftKey paramMinecraftKey) {
/* 107 */     if (aE.add(paramMinecraftKey)) {
/* 108 */       return paramMinecraftKey;
/*     */     }
/*     */     
/* 111 */     throw new IllegalArgumentException(paramMinecraftKey + " is already a registered built-in loot table");
/*     */   }
/*     */   
/*     */   public static Set<MinecraftKey> a() {
/* 115 */     return aF;
/*     */   }
/*     */   
/*     */   public static boolean b() {
/* 119 */     LootTableRegistry lootTableRegistry = new LootTableRegistry(null);
/* 120 */     for (MinecraftKey minecraftKey : aF) {
/* 121 */       if (lootTableRegistry.a(minecraftKey) == LootTable.a) {
/* 122 */         return false;
/*     */       }
/*     */     } 
/* 125 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\LootTables.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */