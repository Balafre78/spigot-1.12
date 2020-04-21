/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ public class ChatComponentText extends ChatBaseComponent {
/*    */   private final String b;
/*    */   
/*    */   public ChatComponentText(String paramString) {
/*  7 */     this.b = paramString;
/*    */   }
/*    */   
/*    */   public String g() {
/* 11 */     return this.b;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getText() {
/* 16 */     return this.b;
/*    */   }
/*    */ 
/*    */   
/*    */   public ChatComponentText h() {
/* 21 */     ChatComponentText chatComponentText = new ChatComponentText(this.b);
/* 22 */     chatComponentText.setChatModifier(getChatModifier().clone());
/* 23 */     for (IChatBaseComponent iChatBaseComponent : a()) {
/* 24 */       chatComponentText.addSibling(iChatBaseComponent.f());
/*    */     }
/* 26 */     return chatComponentText;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object paramObject) {
/* 31 */     if (this == paramObject) {
/* 32 */       return true;
/*    */     }
/*    */     
/* 35 */     if (paramObject instanceof ChatComponentText) {
/* 36 */       ChatComponentText chatComponentText = (ChatComponentText)paramObject;
/* 37 */       return (this.b.equals(chatComponentText.g()) && super.equals(paramObject));
/*    */     } 
/*    */     
/* 40 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 45 */     return "TextComponent{text='" + this.b + '\'' + ", siblings=" + this.a + ", style=" + 
/*    */ 
/*    */       
/* 48 */       getChatModifier() + '}';
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ChatComponentText.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */