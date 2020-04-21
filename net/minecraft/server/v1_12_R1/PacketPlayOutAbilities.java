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
/*     */ public class PacketPlayOutAbilities
/*     */   implements Packet<PacketListenerPlayOut>
/*     */ {
/*     */   private boolean a;
/*     */   private boolean b;
/*     */   private boolean c;
/*     */   private boolean d;
/*     */   private float e;
/*     */   private float f;
/*     */   
/*     */   public PacketPlayOutAbilities() {}
/*     */   
/*     */   public PacketPlayOutAbilities(PlayerAbilities paramPlayerAbilities) {
/*  28 */     a(paramPlayerAbilities.isInvulnerable);
/*  29 */     b(paramPlayerAbilities.isFlying);
/*  30 */     c(paramPlayerAbilities.canFly);
/*  31 */     d(paramPlayerAbilities.canInstantlyBuild);
/*  32 */     a(paramPlayerAbilities.a());
/*  33 */     b(paramPlayerAbilities.b());
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/*  38 */     byte b = paramPacketDataSerializer.readByte();
/*     */     
/*  40 */     a(((b & 0x1) > 0));
/*  41 */     b(((b & 0x2) > 0));
/*  42 */     c(((b & 0x4) > 0));
/*  43 */     d(((b & 0x8) > 0));
/*  44 */     a(paramPacketDataSerializer.readFloat());
/*  45 */     b(paramPacketDataSerializer.readFloat());
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/*  50 */     byte b = 0;
/*     */     
/*  52 */     if (a()) {
/*  53 */       b = (byte)(b | 0x1);
/*     */     }
/*  55 */     if (b()) {
/*  56 */       b = (byte)(b | 0x2);
/*     */     }
/*  58 */     if (c()) {
/*  59 */       b = (byte)(b | 0x4);
/*     */     }
/*  61 */     if (d()) {
/*  62 */       b = (byte)(b | 0x8);
/*     */     }
/*     */     
/*  65 */     paramPacketDataSerializer.writeByte(b);
/*  66 */     paramPacketDataSerializer.writeFloat(this.e);
/*  67 */     paramPacketDataSerializer.writeFloat(this.f);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(PacketListenerPlayOut paramPacketListenerPlayOut) {
/*  72 */     paramPacketListenerPlayOut.a(this);
/*     */   }
/*     */   
/*     */   public boolean a() {
/*  76 */     return this.a;
/*     */   }
/*     */   
/*     */   public void a(boolean paramBoolean) {
/*  80 */     this.a = paramBoolean;
/*     */   }
/*     */   
/*     */   public boolean b() {
/*  84 */     return this.b;
/*     */   }
/*     */   
/*     */   public void b(boolean paramBoolean) {
/*  88 */     this.b = paramBoolean;
/*     */   }
/*     */   
/*     */   public boolean c() {
/*  92 */     return this.c;
/*     */   }
/*     */   
/*     */   public void c(boolean paramBoolean) {
/*  96 */     this.c = paramBoolean;
/*     */   }
/*     */   
/*     */   public boolean d() {
/* 100 */     return this.d;
/*     */   }
/*     */   
/*     */   public void d(boolean paramBoolean) {
/* 104 */     this.d = paramBoolean;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(float paramFloat) {
/* 112 */     this.e = paramFloat;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void b(float paramFloat) {
/* 120 */     this.f = paramFloat;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\PacketPlayOutAbilities.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */