/*     */ package net.minecraft.server.v1_12_R1;
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
/*     */ public class DemoPlayerInteractManager
/*     */   extends PlayerInteractManager
/*     */ {
/*     */   private boolean c;
/*     */   private boolean d;
/*     */   private int e;
/*     */   private int f;
/*     */   
/*     */   public DemoPlayerInteractManager(World paramWorld) {
/*  24 */     super(paramWorld);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a() {
/*  29 */     super.a();
/*  30 */     this.f++;
/*     */     
/*  32 */     long l1 = this.world.getTime();
/*  33 */     long l2 = l1 / 24000L + 1L;
/*     */     
/*  35 */     if (!this.c && this.f > 20) {
/*  36 */       this.c = true;
/*  37 */       this.player.playerConnection.sendPacket(new PacketPlayOutGameStateChange(5, 0.0F));
/*     */     } 
/*     */     
/*  40 */     this.d = (l1 > 120500L);
/*  41 */     if (this.d) {
/*  42 */       this.e++;
/*     */     }
/*     */     
/*  45 */     if (l1 % 24000L == 500L) {
/*  46 */       if (l2 <= 6L) {
/*  47 */         this.player.sendMessage(new ChatMessage("demo.day." + l2, new Object[0]));
/*     */       }
/*  49 */     } else if (l2 == 1L) {
/*  50 */       if (l1 == 100L) {
/*  51 */         this.player.playerConnection.sendPacket(new PacketPlayOutGameStateChange(5, 101.0F));
/*  52 */       } else if (l1 == 175L) {
/*  53 */         this.player.playerConnection.sendPacket(new PacketPlayOutGameStateChange(5, 102.0F));
/*  54 */       } else if (l1 == 250L) {
/*  55 */         this.player.playerConnection.sendPacket(new PacketPlayOutGameStateChange(5, 103.0F));
/*     */       } 
/*  57 */     } else if (l2 == 5L && 
/*  58 */       l1 % 24000L == 22000L) {
/*  59 */       this.player.sendMessage(new ChatMessage("demo.day.warning", new Object[0]));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void f() {
/*  65 */     if (this.e > 100) {
/*  66 */       this.player.sendMessage(new ChatMessage("demo.reminder", new Object[0]));
/*  67 */       this.e = 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(BlockPosition paramBlockPosition, EnumDirection paramEnumDirection) {
/*  73 */     if (this.d) {
/*  74 */       f();
/*     */       return;
/*     */     } 
/*  77 */     super.a(paramBlockPosition, paramEnumDirection);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(BlockPosition paramBlockPosition) {
/*  82 */     if (this.d) {
/*     */       return;
/*     */     }
/*  85 */     super.a(paramBlockPosition);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean breakBlock(BlockPosition paramBlockPosition) {
/*  90 */     if (this.d) {
/*  91 */       return false;
/*     */     }
/*  93 */     return super.breakBlock(paramBlockPosition);
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumInteractionResult a(EntityHuman paramEntityHuman, World paramWorld, ItemStack paramItemStack, EnumHand paramEnumHand) {
/*  98 */     if (this.d) {
/*  99 */       f();
/* 100 */       return EnumInteractionResult.PASS;
/*     */     } 
/* 102 */     return super.a(paramEntityHuman, paramWorld, paramItemStack, paramEnumHand);
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumInteractionResult a(EntityHuman paramEntityHuman, World paramWorld, ItemStack paramItemStack, EnumHand paramEnumHand, BlockPosition paramBlockPosition, EnumDirection paramEnumDirection, float paramFloat1, float paramFloat2, float paramFloat3) {
/* 107 */     if (this.d) {
/* 108 */       f();
/* 109 */       return EnumInteractionResult.PASS;
/*     */     } 
/* 111 */     return super.a(paramEntityHuman, paramWorld, paramItemStack, paramEnumHand, paramBlockPosition, paramEnumDirection, paramFloat1, paramFloat2, paramFloat3);
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DemoPlayerInteractManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */