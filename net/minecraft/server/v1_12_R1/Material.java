/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ public class Material {
/*   4 */   public static final Material AIR = new MaterialGas(MaterialMapColor.c);
/*   5 */   public static final Material GRASS = new Material(MaterialMapColor.d);
/*   6 */   public static final Material EARTH = new Material(MaterialMapColor.m);
/*   7 */   public static final Material WOOD = (new Material(MaterialMapColor.p)).g();
/*   8 */   public static final Material STONE = (new Material(MaterialMapColor.n)).f();
/*   9 */   public static final Material ORE = (new Material(MaterialMapColor.i)).f();
/*  10 */   public static final Material HEAVY = (new Material(MaterialMapColor.i)).f().o();
/*  11 */   public static final Material WATER = (new MaterialLiquid(MaterialMapColor.o)).n();
/*  12 */   public static final Material LAVA = (new MaterialLiquid(MaterialMapColor.g)).n();
/*  13 */   public static final Material LEAVES = (new Material(MaterialMapColor.j)).g().s().n();
/*  14 */   public static final Material PLANT = (new MaterialDecoration(MaterialMapColor.j)).n();
/*  15 */   public static final Material REPLACEABLE_PLANT = (new MaterialDecoration(MaterialMapColor.j)).g().n().i();
/*  16 */   public static final Material SPONGE = new Material(MaterialMapColor.u);
/*  17 */   public static final Material CLOTH = (new Material(MaterialMapColor.f)).g();
/*  18 */   public static final Material FIRE = (new MaterialGas(MaterialMapColor.c)).n();
/*  19 */   public static final Material SAND = new Material(MaterialMapColor.e);
/*  20 */   public static final Material ORIENTABLE = (new MaterialDecoration(MaterialMapColor.c)).n();
/*  21 */   public static final Material WOOL = (new MaterialDecoration(MaterialMapColor.f)).g();
/*  22 */   public static final Material SHATTERABLE = (new Material(MaterialMapColor.c)).s().p();
/*  23 */   public static final Material BUILDABLE_GLASS = (new Material(MaterialMapColor.c)).p();
/*  24 */   public static final Material TNT = (new Material(MaterialMapColor.g)).g().s();
/*  25 */   public static final Material CORAL = (new Material(MaterialMapColor.j)).n();
/*  26 */   public static final Material ICE = (new Material(MaterialMapColor.h)).s().p();
/*  27 */   public static final Material SNOW_LAYER = (new Material(MaterialMapColor.h)).p();
/*  28 */   public static final Material PACKED_ICE = (new MaterialDecoration(MaterialMapColor.k)).i().s().f().n();
/*  29 */   public static final Material SNOW_BLOCK = (new Material(MaterialMapColor.k)).f();
/*  30 */   public static final Material CACTUS = (new Material(MaterialMapColor.j)).s().n();
/*  31 */   public static final Material CLAY = new Material(MaterialMapColor.l);
/*  32 */   public static final Material PUMPKIN = (new Material(MaterialMapColor.j)).n();
/*  33 */   public static final Material DRAGON_EGG = (new Material(MaterialMapColor.j)).n();
/*  34 */   public static final Material PORTAL = (new MaterialPortal(MaterialMapColor.c)).o();
/*  35 */   public static final Material CAKE = (new Material(MaterialMapColor.c)).n();
/*  36 */   public static final Material WEB = (new Material(MaterialMapColor.f)
/*     */     {
/*     */       public boolean isSolid() {
/*  39 */         return false;
/*     */       }
/*  41 */     }).f().n();
/*  42 */   public static final Material PISTON = (new Material(MaterialMapColor.n)).o();
/*  43 */   public static final Material BANNER = (new Material(MaterialMapColor.c)).f().o();
/*  44 */   public static final Material J = new MaterialGas(MaterialMapColor.c); private boolean canBurn;
/*     */   private boolean L;
/*     */   private boolean M;
/*     */   private final MaterialMapColor N;
/*     */   private boolean O = true;
/*  49 */   private EnumPistonReaction P = EnumPistonReaction.NORMAL;
/*     */   private boolean Q;
/*     */   
/*     */   public Material(MaterialMapColor paramMaterialMapColor) {
/*  53 */     this.N = paramMaterialMapColor;
/*     */   }
/*     */   
/*     */   public boolean isLiquid() {
/*  57 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isBuildable() {
/*  65 */     return true;
/*     */   }
/*     */   
/*     */   public boolean blocksLight() {
/*  69 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isSolid() {
/*  73 */     return true;
/*     */   }
/*     */   
/*     */   private Material s() {
/*  77 */     this.M = true;
/*  78 */     return this;
/*     */   }
/*     */   
/*     */   protected Material f() {
/*  82 */     this.O = false;
/*  83 */     return this;
/*     */   }
/*     */   
/*     */   protected Material g() {
/*  87 */     this.canBurn = true;
/*  88 */     return this;
/*     */   }
/*     */   
/*     */   public boolean isBurnable() {
/*  92 */     return this.canBurn;
/*     */   }
/*     */   
/*     */   public Material i() {
/*  96 */     this.L = true;
/*  97 */     return this;
/*     */   }
/*     */   
/*     */   public boolean isReplaceable() {
/* 101 */     return this.L;
/*     */   }
/*     */   
/*     */   public boolean k() {
/* 105 */     if (this.M) {
/* 106 */       return false;
/*     */     }
/* 108 */     return isSolid();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAlwaysDestroyable() {
/* 114 */     return this.O;
/*     */   }
/*     */   
/*     */   public EnumPistonReaction getPushReaction() {
/* 118 */     return this.P;
/*     */   }
/*     */   
/*     */   protected Material n() {
/* 122 */     this.P = EnumPistonReaction.DESTROY;
/* 123 */     return this;
/*     */   }
/*     */   
/*     */   protected Material o() {
/* 127 */     this.P = EnumPistonReaction.BLOCK;
/* 128 */     return this;
/*     */   }
/*     */   
/*     */   protected Material p() {
/* 132 */     this.Q = true;
/* 133 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MaterialMapColor r() {
/* 141 */     return this.N;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\Material.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */