/*    */ package net.minecraft.server.v1_12_R1;
/*    */ import java.io.BufferedInputStream;
/*    */ import java.io.BufferedOutputStream;
/*    */ import java.io.DataInput;
/*    */ import java.io.DataInputStream;
/*    */ import java.io.DataOutput;
/*    */ import java.io.DataOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.io.OutputStream;
/*    */ import java.util.zip.GZIPInputStream;
/*    */ import java.util.zip.GZIPOutputStream;
/*    */ import org.spigotmc.LimitStream;
/*    */ 
/*    */ public class NBTCompressedStreamTools {
/*    */   public static NBTTagCompound a(InputStream inputstream) throws IOException {
/*    */     NBTTagCompound nbttagcompound;
/* 18 */     DataInputStream datainputstream = new DataInputStream(new BufferedInputStream(new GZIPInputStream(inputstream)));
/*    */ 
/*    */ 
/*    */     
/*    */     try {
/* 23 */       nbttagcompound = a(datainputstream, NBTReadLimiter.a);
/*    */     } finally {
/* 25 */       datainputstream.close();
/*    */     } 
/*    */     
/* 28 */     return nbttagcompound;
/*    */   }
/*    */   
/*    */   public static void a(NBTTagCompound nbttagcompound, OutputStream outputstream) throws IOException {
/* 32 */     DataOutputStream dataoutputstream = new DataOutputStream(new BufferedOutputStream(new GZIPOutputStream(outputstream)));
/*    */     
/*    */     try {
/* 35 */       a(nbttagcompound, dataoutputstream);
/*    */     } finally {
/* 37 */       dataoutputstream.close();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static NBTTagCompound a(DataInputStream datainputstream) throws IOException {
/* 43 */     return a(datainputstream, NBTReadLimiter.a);
/*    */   }
/*    */ 
/*    */   
/*    */   public static NBTTagCompound a(DataInput datainput, NBTReadLimiter nbtreadlimiter) throws IOException {
/* 48 */     if (datainput instanceof io.netty.buffer.ByteBufInputStream)
/*    */     {
/* 50 */       datainput = new DataInputStream((InputStream)new LimitStream((InputStream)datainput, nbtreadlimiter));
/*    */     }
/*    */     
/* 53 */     NBTBase nbtbase = a(datainput, 0, nbtreadlimiter);
/*    */     
/* 55 */     if (nbtbase instanceof NBTTagCompound) {
/* 56 */       return (NBTTagCompound)nbtbase;
/*    */     }
/* 58 */     throw new IOException("Root tag must be a named compound tag");
/*    */   }
/*    */ 
/*    */   
/*    */   public static void a(NBTTagCompound nbttagcompound, DataOutput dataoutput) throws IOException {
/* 63 */     a(nbttagcompound, dataoutput);
/*    */   }
/*    */   
/*    */   private static void a(NBTBase nbtbase, DataOutput dataoutput) throws IOException {
/* 67 */     dataoutput.writeByte(nbtbase.getTypeId());
/* 68 */     if (nbtbase.getTypeId() != 0) {
/* 69 */       dataoutput.writeUTF("");
/* 70 */       nbtbase.write(dataoutput);
/*    */     } 
/*    */   }
/*    */   
/*    */   private static NBTBase a(DataInput datainput, int i, NBTReadLimiter nbtreadlimiter) throws IOException {
/* 75 */     byte b0 = datainput.readByte();
/*    */     
/* 77 */     if (b0 == 0) {
/* 78 */       return new NBTTagEnd();
/*    */     }
/* 80 */     datainput.readUTF();
/* 81 */     NBTBase nbtbase = NBTBase.createTag(b0);
/*    */     
/*    */     try {
/* 84 */       nbtbase.load(datainput, i, nbtreadlimiter);
/* 85 */       return nbtbase;
/* 86 */     } catch (IOException ioexception) {
/* 87 */       CrashReport crashreport = CrashReport.a(ioexception, "Loading NBT data");
/* 88 */       CrashReportSystemDetails crashreportsystemdetails = crashreport.a("NBT Tag");
/*    */       
/* 90 */       crashreportsystemdetails.a("Tag type", Byte.valueOf(b0));
/* 91 */       throw new ReportedException(crashreport);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\NBTCompressedStreamTools.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */