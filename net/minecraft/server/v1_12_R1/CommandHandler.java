/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class CommandHandler
/*     */   implements ICommandHandler
/*     */ {
/*  24 */   private static final Logger a = LogManager.getLogger();
/*  25 */   private final Map<String, ICommand> b = Maps.newHashMap();
/*  26 */   private final Set<ICommand> c = Sets.newHashSet();
/*     */ 
/*     */   
/*     */   public int a(ICommandListener paramICommandListener, String paramString) {
/*  30 */     paramString = paramString.trim();
/*  31 */     if (paramString.startsWith("/")) {
/*  32 */       paramString = paramString.substring(1);
/*     */     }
/*     */     
/*  35 */     String[] arrayOfString = paramString.split(" ");
/*  36 */     String str = arrayOfString[0];
/*     */     
/*  38 */     arrayOfString = a(arrayOfString);
/*     */     
/*  40 */     ICommand iCommand = this.b.get(str);
/*  41 */     byte b = 0;
/*     */     try {
/*  43 */       int i = a(iCommand, arrayOfString);
/*  44 */       if (iCommand == null) {
/*  45 */         ChatMessage chatMessage = new ChatMessage("commands.generic.notFound", new Object[0]);
/*  46 */         chatMessage.getChatModifier().setColor(EnumChatFormat.RED);
/*  47 */         paramICommandListener.sendMessage(chatMessage);
/*     */       }
/*  49 */       else if (iCommand.canUse(a(), paramICommandListener)) {
/*  50 */         if (i > -1) {
/*     */           
/*  52 */           List<Entity> list = PlayerSelector.getPlayers(paramICommandListener, arrayOfString[i], Entity.class);
/*  53 */           String str1 = arrayOfString[i];
/*  54 */           paramICommandListener.a(CommandObjectiveExecutor.EnumCommandResult.AFFECTED_ENTITIES, list.size());
/*     */           
/*  56 */           if (list.isEmpty()) {
/*  57 */             throw new ExceptionPlayerNotFound("commands.generic.selector.notFound", new Object[] { arrayOfString[i] });
/*     */           }
/*  59 */           for (Entity entity : list) {
/*  60 */             arrayOfString[i] = entity.bn();
/*     */             
/*  62 */             if (a(paramICommandListener, arrayOfString, iCommand, paramString)) {
/*  63 */               b++;
/*     */             }
/*     */           } 
/*     */           
/*  67 */           arrayOfString[i] = str1;
/*     */         } else {
/*  69 */           paramICommandListener.a(CommandObjectiveExecutor.EnumCommandResult.AFFECTED_ENTITIES, 1);
/*  70 */           if (a(paramICommandListener, arrayOfString, iCommand, paramString)) {
/*  71 */             b++;
/*     */           }
/*     */         } 
/*     */       } else {
/*  75 */         ChatMessage chatMessage = new ChatMessage("commands.generic.permission", new Object[0]);
/*  76 */         chatMessage.getChatModifier().setColor(EnumChatFormat.RED);
/*  77 */         paramICommandListener.sendMessage(chatMessage);
/*     */       }
/*     */     
/*  80 */     } catch (CommandException commandException) {
/*  81 */       ChatMessage chatMessage = new ChatMessage(commandException.getMessage(), commandException.getArgs());
/*  82 */       chatMessage.getChatModifier().setColor(EnumChatFormat.RED);
/*  83 */       paramICommandListener.sendMessage(chatMessage);
/*     */     } 
/*     */     
/*  86 */     paramICommandListener.a(CommandObjectiveExecutor.EnumCommandResult.SUCCESS_COUNT, b);
/*  87 */     return b;
/*     */   }
/*     */   
/*     */   protected boolean a(ICommandListener paramICommandListener, String[] paramArrayOfString, ICommand paramICommand, String paramString) {
/*     */     try {
/*  92 */       paramICommand.execute(a(), paramICommandListener, paramArrayOfString);
/*  93 */       return true;
/*  94 */     } catch (ExceptionUsage exceptionUsage) {
/*  95 */       ChatMessage chatMessage = new ChatMessage("commands.generic.usage", new Object[] { new ChatMessage(exceptionUsage.getMessage(), exceptionUsage.getArgs()) });
/*  96 */       chatMessage.getChatModifier().setColor(EnumChatFormat.RED);
/*  97 */       paramICommandListener.sendMessage(chatMessage);
/*  98 */     } catch (CommandException commandException) {
/*  99 */       ChatMessage chatMessage = new ChatMessage(commandException.getMessage(), commandException.getArgs());
/* 100 */       chatMessage.getChatModifier().setColor(EnumChatFormat.RED);
/* 101 */       paramICommandListener.sendMessage(chatMessage);
/* 102 */     } catch (Throwable throwable) {
/* 103 */       ChatMessage chatMessage = new ChatMessage("commands.generic.exception", new Object[0]);
/* 104 */       chatMessage.getChatModifier().setColor(EnumChatFormat.RED);
/* 105 */       paramICommandListener.sendMessage(chatMessage);
/* 106 */       a.warn("Couldn't process command: " + paramString, throwable);
/*     */     } 
/*     */     
/* 109 */     return false;
/*     */   }
/*     */   
/*     */   protected abstract MinecraftServer a();
/*     */   
/*     */   public ICommand a(ICommand paramICommand) {
/* 115 */     this.b.put(paramICommand.getCommand(), paramICommand);
/* 116 */     this.c.add(paramICommand);
/*     */     
/* 118 */     for (String str : paramICommand.getAliases()) {
/* 119 */       ICommand iCommand = this.b.get(str);
/*     */       
/* 121 */       if (iCommand != null && iCommand.getCommand().equals(str)) {
/*     */         continue;
/*     */       }
/*     */       
/* 125 */       this.b.put(str, paramICommand);
/*     */     } 
/*     */     
/* 128 */     return paramICommand;
/*     */   }
/*     */   
/*     */   private static String[] a(String[] paramArrayOfString) {
/* 132 */     String[] arrayOfString = new String[paramArrayOfString.length - 1];
/* 133 */     System.arraycopy(paramArrayOfString, 1, arrayOfString, 0, paramArrayOfString.length - 1);
/* 134 */     return arrayOfString;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> a(ICommandListener paramICommandListener, String paramString, @Nullable BlockPosition paramBlockPosition) {
/* 139 */     String[] arrayOfString = paramString.split(" ", -1);
/* 140 */     String str = arrayOfString[0];
/*     */     
/* 142 */     if (arrayOfString.length == 1) {
/*     */       
/* 144 */       ArrayList<String> arrayList = Lists.newArrayList();
/*     */       
/* 146 */       for (Map.Entry<String, ICommand> entry : this.b.entrySet()) {
/* 147 */         if (CommandAbstract.a(str, (String)entry.getKey()) && ((ICommand)entry.getValue()).canUse(a(), paramICommandListener)) {
/* 148 */           arrayList.add(entry.getKey());
/*     */         }
/*     */       } 
/*     */       
/* 152 */       return arrayList;
/* 153 */     }  if (arrayOfString.length > 1) {
/*     */       
/* 155 */       ICommand iCommand = this.b.get(str);
/*     */       
/* 157 */       if (iCommand != null && iCommand.canUse(a(), paramICommandListener)) {
/* 158 */         return iCommand.tabComplete(a(), paramICommandListener, a(arrayOfString), paramBlockPosition);
/*     */       }
/*     */     } 
/*     */     
/* 162 */     return Collections.emptyList();
/*     */   }
/*     */ 
/*     */   
/*     */   public List<ICommand> a(ICommandListener paramICommandListener) {
/* 167 */     ArrayList<ICommand> arrayList = Lists.newArrayList();
/*     */     
/* 169 */     for (ICommand iCommand : this.c) {
/* 170 */       if (iCommand.canUse(a(), paramICommandListener)) {
/* 171 */         arrayList.add(iCommand);
/*     */       }
/*     */     } 
/*     */     
/* 175 */     return arrayList;
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<String, ICommand> getCommands() {
/* 180 */     return this.b;
/*     */   }
/*     */   
/*     */   private int a(ICommand paramICommand, String[] paramArrayOfString) throws CommandException {
/* 184 */     if (paramICommand == null) {
/* 185 */       return -1;
/*     */     }
/*     */     
/* 188 */     for (byte b = 0; b < paramArrayOfString.length; b++) {
/* 189 */       if (paramICommand.isListStart(paramArrayOfString, b) && PlayerSelector.isList(paramArrayOfString[b])) {
/* 190 */         return b;
/*     */       }
/*     */     } 
/*     */     
/* 194 */     return -1;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CommandHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */