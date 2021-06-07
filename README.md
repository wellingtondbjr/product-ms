# product-ms
## Catálogo de produtos

Neste microserviço é possível criar, alterar, visualizar e excluir um determinado produto, além de visualizar a lista de produtos atuais disponíveis. Também deve ser possível realizar a busca de produtos filtrando por name, description e price.


### Execução do serviço

#### Para a correta execução do serviço a máquina em que ele será executado deve obedecer as seguintes dependências

- Dependências
  - JDK 1.8
  - Mysql Server 8 configurado com um usuário que tenha permissões de DBA ou DBManager
  - Maven command line (caso prefira gerar o jar utilizando a linha de comando ao invés de utilizar uma IDE como o STS ou o Intellij)
    - Guia de instalação official: https://maven.apache.org/install.html
      - Um guia mais detalhado para o windows pode ser encontrado em https://mkyong.com/maven/how-to-install-maven-in-windows/
      
#### Após a instalação das dependências devem ser executados os passos abaixo:

- Clonar ou fazer o download do projeto: https://github.com/wellingtondbjr/product-ms 
- Setar as informações de usuário e senha do usuário do Mysql no arquivo: "product-ms\src\main\resources\application-dev.yaml"
	- caso o banco não seja o localhost a propriedade spring datasource url deve ser alterada
- Criar a database challenge no MySQL
```sql
CREATE DATABASE `challenge` /* DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci*/;
```
- Criar a tabela product utilizando o script sql abaixo:
```sql
    CREATE TABLE challenge.`product` (
      `id` varchar(255) NOT NULL,
      `description` varchar(255) DEFAULT NULL,
      `name` varchar(255) DEFAULT NULL,
      `price` double DEFAULT NULL,
      PRIMARY KEY (`id`),
      KEY `IDXjmivyxk9rmgysrmsqw15lqr5b` (`name`),
      KEY `IDXq2n3melweyrl5d4rqkg7pq6ra` (`description`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```
    
- Gerar o jar do projeto utilizando uma IDE ou entrando na pasta raiz do projeto e executando o comando abaixo:
  ```
  	mvn clean package spring-boot:repackage 
  ```
- Executar o jar do projeto com o comando
  ``` 
  	java -jar product-ms-1.0.0.jar 
