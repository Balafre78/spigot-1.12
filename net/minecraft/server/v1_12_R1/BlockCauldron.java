/*     */ package net.minecraft.server.v1_12_R1;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.block.CauldronLevelChangeEvent;
/*     */ 
/*     */ public class BlockCauldron extends Block {
/*  10 */   public static final BlockStateInteger LEVEL = BlockStateInteger.of("level", 0, 3);
/*  11 */   protected static final AxisAlignedBB b = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.3125D, 1.0D);
/*  12 */   protected static final AxisAlignedBB c = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.125D);
/*  13 */   protected static final AxisAlignedBB d = new AxisAlignedBB(0.0D, 0.0D, 0.875D, 1.0D, 1.0D, 1.0D);
/*  14 */   protected static final AxisAlignedBB e = new AxisAlignedBB(0.875D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/*  15 */   protected static final AxisAlignedBB f = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.125D, 1.0D, 1.0D);
/*     */   
/*     */   public BlockCauldron() {
/*  18 */     super(Material.ORE, MaterialMapColor.n);
/*  19 */     w(this.blockStateList.getBlockData().set(LEVEL, Integer.valueOf(0)));
/*     */   }
/*     */   
/*     */   public void a(IBlockData iblockdata, World world, BlockPosition blockposition, AxisAlignedBB axisalignedbb, List<AxisAlignedBB> list, @Nullable Entity entity, boolean flag) {
/*  23 */     a(blockposition, axisalignedbb, list, b);
/*  24 */     a(blockposition, axisalignedbb, list, f);
/*  25 */     a(blockposition, axisalignedbb, list, c);
/*  26 */     a(blockposition, axisalignedbb, list, e);
/*  27 */     a(blockposition, axisalignedbb, list, d);
/*     */   }
/*     */   
/*     */   public AxisAlignedBB b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/*  31 */     return j;
/*     */   }
/*     */   
/*     */   public boolean b(IBlockData iblockdata) {
/*  35 */     return false;
/*     */   }
/*     */   
/*     */   public boolean c(IBlockData iblockdata) {
/*  39 */     return false;
/*     */   }
/*     */   
/*     */   public void a(World world, BlockPosition blockposition, IBlockData iblockdata, Entity entity) {
/*  43 */     int i = ((Integer)iblockdata.<Integer>get(LEVEL)).intValue();
/*  44 */     float f = blockposition.getY() + (6.0F + (3 * i)) / 16.0F;
/*     */     
/*  46 */     if (!world.isClientSide && entity.isBurning() && i > 0 && (entity.getBoundingBox()).b <= f) {
/*     */       
/*  48 */       if (!changeLevel(world, blockposition, iblockdata, i - 1, entity, CauldronLevelChangeEvent.ChangeReason.EXTINGUISH)) {
/*     */         return;
/*     */       }
/*  51 */       entity.extinguish();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean interact(World world, BlockPosition blockposition, IBlockData iblockdata, EntityHuman entityhuman, EnumHand enumhand, EnumDirection enumdirection, float f, float f1, float f2) {
/*  59 */     ItemStack itemstack = entityhuman.b(enumhand);
/*     */     
/*  61 */     if (itemstack.isEmpty()) {
/*  62 */       return true;
/*     */     }
/*  64 */     int i = ((Integer)iblockdata.<Integer>get(LEVEL)).intValue();
/*  65 */     Item item = itemstack.getItem();
/*     */     
/*  67 */     if (item == Items.WATER_BUCKET) {
/*  68 */       if (i < 3 && !world.isClientSide) {
/*     */         
/*  70 */         if (!changeLevel(world, blockposition, iblockdata, 3, entityhuman, CauldronLevelChangeEvent.ChangeReason.BUCKET_EMPTY)) {
/*  71 */           return true;
/*     */         }
/*  73 */         if (!entityhuman.abilities.canInstantlyBuild) {
/*  74 */           entityhuman.a(enumhand, new ItemStack(Items.BUCKET));
/*     */         }
/*     */         
/*  77 */         entityhuman.b(StatisticList.I);
/*     */ 
/*     */         
/*  80 */         world.a(null, blockposition, SoundEffects.Q, SoundCategory.BLOCKS, 1.0F, 1.0F);
/*     */       } 
/*     */       
/*  83 */       return true;
/*  84 */     }  if (item == Items.BUCKET) {
/*  85 */       if (i == 3 && !world.isClientSide) {
/*     */         
/*  87 */         if (!changeLevel(world, blockposition, iblockdata, 0, entityhuman, CauldronLevelChangeEvent.ChangeReason.BUCKET_FILL)) {
/*  88 */           return true;
/*     */         }
/*  90 */         if (!entityhuman.abilities.canInstantlyBuild) {
/*  91 */           itemstack.subtract(1);
/*  92 */           if (itemstack.isEmpty()) {
/*  93 */             entityhuman.a(enumhand, new ItemStack(Items.WATER_BUCKET));
/*  94 */           } else if (!entityhuman.inventory.pickup(new ItemStack(Items.WATER_BUCKET))) {
/*  95 */             entityhuman.drop(new ItemStack(Items.WATER_BUCKET), false);
/*     */           } 
/*     */         } 
/*     */         
/*  99 */         entityhuman.b(StatisticList.J);
/*     */ 
/*     */         
/* 102 */         world.a(null, blockposition, SoundEffects.S, SoundCategory.BLOCKS, 1.0F, 1.0F);
/*     */       } 
/*     */       
/* 105 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 109 */     if (item == Items.GLASS_BOTTLE) {
/* 110 */       if (i > 0 && !world.isClientSide) {
/*     */         
/* 112 */         if (!changeLevel(world, blockposition, iblockdata, i - 1, entityhuman, CauldronLevelChangeEvent.ChangeReason.BOTTLE_FILL)) {
/* 113 */           return true;
/*     */         }
/* 115 */         if (!entityhuman.abilities.canInstantlyBuild) {
/* 116 */           ItemStack itemstack1 = PotionUtil.a(new ItemStack(Items.POTION), Potions.b);
/* 117 */           entityhuman.b(StatisticList.J);
/* 118 */           itemstack.subtract(1);
/* 119 */           if (itemstack.isEmpty()) {
/* 120 */             entityhuman.a(enumhand, itemstack1);
/* 121 */           } else if (!entityhuman.inventory.pickup(itemstack1)) {
/* 122 */             entityhuman.drop(itemstack1, false);
/* 123 */           } else if (entityhuman instanceof EntityPlayer) {
/* 124 */             ((EntityPlayer)entityhuman).updateInventory(entityhuman.defaultContainer);
/*     */           } 
/*     */         } 
/*     */         
/* 128 */         world.a(null, blockposition, SoundEffects.N, SoundCategory.BLOCKS, 1.0F, 1.0F);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 133 */       return true;
/* 134 */     }  if (item == Items.POTION && PotionUtil.d(itemstack) == Potions.b) {
/* 135 */       if (i < 3 && !world.isClientSide) {
/*     */         
/* 137 */         if (!changeLevel(world, blockposition, iblockdata, i + 1, entityhuman, CauldronLevelChangeEvent.ChangeReason.BOTTLE_EMPTY)) {
/* 138 */           return true;
/*     */         }
/* 140 */         if (!entityhuman.abilities.canInstantlyBuild) {
/* 141 */           ItemStack itemstack1 = new ItemStack(Items.GLASS_BOTTLE);
/* 142 */           entityhuman.b(StatisticList.J);
/* 143 */           entityhuman.a(enumhand, itemstack1);
/* 144 */           if (entityhuman instanceof EntityPlayer) {
/* 145 */             ((EntityPlayer)entityhuman).updateInventory(entityhuman.defaultContainer);
/*     */           }
/*     */         } 
/*     */         
/* 149 */         world.a(null, blockposition, SoundEffects.M, SoundCategory.BLOCKS, 1.0F, 1.0F);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 154 */       return true;
/*     */     } 
/* 156 */     if (i > 0 && item instanceof ItemArmor) {
/* 157 */       ItemArmor itemarmor = (ItemArmor)item;
/*     */       
/* 159 */       if (itemarmor.d() == ItemArmor.EnumArmorMaterial.LEATHER && itemarmor.e_(itemstack) && !world.isClientSide) {
/*     */         
/* 161 */         if (!changeLevel(world, blockposition, iblockdata, i - 1, entityhuman, CauldronLevelChangeEvent.ChangeReason.ARMOR_WASH)) {
/* 162 */           return true;
/*     */         }
/* 164 */         itemarmor.d(itemstack);
/*     */ 
/*     */         
/* 167 */         entityhuman.b(StatisticList.K);
/* 168 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/* 172 */     if (i > 0 && item instanceof ItemBanner) {
/* 173 */       if (TileEntityBanner.b(itemstack) > 0 && !world.isClientSide) {
/*     */         
/* 175 */         if (!changeLevel(world, blockposition, iblockdata, i - 1, entityhuman, CauldronLevelChangeEvent.ChangeReason.BANNER_WASH)) {
/* 176 */           return true;
/*     */         }
/* 178 */         ItemStack itemstack1 = itemstack.cloneItemStack();
/* 179 */         itemstack1.setCount(1);
/* 180 */         TileEntityBanner.c(itemstack1);
/* 181 */         entityhuman.b(StatisticList.L);
/* 182 */         if (!entityhuman.abilities.canInstantlyBuild) {
/* 183 */           itemstack.subtract(1);
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 188 */         if (itemstack.isEmpty()) {
/* 189 */           entityhuman.a(enumhand, itemstack1);
/* 190 */         } else if (!entityhuman.inventory.pickup(itemstack1)) {
/* 191 */           entityhuman.drop(itemstack1, false);
/* 192 */         } else if (entityhuman instanceof EntityPlayer) {
/* 193 */           ((EntityPlayer)entityhuman).updateInventory(entityhuman.defaultContainer);
/*     */         } 
/*     */       } 
/*     */       
/* 197 */       return true;
/*     */     } 
/* 199 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(World world, BlockPosition blockposition, IBlockData iblockdata, int i) {
/* 208 */     changeLevel(world, blockposition, iblockdata, i, (Entity)null, CauldronLevelChangeEvent.ChangeReason.UNKNOWN);
/*     */   }
/*     */   
/*     */   private boolean changeLevel(World world, BlockPosition blockposition, IBlockData iblockdata, int i, Entity entity, CauldronLevelChangeEvent.ChangeReason reason) {
/* 212 */     int newLevel = Integer.valueOf(MathHelper.clamp(i, 0, 3)).intValue();
/* 213 */     CauldronLevelChangeEvent event = new CauldronLevelChangeEvent(
/* 214 */         world.getWorld().getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ()), 
/* 215 */         (entity == null) ? null : (Entity)entity.getBukkitEntity(), reason, ((Integer)iblockdata.<Integer>get(LEVEL)).intValue(), newLevel);
/*     */     
/* 217 */     world.getServer().getPluginManager().callEvent((Event)event);
/* 218 */     if (event.isCancelled()) {
/* 219 */       return false;
/*     */     }
/* 221 */     world.setTypeAndData(blockposition, iblockdata.set(LEVEL, Integer.valueOf(newLevel)), 2);
/* 222 */     world.updateAdjacentComparators(blockposition, this);
/* 223 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void h(World world, BlockPosition blockposition) {
/* 228 */     if (world.random.nextInt(20) == 1) {
/* 229 */       float f = world.getBiome(blockposition).a(blockposition);
/*     */       
/* 231 */       if (world.getWorldChunkManager().a(f, blockposition.getY()) >= 0.15F) {
/* 232 */         IBlockData iblockdata = world.getType(blockposition);
/*     */         
/* 234 */         if (((Integer)iblockdata.get(LEVEL)).intValue() < 3) {
/* 235 */           a(world, blockposition, iblockdata.a(LEVEL), 2);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getDropType(IBlockData iblockdata, Random random, int i) {
/* 243 */     return Items.CAULDRON;
/*     */   }
/*     */   
/*     */   public ItemStack a(World world, BlockPosition blockposition, IBlockData iblockdata) {
/* 247 */     return new ItemStack(Items.CAULDRON);
/*     */   }
/*     */   
/*     */   public boolean isComplexRedstone(IBlockData iblockdata) {
/* 251 */     return true;
/*     */   }
/*     */   
/*     */   public int c(IBlockData iblockdata, World world, BlockPosition blockposition) {
/* 255 */     return ((Integer)iblockdata.<Integer>get(LEVEL)).intValue();
/*     */   }
/*     */   
/*     */   public IBlockData fromLegacyData(int i) {
/* 259 */     return getBlockData().set(LEVEL, Integer.valueOf(i));
/*     */   }
/*     */   
/*     */   public int toLegacyData(IBlockData iblockdata) {
/* 263 */     return ((Integer)iblockdata.<Integer>get(LEVEL)).intValue();
/*     */   }
/*     */   
/*     */   protected BlockStateList getStateList() {
/* 267 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { LEVEL });
/*     */   }
/*     */   
/*     */   public boolean b(IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 271 */     return true;
/*     */   }
/*     */   
/*     */   public EnumBlockFaceShape a(IBlockAccess iblockaccess, IBlockData iblockdata, BlockPosition blockposition, EnumDirection enumdirection) {
/* 275 */     return (enumdirection == EnumDirection.UP) ? EnumBlockFaceShape.BOWL : ((enumdirection == EnumDirection.DOWN) ? EnumBlockFaceShape.UNDEFINED : EnumBlockFaceShape.SOLID);
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockCauldron.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */