/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ public class CriterionConditionValue
/*    */ {
/* 10 */   public static final CriterionConditionValue a = new CriterionConditionValue(null, null);
/*    */   
/*    */   private final Float b;
/*    */   private final Float c;
/*    */   
/*    */   public CriterionConditionValue(@Nullable Float paramFloat1, @Nullable Float paramFloat2) {
/* 16 */     this.b = paramFloat1;
/* 17 */     this.c = paramFloat2;
/*    */   }
/*    */   
/*    */   public boolean a(float paramFloat) {
/* 21 */     if (this.b != null && this.b.floatValue() > paramFloat) {
/* 22 */       return false;
/*    */     }
/* 24 */     if (this.c != null && this.c.floatValue() < paramFloat) {
/* 25 */       return false;
/*    */     }
/* 27 */     return true;
/*    */   }
/*    */   
/*    */   public boolean a(double paramDouble) {
/* 31 */     if (this.b != null && (this.b.floatValue() * this.b.floatValue()) > paramDouble) {
/* 32 */       return false;
/*    */     }
/* 34 */     if (this.c != null && (this.c.floatValue() * this.c.floatValue()) < paramDouble) {
/* 35 */       return false;
/*    */     }
/* 37 */     return true;
/*    */   }
/*    */   
/*    */   public static CriterionConditionValue a(@Nullable JsonElement paramJsonElement) {
/* 41 */     if (paramJsonElement == null || paramJsonElement.isJsonNull()) {
/* 42 */       return a;
/*    */     }
/*    */     
/* 45 */     if (ChatDeserializer.b(paramJsonElement)) {
/* 46 */       float f = ChatDeserializer.e(paramJsonElement, "value");
/* 47 */       return new CriterionConditionValue(Float.valueOf(f), Float.valueOf(f));
/*    */     } 
/* 49 */     JsonObject jsonObject = ChatDeserializer.m(paramJsonElement, "value");
/* 50 */     Float float_1 = jsonObject.has("min") ? Float.valueOf(ChatDeserializer.l(jsonObject, "min")) : null;
/* 51 */     Float float_2 = jsonObject.has("max") ? Float.valueOf(ChatDeserializer.l(jsonObject, "max")) : null;
/* 52 */     return new CriterionConditionValue(float_1, float_2);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CriterionConditionValue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */