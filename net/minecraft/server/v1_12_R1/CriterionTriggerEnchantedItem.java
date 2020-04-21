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
/*     */ public class CriterionTriggerEnchantedItem
/*     */   implements CriterionTrigger<CriterionTriggerEnchantedItem.b>
/*     */ {
/*  19 */   private static final MinecraftKey a = new MinecraftKey("enchanted_item");
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
/*  56 */     CriterionConditionValue criterionConditionValue = CriterionConditionValue.a(paramJsonObject.get("levels"));
/*  57 */     return new b(criterionConditionItem, criterionConditionValue);
/*     */   }
/*     */   
/*     */   public void a(EntityPlayer paramEntityPlayer, ItemStack paramItemStack, int paramInt) {
/*  61 */     a a = this.b.get(paramEntityPlayer.getAdvancementData());
/*  62 */     if (a != null)
/*  63 */       a.a(paramItemStack, paramInt); 
/*     */   }
/*     */   
/*     */   public static class b
/*     */     extends CriterionInstanceAbstract {
/*     */     private final CriterionConditionItem a;
/*     */     private final CriterionConditionValue b;
/*     */     
/*     */     public b(CriterionConditionItem param1CriterionConditionItem, CriterionConditionValue param1CriterionConditionValue) {
/*  72 */       super(CriterionTriggerEnchantedItem.b());
/*  73 */       this.a = param1CriterionConditionItem;
/*  74 */       this.b = param1CriterionConditionValue;
/*     */     }
/*     */     
/*     */     public boolean a(ItemStack param1ItemStack, int param1Int) {
/*  78 */       if (!this.a.a(param1ItemStack)) {
/*  79 */         return false;
/*     */       }
/*  81 */       if (!this.b.a(param1Int)) {
/*  82 */         return false;
/*     */       }
/*  84 */       return true;
/*     */     }
/*     */   }
/*     */   
/*     */   static class a {
/*     */     private final AdvancementDataPlayer a;
/*  90 */     private final Set<CriterionTrigger.a<CriterionTriggerEnchantedItem.b>> b = Sets.newHashSet();
/*     */     
/*     */     public a(AdvancementDataPlayer param1AdvancementDataPlayer) {
/*  93 */       this.a = param1AdvancementDataPlayer;
/*     */     }
/*     */     
/*     */     public boolean a() {
/*  97 */       return this.b.isEmpty();
/*     */     }
/*     */     
/*     */     public void a(CriterionTrigger.a<CriterionTriggerEnchantedItem.b> param1a) {
/* 101 */       this.b.add(param1a);
/*     */     }
/*     */     
/*     */     public void b(CriterionTrigger.a<CriterionTriggerEnchantedItem.b> param1a) {
/* 105 */       this.b.remove(param1a);
/*     */     }
/*     */     
/*     */     public void a(ItemStack param1ItemStack, int param1Int) {
/* 109 */       ArrayList<CriterionTrigger.a<CriterionTriggerEnchantedItem.b>> arrayList = null;
/* 110 */       for (CriterionTrigger.a<CriterionTriggerEnchantedItem.b> a1 : this.b) {
/* 111 */         if (((CriterionTriggerEnchantedItem.b)a1.a()).a(param1ItemStack, param1Int)) {
/* 112 */           if (arrayList == null) {
/* 113 */             arrayList = Lists.newArrayList();
/*     */           }
/* 115 */           arrayList.add(a1);
/*     */         } 
/*     */       } 
/* 118 */       if (arrayList != null)
/* 119 */         for (CriterionTrigger.a<CriterionTriggerEnchantedItem.b> a1 : arrayList)
/* 120 */           a1.a(this.a);  
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CriterionTriggerEnchantedItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */