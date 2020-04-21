/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.Sets;
/*     */ import com.google.gson.JsonDeserializationContext;
/*     */ import com.google.gson.JsonObject;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CriterionTriggerPlayerHurtEntity
/*     */   implements CriterionTrigger<CriterionTriggerPlayerHurtEntity.b>
/*     */ {
/*  20 */   private static final MinecraftKey a = new MinecraftKey("player_hurt_entity");
/*  21 */   private final Map<AdvancementDataPlayer, a> b = Maps.newHashMap();
/*     */ 
/*     */   
/*     */   public MinecraftKey a() {
/*  25 */     return a;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(AdvancementDataPlayer paramAdvancementDataPlayer, CriterionTrigger.a<b> parama) {
/*  30 */     a a1 = this.b.get(paramAdvancementDataPlayer);
/*  31 */     if (a1 == null) {
/*  32 */       a1 = new a(paramAdvancementDataPlayer);
/*  33 */       this.b.put(paramAdvancementDataPlayer, a1);
/*     */     } 
/*  35 */     a1.a(parama);
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(AdvancementDataPlayer paramAdvancementDataPlayer, CriterionTrigger.a<b> parama) {
/*  40 */     a a1 = this.b.get(paramAdvancementDataPlayer);
/*  41 */     if (a1 != null) {
/*  42 */       a1.b(parama);
/*  43 */       if (a1.a()) {
/*  44 */         this.b.remove(paramAdvancementDataPlayer);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(AdvancementDataPlayer paramAdvancementDataPlayer) {
/*  51 */     this.b.remove(paramAdvancementDataPlayer);
/*     */   }
/*     */ 
/*     */   
/*     */   public b b(JsonObject paramJsonObject, JsonDeserializationContext paramJsonDeserializationContext) {
/*  56 */     CriterionConditionDamage criterionConditionDamage = CriterionConditionDamage.a(paramJsonObject.get("damage"));
/*  57 */     CriterionConditionEntity criterionConditionEntity = CriterionConditionEntity.a(paramJsonObject.get("entity"));
/*  58 */     return new b(criterionConditionDamage, criterionConditionEntity);
/*     */   }
/*     */   
/*     */   public void a(EntityPlayer paramEntityPlayer, Entity paramEntity, DamageSource paramDamageSource, float paramFloat1, float paramFloat2, boolean paramBoolean) {
/*  62 */     a a = this.b.get(paramEntityPlayer.getAdvancementData());
/*  63 */     if (a != null)
/*  64 */       a.a(paramEntityPlayer, paramEntity, paramDamageSource, paramFloat1, paramFloat2, paramBoolean); 
/*     */   }
/*     */   
/*     */   public static class b
/*     */     extends CriterionInstanceAbstract {
/*     */     private final CriterionConditionDamage a;
/*     */     private final CriterionConditionEntity b;
/*     */     
/*     */     public b(CriterionConditionDamage param1CriterionConditionDamage, CriterionConditionEntity param1CriterionConditionEntity) {
/*  73 */       super(CriterionTriggerPlayerHurtEntity.b());
/*  74 */       this.a = param1CriterionConditionDamage;
/*  75 */       this.b = param1CriterionConditionEntity;
/*     */     }
/*     */     
/*     */     public boolean a(EntityPlayer param1EntityPlayer, Entity param1Entity, DamageSource param1DamageSource, float param1Float1, float param1Float2, boolean param1Boolean) {
/*  79 */       if (!this.a.a(param1EntityPlayer, param1DamageSource, param1Float1, param1Float2, param1Boolean)) {
/*  80 */         return false;
/*     */       }
/*  82 */       if (!this.b.a(param1EntityPlayer, param1Entity)) {
/*  83 */         return false;
/*     */       }
/*  85 */       return true;
/*     */     }
/*     */   }
/*     */   
/*     */   static class a {
/*     */     private final AdvancementDataPlayer a;
/*  91 */     private final Set<CriterionTrigger.a<CriterionTriggerPlayerHurtEntity.b>> b = Sets.newHashSet();
/*     */     
/*     */     public a(AdvancementDataPlayer param1AdvancementDataPlayer) {
/*  94 */       this.a = param1AdvancementDataPlayer;
/*     */     }
/*     */     
/*     */     public boolean a() {
/*  98 */       return this.b.isEmpty();
/*     */     }
/*     */     
/*     */     public void a(CriterionTrigger.a<CriterionTriggerPlayerHurtEntity.b> param1a) {
/* 102 */       this.b.add(param1a);
/*     */     }
/*     */     
/*     */     public void b(CriterionTrigger.a<CriterionTriggerPlayerHurtEntity.b> param1a) {
/* 106 */       this.b.remove(param1a);
/*     */     }
/*     */     
/*     */     public void a(EntityPlayer param1EntityPlayer, Entity param1Entity, DamageSource param1DamageSource, float param1Float1, float param1Float2, boolean param1Boolean) {
/* 110 */       ArrayList<CriterionTrigger.a<CriterionTriggerPlayerHurtEntity.b>> arrayList = null;
/* 111 */       for (CriterionTrigger.a<CriterionTriggerPlayerHurtEntity.b> a1 : this.b) {
/* 112 */         if (((CriterionTriggerPlayerHurtEntity.b)a1.a()).a(param1EntityPlayer, param1Entity, param1DamageSource, param1Float1, param1Float2, param1Boolean)) {
/* 113 */           if (arrayList == null) {
/* 114 */             arrayList = Lists.newArrayList();
/*     */           }
/* 116 */           arrayList.add(a1);
/*     */         } 
/*     */       } 
/* 119 */       if (arrayList != null)
/* 120 */         for (CriterionTrigger.a<CriterionTriggerPlayerHurtEntity.b> a1 : arrayList)
/* 121 */           a1.a(this.a);  
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CriterionTriggerPlayerHurtEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */