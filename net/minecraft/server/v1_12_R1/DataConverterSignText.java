/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.GsonBuilder;
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonDeserializationContext;
/*     */ import com.google.gson.JsonDeserializer;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonParseException;
/*     */ import java.lang.reflect.Type;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DataConverterSignText
/*     */   implements IDataConverter
/*     */ {
/*  20 */   public static final Gson a = (new GsonBuilder())
/*  21 */     .registerTypeAdapter(IChatBaseComponent.class, new JsonDeserializer<IChatBaseComponent>()
/*     */       {
/*     */         public IChatBaseComponent a(JsonElement param1JsonElement, Type param1Type, JsonDeserializationContext param1JsonDeserializationContext) throws JsonParseException {
/*  24 */           if (param1JsonElement.isJsonPrimitive())
/*     */           {
/*  26 */             return new ChatComponentText(param1JsonElement.getAsString()); } 
/*  27 */           if (param1JsonElement.isJsonArray()) {
/*     */             
/*  29 */             JsonArray jsonArray = param1JsonElement.getAsJsonArray();
/*  30 */             IChatBaseComponent iChatBaseComponent = null;
/*     */             
/*  32 */             for (JsonElement jsonElement : jsonArray) {
/*  33 */               IChatBaseComponent iChatBaseComponent1 = a(jsonElement, jsonElement.getClass(), param1JsonDeserializationContext);
/*  34 */               if (iChatBaseComponent == null) {
/*  35 */                 iChatBaseComponent = iChatBaseComponent1; continue;
/*     */               } 
/*  37 */               iChatBaseComponent.addSibling(iChatBaseComponent1);
/*     */             } 
/*     */ 
/*     */             
/*  41 */             return iChatBaseComponent;
/*     */           } 
/*  43 */           throw new JsonParseException("Don't know how to turn " + param1JsonElement + " into a Component");
/*     */         }
/*  47 */       }).create();
/*     */ 
/*     */   
/*     */   public int a() {
/*  51 */     return 101;
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagCompound a(NBTTagCompound paramNBTTagCompound) {
/*  56 */     if ("Sign".equals(paramNBTTagCompound.getString("id"))) {
/*  57 */       a(paramNBTTagCompound, "Text1");
/*  58 */       a(paramNBTTagCompound, "Text2");
/*  59 */       a(paramNBTTagCompound, "Text3");
/*  60 */       a(paramNBTTagCompound, "Text4");
/*     */     } 
/*  62 */     return paramNBTTagCompound;
/*     */   }
/*     */   
/*     */   private void a(NBTTagCompound paramNBTTagCompound, String paramString) {
/*  66 */     String str = paramNBTTagCompound.getString(paramString);
/*     */     
/*  68 */     ChatComponentText chatComponentText = null;
/*  69 */     if ("null".equals(str) || UtilColor.b(str)) {
/*  70 */       chatComponentText = new ChatComponentText("");
/*  71 */     } else if ((str
/*  72 */       .charAt(0) == '"' && str.charAt(str.length() - 1) == '"') || (str
/*  73 */       .charAt(0) == '{' && str.charAt(str.length() - 1) == '}')) {
/*     */       IChatBaseComponent iChatBaseComponent;
/*     */       try {
/*  76 */         iChatBaseComponent = ChatDeserializer.<IChatBaseComponent>a(a, str, IChatBaseComponent.class, true);
/*  77 */         if (iChatBaseComponent == null) {
/*  78 */           iChatBaseComponent = new ChatComponentText("");
/*     */         }
/*  80 */       } catch (JsonParseException jsonParseException) {}
/*     */ 
/*     */       
/*  83 */       if (iChatBaseComponent == null) {
/*     */         try {
/*  85 */           iChatBaseComponent = IChatBaseComponent.ChatSerializer.a(str);
/*  86 */         } catch (JsonParseException jsonParseException) {}
/*     */       }
/*     */ 
/*     */       
/*  90 */       if (iChatBaseComponent == null) {
/*     */         try {
/*  92 */           iChatBaseComponent = IChatBaseComponent.ChatSerializer.b(str);
/*  93 */         } catch (JsonParseException jsonParseException) {}
/*     */       }
/*     */ 
/*     */       
/*  97 */       if (iChatBaseComponent == null) {
/*  98 */         iChatBaseComponent = new ChatComponentText(str);
/*     */       }
/*     */     } else {
/* 101 */       chatComponentText = new ChatComponentText(str);
/*     */     } 
/*     */     
/* 104 */     paramNBTTagCompound.setString(paramString, IChatBaseComponent.ChatSerializer.a(chatComponentText));
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DataConverterSignText.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */