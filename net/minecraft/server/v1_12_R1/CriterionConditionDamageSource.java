/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CriterionConditionDamageSource
/*     */ {
/*  12 */   public static CriterionConditionDamageSource a = new CriterionConditionDamageSource();
/*     */   
/*     */   private final Boolean b;
/*     */   private final Boolean c;
/*     */   private final Boolean d;
/*     */   private final Boolean e;
/*     */   private final Boolean f;
/*     */   private final Boolean g;
/*     */   private final Boolean h;
/*     */   private final CriterionConditionEntity i;
/*     */   private final CriterionConditionEntity j;
/*     */   
/*     */   public CriterionConditionDamageSource() {
/*  25 */     this.b = null;
/*  26 */     this.c = null;
/*  27 */     this.d = null;
/*  28 */     this.e = null;
/*  29 */     this.f = null;
/*  30 */     this.g = null;
/*  31 */     this.h = null;
/*  32 */     this.i = CriterionConditionEntity.a;
/*  33 */     this.j = CriterionConditionEntity.a;
/*     */   }
/*     */   
/*     */   public CriterionConditionDamageSource(@Nullable Boolean paramBoolean1, @Nullable Boolean paramBoolean2, @Nullable Boolean paramBoolean3, @Nullable Boolean paramBoolean4, @Nullable Boolean paramBoolean5, @Nullable Boolean paramBoolean6, @Nullable Boolean paramBoolean7, CriterionConditionEntity paramCriterionConditionEntity1, CriterionConditionEntity paramCriterionConditionEntity2) {
/*  37 */     this.b = paramBoolean1;
/*  38 */     this.c = paramBoolean2;
/*  39 */     this.d = paramBoolean3;
/*  40 */     this.e = paramBoolean4;
/*  41 */     this.f = paramBoolean5;
/*  42 */     this.g = paramBoolean6;
/*  43 */     this.h = paramBoolean7;
/*  44 */     this.i = paramCriterionConditionEntity1;
/*  45 */     this.j = paramCriterionConditionEntity2;
/*     */   }
/*     */   
/*     */   public boolean a(EntityPlayer paramEntityPlayer, DamageSource paramDamageSource) {
/*  49 */     if (this == a) {
/*  50 */       return true;
/*     */     }
/*  52 */     if (this.b != null && this.b.booleanValue() != paramDamageSource.a()) {
/*  53 */       return false;
/*     */     }
/*  55 */     if (this.c != null && this.c.booleanValue() != paramDamageSource.isExplosion()) {
/*  56 */       return false;
/*     */     }
/*  58 */     if (this.d != null && this.d.booleanValue() != paramDamageSource.ignoresArmor()) {
/*  59 */       return false;
/*     */     }
/*  61 */     if (this.e != null && this.e.booleanValue() != paramDamageSource.ignoresInvulnerability()) {
/*  62 */       return false;
/*     */     }
/*  64 */     if (this.f != null && this.f.booleanValue() != paramDamageSource.isStarvation()) {
/*  65 */       return false;
/*     */     }
/*  67 */     if (this.g != null && this.g.booleanValue() != paramDamageSource.o()) {
/*  68 */       return false;
/*     */     }
/*  70 */     if (this.h != null && this.h.booleanValue() != paramDamageSource.isMagic()) {
/*  71 */       return false;
/*     */     }
/*  73 */     if (!this.i.a(paramEntityPlayer, paramDamageSource.i())) {
/*  74 */       return false;
/*     */     }
/*  76 */     if (!this.j.a(paramEntityPlayer, paramDamageSource.getEntity())) {
/*  77 */       return false;
/*     */     }
/*  79 */     return true;
/*     */   }
/*     */   
/*     */   public static CriterionConditionDamageSource a(@Nullable JsonElement paramJsonElement) {
/*  83 */     if (paramJsonElement == null || paramJsonElement.isJsonNull()) {
/*  84 */       return a;
/*     */     }
/*  86 */     JsonObject jsonObject = ChatDeserializer.m(paramJsonElement, "damage type");
/*  87 */     Boolean bool1 = a(jsonObject, "is_projectile");
/*  88 */     Boolean bool2 = a(jsonObject, "is_explosion");
/*  89 */     Boolean bool3 = a(jsonObject, "bypasses_armor");
/*  90 */     Boolean bool4 = a(jsonObject, "bypasses_invulnerability");
/*  91 */     Boolean bool5 = a(jsonObject, "bypasses_magic");
/*  92 */     Boolean bool6 = a(jsonObject, "is_fire");
/*  93 */     Boolean bool7 = a(jsonObject, "is_magic");
/*  94 */     CriterionConditionEntity criterionConditionEntity1 = CriterionConditionEntity.a(jsonObject.get("direct_entity"));
/*  95 */     CriterionConditionEntity criterionConditionEntity2 = CriterionConditionEntity.a(jsonObject.get("source_entity"));
/*  96 */     return new CriterionConditionDamageSource(bool1, bool2, bool3, bool4, bool5, bool6, bool7, criterionConditionEntity1, criterionConditionEntity2);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private static Boolean a(JsonObject paramJsonObject, String paramString) {
/* 101 */     return paramJsonObject.has(paramString) ? Boolean.valueOf(ChatDeserializer.j(paramJsonObject, paramString)) : null;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CriterionConditionDamageSource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */