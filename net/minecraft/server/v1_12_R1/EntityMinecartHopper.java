/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.List;
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
/*     */ public class EntityMinecartHopper
/*     */   extends EntityMinecartContainer
/*     */   implements IHopper
/*     */ {
/*     */   private boolean a = true;
/*  27 */   private int b = -1;
/*  28 */   private final BlockPosition c = BlockPosition.ZERO;
/*     */   
/*     */   public EntityMinecartHopper(World paramWorld) {
/*  31 */     super(paramWorld);
/*     */   }
/*     */   
/*     */   public EntityMinecartHopper(World paramWorld, double paramDouble1, double paramDouble2, double paramDouble3) {
/*  35 */     super(paramWorld, paramDouble1, paramDouble2, paramDouble3);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityMinecartAbstract.EnumMinecartType v() {
/*  40 */     return EntityMinecartAbstract.EnumMinecartType.HOPPER;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData x() {
/*  45 */     return Blocks.HOPPER.getBlockData();
/*     */   }
/*     */ 
/*     */   
/*     */   public int z() {
/*  50 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSize() {
/*  55 */     return 5;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b(EntityHuman paramEntityHuman, EnumHand paramEnumHand) {
/*  60 */     if (!this.world.isClientSide) {
/*  61 */       paramEntityHuman.openContainer(this);
/*     */     }
/*     */     
/*  64 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean) {
/*  69 */     boolean bool = !paramBoolean;
/*     */     
/*  71 */     if (bool != isEnabled()) {
/*  72 */       setEnabled(bool);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isEnabled() {
/*  77 */     return this.a;
/*     */   }
/*     */   
/*     */   public void setEnabled(boolean paramBoolean) {
/*  81 */     this.a = paramBoolean;
/*     */   }
/*     */ 
/*     */   
/*     */   public World getWorld() {
/*  86 */     return this.world;
/*     */   }
/*     */ 
/*     */   
/*     */   public double E() {
/*  91 */     return this.locX;
/*     */   }
/*     */ 
/*     */   
/*     */   public double F() {
/*  96 */     return this.locY + 0.5D;
/*     */   }
/*     */ 
/*     */   
/*     */   public double G() {
/* 101 */     return this.locZ;
/*     */   }
/*     */ 
/*     */   
/*     */   public void B_() {
/* 106 */     super.B_();
/*     */     
/* 108 */     if (!this.world.isClientSide && isAlive() && isEnabled()) {
/* 109 */       BlockPosition blockPosition = new BlockPosition(this);
/* 110 */       if (blockPosition.equals(this.c)) {
/* 111 */         this.b--;
/*     */       } else {
/* 113 */         setCooldown(0);
/*     */       } 
/*     */       
/* 116 */       if (!J()) {
/* 117 */         setCooldown(0);
/*     */         
/* 119 */         if (H()) {
/* 120 */           setCooldown(4);
/* 121 */           update();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean H() {
/* 128 */     if (TileEntityHopper.a(this)) {
/* 129 */       return true;
/*     */     }
/*     */     
/* 132 */     List<Entity> list = this.world.a((Class)EntityItem.class, getBoundingBox().grow(0.25D, 0.0D, 0.25D), IEntitySelector.a);
/*     */     
/* 134 */     if (!list.isEmpty()) {
/* 135 */       TileEntityHopper.a(null, this, (EntityItem)list.get(0));
/*     */     }
/*     */     
/* 138 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(DamageSource paramDamageSource) {
/* 143 */     super.a(paramDamageSource);
/*     */     
/* 145 */     if (this.world.getGameRules().getBoolean("doEntityDrops")) {
/* 146 */       a(Item.getItemOf(Blocks.HOPPER), 1, 0.0F);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void a(DataConverterManager paramDataConverterManager) {
/* 151 */     EntityMinecartContainer.b(paramDataConverterManager, EntityMinecartHopper.class);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void b(NBTTagCompound paramNBTTagCompound) {
/* 156 */     super.b(paramNBTTagCompound);
/* 157 */     paramNBTTagCompound.setInt("TransferCooldown", this.b);
/* 158 */     paramNBTTagCompound.setBoolean("Enabled", this.a);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(NBTTagCompound paramNBTTagCompound) {
/* 163 */     super.a(paramNBTTagCompound);
/* 164 */     this.b = paramNBTTagCompound.getInt("TransferCooldown");
/* 165 */     this.a = paramNBTTagCompound.hasKey("Enabled") ? paramNBTTagCompound.getBoolean("Enabled") : true;
/*     */   }
/*     */   
/*     */   public void setCooldown(int paramInt) {
/* 169 */     this.b = paramInt;
/*     */   }
/*     */   
/*     */   public boolean J() {
/* 173 */     return (this.b > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getContainerName() {
/* 178 */     return "minecraft:hopper";
/*     */   }
/*     */ 
/*     */   
/*     */   public Container createContainer(PlayerInventory paramPlayerInventory, EntityHuman paramEntityHuman) {
/* 183 */     return new ContainerHopper(paramPlayerInventory, this, paramEntityHuman);
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityMinecartHopper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */