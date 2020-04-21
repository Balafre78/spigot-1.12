/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.security.GeneralSecurityException;
/*     */ import java.security.InvalidKeyException;
/*     */ import java.security.Key;
/*     */ import java.security.KeyFactory;
/*     */ import java.security.KeyPair;
/*     */ import java.security.KeyPairGenerator;
/*     */ import java.security.MessageDigest;
/*     */ import java.security.NoSuchAlgorithmException;
/*     */ import java.security.PrivateKey;
/*     */ import java.security.PublicKey;
/*     */ import java.security.spec.InvalidKeySpecException;
/*     */ import java.security.spec.X509EncodedKeySpec;
/*     */ import javax.crypto.BadPaddingException;
/*     */ import javax.crypto.Cipher;
/*     */ import javax.crypto.IllegalBlockSizeException;
/*     */ import javax.crypto.NoSuchPaddingException;
/*     */ import javax.crypto.SecretKey;
/*     */ import javax.crypto.spec.IvParameterSpec;
/*     */ import javax.crypto.spec.SecretKeySpec;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MinecraftEncryption
/*     */ {
/*  30 */   private static final Logger a = LogManager.getLogger();
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
/*     */   public static KeyPair b() {
/*     */     try {
/*  51 */       KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
/*  52 */       keyPairGenerator.initialize(1024);
/*     */       
/*  54 */       return keyPairGenerator.generateKeyPair();
/*  55 */     } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
/*  56 */       noSuchAlgorithmException.printStackTrace();
/*     */       
/*  58 */       a.error("Key pair generation failed!");
/*  59 */       return null;
/*     */     } 
/*     */   }
/*     */   public static byte[] a(String paramString, PublicKey paramPublicKey, SecretKey paramSecretKey) {
/*     */     try {
/*  64 */       return a("SHA-1", new byte[][] { paramString
/*     */             
/*  66 */             .getBytes("ISO_8859_1"), paramSecretKey
/*  67 */             .getEncoded(), paramPublicKey
/*  68 */             .getEncoded() });
/*     */     }
/*  70 */     catch (UnsupportedEncodingException unsupportedEncodingException) {
/*  71 */       unsupportedEncodingException.printStackTrace();
/*     */ 
/*     */       
/*  74 */       return null;
/*     */     } 
/*     */   }
/*     */   private static byte[] a(String paramString, byte[]... paramVarArgs) {
/*     */     try {
/*  79 */       MessageDigest messageDigest = MessageDigest.getInstance(paramString);
/*  80 */       for (byte[] arrayOfByte : paramVarArgs) {
/*  81 */         messageDigest.update(arrayOfByte);
/*     */       }
/*  83 */       return messageDigest.digest();
/*  84 */     } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
/*  85 */       noSuchAlgorithmException.printStackTrace();
/*     */ 
/*     */       
/*  88 */       return null;
/*     */     } 
/*     */   }
/*     */   public static PublicKey a(byte[] paramArrayOfbyte) {
/*     */     
/*  93 */     try { X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(paramArrayOfbyte);
/*  94 */       KeyFactory keyFactory = KeyFactory.getInstance("RSA");
/*  95 */       return keyFactory.generatePublic(x509EncodedKeySpec); }
/*  96 */     catch (NoSuchAlgorithmException noSuchAlgorithmException) {  }
/*  97 */     catch (InvalidKeySpecException invalidKeySpecException) {}
/*     */     
/*  99 */     a.error("Public key reconstitute failed!");
/* 100 */     return null;
/*     */   }
/*     */   
/*     */   public static SecretKey a(PrivateKey paramPrivateKey, byte[] paramArrayOfbyte) {
/* 104 */     return new SecretKeySpec(b(paramPrivateKey, paramArrayOfbyte), "AES");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static byte[] b(Key paramKey, byte[] paramArrayOfbyte) {
/* 112 */     return a(2, paramKey, paramArrayOfbyte);
/*     */   }
/*     */   
/*     */   private static byte[] a(int paramInt, Key paramKey, byte[] paramArrayOfbyte) {
/*     */     try {
/* 117 */       return a(paramInt, paramKey.getAlgorithm(), paramKey).doFinal(paramArrayOfbyte);
/* 118 */     } catch (IllegalBlockSizeException illegalBlockSizeException) {
/* 119 */       illegalBlockSizeException.printStackTrace();
/* 120 */     } catch (BadPaddingException badPaddingException) {
/* 121 */       badPaddingException.printStackTrace();
/*     */     } 
/* 123 */     a.error("Cipher data failed!");
/* 124 */     return null;
/*     */   }
/*     */   
/*     */   private static Cipher a(int paramInt, String paramString, Key paramKey) {
/*     */     try {
/* 129 */       Cipher cipher = Cipher.getInstance(paramString);
/* 130 */       cipher.init(paramInt, paramKey);
/* 131 */       return cipher;
/* 132 */     } catch (InvalidKeyException invalidKeyException) {
/* 133 */       invalidKeyException.printStackTrace();
/* 134 */     } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
/* 135 */       noSuchAlgorithmException.printStackTrace();
/* 136 */     } catch (NoSuchPaddingException noSuchPaddingException) {
/* 137 */       noSuchPaddingException.printStackTrace();
/*     */     } 
/* 139 */     a.error("Cipher creation failed!");
/* 140 */     return null;
/*     */   }
/*     */   
/*     */   public static Cipher a(int paramInt, Key paramKey) {
/*     */     try {
/* 145 */       Cipher cipher = Cipher.getInstance("AES/CFB8/NoPadding");
/* 146 */       cipher.init(paramInt, paramKey, new IvParameterSpec(paramKey.getEncoded()));
/* 147 */       return cipher;
/* 148 */     } catch (GeneralSecurityException generalSecurityException) {
/* 149 */       throw new RuntimeException(generalSecurityException);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\MinecraftEncryption.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */