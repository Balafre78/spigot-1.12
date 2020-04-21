/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ public class CounterStatistic
/*    */   extends Statistic
/*    */ {
/*    */   public CounterStatistic(String paramString, IChatBaseComponent paramIChatBaseComponent, Counter paramCounter) {
/*  7 */     super(paramString, paramIChatBaseComponent, paramCounter);
/*    */   }
/*    */   
/*    */   public CounterStatistic(String paramString, IChatBaseComponent paramIChatBaseComponent) {
/* 11 */     super(paramString, paramIChatBaseComponent);
/*    */   }
/*    */ 
/*    */   
/*    */   public Statistic a() {
/* 16 */     super.a();
/*    */     
/* 18 */     StatisticList.c.add(this);
/*    */     
/* 20 */     return this;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CounterStatistic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */