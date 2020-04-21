/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ public class ChatMessageException extends IllegalArgumentException {
/*    */   public ChatMessageException(ChatMessage paramChatMessage, String paramString) {
/*  5 */     super(String.format("Error parsing: %s: %s", new Object[] { paramChatMessage, paramString }));
/*    */   }
/*    */   
/*    */   public ChatMessageException(ChatMessage paramChatMessage, int paramInt) {
/*  9 */     super(String.format("Invalid index %d requested for %s", new Object[] { Integer.valueOf(paramInt), paramChatMessage }));
/*    */   }
/*    */   
/*    */   public ChatMessageException(ChatMessage paramChatMessage, Throwable paramThrowable) {
/* 13 */     super(String.format("Error while parsing: %s", new Object[] { paramChatMessage }), paramThrowable);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ChatMessageException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */