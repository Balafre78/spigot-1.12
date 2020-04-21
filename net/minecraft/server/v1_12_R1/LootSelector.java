/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonDeserializer;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonParseException;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import com.google.gson.JsonSerializer;
/*    */ import java.lang.reflect.Type;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import java.util.Random;
/*    */ import org.apache.commons.lang3.ArrayUtils;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LootSelector
/*    */ {
/*    */   private final LotoSelectorEntry[] a;
/*    */   private final LootItemCondition[] b;
/*    */   private final LootValueBounds c;
/*    */   private final LootValueBounds d;
/*    */   
/*    */   public LootSelector(LotoSelectorEntry[] paramArrayOfLotoSelectorEntry, LootItemCondition[] paramArrayOfLootItemCondition, LootValueBounds paramLootValueBounds1, LootValueBounds paramLootValueBounds2) {
/* 30 */     this.a = paramArrayOfLotoSelectorEntry;
/* 31 */     this.b = paramArrayOfLootItemCondition;
/* 32 */     this.c = paramLootValueBounds1;
/* 33 */     this.d = paramLootValueBounds2;
/*    */   }
/*    */   
/*    */   protected void a(Collection<ItemStack> paramCollection, Random paramRandom, LootTableInfo paramLootTableInfo) {
/* 37 */     ArrayList<LotoSelectorEntry> arrayList = Lists.newArrayList();
/* 38 */     int i = 0;
/* 39 */     for (LotoSelectorEntry lotoSelectorEntry : this.a) {
/* 40 */       if (LootItemConditions.a(lotoSelectorEntry.e, paramRandom, paramLootTableInfo)) {
/* 41 */         int k = lotoSelectorEntry.a(paramLootTableInfo.f());
/* 42 */         if (k > 0) {
/* 43 */           arrayList.add(lotoSelectorEntry);
/* 44 */           i += k;
/*    */         } 
/*    */       } 
/*    */     } 
/* 48 */     if (i == 0 || arrayList.isEmpty()) {
/*    */       return;
/*    */     }
/* 51 */     int j = paramRandom.nextInt(i);
/* 52 */     for (LotoSelectorEntry lotoSelectorEntry : arrayList) {
/* 53 */       j -= lotoSelectorEntry.a(paramLootTableInfo.f());
/* 54 */       if (j < 0) {
/* 55 */         lotoSelectorEntry.a(paramCollection, paramRandom, paramLootTableInfo);
/*    */         return;
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public void b(Collection<ItemStack> paramCollection, Random paramRandom, LootTableInfo paramLootTableInfo) {
/* 62 */     if (!LootItemConditions.a(this.b, paramRandom, paramLootTableInfo)) {
/*    */       return;
/*    */     }
/* 65 */     int i = this.c.a(paramRandom) + MathHelper.d(this.d.b(paramRandom) * paramLootTableInfo.f());
/* 66 */     for (byte b = 0; b < i; b++)
/* 67 */       a(paramCollection, paramRandom, paramLootTableInfo); 
/*    */   }
/*    */   
/*    */   public static class a
/*    */     implements JsonDeserializer<LootSelector>, JsonSerializer<LootSelector>
/*    */   {
/*    */     public LootSelector a(JsonElement param1JsonElement, Type param1Type, JsonDeserializationContext param1JsonDeserializationContext) throws JsonParseException {
/* 74 */       JsonObject jsonObject = ChatDeserializer.m(param1JsonElement, "loot pool");
/* 75 */       LotoSelectorEntry[] arrayOfLotoSelectorEntry = ChatDeserializer.<LotoSelectorEntry[]>a(jsonObject, "entries", param1JsonDeserializationContext, (Class)LotoSelectorEntry[].class);
/* 76 */       LootItemCondition[] arrayOfLootItemCondition = ChatDeserializer.<LootItemCondition[]>a(jsonObject, "conditions", new LootItemCondition[0], param1JsonDeserializationContext, (Class)LootItemCondition[].class);
/* 77 */       LootValueBounds lootValueBounds1 = ChatDeserializer.<LootValueBounds>a(jsonObject, "rolls", param1JsonDeserializationContext, LootValueBounds.class);
/* 78 */       LootValueBounds lootValueBounds2 = ChatDeserializer.<LootValueBounds>a(jsonObject, "bonus_rolls", new LootValueBounds(0.0F, 0.0F), param1JsonDeserializationContext, LootValueBounds.class);
/* 79 */       return new LootSelector(arrayOfLotoSelectorEntry, arrayOfLootItemCondition, lootValueBounds1, lootValueBounds2);
/*    */     }
/*    */ 
/*    */     
/*    */     public JsonElement a(LootSelector param1LootSelector, Type param1Type, JsonSerializationContext param1JsonSerializationContext) {
/* 84 */       JsonObject jsonObject = new JsonObject();
/* 85 */       jsonObject.add("entries", param1JsonSerializationContext.serialize(LootSelector.a(param1LootSelector)));
/* 86 */       jsonObject.add("rolls", param1JsonSerializationContext.serialize(LootSelector.b(param1LootSelector)));
/* 87 */       if (LootSelector.c(param1LootSelector).a() != 0.0F && LootSelector.c(param1LootSelector).b() != 0.0F) {
/* 88 */         jsonObject.add("bonus_rolls", param1JsonSerializationContext.serialize(LootSelector.c(param1LootSelector)));
/*    */       }
/* 90 */       if (!ArrayUtils.isEmpty((Object[])LootSelector.d(param1LootSelector))) {
/* 91 */         jsonObject.add("conditions", param1JsonSerializationContext.serialize(LootSelector.d(param1LootSelector)));
/*    */       }
/*    */       
/* 94 */       return (JsonElement)jsonObject;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\LootSelector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */