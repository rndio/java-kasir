/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DashboardKasir;

/**
 *
 * @author MrSimamora
 */

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

public class PrintableReceipt implements Printable{
    private final javax.swing.JFrame Receipt;

    public PrintableReceipt(javax.swing.JFrame frameToPrint) {
        this.Receipt = frameToPrint;
    }

    @Override
    public int print(Graphics g, PageFormat pf, int pageIndex) throws PrinterException {
        if (pageIndex > 0) {
            return Printable.NO_SUCH_PAGE;
        }

        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());

        // Print the contents of the JFrame
        Receipt.printAll(g);

        return Printable.PAGE_EXISTS;
    }
}
