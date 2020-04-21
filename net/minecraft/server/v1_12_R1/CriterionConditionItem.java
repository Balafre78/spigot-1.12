/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonSyntaxException;
/*     */ import java.util.Map;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CriterionConditionItem
/*     */ {
/*  20 */   public static final CriterionConditionItem a = new CriterionConditionItem();
/*     */   
/*     */   private final Item b;
/*     */   private final Integer c;
/*     */   private final CriterionConditionValue d;
/*     */   private final CriterionConditionValue e;
/*     */   private final CriterionConditionEnchantments[] f;
/*     */   private final PotionRegistry g;
/*     */   private final CriterionConditionNBT h;
/*     */   
/*     */   public CriterionConditionItem() {
/*  31 */     this.b = null;
/*  32 */     this.c = null;
/*  33 */     this.g = null;
/*  34 */     this.d = CriterionConditionValue.a;
/*  35 */     this.e = CriterionConditionValue.a;
/*  36 */     this.f = new CriterionConditionEnchantments[0];
/*  37 */     this.h = CriterionConditionNBT.a;
/*     */   }
/*     */   
/*     */   public CriterionConditionItem(@Nullable Item paramItem, @Nullable Integer paramInteger, CriterionConditionValue paramCriterionConditionValue1, CriterionConditionValue paramCriterionConditionValue2, CriterionConditionEnchantments[] paramArrayOfCriterionConditionEnchantments, @Nullable PotionRegistry paramPotionRegistry, CriterionConditionNBT paramCriterionConditionNBT) {
/*  41 */     this.b = paramItem;
/*  42 */     this.c = paramInteger;
/*  43 */     this.d = paramCriterionConditionValue1;
/*  44 */     this.e = paramCriterionConditionValue2;
/*  45 */     this.f = paramArrayOfCriterionConditionEnchantments;
/*  46 */     this.g = paramPotionRegistry;
/*  47 */     this.h = paramCriterionConditionNBT;
/*     */   }
/*     */   
/*     */   public boolean a(ItemStack paramItemStack) {
/*  51 */     if (this.b != null && paramItemStack.getItem() != this.b) {
/*  52 */       return false;
/*     */     }
/*  54 */     if (this.c != null && paramItemStack.getData() != this.c.intValue()) {
/*  55 */       return false;
/*     */     }
/*  57 */     if (!this.d.a(paramItemStack.getCount())) {
/*  58 */       return false;
/*     */     }
/*  60 */     if (this.e != CriterionConditionValue.a && !paramItemStack.f()) {
/*  61 */       return false;
/*     */     }
/*  63 */     if (!this.e.a((paramItemStack.k() - paramItemStack.i()))) {
/*  64 */       return false;
/*     */     }
/*  66 */     if (!this.h.a(paramItemStack)) {
/*  67 */       return false;
/*     */     }
/*  69 */     Map<Enchantment, Integer> map = EnchantmentManager.a(paramItemStack);
/*  70 */     for (byte b = 0; b < this.f.length; b++) {
/*  71 */       if (!this.f[b].a(map)) {
/*  72 */         return false;
/*     */       }
/*     */     } 
/*  75 */     PotionRegistry potionRegistry = PotionUtil.d(paramItemStack);
/*  76 */     if (this.g != null && this.g != potionRegistry) {
/*  77 */       return false;
/*     */     }
/*  79 */     return true;
/*     */   }
/*     */   
/*     */   public static CriterionConditionItem a(@Nullable JsonElement paramJsonElement) {
/*  83 */     if (paramJsonElement == null || paramJsonElement.isJsonNull()) {
/*  84 */       return a;
/*     */     }
/*  86 */     JsonObject jsonObject = ChatDeserializer.m(paramJsonElement, "item");
/*  87 */     CriterionConditionValue criterionConditionValue1 = CriterionConditionValue.a(jsonObject.get("count"));
/*  88 */     CriterionConditionValue criterionConditionValue2 = CriterionConditionValue.a(jsonObject.get("durability"));
/*  89 */     Integer integer = jsonObject.has("data") ? Integer.valueOf(ChatDeserializer.n(jsonObject, "data")) : null;
/*  90 */     CriterionConditionNBT criterionConditionNBT = CriterionConditionNBT.a(jsonObject.get("nbt"));
/*  91 */     Item item = null;
/*  92 */     if (jsonObject.has("item")) {
/*  93 */       MinecraftKey minecraftKey = new MinecraftKey(ChatDeserializer.h(jsonObject, "item"));
/*  94 */       item = Item.REGISTRY.get(minecraftKey);
/*  95 */       if (item == null) {
/*  96 */         throw new JsonSyntaxException("Unknown item id '" + minecraftKey + "'");
/*     */       }
/*     */     } 
/*  99 */     CriterionConditionEnchantments[] arrayOfCriterionConditionEnchantments = CriterionConditionEnchantments.b(jsonObject.get("enchantments"));
/* 100 */     PotionRegistry potionRegistry = null;
/* 101 */     if (jsonObject.has("potion")) {
/* 102 */       MinecraftKey minecraftKey = new MinecraftKey(ChatDeserializer.h(jsonObject, "potion"));
/* 103 */       if (!PotionRegistry.a.d(minecraftKey)) {
/* 104 */         throw new JsonSyntaxException("Unknown potion '" + minecraftKey + "'");
/*     */       }
/* 106 */       potionRegistry = PotionRegistry.a.get(minecraftKey);
/*     */     } 
/*     */     
/* 109 */     return new CriterionConditionItem(item, integer, criterionConditionValue1, criterionConditionValue2, arrayOfCriterionConditionEnchantments, potionRegistry, criterionConditionNBT);
/*     */   }
/*     */   
/*     */   public static CriterionConditionItem[] b(@Nullable JsonElement paramJsonElement) {
/* 113 */     if (paramJsonElement == null || paramJsonElement.isJsonNull()) {
/* 114 */       return new CriterionConditionItem[0];
/*     */     }
/*     */     
/* 117 */     JsonArray jsonArray = ChatDeserializer.n(paramJsonElement, "items");
/* 118 */     CriterionConditionItem[] arrayOfCriterionConditionItem = new CriterionConditionItem[jsonArray.size()];
/*     */     
/* 120 */     for (byte b = 0; b < arrayOfCriterionConditionItem.length; b++) {
/* 121 */       arrayOfCriterionConditionItem[b] = a(jsonArray.get(b));
/*     */     }
/*     */     
/* 124 */     return arrayOfCriterionConditionItem;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CriterionConditionItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */