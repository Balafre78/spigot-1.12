/*     */ package net.minecraft.server.v1_12_R1;
/*     */ import com.google.common.base.Joiner;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.logging.Level;
/*     */ import org.bukkit.command.Command;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.command.ConsoleCommandSender;
/*     */ import org.bukkit.command.SimpleCommandMap;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.command.VanillaCommandWrapper;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.entity.CraftEntity;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.server.ServerCommandEvent;
/*     */ 
/*     */ public abstract class CommandBlockListenerAbstract implements ICommandListener {
/*  18 */   private static final SimpleDateFormat a = new SimpleDateFormat("HH:mm:ss");
/*  19 */   private long b = -1L;
/*     */   private boolean c = true;
/*     */   private int d;
/*     */   private boolean e = true;
/*     */   private IChatBaseComponent f;
/*  24 */   private String g = "";
/*  25 */   private String h = "@";
/*  26 */   private final CommandObjectiveExecutor i = new CommandObjectiveExecutor();
/*     */   
/*     */   protected CommandSender sender;
/*     */ 
/*     */   
/*     */   public int k() {
/*  32 */     return this.d;
/*     */   }
/*     */   
/*     */   public void a(int i) {
/*  36 */     this.d = i;
/*     */   }
/*     */   
/*     */   public IChatBaseComponent l() {
/*  40 */     return (this.f == null) ? new ChatComponentText("") : this.f;
/*     */   }
/*     */   
/*     */   public NBTTagCompound a(NBTTagCompound nbttagcompound) {
/*  44 */     nbttagcompound.setString("Command", this.g);
/*  45 */     nbttagcompound.setInt("SuccessCount", this.d);
/*  46 */     nbttagcompound.setString("CustomName", this.h);
/*  47 */     nbttagcompound.setBoolean("TrackOutput", this.e);
/*  48 */     if (this.f != null && this.e) {
/*  49 */       nbttagcompound.setString("LastOutput", IChatBaseComponent.ChatSerializer.a(this.f));
/*     */     }
/*     */     
/*  52 */     nbttagcompound.setBoolean("UpdateLastExecution", this.c);
/*  53 */     if (this.c && this.b > 0L) {
/*  54 */       nbttagcompound.setLong("LastExecution", this.b);
/*     */     }
/*     */     
/*  57 */     this.i.b(nbttagcompound);
/*  58 */     return nbttagcompound;
/*     */   }
/*     */   
/*     */   public void b(NBTTagCompound nbttagcompound) {
/*  62 */     this.g = nbttagcompound.getString("Command");
/*  63 */     this.d = nbttagcompound.getInt("SuccessCount");
/*  64 */     if (nbttagcompound.hasKeyOfType("CustomName", 8)) {
/*  65 */       this.h = nbttagcompound.getString("CustomName");
/*     */     }
/*     */     
/*  68 */     if (nbttagcompound.hasKeyOfType("TrackOutput", 1)) {
/*  69 */       this.e = nbttagcompound.getBoolean("TrackOutput");
/*     */     }
/*     */     
/*  72 */     if (nbttagcompound.hasKeyOfType("LastOutput", 8) && this.e) {
/*     */       try {
/*  74 */         this.f = IChatBaseComponent.ChatSerializer.a(nbttagcompound.getString("LastOutput"));
/*  75 */       } catch (Throwable throwable) {
/*  76 */         this.f = new ChatComponentText(throwable.getMessage());
/*     */       } 
/*     */     } else {
/*  79 */       this.f = null;
/*     */     } 
/*     */     
/*  82 */     if (nbttagcompound.hasKey("UpdateLastExecution")) {
/*  83 */       this.c = nbttagcompound.getBoolean("UpdateLastExecution");
/*     */     }
/*     */     
/*  86 */     if (this.c && nbttagcompound.hasKey("LastExecution")) {
/*  87 */       this.b = nbttagcompound.getLong("LastExecution");
/*     */     } else {
/*  89 */       this.b = -1L;
/*     */     } 
/*     */     
/*  92 */     this.i.a(nbttagcompound);
/*     */   }
/*     */   
/*     */   public boolean a(int i, String s) {
/*  96 */     return (i <= 2);
/*     */   }
/*     */   
/*     */   public void setCommand(String s) {
/* 100 */     this.g = s;
/* 101 */     this.d = 0;
/*     */   }
/*     */   
/*     */   public String getCommand() {
/* 105 */     return this.g;
/*     */   }
/*     */   
/*     */   public boolean a(World world) {
/* 109 */     if (!world.isClientSide && world.getTime() != this.b) {
/* 110 */       if ("Searge".equalsIgnoreCase(this.g)) {
/* 111 */         this.f = new ChatComponentText("#itzlipofutzli");
/* 112 */         this.d = 1;
/* 113 */         return true;
/*     */       } 
/* 115 */       MinecraftServer minecraftserver = C_();
/*     */       
/* 117 */       if (minecraftserver != null && minecraftserver.M() && minecraftserver.getEnableCommandBlock()) {
/*     */         try {
/* 119 */           this.f = null;
/*     */           
/* 121 */           this.d = executeSafely(this, this.sender, this.g);
/*     */         }
/* 123 */         catch (Throwable throwable) {
/* 124 */           CrashReport crashreport = CrashReport.a(throwable, "Executing command block");
/* 125 */           CrashReportSystemDetails crashreportsystemdetails = crashreport.a("Command to be executed");
/*     */           
/* 127 */           crashreportsystemdetails.a("Command", new CrashReportCallable<String>() {
/*     */                 public String a() throws Exception {
/* 129 */                   return CommandBlockListenerAbstract.this.getCommand();
/*     */                 }
/*     */                 
/*     */                 public Object call() throws Exception {
/* 133 */                   return a();
/*     */                 }
/*     */               });
/* 136 */           crashreportsystemdetails.a("Name", new CrashReportCallable<String>() {
/*     */                 public String a() throws Exception {
/* 138 */                   return CommandBlockListenerAbstract.this.getName();
/*     */                 }
/*     */                 
/*     */                 public Object call() throws Exception {
/* 142 */                   return a();
/*     */                 }
/*     */               });
/* 145 */           throw new ReportedException(crashreport);
/*     */         } 
/*     */       } else {
/* 148 */         this.d = 0;
/*     */       } 
/*     */       
/* 151 */       if (this.c) {
/* 152 */         this.b = world.getTime();
/*     */       } else {
/* 154 */         this.b = -1L;
/*     */       } 
/*     */       
/* 157 */       return true;
/*     */     } 
/*     */     
/* 160 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int executeSafely(ICommandListener sender, CommandSender bSender, String command) {
/*     */     try {
/* 166 */       return executeCommand(sender, bSender, command);
/* 167 */     } catch (CommandException commandexception) {
/*     */       
/* 169 */       ChatMessage chatmessage = new ChatMessage(commandexception.getMessage(), commandexception.getArgs());
/* 170 */       chatmessage.getChatModifier().setColor(EnumChatFormat.RED);
/* 171 */       sender.sendMessage(chatmessage);
/*     */ 
/*     */       
/* 174 */       return 0;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static int executeCommand(ICommandListener sender, CommandSender bSender, String command) throws CommandException {
/* 179 */     SimpleCommandMap commandMap = sender.getWorld().getServer().getCommandMap();
/* 180 */     Joiner joiner = Joiner.on(" ");
/* 181 */     if (command.startsWith("/")) {
/* 182 */       command = command.substring(1);
/*     */     }
/*     */     
/* 185 */     ServerCommandEvent event = new ServerCommandEvent(bSender, command);
/* 186 */     Bukkit.getPluginManager().callEvent((Event)event);
/* 187 */     if (event.isCancelled()) {
/* 188 */       return 0;
/*     */     }
/* 190 */     command = event.getCommand();
/*     */     
/* 192 */     String[] args = command.split(" ");
/* 193 */     ArrayList<String[]> commands = (ArrayList)new ArrayList<>();
/*     */     
/* 195 */     String cmd = args[0];
/* 196 */     if (cmd.startsWith("minecraft:")) cmd = cmd.substring("minecraft:".length()); 
/* 197 */     if (cmd.startsWith("bukkit:")) cmd = cmd.substring("bukkit:".length());
/*     */ 
/*     */     
/* 200 */     if (cmd.equalsIgnoreCase("stop") || cmd.equalsIgnoreCase("kick") || cmd.equalsIgnoreCase("op") || 
/* 201 */       cmd.equalsIgnoreCase("deop") || cmd.equalsIgnoreCase("ban") || cmd.equalsIgnoreCase("ban-ip") || 
/* 202 */       cmd.equalsIgnoreCase("pardon") || cmd.equalsIgnoreCase("pardon-ip") || cmd.equalsIgnoreCase("reload")) {
/* 203 */       return 0;
/*     */     }
/*     */ 
/*     */     
/* 207 */     Command commandBlockCommand = commandMap.getCommand(args[0]);
/* 208 */     if (sender.getWorld().getServer().getCommandBlockOverride(args[0])) {
/* 209 */       commandBlockCommand = commandMap.getCommand("minecraft:" + args[0]);
/*     */     }
/* 211 */     if (commandBlockCommand instanceof VanillaCommandWrapper) {
/* 212 */       command = command.trim();
/* 213 */       if (command.startsWith("/")) {
/* 214 */         command = command.substring(1);
/*     */       }
/* 216 */       String[] as = command.split(" ");
/* 217 */       as = VanillaCommandWrapper.dropFirstArgument(as);
/* 218 */       if (!((VanillaCommandWrapper)commandBlockCommand).testPermission(bSender)) {
/* 219 */         return 0;
/*     */       }
/* 221 */       return ((VanillaCommandWrapper)commandBlockCommand).dispatchVanillaCommand(bSender, sender, as);
/*     */     } 
/*     */ 
/*     */     
/* 225 */     if (commandMap.getCommand(args[0]) == null) {
/* 226 */       return 0;
/*     */     }
/*     */     
/* 229 */     commands.add(args);
/*     */ 
/*     */     
/* 232 */     WorldServer[] prev = (MinecraftServer.getServer()).worldServer;
/* 233 */     MinecraftServer server = MinecraftServer.getServer();
/* 234 */     server.worldServer = new WorldServer[server.worlds.size()];
/* 235 */     server.worldServer[0] = (WorldServer)sender.getWorld();
/* 236 */     int bpos = 0;
/* 237 */     for (int pos = 1; pos < server.worldServer.length; pos++) {
/* 238 */       WorldServer world = server.worlds.get(bpos++);
/* 239 */       if (server.worldServer[0] == world) {
/* 240 */         pos--;
/*     */       } else {
/*     */         
/* 243 */         server.worldServer[pos] = world;
/*     */       } 
/*     */     }  try {
/* 246 */       ArrayList<String[]> newCommands = (ArrayList)new ArrayList<>();
/* 247 */       for (int j = 0; j < args.length; j++) {
/* 248 */         if (PlayerSelector.isPattern(args[j])) {
/* 249 */           for (int k = 0; k < commands.size(); k++) {
/* 250 */             newCommands.addAll(buildCommands(sender, commands.get(k), j));
/*     */           }
/* 252 */           ArrayList<String[]> temp = commands;
/* 253 */           commands = newCommands;
/* 254 */           newCommands = temp;
/* 255 */           newCommands.clear();
/*     */         } 
/*     */       } 
/*     */     } finally {
/* 259 */       (MinecraftServer.getServer()).worldServer = prev;
/*     */     } 
/*     */     
/* 262 */     int completed = 0;
/*     */ 
/*     */     
/* 265 */     for (int i = 0; i < commands.size(); i++) {
/*     */       try {
/* 267 */         if (commandMap.dispatch(bSender, joiner.join(Arrays.asList(commands.get(i))))) {
/* 268 */           completed++;
/*     */         }
/* 270 */       } catch (Throwable exception) {
/* 271 */         if (sender.f() instanceof EntityMinecartCommandBlock) {
/* 272 */           (MinecraftServer.getServer()).server.getLogger().log(Level.WARNING, String.format("MinecartCommandBlock at (%d,%d,%d) failed to handle command", new Object[] { Integer.valueOf(sender.getChunkCoordinates().getX()), Integer.valueOf(sender.getChunkCoordinates().getY()), Integer.valueOf(sender.getChunkCoordinates().getZ()) }), exception);
/* 273 */         } else if (sender instanceof CommandBlockListenerAbstract) {
/* 274 */           CommandBlockListenerAbstract listener = (CommandBlockListenerAbstract)sender;
/* 275 */           (MinecraftServer.getServer()).server.getLogger().log(Level.WARNING, String.format("CommandBlock at (%d,%d,%d) failed to handle command", new Object[] { Integer.valueOf(listener.getChunkCoordinates().getX()), Integer.valueOf(listener.getChunkCoordinates().getY()), Integer.valueOf(listener.getChunkCoordinates().getZ()) }), exception);
/*     */         } else {
/* 277 */           (MinecraftServer.getServer()).server.getLogger().log(Level.WARNING, String.format("Unknown CommandBlock failed to handle command", new Object[0]), exception);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 282 */     return completed;
/*     */   }
/*     */   
/*     */   private static ArrayList<String[]> buildCommands(ICommandListener sender, String[] args, int pos) throws CommandException {
/* 286 */     ArrayList<String[]> commands = (ArrayList)new ArrayList<>();
/* 287 */     List<EntityPlayer> players = PlayerSelector.getPlayers(sender, args[pos], EntityPlayer.class);
/*     */     
/* 289 */     if (players != null) {
/* 290 */       for (EntityPlayer player : players) {
/* 291 */         if (player.world != sender.getWorld()) {
/*     */           continue;
/*     */         }
/* 294 */         String[] command = (String[])args.clone();
/* 295 */         command[pos] = player.getName();
/* 296 */         commands.add(command);
/*     */       } 
/*     */     }
/*     */     
/* 300 */     return commands;
/*     */   }
/*     */   public static CommandSender unwrapSender(ICommandListener listener) {
/*     */     CraftEntity craftEntity;
/* 304 */     CommandSender sender = null;
/* 305 */     while (sender == null) {
/* 306 */       if (listener instanceof DedicatedServer) {
/* 307 */         ConsoleCommandSender consoleCommandSender = ((DedicatedServer)listener).console; continue;
/* 308 */       }  if (listener instanceof RemoteControlCommandListener) {
/* 309 */         RemoteConsoleCommandSender remoteConsoleCommandSender = ((DedicatedServer)listener).remoteConsole; continue;
/* 310 */       }  if (listener instanceof CommandBlockListenerAbstract) {
/* 311 */         sender = ((CommandBlockListenerAbstract)listener).sender; continue;
/* 312 */       }  if (listener instanceof CustomFunctionData.CustomFunctionListener) {
/* 313 */         sender = ((CustomFunctionData.CustomFunctionListener)listener).sender; continue;
/* 314 */       }  if (listener instanceof CommandListenerWrapper) {
/* 315 */         listener = ((CommandListenerWrapper)listener).base; continue;
/* 316 */       }  if (VanillaCommandWrapper.lastSender != null) {
/* 317 */         sender = VanillaCommandWrapper.lastSender; continue;
/* 318 */       }  if (listener.f() != null) {
/* 319 */         craftEntity = listener.f().getBukkitEntity(); continue;
/*     */       } 
/* 321 */       throw new RuntimeException("Unhandled executor " + listener.getClass().getSimpleName());
/*     */     } 
/*     */ 
/*     */     
/* 325 */     return (CommandSender)craftEntity;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/* 330 */     return this.h;
/*     */   }
/*     */   
/*     */   public void setName(String s) {
/* 334 */     this.h = s;
/*     */   }
/*     */   
/*     */   public void sendMessage(IChatBaseComponent ichatbasecomponent) {
/* 338 */     if (this.e && getWorld() != null && !(getWorld()).isClientSide) {
/* 339 */       this.f = (new ChatComponentText("[" + a.format(new Date()) + "] ")).addSibling(ichatbasecomponent);
/* 340 */       i();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getSendCommandFeedback() {
/* 346 */     MinecraftServer minecraftserver = C_();
/*     */     
/* 348 */     return !(minecraftserver != null && minecraftserver.M() && !minecraftserver.worldServer[0].getGameRules().getBoolean("commandBlockOutput"));
/*     */   }
/*     */   
/*     */   public void a(CommandObjectiveExecutor.EnumCommandResult commandobjectiveexecutor_enumcommandresult, int i) {
/* 352 */     this.i.a(C_(), this, commandobjectiveexecutor_enumcommandresult, i);
/*     */   }
/*     */   
/*     */   public abstract void i();
/*     */   
/*     */   public void b(@Nullable IChatBaseComponent ichatbasecomponent) {
/* 358 */     this.f = ichatbasecomponent;
/*     */   }
/*     */   
/*     */   public void a(boolean flag) {
/* 362 */     this.e = flag;
/*     */   }
/*     */   
/*     */   public boolean n() {
/* 366 */     return this.e;
/*     */   }
/*     */   
/*     */   public boolean a(EntityHuman entityhuman) {
/* 370 */     if (!entityhuman.isCreativeAndOp()) {
/* 371 */       return false;
/*     */     }
/* 373 */     if ((entityhuman.getWorld()).isClientSide) {
/* 374 */       entityhuman.a(this);
/*     */     }
/*     */     
/* 377 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public CommandObjectiveExecutor o() {
/* 382 */     return this.i;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CommandBlockListenerAbstract.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */