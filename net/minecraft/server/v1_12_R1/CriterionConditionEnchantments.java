/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.gson.JsonArray;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonSyntaxException;
/*    */ import java.util.Map;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CriterionConditionEnchantments
/*    */ {
/* 15 */   public static final CriterionConditionEnchantments a = new CriterionConditionEnchantments();
/*    */   
/*    */   private final Enchantment b;
/*    */   private final CriterionConditionValue c;
/*    */   
/*    */   public CriterionConditionEnchantments() {
/* 21 */     this.b = null;
/* 22 */     this.c = CriterionConditionValue.a;
/*    */   }
/*    */   
/*    */   public CriterionConditionEnchantments(@Nullable Enchantment paramEnchantment, CriterionConditionValue paramCriterionConditionValue) {
/* 26 */     this.b = paramEnchantment;
/* 27 */     this.c = paramCriterionConditionValue;
/*    */   }
/*    */   
/*    */   public boolean a(Map<Enchantment, Integer> paramMap) {
/* 31 */     if (this.b != null) {
/*    */       
/* 33 */       if (!paramMap.containsKey(this.b)) {
/* 34 */         return false;
/*    */       }
/* 36 */       int i = ((Integer)paramMap.get(this.b)).intValue();
/* 37 */       if (this.c != null && !this.c.a(i)) {
/* 38 */         return false;
/*    */       }
/* 40 */     } else if (this.c != null) {
/*    */       
/* 42 */       for (Integer integer : paramMap.values()) {
/* 43 */         if (this.c.a(integer.intValue())) {
/* 44 */           return true;
/*    */         }
/*    */       } 
/* 47 */       return false;
/*    */     } 
/*    */     
/* 50 */     return true;
/*    */   }
/*    */   
/*    */   public static CriterionConditionEnchantments a(@Nullable JsonElement paramJsonElement) {
/* 54 */     if (paramJsonElement == null || paramJsonElement.isJsonNull()) {
/* 55 */       return a;
/*    */     }
/* 57 */     JsonObject jsonObject = ChatDeserializer.m(paramJsonElement, "enchantment");
/*    */     
/* 59 */     Enchantment enchantment = null;
/* 60 */     if (jsonObject.has("enchantment")) {
/* 61 */       MinecraftKey minecraftKey = new MinecraftKey(ChatDeserializer.h(jsonObject, "enchantment"));
/* 62 */       enchantment = Enchantment.enchantments.get(minecraftKey);
/* 63 */       if (enchantment == null) {
/* 64 */         throw new JsonSyntaxException("Unknown enchantment '" + minecraftKey + "'");
/*    */       }
/*    */     } 
/* 67 */     CriterionConditionValue criterionConditionValue = CriterionConditionValue.a(jsonObject.get("levels"));
/*    */     
/* 69 */     return new CriterionConditionEnchantments(enchantment, criterionConditionValue);
/*    */   }
/*    */   
/*    */   public static CriterionConditionEnchantments[] b(@Nullable JsonElement paramJsonElement) {
/* 73 */     if (paramJsonElement == null || paramJsonElement.isJsonNull()) {
/* 74 */       return new CriterionConditionEnchantments[0];
/*    */     }
/*    */     
/* 77 */     JsonArray jsonArray = ChatDeserializer.n(paramJsonElement, "enchantments");
/* 78 */     CriterionConditionEnchantments[] arrayOfCriterionConditionEnchantments = new CriterionConditionEnchantments[jsonArray.size()];
/* 79 */     for (byte b = 0; b < arrayOfCriterionConditionEnchantments.length; b++) {
/* 80 */       arrayOfCriterionConditionEnchantments[b] = a(jsonArray.get(b));
/*    */     }
/*    */     
/* 83 */     return arrayOfCriterionConditionEnchantments;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CriterionConditionEnchantments.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */