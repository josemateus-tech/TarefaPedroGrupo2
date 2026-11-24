# IF Sistema — Sistema de Liberação de Alunos

Aplicação web acadêmica com Java 17, Spring Boot, Thymeleaf, Spring Data JPA e MySQL.

## Requisitos
- JDK 17 ou superior
- Maven 3.9+
- MySQL 8+
- IDE (IntelliJ, Eclipse ou NetBeans com suporte Maven)

## Banco de dados
1. Inicie o MySQL.
2. Execute `banco/schema.sql` no MySQL Workbench.
3. Abra `src/main/resources/application.properties`.
4. Informe seu usuário e senha do MySQL nas propriedades `spring.datasource.username` e `spring.datasource.password`.

O Hibernate cria/atualiza as tabelas com `ddl-auto=update`. O script SQL cria apenas o banco.

## Executar
Na pasta do projeto:
```bash
mvn spring-boot:run
```
Acesse http://localhost:8080

Também é possível abrir o `pom.xml` pela IDE como projeto Maven e executar `SistemaApplication`.

## Funcionalidades incluídas
- Dashboard com contagens reais do banco.
- Cadastro, listagem, edição e exclusão de usuários.
- Cadastro/listagem de turmas.
- Solicitação de liberação por aluno e indicação de professor.
- Aprovação e recusa de solicitações.
- Registro de saída e retorno pela portaria.

## Observações importantes
- Esta é uma base acadêmica funcional para desenvolvimento e demonstração, não um sistema pronto para uso institucional.
- A autenticação, autorização real por perfil, recuperação de senha, geração/validação de QR Code e auditoria ainda precisam ser implementadas antes de uso real.
- Não utilize dados pessoais reais de estudantes em repositório público.
- Nunca publique senhas do banco ou credenciais. Configure-as localmente.

## Git / GitHub
O projeto deve ser versionado na raiz desta pasta. Consulte as instruções de integração fornecidas junto com o arquivo compactado.
