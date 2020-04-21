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
/*     */ public class CriterionTriggerItemDurabilityChanged
/*     */   implements CriterionTrigger<CriterionTriggerItemDurabilityChanged.b>
/*     */ {
/*  19 */   private static final MinecraftKey a = new MinecraftKey("item_durability_changed");
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
/*  55 */     CriterionConditionItem criterionConditionItem = CriterionConditionItem.a(paramJsonObject.get("item"));
/*  56 */     CriterionConditionValue criterionConditionValue1 = CriterionConditionValue.a(paramJsonObject.get("durability"));
/*  57 */     CriterionConditionValue criterionConditionValue2 = CriterionConditionValue.a(paramJsonObject.get("delta"));
/*  58 */     return new b(criterionConditionItem, criterionConditionValue1, criterionConditionValue2);
/*     */   }
/*     */   
/*     */   public void a(EntityPlayer paramEntityPlayer, ItemStack paramItemStack, int paramInt) {
/*  62 */     a a = this.b.get(paramEntityPlayer.getAdvancementData());
/*  63 */     if (a != null)
/*  64 */       a.a(paramItemStack, paramInt); 
/*     */   }
/*     */   
/*     */   public static class b
/*     */     extends CriterionInstanceAbstract {
/*     */     private final CriterionConditionItem a;
/*     */     private final CriterionConditionValue b;
/*     */     private final CriterionConditionValue c;
/*     */     
/*     */     public b(CriterionConditionItem param1CriterionConditionItem, CriterionConditionValue param1CriterionConditionValue1, CriterionConditionValue param1CriterionConditionValue2) {
/*  74 */       super(CriterionTriggerItemDurabilityChanged.b());
/*  75 */       this.a = param1CriterionConditionItem;
/*  76 */       this.b = param1CriterionConditionValue1;
/*  77 */       this.c = param1CriterionConditionValue2;
/*     */     }
/*     */     
/*     */     public boolean a(ItemStack param1ItemStack, int param1Int) {
/*  81 */       if (!this.a.a(param1ItemStack)) {
/*  82 */         return false;
/*     */       }
/*  84 */       if (!this.b.a((param1ItemStack.k() - param1Int))) {
/*  85 */         return false;
/*     */       }
/*  87 */       if (!this.c.a((param1ItemStack.i() - param1Int))) {
/*  88 */         return false;
/*     */       }
/*  90 */       return true;
/*     */     }
/*     */   }
/*     */   
/*     */   static class a {
/*     */     private final AdvancementDataPlayer a;
/*  96 */     private final Set<CriterionTrigger.a<CriterionTriggerItemDurabilityChanged.b>> b = Sets.newHashSet();
/*     */     
/*     */     public a(AdvancementDataPlayer param1AdvancementDataPlayer) {
/*  99 */       this.a = param1AdvancementDataPlayer;
/*     */     }
/*     */     
/*     */     public boolean a() {
/* 103 */       return this.b.isEmpty();
/*     */     }
/*     */     
/*     */     public void a(CriterionTrigger.a<CriterionTriggerItemDurabilityChanged.b> param1a) {
/* 107 */       this.b.add(param1a);
/*     */     }
/*     */     
/*     */     public void b(CriterionTrigger.a<CriterionTriggerItemDurabilityChanged.b> param1a) {
/* 111 */       this.b.remove(param1a);
/*     */     }
/*     */     
/*     */     public void a(ItemStack param1ItemStack, int param1Int) {
/* 115 */       ArrayList<CriterionTrigger.a<CriterionTriggerItemDurabilityChanged.b>> arrayList = null;
/* 116 */       for (CriterionTrigger.a<CriterionTriggerItemDurabilityChanged.b> a1 : this.b) {
/* 117 */         if (((CriterionTriggerItemDurabilityChanged.b)a1.a()).a(param1ItemStack, param1Int)) {
/* 118 */           if (arrayList == null) {
/* 119 */             arrayList = Lists.newArrayList();
/*     */           }
/* 121 */           arrayList.add(a1);
/*     */         } 
/*     */       } 
/* 124 */       if (arrayList != null)
/* 125 */         for (CriterionTrigger.a<CriterionTriggerItemDurabilityChanged.b> a1 : arrayList)
/* 126 */           a1.a(this.a);  
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CriterionTriggerItemDurabilityChanged.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */