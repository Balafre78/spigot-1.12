/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ public class PacketPlayInRecipeDisplayed implements Packet<PacketListenerPlayIn> {
/*    */   private Status a;
/*    */   private IRecipe b;
/*    */   private boolean c;
/*    */   private boolean d;
/*    */   
/*    */   public enum Status {
/* 12 */     SHOWN, SETTINGS;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public PacketPlayInRecipeDisplayed() {}
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public PacketPlayInRecipeDisplayed(IRecipe paramIRecipe) {
/* 26 */     this.a = Status.SHOWN;
/* 27 */     this.b = paramIRecipe;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 38 */     this.a = paramPacketDataSerializer.<Status>a(Status.class);
/* 39 */     if (this.a == Status.SHOWN) {
/* 40 */       this.b = CraftingManager.a(paramPacketDataSerializer.readInt());
/* 41 */     } else if (this.a == Status.SETTINGS) {
/* 42 */       this.c = paramPacketDataSerializer.readBoolean();
/* 43 */       this.d = paramPacketDataSerializer.readBoolean();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 49 */     paramPacketDataSerializer.a(this.a);
/*    */     
/* 51 */     if (this.a == Status.SHOWN) {
/* 52 */       paramPacketDataSerializer.writeInt(CraftingManager.a(this.b));
/* 53 */     } else if (this.a == Status.SETTINGS) {
/* 54 */       paramPacketDataSerializer.writeBoolean(this.c);
/* 55 */       paramPacketDataSerializer.writeBoolean(this.d);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayIn paramPacketListenerPlayIn) {
/* 61 */     paramPacketListenerPlayIn.a(this);
/*    */   }
/*    */   
/*    */   public Status a() {
/* 65 */     return this.a;
/*    */   }
/*    */   
/*    */   public IRecipe b() {
/* 69 */     return this.b;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 73 */     return this.c;
/*    */   }
/*    */   
/*    */   public boolean d() {
/* 77 */     return this.d;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\PacketPlayInRecipeDisplayed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */