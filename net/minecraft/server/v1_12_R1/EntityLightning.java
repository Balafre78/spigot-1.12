/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.List;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*     */ 
/*     */ public class EntityLightning
/*     */   extends EntityWeather
/*     */ {
/*     */   private int lifeTicks;
/*     */   public long a;
/*     */   private int c;
/*     */   private final boolean d;
/*     */   public boolean isEffect;
/*     */   public boolean isSilent = false;
/*     */   
/*     */   public EntityLightning(World world, double d0, double d1, double d2, boolean flag) {
/*  17 */     super(world);
/*  18 */     this.isEffect = flag;
/*  19 */     setPositionRotation(d0, d1, d2, 0.0F, 0.0F);
/*  20 */     this.lifeTicks = 2;
/*  21 */     this.a = this.random.nextLong();
/*  22 */     this.c = this.random.nextInt(3) + 1;
/*  23 */     this.d = flag;
/*  24 */     BlockPosition blockposition = new BlockPosition(this);
/*     */     
/*  26 */     if (!flag && !world.isClientSide && world.getGameRules().getBoolean("doFireTick") && (world.getDifficulty() == EnumDifficulty.NORMAL || world.getDifficulty() == EnumDifficulty.HARD) && world.areChunksLoaded(blockposition, 10)) {
/*  27 */       if (world.getType(blockposition).getMaterial() == Material.AIR && Blocks.FIRE.canPlace(world, blockposition))
/*     */       {
/*  29 */         if (!CraftEventFactory.callBlockIgniteEvent(world, blockposition.getX(), blockposition.getY(), blockposition.getZ(), this).isCancelled()) {
/*  30 */           world.setTypeUpdate(blockposition, Blocks.FIRE.getBlockData());
/*     */         }
/*     */       }
/*     */ 
/*     */       
/*  35 */       for (int i = 0; i < 4; i++) {
/*  36 */         BlockPosition blockposition1 = blockposition.a(this.random.nextInt(3) - 1, this.random.nextInt(3) - 1, this.random.nextInt(3) - 1);
/*     */         
/*  38 */         if (world.getType(blockposition1).getMaterial() == Material.AIR && Blocks.FIRE.canPlace(world, blockposition1))
/*     */         {
/*  40 */           if (!CraftEventFactory.callBlockIgniteEvent(world, blockposition1.getX(), blockposition1.getY(), blockposition1.getZ(), this).isCancelled()) {
/*  41 */             world.setTypeUpdate(blockposition1, Blocks.FIRE.getBlockData());
/*     */           }
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityLightning(World world, double d0, double d1, double d2, boolean isEffect, boolean isSilent) {
/*  53 */     this(world, d0, d1, d2, isEffect);
/*  54 */     this.isSilent = isSilent;
/*     */   }
/*     */ 
/*     */   
/*     */   public SoundCategory bK() {
/*  59 */     return SoundCategory.WEATHER;
/*     */   }
/*     */   
/*     */   public void B_() {
/*  63 */     super.B_();
/*  64 */     if (!this.isSilent && this.lifeTicks == 2) {
/*     */ 
/*     */       
/*  67 */       float pitch = 0.8F + this.random.nextFloat() * 0.2F;
/*  68 */       int viewDistance = ((WorldServer)this.world).getServer().getViewDistance() * 16;
/*  69 */       for (EntityPlayer player : this.world.players) {
/*  70 */         double deltaX = this.locX - player.locX;
/*  71 */         double deltaZ = this.locZ - player.locZ;
/*  72 */         double distanceSquared = deltaX * deltaX + deltaZ * deltaZ;
/*  73 */         if (distanceSquared > (viewDistance * viewDistance)) {
/*  74 */           double deltaLength = Math.sqrt(distanceSquared);
/*  75 */           double relativeX = player.locX + deltaX / deltaLength * viewDistance;
/*  76 */           double relativeZ = player.locZ + deltaZ / deltaLength * viewDistance;
/*  77 */           player.playerConnection.sendPacket(new PacketPlayOutNamedSoundEffect(SoundEffects.dK, SoundCategory.WEATHER, relativeX, this.locY, relativeZ, 10000.0F, pitch)); continue;
/*     */         } 
/*  79 */         player.playerConnection.sendPacket(new PacketPlayOutNamedSoundEffect(SoundEffects.dK, SoundCategory.WEATHER, this.locX, this.locY, this.locZ, 10000.0F, pitch));
/*     */       } 
/*     */ 
/*     */       
/*  83 */       this.world.a((EntityHuman)null, this.locX, this.locY, this.locZ, SoundEffects.dJ, SoundCategory.WEATHER, 2.0F, 0.5F + this.random.nextFloat() * 0.2F);
/*     */     } 
/*     */     
/*  86 */     this.lifeTicks--;
/*  87 */     if (this.lifeTicks < 0) {
/*  88 */       if (this.c == 0) {
/*  89 */         die();
/*  90 */       } else if (this.lifeTicks < -this.random.nextInt(10)) {
/*  91 */         this.c--;
/*  92 */         this.lifeTicks = 1;
/*  93 */         if (!this.d && !this.world.isClientSide) {
/*  94 */           this.a = this.random.nextLong();
/*  95 */           BlockPosition blockposition = new BlockPosition(this);
/*     */           
/*  97 */           if (this.world.getGameRules().getBoolean("doFireTick") && this.world.areChunksLoaded(blockposition, 10) && this.world.getType(blockposition).getMaterial() == Material.AIR && Blocks.FIRE.canPlace(this.world, blockposition))
/*     */           {
/*  99 */             if (!this.isEffect && !CraftEventFactory.callBlockIgniteEvent(this.world, blockposition.getX(), blockposition.getY(), blockposition.getZ(), this).isCancelled()) {
/* 100 */               this.world.setTypeUpdate(blockposition, Blocks.FIRE.getBlockData());
/*     */             }
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 108 */     if (this.lifeTicks >= 0 && !this.isEffect)
/* 109 */       if (this.world.isClientSide) {
/* 110 */         this.world.d(2);
/* 111 */       } else if (!this.d) {
/*     */         
/* 113 */         List<Entity> list = this.world.getEntities(this, new AxisAlignedBB(this.locX - 3.0D, this.locY - 3.0D, this.locZ - 3.0D, this.locX + 3.0D, this.locY + 6.0D + 3.0D, this.locZ + 3.0D));
/*     */         
/* 115 */         for (int i = 0; i < list.size(); i++) {
/* 116 */           Entity entity = list.get(i);
/*     */           
/* 118 */           entity.onLightningStrike(this);
/*     */         } 
/*     */       }  
/*     */   }
/*     */   
/*     */   protected void i() {}
/*     */   
/*     */   protected void a(NBTTagCompound nbttagcompound) {}
/*     */   
/*     */   protected void b(NBTTagCompound nbttagcompound) {}
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityLightning.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */