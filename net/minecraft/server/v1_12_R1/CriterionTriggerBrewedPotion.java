/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.Sets;
/*     */ import com.google.gson.JsonDeserializationContext;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonSyntaxException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CriterionTriggerBrewedPotion
/*     */   implements CriterionTrigger<CriterionTriggerBrewedPotion.b>
/*     */ {
/*  22 */   private static final MinecraftKey a = new MinecraftKey("brewed_potion");
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
/*  58 */     PotionRegistry potionRegistry = null;
/*  59 */     if (paramJsonObject.has("potion")) {
/*  60 */       MinecraftKey minecraftKey = new MinecraftKey(ChatDeserializer.h(paramJsonObject, "potion"));
/*  61 */       if (!PotionRegistry.a.d(minecraftKey)) {
/*  62 */         throw new JsonSyntaxException("Unknown potion '" + minecraftKey + "'");
/*     */       }
/*  64 */       potionRegistry = PotionRegistry.a.get(minecraftKey);
/*     */     } 
/*  66 */     return new b(potionRegistry);
/*     */   }
/*     */   
/*     */   public void a(EntityPlayer paramEntityPlayer, PotionRegistry paramPotionRegistry) {
/*  70 */     a a = this.b.get(paramEntityPlayer.getAdvancementData());
/*  71 */     if (a != null)
/*  72 */       a.a(paramPotionRegistry); 
/*     */   }
/*     */   
/*     */   public static class b
/*     */     extends CriterionInstanceAbstract {
/*     */     private final PotionRegistry a;
/*     */     
/*     */     public b(@Nullable PotionRegistry param1PotionRegistry) {
/*  80 */       super(CriterionTriggerBrewedPotion.b());
/*  81 */       this.a = param1PotionRegistry;
/*     */     }
/*     */     
/*     */     public boolean a(PotionRegistry param1PotionRegistry) {
/*  85 */       if (this.a != null && this.a != param1PotionRegistry) {
/*  86 */         return false;
/*     */       }
/*  88 */       return true;
/*     */     }
/*     */   }
/*     */   
/*     */   static class a {
/*     */     private final AdvancementDataPlayer a;
/*  94 */     private final Set<CriterionTrigger.a<CriterionTriggerBrewedPotion.b>> b = Sets.newHashSet();
/*     */     
/*     */     public a(AdvancementDataPlayer param1AdvancementDataPlayer) {
/*  97 */       this.a = param1AdvancementDataPlayer;
/*     */     }
/*     */     
/*     */     public boolean a() {
/* 101 */       return this.b.isEmpty();
/*     */     }
/*     */     
/*     */     public void a(CriterionTrigger.a<CriterionTriggerBrewedPotion.b> param1a) {
/* 105 */       this.b.add(param1a);
/*     */     }
/*     */     
/*     */     public void b(CriterionTrigger.a<CriterionTriggerBrewedPotion.b> param1a) {
/* 109 */       this.b.remove(param1a);
/*     */     }
/*     */     
/*     */     public void a(PotionRegistry param1PotionRegistry) {
/* 113 */       ArrayList<CriterionTrigger.a<CriterionTriggerBrewedPotion.b>> arrayList = null;
/* 114 */       for (CriterionTrigger.a<CriterionTriggerBrewedPotion.b> a1 : this.b) {
/* 115 */         if (((CriterionTriggerBrewedPotion.b)a1.a()).a(param1PotionRegistry)) {
/* 116 */           if (arrayList == null) {
/* 117 */             arrayList = Lists.newArrayList();
/*     */           }
/* 119 */           arrayList.add(a1);
/*     */         } 
/*     */       } 
/* 122 */       if (arrayList != null)
/* 123 */         for (CriterionTrigger.a<CriterionTriggerBrewedPotion.b> a1 : arrayList)
/* 124 */           a1.a(this.a);  
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CriterionTriggerBrewedPotion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */