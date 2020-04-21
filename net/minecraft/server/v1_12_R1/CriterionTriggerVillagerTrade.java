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
/*     */ public class CriterionTriggerVillagerTrade
/*     */   implements CriterionTrigger<CriterionTriggerVillagerTrade.b>
/*     */ {
/*  20 */   private static final MinecraftKey a = new MinecraftKey("villager_trade");
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
/*  56 */     CriterionConditionEntity criterionConditionEntity = CriterionConditionEntity.a(paramJsonObject.get("villager"));
/*  57 */     CriterionConditionItem criterionConditionItem = CriterionConditionItem.a(paramJsonObject.get("item"));
/*  58 */     return new b(criterionConditionEntity, criterionConditionItem);
/*     */   }
/*     */   
/*     */   public void a(EntityPlayer paramEntityPlayer, EntityVillager paramEntityVillager, ItemStack paramItemStack) {
/*  62 */     a a = this.b.get(paramEntityPlayer.getAdvancementData());
/*  63 */     if (a != null)
/*  64 */       a.a(paramEntityPlayer, paramEntityVillager, paramItemStack); 
/*     */   }
/*     */   
/*     */   public static class b
/*     */     extends CriterionInstanceAbstract {
/*     */     private final CriterionConditionEntity a;
/*     */     private final CriterionConditionItem b;
/*     */     
/*     */     public b(CriterionConditionEntity param1CriterionConditionEntity, CriterionConditionItem param1CriterionConditionItem) {
/*  73 */       super(CriterionTriggerVillagerTrade.b());
/*  74 */       this.a = param1CriterionConditionEntity;
/*  75 */       this.b = param1CriterionConditionItem;
/*     */     }
/*     */     
/*     */     public boolean a(EntityPlayer param1EntityPlayer, EntityVillager param1EntityVillager, ItemStack param1ItemStack) {
/*  79 */       if (!this.a.a(param1EntityPlayer, param1EntityVillager)) {
/*  80 */         return false;
/*     */       }
/*  82 */       if (!this.b.a(param1ItemStack)) {
/*  83 */         return false;
/*     */       }
/*  85 */       return true;
/*     */     }
/*     */   }
/*     */   
/*     */   static class a {
/*     */     private final AdvancementDataPlayer a;
/*  91 */     private final Set<CriterionTrigger.a<CriterionTriggerVillagerTrade.b>> b = Sets.newHashSet();
/*     */     
/*     */     public a(AdvancementDataPlayer param1AdvancementDataPlayer) {
/*  94 */       this.a = param1AdvancementDataPlayer;
/*     */     }
/*     */     
/*     */     public boolean a() {
/*  98 */       return this.b.isEmpty();
/*     */     }
/*     */     
/*     */     public void a(CriterionTrigger.a<CriterionTriggerVillagerTrade.b> param1a) {
/* 102 */       this.b.add(param1a);
/*     */     }
/*     */     
/*     */     public void b(CriterionTrigger.a<CriterionTriggerVillagerTrade.b> param1a) {
/* 106 */       this.b.remove(param1a);
/*     */     }
/*     */     
/*     */     public void a(EntityPlayer param1EntityPlayer, EntityVillager param1EntityVillager, ItemStack param1ItemStack) {
/* 110 */       ArrayList<CriterionTrigger.a<CriterionTriggerVillagerTrade.b>> arrayList = null;
/* 111 */       for (CriterionTrigger.a<CriterionTriggerVillagerTrade.b> a1 : this.b) {
/* 112 */         if (((CriterionTriggerVillagerTrade.b)a1.a()).a(param1EntityPlayer, param1EntityVillager, param1ItemStack)) {
/* 113 */           if (arrayList == null) {
/* 114 */             arrayList = Lists.newArrayList();
/*     */           }
/* 116 */           arrayList.add(a1);
/*     */         } 
/*     */       } 
/* 119 */       if (arrayList != null)
/* 120 */         for (CriterionTrigger.a<CriterionTriggerVillagerTrade.b> a1 : arrayList)
/* 121 */           a1.a(this.a);  
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CriterionTriggerVillagerTrade.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */