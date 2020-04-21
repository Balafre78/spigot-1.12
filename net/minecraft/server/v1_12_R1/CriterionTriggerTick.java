/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import com.google.common.collect.Maps;
/*    */ import com.google.common.collect.Sets;
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonObject;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CriterionTriggerTick
/*    */   implements CriterionTrigger<CriterionTriggerTick.b>
/*    */ {
/* 17 */   public static final MinecraftKey a = new MinecraftKey("tick");
/*    */   
/* 19 */   private final Map<AdvancementDataPlayer, a> b = Maps.newHashMap();
/*    */ 
/*    */   
/*    */   public MinecraftKey a() {
/* 23 */     return a;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(AdvancementDataPlayer paramAdvancementDataPlayer, CriterionTrigger.a<b> parama) {
/* 28 */     a a1 = this.b.get(paramAdvancementDataPlayer);
/* 29 */     if (a1 == null) {
/* 30 */       a1 = new a(paramAdvancementDataPlayer);
/* 31 */       this.b.put(paramAdvancementDataPlayer, a1);
/*    */     } 
/* 33 */     a1.a(parama);
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(AdvancementDataPlayer paramAdvancementDataPlayer, CriterionTrigger.a<b> parama) {
/* 38 */     a a1 = this.b.get(paramAdvancementDataPlayer);
/* 39 */     if (a1 != null) {
/* 40 */       a1.b(parama);
/* 41 */       if (a1.a()) {
/* 42 */         this.b.remove(paramAdvancementDataPlayer);
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(AdvancementDataPlayer paramAdvancementDataPlayer) {
/* 49 */     this.b.remove(paramAdvancementDataPlayer);
/*    */   }
/*    */ 
/*    */   
/*    */   public b b(JsonObject paramJsonObject, JsonDeserializationContext paramJsonDeserializationContext) {
/* 54 */     return new b();
/*    */   }
/*    */   
/*    */   public void a(EntityPlayer paramEntityPlayer) {
/* 58 */     a a = this.b.get(paramEntityPlayer.getAdvancementData());
/* 59 */     if (a != null)
/* 60 */       a.b(); 
/*    */   }
/*    */   
/*    */   public static class b
/*    */     extends CriterionInstanceAbstract {
/*    */     public b() {
/* 66 */       super(CriterionTriggerTick.a);
/*    */     }
/*    */   }
/*    */   
/*    */   static class a {
/*    */     private final AdvancementDataPlayer a;
/* 72 */     private final Set<CriterionTrigger.a<CriterionTriggerTick.b>> b = Sets.newHashSet();
/*    */     
/*    */     public a(AdvancementDataPlayer param1AdvancementDataPlayer) {
/* 75 */       this.a = param1AdvancementDataPlayer;
/*    */     }
/*    */     
/*    */     public boolean a() {
/* 79 */       return this.b.isEmpty();
/*    */     }
/*    */     
/*    */     public void a(CriterionTrigger.a<CriterionTriggerTick.b> param1a) {
/* 83 */       this.b.add(param1a);
/*    */     }
/*    */     
/*    */     public void b(CriterionTrigger.a<CriterionTriggerTick.b> param1a) {
/* 87 */       this.b.remove(param1a);
/*    */     }
/*    */     
/*    */     public void b() {
/* 91 */       for (CriterionTrigger.a a1 : Lists.newArrayList(this.b))
/* 92 */         a1.a(this.a); 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CriterionTriggerTick.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */