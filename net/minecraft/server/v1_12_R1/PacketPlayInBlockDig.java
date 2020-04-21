/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayInBlockDig
/*    */   implements Packet<PacketListenerPlayIn>
/*    */ {
/*    */   private BlockPosition a;
/*    */   private EnumDirection b;
/*    */   private EnumPlayerDigType c;
/*    */   
/*    */   public void a(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 26 */     this.c = paramPacketDataSerializer.<EnumPlayerDigType>a(EnumPlayerDigType.class);
/* 27 */     this.a = paramPacketDataSerializer.e();
/* 28 */     this.b = EnumDirection.fromType1(paramPacketDataSerializer.readUnsignedByte());
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 33 */     paramPacketDataSerializer.a(this.c);
/* 34 */     paramPacketDataSerializer.a(this.a);
/* 35 */     paramPacketDataSerializer.writeByte(this.b.a());
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayIn paramPacketListenerPlayIn) {
/* 40 */     paramPacketListenerPlayIn.a(this);
/*    */   }
/*    */   
/*    */   public BlockPosition a() {
/* 44 */     return this.a;
/*    */   }
/*    */   
/*    */   public EnumDirection b() {
/* 48 */     return this.b;
/*    */   }
/*    */   
/*    */   public EnumPlayerDigType c() {
/* 52 */     return this.c;
/*    */   }
/*    */   
/*    */   public enum EnumPlayerDigType {
/* 56 */     START_DESTROY_BLOCK,
/* 57 */     ABORT_DESTROY_BLOCK,
/* 58 */     STOP_DESTROY_BLOCK,
/* 59 */     DROP_ALL_ITEMS,
/* 60 */     DROP_ITEM,
/* 61 */     RELEASE_USE_ITEM,
/* 62 */     SWAP_HELD_ITEMS;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\PacketPlayInBlockDig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */