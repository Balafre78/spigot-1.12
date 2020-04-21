/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import java.util.Collection;
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LootItem
/*    */   extends LotoSelectorEntry
/*    */ {
/*    */   protected final Item a;
/*    */   protected final LootItemFunction[] b;
/*    */   
/*    */   public LootItem(Item paramItem, int paramInt1, int paramInt2, LootItemFunction[] paramArrayOfLootItemFunction, LootItemCondition[] paramArrayOfLootItemCondition) {
/* 22 */     super(paramInt1, paramInt2, paramArrayOfLootItemCondition);
/* 23 */     this.a = paramItem;
/* 24 */     this.b = paramArrayOfLootItemFunction;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(Collection<ItemStack> paramCollection, Random paramRandom, LootTableInfo paramLootTableInfo) {
/* 29 */     ItemStack itemStack = new ItemStack(this.a);
/* 30 */     for (LootItemFunction lootItemFunction : this.b) {
/* 31 */       if (LootItemConditions.a(lootItemFunction.a(), paramRandom, paramLootTableInfo)) {
/* 32 */         itemStack = lootItemFunction.a(itemStack, paramRandom, paramLootTableInfo);
/*    */       }
/*    */     } 
/*    */     
/* 36 */     if (!itemStack.isEmpty()) {
/* 37 */       if (itemStack.getCount() < this.a.getMaxStackSize()) {
/* 38 */         paramCollection.add(itemStack);
/*    */       } else {
/* 40 */         int i = itemStack.getCount();
/* 41 */         while (i > 0) {
/* 42 */           ItemStack itemStack1 = itemStack.cloneItemStack();
/* 43 */           itemStack1.setCount(Math.min(itemStack.getMaxStackSize(), i));
/* 44 */           i -= itemStack1.getCount();
/* 45 */           paramCollection.add(itemStack1);
/*    */         } 
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(JsonObject paramJsonObject, JsonSerializationContext paramJsonSerializationContext) {
/* 53 */     if (this.b != null && this.b.length > 0) {
/* 54 */       paramJsonObject.add("functions", paramJsonSerializationContext.serialize(this.b));
/*    */     }
/*    */     
/* 57 */     MinecraftKey minecraftKey = Item.REGISTRY.b(this.a);
/* 58 */     if (minecraftKey == null) {
/* 59 */       throw new IllegalArgumentException("Can't serialize unknown item " + this.a);
/*    */     }
/*    */     
/* 62 */     paramJsonObject.addProperty("name", minecraftKey.toString());
/*    */   }
/*    */   public static LootItem a(JsonObject paramJsonObject, JsonDeserializationContext paramJsonDeserializationContext, int paramInt1, int paramInt2, LootItemCondition[] paramArrayOfLootItemCondition) {
/*    */     LootItemFunction[] arrayOfLootItemFunction;
/* 66 */     Item item = ChatDeserializer.i(paramJsonObject, "name");
/*    */ 
/*    */     
/* 69 */     if (paramJsonObject.has("functions")) {
/* 70 */       arrayOfLootItemFunction = ChatDeserializer.<LootItemFunction[]>a(paramJsonObject, "functions", paramJsonDeserializationContext, (Class)LootItemFunction[].class);
/*    */     } else {
/* 72 */       arrayOfLootItemFunction = new LootItemFunction[0];
/*    */     } 
/*    */     
/* 75 */     return new LootItem(item, paramInt1, paramInt2, arrayOfLootItemFunction, paramArrayOfLootItemCondition);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\LootItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */