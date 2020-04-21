/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.Sets;
/*     */ import com.google.gson.JsonDeserializationContext;
/*     */ import com.google.gson.JsonObject;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CriterionTriggerInventoryChanged
/*     */   implements CriterionTrigger<CriterionTriggerInventoryChanged.b>
/*     */ {
/*  22 */   private static final MinecraftKey a = new MinecraftKey("inventory_changed");
/*  23 */   private final Map<AdvancementDataPlayer, a> b = Maps.newHashMap();
/*     */ 
/*     */   
/*     */   public MinecraftKey a() {
/*  27 */     return a;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(AdvancementDataPlayer paramAdvancementDataPlayer, CriterionTrigger.a<b> parama) {
/*  32 */     a a1 = this.b.get(paramAdvancementDataPlayer);
/*  33 */     if (a1 == null) {
/*  34 */       a1 = new a(paramAdvancementDataPlayer);
/*  35 */       this.b.put(paramAdvancementDataPlayer, a1);
/*     */     } 
/*  37 */     a1.a(parama);
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(AdvancementDataPlayer paramAdvancementDataPlayer, CriterionTrigger.a<b> parama) {
/*  42 */     a a1 = this.b.get(paramAdvancementDataPlayer);
/*  43 */     if (a1 != null) {
/*  44 */       a1.b(parama);
/*  45 */       if (a1.a()) {
/*  46 */         this.b.remove(paramAdvancementDataPlayer);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(AdvancementDataPlayer paramAdvancementDataPlayer) {
/*  53 */     this.b.remove(paramAdvancementDataPlayer);
/*     */   }
/*     */ 
/*     */   
/*     */   public b b(JsonObject paramJsonObject, JsonDeserializationContext paramJsonDeserializationContext) {
/*  58 */     JsonObject jsonObject = ChatDeserializer.a(paramJsonObject, "slots", new JsonObject());
/*  59 */     CriterionConditionValue criterionConditionValue1 = CriterionConditionValue.a(jsonObject.get("occupied"));
/*  60 */     CriterionConditionValue criterionConditionValue2 = CriterionConditionValue.a(jsonObject.get("full"));
/*  61 */     CriterionConditionValue criterionConditionValue3 = CriterionConditionValue.a(jsonObject.get("empty"));
/*  62 */     CriterionConditionItem[] arrayOfCriterionConditionItem = CriterionConditionItem.b(paramJsonObject.get("items"));
/*  63 */     return new b(criterionConditionValue1, criterionConditionValue2, criterionConditionValue3, arrayOfCriterionConditionItem);
/*     */   }
/*     */   
/*     */   public void a(EntityPlayer paramEntityPlayer, PlayerInventory paramPlayerInventory) {
/*  67 */     a a = this.b.get(paramEntityPlayer.getAdvancementData());
/*  68 */     if (a != null)
/*  69 */       a.a(paramPlayerInventory); 
/*     */   }
/*     */   
/*     */   public static class b
/*     */     extends CriterionInstanceAbstract {
/*     */     private final CriterionConditionValue a;
/*     */     private final CriterionConditionValue b;
/*     */     private final CriterionConditionValue c;
/*     */     private final CriterionConditionItem[] d;
/*     */     
/*     */     public b(CriterionConditionValue param1CriterionConditionValue1, CriterionConditionValue param1CriterionConditionValue2, CriterionConditionValue param1CriterionConditionValue3, CriterionConditionItem[] param1ArrayOfCriterionConditionItem) {
/*  80 */       super(CriterionTriggerInventoryChanged.b());
/*  81 */       this.a = param1CriterionConditionValue1;
/*  82 */       this.b = param1CriterionConditionValue2;
/*  83 */       this.c = param1CriterionConditionValue3;
/*  84 */       this.d = param1ArrayOfCriterionConditionItem;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a(PlayerInventory param1PlayerInventory) {
/*  89 */       byte b1 = 0;
/*  90 */       byte b2 = 0;
/*  91 */       byte b3 = 0;
/*  92 */       ArrayList arrayList = Lists.newArrayList((Object[])this.d);
/*  93 */       for (byte b4 = 0; b4 < param1PlayerInventory.getSize(); b4++) {
/*  94 */         ItemStack itemStack = param1PlayerInventory.getItem(b4);
/*  95 */         if (itemStack.isEmpty()) {
/*  96 */           b2++;
/*     */         } else {
/*  98 */           b3++;
/*  99 */           if (itemStack.getCount() >= itemStack.getMaxStackSize()) {
/* 100 */             b1++;
/*     */           }
/* 102 */           for (Iterator<CriterionConditionItem> iterator = arrayList.iterator(); iterator.hasNext(); ) {
/* 103 */             CriterionConditionItem criterionConditionItem = iterator.next();
/* 104 */             if (criterionConditionItem.a(itemStack)) {
/* 105 */               iterator.remove();
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/* 110 */       if (!this.b.a(b1)) {
/* 111 */         return false;
/*     */       }
/* 113 */       if (!this.c.a(b2)) {
/* 114 */         return false;
/*     */       }
/* 116 */       if (!this.a.a(b3)) {
/* 117 */         return false;
/*     */       }
/* 119 */       if (!arrayList.isEmpty()) {
/* 120 */         return false;
/*     */       }
/* 122 */       return true;
/*     */     }
/*     */   }
/*     */   
/*     */   static class a {
/*     */     private final AdvancementDataPlayer a;
/* 128 */     private final Set<CriterionTrigger.a<CriterionTriggerInventoryChanged.b>> b = Sets.newHashSet();
/*     */     
/*     */     public a(AdvancementDataPlayer param1AdvancementDataPlayer) {
/* 131 */       this.a = param1AdvancementDataPlayer;
/*     */     }
/*     */     
/*     */     public boolean a() {
/* 135 */       return this.b.isEmpty();
/*     */     }
/*     */     
/*     */     public void a(CriterionTrigger.a<CriterionTriggerInventoryChanged.b> param1a) {
/* 139 */       this.b.add(param1a);
/*     */     }
/*     */     
/*     */     public void b(CriterionTrigger.a<CriterionTriggerInventoryChanged.b> param1a) {
/* 143 */       this.b.remove(param1a);
/*     */     }
/*     */     
/*     */     public void a(PlayerInventory param1PlayerInventory) {
/* 147 */       ArrayList<CriterionTrigger.a<CriterionTriggerInventoryChanged.b>> arrayList = null;
/* 148 */       for (CriterionTrigger.a<CriterionTriggerInventoryChanged.b> a1 : this.b) {
/* 149 */         if (((CriterionTriggerInventoryChanged.b)a1.a()).a(param1PlayerInventory)) {
/* 150 */           if (arrayList == null) {
/* 151 */             arrayList = Lists.newArrayList();
/*     */           }
/* 153 */           arrayList.add(a1);
/*     */         } 
/*     */       } 
/* 156 */       if (arrayList != null)
/* 157 */         for (CriterionTrigger.a<CriterionTriggerInventoryChanged.b> a1 : arrayList)
/* 158 */           a1.a(this.a);  
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CriterionTriggerInventoryChanged.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */