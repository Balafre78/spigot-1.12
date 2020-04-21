/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*     */ 
/*     */ public class EntityFallingBlock
/*     */   extends Entity
/*     */ {
/*     */   private IBlockData block;
/*     */   public int ticksLived;
/*     */   public boolean dropItem = true;
/*     */   private boolean f;
/*     */   public boolean hurtEntities;
/*  17 */   private int fallHurtMax = 40;
/*  18 */   private float fallHurtAmount = 2.0F;
/*     */   public NBTTagCompound tileEntityData;
/*  20 */   protected static final DataWatcherObject<BlockPosition> d = DataWatcher.a((Class)EntityFallingBlock.class, DataWatcherRegistry.j);
/*     */   
/*     */   public EntityFallingBlock(World world) {
/*  23 */     super(world);
/*     */   }
/*     */   
/*     */   public EntityFallingBlock(World world, double d0, double d1, double d2, IBlockData iblockdata) {
/*  27 */     super(world);
/*  28 */     this.block = iblockdata;
/*  29 */     this.i = true;
/*  30 */     setSize(0.98F, 0.98F);
/*  31 */     setPosition(d0, d1 + ((1.0F - this.length) / 2.0F), d2);
/*  32 */     this.motX = 0.0D;
/*  33 */     this.motY = 0.0D;
/*  34 */     this.motZ = 0.0D;
/*  35 */     this.lastX = d0;
/*  36 */     this.lastY = d1;
/*  37 */     this.lastZ = d2;
/*  38 */     a(new BlockPosition(this));
/*     */   }
/*     */   
/*     */   public boolean bd() {
/*  42 */     return false;
/*     */   }
/*     */   
/*     */   public void a(BlockPosition blockposition) {
/*  46 */     this.datawatcher.set(d, blockposition);
/*     */   }
/*     */   
/*     */   protected boolean playStepSound() {
/*  50 */     return false;
/*     */   }
/*     */   
/*     */   protected void i() {
/*  54 */     this.datawatcher.register(d, BlockPosition.ZERO);
/*     */   }
/*     */   
/*     */   public boolean isInteractable() {
/*  58 */     return !this.dead;
/*     */   }
/*     */   
/*     */   public void B_() {
/*  62 */     Block block = this.block.getBlock();
/*     */     
/*  64 */     if (this.block.getMaterial() == Material.AIR) {
/*  65 */       die();
/*     */     } else {
/*  67 */       this.lastX = this.locX;
/*  68 */       this.lastY = this.locY;
/*  69 */       this.lastZ = this.locZ;
/*     */ 
/*     */       
/*  72 */       if (this.ticksLived++ == 0) {
/*  73 */         BlockPosition blockposition = new BlockPosition(this);
/*  74 */         if (this.world.getType(blockposition).getBlock() == block && !CraftEventFactory.callEntityChangeBlockEvent(this, blockposition, Blocks.AIR, 0).isCancelled()) {
/*  75 */           this.world.setAir(blockposition);
/*  76 */         } else if (!this.world.isClientSide) {
/*  77 */           die();
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/*  82 */       if (!isNoGravity()) {
/*  83 */         this.motY -= 0.03999999910593033D;
/*     */       }
/*     */       
/*  86 */       move(EnumMoveType.SELF, this.motX, this.motY, this.motZ);
/*  87 */       if (!this.world.isClientSide) {
/*  88 */         BlockPosition blockposition = new BlockPosition(this);
/*  89 */         boolean flag = (this.block.getBlock() == Blocks.dS);
/*  90 */         boolean flag1 = (flag && this.world.getType(blockposition).getMaterial() == Material.WATER);
/*  91 */         double d0 = this.motX * this.motX + this.motY * this.motY + this.motZ * this.motZ;
/*     */         
/*  93 */         if (flag && d0 > 1.0D) {
/*  94 */           MovingObjectPosition movingobjectposition = this.world.rayTrace(new Vec3D(this.lastX, this.lastY, this.lastZ), new Vec3D(this.locX, this.locY, this.locZ), true);
/*     */           
/*  96 */           if (movingobjectposition != null && this.world.getType(movingobjectposition.a()).getMaterial() == Material.WATER) {
/*  97 */             blockposition = movingobjectposition.a();
/*  98 */             flag1 = true;
/*     */           } 
/*     */         } 
/*     */         
/* 102 */         if (!this.onGround && !flag1) {
/* 103 */           if ((this.ticksLived > 100 && !this.world.isClientSide && (blockposition.getY() < 1 || blockposition.getY() > 256)) || this.ticksLived > 600) {
/* 104 */             if (this.dropItem && this.world.getGameRules().getBoolean("doEntityDrops")) {
/* 105 */               a(new ItemStack(block, 1, block.getDropData(this.block)), 0.0F);
/*     */             }
/*     */             
/* 108 */             die();
/*     */           } 
/*     */         } else {
/* 111 */           IBlockData iblockdata = this.world.getType(blockposition);
/*     */           
/* 113 */           if (!flag1 && BlockFalling.x(this.world.getType(new BlockPosition(this.locX, this.locY - 0.009999999776482582D, this.locZ)))) {
/* 114 */             this.onGround = false;
/*     */           }
/*     */ 
/*     */           
/* 118 */           this.motX *= 0.699999988079071D;
/* 119 */           this.motZ *= 0.699999988079071D;
/* 120 */           this.motY *= -0.5D;
/* 121 */           if (iblockdata.getBlock() != Blocks.PISTON_EXTENSION) {
/* 122 */             die();
/* 123 */             if (!this.f) {
/*     */               
/* 125 */               if (this.world.a(block, blockposition, true, EnumDirection.UP, (Entity)null) && (flag1 || !BlockFalling.x(this.world.getType(blockposition.down())))) {
/* 126 */                 if (CraftEventFactory.callEntityChangeBlockEvent(this, blockposition, this.block.getBlock(), this.block.getBlock().toLegacyData(this.block)).isCancelled()) {
/*     */                   return;
/*     */                 }
/* 129 */                 this.world.setTypeAndData(blockposition, this.block, 3);
/*     */                 
/* 131 */                 if (block instanceof BlockFalling) {
/* 132 */                   ((BlockFalling)block).a(this.world, blockposition, this.block, iblockdata);
/*     */                 }
/*     */                 
/* 135 */                 if (this.tileEntityData != null && block instanceof ITileEntity) {
/* 136 */                   TileEntity tileentity = this.world.getTileEntity(blockposition);
/*     */                   
/* 138 */                   if (tileentity != null) {
/* 139 */                     NBTTagCompound nbttagcompound = tileentity.save(new NBTTagCompound());
/* 140 */                     Iterator<String> iterator = this.tileEntityData.c().iterator();
/*     */                     
/* 142 */                     while (iterator.hasNext()) {
/* 143 */                       String s = iterator.next();
/* 144 */                       NBTBase nbtbase = this.tileEntityData.get(s);
/*     */                       
/* 146 */                       if (!"x".equals(s) && !"y".equals(s) && !"z".equals(s)) {
/* 147 */                         nbttagcompound.set(s, nbtbase.clone());
/*     */                       }
/*     */                     } 
/*     */                     
/* 151 */                     tileentity.a(nbttagcompound);
/* 152 */                     tileentity.update();
/*     */                   } 
/*     */                 } 
/* 155 */               } else if (this.dropItem && this.world.getGameRules().getBoolean("doEntityDrops")) {
/* 156 */                 a(new ItemStack(block, 1, block.getDropData(this.block)), 0.0F);
/*     */               } 
/* 158 */             } else if (block instanceof BlockFalling) {
/* 159 */               ((BlockFalling)block).a_(this.world, blockposition);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 165 */       this.motX *= 0.9800000190734863D;
/* 166 */       this.motY *= 0.9800000190734863D;
/* 167 */       this.motZ *= 0.9800000190734863D;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void e(float f, float f1) {
/* 172 */     Block block = this.block.getBlock();
/*     */     
/* 174 */     if (this.hurtEntities) {
/* 175 */       int i = MathHelper.f(f - 1.0F);
/*     */       
/* 177 */       if (i > 0) {
/* 178 */         ArrayList arraylist = Lists.newArrayList(this.world.getEntities(this, getBoundingBox()));
/* 179 */         boolean flag = (block == Blocks.ANVIL);
/* 180 */         DamageSource damagesource = flag ? DamageSource.ANVIL : DamageSource.FALLING_BLOCK;
/* 181 */         Iterator<Entity> iterator = arraylist.iterator();
/*     */         
/* 183 */         while (iterator.hasNext()) {
/* 184 */           Entity entity = iterator.next();
/*     */           
/* 186 */           CraftEventFactory.entityDamage = this;
/* 187 */           entity.damageEntity(damagesource, Math.min(MathHelper.d(i * this.fallHurtAmount), this.fallHurtMax));
/* 188 */           CraftEventFactory.entityDamage = null;
/*     */         } 
/*     */         
/* 191 */         if (flag && this.random.nextFloat() < 0.05000000074505806D + i * 0.05D) {
/* 192 */           int j = ((Integer)this.block.<Integer>get(BlockAnvil.DAMAGE)).intValue();
/*     */           
/* 194 */           j++;
/* 195 */           if (j > 2) {
/* 196 */             this.f = true;
/*     */           } else {
/* 198 */             this.block = this.block.set(BlockAnvil.DAMAGE, Integer.valueOf(j));
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void a(DataConverterManager dataconvertermanager) {}
/*     */   
/*     */   protected void b(NBTTagCompound nbttagcompound) {
/* 209 */     Block block = (this.block != null) ? this.block.getBlock() : Blocks.AIR;
/* 210 */     MinecraftKey minecraftkey = Block.REGISTRY.b(block);
/*     */     
/* 212 */     nbttagcompound.setString("Block", (minecraftkey == null) ? "" : minecraftkey.toString());
/* 213 */     nbttagcompound.setByte("Data", (byte)block.toLegacyData(this.block));
/* 214 */     nbttagcompound.setInt("Time", this.ticksLived);
/* 215 */     nbttagcompound.setBoolean("DropItem", this.dropItem);
/* 216 */     nbttagcompound.setBoolean("HurtEntities", this.hurtEntities);
/* 217 */     nbttagcompound.setFloat("FallHurtAmount", this.fallHurtAmount);
/* 218 */     nbttagcompound.setInt("FallHurtMax", this.fallHurtMax);
/* 219 */     if (this.tileEntityData != null) {
/* 220 */       nbttagcompound.set("TileEntityData", this.tileEntityData);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(NBTTagCompound nbttagcompound) {
/* 226 */     int i = nbttagcompound.getByte("Data") & 0xFF;
/*     */     
/* 228 */     if (nbttagcompound.hasKeyOfType("Block", 8)) {
/* 229 */       this.block = Block.getByName(nbttagcompound.getString("Block")).fromLegacyData(i);
/* 230 */     } else if (nbttagcompound.hasKeyOfType("TileID", 99)) {
/* 231 */       this.block = Block.getById(nbttagcompound.getInt("TileID")).fromLegacyData(i);
/*     */     } else {
/* 233 */       this.block = Block.getById(nbttagcompound.getByte("Tile") & 0xFF).fromLegacyData(i);
/*     */     } 
/*     */     
/* 236 */     this.ticksLived = nbttagcompound.getInt("Time");
/* 237 */     Block block = this.block.getBlock();
/*     */     
/* 239 */     if (nbttagcompound.hasKeyOfType("HurtEntities", 99)) {
/* 240 */       this.hurtEntities = nbttagcompound.getBoolean("HurtEntities");
/* 241 */       this.fallHurtAmount = nbttagcompound.getFloat("FallHurtAmount");
/* 242 */       this.fallHurtMax = nbttagcompound.getInt("FallHurtMax");
/* 243 */     } else if (block == Blocks.ANVIL) {
/* 244 */       this.hurtEntities = true;
/*     */     } 
/*     */     
/* 247 */     if (nbttagcompound.hasKeyOfType("DropItem", 99)) {
/* 248 */       this.dropItem = nbttagcompound.getBoolean("DropItem");
/*     */     }
/*     */     
/* 251 */     if (nbttagcompound.hasKeyOfType("TileEntityData", 10)) {
/* 252 */       this.tileEntityData = nbttagcompound.getCompound("TileEntityData");
/*     */     }
/*     */     
/* 255 */     if (block == null || block.getBlockData().getMaterial() == Material.AIR) {
/* 256 */       this.block = Blocks.SAND.getBlockData();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(boolean flag) {
/* 262 */     this.hurtEntities = flag;
/*     */   }
/*     */   
/*     */   public void appendEntityCrashDetails(CrashReportSystemDetails crashreportsystemdetails) {
/* 266 */     super.appendEntityCrashDetails(crashreportsystemdetails);
/* 267 */     if (this.block != null) {
/* 268 */       Block block = this.block.getBlock();
/*     */       
/* 270 */       crashreportsystemdetails.a("Immitating block ID", Integer.valueOf(Block.getId(block)));
/* 271 */       crashreportsystemdetails.a("Immitating block data", Integer.valueOf(block.toLegacyData(this.block)));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public IBlockData getBlock() {
/* 278 */     return this.block;
/*     */   }
/*     */   
/*     */   public boolean bC() {
/* 282 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityFallingBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */