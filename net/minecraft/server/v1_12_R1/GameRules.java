/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.Set;
/*     */ import java.util.TreeMap;
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
/*     */ public class GameRules
/*     */ {
/*  36 */   private final TreeMap<String, GameRuleValue> a = new TreeMap<>();
/*     */   
/*     */   public GameRules() {
/*  39 */     a("doFireTick", "true", EnumGameRuleType.BOOLEAN_VALUE);
/*  40 */     a("mobGriefing", "true", EnumGameRuleType.BOOLEAN_VALUE);
/*  41 */     a("keepInventory", "false", EnumGameRuleType.BOOLEAN_VALUE);
/*  42 */     a("doMobSpawning", "true", EnumGameRuleType.BOOLEAN_VALUE);
/*  43 */     a("doMobLoot", "true", EnumGameRuleType.BOOLEAN_VALUE);
/*  44 */     a("doTileDrops", "true", EnumGameRuleType.BOOLEAN_VALUE);
/*  45 */     a("doEntityDrops", "true", EnumGameRuleType.BOOLEAN_VALUE);
/*  46 */     a("commandBlockOutput", "true", EnumGameRuleType.BOOLEAN_VALUE);
/*  47 */     a("naturalRegeneration", "true", EnumGameRuleType.BOOLEAN_VALUE);
/*  48 */     a("doDaylightCycle", "true", EnumGameRuleType.BOOLEAN_VALUE);
/*  49 */     a("logAdminCommands", "true", EnumGameRuleType.BOOLEAN_VALUE);
/*  50 */     a("showDeathMessages", "true", EnumGameRuleType.BOOLEAN_VALUE);
/*  51 */     a("randomTickSpeed", "3", EnumGameRuleType.NUMERICAL_VALUE);
/*  52 */     a("sendCommandFeedback", "true", EnumGameRuleType.BOOLEAN_VALUE);
/*  53 */     a("reducedDebugInfo", "false", EnumGameRuleType.BOOLEAN_VALUE);
/*  54 */     a("spectatorsGenerateChunks", "true", EnumGameRuleType.BOOLEAN_VALUE);
/*  55 */     a("spawnRadius", "10", EnumGameRuleType.NUMERICAL_VALUE);
/*  56 */     a("disableElytraMovementCheck", "false", EnumGameRuleType.BOOLEAN_VALUE);
/*  57 */     a("maxEntityCramming", "24", EnumGameRuleType.NUMERICAL_VALUE);
/*  58 */     a("doWeatherCycle", "true", EnumGameRuleType.BOOLEAN_VALUE);
/*  59 */     a("doLimitedCrafting", "false", EnumGameRuleType.BOOLEAN_VALUE);
/*  60 */     a("maxCommandChainLength", "65536", EnumGameRuleType.NUMERICAL_VALUE);
/*  61 */     a("announceAdvancements", "true", EnumGameRuleType.BOOLEAN_VALUE);
/*  62 */     a("gameLoopFunction", "-", EnumGameRuleType.FUNCTION);
/*     */   }
/*     */   
/*     */   public void a(String paramString1, String paramString2, EnumGameRuleType paramEnumGameRuleType) {
/*  66 */     this.a.put(paramString1, new GameRuleValue(paramString2, paramEnumGameRuleType));
/*     */   }
/*     */   
/*     */   public void set(String paramString1, String paramString2) {
/*  70 */     GameRuleValue gameRuleValue = this.a.get(paramString1);
/*  71 */     if (gameRuleValue != null) {
/*  72 */       gameRuleValue.a(paramString2);
/*     */     } else {
/*  74 */       a(paramString1, paramString2, EnumGameRuleType.ANY_VALUE);
/*     */     } 
/*     */   }
/*     */   
/*     */   public String get(String paramString) {
/*  79 */     GameRuleValue gameRuleValue = this.a.get(paramString);
/*  80 */     if (gameRuleValue != null) {
/*  81 */       return gameRuleValue.a();
/*     */     }
/*  83 */     return "";
/*     */   }
/*     */   
/*     */   public boolean getBoolean(String paramString) {
/*  87 */     GameRuleValue gameRuleValue = this.a.get(paramString);
/*  88 */     if (gameRuleValue != null) {
/*  89 */       return gameRuleValue.b();
/*     */     }
/*  91 */     return false;
/*     */   }
/*     */   
/*     */   public int c(String paramString) {
/*  95 */     GameRuleValue gameRuleValue = this.a.get(paramString);
/*  96 */     if (gameRuleValue != null) {
/*  97 */       return gameRuleValue.c();
/*     */     }
/*  99 */     return 0;
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
/*     */   public NBTTagCompound a() {
/* 111 */     NBTTagCompound nBTTagCompound = new NBTTagCompound();
/*     */     
/* 113 */     for (String str : this.a.keySet()) {
/* 114 */       GameRuleValue gameRuleValue = this.a.get(str);
/* 115 */       nBTTagCompound.setString(str, gameRuleValue.a());
/*     */     } 
/*     */     
/* 118 */     return nBTTagCompound;
/*     */   }
/*     */   
/*     */   public void a(NBTTagCompound paramNBTTagCompound) {
/* 122 */     Set<String> set = paramNBTTagCompound.c();
/* 123 */     for (String str : set) {
/* 124 */       set(str, paramNBTTagCompound.getString(str));
/*     */     }
/*     */   }
/*     */   
/*     */   public String[] getGameRules() {
/* 129 */     Set<String> set = this.a.keySet();
/* 130 */     return set.<String>toArray(new String[set.size()]);
/*     */   }
/*     */   
/*     */   public boolean contains(String paramString) {
/* 134 */     return this.a.containsKey(paramString);
/*     */   }
/*     */   
/*     */   public boolean a(String paramString, EnumGameRuleType paramEnumGameRuleType) {
/* 138 */     GameRuleValue gameRuleValue = this.a.get(paramString);
/* 139 */     if (gameRuleValue != null && (gameRuleValue.e() == paramEnumGameRuleType || paramEnumGameRuleType == EnumGameRuleType.ANY_VALUE)) {
/* 140 */       return true;
/*     */     }
/* 142 */     return false;
/*     */   }
/*     */   
/*     */   static class GameRuleValue {
/*     */     private String a;
/*     */     private boolean b;
/*     */     private int c;
/*     */     private double d;
/*     */     private final GameRules.EnumGameRuleType e;
/*     */     
/*     */     public GameRuleValue(String param1String, GameRules.EnumGameRuleType param1EnumGameRuleType) {
/* 153 */       this.e = param1EnumGameRuleType;
/* 154 */       a(param1String);
/*     */     }
/*     */     
/*     */     public void a(String param1String) {
/* 158 */       this.a = param1String;
/* 159 */       this.b = Boolean.parseBoolean(param1String);
/* 160 */       this.c = this.b ? 1 : 0;
/*     */       try {
/* 162 */         this.c = Integer.parseInt(param1String);
/* 163 */       } catch (NumberFormatException numberFormatException) {}
/*     */       
/*     */       try {
/* 166 */         this.d = Double.parseDouble(param1String);
/* 167 */       } catch (NumberFormatException numberFormatException) {}
/*     */     }
/*     */ 
/*     */     
/*     */     public String a() {
/* 172 */       return this.a;
/*     */     }
/*     */     
/*     */     public boolean b() {
/* 176 */       return this.b;
/*     */     }
/*     */     
/*     */     public int c() {
/* 180 */       return this.c;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public GameRules.EnumGameRuleType e() {
/* 188 */       return this.e;
/*     */     }
/*     */   }
/*     */   
/*     */   public enum EnumGameRuleType {
/* 193 */     ANY_VALUE,
/* 194 */     BOOLEAN_VALUE,
/* 195 */     NUMERICAL_VALUE,
/* 196 */     FUNCTION;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\GameRules.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */