import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;


public class App {
    public static void main(String[] args) throws Exception {

        //CONEXÃO HTTP
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();

        //builder com endereço da URI + GET
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString()); //doc

        //armazena o JSON em string
        String body = response.body();

        //INSTANCIAÇÃO DE OBJETO PARA CHAMADA DE CLASSE COM MÉTODO PARSE
        JsonParser parser = new JsonParser(); 
        List<Map<String, String>> listaFilmes = parser.parse(body);
        //System.out.println(listaFilmes.size());
        //System.out.println(listaFilmes.get(0));

        //cria diretorio para saida
        var diretorio = new File("FigurinhasFinal/" );
        diretorio.mkdir();

        //EXIBIÇÃO
        for (Map<String,String> filme : listaFilmes) {
            System.out.println(filme.get("title"));
            System.out.println(filme.get("image"));
            System.out.println(filme.get("imDbRating"));
        
            String urlImagem = filme.get("image");
            String titulo = filme.get("title");

            InputStream inputStream = new URL(urlImagem).openStream();
            String nomeArquivo = titulo + ".png";

            CreateSticker.criaSticker(inputStream, nomeArquivo);

            System.out.println(titulo);
            System.out.println();
        }
    }
}
