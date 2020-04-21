/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonSyntaxException;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CriterionConditionMobEffect
/*     */ {
/*  19 */   public static final CriterionConditionMobEffect a = new CriterionConditionMobEffect(Collections.emptyMap());
/*     */   
/*     */   private final Map<MobEffectList, a> b;
/*     */   
/*     */   public CriterionConditionMobEffect(Map<MobEffectList, a> paramMap) {
/*  24 */     this.b = paramMap;
/*     */   }
/*     */   
/*     */   public boolean a(Entity paramEntity) {
/*  28 */     if (this == a) {
/*  29 */       return true;
/*     */     }
/*  31 */     if (paramEntity instanceof EntityLiving) {
/*  32 */       return a(((EntityLiving)paramEntity).cb());
/*     */     }
/*  34 */     return false;
/*     */   }
/*     */   
/*     */   public boolean a(EntityLiving paramEntityLiving) {
/*  38 */     if (this == a) {
/*  39 */       return true;
/*     */     }
/*  41 */     return a(paramEntityLiving.cb());
/*     */   }
/*     */   
/*     */   public boolean a(Map<MobEffectList, MobEffect> paramMap) {
/*  45 */     if (this == a) {
/*  46 */       return true;
/*     */     }
/*     */     
/*  49 */     for (Map.Entry<MobEffectList, a> entry : this.b.entrySet()) {
/*  50 */       MobEffect mobEffect = paramMap.get(entry.getKey());
/*  51 */       if (!((a)entry.getValue()).a(mobEffect)) {
/*  52 */         return false;
/*     */       }
/*     */     } 
/*     */     
/*  56 */     return true;
/*     */   }
/*     */   
/*     */   public static CriterionConditionMobEffect a(@Nullable JsonElement paramJsonElement) {
/*  60 */     if (paramJsonElement == null || paramJsonElement.isJsonNull()) {
/*  61 */       return a;
/*     */     }
/*  63 */     JsonObject jsonObject = ChatDeserializer.m(paramJsonElement, "effects");
/*  64 */     HashMap<MobEffectList, a> hashMap = Maps.newHashMap();
/*     */     
/*  66 */     for (Map.Entry entry : jsonObject.entrySet()) {
/*  67 */       MinecraftKey minecraftKey = new MinecraftKey((String)entry.getKey());
/*  68 */       MobEffectList mobEffectList = MobEffectList.REGISTRY.get(minecraftKey);
/*  69 */       if (mobEffectList == null) {
/*  70 */         throw new JsonSyntaxException("Unknown effect '" + minecraftKey + "'");
/*     */       }
/*  72 */       a a = a.a(ChatDeserializer.m((JsonElement)entry.getValue(), (String)entry.getKey()));
/*  73 */       hashMap.put(mobEffectList, a);
/*     */     } 
/*     */     
/*  76 */     return new CriterionConditionMobEffect(hashMap);
/*     */   }
/*     */ 
/*     */   
/*     */   public static class a
/*     */   {
/*     */     private final CriterionConditionValue a;
/*     */     private final CriterionConditionValue b;
/*     */     
/*     */     public a(CriterionConditionValue param1CriterionConditionValue1, CriterionConditionValue param1CriterionConditionValue2, @Nullable Boolean param1Boolean1, @Nullable Boolean param1Boolean2) {
/*  86 */       this.a = param1CriterionConditionValue1;
/*  87 */       this.b = param1CriterionConditionValue2;
/*  88 */       this.c = param1Boolean1;
/*  89 */       this.d = param1Boolean2;
/*     */     } @Nullable
/*     */     private final Boolean c; @Nullable
/*     */     private final Boolean d; public boolean a(@Nullable MobEffect param1MobEffect) {
/*  93 */       if (param1MobEffect == null) {
/*  94 */         return false;
/*     */       }
/*  96 */       if (!this.a.a(param1MobEffect.getAmplifier())) {
/*  97 */         return false;
/*     */       }
/*  99 */       if (!this.b.a(param1MobEffect.getDuration())) {
/* 100 */         return false;
/*     */       }
/* 102 */       if (this.c != null && this.c.booleanValue() != param1MobEffect.isAmbient()) {
/* 103 */         return false;
/*     */       }
/* 105 */       if (this.d != null && this.d.booleanValue() != param1MobEffect.isShowParticles()) {
/* 106 */         return false;
/*     */       }
/* 108 */       return true;
/*     */     }
/*     */     
/*     */     public static a a(JsonObject param1JsonObject) {
/* 112 */       CriterionConditionValue criterionConditionValue1 = CriterionConditionValue.a(param1JsonObject.get("amplifier"));
/* 113 */       CriterionConditionValue criterionConditionValue2 = CriterionConditionValue.a(param1JsonObject.get("duration"));
/* 114 */       Boolean bool1 = param1JsonObject.has("ambient") ? Boolean.valueOf(ChatDeserializer.j(param1JsonObject, "ambient")) : null;
/* 115 */       Boolean bool2 = param1JsonObject.has("visible") ? Boolean.valueOf(ChatDeserializer.j(param1JsonObject, "visible")) : null;
/* 116 */       return new a(criterionConditionValue1, criterionConditionValue2, bool1, bool2);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CriterionConditionMobEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */