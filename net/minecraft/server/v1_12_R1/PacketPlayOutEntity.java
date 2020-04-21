/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.io.IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PacketPlayOutEntity
/*     */   implements Packet<PacketListenerPlayOut>
/*     */ {
/*     */   protected int a;
/*     */   protected int b;
/*     */   protected int c;
/*     */   protected int d;
/*     */   protected byte e;
/*     */   protected byte f;
/*     */   protected boolean g;
/*     */   protected boolean h;
/*     */   
/*     */   public static class PacketPlayOutRelEntityMoveLook
/*     */     extends PacketPlayOutEntity
/*     */   {
/*     */     public PacketPlayOutRelEntityMoveLook() {
/*  28 */       this.h = true;
/*     */     }
/*     */     
/*     */     public PacketPlayOutRelEntityMoveLook(int param1Int, long param1Long1, long param1Long2, long param1Long3, byte param1Byte1, byte param1Byte2, boolean param1Boolean) {
/*  32 */       super(param1Int);
/*     */ 
/*     */       
/*  35 */       this.b = (int)param1Long1;
/*  36 */       this.c = (int)param1Long2;
/*  37 */       this.d = (int)param1Long3;
/*  38 */       this.e = param1Byte1;
/*  39 */       this.f = param1Byte2;
/*  40 */       this.g = param1Boolean;
/*  41 */       this.h = true;
/*     */     }
/*     */ 
/*     */     
/*     */     public void a(PacketDataSerializer param1PacketDataSerializer) throws IOException {
/*  46 */       super.a(param1PacketDataSerializer);
/*  47 */       this.b = param1PacketDataSerializer.readShort();
/*  48 */       this.c = param1PacketDataSerializer.readShort();
/*  49 */       this.d = param1PacketDataSerializer.readShort();
/*  50 */       this.e = param1PacketDataSerializer.readByte();
/*  51 */       this.f = param1PacketDataSerializer.readByte();
/*  52 */       this.g = param1PacketDataSerializer.readBoolean();
/*     */     }
/*     */ 
/*     */     
/*     */     public void b(PacketDataSerializer param1PacketDataSerializer) throws IOException {
/*  57 */       super.b(param1PacketDataSerializer);
/*  58 */       param1PacketDataSerializer.writeShort(this.b);
/*  59 */       param1PacketDataSerializer.writeShort(this.c);
/*  60 */       param1PacketDataSerializer.writeShort(this.d);
/*  61 */       param1PacketDataSerializer.writeByte(this.e);
/*  62 */       param1PacketDataSerializer.writeByte(this.f);
/*  63 */       param1PacketDataSerializer.writeBoolean(this.g);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class PacketPlayOutRelEntityMove
/*     */     extends PacketPlayOutEntity
/*     */   {
/*     */     public PacketPlayOutRelEntityMove() {}
/*     */     
/*     */     public PacketPlayOutRelEntityMove(int param1Int, long param1Long1, long param1Long2, long param1Long3, boolean param1Boolean) {
/*  73 */       super(param1Int);
/*     */ 
/*     */       
/*  76 */       this.b = (int)param1Long1;
/*  77 */       this.c = (int)param1Long2;
/*  78 */       this.d = (int)param1Long3;
/*  79 */       this.g = param1Boolean;
/*     */     }
/*     */ 
/*     */     
/*     */     public void a(PacketDataSerializer param1PacketDataSerializer) throws IOException {
/*  84 */       super.a(param1PacketDataSerializer);
/*  85 */       this.b = param1PacketDataSerializer.readShort();
/*  86 */       this.c = param1PacketDataSerializer.readShort();
/*  87 */       this.d = param1PacketDataSerializer.readShort();
/*  88 */       this.g = param1PacketDataSerializer.readBoolean();
/*     */     }
/*     */ 
/*     */     
/*     */     public void b(PacketDataSerializer param1PacketDataSerializer) throws IOException {
/*  93 */       super.b(param1PacketDataSerializer);
/*  94 */       param1PacketDataSerializer.writeShort(this.b);
/*  95 */       param1PacketDataSerializer.writeShort(this.c);
/*  96 */       param1PacketDataSerializer.writeShort(this.d);
/*  97 */       param1PacketDataSerializer.writeBoolean(this.g);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class PacketPlayOutEntityLook
/*     */     extends PacketPlayOutEntity {
/*     */     public PacketPlayOutEntityLook() {
/* 104 */       this.h = true;
/*     */     }
/*     */     
/*     */     public PacketPlayOutEntityLook(int param1Int, byte param1Byte1, byte param1Byte2, boolean param1Boolean) {
/* 108 */       super(param1Int);
/* 109 */       this.e = param1Byte1;
/* 110 */       this.f = param1Byte2;
/* 111 */       this.h = true;
/* 112 */       this.g = param1Boolean;
/*     */     }
/*     */ 
/*     */     
/*     */     public void a(PacketDataSerializer param1PacketDataSerializer) throws IOException {
/* 117 */       super.a(param1PacketDataSerializer);
/* 118 */       this.e = param1PacketDataSerializer.readByte();
/* 119 */       this.f = param1PacketDataSerializer.readByte();
/* 120 */       this.g = param1PacketDataSerializer.readBoolean();
/*     */     }
/*     */ 
/*     */     
/*     */     public void b(PacketDataSerializer param1PacketDataSerializer) throws IOException {
/* 125 */       super.b(param1PacketDataSerializer);
/* 126 */       param1PacketDataSerializer.writeByte(this.e);
/* 127 */       param1PacketDataSerializer.writeByte(this.f);
/* 128 */       param1PacketDataSerializer.writeBoolean(this.g);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public PacketPlayOutEntity() {}
/*     */ 
/*     */   
/*     */   public PacketPlayOutEntity(int paramInt) {
/* 137 */     this.a = paramInt;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 142 */     this.a = paramPacketDataSerializer.g();
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 147 */     paramPacketDataSerializer.d(this.a);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(PacketListenerPlayOut paramPacketListenerPlayOut) {
/* 152 */     paramPacketListenerPlayOut.a(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 157 */     return "Entity_" + super.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\PacketPlayOutEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */