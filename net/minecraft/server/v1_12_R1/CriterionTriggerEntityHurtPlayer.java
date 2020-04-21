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
/*     */ public class CriterionTriggerEntityHurtPlayer
/*     */   implements CriterionTrigger<CriterionTriggerEntityHurtPlayer.b>
/*     */ {
/*  19 */   private static final MinecraftKey a = new MinecraftKey("entity_hurt_player");
/*  20 */   private final Map<AdvancementDataPlayer, a> b = Maps.newHashMap();
/*     */ 
/*     */   
/*     */   public MinecraftKey a() {
/*  24 */     return a;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(AdvancementDataPlayer paramAdvancementDataPlayer, CriterionTrigger.a<b> parama) {
/*  29 */     a a1 = this.b.get(paramAdvancementDataPlayer);
/*  30 */     if (a1 == null) {
/*  31 */       a1 = new a(paramAdvancementDataPlayer);
/*  32 */       this.b.put(paramAdvancementDataPlayer, a1);
/*     */     } 
/*  34 */     a1.a(parama);
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(AdvancementDataPlayer paramAdvancementDataPlayer, CriterionTrigger.a<b> parama) {
/*  39 */     a a1 = this.b.get(paramAdvancementDataPlayer);
/*  40 */     if (a1 != null) {
/*  41 */       a1.b(parama);
/*  42 */       if (a1.a()) {
/*  43 */         this.b.remove(paramAdvancementDataPlayer);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(AdvancementDataPlayer paramAdvancementDataPlayer) {
/*  50 */     this.b.remove(paramAdvancementDataPlayer);
/*     */   }
/*     */ 
/*     */   
/*     */   public b b(JsonObject paramJsonObject, JsonDeserializationContext paramJsonDeserializationContext) {
/*  55 */     CriterionConditionDamage criterionConditionDamage = CriterionConditionDamage.a(paramJsonObject.get("damage"));
/*  56 */     return new b(criterionConditionDamage);
/*     */   }
/*     */   
/*     */   public void a(EntityPlayer paramEntityPlayer, DamageSource paramDamageSource, float paramFloat1, float paramFloat2, boolean paramBoolean) {
/*  60 */     a a = this.b.get(paramEntityPlayer.getAdvancementData());
/*  61 */     if (a != null)
/*  62 */       a.a(paramEntityPlayer, paramDamageSource, paramFloat1, paramFloat2, paramBoolean); 
/*     */   }
/*     */   
/*     */   public static class b
/*     */     extends CriterionInstanceAbstract {
/*     */     private final CriterionConditionDamage a;
/*     */     
/*     */     public b(CriterionConditionDamage param1CriterionConditionDamage) {
/*  70 */       super(CriterionTriggerEntityHurtPlayer.b());
/*  71 */       this.a = param1CriterionConditionDamage;
/*     */     }
/*     */     
/*     */     public boolean a(EntityPlayer param1EntityPlayer, DamageSource param1DamageSource, float param1Float1, float param1Float2, boolean param1Boolean) {
/*  75 */       if (!this.a.a(param1EntityPlayer, param1DamageSource, param1Float1, param1Float2, param1Boolean)) {
/*  76 */         return false;
/*     */       }
/*  78 */       return true;
/*     */     }
/*     */   }
/*     */   
/*     */   static class a {
/*     */     private final AdvancementDataPlayer a;
/*  84 */     private final Set<CriterionTrigger.a<CriterionTriggerEntityHurtPlayer.b>> b = Sets.newHashSet();
/*     */     
/*     */     public a(AdvancementDataPlayer param1AdvancementDataPlayer) {
/*  87 */       this.a = param1AdvancementDataPlayer;
/*     */     }
/*     */     
/*     */     public boolean a() {
/*  91 */       return this.b.isEmpty();
/*     */     }
/*     */     
/*     */     public void a(CriterionTrigger.a<CriterionTriggerEntityHurtPlayer.b> param1a) {
/*  95 */       this.b.add(param1a);
/*     */     }
/*     */     
/*     */     public void b(CriterionTrigger.a<CriterionTriggerEntityHurtPlayer.b> param1a) {
/*  99 */       this.b.remove(param1a);
/*     */     }
/*     */     
/*     */     public void a(EntityPlayer param1EntityPlayer, DamageSource param1DamageSource, float param1Float1, float param1Float2, boolean param1Boolean) {
/* 103 */       ArrayList<CriterionTrigger.a<CriterionTriggerEntityHurtPlayer.b>> arrayList = null;
/* 104 */       for (CriterionTrigger.a<CriterionTriggerEntityHurtPlayer.b> a1 : this.b) {
/* 105 */         if (((CriterionTriggerEntityHurtPlayer.b)a1.a()).a(param1EntityPlayer, param1DamageSource, param1Float1, param1Float2, param1Boolean)) {
/* 106 */           if (arrayList == null) {
/* 107 */             arrayList = Lists.newArrayList();
/*     */           }
/* 109 */           arrayList.add(a1);
/*     */         } 
/*     */       } 
/* 112 */       if (arrayList != null)
/* 113 */         for (CriterionTrigger.a<CriterionTriggerEntityHurtPlayer.b> a1 : arrayList)
/* 114 */           a1.a(this.a);  
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CriterionTriggerEntityHurtPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */