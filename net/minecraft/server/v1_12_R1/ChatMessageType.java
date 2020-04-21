/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ public enum ChatMessageType {
/*  4 */   CHAT((byte)0),
/*  5 */   SYSTEM((byte)1),
/*  6 */   GAME_INFO((byte)2);
/*    */   
/*    */   private final byte d;
/*    */ 
/*    */   
/*    */   ChatMessageType(byte paramByte) {
/* 12 */     this.d = paramByte;
/*    */   }
/*    */   
/*    */   public byte a() {
/* 16 */     return this.d;
/*    */   }
/*    */   
/*    */   public static ChatMessageType a(byte paramByte) {
/* 20 */     for (ChatMessageType chatMessageType : values()) {
/* 21 */       if (paramByte == chatMessageType.d) {
/* 22 */         return chatMessageType;
/*    */       }
/*    */     } 
/* 25 */     return CHAT;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ChatMessageType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */