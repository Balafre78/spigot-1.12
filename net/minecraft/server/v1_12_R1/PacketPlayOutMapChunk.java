/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import io.netty.buffer.Unpooled;
/*     */ import java.io.IOException;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PacketPlayOutMapChunk
/*     */   implements Packet<PacketListenerPlayOut>
/*     */ {
/*     */   private int a;
/*     */   private int b;
/*     */   private int c;
/*     */   private byte[] d;
/*     */   private List<NBTTagCompound> e;
/*     */   private boolean f;
/*     */   
/*     */   public PacketPlayOutMapChunk() {}
/*     */   
/*     */   public PacketPlayOutMapChunk(Chunk paramChunk, int paramInt) {
/*  34 */     this.a = paramChunk.locX;
/*  35 */     this.b = paramChunk.locZ;
/*  36 */     this.f = (paramInt == 65535);
/*     */     
/*  38 */     boolean bool = (paramChunk.getWorld()).worldProvider.m();
/*  39 */     this.d = new byte[a(paramChunk, bool, paramInt)];
/*  40 */     this.c = a(new PacketDataSerializer(g()), paramChunk, bool, paramInt);
/*     */     
/*  42 */     this.e = Lists.newArrayList();
/*  43 */     for (Map.Entry<BlockPosition, TileEntity> entry : paramChunk.getTileEntities().entrySet()) {
/*  44 */       BlockPosition blockPosition = (BlockPosition)entry.getKey();
/*  45 */       TileEntity tileEntity = (TileEntity)entry.getValue();
/*     */       
/*  47 */       int i = blockPosition.getY() >> 4;
/*  48 */       if (!e() && (paramInt & 1 << i) == 0) {
/*     */         continue;
/*     */       }
/*     */       
/*  52 */       NBTTagCompound nBTTagCompound = tileEntity.d();
/*  53 */       this.e.add(nBTTagCompound);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/*  59 */     this.a = paramPacketDataSerializer.readInt();
/*  60 */     this.b = paramPacketDataSerializer.readInt();
/*  61 */     this.f = paramPacketDataSerializer.readBoolean();
/*  62 */     this.c = paramPacketDataSerializer.g();
/*     */     
/*  64 */     int i = paramPacketDataSerializer.g();
/*  65 */     if (i > 2097152) {
/*  66 */       throw new RuntimeException("Chunk Packet trying to allocate too much memory on read.");
/*     */     }
/*     */     
/*  69 */     this.d = new byte[i];
/*  70 */     paramPacketDataSerializer.readBytes(this.d);
/*     */     
/*  72 */     int j = paramPacketDataSerializer.g();
/*  73 */     this.e = Lists.newArrayList();
/*  74 */     for (byte b = 0; b < j; b++) {
/*  75 */       this.e.add(paramPacketDataSerializer.j());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/*  81 */     paramPacketDataSerializer.writeInt(this.a);
/*  82 */     paramPacketDataSerializer.writeInt(this.b);
/*  83 */     paramPacketDataSerializer.writeBoolean(this.f);
/*  84 */     paramPacketDataSerializer.d(this.c);
/*  85 */     paramPacketDataSerializer.d(this.d.length);
/*  86 */     paramPacketDataSerializer.writeBytes(this.d);
/*     */     
/*  88 */     paramPacketDataSerializer.d(this.e.size());
/*  89 */     for (NBTTagCompound nBTTagCompound : this.e) {
/*  90 */       paramPacketDataSerializer.a(nBTTagCompound);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(PacketListenerPlayOut paramPacketListenerPlayOut) {
/*  96 */     paramPacketListenerPlayOut.a(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private ByteBuf g() {
/* 104 */     ByteBuf byteBuf = Unpooled.wrappedBuffer(this.d);
/* 105 */     byteBuf.writerIndex(0);
/* 106 */     return byteBuf;
/*     */   }
/*     */   
/*     */   public int a(PacketDataSerializer paramPacketDataSerializer, Chunk paramChunk, boolean paramBoolean, int paramInt) {
/* 110 */     int i = 0;
/*     */     
/* 112 */     ChunkSection[] arrayOfChunkSection = paramChunk.getSections(); byte b; int j;
/* 113 */     for (b = 0, j = arrayOfChunkSection.length; b < j; b++) {
/* 114 */       ChunkSection chunkSection = arrayOfChunkSection[b];
/*     */       
/* 116 */       if (chunkSection != Chunk.a && (!e() || !chunkSection.a()) && (paramInt & 1 << b) != 0) {
/*     */ 
/*     */ 
/*     */         
/* 120 */         i |= 1 << b;
/*     */         
/* 122 */         chunkSection.getBlocks().b(paramPacketDataSerializer);
/*     */         
/* 124 */         paramPacketDataSerializer.writeBytes(chunkSection.getEmittedLightArray().asBytes());
/*     */         
/* 126 */         if (paramBoolean) {
/* 127 */           paramPacketDataSerializer.writeBytes(chunkSection.getSkyLightArray().asBytes());
/*     */         }
/*     */       } 
/*     */     } 
/* 131 */     if (e()) {
/* 132 */       paramPacketDataSerializer.writeBytes(paramChunk.getBiomeIndex());
/*     */     }
/*     */     
/* 135 */     return i;
/*     */   }
/*     */   
/*     */   protected int a(Chunk paramChunk, boolean paramBoolean, int paramInt) {
/* 139 */     int i = 0;
/*     */     
/* 141 */     ChunkSection[] arrayOfChunkSection = paramChunk.getSections(); byte b; int j;
/* 142 */     for (b = 0, j = arrayOfChunkSection.length; b < j; b++) {
/* 143 */       ChunkSection chunkSection = arrayOfChunkSection[b];
/*     */       
/* 145 */       if (chunkSection != Chunk.a && (!e() || !chunkSection.a()) && (paramInt & 1 << b) != 0) {
/*     */ 
/*     */ 
/*     */         
/* 149 */         i += chunkSection.getBlocks().a();
/* 150 */         i += (chunkSection.getEmittedLightArray().asBytes()).length;
/*     */         
/* 152 */         if (paramBoolean) {
/* 153 */           i += (chunkSection.getSkyLightArray().asBytes()).length;
/*     */         }
/*     */       } 
/*     */     } 
/* 157 */     if (e()) {
/* 158 */       i += (paramChunk.getBiomeIndex()).length;
/*     */     }
/*     */     
/* 161 */     return i;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean e() {
/* 177 */     return this.f;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\PacketPlayOutMapChunk.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */