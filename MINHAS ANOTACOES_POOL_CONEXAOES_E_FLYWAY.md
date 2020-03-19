#Para configurar o pool de conexões no spring, precisamos adicionar a propriedade que defino o maximo de pool de 
conexões no arquivo application.properties
spring.datasource.hikari.maximum-pool-size=5

#Podemos tambem definir o minimo de pool de conexões.
spring.datasource.hikari.minimum-idle=3.

#Podemos tambem definir o tempo maximo que as conexões vão ficar ociosas.
spring.datasource.hikari.idle-timeout=10000

--------------------------
#Flyway é uma feramenta de versionamento de banco de dados, para utilizar precisa adicionar a seguinte
dependencia.
<dependency>
	<groupId>org.flywaydb</groupId>
	<artifactId>flyway-core</artifactId>
</dependency>

#Segundo passo é adicionar o repositorio de arquivos em src/main/resources/db/migration.
Dentro do repositorio podemos adicionar nossos arquivos de script, os arquivos devem seguir no seguinte padrão 
V001_criacao-inicial.sql
V002_adicionando-campos.sql

#Podemos criar arquivo callback, ou seja, depois que todos os arquivos de migração, roda esse arquivo.
Para isso no mesmo repositorio de versionamento devo criar o arquivo afterMigrate.sql

#Em agumas situações só queremos executar o arquivo afterMigrate.sql em ambiente de dev, para isso, podemos adicionar no
arquivo de propriedade a seguinte linha
spring.flyway.locations=classpath:db/migration,classpath:db/testedata



#Podemos gerar um arquivo DDL(sql) de todas as entidades, para isso podemos adicionar as seguintes propriedades:
spring.jpa.properties.javax.persistence.schema-generation.scripts.action=create
spring.jpa.properties.javax.persistence.schema-generation.scripts.create-target=src/main/resorces/ddl.sql
