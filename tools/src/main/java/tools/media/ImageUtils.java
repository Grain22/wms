package tools.media;

import javax.imageio.ImageIO;
import javax.print.Doc;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author laowu
 */
public class ImageUtils {

    public static final String jpg = "jpg";
    public static final String png = "png";

    private static final String pic = "F:\\interesting\\987852-164246395.png";
    private static final String tem = "F:\\interesting\\save.png";
    private static final String tems = "F:\\interesting\\";

    public static void main(String[] args) throws IOException {
        BufferedImage a = cleanFormat(getImg(pic));
        writePng(addWord(a,"rewqrewqreqwrewq",new Font("宋体",Font.PLAIN,40),600,600), tem);
    }

    /**
     * 简单文字添加
     * @param bufferedImage
     * @param s
     * @param font
     * @param x
     * @param y
     * @return
     */
    private static BufferedImage addWord(BufferedImage bufferedImage, String s, Font font,int x,int y) {
        Graphics2D graphics = bufferedImage.createGraphics();
        graphics.setColor(Color.BLACK);
        graphics.setFont(font);
        graphics.drawString(s,x,y);
        return bufferedImage;
    }

    private static BufferedImage addPic(BufferedImage base, BufferedImage add, int x, int y) {
        Graphics2D graphics = base.createGraphics();
        graphics.drawImage(add, x, y, null);
        graphics.dispose();
        return base;
    }

    /**
     * 裁剪
     * @param bufferedImage
     * @param x
     * @param y
     * @param width
     * @param height
     * @return
     */
    private static BufferedImage sub(BufferedImage bufferedImage, int x, int y, int width, int height) {
        return bufferedImage.getSubimage(x, y, width, height);
    }

    /**
     * 缩放图片
     * @param bufferedImage
     * @param width 缩放后宽度
     * @param height 缩放后高度
     * @return
     */
    public static BufferedImage resize(BufferedImage bufferedImage,  int width, int height){
        BufferedImage image = getTranslucent(width, height);
        Graphics graphics = image.getGraphics();
        graphics.drawImage(bufferedImage, 0, 0, width, height, null);
        graphics.dispose();
        return image;
    }


    /**
     * 有损旋转
     * @param bufferedImage
     * @param angle
     * @return
     */
    private static BufferedImage rotate(BufferedImage bufferedImage, int angle) {
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        image = graphics.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
        graphics.dispose();
        graphics = image.createGraphics();
        graphics.rotate(Math.toRadians(angle), width / 2, height / 2);
        graphics.drawImage(bufferedImage, 0, 0, null);
        graphics.dispose();
        return image;
    }

    /**
     * 无损旋转
     * @param bufferedImage
     * @param angle
     * @return
     */
    public static BufferedImage rotateLossLess(BufferedImage bufferedImage, int angle) {
        int width = bufferedImage.getWidth(null);
        int height = bufferedImage.getHeight(null);
        Rectangle rectangle = CalcRotatedSize(new Rectangle(new Dimension(width, height)), angle);
        BufferedImage image = new BufferedImage(rectangle.width, rectangle.height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
//        image = graphics.getDeviceConfiguration().createCompatibleImage(rectangle.width, rectangle.height, Transparency.TRANSLUCENT);
        image = getTranslucent(rectangle.width, rectangle.height);
        graphics.dispose();
        graphics = image.createGraphics();
        graphics.translate((rectangle.width - width) / 2, (rectangle.height - height) / 2);
        graphics.rotate(Math.toRadians(angle), width / 2, height / 2);
        graphics.drawImage(bufferedImage, null, null);
        return image;
    }

    /**
     * 获取蒙版
     * @param width
     * @param height
     * @return
     */
    public static BufferedImage getTranslucent(int width,int height) {
        return new BufferedImage(1,1,BufferedImage.TYPE_3BYTE_BGR).createGraphics().getDeviceConfiguration().createCompatibleImage(width,height,Transparency.TRANSLUCENT);
    }

    public static Rectangle CalcRotatedSize(Rectangle src, int angel) {
        // 如果旋转的角度大于90度做相应的转换
        if (angel >= 90) {
            if (angel / 90 % 2 == 1) {
                int temp = src.height;
                src.height = src.width;
                src.width = temp;
            }
            angel = angel % 90;
        }

        double r = Math.sqrt(src.height * src.height + src.width * src.width) / 2;
        double len = 2 * Math.sin(Math.toRadians(angel) / 2) * r;
        double angel_alpha = (Math.PI - Math.toRadians(angel)) / 2;
        double angel_dalta_width = Math.atan((double) src.height / src.width);
        double angel_dalta_height = Math.atan((double) src.width / src.height);

        int len_dalta_width = (int) (len * Math.cos(Math.PI - angel_alpha
                - angel_dalta_width));
        int len_dalta_height = (int) (len * Math.cos(Math.PI - angel_alpha
                - angel_dalta_height));
        int des_width = src.width + len_dalta_width * 2;
        int des_height = src.height + len_dalta_height * 2;
        return new Rectangle(new Dimension(des_width, des_height));
    }

    public static BufferedImage getImg(String string) throws IOException {
        return getImg(new File(string));
    }

    private static BufferedImage getImg(File file) throws IOException {
        return ImageIO.read(file);
    }

    /**
     * 清理格式信息
     * @param bufferedImage
     * @return
     */
    private static BufferedImage cleanFormat(BufferedImage bufferedImage) {
        BufferedImage image = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D graphics = image.createGraphics();
        graphics.drawImage(bufferedImage, 0, 0, null);
        graphics.dispose();
        return image;
    }

    private static boolean writeJpg(BufferedImage image, String path) throws IOException {
        return ImageIO.write(image, jpg, new File(path));
    }

    private static boolean writePng(BufferedImage image, String path) throws IOException {
        return ImageIO.write(image, png, new File(path));
    }

}
