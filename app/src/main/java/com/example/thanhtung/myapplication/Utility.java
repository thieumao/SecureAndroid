package com.example.thanhtung.myapplication;

import android.util.Base64;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class Utility
{
    private Cipher cipher;
    SecretKeySpec keySpec = new SecretKeySpec("76dcaa023162fdb1acca24b28bc54882".getBytes(), "AES");

    public static String MD5(String paramString)
    {
        try
        {
            MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
            localMessageDigest.update(paramString.getBytes());
            byte[] arrayOfByte = localMessageDigest.digest();
            StringBuffer localStringBuffer = new StringBuffer();
            for (int i = 0; i < arrayOfByte.length; i++)
            {
                String str2 = Integer.toHexString(0xFF & arrayOfByte[i]);
                if (str2.length() == 1)
                    localStringBuffer.append("0");
                localStringBuffer.append(str2);
            }
            String str1 = localStringBuffer.toString();
            return str1;
        }
        catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
        {
        }
        return "";
    }

    public boolean check_valid_pin(String paramString)
    {
        return (paramString.length() > 3) && (paramString.length() < 7);
    }

    public String decrypt(String paramString)
    {
        byte[] arrayOfByte = Base64.decode(paramString.getBytes(), 0);
        try
        {
            this.cipher = Cipher.getInstance("AES");
            this.cipher.init(2, this.keySpec);
            String str = new String(this.cipher.doFinal(arrayOfByte), "UTF-8");
            return str;
        }
        catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
        {
            localNoSuchAlgorithmException.printStackTrace();
            return null;
        }
        catch (NoSuchPaddingException localNoSuchPaddingException)
        {
            while (true)
                localNoSuchPaddingException.printStackTrace();
        }
        catch (InvalidKeyException localInvalidKeyException)
        {
            while (true)
                localInvalidKeyException.printStackTrace();
        }
        catch (UnsupportedEncodingException localUnsupportedEncodingException)
        {
            while (true)
                localUnsupportedEncodingException.printStackTrace();
        }
        catch (IllegalBlockSizeException localIllegalBlockSizeException)
        {
            while (true)
                localIllegalBlockSizeException.printStackTrace();
        }
        catch (BadPaddingException localBadPaddingException)
        {
            while (true)
                localBadPaddingException.printStackTrace();
        }
    }

    public String encrypt(String paramString)
    {
        try
        {
            this.cipher = Cipher.getInstance("AES");
            this.cipher.init(1, this.keySpec);
            String str = Base64.encodeToString(this.cipher.doFinal(paramString.getBytes()), 0);
            return str;
        }
        catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
        {
            localNoSuchAlgorithmException.printStackTrace();
            return null;
        }
        catch (NoSuchPaddingException localNoSuchPaddingException)
        {
            while (true)
                localNoSuchPaddingException.printStackTrace();
        }
        catch (InvalidKeyException localInvalidKeyException)
        {
            while (true)
                localInvalidKeyException.printStackTrace();
        }
        catch (IllegalBlockSizeException localIllegalBlockSizeException)
        {
            while (true)
                localIllegalBlockSizeException.printStackTrace();
        }
        catch (BadPaddingException localBadPaddingException)
        {
            while (true)
                localBadPaddingException.printStackTrace();
        }
    }

    public String xor(String paramString1, String paramString2)
    {
        String str = paramString2.substring(0, paramString1.length());
        return new BigInteger(paramString1, 16).xor(new BigInteger(str, 16)).toString(16);
    }
}
