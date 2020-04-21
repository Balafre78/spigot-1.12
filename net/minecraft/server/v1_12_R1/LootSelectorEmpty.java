/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import java.util.Collection;
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ public class LootSelectorEmpty
/*    */   extends LotoSelectorEntry
/*    */ {
/*    */   public LootSelectorEmpty(int paramInt1, int paramInt2, LootItemCondition[] paramArrayOfLootItemCondition) {
/* 14 */     super(paramInt1, paramInt2, paramArrayOfLootItemCondition);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(Collection<ItemStack> paramCollection, Random paramRandom, LootTableInfo paramLootTableInfo) {}
/*    */ 
/*    */   
/*    */   protected void a(JsonObject paramJsonObject, JsonSerializationContext paramJsonSerializationContext) {}
/*    */ 
/*    */   
/*    */   public static LootSelectorEmpty a(JsonObject paramJsonObject, JsonDeserializationContext paramJsonDeserializationContext, int paramInt1, int paramInt2, LootItemCondition[] paramArrayOfLootItemCondition) {
/* 26 */     return new LootSelectorEmpty(paramInt1, paramInt2, paramArrayOfLootItemCondition);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\LootSelectorEmpty.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */