/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonDeserializationContext;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonPrimitive;
/*     */ import com.google.gson.JsonSerializationContext;
/*     */ import com.google.gson.JsonSyntaxException;
/*     */ import java.util.Random;
/*     */ import java.util.UUID;
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
/*     */ public class LootItemFunctionSetAttribute
/*     */   extends LootItemFunction
/*     */ {
/*  26 */   private static final Logger a = LogManager.getLogger();
/*     */   private final a[] b;
/*     */   
/*     */   public LootItemFunctionSetAttribute(LootItemCondition[] paramArrayOfLootItemCondition, a[] paramArrayOfa) {
/*  30 */     super(paramArrayOfLootItemCondition);
/*  31 */     this.b = paramArrayOfa;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack a(ItemStack paramItemStack, Random paramRandom, LootTableInfo paramLootTableInfo) {
/*  36 */     for (a a1 : this.b) {
/*  37 */       UUID uUID = a.a(a1);
/*  38 */       if (uUID == null) {
/*  39 */         uUID = UUID.randomUUID();
/*     */       }
/*  41 */       EnumItemSlot enumItemSlot = a.b(a1)[paramRandom.nextInt((a.b(a1)).length)];
/*  42 */       paramItemStack.a(a.c(a1), new AttributeModifier(uUID, a.d(a1), a.e(a1).b(paramRandom), a.f(a1)), enumItemSlot);
/*     */     } 
/*  44 */     return paramItemStack;
/*     */   }
/*     */   
/*     */   public static class b extends LootItemFunction.a<LootItemFunctionSetAttribute> {
/*     */     public b() {
/*  49 */       super(new MinecraftKey("set_attributes"), LootItemFunctionSetAttribute.class);
/*     */     }
/*     */ 
/*     */     
/*     */     public void a(JsonObject param1JsonObject, LootItemFunctionSetAttribute param1LootItemFunctionSetAttribute, JsonSerializationContext param1JsonSerializationContext) {
/*  54 */       JsonArray jsonArray = new JsonArray();
/*  55 */       for (LootItemFunctionSetAttribute.a a1 : LootItemFunctionSetAttribute.a(param1LootItemFunctionSetAttribute)) {
/*  56 */         jsonArray.add((JsonElement)a1.a(param1JsonSerializationContext));
/*     */       }
/*  58 */       param1JsonObject.add("modifiers", (JsonElement)jsonArray);
/*     */     }
/*     */ 
/*     */     
/*     */     public LootItemFunctionSetAttribute a(JsonObject param1JsonObject, JsonDeserializationContext param1JsonDeserializationContext, LootItemCondition[] param1ArrayOfLootItemCondition) {
/*  63 */       JsonArray jsonArray = ChatDeserializer.u(param1JsonObject, "modifiers");
/*  64 */       LootItemFunctionSetAttribute.a[] arrayOfA = new LootItemFunctionSetAttribute.a[jsonArray.size()];
/*  65 */       byte b1 = 0;
/*  66 */       for (JsonElement jsonElement : jsonArray) {
/*  67 */         arrayOfA[b1++] = LootItemFunctionSetAttribute.a.a(ChatDeserializer.m(jsonElement, "modifier"), param1JsonDeserializationContext);
/*     */       }
/*     */       
/*  70 */       if (arrayOfA.length == 0) {
/*  71 */         throw new JsonSyntaxException("Invalid attribute modifiers array; cannot be empty");
/*     */       }
/*  73 */       return new LootItemFunctionSetAttribute(param1ArrayOfLootItemCondition, arrayOfA);
/*     */     } }
/*     */   
/*     */   static class a {
/*     */     private final String a;
/*     */     private final String b;
/*     */     private final int c;
/*     */     private final LootValueBounds d;
/*     */     @Nullable
/*     */     private final UUID e;
/*     */     private final EnumItemSlot[] f;
/*     */     
/*     */     private a(String param1String1, String param1String2, int param1Int, LootValueBounds param1LootValueBounds, EnumItemSlot[] param1ArrayOfEnumItemSlot, @Nullable UUID param1UUID) {
/*  86 */       this.a = param1String1;
/*  87 */       this.b = param1String2;
/*  88 */       this.c = param1Int;
/*  89 */       this.d = param1LootValueBounds;
/*  90 */       this.e = param1UUID;
/*  91 */       this.f = param1ArrayOfEnumItemSlot;
/*     */     }
/*     */     
/*     */     public JsonObject a(JsonSerializationContext param1JsonSerializationContext) {
/*  95 */       JsonObject jsonObject = new JsonObject();
/*  96 */       jsonObject.addProperty("name", this.a);
/*  97 */       jsonObject.addProperty("attribute", this.b);
/*  98 */       jsonObject.addProperty("operation", a(this.c));
/*  99 */       jsonObject.add("amount", param1JsonSerializationContext.serialize(this.d));
/* 100 */       if (this.e != null) {
/* 101 */         jsonObject.addProperty("id", this.e.toString());
/*     */       }
/* 103 */       if (this.f.length == 1) {
/* 104 */         jsonObject.addProperty("slot", this.f[0].d());
/*     */       } else {
/* 106 */         JsonArray jsonArray = new JsonArray();
/* 107 */         for (EnumItemSlot enumItemSlot : this.f) {
/* 108 */           jsonArray.add((JsonElement)new JsonPrimitive(enumItemSlot.d()));
/*     */         }
/* 110 */         jsonObject.add("slot", (JsonElement)jsonArray);
/*     */       } 
/* 112 */       return jsonObject;
/*     */     }
/*     */     public static a a(JsonObject param1JsonObject, JsonDeserializationContext param1JsonDeserializationContext) {
/*     */       EnumItemSlot[] arrayOfEnumItemSlot;
/* 116 */       String str1 = ChatDeserializer.h(param1JsonObject, "name");
/* 117 */       String str2 = ChatDeserializer.h(param1JsonObject, "attribute");
/* 118 */       int i = a(ChatDeserializer.h(param1JsonObject, "operation"));
/* 119 */       LootValueBounds lootValueBounds = ChatDeserializer.<LootValueBounds>a(param1JsonObject, "amount", param1JsonDeserializationContext, LootValueBounds.class);
/*     */       
/* 121 */       UUID uUID = null;
/*     */       
/* 123 */       if (ChatDeserializer.a(param1JsonObject, "slot")) {
/* 124 */         arrayOfEnumItemSlot = new EnumItemSlot[] { EnumItemSlot.a(ChatDeserializer.h(param1JsonObject, "slot")) };
/* 125 */       } else if (ChatDeserializer.d(param1JsonObject, "slot")) {
/* 126 */         JsonArray jsonArray = ChatDeserializer.u(param1JsonObject, "slot");
/* 127 */         arrayOfEnumItemSlot = new EnumItemSlot[jsonArray.size()];
/* 128 */         byte b = 0;
/* 129 */         for (JsonElement jsonElement : jsonArray) {
/* 130 */           arrayOfEnumItemSlot[b++] = EnumItemSlot.a(ChatDeserializer.a(jsonElement, "slot"));
/*     */         }
/* 132 */         if (arrayOfEnumItemSlot.length == 0) {
/* 133 */           throw new JsonSyntaxException("Invalid attribute modifier slot; must contain at least one entry.");
/*     */         }
/*     */       } else {
/* 136 */         throw new JsonSyntaxException("Invalid or missing attribute modifier slot; must be either string or array of strings.");
/*     */       } 
/*     */       
/* 139 */       if (param1JsonObject.has("id")) {
/* 140 */         String str = ChatDeserializer.h(param1JsonObject, "id");
/*     */         try {
/* 142 */           uUID = UUID.fromString(str);
/* 143 */         } catch (IllegalArgumentException illegalArgumentException) {
/* 144 */           throw new JsonSyntaxException("Invalid attribute modifier id '" + str + "' (must be UUID format, with dashes)");
/*     */         } 
/*     */       } 
/*     */       
/* 148 */       return new a(str1, str2, i, lootValueBounds, arrayOfEnumItemSlot, uUID);
/*     */     }
/*     */     
/*     */     private static String a(int param1Int) {
/* 152 */       switch (param1Int) {
/*     */         case 0:
/* 154 */           return "addition";
/*     */         case 1:
/* 156 */           return "multiply_base";
/*     */         case 2:
/* 158 */           return "multiply_total";
/*     */       } 
/* 160 */       throw new IllegalArgumentException("Unknown operation " + param1Int);
/*     */     }
/*     */     
/*     */     private static int a(String param1String) {
/* 164 */       if ("addition".equals(param1String))
/* 165 */         return 0; 
/* 166 */       if ("multiply_base".equals(param1String))
/* 167 */         return 1; 
/* 168 */       if ("multiply_total".equals(param1String)) {
/* 169 */         return 2;
/*     */       }
/* 171 */       throw new JsonSyntaxException("Unknown attribute modifier operation " + param1String);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\LootItemFunctionSetAttribute.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */