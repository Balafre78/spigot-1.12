/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public enum EnumBannerPatternType
/*     */ {
/*  12 */   BASE("base", "b"),
/*  13 */   SQUARE_BOTTOM_LEFT("square_bottom_left", "bl", "   ", "   ", "#  "),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  18 */   SQUARE_BOTTOM_RIGHT("square_bottom_right", "br", "   ", "   ", "  #"),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  23 */   SQUARE_TOP_LEFT("square_top_left", "tl", "#  ", "   ", "   "),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  28 */   SQUARE_TOP_RIGHT("square_top_right", "tr", "  #", "   ", "   "),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  33 */   STRIPE_BOTTOM("stripe_bottom", "bs", "   ", "   ", "###"),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  38 */   STRIPE_TOP("stripe_top", "ts", "###", "   ", "   "),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  43 */   STRIPE_LEFT("stripe_left", "ls", "#  ", "#  ", "#  "),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  48 */   STRIPE_RIGHT("stripe_right", "rs", "  #", "  #", "  #"),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  53 */   STRIPE_CENTER("stripe_center", "cs", " # ", " # ", " # "),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  58 */   STRIPE_MIDDLE("stripe_middle", "ms", "   ", "###", "   "),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  63 */   STRIPE_DOWNRIGHT("stripe_downright", "drs", "#  ", " # ", "  #"),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  68 */   STRIPE_DOWNLEFT("stripe_downleft", "dls", "  #", " # ", "#  "),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  73 */   STRIPE_SMALL("small_stripes", "ss", "# #", "# #", "   "),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  78 */   CROSS("cross", "cr", "# #", " # ", "# #"),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  83 */   STRAIGHT_CROSS("straight_cross", "sc", " # ", "###", " # "),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  88 */   TRIANGLE_BOTTOM("triangle_bottom", "bt", "   ", " # ", "# #"),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  93 */   TRIANGLE_TOP("triangle_top", "tt", "# #", " # ", "   "),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  98 */   TRIANGLES_BOTTOM("triangles_bottom", "bts", "   ", "# #", " # "),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 103 */   TRIANGLES_TOP("triangles_top", "tts", " # ", "# #", "   "),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 108 */   DIAGONAL_LEFT("diagonal_left", "ld", "## ", "#  ", "   "),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 113 */   DIAGONAL_RIGHT("diagonal_up_right", "rd", "   ", "  #", " ##"),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 118 */   DIAGONAL_LEFT_MIRROR("diagonal_up_left", "lud", "   ", "#  ", "## "),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 123 */   DIAGONAL_RIGHT_MIRROR("diagonal_right", "rud", " ##", "  #", "   "),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 128 */   CIRCLE_MIDDLE("circle", "mc", "   ", " # ", "   "),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 133 */   RHOMBUS_MIDDLE("rhombus", "mr", " # ", "# #", " # "),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 138 */   HALF_VERTICAL("half_vertical", "vh", "## ", "## ", "## "),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 143 */   HALF_HORIZONTAL("half_horizontal", "hh", "###", "###", "   "),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 148 */   HALF_VERTICAL_MIRROR("half_vertical_right", "vhr", " ##", " ##", " ##"),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 153 */   HALF_HORIZONTAL_MIRROR("half_horizontal_bottom", "hhb", "   ", "###", "###"),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 158 */   BORDER("border", "bo", "###", "# #", "###"),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 163 */   CURLY_BORDER("curly_border", "cbo", new ItemStack(Blocks.VINE)),
/* 164 */   CREEPER("creeper", "cre", new ItemStack(Items.SKULL, 1, 4)),
/* 165 */   GRADIENT("gradient", "gra", "# #", " # ", " # "),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 170 */   GRADIENT_UP("gradient_up", "gru", " # ", " # ", "# #"),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 175 */   BRICKS("bricks", "bri", new ItemStack(Blocks.BRICK_BLOCK)),
/* 176 */   SKULL("skull", "sku", new ItemStack(Items.SKULL, 1, 1)),
/* 177 */   FLOWER("flower", "flo", new ItemStack(Blocks.RED_FLOWER, 1, BlockFlowers.EnumFlowerVarient.OXEYE_DAISY.b())),
/* 178 */   MOJANG("mojang", "moj", new ItemStack(Items.GOLDEN_APPLE, 1, 1));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 184 */   private final String[] P = new String[3];
/* 185 */   private ItemStack Q = ItemStack.a; private final String N;
/*     */   
/*     */   EnumBannerPatternType(String paramString1, String paramString2) {
/* 188 */     this.N = paramString1;
/* 189 */     this.O = paramString2;
/*     */   }
/*     */   private final String O;
/*     */   
/*     */   EnumBannerPatternType(String paramString1, String paramString2, ItemStack paramItemStack) {
/* 194 */     this.Q = paramItemStack;
/*     */   }
/*     */ 
/*     */   
/*     */   EnumBannerPatternType(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5) {
/* 199 */     this.P[0] = paramString3;
/* 200 */     this.P[1] = paramString4;
/* 201 */     this.P[2] = paramString5;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String b() {
/* 209 */     return this.O;
/*     */   }
/*     */   
/*     */   public String[] c() {
/* 213 */     return this.P;
/*     */   }
/*     */   
/*     */   public boolean d() {
/* 217 */     return (!this.Q.isEmpty() || this.P[0] != null);
/*     */   }
/*     */   
/*     */   public boolean e() {
/* 221 */     return !this.Q.isEmpty();
/*     */   }
/*     */   
/*     */   public ItemStack f() {
/* 225 */     return this.Q;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EnumBannerPatternType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */