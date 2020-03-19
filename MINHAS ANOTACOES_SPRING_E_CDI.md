#O seguinte comando empacota o projeto spring usando maven, gerar um arquivo .jar que possui servidor tomcat embutido, permitindo que possa ser executado de qualquer lugar. Para executar vai por linha de comando até raiz do projeto onde tem um arquivo mvnw e executa o seguinte comando.
./mvnw package 

--------------------------------------------------------------------------
#Para exetutar o jar do projeto, va até a pasta target onde encontra se o jar e executa o segunte comando.
java -jar algafood-api-0.0.1-SNAPSHOT.jar

-------------------------------------------
#Dependencia abaixo permite que todas vez que altera alguma coisa, copila e restarta automaticamente.
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-devtools</artifactId>
</dependency>

-------------------------------------------------
"String IoC Contrainer" é resposavel por injeção de dependencia no spring.

-------------------------------------------------
#Para tornar um classe ou seja um bean gerenciável pelo spring, que permite injeção deve usar a seguinte anotação na classe.
@Component

-------------------------------------------------
#Classe definida com a anotação possui o metodo main e indica que todas as classes apartir desse pacote podem ser gerenciadas pelo spring, ou seja, poderam ser injetadas, caso tenha tenha a anotação @Component
@SpringBootApplication

---------------------------------------------------
#Primeira forma de injeção é receber o objeto no contrutor.
public Notificacao notificador;
public PrimeiroController(Notificacao notificacao) {
	this.notificador = notificacao;
}

-------------------------------------------
#A anotação pode ser colocado em métodos, construtor ou no atributo.
@Autowired
public Notificacao notificador;

---------------------------
#"required = false" cria uma independencia opcional, ou seja, é possível objeto Notificacao ser nulo, por exemplo se a classe Notificacao não tiver anotado com @Component esse objeto fica nulo.
@Autowired(required = false)
public Notificacao notificador;

-------------------------------------
#Ambiguidade entre beans, acontece quando por exemplo temos uma interface A e duas classes B e C, quando injetamos a interface e chamamos um método, o spring não sabe qual implementação chamar, a solução seria anotar uma das implementações com a seguinte anotação.
@Primary

#Outra forma de resolver problema de ambiguidade é definir um qualificador para cada implementação e onde for chamar escolhe qual qualificador chamar.
#PRIMEIRA IMPLEMENTAÇÃO
@Qualifier("email")
public class NotificadorEmail  implements Notificador
#SEGUNDA IMPLEMENTAÇÃO
@Qualifier("cliente")
public class NotificadorCliente implements Notificador 
#CHAMA IMPLEMENTAÇÃO
@Qualifier("email")
public Notificador notificador;

#A mesma forma acima, mas usando qualificador criado.
#PRIMEIRA IMPLEMENTAÇÃO
@TipoDoNotificador(TipoNotificador.EMAIL)
public class NotificadorEmail  implements Notificador
#SEGUNDA IMPLEMENTAÇÃO
@TipoDoNotificador(TipoNotificador.SMS)
public class NotificadorCliente implements Notificador 
#CHAMA IMPLEMENTAÇÃO
@TipoDoNotificador(TipoNotificador.EMAIL)
public Notificador notificador;

-----------------------------------------------
#Cria uma anotação.
@Retention(RetentionPolicy.RUNTIME) // ser encontrado em tempo de execucao
@Qualifier
public @interface TipoDoNotificador {
	TipoNotificador value();
}

--------------------------
#Usando spring profile que permite iniciar o sistema com comportamento diferente. A classe que deve ter um comportamento diferente recebe por exemplo a anotação @Profile("prod") para produção ou @Profile("dev") para desenvolvimento. Para que uma das classe seja iniciada pelo container deve informar a propriedade spring.profiles.active=dev no arquivo application.properties para iniciar e acessar a classe de desemvolvimento.

--------------------------------
#A propriedade pode receber mais de um value, informando que todas as classes que tiver o profile definido pelos informado serão iniciadas pelo container spring. 
spring.profiles.active=prod,mysql,filesystem

---------------------------
#Criando padrão observer, ou seja, disparando um evento para o ouvinte capiturar.
ApplicationEventPublisher #interface do spring usado para disparar eventos.
ClienteAtivadoEvent #meu objeto evento.
@EventListener #anota o metodo que será o observador, capitura o evento disparado.

@Component
public class NotificadorClienteService {
	@Autowired
	private ApplicationEventPublisher eventPublisher;
	public void ativar(Cliente cliente) {
		eventPublisher.publishEvent(new ClienteAtivadoEvent(cliente));
	}
}

public class ClienteAtivadoEvent {
	private Cliente cliente;
	public ClienteAtivadoEvent(Cliente cliente) {
		this.cliente = cliente;
	}
	public Cliente getCliente() {return cliente;}
}

@Component
public class AtivandoCliente {
	@EventListener
	public void  ativando(ClienteAtivadoEvent event) {
		System.out.println("Cliente ativado" + event.getCliente());
	}
}

-------------------------------------------------------
#No application.properties pode adicionar novas propriedades ou usar as propriedade fornecidas pelo spring. Para ver todas as propriedades disponíveis acesso o seguinte link.
https://docs.spring.io/spring-boot/docs/current/reference/html/appendix-application-properties.html

#As propriedades definidas no arquivo application.properties podem ser iniciadas ou sobrescrita por linha de comando, como no exemplo onde alterador a porta. Lembrando que não pode ter espaços.
java -jar target/algafood-api-0.0.1-SNAPSHOT.jar --server.port=9180

#Essas propriedades também podem ser definidas em variáveis de ambiente. Em linux usamos o seguinte comando para definir variáveis de ambiente.
export SERVER_PORT=8181

#Para ver o valor da variável usamos o comando.
echo $SERVER_PORT

#Para acesso uma propriedade definida no application.properties usamos a seguinte anotação em um atributo da classe.
@Value("${notificador.email.porta-servidor}")
private Integer porta;

#O correto para acessar as propriedades de um arquivo de propriedade não é anotar um atributo como mostado acima. Correto é criar uma classe de propriedade, caso o nome do atributo da classe for o mesmo da propriedade do arquivo pega o valor do arquivo, caso contrario pega o valor default definido na classe. Para acessar as propriedade de um arquivo precisa anotar a classe com @ConfigurationProperties. A classe abaixo acessaria a propriedade notificador.email.porta-servidor
@Component
@ConfigurationProperties("notificador.email")
public class NotificadorProperties {
	private Integer portaServidor = 9090;
	public Integer getPortaServidor() {
		return portaServidor;
	}
	public void setPortaServidor(Integer portaServidor) {
		this.portaServidor = portaServidor;
	}
}

#Podemos criar arquivos de propriedade para por exemplo acesso a produção e desenvolvimento, como application-dev e appication-prod. Para indicar qual dos utilizar colocamos no arquivo de propriedade default application.properties a seguinte propriedade.
spring.profiles.active=dev


