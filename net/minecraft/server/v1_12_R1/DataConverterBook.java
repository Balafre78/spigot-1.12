/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.gson.JsonParseException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DataConverterBook
/*    */   implements IDataConverter
/*    */ {
/*    */   public int a() {
/* 17 */     return 165;
/*    */   }
/*    */ 
/*    */   
/*    */   public NBTTagCompound a(NBTTagCompound paramNBTTagCompound) {
/* 22 */     if ("minecraft:written_book".equals(paramNBTTagCompound.getString("id"))) {
/* 23 */       NBTTagCompound nBTTagCompound = paramNBTTagCompound.getCompound("tag");
/* 24 */       if (nBTTagCompound.hasKeyOfType("pages", 9)) {
/* 25 */         NBTTagList nBTTagList = nBTTagCompound.getList("pages", 8);
/*    */         
/* 27 */         for (byte b = 0; b < nBTTagList.size(); b++) {
/* 28 */           String str = nBTTagList.getString(b);
/*    */           
/* 30 */           ChatComponentText chatComponentText = null;
/* 31 */           if ("null".equals(str) || UtilColor.b(str)) {
/* 32 */             chatComponentText = new ChatComponentText("");
/* 33 */           } else if ((str
/* 34 */             .charAt(0) == '"' && str.charAt(str.length() - 1) == '"') || (str
/* 35 */             .charAt(0) == '{' && str.charAt(str.length() - 1) == '}')) {
/*    */             IChatBaseComponent iChatBaseComponent;
/*    */             try {
/* 38 */               iChatBaseComponent = ChatDeserializer.<IChatBaseComponent>a(DataConverterSignText.a, str, IChatBaseComponent.class, true);
/* 39 */               if (iChatBaseComponent == null) {
/* 40 */                 iChatBaseComponent = new ChatComponentText("");
/*    */               }
/* 42 */             } catch (JsonParseException jsonParseException) {}
/*    */ 
/*    */             
/* 45 */             if (iChatBaseComponent == null) {
/*    */               try {
/* 47 */                 iChatBaseComponent = IChatBaseComponent.ChatSerializer.a(str);
/* 48 */               } catch (JsonParseException jsonParseException) {}
/*    */             }
/*    */ 
/*    */             
/* 52 */             if (iChatBaseComponent == null) {
/*    */               try {
/* 54 */                 iChatBaseComponent = IChatBaseComponent.ChatSerializer.b(str);
/* 55 */               } catch (JsonParseException jsonParseException) {}
/*    */             }
/*    */ 
/*    */             
/* 59 */             if (iChatBaseComponent == null) {
/* 60 */               iChatBaseComponent = new ChatComponentText(str);
/*    */             }
/*    */           } else {
/* 63 */             chatComponentText = new ChatComponentText(str);
/*    */           } 
/*    */           
/* 66 */           nBTTagList.a(b, new NBTTagString(IChatBaseComponent.ChatSerializer.a(chatComponentText)));
/*    */         } 
/* 68 */         nBTTagCompound.set("pages", nBTTagList);
/*    */       } 
/*    */     } 
/* 71 */     return paramNBTTagCompound;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DataConverterBook.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */