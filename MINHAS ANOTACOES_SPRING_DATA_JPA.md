#Para usar Spring Data Jpa precisa adicionar a dependencia 
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

--------------------------------------------------------------
#Para começar usar precisamos apenas de uma interface que implemente JpaRepository, em tempo de execução é implementado 
esse interface.
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
}

----------------------------------------------------------------
#Keywords para definir nomes de métodos na interface, ao informar nome de metodos seguindo as palavras
chaves definidas, esse métodos serão implementadados em tempo de execução. A lista de palavras chaves é encontrado 
aqui https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation

--------------------------------------------------------------------
#Caso queira declarar na interface repository um método que não tenha no spring data jpa, podemo declarar da seguinte
forma. O bind do nome dos parametros é automaricamete caso o nome seja o mesmo, caso contrario deve colocar @Param.
@Query("from Cliente where nome like %:nome% and id = :id")
List<Cliente> consultarClientePorNome(String nome, @Param("id") Long clienteId);

--------------------------------------------------------
#É possível que as query fique no arquivo orm.xml, para isso o arquivo deve ficar em src/main/resources/orm.xml

--------------------------------------------------------
#Para que o spring data jpa identifique uma implementação da interface repository, o nome da classe de implementação
tem que ter o mome da interface repository e terminar com Impl. Lembrando que a implementação é automatico, não precisamos
de informar implements.
@Repository
public class ClienteRepositoryImpl {
	@PersistenceContext
	private EntityManager manager;
}

-----------------------------------------------
Spring possui a classe StringUtils que possíu varios métodos estatico para validar objetos como StringUtils.isEmpty("")

-----------------------------------------------
#Podemos criar um repository customatizado e não usar o JpaRepository. Para usar um novo repository precisa informar
ao spring que existe um novo repository, adicionando a anotação @EnableJpaRepositories
@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class AlgafoodApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(AlgafoodApiApplication.class, args);
	}
}