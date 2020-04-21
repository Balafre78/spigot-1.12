/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonDeserializer;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonParseException;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import com.google.gson.JsonSerializer;
/*    */ import com.google.gson.JsonSyntaxException;
/*    */ import java.lang.reflect.Type;
/*    */ import java.util.Collection;
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class LotoSelectorEntry
/*    */ {
/*    */   protected final int c;
/*    */   protected final int d;
/*    */   protected final LootItemCondition[] e;
/*    */   
/*    */   protected LotoSelectorEntry(int paramInt1, int paramInt2, LootItemCondition[] paramArrayOfLootItemCondition) {
/* 26 */     this.c = paramInt1;
/* 27 */     this.d = paramInt2;
/* 28 */     this.e = paramArrayOfLootItemCondition;
/*    */   }
/*    */   public abstract void a(Collection<ItemStack> paramCollection, Random paramRandom, LootTableInfo paramLootTableInfo);
/*    */   public int a(float paramFloat) {
/* 32 */     return Math.max(MathHelper.d(this.c + this.d * paramFloat), 0);
/*    */   }
/*    */   
/*    */   protected abstract void a(JsonObject paramJsonObject, JsonSerializationContext paramJsonSerializationContext);
/*    */   
/*    */   public static class a implements JsonDeserializer<LotoSelectorEntry>, JsonSerializer<LotoSelectorEntry> {
/*    */     public LotoSelectorEntry a(JsonElement param1JsonElement, Type param1Type, JsonDeserializationContext param1JsonDeserializationContext) throws JsonParseException {
/*    */       LootItemCondition[] arrayOfLootItemCondition;
/* 40 */       JsonObject jsonObject = ChatDeserializer.m(param1JsonElement, "loot item");
/* 41 */       String str = ChatDeserializer.h(jsonObject, "type");
/* 42 */       int i = ChatDeserializer.a(jsonObject, "weight", 1);
/* 43 */       int j = ChatDeserializer.a(jsonObject, "quality", 0);
/*    */ 
/*    */       
/* 46 */       if (jsonObject.has("conditions")) {
/* 47 */         arrayOfLootItemCondition = ChatDeserializer.<LootItemCondition[]>a(jsonObject, "conditions", param1JsonDeserializationContext, (Class)LootItemCondition[].class);
/*    */       } else {
/* 49 */         arrayOfLootItemCondition = new LootItemCondition[0];
/*    */       } 
/*    */       
/* 52 */       if ("item".equals(str))
/* 53 */         return LootItem.a(jsonObject, param1JsonDeserializationContext, i, j, arrayOfLootItemCondition); 
/* 54 */       if ("loot_table".equals(str))
/* 55 */         return LootSelectorLootTable.a(jsonObject, param1JsonDeserializationContext, i, j, arrayOfLootItemCondition); 
/* 56 */       if ("empty".equals(str)) {
/* 57 */         return LootSelectorEmpty.a(jsonObject, param1JsonDeserializationContext, i, j, arrayOfLootItemCondition);
/*    */       }
/* 59 */       throw new JsonSyntaxException("Unknown loot entry type '" + str + "'");
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public JsonElement a(LotoSelectorEntry param1LotoSelectorEntry, Type param1Type, JsonSerializationContext param1JsonSerializationContext) {
/* 65 */       JsonObject jsonObject = new JsonObject();
/*    */       
/* 67 */       jsonObject.addProperty("weight", Integer.valueOf(param1LotoSelectorEntry.c));
/* 68 */       jsonObject.addProperty("quality", Integer.valueOf(param1LotoSelectorEntry.d));
/*    */       
/* 70 */       if (param1LotoSelectorEntry.e.length > 0) {
/* 71 */         jsonObject.add("conditions", param1JsonSerializationContext.serialize(param1LotoSelectorEntry.e));
/*    */       }
/*    */       
/* 74 */       if (param1LotoSelectorEntry instanceof LootItem) {
/* 75 */         jsonObject.addProperty("type", "item");
/* 76 */       } else if (param1LotoSelectorEntry instanceof LootSelectorLootTable) {
/* 77 */         jsonObject.addProperty("type", "loot_table");
/* 78 */       } else if (param1LotoSelectorEntry instanceof LootSelectorEmpty) {
/* 79 */         jsonObject.addProperty("type", "empty");
/*    */       } else {
/* 81 */         throw new IllegalArgumentException("Don't know how to serialize " + param1LotoSelectorEntry);
/*    */       } 
/* 83 */       param1LotoSelectorEntry.a(jsonObject, param1JsonSerializationContext);
/*    */       
/* 85 */       return (JsonElement)jsonObject;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\LotoSelectorEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */