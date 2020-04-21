/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayInUseEntity
/*    */   implements Packet<PacketListenerPlayIn>
/*    */ {
/*    */   private int a;
/*    */   private EnumEntityUseAction action;
/*    */   private Vec3D c;
/*    */   private EnumHand d;
/*    */   
/*    */   public PacketPlayInUseEntity() {}
/*    */   
/*    */   public PacketPlayInUseEntity(Entity paramEntity) {
/* 24 */     this.a = paramEntity.getId();
/* 25 */     this.action = EnumEntityUseAction.ATTACK;
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
/*    */   public void a(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 43 */     this.a = paramPacketDataSerializer.g();
/* 44 */     this.action = paramPacketDataSerializer.<EnumEntityUseAction>a(EnumEntityUseAction.class);
/* 45 */     if (this.action == EnumEntityUseAction.INTERACT_AT) {
/* 46 */       this.c = new Vec3D(paramPacketDataSerializer.readFloat(), paramPacketDataSerializer.readFloat(), paramPacketDataSerializer.readFloat());
/*    */     }
/* 48 */     if (this.action == EnumEntityUseAction.INTERACT || this.action == EnumEntityUseAction.INTERACT_AT) {
/* 49 */       this.d = paramPacketDataSerializer.<EnumHand>a(EnumHand.class);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 55 */     paramPacketDataSerializer.d(this.a);
/* 56 */     paramPacketDataSerializer.a(this.action);
/* 57 */     if (this.action == EnumEntityUseAction.INTERACT_AT) {
/* 58 */       paramPacketDataSerializer.writeFloat((float)this.c.x);
/* 59 */       paramPacketDataSerializer.writeFloat((float)this.c.y);
/* 60 */       paramPacketDataSerializer.writeFloat((float)this.c.z);
/*    */     } 
/* 62 */     if (this.action == EnumEntityUseAction.INTERACT || this.action == EnumEntityUseAction.INTERACT_AT) {
/* 63 */       paramPacketDataSerializer.a(this.d);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayIn paramPacketListenerPlayIn) {
/* 69 */     paramPacketListenerPlayIn.a(this);
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public Entity a(World paramWorld) {
/* 74 */     return paramWorld.getEntity(this.a);
/*    */   }
/*    */   
/*    */   public EnumEntityUseAction a() {
/* 78 */     return this.action;
/*    */   }
/*    */   
/*    */   public EnumHand b() {
/* 82 */     return this.d;
/*    */   }
/*    */   
/*    */   public Vec3D c() {
/* 86 */     return this.c;
/*    */   }
/*    */   
/*    */   public enum EnumEntityUseAction {
/* 90 */     INTERACT,
/* 91 */     ATTACK,
/* 92 */     INTERACT_AT;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\PacketPlayInUseEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */