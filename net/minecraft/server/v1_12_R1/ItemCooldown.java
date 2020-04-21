/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ public class ItemCooldown
/*    */ {
/* 10 */   public final Map<Item, Info> cooldowns = Maps.newHashMap();
/*    */   public int currentTick;
/*    */   
/*    */   public boolean a(Item paramItem) {
/* 14 */     return (a(paramItem, 0.0F) > 0.0F);
/*    */   }
/*    */   
/*    */   public float a(Item paramItem, float paramFloat) {
/* 18 */     Info info = this.cooldowns.get(paramItem);
/*    */     
/* 20 */     if (info != null) {
/* 21 */       float f1 = (info.endTick - info.a);
/* 22 */       float f2 = info.endTick - this.currentTick + paramFloat;
/* 23 */       return MathHelper.a(f2 / f1, 0.0F, 1.0F);
/*    */     } 
/*    */     
/* 26 */     return 0.0F;
/*    */   }
/*    */   
/*    */   public void a() {
/* 30 */     this.currentTick++;
/*    */     
/* 32 */     if (!this.cooldowns.isEmpty()) {
/* 33 */       for (Iterator<Map.Entry> iterator = this.cooldowns.entrySet().iterator(); iterator.hasNext(); ) {
/* 34 */         Map.Entry entry = iterator.next();
/* 35 */         if (((Info)entry.getValue()).endTick <= this.currentTick) {
/* 36 */           iterator.remove();
/* 37 */           c((Item)entry.getKey());
/*    */         } 
/*    */       } 
/*    */     }
/*    */   }
/*    */   
/*    */   public void a(Item paramItem, int paramInt) {
/* 44 */     this.cooldowns.put(paramItem, new Info(this.currentTick, this.currentTick + paramInt));
/* 45 */     b(paramItem, paramInt);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void b(Item paramItem, int paramInt) {}
/*    */ 
/*    */   
/*    */   protected void c(Item paramItem) {}
/*    */ 
/*    */   
/*    */   public class Info
/*    */   {
/*    */     final int a;
/*    */     
/*    */     public final int endTick;
/*    */ 
/*    */     
/*    */     private Info(ItemCooldown this$0, int param1Int1, int param1Int2) {
/* 64 */       this.a = param1Int1;
/* 65 */       this.endTick = param1Int2;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemCooldown.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */