/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crudpi.services;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import javafx.scene.image.ImageView;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.awt.image.BufferedImage;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ANAS
 */
public class qrcode {
    
    public static ImageView generateQRCode(String compteinfo)  {
        String qrCodeData = compteinfo;

        // Set QR code parameters
        int size = 250;

        // Generate QR code
        QRCodeWriter qrWriter = new QRCodeWriter();
        BitMatrix bitMatrix;
        try {
            bitMatrix = qrWriter.encode(qrCodeData, BarcodeFormat.QR_CODE, size, size);
            BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);
            // Convert BufferedImage to JavaFX Image
            Image fxImage = SwingFXUtils.toFXImage(image, null);
            // Create ImageView object and set its image property
            ImageView imageView = new ImageView(fxImage);
            return imageView;
        } catch (WriterException ex) {
            Logger.getLogger(qrcode.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    } 
    
}
