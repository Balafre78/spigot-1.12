/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonDeserializationContext;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonPrimitive;
/*     */ import com.google.gson.JsonSerializationContext;
/*     */ import com.google.gson.JsonSyntaxException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LootItemFunctionEnchant
/*     */   extends LootItemFunction
/*     */ {
/*  30 */   private static final Logger a = LogManager.getLogger();
/*     */   
/*     */   private final List<Enchantment> b;
/*     */   
/*     */   public LootItemFunctionEnchant(LootItemCondition[] paramArrayOfLootItemCondition, @Nullable List<Enchantment> paramList) {
/*  35 */     super(paramArrayOfLootItemCondition);
/*  36 */     this.b = (paramList == null) ? Collections.<Enchantment>emptyList() : paramList;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack a(ItemStack paramItemStack, Random paramRandom, LootTableInfo paramLootTableInfo) {
/*     */     Enchantment enchantment;
/*  43 */     if (this.b.isEmpty()) {
/*  44 */       ArrayList<Enchantment> arrayList = Lists.newArrayList();
/*  45 */       for (Enchantment enchantment1 : Enchantment.enchantments) {
/*  46 */         if (paramItemStack.getItem() == Items.BOOK || enchantment1.canEnchant(paramItemStack)) {
/*  47 */           arrayList.add(enchantment1);
/*     */         }
/*     */       } 
/*  50 */       if (arrayList.isEmpty()) {
/*  51 */         a.warn("Couldn't find a compatible enchantment for {}", paramItemStack);
/*  52 */         return paramItemStack;
/*     */       } 
/*  54 */       enchantment = arrayList.get(paramRandom.nextInt(arrayList.size()));
/*     */     } else {
/*  56 */       enchantment = this.b.get(paramRandom.nextInt(this.b.size()));
/*     */     } 
/*     */     
/*  59 */     int i = MathHelper.nextInt(paramRandom, enchantment.getStartLevel(), enchantment.getMaxLevel());
/*  60 */     if (paramItemStack.getItem() == Items.BOOK) {
/*  61 */       paramItemStack = new ItemStack(Items.ENCHANTED_BOOK);
/*  62 */       ItemEnchantedBook.a(paramItemStack, new WeightedRandomEnchant(enchantment, i));
/*     */     } else {
/*  64 */       paramItemStack.addEnchantment(enchantment, i);
/*     */     } 
/*  66 */     return paramItemStack;
/*     */   }
/*     */   
/*     */   public static class a extends LootItemFunction.a<LootItemFunctionEnchant> {
/*     */     public a() {
/*  71 */       super(new MinecraftKey("enchant_randomly"), LootItemFunctionEnchant.class);
/*     */     }
/*     */ 
/*     */     
/*     */     public void a(JsonObject param1JsonObject, LootItemFunctionEnchant param1LootItemFunctionEnchant, JsonSerializationContext param1JsonSerializationContext) {
/*  76 */       if (!LootItemFunctionEnchant.a(param1LootItemFunctionEnchant).isEmpty()) {
/*  77 */         JsonArray jsonArray = new JsonArray();
/*  78 */         for (Enchantment enchantment : LootItemFunctionEnchant.a(param1LootItemFunctionEnchant)) {
/*  79 */           MinecraftKey minecraftKey = Enchantment.enchantments.b(enchantment);
/*  80 */           if (minecraftKey == null) {
/*  81 */             throw new IllegalArgumentException("Don't know how to serialize enchantment " + enchantment);
/*     */           }
/*  83 */           jsonArray.add((JsonElement)new JsonPrimitive(minecraftKey.toString()));
/*     */         } 
/*  85 */         param1JsonObject.add("enchantments", (JsonElement)jsonArray);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public LootItemFunctionEnchant a(JsonObject param1JsonObject, JsonDeserializationContext param1JsonDeserializationContext, LootItemCondition[] param1ArrayOfLootItemCondition) {
/*  91 */       ArrayList<Enchantment> arrayList = Lists.newArrayList();
/*  92 */       if (param1JsonObject.has("enchantments")) {
/*  93 */         JsonArray jsonArray = ChatDeserializer.u(param1JsonObject, "enchantments");
/*  94 */         for (JsonElement jsonElement : jsonArray) {
/*  95 */           String str = ChatDeserializer.a(jsonElement, "enchantment");
/*  96 */           Enchantment enchantment = Enchantment.enchantments.get(new MinecraftKey(str));
/*  97 */           if (enchantment == null) {
/*  98 */             throw new JsonSyntaxException("Unknown enchantment '" + str + "'");
/*     */           }
/* 100 */           arrayList.add(enchantment);
/*     */         } 
/*     */       } 
/* 103 */       return new LootItemFunctionEnchant(param1ArrayOfLootItemCondition, arrayList);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\LootItemFunctionEnchant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */