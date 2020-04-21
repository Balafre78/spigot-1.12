/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ 
/*     */ public enum EnumColor
/*     */   implements INamable
/*     */ {
/*   7 */   WHITE(0, 15, "white", "white", 16383998, EnumChatFormat.WHITE),
/*   8 */   ORANGE(1, 14, "orange", "orange", 16351261, EnumChatFormat.GOLD),
/*   9 */   MAGENTA(2, 13, "magenta", "magenta", 13061821, EnumChatFormat.AQUA),
/*  10 */   LIGHT_BLUE(3, 12, "light_blue", "lightBlue", 3847130, EnumChatFormat.BLUE),
/*  11 */   YELLOW(4, 11, "yellow", "yellow", 16701501, EnumChatFormat.YELLOW),
/*  12 */   LIME(5, 10, "lime", "lime", 8439583, EnumChatFormat.GREEN),
/*  13 */   PINK(6, 9, "pink", "pink", 15961002, EnumChatFormat.LIGHT_PURPLE),
/*  14 */   GRAY(7, 8, "gray", "gray", 4673362, EnumChatFormat.DARK_GRAY),
/*  15 */   SILVER(8, 7, "silver", "silver", 10329495, EnumChatFormat.GRAY),
/*  16 */   CYAN(9, 6, "cyan", "cyan", 1481884, EnumChatFormat.DARK_AQUA),
/*  17 */   PURPLE(10, 5, "purple", "purple", 8991416, EnumChatFormat.DARK_PURPLE),
/*  18 */   BLUE(11, 4, "blue", "blue", 3949738, EnumChatFormat.DARK_BLUE),
/*  19 */   BROWN(12, 3, "brown", "brown", 8606770, EnumChatFormat.GOLD),
/*  20 */   GREEN(13, 2, "green", "green", 6192150, EnumChatFormat.DARK_GREEN),
/*  21 */   RED(14, 1, "red", "red", 11546150, EnumChatFormat.DARK_RED),
/*  22 */   BLACK(15, 0, "black", "black", 1908001, EnumChatFormat.BLACK);
/*     */   
/*     */   private static final EnumColor[] q;
/*     */   
/*     */   private static final EnumColor[] r;
/*     */   private final int s;
/*     */   private final int t;
/*     */   private final String u;
/*     */   private final String v;
/*     */   private final int w;
/*     */   private final float[] x;
/*     */   private final EnumChatFormat y;
/*     */   
/*     */   static {
/*  36 */     q = new EnumColor[(values()).length];
/*  37 */     r = new EnumColor[(values()).length];
/*     */     
/*  39 */     for (EnumColor enumColor : values()) {
/*  40 */       q[enumColor.getColorIndex()] = enumColor;
/*  41 */       r[enumColor.getInvColorIndex()] = enumColor;
/*     */     } 
/*     */   }
/*     */   
/*     */   EnumColor(int paramInt1, int paramInt2, String paramString1, String paramString2, int paramInt3, EnumChatFormat paramEnumChatFormat) {
/*  46 */     this.s = paramInt1;
/*  47 */     this.t = paramInt2;
/*  48 */     this.u = paramString1;
/*  49 */     this.v = paramString2;
/*  50 */     this.w = paramInt3;
/*  51 */     this.y = paramEnumChatFormat;
/*     */     
/*  53 */     int i = (paramInt3 & 0xFF0000) >> 16;
/*  54 */     int j = (paramInt3 & 0xFF00) >> 8;
/*  55 */     int k = (paramInt3 & 0xFF) >> 0;
/*  56 */     this.x = new float[] { i / 255.0F, j / 255.0F, k / 255.0F };
/*     */   }
/*     */   
/*     */   public int getColorIndex() {
/*  60 */     return this.s;
/*     */   }
/*     */   
/*     */   public int getInvColorIndex() {
/*  64 */     return this.t;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String d() {
/*  72 */     return this.v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] f() {
/*  80 */     return this.x;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static EnumColor fromInvColorIndex(int paramInt) {
/*  88 */     if (paramInt < 0 || paramInt >= r.length) {
/*  89 */       paramInt = 0;
/*     */     }
/*  91 */     return r[paramInt];
/*     */   }
/*     */   
/*     */   public static EnumColor fromColorIndex(int paramInt) {
/*  95 */     if (paramInt < 0 || paramInt >= q.length) {
/*  96 */       paramInt = 0;
/*     */     }
/*  98 */     return q[paramInt];
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 103 */     return this.v;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/* 108 */     return this.u;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EnumColor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */