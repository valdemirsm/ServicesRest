#1 - Para começa a utilizar JPA temos que adicionar o spring-boot-starter-data-jpa e o driver mysql mysql-connector-java no pom.xml. Adicionar a string de conexão no arquivo application.properties.

#2 - Geramos o mapeamento das entidades

#3 - Insere automaticamente dados na tabela, para isso criar o arquivo com o nome import.sql na pasta resource, apenas isso.

#4 - Para printar o sql adicionamos spring.jpa.show-sql no application.properties.

#5 - Para testar podemos usar a classe implementada CadastroCarroMain.java