/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class Biomes
/*    */ {
/*    */   static {
/*  8 */     if (!DispenserRegistry.a()) {
/*  9 */       throw new RuntimeException("Accessed Biomes before Bootstrap!");
/*    */     }
/*    */   }
/*    */   
/* 13 */   public static final BiomeBase a = a("ocean");
/* 14 */   public static final BiomeBase b = a;
/* 15 */   public static final BiomeBase c = a("plains");
/* 16 */   public static final BiomeBase d = a("desert");
/* 17 */   public static final BiomeBase e = a("extreme_hills");
/* 18 */   public static final BiomeBase f = a("forest");
/* 19 */   public static final BiomeBase g = a("taiga");
/* 20 */   public static final BiomeBase h = a("swampland");
/* 21 */   public static final BiomeBase i = a("river");
/* 22 */   public static final BiomeBase j = a("hell");
/* 23 */   public static final BiomeBase k = a("sky");
/* 24 */   public static final BiomeBase l = a("frozen_ocean");
/* 25 */   public static final BiomeBase m = a("frozen_river");
/* 26 */   public static final BiomeBase n = a("ice_flats");
/* 27 */   public static final BiomeBase o = a("ice_mountains");
/* 28 */   public static final BiomeBase p = a("mushroom_island");
/* 29 */   public static final BiomeBase q = a("mushroom_island_shore");
/* 30 */   public static final BiomeBase r = a("beaches");
/* 31 */   public static final BiomeBase s = a("desert_hills");
/* 32 */   public static final BiomeBase t = a("forest_hills");
/* 33 */   public static final BiomeBase u = a("taiga_hills");
/* 34 */   public static final BiomeBase v = a("smaller_extreme_hills");
/* 35 */   public static final BiomeBase w = a("jungle");
/* 36 */   public static final BiomeBase x = a("jungle_hills");
/* 37 */   public static final BiomeBase y = a("jungle_edge");
/* 38 */   public static final BiomeBase z = a("deep_ocean");
/* 39 */   public static final BiomeBase A = a("stone_beach");
/* 40 */   public static final BiomeBase B = a("cold_beach");
/* 41 */   public static final BiomeBase C = a("birch_forest");
/* 42 */   public static final BiomeBase D = a("birch_forest_hills");
/* 43 */   public static final BiomeBase E = a("roofed_forest");
/* 44 */   public static final BiomeBase F = a("taiga_cold");
/* 45 */   public static final BiomeBase G = a("taiga_cold_hills");
/* 46 */   public static final BiomeBase H = a("redwood_taiga");
/* 47 */   public static final BiomeBase I = a("redwood_taiga_hills");
/* 48 */   public static final BiomeBase J = a("extreme_hills_with_trees");
/* 49 */   public static final BiomeBase K = a("savanna");
/* 50 */   public static final BiomeBase L = a("savanna_rock");
/* 51 */   public static final BiomeBase M = a("mesa");
/* 52 */   public static final BiomeBase N = a("mesa_rock");
/* 53 */   public static final BiomeBase O = a("mesa_clear_rock");
/* 54 */   public static final BiomeBase P = a("void");
/* 55 */   public static final BiomeBase Q = a("mutated_plains");
/* 56 */   public static final BiomeBase R = a("mutated_desert");
/* 57 */   public static final BiomeBase S = a("mutated_extreme_hills");
/* 58 */   public static final BiomeBase T = a("mutated_forest");
/* 59 */   public static final BiomeBase U = a("mutated_taiga");
/* 60 */   public static final BiomeBase V = a("mutated_swampland");
/* 61 */   public static final BiomeBase W = a("mutated_ice_flats");
/* 62 */   public static final BiomeBase X = a("mutated_jungle");
/* 63 */   public static final BiomeBase Y = a("mutated_jungle_edge");
/* 64 */   public static final BiomeBase Z = a("mutated_birch_forest");
/* 65 */   public static final BiomeBase aa = a("mutated_birch_forest_hills");
/* 66 */   public static final BiomeBase ab = a("mutated_roofed_forest");
/* 67 */   public static final BiomeBase ac = a("mutated_taiga_cold");
/* 68 */   public static final BiomeBase ad = a("mutated_redwood_taiga");
/* 69 */   public static final BiomeBase ae = a("mutated_redwood_taiga_hills");
/* 70 */   public static final BiomeBase af = a("mutated_extreme_hills_with_trees");
/* 71 */   public static final BiomeBase ag = a("mutated_savanna");
/* 72 */   public static final BiomeBase ah = a("mutated_savanna_rock");
/* 73 */   public static final BiomeBase ai = a("mutated_mesa");
/* 74 */   public static final BiomeBase aj = a("mutated_mesa_rock");
/* 75 */   public static final BiomeBase ak = a("mutated_mesa_clear_rock");
/*    */   
/*    */   private static BiomeBase a(String paramString) {
/* 78 */     BiomeBase biomeBase = BiomeBase.REGISTRY_ID.get(new MinecraftKey(paramString));
/* 79 */     if (biomeBase == null) {
/* 80 */       throw new IllegalStateException("Invalid Biome requested: " + paramString);
/*    */     }
/* 82 */     return biomeBase;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\Biomes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */