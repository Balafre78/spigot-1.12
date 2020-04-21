/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.util.function.Function;
/*    */ import java.util.function.Supplier;
/*    */ 
/*    */ public class ChatComponentKeybind
/*    */   extends ChatBaseComponent {
/*    */   public static Function<String, Supplier<String>> b = paramString -> ();
/*    */   private final String c;
/*    */   private Supplier<String> d;
/*    */   
/*    */   public ChatComponentKeybind(String paramString) {
/* 13 */     this.c = paramString;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getText() {
/* 18 */     if (this.d == null) {
/* 19 */       this.d = b.apply(this.c);
/*    */     }
/* 21 */     return this.d.get();
/*    */   }
/*    */ 
/*    */   
/*    */   public ChatComponentKeybind g() {
/* 26 */     ChatComponentKeybind chatComponentKeybind = new ChatComponentKeybind(this.c);
/* 27 */     chatComponentKeybind.setChatModifier(getChatModifier().clone());
/* 28 */     for (IChatBaseComponent iChatBaseComponent : a()) {
/* 29 */       chatComponentKeybind.addSibling(iChatBaseComponent.f());
/*    */     }
/* 31 */     return chatComponentKeybind;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object paramObject) {
/* 36 */     if (this == paramObject) {
/* 37 */       return true;
/*    */     }
/*    */     
/* 40 */     if (paramObject instanceof ChatComponentKeybind) {
/* 41 */       ChatComponentKeybind chatComponentKeybind = (ChatComponentKeybind)paramObject;
/* 42 */       return (this.c.equals(chatComponentKeybind.c) && super.equals(paramObject));
/*    */     } 
/*    */     
/* 45 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 50 */     return "KeybindComponent{keybind='" + this.c + '\'' + ", siblings=" + this.a + ", style=" + 
/*    */ 
/*    */       
/* 53 */       getChatModifier() + '}';
/*    */   }
/*    */ 
/*    */   
/*    */   public String h() {
/* 58 */     return this.c;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ChatComponentKeybind.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */