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
/*    */ import java.util.Random;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LootItemConditions
/*    */ {
/* 22 */   private static final Map<MinecraftKey, LootItemCondition.a<?>> a = Maps.newHashMap();
/* 23 */   private static final Map<Class<? extends LootItemCondition>, LootItemCondition.a<?>> b = Maps.newHashMap();
/*    */   
/*    */   static {
/* 26 */     a(new LootItemConditionRandomChance.a());
/* 27 */     a(new LootItemConditionRandomChanceWithLooting.a());
/* 28 */     a(new LootItemConditionEntityProperty.a());
/* 29 */     a(new LootItemConditionKilledByPlayer.a());
/* 30 */     a(new LootItemConditionEntityScore.a());
/*    */   }
/*    */ 
/*    */   
/*    */   public static <T extends LootItemCondition> void a(LootItemCondition.a<? extends T> parama) {
/* 35 */     MinecraftKey minecraftKey = parama.a();
/* 36 */     Class<? extends T> clazz = parama.b();
/* 37 */     if (a.containsKey(minecraftKey)) {
/* 38 */       throw new IllegalArgumentException("Can't re-register item condition name " + minecraftKey);
/*    */     }
/* 40 */     if (b.containsKey(clazz)) {
/* 41 */       throw new IllegalArgumentException("Can't re-register item condition class " + clazz.getName());
/*    */     }
/* 43 */     a.put(minecraftKey, parama);
/* 44 */     b.put(clazz, parama);
/*    */   }
/*    */   
/*    */   public static boolean a(@Nullable LootItemCondition[] paramArrayOfLootItemCondition, Random paramRandom, LootTableInfo paramLootTableInfo) {
/* 48 */     if (paramArrayOfLootItemCondition == null) {
/* 49 */       return true;
/*    */     }
/* 51 */     for (LootItemCondition lootItemCondition : paramArrayOfLootItemCondition) {
/* 52 */       if (!lootItemCondition.a(paramRandom, paramLootTableInfo)) {
/* 53 */         return false;
/*    */       }
/*    */     } 
/* 56 */     return true;
/*    */   }
/*    */   
/*    */   public static LootItemCondition.a<?> a(MinecraftKey paramMinecraftKey) {
/* 60 */     LootItemCondition.a<?> a = a.get(paramMinecraftKey);
/* 61 */     if (a == null) {
/* 62 */       throw new IllegalArgumentException("Unknown loot item condition '" + paramMinecraftKey + "'");
/*    */     }
/* 64 */     return a;
/*    */   }
/*    */ 
/*    */   
/*    */   public static <T extends LootItemCondition> LootItemCondition.a<T> a(T paramT) {
/* 69 */     LootItemCondition.a<T> a = (LootItemCondition.a)b.get(paramT.getClass());
/* 70 */     if (a == null) {
/* 71 */       throw new IllegalArgumentException("Unknown loot item condition " + paramT);
/*    */     }
/* 73 */     return a;
/*    */   }
/*    */   
/*    */   public static class a implements JsonDeserializer<LootItemCondition>, JsonSerializer<LootItemCondition> {
/*    */     public LootItemCondition a(JsonElement param1JsonElement, Type param1Type, JsonDeserializationContext param1JsonDeserializationContext) throws JsonParseException {
/*    */       LootItemCondition.a<?> a1;
/* 79 */       JsonObject jsonObject = ChatDeserializer.m(param1JsonElement, "condition");
/* 80 */       MinecraftKey minecraftKey = new MinecraftKey(ChatDeserializer.h(jsonObject, "condition"));
/*    */ 
/*    */       
/*    */       try {
/* 84 */         a1 = LootItemConditions.a(minecraftKey);
/* 85 */       } catch (IllegalArgumentException illegalArgumentException) {
/* 86 */         throw new JsonSyntaxException("Unknown condition '" + minecraftKey + "'");
/*    */       } 
/* 88 */       return (LootItemCondition)a1.b(jsonObject, param1JsonDeserializationContext);
/*    */     }
/*    */ 
/*    */     
/*    */     public JsonElement a(LootItemCondition param1LootItemCondition, Type param1Type, JsonSerializationContext param1JsonSerializationContext) {
/* 93 */       LootItemCondition.a<LootItemCondition> a1 = LootItemConditions.a(param1LootItemCondition);
/* 94 */       JsonObject jsonObject = new JsonObject();
/* 95 */       a1.a(jsonObject, param1LootItemCondition, param1JsonSerializationContext);
/* 96 */       jsonObject.addProperty("condition", a1.a().toString());
/* 97 */       return (JsonElement)jsonObject;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\LootItemConditions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */