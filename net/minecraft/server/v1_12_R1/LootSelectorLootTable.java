/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LootSelectorLootTable
/*    */   extends LotoSelectorEntry
/*    */ {
/*    */   protected final MinecraftKey a;
/*    */   
/*    */   public LootSelectorLootTable(MinecraftKey paramMinecraftKey, int paramInt1, int paramInt2, LootItemCondition[] paramArrayOfLootItemCondition) {
/* 18 */     super(paramInt1, paramInt2, paramArrayOfLootItemCondition);
/* 19 */     this.a = paramMinecraftKey;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(Collection<ItemStack> paramCollection, Random paramRandom, LootTableInfo paramLootTableInfo) {
/* 24 */     LootTable lootTable = paramLootTableInfo.e().a(this.a);
/* 25 */     List<ItemStack> list = lootTable.a(paramRandom, paramLootTableInfo);
/* 26 */     paramCollection.addAll(list);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(JsonObject paramJsonObject, JsonSerializationContext paramJsonSerializationContext) {
/* 31 */     paramJsonObject.addProperty("name", this.a.toString());
/*    */   }
/*    */   
/*    */   public static LootSelectorLootTable a(JsonObject paramJsonObject, JsonDeserializationContext paramJsonDeserializationContext, int paramInt1, int paramInt2, LootItemCondition[] paramArrayOfLootItemCondition) {
/* 35 */     MinecraftKey minecraftKey = new MinecraftKey(ChatDeserializer.h(paramJsonObject, "name"));
/*    */     
/* 37 */     return new LootSelectorLootTable(minecraftKey, paramInt1, paramInt2, paramArrayOfLootItemCondition);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\LootSelectorLootTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */