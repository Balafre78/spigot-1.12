/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ public class ChatComponentSelector extends ChatBaseComponent {
/*    */   private final String b;
/*    */   
/*    */   public ChatComponentSelector(String paramString) {
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
/*    */   public ChatComponentSelector h() {
/* 21 */     ChatComponentSelector chatComponentSelector = new ChatComponentSelector(this.b);
/* 22 */     chatComponentSelector.setChatModifier(getChatModifier().clone());
/* 23 */     for (IChatBaseComponent iChatBaseComponent : a()) {
/* 24 */       chatComponentSelector.addSibling(iChatBaseComponent.f());
/*    */     }
/* 26 */     return chatComponentSelector;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object paramObject) {
/* 31 */     if (this == paramObject) {
/* 32 */       return true;
/*    */     }
/*    */     
/* 35 */     if (paramObject instanceof ChatComponentSelector) {
/* 36 */       ChatComponentSelector chatComponentSelector = (ChatComponentSelector)paramObject;
/* 37 */       return (this.b.equals(chatComponentSelector.b) && super.equals(paramObject));
/*    */     } 
/*    */     
/* 40 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 45 */     return "SelectorComponent{pattern='" + this.b + '\'' + ", siblings=" + this.a + ", style=" + 
/*    */ 
/*    */       
/* 48 */       getChatModifier() + '}';
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ChatComponentSelector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */