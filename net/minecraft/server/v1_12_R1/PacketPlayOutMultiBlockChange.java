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
/*    */ public class PacketPlayOutMultiBlockChange
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private ChunkCoordIntPair a;
/*    */   private MultiBlockChangeInfo[] b;
/*    */   
/*    */   public PacketPlayOutMultiBlockChange() {}
/*    */   
/*    */   public PacketPlayOutMultiBlockChange(int paramInt, short[] paramArrayOfshort, Chunk paramChunk) {
/* 22 */     this.a = new ChunkCoordIntPair(paramChunk.locX, paramChunk.locZ);
/*    */     
/* 24 */     this.b = new MultiBlockChangeInfo[paramInt];
/* 25 */     for (byte b = 0; b < this.b.length; b++) {
/* 26 */       this.b[b] = new MultiBlockChangeInfo(this, paramArrayOfshort[b], paramChunk);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 32 */     this.a = new ChunkCoordIntPair(paramPacketDataSerializer.readInt(), paramPacketDataSerializer.readInt());
/* 33 */     this.b = new MultiBlockChangeInfo[paramPacketDataSerializer.g()];
/*    */     
/* 35 */     for (byte b = 0; b < this.b.length; b++) {
/* 36 */       this.b[b] = new MultiBlockChangeInfo(this, paramPacketDataSerializer.readShort(), Block.REGISTRY_ID.fromId(paramPacketDataSerializer.g()));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 42 */     paramPacketDataSerializer.writeInt(this.a.x);
/* 43 */     paramPacketDataSerializer.writeInt(this.a.z);
/* 44 */     paramPacketDataSerializer.d(this.b.length);
/* 45 */     for (MultiBlockChangeInfo multiBlockChangeInfo : this.b) {
/* 46 */       paramPacketDataSerializer.writeShort(multiBlockChangeInfo.b());
/* 47 */       paramPacketDataSerializer.d(Block.REGISTRY_ID.getId(multiBlockChangeInfo.c()));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut paramPacketListenerPlayOut) {
/* 53 */     paramPacketListenerPlayOut.a(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public class MultiBlockChangeInfo
/*    */   {
/*    */     private final short b;
/*    */     
/*    */     private final IBlockData c;
/*    */ 
/*    */     
/*    */     public MultiBlockChangeInfo(PacketPlayOutMultiBlockChange this$0, short param1Short, IBlockData param1IBlockData) {
/* 65 */       this.b = param1Short;
/* 66 */       this.c = param1IBlockData;
/*    */     }
/*    */     
/*    */     public MultiBlockChangeInfo(PacketPlayOutMultiBlockChange this$0, short param1Short, Chunk param1Chunk) {
/* 70 */       this.b = param1Short;
/* 71 */       this.c = param1Chunk.getBlockData(a());
/*    */     }
/*    */     
/*    */     public BlockPosition a() {
/* 75 */       return new BlockPosition(PacketPlayOutMultiBlockChange.a(this.a).a(this.b >> 12 & 0xF, this.b & 0xFF, this.b >> 8 & 0xF));
/*    */     }
/*    */     
/*    */     public short b() {
/* 79 */       return this.b;
/*    */     }
/*    */     
/*    */     public IBlockData c() {
/* 83 */       return this.c;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\PacketPlayOutMultiBlockChange.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */