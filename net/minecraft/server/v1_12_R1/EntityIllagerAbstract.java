/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class EntityIllagerAbstract
/*    */   extends EntityMonster
/*    */ {
/* 10 */   protected static final DataWatcherObject<Byte> a = DataWatcher.a((Class)EntityIllagerAbstract.class, DataWatcherRegistry.a);
/*    */ 
/*    */ 
/*    */   
/*    */   public EntityIllagerAbstract(World paramWorld) {
/* 15 */     super(paramWorld);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void i() {
/* 20 */     super.i();
/*    */     
/* 22 */     this.datawatcher.register(a, Byte.valueOf((byte)0));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void a(int paramInt, boolean paramBoolean) {
/* 31 */     int i = ((Byte)this.datawatcher.<Byte>get(a)).byteValue();
/* 32 */     if (paramBoolean) {
/* 33 */       i = i | paramInt;
/*    */     } else {
/* 35 */       i &= paramInt ^ 0xFFFFFFFF;
/*    */     } 
/* 37 */     this.datawatcher.set(a, Byte.valueOf((byte)(i & 0xFF)));
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumMonsterType getMonsterType() {
/* 42 */     return EnumMonsterType.ILLAGER;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityIllagerAbstract.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */