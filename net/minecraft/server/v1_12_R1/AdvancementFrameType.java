/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ public enum AdvancementFrameType
/*    */ {
/*  6 */   TASK("task", 0, EnumChatFormat.GREEN),
/*  7 */   CHALLENGE("challenge", 26, EnumChatFormat.DARK_PURPLE),
/*  8 */   GOAL("goal", 52, EnumChatFormat.GREEN);
/*    */   
/*    */   private final String d;
/*    */   
/*    */   private final int e;
/*    */   private final EnumChatFormat f;
/*    */   
/*    */   AdvancementFrameType(String paramString1, int paramInt1, EnumChatFormat paramEnumChatFormat) {
/* 16 */     this.d = paramString1;
/* 17 */     this.e = paramInt1;
/* 18 */     this.f = paramEnumChatFormat;
/*    */   }
/*    */   
/*    */   public String a() {
/* 22 */     return this.d;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static AdvancementFrameType a(String paramString) {
/* 30 */     for (AdvancementFrameType advancementFrameType : values()) {
/* 31 */       if (advancementFrameType.d.equals(paramString)) {
/* 32 */         return advancementFrameType;
/*    */       }
/*    */     } 
/* 35 */     throw new IllegalArgumentException("Unknown frame type '" + paramString + "'");
/*    */   }
/*    */   
/*    */   public EnumChatFormat c() {
/* 39 */     return this.f;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\AdvancementFrameType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */