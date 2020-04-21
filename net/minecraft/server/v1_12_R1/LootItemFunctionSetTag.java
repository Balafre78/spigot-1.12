/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import com.google.gson.JsonSyntaxException;
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LootItemFunctionSetTag
/*    */   extends LootItemFunction
/*    */ {
/*    */   private final NBTTagCompound a;
/*    */   
/*    */   public LootItemFunctionSetTag(LootItemCondition[] paramArrayOfLootItemCondition, NBTTagCompound paramNBTTagCompound) {
/* 22 */     super(paramArrayOfLootItemCondition);
/* 23 */     this.a = paramNBTTagCompound;
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack a(ItemStack paramItemStack, Random paramRandom, LootTableInfo paramLootTableInfo) {
/* 28 */     NBTTagCompound nBTTagCompound = paramItemStack.getTag();
/*    */     
/* 30 */     if (nBTTagCompound == null) {
/* 31 */       nBTTagCompound = this.a.g();
/*    */     } else {
/* 33 */       nBTTagCompound.a(this.a);
/*    */     } 
/*    */     
/* 36 */     paramItemStack.setTag(nBTTagCompound);
/* 37 */     return paramItemStack;
/*    */   }
/*    */   
/*    */   public static class a extends LootItemFunction.a<LootItemFunctionSetTag> {
/*    */     public a() {
/* 42 */       super(new MinecraftKey("set_nbt"), LootItemFunctionSetTag.class);
/*    */     }
/*    */ 
/*    */     
/*    */     public void a(JsonObject param1JsonObject, LootItemFunctionSetTag param1LootItemFunctionSetTag, JsonSerializationContext param1JsonSerializationContext) {
/* 47 */       param1JsonObject.addProperty("tag", LootItemFunctionSetTag.a(param1LootItemFunctionSetTag).toString());
/*    */     }
/*    */ 
/*    */     
/*    */     public LootItemFunctionSetTag a(JsonObject param1JsonObject, JsonDeserializationContext param1JsonDeserializationContext, LootItemCondition[] param1ArrayOfLootItemCondition) {
/*    */       try {
/* 53 */         NBTTagCompound nBTTagCompound = MojangsonParser.parse(ChatDeserializer.h(param1JsonObject, "tag"));
/* 54 */         return new LootItemFunctionSetTag(param1ArrayOfLootItemCondition, nBTTagCompound);
/* 55 */       } catch (MojangsonParseException mojangsonParseException) {
/* 56 */         throw new JsonSyntaxException(mojangsonParseException);
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\LootItemFunctionSetTag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */