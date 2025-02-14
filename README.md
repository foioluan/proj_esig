[JAVA_BADGE]: https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white
[JSF_BADGE]: https://img.shields.io/badge/JSF-007396?style=for-the-badge&logo=java&logoColor=white

<h1 align="center" style="font-weight: bold;"> Projeto eSig (Processo Seletivo) 💻</h1>

![java][JAVA_BADGE]
![jsf][JSF_BADGE]

<p align="left">
 • <a href="#about">Sobre</a> 
 • <a href="#started">Instruções</a>
</p>

Projeto desenvolvido em Java, utilizando as técnologias: Javax EE, Tomcat, Java JPA, JSF e Primefaces. O projeto consiste em uma aplicação WEB para o processo seletivo da vaga Pessoa Desenvolvedora Java (Estágio) na eSig Group.
Funções da aplicação:

- Criação, leitura, atualização e remoção de atividades (Tasks);
- Criação e leitura de usuários (User);
- Sistema de login utilizando segurança de filtros na url;
- Testes de persistência foram feitos utilizando o JUnit Jupiter (Podem ser encontrados em src/test/java/projEsig/PersistenceTests.java);

<h2 id="started">🚀 Intruções de uso</h2>

1. Após clonar o projeto, execute o servidor e acesse:

```bash
http://localhost:8080/projEsig/pages/login.xhtml
```

2. Você não conseguirá acessar nenhuma URL sem executar o login corretamente. Caso queira logar como Admin utilize essas credenciais:
   - Email: luan.vfv@gmail.com
   - Senha: 2005

3. Ao acessar o site como Admin você terá acesso às funções de criar, ler, editar e remover tarefas. Caso crie um novo usuário, você apenas conseguirá ler tarefas para este usuário específico.

4. O site támbem contém uma busca avançada de tarefas, podendo utilizar qualquer informção relevante como parâmetro. 

<h3>Clonando</h3>

```bash
git clone https://github.com/foioluan/proj_esig.git
```
