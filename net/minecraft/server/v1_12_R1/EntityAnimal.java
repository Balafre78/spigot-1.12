/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ public abstract class EntityAnimal
/*     */   extends EntityAgeable implements IAnimal {
/*     */   protected Block bA;
/*     */   private int bx;
/*     */   private UUID by;
/*     */   public ItemStack breedItem;
/*     */   
/*     */   public EntityAnimal(World world) {
/*  14 */     super(world);
/*  15 */     this.bA = Blocks.GRASS;
/*     */   }
/*     */   
/*     */   protected void M() {
/*  19 */     if (getAge() != 0) {
/*  20 */       this.bx = 0;
/*     */     }
/*     */     
/*  23 */     super.M();
/*     */   }
/*     */   
/*     */   public void n() {
/*  27 */     super.n();
/*  28 */     if (getAge() != 0) {
/*  29 */       this.bx = 0;
/*     */     }
/*     */     
/*  32 */     if (this.bx > 0) {
/*  33 */       this.bx--;
/*  34 */       if (this.bx % 10 == 0) {
/*  35 */         double d0 = this.random.nextGaussian() * 0.02D;
/*  36 */         double d1 = this.random.nextGaussian() * 0.02D;
/*  37 */         double d2 = this.random.nextGaussian() * 0.02D;
/*     */         
/*  39 */         this.world.addParticle(EnumParticle.HEART, this.locX + (this.random.nextFloat() * this.width * 2.0F) - this.width, this.locY + 0.5D + (this.random.nextFloat() * this.length), this.locZ + (this.random.nextFloat() * this.width * 2.0F) - this.width, d0, d1, d2, new int[0]);
/*     */       } 
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
/*     */ 
/*     */   
/*     */   public float a(BlockPosition blockposition) {
/*  59 */     return (this.world.getType(blockposition.down()).getBlock() == this.bA) ? 10.0F : (this.world.n(blockposition) - 0.5F);
/*     */   }
/*     */   
/*     */   public void b(NBTTagCompound nbttagcompound) {
/*  63 */     super.b(nbttagcompound);
/*  64 */     nbttagcompound.setInt("InLove", this.bx);
/*  65 */     if (this.by != null) {
/*  66 */       nbttagcompound.a("LoveCause", this.by);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public double aF() {
/*  72 */     return 0.14D;
/*     */   }
/*     */   
/*     */   public void a(NBTTagCompound nbttagcompound) {
/*  76 */     super.a(nbttagcompound);
/*  77 */     this.bx = nbttagcompound.getInt("InLove");
/*  78 */     this.by = nbttagcompound.b("LoveCause") ? nbttagcompound.a("LoveCause") : null;
/*     */   }
/*     */   
/*     */   public boolean P() {
/*  82 */     int i = MathHelper.floor(this.locX);
/*  83 */     int j = MathHelper.floor((getBoundingBox()).b);
/*  84 */     int k = MathHelper.floor(this.locZ);
/*  85 */     BlockPosition blockposition = new BlockPosition(i, j, k);
/*     */     
/*  87 */     return (this.world.getType(blockposition.down()).getBlock() == this.bA && this.world.j(blockposition) > 8 && super.P());
/*     */   }
/*     */   
/*     */   public int C() {
/*  91 */     return 120;
/*     */   }
/*     */   
/*     */   protected boolean isTypeNotPersistent() {
/*  95 */     return false;
/*     */   }
/*     */   
/*     */   protected int getExpValue(EntityHuman entityhuman) {
/*  99 */     return 1 + this.world.random.nextInt(3);
/*     */   }
/*     */   
/*     */   public boolean e(ItemStack itemstack) {
/* 103 */     return (itemstack.getItem() == Items.WHEAT);
/*     */   }
/*     */   
/*     */   public boolean a(EntityHuman entityhuman, EnumHand enumhand) {
/* 107 */     ItemStack itemstack = entityhuman.b(enumhand);
/*     */     
/* 109 */     if (!itemstack.isEmpty()) {
/* 110 */       if (e(itemstack) && getAge() == 0 && this.bx <= 0) {
/* 111 */         a(entityhuman, itemstack);
/* 112 */         f(entityhuman);
/* 113 */         return true;
/*     */       } 
/*     */       
/* 116 */       if (isBaby() && e(itemstack)) {
/* 117 */         a(entityhuman, itemstack);
/* 118 */         setAge((int)((-getAge() / 20) * 0.1F), true);
/* 119 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/* 123 */     return super.a(entityhuman, enumhand);
/*     */   }
/*     */   
/*     */   protected void a(EntityHuman entityhuman, ItemStack itemstack) {
/* 127 */     if (!entityhuman.abilities.canInstantlyBuild) {
/* 128 */       itemstack.subtract(1);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void f(@Nullable EntityHuman entityhuman) {
/* 134 */     this.bx = 600;
/* 135 */     if (entityhuman != null) {
/* 136 */       this.by = entityhuman.getUniqueID();
/*     */     }
/* 138 */     this.breedItem = entityhuman.inventory.getItemInHand();
/*     */     
/* 140 */     this.world.broadcastEntityEffect(this, (byte)18);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public EntityPlayer getBreedCause() {
/* 145 */     if (this.by == null) {
/* 146 */       return null;
/*     */     }
/* 148 */     EntityHuman entityhuman = this.world.b(this.by);
/*     */     
/* 150 */     return (entityhuman instanceof EntityPlayer) ? (EntityPlayer)entityhuman : null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isInLove() {
/* 155 */     return (this.bx > 0);
/*     */   }
/*     */   
/*     */   public void resetLove() {
/* 159 */     this.bx = 0;
/*     */   }
/*     */   
/*     */   public boolean mate(EntityAnimal entityanimal) {
/* 163 */     return (entityanimal == this) ? false : ((entityanimal.getClass() != getClass()) ? false : ((isInLove() && entityanimal.isInLove())));
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityAnimal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */