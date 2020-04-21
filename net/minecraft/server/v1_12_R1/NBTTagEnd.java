/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NBTTagEnd
/*    */   extends NBTBase
/*    */ {
/*    */   void load(DataInput paramDataInput, int paramInt, NBTReadLimiter paramNBTReadLimiter) throws IOException {
/* 18 */     paramNBTReadLimiter.a(64L);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   void write(DataOutput paramDataOutput) throws IOException {}
/*    */ 
/*    */   
/*    */   public byte getTypeId() {
/* 27 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 32 */     return "END";
/*    */   }
/*    */ 
/*    */   
/*    */   public NBTTagEnd c() {
/* 37 */     return new NBTTagEnd();
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\NBTTagEnd.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */