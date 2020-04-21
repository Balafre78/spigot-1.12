/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ public interface IScoreboardCriteria
/*    */ {
/*  9 */   public static final Map<String, IScoreboardCriteria> criteria = Maps.newHashMap();
/*    */   
/* 11 */   public static final IScoreboardCriteria b = new ScoreboardBaseCriteria("dummy");
/* 12 */   public static final IScoreboardCriteria c = new ScoreboardBaseCriteria("trigger");
/* 13 */   public static final IScoreboardCriteria d = new ScoreboardBaseCriteria("deathCount");
/* 14 */   public static final IScoreboardCriteria e = new ScoreboardBaseCriteria("playerKillCount");
/* 15 */   public static final IScoreboardCriteria f = new ScoreboardBaseCriteria("totalKillCount");
/* 16 */   public static final IScoreboardCriteria g = new ScoreboardHealthCriteria("health");
/* 17 */   public static final IScoreboardCriteria h = new ScoreboardReadOnlyCriteria("food");
/* 18 */   public static final IScoreboardCriteria i = new ScoreboardReadOnlyCriteria("air");
/* 19 */   public static final IScoreboardCriteria j = new ScoreboardReadOnlyCriteria("armor");
/* 20 */   public static final IScoreboardCriteria k = new ScoreboardReadOnlyCriteria("xp");
/* 21 */   public static final IScoreboardCriteria l = new ScoreboardReadOnlyCriteria("level");
/* 22 */   public static final IScoreboardCriteria[] m = new IScoreboardCriteria[] { new ScoreboardCriteriaInteger("teamkill.", EnumChatFormat.BLACK), new ScoreboardCriteriaInteger("teamkill.", EnumChatFormat.DARK_BLUE), new ScoreboardCriteriaInteger("teamkill.", EnumChatFormat.DARK_GREEN), new ScoreboardCriteriaInteger("teamkill.", EnumChatFormat.DARK_AQUA), new ScoreboardCriteriaInteger("teamkill.", EnumChatFormat.DARK_RED), new ScoreboardCriteriaInteger("teamkill.", EnumChatFormat.DARK_PURPLE), new ScoreboardCriteriaInteger("teamkill.", EnumChatFormat.GOLD), new ScoreboardCriteriaInteger("teamkill.", EnumChatFormat.GRAY), new ScoreboardCriteriaInteger("teamkill.", EnumChatFormat.DARK_GRAY), new ScoreboardCriteriaInteger("teamkill.", EnumChatFormat.BLUE), new ScoreboardCriteriaInteger("teamkill.", EnumChatFormat.GREEN), new ScoreboardCriteriaInteger("teamkill.", EnumChatFormat.AQUA), new ScoreboardCriteriaInteger("teamkill.", EnumChatFormat.RED), new ScoreboardCriteriaInteger("teamkill.", EnumChatFormat.LIGHT_PURPLE), new ScoreboardCriteriaInteger("teamkill.", EnumChatFormat.YELLOW), new ScoreboardCriteriaInteger("teamkill.", EnumChatFormat.WHITE) };
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 32 */   public static final IScoreboardCriteria[] n = new IScoreboardCriteria[] { new ScoreboardCriteriaInteger("killedByTeam.", EnumChatFormat.BLACK), new ScoreboardCriteriaInteger("killedByTeam.", EnumChatFormat.DARK_BLUE), new ScoreboardCriteriaInteger("killedByTeam.", EnumChatFormat.DARK_GREEN), new ScoreboardCriteriaInteger("killedByTeam.", EnumChatFormat.DARK_AQUA), new ScoreboardCriteriaInteger("killedByTeam.", EnumChatFormat.DARK_RED), new ScoreboardCriteriaInteger("killedByTeam.", EnumChatFormat.DARK_PURPLE), new ScoreboardCriteriaInteger("killedByTeam.", EnumChatFormat.GOLD), new ScoreboardCriteriaInteger("killedByTeam.", EnumChatFormat.GRAY), new ScoreboardCriteriaInteger("killedByTeam.", EnumChatFormat.DARK_GRAY), new ScoreboardCriteriaInteger("killedByTeam.", EnumChatFormat.BLUE), new ScoreboardCriteriaInteger("killedByTeam.", EnumChatFormat.GREEN), new ScoreboardCriteriaInteger("killedByTeam.", EnumChatFormat.AQUA), new ScoreboardCriteriaInteger("killedByTeam.", EnumChatFormat.RED), new ScoreboardCriteriaInteger("killedByTeam.", EnumChatFormat.LIGHT_PURPLE), new ScoreboardCriteriaInteger("killedByTeam.", EnumChatFormat.YELLOW), new ScoreboardCriteriaInteger("killedByTeam.", EnumChatFormat.WHITE) };
/*    */ 
/*    */ 
/*    */   
/*    */   String getName();
/*    */ 
/*    */ 
/*    */   
/*    */   boolean isReadOnly();
/*    */ 
/*    */   
/*    */   EnumScoreboardHealthDisplay c();
/*    */ 
/*    */   
/*    */   public enum EnumScoreboardHealthDisplay
/*    */   {
/* 48 */     INTEGER("integer"),
/* 49 */     HEARTS("hearts");
/*    */ 
/*    */     
/* 52 */     private static final Map<String, EnumScoreboardHealthDisplay> c = Maps.newHashMap();
/*    */     private final String d;
/*    */     
/*    */     static {
/* 56 */       for (EnumScoreboardHealthDisplay enumScoreboardHealthDisplay : values()) {
/* 57 */         c.put(enumScoreboardHealthDisplay.a(), enumScoreboardHealthDisplay);
/*    */       }
/*    */     }
/*    */     
/*    */     EnumScoreboardHealthDisplay(String param1String1) {
/* 62 */       this.d = param1String1;
/*    */     }
/*    */     
/*    */     public String a() {
/* 66 */       return this.d;
/*    */     }
/*    */     
/*    */     public static EnumScoreboardHealthDisplay a(String param1String) {
/* 70 */       EnumScoreboardHealthDisplay enumScoreboardHealthDisplay = c.get(param1String);
/* 71 */       return (enumScoreboardHealthDisplay == null) ? INTEGER : enumScoreboardHealthDisplay;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\IScoreboardCriteria.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */