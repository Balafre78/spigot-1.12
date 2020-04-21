/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import java.util.LinkedHashMap;
/*    */ import java.util.Map;
/*    */ import java.util.Random;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LootItemConditionEntityScore
/*    */   implements LootItemCondition
/*    */ {
/*    */   private final Map<String, LootValueBounds> a;
/*    */   private final LootTableInfo.EntityTarget b;
/*    */   
/*    */   public LootItemConditionEntityScore(Map<String, LootValueBounds> paramMap, LootTableInfo.EntityTarget paramEntityTarget) {
/* 26 */     this.a = paramMap;
/* 27 */     this.b = paramEntityTarget;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(Random paramRandom, LootTableInfo paramLootTableInfo) {
/* 32 */     Entity entity = paramLootTableInfo.a(this.b);
/* 33 */     if (entity == null) {
/* 34 */       return false;
/*    */     }
/* 36 */     Scoreboard scoreboard = entity.world.getScoreboard();
/* 37 */     for (Map.Entry<String, LootValueBounds> entry : this.a.entrySet()) {
/* 38 */       if (!a(entity, scoreboard, (String)entry.getKey(), (LootValueBounds)entry.getValue())) {
/* 39 */         return false;
/*    */       }
/*    */     } 
/* 42 */     return true;
/*    */   }
/*    */   
/*    */   protected boolean a(Entity paramEntity, Scoreboard paramScoreboard, String paramString, LootValueBounds paramLootValueBounds) {
/* 46 */     ScoreboardObjective scoreboardObjective = paramScoreboard.getObjective(paramString);
/* 47 */     if (scoreboardObjective == null) {
/* 48 */       return false;
/*    */     }
/* 50 */     String str = (paramEntity instanceof EntityPlayer) ? paramEntity.getName() : paramEntity.bn();
/* 51 */     if (!paramScoreboard.b(str, scoreboardObjective)) {
/* 52 */       return false;
/*    */     }
/* 54 */     return paramLootValueBounds.a(paramScoreboard.getPlayerScoreForObjective(str, scoreboardObjective).getScore());
/*    */   }
/*    */   
/*    */   public static class a extends LootItemCondition.a<LootItemConditionEntityScore> {
/*    */     protected a() {
/* 59 */       super(new MinecraftKey("entity_scores"), LootItemConditionEntityScore.class);
/*    */     }
/*    */ 
/*    */     
/*    */     public void a(JsonObject param1JsonObject, LootItemConditionEntityScore param1LootItemConditionEntityScore, JsonSerializationContext param1JsonSerializationContext) {
/* 64 */       JsonObject jsonObject = new JsonObject();
/* 65 */       for (Map.Entry entry : LootItemConditionEntityScore.a(param1LootItemConditionEntityScore).entrySet()) {
/* 66 */         jsonObject.add((String)entry.getKey(), param1JsonSerializationContext.serialize(entry.getValue()));
/*    */       }
/* 68 */       param1JsonObject.add("scores", (JsonElement)jsonObject);
/* 69 */       param1JsonObject.add("entity", param1JsonSerializationContext.serialize(LootItemConditionEntityScore.b(param1LootItemConditionEntityScore)));
/*    */     }
/*    */ 
/*    */     
/*    */     public LootItemConditionEntityScore a(JsonObject param1JsonObject, JsonDeserializationContext param1JsonDeserializationContext) {
/* 74 */       Set set = ChatDeserializer.t(param1JsonObject, "scores").entrySet();
/* 75 */       LinkedHashMap<String, LootValueBounds> linkedHashMap = Maps.newLinkedHashMap();
/* 76 */       for (Map.Entry entry : set) {
/* 77 */         linkedHashMap.put(entry.getKey(), ChatDeserializer.a((JsonElement)entry.getValue(), "score", param1JsonDeserializationContext, LootValueBounds.class));
/*    */       }
/* 79 */       return new LootItemConditionEntityScore(linkedHashMap, ChatDeserializer.<LootTableInfo.EntityTarget>a(param1JsonObject, "entity", param1JsonDeserializationContext, LootTableInfo.EntityTarget.class));
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\LootItemConditionEntityScore.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */