/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ public class EntityMinecartCommandBlock
/*     */   extends EntityMinecartAbstract {
/*   5 */   public static final DataWatcherObject<String> COMMAND = DataWatcher.a((Class)EntityMinecartCommandBlock.class, DataWatcherRegistry.d);
/*   6 */   private static final DataWatcherObject<IChatBaseComponent> b = DataWatcher.a((Class)EntityMinecartCommandBlock.class, DataWatcherRegistry.e);
/*   7 */   private final CommandBlockListenerAbstract c = new CommandBlockListenerAbstract()
/*     */     {
/*     */       
/*     */       public void i()
/*     */       {
/*  12 */         EntityMinecartCommandBlock.this.getDataWatcher().set(EntityMinecartCommandBlock.COMMAND, getCommand());
/*  13 */         EntityMinecartCommandBlock.this.getDataWatcher().set(EntityMinecartCommandBlock.b, l());
/*     */       }
/*     */       
/*     */       public BlockPosition getChunkCoordinates() {
/*  17 */         return new BlockPosition(EntityMinecartCommandBlock.this.locX, EntityMinecartCommandBlock.this.locY + 0.5D, EntityMinecartCommandBlock.this.locZ);
/*     */       }
/*     */       
/*     */       public Vec3D d() {
/*  21 */         return new Vec3D(EntityMinecartCommandBlock.this.locX, EntityMinecartCommandBlock.this.locY, EntityMinecartCommandBlock.this.locZ);
/*     */       }
/*     */       
/*     */       public World getWorld() {
/*  25 */         return EntityMinecartCommandBlock.this.world;
/*     */       }
/*     */       
/*     */       public Entity f() {
/*  29 */         return EntityMinecartCommandBlock.this;
/*     */       }
/*     */       
/*     */       public MinecraftServer C_() {
/*  33 */         return EntityMinecartCommandBlock.this.world.getMinecraftServer();
/*     */       }
/*     */     };
/*     */   private int d;
/*     */   
/*     */   public EntityMinecartCommandBlock(World world) {
/*  39 */     super(world);
/*     */   }
/*     */   
/*     */   public EntityMinecartCommandBlock(World world, double d0, double d1, double d2) {
/*  43 */     super(world, d0, d1, d2);
/*     */   }
/*     */   
/*     */   public static void a(DataConverterManager dataconvertermanager) {
/*  47 */     EntityMinecartAbstract.a(dataconvertermanager, EntityMinecartCommandBlock.class);
/*  48 */     dataconvertermanager.a(DataConverterTypes.ENTITY, new DataInspector() {
/*     */           public NBTTagCompound a(DataConverter dataconverter, NBTTagCompound nbttagcompound, int i) {
/*  50 */             if (TileEntity.a((Class)TileEntityCommand.class).equals(new MinecraftKey(nbttagcompound.getString("id")))) {
/*  51 */               nbttagcompound.setString("id", "Control");
/*  52 */               dataconverter.a(DataConverterTypes.BLOCK_ENTITY, nbttagcompound, i);
/*  53 */               nbttagcompound.setString("id", "MinecartCommandBlock");
/*     */             } 
/*     */             
/*  56 */             return nbttagcompound;
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   protected void i() {
/*  62 */     super.i();
/*  63 */     getDataWatcher().register(COMMAND, "");
/*  64 */     getDataWatcher().register(b, new ChatComponentText(""));
/*     */   }
/*     */   
/*     */   protected void a(NBTTagCompound nbttagcompound) {
/*  68 */     super.a(nbttagcompound);
/*  69 */     this.c.b(nbttagcompound);
/*  70 */     getDataWatcher().set(COMMAND, getCommandBlock().getCommand());
/*  71 */     getDataWatcher().set(b, getCommandBlock().l());
/*     */   }
/*     */   
/*     */   protected void b(NBTTagCompound nbttagcompound) {
/*  75 */     super.b(nbttagcompound);
/*  76 */     this.c.a(nbttagcompound);
/*     */   }
/*     */   
/*     */   public EntityMinecartAbstract.EnumMinecartType v() {
/*  80 */     return EntityMinecartAbstract.EnumMinecartType.COMMAND_BLOCK;
/*     */   }
/*     */   
/*     */   public IBlockData x() {
/*  84 */     return Blocks.COMMAND_BLOCK.getBlockData();
/*     */   }
/*     */   
/*     */   public CommandBlockListenerAbstract getCommandBlock() {
/*  88 */     return this.c;
/*     */   }
/*     */   
/*     */   public void a(int i, int j, int k, boolean flag) {
/*  92 */     if (flag && this.ticksLived - this.d >= 4) {
/*  93 */       getCommandBlock().a(this.world);
/*  94 */       this.d = this.ticksLived;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b(EntityHuman entityhuman, EnumHand enumhand) {
/* 100 */     this.c.a(entityhuman);
/* 101 */     return false;
/*     */   }
/*     */   
/*     */   public void a(DataWatcherObject<?> datawatcherobject) {
/* 105 */     super.a(datawatcherobject);
/* 106 */     if (b.equals(datawatcherobject)) {
/*     */       try {
/* 108 */         this.c.b(getDataWatcher().<IChatBaseComponent>get(b));
/* 109 */       } catch (Throwable throwable) {}
/*     */     
/*     */     }
/* 112 */     else if (COMMAND.equals(datawatcherobject)) {
/* 113 */       this.c.setCommand(getDataWatcher().<String>get(COMMAND));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean bC() {
/* 119 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityMinecartCommandBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */