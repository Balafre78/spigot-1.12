/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.collect.Lists;
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
/*     */ public class BlockNote
/*     */   extends BlockTileEntity
/*     */ {
/*  23 */   private static final List<SoundEffect> a = Lists.newArrayList((Object[])new SoundEffect[] { SoundEffects.eA, SoundEffects.eu, SoundEffects.eD, SoundEffects.eB, SoundEffects.ev, SoundEffects.ey, SoundEffects.ew, SoundEffects.ez, SoundEffects.ex, SoundEffects.eE });
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
/*     */   public BlockNote() {
/*  37 */     super(Material.WOOD);
/*  38 */     a(CreativeModeTab.d);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(IBlockData paramIBlockData, World paramWorld, BlockPosition paramBlockPosition1, Block paramBlock, BlockPosition paramBlockPosition2) {
/*  43 */     boolean bool = paramWorld.isBlockIndirectlyPowered(paramBlockPosition1);
/*     */     
/*  45 */     TileEntity tileEntity = paramWorld.getTileEntity(paramBlockPosition1);
/*  46 */     if (tileEntity instanceof TileEntityNote) {
/*  47 */       TileEntityNote tileEntityNote = (TileEntityNote)tileEntity;
/*  48 */       if (tileEntityNote.f != bool) {
/*  49 */         if (bool) {
/*  50 */           tileEntityNote.play(paramWorld, paramBlockPosition1);
/*     */         }
/*  52 */         tileEntityNote.f = bool;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean interact(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData, EntityHuman paramEntityHuman, EnumHand paramEnumHand, EnumDirection paramEnumDirection, float paramFloat1, float paramFloat2, float paramFloat3) {
/*  59 */     if (paramWorld.isClientSide) {
/*  60 */       return true;
/*     */     }
/*     */     
/*  63 */     TileEntity tileEntity = paramWorld.getTileEntity(paramBlockPosition);
/*  64 */     if (tileEntity instanceof TileEntityNote) {
/*  65 */       TileEntityNote tileEntityNote = (TileEntityNote)tileEntity;
/*     */       
/*  67 */       tileEntityNote.a();
/*  68 */       tileEntityNote.play(paramWorld, paramBlockPosition);
/*  69 */       paramEntityHuman.b(StatisticList.S);
/*     */     } 
/*     */     
/*  72 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void attack(World paramWorld, BlockPosition paramBlockPosition, EntityHuman paramEntityHuman) {
/*  77 */     if (paramWorld.isClientSide) {
/*     */       return;
/*     */     }
/*     */     
/*  81 */     TileEntity tileEntity = paramWorld.getTileEntity(paramBlockPosition);
/*  82 */     if (tileEntity instanceof TileEntityNote) {
/*  83 */       ((TileEntityNote)tileEntity).play(paramWorld, paramBlockPosition);
/*  84 */       paramEntityHuman.b(StatisticList.R);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public TileEntity a(World paramWorld, int paramInt) {
/*  90 */     return new TileEntityNote();
/*     */   }
/*     */   
/*     */   private SoundEffect b(int paramInt) {
/*  94 */     if (paramInt < 0 || paramInt >= a.size()) {
/*  95 */       paramInt = 0;
/*     */     }
/*     */     
/*  98 */     return a.get(paramInt);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(IBlockData paramIBlockData, World paramWorld, BlockPosition paramBlockPosition, int paramInt1, int paramInt2) {
/* 103 */     float f = (float)Math.pow(2.0D, (paramInt2 - 12) / 12.0D);
/*     */     
/* 105 */     paramWorld.a(null, paramBlockPosition, b(paramInt1), SoundCategory.RECORDS, 3.0F, f);
/* 106 */     paramWorld.addParticle(EnumParticle.NOTE, paramBlockPosition.getX() + 0.5D, paramBlockPosition.getY() + 1.2D, paramBlockPosition.getZ() + 0.5D, paramInt2 / 24.0D, 0.0D, 0.0D, new int[0]);
/* 107 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumRenderType a(IBlockData paramIBlockData) {
/* 112 */     return EnumRenderType.MODEL;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockNote.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */