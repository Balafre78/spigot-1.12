/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayOutEntityMetadata
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private int a;
/*    */   private List<DataWatcher.Item<?>> b;
/*    */   
/*    */   public PacketPlayOutEntityMetadata() {}
/*    */   
/*    */   public PacketPlayOutEntityMetadata(int paramInt, DataWatcher paramDataWatcher, boolean paramBoolean) {
/* 19 */     this.a = paramInt;
/* 20 */     if (paramBoolean) {
/* 21 */       this.b = paramDataWatcher.c();
/* 22 */       paramDataWatcher.e();
/*    */     } else {
/* 24 */       this.b = paramDataWatcher.b();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 30 */     this.a = paramPacketDataSerializer.g();
/* 31 */     this.b = DataWatcher.b(paramPacketDataSerializer);
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 36 */     paramPacketDataSerializer.d(this.a);
/* 37 */     DataWatcher.a(this.b, paramPacketDataSerializer);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut paramPacketListenerPlayOut) {
/* 42 */     paramPacketListenerPlayOut.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\PacketPlayOutEntityMetadata.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */