/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.gson.JsonDeserializationContext;
/*     */ import com.google.gson.JsonDeserializer;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParseException;
/*     */ import com.google.gson.JsonPrimitive;
/*     */ import com.google.gson.JsonSerializationContext;
/*     */ import com.google.gson.JsonSerializer;
/*     */ import java.lang.reflect.Type;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ public class ChatModifier {
/*     */   private ChatModifier a;
/*     */   private EnumChatFormat b;
/*     */   private Boolean c;
/*     */   private Boolean d;
/*     */   private Boolean e;
/*     */   private Boolean f;
/*     */   private Boolean g;
/*     */   private ChatClickable h;
/*     */   private ChatHoverable i;
/*     */   private String j;
/*     */   
/*  26 */   private static final ChatModifier k = new ChatModifier() {
/*     */       @Nullable
/*     */       public EnumChatFormat getColor() {
/*  29 */         return null;
/*     */       }
/*     */       
/*     */       public boolean isBold() {
/*  33 */         return false;
/*     */       }
/*     */       
/*     */       public boolean isItalic() {
/*  37 */         return false;
/*     */       }
/*     */       
/*     */       public boolean isStrikethrough() {
/*  41 */         return false;
/*     */       }
/*     */       
/*     */       public boolean isUnderlined() {
/*  45 */         return false;
/*     */       }
/*     */       
/*     */       public boolean isRandom() {
/*  49 */         return false;
/*     */       }
/*     */       
/*     */       @Nullable
/*     */       public ChatClickable h() {
/*  54 */         return null;
/*     */       }
/*     */       
/*     */       @Nullable
/*     */       public ChatHoverable i() {
/*  59 */         return null;
/*     */       }
/*     */       
/*     */       @Nullable
/*     */       public String j() {
/*  64 */         return null;
/*     */       }
/*     */       
/*     */       public ChatModifier setColor(EnumChatFormat enumchatformat) {
/*  68 */         throw new UnsupportedOperationException();
/*     */       }
/*     */       
/*     */       public ChatModifier setBold(Boolean obool) {
/*  72 */         throw new UnsupportedOperationException();
/*     */       }
/*     */       
/*     */       public ChatModifier setItalic(Boolean obool) {
/*  76 */         throw new UnsupportedOperationException();
/*     */       }
/*     */       
/*     */       public ChatModifier setStrikethrough(Boolean obool) {
/*  80 */         throw new UnsupportedOperationException();
/*     */       }
/*     */       
/*     */       public ChatModifier setUnderline(Boolean obool) {
/*  84 */         throw new UnsupportedOperationException();
/*     */       }
/*     */       
/*     */       public ChatModifier setRandom(Boolean obool) {
/*  88 */         throw new UnsupportedOperationException();
/*     */       }
/*     */       
/*     */       public ChatModifier setChatClickable(ChatClickable chatclickable) {
/*  92 */         throw new UnsupportedOperationException();
/*     */       }
/*     */       
/*     */       public ChatModifier setChatHoverable(ChatHoverable chathoverable) {
/*  96 */         throw new UnsupportedOperationException();
/*     */       }
/*     */       
/*     */       public ChatModifier setChatModifier(ChatModifier chatmodifier) {
/* 100 */         throw new UnsupportedOperationException();
/*     */       }
/*     */       
/*     */       public String toString() {
/* 104 */         return "Style.ROOT";
/*     */       }
/*     */       
/*     */       public ChatModifier clone() {
/* 108 */         return this;
/*     */       }
/*     */       
/*     */       public ChatModifier n() {
/* 112 */         return this;
/*     */       }
/*     */     };
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public EnumChatFormat getColor() {
/* 120 */     return (this.b == null) ? o().getColor() : this.b;
/*     */   }
/*     */   
/*     */   public boolean isBold() {
/* 124 */     return (this.c == null) ? o().isBold() : this.c.booleanValue();
/*     */   }
/*     */   
/*     */   public boolean isItalic() {
/* 128 */     return (this.d == null) ? o().isItalic() : this.d.booleanValue();
/*     */   }
/*     */   
/*     */   public boolean isStrikethrough() {
/* 132 */     return (this.f == null) ? o().isStrikethrough() : this.f.booleanValue();
/*     */   }
/*     */   
/*     */   public boolean isUnderlined() {
/* 136 */     return (this.e == null) ? o().isUnderlined() : this.e.booleanValue();
/*     */   }
/*     */   
/*     */   public boolean isRandom() {
/* 140 */     return (this.g == null) ? o().isRandom() : this.g.booleanValue();
/*     */   }
/*     */   
/*     */   public boolean g() {
/* 144 */     return (this.c == null && this.d == null && this.f == null && this.e == null && this.g == null && this.b == null && this.h == null && this.i == null && this.j == null);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public ChatClickable h() {
/* 149 */     return (this.h == null) ? o().h() : this.h;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public ChatHoverable i() {
/* 154 */     return (this.i == null) ? o().i() : this.i;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public String j() {
/* 159 */     return (this.j == null) ? o().j() : this.j;
/*     */   }
/*     */   
/*     */   public ChatModifier setColor(EnumChatFormat enumchatformat) {
/* 163 */     this.b = enumchatformat;
/* 164 */     return this;
/*     */   }
/*     */   
/*     */   public ChatModifier setBold(Boolean obool) {
/* 168 */     this.c = obool;
/* 169 */     return this;
/*     */   }
/*     */   
/*     */   public ChatModifier setItalic(Boolean obool) {
/* 173 */     this.d = obool;
/* 174 */     return this;
/*     */   }
/*     */   
/*     */   public ChatModifier setStrikethrough(Boolean obool) {
/* 178 */     this.f = obool;
/* 179 */     return this;
/*     */   }
/*     */   
/*     */   public ChatModifier setUnderline(Boolean obool) {
/* 183 */     this.e = obool;
/* 184 */     return this;
/*     */   }
/*     */   
/*     */   public ChatModifier setRandom(Boolean obool) {
/* 188 */     this.g = obool;
/* 189 */     return this;
/*     */   }
/*     */   
/*     */   public ChatModifier setChatClickable(ChatClickable chatclickable) {
/* 193 */     this.h = chatclickable;
/* 194 */     return this;
/*     */   }
/*     */   
/*     */   public ChatModifier setChatHoverable(ChatHoverable chathoverable) {
/* 198 */     this.i = chathoverable;
/* 199 */     return this;
/*     */   }
/*     */   
/*     */   public ChatModifier setInsertion(String s) {
/* 203 */     this.j = s;
/* 204 */     return this;
/*     */   }
/*     */   
/*     */   public ChatModifier setChatModifier(ChatModifier chatmodifier) {
/* 208 */     this.a = chatmodifier;
/* 209 */     return this;
/*     */   }
/*     */   
/*     */   private ChatModifier o() {
/* 213 */     return (this.a == null) ? k : this.a;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 217 */     return "Style{hasParent=" + ((this.a != null) ? 1 : 0) + ", color=" + this.b + ", bold=" + this.c + ", italic=" + this.d + ", underlined=" + this.e + ", obfuscated=" + this.g + ", clickEvent=" + h() + ", hoverEvent=" + i() + ", insertion=" + j() + '}';
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 221 */     if (this == object)
/* 222 */       return true; 
/* 223 */     if (!(object instanceof ChatModifier)) {
/* 224 */       return false;
/*     */     }
/* 226 */     ChatModifier chatmodifier = (ChatModifier)object;
/*     */ 
/*     */     
/* 229 */     if (isBold() == chatmodifier.isBold() && getColor() == chatmodifier.getColor() && isItalic() == chatmodifier.isItalic() && isRandom() == chatmodifier.isRandom() && isStrikethrough() == chatmodifier.isStrikethrough() && isUnderlined() == chatmodifier.isUnderlined())
/*     */     {
/* 231 */       if ((h() != null) ? 
/* 232 */         !h().equals(chatmodifier.h()) : (
/*     */ 
/*     */         
/* 235 */         chatmodifier.h() != null))
/*     */       {
/*     */ 
/*     */         
/* 239 */         if ((i() != null) ? 
/* 240 */           !i().equals(chatmodifier.i()) : (
/*     */ 
/*     */           
/* 243 */           chatmodifier.i() != null))
/*     */         {
/*     */ 
/*     */           
/* 247 */           if ((j() != null) ? 
/* 248 */             !j().equals(chatmodifier.j()) : (
/*     */ 
/*     */             
/* 251 */             chatmodifier.j() != null)) {
/*     */ 
/*     */ 
/*     */             
/* 255 */             boolean bool = true;
/* 256 */             return bool;
/*     */           }  } 
/*     */       }
/*     */     }
/* 260 */     boolean flag = false;
/* 261 */     return flag;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 267 */     int i = (this.b == null) ? 0 : this.b.hashCode();
/*     */     
/* 269 */     i = 31 * i + ((this.c == null) ? 0 : this.c.hashCode());
/* 270 */     i = 31 * i + ((this.d == null) ? 0 : this.d.hashCode());
/* 271 */     i = 31 * i + ((this.e == null) ? 0 : this.e.hashCode());
/* 272 */     i = 31 * i + ((this.f == null) ? 0 : this.f.hashCode());
/* 273 */     i = 31 * i + ((this.g == null) ? 0 : this.g.hashCode());
/* 274 */     i = 31 * i + ((this.h == null) ? 0 : this.h.hashCode());
/* 275 */     i = 31 * i + ((this.i == null) ? 0 : this.i.hashCode());
/* 276 */     i = 31 * i + ((this.j == null) ? 0 : this.j.hashCode());
/*     */     
/* 278 */     return i;
/*     */   }
/*     */   
/*     */   public ChatModifier clone() {
/* 282 */     ChatModifier chatmodifier = new ChatModifier();
/*     */     
/* 284 */     chatmodifier.c = this.c;
/* 285 */     chatmodifier.d = this.d;
/* 286 */     chatmodifier.f = this.f;
/* 287 */     chatmodifier.e = this.e;
/* 288 */     chatmodifier.g = this.g;
/* 289 */     chatmodifier.b = this.b;
/* 290 */     chatmodifier.h = this.h;
/* 291 */     chatmodifier.i = this.i;
/* 292 */     chatmodifier.a = this.a;
/* 293 */     chatmodifier.j = this.j;
/* 294 */     return chatmodifier;
/*     */   }
/*     */   
/*     */   public ChatModifier n() {
/* 298 */     ChatModifier chatmodifier = new ChatModifier();
/*     */     
/* 300 */     chatmodifier.setBold(Boolean.valueOf(isBold()));
/* 301 */     chatmodifier.setItalic(Boolean.valueOf(isItalic()));
/* 302 */     chatmodifier.setStrikethrough(Boolean.valueOf(isStrikethrough()));
/* 303 */     chatmodifier.setUnderline(Boolean.valueOf(isUnderlined()));
/* 304 */     chatmodifier.setRandom(Boolean.valueOf(isRandom()));
/* 305 */     chatmodifier.setColor(getColor());
/* 306 */     chatmodifier.setChatClickable(h());
/* 307 */     chatmodifier.setChatHoverable(i());
/* 308 */     chatmodifier.setInsertion(j());
/* 309 */     return chatmodifier;
/*     */   }
/*     */ 
/*     */   
/*     */   public static class ChatModifierSerializer
/*     */     implements JsonDeserializer<ChatModifier>, JsonSerializer<ChatModifier>
/*     */   {
/*     */     @Nullable
/*     */     public ChatModifier a(JsonElement jsonelement, Type type, JsonDeserializationContext jsondeserializationcontext) throws JsonParseException {
/* 318 */       if (jsonelement.isJsonObject()) {
/* 319 */         ChatModifier chatmodifier = new ChatModifier();
/* 320 */         JsonObject jsonobject = jsonelement.getAsJsonObject();
/*     */         
/* 322 */         if (jsonobject == null) {
/* 323 */           return null;
/*     */         }
/* 325 */         if (jsonobject.has("bold")) {
/* 326 */           chatmodifier.c = Boolean.valueOf(jsonobject.get("bold").getAsBoolean());
/*     */         }
/*     */         
/* 329 */         if (jsonobject.has("italic")) {
/* 330 */           chatmodifier.d = Boolean.valueOf(jsonobject.get("italic").getAsBoolean());
/*     */         }
/*     */         
/* 333 */         if (jsonobject.has("underlined")) {
/* 334 */           chatmodifier.e = Boolean.valueOf(jsonobject.get("underlined").getAsBoolean());
/*     */         }
/*     */         
/* 337 */         if (jsonobject.has("strikethrough")) {
/* 338 */           chatmodifier.f = Boolean.valueOf(jsonobject.get("strikethrough").getAsBoolean());
/*     */         }
/*     */         
/* 341 */         if (jsonobject.has("obfuscated")) {
/* 342 */           chatmodifier.g = Boolean.valueOf(jsonobject.get("obfuscated").getAsBoolean());
/*     */         }
/*     */         
/* 345 */         if (jsonobject.has("color")) {
/* 346 */           chatmodifier.b = (EnumChatFormat)jsondeserializationcontext.deserialize(jsonobject.get("color"), EnumChatFormat.class);
/*     */         }
/*     */         
/* 349 */         if (jsonobject.has("insertion")) {
/* 350 */           chatmodifier.j = jsonobject.get("insertion").getAsString();
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 356 */         if (jsonobject.has("clickEvent")) {
/* 357 */           JsonObject jsonobject1 = jsonobject.getAsJsonObject("clickEvent");
/* 358 */           if (jsonobject1 != null) {
/* 359 */             JsonPrimitive jsonprimitive = jsonobject1.getAsJsonPrimitive("action");
/* 360 */             ChatClickable.EnumClickAction chatclickable_enumclickaction = (jsonprimitive == null) ? null : ChatClickable.EnumClickAction.a(jsonprimitive.getAsString());
/* 361 */             JsonPrimitive jsonprimitive1 = jsonobject1.getAsJsonPrimitive("value");
/* 362 */             String s = (jsonprimitive1 == null) ? null : jsonprimitive1.getAsString();
/*     */             
/* 364 */             if (chatclickable_enumclickaction != null && s != null && chatclickable_enumclickaction.a()) {
/* 365 */               chatmodifier.h = new ChatClickable(chatclickable_enumclickaction, s);
/*     */             }
/*     */           } 
/*     */         } 
/*     */         
/* 370 */         if (jsonobject.has("hoverEvent")) {
/* 371 */           JsonObject jsonobject1 = jsonobject.getAsJsonObject("hoverEvent");
/* 372 */           if (jsonobject1 != null) {
/* 373 */             JsonPrimitive jsonprimitive = jsonobject1.getAsJsonPrimitive("action");
/* 374 */             ChatHoverable.EnumHoverAction chathoverable_enumhoveraction = (jsonprimitive == null) ? null : ChatHoverable.EnumHoverAction.a(jsonprimitive.getAsString());
/* 375 */             IChatBaseComponent ichatbasecomponent = (IChatBaseComponent)jsondeserializationcontext.deserialize(jsonobject1.get("value"), IChatBaseComponent.class);
/*     */             
/* 377 */             if (chathoverable_enumhoveraction != null && ichatbasecomponent != null && chathoverable_enumhoveraction.a()) {
/* 378 */               chatmodifier.i = new ChatHoverable(chathoverable_enumhoveraction, ichatbasecomponent);
/*     */             }
/*     */           } 
/*     */         } 
/*     */         
/* 383 */         return chatmodifier;
/*     */       } 
/*     */       
/* 386 */       return null;
/*     */     }
/*     */ 
/*     */     
/*     */     @Nullable
/*     */     public JsonElement a(ChatModifier chatmodifier, Type type, JsonSerializationContext jsonserializationcontext) {
/* 392 */       if (chatmodifier.g()) {
/* 393 */         return null;
/*     */       }
/* 395 */       JsonObject jsonobject = new JsonObject();
/*     */       
/* 397 */       if (chatmodifier.c != null) {
/* 398 */         jsonobject.addProperty("bold", chatmodifier.c);
/*     */       }
/*     */       
/* 401 */       if (chatmodifier.d != null) {
/* 402 */         jsonobject.addProperty("italic", chatmodifier.d);
/*     */       }
/*     */       
/* 405 */       if (chatmodifier.e != null) {
/* 406 */         jsonobject.addProperty("underlined", chatmodifier.e);
/*     */       }
/*     */       
/* 409 */       if (chatmodifier.f != null) {
/* 410 */         jsonobject.addProperty("strikethrough", chatmodifier.f);
/*     */       }
/*     */       
/* 413 */       if (chatmodifier.g != null) {
/* 414 */         jsonobject.addProperty("obfuscated", chatmodifier.g);
/*     */       }
/*     */       
/* 417 */       if (chatmodifier.b != null) {
/* 418 */         jsonobject.add("color", jsonserializationcontext.serialize(chatmodifier.b));
/*     */       }
/*     */       
/* 421 */       if (chatmodifier.j != null) {
/* 422 */         jsonobject.add("insertion", jsonserializationcontext.serialize(chatmodifier.j));
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 427 */       if (chatmodifier.h != null) {
/* 428 */         JsonObject jsonobject1 = new JsonObject();
/* 429 */         jsonobject1.addProperty("action", chatmodifier.h.a().b());
/* 430 */         jsonobject1.addProperty("value", chatmodifier.h.b());
/* 431 */         jsonobject.add("clickEvent", (JsonElement)jsonobject1);
/*     */       } 
/*     */       
/* 434 */       if (chatmodifier.i != null) {
/* 435 */         JsonObject jsonobject1 = new JsonObject();
/* 436 */         jsonobject1.addProperty("action", chatmodifier.i.a().b());
/* 437 */         jsonobject1.add("value", jsonserializationcontext.serialize(chatmodifier.i.b()));
/* 438 */         jsonobject.add("hoverEvent", (JsonElement)jsonobject1);
/*     */       } 
/*     */       
/* 441 */       return (JsonElement)jsonobject;
/*     */     }
/*     */ 
/*     */     
/*     */     @Nullable
/*     */     public JsonElement serialize(ChatModifier object, Type type, JsonSerializationContext jsonserializationcontext) {
/* 447 */       return a(object, type, jsonserializationcontext);
/*     */     }
/*     */     
/*     */     @Nullable
/*     */     public ChatModifier deserialize(JsonElement jsonelement, Type type, JsonDeserializationContext jsondeserializationcontext) throws JsonParseException {
/* 452 */       return a(jsonelement, type, jsondeserializationcontext);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ChatModifier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */