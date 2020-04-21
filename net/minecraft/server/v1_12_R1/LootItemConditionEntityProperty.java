/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import java.util.Map;
/*    */ import java.util.Random;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LootItemConditionEntityProperty
/*    */   implements LootItemCondition
/*    */ {
/*    */   private final LootEntityProperty[] a;
/*    */   private final LootTableInfo.EntityTarget b;
/*    */   
/*    */   public LootItemConditionEntityProperty(LootEntityProperty[] paramArrayOfLootEntityProperty, LootTableInfo.EntityTarget paramEntityTarget) {
/* 23 */     this.a = paramArrayOfLootEntityProperty;
/* 24 */     this.b = paramEntityTarget;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(Random paramRandom, LootTableInfo paramLootTableInfo) {
/* 29 */     Entity entity = paramLootTableInfo.a(this.b);
/* 30 */     if (entity == null) {
/* 31 */       return false;
/*    */     }
/* 33 */     for (LootEntityProperty lootEntityProperty : this.a) {
/* 34 */       if (!lootEntityProperty.a(paramRandom, entity)) {
/* 35 */         return false;
/*    */       }
/*    */     } 
/* 38 */     return true;
/*    */   }
/*    */   
/*    */   public static class a extends LootItemCondition.a<LootItemConditionEntityProperty> {
/*    */     protected a() {
/* 43 */       super(new MinecraftKey("entity_properties"), LootItemConditionEntityProperty.class);
/*    */     }
/*    */ 
/*    */     
/*    */     public void a(JsonObject param1JsonObject, LootItemConditionEntityProperty param1LootItemConditionEntityProperty, JsonSerializationContext param1JsonSerializationContext) {
/* 48 */       JsonObject jsonObject = new JsonObject();
/* 49 */       for (LootEntityProperty lootEntityProperty : LootItemConditionEntityProperty.a(param1LootItemConditionEntityProperty)) {
/* 50 */         LootEntityProperty.a<LootEntityProperty> a1 = LootEntityProperties.a(lootEntityProperty);
/* 51 */         jsonObject.add(a1.a().toString(), a1.a(lootEntityProperty, param1JsonSerializationContext));
/*    */       } 
/* 53 */       param1JsonObject.add("properties", (JsonElement)jsonObject);
/* 54 */       param1JsonObject.add("entity", param1JsonSerializationContext.serialize(LootItemConditionEntityProperty.b(param1LootItemConditionEntityProperty)));
/*    */     }
/*    */ 
/*    */     
/*    */     public LootItemConditionEntityProperty a(JsonObject param1JsonObject, JsonDeserializationContext param1JsonDeserializationContext) {
/* 59 */       Set set = ChatDeserializer.t(param1JsonObject, "properties").entrySet();
/* 60 */       LootEntityProperty[] arrayOfLootEntityProperty = new LootEntityProperty[set.size()];
/* 61 */       byte b = 0;
/* 62 */       for (Map.Entry entry : set) {
/* 63 */         arrayOfLootEntityProperty[b++] = (LootEntityProperty)LootEntityProperties.a(new MinecraftKey((String)entry.getKey())).a((JsonElement)entry.getValue(), param1JsonDeserializationContext);
/*    */       }
/* 65 */       return new LootItemConditionEntityProperty(arrayOfLootEntityProperty, ChatDeserializer.<LootTableInfo.EntityTarget>a(param1JsonObject, "entity", param1JsonDeserializationContext, LootTableInfo.EntityTarget.class));
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\LootItemConditionEntityProperty.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */