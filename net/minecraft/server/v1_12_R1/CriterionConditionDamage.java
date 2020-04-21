/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CriterionConditionDamage
/*    */ {
/* 12 */   public static CriterionConditionDamage a = new CriterionConditionDamage();
/*    */   
/*    */   private final CriterionConditionValue b;
/*    */   private final CriterionConditionValue c;
/*    */   private final CriterionConditionEntity d;
/*    */   private final Boolean e;
/*    */   private final CriterionConditionDamageSource f;
/*    */   
/*    */   public CriterionConditionDamage() {
/* 21 */     this.b = CriterionConditionValue.a;
/* 22 */     this.c = CriterionConditionValue.a;
/* 23 */     this.d = CriterionConditionEntity.a;
/* 24 */     this.e = null;
/* 25 */     this.f = CriterionConditionDamageSource.a;
/*    */   }
/*    */   
/*    */   public CriterionConditionDamage(CriterionConditionValue paramCriterionConditionValue1, CriterionConditionValue paramCriterionConditionValue2, CriterionConditionEntity paramCriterionConditionEntity, @Nullable Boolean paramBoolean, CriterionConditionDamageSource paramCriterionConditionDamageSource) {
/* 29 */     this.b = paramCriterionConditionValue1;
/* 30 */     this.c = paramCriterionConditionValue2;
/* 31 */     this.d = paramCriterionConditionEntity;
/* 32 */     this.e = paramBoolean;
/* 33 */     this.f = paramCriterionConditionDamageSource;
/*    */   }
/*    */   
/*    */   public boolean a(EntityPlayer paramEntityPlayer, DamageSource paramDamageSource, float paramFloat1, float paramFloat2, boolean paramBoolean) {
/* 37 */     if (this == a) {
/* 38 */       return true;
/*    */     }
/* 40 */     if (!this.b.a(paramFloat1)) {
/* 41 */       return false;
/*    */     }
/* 43 */     if (!this.c.a(paramFloat2)) {
/* 44 */       return false;
/*    */     }
/* 46 */     if (!this.d.a(paramEntityPlayer, paramDamageSource.getEntity())) {
/* 47 */       return false;
/*    */     }
/* 49 */     if (this.e != null && this.e.booleanValue() != paramBoolean) {
/* 50 */       return false;
/*    */     }
/* 52 */     if (!this.f.a(paramEntityPlayer, paramDamageSource)) {
/* 53 */       return false;
/*    */     }
/* 55 */     return true;
/*    */   }
/*    */   
/*    */   public static CriterionConditionDamage a(@Nullable JsonElement paramJsonElement) {
/* 59 */     if (paramJsonElement == null || paramJsonElement.isJsonNull()) {
/* 60 */       return a;
/*    */     }
/* 62 */     JsonObject jsonObject = ChatDeserializer.m(paramJsonElement, "damage");
/* 63 */     CriterionConditionValue criterionConditionValue1 = CriterionConditionValue.a(jsonObject.get("dealt"));
/* 64 */     CriterionConditionValue criterionConditionValue2 = CriterionConditionValue.a(jsonObject.get("taken"));
/* 65 */     Boolean bool = jsonObject.has("blocked") ? Boolean.valueOf(ChatDeserializer.j(jsonObject, "blocked")) : null;
/* 66 */     CriterionConditionEntity criterionConditionEntity = CriterionConditionEntity.a(jsonObject.get("source_entity"));
/* 67 */     CriterionConditionDamageSource criterionConditionDamageSource = CriterionConditionDamageSource.a(jsonObject.get("type"));
/* 68 */     return new CriterionConditionDamage(criterionConditionValue1, criterionConditionValue2, criterionConditionEntity, bool, criterionConditionDamageSource);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CriterionConditionDamage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */