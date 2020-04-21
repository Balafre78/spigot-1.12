/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.GsonBuilder;
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonDeserializationContext;
/*     */ import com.google.gson.JsonDeserializer;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParseException;
/*     */ import com.google.gson.JsonPrimitive;
/*     */ import com.google.gson.JsonSerializationContext;
/*     */ import com.google.gson.JsonSerializer;
/*     */ import java.lang.reflect.Type;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ public interface IChatBaseComponent
/*     */   extends Iterable<IChatBaseComponent>
/*     */ {
/*     */   IChatBaseComponent setChatModifier(ChatModifier paramChatModifier);
/*     */   
/*     */   ChatModifier getChatModifier();
/*     */   
/*     */   IChatBaseComponent a(String paramString);
/*     */   
/*     */   IChatBaseComponent addSibling(IChatBaseComponent paramIChatBaseComponent);
/*     */   
/*     */   String getText();
/*     */   
/*     */   String toPlainText();
/*     */   
/*     */   List<IChatBaseComponent> a();
/*     */   
/*     */   IChatBaseComponent f();
/*     */   
/*     */   public static class ChatSerializer
/*     */     implements JsonDeserializer<IChatBaseComponent>, JsonSerializer<IChatBaseComponent>
/*     */   {
/*     */     private static final Gson a;
/*     */     
/*     */     static {
/*  45 */       GsonBuilder gsonBuilder = new GsonBuilder();
/*  46 */       gsonBuilder.registerTypeHierarchyAdapter(IChatBaseComponent.class, new ChatSerializer());
/*  47 */       gsonBuilder.registerTypeHierarchyAdapter(ChatModifier.class, new ChatModifier.ChatModifierSerializer());
/*  48 */       gsonBuilder.registerTypeAdapterFactory(new ChatTypeAdapterFactory());
/*  49 */       a = gsonBuilder.create();
/*     */     }
/*     */ 
/*     */     
/*     */     public IChatBaseComponent a(JsonElement param1JsonElement, Type param1Type, JsonDeserializationContext param1JsonDeserializationContext) throws JsonParseException {
/*  54 */       if (param1JsonElement.isJsonPrimitive())
/*     */       {
/*  56 */         return new ChatComponentText(param1JsonElement.getAsString()); } 
/*  57 */       if (param1JsonElement.isJsonObject()) {
/*  58 */         ChatComponentKeybind chatComponentKeybind; JsonObject jsonObject = param1JsonElement.getAsJsonObject();
/*     */ 
/*     */         
/*  61 */         if (jsonObject.has("text")) {
/*  62 */           ChatComponentText chatComponentText = new ChatComponentText(jsonObject.get("text").getAsString());
/*  63 */         } else if (jsonObject.has("translate")) {
/*  64 */           String str = jsonObject.get("translate").getAsString();
/*     */           
/*  66 */           if (jsonObject.has("with")) {
/*  67 */             JsonArray jsonArray = jsonObject.getAsJsonArray("with");
/*  68 */             Object[] arrayOfObject = new Object[jsonArray.size()];
/*     */             
/*  70 */             for (byte b = 0; b < arrayOfObject.length; b++) {
/*  71 */               arrayOfObject[b] = a(jsonArray.get(b), param1Type, param1JsonDeserializationContext);
/*     */               
/*  73 */               if (arrayOfObject[b] instanceof ChatComponentText) {
/*  74 */                 ChatComponentText chatComponentText = (ChatComponentText)arrayOfObject[b];
/*  75 */                 if (chatComponentText.getChatModifier().g() && chatComponentText.a().isEmpty()) {
/*  76 */                   arrayOfObject[b] = chatComponentText.g();
/*     */                 }
/*     */               } 
/*     */             } 
/*     */             
/*  81 */             ChatMessage chatMessage = new ChatMessage(str, arrayOfObject);
/*     */           } else {
/*  83 */             ChatMessage chatMessage = new ChatMessage(str, new Object[0]);
/*     */           } 
/*  85 */         } else if (jsonObject.has("score")) {
/*  86 */           JsonObject jsonObject1 = jsonObject.getAsJsonObject("score");
/*  87 */           if (jsonObject1.has("name") && jsonObject1.has("objective")) {
/*  88 */             ChatComponentScore chatComponentScore = new ChatComponentScore(ChatDeserializer.h(jsonObject1, "name"), ChatDeserializer.h(jsonObject1, "objective"));
/*  89 */             if (jsonObject1.has("value")) {
/*  90 */               chatComponentScore.b(ChatDeserializer.h(jsonObject1, "value"));
/*     */             }
/*     */           } else {
/*  93 */             throw new JsonParseException("A score component needs a least a name and an objective");
/*     */           } 
/*  95 */         } else if (jsonObject.has("selector")) {
/*  96 */           ChatComponentSelector chatComponentSelector = new ChatComponentSelector(ChatDeserializer.h(jsonObject, "selector"));
/*  97 */         } else if (jsonObject.has("keybind")) {
/*  98 */           chatComponentKeybind = new ChatComponentKeybind(ChatDeserializer.h(jsonObject, "keybind"));
/*     */         } else {
/* 100 */           throw new JsonParseException("Don't know how to turn " + param1JsonElement + " into a Component");
/*     */         } 
/*     */         
/* 103 */         if (jsonObject.has("extra")) {
/* 104 */           JsonArray jsonArray = jsonObject.getAsJsonArray("extra");
/*     */           
/* 106 */           if (jsonArray.size() > 0) {
/* 107 */             for (byte b = 0; b < jsonArray.size(); b++) {
/* 108 */               chatComponentKeybind.addSibling(a(jsonArray.get(b), param1Type, param1JsonDeserializationContext));
/*     */             }
/*     */           } else {
/* 111 */             throw new JsonParseException("Unexpected empty array of components");
/*     */           } 
/*     */         } 
/*     */         
/* 115 */         chatComponentKeybind.setChatModifier((ChatModifier)param1JsonDeserializationContext.deserialize(param1JsonElement, ChatModifier.class));
/*     */         
/* 117 */         return chatComponentKeybind;
/* 118 */       }  if (param1JsonElement.isJsonArray()) {
/*     */         
/* 120 */         JsonArray jsonArray = param1JsonElement.getAsJsonArray();
/* 121 */         IChatBaseComponent iChatBaseComponent = null;
/*     */         
/* 123 */         for (JsonElement jsonElement : jsonArray) {
/* 124 */           IChatBaseComponent iChatBaseComponent1 = a(jsonElement, jsonElement.getClass(), param1JsonDeserializationContext);
/* 125 */           if (iChatBaseComponent == null) {
/* 126 */             iChatBaseComponent = iChatBaseComponent1; continue;
/*     */           } 
/* 128 */           iChatBaseComponent.addSibling(iChatBaseComponent1);
/*     */         } 
/*     */ 
/*     */         
/* 132 */         return iChatBaseComponent;
/*     */       } 
/* 134 */       throw new JsonParseException("Don't know how to turn " + param1JsonElement + " into a Component");
/*     */     }
/*     */ 
/*     */     
/*     */     private void a(ChatModifier param1ChatModifier, JsonObject param1JsonObject, JsonSerializationContext param1JsonSerializationContext) {
/* 139 */       JsonElement jsonElement = param1JsonSerializationContext.serialize(param1ChatModifier);
/*     */       
/* 141 */       if (jsonElement.isJsonObject()) {
/* 142 */         JsonObject jsonObject = (JsonObject)jsonElement;
/* 143 */         for (Map.Entry entry : jsonObject.entrySet()) {
/* 144 */           param1JsonObject.add((String)entry.getKey(), (JsonElement)entry.getValue());
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public JsonElement a(IChatBaseComponent param1IChatBaseComponent, Type param1Type, JsonSerializationContext param1JsonSerializationContext) {
/* 151 */       JsonObject jsonObject = new JsonObject();
/*     */       
/* 153 */       if (!param1IChatBaseComponent.getChatModifier().g()) {
/* 154 */         a(param1IChatBaseComponent.getChatModifier(), jsonObject, param1JsonSerializationContext);
/*     */       }
/*     */       
/* 157 */       if (!param1IChatBaseComponent.a().isEmpty()) {
/* 158 */         JsonArray jsonArray = new JsonArray();
/*     */         
/* 160 */         for (IChatBaseComponent iChatBaseComponent : param1IChatBaseComponent.a()) {
/* 161 */           jsonArray.add(a(iChatBaseComponent, iChatBaseComponent.getClass(), param1JsonSerializationContext));
/*     */         }
/*     */         
/* 164 */         jsonObject.add("extra", (JsonElement)jsonArray);
/*     */       } 
/*     */       
/* 167 */       if (param1IChatBaseComponent instanceof ChatComponentText) {
/* 168 */         jsonObject.addProperty("text", ((ChatComponentText)param1IChatBaseComponent).g());
/* 169 */       } else if (param1IChatBaseComponent instanceof ChatMessage) {
/* 170 */         ChatMessage chatMessage = (ChatMessage)param1IChatBaseComponent;
/* 171 */         jsonObject.addProperty("translate", chatMessage.i());
/*     */         
/* 173 */         if (chatMessage.j() != null && (chatMessage.j()).length > 0) {
/* 174 */           JsonArray jsonArray = new JsonArray();
/*     */           
/* 176 */           for (Object object : chatMessage.j()) {
/* 177 */             if (object instanceof IChatBaseComponent) {
/* 178 */               jsonArray.add(a((IChatBaseComponent)object, object.getClass(), param1JsonSerializationContext));
/*     */             } else {
/* 180 */               jsonArray.add((JsonElement)new JsonPrimitive(String.valueOf(object)));
/*     */             } 
/*     */           } 
/*     */           
/* 184 */           jsonObject.add("with", (JsonElement)jsonArray);
/*     */         } 
/* 186 */       } else if (param1IChatBaseComponent instanceof ChatComponentScore) {
/* 187 */         ChatComponentScore chatComponentScore = (ChatComponentScore)param1IChatBaseComponent;
/* 188 */         JsonObject jsonObject1 = new JsonObject();
/* 189 */         jsonObject1.addProperty("name", chatComponentScore.g());
/* 190 */         jsonObject1.addProperty("objective", chatComponentScore.h());
/* 191 */         jsonObject1.addProperty("value", chatComponentScore.getText());
/* 192 */         jsonObject.add("score", (JsonElement)jsonObject1);
/* 193 */       } else if (param1IChatBaseComponent instanceof ChatComponentSelector) {
/* 194 */         ChatComponentSelector chatComponentSelector = (ChatComponentSelector)param1IChatBaseComponent;
/* 195 */         jsonObject.addProperty("selector", chatComponentSelector.g());
/* 196 */       } else if (param1IChatBaseComponent instanceof ChatComponentKeybind) {
/* 197 */         ChatComponentKeybind chatComponentKeybind = (ChatComponentKeybind)param1IChatBaseComponent;
/* 198 */         jsonObject.addProperty("keybind", chatComponentKeybind.h());
/*     */       } else {
/* 200 */         throw new IllegalArgumentException("Don't know how to serialize " + param1IChatBaseComponent + " as a Component");
/*     */       } 
/*     */       
/* 203 */       return (JsonElement)jsonObject;
/*     */     }
/*     */     
/*     */     public static String a(IChatBaseComponent param1IChatBaseComponent) {
/* 207 */       return a.toJson(param1IChatBaseComponent);
/*     */     }
/*     */     
/*     */     @Nullable
/*     */     public static IChatBaseComponent a(String param1String) {
/* 212 */       return ChatDeserializer.<IChatBaseComponent>a(a, param1String, IChatBaseComponent.class, false);
/*     */     }
/*     */     
/*     */     @Nullable
/*     */     public static IChatBaseComponent b(String param1String) {
/* 217 */       return ChatDeserializer.<IChatBaseComponent>a(a, param1String, IChatBaseComponent.class, true);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\IChatBaseComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */