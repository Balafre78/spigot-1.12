/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.ArrayList;
/*     */ import java.util.LinkedList;
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
/*     */ public class BlockSponge
/*     */   extends Block
/*     */ {
/*  24 */   public static final BlockStateBoolean WET = BlockStateBoolean.of("wet");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected BlockSponge() {
/*  33 */     super(Material.SPONGE);
/*  34 */     w(this.blockStateList.getBlockData().set(WET, Boolean.valueOf(false)));
/*  35 */     a(CreativeModeTab.b);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/*  40 */     return LocaleI18n.get(a() + ".dry.name");
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDropData(IBlockData paramIBlockData) {
/*  45 */     return ((Boolean)paramIBlockData.<Boolean>get(WET)).booleanValue() ? 1 : 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onPlace(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/*  50 */     e(paramWorld, paramBlockPosition, paramIBlockData);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(IBlockData paramIBlockData, World paramWorld, BlockPosition paramBlockPosition1, Block paramBlock, BlockPosition paramBlockPosition2) {
/*  55 */     e(paramWorld, paramBlockPosition1, paramIBlockData);
/*  56 */     super.a(paramIBlockData, paramWorld, paramBlockPosition1, paramBlock, paramBlockPosition2);
/*     */   }
/*     */   
/*     */   protected void e(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/*  60 */     if (!((Boolean)paramIBlockData.<Boolean>get(WET)).booleanValue() && b(paramWorld, paramBlockPosition)) {
/*     */       
/*  62 */       paramWorld.setTypeAndData(paramBlockPosition, paramIBlockData.set(WET, Boolean.valueOf(true)), 2);
/*  63 */       paramWorld.triggerEffect(2001, paramBlockPosition, Block.getId(Blocks.WATER));
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean b(World paramWorld, BlockPosition paramBlockPosition) {
/*  68 */     LinkedList<Tuple> linkedList = Lists.newLinkedList();
/*  69 */     ArrayList<BlockPosition> arrayList = Lists.newArrayList();
/*  70 */     linkedList.add(new Tuple<>(paramBlockPosition, Integer.valueOf(0)));
/*     */     
/*  72 */     byte b = 0;
/*  73 */     while (!linkedList.isEmpty()) {
/*  74 */       Tuple tuple = linkedList.poll();
/*  75 */       BlockPosition blockPosition = (BlockPosition)tuple.a();
/*  76 */       int i = ((Integer)tuple.b()).intValue();
/*     */       
/*  78 */       for (EnumDirection enumDirection : EnumDirection.values()) {
/*  79 */         BlockPosition blockPosition1 = blockPosition.shift(enumDirection);
/*  80 */         if (paramWorld.getType(blockPosition1).getMaterial() == Material.WATER) {
/*  81 */           paramWorld.setTypeAndData(blockPosition1, Blocks.AIR.getBlockData(), 2);
/*  82 */           arrayList.add(blockPosition1);
/*  83 */           b++;
/*  84 */           if (i < 6) {
/*  85 */             linkedList.add(new Tuple<>(blockPosition1, Integer.valueOf(i + 1)));
/*     */           }
/*     */         } 
/*     */       } 
/*  89 */       if (b > 64) {
/*     */         break;
/*     */       }
/*     */     } 
/*  93 */     for (BlockPosition blockPosition : arrayList) {
/*  94 */       paramWorld.applyPhysics(blockPosition, Blocks.AIR, false);
/*     */     }
/*  96 */     return (b > 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockData fromLegacyData(int paramInt) {
/* 107 */     return getBlockData().set(WET, Boolean.valueOf(((paramInt & 0x1) == 1)));
/*     */   }
/*     */ 
/*     */   
/*     */   public int toLegacyData(IBlockData paramIBlockData) {
/* 112 */     return ((Boolean)paramIBlockData.<Boolean>get(WET)).booleanValue() ? 1 : 0;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockStateList getStateList() {
/* 117 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { WET });
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockSponge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */