/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum EnumMainHand
/*    */ {
/*  7 */   LEFT(new ChatMessage("options.mainHand.left", new Object[0])),
/*  8 */   RIGHT(new ChatMessage("options.mainHand.right", new Object[0]));
/*    */   
/*    */   private final IChatBaseComponent c;
/*    */ 
/*    */   
/*    */   EnumMainHand(IChatBaseComponent paramIChatBaseComponent) {
/* 14 */     this.c = paramIChatBaseComponent;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 26 */     return this.c.toPlainText();
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EnumMainHand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */