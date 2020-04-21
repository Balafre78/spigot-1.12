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
/*     */ public class PacketPlayInFlying
/*     */   implements Packet<PacketListenerPlayIn>
/*     */ {
/*     */   protected double x;
/*     */   protected double y;
/*     */   protected double z;
/*     */   protected float yaw;
/*     */   protected float pitch;
/*     */   protected boolean f;
/*     */   protected boolean hasPos;
/*     */   protected boolean hasLook;
/*     */   
/*     */   public static class PacketPlayInPositionLook
/*     */     extends PacketPlayInFlying
/*     */   {
/*     */     public void a(PacketDataSerializer param1PacketDataSerializer) throws IOException {
/*  38 */       this.x = param1PacketDataSerializer.readDouble();
/*  39 */       this.y = param1PacketDataSerializer.readDouble();
/*  40 */       this.z = param1PacketDataSerializer.readDouble();
/*  41 */       this.yaw = param1PacketDataSerializer.readFloat();
/*  42 */       this.pitch = param1PacketDataSerializer.readFloat();
/*  43 */       super.a(param1PacketDataSerializer);
/*     */     }
/*     */ 
/*     */     
/*     */     public void b(PacketDataSerializer param1PacketDataSerializer) throws IOException {
/*  48 */       param1PacketDataSerializer.writeDouble(this.x);
/*  49 */       param1PacketDataSerializer.writeDouble(this.y);
/*  50 */       param1PacketDataSerializer.writeDouble(this.z);
/*  51 */       param1PacketDataSerializer.writeFloat(this.yaw);
/*  52 */       param1PacketDataSerializer.writeFloat(this.pitch);
/*  53 */       super.b(param1PacketDataSerializer);
/*     */     }
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
/*     */   public static class PacketPlayInPosition
/*     */     extends PacketPlayInFlying
/*     */   {
/*     */     public void a(PacketDataSerializer param1PacketDataSerializer) throws IOException {
/*  73 */       this.x = param1PacketDataSerializer.readDouble();
/*  74 */       this.y = param1PacketDataSerializer.readDouble();
/*  75 */       this.z = param1PacketDataSerializer.readDouble();
/*  76 */       super.a(param1PacketDataSerializer);
/*     */     }
/*     */ 
/*     */     
/*     */     public void b(PacketDataSerializer param1PacketDataSerializer) throws IOException {
/*  81 */       param1PacketDataSerializer.writeDouble(this.x);
/*  82 */       param1PacketDataSerializer.writeDouble(this.y);
/*  83 */       param1PacketDataSerializer.writeDouble(this.z);
/*  84 */       super.b(param1PacketDataSerializer);
/*     */     }
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
/*     */   public static class PacketPlayInLook
/*     */     extends PacketPlayInFlying
/*     */   {
/*     */     public void a(PacketDataSerializer param1PacketDataSerializer) throws IOException {
/* 103 */       this.yaw = param1PacketDataSerializer.readFloat();
/* 104 */       this.pitch = param1PacketDataSerializer.readFloat();
/* 105 */       super.a(param1PacketDataSerializer);
/*     */     }
/*     */ 
/*     */     
/*     */     public void b(PacketDataSerializer param1PacketDataSerializer) throws IOException {
/* 110 */       param1PacketDataSerializer.writeFloat(this.yaw);
/* 111 */       param1PacketDataSerializer.writeFloat(this.pitch);
/* 112 */       super.b(param1PacketDataSerializer);
/*     */     }
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
/*     */   public void a(PacketListenerPlayIn paramPacketListenerPlayIn) {
/* 126 */     paramPacketListenerPlayIn.a(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 131 */     this.f = (paramPacketDataSerializer.readUnsignedByte() != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 136 */     paramPacketDataSerializer.writeByte(this.f ? 1 : 0);
/*     */   }
/*     */   
/*     */   public double a(double paramDouble) {
/* 140 */     return this.hasPos ? this.x : paramDouble;
/*     */   }
/*     */   
/*     */   public double b(double paramDouble) {
/* 144 */     return this.hasPos ? this.y : paramDouble;
/*     */   }
/*     */   
/*     */   public double c(double paramDouble) {
/* 148 */     return this.hasPos ? this.z : paramDouble;
/*     */   }
/*     */   
/*     */   public float a(float paramFloat) {
/* 152 */     return this.hasLook ? this.yaw : paramFloat;
/*     */   }
/*     */   
/*     */   public float b(float paramFloat) {
/* 156 */     return this.hasLook ? this.pitch : paramFloat;
/*     */   }
/*     */   
/*     */   public boolean a() {
/* 160 */     return this.f;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\PacketPlayInFlying.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */