# IF Sistema — Sistema de Liberação de Alunos

Aplicação web acadêmica desenvolvida com Java 17, Spring Boot, Thymeleaf, Spring Data JPA e MySQL.

## Requisitos

- JDK 17 ou superior.
- Maven 3.9 ou superior.
- MySQL 8 ou superior.
- IDE com suporte a projetos Maven (NetBeans, IntelliJ IDEA ou Eclipse).

## Banco de dados

1. Inicie o servidor MySQL.
2. Abra o MySQL Workbench.
3. Execute o arquivo `banco/schema.sql`.
4. Confira se o banco de dados foi criado.
5. Configure as credenciais do MySQL por meio de variáveis de ambiente.

O Hibernate utiliza `ddl-auto=update` para criar e atualizar as tabelas automaticamente. O script SQL cria o banco de dados.

### Configuração das credenciais

O arquivo `src/main/resources/application.properties` utiliza a variável de ambiente `DB_PASSWORD`.

No Windows, abra o Prompt de Comando e configure a senha antes de iniciar a aplicação:

```cmd
set DB_PASSWORD=sua_senha_do_mysql
```

Substitua `sua_senha_do_mysql` pela senha utilizada no seu MySQL.

O usuário do banco deve ser configurado em `spring.datasource.username`, no arquivo `application.properties`.

**Importante:** nunca publique senhas ou credenciais no GitHub.

## Executar o projeto

Na pasta raiz do projeto, execute:

```bash
mvn spring-boot:run
```

No Windows, caso o Maven não esteja configurado no PATH, também é possível utilizar o Maven integrado ao NetBeans.

Acesse pelo navegador:

http://localhost:8080

Também é possível abrir o arquivo `pom.xml` pela IDE como projeto Maven e executar a classe `SistemaApplication`.

## Funcionalidades incluídas

- Dashboard com contagens reais do banco de dados.
- Cadastro, listagem, edição e exclusão de usuários.
- Cadastro e listagem de turmas.
- Solicitação de liberação por aluno e indicação de professor.
- Aprovação e recusa de solicitações.
- Registro de saída e retorno pela portaria.

## Observações importantes

- Esta é uma base acadêmica funcional para desenvolvimento e demonstração, não um sistema pronto para uso institucional.
- Autenticação, autorização real por perfil, recuperação de senha, geração e validação de QR Code e auditoria ainda precisam ser implementadas antes do uso institucional.
- Não utilize dados pessoais reais de estudantes em repositórios públicos.
- Nunca publique senhas do banco de dados ou outras credenciais.
- Cada integrante deve configurar suas próprias credenciais localmente.

## Git / GitHub

O projeto deve ser versionado na raiz desta pasta.

Repositório do projeto:

https://github.com/josemateus-tech/TarefaPedroGrupo2
