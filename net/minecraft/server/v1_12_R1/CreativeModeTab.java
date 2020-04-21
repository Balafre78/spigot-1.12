/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class CreativeModeTab
/*     */ {
/*  13 */   public static final CreativeModeTab[] a = new CreativeModeTab[12];
/*  14 */   public static final CreativeModeTab b = new CreativeModeTab(0, "buildingBlocks")
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */   
/*  20 */   public static final CreativeModeTab c = new CreativeModeTab(1, "decorations")
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */   
/*  26 */   public static final CreativeModeTab d = new CreativeModeTab(2, "redstone")
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */   
/*  32 */   public static final CreativeModeTab e = new CreativeModeTab(3, "transportation")
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */   
/*  38 */   public static final CreativeModeTab f = new CreativeModeTab(6, "misc")
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */   
/*  44 */   public static final CreativeModeTab g = (new CreativeModeTab(5, "search")
/*     */     {
/*     */ 
/*     */ 
/*     */     
/*  49 */     }).a("item_search.png");
/*     */   
/*  51 */   public static final CreativeModeTab h = new CreativeModeTab(7, "food")
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */   
/*  57 */   public static final CreativeModeTab i = (new CreativeModeTab(8, "tools")
/*     */     {
/*     */ 
/*     */ 
/*     */     
/*  62 */     }).a(new EnchantmentSlotType[] { EnchantmentSlotType.ALL, EnchantmentSlotType.DIGGER, EnchantmentSlotType.FISHING_ROD, EnchantmentSlotType.BREAKABLE });
/*  63 */   public static final CreativeModeTab j = (new CreativeModeTab(9, "combat")
/*     */     {
/*     */ 
/*     */ 
/*     */     
/*  68 */     }).a(new EnchantmentSlotType[] { EnchantmentSlotType.ALL, EnchantmentSlotType.ARMOR, EnchantmentSlotType.ARMOR_FEET, EnchantmentSlotType.ARMOR_HEAD, EnchantmentSlotType.ARMOR_LEGS, EnchantmentSlotType.ARMOR_CHEST, EnchantmentSlotType.BOW, EnchantmentSlotType.WEAPON, EnchantmentSlotType.WEARABLE, EnchantmentSlotType.BREAKABLE });
/*  69 */   public static final CreativeModeTab k = new CreativeModeTab(10, "brewing")
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */   
/*  75 */   public static final CreativeModeTab l = f;
/*  76 */   public static final CreativeModeTab m = new CreativeModeTab(4, "hotbar")
/*     */     {
/*     */     
/*     */     };
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
/*  91 */   public static final CreativeModeTab n = (new CreativeModeTab(11, "inventory")
/*     */     {
/*     */ 
/*     */ 
/*     */     
/*  96 */     }).a("inventory.png").j().h();
/*     */   
/*     */   private final int o;
/*     */   private final String p;
/* 100 */   private String q = "items.png";
/*     */   private boolean r = true;
/*     */   private boolean s = true;
/* 103 */   private EnchantmentSlotType[] t = new EnchantmentSlotType[0];
/*     */   private ItemStack u;
/*     */   
/*     */   public CreativeModeTab(int paramInt, String paramString) {
/* 107 */     this.o = paramInt;
/* 108 */     this.p = paramString;
/*     */     
/* 110 */     this.u = ItemStack.a;
/*     */     
/* 112 */     a[paramInt] = this;
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
/*     */   public CreativeModeTab a(String paramString) {
/* 141 */     this.q = paramString;
/* 142 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CreativeModeTab h() {
/* 150 */     this.s = false;
/* 151 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CreativeModeTab j() {
/* 159 */     this.r = false;
/* 160 */     return this;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CreativeModeTab a(EnchantmentSlotType... paramVarArgs) {
/* 180 */     this.t = paramVarArgs;
/* 181 */     return this;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CreativeModeTab.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */