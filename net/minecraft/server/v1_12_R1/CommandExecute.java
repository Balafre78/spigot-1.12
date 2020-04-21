/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.command.ProxiedNativeCommandSender;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CommandExecute
/*     */   extends CommandAbstract
/*     */ {
/*     */   public String getCommand() {
/*  17 */     return "execute";
/*     */   }
/*     */   
/*     */   public int a() {
/*  21 */     return 2;
/*     */   }
/*     */   
/*     */   public String getUsage(ICommandListener icommandlistener) {
/*  25 */     return "commands.execute.usage";
/*     */   }
/*     */   
/*     */   public void execute(MinecraftServer minecraftserver, ICommandListener icommandlistener, String[] astring) throws CommandException {
/*  29 */     if (astring.length < 5) {
/*  30 */       throw new ExceptionUsage("commands.execute.usage", new Object[0]);
/*     */     }
/*  32 */     Entity entity = a(minecraftserver, icommandlistener, astring[0], Entity.class);
/*  33 */     double d0 = b(entity.locX, astring[1], false);
/*  34 */     double d1 = b(entity.locY, astring[2], false);
/*  35 */     double d2 = b(entity.locZ, astring[3], false);
/*     */ 
/*     */     
/*  38 */     byte b0 = 4;
/*     */     
/*  40 */     if ("detect".equals(astring[4]) && astring.length > 10) {
/*  41 */       World world = entity.getWorld();
/*  42 */       double d3 = b(d0, astring[5], false);
/*  43 */       double d4 = b(d1, astring[6], false);
/*  44 */       double d5 = b(d2, astring[7], false);
/*  45 */       Block block = b(icommandlistener, astring[8]);
/*  46 */       BlockPosition blockposition = new BlockPosition(d3, d4, d5);
/*     */       
/*  48 */       if (!world.isLoaded(blockposition)) {
/*  49 */         throw new CommandException("commands.execute.failed", new Object[] { "detect", entity.getName() });
/*     */       }
/*     */       
/*  52 */       IBlockData iblockdata = world.getType(blockposition);
/*     */       
/*  54 */       if (iblockdata.getBlock() != block) {
/*  55 */         throw new CommandException("commands.execute.failed", new Object[] { "detect", entity.getName() });
/*     */       }
/*     */       
/*  58 */       if (!CommandAbstract.b(block, astring[9]).apply(iblockdata)) {
/*  59 */         throw new CommandException("commands.execute.failed", new Object[] { "detect", entity.getName() });
/*     */       }
/*     */       
/*  62 */       b0 = 10;
/*     */     } 
/*     */     
/*  65 */     String s = a(astring, b0);
/*  66 */     CommandListenerWrapper commandlistenerwrapper = CommandListenerWrapper.a(icommandlistener).a(entity, new Vec3D(d0, d1, d2)).a(((WorldServer)minecraftserver.worlds.get(0)).getGameRules().getBoolean("commandBlockOutput"));
/*  67 */     minecraftserver.getCommandHandler();
/*     */ 
/*     */     
/*     */     try {
/*  71 */       CommandSender sender = CommandBlockListenerAbstract.unwrapSender(icommandlistener);
/*  72 */       int i = CommandBlockListenerAbstract.executeCommand(commandlistenerwrapper, (CommandSender)new ProxiedNativeCommandSender(commandlistenerwrapper, sender, (CommandSender)entity.getBukkitEntity()), s);
/*     */ 
/*     */       
/*  75 */       if (i < 1) {
/*  76 */         throw new CommandException("commands.execute.allInvocationsFailed", new Object[] { s });
/*     */       }
/*  78 */     } catch (Throwable throwable) {
/*     */       
/*  80 */       if (throwable instanceof CommandException) {
/*  81 */         throw (CommandException)throwable;
/*     */       }
/*     */       
/*  84 */       throw new CommandException("commands.execute.failed", new Object[] { s, entity.getName() });
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> tabComplete(MinecraftServer minecraftserver, ICommandListener icommandlistener, String[] astring, @Nullable BlockPosition blockposition) {
/*  90 */     return (astring.length == 1) ? a(astring, minecraftserver.getPlayers()) : ((astring.length > 1 && astring.length <= 4) ? a(astring, 1, blockposition) : ((astring.length > 5 && astring.length <= 8 && "detect".equals(astring[4])) ? a(astring, 5, blockposition) : ((astring.length == 9 && "detect".equals(astring[4])) ? a(astring, Block.REGISTRY.keySet()) : Collections.<String>emptyList())));
/*     */   }
/*     */   
/*     */   public boolean isListStart(String[] astring, int i) {
/*  94 */     return (i == 0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int compareTo(ICommand o) {
/* 100 */     return a(o);
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CommandExecute.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */