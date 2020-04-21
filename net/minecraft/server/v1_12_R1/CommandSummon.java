/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
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
/*     */ public class CommandSummon
/*     */   extends CommandAbstract
/*     */ {
/*     */   public String getCommand() {
/*  28 */     return "summon";
/*     */   }
/*     */ 
/*     */   
/*     */   public int a() {
/*  33 */     return 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getUsage(ICommandListener paramICommandListener) {
/*  38 */     return "commands.summon.usage";
/*     */   }
/*     */ 
/*     */   
/*     */   public void execute(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString) throws CommandException {
/*  43 */     if (paramArrayOfString.length < 1) {
/*  44 */       throw new ExceptionUsage("commands.summon.usage", new Object[0]);
/*     */     }
/*     */     
/*  47 */     String str = paramArrayOfString[0];
/*  48 */     BlockPosition blockPosition = paramICommandListener.getChunkCoordinates();
/*  49 */     Vec3D vec3D = paramICommandListener.d();
/*     */     
/*  51 */     double d1 = vec3D.x;
/*  52 */     double d2 = vec3D.y;
/*  53 */     double d3 = vec3D.z;
/*     */     
/*  55 */     if (paramArrayOfString.length >= 4) {
/*  56 */       d1 = b(d1, paramArrayOfString[1], true);
/*  57 */       d2 = b(d2, paramArrayOfString[2], false);
/*  58 */       d3 = b(d3, paramArrayOfString[3], true);
/*  59 */       blockPosition = new BlockPosition(d1, d2, d3);
/*     */     } 
/*     */     
/*  62 */     World world = paramICommandListener.getWorld();
/*  63 */     if (!world.isLoaded(blockPosition)) {
/*  64 */       throw new CommandException("commands.summon.outOfWorld", new Object[0]);
/*     */     }
/*     */ 
/*     */     
/*  68 */     if (EntityTypes.a.equals(new MinecraftKey(str))) {
/*  69 */       world.strikeLightning(new EntityLightning(world, d1, d2, d3, false));
/*  70 */       a(paramICommandListener, this, "commands.summon.success", new Object[0]);
/*     */       
/*     */       return;
/*     */     } 
/*  74 */     NBTTagCompound nBTTagCompound = new NBTTagCompound();
/*  75 */     boolean bool = false;
/*  76 */     if (paramArrayOfString.length >= 5) {
/*  77 */       String str1 = a(paramArrayOfString, 4);
/*     */       try {
/*  79 */         nBTTagCompound = MojangsonParser.parse(str1);
/*  80 */         bool = true;
/*  81 */       } catch (MojangsonParseException mojangsonParseException) {
/*  82 */         throw new CommandException("commands.summon.tagError", new Object[] { mojangsonParseException.getMessage() });
/*     */       } 
/*     */     } 
/*  85 */     nBTTagCompound.setString("id", str);
/*     */     
/*  87 */     Entity entity = ChunkRegionLoader.a(nBTTagCompound, world, d1, d2, d3, true);
/*     */     
/*  89 */     if (entity == null) {
/*  90 */       throw new CommandException("commands.summon.failed", new Object[0]);
/*     */     }
/*     */     
/*  93 */     entity.setPositionRotation(d1, d2, d3, entity.yaw, entity.pitch);
/*  94 */     if (!bool)
/*     */     {
/*  96 */       if (entity instanceof EntityInsentient) {
/*  97 */         ((EntityInsentient)entity).prepare(world.D(new BlockPosition(entity)), null);
/*     */       }
/*     */     }
/* 100 */     a(paramICommandListener, this, "commands.summon.success", new Object[0]);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> tabComplete(MinecraftServer paramMinecraftServer, ICommandListener paramICommandListener, String[] paramArrayOfString, @Nullable BlockPosition paramBlockPosition) {
/* 105 */     if (paramArrayOfString.length == 1)
/* 106 */       return a(paramArrayOfString, EntityTypes.a()); 
/* 107 */     if (paramArrayOfString.length > 1 && paramArrayOfString.length <= 4) {
/* 108 */       return a(paramArrayOfString, 1, paramBlockPosition);
/*     */     }
/*     */     
/* 111 */     return Collections.emptyList();
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CommandSummon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */