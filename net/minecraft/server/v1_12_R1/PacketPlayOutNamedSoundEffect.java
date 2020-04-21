/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import org.apache.commons.lang3.Validate;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayOutNamedSoundEffect
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private SoundEffect a;
/*    */   private SoundCategory b;
/*    */   private int c;
/*    */   private int d;
/*    */   private int e;
/*    */   private float f;
/*    */   private float g;
/*    */   
/*    */   public PacketPlayOutNamedSoundEffect() {}
/*    */   
/*    */   public PacketPlayOutNamedSoundEffect(SoundEffect paramSoundEffect, SoundCategory paramSoundCategory, double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat1, float paramFloat2) {
/* 27 */     Validate.notNull(paramSoundEffect, "sound", new Object[0]);
/* 28 */     this.a = paramSoundEffect;
/* 29 */     this.b = paramSoundCategory;
/* 30 */     this.c = (int)(paramDouble1 * 8.0D);
/* 31 */     this.d = (int)(paramDouble2 * 8.0D);
/* 32 */     this.e = (int)(paramDouble3 * 8.0D);
/* 33 */     this.f = paramFloat1;
/* 34 */     this.g = paramFloat2;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 39 */     this.a = SoundEffect.a.getId(paramPacketDataSerializer.g());
/* 40 */     this.b = paramPacketDataSerializer.<SoundCategory>a(SoundCategory.class);
/* 41 */     this.c = paramPacketDataSerializer.readInt();
/* 42 */     this.d = paramPacketDataSerializer.readInt();
/* 43 */     this.e = paramPacketDataSerializer.readInt();
/* 44 */     this.f = paramPacketDataSerializer.readFloat();
/* 45 */     this.g = paramPacketDataSerializer.readFloat();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 50 */     paramPacketDataSerializer.d(SoundEffect.a.a(this.a));
/* 51 */     paramPacketDataSerializer.a(this.b);
/* 52 */     paramPacketDataSerializer.writeInt(this.c);
/* 53 */     paramPacketDataSerializer.writeInt(this.d);
/* 54 */     paramPacketDataSerializer.writeInt(this.e);
/* 55 */     paramPacketDataSerializer.writeFloat(this.f);
/* 56 */     paramPacketDataSerializer.writeFloat(this.g);
/*    */   }
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
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut paramPacketListenerPlayOut) {
/* 89 */     paramPacketListenerPlayOut.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\PacketPlayOutNamedSoundEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */