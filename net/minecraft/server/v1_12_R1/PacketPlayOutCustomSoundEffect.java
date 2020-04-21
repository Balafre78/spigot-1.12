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
/*    */ public class PacketPlayOutCustomSoundEffect
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private String a;
/*    */   private SoundCategory b;
/*    */   private int c;
/* 18 */   private int d = Integer.MAX_VALUE;
/*    */   
/*    */   private int e;
/*    */   
/*    */   private float f;
/*    */   
/*    */   private float g;
/*    */ 
/*    */   
/*    */   public PacketPlayOutCustomSoundEffect(String paramString, SoundCategory paramSoundCategory, double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat1, float paramFloat2) {
/* 28 */     Validate.notNull(paramString, "name", new Object[0]);
/* 29 */     this.a = paramString;
/* 30 */     this.b = paramSoundCategory;
/* 31 */     this.c = (int)(paramDouble1 * 8.0D);
/* 32 */     this.d = (int)(paramDouble2 * 8.0D);
/* 33 */     this.e = (int)(paramDouble3 * 8.0D);
/* 34 */     this.f = paramFloat1;
/* 35 */     this.g = paramFloat2;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 40 */     this.a = paramPacketDataSerializer.e(256);
/* 41 */     this.b = paramPacketDataSerializer.<SoundCategory>a(SoundCategory.class);
/* 42 */     this.c = paramPacketDataSerializer.readInt();
/* 43 */     this.d = paramPacketDataSerializer.readInt();
/* 44 */     this.e = paramPacketDataSerializer.readInt();
/* 45 */     this.f = paramPacketDataSerializer.readFloat();
/* 46 */     this.g = paramPacketDataSerializer.readFloat();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 51 */     paramPacketDataSerializer.a(this.a);
/* 52 */     paramPacketDataSerializer.a(this.b);
/* 53 */     paramPacketDataSerializer.writeInt(this.c);
/* 54 */     paramPacketDataSerializer.writeInt(this.d);
/* 55 */     paramPacketDataSerializer.writeInt(this.e);
/* 56 */     paramPacketDataSerializer.writeFloat(this.f);
/* 57 */     paramPacketDataSerializer.writeFloat(this.g);
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
/* 90 */     paramPacketListenerPlayOut.a(this);
/*    */   }
/*    */   
/*    */   public PacketPlayOutCustomSoundEffect() {}
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\PacketPlayOutCustomSoundEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */