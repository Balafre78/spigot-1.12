/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DataConverterPotionId
/*     */   implements IDataConverter
/*     */ {
/*   9 */   private static final String[] a = new String[128];
/*     */   
/*     */   static {
/*  12 */     a[0] = "minecraft:water";
/*  13 */     a[1] = "minecraft:regeneration";
/*  14 */     a[2] = "minecraft:swiftness";
/*  15 */     a[3] = "minecraft:fire_resistance";
/*  16 */     a[4] = "minecraft:poison";
/*  17 */     a[5] = "minecraft:healing";
/*  18 */     a[6] = "minecraft:night_vision";
/*  19 */     a[7] = null;
/*  20 */     a[8] = "minecraft:weakness";
/*  21 */     a[9] = "minecraft:strength";
/*  22 */     a[10] = "minecraft:slowness";
/*  23 */     a[11] = "minecraft:leaping";
/*  24 */     a[12] = "minecraft:harming";
/*  25 */     a[13] = "minecraft:water_breathing";
/*  26 */     a[14] = "minecraft:invisibility";
/*  27 */     a[15] = null;
/*  28 */     a[16] = "minecraft:awkward";
/*  29 */     a[17] = "minecraft:regeneration";
/*  30 */     a[18] = "minecraft:swiftness";
/*  31 */     a[19] = "minecraft:fire_resistance";
/*  32 */     a[20] = "minecraft:poison";
/*  33 */     a[21] = "minecraft:healing";
/*  34 */     a[22] = "minecraft:night_vision";
/*  35 */     a[23] = null;
/*  36 */     a[24] = "minecraft:weakness";
/*  37 */     a[25] = "minecraft:strength";
/*  38 */     a[26] = "minecraft:slowness";
/*  39 */     a[27] = "minecraft:leaping";
/*  40 */     a[28] = "minecraft:harming";
/*  41 */     a[29] = "minecraft:water_breathing";
/*  42 */     a[30] = "minecraft:invisibility";
/*  43 */     a[31] = null;
/*  44 */     a[32] = "minecraft:thick";
/*  45 */     a[33] = "minecraft:strong_regeneration";
/*  46 */     a[34] = "minecraft:strong_swiftness";
/*  47 */     a[35] = "minecraft:fire_resistance";
/*  48 */     a[36] = "minecraft:strong_poison";
/*  49 */     a[37] = "minecraft:strong_healing";
/*  50 */     a[38] = "minecraft:night_vision";
/*  51 */     a[39] = null;
/*  52 */     a[40] = "minecraft:weakness";
/*  53 */     a[41] = "minecraft:strong_strength";
/*  54 */     a[42] = "minecraft:slowness";
/*  55 */     a[43] = "minecraft:strong_leaping";
/*  56 */     a[44] = "minecraft:strong_harming";
/*  57 */     a[45] = "minecraft:water_breathing";
/*  58 */     a[46] = "minecraft:invisibility";
/*  59 */     a[47] = null;
/*  60 */     a[48] = null;
/*  61 */     a[49] = "minecraft:strong_regeneration";
/*  62 */     a[50] = "minecraft:strong_swiftness";
/*  63 */     a[51] = "minecraft:fire_resistance";
/*  64 */     a[52] = "minecraft:strong_poison";
/*  65 */     a[53] = "minecraft:strong_healing";
/*  66 */     a[54] = "minecraft:night_vision";
/*  67 */     a[55] = null;
/*  68 */     a[56] = "minecraft:weakness";
/*  69 */     a[57] = "minecraft:strong_strength";
/*  70 */     a[58] = "minecraft:slowness";
/*  71 */     a[59] = "minecraft:strong_leaping";
/*  72 */     a[60] = "minecraft:strong_harming";
/*  73 */     a[61] = "minecraft:water_breathing";
/*  74 */     a[62] = "minecraft:invisibility";
/*  75 */     a[63] = null;
/*  76 */     a[64] = "minecraft:mundane";
/*  77 */     a[65] = "minecraft:long_regeneration";
/*  78 */     a[66] = "minecraft:long_swiftness";
/*  79 */     a[67] = "minecraft:long_fire_resistance";
/*  80 */     a[68] = "minecraft:long_poison";
/*  81 */     a[69] = "minecraft:healing";
/*  82 */     a[70] = "minecraft:long_night_vision";
/*  83 */     a[71] = null;
/*  84 */     a[72] = "minecraft:long_weakness";
/*  85 */     a[73] = "minecraft:long_strength";
/*  86 */     a[74] = "minecraft:long_slowness";
/*  87 */     a[75] = "minecraft:long_leaping";
/*  88 */     a[76] = "minecraft:harming";
/*  89 */     a[77] = "minecraft:long_water_breathing";
/*  90 */     a[78] = "minecraft:long_invisibility";
/*  91 */     a[79] = null;
/*  92 */     a[80] = "minecraft:awkward";
/*  93 */     a[81] = "minecraft:long_regeneration";
/*  94 */     a[82] = "minecraft:long_swiftness";
/*  95 */     a[83] = "minecraft:long_fire_resistance";
/*  96 */     a[84] = "minecraft:long_poison";
/*  97 */     a[85] = "minecraft:healing";
/*  98 */     a[86] = "minecraft:long_night_vision";
/*  99 */     a[87] = null;
/* 100 */     a[88] = "minecraft:long_weakness";
/* 101 */     a[89] = "minecraft:long_strength";
/* 102 */     a[90] = "minecraft:long_slowness";
/* 103 */     a[91] = "minecraft:long_leaping";
/* 104 */     a[92] = "minecraft:harming";
/* 105 */     a[93] = "minecraft:long_water_breathing";
/* 106 */     a[94] = "minecraft:long_invisibility";
/* 107 */     a[95] = null;
/* 108 */     a[96] = "minecraft:thick";
/* 109 */     a[97] = "minecraft:regeneration";
/* 110 */     a[98] = "minecraft:swiftness";
/* 111 */     a[99] = "minecraft:long_fire_resistance";
/* 112 */     a[100] = "minecraft:poison";
/* 113 */     a[101] = "minecraft:strong_healing";
/* 114 */     a[102] = "minecraft:long_night_vision";
/* 115 */     a[103] = null;
/* 116 */     a[104] = "minecraft:long_weakness";
/* 117 */     a[105] = "minecraft:strength";
/* 118 */     a[106] = "minecraft:long_slowness";
/* 119 */     a[107] = "minecraft:leaping";
/* 120 */     a[108] = "minecraft:strong_harming";
/* 121 */     a[109] = "minecraft:long_water_breathing";
/* 122 */     a[110] = "minecraft:long_invisibility";
/* 123 */     a[111] = null;
/* 124 */     a[112] = null;
/* 125 */     a[113] = "minecraft:regeneration";
/* 126 */     a[114] = "minecraft:swiftness";
/* 127 */     a[115] = "minecraft:long_fire_resistance";
/* 128 */     a[116] = "minecraft:poison";
/* 129 */     a[117] = "minecraft:strong_healing";
/* 130 */     a[118] = "minecraft:long_night_vision";
/* 131 */     a[119] = null;
/* 132 */     a[120] = "minecraft:long_weakness";
/* 133 */     a[121] = "minecraft:strength";
/* 134 */     a[122] = "minecraft:long_slowness";
/* 135 */     a[123] = "minecraft:leaping";
/* 136 */     a[124] = "minecraft:strong_harming";
/* 137 */     a[125] = "minecraft:long_water_breathing";
/* 138 */     a[126] = "minecraft:long_invisibility";
/* 139 */     a[127] = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int a() {
/* 146 */     return 102;
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagCompound a(NBTTagCompound paramNBTTagCompound) {
/* 151 */     if ("minecraft:potion".equals(paramNBTTagCompound.getString("id"))) {
/* 152 */       NBTTagCompound nBTTagCompound = paramNBTTagCompound.getCompound("tag");
/* 153 */       short s = paramNBTTagCompound.getShort("Damage");
/*     */       
/* 155 */       if (!nBTTagCompound.hasKeyOfType("Potion", 8)) {
/* 156 */         String str = a[s & 0x7F];
/* 157 */         nBTTagCompound.setString("Potion", (str == null) ? "minecraft:water" : str);
/* 158 */         paramNBTTagCompound.set("tag", nBTTagCompound);
/*     */         
/* 160 */         if ((s & 0x4000) == 16384) {
/* 161 */           paramNBTTagCompound.setString("id", "minecraft:splash_potion");
/*     */         }
/*     */       } 
/*     */       
/* 165 */       if (s != 0) {
/* 166 */         paramNBTTagCompound.setShort("Damage", (short)0);
/*     */       }
/*     */     } 
/*     */     
/* 170 */     return paramNBTTagCompound;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DataConverterPotionId.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */