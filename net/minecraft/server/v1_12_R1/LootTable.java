/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.gson.JsonDeserializationContext;
/*     */ import com.google.gson.JsonDeserializer;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParseException;
/*     */ import com.google.gson.JsonSerializationContext;
/*     */ import com.google.gson.JsonSerializer;
/*     */ import java.lang.reflect.Type;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LootTable
/*     */ {
/*  25 */   private static final Logger b = LogManager.getLogger();
/*     */   
/*  27 */   public static final LootTable a = new LootTable(new LootSelector[0]);
/*     */   
/*     */   private final LootSelector[] c;
/*     */   
/*     */   public LootTable(LootSelector[] paramArrayOfLootSelector) {
/*  32 */     this.c = paramArrayOfLootSelector;
/*     */   }
/*     */   
/*     */   public List<ItemStack> a(Random paramRandom, LootTableInfo paramLootTableInfo) {
/*  36 */     ArrayList<ItemStack> arrayList = Lists.newArrayList();
/*  37 */     if (paramLootTableInfo.a(this)) {
/*  38 */       for (LootSelector lootSelector : this.c) {
/*  39 */         lootSelector.b(arrayList, paramRandom, paramLootTableInfo);
/*     */       }
/*  41 */       paramLootTableInfo.b(this);
/*     */     } else {
/*  43 */       b.warn("Detected infinite loop in loot tables");
/*     */     } 
/*  45 */     return arrayList;
/*     */   }
/*     */   
/*     */   public void a(IInventory paramIInventory, Random paramRandom, LootTableInfo paramLootTableInfo) {
/*  49 */     List<ItemStack> list = a(paramRandom, paramLootTableInfo);
/*  50 */     List<Integer> list1 = a(paramIInventory, paramRandom);
/*  51 */     a(list, list1.size(), paramRandom);
/*  52 */     for (ItemStack itemStack : list) {
/*  53 */       if (list1.isEmpty()) {
/*  54 */         b.warn("Tried to over-fill a container");
/*     */         
/*     */         return;
/*     */       } 
/*  58 */       if (itemStack.isEmpty()) {
/*  59 */         paramIInventory.setItem(((Integer)list1.remove(list1.size() - 1)).intValue(), ItemStack.a); continue;
/*     */       } 
/*  61 */       paramIInventory.setItem(((Integer)list1.remove(list1.size() - 1)).intValue(), itemStack);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void a(List<ItemStack> paramList, int paramInt, Random paramRandom) {
/*  67 */     ArrayList<ItemStack> arrayList = Lists.newArrayList();
/*  68 */     for (Iterator<ItemStack> iterator = paramList.iterator(); iterator.hasNext(); ) {
/*  69 */       ItemStack itemStack = iterator.next();
/*  70 */       if (itemStack.isEmpty()) {
/*  71 */         iterator.remove(); continue;
/*  72 */       }  if (itemStack.getCount() > 1) {
/*  73 */         arrayList.add(itemStack);
/*  74 */         iterator.remove();
/*     */       } 
/*     */     } 
/*  77 */     paramInt -= paramList.size();
/*     */     
/*  79 */     while (paramInt > 0 && !arrayList.isEmpty()) {
/*  80 */       ItemStack itemStack1 = arrayList.remove(MathHelper.nextInt(paramRandom, 0, arrayList.size() - 1));
/*  81 */       int i = MathHelper.nextInt(paramRandom, 1, itemStack1.getCount() / 2);
/*  82 */       ItemStack itemStack2 = itemStack1.cloneAndSubtract(i);
/*     */       
/*  84 */       if (itemStack1.getCount() > 1 && paramRandom.nextBoolean()) {
/*  85 */         arrayList.add(itemStack1);
/*     */       } else {
/*  87 */         paramList.add(itemStack1);
/*     */       } 
/*     */       
/*  90 */       if (itemStack2.getCount() > 1 && paramRandom.nextBoolean()) {
/*  91 */         arrayList.add(itemStack2); continue;
/*     */       } 
/*  93 */       paramList.add(itemStack2);
/*     */     } 
/*     */ 
/*     */     
/*  97 */     paramList.addAll(arrayList);
/*     */     
/*  99 */     Collections.shuffle(paramList, paramRandom);
/*     */   }
/*     */   
/*     */   private List<Integer> a(IInventory paramIInventory, Random paramRandom) {
/* 103 */     ArrayList<Integer> arrayList = Lists.newArrayList();
/*     */     
/* 105 */     for (byte b = 0; b < paramIInventory.getSize(); b++) {
/* 106 */       if (paramIInventory.getItem(b).isEmpty()) {
/* 107 */         arrayList.add(Integer.valueOf(b));
/*     */       }
/*     */     } 
/*     */     
/* 111 */     Collections.shuffle(arrayList, paramRandom);
/* 112 */     return arrayList;
/*     */   }
/*     */   
/*     */   public static class a
/*     */     implements JsonDeserializer<LootTable>, JsonSerializer<LootTable> {
/*     */     public LootTable a(JsonElement param1JsonElement, Type param1Type, JsonDeserializationContext param1JsonDeserializationContext) throws JsonParseException {
/* 118 */       JsonObject jsonObject = ChatDeserializer.m(param1JsonElement, "loot table");
/* 119 */       LootSelector[] arrayOfLootSelector = ChatDeserializer.<LootSelector[]>a(jsonObject, "pools", new LootSelector[0], param1JsonDeserializationContext, (Class)LootSelector[].class);
/* 120 */       return new LootTable(arrayOfLootSelector);
/*     */     }
/*     */ 
/*     */     
/*     */     public JsonElement a(LootTable param1LootTable, Type param1Type, JsonSerializationContext param1JsonSerializationContext) {
/* 125 */       JsonObject jsonObject = new JsonObject();
/* 126 */       jsonObject.add("pools", param1JsonSerializationContext.serialize(LootTable.a(param1LootTable)));
/*     */       
/* 128 */       return (JsonElement)jsonObject;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\LootTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */