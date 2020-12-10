package codigo;

import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;


public class codigo {

    private WebDriver navegador;
    private String tmp;



    @Dado("configurei ambiente para acessar URL Chrome")
    public void configurei_ambiente_para_acessar_URL_Chrome() {
        // Write code here that turns the phrase above into concrete actions

        System.setProperty("webdriver.chrome.driver", "/Users/leandrobrum/autoMaven/chromedriver");

    }


    @Quando("^abri tela WebMotors$")
    public void abreSimulador() {

        navegador = new ChromeDriver();
        navegador.get("https://www.webmotors.com.br/");
        navegador.manage().window().maximize();

    }

    @Quando("^abri pagina da loja$")
    public void abrePaginaSimulador() {

        navegador = new ChromeDriver();
        navegador.get("https://www.webmotors.com.br/carros/estoque/?IdRevendedor=3834764&TipoVeiculo=carros&anunciante=concession%C3%A1ria%7Cloja");
        navegador.manage().window().maximize();

    }

    @Entao("^valido que retornou o estoque$")
    public void printEstoque() {
        By searchId = By.xpath("//*[@id=\"root\"]/main/div[1]/div[3]/div[2]/div/div[1]/div/div[1]/div/div[2]/a[1]");

        WebDriverWait wait = new WebDriverWait(navegador, 20);

        wait.until(visibilityOfElementLocated(searchId));

        Screenshot.tirar(navegador, "src/test/java/screenshot/Versao Pesquisada " + Generator.dataHoraArquivo() + ".png");

        navegador.close();

    }


    @Quando("^abri a tela de buscas$")
    public void abreBuscas() throws InterruptedException {

        By searchId = By.xpath("//*[@id=\"WhiteBox\"]/div[1]/div[2]/div/div/a");

        WebDriverWait wait = new WebDriverWait(navegador, 20);

        WebElement botaoverofertas =wait.until(visibilityOfElementLocated(searchId));

        wait.until(visibilityOfElementLocated(searchId));


        botaoverofertas.click();

    }

    @Quando("^validar buscar carro por marca, pesquisando menu esquerdo modelo, versao e validando retorno das pesquisas$")
    public void buscapormarcanovo () throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(navegador, 20);


        By search = By.xpath("//*[@id=\"WhiteBox\"]/div[1]/div[2]/div/div/a");

        WebElement clic = wait.until(visibilityOfElementLocated(search));

        //aguardo campo de buscas carregar na tela para clicar.
        wait.until(visibilityOfElementLocated(search));

        clic.click();

        String[] marca = {"Honda","Chevrolet","Fiat","Ford","Hyundai","Mitsubishi","Renault","Toyota","Volkswagen" };
        String[] modelo = {"City","Astra","Palio","Focus","Elantra","Pajero","Logan","Corolla","Golf" };
        String versao = "a";

        //entro no loop para digitar e pesquisar as marcas definidas acima.
        for(int i = 0; i < marca.length; i++) {

            By searchId = By.xpath("//*[@id=\"searchBar\"]");
            //aguardo apareceer o campo de pesquisa
            wait.until(visibilityOfElementLocated(searchId));

            //digito no nome da marcar para pesquisa
            navegador.findElement(By.xpath("//*[@id=\"searchBar\"]")).sendKeys(marca[i]);

            //aguardo aparece o nome  da marca
            wait.until(visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/header/div[2]/div/div/div[1]/a/div[2]/strong")));

            //clico na marca
            navegador.findElement(By.xpath(("//*[@id=\"root\"]/header/div[2]/div/div/div[1]/a/div[2]/strong"))).click();

            //aguardo apareceer os carros da pesquisa
            wait.until(visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/main/div[1]/div[3]/div[2]/div/div[1]/div/div[2]/div/div[2]/a[1]")));

            //gravo o texto que tem o nome da marca
            String validacao1 = navegador.findElement(By.xpath("//*[@id=\"root\"]/main/div[1]/div[3]/h1")).getText();

            //valido se retornou a marca pesquisada
            Assert.assertTrue(validacao1.contains(marca[i]));
            Screenshot.tirar(navegador, "src/test/java/screenshot/Marca Pesquisada " + Generator.dataHoraArquivo() + ".png");
            //clico no campo todos os modelos
            navegador.findElement(By.xpath("//*[@id=\"root\"]/main/div[1]/div[2]/div/div[1]/div[2]/div[2]/div/form/div[3]/div[2]/div[2]/div[2]")).click();


            By buscaModelo = By.xpath("//*[@id=\"root\"]/main/div[1]/div[2]/div/div[3]/div/div[4]/div/input");
            //aguardo apareceer o campo de pesquisa
            wait.until(visibilityOfElementLocated(buscaModelo));

            //digito o modelo
            navegador.findElement(buscaModelo).sendKeys(modelo[i]);

            //clico no modelo
            navegador.findElement(By.xpath("//*[@id=\"root\"]/main/div[1]/div[2]/div/div[3]/div/div[5]/a")).click();


            //aguardo apareceer os carros da pesquisa
            wait.until(visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/main/div[1]/div[3]/div[2]/div/div[1]/div/div[2]/div/div[2]/a[1]")));

            //valido se retornou a marca pesquisada
            Assert.assertTrue(validacao1.contains(marca[i]));

            //clico em todas versoes
            navegador.findElement(By.xpath("//*[@id=\"root\"]/main/div[1]/div[2]/div/div[1]/div[2]/div[2]/div/form/div[3]/div[2]/div[2]/div[3]")).click();

            //digito gasolina
            navegador.findElement(By.xpath("//*[@id=\"root\"]/main/div[1]/div[2]/div/div[2]/div/div[3]/div/input")).sendKeys(versao);

            //clico na primeira versão
            navegador.findElement(By.xpath("//*[@id=\"root\"]/main/div[1]/div[2]/div/div[2]/div/div[4]/a[1]")).click();

            //aguardo apareceer os carros da pesquisa
            wait.until(visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/main/div[1]/div[3]/div[2]/div/div[1]/div/div[1]/div/div[2]/a[1]")));

            //valido se retornou a marca pesquisada
            Assert.assertTrue(validacao1.contains(marca[i]));
            Screenshot.tirar(navegador, "src/test/java/screenshot/Versao Pesquisada " + Generator.dataHoraArquivo() + ".png");
        }

    }


    @Quando("^validar buscar moto por marca$")
    public void buscamotopormarcanovo () throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(navegador, 30);

        By search = By.xpath("//*[@id=\"WhiteBox\"]/div[1]/div[2]/div/div/a");

        WebElement clic = wait.until(visibilityOfElementLocated(search));

        //aguardo campo de buscas carregar na tela para clicar.
        wait.until(visibilityOfElementLocated(search));

        clic.click();

        By motos = By.xpath("//*[@id=\"root\"]/main/div[1]/div[2]/div/div[1]/div[2]/div[1]/a[2]");
        //aguardo apareceer o icone motos
        wait.until(visibilityOfElementLocated(motos));
        //clico em motos
        navegador.findElement(motos).click();

        String[] marca = {"Bmw","Dafra","Ducati","Harley-davidson","Honda","Kasinski","Kawasaki","Suzuki" };
        String[] modelo = {"F 650","Apache","848 Evo","Fat Boy","125","150","Ninja","Bandit" };


        //entro no loop para digitar e pesquisar as marcas definidas acima.
        for(int i = 0; i < marca.length; i++) {

            By searchId=By.xpath("//*[@id=\"searchBar\"]");
            //aguardo apareceer o campo de pesquisa
            wait.until(visibilityOfElementLocated(searchId));

            //digito no nome da marcar para pesquisa
            navegador.findElement(By.xpath("//*[@id=\"searchBar\"]")).sendKeys(marca[i]);

            //aguardo aparece o nome  da marca
            wait.until(visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/header/div[2]/div/div/div[1]/a/div[2]/strong")));

            //clico na marca
            navegador.findElement(By.xpath(("//*[@id=\"root\"]/header/div[2]/div/div/div[1]/a/div[2]/strong"))).click();

            //aguardo apareceer as motos da pesquisa
            wait.until(visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/main/div[1]/div[3]/div[2]/div/div[1]/div/div[2]/div/div[2]/a[1]")));

            //gravo o texto que tem o nome da marca
            String validacao1=navegador.findElement(By.xpath("//*[@id=\"root\"]/main/div[1]/div[3]/h1")).getText();

            //valido se retornou a marca pesquisada
            Assert.assertTrue(validacao1.contains(marca[i]));

        }

    }


    @Quando("^clicar em marcas menu esquerdo$")
    public void clicarmenuesquerdo(){
        WebDriverWait wait = new WebDriverWait(navegador, 30);
        String[] marca = {"Bmw","Dafra","Ducati","Harley-davidson","Honda","Kasinski","Kawasaki","Suzuki" };
        String[] modelo = {"F 650","Apache","848 Evo","Fat Boy","125","150","Ninja","Bandit" };


        for(int i = 0; i < marca.length; i++) {

            //clico em ver todas marcas
            navegador.findElement(By.xpath("//*[@id=\"root\"]/main/div[1]/div[2]/div/div[1]/div[2]/div[2]/div/form/div[3]/div[3]")).click();

            //digito a marca no campo de pesquisa
            navegador.findElement(By.xpath("//*[@id=\"root\"]/main/div[1]/div[2]/div/div[4]/div/div[4]/div/input")).clear();
            navegador.findElement(By.xpath("//*[@id=\"root\"]/main/div[1]/div[2]/div/div[4]/div/div[4]/div/input")).sendKeys(marca[i]);

            //clico na lista com a marca
            navegador.findElement(By.xpath("//*[@id=\"root\"]/main/div[1]/div[2]/div/div[4]/div/div[5]/a")).click();


            //clico no campo todos os modelos
            navegador.findElement(By.xpath("//*[@id=\"root\"]/main/div[1]/div[2]/div/div[1]/div[2]/div[2]/div/form/div[3]/div[2]/div[2]/div[2]")).click();


            By buscaModelo=By.xpath("//*[@id=\"root\"]/main/div[1]/div[2]/div/div[3]/div/div[4]/div/input");
            //aguardo apareceer o campo de pesquisa
            wait.until(visibilityOfElementLocated(buscaModelo));

            //digito o modelo
            navegador.findElement(buscaModelo).sendKeys(modelo[i]);

            //clico no modelo
            navegador.findElement(By.xpath("//*[@id=\"root\"]/main/div[1]/div[2]/div/div[3]/div/div[5]/a")).click();


            //aguardo apareceer motos da pesquisa
            wait.until(visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/main/div[1]/div[3]/div[1]/p")));

            //valido se retornou a marca pesquisada
         //  Assert.assertTrue(buscaModelo.contains(marca[i]));
        }

    }

    @Quando("^validar buscar carro por modelo$")
    public void buscapormodelo () throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(navegador, 20);

        String[] marca = {"Astra","Palio","Focus","Civic","Elantra","Pajero","Logan","Corolla","Golf" };

        //entro no loop para digitar e pesquisar as marcas definidas acima.
        for(int i = 0; i < marca.length; i++) {

            By searchId = By.xpath("//*[@id=\"searchBar\"]");
            //aguardo apareceer o campo de pesquisa
            wait.until(visibilityOfElementLocated(searchId));

            //digito no nome da marcar para pesquisa
            navegador.findElement(By.xpath("//*[@id=\"searchBar\"]")).sendKeys(marca[i]);

            //aguardo aparece o nome  da marca
            wait.until(visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/header/div[2]/div/div/div[1]/a/div[2]/strong")));

            //clico na marca
            navegador.findElement(By.xpath(("//*[@id=\"root\"]/header/div[2]/div/div/div[1]/a/div[2]/strong"))).click();

            //aguardo apareceer o campo de pesquisa
            wait.until(visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/main/div[1]/div[3]/div[2]/div/div[1]/div/div[2]/div/div[2]/a[1]")));

            //gravo o texto que tem o nome da marca
            String validacao1 = navegador.findElement(By.xpath("//*[@id=\"root\"]/main/div[1]/div[3]/h1")).getText();

            //valido se retornou a marca pesquisada
            Assert.assertTrue(validacao1.contains(marca[i]));
            Screenshot.tirar(navegador, "src/test/java/screenshot/Modelo Pesquisado " + Generator.dataHoraArquivo() + ".png");

        }

    }


    @Quando("^validar buscar moto por modelo$")
    public void buscamotopormodelo () throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(navegador, 20);



        By search = By.xpath("//*[@id=\"WhiteBox\"]/div[1]/div[2]/div/div/a");

        WebElement clic = wait.until(visibilityOfElementLocated(search));

        //aguardo campo de buscas carregar na tela para clicar.
        wait.until(visibilityOfElementLocated(search));

        clic.click();

        By motos = By.xpath("//*[@id=\"root\"]/main/div[1]/div[2]/div/div[1]/div[2]/div[1]/a[2]");
        //aguardo apareceer o icone motos
        wait.until(visibilityOfElementLocated(motos));
        //clico em motos
        navegador.findElement(motos).click();

        String[] marca = {"650","Apache","Evo","Fat Boy","125","150","Ninja","Bandit" };

        //entro no loop para digitar e pesquisar as marcas definidas acima.
        for(int i = 0; i < marca.length; i++) {

            By searchId = By.xpath("//*[@id=\"searchBar\"]");
            //aguardo apareceer o campo de pesquisa
            wait.until(visibilityOfElementLocated(searchId));

            //digito no nome da marcar para pesquisa
            navegador.findElement(By.xpath("//*[@id=\"searchBar\"]")).sendKeys(marca[i]);

            //aguardo aparece o nome  da marca
            wait.until(visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/header/div[2]/div/div/div[1]/a/div[2]/strong")));

            //clico na marca
            navegador.findElement(By.xpath(("//*[@id=\"root\"]/header/div[2]/div/div/div[1]/a/div[2]/strong"))).click();

            //aguardo apareceer o campo de pesquisa
            wait.until(visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/main/div[1]/div[3]/div[2]/div/div[1]/div/div[2]/div/div[2]/a[1]")));

            //gravo o texto que tem o nome da marca
            String validacao1 = navegador.findElement(By.xpath("//*[@id=\"root\"]/main/div[1]/div[3]/h1")).getText();

            //valido se retornou a marca pesquisada
            Assert.assertTrue(validacao1.contains(marca[i]));
            Screenshot.tirar(navegador, "src/test/java/screenshot/Marca_Pesquisada " + Generator.dataHoraArquivo() + ".png");


        }

    }


    @Quando("^valida a busca por marca$")
    public void validaMarcas() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(navegador, 20);


        By searchId = By.xpath("//*[@id=\"root\"]/main/div[1]/div[2]/div/div[1]/div[2]/div[2]/div/form/div[3]/div[2]/div/div/a[1]/small");

        WebElement clicMarca = wait.until(visibilityOfElementLocated(searchId));

        wait.until(visibilityOfElementLocated(searchId));

        clicMarca.click();

        System.out.println("teste");


    }

    @Quando("^abriu tela$")
    public void abriuTela() throws InterruptedException {
        //classe que valida se abriu a pesquisa pela marca selecionada.
        WebDriverWait wait = new WebDriverWait(navegador, 20);

        By searchId = By.xpath("//*[@id=\"root\"]/main/div[1]/div[3]/div[2]/div/div[1]/div/div[1]/div/div[1]/div/div[1]/a[1]/img");

        wait.until(visibilityOfElementLocated(searchId));

        System.out.println(searchId);

        String validacao1 = navegador.findElement(By.xpath("//*[@id=\"root\"]/main/div[1]/div[3]/h1")).getText();

        System.out.println(validacao1);

        tmp = ("Novos e Usados");

        Assert.assertTrue(validacao1.contains(tmp));

    }
    @Entao("^validacao efetuada e fecho a tela$")
    public void fechoatela() throws InterruptedException {

    navegador.quit();

    }




}
