/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ 
/*     */ public class MaterialMapColor
/*     */ {
/*   6 */   public static final MaterialMapColor[] a = new MaterialMapColor[64];
/*   7 */   public static final MaterialMapColor[] b = new MaterialMapColor[16];
/*     */   
/*   9 */   public static final MaterialMapColor c = new MaterialMapColor(0, 0);
/*  10 */   public static final MaterialMapColor d = new MaterialMapColor(1, 8368696);
/*  11 */   public static final MaterialMapColor e = new MaterialMapColor(2, 16247203);
/*  12 */   public static final MaterialMapColor f = new MaterialMapColor(3, 13092807);
/*  13 */   public static final MaterialMapColor g = new MaterialMapColor(4, 16711680);
/*  14 */   public static final MaterialMapColor h = new MaterialMapColor(5, 10526975);
/*  15 */   public static final MaterialMapColor i = new MaterialMapColor(6, 10987431);
/*  16 */   public static final MaterialMapColor j = new MaterialMapColor(7, 31744);
/*  17 */   public static final MaterialMapColor k = new MaterialMapColor(8, 16777215);
/*  18 */   public static final MaterialMapColor l = new MaterialMapColor(9, 10791096);
/*  19 */   public static final MaterialMapColor m = new MaterialMapColor(10, 9923917);
/*  20 */   public static final MaterialMapColor n = new MaterialMapColor(11, 7368816);
/*  21 */   public static final MaterialMapColor o = new MaterialMapColor(12, 4210943);
/*  22 */   public static final MaterialMapColor p = new MaterialMapColor(13, 9402184);
/*  23 */   public static final MaterialMapColor q = new MaterialMapColor(14, 16776437);
/*  24 */   public static final MaterialMapColor r = new MaterialMapColor(15, 14188339);
/*  25 */   public static final MaterialMapColor s = new MaterialMapColor(16, 11685080);
/*  26 */   public static final MaterialMapColor t = new MaterialMapColor(17, 6724056);
/*  27 */   public static final MaterialMapColor u = new MaterialMapColor(18, 15066419);
/*  28 */   public static final MaterialMapColor v = new MaterialMapColor(19, 8375321);
/*  29 */   public static final MaterialMapColor w = new MaterialMapColor(20, 15892389);
/*  30 */   public static final MaterialMapColor x = new MaterialMapColor(21, 5000268);
/*  31 */   public static final MaterialMapColor y = new MaterialMapColor(22, 10066329);
/*  32 */   public static final MaterialMapColor z = new MaterialMapColor(23, 5013401);
/*  33 */   public static final MaterialMapColor A = new MaterialMapColor(24, 8339378);
/*  34 */   public static final MaterialMapColor B = new MaterialMapColor(25, 3361970);
/*  35 */   public static final MaterialMapColor C = new MaterialMapColor(26, 6704179);
/*  36 */   public static final MaterialMapColor D = new MaterialMapColor(27, 6717235);
/*  37 */   public static final MaterialMapColor E = new MaterialMapColor(28, 10040115);
/*  38 */   public static final MaterialMapColor F = new MaterialMapColor(29, 1644825);
/*  39 */   public static final MaterialMapColor G = new MaterialMapColor(30, 16445005);
/*  40 */   public static final MaterialMapColor H = new MaterialMapColor(31, 6085589);
/*  41 */   public static final MaterialMapColor I = new MaterialMapColor(32, 4882687);
/*  42 */   public static final MaterialMapColor J = new MaterialMapColor(33, 55610);
/*  43 */   public static final MaterialMapColor K = new MaterialMapColor(34, 8476209);
/*  44 */   public static final MaterialMapColor L = new MaterialMapColor(35, 7340544);
/*     */   
/*  46 */   public static final MaterialMapColor M = new MaterialMapColor(36, 13742497);
/*  47 */   public static final MaterialMapColor N = new MaterialMapColor(37, 10441252);
/*  48 */   public static final MaterialMapColor O = new MaterialMapColor(38, 9787244);
/*  49 */   public static final MaterialMapColor P = new MaterialMapColor(39, 7367818);
/*  50 */   public static final MaterialMapColor Q = new MaterialMapColor(40, 12223780);
/*  51 */   public static final MaterialMapColor R = new MaterialMapColor(41, 6780213);
/*  52 */   public static final MaterialMapColor S = new MaterialMapColor(42, 10505550);
/*  53 */   public static final MaterialMapColor T = new MaterialMapColor(43, 3746083);
/*  54 */   public static final MaterialMapColor U = new MaterialMapColor(44, 8874850);
/*  55 */   public static final MaterialMapColor V = new MaterialMapColor(45, 5725276);
/*  56 */   public static final MaterialMapColor W = new MaterialMapColor(46, 8014168);
/*  57 */   public static final MaterialMapColor X = new MaterialMapColor(47, 4996700);
/*  58 */   public static final MaterialMapColor Y = new MaterialMapColor(48, 4993571);
/*  59 */   public static final MaterialMapColor Z = new MaterialMapColor(49, 5001770);
/*  60 */   public static final MaterialMapColor aa = new MaterialMapColor(50, 9321518);
/*  61 */   public static final MaterialMapColor ab = new MaterialMapColor(51, 2430480);
/*     */   
/*     */   static {
/*  64 */     b[EnumColor.WHITE.getColorIndex()] = k;
/*  65 */     b[EnumColor.ORANGE.getColorIndex()] = r;
/*  66 */     b[EnumColor.MAGENTA.getColorIndex()] = s;
/*  67 */     b[EnumColor.LIGHT_BLUE.getColorIndex()] = t;
/*  68 */     b[EnumColor.YELLOW.getColorIndex()] = u;
/*  69 */     b[EnumColor.LIME.getColorIndex()] = v;
/*  70 */     b[EnumColor.PINK.getColorIndex()] = w;
/*  71 */     b[EnumColor.GRAY.getColorIndex()] = x;
/*  72 */     b[EnumColor.SILVER.getColorIndex()] = y;
/*  73 */     b[EnumColor.CYAN.getColorIndex()] = z;
/*  74 */     b[EnumColor.PURPLE.getColorIndex()] = A;
/*  75 */     b[EnumColor.BLUE.getColorIndex()] = B;
/*  76 */     b[EnumColor.BROWN.getColorIndex()] = C;
/*  77 */     b[EnumColor.GREEN.getColorIndex()] = D;
/*  78 */     b[EnumColor.RED.getColorIndex()] = E;
/*  79 */     b[EnumColor.BLACK.getColorIndex()] = F;
/*     */   }
/*     */   
/*     */   public final int ac;
/*     */   public final int ad;
/*     */   
/*     */   private MaterialMapColor(int paramInt1, int paramInt2) {
/*  86 */     if (paramInt1 < 0 || paramInt1 > 63) {
/*  87 */       throw new IndexOutOfBoundsException("Map colour ID must be between 0 and 63 (inclusive)");
/*     */     }
/*  89 */     this.ad = paramInt1;
/*  90 */     this.ac = paramInt2;
/*  91 */     a[paramInt1] = this;
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
/*     */   public static MaterialMapColor a(EnumColor paramEnumColor) {
/* 117 */     return b[paramEnumColor.getColorIndex()];
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\MaterialMapColor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */