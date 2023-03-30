import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class CreateSticker {

    
    public static void criaSticker(InputStream inputStream, String nomeArquivo) throws Exception {

        // faz a leitura da imagem
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        // cria nova imagem com transparência e com tamanho novo
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 20;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        // copia a imagem original pra nova imagem 
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        // configura a fonte
        var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 38);
        graphics.setColor(Color.BLUE);
        graphics.setFont(fonte);

        // escreve uma frase na nova imagem
        graphics.drawString("Veja no ErikaFlixx", 100, novaAltura - 10);

        // escreve a nova imagem em um arquivo
        ImageIO.write(novaImagem, "png", new File(nomeArquivo));

    }


}
