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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CriterionTriggerRecipeUnlocked
/*     */   implements CriterionTrigger<CriterionTriggerRecipeUnlocked.b>
/*     */ {
/*  22 */   private static final MinecraftKey a = new MinecraftKey("recipe_unlocked");
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
/*  58 */     MinecraftKey minecraftKey = new MinecraftKey(ChatDeserializer.h(paramJsonObject, "recipe"));
/*  59 */     IRecipe iRecipe = CraftingManager.a(minecraftKey);
/*  60 */     if (iRecipe == null) {
/*  61 */       throw new JsonSyntaxException("Unknown recipe '" + minecraftKey + "'");
/*     */     }
/*  63 */     return new b(iRecipe);
/*     */   }
/*     */   
/*     */   public void a(EntityPlayer paramEntityPlayer, IRecipe paramIRecipe) {
/*  67 */     a a = this.b.get(paramEntityPlayer.getAdvancementData());
/*  68 */     if (a != null)
/*  69 */       a.a(paramIRecipe); 
/*     */   }
/*     */   
/*     */   public static class b
/*     */     extends CriterionInstanceAbstract {
/*     */     private final IRecipe a;
/*     */     
/*     */     public b(IRecipe param1IRecipe) {
/*  77 */       super(CriterionTriggerRecipeUnlocked.b());
/*  78 */       this.a = param1IRecipe;
/*     */     }
/*     */     
/*     */     public boolean a(IRecipe param1IRecipe) {
/*  82 */       return (this.a == param1IRecipe);
/*     */     }
/*     */   }
/*     */   
/*     */   static class a {
/*     */     private final AdvancementDataPlayer a;
/*  88 */     private final Set<CriterionTrigger.a<CriterionTriggerRecipeUnlocked.b>> b = Sets.newHashSet();
/*     */     
/*     */     public a(AdvancementDataPlayer param1AdvancementDataPlayer) {
/*  91 */       this.a = param1AdvancementDataPlayer;
/*     */     }
/*     */     
/*     */     public boolean a() {
/*  95 */       return this.b.isEmpty();
/*     */     }
/*     */     
/*     */     public void a(CriterionTrigger.a<CriterionTriggerRecipeUnlocked.b> param1a) {
/*  99 */       this.b.add(param1a);
/*     */     }
/*     */     
/*     */     public void b(CriterionTrigger.a<CriterionTriggerRecipeUnlocked.b> param1a) {
/* 103 */       this.b.remove(param1a);
/*     */     }
/*     */     
/*     */     public void a(IRecipe param1IRecipe) {
/* 107 */       ArrayList<CriterionTrigger.a<CriterionTriggerRecipeUnlocked.b>> arrayList = null;
/* 108 */       for (CriterionTrigger.a<CriterionTriggerRecipeUnlocked.b> a1 : this.b) {
/* 109 */         if (((CriterionTriggerRecipeUnlocked.b)a1.a()).a(param1IRecipe)) {
/* 110 */           if (arrayList == null) {
/* 111 */             arrayList = Lists.newArrayList();
/*     */           }
/* 113 */           arrayList.add(a1);
/*     */         } 
/*     */       } 
/* 116 */       if (arrayList != null)
/* 117 */         for (CriterionTrigger.a<CriterionTriggerRecipeUnlocked.b> a1 : arrayList)
/* 118 */           a1.a(this.a);  
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CriterionTriggerRecipeUnlocked.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */