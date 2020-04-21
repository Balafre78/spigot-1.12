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
/*     */ public class PacketPlayInAbilities
/*     */   implements Packet<PacketListenerPlayIn>
/*     */ {
/*     */   private boolean a;
/*     */   private boolean b;
/*     */   private boolean c;
/*     */   private boolean d;
/*     */   private float e;
/*     */   private float f;
/*     */   
/*     */   public PacketPlayInAbilities() {}
/*     */   
/*     */   public PacketPlayInAbilities(PlayerAbilities paramPlayerAbilities) {
/*  26 */     a(paramPlayerAbilities.isInvulnerable);
/*  27 */     b(paramPlayerAbilities.isFlying);
/*  28 */     c(paramPlayerAbilities.canFly);
/*  29 */     d(paramPlayerAbilities.canInstantlyBuild);
/*  30 */     a(paramPlayerAbilities.a());
/*  31 */     b(paramPlayerAbilities.b());
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/*  36 */     byte b = paramPacketDataSerializer.readByte();
/*     */     
/*  38 */     a(((b & 0x1) > 0));
/*  39 */     b(((b & 0x2) > 0));
/*  40 */     c(((b & 0x4) > 0));
/*  41 */     d(((b & 0x8) > 0));
/*  42 */     a(paramPacketDataSerializer.readFloat());
/*  43 */     b(paramPacketDataSerializer.readFloat());
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/*  48 */     byte b = 0;
/*     */     
/*  50 */     if (a()) {
/*  51 */       b = (byte)(b | 0x1);
/*     */     }
/*  53 */     if (isFlying()) {
/*  54 */       b = (byte)(b | 0x2);
/*     */     }
/*  56 */     if (c()) {
/*  57 */       b = (byte)(b | 0x4);
/*     */     }
/*  59 */     if (d()) {
/*  60 */       b = (byte)(b | 0x8);
/*     */     }
/*     */     
/*  63 */     paramPacketDataSerializer.writeByte(b);
/*  64 */     paramPacketDataSerializer.writeFloat(this.e);
/*  65 */     paramPacketDataSerializer.writeFloat(this.f);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(PacketListenerPlayIn paramPacketListenerPlayIn) {
/*  70 */     paramPacketListenerPlayIn.a(this);
/*     */   }
/*     */   
/*     */   public boolean a() {
/*  74 */     return this.a;
/*     */   }
/*     */   
/*     */   public void a(boolean paramBoolean) {
/*  78 */     this.a = paramBoolean;
/*     */   }
/*     */   
/*     */   public boolean isFlying() {
/*  82 */     return this.b;
/*     */   }
/*     */   
/*     */   public void b(boolean paramBoolean) {
/*  86 */     this.b = paramBoolean;
/*     */   }
/*     */   
/*     */   public boolean c() {
/*  90 */     return this.c;
/*     */   }
/*     */   
/*     */   public void c(boolean paramBoolean) {
/*  94 */     this.c = paramBoolean;
/*     */   }
/*     */   
/*     */   public boolean d() {
/*  98 */     return this.d;
/*     */   }
/*     */   
/*     */   public void d(boolean paramBoolean) {
/* 102 */     this.d = paramBoolean;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(float paramFloat) {
/* 110 */     this.e = paramFloat;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void b(float paramFloat) {
/* 118 */     this.f = paramFloat;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\PacketPlayInAbilities.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */