/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ChatComponentScore
/*    */   extends ChatBaseComponent
/*    */ {
/*    */   private final String b;
/*    */   private final String c;
/* 13 */   private String d = "";
/*    */   
/*    */   public ChatComponentScore(String paramString1, String paramString2) {
/* 16 */     this.b = paramString1;
/* 17 */     this.c = paramString2;
/*    */   }
/*    */   
/*    */   public String g() {
/* 21 */     return this.b;
/*    */   }
/*    */   
/*    */   public String h() {
/* 25 */     return this.c;
/*    */   }
/*    */   
/*    */   public void b(String paramString) {
/* 29 */     this.d = paramString;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getText() {
/* 34 */     return this.d;
/*    */   }
/*    */   
/*    */   public void a(ICommandListener paramICommandListener) {
/* 38 */     MinecraftServer minecraftServer = paramICommandListener.C_();
/* 39 */     if (minecraftServer != null && minecraftServer.M() && UtilColor.b(this.d)) {
/* 40 */       Scoreboard scoreboard = minecraftServer.getWorldServer(0).getScoreboard();
/* 41 */       ScoreboardObjective scoreboardObjective = scoreboard.getObjective(this.c);
/* 42 */       if (scoreboard.b(this.b, scoreboardObjective)) {
/* 43 */         ScoreboardScore scoreboardScore = scoreboard.getPlayerScoreForObjective(this.b, scoreboardObjective);
/* 44 */         b(String.format("%d", new Object[] { Integer.valueOf(scoreboardScore.getScore()) }));
/*    */       } else {
/* 46 */         this.d = "";
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public ChatComponentScore i() {
/* 53 */     ChatComponentScore chatComponentScore = new ChatComponentScore(this.b, this.c);
/* 54 */     chatComponentScore.b(this.d);
/* 55 */     chatComponentScore.setChatModifier(getChatModifier().clone());
/* 56 */     for (IChatBaseComponent iChatBaseComponent : a()) {
/* 57 */       chatComponentScore.addSibling(iChatBaseComponent.f());
/*    */     }
/* 59 */     return chatComponentScore;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object paramObject) {
/* 64 */     if (this == paramObject) {
/* 65 */       return true;
/*    */     }
/*    */     
/* 68 */     if (paramObject instanceof ChatComponentScore) {
/* 69 */       ChatComponentScore chatComponentScore = (ChatComponentScore)paramObject;
/* 70 */       return (this.b.equals(chatComponentScore.b) && this.c.equals(chatComponentScore.c) && super.equals(paramObject));
/*    */     } 
/*    */     
/* 73 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 78 */     return "ScoreComponent{name='" + this.b + '\'' + "objective='" + this.c + '\'' + ", siblings=" + this.a + ", style=" + 
/*    */ 
/*    */ 
/*    */       
/* 82 */       getChatModifier() + '}';
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ChatComponentScore.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */