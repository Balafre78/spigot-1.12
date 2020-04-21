/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonDeserializer;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonParseException;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import com.google.gson.JsonSerializer;
/*    */ import com.google.gson.JsonSyntaxException;
/*    */ import java.lang.reflect.Type;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LootItemFunctions
/*    */ {
/* 20 */   private static final Map<MinecraftKey, LootItemFunction.a<?>> a = Maps.newHashMap();
/* 21 */   private static final Map<Class<? extends LootItemFunction>, LootItemFunction.a<?>> b = Maps.newHashMap();
/*    */   
/*    */   static {
/* 24 */     a(new LootItemFunctionSetCount.a());
/* 25 */     a(new LootItemFunctionSetData.a());
/* 26 */     a(new LootEnchantLevel.a());
/* 27 */     a(new LootItemFunctionEnchant.a());
/* 28 */     a(new LootItemFunctionSetTag.a());
/* 29 */     a(new LootItemFunctionSmelt.a());
/* 30 */     a(new LootEnchantFunction.a());
/* 31 */     a(new LootItemFunctionSetDamage.a());
/* 32 */     a(new LootItemFunctionSetAttribute.b());
/*    */   }
/*    */ 
/*    */   
/*    */   public static <T extends LootItemFunction> void a(LootItemFunction.a<? extends T> parama) {
/* 37 */     MinecraftKey minecraftKey = parama.a();
/* 38 */     Class<? extends T> clazz = parama.b();
/* 39 */     if (a.containsKey(minecraftKey)) {
/* 40 */       throw new IllegalArgumentException("Can't re-register item function name " + minecraftKey);
/*    */     }
/* 42 */     if (b.containsKey(clazz)) {
/* 43 */       throw new IllegalArgumentException("Can't re-register item function class " + clazz.getName());
/*    */     }
/* 45 */     a.put(minecraftKey, parama);
/* 46 */     b.put(clazz, parama);
/*    */   }
/*    */   
/*    */   public static LootItemFunction.a<?> a(MinecraftKey paramMinecraftKey) {
/* 50 */     LootItemFunction.a<?> a = a.get(paramMinecraftKey);
/* 51 */     if (a == null) {
/* 52 */       throw new IllegalArgumentException("Unknown loot item function '" + paramMinecraftKey + "'");
/*    */     }
/* 54 */     return a;
/*    */   }
/*    */ 
/*    */   
/*    */   public static <T extends LootItemFunction> LootItemFunction.a<T> a(T paramT) {
/* 59 */     LootItemFunction.a<T> a = (LootItemFunction.a)b.get(paramT.getClass());
/* 60 */     if (a == null) {
/* 61 */       throw new IllegalArgumentException("Unknown loot item function " + paramT);
/*    */     }
/* 63 */     return a;
/*    */   }
/*    */   
/*    */   public static class a implements JsonDeserializer<LootItemFunction>, JsonSerializer<LootItemFunction> {
/*    */     public LootItemFunction a(JsonElement param1JsonElement, Type param1Type, JsonDeserializationContext param1JsonDeserializationContext) throws JsonParseException {
/*    */       LootItemFunction.a<?> a1;
/* 69 */       JsonObject jsonObject = ChatDeserializer.m(param1JsonElement, "function");
/* 70 */       MinecraftKey minecraftKey = new MinecraftKey(ChatDeserializer.h(jsonObject, "function"));
/*    */ 
/*    */       
/*    */       try {
/* 74 */         a1 = LootItemFunctions.a(minecraftKey);
/* 75 */       } catch (IllegalArgumentException illegalArgumentException) {
/* 76 */         throw new JsonSyntaxException("Unknown function '" + minecraftKey + "'");
/*    */       } 
/* 78 */       return (LootItemFunction)a1.b(jsonObject, param1JsonDeserializationContext, ChatDeserializer.<LootItemCondition[]>a(jsonObject, "conditions", new LootItemCondition[0], param1JsonDeserializationContext, (Class)LootItemCondition[].class));
/*    */     }
/*    */ 
/*    */     
/*    */     public JsonElement a(LootItemFunction param1LootItemFunction, Type param1Type, JsonSerializationContext param1JsonSerializationContext) {
/* 83 */       LootItemFunction.a<LootItemFunction> a1 = LootItemFunctions.a(param1LootItemFunction);
/* 84 */       JsonObject jsonObject = new JsonObject();
/* 85 */       a1.a(jsonObject, param1LootItemFunction, param1JsonSerializationContext);
/* 86 */       jsonObject.addProperty("function", a1.a().toString());
/* 87 */       if (param1LootItemFunction.a() != null && (param1LootItemFunction.a()).length > 0) {
/* 88 */         jsonObject.add("conditions", param1JsonSerializationContext.serialize(param1LootItemFunction.a()));
/*    */       }
/* 90 */       return (JsonElement)jsonObject;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\LootItemFunctions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */