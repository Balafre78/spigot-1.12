/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ChatComponentUtils
/*    */ {
/*    */   public static IChatBaseComponent filterForDisplay(ICommandListener paramICommandListener, IChatBaseComponent paramIChatBaseComponent, Entity paramEntity) throws CommandException {
/*    */     IChatBaseComponent iChatBaseComponent;
/* 17 */     if (paramIChatBaseComponent instanceof ChatComponentScore) {
/* 18 */       ChatComponentScore chatComponentScore1 = (ChatComponentScore)paramIChatBaseComponent;
/* 19 */       String str1 = chatComponentScore1.g();
/* 20 */       if (PlayerSelector.isPattern(str1)) {
/* 21 */         List<Entity> list = PlayerSelector.getPlayers(paramICommandListener, str1, Entity.class);
/* 22 */         if (list.size() != 1) {
/* 23 */           throw new ExceptionEntityNotFound("commands.generic.selector.notFound", new Object[] { str1 });
/*    */         }
/*    */         
/* 26 */         Entity entity = list.get(0);
/* 27 */         if (entity instanceof EntityHuman) {
/* 28 */           str1 = entity.getName();
/*    */         } else {
/* 30 */           str1 = entity.bn();
/*    */         } 
/*    */       } 
/* 33 */       String str2 = (paramEntity != null && str1.equals("*")) ? paramEntity.getName() : str1;
/* 34 */       ChatComponentScore chatComponentScore2 = new ChatComponentScore(str2, chatComponentScore1.h());
/* 35 */       chatComponentScore2.b(chatComponentScore1.getText());
/*    */       
/* 37 */       chatComponentScore2.a(paramICommandListener);
/* 38 */     } else if (paramIChatBaseComponent instanceof ChatComponentSelector) {
/* 39 */       String str = ((ChatComponentSelector)paramIChatBaseComponent).g();
/* 40 */       iChatBaseComponent = PlayerSelector.getPlayerNames(paramICommandListener, str);
/* 41 */       if (iChatBaseComponent == null) {
/* 42 */         iChatBaseComponent = new ChatComponentText("");
/*    */       }
/* 44 */     } else if (paramIChatBaseComponent instanceof ChatComponentText) {
/* 45 */       iChatBaseComponent = new ChatComponentText(((ChatComponentText)paramIChatBaseComponent).g());
/* 46 */     } else if (paramIChatBaseComponent instanceof ChatComponentKeybind) {
/* 47 */       iChatBaseComponent = new ChatComponentKeybind(((ChatComponentKeybind)paramIChatBaseComponent).h());
/* 48 */     } else if (paramIChatBaseComponent instanceof ChatMessage) {
/* 49 */       Object[] arrayOfObject = ((ChatMessage)paramIChatBaseComponent).j();
/* 50 */       for (byte b = 0; b < arrayOfObject.length; b++) {
/* 51 */         Object object = arrayOfObject[b];
/* 52 */         if (object instanceof IChatBaseComponent) {
/* 53 */           arrayOfObject[b] = filterForDisplay(paramICommandListener, (IChatBaseComponent)object, paramEntity);
/*    */         }
/*    */       } 
/* 56 */       iChatBaseComponent = new ChatMessage(((ChatMessage)paramIChatBaseComponent).i(), arrayOfObject);
/*    */     } else {
/* 58 */       return paramIChatBaseComponent;
/*    */     } 
/* 60 */     ChatModifier chatModifier = paramIChatBaseComponent.getChatModifier();
/* 61 */     if (chatModifier != null) {
/* 62 */       iChatBaseComponent.setChatModifier(chatModifier.clone());
/*    */     }
/* 64 */     for (IChatBaseComponent iChatBaseComponent1 : paramIChatBaseComponent.a()) {
/* 65 */       iChatBaseComponent.addSibling(filterForDisplay(paramICommandListener, iChatBaseComponent1, paramEntity));
/*    */     }
/* 67 */     return iChatBaseComponent;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ChatComponentUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */