/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.util.concurrent.ListeningExecutorService;
/*     */ import com.google.common.util.concurrent.MoreExecutors;
/*     */ import com.google.common.util.concurrent.ThreadFactoryBuilder;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.net.HttpURLConnection;
/*     */ import java.net.Proxy;
/*     */ import java.net.URL;
/*     */ import java.net.URLEncoder;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.Executors;
/*     */ import java.util.concurrent.atomic.AtomicInteger;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class HttpUtilities
/*     */ {
/*  33 */   public static final ListeningExecutorService a = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool((new ThreadFactoryBuilder()).setDaemon(true).setNameFormat("Downloader %d").build()));
/*  34 */   private static final AtomicInteger b = new AtomicInteger(0);
/*  35 */   private static final Logger c = LogManager.getLogger();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String a(Map<String, Object> paramMap) {
/*  41 */     StringBuilder stringBuilder = new StringBuilder();
/*     */     
/*  43 */     for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
/*  44 */       if (stringBuilder.length() > 0) {
/*  45 */         stringBuilder.append('&');
/*     */       }
/*     */       
/*     */       try {
/*  49 */         stringBuilder.append(URLEncoder.encode((String)entry.getKey(), "UTF-8"));
/*  50 */       } catch (UnsupportedEncodingException unsupportedEncodingException) {
/*  51 */         unsupportedEncodingException.printStackTrace();
/*     */       } 
/*     */       
/*  54 */       if (entry.getValue() != null) {
/*  55 */         stringBuilder.append('=');
/*     */         try {
/*  57 */           stringBuilder.append(URLEncoder.encode(entry.getValue().toString(), "UTF-8"));
/*  58 */         } catch (UnsupportedEncodingException unsupportedEncodingException) {
/*  59 */           unsupportedEncodingException.printStackTrace();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  64 */     return stringBuilder.toString();
/*     */   }
/*     */   
/*     */   public static String a(URL paramURL, Map<String, Object> paramMap, boolean paramBoolean, @Nullable Proxy paramProxy) {
/*  68 */     return a(paramURL, a(paramMap), paramBoolean, paramProxy);
/*     */   }
/*     */   
/*     */   private static String a(URL paramURL, String paramString, boolean paramBoolean, @Nullable Proxy paramProxy) {
/*     */     try {
/*  73 */       if (paramProxy == null) {
/*  74 */         paramProxy = Proxy.NO_PROXY;
/*     */       }
/*  76 */       HttpURLConnection httpURLConnection = (HttpURLConnection)paramURL.openConnection(paramProxy);
/*  77 */       httpURLConnection.setRequestMethod("POST");
/*  78 */       httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
/*     */       
/*  80 */       httpURLConnection.setRequestProperty("Content-Length", "" + (paramString.getBytes()).length);
/*  81 */       httpURLConnection.setRequestProperty("Content-Language", "en-US");
/*     */       
/*  83 */       httpURLConnection.setUseCaches(false);
/*  84 */       httpURLConnection.setDoInput(true);
/*  85 */       httpURLConnection.setDoOutput(true);
/*     */ 
/*     */       
/*  88 */       DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
/*  89 */       dataOutputStream.writeBytes(paramString);
/*  90 */       dataOutputStream.flush();
/*  91 */       dataOutputStream.close();
/*     */ 
/*     */       
/*  94 */       BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
/*     */       
/*  96 */       StringBuffer stringBuffer = new StringBuffer();
/*     */       String str;
/*  98 */       while ((str = bufferedReader.readLine()) != null) {
/*  99 */         stringBuffer.append(str);
/* 100 */         stringBuffer.append('\r');
/*     */       } 
/*     */       
/* 103 */       bufferedReader.close();
/* 104 */       return stringBuffer.toString();
/* 105 */     } catch (Exception exception) {
/* 106 */       if (!paramBoolean) {
/* 107 */         c.error("Could not post to {}", paramURL, exception);
/*     */       }
/* 109 */       return "";
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\HttpUtilities.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */